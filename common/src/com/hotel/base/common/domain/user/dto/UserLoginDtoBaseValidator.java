/**
 * 
 */
package com.hotel.base.common.domain.user.dto;

import com.rennover.hotel.common.helper.Wrapper;
import com.rennover.hotel.common.validation.ValidationResponse;
import com.rennover.hotel.common.validation.ValueObjectValidator;
import com.rennover.hotel.common.valueobject.ValueObject;

public class UserLoginDtoBaseValidator extends ValueObjectValidator {
	public UserLoginDtoBaseValidator() {

		// property name = USER_NAME

		setPropertyReadOnly(UserLoginDto.USER_NAME, false);
		setPropertyMandatory(UserLoginDto.USER_NAME, true);
		setPropertyDescription(UserLoginDto.USER_NAME, "Test");
		

		setPropertyReadOnly(UserLoginDto.PASSWORD, false);		
		setPropertyMandatory(UserLoginDto.PASSWORD, true);
		setPropertyDescription(UserLoginDto.PASSWORD, "Test");
		setPropertySizeMin(UserLoginDto.PASSWORD, 8);
		setPropertySizeMax(UserLoginDto.PASSWORD, 20);

		

	}

	/**
	 * 
	 */
	protected void validatePropertyRules(ValueObject valueObject,
			ValidationResponse response) {
		super.validatePropertyRules(valueObject, response);
		UserLoginDto obj = (UserLoginDto) valueObject;

		checkProperty(UserLoginDto.USER_NAME, Wrapper.wrap(obj.getUserName()),
				response);
		checkProperty(UserLoginDto.PASSWORD, Wrapper.wrap(obj.getUserName()),
				response);
	}
}