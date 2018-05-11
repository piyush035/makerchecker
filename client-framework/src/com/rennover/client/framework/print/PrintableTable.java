/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */
package com.rennover.client.framework.print;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import com.rennover.client.framework.widget.base.ZTable;
import com.rennover.hotel.common.log.Logger;

public class PrintableTable extends ZTable implements Printable
{
	public void doImprimerListe()
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

	public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		int fontHeight = g2.getFontMetrics().getHeight();
		int fontDescent = g2.getFontMetrics().getDescent();

		// laisser de l'espace pour le numero de page
		double pageHeight = pageFormat.getImageableHeight() - fontHeight;
		double pageWidth = pageFormat.getImageableWidth();
		double tableWidth = (double) getColumnModel().getTotalColumnWidth();
		double scale = 1;

		if (tableWidth >= pageWidth)
		{
			scale = pageWidth / tableWidth;
		}

		double headerHeightOnPage = getTableHeader().getHeight() * scale;
		double tableWidthOnPage = tableWidth * scale;

		double oneRowHeight = (getRowHeight() + getRowMargin()) * scale;

		int numRowsOnAPage = (int) ((pageHeight - headerHeightOnPage) / oneRowHeight);

		double pageHeightForTable = oneRowHeight * numRowsOnAPage;

		int totalNumPages = (int) Math.ceil((double) getRowCount() / numRowsOnAPage);

		if (pageIndex >= totalNumPages)
		{
			return NO_SUCH_PAGE;
		}

		g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		// en bas au centre
		g2.drawString("Page: " + (pageIndex + 1), (int) pageWidth / 2 - 35,
		        (int) (pageHeight + fontHeight - fontDescent));

		g2.translate(0f, headerHeightOnPage);
		g2.translate(0f, -pageIndex * pageHeightForTable);

		// si cette partie de la page est plus petite
		// que la taille disponible, alors découper les contours appropriés
		if (pageIndex + 1 == totalNumPages)
		{
			int lastRowPrinted = numRowsOnAPage * pageIndex;
			int numRowsLeft = getRowCount() - lastRowPrinted;

			g2.setClip(0, (int) (pageHeightForTable * pageIndex), (int) Math.ceil(tableWidthOnPage), (int) Math
			        .ceil(oneRowHeight * numRowsLeft));
		}
		// sinon découper la zone disponible toute entière
		else
		{
			g2.setClip(0, (int) (pageHeightForTable * pageIndex), (int) Math.ceil(tableWidthOnPage), (int) Math
			        .ceil(pageHeightForTable));
		}

		g2.scale(scale, scale);
		this.paint(g2);

		// dessiner les entêtes en haut
		g2.scale(1 / scale, 1 / scale);
		g2.translate(0f, pageIndex * pageHeightForTable);
		g2.translate(0f, -headerHeightOnPage);
		g2.setClip(0, 0, (int) Math.ceil(tableWidthOnPage), (int) Math.ceil(headerHeightOnPage));
		g2.scale(scale, scale);
		getTableHeader().paint(g2);
		return Printable.PAGE_EXISTS;
	}
}