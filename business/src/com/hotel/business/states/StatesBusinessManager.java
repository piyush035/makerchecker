/**
 * 
 */
package com.hotel.business.states;

import java.util.List;

import com.hotel.base.common.domain.states.dto.StatesDto;
import com.hotel.base.domain.transverse.business.DefaultBusinessManager;

/**
 * @author Prince
 * 
 */
public interface StatesBusinessManager extends DefaultBusinessManager {
	
	/** */
	StatesBusiness getStatesBusiness();
	
	/**
	 * List of all States
	 * 
	 * @return List<StatesDto>
	 */
	List<StatesDto> getStatesAll();
}
