package com.hotel.client.create.user;

import com.hotel.base.common.domain.address.dto.AddressDto;
import com.hotel.base.common.domain.city.dto.CityDto;
import com.hotel.base.common.domain.country.dto.CountryDto;
import com.hotel.base.common.domain.user.dto.UserInformationDto;
import com.hotel.base.common.domain.user.dto.UserLoginDto;
import com.hotel.client.common.message.ErrorMessage;
import com.hotel.client.common.message.MessageConstants;
import com.hotel.common.facade.FacadeFactory;
import com.rennover.client.framework.mvc.ActionDescription;
import com.rennover.client.framework.mvc.PanelController;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpEvent;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpListener;
import com.rennover.client.framework.window.WindowManager;

public class UserController extends PanelController implements InstantHelpListener {
	
	/** */
	public static final ActionDescription ACTION_CHECK_AVAILABILITY = new ActionDescription("Check Availability",
			"doCheckAvailability", null, "Check user name available or not.");
	
	/** */
	public static final ActionDescription ACTION_CREATE_USER = new ActionDescription("Create User",
			"doCreateUser", null, "Create User");
	
	/** */
	public static final ActionDescription ACTION_CANCEL = new ActionDescription("Cancel",
			"doCancel", null, "Cancel to create user.");
	
	/**
	 * 
	 * 
	 */
	public UserController() {
		init();
	}
	
	/**
	 * 
	 * 
	 */
	public UserController(boolean isForModification) {
		init();
	}
	
	/**
	 * @param parentController
	 */
	public UserController(PanelController parentController) {
		super(parentController);
		init();
	}
	
	/**
	 * @return
	 */
	public UserModel getUserModel() {
		return (UserModel) this.getModel();
	}
	
	/**
	 * @return
	 */
	public UserView getUserView() {
		return (UserView) this.getView();
	}
	
	/**
	 * 
	 */
	protected void checkPreconditions() {
		
		//RolesDto rolesDto = UserContextManager.getUserContext().getUserLogin().getRoleDto();
		
	}
	
	@Override
	protected void createModel() {
		this.setModel(new UserModel());
		
	}
	/**
	 * 
	 */
	@Override
	protected void retrieveInitialData() {		
		getUserModel().setRolesList(FacadeFactory.getFacadeRoles().getRolesAll());
		getUserModel().setStateList(FacadeFactory.getFacadeStates().getStatesAll());
		getUserModel().setCountryList(FacadeFactory.getFacadeCountry().getCountryAll());
	}
	
	@Override
	protected void createView() {
		this.setView(new UserView(this, getUserModel()));
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
	public void doCheckAvailability() {
		UserLoginDto userLoginDto = (UserLoginDto) getUserModel().getUserLoginModel().getWorkingValueObject();
		if (null != userLoginDto.getUserName()) {
			if (!FacadeFactory.getFacadeUserLogin().checkUserAvailability(userLoginDto.getUserName())) {
				WindowManager.showInformationMessage(ErrorMessage.ERR_002, this.getUserView());
			} else {
				WindowManager.showInformationMessage(MessageConstants.MSG_002, this.getUserView());
				getUserView().enableFields();
			}
		} else {
			WindowManager.showInformationMessage(ErrorMessage.ERR_001, this.getUserView());
		}
		
	}
	
	public void doCreateUser(){
		UserLoginDto userLoginDto = (UserLoginDto) getUserModel().getUserLoginModel().getWorkingValueObject();
		UserInformationDto userInfoDto = (UserInformationDto) getUserModel().getUserInfoModel().getWorkingValueObject();
		CityDto presentCityDto = (CityDto) getUserModel().getCityPresentModel().getWorkingValueObject();
		CityDto permaCityDto = (CityDto) getUserModel().getCityPermanentModel().getWorkingValueObject();
		AddressDto presentAddressDto = (AddressDto) getUserModel().getAddressPresentModel().getWorkingValueObject();
		AddressDto permaadAddressDto = (AddressDto) getUserModel().getAddressPermanentModel().getWorkingValueObject();
		CountryDto  countryDto = (CountryDto)getUserModel().getCountryListVO().getSelectedValue();
		System.out.println("Create user");
	}
	
	/**
	 * 
	 */
	public void doCancel()
	{
		getUserView().close();
	}

}