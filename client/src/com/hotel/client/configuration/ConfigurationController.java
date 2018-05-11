package com.hotel.client.configuration;

import com.hotel.client.add.room.RoomController;
import com.hotel.client.country.CountryController;
import com.hotel.client.create.user.UserController;
import com.rennover.client.framework.mvc.ActionDescription;
import com.rennover.client.framework.mvc.PanelController;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpEvent;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpListener;
import com.rennover.client.framework.window.WindowManager;

public class ConfigurationController extends PanelController implements InstantHelpListener {
	
	/** */
	public static final ActionDescription ACTION_CREATE_USER = new ActionDescription("Add Users", "doCreateUser", null,
			"Add/Create Users.");
	
	/** */
	public static final ActionDescription ACTION_MODIFY_USER = new ActionDescription("Modify User", "doModifyUser",
			null, "Modify existing user.");
	
	/** */
	public static final ActionDescription ACTION_ADD_ROOM = new ActionDescription("Add Room", "doCreateRooms", null,
			"Add new rooms.");
	
	/** */
	public static final ActionDescription ACTION_MODIFY_ROOM = new ActionDescription("Modify Room", "doModifyRooms",
			null, "Modify existing rooms.");
	
	/** */
	public static final ActionDescription ACTION_COUNTRY = new ActionDescription("Country", "doCountry", null,
			"Add/Modify country.");
	
	/** */
	public static final ActionDescription ACTION_STATE = new ActionDescription("States", "doState", null,
			"Add/Modify state.");
	
	/** */
	public static final ActionDescription ACTION_CITY = new ActionDescription("States", "doCity", null,
			"Add/Modify city.");
	
	/** */
	public static final ActionDescription ACTION_POSTAL_CODE = new ActionDescription("Postal Code", "doPostalCode", null,
			"Add/Modify pincodes.");
	
	/**
	 * 
	 * 
	 */
	public ConfigurationController() {
		init();
	}
	
	/**
	 * 
	 * 
	 */
	public ConfigurationController(boolean isForModification) {
		init();
	}
	
	/**
	 * @param parentController
	 */
	public ConfigurationController(PanelController parentController) {
		super(parentController);
		init();
	}
	
	/**
	 * @return
	 */
	public ConfigurationModel getUserModel() {
		return (ConfigurationModel) this.getModel();
	}
	
	/**
	 * @return
	 */
	public ConfigurationView getUserView() {
		return (ConfigurationView) this.getView();
	}
	
	/**
	 * 
	 */
	protected void checkPreconditions() {

		//RolesDto rolesDto = UserContextManager.getUserContext().getUserLogin().getRoleDto();
		
	}
	
	@Override
	protected void createModel() {
		this.setModel(new ConfigurationModel());
		
	}
	
	/**
	 * 
	 */
	@Override
	protected void retrieveInitialData() {}
	
	@Override
	protected void createView() {
		this.setView(new ConfigurationView(this, getUserModel()));
	}
	
	@Override
	public void validityChanged(InstantHelpEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void valueChanged(InstantHelpEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Check that user name availability.
	 */
	public void doCreateUser() {
		WindowManager.showAsDialog(new UserController().getUserView(), this.getView());
	}
	
	public void doModifyUser() {
		WindowManager.showAsDialog(new UserController(true).getUserView(), this.getView());
	}
	
	public void doCreateRooms() {
		WindowManager.showAsDialog(new RoomController().getRoomView(), this.getView());
	}
	
	public void doModifyRooms() {
		WindowManager.showAsDialog(new RoomController(true).getRoomView(), this.getView());
	}
	
	public void doCountry(){
		WindowManager.showAsDialog(new CountryController().getCountryView(), this.getView());
	}
	
	public void doState(){
		
	}
	
	public void doCity(){
		
	}
	
	public void doPostalCode(){
		
	}
	public void doCancel() {
		//getCreateUserView().hide();
	}
}