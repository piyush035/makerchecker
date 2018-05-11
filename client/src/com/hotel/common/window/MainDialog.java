package com.hotel.common.window;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Window;

import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.widget.base.ZMenuBar;
import com.rennover.client.framework.window.DefaultDialog;
import com.rennover.client.framework.window.WindowManager;

/**
 * .
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation. *
 * 
 * @author Piyush
 * 
 */
public class MainDialog extends DefaultDialog
{
	public MainDialog(Frame parent) throws HeadlessException
	{
		super(parent, "HOTEL");
	}

	public MainDialog(Dialog parent) throws HeadlessException
	{
		super(parent, "HOTEL");
	}

	// removed the commented constructors

	protected ZMenuBar createMenuBar()
	{
		return null;
	}

	public boolean confirmExit()
	{
		return WindowManager.showConfirmationMessage("Are you sure you want to leave application.", this);
	}

	/**
	 * @param parent
	 * @return
	 */
	public static MainDialog createMainDialog(Window parent, PanelView view)
	{
		MainDialog dialog = null;

		if (parent instanceof Frame)
		{
			dialog = new MainDialog((Frame) parent);
		} else
		{
			dialog = new MainDialog((Dialog) parent);
		}

		dialog.changeContent(view);

		return dialog;
	}
}