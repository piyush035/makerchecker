/**
 * 
 */
package com.hotel.service.states.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import com.hotel.base.common.domain.states.dto.StatesDto;
import com.hotel.business.states.StatesBusinessManager;
import com.hotel.service.states.FacadeStates;

/**
 * @author Prince
 * 
 */

@Remote(FacadeStates.class)
@Stateless(name = "facadeStates")
@javax.interceptor.Interceptors(SpringBeanAutowiringInterceptor.class)
public class FacadeStatesImpl implements FacadeStates {
	/** */
	@Autowired
	private StatesBusinessManager statesBusinessManager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<StatesDto> getStatesAll() {
		return statesBusinessManager.getStatesAll();
	}
}
