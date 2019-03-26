/**
 * 
 */
package org.mevenk.utils.git.report.log;

import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.filter.CommitTimeRevFilter;
import org.eclipse.jgit.revwalk.filter.RevFilter;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
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
	 * @param abbreviateCommitLength
	 * @param revFilter
	 * @param outputStreamDiff
	 * @return
	 * @throws Exception
	 */
	private static final LinkedHashSet<GitLogData> generateLogReport(Repository repository, String tree,
			int abbreviateCommitLength, RevFilter revFilter, OutputStream outputStreamDiff) throws Exception {

		boolean difffWriteRequired = outputStreamDiff != null;
		Git git = null;
		DiffFormatter diffFormatter = null;

		try {

			git = new Git(repository);

			LOG.info("Reading logs for " + tree);

			String treeName = "remotes/origin/" + tree;

			ObjectId objectIdTree = repository.resolve(treeName);
			if (objectIdTree == null || objectIdTree.getName() == null) {
				throw new IllegalArgumentException("Nothing found for" + tree);
			}

			diffFormatter = difffWriteRequired ? new DiffFormatter(outputStreamDiff)
					: new DiffFormatter(DisabledOutputStream.INSTANCE);
			diffFormatter.setRepository(repository);
			diffFormatter.setDiffComparator(RawTextComparator.DEFAULT);
			diffFormatter.setDetectRenames(true);

			Iterable<RevCommit> revCommits = git.log().add(objectIdTree).setRevFilter(revFilter).call();

			LinkedHashSet<GitLogData> gLogDatas = new LinkedHashSet<GitLogData>();
			LinkedHashSet<GitDiffData> gitDiffDatas = null;

			for (RevCommit commit : revCommits) {

				PersonIdent authordent = commit.getAuthorIdent();
				ObjectId objectIdRevCommit = commit.getId();

				RevCommit commitParent = null;
				try {
					commitParent = commit.getParent(0);
				} catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
					commitParent = null;
					continue;
				}

				List<DiffEntry> diffEntries = diffFormatter.scan(commitParent.getTree(), commit.getTree());
				gitDiffDatas = new LinkedHashSet<GitDiffData>(diffEntries.size());
				for (DiffEntry diff : diffEntries) {
					String changeType = diff.getChangeType().name();
					String oldPath = diff.getOldPath();
					String newPath = diff.getNewPath();
					gitDiffDatas.add(new GitDiffData(changeType, null, oldPath, newPath));
					if (difffWriteRequired) {
						diffFormatter.format(diff);
					}
				}

				gLogDatas.add(new GitLogData(objectIdRevCommit.name(),
						objectIdRevCommit.abbreviate(abbreviateCommitLength).name(), authordent.getName(),
						authordent.getEmailAddress(), authordent.getWhen(), commit.getFullMessage())
								.addGitDiffDatas(gitDiffDatas));
			}

			return gLogDatas;

		} finally {
			diffFormatter.close();
			git.close();
		}

	}

	/**
	 * 
	 * @param gitDir
	 * @param tree
	 * @param abbreviateCommitLength
	 * @param since
	 * @param until
	 * @param outputStreamDiff
	 * @return
	 * @throws Exception
	 */
	public static final LinkedHashSet<GitLogData> generateLogReport(File gitDir, String tree,
			int abbreviateCommitLength, Date since, Date until, OutputStream outputStreamDiff) throws Exception {

		Repository repository = new FileRepositoryBuilder().setGitDir(gitDir).setMustExist(true).build();
		RevFilter between = CommitTimeRevFilter.between(since, until);

		return generateLogReport(repository, tree, abbreviateCommitLength, between, outputStreamDiff);

	}

}