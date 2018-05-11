/**
 * 
 */
package com.hotel.business.addresses.impl;

import com.hotel.addresses.dao.AddressesDao;
import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusiness;
import com.hotel.business.addresses.AddressesBusiness;

/**
 * @author Prince
 * 
 */
public class AddressesBusinessImpl extends AbstractDefaultBusiness implements
		AddressesBusiness {

	/** DAO for Addresses Info */
	private AddressesDao addressesDao;

	@Override
	protected DefaultDao getDefaultDao() {
		return addressesDao;
	}

	/**
	 * @param DefaultDao
	 *            the DefaultDao to set
	 */
	public void setDefaultDao(final AddressesDao addressesDao) {
		this.addressesDao = addressesDao;
	}
}