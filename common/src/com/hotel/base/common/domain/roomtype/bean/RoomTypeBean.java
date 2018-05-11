/**
 * 
 */
package com.hotel.base.common.domain.roomtype.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hotel.base.common.domain.roomtype.dto.RoomTypeDto;
import com.hotel.base.common.tech.bean.DefaultBean;

/**
 * @author Piyush
 */
@Entity
@Table(name = "roomtype")
public class RoomTypeBean implements DefaultBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4850610158554120741L;
	
	/** */
	@Id
	@Column(name = "id", nullable = false)
	private long id;
	
	/** */
	@Column(name = "roomType", nullable = false)
	private String roomType;
	
	/** */
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	
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
	 * @return the roomType
	 */
	public String getRoomType() {
		return roomType;
	}
	
	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public RoomTypeDto getRoomTypeDto() {
		RoomTypeDto roomTypeDto = new RoomTypeDto();
		roomTypeDto.setId(RoomTypeDto.createObjectId(this.id));
		roomTypeDto.setRoomType(this.roomType);
		roomTypeDto.setPrice(this.price);
		return roomTypeDto;
	}
}