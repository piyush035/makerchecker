package com.hotel.base.common.tech.dto;

import java.io.Serializable;


/**
 * 
 * @author pkgupta
 * the class of pair classes.
 * @param <One> the first class
 * @param <Tow> the seconde class
 */
public class PairDto<One,Two > implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The t value. */
	private One one;
	
	/** The e value. */
	private Two two;
	
	/**
	 * Instantiates a new pair dto.
	 * 
	 * @param t the t
	 * @param e the e
	 */
	public PairDto( One t,Two e)
	{
		this.one = t;
		this.two = e;
	}
	
	/**
	 * Gets the value one.
	 * 
	 * @return the value one
	 */
	public One getValueOne()
	{
		return one;
	}
	
	/**
	 * Gets the value tow.
	 * 
	 * @return the value tow
	 */
	public Two getValueTwo()
	{
		return two;
	}
}
