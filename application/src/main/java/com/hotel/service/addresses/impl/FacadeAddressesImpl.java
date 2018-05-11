/**
 * 
 */
package com.hotel.service.addresses.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import com.hotel.service.addresses.FacadeAddresses;

/**
 * @author Prince
 * 
 */

@Remote(FacadeAddresses.class)
@Stateless(name = "facadeAddresses")
@javax.interceptor.Interceptors(SpringBeanAutowiringInterceptor.class)
public class FacadeAddressesImpl implements FacadeAddresses {
	/** *//*
	@Autowired
	private AddressesBusinessManager addressesBusinessManager;

	*//**
	 * {@inheritDoc}
	 *//*
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<AddressesDto> getAddressesAll() {
		return addressesBusinessManager.getAddressesAll();
	}*/
}
