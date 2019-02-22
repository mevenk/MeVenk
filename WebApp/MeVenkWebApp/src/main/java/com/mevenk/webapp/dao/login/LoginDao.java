/**
 *
 */
package com.mevenk.webapp.dao.login;

import com.mevenk.webapp.entity.user.UserVW;

/**
 * @author venky
 *
 */
public interface LoginDao {

	public Long getUidCount(String uid);

	public String getUserPassword(String uid);

	public UserVW loginUser(String uid);
}
