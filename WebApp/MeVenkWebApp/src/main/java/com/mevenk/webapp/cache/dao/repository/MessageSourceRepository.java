/**
 * 
 */
package com.mevenk.webapp.cache.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mevenk.webapp.entity.cache.MessageSource;

/**
 * @author vkolisetty
 *
 */
public interface MessageSourceRepository extends CrudRepository<MessageSource, Integer> {

	Iterable<MessageSource> findByMessageId(Integer messageId);

	@Query("SELECT DISTINCT(messageId) FROM MessageSource")
	Iterable<Integer> getMessageIds();

}
