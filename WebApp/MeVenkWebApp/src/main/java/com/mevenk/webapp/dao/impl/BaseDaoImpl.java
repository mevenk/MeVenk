/**
 *
 */
package com.mevenk.webapp.dao.impl;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mevenk.webapp.dao.BaseDao;
import com.mevenk.webapp.entity.audit.ApplicationException;

/**
 * @author venky
 *
 */
@Repository
public class BaseDaoImpl implements BaseDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Date databaseTime() {
		Session currentSession = sessionFactory.getCurrentSession();
		return (Date) currentSession.createNativeQuery("SELECT SYSDATE()").uniqueResult();
	}

	@Override
	public String databaseTimeFormatted() {

		String dateFormat = "%d %m %Y  %H:%i:%s";
		NativeQuery<?> queryGetFormattedDate = sessionFactory.getCurrentSession()
				.createNativeQuery("SELECT DATE_FORMAT(SYSDATE(), :dateFormat)");
		queryGetFormattedDate.setParameter("dateFormat", dateFormat);
		return (String) queryGetFormattedDate.uniqueResult();

	}

	/**
	 *
	 */
	@Override
	public Serializable saveApplicationException(ApplicationException applicationException) {
		return sessionFactory.getCurrentSession().save(applicationException);
	}

}
