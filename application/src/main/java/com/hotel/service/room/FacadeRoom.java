/**
 * 
 */
package com.hotel.service.room;

import java.util.List;

import javax.ejb.Remote;

import com.hotel.base.common.domain.room.dto.RoomDto;

/**
 * @author Prince
 */
@Remote
public interface FacadeRoom {
	/**
	 * @return
	 */
	List<RoomDto> getRoomAll();
	
	/**
	 * @param roomDto
	 * @return
	 */
	List<RoomDto> getRoomsWithCriteria(RoomDto roomDto);
}
