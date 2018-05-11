package com.hotel.client.common.welcome;

import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.base.ZScrollPane;
import com.rennover.client.framework.widget.panel.OrganisedPanel;

public class HomeScreenPanel extends OrganisedPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8298307542907544052L;

	private HomeScreenFrame homeScreemFrame = null;
	
	public HomeScreenPanel(HomeScreenFrame homeScreenFrame) {

		super(STYLE_1LEFT_1RIGHT, new ZScrollPane(new LeftMenuPanel()), 
				new ZPanel(), true);
		this.homeScreemFrame = homeScreenFrame;
	}

	public void closeForcibly() {
		homeScreemFrame.exitApplicationForicbly();
	}

}
