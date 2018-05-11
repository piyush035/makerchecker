package com.hotel.client.common.welcome;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.hotel.client.main.HotelFullClientApplication;
import com.rennover.client.framework.mvc.ActionDescription;
import com.rennover.client.framework.mvc.ActionManager;
import com.rennover.client.framework.widget.base.ZButton;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.button.ButtonFactory;
import com.rennover.client.framework.widget.layout.GridData;
import com.rennover.client.framework.widget.layout.SWTGridLayout;

/**
 * @author Piyush
 * 
 *         To change the template for this generated sype comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MenuPanel extends ZPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2722473011726790091L;

	public MenuPanel(String title, int nbColumns) {
		super(title);
		compose(nbColumns);
	}

	static class DisabledAction extends AbstractAction {
		public DisabledAction() {
			setEnabled(true);
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	private static final Action m_disabledAction = new DisabledAction();

	private ZPanel m_panel = new ZPanel();

	private void compose(int nbColumns) {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), getName(),
				TitledBorder.LEFT, TitledBorder.TOP));
		m_panel.setBorder(new EmptyBorder(8, 8, 8, 8));
		m_panel.setLayout(new SWTGridLayout(nbColumns, false));		
		add(m_panel, BorderLayout.CENTER);
	}

	protected void setIconDirectory(String directoryName) {
		ButtonFactory.setDefaultIconDirectory(directoryName);
	}

	protected static String format(String text) {
		String formatted = text;
		if (text.indexOf("\n") != -1) {
			formatted = formatted.replaceAll("\\n", "<br>");
		}
		formatted = "<html><center>" + formatted + "</center></html>";

		return formatted;
	}

	protected void addButton(ZButton button) {
		GridData gridData = new GridData();
		gridData.m_horizontalAlignment = GridData.CENTER;
		gridData.m_verticalAlignment = GridData.BEGINNING;
		m_panel.add(button, gridData);
	}

	protected void addButton(String iconName, String rolloverIconName,
			String description, Action action) {
		if (action == null) {
			action = m_disabledAction;
		}

		ZButton button = ButtonFactory.newButton(iconName, description, action);
		button.setFocusable(false);
		addButton(button);
	}

	protected void addButton(String iconName, String description, Action action) {
		addButton(createButton(iconName, format(description), action));
	}

	protected void addButton(String description, Action action) {
		addButton(createButton(format(description), action));
	}

	protected ZButton createButton(String iconName, String description,
			Action action) {
		if (action == null) {
			action = m_disabledAction;
		}

		ZButton button = ButtonFactory.newButton(iconName, null, action);
		button.setFocusable(false);
		button.setText(description);
		button.setHorizontalTextPosition(ZButton.CENTER);
		button.setVerticalTextPosition(ZButton.BOTTOM);
		button.setHorizontalAlignment(ZButton.CENTER);
		button.setHorizontalAlignment(ZButton.CENTER);
		return button;
	}
	
	protected ZButton createButton( String description,
			Action action) {
		if (action == null) {
			action = m_disabledAction;
		}

		ZButton button = ButtonFactory.newButton(null, description, action);
		button.setBorderPainted(true);
		button.setContentAreaFilled(true);
		button.setFocusPainted(true);
		button.setMargin(new Insets(3, 3, 3, 3));
		button.setFocusable(true);
		button.setText(description);		
		button.setHorizontalTextPosition(ZButton.CENTER);
		button.setVerticalTextPosition(ZButton.BOTTOM);
		button.setHorizontalAlignment(ZButton.CENTER);
		button.setHorizontalAlignment(ZButton.CENTER);
		return button;
	}

	protected static Action getApplicationAction(
			ActionDescription actionDescription) {
		if (null != actionDescription) {
			return HotelFullClientApplication.getApplication().getAction(
					actionDescription);
		}
		return null;
	}

	protected Action getAction(ActionDescription actionDescription) {
		return ActionManager.createAction(this, actionDescription);
	}
}
