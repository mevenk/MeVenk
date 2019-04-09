/**
 * 
 */
package org.mevenk.utils.git.report.log;

import static org.mevenk.utils.helper.MeVenkUtilsHelper.LINE_SEPARATOR;
import static org.mevenk.utils.helper.MeVenkUtilsHelper.writeToStream;

import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.eclipse.jgit.util.io.DisabledOutputStream;
import org.mevenk.utils.git.report.log.data.GitDiffData;
import org.mevenk.utils.git.report.log.data.GitLogData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vkolisetty
 *
 */
public class GitLogReport {

	private static final Logger LOG = LoggerFactory.getLogger(GitLogReport.class);

	public static final SimpleDateFormat SIMPLE_DATE_FORMAT_COMMIT_TIME = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 
	 * @param repository
	 * @param tree
	 * @param abbreviatedCommitLength
	 * @param revFilter
	 * @param outputStreamDiff
	 * @return
	 * @throws Exception
	 */
	private static final LinkedHashSet<GitLogData> generateLogReport(Repository repository, String tree,
			int abbreviatedCommitLength, RevFilter revFilter, OutputStream outputStreamDiff) throws Exception {
		Git git = null;
		DiffFormatter diffFormatter = null;

		try {

			git = new Git(repository);

			LOG.info("Reading logs for " + tree);

			ObjectId objectIdTree = repository.resolve(tree);
			if (objectIdTree == null || objectIdTree.getName() == null) {
				throw new IllegalArgumentException("Nothing found for" + tree);
			}

			diffFormatter = getDiffFormatter(repository, outputStreamDiff);

			Iterable<RevCommit> revCommits = getRevCommits(revFilter, git, objectIdTree);

			return generateGitLogs(abbreviatedCommitLength, outputStreamDiff, diffFormatter, revCommits);

		} finally {
			if (diffFormatter != null) {
				diffFormatter.close();
			}
			if (git != null) {
				git.close();
			}
		}

	}

	/**
	 * @param revFilter
	 * @param git
	 * @param objectIdTree
	 * @return
	 * @throws GitAPIException
	 * @throws NoHeadException
	 * @throws MissingObjectException
	 * @throws IncorrectObjectTypeException
	 */
	private static Iterable<RevCommit> getRevCommits(RevFilter revFilter, Git git, ObjectId objectIdTree)
			throws GitAPIException, NoHeadException, MissingObjectException, IncorrectObjectTypeException {
		return git.log().add(objectIdTree).setRevFilter(revFilter).call();
	}

	/**
	 * 
	 * @param abbreviatedCommitLength
	 * @param outputStreamDiff
	 * @param diffFormatter
	 * @param revCommits
	 * @return
	 * @throws Exception
	 */
	private static LinkedHashSet<GitLogData> generateGitLogs(int abbreviatedCommitLength, OutputStream outputStreamDiff,
			DiffFormatter diffFormatter, Iterable<RevCommit> revCommits) throws Exception {

		boolean diffWriteRequired = outputStreamDiff != null && !(outputStreamDiff instanceof DisabledOutputStream);

		GitLogData gitLogData = null;
		LinkedHashSet<GitLogData> gitLogDatas = new LinkedHashSet<GitLogData>();
		LinkedHashSet<GitDiffData> gitDiffDatas = null;

		for (RevCommit commit : revCommits) {

			PersonIdent authordent = commit.getAuthorIdent();
			ObjectId objectIdRevCommit = commit.getId();

			RevCommit commitParent = null;
			try {
				commitParent = commit.getParent(0);
			} catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
				commitParent = null;
			}

			gitLogData = new GitLogData(objectIdRevCommit.name(),
					objectIdRevCommit.abbreviate(abbreviatedCommitLength).name(), authordent.getName(),
					authordent.getEmailAddress(), authordent.getWhen(), commit.getFullMessage());

			if (diffWriteRequired) {
				writeToStream("@@@@@@@@@@@@@@@@@@@@@@@@@" + LINE_SEPARATOR + LINE_SEPARATOR, outputStreamDiff);

				writeToStream("@@ commit " + gitLogData.getAbbreviatedCommit() + "	[" + gitLogData.getCommit() + "] @@"
						+ LINE_SEPARATOR, outputStreamDiff);
				writeToStream("@@ Author: " + gitLogData.getAuthorName() + " <" + gitLogData.getAuthorEmail() + "> @@"
						+ LINE_SEPARATOR, outputStreamDiff);
				writeToStream("@@ Date: " + gitLogData.getWhen() + " @@" + LINE_SEPARATOR, outputStreamDiff);

				writeToStream(LINE_SEPARATOR + LINE_SEPARATOR, outputStreamDiff);
			}

			RevTree treeParent = commitParent != null ? commitParent.getTree() : null;
			List<DiffEntry> diffEntries = diffFormatter.scan(treeParent, commit.getTree());
			gitDiffDatas = new LinkedHashSet<GitDiffData>(diffEntries.size());
			for (DiffEntry diff : diffEntries) {
				String changeType = diff.getChangeType().name();
				String oldPath = diff.getOldPath();
				String newPath = diff.getNewPath();
				gitDiffDatas.add(new GitDiffData(changeType, null, oldPath, newPath));
				if (diffWriteRequired) {
					diffFormatter.format(diff);
				}
			}

			gitLogDatas.add(gitLogData.addGitDiffDatas(gitDiffDatas));
		}

		return gitLogDatas;
	}

	/**
	 * 
	 * @param repository
	 * @param outputStreamDiff
	 * @return
	 */
	private static DiffFormatter getDiffFormatter(Repository repository, OutputStream outputStreamDiff) {
		DiffFormatter diffFormatter;
		diffFormatter = outputStreamDiff != null ? new DiffFormatter(outputStreamDiff)
				: new DiffFormatter(DisabledOutputStream.INSTANCE);
		diffFormatter.setRepository(repository);
		diffFormatter.setDiffComparator(RawTextComparator.DEFAULT);
		diffFormatter.setDetectRenames(true);
		return diffFormatter;
	}

	/**
	 * 
	 * @param gitDir
	 * @param tree
	 * @param abbreviatedCommitLength
	 * @param maxNoOfCommits
	 * @param outputStreamDiff
	 * @return
	 * @throws Exception
	 */
	public static final LinkedHashSet<GitLogData> generateLogReport(File gitDir, String tree,
			int abbreviatedCommitLength, int maxNoOfCommits, OutputStream outputStreamDiff) throws Exception {

		Repository repository = GitLogRepository.getRepository(gitDir);
		RevFilter maxCount = GitLogFilter.maxNoOfCommits(maxNoOfCommits);

		return generateLogReport(repository, tree, abbreviatedCommitLength, maxCount, outputStreamDiff);

	}

	/**
	 * 
	 * @param gitDir
	 * @param tree
	 * @param abbreviatedCommitLength
	 * @param since
	 * @param until
	 * @param outputStreamDiff
	 * @return
	 * @throws Exception
	 */
	public static final LinkedHashSet<GitLogData> generateLogReport(File gitDir, String tree,
			int abbreviatedCommitLength, Date since, Date until, OutputStream outputStreamDiff) throws Exception {

		Repository repository = GitLogRepository.getRepository(gitDir);
		RevFilter between = GitLogFilter.between(since, until);

		return generateLogReport(repository, tree, abbreviatedCommitLength, between, outputStreamDiff);

	}

}
