/**
 *
 */
package com.mevenk.webapp.entity.cache;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author venky
 *
 */
@Entity
@Table(name = "MESSAGE_SOURCE")
public class MessageSource implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3879695996103414447L;

	@Id
	@Column(name = "MESSAGE_ID", insertable = false)
	private Integer messageId;

	@Column(name = "MESSAGE_CATEGORY_ID", insertable = false)
	private Integer messageCategoryId;

	@Column(name = "LOCALE_ID", insertable = false)
	private Integer localeId;

	@Column(name = "MESSAGE", insertable = false)
	private String message;

	/**
	 * @return the messageId
	 */
	public final Integer getMessageId() {
		return messageId;
	}

	/**
	 * @return the messageCategoryId
	 */
	public final Integer getMessageCategoryId() {
		return messageCategoryId;
	}

	/**
	 * @return the localeId
	 */
	public final Integer getLocaleId() {
		return localeId;
	}

	/**
	 * @return the message
	 */
	public final String getMessage() {
		return message;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MessageSource [messageId=" + messageId + ", messageCategoryId=" + messageCategoryId + ", localeId="
				+ localeId + ", message=" + message + "]";
	}

}
