/**
 * 
 */
package com.hotel.roles.dao;

import java.util.List;

import com.hotel.base.common.domain.roles.bean.RolesBean;
import com.hotel.base.common.persistance.dao.DefaultDao;

/**
 * @author Prince
 */

public interface RolesDao extends DefaultDao {
	/**
	 * @param roleName
	 * @return
	 */
	RolesBean findRolesByName(String roleName);
	
	/**
	 * List of all Roles
	 * @return List<RolesDto>
	 */
	List<RolesBean> getRolesAll();
}