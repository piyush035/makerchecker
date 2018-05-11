package com.hotel.alert.dao.impl;

import java.util.List;

import com.hotel.alert.dao.AlertDao;
import com.hotel.base.common.domain.alert.bean.AlertBean;
import com.hotel.base.common.persistance.dao.impl.AbstractDefaultDao;

public class AlertDaoImpl extends AbstractDefaultDao implements AlertDao {

	@Override
	public AlertBean findAlertByType(String alertType) {
		List<AlertBean> alertList = getHibernateTemplate()
				.find("FROM FROM com.hotel.base.common.domain.alert.bean.AlertBean alert where alert.alertType = '"
						+ alertType + "'");
		if (!alertList.isEmpty()) {
			return alertList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public AlertBean findAlertByName(String alertName) {
		List<AlertBean> alertList = getHibernateTemplate()
				.find("FROM FROM com.hotel.base.common.domain.alert.bean.AlertBean alert where alert.alertName = '"
						+ alertName + "'");
		if (!alertList.isEmpty()) {
			return alertList.get(0);
		} else {
			return null;
		}

	}

	@Override
	public List<AlertBean> getAlertAll() {
		return getHibernateTemplate().find(
				"FROM com.hotel.base.common.domain.alert.bean.AlertBean alert");
	}

	@Override
	public Class getDefaultClassBean() {
		return AlertBean.class;
	}
}
