/**
 * 
 */
package com.hotel.service.role.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import com.hotel.base.common.domain.roles.dto.RolesDto;
import com.hotel.business.role.RolesBusinessManager;
import com.hotel.service.role.FacadeRoles;

/**
 * @author Piyush
 */

@Remote(FacadeRoles.class)
@Stateless(name = "facadeRoles")
@javax.interceptor.Interceptors(SpringBeanAutowiringInterceptor.class)
public class FacadeRolesImpl implements FacadeRoles {
	/** */
	@Autowired
	private RolesBusinessManager rolesBusinessManager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<RolesDto> getRolesAll() {
		return rolesBusinessManager.getRolesAll();
	}
}
