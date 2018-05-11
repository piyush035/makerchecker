/**
 * 
 */
package com.hotel.roomtype.dao;

import java.util.List;

import com.hotel.base.common.domain.roomtype.bean.RoomTypeBean;
import com.hotel.base.common.persistance.dao.DefaultDao;

/**
 * @author Piyush
 *
 */
public interface RoomTypeDao extends DefaultDao{

	/**
	 * 
	 * @return
	 */
	public List<RoomTypeBean> getRoomTypeAll();
}
