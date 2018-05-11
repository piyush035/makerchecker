/**
 * 
 */
package com.hotel.business.city.impl;

import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusiness;
import com.hotel.business.city.CityBusiness;
import com.hotel.city.dao.CityDao;

/**
 * @author Prince
 * 
 */
public class CityBusinessImpl extends AbstractDefaultBusiness implements
		CityBusiness {

	/** DAO for City Info */
	private CityDao cityDao;

	@Override
	protected DefaultDao getDefaultDao() {
		return cityDao;
	}

	/**
	 * @param DefaultDao
	 *            the DefaultDao to set
	 */
	public void setDefaultDao(final CityDao cityDao) {
		this.cityDao = cityDao;
	}
}