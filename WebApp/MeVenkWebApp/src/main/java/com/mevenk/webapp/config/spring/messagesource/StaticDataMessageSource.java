/**
 *
 */
package com.mevenk.webapp.config.spring.messagesource;

import static java.lang.Integer.parseInt;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.support.AbstractMessageSource;

import com.mevenk.webapp.util.HTTPUtil;

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
		if(errorCode.equals("typeMismatch")) {
			return createMessageFormat("Not an expected type", locale);
		}
		int messageId;
		try {
			messageId = parseInt(errorCode);
		} catch (NumberFormatException e) {
			return null;
		}
		String message = MessageSourceStaticData.getMessage(messageId, HTTPUtil.getUserFromSession().getLocale());
		return createMessageFormat(message, locale);
	}
}
