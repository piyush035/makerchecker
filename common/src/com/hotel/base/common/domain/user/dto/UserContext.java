/**
 * 
 */
package com.hotel.base.common.domain.user.dto;

import java.util.Date;

import com.hotel.base.common.domain.user.dto.UserInfoGraph;
import com.hotel.base.common.domain.user.dto.UserInformationDto;
import com.hotel.base.common.domain.user.dto.UserLoginDto;
import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * @author Piyush
 * 
 */
public class UserContext extends ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1133779837260507637L;
	
	/** */
	private UserInfoGraph user;

	/** */
	private long sessionTimeout;
	
	/** */
	private Date date;

	/** */
	private Date loginDateTime;
	
	/** */
	private String ticketUser;
	/**
	 * 
	 */
	public UserContext() {
	}
	/**
	 * 
	 * @return
	 */
	public UserInformationDto getUserInfo() {
		if (null == user) {
			return null;
		}

		return user.getUserInformationDto();
	}
	/**
	 * 
	 * @return
	 */
	public UserLoginDto getUserLogin() {
		if (null == user) {
			return null;
		}

		return user.getUserLoginDto();
	}
	/**
	 * 
	 * @return
	 */
	public long getUserId() {
		return user.getUserInformationDto().getId();
	}	

	/**
	 * @return the user
	 */
	public UserInfoGraph getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserInfoGraph user) {
		this.user = user;
	}

	/**
	 * @return the sessionTimeout
	 */
	public long getSessionTimeout() {
		return sessionTimeout;
	}

	/**
	 * @param sessionTimeout the sessionTimeout to set
	 */
	public void setSessionTimeout(long sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the loginDateTime
	 */
	public Date getLoginDateTime() {
		return loginDateTime;
	}

	/**
	 * @param loginDateTime the loginDateTime to set
	 */
	public void setLoginDateTime(Date loginDateTime) {
		this.loginDateTime = loginDateTime;
	}

	/**
	 * @return the ticketUser
	 */
	public String getTicketUser() {
		return ticketUser;
	}

	/**
	 * @param ticketUser the ticketUser to set
	 */
	public void setTicketUser(String ticketUser) {
		this.ticketUser = ticketUser;
	}

	@Override
	protected boolean equals2(ValueObject obj) {
		
		return false;
	}
}