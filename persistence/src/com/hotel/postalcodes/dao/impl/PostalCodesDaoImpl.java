/**
 * 
 */
package com.hotel.postalcodes.dao.impl;

import java.util.List;

import com.hotel.base.common.domain.postalcodes.bean.PostalCodesBean;
import com.hotel.base.common.persistance.dao.impl.AbstractDefaultDao;
import com.hotel.postalcodes.dao.PostalCodesDao;

/**
 * 
 * @author Prince
 * 
 */
public class PostalCodesDaoImpl extends AbstractDefaultDao implements
		PostalCodesDao {

	/**
	 * {@inheritDoc}
	 */

	public PostalCodesBean findPostalCodesByStateId(int stateId) {
		List<PostalCodesBean> postalcodesList = getHibernateTemplate()
				.find("FROM com.hotel.base.common.domain.postalcodes.bean.PostalCodesBean postalcodes where postalcodes.stateId = '"
						+ stateId + "'");
		if (!postalcodesList.isEmpty()) {
			return postalcodesList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Class getDefaultClassBean() {

		return PostalCodesBean.class;
	}

	@Override
	public List<PostalCodesBean> getPostalCodesAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
