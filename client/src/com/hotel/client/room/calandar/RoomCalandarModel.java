package com.hotel.client.room.calandar;

import com.hotel.base.common.domain.user.dto.UserInformationDto;
import com.rennover.client.framework.mvc.ModelDescription;
import com.rennover.client.framework.mvc.PanelModel;

/**
 * 
 * @author Piyush
 *
 */
public class RoomCalandarModel extends PanelModel{
	
	/**
	 * 
	 */
	public static final ModelDescription COMPTE_UTILISATUER_MODEL = new ModelDescription(UserInformationDto.class);
	
	//private ValueObjectListModel m_ccnSelectionneeListModel = new ValueObjectListModel(CCN.class);
	
	/** */
	private int currentMonth;
	
	/** */
	private int year;
	/**
	 * @return the currentMonth
	 */
	public int getCurrentMonth() {
		return currentMonth;
	}
	/**
	 * @param currentMonth the currentMonth to set
	 */
	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
}