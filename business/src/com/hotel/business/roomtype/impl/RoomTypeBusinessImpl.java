/**
 * 
 */
package com.hotel.business.roomtype.impl;

import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusiness;
import com.hotel.business.roomtype.RoomTypeBusiness;
import com.hotel.roomtype.dao.RoomTypeDao;

/**
 * @author Piyush
 */
public class RoomTypeBusinessImpl extends AbstractDefaultBusiness implements RoomTypeBusiness {
	
	/***/
	private RoomTypeDao roomTypeDao;
	
	@Override
	protected DefaultDao getDefaultDao() {
		return roomTypeDao;
	}
	
	/**
	 * @param DefaultDao the DefaultDao to set
	 */
	public void setDefaultDao(final RoomTypeDao roomTypeDao) {
		this.roomTypeDao = roomTypeDao;
	}
}
