/**
 * 
 */
package com.hotel.states.dao.impl;

import java.util.List;

import com.hotel.base.common.domain.states.bean.StatesBean;
import com.hotel.base.common.persistance.dao.impl.AbstractDefaultDao;
import com.hotel.states.dao.StatesDao;

/**
 * @author Prince
 */
public class StatesDaoImpl extends AbstractDefaultDao implements StatesDao {
	
	/**
	 * {@inheritDoc}
	 */
	
	public StatesBean findStatesByCountryId(int countryId) {
		List<StatesBean> statesList = getHibernateTemplate().find(
				"FROM com.hotel.base.common.domain.states.bean.StatesBean states where states.countryId = '"
						+ countryId + "'");
		if (!statesList.isEmpty()) {
			return statesList.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public Class getDefaultClassBean() {
		
		return StatesBean.class;
	}
	
	@Override
	public StatesBean findStatesByName(String statesName) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 */
	@Override
	public List<StatesBean> getStatesAll() {
		return getHibernateTemplate().find("FROM com.hotel.base.common.domain.states.bean.StatesBean states");
	}
	
}
