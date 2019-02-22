/**
 *
 */
package com.mevenk.webapp.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mevenk.webapp.entity.BaseEntity;

/**
 * @author venky
 *
 */
@Entity
@Table(name = "USER_PASSWORD")
public class UserPasswordVW extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 162068159166457256L;

	@Id
	@Column(name = "UID", insertable = false)
	private String uid;

	@Column(name = "PASSWORD", insertable = false)
	private String password;

	/**
	 * @return the uid
	 */
	public final String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public final void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

}
