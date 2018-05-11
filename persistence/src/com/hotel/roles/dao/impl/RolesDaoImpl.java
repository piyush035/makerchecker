package com.hotel.roles.dao.impl;

import java.util.List;

import com.hotel.base.common.domain.roles.bean.RolesBean;
import com.hotel.base.common.persistance.dao.impl.AbstractDefaultDao;
import com.hotel.roles.dao.RolesDao;

/**
 * @author Prince
 */
public class RolesDaoImpl extends AbstractDefaultDao implements RolesDao {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolesBean findRolesByName(String roleName) {
		List<RolesBean> rolesList = getHibernateTemplate().find(
				"FROM com.hotel.base.common.domain.roles.bean.RolesBean roles where roles.roleName = '" + roleName
						+ "'");
		if (!rolesList.isEmpty()) {
			return rolesList.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<RolesBean> getRolesAll() {
		return getHibernateTemplate().find("FROM com.hotel.base.common.domain.roles.bean.RolesBean roles");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class getDefaultClassBean() {
		return RolesBean.class;
	}
}