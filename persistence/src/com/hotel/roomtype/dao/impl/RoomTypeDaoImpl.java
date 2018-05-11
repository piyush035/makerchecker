/**
 * 
 */
package com.hotel.roomtype.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.hotel.base.common.domain.room.bean.RoomBean;
import com.hotel.base.common.domain.roomtype.bean.RoomTypeBean;
import com.hotel.base.common.persistance.dao.impl.AbstractDefaultDao;
import com.hotel.roomtype.dao.RoomTypeDao;

/**
 * @author Piyush
 */
public class RoomTypeDaoImpl extends AbstractDefaultDao implements RoomTypeDao {
	
	private final static Logger LOGGER = Logger.getLogger(RoomTypeDaoImpl.class);
	
	@Override
	public List<RoomTypeBean> getRoomTypeAll() {
		LOGGER.info("In Room all");
		return getHibernateTemplate().find("FROM com.hotel.base.common.domain.roomtype.bean.RoomTypeBean");
	}
	
	/** */
	@Override
	public Class getDefaultClassBean() {
		return RoomTypeBean.class;
	}
}
