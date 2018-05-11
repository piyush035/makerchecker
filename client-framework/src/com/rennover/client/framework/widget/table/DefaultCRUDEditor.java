package com.rennover.client.framework.widget.table;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.panel.VOEditionDialog;
import com.rennover.client.framework.widget.panel.ValidationDialog;
import com.rennover.client.framework.widget.panel.ValidationPanel;
import com.rennover.client.framework.window.WindowManager;
import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.exception.UserShowableException;
import com.rennover.hotel.common.helper.ReflectionHelper;
import com.rennover.hotel.common.valueobject.GraphObject;
import com.rennover.hotel.common.valueobject.Identifiable;
import com.rennover.hotel.common.valueobject.ValueObject;

class DefaultCRUDEditor implements CRUDEditor
{
	private VOEditionComponent m_editionComponent;

	private ValueObjectListModel m_model;

	private List m_oids;

	private String m_uniqueKeyErrorMessage;

	private Component m_parent;

	private boolean m_customizedDialog;

	DefaultCRUDEditor(VOEditionComponent editionComponent, Component parent)
	{
		m_parent = parent;
		m_editionComponent = editionComponent;
	}

	/**
	 * This constructor handles the customized behavior required on the click of
	 * Valider button of the Dialog box. Constructor Added for ME_1213.
	 * 
	 * @param editionComponent
	 * @param parent
	 * @param customizedDialog
	 */
	DefaultCRUDEditor(VOEditionComponent editionComponent, Component parent, boolean customizedDialog)
	{
		m_parent = parent;
		m_editionComponent = editionComponent;
		m_customizedDialog = customizedDialog;
	}

	public ValueObject doAdd()
	{
		m_editionComponent.setEditedValueObject(null);
		if (showAsEditionDialog(m_editionComponent, true, m_parent, false))
		{
			ValueObject vo = m_editionComponent.getEditedValueObject();
			if (vo == null)
			{
				return null;
			}
			if (vo instanceof Identifiable
			        || (vo instanceof GraphObject && ((GraphObject) vo).getWrappedObject() instanceof Identifiable))
			{
				Identifiable pr;
				if (vo instanceof Identifiable)
				{
					pr = (Identifiable) vo;
					populateOidList(true);
				} else
				{
					pr = (Identifiable) ((GraphObject) vo).getWrappedObject();
					populateOidList(false);
				}
				// valide l'unicité de la clef fonctionnelle
				if (m_oids.contains(pr.getObjectId()))
				{
					if (m_uniqueKeyErrorMessage == null)
					{
						throw new UserShowableException("La clef fonctionnelle de l'objet "
						        + ReflectionHelper.getClassShortName(vo.getClass()) + " doit etre unique.");
					} else
					{
						throw new UserShowableException(m_uniqueKeyErrorMessage);
					}
				}
			}
			return vo;
		} else
		{
			return null;
		}
	}

	public boolean doRemove(ValueObject vo)
	{
		return (WindowManager.showConfirmationMessage("Etes vous sûr(e) de vouloir supprimer la ligne sélectionnée?",
		        null));
	}

	public boolean doUpdate(ValueObject vo)
	{
		m_editionComponent.setEditedValueObject((ValueObject) vo.clone());
		if (showAsEditionDialog(m_editionComponent, false, m_parent, false))
		{
			ValueObject clone = m_editionComponent.getEditedValueObject();
			if (vo == null)
			{
				return false;
			}
			if (vo instanceof Identifiable
			        || (vo instanceof GraphObject && ((GraphObject) vo).getWrappedObject() instanceof Identifiable))
			{
				Identifiable pr;
				Identifiable prClone;
				if (vo instanceof Identifiable)
				{
					pr = (Identifiable) vo;
					prClone = (Identifiable) clone;
					populateOidList(true);
				} else
				{
					pr = (Identifiable) ((GraphObject) vo).getWrappedObject();
					prClone = (Identifiable) ((GraphObject) clone).getWrappedObject();
					populateOidList(false);
				}

				// valide l'unicité de la clef fonctionnelle
				m_oids.remove(pr.getObjectId());
				if (m_oids.contains(prClone.getObjectId()))
				{
					if (m_uniqueKeyErrorMessage == null)
					{
						throw new UserShowableException("La clef fonctionnelle de l'objet "
						        + ReflectionHelper.getClassShortName(vo.getClass()) + " doit etre unique.");
					} else
					{
						throw new UserShowableException(m_uniqueKeyErrorMessage);
					}
				}
			}
			vo.copyFrom(clone);
			return true;
		} else
		{
			return false;
		}
	}

	public void doDisplay(ValueObject vo)
	{
		m_editionComponent.setEditedValueObject((ValueObject) vo.clone());
		// removed the commented try catch block
		showAsEditionDialog(m_editionComponent, false, m_parent, true);
	}

	/**
	 * Le model est utilisé pour vérifier l'unicité des IDs des objets pour les
	 * instances de PersistentRelation.
	 */
	void setValueObjectListModel(ValueObjectListModel model)
	{
		m_model = model;
	}

	/**
	 * Remplit la liste des ObjectIds
	 * 
	 * @param forPersistentRelation
	 *            specifie si on travaille sur des Identifiables ou sur des
	 *            graphs
	 */
	private void populateOidList(boolean forIdentifiable)
	{
		if (m_oids == null)
		{
			m_oids = new ArrayList();
		}
		m_oids.clear();
		Iterator iter = m_model.getValueObjectList().iterator();
		Identifiable pr;
		while (iter.hasNext())
		{
			if (forIdentifiable)
			{
				pr = (Identifiable) iter.next();
			} else
			{
				pr = (Identifiable) ((GraphObject) iter.next()).getWrappedObject();
			}
			m_oids.add(pr.getObjectId());
		}
	}

	public void setUniqueCompositeKeyErrorMessage(String message)
	{
		m_uniqueKeyErrorMessage = message;
	}

	private boolean showAsEditionDialog(VOEditionComponent editionComponent, boolean newObject, Component parent,
	        boolean isConsultation)
	{
		Window parentWindow = WindowManager.getOwningWindow(parent);
		VOEditionDialog editionDialog;
		if (parentWindow == null)
		{
			throw new TechnicalException("Edition dialog can not be opened due to a null parent window");
		} else if (parentWindow instanceof Frame)
		{
			// ME_1213_IMPLEMENTATION_STARTS...
			/*
			 * Checking if we are dealing with the customized behavior of the
			 * action buttons of the edition component.
			 */
			if (m_customizedDialog)
			{
				/*
				 * Changing the edition components properties as per CRUD table
				 * action.
				 */
				changePanelProperties(editionComponent, isConsultation, newObject);
				// Instanciating the validation panel.
				ValidationDialog validationDialog = new ValidationDialog((Frame) parentWindow,
				        (ValidationPanel) editionComponent);

				if (isConsultation)
				{
					validationDialog.setValidationState(false);
				}
				setTitle(editionComponent);
				validationDialog.pack();
				validationDialog.setModal(true);
				if (parent != null)
				{
					validationDialog.setLocationRelativeTo(parent);
				}
				validationDialog.centerToParent();

				validationDialog.show();
				return validationDialog.isValidated();
			} else
			{
				editionDialog = new VOEditionDialog((Frame) parentWindow, editionComponent, newObject, isConsultation);
			}
		} else
		{
			if (m_customizedDialog)
			{
				changePanelProperties(editionComponent, isConsultation, newObject);
				ValidationDialog validationDialog = new ValidationDialog((Dialog) parentWindow,
				        (ValidationPanel) editionComponent);
				if (isConsultation)
				{
					validationDialog.setValidationState(false);
				}
				setTitle(editionComponent);
				validationDialog.pack();
				validationDialog.setModal(true);
				if (parent != null)
				{
					validationDialog.setLocationRelativeTo(parent);
				}
				validationDialog.centerToParent();

				validationDialog.show();
				return validationDialog.isValidated();
			} else
			{
				editionDialog = new VOEditionDialog((Dialog) parentWindow, editionComponent, newObject, isConsultation);
			}
			// ME_1213_IMPLEMENTATION_ENDS.
		}
		editionDialog.pack();
		editionDialog.setModal(true);
		if (parent != null)
		{
			editionDialog.setLocationRelativeTo(parent);
		}
		editionDialog.centerToParent();

		editionDialog.show();
		return editionDialog.isValidated();
	}

	/**
	 * This method changed the panel's properties like panel name and state of
	 * the components of the panel. Added for ME_1213.
	 * 
	 * @param editionComponent
	 * @param isConsultation
	 * @param newObject
	 */
	private void changePanelProperties(VOEditionComponent editionComponent, boolean isConsultation, boolean newObject)
	{
		if (isConsultation)
		{
			editionComponent.getDisplayPanel();
		} else if (newObject)
		{
			editionComponent.getAddPanel();
		} else
		{
			editionComponent.getEditionPanel();
		}
	}

	/**
	 * Setting the title of the currently operated panel.
	 * 
	 * @param editionComponent
	 */
	private void setTitle(VOEditionComponent editionComponent)
	{
		/*
		 * Getting the validation panel to set its title.
		 */
		ValidationPanel valPanel = (ValidationPanel) editionComponent;
		ZPanel viewPanel = valPanel.getPanel();// getting current panel.
		viewPanel.setTitle(viewPanel.getName());// Setting the title.
	}

	/**
	 * used to clean the refrence #8201 -ashisht
	 */
	public void cleanReferences()
	{
		m_editionComponent = null;
		m_model = null;
		m_oids = null;
		m_uniqueKeyErrorMessage = null;
		m_parent = null;
	}
}