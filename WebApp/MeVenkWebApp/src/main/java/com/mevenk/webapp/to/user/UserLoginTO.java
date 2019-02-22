/**
 * 
 */
package com.mevenk.webapp.to.user;

import com.mevenk.webapp.to.BaseTO;

/**
 * @author venky
 *
 */
public class UserLoginTO extends BaseTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -606797331307584076L;
	private String uid;
	private String password;

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
