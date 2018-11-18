/**
 *
 */
package com.mevenk.webapp.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mevenk.webapp.to.ApplicationExceptionTO;

/**
 * @author venky
 *
 */
public interface BaseService {

	public Date databaseTime();

	public String databaseTimeFormatted();

	public ApplicationExceptionTO saveApplicationException(Throwable throwable, HttpServletRequest request,
			HttpSession session);

}
