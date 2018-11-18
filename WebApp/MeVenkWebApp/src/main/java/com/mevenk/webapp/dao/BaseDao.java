/**
 *
 */
package com.mevenk.webapp.dao;

import java.io.Serializable;
import java.util.Date;

import com.mevenk.webapp.entity.audit.ApplicationException;

/**
 * @author venky
 *
 */
public interface BaseDao {

	public Date databaseTime();

	public String databaseTimeFormatted();

	public Serializable saveApplicationException(ApplicationException applicationException);

}
