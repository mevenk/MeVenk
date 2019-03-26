/**
 * 
 */
package org.mevenk.utils.git.report.log.data;

/**
 * @author vkolisetty
 *
 */
public class GitDiffData {

	public static enum ChangeType {
		ADD, MODIFY, DELETE, RENAME, COPY;
	}

	private ChangeType changeType;
	private String fileMode;
	private String oldPath;
	private String newPath;

	/**
	 * @param changeType
	 * @param fileMode
	 * @param oldPath
	 * @param newPath
	 */
	public GitDiffData(String changeType, String fileMode, String oldPath, String newPath) {
		this.changeType = ChangeType.valueOf(changeType);
		this.fileMode = fileMode;
		this.oldPath = oldPath;
		this.newPath = newPath;
	}

	/**
	 * @return the changeType
	 */
	public final ChangeType getChangeType() {
		return changeType;
	}

	/**
	 * @return the fileMode
	 */
	public final String getFileMode() {
		return fileMode;
	}

	/**
	 * @return the oldPath
	 */
	public final String getOldPath() {
		return oldPath;
	}

	/**
	 * @return the newPath
	 */
	public final String getNewPath() {
		return newPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GitDiffData [changeType=" + getChangeType() + ", fileMode=" + fileMode + ", oldPath=" + oldPath
				+ ", newPath=" + newPath + "]";
	}

}
