/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */
package com.rennover.client.framework.print;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import com.rennover.client.framework.widget.base.ZTable;
import com.rennover.client.framework.window.WindowManager;
import com.rennover.hotel.common.log.Logger;

/**
 * @author vMiramond
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class PrintToolbox
{
	/**
	 * Cette méthode permet de lancer l'ipression d'une widget donnée
	 * 
	 * @param component
	 *            le composnant à imprimer. Ce composant doit implémenter
	 *            l'interface Printable.
	 * @todo Cette méthode aura besion d'un refactoring.
	 */
	public static void printPrintableWidget(Printable component)
	{
		Window activeWindow = WindowManager.getActiveWindow();
		WindowManager.freeze(activeWindow);
		try
		{
			DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
			PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

			PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor, pras);
			PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
			if (defaultService != null)
			{
				PrintService service = ServiceUI
				        .printDialog(null, 200, 200, printService, defaultService, flavor, pras);

				if (service != null)
				{
					DocPrintJob job = service.createPrintJob();

					DocAttributeSet das = new HashDocAttributeSet();
					Doc doc = new SimpleDoc(component, flavor, das);
					try
					{
						job.print(doc, pras);
					} catch (PrintException pe)
					{
						pe.printStackTrace();
					}
				}
			} else
			{
				WindowManager
				        .showErrorMessage(
				                "Aucun service d'impression n'est disponible.\nVérifier qu'au moins une imprimante a été configurée dans le système d'exploitation.",
				                activeWindow);
			}
		} finally
		{
			WindowManager.unfreeze(activeWindow);
		}
	}

	public static void doPrintTable(String title, ZTable table, Component parent)
	{
		PrintTableDlg printDlg = new PrintTableDlg(table, title);
		WindowManager.showAsDialog(printDlg, parent);
	}

	public static void print(Component component)
	{
		printPrintableWidget(new ComponentPrinter(component));
	}

	static class ComponentPrinter implements Printable
	{
		Component m_component = null;

		public ComponentPrinter(Component panel)
		{
			m_component = panel;
		}

		public int print(Graphics g, PageFormat pageFormat, int pageIndex)
		{
			Logger.debug(this, "PageFormat : " + pageFormat + " pageIndex : " + pageIndex);
			double pageWidth = pageFormat.getImageableWidth();
			double pageHeight = pageFormat.getImageableHeight();
			double scale1 = 1;
			double scale2 = 1;
			double bestScale = 0;

			if (m_component.getWidth() >= pageWidth)
			{
				scale1 = pageWidth / m_component.getWidth();
			}

			if (m_component.getHeight() >= pageHeight)
			{
				scale2 = pageHeight / m_component.getHeight();
			}

			if (scale1 <= scale2)
			{
				bestScale = scale1;
			}
			if (scale2 < scale1)
			{
				bestScale = scale2;
			}

			Logger.debug(this, "BestScale " + bestScale);

			Graphics2D g2;
			g2 = (Graphics2D) g;

			g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

			g2.scale(bestScale, bestScale);

			if (pageIndex == 0)
			{
				m_component.print(g2);

				return PAGE_EXISTS;
			} else
			{
				return NO_SUCH_PAGE;
			}
		}
	}
}
