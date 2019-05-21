/**
 * 
 */
package com.mevenk.webapp.cache.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mevenk.webapp.entity.cache.MessageSource;

/**
 * @author vkolisetty
 *
 */
@Repository
public interface MessageSourceRepository extends CrudRepository<MessageSource, Integer> {

	List<MessageSource> findByMessageId(Integer messageId);

	@Query("SELECT DISTINCT(messageId) FROM MessageSource")
	List<Integer> getMessageIds();

}
