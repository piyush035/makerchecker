/**
 * 
 */
package com.hotel.service.room.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import com.hotel.base.common.domain.room.dto.RoomDto;
import com.hotel.business.room.RoomBusinessManager;
import com.hotel.service.room.FacadeRoom;

/**
 * @author Prince
 */
@Remote(FacadeRoom.class)
@Stateless(name = "facadeRoom")
@javax.interceptor.Interceptors(SpringBeanAutowiringInterceptor.class)
public class FacadeRoomImpl implements FacadeRoom {
	
	/** */
	@Autowired
	private RoomBusinessManager roomBusinessManager;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<RoomDto> getRoomAll() {
		
		return roomBusinessManager.getRoomAll();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<RoomDto> getRoomsWithCriteria(RoomDto roomDto) {
		return roomBusinessManager.getRoomsWithCriteria(roomDto);
	}
	
}
