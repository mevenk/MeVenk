/**
 *
 */
package com.mevenk.webapp.entity.cache;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mevenk.webapp.entity.BaseEntity;

/**
 * @author venky
 *
 */
@Entity
@Table(name = "MESSAGE_SOURCE")
public class MessageSource extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 3879695996103414447L;

	@Id
	@Column(name = "MESSAGE_KEY", insertable = false)
	private Integer messageKey;

	@Column(name = "MESSAGE_ID", insertable = false)
	private Integer messageId;

	@Column(name = "LOCALE_ID", insertable = false)
	private Integer localeId;

	@Column(name = "MESSAGE", insertable = false)
	private String message;

	/**
	 * @return the messageKey
	 */
	public final Integer getMessageKey() {
		return messageKey;
	}

	/**
	 * @param messageKey the messageKey to set
	 */
	public final void setMessageKey(Integer messageKey) {
		this.messageKey = messageKey;
	}

	/**
	 * @return the messageId
	 */
	public final Integer getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public final void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the localeId
	 */
	public final Integer getLocaleId() {
		return localeId;
	}

	/**
	 * @param localeId the localeId to set
	 */
	public final void setLocaleId(Integer localeId) {
		this.localeId = localeId;
	}

	/**
	 * @return the message
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public final void setMessage(String message) {
		this.message = message;
	}

}
