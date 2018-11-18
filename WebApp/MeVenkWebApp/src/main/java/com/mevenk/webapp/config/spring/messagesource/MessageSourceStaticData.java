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

	private static Map<Integer, Map<Integer, String>> messagesMasterData = new HashMap<>();

	/**
	 *
	 * @throws IllegalAccessException
	 */
	private MessageSourceStaticData() throws IllegalAccessException {
		throw MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

	/**
	 *
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
	 * @param messageCategoryId
	 * @param localeId
	 * @return
	 */
	public static String getMessage(int messageCategoryId, int localeId) {

		return messagesMasterData.get(messageCategoryId).get(localeId);

	}

	/**
	 *
	 * @param messageCategoryId
	 * @return
	 */
	public static String getMessage(int messageCategoryId) {
		return getMessage(messageCategoryId, 0);
	}

}
