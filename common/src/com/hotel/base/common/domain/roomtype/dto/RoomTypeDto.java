/**
 * 
 */
package com.hotel.base.common.domain.roomtype.dto;

import java.math.BigDecimal;

import javax.persistence.Column;

import com.hotel.base.common.domain.room.dto.RoomDto;
import com.rennover.hotel.common.helper.Wrapper;
import com.rennover.hotel.common.valueobject.ObjectId;
import com.rennover.hotel.common.valueobject.PersistentDomainObject;

/**
 * @author Piyush
 */

public class RoomTypeDto extends PersistentDomainObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4752638672546446137L;

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
		ObjectId oid = new ObjectId(RoomTypeDto.class);
		oid.setKeyPart(ID, Wrapper.wrap(id));
		return oid;
	}

	private String roomType;
	
	public static final String ROOM_TYPE = "roomType";
	
	public String getRoomType() {
		return nullToEmpty(roomType);
		
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
	
	/** */
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	
	public static final String PRICE = "price";
	
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal newValue) {
		setPrice0(newValue);
	}
	
	private final void setPrice0(BigDecimal newValue) {
		BigDecimal oldValue = price;
		if (!equals(oldValue, newValue)) {
			price = newValue;
			setDirty(true);
			firePropertyChange(PRICE, oldValue, newValue);
		}
	}
	
	public String toString(){
		return roomType;
	}
}