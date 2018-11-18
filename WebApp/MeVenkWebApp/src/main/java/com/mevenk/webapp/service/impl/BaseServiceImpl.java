/**
 *
 */
package com.mevenk.webapp.service.impl;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mevenk.webapp.dao.BaseDao;
import com.mevenk.webapp.entity.audit.ApplicationException;
import com.mevenk.webapp.service.BaseService;
import com.mevenk.webapp.to.ApplicationExceptionTO;
import com.mevenk.webapp.util.MeVenkWebAppUtil;

/**
 * @author venky
 *
 */
@Service
public class BaseServiceImpl implements BaseService {

	private static final Logger LOG = getLogger(BaseServiceImpl.class);

	@Autowired
	private BaseDao baseDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.mevenk.webapp.service.BaseService#databaseTime()
	 */
	@Override
	@Transactional(readOnly = true, propagation = SUPPORTS)
	public Date databaseTime() {
		return baseDao.databaseTime();
	}

	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true, propagation = SUPPORTS)
	public String databaseTimeFormatted() {
		return baseDao.databaseTimeFormatted();
	}

	@Override
	@Transactional(propagation = REQUIRED)
	public ApplicationExceptionTO saveApplicationException(Throwable throwable, HttpServletRequest request,
			HttpSession session) {

		ApplicationException applicationException = new ApplicationException();
		applicationException.setCreatedDate(new Date());
		applicationException.setExceptionClass(throwable.getClass());
		applicationException.setStackTrace(MeVenkWebAppUtil.exceptionStactTraceAsString(throwable));
		applicationException.setHttpSessionId(session.getId());
		applicationException.setUid("UID");
		int exceptionId = (int) baseDao.saveApplicationException(applicationException);
		LOG.info("Exception Id generated:{}", exceptionId);
		return new ApplicationExceptionTO(exceptionId, throwable.getMessage());
	}

}
