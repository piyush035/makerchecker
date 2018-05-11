/**
 * 
 */
package com.hotel.business.roomtype.impl;

import java.util.ArrayList;
import java.util.List;

import com.hotel.base.common.domain.roomtype.bean.RoomTypeBean;
import com.hotel.base.common.domain.roomtype.dto.RoomTypeDto;
import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusinessManager;
import com.hotel.business.roomtype.RoomTypeBusiness;
import com.hotel.business.roomtype.RoomTypeBusinessManager;
import com.hotel.roomtype.dao.RoomTypeDao;
import com.rennover.hotel.common.helper.CollectionHelper;

/**
 * @author Piyush
 *
 */
public class RoomTypeBusinessManagerImpl extends AbstractDefaultBusinessManager implements RoomTypeBusinessManager {
	
	/** */
	private RoomTypeDao roomTypeDao;
	
	/** Business. */
	private RoomTypeBusiness roomTypeBusiness;
	
	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RoomTypeDto> getRoomTypeAll() {
		List<RoomTypeBean> roomTypeList = roomTypeDao.getRoomTypeAll();
		List<RoomTypeDto> roomTypeDtoList = new ArrayList<RoomTypeDto>();
		if (!CollectionHelper.isNullOrEmpty(roomTypeList)) {
			for (RoomTypeBean bean : roomTypeList) {
				roomTypeDtoList.add(bean.getRoomTypeDto());
			}
		}
		return roomTypeDtoList;
	}

	/**
	 * @return the roomTypeBusiness
	 */
	public RoomTypeBusiness getRoomTypeBusiness() {
		return roomTypeBusiness;
	}

	/**
	 * @param roomTypeBusiness the roomTypeBusiness to set
	 */
	public void setRoomTypeBusiness(RoomTypeBusiness roomTypeBusiness) {
		this.roomTypeBusiness = roomTypeBusiness;
	}
	
}