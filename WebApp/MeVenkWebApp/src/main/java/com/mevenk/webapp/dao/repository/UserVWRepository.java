/**
 * 
 */
package com.mevenk.webapp.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mevenk.webapp.entity.user.UserVW;

/**
 * @author vkolisetty
 *
 */
public interface UserVWRepository extends CrudRepository<UserVW, Integer> {

	@Query("SELECT COUNT(UV) FROM UserVW UV WHERE UV.uid = :uid")
	Long getCountByUid(@Param("uid") String uid);

	UserVW findByUid(String uid);

}
