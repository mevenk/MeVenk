/**
 * 
 */
package com.mevenk.webapp.exception.login;

import org.springframework.validation.BindingResult;

import com.mevenk.webapp.to.user.UserLoginTO;

/**
 * @author venky
 *
 */
public class LoginUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5464646727428320998L;

	private UserLoginTO userLoginTO;
	private BindingResult bindingResult;

	/**
	 * @param userLoginTO
	 * @param bindingResult
	 */
	public LoginUserException(Exception exception, UserLoginTO userLoginTO, BindingResult bindingResult) {
		super(exception);
		this.userLoginTO = userLoginTO;
		this.bindingResult = bindingResult;
	}

	/**
	 * @return the userLoginTO
	 */
	public UserLoginTO getUserLoginTO() {
		return userLoginTO;
	}

	/**
	 * @return the bindingResult
	 */
	public BindingResult getBindingResult() {
		return bindingResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoginUserException [uid=" + userLoginTO.getUid() + ", no of validator errors="
				+ bindingResult.getErrorCount() + "]" + super.toString();
	}

}
