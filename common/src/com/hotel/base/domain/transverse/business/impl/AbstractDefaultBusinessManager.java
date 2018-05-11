package com.hotel.base.domain.transverse.business.impl;

import java.io.Serializable;

import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.common.tech.bean.DefaultBean;
import com.hotel.base.domain.transverse.business.DefaultBusinessManager;

/**
 * {@inheritDoc}
 */
public abstract class AbstractDefaultBusinessManager implements
		DefaultBusinessManager {

	/**
	 * {@inheritDoc}
	 */
	public DefaultBean load(final Serializable key) {

		return getDefaultDao().load(key);
	}

	/**
	 * method to get the defaultDAO.
	 * 
	 * @return the default DAO
	 */
	protected abstract DefaultDao getDefaultDao();

}