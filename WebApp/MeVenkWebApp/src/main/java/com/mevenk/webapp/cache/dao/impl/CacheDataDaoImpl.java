/**
 *
 */
package com.mevenk.webapp.cache.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mevenk.webapp.cache.dao.CacheDataDao;
import com.mevenk.webapp.cache.dao.repository.MessageSourceRepository;
import com.mevenk.webapp.entity.cache.MessageSource;

/**
 * @author venky
 *
 */
@Repository
public class CacheDataDaoImpl implements CacheDataDao {

	@Autowired
	private MessageSourceRepository messageSourceRepository;

	@Override
	public Iterable<MessageSource> getMessageSource() {

		return messageSourceRepository.findAll();
	}

	@Override
	public Iterable<MessageSource> getMessageSource(int messageId) {

		return messageSourceRepository.findByMessageId(messageId);

	}

	@Override
	public Iterable<Integer> getMessageIds() {

		return messageSourceRepository.getMessageIds();
	}

}
