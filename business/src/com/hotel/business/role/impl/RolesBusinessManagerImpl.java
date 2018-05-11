package com.hotel.business.role.impl;

import java.util.ArrayList;
import java.util.List;

import com.hotel.base.common.domain.roles.bean.RolesBean;
import com.hotel.base.common.domain.roles.dto.RolesDto;
import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusinessManager;
import com.hotel.business.role.RolesBusiness;
import com.hotel.business.role.RolesBusinessManager;
import com.hotel.roles.dao.RolesDao;

/**
 * @author Piyush
 */
public class RolesBusinessManagerImpl extends AbstractDefaultBusinessManager implements RolesBusinessManager {
	
	/** */
	private RolesDao rolesDao;
	
	/** Businees. */
	private RolesBusiness rolesBusiness;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DefaultDao getDefaultDao() {
		return rolesDao;
	}
	
	/**
	 * @param DefaultDao the DefaultDao to set
	 */
	public void setDefaultDao(final RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}
	
	/**
	 * @return the rolesBusiness
	 */
	public RolesBusiness getRolesBusiness() {
		return rolesBusiness;
	}
	
	/**
	 * @param rolesBusiness the rolesBusiness to set
	 */
	public void setRolesBusiness(final RolesBusiness rolesBusiness) {
		this.rolesBusiness = rolesBusiness;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RolesDto> getRolesAll() {
		List<RolesBean> rolesList = rolesDao.getRolesAll();
		List<RolesDto> rolesDtoList = new ArrayList<RolesDto>();
		for (RolesBean bean : rolesList) {
			rolesDtoList.add(bean.getRolesDto());
		}
		return rolesDtoList;
	}
}