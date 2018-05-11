package com.rennover.client.framework.widget.base;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.rennover.client.framework.widget.list.ZListModel;

public class ZList extends JList
{
	private Action m_selectionAction;

	private Action m_doubleClickAction;

	/**
	 * Constructor for ZList.
	 * 
	 * @param dataModel
	 */
	public ZList(ListModel dataModel)
	{
		super(dataModel);
		init();
	}

	/**
	 * Constructor for ZList.
	 * 
	 * @param listData
	 */
	public ZList(Object[] listData)
	{
		super(listData);
		init();
	}

	/**
	 * Constructor for ZList.
	 * 
	 * @param listData
	 */
	public ZList(Vector listData)
	{
		super(listData);
		init();
	}

	public ZList()
	{
		super(new ZListModel());
		init();
	}

	private void init()
	{
		setCellRenderer(new ZDefaultListCellRenderer());

		addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2 && m_doubleClickAction != null)
				{
					ActionEvent actEvent = new ActionEvent(ZList.this, ActionEvent.ACTION_PERFORMED, null);
					m_doubleClickAction.actionPerformed(actEvent);
				}
			}
		});

		addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				ActionEvent actEvent = new ActionEvent(ZList.this, ActionEvent.ACTION_PERFORMED, null);
				if (m_selectionAction != null)
				{
					m_selectionAction.actionPerformed(actEvent);
				}
			}
		});

		setVisibleRowCount(5);
		setFixedCellHeight(21);
		setMinimumSize(new Dimension(10, getPreferredHeight(3)));
	}

	/**
	 * Correction du BUG 4654916 : JList and JTree should scroll automatically
	 * with first-letter navigation
	 * 
	 * @see http://developer.java.sun.com/developer/bugParade/bugs/4654916.html
	 */
	public void setSelectedIndex(int index)
	{
		super.setSelectedIndex(index);
		ensureIndexIsVisible(index);
	}

	/**
	 * Associe une action sur l'évènement de sélection dans la table
	 */
	public void setSelectionAction(Action action)
	{
		m_selectionAction = action;
	}

	/**
	 * Associe une action sur l'évènement de double-clic sur un élément de la
	 * table
	 */
	public void setDoubleClickAction(Action action)
	{
		m_doubleClickAction = action;
	}

	public int getValueIndex(Object anObject)
	{
		if (anObject != null)
		{
			int i;
			int c;
			ListModel dm = getModel();
			for (i = 0, c = dm.getSize(); i < c; i++)
			{
				if (anObject.equals(dm.getElementAt(i)))
				{
					return i;
				}
			}
		}
		return -1;
	}

	public void setSelectedValues(List values)
	{
		if (values != null)
		{
			List selectedValues = (List) values;
			int valuesCount = selectedValues.size();
			int[] selectedIndex = new int[valuesCount];
			for (int i = 0; i < valuesCount; i++)
			{
				Object value = selectedValues.get(i);
				selectedIndex[i] = getValueIndex(value);
			}

			setSelectedIndices(selectedIndex);
		} else
		{
			clearSelection();
		}
	}

	public void setEnabled(boolean enabled)
	{
		Color defaultBackground = UIManager.getColor("List.background");
		Color disabledBackground = UIManager.getColor("List.disabledBackground");
		setBackground(enabled ? defaultBackground : disabledBackground);

		Color foreground = UIManager.getColor("List.foreground");
		Color disabledForeground = UIManager.getColor("List.disabledForeground");
		setForeground(enabled ? foreground : disabledForeground);

		super.setEnabled(enabled);
	}

	public Dimension getPreferredScrollableViewportSize()
	{
		return getPreferredSize(getVisibleRowCount());
	}

	public Dimension getPreferredSize(int visibleRowCount)
	{
		if (getLayoutOrientation() != VERTICAL)
		{
			return getPreferredSize();
		}
		Insets insets = getInsets();
		int dx = insets.left + insets.right;
		int dy = insets.top + insets.bottom;

		int fixedCellWidth = getFixedCellWidth();
		int fixedCellHeight = getFixedCellHeight();

		if ((fixedCellWidth > 0) && (fixedCellHeight > 0))
		{
			int width = fixedCellWidth + dx;
			int height = (visibleRowCount * fixedCellHeight) + dy;
			return new Dimension(width, height);
		} else if (getModel().getSize() > 0)
		{
			int width = getPreferredSize().width;
			int height;
			Rectangle r = getCellBounds(0, 0);
			if (r != null)
			{
				height = (visibleRowCount * r.height) + dy;
			} else
			{
				// Will only happen if UI null, shouldn't matter what we return
				height = 1;
			}
			return new Dimension(width, height);
		} else
		{
			fixedCellWidth = (fixedCellWidth > 0) ? fixedCellWidth : 16;
			fixedCellHeight = (fixedCellHeight > 0) ? fixedCellHeight : 16;
			return new Dimension(fixedCellWidth, fixedCellHeight * visibleRowCount);
		}
	}

	public int getPreferredHeight(int visibleRowCount)
	{
		if (getLayoutOrientation() != VERTICAL)
		{
			return getPreferredSize().height;
		}

		Insets insets = getInsets();
		int dy = insets.top + insets.bottom;

		int fixedCellHeight = getFixedCellHeight();

		if (fixedCellHeight > 0)
		{
			int height = (visibleRowCount * fixedCellHeight) + dy;
			return height;
		} else if (getModel().getSize() > 0)
		{
			int height;
			Rectangle r = getCellBounds(0, 0);
			if (r != null)
			{
				height = (visibleRowCount * r.height) + dy;
			} else
			{
				// Will only happen if UI null, shouldn't matter what we return
				height = 1;
			}
			return height;
		} else
		{
			fixedCellHeight = (fixedCellHeight > 0) ? fixedCellHeight : getFontMetrics(getFont()).getHeight();
			return fixedCellHeight * visibleRowCount;
		}
	}

	static class ZDefaultListCellRenderer extends DefaultListCellRenderer
	{
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
		        boolean cellHasFocus)
		{
			setComponentOrientation(list.getComponentOrientation());
			if (isSelected)
			{
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else
			{
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			if (value instanceof Icon)
			{
				setIcon((Icon) value);
				setText("");
			} else
			{
				setIcon(null);
				setText((value == null) ? "" : value.toString());
			}

			setFont(list.getFont());
			setBorder(cellHasFocus ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);

			setToolTipText(getText());

			return this;
		}
	}
}