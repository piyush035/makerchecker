/**
 * 
 */
package com.hotel.alert.dao;

import java.util.List;

import com.hotel.base.common.domain.alert.bean.AlertBean;
import com.hotel.base.common.persistance.dao.DefaultDao;

/**
 * @author Prince
 */

public interface AlertDao extends DefaultDao {
	/**
	 * @param alertType
	 * @return
	 */
	AlertBean findAlertByType(String alertType);

	/**
	 * List of all Alert
	 * 
	 * @return List<AlertDto>
	 */

	AlertBean findAlertByName(String alertName);

	/**
	 * List of all Alert
	 * 
	 * @return List<AlertDto>
	 */
	List<AlertBean> getAlertAll();
}