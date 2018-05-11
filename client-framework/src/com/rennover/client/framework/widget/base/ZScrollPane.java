package com.rennover.client.framework.widget.base;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

/**
 * Zone avec des ascenseurs (scroll bars) à utiliser avec des tables, lists,
 * textArea, etc.
 * 
 * <p>
 * L'utilisation typique est la suivante:
 * 
 * <pre>
 * ZTable tblTypesDocumentsARouter = new ZTable();
 * ZScrollPane scTypesDocumentsARouter = new ZScrollPane(tblTypesDocumentsARouter);
 * panel.add(scTypesDocumentsARouter, BorderLayout.CENTER);
 * </pre>
 * 
 * ou plus simplement:
 * 
 * <pre>
 * ZTable tblTypesDocumentsARouter = new ZTable();
 * panel.add(new ZScrollPane(scTypesDocumentsARouter), BorderLayout.CENTER);
 * </pre>
 * 
 * ou encore:
 * 
 * <pre>
 * ZTable tblTypesDocumentsARouter = new ZTable();
 * panel.add(new ZScrollPane(scTypesDocumentsARouter, 5), BorderLayout.CENTER);
 * </pre>
 * 
 * </p>
 */
public class ZScrollPane extends JScrollPane
{
	public ZScrollPane()
	{
	}

	/**
	 * 
	 * @param displayedComponent
	 *            composant à afficher dans le viewport du scrollpane
	 */
	public ZScrollPane(Component displayedComponent)
	{
		super(displayedComponent);
	}

	/**
	 * 
	 * @param table
	 *            table à afficher dans le viewport du scrollpane (5 lignes par
	 *            défaut)
	 */
	public ZScrollPane(ZTable table)
	{
		this(table, 5);
	}

	/**
	 * 
	 * @param list
	 *            liste à afficher dans le viewport du scrollpane (5 lignes par
	 *            défaut)
	 */
	public ZScrollPane(ZPanel panel)
	{
		this(panel, true);
	}

	/**
	 * 
	 * @param list
	 *            liste à afficher dans le viewport du scrollpane (5 lignes par
	 *            défaut)
	 */
	public ZScrollPane(ZPanel panel, boolean border)
	{
		super(panel);
		if (!border)
		{
			setBorder(new EmptyBorder(0, 0, 0, 0));
		}

	}

	/**
	 * 
	 * @param list
	 *            liste à afficher dans le viewport du scrollpane (5 lignes par
	 *            défaut)
	 */
	public ZScrollPane(ZList list)
	{
		this(list, 5);
	}

	/**
	 * 
	 * @param textArea
	 *            zone de texte à afficher dans le viewport du scrollpane (5
	 *            lignes par défaut)
	 */
	public ZScrollPane(ZTextArea textArea)
	{
		this(textArea, 5);
	}

	/**
	 * 
	 * @param table
	 *            table à afficher dans le viewport du scrollpane
	 * @param prefNbRows
	 *            nombre de lignes à afficher
	 */
	public ZScrollPane(ZTable table, int prefNbRows)
	{
		super(table);
		setViewportView(table, prefNbRows);
	}

	/**
	 * 
	 * @param list
	 *            liste à afficher dans le viewport du scrollpane
	 * @param prefNbRows
	 *            nombre de lignes à afficher
	 */
	public ZScrollPane(ZList list, int prefNbRows)
	{
		super(list);
		setViewportView(list, prefNbRows);
	}

	/**
	 * 
	 * @param textArea
	 *            à afficher dans le viewport du scrollpane
	 * @param prefNbRows
	 *            nombre de lignes à afficher
	 */
	public ZScrollPane(ZTextArea textArea, int prefNbRows)
	{
		super();
		setViewportView(textArea, prefNbRows);
	}

	public void setViewportView(ZTextArea textArea, int prefNbRows)
	{
		super.setViewportView(textArea);
		textArea.setRows(prefNbRows);
		Dimension size = textArea.getMinimumSize();
		size = addInsets(size, getInsets());
		setMinimumSize(size);
	}

	public void setViewportView(ZList list, int prefNbRows)
	{
		super.setViewportView(list);
		list.setVisibleRowCount(prefNbRows);
		Dimension size = list.getMinimumSize();
		size = addInsets(size, getInsets());
		setMinimumSize(size);
	}

	public void setViewportView(ZTable table, int prefNbRows)
	{
		super.setViewportView(table);
		table.setVisibleRowCount(prefNbRows);
		Dimension headerSize = table.getTableHeader().getMinimumSize();
		Dimension size = table.getPreferredSize();
		size.height = table.getRowHeight() * prefNbRows;
		size.width += headerSize.width;
		size.height += headerSize.height;
		size = addInsets(size, getInsets());

		setPreferredSize(size);
	}

	public Dimension getMinimumSize()
	{
		Dimension size = super.getMinimumSize();
		size.height = getPreferredSize().height;
		return size;
	}

	// removed the commented method getPreferredSize()

	/**
	 * Calculer une nouvelle dimension en y ajoutant des insets.
	 * 
	 * @param size
	 *            dimension de départ
	 * @param insets
	 *            contour à ajouter
	 * @return nouvelle dimension augmentée
	 */
	protected Dimension addInsets(Dimension size, Insets insets)
	{
		Dimension newSize = new Dimension(size);
		newSize.width += insets.left + insets.right;
		newSize.height += insets.top + insets.bottom;
		return newSize;
	}

	public void setEnabled(boolean enabled)
	{
		; // un scrollpane n'est pas désactivable
	}

	public void super_setEnabled(boolean b)
	{
		super.setEnabled(b);
	}

	protected void setSizes(ZTextArea textArea, int minRows, int prefRows, int maxRows)
	{
		textArea.setRows(prefRows);
		setMinimumSize(computeSize(textArea, minRows));
		setMaximumSize(computeSize(textArea, maxRows));
	}

	protected Dimension computeSize(ZTextArea textArea, int nbRows)
	{
		Insets insets = getInsets();
		int itemWidth = 100;
		int itemHeight = 18;

		int width = itemWidth + insets.left + insets.right;
		int height = itemHeight * nbRows + insets.top + insets.bottom;

		return new Dimension(width, height);
	}

	protected void setSizes(ZList list, int minRows, int prefRows, int maxRows)
	{
		list.setVisibleRowCount(prefRows);
	}
}