package com.rennover.client.framework.widget.table;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ColumnSize
{
	public Integer m_minWidth;

	public Integer m_preferredWidth;

	public Integer m_maxWidth;

	public boolean m_visible = true;

	public ColumnSize()
	{
		this(null, null, null);
	}

	public ColumnSize(int width)
	{
		this(new Integer(width), new Integer(width), null);
	}

	public ColumnSize(int width, boolean resizable)
	{
		this(resizable ? null : new Integer(width), new Integer(width), resizable ? null : new Integer(width));
	}

	public ColumnSize(int min, int pref)
	{
		this(new Integer(min), new Integer(pref), null);
	}

	public ColumnSize(int min, int pref, boolean resizable)
	{
		this(new Integer(min), new Integer(pref), resizable ? null : new Integer(pref));
	}

	public ColumnSize(int min, int pref, int max)
	{
		this(new Integer(min), new Integer(pref), new Integer(max));
	}

	public ColumnSize(Integer min, Integer pref, Integer max)
	{
		m_minWidth = min;
		m_preferredWidth = pref;
		m_maxWidth = max;
	}
}