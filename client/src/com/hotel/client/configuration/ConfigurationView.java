package com.hotel.client.configuration;

import com.hotel.client.create.user.constants.UserConstants;
import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpEvent;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpListener;
import com.rennover.client.framework.widget.button.ButtonFactory;

/**
 * @author Piyush
 */
public class ConfigurationView extends PanelView implements InstantHelpListener, UserConstants {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 505723223841714154L;

	
	private ConfigurationPanel configurationPanel;
	/**
	 * Default Construtor
	 */
	public ConfigurationView() {
		super();
		init();
	}
	
	/**
	 * @param controller ConfigurationController
	 * @param model ConfigurationModel
	 */
	public ConfigurationView(ConfigurationController controller, ConfigurationModel model) {
		super(controller, model);
		init();
	}
	
	/**
	 * Overloading methods Instantiates all the widgets on the screen.
	 */
	protected void instanciate() {
		configurationPanel = new ConfigurationPanel("Configuration", 3);
	}
	
	@Override
	protected void compose() {
		configurationPanel.setIconDirectory(ButtonFactory.ACCES_DIRECT_DIR);

		configurationPanel.addButton("createUser.gif", "Create Users",getConfigurationController().getAction(ConfigurationController.ACTION_CREATE_USER));
		configurationPanel.addButton("modifyUser.gif", "Modify Users",getConfigurationController().getAction(ConfigurationController.ACTION_MODIFY_USER));
		
		configurationPanel.addButton("addRoom.jpg", "Add Roomes", getConfigurationController().getAction(ConfigurationController.ACTION_ADD_ROOM));
		configurationPanel.addButton("modifyRoom.jpg", "Modify Roomes", getConfigurationController().getAction(ConfigurationController.ACTION_MODIFY_ROOM));
		
		configurationPanel.addButton("addRoom.jpg", "Country", getConfigurationController().getAction(ConfigurationController.ACTION_COUNTRY));
		configurationPanel.addButton("modifyRoom.jpg", "States", getConfigurationController().getAction(ConfigurationController.ACTION_STATE));
		configurationPanel.addButton("modifyRoom.jpg", "States", getConfigurationController().getAction(ConfigurationController.ACTION_CITY));
		configurationPanel.addButton("modifyRoom.jpg", "States", getConfigurationController().getAction(ConfigurationController.ACTION_POSTAL_CODE));
		add(configurationPanel);
	}
	
	@Override
	public void validityChanged(InstantHelpEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void valueChanged(InstantHelpEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public ConfigurationController getConfigurationController() {
		return (ConfigurationController) getController();
	}
	
	public ConfigurationModel getConfigurationModel() {
		return (ConfigurationModel) getModel();
	}	
}