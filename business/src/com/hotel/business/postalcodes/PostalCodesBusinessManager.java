/**
 * 
 */
package com.hotel.business.postalcodes;

import java.util.List;

import com.hotel.base.common.domain.postalcodes.dto.PostalCodesDto;
import com.hotel.base.domain.transverse.business.DefaultBusinessManager;

/**
 * @author Prince
 * 
 */
public interface PostalCodesBusinessManager extends DefaultBusinessManager {
	/**
	 * List of all PostalCodes
	 * 
	 * @return List<PostalCodesDto>
	 */
	List<PostalCodesDto> getPostalCodesAll();
}
