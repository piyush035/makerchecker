package com.rennover.client.framework.widget.table;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.rennover.client.framework.widget.field.NumericSpinnerField;

/**
 * @author gNanda
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class NumericSpinnerFieldCellEditorRenderer extends AbstractCellEditor implements TableCellEditor,
        TableCellRenderer
{
	/**
	 * This field will hold the instance of SpinnerField
	 */
	private NumericSpinnerField m_component;

	/**
	 * Constructor for NationalCcnTableCellEditorRenderer.
	 * 
	 * @param Class
	 *            numberClass
	 * @param int
	 *            value
	 * @param int
	 *            minimum
	 * @param int
	 *            maximum
	 * @param int
	 *            stepSize
	 * @param int
	 *            nbCaracters
	 */
	public NumericSpinnerFieldCellEditorRenderer(Class numberClass, int value, int minimum, int maximum, int stepSize,
	        int nbCaracters)
	{
		super();
		m_component = new NumericSpinnerField(numberClass, value, minimum, maximum, stepSize, nbCaracters);
		m_component.addFocusListener(new FocusAdapter()
		{
			public void focusLost(FocusEvent e)
			{
				stopCellEditing();
			}
		});
	}

	/**
	 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent
	 *      (JTable, Object, boolean, int, int)
	 * @param JTable
	 *            table
	 * @param Object
	 *            value
	 * @param boolean
	 *            isSelected
	 * @param int
	 *            row
	 * @param int
	 *            column
	 * @return Component
	 */
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		m_component.setValue(value);
		return m_component;
	}

	/**
	 * @see javax.swing.table. TableCellRenderer#
	 *      getTableCellEditorComponentgetTableCellRendererComponent (JTable,
	 *      Object, boolean, boolean, int,int)
	 * @param JTable
	 *            table
	 * @param Object
	 *            value
	 * @param boolean
	 *            isSelected
	 * @param boolean
	 *            hasFocus
	 * @param int
	 *            row
	 * @param int
	 *            column
	 * @return Component
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	        int row, int column)
	{
		m_component.setValue(value);
		m_component.setEnabled(table.isCellEditable(row, column));
		if (isSelected || hasFocus)
		{
			m_component.setForeground(table.getSelectionForeground());
		} else
		{
			m_component.setForeground(table.getForeground());
		}
		return m_component;
	}

	/**
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 * @return Object
	 */
	public Object getCellEditorValue()
	{
		return m_component.getValue();
	}
}
