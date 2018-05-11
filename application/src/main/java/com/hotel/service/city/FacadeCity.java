/**
 * 
 */
package com.hotel.service.city;

import java.util.List;

import javax.ejb.Remote;

import com.hotel.base.common.domain.city.dto.CityDto;

/**
 * @author Prince
 * 
 */
@Remote
public interface FacadeCity {
	/**
	 * List of all Cities.
	 * 
	 * @return List<CityDto>
	 */
	List<CityDto> getCityAll();
}
