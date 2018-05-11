/**
 * 
 */
package com.hotel.service.alert;

import java.util.List;

import javax.ejb.Remote;

import com.hotel.base.common.domain.alert.dto.AlertDto;

/**
 * @author Prince
 * 
 */
@Remote
public interface FacadeAlert {
	/**
	 * List of all Alert
	 * 
	 * @return List<AlertDto>
	 */
	List<AlertDto> getAlertAll();
}
