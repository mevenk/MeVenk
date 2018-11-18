/**
 *
 */
package com.mevenk.webapp.cache.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mevenk.webapp.cache.dao.CacheDataDao;
import com.mevenk.webapp.entity.cache.MessageSource;

/**
 * @author venky
 *
 */
@Repository
public class CacheDataDaoImpl implements CacheDataDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<MessageSource> getMessageSource() {

		Session currentSession = sessionFactory.getCurrentSession();
		CriteriaQuery<MessageSource> criteriaQueryEntityMessageSource = currentSession.getCriteriaBuilder()
				.createQuery(MessageSource.class);
		criteriaQueryEntityMessageSource.from(MessageSource.class);
		return currentSession.createQuery(criteriaQueryEntityMessageSource).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageSource> getMessageSource(int messageCategoryId) {

		@SuppressWarnings("rawtypes")
		Query queryGetMessageSource = sessionFactory.getCurrentSession()
				.createQuery("FROM MessageSource ms WHERE ms.messageCategoryId = :messageCategoryId");
		return queryGetMessageSource.setParameter("messageCategoryId", messageCategoryId).getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getMessageCategoryIds() {

		return sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT(messageCategoryId) FROM MessageSource")
				.getResultList();
	}

}
