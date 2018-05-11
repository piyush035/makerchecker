/**
 * 
 */
package com.hotel.business.states.impl;

import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusiness;
import com.hotel.business.states.StatesBusiness;
import com.hotel.states.dao.StatesDao;

/**
 * @author Prince
 * 
 */
public class StatesBusinessImpl extends AbstractDefaultBusiness implements
		StatesBusiness {

	/** DAO for City Info */
	private StatesDao statesDao;

	@Override
	protected DefaultDao getDefaultDao() {
		return statesDao;
	}

	/**
	 * @param DefaultDao
	 *            the DefaultDao to set
	 */
	public void setDefaultDao(final StatesDao statesDao) {
		this.statesDao = statesDao;
	}
}