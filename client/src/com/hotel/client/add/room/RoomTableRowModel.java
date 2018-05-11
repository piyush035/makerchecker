package com.hotel.client.add.room;

import com.hotel.base.common.domain.room.dto.RoomDto;
import com.hotel.base.common.helper.PropertyHelper;
import com.hotel.client.add.room.constants.RoomConstants;
import com.rennover.client.framework.widget.table.TableRowModel;
import com.rennover.hotel.common.exception.IncoherenceException;

/**
 * @author Piyush
 */
public class RoomTableRowModel extends TableRowModel implements RoomConstants {
	/**
	 * Retrieves the column headings of the tables.
	 */
	protected String[] getTitles() {
		return new String[] {
				ROOM_NAME, ROOM_NUMBER, ROOM_TYPE, ROOM_PRICE
		};
	}
	
	/**
	 * Retrieves the value in a table cell.
	 * @param rowObject the object represented by a table row
	 * @param columnIndex the index of the column in which you want to display a value
	 */
	protected Object getValueAt(int columnIndex, Object rowObject) {
		RoomDto roomDto = (RoomDto) rowObject;
		
		switch (columnIndex) {
			case 0:
				if (PropertyHelper.isNull(roomDto.getRoomName())) {
					return " ";
				} else {
					return roomDto.getRoomName();
				}
			case 1:
				if (PropertyHelper.isNull(roomDto.getRoomNumber())) {
					return " ";
				} else {
					return roomDto.getRoomNumber();
				}
				
			case 2:
				if (PropertyHelper.isNull(roomDto.getRoomType())) {
					return " ";
				} else {
					return roomDto.getRoomType();
				}
			case 3:
				if (PropertyHelper.isNull(roomDto.getRoomPrice())) {
					return " ";
				} else {
					return roomDto.getRoomTypeDto().getPrice();
				}
			default:
				throw new IncoherenceException("Unmanaged index");
		}
	}
	
	/**
	 * Gets the type of the values shown in the columns. this allows Have publishers default corresponding to certain
	 * types. (checkbox For booleans, combo for enumerations, etc.. By default, the type Of each column is Object.
	 * Override this method to have a Different behavior.
	 */
	public Class[] getColumnTypes() {
		return new Class[] {
				String.class, String.class, String.class, String.class
		};
	}
	
}
