package com.hotel.business.city.impl;

import java.util.ArrayList;
import java.util.List;

import com.hotel.base.common.domain.city.bean.CityBean;
import com.hotel.base.common.domain.city.dto.CityDto;
import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusinessManager;
import com.hotel.business.city.CityBusiness;
import com.hotel.business.city.CityBusinessManager;
import com.hotel.city.dao.CityDao;

/**
 * @author Prince
 */
public class CityBusinessManagerImpl extends AbstractDefaultBusinessManager
		implements CityBusinessManager {

	/** */
	private CityDao cityDao;

	/** Businees. */
	private CityBusiness cityBusiness;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DefaultDao getDefaultDao() {
		return cityDao;
	}

	/**
	 * @param DefaultDao
	 *            the DefaultDao to set
	 */
	public void setDefaultDao(final CityDao cityDao) {
		this.cityDao = cityDao;
	}

	/**
	 * @return the cityBusiness
	 */
	public CityBusiness getCityBusiness() {
		return cityBusiness;
	}

	/**
	 * @param cityBusiness
	 *            the cityBusiness to set
	 */
	public void setCityBusiness(final CityBusiness cityBusiness) {
		this.cityBusiness = cityBusiness;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CityDto> getCityAll() {
		List<CityBean> cityList = cityDao.getCityAll();
		List<CityDto> cityDtoList = new ArrayList<CityDto>();
		for (CityBean bean : cityList) {
			cityDtoList.add(bean.getCityDto());
		}
		return cityDtoList;
	}
}