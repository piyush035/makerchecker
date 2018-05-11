
package com.rennover.client.framework.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.IconUIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rennover.client.framework.widget.base.ZDialog;
import com.rennover.client.framework.widget.base.ZFrame;

/**
 * <p>
 * Titre :
 * </p>
 * <p>
 * Description :
 * </p>
 * <p>
 * Copyright : Copyright (c) 2002
 * </p>
 * <p>
 * Société :
 * </p>
 * 
 * @author non attribué
 * @version 1.0
 */
public class ShowUIDefaults extends ZDialog implements ActionListener
{
	JDialog m_frame;

	JTabbedPane m_tabbedPane;

	JButton m_metal;

	JButton m_windows;

	JButton m_motif;

	SampleRenderer m_sampleRenderer;

	public ShowUIDefaults(ZFrame parent, String title)
	{
		super(parent, title);
		m_frame = this;

		setModal(false);

		getContentPane().setLayout(new BorderLayout());
		m_tabbedPane = getTabbedPane();
		getContentPane().add(m_tabbedPane);

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 3));
		getContentPane().add(buttons, BorderLayout.SOUTH);

		m_metal = new JButton("Metal");
		m_metal.addActionListener(this);
		buttons.add(m_metal);

		m_windows = new JButton("Windows");
		m_windows.addActionListener(this);
		buttons.add(m_windows);

		m_motif = new JButton("Motif");
		m_motif.addActionListener(this);
		buttons.add(m_motif);
	}

	public void actionPerformed(ActionEvent e)
	{
		String laf = "";
		Object o = e.getSource();

		if (o == m_metal)
		{
			laf = "javax.swing.plaf.metal.MetalLookAndFeel";
		} else if (o == m_windows)
		{
			laf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		} else if (o == m_motif)
		{
			laf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		}

		try
		{
			UIManager.setLookAndFeel(laf);
		} catch (Exception e2)
		{
			System.out.println(e2);
		}

		getContentPane().remove(m_tabbedPane);
		m_tabbedPane = getTabbedPane();
		getContentPane().add(m_tabbedPane);
		SwingUtilities.updateComponentTreeUI(m_frame);
		m_frame.pack();
	}

	private JTabbedPane getTabbedPane()
	{
		Map components = new TreeMap();

		UIDefaults defaults = UIManager.getDefaults();

		// Build of Map of attributes for each component
		for (Enumeration enum1 = defaults.keys(); enum1.hasMoreElements();)
		{
			Object key = enum1.nextElement();
			Object value = defaults.get(key);

			Map componentMap = getComponentMap(components, key.toString());

			if (componentMap != null)
			{
				componentMap.put(key, value);
			}
		}

		JTabbedPane pane = new JTabbedPane(SwingConstants.BOTTOM);
		pane.setPreferredSize(new Dimension(800, 400));
		addComponentTabs(pane, components);

		return pane;
	}

	private Map getComponentMap(Map components, String key)
	{
		if (key.startsWith("class") | key.startsWith("javax"))
		{
			return null;
		}

		// Component name is found before the first "."
		String componentName;

		int pos = key.indexOf(".");

		if (pos == -1)
		{
			if (key.endsWith("UI"))
			{
				componentName = key.substring(0, key.length() - 2);
			} else
			{
				componentName = "System Colors";
			}
		} else
		{
			componentName = key.substring(0, pos);
		}

		// Get the Map for this particular component
		Object componentMap = components.get(componentName);

		if (componentMap == null)
		{
			componentMap = new TreeMap();
			components.put(componentName, componentMap);
		}

		return (Map) componentMap;
	}

	private void addComponentTabs(JTabbedPane pane, Map components)
	{
		m_sampleRenderer = new SampleRenderer();

		String[] colName = { "Key", "Value", "Sample" };
		Set c = components.keySet();

		for (Iterator ci = c.iterator(); ci.hasNext();)
		{
			String component = (String) ci.next();
			Map attributes = (Map) components.get(component);

			Object[][] rowData = new Object[attributes.size()][3];
			int i = 0;

			Set a = attributes.keySet();

			for (Iterator ai = a.iterator(); ai.hasNext(); i++)
			{
				String attribute = (String) ai.next();
				rowData[i][0] = attribute;
				Object o = attributes.get(attribute);

				if (o != null)
				{
					rowData[i][1] = o.toString();
					rowData[i][2] = "";

					if (o instanceof Font)
					{
						rowData[i][2] = (Font) o;
					}

					if (o instanceof Color)
					{
						rowData[i][2] = (Color) o;
					}

					if (o instanceof IconUIResource)
					{
						rowData[i][2] = (Icon) o;
					}
				} else
				{
					rowData[i][1] = "";
					rowData[i][2] = "";
				}
			}

			MyTableModel myModel = new MyTableModel(rowData, colName);
			JTable table = new JTable(myModel);
			table.setDefaultRenderer(m_sampleRenderer.getClass(), m_sampleRenderer);
			table.getColumnModel().getColumn(0).setPreferredWidth(250);
			table.getColumnModel().getColumn(1).setPreferredWidth(500);
			table.getColumnModel().getColumn(2).setPreferredWidth(50);

			pane.addTab(component, new JScrollPane(table));
		}
	}

	public static void main(String[] args)
	{
		// Set the Look and Feel
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			;
		}
	}

	class MyTableModel extends AbstractTableModel
	{
		private String[] m_columnNames;

		private Object[][] m_rowData;

		public MyTableModel(Object[][] rowData, String[] columnNames)
		{
			this.m_rowData = rowData;
			this.m_columnNames = columnNames;
		}

		public int getColumnCount()
		{
			return m_columnNames.length;
		}

		public int getRowCount()
		{
			return m_rowData.length;
		}

		public String getColumnName(int col)
		{
			return m_columnNames[col];
		}

		public Object getValueAt(int row, int col)
		{
			return m_rowData[row][col];
		}

		public Class getColumnClass(int c)
		{
			Object o;

			if (c == 2)
			{
				o = m_sampleRenderer;
			} else
			{
				o = getValueAt(0, c);
			}

			return o.getClass();
		}

		public void setValueAt(Object value, int row, int col)
		{
			m_rowData[row][col] = value;
			fireTableCellUpdated(row, col);
		}
	}

	class SampleRenderer extends JLabel implements TableCellRenderer
	{
		public SampleRenderer()
		{
			super();
			setHorizontalAlignment(SwingConstants.CENTER);
			setOpaque(true); // MUST do this for background to show up.
		}

		public Component getTableCellRendererComponent(JTable table, Object sample, boolean isSelected,
		        boolean hasFocus, int row, int column)
		{
			setBackground(null);
			setIcon(null);
			setText("");

			if (sample instanceof Color)
			{
				setBackground((Color) sample);
			}

			if (sample instanceof Font)
			{
				setText("Sample");
				setFont((Font) sample);
			}

			if (sample instanceof Icon)
			{
				setIcon((Icon) sample);
			}

			return this;
		}
	}
}