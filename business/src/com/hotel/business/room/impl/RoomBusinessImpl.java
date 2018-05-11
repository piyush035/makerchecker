/**
 * 
 */
package com.hotel.business.room.impl;

import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusiness;
import com.hotel.business.room.RoomBusiness;
import com.hotel.room.dao.RoomDao;

/**
 * @author Piyush
 */
public class RoomBusinessImpl extends AbstractDefaultBusiness implements RoomBusiness {
	
	/***/
	private RoomDao roomDao;
	
	@Override
	protected DefaultDao getDefaultDao() {
		return roomDao;
	}
	
	/**
	 * @param DefaultDao the DefaultDao to set
	 */
	public void setDefaultDao(final RoomDao roomDao) {
		this.roomDao = roomDao;
	}
}
