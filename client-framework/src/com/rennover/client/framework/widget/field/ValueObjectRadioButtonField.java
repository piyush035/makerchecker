package com.rennover.client.framework.widget.field;

import java.util.List;

import com.rennover.hotel.common.exception.IncoherenceException;
import com.rennover.hotel.common.valueobject.ObjectId;
import com.rennover.hotel.common.valueobject.PersistentDomainObject;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class ValueObjectRadioButtonField extends RadioButtonField
{
	private boolean m_objectIdMode;

	/**
	 * Constructor for ValueObjectRadioButtonField.
	 */
	public ValueObjectRadioButtonField()
	{
		super();
	}

	/**
	 * Constructor ValueObjectRadioButtonField.
	 * 
	 * @param orientation
	 */
	public ValueObjectRadioButtonField(int orientation)
	{
		super(orientation);
	}

	/**
	 * Constructor ValueObjectRadioButtonField.
	 * 
	 * @param orientation
	 * @param objectList
	 */
	public ValueObjectRadioButtonField(int orientation, List objectList)
	{
		super(orientation, objectList);
	}

	public Object getValue()
	{
		Object vo = super.getValue();

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
				throw new IncoherenceException("En mode ObjectId, le getValue requiert un PersistentData");
			}
		}

		return vo;
	}

	public void setValue(Object value)
	{
		if (value == null)
		{
			setSelectedItem(null);
		} else if (isObjectIdMode())
		{
			if (value instanceof ObjectId)
			{
				for (int i = 0; i < getItemCount(); i++)
				{
					PersistentDomainObject element = (PersistentDomainObject) getItemAt(i);
					if (element.getObjectId().equals(value))
					{
						setSelectedItem(element);
						return;
					}
				}
				setSelectedItem(null);
			} else
			{
				throw new IncoherenceException("En mode ObjectId, le setValue requiert un ObjectId");
			}
		} else
		{
			super.setValue(value);
		}
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