
package com.rennover.client.framework.print.itext;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter;

public class Test
{
	public static void main(String[] args)
	{
		// step 1: creation of a document-object
		Document document = new Document();
		try
		{
			// step 2:
			// we create a writer that listens to the document
			// and directs a PDF-stream to a file
			long time = System.currentTimeMillis();

			RtfWriter.getInstance(document, new FileOutputStream("c:\\doc" + time + ".rtf"));

			// step 3: we open the document
			document.open();
			Paragraph p1 = new Paragraph(new Chunk("Impression de la liste des prospect/adhérent", FontFactory.getFont(
			        FontFactory.HELVETICA, 16)));
			p1.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(p1);
			document.add(new Paragraph(""));

			Image img = Image
			        .getInstance("D:\\cc_views\\smalbequi_default_dev\\nsi\\04_Implementation\\client-lib\\classes\\images\\agefos_logo.jpg");
			img.setAlignment(Image.RIGHT);

			Table aTable = new Table(2, 1); // 2 rows, 2 columns
			aTable.setBorder(0);
			Cell cell = new Cell("");
			cell.setBorder(0);
			aTable.addCell(cell);
			cell = new Cell("");
			cell.setBorder(0);
			cell.add(img);
			aTable.addCell(cell);

			document.add(aTable);

			document.add(new Paragraph(""));

			// step 4: we create a table and add it to the document
			aTable = new Table(2, 2); // 2 rows, 2 columns
			cell = new Cell("toto");
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(new Color(200, 200, 200));
			aTable.addCell(cell);
			aTable.addCell("0.1");
			aTable.addCell("1.0");
			aTable.addCell("1.1");
			document.add(aTable);

			document.close();
			Runtime.getRuntime().exec("cmd /c start " + "c:\\doc" + time + ".rtf");
		} catch (DocumentException de)
		{
			System.err.println(de.getMessage());
		} catch (IOException ioe)
		{
			System.err.println(ioe.getMessage());
		}
	}
}