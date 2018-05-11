/**
 * 
 */
package com.hotel.country.dao;

import java.util.List;

import com.hotel.base.common.domain.country.bean.CountryBean;
import com.hotel.base.common.persistance.dao.DefaultDao;

/**
 * 
 * @author Prince
 * 
 */
public interface CountryDao extends DefaultDao {
	/**
	 * @param countryName
	 * @return
	 */
	CountryBean findCountryByName(String countryName);

	/**
	 * List of all Country
	 * 
	 * @return List<CountryDto>
	 */
	List<CountryBean> getCountryAll();
}