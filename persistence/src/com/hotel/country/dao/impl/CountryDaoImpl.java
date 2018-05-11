package com.hotel.country.dao.impl;

import java.util.List;

import com.hotel.base.common.domain.country.bean.CountryBean;
import com.hotel.base.common.persistance.dao.impl.AbstractDefaultDao;
import com.hotel.country.dao.CountryDao;

/**
 * @author Prince
 */
public class CountryDaoImpl extends AbstractDefaultDao implements CountryDao {
	
	/**
	 * {@inheritDoc}
	 */
	
	public CountryBean findCountryByName(String countryName) {
		List<CountryBean> countryList = getHibernateTemplate().find(
				"FROM com.hotel.base.common.domain.country.bean.CountryBean country where country.countryName = '"
						+ countryName + "'");
		if (!countryList.isEmpty()) {
			return countryList.get(0);
		} else {
			return null;
		}
	}
	
	public CountryBean findCountryByCode(int countryCode) {
		List<CountryBean> countryList = getHibernateTemplate().find(
				"FROM com.hotel.base.common.domain.country.bean.CountryBean country where country.countryCode = '"
						+ countryCode + "'");
		if (!countryList.isEmpty()) {
			return countryList.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public Class getDefaultClassBean() {
		
		return CountryBean.class;
	}
	
	@Override
	public List<CountryBean> getCountryAll() {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("FROM com.hotel.base.common.domain.country.bean.CountryBean country");
	}
	
}
