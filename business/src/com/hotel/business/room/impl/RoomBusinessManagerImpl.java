/**
 * 
 */
package com.hotel.business.room.impl;

import java.util.ArrayList;
import java.util.List;

import com.hotel.base.common.domain.room.bean.RoomBean;
import com.hotel.base.common.domain.room.dto.RoomDto;
import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusinessManager;
import com.hotel.business.room.RoomBusiness;
import com.hotel.business.room.RoomBusinessManager;
import com.hotel.room.dao.RoomDao;
import com.rennover.hotel.common.helper.CollectionHelper;

/**
 * @author Piyush
 *
 */
public class RoomBusinessManagerImpl extends AbstractDefaultBusinessManager implements RoomBusinessManager {
	
	/** */
	private RoomDao roomDao;
	
	/** Business. */
	private RoomBusiness roomBusiness;
	
	/**
	 * {@inheritDoc}
	 */
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
	
	
	
	/**
	 * @return the roomBusiness
	 */
	public RoomBusiness getRoomBusiness() {
		return roomBusiness;
	}

	/**
	 * @param roomBusiness the roomBusiness to set
	 */
	public void setRoomBusiness(RoomBusiness roomBusiness) {
		this.roomBusiness = roomBusiness;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RoomDto> getRoomAll() {
		List<RoomBean> roomList = roomDao.getRoomAll();
		List<RoomDto> roomDtoList = new ArrayList<RoomDto>();
		if (!CollectionHelper.isNullOrEmpty(roomList)) {
			for (RoomBean bean : roomList) {
				roomDtoList.add(bean.getRoomDto());
			}
		}
		return roomDtoList;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RoomDto> getRoomsWithCriteria(RoomDto roomDto){
		List<RoomBean> roomList = roomDao.getRoomsWithCriteria(roomDto);
		List<RoomDto> roomDtoList = new ArrayList<RoomDto>();
		if (!CollectionHelper.isNullOrEmpty(roomList)) {
			for (RoomBean bean : roomList) {
				roomDtoList.add(bean.getRoomDto());
			}
		}
		return roomDtoList;
	}
}