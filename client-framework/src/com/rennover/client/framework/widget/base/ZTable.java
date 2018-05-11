package com.rennover.client.framework.widget.base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;

/**
 * @deprecate use DefaultTable or SortedTable or ValueObjectListTable
 * 
 * Une JTable dont les headers sont des boutons qui permettent de trier les
 * colonnes. Des flèches dans les headers indiquent l'ordre de tri.<br>
 * Les {@link com.rennover.hotel.common.valueobject.ValueObject}s ne sont pas
 * supportés explicitement; il faut utiliser un modèle particulier pour les
 * afficher.
 * 
 * <p>
 * En pratique, elle doit être utilisée en conjonction avec {@link
 * com.agefospme.nsicm.client.common.model.ZSpliteredObjectListTableModel} (pour
 * afficher des {@link com.rennover.hotel.common.valueobject.ValueObject}s) ou
 * avec
 * {@link com.agefospme.nsicm.client.common.model.ZLargeObjectListTableModel}
 * (si l'on ne veut pas récupérer tous ces ValueObjects en une seule fois depuis
 * la base de données).
 * </p>
 * 
 * <p>
 * Noter que, quelque soit le modèle utilisé pour cette table, les headers
 * doivent être définis dans une instance de {@link
 * com.agefospme.nsicm.client.common.model.TableCellObjectSplitter.ColumnHeaderValue}
 * qui sera passée en paramètre du modèle.
 * </p>
 * 
 * <p>
 * Pour une table qui supporte JetForm, utiliser {@link
 * com.agefospme.nsicm.client.common.print.PrintableTable}.
 * </p>
 */
public class ZTable extends JTable
{
	private Action m_selectionAction;

	private Action m_doubleClickAction;

	private int m_visibleRowCount = 8;

	public static final int SINGLE_SELECTION = ListSelectionModel.SINGLE_SELECTION;

	public static final int SINGLE_INTERVAL_SELECTION = ListSelectionModel.SINGLE_INTERVAL_SELECTION;

	public static final int MULTIPLE_INTERVAL_SELECTION = ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;

	public ZTable()
	{
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		init();
	}

	public ZTable(Object[][] rowData, Object[] columns)
	{
		super(rowData, columns);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		init();
	}

	private void init()
	{
		addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2 && m_doubleClickAction != null)
				{
					ActionEvent actEvent = new ActionEvent(ZTable.this, ActionEvent.ACTION_PERFORMED, null);
					if (m_doubleClickAction != null)
					{
						m_doubleClickAction.actionPerformed(actEvent);
					}
				}
			}
		});

		// removed the commented ListSelectionListener

		setVisibleRowCount(8);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JTable#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	public void valueChanged(ListSelectionEvent e)
	{
		if (!e.getValueIsAdjusting())
		{

			if (m_selectionAction != null)
			{
				ActionEvent actEvent = new ActionEvent(ZTable.this, ActionEvent.ACTION_PERFORMED, null);
				m_selectionAction.actionPerformed(actEvent);
			}

		}

		super.valueChanged(e);
	}

	public Dimension getMinimumSize()
	{
		return super.getMinimumSize();
	}

	public Dimension getPreferredSize()
	{
		return super.getPreferredSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#getMaximumSize()
	 */
	public Dimension getMaximumSize()
	{
		return super.getMaximumSize();
	}

	/**
	 * Returns the preferred number of visible rows.
	 * 
	 * @return an integer indicating the preferred number of rows to display
	 *         without using a scroll bar
	 * @see #setVisibleRowCount
	 */
	public int getVisibleRowCount()
	{
		return m_visibleRowCount;
	}

	/**
	 * Sets the preferred number of rows in the list that can be displayed
	 * without a scrollbar, as determined by the nearest <code>JViewport</code>
	 * ancestor, if any. The value of this property only affects the value of
	 * the <code>ZTable</code>'s <code>preferredScrollableViewportSize</code>.
	 * <p>
	 * The default value of this property is 8.
	 * <p>
	 * This is a JavaBeans bound property.
	 * 
	 * @param visibleRowCount
	 *            an integer specifying the preferred number of visible rows
	 * @see #getVisibleRowCount
	 * @see JComponent#getVisibleRect
	 * @see JViewport
	 * @beaninfo bound: true attribute: visualUpdate true description: The
	 *           preferred number of cells that can be displayed without a
	 *           scroll bar.
	 */
	public void setVisibleRowCount(int visibleRowCount)
	{
		int oldValue = this.m_visibleRowCount;
		this.m_visibleRowCount = Math.max(0, visibleRowCount);
		firePropertyChange("visibleRowCount", oldValue, visibleRowCount);
	}

	public Dimension getPreferredScrollableViewportSize()
	{
		int visibleRowCount = getVisibleRowCount();
		int width = super.getPreferredSize().width;
		int fixedCellHeight = getRowHeight(); // + getRowMargin();
		fixedCellHeight = (fixedCellHeight > 0) ? fixedCellHeight : 16;
		int height = fixedCellHeight * visibleRowCount;

		Dimension size = new Dimension(width, height);
		Insets insets = getInsets();
		size.width += insets.left + insets.right;
		size.height += insets.top + insets.bottom;
		return size;
	}

	public void setEnabled(boolean enabled)
	{
		Color defaultBackground = UIManager.getColor("Table.background");
		Color disabledBackground = UIManager.getColor("Table.disabledBackground");
		setBackground(enabled ? defaultBackground : disabledBackground);
		super.setEnabled(enabled);
	}

	/**
	 * Permet d'ajouter un listener de sélection sur la table
	 */
	public void addListSelectionListener(ListSelectionListener listener)
	{
		ListSelectionModel rowSelectionModel = getSelectionModel();
		rowSelectionModel.addListSelectionListener(listener);
	}

	/**
	 * Permet d'enlever un listener de sélection sur la table
	 */
	public void removeListSelectionListener(ListSelectionListener listener)
	{
		ListSelectionModel rowSelectionModel = getSelectionModel();
		rowSelectionModel.removeListSelectionListener(listener);
	}

	/**
	 * Permet de sélectionner une ligne de la table
	 */
	public void setSelectedRow(int index)
	{
		if (index == -1)
		{
			getSelectionModel().clearSelection();
		} else
		{
			getSelectionModel().setSelectionInterval(index, index);
		}
	}

	protected void updateRow(int index)
	{
		if (index != -1)
		{
			ListSelectionEvent e = new ListSelectionEvent(this, index, index, true);
			valueChanged(e);
		}
	}

	/**
	 * Permet de sélectionner une ligne de la table
	 */
	public void setSelectedRows(int[] indices)
	{
		ListSelectionModel sm = getSelectionModel();
		sm.clearSelection();
		for (int i = 0; i < indices.length; i++)
		{
			sm.addSelectionInterval(indices[i], indices[i]);
		}
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

	public TableCellRenderer getDefaultRenderer(Class columnClass)
	{
		if (columnClass == null)
		{
			return getDefaultRenderer(Object.class);
		} else
		{
			Object renderer = defaultRenderersByColumnClass.get(columnClass);
			if (renderer != null)
			{
				return (TableCellRenderer) renderer;
			} else
			{
				return getDefaultRenderer(columnClass.getSuperclass());
			}
		}
	}
}