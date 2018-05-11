package com.hotel.base.common.tech.exception.user;

/**
 * 
 * @author Piyush
 *
 */
public class DesactivationException extends ConnexionException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4014141996721735806L;

	public DesactivationException()
	{
		super("Account Disabled");
	}
}
