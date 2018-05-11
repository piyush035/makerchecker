/**
 * 
 */
package com.hotel.base.common.domain.room.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.hotel.base.common.domain.room.dto.RoomDto;
import com.hotel.base.common.domain.roomtype.bean.RoomTypeBean;
import com.hotel.base.common.tech.bean.DefaultBean;

/**
 * @author Piyush
 */
@Entity
@Table(name = "room")
public class RoomBean implements DefaultBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1221393773985347795L;
	
	/** */
	@Id
	@Column(name = "id", nullable = false)
	private long id;
	
	/** */
	@Column(name = "roomName", nullable = false)
	private String roomName;
	
	/** */
	@Column(name = "roomNumber", nullable = false)
	private String roomNumber;
	
	/** */
	@OneToOne
	@JoinColumn(name = "roomTypeId")
	private RoomTypeBean roomType;
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName;
	}
	
	/**
	 * @param roomName the roomName to set
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	/**
	 * @return the roomNumber
	 */
	public String getRoomNumber() {
		return roomNumber;
	}
	
	/**
	 * @param roomNumber the roomNumber to set
	 */
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	/**
	 * @return the roomType
	 */
	public RoomTypeBean getRoomType() {
		return roomType;
	}
	
	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(RoomTypeBean roomType) {
		this.roomType = roomType;
	}
	
	public RoomDto getRoomDto() {
		RoomDto roomDto = new RoomDto();
		roomDto.setId(RoomDto.createObjectId(this.id));
		roomDto.setRoomName(this.roomName);
		roomDto.setRoomNumber(this.roomNumber);
		roomDto.setRoomType(this.roomType.getRoomType());
		roomDto.setRoomTypeDto(this.roomType.getRoomTypeDto());
		return roomDto;
	}
}