package com.hotel.base.domain.transverse.business;

import java.io.Serializable;

import com.hotel.base.common.tech.bean.DefaultBean;

/**
 * @author pkgupta global methods to save, update, delete, ...
 */
public interface DefaultBusinessManager {
	/**
	 * method to find an object bean by primary key.
	 * @param key : primary key for entity bean
	 * @return obj : entity bean loaded
	 */
	DefaultBean load(Serializable key);
}