package com.rennover.client.framework.widget.list;

import java.awt.Dimension;

import com.rennover.client.framework.widget.base.ZScrollPane;
import com.rennover.client.framework.widget.base.ZTable;

/**
 * @deprecated use ZScrollPane()
 * 
 * @author tcaboste
 * 
 */
public class TableScrollPane extends ZScrollPane
{
	public static final int DEFAULT_BORDER_HEIGHT = 4;

	public static final int DEFAULT_HEADER_HEIGHT = 21;

	public static final int DEFAULT_ITEM_HEIGHT = 16;

	/**
	 * Constructor for ListScrollPane.
	 */
	public TableScrollPane()
	{
		super();
		init();
	}

	/**
	 * @deprecated use ZScrollPane(Ztable table)
	 * @param displayedComponent
	 */
	public TableScrollPane(ZTable displayedComponent)
	{
		super(displayedComponent);
	}

	/**
	 * @deprecated use ZScrollPane(Ztable table, int nbRows)
	 * @param displayedComponent
	 * @param minRows
	 * @param prefRows
	 * @param maxRows
	 */
	public TableScrollPane(ZTable displayedComponent, int minRows, int prefRows, int maxRows)
	{
		super(displayedComponent, prefRows);
	}

	private void init()
	{
		setMinimumSize(new Dimension(100, DEFAULT_ITEM_HEIGHT * 3 + DEFAULT_HEADER_HEIGHT + DEFAULT_BORDER_HEIGHT));
		setPreferredSize(new Dimension(100, DEFAULT_ITEM_HEIGHT * 5 + DEFAULT_HEADER_HEIGHT + DEFAULT_BORDER_HEIGHT));
		setMaximumSize(new Dimension(100, DEFAULT_ITEM_HEIGHT * 8 + DEFAULT_HEADER_HEIGHT + DEFAULT_BORDER_HEIGHT));
	}
}