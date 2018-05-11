
package com.rennover.client.framework.print.itext;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter;
import com.rennover.client.framework.widget.base.ComparatorHelper;
import com.rennover.client.framework.widget.base.ZTable;
import com.rennover.hotel.common.exception.TechnicalException;

public class TableModelPrinter
{
	public static final short PDF = 0;

	public static final short RTF = 1;

	public static final short HTML = 2;

	private static File createFile(String suffix) throws IOException
	{
		return File.createTempFile("nsicm", suffix);
	}

	public static void exportCsv(ZTable table, int[] columnsToPrint, File file)
	{
		try
		{
			writeCsv(table, columnsToPrint, file);
		} catch (IOException e)
		{
			throw new TechnicalException(e);
		}
	}

	public static void print(String title, ZTable table, int[] columnsToPrint, short format, boolean direct)
	{
		if (columnsToPrint == null)
		{
			return;
		}
		try
		{
			File file = generate(title, table, columnsToPrint, format);
			Process process = null;

			if (format == PDF)
			{
				process = exec("cmd /c start " + file.getPath());
			}
			if (format == RTF)
			{
				process = exec("cmd /c start " + file.getPath());
			}
			if (format == HTML)
			{
				if (direct)
				{
					process = exec("rundll32.exe mshtml.dll,PrintHTML \"" + file.getPath() + "\"");
				} else
				{
					process = exec("cmd /c start " + file.getPath());
				}
			}

			if (process == null)
			{
				throw new TechnicalException("Impossible d'imprimer '" + file.getPath() + "'");
			}
		} catch (IOException e)
		{
			throw new TechnicalException(e);
		}
	}

	private static Process exec(String command) throws IOException
	{
		return Runtime.getRuntime().exec(command);
	}

	static File generate(String title, ZTable table, int[] columnsToPrint, short format) throws IOException
	{
		Document document = new Document();
		File file = null;
		try
		{
			if (format == PDF)
			{
				file = createFile(".pdf");
				PdfWriter.getInstance(document, new FileOutputStream(file));
			}
			if (format == RTF)
			{
				file = createFile(".rtf");
				RtfWriter.getInstance(document, new FileOutputStream(file));
			}
			if (format == HTML)
			{
				file = createFile(".html");
				HtmlWriter.getInstance(document, new FileOutputStream(file));
			}
			document.open();

			int m = columnsToPrint.length;
			int n = table.getRowCount();
			Table aTable = new Table(m, n + 1);
			for (int i = 0; i < m; i++)
			{
				int col = columnsToPrint[i];
				Cell cell = new Cell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBackgroundColor(new Color(200, 200, 200));
				cell.add(new Phrase(new Chunk(table.getColumnName(col), FontFactory.getFont(FontFactory.HELVETICA, 12,
				        Font.BOLD))));
				aTable.addCell(cell);
			}
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < m; j++)
				{
					int col = columnsToPrint[j];
					Object obj = table.getValueAt(i, col);
					String str = formatObject(obj);
					aTable.addCell(new Phrase(new Chunk(str, FontFactory
					        .getFont(FontFactory.HELVETICA, 10, Font.NORMAL))));
				}
			}
			document.add(aTable);

			if (document != null && document.isOpen())
			{
				document.close();
			}
		} catch (DocumentException e)
		{
			throw new TechnicalException(e.getMessage());
		} finally
		{
			if (document != null && document.isOpen())
			{
				document.close();
			}
		}
		return file;
	}

	static DateFormat s_dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	static private String formatObject(Object obj)
	{
		String str;

		if (obj == null)
		{
			str = "";
		} else if (obj instanceof Boolean)
		{
			str = ((Boolean) obj).booleanValue() ? "X" : "";
		} else if (obj instanceof Date)
		{
			str = s_dateFormat.format((Date) obj);
		} else if (obj instanceof Collection)
		{
			StringBuffer buffer = new StringBuffer();
			Object[] items = ((Collection) obj).toArray();
			Arrays.sort(items, ComparatorHelper.createComparator());
			for (int i = 0; i < items.length; i++)
			{
				buffer.append(items[i] + "\n");
			}
			str = buffer.toString();
		} else
		{
			str = obj.toString();
		}

		return str;
	}

	/**
	 * @param model
	 */
	private static void writeCsv(ZTable table, int[] columnsToPrint, File file) throws IOException
	{
		StringBuffer buffer = new StringBuffer();
		int m = columnsToPrint.length;
		int n = table.getRowCount();
		for (int i = 0; i < m; i++)
		{
			int col = columnsToPrint[i];
			String name = table.getColumnName(col);
			buffer.append(name);
			if (i < m)
			{
				buffer.append(",");
			}
		}
		buffer.append("\n");
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
			{
				int col = columnsToPrint[j];
				Object obj = table.getValueAt(i, col);
				buffer.append(obj != null ? obj.toString() : "");
				if (j < m)
				{
					buffer.append(",");
				}
			}
			buffer.append("\n");
		}
		FileOutputStream fos = null;
		try
		{
			fos = new FileOutputStream(file);
			fos.write(buffer.toString().getBytes());
		} finally
		{
			if (fos != null)
			{
				try
				{
					fos.close();
				} catch (IOException e)
				{
					;
				}
			}
		}
	}
}
