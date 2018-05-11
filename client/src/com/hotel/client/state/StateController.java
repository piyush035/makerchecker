package com.hotel.client.state;

import java.util.List;

import com.hotel.base.common.domain.country.dto.CountryDto;
import com.hotel.client.configuration.constant.ConfigurationConstants;
import com.hotel.common.facade.FacadeFactory;
import com.rennover.client.framework.mvc.ActionDescription;
import com.rennover.client.framework.mvc.PanelController;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpEvent;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpListener;

/**
 * @author Piyush
 */
public class StateController extends PanelController implements InstantHelpListener, ConfigurationConstants {
	
	/** */
	public static final ActionDescription ACTION_CHECK_AVAILABILITY = new ActionDescription("Check Availability",
			"doCheckAvailability", null, "Check room available or not.");
	
	/** */
	public static final ActionDescription ACTION_ADD_ROOM = new ActionDescription("Add Room", "doAddRoom", null,
			"Add new room.");
	
	/** */
	public static final ActionDescription ACTION_CANCEL = new ActionDescription("Cancel", "doCancel", null,
			"Cancel to add/modify country.");
	
	/** */
	public static final ActionDescription ACTION_COUNTRY_SEARCH = new ActionDescription("Search", "doSearch", null,
			"Search to get country.");
	
	/**
	 * 
	 * 
	 */
	public StateController() {
		init();
	}
	
	/**
	 * 
	 * 
	 */
	public StateController(boolean isForModification) {
		init();
	}
	
	/**
	 * @param parentController
	 */
	public StateController(PanelController parentController) {
		super(parentController);
		init();
	}
	
	/**
	 * @return
	 */
	public StateModel getStateModel() {
		return (StateModel) this.getModel();
	}
	
	/**
	 * @return
	 */
	public StateView getStateView() {
		return (StateView) this.getView();
	}
	
	/**
	 * 
	 */
	protected void checkPreconditions() {

		//RolesDto rolesDto = UserContextManager.getUserContext().getUserLogin().getRoleDto();
		
	}
	
	@Override
	protected void createModel() {
		this.setModel(new StateModel());
		
	}
	
	/**
	 * 
	 */
	@Override
	protected void retrieveInitialData() {}
	
	@Override
	protected void createView() {
		this.setView(new StateView(this, getStateModel()));
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
	public void doCheckAvailability() {}
	
	public void doAddRoom() {

	}
	
	public void doSearch() {
		CountryDto countryDto = (CountryDto) getStateModel().getSearchCountryModel().getWorkingValueObject();
		List<CountryDto> list = FacadeFactory.getFacadeCountry().getCountryAll();
		getStateModel().getValueObjectListModel(StateModel.SELECTED_COUNTRY_MODEL).setValueObjectList(list);
	}
	
	/**
	 * 
	 */
	public void doCancel() {
		getStateView().close();
	}
	
}
