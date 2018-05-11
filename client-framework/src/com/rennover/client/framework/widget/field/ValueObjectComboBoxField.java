package com.rennover.client.framework.widget.field;

import java.util.List;

import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.widget.combobox.ValueObjectListComboBoxModel;
import com.rennover.hotel.common.exception.IncoherenceException;
import com.rennover.hotel.common.valueobject.ObjectId;
import com.rennover.hotel.common.valueobject.PersistentDomainObject;
import com.rennover.hotel.common.valueobject.ValueObject;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ValueObjectComboBoxField extends ComboBoxField
{
	private boolean m_objectIdMode = false;

	/**
	 * Constructor for ValueObjectComboBoxField.
	 */
	public ValueObjectComboBoxField()
	{
		super();
	}

	public ValueObjectComboBoxField(List objectList)
	{
		super(objectList);
	}

	public ValueObjectComboBoxField(ValueObjectListModel valueObjectListModel)
	{
		super();
		setModel(new ValueObjectListComboBoxModel(valueObjectListModel));
	}

	public Object getValue()
	{
		ValueObject vo = (ValueObject) super.getValue();

		if (vo == null)
		{
			return null;
		}

		if (isObjectIdMode())
		{
			if (vo instanceof PersistentDomainObject)
			{
				PersistentDomainObject pdata = (PersistentDomainObject) vo;
				return pdata.getObjectId();
			} else
			{
				throw new IncoherenceException("In ObjectId mode, the getValue requires PersistentData");
			}
		}

		return vo;
	}

	public void setValue(Object value)
	{
		if (value == null)
		{
			super.setValue(null);
		} else if (isObjectIdMode())
		{
			if (value instanceof ObjectId)
			{
				for (int i = 0; i < getModel().getSize(); i++)
				{
					PersistentDomainObject element = (PersistentDomainObject) getModel().getElementAt(i);
					if (element != null && element.getObjectId().equals(value))
					{
						getModel().setSelectedItem(element);
						updateTooltip();
						return;
					}
				}
				throw new IncoherenceException("setValue impossible Expensive ObjectID '" + value
				        + "' did not match any item in the list ");
			} else
			{
				throw new IncoherenceException("setValue impossible as the ObjectId mode requires an ObjectId");
			}
		} else
		{
			super.setValue(value);
		}
		updateTooltip();
	}

	/**
	 * Returns the objectIdMode.
	 * 
	 * @return boolean
	 */
	public boolean isObjectIdMode()
	{
		return m_objectIdMode;
	}

	/**
	 * Sets the objectIdMode.
	 * 
	 * @param objectIdMode
	 *            The objectIdMode to set
	 */
	public void setObjectIdMode(boolean objectIdMode)
	{
		m_objectIdMode = objectIdMode;
	}
}