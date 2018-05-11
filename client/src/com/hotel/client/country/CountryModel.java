package com.hotel.client.country;

import com.hotel.base.common.domain.country.dto.CountryDto;
import com.rennover.client.framework.mvc.ModelDescription;
import com.rennover.client.framework.mvc.PanelModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;

/**
 * @author Piyush
 */
public class CountryModel extends PanelModel {
	//public static final ModelDescription USER_LOGIN_MODEL = new ModelDescription(UserLoginDto.class);
	
	private ValueObjectModel searchCountryModel = new ValueObjectModel(CountryDto.class);
	
	private ValueObjectModel countryModel = new ValueObjectModel(CountryDto.class);
	
	public static final ModelDescription SELECTED_COUNTRY_MODEL = new ModelDescription(CountryDto.class, true);
	
	/**
	 * @param countryDto
	 */
	public void setSearchCountryModel(CountryDto countryDto) {
		searchCountryModel.setValueObject(countryDto);
	}
	
	/**
	 * @return the getCountryModel
	 */
	public ValueObjectModel getSearchCountryModel() {
		return searchCountryModel;
	}
	
	/**
	 * @param countryDto
	 */
	public void setCountryModel(CountryDto countryDto) {
		countryModel.setValueObject(countryDto);
	}
	
	/**
	 * @return the getCountryModel
	 */
	public ValueObjectModel getCountryModel() {
		return countryModel;
	}
}