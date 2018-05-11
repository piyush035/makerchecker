/**
 * 
 */
package com.hotel.city.dao;

import java.util.List;

import com.hotel.base.common.domain.city.bean.CityBean;
import com.hotel.base.common.persistance.dao.DefaultDao;

/**
 * 
 * @author Prince
 * 
 */
public interface CityDao extends DefaultDao {
	/**
	 * @param cityName
	 * @return
	 */
	CityBean findCityByName(String cityName);

	/**
	 * List of all Cities
	 * 
	 * @return List<CityDto>
	 */
	List<CityBean> getCityAll();
}