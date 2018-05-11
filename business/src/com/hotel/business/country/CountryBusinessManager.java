/**
 * 
 */
package com.hotel.business.country;

import java.util.List;

import com.hotel.base.common.domain.country.dto.CountryDto;
import com.hotel.base.domain.transverse.business.DefaultBusinessManager;

/**
 * @author Prince
 * 
 */
public interface CountryBusinessManager extends DefaultBusinessManager {
	/**
	 * List of all Country
	 * 
	 * @return List<CountryDto>
	 */
	List<CountryDto> getCountryAll();
}
