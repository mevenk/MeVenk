/**
 *
 */
package com.mevenk.webapp.config.spring.messagesource;

import static java.lang.Integer.parseInt;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.support.AbstractMessageSource;

/**
 * @author venky
 *
 */
public class StaticDataMessageSource extends AbstractMessageSource {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.context.support.AbstractMessageSource#resolveCode(java.
	 * lang.String, java.util.Locale)
	 */
	@Override
	protected MessageFormat resolveCode(String errorCode, Locale locale) {
		int messageCategoryId;
		try {
			messageCategoryId = parseInt(errorCode);
		} catch (NumberFormatException e) {
			return null;
		}
		String message = MessageSourceStaticData.getMessage(messageCategoryId, 1);
		return createMessageFormat(message, locale);
	}
}
