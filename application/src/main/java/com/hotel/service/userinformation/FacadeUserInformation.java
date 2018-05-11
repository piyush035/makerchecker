/**
 * 
 */
package com.hotel.service.userinformation;

import javax.ejb.Remote;

import com.hotel.base.common.domain.user.dto.UserContext;
import com.hotel.base.common.domain.user.dto.UserInformationDto;

/**
 * @author Piyush
 */
@Remote
public interface FacadeUserInformation {
	/**
	 * @param id
	 * @return
	 */
	UserInformationDto getUserInformation(final long id);
	
	/**
	 * @return
	 */
	UserContext connectUser();
}
