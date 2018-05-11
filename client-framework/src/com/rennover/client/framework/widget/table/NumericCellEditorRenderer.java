package com.rennover.client.framework.widget.table;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.field.NumericField;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class NumericCellEditorRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer
{
	protected static final Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

	private ZLabel m_renderer = new ZLabel();

	private NumericField m_component;

	private JTable m_table;

	private static final boolean DEBUG = false;

	/**
	 * Constructor for NationalCcnTableCellEditorRenderer.
	 */
	public NumericCellEditorRenderer(Class numberClass, int length, int dec, boolean useThousandSeparator)
	{
		this(numberClass, length, dec, useThousandSeparator, true);
	}

	public NumericCellEditorRenderer(Class numberClass, int length, int dec, boolean useThousandSeparator,
	        boolean useNegative)

	{
		super();
		m_component = new NumericField(numberClass, length, dec, true)
		{
			public void setValidField(boolean valid)
			{
			}
		};

		m_component.setUsingThousandSeparator(useThousandSeparator);
		m_component.setUseNegative(useNegative);
		m_component.addFocusListener(new FocusAdapter()
		{
			public void focusGained(FocusEvent e)
			{
				if (DEBUG)
				{
					System.out.println("focusGained - editor");
				}
				m_enteringNewCell = false;

			}

			public void focusLost(FocusEvent e)
			{
				if (DEBUG)
				{
					System.out.println("focusLost - editor  (enteringNewCell =" + m_enteringNewCell + ")");
				}
				if (!m_enteringNewCell)
				{
					stopCellEditing();
				}
			}
		});

		m_renderer.setOpaque(true);
		m_renderer.setBorder(noFocusBorder);
		m_renderer.setHorizontalAlignment(ZLabel.RIGHT);

	}

	private boolean m_enteringNewCell = false;

	public boolean stopCellEditing()
	{
		if (DEBUG)
		{
			System.out.println("stopCellEditing");
		}

		return super.stopCellEditing();
	}

	public void cancelCellEditing()
	{
		if (DEBUG)
		{
			System.out.println("cancelCellEditing");
		}

		super.cancelCellEditing();
	}

	/**
	 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(JTable,
	 *      Object, boolean, int, int)
	 */
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		if (DEBUG)
		{
			System.out.println("getTableCellEditorComponent (" + row + "," + column + ")");
		}

		boolean hasFocus = true;

		getTableCellRendererComponent(table, value, false, hasFocus, row, column);

		m_component.selectAll();

		m_enteringNewCell = true;

		return m_component;
	}

	private String m_stringForNull = "";

	public void setStringForNullValue(String stringForNull)
	{
		m_stringForNull = stringForNull;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	        int row, int column)
	{
		m_table = table;

		if (value == null)
		{
			m_renderer.setText(m_stringForNull);
			return DefaultTableCellRenderer.adaptColorComponent(m_renderer, table, value, isSelected, hasFocus, row,
			        column);
		}

		else
		{
			m_component.setValue(value);

			return DefaultTableCellRenderer.adaptColorComponent(m_component, table, value, isSelected, hasFocus, row,
			        column);
		}
	}

	/**
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	public Object getCellEditorValue()
	{
		return m_component.getValue();
	}

}