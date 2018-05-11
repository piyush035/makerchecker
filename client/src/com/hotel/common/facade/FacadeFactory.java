package com.hotel.common.facade;

import com.hotel.base.common.facade.FacadeFactoryBase;
import com.hotel.service.alert.FacadeAlert;
import com.hotel.service.country.FacadeCountry;
import com.hotel.service.role.FacadeRoles;
import com.hotel.service.room.FacadeRoom;
import com.hotel.service.roomtype.FacadeRoomType;
import com.hotel.service.states.FacadeStates;
import com.hotel.service.userinformation.FacadeUserInformation;
import com.hotel.service.userinformation.FacadeUserLogin;

/**
 * @author Piyush
 */
public abstract class FacadeFactory extends FacadeFactoryBase {
	/**
	 * @return
	 */
	public static FacadeUserLogin getFacadeUserLogin() {
		return (FacadeUserLogin) getProxy(FacadeUserLogin.class);
	}
	
	/**
	 * @return
	 */
	public static FacadeUserInformation getFacadeUserInformation() {
		return (FacadeUserInformation) getProxy(FacadeUserInformation.class);
	}
	
	/**
	 * @return
	 */
	public static FacadeRoles getFacadeRoles() {
		return (FacadeRoles) getProxy(FacadeRoles.class);
	}
	
	/**
	 * @return
	 */
	public static FacadeStates getFacadeStates() {
		return (FacadeStates) getProxy(FacadeStates.class);
	}
	
	/**
	 * @return
	 */
	public static FacadeCountry getFacadeCountry() {
		return (FacadeCountry) getProxy(FacadeCountry.class);
	}
	
	/**
	 * @return
	 */
	public static FacadeAlert getFacadeAlert() {
		return (FacadeAlert) getProxy(FacadeAlert.class);
	}
	
	/**
	 * @return
	 */
	public static FacadeRoom getFacadeRoom() {
		return (FacadeRoom) getProxy(FacadeRoom.class);
	}
	

	/**
	 * @return
	 */
	public static FacadeRoomType getFacadeRoomType() {
		return (FacadeRoomType) getProxy(FacadeRoomType.class);
	}
}