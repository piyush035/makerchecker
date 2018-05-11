package com.hotel.business.country.impl;

import java.util.ArrayList;
import java.util.List;

import com.hotel.base.common.domain.country.bean.CountryBean;
import com.hotel.base.common.domain.country.dto.CountryDto;
import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusinessManager;
import com.hotel.business.country.CountryBusiness;
import com.hotel.business.country.CountryBusinessManager;
import com.hotel.country.dao.CountryDao;

/**
 * @author Prince
 */
public class CountryBusinessManagerImpl extends AbstractDefaultBusinessManager
		implements CountryBusinessManager {

	/** */
	private CountryDao countryDao;

	/** Businees. */
	private CountryBusiness countryBusiness;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DefaultDao getDefaultDao() {
		return countryDao;
	}

	/**
	 * @param DefaultDao
	 *            the DefaultDao to set
	 */
	public void setDefaultDao(final CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	/**
	 * @return the countryBusiness
	 */
	public CountryBusiness getCountryBusiness() {
		return countryBusiness;
	}

	/**
	 * @param countryBusiness
	 *            the countryBusiness to set
	 */
	public void setCountryBusiness(final CountryBusiness countryBusiness) {
		this.countryBusiness = countryBusiness;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CountryDto> getCountryAll() {
		List<CountryBean> countryList = countryDao.getCountryAll();
		List<CountryDto> countryDtoList = new ArrayList<CountryDto>();
		for (CountryBean bean : countryList) {
			countryDtoList.add(bean.getCountryDto());
		}
		return countryDtoList;
	}
}