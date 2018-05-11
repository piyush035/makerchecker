/**
 * 
 */
package com.hotel.states.dao;

import java.util.List;

import com.hotel.base.common.domain.states.bean.StatesBean;
import com.hotel.base.common.persistance.dao.DefaultDao;

/**
 * 
 * @author Prince
 * 
 */
public interface StatesDao extends DefaultDao {
	/**
	 * @param statesName
	 * @return
	 */
	StatesBean findStatesByName(String statesName);

	/**
	 * List of all States
	 * 
	 * @return List<StatesDto>
	 */
	List<StatesBean> getStatesAll();
}