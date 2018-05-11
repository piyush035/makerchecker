/**
 * 
 */
package com.hotel.service.roomtype.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import com.hotel.base.common.domain.roomtype.dto.RoomTypeDto;
import com.hotel.business.roomtype.RoomTypeBusinessManager;
import com.hotel.service.roomtype.FacadeRoomType;

/**
 * @author Prince
 */
@Remote(FacadeRoomType.class)
@Stateless(name = "facadeRoomType")
@javax.interceptor.Interceptors(SpringBeanAutowiringInterceptor.class)
public class FacadeRoomTypeImpl implements FacadeRoomType {
	
	/** */
	@Autowired
	private RoomTypeBusinessManager roomTypeBusinessManager;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<RoomTypeDto> getRoomTypeAll() {
		return roomTypeBusinessManager.getRoomTypeAll();
	}
	
}
