/**
 * 
 */
package com.hotel.postalcodes.dao;

import java.util.List;

import com.hotel.base.common.domain.postalcodes.bean.PostalCodesBean;
import com.hotel.base.common.persistance.dao.DefaultDao;

/**
 * @author Prince
 */
public interface PostalCodesDao extends DefaultDao {
	/**
	 * @param stateId
	 * @return
	 */
	PostalCodesBean findPostalCodesByStateId(int stateId);
	
	/**
	 * @return
	 */
	List<PostalCodesBean> getPostalCodesAll();
	
}
