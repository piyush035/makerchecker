package com.hotel.client.alert;

import com.hotel.base.common.domain.alert.dto.AlertDto;
import com.rennover.client.framework.mvc.PanelModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;

/**
 * @author Piyush
 */
public class AlertModel extends PanelModel {
	// public static final ModelDescription USER_LOGIN_MODEL = new
	// ModelDescription(UserLoginDto.class);

	private ValueObjectModel alertModel = new ValueObjectModel(AlertDto.class);

	/**
	 * @return
	 */
	public void setAlertModel(AlertDto alertDto) {
		alertModel.setValueObject(alertDto);
	}

	/**
	 * @return the getAlertModel
	 */
	public ValueObjectModel getAlertModel() {
		return alertModel;
	}
}