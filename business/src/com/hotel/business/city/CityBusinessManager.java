/**
 * 
 */
package com.hotel.business.city;

import java.util.List;

import com.hotel.base.common.domain.city.dto.CityDto;
import com.hotel.base.domain.transverse.business.DefaultBusinessManager;

/**
 * @author Prince
 * 
 */
public interface CityBusinessManager extends DefaultBusinessManager {
	/**
	 * List of all Cities
	 * 
	 * @return List<CityDto>
	 */
	List<CityDto> getCityAll();
}
