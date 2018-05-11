package com.rennover.client.framework.widget.field;

import java.util.Arrays;
import java.util.List;

import javax.swing.ListSelectionModel;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class SelectionListField extends ListField
{
	public SelectionListField()
	{
		super();
		setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}

	public SelectionListField(List objectList)
	{
		this();
		setObjectList(objectList);
	}

	public Object getValue()
	{
		List list = Arrays.asList(this.getSelectedValues());
		return list;
	}

	public void setValue(Object valueList)
	{
		setSelectedValues((List) valueList);
	}

	public void setObjectList(List objectList)
	{
		super.setObjectList(objectList);
	}
}