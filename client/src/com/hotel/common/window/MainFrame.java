package com.hotel.common.window;

import java.awt.HeadlessException;

import com.hotel.client.main.HotelClientApplication;
import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.widget.base.ZMenuBar;
import com.rennover.client.framework.widget.panel.ToolBar;
import com.rennover.client.framework.window.DefaultFrame;
import com.rennover.client.framework.window.WindowManager;

/**
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 * @author Piyush
 * 
 */
public class MainFrame extends DefaultFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5807564900568127692L;

	public MainFrame() throws HeadlessException
	{
		super("Hotel");
	}

	public MainFrame(PanelView view) throws HeadlessException
	{
		super(view);
	}

	protected ZMenuBar createMenuBar()
	{
		return new MainMenuBar();
	}

	protected ToolBar createToolBar()
	{
		return new MainToolBar();
	}

	public boolean confirmExit()
	{
		return WindowManager.showConfirmationMessage("Are you sure you want to leave application?", this);
	}

	public void doOnExitApplication()
	{
		try
		{
			HotelClientApplication.getHotelApplication().logout();
		} catch (Exception ex)
		{
			WindowManager.showWarningMessage("There was a problem when disconnecting.", this);
		}
	}
}