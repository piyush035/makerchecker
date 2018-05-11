/**
 * 
 */
package com.hotel.service.alert.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import com.hotel.base.common.domain.alert.dto.AlertDto;
import com.hotel.business.alert.AlertBusinessManager;
import com.hotel.service.alert.FacadeAlert;

/**
 * @author Prince
 * 
 */

@Remote(FacadeAlert.class)
@Stateless(name = "facadeAlert")
@javax.interceptor.Interceptors(SpringBeanAutowiringInterceptor.class)
public class FacadeAlertImpl implements FacadeAlert {
	/** */
	@Autowired
	private AlertBusinessManager alertBusinessManager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<AlertDto> getAlertAll() {
		return alertBusinessManager.getAlertAll();
	}
}
