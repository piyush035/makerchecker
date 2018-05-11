/**
 * 
 */
package com.hotel.service.states;

import java.util.List;

import javax.ejb.Remote;

import com.hotel.base.common.domain.states.dto.StatesDto;

/**
 * @author Prince
 * 
 */
@Remote
public interface FacadeStates {
	/**
	 * List of all states.
	 * 
	 * @return List<StatesDto>
	 */
	List<StatesDto> getStatesAll();
}
