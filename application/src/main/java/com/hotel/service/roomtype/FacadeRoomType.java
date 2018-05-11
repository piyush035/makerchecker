/**
 * 
 */
package com.hotel.service.roomtype;

import java.util.List;

import javax.ejb.Remote;

import com.hotel.base.common.domain.roomtype.dto.RoomTypeDto;

/**
 * @author Prince
 */
@Remote
public interface FacadeRoomType {
	/**
	 * @return
	 */
	List<RoomTypeDto> getRoomTypeAll();
}
