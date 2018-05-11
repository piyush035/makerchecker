/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */
package com.rennover.client.framework.print;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JPanel;

import com.rennover.hotel.common.log.Logger;

/**
 * @author rquere
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class PrintablePanel extends JPanel implements Printable
{
	public void doImprimerDetail()
	{
		PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(this);
		if (printJob.printDialog())
		{
			try
			{
				printJob.print();
			} catch (PrinterException pe)
			{
				Logger.debug(this, pe);
			}
		}
	}

	public int print(Graphics g, PageFormat pageFormat, int page)
	{
		Graphics2D g2;
		double pageWidth = pageFormat.getImageableWidth();
		double scale = 1;
		if (getWidth() >= pageWidth)
		{
			scale = pageWidth / getWidth();
		}
		if (page == 0)
		{
			g2 = (Graphics2D) g;
			g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			g2.scale(scale, scale);
			this.paint(g2);
			return PAGE_EXISTS;
		} else
		{
			return NO_SUCH_PAGE;
		}
	}
}