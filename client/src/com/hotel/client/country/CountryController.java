package com.hotel.client.country;

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
public class CountryController extends PanelController implements InstantHelpListener, ConfigurationConstants {
	
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
	public CountryController() {
		init();
	}
	
	/**
	 * 
	 * 
	 */
	public CountryController(boolean isForModification) {
		init();
	}
	
	/**
	 * @param parentController
	 */
	public CountryController(PanelController parentController) {
		super(parentController);
		init();
	}
	
	/**
	 * @return
	 */
	public CountryModel getCountryModel() {
		return (CountryModel) this.getModel();
	}
	
	/**
	 * @return
	 */
	public CountryView getCountryView() {
		return (CountryView) this.getView();
	}
	
	/**
	 * 
	 */
	protected void checkPreconditions() {

		//RolesDto rolesDto = UserContextManager.getUserContext().getUserLogin().getRoleDto();
		
	}
	
	@Override
	protected void createModel() {
		this.setModel(new CountryModel());
		
	}
	
	/**
	 * 
	 */
	@Override
	protected void retrieveInitialData() {}
	
	@Override
	protected void createView() {
		this.setView(new CountryView(this, getCountryModel()));
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
		CountryDto countryDto = (CountryDto) getCountryModel().getSearchCountryModel().getWorkingValueObject();
		List<CountryDto> list = FacadeFactory.getFacadeCountry().getCountryAll();
		getCountryModel().getValueObjectListModel(CountryModel.SELECTED_COUNTRY_MODEL).setValueObjectList(list);
	}
	
	/**
	 * 
	 */
	public void doCancel() {
		getCountryView().close();
	}
	
}
