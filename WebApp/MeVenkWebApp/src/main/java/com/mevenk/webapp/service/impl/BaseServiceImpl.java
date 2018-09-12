/**
 *
 */
package com.mevenk.webapp.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mevenk.webapp.dao.BaseDao;
import com.mevenk.webapp.service.BaseService;

/**
 * @author venky
 *
 */
@Service
public class BaseServiceImpl implements BaseService {

	@Autowired
	private BaseDao baseDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.mevenk.webapp.service.BaseService#databaseTime()
	 */
	@Override
	@Transactional(readOnly = true)
	public Date databaseTime() {
		return baseDao.databaseTime();
	}

}
