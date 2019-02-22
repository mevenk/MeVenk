/**
 *
 */
package com.mevenk.webapp.service.login;

import com.mevenk.webapp.to.user.UserLoginTO;
import com.mevenk.webapp.to.user.UserTO;

/**
 * @author venky
 *
 */
public interface LoginService {

	public boolean isValidUId(String uid);

	public boolean isValidPassword(String uid, String password);

	public UserTO loginUser(UserLoginTO userLoginTO);

}
