/**
 *
 */
package com.mevenk.webapp.dao.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

}
