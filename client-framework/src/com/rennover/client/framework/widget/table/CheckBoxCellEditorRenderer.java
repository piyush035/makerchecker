package com.rennover.client.framework.widget.table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.rennover.client.framework.widget.base.ZCheckBox;
import com.rennover.client.framework.widget.base.ZPanel;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CheckBoxCellEditorRenderer extends AbstractCellEditor implements TableCellEditor, TableCellRenderer
{
	protected static final Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

	private ZCheckBox m_component = new ZCheckBox();

	private ZPanel m_panel = new ZPanel(new BorderLayout());

	/**
	 * Constructor for NationalCcnTableCellEditorRenderer.
	 */
	public CheckBoxCellEditorRenderer()
	{
		super();
		m_component.setBorderPaintedFlat(true);
		m_component.setHorizontalAlignment(JCheckBox.CENTER);
		m_component.setOpaque(false);

		m_component.addActionListener(new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				stopCellEditing();
			}
		});

		m_component.addFocusListener(new FocusAdapter()
		{
			public void focusLost(FocusEvent e)
			{
				stopCellEditing();
			}
		});

		m_panel.add(m_component);
	}

	/**
	 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(JTable,
	 *      Object, boolean, int, int)
	 */
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		boolean hasFocus = true;
		getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		return m_component;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	        int row, int column)
	{
		boolean selected = false;

		if (value != null)
		{
			selected = ((Boolean) value).booleanValue();
		}

		m_component.setSelected(selected);
		m_component.setEnabled(table.isCellEditable(row, column));

		if (isSelected || hasFocus)
		{
			m_component.setForeground(table.getSelectionForeground());
			m_panel.setBackground(table.getSelectionBackground());
		} else
		{
			m_component.setForeground(table.getForeground());
			m_panel.setBackground(table.isCellEditable(row, column) ? table.getBackground() : UIManager
			        .getColor("Table.disabledBackground"));
		}

		m_panel.setBorder(hasFocus ? UIManager.getBorder("Table.focusCellHighlightBorder") : noFocusBorder);

		return m_panel;
	}

	/**
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	public Object getCellEditorValue()
	{
		return Boolean.valueOf(m_component.isSelected());
	}
}
