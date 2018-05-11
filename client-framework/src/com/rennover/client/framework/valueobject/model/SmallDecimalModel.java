package com.rennover.client.framework.valueobject.model;

import com.rennover.hotel.common.valueobject.SmallDecimal;

public class SmallDecimalModel extends ValueModel
{
	public SmallDecimalModel()
	{
		super(SmallDecimal.class);
	}

	public SmallDecimal getSmallDecimalValue()
	{
		return (SmallDecimal) getValue();
	}

}