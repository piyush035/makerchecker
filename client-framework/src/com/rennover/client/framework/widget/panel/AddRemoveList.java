package com.rennover.client.framework.widget.panel;

import java.util.List;

import javax.swing.Action;
import javax.swing.JComponent;

/**
 * @author Thierry.Caboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public interface AddRemoveList
{
	List getList();

	void moveSelectedUp();

	void moveSelectedDown();

	void addElements(List values);

	void removeElements(List values);

	boolean isSelectionEmpty();

	boolean isManualySorted();

	List getSelectedElements();

	JComponent getComponent();

	void setDoubleClickAction(Action action);
}