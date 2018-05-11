package com.rennover.client.framework.widget.combobox;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface ComboBoxModelNew extends javax.swing.ComboBoxModel
{
	public void setNullValue(Object nullValue);

	public void setUseNullValue(boolean useNullValue);

	public void setUseMandatoryValue(boolean useMandatoryValue);

	public Object getNullValue();

	public boolean getUseNullValue();

	public boolean getUseMandatoryValue();
}
