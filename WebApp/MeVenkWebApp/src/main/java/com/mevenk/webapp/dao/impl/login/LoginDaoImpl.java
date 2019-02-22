/**
 *
 */
package com.mevenk.webapp.dao.impl.login;

import static org.hibernate.criterion.Projections.rowCount;
import static org.hibernate.criterion.Restrictions.eq;
import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mevenk.webapp.dao.login.LoginDao;
import com.mevenk.webapp.entity.user.UserVW;

/**
 * @author venky
 *
 */
@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional(readOnly = true, propagation = SUPPORTS)
	public Long getUidCount(String uid) {
		Criteria criteriaGetUidCount = sessionFactory.getCurrentSession().createCriteria(UserVW.class);
		criteriaGetUidCount.add(eq("uid", uid));
		criteriaGetUidCount.setProjection(rowCount());
		return (Long) criteriaGetUidCount.uniqueResult();
	}

	@Override
	@Transactional(readOnly = true, propagation = SUPPORTS)
	public String getUserPassword(String uid) {

		return uid;
	}

	@Override
	@Transactional(readOnly = true, propagation = SUPPORTS)
	public UserVW loginUser(String uid) {
		Criteria criteriaGetUserByUid = sessionFactory.getCurrentSession().createCriteria(UserVW.class);
		criteriaGetUserByUid.add(eq("uid", uid));
		return (UserVW) criteriaGetUserByUid.uniqueResult();
	}

}
