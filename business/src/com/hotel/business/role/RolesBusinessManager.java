/**
 * 
 */
package com.hotel.business.role;

import java.util.List;

import com.hotel.base.common.domain.roles.dto.RolesDto;
import com.hotel.base.domain.transverse.business.DefaultBusinessManager;

/**
 * @author Piyush
 *
 */
public interface RolesBusinessManager extends DefaultBusinessManager{
	/**
	 * List of all Roles
	 * @return List<RolesDto>
	 */
	List<RolesDto> getRolesAll();
}
