package com.rennover.client.framework.widget.base;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.util.Set;

import javax.swing.JSplitPane;

public class ZSplitPane extends JSplitPane
{
	/**
	 * Constructor for ZSplitPane.
	 */
	public ZSplitPane()
	{
		super();
		init();
	}

	/**
	 * Constructor for ZSplitPane.
	 * 
	 * @param newOrientation
	 */
	public ZSplitPane(int newOrientation)
	{
		super(newOrientation);
		init();
	}

	/**
	 * Constructor for ZSplitPane.
	 * 
	 * @param newOrientation
	 * @param newContinuousLayout
	 */
	public ZSplitPane(int newOrientation, boolean newContinuousLayout)
	{
		super(newOrientation, newContinuousLayout);
		init();
	}

	/**
	 * Constructor for ZSplitPane.
	 * 
	 * @param newOrientation
	 * @param newLeftComponent
	 * @param newRightComponent
	 */
	public ZSplitPane(int newOrientation, Component newLeftComponent, Component newRightComponent)
	{
		super(newOrientation, newLeftComponent, newRightComponent);
		init();
	}

	/**
	 * Constructor for ZSplitPane.
	 * 
	 * @param newOrientation
	 * @param newContinuousLayout
	 * @param newLeftComponent
	 * @param newRightComponent
	 */
	public ZSplitPane(int newOrientation, boolean newContinuousLayout, Component newLeftComponent,
	        Component newRightComponent)
	{
		super(newOrientation, newContinuousLayout, newLeftComponent, newRightComponent);
		init();
	}

	/**
	 * Method init.
	 */
	private void init()
	{
		setAutoscrolls(true);
		setResizeWeight(0.5);

		Set forwardTraversalKeys = KeyboardFocusManager.getCurrentKeyboardFocusManager().getDefaultFocusTraversalKeys(
		        KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
		setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardTraversalKeys);

		Set backwardTraversalKeys = KeyboardFocusManager.getCurrentKeyboardFocusManager().getDefaultFocusTraversalKeys(
		        KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS);
		setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, backwardTraversalKeys);
	}
}