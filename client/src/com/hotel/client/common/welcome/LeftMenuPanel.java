package com.hotel.client.common.welcome;

import com.hotel.client.main.HotelFullClientApplication;
import com.rennover.client.framework.widget.button.ButtonFactory;

/**
 * @author Piyush
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class LeftMenuPanel extends MenuPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 314598977794962585L;

	public LeftMenuPanel() {
		super("", 1);
		compose();
	}

	protected void compose() {
		setIconDirectory(ButtonFactory.ACCES_DIRECT_DIR);

		addButton(
				"Room Calendar",
				getApplicationAction(HotelFullClientApplication.ACTION_ROOM_CALENDAR));
		addButton(
				"Banquet Calendar",
				getApplicationAction(HotelFullClientApplication.ACTION_BANQUET_CALENDAR));
		addButton(
				"Event Calendar",
				getApplicationAction(HotelFullClientApplication.ACTION_EVENT_CALENDAR));
		addButton(
				"History",
				getApplicationAction(HotelFullClientApplication.ACTION_HISTORY));
		addButton(
				"Reports",
				getApplicationAction(HotelFullClientApplication.ACTION_REPORTS));
		addButton(
				"Kitchen Order",
				getApplicationAction(HotelFullClientApplication.ACTION_KITCHEN_ORDER));
		addButton(
				"Laundry",
				getApplicationAction(HotelFullClientApplication.ACTION_LAUNDRY));
		addButton(
				"Tools",
				getApplicationAction(HotelFullClientApplication.ACTION_TOOLS));
		addButton(
				"Directory",
				getApplicationAction(HotelFullClientApplication.ACTION_DIRECTORY));
		addButton(
				"Alert",
				getApplicationAction(HotelFullClientApplication.ACTION_ALERT));
		addButton(
				"Configuration",
				getApplicationAction(HotelFullClientApplication.ACTION_CONFIGURATION));
	}
}
