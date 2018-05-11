/**
 * 
 */
package com.hotel.business.country.impl;

import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusiness;
import com.hotel.business.country.CountryBusiness;
import com.hotel.country.dao.CountryDao;

/**
 * @author Prince
 * 
 */
public class CountryBusinessImpl extends AbstractDefaultBusiness implements
		CountryBusiness {

	/** DAO for Country Info */
	private CountryDao countryDao;

	@Override
	protected DefaultDao getDefaultDao() {
		return countryDao;
	}

	/**
	 * @param DefaultDao
	 *            the DefaultDao to set
	 */
	public void setDefaultDao(final CountryDao countryDao) {
		this.countryDao = countryDao;
	}
}