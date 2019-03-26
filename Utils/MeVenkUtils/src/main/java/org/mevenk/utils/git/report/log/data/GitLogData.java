/**
 * 
 */
package org.mevenk.utils.git.report.log.data;

import java.util.Date;
import java.util.LinkedHashSet;

/**
 * @author vkolisetty
 *
 */
public class GitLogData {

	private String commit;
	private String abbreviatedCommit;
	private String authorName;
	private String authorEmail;
	private Date when;
	private String commitFullMessage;
	private LinkedHashSet<GitDiffData> gitDiffDatas;

	/**
	 * 
	 * @param commit
	 * @param abbreviatedCommit
	 * @param authorName
	 * @param authorEmail
	 * @param when
	 * @param commitFullMessage
	 */
	public GitLogData(String commit, String abbreviatedCommit, String authorName, String authorEmail, Date when,
			String commitFullMessage) {
		this.commit = commit.trim();
		this.abbreviatedCommit = abbreviatedCommit.trim();
		this.authorName = authorName.trim();
		this.authorEmail = authorEmail.trim();
		this.when = when;
		this.commitFullMessage = commitFullMessage.trim();
	}

	/**
	 * @return the gitDiffDatas
	 */
	public final LinkedHashSet<GitDiffData> getGitDiffDatas() {
		return gitDiffDatas;
	}

	/**
	 * @return the commit
	 */
	public final String getCommit() {
		return commit;
	}

	/**
	 * @return the abbreviatedCommit
	 */
	public final String getAbbreviatedCommit() {
		return abbreviatedCommit;
	}

	/**
	 * @return the authorName
	 */
	public final String getAuthorName() {
		return authorName;
	}

	/**
	 * @return the authorEmail
	 */
	public final String getAuthorEmail() {
		return authorEmail;
	}

	/**
	 * @return the when
	 */
	public final Date getWhen() {
		return when;
	}

	/**
	 * @return the commitFullMessage
	 */
	public final String getCommitFullMessage() {
		return commitFullMessage;
	}

	/**
	 * 
	 * @param gitDiffDatas
	 * @return
	 */
	public final GitLogData addGitDiffDatas(LinkedHashSet<GitDiffData> gitDiffDatas) {
		this.gitDiffDatas = gitDiffDatas;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GitLogData [commit=" + commit + ", abbreviatedCommit=" + abbreviatedCommit + ", authorName="
				+ authorName + ", authorEmail=" + authorEmail + ", when=" + when + ", commitFullMessage="
				+ commitFullMessage + ", gitDiffDatas=" + gitDiffDatas + "]";
	}

}
