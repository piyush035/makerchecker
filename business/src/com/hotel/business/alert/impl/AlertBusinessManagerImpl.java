/**
 * 
 */
package com.hotel.business.alert.impl;

import java.util.ArrayList;
import java.util.List;

import com.hotel.alert.dao.AlertDao;
import com.hotel.base.common.domain.alert.bean.AlertBean;
import com.hotel.base.common.domain.alert.dto.AlertDto;
import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusinessManager;
import com.hotel.business.alert.AlertBusiness;
import com.hotel.business.alert.AlertBusinessManager;
import com.rennover.hotel.common.helper.CollectionHelper;

/**
 * @author Prince
 */
public class AlertBusinessManagerImpl extends AbstractDefaultBusinessManager
		implements AlertBusinessManager {

	/** */
	private AlertDao alertDao;

	/** Businees. */
	private AlertBusiness alertBusiness;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DefaultDao getDefaultDao() {
		return alertDao;
	}

	/**
	 * @param DefaultDao
	 *            the DefaultDao to set
	 */
	public void setDefaultDao(final AlertDao alertDao) {
		this.alertDao = alertDao;
	}

	/**
	 * @return the statesBusiness
	 */
	public AlertBusiness getAlertBusiness() {
		return alertBusiness;
	}

	/**
	 * @param alertBusiness
	 *            the alertBusiness to set
	 */
	public void setAlertBusiness(final AlertBusiness alertBusiness) {
		this.alertBusiness = alertBusiness;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AlertDto> getAlertAll() {
		List<AlertBean> alertList = alertDao.getAlertAll();
		List<AlertDto> alertDtoList = new ArrayList<AlertDto>();
		if (!CollectionHelper.isNullOrEmpty(alertList)) {
			for (AlertBean bean : alertList) {
				alertDtoList.add(bean.getalertDto());
			}
		}
		return alertDtoList;
	}
}