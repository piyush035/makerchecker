package com.hotel.base.common.domain.roles.dto;

import com.rennover.hotel.common.valueobject.DomainObject;

public class RolesDto extends DomainObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3390247841281346051L;

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

	private String roleName;
	public static final String ROLE_NAME = "roleName";

	public String getRoleName() {
		return roleName;

	}

	public void setRoleName(String newValue) {
		setRoleName0(newValue);
	}

	private final void setRoleName0(String newValue) {
		String oldValue = roleName;
		if (!equals(oldValue, newValue)) {
			roleName = newValue;
			setDirty(true);
			firePropertyChange(ROLE_NAME, oldValue, newValue);
		}
	}

	public String toString(){
		return this.roleName;
	}
	/*private int p_create;
	private int p_delete;
	private int p_update;
	private int p_modify;*/
}