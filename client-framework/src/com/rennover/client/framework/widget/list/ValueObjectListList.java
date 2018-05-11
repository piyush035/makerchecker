package com.rennover.client.framework.widget.list;

import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.widget.base.ZList;
import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ValueObjectListList extends ZList
{
	/**
	 * Constructor for ValueObjectListList.
	 * 
	 * @param valueObjectListModel
	 */
	public ValueObjectListList(ValueObjectListModel valueObjectListModel)
	{
		super(new ValueObjectListListModel(valueObjectListModel));
	}

	public ValueObject getSelectedValueObject()
	{
		return (ValueObject) getSelectedValue();
	}
}