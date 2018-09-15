/**
 *
 */
package com.mevenk.webapp.dao.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mevenk.webapp.dao.BaseDao;

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
		Date dateDatabase = (Date) currentSession.createNativeQuery("SELECT NOW()").uniqueResult();
		return dateDatabase;
	}

	@Override
	public String databaseTimeFormatted() {

		String dateFormat = "Dy, dd MON yyyy  HH24:MI:SS.MS";
		NativeQuery<?> queryGetFormattedDate = sessionFactory.getCurrentSession()
				.createNativeQuery("SELECT TO_CHAR(NOW(), :dateFormat)");
		queryGetFormattedDate.setParameter("dateFormat", dateFormat);
		return (String) queryGetFormattedDate.uniqueResult();

	}

}
