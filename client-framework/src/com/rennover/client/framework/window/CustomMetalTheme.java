package com.rennover.client.framework.window;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
class CustomMetalTheme extends DefaultMetalTheme
{
	ColorUIResource m_primary1;

	ColorUIResource m_primary2;

	ColorUIResource m_primary3;

	ColorUIResource m_secondary1;

	ColorUIResource m_secondary2;

	ColorUIResource m_secondary3;

	public CustomMetalTheme(MetalCustomColorSet colors)
	{
		m_primary1 = new ColorUIResource(colors.m_primary1);
		m_primary2 = new ColorUIResource(colors.m_primary2);
		m_primary3 = new ColorUIResource(colors.m_primary3);
		m_secondary1 = new ColorUIResource(colors.m_secondary1);
		m_secondary2 = new ColorUIResource(colors.m_secondary2);
		m_secondary3 = new ColorUIResource(colors.m_secondary3);
	}

	protected ColorUIResource getPrimary1()
	{
		return m_primary1;
	}

	protected ColorUIResource getPrimary2()
	{
		return m_primary2;
	}

	protected ColorUIResource getPrimary3()
	{
		return m_primary3;
	}

	protected ColorUIResource getSecondary1()
	{
		return m_secondary1;
	}

	protected ColorUIResource getSecondary2()
	{
		return m_secondary2;
	}

	protected ColorUIResource getSecondary3()
	{
		return m_secondary3;
	}

}
