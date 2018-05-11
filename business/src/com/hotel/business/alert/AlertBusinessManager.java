/**
 * 
 */
package com.hotel.business.alert;

import java.util.List;

import com.hotel.base.common.domain.alert.dto.AlertDto;
import com.hotel.base.domain.transverse.business.DefaultBusinessManager;

/**
 * @author Prince
 * 
 */
public interface AlertBusinessManager extends DefaultBusinessManager {

	/** */
	AlertBusiness getAlertBusiness();

	/**
	 * List of all Alert
	 * 
	 * @return List<AlertDto>
	 */
	List<AlertDto> getAlertAll();
}
