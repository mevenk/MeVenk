/**
 *
 */
package com.mevenk.webapp.config.spring.messagesource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mevenk.webapp.util.MeVenkWebAppUtil;

/**
 * @author venky
 *
 */
public final class MessageSourceStaticData {

	public static final int LOCALE_ID_ENGLISH = 0;
	public static final int LOCALE_ID_HINDI = 1;

	private static Map<Integer, Map<Integer, String>> messagesMasterData = new HashMap<>();

	/**
	 *
	 * @throws IllegalAccessException
	 */
	private MessageSourceStaticData() throws IllegalAccessException {
		throw MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

	/**
	 * @param messagesMasterData the messagesMasterData to set
	 * @throws IllegalAccessException
	 */
	public static final void setMessagesMasterData(Map<Integer, Map<Integer, String>> messagesMasterData)
			throws IllegalAccessException {
		if (!MessageSourceStaticData.messagesMasterData.isEmpty()) {
			throw new IllegalAccessException("Messages master data already added");
		}

		Iterator<Map<Integer, String>> iteratorMessages = messagesMasterData.values().iterator();
		while (iteratorMessages.hasNext()) {

			Iterator<Integer> iteratorMessageIds = iteratorMessages.next().keySet().iterator();

			while (iteratorMessageIds.hasNext()) {
				Integer localeId = iteratorMessageIds.next();

				Iterator<Map<Integer, String>> iteratorMessagesInnerLoop = messagesMasterData.values().iterator();
				while (iteratorMessagesInnerLoop.hasNext()) {

					if (!iteratorMessagesInnerLoop.next().keySet().contains(localeId)) {
						throw new IllegalArgumentException(localeId + " not added to all messages ids");
					}

				}

			}

		}

		MessageSourceStaticData.messagesMasterData = messagesMasterData;
	}

	/**
	 *
	 * @param localeId
	 * @param messages
	 * @throws IllegalAccessException
	 */
	public static final void setMessages(Integer messageId, Map<Integer, String> messages)
			throws IllegalAccessException {

		if (messagesMasterData.keySet().contains(messageId)) {
			throw new IllegalAccessException("Messages already added for " + messageId);
		}

		messagesMasterData.put(messageId, messages);

	}

	/**
	 *
	 * @param localeId
	 * @param messageId
	 * @return
	 */
	public static String getMessage(int messageId, int localeId) {
		return messagesMasterData.get(messageId).get(localeId);
	}

	/**
	 *
	 * @param errorCode
	 * @return
	 */
	public static String getMessage(int messageId) {
		return getMessage(messageId, 0);
	}

}
