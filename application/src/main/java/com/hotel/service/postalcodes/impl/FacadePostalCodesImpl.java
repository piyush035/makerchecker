/**
 * 
 */
package com.hotel.service.postalcodes.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import com.hotel.base.common.domain.postalcodes.dto.PostalCodesDto;
import com.hotel.business.postalcodes.PostalCodesBusinessManager;
import com.hotel.service.postalcodes.FacadePostalCodes;

/**
 * @author Prince
 * 
 */

@Remote(FacadePostalCodes.class)
@Stateless(name = "facadePostalCodes")
@javax.interceptor.Interceptors(SpringBeanAutowiringInterceptor.class)
public class FacadePostalCodesImpl implements FacadePostalCodes {
	/** */
	@Autowired
	private PostalCodesBusinessManager postalcodesBusinessManager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<PostalCodesDto> getPostalCodesAll() {
		return postalcodesBusinessManager.getPostalCodesAll();
	}
}
