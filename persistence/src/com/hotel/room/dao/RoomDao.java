package com.hotel.room.dao;

import java.util.List;

import com.hotel.base.common.domain.room.bean.RoomBean;
import com.hotel.base.common.domain.room.dto.RoomDto;
import com.hotel.base.common.persistance.dao.DefaultDao;

/**
 * @author Piyush
 */
public interface RoomDao extends DefaultDao {
	
	/**
	 * @return
	 */
	public List<RoomBean> getRoomAll();
	
	/**
	 * @param RoomDto
	 * @return
	 */
	List<RoomBean> getRoomsWithCriteria(RoomDto roomDto);
}
