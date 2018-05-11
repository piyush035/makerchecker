package com.hotel.base.common.persistance.dao;

import java.io.Serializable;

import com.hotel.base.common.tech.bean.DefaultBean;


/**
 * 
 * @author piyush
 *
 */

public interface DefaultDao {
	/**
	 * method to create an object bean.
	 * 
	 * @param defaultBean
	 *            : object to create
	 */
	void create(DefaultBean defaultBean);

	/**
	 * method to modify an object bean.
	 * 
	 * @param defaultBean
	 *            : object to modify
	 */
	void modify(DefaultBean defaultBean);

	/**
	 * method to delete an object bean.
	 * 
	 * @param defaultBean
	 *            : Object to delete
	 */
	void delete(DefaultBean defaultBean);

	/**
	 * method to find an object bean by primary key.
	 * 
	 * @param key
	 *            : primary key for entity bean
	 * @return DefaultBean : entity bean loaded
	 */
	DefaultBean load(Serializable key);

	/**
	 * Generic method to get a default bean.
	 * 
	 * @return Class of that bean
	 */
	Class getDefaultClassBean();
}
