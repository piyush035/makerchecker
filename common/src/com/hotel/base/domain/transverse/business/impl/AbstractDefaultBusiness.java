package com.hotel.base.domain.transverse.business.impl;

import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.common.tech.bean.DefaultBean;
import com.hotel.base.domain.transverse.business.DefaultBusiness;



/**
 * {@inheritDoc}
 */

public abstract class AbstractDefaultBusiness implements DefaultBusiness {

	/**
	 * {@inheritDoc}
	 */
	public void delete(final DefaultBean defautBean) {
		getDefaultDao().delete(defautBean);

	}

	/**
	 * {@inheritDoc}
	 */
	public void modify(final DefaultBean defautBean) {
		getDefaultDao().modify(defautBean);

	}

	/**
	 * method to get the defaultDAO.
	 * @return the default DAO
	 */
	protected abstract DefaultDao getDefaultDao();

	/**
	 * {@inheritDoc}
	 */
	public void create(final DefaultBean defautBean) {
		getDefaultDao().create(defautBean);
	}

}