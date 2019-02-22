package com.mevenk.webapp.to.user;

import com.mevenk.webapp.to.BaseTO;

public final class UserTO extends BaseTO {

	private static final long serialVersionUID = -6770166009731500159L;

	public static enum LoginStatus {
		SUCCESS, INCORRECT_PASSWORD, USER_NOT_AVAILABLE;
	}

	private Integer userIdentificationNumber;
	private String uid;
	private Integer locale;
	private Integer role;
	private String fullName;
	private LoginStatus loginStatus;

	public UserTO() {
	}

	public UserTO(UserTO.LoginStatus loginStatus) {
		this.loginStatus = loginStatus;
	}

	public UserTO(Integer userIdentificationNumber, String uid, Integer locale, Integer role, String fullName) {
		this.userIdentificationNumber = userIdentificationNumber;
		this.uid = uid;
		this.locale = locale;
		this.role = role;
		this.fullName = fullName;
		loginStatus = LoginStatus.SUCCESS;
	}

	public final Integer getUserIdentificationNumber() {
		return userIdentificationNumber;
	}

	public final String getUid() {
		return uid;
	}

	public final Integer getLocale() {
		return locale;
	}

	public final Integer getRole() {
		return role;
	}

	public final String getFullName() {
		return fullName;
	}

	public final UserTO.LoginStatus getLoginStatus() {
		return loginStatus;
	}
}