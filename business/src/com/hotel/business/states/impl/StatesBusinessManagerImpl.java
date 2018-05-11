package com.hotel.business.states.impl;

import java.util.ArrayList;
import java.util.List;

import com.hotel.base.common.domain.states.bean.StatesBean;
import com.hotel.base.common.domain.states.dto.StatesDto;
import com.hotel.base.common.persistance.dao.DefaultDao;
import com.hotel.base.domain.transverse.business.impl.AbstractDefaultBusinessManager;
import com.hotel.business.states.StatesBusiness;
import com.hotel.business.states.StatesBusinessManager;
import com.hotel.states.dao.StatesDao;
import com.rennover.hotel.common.helper.CollectionHelper;

/**
 * @author Prince
 */
public class StatesBusinessManagerImpl extends AbstractDefaultBusinessManager implements StatesBusinessManager {
	
	/** */
	private StatesDao statesDao;
	
	/** Businees. */
	private StatesBusiness statesBusiness;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DefaultDao getDefaultDao() {
		return statesDao;
	}
	
	/**
	 * @param DefaultDao the DefaultDao to set
	 */
	public void setDefaultDao(final StatesDao statesDao) {
		this.statesDao = statesDao;
	}
	
	/**
	 * @return the statesBusiness
	 */
	public StatesBusiness getStatesBusiness() {
		return statesBusiness;
	}
	
	/**
	 * @param statesBusiness the statesBusiness to set
	 */
	public void setStatesBusiness(final StatesBusiness statesBusiness) {
		this.statesBusiness = statesBusiness;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StatesDto> getStatesAll() {
		List<StatesBean> statesList = statesDao.getStatesAll();
		List<StatesDto> statesDtoList = new ArrayList<StatesDto>();
		if (!CollectionHelper.isNullOrEmpty(statesList)) {
			for (StatesBean bean : statesList) {
				statesDtoList.add(bean.getStatesDto());
			}
		}
		return statesDtoList;
	}
}