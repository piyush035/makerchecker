package com.hotel.base.common.domain.room.dto;

import com.hotel.base.common.domain.roomtype.dto.RoomTypeDto;
import com.rennover.hotel.common.helper.Wrapper;
import com.rennover.hotel.common.valueobject.ObjectId;
import com.rennover.hotel.common.valueobject.PersistentDomainObject;

public class RoomDto extends PersistentDomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3390247841281346051L;
	
	/** */
	private ObjectId id;
	
	public static final String ID = "id";
	
	public ObjectId getId() {
		return id;
		
	}
	
	public void setId(ObjectId newValue) {
		setId0(newValue);
	}
	
	private final void setId0(ObjectId newValue) {
		ObjectId oldValue = id;
		if (!equals(oldValue, newValue)) {
			id = newValue;
			setDirty(true);
			firePropertyChange(ID, oldValue, newValue);
		}
	}
	
	public static ObjectId createObjectId(long id)
	{
		ObjectId oid = new ObjectId(RoomDto.class);
		oid.setKeyPart(ID, Wrapper.wrap(id));
		return oid;
	}

	
	private String roomNumber;
	public static final String ROOM_NUMBER = "roomNumber";
	
	public String getRoomNumber() {
		return roomNumber;
		
	}
	
	public void setRoomNumber(String newValue) {
		setRoomNumber0(newValue);
	}
	
	private final void setRoomNumber0(String newValue) {
		String oldValue = roomNumber;
		if (!equals(oldValue, newValue)) {
			roomNumber = newValue;
			setDirty(true);
			firePropertyChange(ROOM_NUMBER, oldValue, newValue);
		}
	}
	
	private String roomName;
	public static final String ROOM_NAME = "roomName";
	
	public String getRoomName() {
		return roomName;
		
	}
	
	public void setRoomName(String newValue) {
		setRoomName0(newValue);
	}
	
	private final void setRoomName0(String newValue) {
		String oldValue = roomName;
		if (!equals(oldValue, newValue)) {
			roomName = newValue;
			setDirty(true);
			firePropertyChange(ROOM_NAME, oldValue, newValue);
		}
	}
	
	private String roomType;
	public static final String ROOM_TYPE = "roomType";
	
	public String getRoomType() {
		return roomType;
		
	}
	
	public void setRoomType(String newValue) {
		setRoomType0(newValue);
	}
	
	private final void setRoomType0(String newValue) {
		String oldValue = roomType;
		if (!equals(oldValue, newValue)) {
			roomType = newValue;
			setDirty(true);
			firePropertyChange(ROOM_TYPE, oldValue, newValue);
		}
	}
	
	private String roomPrice;
	public static final String ROOM_PRICE = "roomPrice";
	
	public String getRoomPrice() {
		return roomPrice;
		
	}
	
	public void setRoomPrice(String newValue) {
		setRoomPrice0(newValue);
	}
	
	private final void setRoomPrice0(String newValue) {
		String oldValue = roomPrice;
		if (!equals(oldValue, newValue)) {
			roomPrice = newValue;
			setDirty(true);
			firePropertyChange(ROOM_PRICE, oldValue, newValue);
		}
	}
	
	public String toString() {
		return this.roomNumber;
	}
	
	private RoomTypeDto roomTypeDto;
	
	/**
	 * @return the roomTypeDto
	 */
	public RoomTypeDto getRoomTypeDto() {
		return roomTypeDto;
	}
	
	/**
	 * @param roomTypeDto the roomTypeDto to set
	 */
	public void setRoomTypeDto(RoomTypeDto roomTypeDto) {
		this.roomTypeDto = roomTypeDto;
	}
	
	private ObjectId roomTypeId;
	
	public static final String ROOM_TYPE_ID = "roomTypeId";
	
	public ObjectId getRoomTypeId() {
		return roomTypeId;
	}
	
	public void setRoomTypeId(ObjectId newValue) {
		setRoomTypeId0(newValue);
	}
	
	private final void setRoomTypeId0(ObjectId newValue) {
		ObjectId oldValue = roomTypeId;
		if (!equals(oldValue, newValue)) {
			roomTypeId = newValue;
			setDirty(true);
			firePropertyChange(ROOM_TYPE_ID, oldValue, newValue);
		}
	}
	
}