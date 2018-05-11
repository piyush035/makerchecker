/**
 * 
 */
package com.hotel.service.country.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import com.hotel.base.common.domain.country.dto.CountryDto;
import com.hotel.business.country.CountryBusinessManager;
import com.hotel.service.country.FacadeCountry;

/**
 * @author Prince
 * 
 */

@Remote(FacadeCountry.class)
@Stateless(name = "facadeCountry")
@javax.interceptor.Interceptors(SpringBeanAutowiringInterceptor.class)
public class FacadeCountryImpl implements FacadeCountry {
	/** */
	@Autowired
	private CountryBusinessManager countryBusinessManager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<CountryDto> getCountryAll() {
		return countryBusinessManager.getCountryAll();
	}
}
