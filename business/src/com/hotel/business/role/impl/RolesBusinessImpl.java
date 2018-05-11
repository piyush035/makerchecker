package com.hotel.business.role.impl;

import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusiness;
import com.hotel.business.role.RolesBusiness;
import com.hotel.roles.dao.RolesDao;

/**
 * @author Piyush
 */
public class RolesBusinessImpl extends AbstractDefaultBusiness implements RolesBusiness {
	
	/** DAO for User Login */
	private RolesDao rolesDao;
	
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
}