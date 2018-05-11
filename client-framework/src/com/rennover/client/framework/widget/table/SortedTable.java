package com.rennover.client.framework.widget.table;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.rennover.client.framework.widget.field.DateMarker;

/**
 * @author tcaboste
 * 
 * Table capable de gérer les tris sur les colonnes
 * 
 */
public class SortedTable extends DefaultTable
{
	private boolean m_sortable = true;

	private List m_indexesTableToModel;

	private int m_sortedColumn = -1;

	private boolean m_ascendingSort = true;

	private ButtonHeaderRenderer m_buttonHeaderRenderer;

	private int m_pushedColumn = -1;

	public SortedTable()
	{
		super();
	}

	public SortedTable(Object[][] rowData, Object[] columns)
	{
		super(rowData, columns);
		TableModelEvent event = new TableModelEvent(getModel(), TableModelEvent.HEADER_ROW);
		tableChanged(event);
	}

	/**
	 * méthode de tri pour les types les plus courants
	 * 
	 * @param col
	 *            index de la colonne de tri
	 * @param r1
	 *            index de la ligne 1
	 * @param r2
	 *            index de la ligne 2
	 * @return
	 */
	private int compareRow(int col, int r1, int r2)
	{
		TableModel model = getModel();
		Class columnClass = model.getColumnClass(col);
		Object o1 = model.getValueAt(r1, col);
		Object o2 = model.getValueAt(r2, col);

		if (columnClass == null)
		{
			return compareToString(o1, o2);
		} else if (Date.class.isAssignableFrom(columnClass))
		{
			return compareDate((Date) o1, (Date) o2);
		} else if (Number.class.isAssignableFrom(columnClass))
		{
			return compareNumber((Number) o1, (Number) o2);
		}
		// added for 7925 -ashisht
		else if (DateMarker.class.isAssignableFrom(columnClass))
		{
			return compareToDateMarker(o1, o2);
		}
		// end of #7925
		else
		{
			return compareToString(o1, o2);
		}
	}

	/**
	 * #7925
	 * 
	 * @ashish.thomas compares two dates which implement the DateMarker.class
	 *                interface only for the format :- "dd/MM/yyyy HH:mm". and
	 *                #8623
	 * @boopathi.siva the format "dd/MM/yyyy".
	 * @param v1
	 * @param v2
	 * @return
	 */
	public int compareToDateMarker(Object v1, Object v2)
	{
		if (v1 != null && v2 != null)
		{
			String str1 = v1.toString();
			String str2 = v2.toString();

			int day1 = Integer.parseInt(str1.substring(0, 2));
			int month1 = Integer.parseInt(str1.substring(3, 5));
			int year1 = Integer.parseInt(str1.substring(6, 10));

			// changed for defect 8623 start
			// To sort "dd/MM/yyyy" formate.
			int hour1 = 0;
			int minute1 = 0;
			if (str1.toCharArray().length > 10)
			{
				hour1 = Integer.parseInt(str1.substring(11, 13));
				minute1 = Integer.parseInt(str1.substring(14, 16));
			}
			// changed for defect 8623 end

			int day2 = Integer.parseInt(str2.substring(0, 2));
			int month2 = Integer.parseInt(str2.substring(3, 5));
			int year2 = Integer.parseInt(str2.substring(6, 10));

			// changed for defect 8623 start
			// To sort "dd/MM/yyyy" formate.
			int hour2 = 0;
			int minute2 = 0;
			if (str2.toCharArray().length > 10)
			{
				hour2 = Integer.parseInt(str2.substring(11, 13));
				minute2 = Integer.parseInt(str2.substring(14, 16));
			}
			// changed for defect 8623 end

			if (year1 > year2)
			{
				return 1;
			}
			if (year1 < year2)
			{
				return -1;
			}

			if (year1 == year2 && month1 > month2)
			{
				return 1;
			}
			if (year1 == year2 && month1 < month2)
			{
				return -1;
			}

			if (year1 == year2 && month1 == month2 && day1 > day2)
			{
				return 1;
			}
			if (year1 == year2 && month1 == month2 && day1 < day2)
			{
				return -1;
			}

			if (year1 == year2 && month1 == month2 && day1 == day2 && hour1 > hour2)
			{
				return 1;
			}
			if (year1 == year2 && month1 == month2 && day1 == day2 && hour1 < hour2)
			{
				return -1;
			}

			if (year1 == year2 && month1 == month2 && day1 == day2 && hour1 == hour2 && minute1 > minute2)
			{
				return 1;
			}
			if (year1 == year2 && month1 == month2 && day1 == day2 && hour1 == hour2 && minute1 < minute2)
			{
				return -1;
			}
			return 0;
		} else if (v1 == null)
		{
			return 1;
		} else
		{
			return -1;
		}
	}

	/**
	 * Comparateur de Dates
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	private int compareDate(Date v1, Date v2)
	{
		if (v1 != null && v2 != null)
		{
			return v1.compareTo(v2);
		} else if (v1 == null)
		{
			return 1;
		} else
		{
			return -1;
		}
	}

	/**
	 * Comparateur de Nombres
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	private int compareNumber(Number v1, Number v2)
	{
		if (v1 != null && v2 != null)
		{
			return Double.compare(v1.doubleValue(), v2.doubleValue());
		} else if (v1 == null)
		{
			return 1;
		} else
		{
			return -1;
		}
	}

	/**
	 * Comparateur de Chaînes
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	private int compareToString(Object v1, Object v2)
	{
		if (v1 != null && v2 != null)
		{
			return v1.toString().compareTo(v2.toString());
		} else if (v1 == null)
		{
			return 1;
		} else
		{
			return -1;
		}
	}

	/**
	 * Tri avec le dernier paramétrage de tri
	 * 
	 */
	public void sort()
	{
		sort(m_sortedColumn, m_ascendingSort);
		// repaint();
	}

	public List getContent()
	{
		List list = new ArrayList();

		// getHeader
		List header = new ArrayList();
		int colCount = getColumnCount();
		for (int col = 0; col < colCount; col++)
		{
			String columnName = getColumnName(col);
			header.add(columnName);
		}
		list.add(header);

		// getRow
		int rowCount = getRowCount();
		for (int row = 0; row < rowCount; row++)
		{
			List rowLine = new ArrayList();
			for (int col = 0; col < colCount; col++)
			{
				Object value = getValueAt(row, col);
				rowLine.add(value);
			}
			list.add(rowLine);
		}

		return list;
	}

	/**
	 * Tri en fonction d'une colonne
	 * 
	 * @param modelColumnIndex
	 *            index de la colonne selon le modèle (modelColumnIndex commence
	 *            à zéro) -1 supprime le tri.
	 * @param ascendingSort
	 *            indique si le tri est ascendant (ordre croissant)
	 */
	public void sort(int modelColumnIndex, boolean ascendingSort)
	{
		// Sauvegarde du tri pour sort()
		m_sortedColumn = modelColumnIndex;
		m_ascendingSort = ascendingSort;

		// pas de tri si la colonne n'est pas valide
		if (modelColumnIndex == -1)
		{
			return;
		}

		TableModel model = getModel();
		int rowCount = model.getRowCount();
		int selectedRow = getModelSelectedRow();

		// Création de la table d'indexes si elle n'existe pas
		if (m_indexesTableToModel == null || m_indexesTableToModel.size() != rowCount)
		{
			m_indexesTableToModel = new ArrayList(rowCount);
			for (int i = 0; i < rowCount; i++)
			{
				m_indexesTableToModel.add(i, new Integer(i));
			}
		}

		// Tri selon le sens souhaité
		if (ascendingSort)
		{
			Collections.sort(m_indexesTableToModel, new Comparator()
			{
				public int compare(Object o1, Object o2)
				{
					int r1 = ((Integer) o1).intValue();
					int r2 = ((Integer) o2).intValue();

					return compareRow(m_sortedColumn, r1, r2);
				}
			});
		} else
		{
			Collections.sort(m_indexesTableToModel, new Comparator()
			{
				public int compare(Object o1, Object o2)
				{
					int r1 = ((Integer) o1).intValue();
					int r2 = ((Integer) o2).intValue();

					return -compareRow(m_sortedColumn, r1, r2);
				}
			});
		}

		// Sélection de la ligne ancienne sélectionnée
		setModelSelectedRow(selectedRow);
	}

	/**
	 * Convertit un index de ligne visuelle en index modèle
	 * 
	 * @param row
	 * @return
	 */
	public int getRowIndex(int row)
	{
		if (row == -1)
		{
			return -1;
		}
		return (m_indexesTableToModel != null) ? ((Integer) m_indexesTableToModel.get(row)).intValue() : row;
	}

	public int convertRowIndexToModel(int row)
	{
		if (row == -1)
		{
			return -1;
		}
		return (m_indexesTableToModel != null) ? ((Integer) m_indexesTableToModel.get(row)).intValue() : row;
	}

	// removed the commented method convertRowIndexToView(int row)

	/**
	 * Retourne la ligne sélectionnée avec un index modèle (et non vue)
	 */
	public int getModelSelectedRow()
	{
		return getRowIndex(getSelectedRow());
	}

	/**
	 * Retourne les indexes des éléments sélectionnés par rapport à un index
	 * modèle (et non vue)
	 */
	public int[] getModelSelectedRows()
	{
		int[] selectedValueIndexes = getSelectedRows();
		int[] modelSelectedValueIndexes = new int[selectedValueIndexes.length];

		for (int i = 0; i < selectedValueIndexes.length; i++)
		{
			modelSelectedValueIndexes[i] = getRowIndex(selectedValueIndexes[i]);
		}
		return modelSelectedValueIndexes;
	}

	/**
	 * Permet de selectionner une ligne par rapport à un index modèle (et non
	 * vue)
	 */
	public void setModelSelectedRow(int selectedModelRow)
	{
		if (selectedModelRow == -1)
		{
			clearSelection();
		} else
		{
			int selectedRow = selectedModelRow;
			if (m_indexesTableToModel != null)
			{
				for (int i = 0; i < m_indexesTableToModel.size(); i++)
				{
					if (((Integer) m_indexesTableToModel.get(i)).intValue() == selectedModelRow)
					{
						selectedRow = i;
						break;
					}
				}
			}
			setRowSelectionInterval(selectedRow, selectedRow);
		}
	}

	/**
	 * Surcharge permet de réaiguiller le passage des valeurs contenues dans les
	 * lignes (par rapport à la vue)
	 */
	public Object getValueAt(int row, int column)
	{
		return super.getValueAt(getRowIndex(row), column);
	}

	/**
	 * Surcharge permet de réaiguiller le passage des valeurs contenues dans les
	 * lignes (par rapport à la vue)
	 */
	public void setValueAt(Object aValue, int row, int column)
	{
		super.setValueAt(aValue, getRowIndex(row), column);
	}

	public boolean isCellEditable(int row, int column)
	{
		return super.isCellEditable(getRowIndex(row), column);
	}

	/**
	 * Appelé par le parent lors de changements sur le modèle. Cette méthode
	 * positionne alors les valeurs pour les headers, le renderer ainsi que le
	 * listener d'évènements souris.
	 */
	public void tableChanged(TableModelEvent e)
	{
		super.tableChanged(e);

		m_indexesTableToModel = null;
		if (e.getFirstRow() == TableModelEvent.HEADER_ROW && e.getLastRow() == TableModelEvent.HEADER_ROW)
		{
			if (getTableHeader() != null)
			{
				initHeader();
			}
		}

		if (isSortable())
		{
			sort();
		}
	}

	/**
	 * Initialise les entêtes de colonnes de la table
	 * 
	 */
	protected void initHeader()
	{
		if (isSortable())
		{

			JTableHeader header = this.getTableHeader();
			if (header != null && m_buttonHeaderRenderer == null)
			{
				m_buttonHeaderRenderer = new ButtonHeaderRenderer();
				header.addMouseListener(new HeaderListener(header, m_buttonHeaderRenderer));
			}

			for (int columnIndex = 0; columnIndex < getColumnModel().getColumnCount(); columnIndex++)
			{
				TableColumn tableColumn = getColumnModel().getColumn(columnIndex);
				tableColumn.setHeaderRenderer(m_buttonHeaderRenderer);
				Component c = m_buttonHeaderRenderer.getTableCellRendererComponent(null, tableColumn.getHeaderValue(),
				        false, false, 0, 0);
				tableColumn.setPreferredWidth(c.getPreferredSize().width);
			}
		}
	}

	public boolean isSortable()
	{
		return m_sortable;
	}

	/**
	 * Permet d'activer le tri par les colonnes
	 * 
	 * @param sortable
	 *            true si tri actif
	 */
	public void setSortable(boolean sortable)
	{
		m_sortable = sortable;
	}

	/**
	 * Ce renderer indique visuellement si une colonne est triée, ainsi que
	 * l'ordre de tri.
	 * 
	 * <p>
	 * La stratégie est de maintenir un pointeur vers le header enfoncé, un
	 * pointeur vers le header trié et une collection de headers. Avec tout ça,
	 * lorsque le renderer est appelé pour une colonne particulière, on vérifie
	 * s'il s'agit d'une colonne enfoncée ou triée avant de mettre à jour le
	 * composant graphique d'affichage du header.
	 * </p>
	 */
	private class ButtonHeaderRenderer implements TableCellRenderer
	{
		private JLabel m_label = new JLabel();

		private Icon m_upArrowIcon = new UpArrowIcon();

		private Icon m_downArrowIcon = new DownArrowIcon();

		public ButtonHeaderRenderer()
		{
			m_label.setHorizontalTextPosition(JLabel.RIGHT);
			m_label.setHorizontalAlignment(JLabel.CENTER);
			m_label.setOpaque(true);
			m_label.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		}

		/**
		 * utilisation interne Retourne le composant représentant une entête
		 * selon son état enfoncé ou en position de tri
		 */
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
		        boolean hasFocus, int row, int column)
		{
			int modelColumnIndex = convertColumnIndexToModel(column);

			// text de l'entête
			String headerText = (value != null) ? value.toString() : "";
			String headerTooltip = null;

			Object headerValue = (table == null) ? null : table.getColumnModel().getColumn(modelColumnIndex)
			        .getHeaderValue();
			if (headerValue instanceof ColumnHeader)
			{
				ColumnHeader columnHeader = (ColumnHeader) headerValue;
				headerTooltip = columnHeader.m_toolTip;
			}
			if (headerTooltip == null)
			{
				headerTooltip = headerText;
			}

			// icon de tri
			Icon icon = null;
			if (value != null && modelColumnIndex == m_sortedColumn)
			{
				icon = m_ascendingSort ? m_downArrowIcon : m_upArrowIcon;
			}

			// bouton enfoncé
			boolean isPressed = false;
			if (value != null && modelColumnIndex == m_pushedColumn)
			{
				isPressed = true;
			}

			m_label.setIcon(icon);
			m_label.setText(headerText);
			m_label.setToolTipText(headerTooltip);
			m_label.setBackground(UIManager.getColor(isPressed ? "TableHeader.shadow" : "TableHeader.background"));

			return m_label;
		}
	}

	/**
	 * Ecoute les évènements souris sur la zone de headers de la table, puis
	 * indique le numéro de la colonne affectée au renderer.
	 */
	private class HeaderListener extends MouseAdapter
	{
		JTableHeader m_header;

		ButtonHeaderRenderer m_renderer;

		HeaderListener(JTableHeader header, ButtonHeaderRenderer renderer)
		{
			this.m_header = header;
			this.m_renderer = renderer;
		}

		public void mouseClicked(MouseEvent e)
		{
			if (e.getClickCount() == 1 || e.getClickCount() == 2)
			{
				int col = m_header.columnAtPoint(e.getPoint());
				setSortedColumn(col);
				m_header.repaint();
			}
		}

		public void mousePressed(MouseEvent e)
		{
			int col = m_header.columnAtPoint(e.getPoint());
			setPressedColumn(col);
			m_header.repaint();
		}

		public void mouseReleased(MouseEvent e)
		{
			setPressedColumn(-1); // aucune colonne pressée
			m_header.repaint();
		}

		/**
		 * Positionne la colonne pressée par l'utilisateur.
		 */
		public void setPressedColumn(int col)
		{
			if (col != -1)
			{
				m_pushedColumn = convertColumnIndexToModel(col);
			} else
			{
				// -1 indique qu'aucune colonne n'est selectionnée
				m_pushedColumn = -1;
			}
		}

		/**
		 * Positionne la colonne servant au tri. Si elle est positionnée deux
		 * fois l'ordre s'inverse
		 */
		public void setSortedColumn(int col)
		{
			if (isSortable())
			{
				int modelColumnIndex = convertColumnIndexToModel(col);

				if (modelColumnIndex == m_sortedColumn)
				{
					m_ascendingSort = !m_ascendingSort;
				} else
				{
					m_ascendingSort = true;
					m_sortedColumn = modelColumnIndex;
				}

				sort(modelColumnIndex, m_ascendingSort);
				repaint();
			}
		}
	}

	/**
	 * @author tcaboste
	 * 
	 * Icône flèche vers la bas
	 */
	class DownArrowIcon implements Icon, Serializable
	{
		/**
		 * Paints the horizontal bars for the
		 */
		public void paintIcon(Component c, Graphics g, int x, int y)
		{
			JComponent component = (JComponent) c;
			int iconWidth = getIconWidth();

			g.translate(x, y);

			g.setColor(component.isEnabled() ? MetalLookAndFeel.getControlInfo() : MetalLookAndFeel.getControlShadow());
			g.drawLine(0, 0, iconWidth - 1, 0);
			g.drawLine(1, 1, 1 + (iconWidth - 3), 1);
			g.drawLine(2, 2, 2 + (iconWidth - 5), 2);
			g.drawLine(3, 3, 3 + (iconWidth - 7), 3);
			g.drawLine(4, 4, 4 + (iconWidth - 9), 4);

			g.translate(-x, -y);
		}

		/**
		 * Created a stub to satisfy the interface.
		 */
		public int getIconWidth()
		{
			return 10;
		}

		/**
		 * Created a stub to satisfy the interface.
		 */
		public int getIconHeight()
		{
			return 5;
		}
	}

	/**
	 * @author tcaboste
	 * 
	 * Icône flèche vers le haut
	 */
	class UpArrowIcon implements Icon, Serializable
	{
		/**
		 * Paints the horizontal bars for the
		 */
		public void paintIcon(Component c, Graphics g, int x, int y)
		{
			JComponent component = (JComponent) c;
			int iconWidth = getIconWidth();

			g.translate(x, y);

			g.setColor(component.isEnabled() ? MetalLookAndFeel.getControlInfo() : MetalLookAndFeel.getControlShadow());
			g.drawLine(0, 4, iconWidth - 1, 4);
			g.drawLine(1, 3, 1 + (iconWidth - 3), 3);
			g.drawLine(2, 2, 2 + (iconWidth - 5), 2);
			g.drawLine(3, 1, 3 + (iconWidth - 7), 1);
			g.drawLine(4, 0, 4 + (iconWidth - 9), 0);

			g.translate(-x, -y);
		}

		/**
		 * Created a stub to satisfy the interface.
		 */
		public int getIconWidth()
		{
			return 10;
		}

		/**
		 * Created a stub to satisfy the interface.
		 */
		public int getIconHeight()
		{
			return 5;
		}
	}
}