package com.rennover.client.framework.valueobject.model;

public class StringModel extends ValueModel
{
	public StringModel()
	{
		super(String.class);
	}

	public String getStringValue()
	{
		return (String) getValue();
	}
}