package com.hotel.room.dao.impl;

import java.util.List;

import com.hotel.base.common.domain.room.bean.RoomBean;
import com.hotel.base.common.domain.room.dto.RoomDto;
import com.hotel.base.common.helper.PropertyHelper;
import com.hotel.base.common.persistance.dao.impl.AbstractDefaultDao;
import com.hotel.room.dao.RoomDao;

/**
 * @author Piyush
 */
public class RoomDaoImpl extends AbstractDefaultDao implements RoomDao {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RoomBean> getRoomAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("FROM com.hotel.base.common.domain.room.bean.RoomBean room");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RoomBean> getRoomsWithCriteria(RoomDto roomDto) {
		String sql = "FROM com.hotel.base.common.domain.room.bean.RoomBean room  ";
		boolean conditionAdded = false;
		if (null != roomDto) {
			if (!PropertyHelper.isNull(roomDto.getRoomName())) {
				sql += "WHERE room.roomName like '" + roomDto.getRoomName() + "'";
				conditionAdded = true;
			}
			if (!PropertyHelper.isNull(roomDto.getRoomNumber())) {
				if (conditionAdded) {
					sql += " AND ";
				}else{
					sql += " WHERE ";
				}
				sql += "room.roomNumber like '" + roomDto.getRoomNumber() + "'";
				conditionAdded = true;
			}
			if (!PropertyHelper.isNull(roomDto.getRoomType())) {
				if (conditionAdded) {
					sql += " AND ";
				}else{
					sql += " WHERE ";
				}
				sql += "room.roomType.id = " + roomDto.getRoomTypeDto().getId();
				conditionAdded = true;
			}
		}
		return getHibernateTemplate().find(sql);
	}
	
	@Override
	public Class getDefaultClassBean() {
		return RoomDao.class;
	}
}