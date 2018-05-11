package com.hotel.base.domain.transverse.business;

import com.hotel.base.common.tech.bean.DefaultBean;

/**
 * 
 * @author pgup49
 *
 */
public interface DefaultBusiness {

	/**
	 * method to delete an object bean.
	 * @param defautBean  Object to delete
	 */
	void delete(final DefaultBean defautBean);

	/**
	 * method to modify an object bean.
	 * @param defaultBean : object to modify
	 */
	 void modify(final DefaultBean defaultBean);

	/**
	 * method to create an object bean.
	 * @param defaultBean : object to create
	 */
	void create(DefaultBean defaultBean);

}