/**
 * 
 */
package com.hotel.service.country;

import java.util.List;

import javax.ejb.Remote;

import com.hotel.base.common.domain.country.dto.CountryDto;

/**
 * @author Prince
 * 
 */
@Remote
public interface FacadeCountry {
	/**
	 * List of all countries.
	 * 
	 * @return List<CountryDto>
	 */
	List<CountryDto> getCountryAll();
}
