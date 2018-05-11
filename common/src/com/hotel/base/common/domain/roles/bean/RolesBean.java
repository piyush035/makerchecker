package com.hotel.base.common.domain.roles.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.hotel.base.common.domain.roles.dto.RolesDto;
import com.hotel.base.common.tech.bean.DefaultBean;

@Entity
@Table(name = "roles")
public class RolesBean implements DefaultBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -424218012823401524L;
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	@Column(name = "roleName", nullable = false)
	private String roleName;
	/*
	 * @Column(name = "p_create") private int p_create;
	 * 
	 * @Column(name = "p_delete") private int p_delete;
	 * 
	 * @Column(name = "p_update") private int p_update;
	 * 
	 * @Column(name = "p_modify") private int p_modify
	 */;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public RolesDto getRolesDto() {
		final RolesDto rolesDto = new RolesDto();
		rolesDto.setId(this.id);
		rolesDto.setRoleName(this.roleName);
		return rolesDto;
	}	
}