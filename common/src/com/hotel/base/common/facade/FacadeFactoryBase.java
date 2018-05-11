package com.hotel.base.common.facade;
/**
 * 
 * @author Piyush
 *
 */
public class FacadeFactoryBase
{
	private static FacadeLocator facadeLocator = null;

	public static void init(String userLogin, String password)
	{
		facadeLocator = new FacadeLocator(userLogin, password);

	}
	/**
	 * 
	 * @return
	 */
	protected static FacadeLocator getFacadeLocator()
	{
		if (facadeLocator != null)
		{
			return facadeLocator;
		} else
		{
			throw new IllegalStateException("FacadeFactory uninitialized.");
		}
	}

	protected static Object getProxy(Class aClass)
	{
		return getFacadeLocator().getProxy(aClass);
	}
}