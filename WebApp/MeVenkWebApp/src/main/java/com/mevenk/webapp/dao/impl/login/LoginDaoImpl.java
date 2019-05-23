/**
 *
 */
package com.mevenk.webapp.dao.impl.login;

import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mevenk.webapp.dao.login.LoginDao;
import com.mevenk.webapp.dao.repository.UserVWRepository;
import com.mevenk.webapp.entity.user.UserVW;

/**
 * @author venky
 *
 */
@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private UserVWRepository userVWRepository;

	@Override
	@Transactional(readOnly = true, propagation = SUPPORTS)
	public Long getUidCount(String uid) {
		return userVWRepository.getCountByUid(uid);
	}

	@Override
	@Transactional(readOnly = true, propagation = SUPPORTS)
	public String getUserPassword(String uid) {

		return uid;
	}

	@Override
	@Transactional(readOnly = true, propagation = SUPPORTS)
	public UserVW loginUser(String uid) {
		return userVWRepository.findByUid(uid);
	}

}
