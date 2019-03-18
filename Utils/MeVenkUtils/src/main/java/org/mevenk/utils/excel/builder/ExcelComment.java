/**
 * 
 */
package org.mevenk.utils.excel.builder;

/**
 * @author vkolisetty
 *
 */
public class ExcelComment {

	private String comment;
	private String author;
	private boolean visible;

	/**
	 * @param comment
	 */
	public ExcelComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @param comment
	 * @param author
	 */
	public ExcelComment(String comment, String author) {
		this.comment = comment;
		this.author = author;
	}

	/**
	 * @param comment
	 * @param visible
	 */
	public ExcelComment(String comment, boolean visible) {
		this.comment = comment;
		this.visible = visible;
	}

	/**
	 * @param comment
	 * @param author
	 * @param visible
	 */
	public ExcelComment(String comment, String author, boolean visible) {
		this.comment = comment;
		this.author = author;
		this.visible = visible;
	}

	/**
	 * @return the comment
	 */
	public final String getComment() {
		return comment;
	}

	/**
	 * @return the author
	 */
	public final String getAuthor() {
		return author;
	}

	/**
	 * @return the visible
	 */
	public final boolean isVisible() {
		return visible;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExcelComment [comment=" + comment + ", author=" + author + ", visible=" + visible + "]";
	}

}
