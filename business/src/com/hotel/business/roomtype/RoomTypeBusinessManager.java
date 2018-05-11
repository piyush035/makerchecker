/**
 * 
 */
package com.hotel.business.roomtype;

import java.util.List;

import com.hotel.base.common.domain.roomtype.dto.RoomTypeDto;
import com.hotel.base.domain.transverse.business.DefaultBusinessManager;

/**
 * @author Piyush
 */
public interface RoomTypeBusinessManager extends DefaultBusinessManager {
	/**
	 * List of all Roles
	 * @return List<RolesDto>
	 */
	List<RoomTypeDto> getRoomTypeAll();
}
