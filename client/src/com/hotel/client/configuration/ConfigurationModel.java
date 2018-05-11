package com.hotel.client.configuration;

import java.util.List;

import com.hotel.base.common.domain.address.dto.AddressDto;
import com.hotel.base.common.domain.city.dto.CityDto;
import com.hotel.base.common.domain.country.dto.CountryDto;
import com.hotel.base.common.domain.roles.dto.RolesDto;
import com.hotel.base.common.domain.states.dto.StatesDto;
import com.hotel.base.common.domain.user.dto.UserInformationDto;
import com.hotel.base.common.domain.user.dto.UserLoginDto;
import com.rennover.client.framework.mvc.PanelModel;
import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.list.ValueObjectListList;

/**
 * @author Piyush
 */
public class ConfigurationModel extends PanelModel {
	//public static final ModelDescription USER_LOGIN_MODEL = new ModelDescription(UserLoginDto.class);
	
	private ValueObjectModel userLoginModel = new ValueObjectModel(UserLoginDto.class);
	
	private ValueObjectModel addressPresentModel = new ValueObjectModel(AddressDto.class);
	
	private ValueObjectModel cityPresentModel = new ValueObjectModel(CityDto.class);
	
	private ValueObjectModel addressPermanentModel = new ValueObjectModel(AddressDto.class);
	
	private ValueObjectModel cityPermanentModel = new ValueObjectModel(CityDto.class);
	
	private ValueObjectModel userInfoModel = new ValueObjectModel(UserInformationDto.class);
	
	public final ValueObjectListModel rolesList = new ValueObjectListModel(RolesDto.class);
	
	public final ValueObjectListModel stateList = new ValueObjectListModel(StatesDto.class);
	
	public final ValueObjectListModel countryList = new ValueObjectListModel(CountryDto.class);
	
	public final ValueObjectListList roleListVO = new ValueObjectListList(rolesList);
	
	public final ValueObjectListList stateListVO = new ValueObjectListList(stateList);
	
	public final ValueObjectListList countryListVO = new ValueObjectListList(countryList);
	
	public ValueObjectListModel getRolesListModel() {
		return rolesList;
	}
	
	public void setRolesList(List<RolesDto> list) {
		rolesList.setValueObjectList(list);
	}
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RolesDto> getRolesList() {
		return (List<RolesDto>) rolesList.getValueObjectList();
	}
	
	/**
	 * @param userLoginDto
	 */
	public void setUserLoginModel(UserLoginDto userLoginDto) {
		userLoginModel.setValueObject(userLoginDto);
	}
	
	/**
	 * @return the userLoginModel
	 */
	public ValueObjectModel getUserLoginModel() {
		return userLoginModel;
	}
	
	/**
	 * @return the userInfoModel
	 */
	public ValueObjectModel getUserInfoModel() {
		return userInfoModel;
	}
	
	/**
	 * @param userInfoModel the userInfoModel to set
	 */
	public void setUserInfoModel(UserInformationDto userInformationDto) {
		userInfoModel.setValueObject(userInformationDto);
	}
	
	/**
	 * @param presAddressDto
	 */
	public void setAddressPresentModel(AddressDto presAddressDto) {
		addressPresentModel.setValueObject(presAddressDto);
	}
	
	/**
	 * @return ValueObjectModel
	 */
	public ValueObjectModel getAddressPresentModel() {
		return addressPresentModel;
	}
	
	/**
	 * @param presAddressDto
	 */
	public void setAddressPermanentModel(AddressDto presAddressDto) {
		addressPermanentModel.setValueObject(presAddressDto);
	}
	
	/**
	 * @return ValueObjectModel
	 */
	public ValueObjectModel getAddressPermanentModel() {
		return addressPermanentModel;
	}
	
	public ValueObjectListModel getStateListModel() {
		return stateList;
	}
	
	public void setStateList(List<StatesDto> list) {
		stateList.setValueObjectList(list);
	}
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<StatesDto> getStateList() {
		return (List<StatesDto>) stateList.getValueObjectList();
	}
	
	public ValueObjectListModel getCountryListModel() {
		return countryList;
	}
	
	public void setCountryList(List<CountryDto> list) {
		countryList.setValueObjectList(list);
	}
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CountryDto> getCountryList() {
		return (List<CountryDto>) countryList.getValueObjectList();
	}
	
	/**
	 * @return the cityPresentModel
	 */
	public ValueObjectModel getCityPresentModel() {
		return cityPresentModel;
	}
	
	/**
	 * @param cityPresentModel the cityPresentModel to set
	 */
	public void setCityPresentModel(CityDto cityDto) {
		this.cityPresentModel.setValueObject(cityDto);
	}
	
	/**
	 * @return the cityPermanentModel
	 */
	public ValueObjectModel getCityPermanentModel() {
		return cityPermanentModel;
	}
	
	/**
	 * @param cityPermanentModel the cityPermanentModel to set
	 */
	public void setCityPermanentModel(CityDto cityDto) {
		this.cityPermanentModel.setValueObject(cityDto);
	}

	/**
	 * @return the roleListVO
	 */
	public ValueObjectListList getRoleListVO() {
		return roleListVO;
	}

	/**
	 * @return the stateListVO
	 */
	public ValueObjectListList getStateListVO() {
		return stateListVO;
	}

	/**
	 * @return the countryListVO
	 */
	public ValueObjectListList getCountryListVO() {
		return countryListVO;
	}
}