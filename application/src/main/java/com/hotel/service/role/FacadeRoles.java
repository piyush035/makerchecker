/**
 * 
 */
package com.hotel.service.role;

import java.util.List;

import javax.ejb.Remote;

import com.hotel.base.common.domain.roles.dto.RolesDto;

/**
 * @author Piyush
 * 
 */
@Remote
public interface FacadeRoles {
	/**
	 * List of all Roles
	 * @return List<RolesDto>
	 */
	List<RolesDto> getRolesAll();
}
