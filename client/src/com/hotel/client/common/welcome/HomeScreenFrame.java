package com.hotel.client.common.welcome;

import com.hotel.common.window.MainFrame;

/**
 * @author Piyush
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class HomeScreenFrame extends MainFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8012148532328805793L;
	private HomeScreenPanel m_panel = null;

	public HomeScreenFrame() {

		m_panel = new HomeScreenPanel(this);
		compose();

	}

	private void compose() {
		changeContent(m_panel);
	}

	public void show() {
		super.show();
		m_panel.setDividerLocation(.70, .50);
	}

	public void exitApplicationForicbly() {
		setAskForExit(false);
		exitApplication();
	}
	
	public HomeScreenPanel getHomeScreenPanel(){
		return m_panel;
	}
}