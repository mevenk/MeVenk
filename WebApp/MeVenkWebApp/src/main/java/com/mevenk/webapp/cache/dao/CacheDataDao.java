/**
 *
 */
package com.mevenk.webapp.cache.dao;

import java.util.List;

import com.mevenk.webapp.entity.cache.MessageSource;

/**
 * @author venky
 *
 */
public interface CacheDataDao {

	public List<MessageSource> getMessageSource();

	public List<MessageSource> getMessageSource(int messageId);

	public List<Integer> getMessageIds();

}
