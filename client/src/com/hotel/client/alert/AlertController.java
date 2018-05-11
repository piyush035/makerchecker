package com.hotel.client.alert;

import com.rennover.client.framework.mvc.PanelController;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpEvent;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpListener;

public class AlertController extends PanelController implements
		InstantHelpListener {

	/**
	 * 
	 * 
	 */
	public AlertController() {
		init();
	}

	/**
	 * 
	 * 
	 */
	public AlertController(boolean isForModification) {
		init();
	}

	/**
	 * @param parentController
	 */
	public AlertController(PanelController parentController) {
		super(parentController);
		init();
	}

	/**
	 * @return
	 */
	public AlertModel getAlertModel() {
		return (AlertModel) this.getModel();
	}

	/**
	 * @return
	 */
	public AlertView getAlertView() {
		return (AlertView) this.getView();
	}

	/**
	 * 
	 */
	protected void checkPreconditions() {

		// RolesDto rolesDto =
		// UserContextManager.getUserContext().getUserLogin().getRoleDto();

	}

	@Override
	protected void createModel() {
		this.setModel(new AlertModel());

	}

	/**
	 * 
	 */
	@Override
	protected void retrieveInitialData() {
	}

	@Override
	protected void createView() {
		this.setView(new AlertView(this, getAlertModel()));
	}

	@Override
	public void validityChanged(InstantHelpEvent e) {

	}

	@Override
	public void valueChanged(InstantHelpEvent e) {

	}

	/**
	 * Check that user name availability.
	 */
	/*
	 * public void doCheckAvailability() { }
	 * 
	 * public void doAddRoom() {
	 * 
	 * }
	 */

	/**
	 * 
	 */
	/*public void doCancel() {
		getAlertView().close();
	}*/

}