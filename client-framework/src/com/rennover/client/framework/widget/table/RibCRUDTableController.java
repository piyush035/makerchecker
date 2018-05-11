package com.rennover.client.framework.widget.table;

import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.window.WindowManager;
import com.rennover.hotel.common.valueobject.ValueObject;

public class RibCRUDTableController extends CRUDTableController {
	
	private boolean m_ribreference = false;
	
	private boolean m_factureReference = false;

	public RibCRUDTableController(CRUDEditor editor, ValueObjectListModel model, boolean useConsulterButton, boolean useModifierButton) {
		super(editor, model, useConsulterButton, useModifierButton);
		// TODO Auto-generated constructor stub
	}

	public RibCRUDTableController(CRUDEditor editor, ValueObjectListModel model, boolean useConsulterButton) {
		super(editor, model, useConsulterButton);
		// TODO Auto-generated constructor stub
	}

	public RibCRUDTableController(CRUDEditor editor, ValueObjectListModel model) {
		super(editor, model);
		// TODO Auto-generated constructor stub
	}

	public void doRemove()
	{
		if (getRibReference())
		{
			WindowManager.showErrorMessage("Le RIB ne peut être supprimé car il est utilisé dans une forme de règlement",null);
			return;
		}	
		
		if (getFactureReference())
		{
			WindowManager.showErrorMessage("Certaines domiciliations bancaires déjà utilisées n'ont pu être supprimées",null);
			return;
		}
		
		ValueObject selectedVO = m_table.getSelectedValueObject();
		if (m_editor.doRemove(selectedVO))
		{
			m_model.removeValueObject(selectedVO);
		}
	}

	public void setRibReference(boolean enabled)
	{
		m_ribreference = enabled;
	}
	
	public boolean getRibReference()
	{
		return m_ribreference;
	}
	
	public void doUpdate()
	{
		ValueObject selectedVO = m_table.getSelectedValueObject();
		if (m_editor.doUpdate(selectedVO))
		{
			m_model.updateValueObject(selectedVO);
		}
	}
	
	public void setFacturebReference(boolean enabled)
	{
		m_factureReference = enabled;
	}
	
	public boolean getFactureReference()
	{
		return m_factureReference;
	}
	
}
