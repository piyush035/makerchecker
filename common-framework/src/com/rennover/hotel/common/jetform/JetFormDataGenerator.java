/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

/*
 * 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.rennover.hotel.common.jetform;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;

import com.rennover.hotel.common.valueobject.PropertyHelper;
import com.rennover.hotel.common.valueobject.SmallDecimal;

public class JetFormDataGenerator
{
	private PrintWriter m_writer;

	public JetFormDataGenerator(Writer writer)
	{
		m_writer = new PrintWriter(writer);
	}

	public PrintWriter getPrintWriter()
	{
		return m_writer;
	}

	// Commmented code removed

	public void addFaxJob(String jobName, String faxFolderPath, String targetName)
	{
		m_writer.print("^job ");
		m_writer.print(jobName);

		// options
		// Commmented code removed
		// target type
		m_writer.print(" -axpson ");
		m_writer.print("-apfon ");
		// Modified for defect 8224
		m_writer.print(" -ass5 -rtrunc");
		m_writer.print(" -apmfax ");
		m_writer.print(" -aspfaxomtl ");

		m_writer.print(" -aip01 ");

		// target name
		m_writer.print("-z");
		m_writer.print(faxFolderPath);
		m_writer.print(targetName);
		m_writer.print(".txt");

	}

	public void addJob(String jobName, String targetType, String targetName)
	{
		m_writer.print("^job ");
		m_writer.print(jobName);

		// options
		m_writer.print(" -ass5 -rtrunc");

		// target type
		m_writer.print(" -asp");
		m_writer.print(targetType);

		// target name
		m_writer.print(" -z\"");
		m_writer.print(targetName);
		m_writer.print('"');
	}

	public void addPrintJob(String jobName, String printerName)
	{
		m_writer.print("^job ");
		m_writer.print(jobName);

		// options
		m_writer.print(" -ass5 -rtrunc");

		// target type
		// Commmented code removed
		// Fixed for defect #6460 "To print on only one side of the page"
		// Commmented code removed
		m_writer.print(" -axpson");
		m_writer.print(" -apfon");

		// target name
		m_writer.print(" -z");
		m_writer.print(printerName);
		// Commmented code removed

	}

	public void addPreviewJob(String jobName, String targetType, String hostName, int nbApercu)
	{
		m_writer.print("^job ");
		m_writer.print(jobName);

		// options
		m_writer.print(" -ass5 -rtrunc");

		// target type
		m_writer.print(" -asp");
		m_writer.print(targetType);

		// Commmented code removed

		m_writer.print(" -apg/ ");
		m_writer.print(nbApercu);

		// target name
		m_writer.print(" -host=\"");
		m_writer.print(hostName);
		m_writer.print('"');
	}

	public void addArchivageJob(String jobName, String targetName)
	{
		m_writer.print("^job ");
		m_writer.print(jobName);

		// options
		m_writer.print(" -ass5 -rtrunc");

		// target type
		// Commmented code removed
		m_writer.print(" -apmfax ");
		m_writer.print(" -asppdf ");

		// target name
		m_writer.print(" -z");
		m_writer.print(targetName);
		m_writer.print(",u.pdf");

	}

	public void addForm(String formName)
	{
		m_writer.println();
		m_writer.print("^form ");
		m_writer.print(formName);
	}

	public void addPage(int number)
	{
		m_writer.println();
		m_writer.print("^page ");
		m_writer.print(number);
	}

	// Commmented code removed

	public void addFaxField(String faxNo, String raisonSociale, String toName, String fromName)
	{
		m_writer.println();
		m_writer.print("^fax ");
		m_writer.print("TO_FAX_NUM ");
		m_writer.print(faxNo);
		m_writer.print(" TO_COMPANY \"");
		m_writer.print(raisonSociale);
		m_writer.print('"');
		m_writer.print(" TO_NAME \"");
		m_writer.print(toName);
		m_writer.print('"');
		m_writer.print(" FROM_NAME \"");
		m_writer.print(fromName);
		m_writer.print('"');

		// Commmented code removed

	}

	public void addField(String fieldName, String fieldValue)
	{
		addField(fieldName);
		addFieldValue(fieldValue);
	}

	public void addField(String fieldName, Short fieldValue)
	{
		addField(fieldName, PropertyHelper.isNull(fieldValue) ? null : String.valueOf(fieldValue));
	}

	public void addField(String fieldName, Long fieldValue)
	{
		addField(fieldName, PropertyHelper.isNull(fieldValue) ? null : String.valueOf(fieldValue));
	}

	public void addField(String fieldName, SmallDecimal fieldValue)
	{
		addField(fieldName, PropertyHelper.isNull(fieldValue) ? null : String.valueOf(fieldValue));
	}

	public void addField(String fieldName, Date fieldValue)
	{
		addField(fieldName, PropertyHelper.isNull(fieldValue) ? null : String.valueOf(fieldValue));
	}

	public void addField(String fieldName, Double fieldValue)
	{
		addField(fieldName, PropertyHelper.isNull(fieldValue) ? null : String.valueOf(fieldValue));
	}

	public void addField(String fieldName)
	{
		m_writer.println();
		m_writer.print("^field ");
		m_writer.print(fieldName);
	}

	public void addFieldValue()
	{
		m_writer.println();
	}

	public void addFieldValue(String fieldValue)
	{
		m_writer.println();

		if (fieldValue != null)
		{
			m_writer.print(fieldValue);
		}
	}

	public void appendBold(String texte)
	{
		if (texte != null)
		{
			m_writer.print("\\b.");
			m_writer.print(texte);
			m_writer.print("\\b0.");
		}
	}

	public void appendItalic(String texte)
	{
		if (texte != null)
		{
			m_writer.print("\\i.");
			m_writer.print(texte);
			m_writer.print("\\i0.");
		}
	}

	public void appendUnderline(String texte)
	{
		if (texte != null)
		{
			m_writer.print("\\ul.");
			m_writer.print(texte);
			m_writer.print("\\ul0.");
		}
	}

	public void appendNewLine()
	{
		m_writer.println();
	}

	public void addSubForm(String subFormName)
	{
		m_writer.println();
		m_writer.print("^subform ");
		m_writer.print(subFormName);
	}

	public void addComment(String comment)
	{
		m_writer.println();
		m_writer.print("^comment ");
		m_writer.print(comment);
	}

	public void addCommentSeparator()
	{
		addComment("-------------------------------------------");
	}

	public void addNextPosition()
	{
		m_writer.println();
		m_writer.print("^field $POSITION");
	}

	// Added by Rakesh for #6708 03/01/06
	public void addNextPosition2()
	{
		m_writer.println();
		m_writer.print("^field $POSITION2");
	}

	public void addAbsolutePosition(float hValue, float vValue, String units)
	{
		m_writer.println();
		m_writer.print("^position ");
		m_writer.print(hValue);
		m_writer.print(' ');
		m_writer.print(vValue);
		m_writer.print(' ');
		m_writer.print(units);
	}

	// Method Added by Sasi for defect #7174
	public void appendBoldAndUnderLine(String texte)
	{
		if (texte != null)
		{
			m_writer.print("\\b");
			m_writer.print("\\ul.");
			m_writer.print(texte);
			m_writer.print("\\b0");
			m_writer.print("\\ul0.");
		}
	}

	// Method Added by Sasi for defect #7174
	public void appendBoldAndItalic(String texte)
	{
		if (texte != null)
		{
			m_writer.print("\\i");
			m_writer.print("\\b.");
			m_writer.print(texte);
			m_writer.print("\\i0");
			m_writer.print("\\b0.");
		}
	}

	// added method for ME-1024
	public void setposition0()
	{
		m_writer.println();
		// Removed the commmented code which was commented for the defect 10393
		m_writer.print("^position left  1 cm");
	}

	public void addGlobalField(String fieldName, String fieldValue)
	{
		addGlobalField(fieldName);
		addFieldValue(fieldValue);
	}

	public void addGlobalField(String fieldName, Short fieldValue)
	{
		addGlobalField(fieldName, PropertyHelper.isNull(fieldValue) ? null : String.valueOf(fieldValue));
	}

	public void addGlobalField(String fieldName, Long fieldValue)
	{
		addGlobalField(fieldName, PropertyHelper.isNull(fieldValue) ? null : String.valueOf(fieldValue));
	}

	public void addGlobalField(String fieldName, SmallDecimal fieldValue)
	{
		addGlobalField(fieldName, PropertyHelper.isNull(fieldValue) ? null : String.valueOf(fieldValue));
	}

	public void addGlobalField(String fieldName, Date fieldValue)
	{
		addGlobalField(fieldName, PropertyHelper.isNull(fieldValue) ? null : String.valueOf(fieldValue));
	}

	public void addGlobalField(String fieldName, Double fieldValue)
	{
		addGlobalField(fieldName, PropertyHelper.isNull(fieldValue) ? null : String.valueOf(fieldValue));
	}

	public void addGlobalField(String fieldName)
	{
		m_writer.println();
		m_writer.print("^global ");
		m_writer.print(fieldName);
	}
}
