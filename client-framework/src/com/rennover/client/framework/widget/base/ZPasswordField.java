package com.rennover.client.framework.widget.base;

import javax.swing.JPasswordField;
import javax.swing.text.Document;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ZPasswordField extends JPasswordField
{
	/**
	 * Constructor for ZPasswordField.
	 */
	public ZPasswordField()
	{
		super(16);
	}

	/**
	 * Constructor for ZPasswordField.
	 * 
	 * @param text
	 */
	public ZPasswordField(String text)
	{
		super(text, 16);
	}

	/**
	 * Constructor for ZPasswordField.
	 * 
	 * @param columns
	 */
	public ZPasswordField(int columns)
	{
		super(columns);
	}

	/**
	 * Constructor for ZPasswordField.
	 * 
	 * @param text
	 * @param columns
	 */
	public ZPasswordField(String text, int columns)
	{
		super(text, columns);
	}

	/**
	 * Constructor for ZPasswordField.
	 * 
	 * @param doc
	 * @param txt
	 * @param columns
	 */
	public ZPasswordField(Document doc, String txt, int columns)
	{
		super(doc, txt, columns);
	}
}