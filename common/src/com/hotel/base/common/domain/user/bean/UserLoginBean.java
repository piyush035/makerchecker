package com.hotel.base.common.domain.user.bean;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.hotel.base.common.domain.roles.bean.RolesBean;
import com.hotel.base.common.domain.user.dto.UserLoginDto;
import com.hotel.base.common.tech.bean.DefaultBean;

@Entity
@Table(name = "userlogin", uniqueConstraints = {
		@UniqueConstraint(columnNames = "username") })
public class UserLoginBean implements DefaultBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4083254447291310840L;

	/** */
	@Id
	@Column(name = "id", nullable = false)
	private long id;

	@OneToOne
	@JoinColumn(name = "userid")
	private UserInformationBean userInformationBean;

	/** */
	@OneToOne
	@JoinColumn(name = "roleid")
	private RolesBean role;

	/** */
	@Column(name = "username", nullable = false)
	private String userName;

	/** */
	@Column(name = "password", nullable = false)
	private String password;

	/** */
	@Column(name = "createdDate", nullable = false)
	private Date createdDate;

	/** */
	@Column(name = "status", nullable = false)
	private String status;

	/** */
	@Column(name = "lastModifiedDate")
	private Date lastModifiedDate;

	/** */
	@Column(name = "deactivationDate")
	private Date deactivationDate;
	/** */
	@Column(name = "lastLoginTime")
	private Date lastLoginTime;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the role
	 */
	public RolesBean getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(RolesBean role) {
		this.role = role;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the lastModifiedDate
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate
	 *            the lastModifiedDate to set
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * @return the deactivationDate
	 */
	public Date getDeactivationDate() {
		return deactivationDate;
	}

	/**
	 * @param deactivationDate
	 *            the deactivationDate to set
	 */
	public void setDeactivationDate(Date deactivationDate) {
		this.deactivationDate = deactivationDate;
	}

	/**
	 * @return the lastLoginTime
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * @param lastLoginTime
	 *            the lastLoginTime to set
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * @return the userInformationBean
	 */
	public UserInformationBean getUserInformationBean() {
		return userInformationBean;
	}

	/**
	 * @param userInformationBean
	 *            the userInformationBean to set
	 */
	public void setUserInformationBean(UserInformationBean userInformationBean) {
		this.userInformationBean = userInformationBean;
	}

	public UserLoginDto getUserLoginDto() {
		UserLoginDto userLoginDto = new UserLoginDto();
		userLoginDto.setId(this.getId());
		/*userLoginDto.setUserInformationDto(this.getUserInformationBean()
				.getUserInformationDto());
		userLoginDto.setRoleDto(this.role.getRolesDto());
		userLoginDto.setPassword(this.getPassword());
		userLoginDto.setUserName(this.getUserName());
		userLoginDto.setCreatedDate(this.getCreatedDate());
		userLoginDto.setStatus(this.status);
		userLoginDto.setLastModifiedDate(this.lastModifiedDate);
		userLoginDto.setDeactivationDate(this.deactivationDate);
		userLoginDto.setLastLoginTime(this.lastLoginTime);*/
		return userLoginDto;
	}
}