package com.hotel.business.userinformation;

import com.hotel.base.common.domain.user.dto.UserContext;
import com.hotel.base.domain.transverse.business.DefaultBusinessManager;
/**
 * 
 * @author Piyush
 *
 */
public interface UserInformationBusinessManager extends DefaultBusinessManager{
	/**
	 * 
	 * @return UserContext
	 */
	public UserContext connectUser();
}
