package com.hotel.base.common.domain.user.dto;

import java.sql.Date;

import com.hotel.base.common.domain.roles.dto.RolesDto;
import com.rennover.hotel.common.valueobject.DomainObject;
import com.rennover.hotel.common.valueobject.ObjectId;

/**
 * This class is created for User Login.
 * 
 * @author Piyush
 * 
 */
public class UserLoginDto extends DomainObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6213276487686991318L;

	public UserLoginDto() {

	}

	/** */
	private long id;

	public static final String ID = "id";

	public long getId() {
		return id;

	}

	public void setId(long newValue) {
		setId0(newValue);
	}

	private final void setId0(long newValue) {
		long oldValue = id;
		if (!equals(oldValue, newValue)) {
			id = newValue;
			setDirty(true);
			firePropertyChange(ID, oldValue, newValue);
		}
	}

	/** */
	private String userName;

	public static final String USER_NAME = "userName";

	public String getUserName() {
		return nullToEmpty(userName);

	}

	public void setUserName(String newValue) {
		setUserName0(newValue);
	}

	private final void setUserName0(String newValue) {
		String oldValue = userName;
		if (!equals(oldValue, newValue)) {
			userName = newValue;
			setDirty(true);
			firePropertyChange(USER_NAME, oldValue, newValue);
		}
	}

	/** */
	private String password;

	public static final String PASSWORD = "password";

	public String getPassword() {
		return password;

	}

	public void setPassword(String newValue) {
		setPassword0(newValue);
	}

	private final void setPassword0(String newValue) {
		String oldValue = password;
		if (!equals(oldValue, newValue)) {
			password = newValue;
			setDirty(true);
			firePropertyChange(PASSWORD, oldValue, newValue);
		}
	}

	/** */
	private ObjectId roleId;

	public static final String ROLE_ID = "roleId";

	public ObjectId getRoleId() {
		return roleId;

	}

	public void setRoleId(ObjectId newValue) {
		setRoleId0(newValue);
	}

	private final void setRoleId0(ObjectId newValue) {
		ObjectId oldValue = roleId;
		if (!equals(oldValue, newValue)) {
			roleId = newValue;
			setDirty(true);
			firePropertyChange(ROLE_ID, oldValue, newValue);
		}
	}

	private UserInformationDto userInformation;

	private RolesDto role;

	private Date createdDate;

	public static final String CREATE_DATE = "createdDate";

	public Date getCreatedDate() {
		return createdDate;

	}

	public void setCreatedDate(Date newValue) {
		setCreatedDate0(createdDate);
	}

	private final void setCreatedDate0(Date newValue) {
		Date oldValue = createdDate;
		if (!equals(oldValue, newValue)) {
			createdDate = newValue;
			setDirty(true);
			firePropertyChange(CREATE_DATE, oldValue, newValue);
		}
	}

	private String status;

	public static final String STATUS = "status";

	public String getStatus() {
		return status;

	}

	public void setStatus(String newValue) {
		setStatus0(newValue);
	}

	private final void setStatus0(String newValue) {
		String oldValue = status;
		if (!equals(oldValue, newValue)) {
			status = newValue;
			setDirty(true);
			firePropertyChange(STATUS, oldValue, newValue);
		}
	}

	private Date lastModifiedDate;

	public static final String LAST_MODIFIED_DATE = "lastModifiedDate";

	public Date getLastModifiedDate() {
		return lastModifiedDate;

	}

	public void setLastModifiedDate(Date newValue) {
		setLastModifiedDate0(lastModifiedDate);
	}

	private final void setLastModifiedDate0(Date newValue) {
		Date oldValue = lastModifiedDate;
		if (!equals(oldValue, newValue)) {
			lastModifiedDate = newValue;
			setDirty(true);
			firePropertyChange(LAST_MODIFIED_DATE, oldValue, newValue);
		}
	}

	private Date deactivationDate;

	public static final String DEACTIVATION_DATE = "deactivationDate";

	public Date getDeactivationDate() {
		return deactivationDate;

	}

	public void setDeactivationDate(Date newValue) {
		setDeactivationDate0(deactivationDate);
	}

	private final void setDeactivationDate0(Date newValue) {
		Date oldValue = deactivationDate;
		if (!equals(oldValue, newValue)) {
			deactivationDate = newValue;
			setDirty(true);
			firePropertyChange(LAST_MODIFIED_DATE, oldValue, newValue);
		}
	}

	private Date lastLoginTime;

	public static final String LAST_LOGIN_TIME = "lastLoginTime";

	public Date getLastLoginTime() {
		return lastLoginTime;

	}

	public void setLastLoginTime(Date newValue) {
		setLastLoginTime0(lastLoginTime);
	}

	private final void setLastLoginTime0(Date newValue) {
		Date oldValue = lastLoginTime;
		if (!equals(oldValue, newValue)) {
			lastLoginTime = newValue;
			setDirty(true);
			firePropertyChange(LAST_LOGIN_TIME, oldValue, newValue);
		}
	}

	public UserInformationDto getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(UserInformationDto userInformation) {
		this.userInformation = userInformation;
	}

	public RolesDto getRole() {
		return role;
	}

	public void setRole(RolesDto role) {
		this.role = role;
	}
}