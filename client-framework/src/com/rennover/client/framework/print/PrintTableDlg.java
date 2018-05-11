/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */
package com.rennover.client.framework.print;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.rennover.client.framework.mvc.ActionDescription;
import com.rennover.client.framework.mvc.PanelViewController;
import com.rennover.client.framework.print.itext.TableModelPrinter;
import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.widget.base.ZTable;
import com.rennover.client.framework.widget.panel.ButtonPanel;
import com.rennover.client.framework.widget.panel.TablePanel;
import com.rennover.client.framework.widget.table.PropertyTableRowModel;
import com.rennover.client.framework.widget.table.ValueObjectListTable;
import com.rennover.client.framework.window.WindowManager;

public class PrintTableDlg extends PanelViewController
{
	private static final ActionDescription ACTION_EXPORTER_CVS = new ActionDescription("Exporter CSV", "doExporterCsv");

	private static final ActionDescription ACTION_PRINT = new ActionDescription("Print", "doImprimer");

	private static final ActionDescription ACTION_CLOSE = new ActionDescription("Close", "doFermer");

	private ZTable m_table;

	private String m_title;

	private ValueObjectListTable m_columnTable;

	public PrintTableDlg(ZTable table, String title)
	{
		super("Imprimer / Exporter");
		m_table = table;
		m_title = title;
		init();
	}

	public void doExporterPdf()
	{
		TableModelPrinter.print(m_title, m_table, buildColumnsToPrint(), TableModelPrinter.PDF, false);
	}

	public void doExporterHtml()
	{
		TableModelPrinter.print(m_title, m_table, buildColumnsToPrint(), TableModelPrinter.HTML, false);
	}

	public void doExporterRtf()
	{
		TableModelPrinter.print(m_title, m_table, buildColumnsToPrint(), TableModelPrinter.RTF, false);
	}

	public void doExporterCsv()
	{

		// added for defect 8356 start
		int exportedColumns[] = buildColumnsToPrint();
		if (exportedColumns == null)
		{
			return;
		}
		// added for defec 8356 end

		JFileChooser fc = new JFileChooser();

		fc.addChoosableFileFilter(new FileFilter()
		{
			public boolean accept(File f)
			{
				return f.getName().endsWith(".csv") || f.isDirectory();
			}

			public String getDescription()
			{
				return "CSV (séparateur : point virgule)";
			}
		});

		int returnVal = fc.showSaveDialog(this);
		File file;
		if (returnVal != JFileChooser.APPROVE_OPTION)
		{
			return;
		} else
		{
			File file1 = fc.getSelectedFile();

			String fileName = file1.getName().trim();
			fileName = fileName.replaceAll("\"", "");

			if (false == fileName.endsWith(".csv"))
			{
				fileName = fileName.concat(".csv");
			}
			file = new File(file1.getParentFile(), fileName);
			fc.setSelectedFile(file1);
		}

		if (file.exists())
		{
			boolean replace = WindowManager.showConfirmationMessage("Le fichier '" + file.getName()
			        + "' existe déjà voulez-vous le remplacer ?", fc);
			if (!replace)
			{
				return;
			}

			try
			{
				file.delete();
				file.createNewFile();
			} catch (IOException exception)
			{
				WindowManager.showExceptionMessage(exception, null);
				return;
			}
		}

		// insted of "buildColumnsToPrint()" menthod relased in to
		// exportedColumns for defect 8356
		TableModelPrinter.exportCsv(m_table, exportedColumns, file);
	}

	public void doImprimer()
	{
		TableModelPrinter.print(m_title, m_table, buildColumnsToPrint(), TableModelPrinter.HTML, true);
	}

	public void doFermer()
	{
		close();
	}

	protected void instanciate()
	{
	}

	protected void compose()
	{
		setLayout(new BorderLayout());
		List columnlist = Arrays.asList(new String[] { "Colonne", "Imprimer" });
		List propertylist = Arrays.asList(new String[] { ColonneAImprimer.NOM, ColonneAImprimer.AIMPRIMER });

		TablePanel pnlListe = new TablePanel("Les colonnes à imprimer", true, false);

		int n = m_table.getColumnCount();
		ValueObjectListModel listModel = new ValueObjectListModel(ColonneAImprimer.class);
		for (int i = 0; i < n; i++)
		{
			String colonne = m_table.getColumnName(i);
			listModel.addValueObject(new ColonneAImprimer(colonne));
		}

		PropertyTableRowModel propertyTableRowModel = new PropertyTableRowModel(ColonneAImprimer.class, propertylist,
		        columnlist)
		{
			protected boolean isCellEditable(int columnIndex, Object rowObject)
			{
				return columnIndex == 1;
			}
		};

		m_columnTable = new ValueObjectListTable(listModel, propertyTableRowModel);

		pnlListe.setTable(m_columnTable);

		add(pnlListe, BorderLayout.CENTER);

		ButtonPanel pnlButton = new ButtonPanel(true);

		pnlButton.addButtonAction(getAction(ACTION_EXPORTER_CVS));

		pnlButton.addButtonAction(getAction(ACTION_PRINT));
		pnlButton.addButtonAction(getAction(ACTION_CLOSE));
		add(pnlButton, BorderLayout.SOUTH);
	}

	private int[] buildColumnsToPrint()
	{
		List list = new ArrayList(m_table.getRowCount());
		int n = m_columnTable.getModel().getRowCount();
		for (int i = 0; i < n; i++)
		{
			Boolean toPrint = (Boolean) m_columnTable.getModel().getValueAt(i, 1);
			if (toPrint.booleanValue())
			{
				list.add(new Integer(i));
			}
		}
		if (list.size() == 0)
		{
			return null;
		}
		int i = 0;
		int[] columns = new int[list.size()];
		for (Iterator iter = list.iterator(); iter.hasNext();)
		{
			Integer index = (Integer) iter.next();
			columns[i++] = index.intValue();
		}
		return columns;
	}
}
