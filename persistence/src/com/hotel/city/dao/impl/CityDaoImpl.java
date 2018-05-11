/**
 * 
 */
package com.hotel.city.dao.impl;

import java.util.List;

import com.hotel.base.common.domain.city.bean.CityBean;
import com.hotel.base.common.persistance.dao.impl.AbstractDefaultDao;
import com.hotel.city.dao.CityDao;

/**
 * @author Prince
 */
public class CityDaoImpl extends AbstractDefaultDao implements CityDao {
	
	/**
	 * {@inheritDoc}
	 */
	
	public CityBean findCityByPostalCode(int postalcode) {
		List<CityBean> cityList = getHibernateTemplate().find(
				"FROM com.hotel.base.common.domain.city.bean.CityBean city where city.postalCode = '" + postalcode
						+ "'");
		if (!cityList.isEmpty()) {
			return cityList.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public Class getDefaultClassBean() {
		
		return CityBean.class;
	}
	
	@Override
	public CityBean findCityByName(String cityName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CityBean> getCityAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
