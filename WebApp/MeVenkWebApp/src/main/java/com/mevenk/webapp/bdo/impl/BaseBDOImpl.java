/**
 *
 */
package com.mevenk.webapp.bdo.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mevenk.webapp.bdo.BaseBDO;
import com.mevenk.webapp.service.BaseService;

/**
 * @author venky
 *
 */
@Component
public class BaseBDOImpl implements BaseBDO {

	@Autowired
	private BaseService baseService;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.mevenk.webapp.bdo.BaseBDO#databaseTime()
	 */
	@Override
	public Date databaseTime() {
		return baseService.databaseTime();
	}

}
