package com.rennover.client.framework.valueobject.model;

import com.rennover.hotel.common.valueobject.ObjectId;

public class ObjectIdModel extends ValueModel
{
	public ObjectIdModel()
	{
		super(ObjectId.class);
	}

	public ObjectId getObjectIdValue()
	{
		return (ObjectId) getValue();
	}
}
