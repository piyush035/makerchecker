/**
 * 
 */
package com.hotel.base.common.tech.exception.user;


/**
 * @author Piyush
 *
 */
public class LoginIncorrectException extends ConnexionException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3790940058344126628L;

	public LoginIncorrectException()
	{
		super("Wrong User Name");
	}
}