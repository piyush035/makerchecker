/**
 * 
 */
package com.hotel.service.postalcodes;

import java.util.List;

import javax.ejb.Remote;

import com.hotel.base.common.domain.postalcodes.dto.PostalCodesDto;

/**
 * @author Prince
 * 
 */
@Remote
public interface FacadePostalCodes {
	/**
	 * List of all postalcodes.
	 * 
	 * @return List<PostalCodeDto>
	 */
	List<PostalCodesDto> getPostalCodesAll();
}
