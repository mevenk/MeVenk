/**
 *
 */
package com.mevenk.webapp.cache.dao;

import com.mevenk.webapp.entity.cache.MessageSource;

/**
 * @author venky
 *
 */
public interface CacheDataDao {

	public Iterable<MessageSource> getMessageSource();

	public Iterable<MessageSource> getMessageSource(int messageId);

	public Iterable<Integer> getMessageIds();

}
