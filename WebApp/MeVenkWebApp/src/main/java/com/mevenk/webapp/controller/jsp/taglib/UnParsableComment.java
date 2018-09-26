/**
 *
 */
package com.mevenk.webapp.controller.jsp.taglib;

import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.logging.log4j.Logger;

/**
 * @author venky
 *
 */
public class UnParsableComment extends BodyTagSupport {

	private static final Logger LOG = getLogger(UnParsableComment.class);

	private String commentId;
	private String simpleComment;

	/**
	 * @return the commentId
	 */
	public String getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	/**
	 * @return the simpleComment
	 */
	public String getSimpleComment() {
		return simpleComment;
	}

	/**
	 * @param simpleComment the simpleComment to set
	 */
	public void setSimpleComment(String simpleComment) {
		this.simpleComment = simpleComment;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -823782674774542256L;

	@Override
	public int doStartTag() throws JspException {

		try {
			LOG.debug(commentId + "|" + simpleComment);
			pageContext.getOut().println("");
		} catch (IOException ioException) {
			throw new JspException("Exception reading  un parsable comment: " + ioException.getMessage());
		}
		return EVAL_PAGE;
	}
}
