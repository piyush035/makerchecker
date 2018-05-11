package com.hotel.client.add.room;

import com.hotel.base.common.domain.room.dto.RoomDto;
import com.hotel.base.common.domain.roomtype.dto.RoomTypeDto;
import com.rennover.client.framework.mvc.ModelDescription;
import com.rennover.client.framework.mvc.PanelModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;

/**
 * @author Piyush
 */
public class RoomModel extends PanelModel {
	
	/** */
	private ValueObjectModel roomModel = new ValueObjectModel(RoomDto.class);
	
	/** */
	private ValueObjectModel searchRoomModel = new ValueObjectModel(RoomDto.class);
	
	/** */
	public static final ModelDescription SEARCHED_ROOM_MODEL = new ModelDescription(RoomDto.class);
	
	/** */
	public static final ModelDescription SEARCH_CRITERIA_ROOM_MODEL = new ModelDescription(RoomDto.class);
	
	/** */
	public static final ModelDescription SEARCH_CRITERIA_ROOM_TYPE_MODEL = new ModelDescription(RoomTypeDto.class);
	
	/**
	 * @param countryDto
	 */
	public void setSearchRoomModel(RoomDto roomDto) {
		searchRoomModel.setValueObject(roomDto);
	}
	
	/**
	 * @return the searchRoomModel
	 */
	public ValueObjectModel getSearchRoomModel() {
		return searchRoomModel;
	}
	
	/**
	 * @param roomDto
	 */
	public void setRoomModel(RoomDto roomDto) {
		roomModel.setValueObject(roomDto);
	}
	
	/**
	 * @return the getRoomModel
	 */
	public ValueObjectModel getRoomModel() {
		return roomModel;
	}
}