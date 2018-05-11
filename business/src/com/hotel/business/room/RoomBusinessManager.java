/**
 * 
 */
package com.hotel.business.room;

import java.util.List;

import com.hotel.base.common.domain.room.dto.RoomDto;
import com.hotel.base.domain.transverse.business.DefaultBusinessManager;

/**
 * @author Piyush
 */
public interface RoomBusinessManager extends DefaultBusinessManager {
	/**
	 * List of all Rooms
	 * @return List<RoomDto>
	 */
	List<RoomDto> getRoomAll();
	
	/**
	 * @param roomDto
	 * @return
	 */
	List<RoomDto> getRoomsWithCriteria(RoomDto roomDto);
}
