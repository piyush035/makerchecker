package com.hotel.business.postalcodes.impl;

import java.util.ArrayList;
import java.util.List;

import com.hotel.base.common.domain.postalcodes.bean.PostalCodesBean;
import com.hotel.base.common.domain.postalcodes.dto.PostalCodesDto;
import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusinessManager;
import com.hotel.business.postalcodes.PostalCodesBusiness;
import com.hotel.business.postalcodes.PostalCodesBusinessManager;
import com.hotel.postalcodes.dao.PostalCodesDao;

/**
 * @author Prince
 */
public class PostalCodesBusinessManagerImpl extends
		AbstractDefaultBusinessManager implements PostalCodesBusinessManager {

	/** */
	private PostalCodesDao postalcodesDao;

	/** Businees. */
	private PostalCodesBusiness postalcodesBusiness;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DefaultDao getDefaultDao() {
		return postalcodesDao;
	}

	/**
	 * @param DefaultDao
	 *            the DefaultDao to set
	 */
	public void setDefaultDao(final PostalCodesDao postalcodesDao) {
		this.postalcodesDao = postalcodesDao;
	}

	/**
	 * @return the postalcodesBusiness
	 */
	public PostalCodesBusiness getPostalCodesBusiness() {
		return postalcodesBusiness;
	}

	/**
	 * @param postalcodesBusiness
	 *            the postalcodesBusiness to set
	 */
	public void setPostalCodesBusiness(
			final PostalCodesBusiness postalcodesBusiness) {
		this.postalcodesBusiness = postalcodesBusiness;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PostalCodesDto> getPostalCodesAll() {
		List<PostalCodesBean> postalcodesList = postalcodesDao
				.getPostalCodesAll();
		List<PostalCodesDto> postalcodesDtoList = new ArrayList<PostalCodesDto>();
		for (PostalCodesBean bean : postalcodesList) {
			postalcodesDtoList.add(bean.getPostalCodesDto());
		}
		return postalcodesDtoList;
	}
}