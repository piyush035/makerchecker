package com.rennover.client.framework.widget.panel;

import java.util.List;

public interface AddRemove
{
	public List getDestinationList();

	public List getSourceList();

	public void setDestinationList(List destinationList);

	public void setSourceList(List sourceList);

	public void addSelectedElements();

	public void removeSelectedElements();

	public void addAllElements();

	public void removeAllElements();

	public void setRemoveFromSourceMode(boolean mode);

	boolean isRemoveFromSourceMode();
}