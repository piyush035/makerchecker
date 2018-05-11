/**
 * 
 */
package com.hotel.user.dao;

import com.hotel.base.common.domain.user.bean.UserInformationBean;
import com.hotel.base.common.persistance.dao.DefaultDao;

/**
 * 
 * @author Prince
 * 
 */
public interface UserInformationDao extends DefaultDao {

	UserInformationBean findUserInformationByNumber(long mobileNumber);
	UserInformationBean findUserInformationByemail(String email);
}
