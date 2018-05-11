package com.rennover.client.framework.widget.list;

import com.rennover.client.framework.widget.base.ZList;
import com.rennover.client.framework.widget.base.ZScrollPane;
import com.rennover.client.framework.widget.base.ZTextArea;

/**
 * @deprecated use ZScrollPane
 * 
 * @author tcaboste
 * 
 */
public class ListScrollPane extends ZScrollPane
{
	public static final int DEFAULT_BORDER_HEIGHT = 4;

	public static final int DEFAULT_ITEM_HEIGHT = 18;

	/**
	 * Constructor for ListScrollPane.
	 */
	public ListScrollPane()
	{
		super();
	}

	/**
	 * @deprecated use ZScrollPane(ZTextArea textArea, int nbRows)
	 * @param list
	 */
	public ListScrollPane(ZTextArea textArea)
	{
		this(textArea, 3, 5, 8);
	}

	/**
	 * @deprecated use ZScrollPane(ZTextArea textArea, int nbRows)
	 * @param textArea
	 * @param minRows
	 * @param prefRows
	 * @param maxRows
	 */
	public ListScrollPane(ZTextArea textArea, int minRows, int prefRows, int maxRows)
	{
		super(textArea);
		setSizes(textArea, minRows, prefRows, maxRows);
	}

	/**
	 * @deprecated use ZScrollPane(ZList list, int nbRows)
	 * @param list
	 */
	public ListScrollPane(ZList list)
	{
		this(list, 3, 5, 8);
	}

	/**
	 * @deprecated use ZScrollPane(ZList list, int nbRows)
	 * @param list
	 * @param minRows
	 * @param prefRows
	 * @param maxRows
	 */
	public ListScrollPane(ZList list, int minRows, int prefRows, int maxRows)
	{
		super(list);
		setSizes(list, minRows, prefRows, maxRows);
	}
}