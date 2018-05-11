package com.rennover.client.framework.widget.table;

import java.util.List;

import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.hotel.common.valueobject.ValueObject;

public interface VOEditionComponent
{
	public void setEditedValueObject(ValueObject vo);

	public ValueObject getEditedValueObject();

	public ZPanel getEditionPanel();

	public ZPanel getAddPanel();

	public List getVomListForValidation();

	public ZPanel getDisplayPanel();
}
