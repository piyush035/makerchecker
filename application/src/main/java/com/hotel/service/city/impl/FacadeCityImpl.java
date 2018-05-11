/**
 * 
 */
package com.hotel.service.city.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import com.hotel.base.common.domain.city.dto.CityDto;
import com.hotel.business.city.CityBusinessManager;
import com.hotel.service.city.FacadeCity;

/**
 * @author Prince
 * 
 */

@Remote(FacadeCity.class)
@Stateless(name = "facadeCity")
@javax.interceptor.Interceptors(SpringBeanAutowiringInterceptor.class)
public class FacadeCityImpl implements FacadeCity {
	/** */
	@Autowired
	private CityBusinessManager cityBusinessManager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<CityDto> getCityAll() {
		return cityBusinessManager.getCityAll();
	}
}
