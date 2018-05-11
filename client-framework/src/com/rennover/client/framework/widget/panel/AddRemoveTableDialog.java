package com.rennover.client.framework.widget.panel;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.List;

import com.rennover.client.framework.widget.table.TableRowModel;
import com.rennover.hotel.common.exception.TechnicalException;

/**
 * 
 */
public class AddRemoveTableDialog extends AddRemoveDialog
{
	static public AddRemoveTableDialog createAddRemoveTableDialog(Window owner, String title, List optionsList,
	        List choiceList, Class objectClass, TableRowModel rowModel)
	{
		if (owner instanceof Frame)
		{
			return new AddRemoveTableDialog((Frame) owner, title, optionsList, choiceList, objectClass, rowModel);
		} else if (owner instanceof Dialog)
		{
			return new AddRemoveTableDialog((Dialog) owner, title, optionsList, choiceList, objectClass, rowModel);
		} else
		{
			throw new TechnicalException("AddRemoveDialog can not be opened due to an invalid parent");
		}
	}

	static public AddRemoveTableDialog createAddRemoveTableDialog(Window owner, String title, List optionsList,
	        List choiceList, Class objectClass, TableRowModel rowModel, boolean manualySorted)
	{
		if (owner instanceof Frame)
		{
			return new AddRemoveTableDialog((Frame) owner, title, optionsList, choiceList, objectClass, rowModel,
			        manualySorted);
		} else if (owner instanceof Dialog)
		{
			return new AddRemoveTableDialog((Dialog) owner, title, optionsList, choiceList, objectClass, rowModel,
			        manualySorted);
		} else
		{
			throw new TechnicalException("AddRemoveDialog can not be opened due to an invalid parent");
		}
	}

	public AddRemoveTableDialog(Dialog owner, String title, List optionsList, List choiceList, Class objectClass,
	        TableRowModel rowModel)
	{
		super(owner, title);
		m_pnlAddRemove = new AddRemoveTablePanel(objectClass, rowModel);
		m_pnlAddRemove.setSourceList(optionsList);
		m_pnlAddRemove.setDestinationList(choiceList);
		init();
		centerToParent();
	}

	public AddRemoveTableDialog(Frame owner, String title, List optionsList, List choiceList, Class objectClass,
	        TableRowModel rowModel)
	{
		super(owner, title);
		m_pnlAddRemove = new AddRemoveTablePanel(objectClass, rowModel);
		m_pnlAddRemove.setSourceList(optionsList);
		m_pnlAddRemove.setDestinationList(choiceList);
		init();
		centerToParent();
	}

	public AddRemoveTableDialog(Dialog owner, String title, List optionsList, List choiceList, Class objectClass,
	        TableRowModel rowModel, boolean manualySorted)
	{
		super(owner, title);
		m_pnlAddRemove = new AddRemoveTablePanel(objectClass, rowModel, manualySorted);
		m_pnlAddRemove.setSourceList(optionsList);
		m_pnlAddRemove.setDestinationList(choiceList);
		init();
		centerToParent();
	}

	public AddRemoveTableDialog(Frame owner, String title, List optionsList, List choiceList, Class objectClass,
	        TableRowModel rowModel, boolean manualySorted)
	{
		super(owner, title);
		m_pnlAddRemove = new AddRemoveTablePanel(objectClass, rowModel, manualySorted);
		m_pnlAddRemove.setSourceList(optionsList);
		m_pnlAddRemove.setDestinationList(choiceList);
		init();
		centerToParent();
	}

	// TO FIX DEFECT#3178
	/**
	 * @param owner
	 * @param title
	 * @param optionsList
	 * @param choiceList
	 * @param objectClass
	 * @param rowModel
	 * @param manualySorted
	 * @param buttonOrientation
	 *            ==> To set the button orientation to Vertical/Horizontal
	 *            Example:ButtonPanel.VERTICAL or ButtonPanel.HORIZONTAL
	 * @param buttonPosition
	 *            ==> To position the button to either EAST/SOUTH of the List.
	 *            Example:BorderLayout.EAST or BorderLayout.SOUTH
	 * @param buttonLabel
	 */

	public AddRemoveTableDialog(Frame owner, String title, List optionsList, List choiceList, Class objectClass,
	        TableRowModel rowModel, boolean manualySorted, int buttonOrientation, String buttonPosition,
	        String buttonLabel)
	{
		super(owner, title);
		m_pnlAddRemove = new AddRemoveTablePanel(objectClass, rowModel, manualySorted, buttonOrientation,
		        buttonPosition, buttonLabel);
		m_pnlAddRemove.setSourceList(optionsList);
		m_pnlAddRemove.setDestinationList(choiceList);
		init();
		centerToParent();
	}

	public AddRemoveTableDialog(Dialog owner, String title, List optionsList, List choiceList, Class objectClass,
	        TableRowModel rowModel, boolean manualySorted, int buttonOrientation, String buttonPosition,
	        String buttonLabel)
	{
		super(owner, title);
		m_pnlAddRemove = new AddRemoveTablePanel(objectClass, rowModel, manualySorted, buttonOrientation,
		        buttonPosition, buttonLabel);
		m_pnlAddRemove.setSourceList(optionsList);
		m_pnlAddRemove.setDestinationList(choiceList);
		init();
		centerToParent();
	}
}
