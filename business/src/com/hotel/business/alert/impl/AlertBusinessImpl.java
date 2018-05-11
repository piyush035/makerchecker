/**
 * 
 */
package com.hotel.business.alert.impl;

import com.hotel.alert.dao.AlertDao;
import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusiness;
import com.hotel.business.alert.AlertBusiness;

/**
 * @author Prince
 * 
 */
public class AlertBusinessImpl extends AbstractDefaultBusiness implements
		AlertBusiness {

	/** DAO for City Info */
	private AlertDao alertDao;

	@Override
	protected DefaultDao getDefaultDao() {
		return alertDao;
	}

	/**
	 * @param DefaultDao
	 *            the DefaultDao to set
	 */
	public void setDefaultDao(final AlertDao alertDao) {
		this.alertDao = alertDao;
	}
}