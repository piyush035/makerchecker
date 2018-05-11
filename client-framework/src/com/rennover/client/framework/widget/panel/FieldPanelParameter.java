package com.rennover.client.framework.widget.panel;

import java.awt.Component;

import javax.swing.Action;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class FieldPanelParameter
{
	public String m_labelName;

	public String m_toolTipText;

	public Component m_component;

	public Integer m_minSize;

	public String m_units;

	public char m_mnemonic = '\0';

	public Action m_action;

	public boolean m_fill = true;

	public boolean m_lockable = true;
}
