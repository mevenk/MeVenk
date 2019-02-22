package com.mevenk.webapp.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_VW")
public class UserVW implements Serializable {

	private static final long serialVersionUID = -6459561896350043478L;

	@Id
	@Column(name = "USER_IDENTIFICATION_NUMBER", insertable = false)
	private Integer userIdentificationNumber;
	@Column(name = "UID", insertable = false)
	private String uid;
	@Column(name = "LOCALE", insertable = false)
	private Integer locale;
	@Column(name = "ROLE", insertable = false)
	private Integer role;
	@Column(name = "FULL_NAME", insertable = false)
	private String fullName;

	public final Integer getUserIdentificationNumber() {
		return userIdentificationNumber;
	}

	public final void setUserIdentificationNumber(Integer userIdentificationNumber) {
		this.userIdentificationNumber = userIdentificationNumber;
	}

	public final String getUid() {
		return uid;
	}

	public final void setUid(String uid) {
		this.uid = uid;
	}

	public final Integer getLocale() {
		return locale;
	}

	public final void setLocale(Integer locale) {
		this.locale = locale;
	}

	public final Integer getRole() {
		return role;
	}

	public final void setRole(Integer role) {
		this.role = role;
	}

	public final String getFullName() {
		return fullName;
	}

	public final void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String toString() {
		return "UserVW [userIdentificationNumber=" + userIdentificationNumber + ", uid=" + uid + ", locale=" + locale
				+ ", role=" + role + ", fullName=" + fullName + "]";
	}
}