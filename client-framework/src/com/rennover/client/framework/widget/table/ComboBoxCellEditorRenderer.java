package com.rennover.client.framework.widget.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.field.ValueObjectComboBoxField;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ComboBoxCellEditorRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer
{
	protected static final Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

	private ValueObjectComboBoxField m_component = new ValueObjectComboBoxField();

	private ZLabel m_renderer = new ZLabel();

	/**
	 * Constructor for NationalCcnTableCellEditorRenderer.
	 */
	public ComboBoxCellEditorRenderer(List valueList, boolean useNullValue)
	{
		m_component = new ValueObjectComboBoxField(valueList);
		m_component.setUseNullValue(useNullValue);
		commonConstructor();
	}

	public ComboBoxCellEditorRenderer(ValueObjectListModel volm)
	{
		m_component = new ValueObjectComboBoxField(volm);
		commonConstructor();
	}

	private void commonConstructor()
	{
		m_component.setBorder(noFocusBorder);
		m_component.addFocusListener(new FocusAdapter()
		{
			public void focusLost(FocusEvent e)
			{
				stopCellEditing();
			}
		});

		m_component.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				stopCellEditing();
			}
		});

		m_renderer.setOpaque(true);
		m_renderer.setBorder(noFocusBorder);
	}

	public void setObjectIdMode(boolean objectIdMode)
	{
		m_component.setObjectIdMode(objectIdMode);
	}

	public void setUseNullValue(boolean useNullValue)
	{
		m_component.setUseNullValue(useNullValue);
	}

	public void setStringForNullValue(String stringForNull)
	{
		m_component.setStringForNullValue(stringForNull);
	}

	/**
	 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(JTable,
	 *      Object, boolean, int, int)
	 */
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		m_component.setValue(value);
		m_component.setEnabled(table.isCellEditable(row, column));

		if (isSelected)
		{
			m_component.setForeground(table.getSelectionForeground());
			m_component.setBackground(table.getSelectionBackground());
		} else
		{
			m_component.setForeground(table.getForeground());
			m_component.setBackground(table.isCellEditable(row, column) ? table.getBackground() : UIManager
			        .getColor("Table.disabledBackground"));
		}

		return m_component;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	        int row, int column)
	{
		m_component.setValue(value);

		Object item = m_component.getSelectedItem();
		m_renderer.setText(item != null ? item.toString() : m_component.getStringForNullValue());
		m_renderer.setToolTipText(m_renderer.getText());

		if (isSelected || hasFocus)
		{
			m_renderer.setForeground(table.getSelectionForeground());
			m_renderer.setBackground(table.getSelectionBackground());
		} else
		{
			m_renderer.setForeground(table.getForeground());
			m_renderer.setBackground(table.isCellEditable(row, column) ? table.getBackground() : UIManager
			        .getColor("Table.disabledBackground"));
		}

		m_renderer.setFont(table.getFont());
		m_renderer.setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : noFocusBorder);

		return m_renderer;
	}

	/**
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	public Object getCellEditorValue()
	{
		return m_component.getValue();
	}
}
