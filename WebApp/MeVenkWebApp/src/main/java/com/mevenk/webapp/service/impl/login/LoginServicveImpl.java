/**
 *
 */
package com.mevenk.webapp.service.impl.login;

import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mevenk.webapp.dao.login.LoginDao;
import com.mevenk.webapp.entity.user.UserVW;
import com.mevenk.webapp.service.login.LoginService;
import com.mevenk.webapp.to.user.UserLoginTO;
import com.mevenk.webapp.to.user.UserTO;

/**
 * @author venky
 *
 */
@Service
public class LoginServicveImpl implements LoginService {

	private static final Logger LOG = getLogger(LoginServicveImpl.class);

	@Autowired
	private LoginDao loginDao;

	@Override
	public UserTO loginUser(UserLoginTO userLoginTO) {

		UserVW userVW = loginDao.loginUser(userLoginTO.getUid());
		LOG.debug("UserVW:", userVW);
		return new UserTO(userVW.getUserIdentificationNumber(), userVW.getUid(), userVW.getLocale(), userVW.getRole(),
				userVW.getFullName());
	}

	@Override
	public boolean isValidUId(String uid) {
		Long uidCount = loginDao.getUidCount(uid);
		LOG.debug("Uid count:", uidCount);
		return uidCount != null && uidCount == 1;
	}

	@Override
	public boolean isValidPassword(String uid, String password) {
		return loginDao.getUserPassword(uid).contentEquals(password);
	}

}
