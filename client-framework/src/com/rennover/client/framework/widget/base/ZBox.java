package com.rennover.client.framework.widget.base;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;

/**
 * @author tcaboste
 * 
 */
public class ZBox extends Box
{
	/**
	 * Constructor for ZBox.
	 * 
	 * @param axis
	 */
	public ZBox(int axis)
	{
		super(axis);
	}

	/**
	 * Correction createHorizontalGlue
	 * 
	 * @return the horizontal glue component
	 */
	public static Component createHorizontalGlue()
	{
		return new Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(Integer.MAX_VALUE, 0));
	}

	/**
	 * Correction createVerticalGlue
	 * 
	 * @return the vertical glue component
	 */
	public static Component createVerticalGlue()
	{
		return new Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, Integer.MAX_VALUE));
	}
}