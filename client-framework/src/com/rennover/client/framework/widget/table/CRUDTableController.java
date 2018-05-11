package com.rennover.client.framework.widget.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.rennover.client.framework.mvc.ActionDescription;
import com.rennover.client.framework.mvc.Controller;
import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.widget.base.ZTable;
import com.rennover.hotel.common.log.Logger;
import com.rennover.hotel.common.valueobject.PropertyHelper;
import com.rennover.hotel.common.valueobject.ValueObject;

public class CRUDTableController extends Controller implements TablePanelController
{
	static final ActionDescription ACTION_ADD = new ActionDescription("Ajouter", "doAdd");

	static final ActionDescription ACTION_UPDATE = new ActionDescription("   Modifier   ", "doUpdate");

	static final ActionDescription ACTION_DISPLAY = new ActionDescription("Consulter", "doDisplay");

	static final ActionDescription ACTION_REMOVE = new ActionDescription("Supprimer", "doRemove");

	protected ValueObjectListModel m_model;

	protected ValueObjectListTable m_table;

	protected CRUDEditor m_editor;

	// permet de savoir si les boutons doivent etre grises ou non
	boolean m_currentEnabledState = true;

	boolean m_supprimerEnabled = true;

	boolean m_ajouterEnabled = true;

	boolean m_modifierEnabled = true;

	boolean m_consulterUsed = true;

	boolean m_consultorEnabled = true; // added for defect # 7941

	boolean m_modifierUsed = true; // added for defect # 7825
	
	public CRUDTableController(CRUDEditor editor, ValueObjectListModel model, boolean useConsulterButton)
	{
		super(null);
		m_consulterUsed = useConsulterButton;
		m_editor = editor;
		m_model = model;
		if (editor instanceof DefaultCRUDEditor)
		{
			((DefaultCRUDEditor) editor).setValueObjectListModel(model);
		}
	}

	public CRUDTableController(CRUDEditor editor, ValueObjectListModel model, boolean useConsulterButton,
	        boolean useModifierButton)
	{
		super(null);
		m_consulterUsed = useConsulterButton;
		m_modifierUsed = useModifierButton;
		m_editor = editor;
		m_model = model;
		if (editor instanceof DefaultCRUDEditor)
		{
			((DefaultCRUDEditor) editor).setValueObjectListModel(model);
		}
	}

	public CRUDTableController(CRUDEditor editor, ValueObjectListModel model)
	{
		this(editor, model, false);
	}

	private void updateButtons()
	{
		// added null pointer check while testing defect 8588
		if (m_currentEnabledState && !PropertyHelper.isNull(m_table) && m_table.getSelectedRow() == -1)
		{
			getAction(ACTION_ADD).setEnabled(m_ajouterEnabled);
			getAction(ACTION_DISPLAY).setEnabled(false);
			getAction(ACTION_UPDATE).setEnabled(false);
			getAction(ACTION_REMOVE).setEnabled(false);
		} else if (m_currentEnabledState)
		{
			getAction(ACTION_ADD).setEnabled(m_ajouterEnabled);
			getAction(ACTION_DISPLAY).setEnabled(m_consultorEnabled); // added
																		// for
																		// 7941
			getAction(ACTION_UPDATE).setEnabled(m_modifierEnabled);
			getAction(ACTION_REMOVE).setEnabled(m_supprimerEnabled);
		} else
		{
			getAction(ACTION_ADD).setEnabled(false);
			getAction(ACTION_DISPLAY).setEnabled(false);
			getAction(ACTION_UPDATE).setEnabled(false);
			getAction(ACTION_REMOVE).setEnabled(false);
		}
	}

	public final List getButtonActionList()
	{
		List list = new ArrayList(4);
		list.add(getAction(ACTION_ADD));
		if (m_consulterUsed)
		{
			list.add(getAction(ACTION_DISPLAY));
		}
		if (m_modifierUsed)
		{
			list.add(getAction(ACTION_UPDATE));
		}
		list.add(getAction(ACTION_REMOVE));
		return list;
	}

	public void setTable(ZTable table)
	{
		m_table = (ValueObjectListTable) table;

		updateButtons();

		m_table.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				updateButtons();
			}
		});
	}

	public void setEnabled(boolean activate)
	{
		m_currentEnabledState = activate;

		updateButtons();
	}

	public void setSupprimerEnabled(boolean enabled)
	{
		m_supprimerEnabled = enabled;
		updateButtons();
	}

	public void setAjouterEnabled(boolean enabled)
	{
		m_ajouterEnabled = enabled;
		updateButtons();
	}

	public void setModifierEnabled(boolean enabled)
	{
		m_modifierEnabled = enabled;
		updateButtons();
	}

	public void doAdd()
	{
		ValueObject vo = m_editor.doAdd();
		if (vo != null)
		{
			m_model.addValueObject(vo);
		}
	}

	public void doRemove()
	{
		ValueObject selectedVO = m_table.getSelectedValueObject();
		

		if (m_editor.doRemove(selectedVO))
		{
			m_model.removeValueObject(selectedVO);
		}
	}

	public void doUpdate()
	{
		ValueObject selectedVO = m_table.getSelectedValueObject();
		if (m_editor.doUpdate(selectedVO))
		{
		}
	}

	public void doDisplay()
	{
		ValueObject selectedVO = m_table.getSelectedValueObject();
		m_editor.doDisplay(selectedVO);
	}

	public void setUniqueCompositeKeyErrorMessage(String message)
	{
		if (m_editor instanceof DefaultCRUDEditor)
		{
			((DefaultCRUDEditor) m_editor).setUniqueCompositeKeyErrorMessage(message);
		} else
		{
			Logger
			        .warn(
			                this,
			                "La méthode setUniqueCompositeKeyErrorMessage(message) de CRUDTable ne devrait pas etre appelée dans cette configuration.");
		}
	}

	// method added for 7941
	public void setConsultorEnabled(boolean enabled)
	{
		m_consultorEnabled = enabled;
		updateButtons();
	}

	/**
	 * used to clean the refrence #8201 -ashisht
	 */
	public void cleanReferences()
	{
		m_model = null;
		if (null != m_table)
		{
			m_table.removeAll();
			m_table = null;
		}
		if (null != m_editor)
		{
			m_editor.cleanReferences();
			m_editor = null;
		}
	}
}
