/**
 * 
 */
package com.hotel.business.postalcodes.impl;

import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusiness;
import com.hotel.business.postalcodes.PostalCodesBusiness;
import com.hotel.postalcodes.dao.PostalCodesDao;

/**
 * @author Prince
 * 
 */
public class PostalCodesBusinessImpl extends AbstractDefaultBusiness implements
		PostalCodesBusiness {

	/** DAO for PostalCodes Info */
	private PostalCodesDao postalcodesDao;

	@Override
	protected DefaultDao getDefaultDao() {
		return postalcodesDao;
	}

	/**
	 * @param DefaultDao
	 *            the DefaultDao to set
	 */
	public void setDefaultDao(final PostalCodesDao postalcodesDao) {
		this.postalcodesDao = postalcodesDao;
	}
}