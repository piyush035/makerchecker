package com.rennover.client.framework.widget.radiobutton;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

import com.rennover.client.framework.widget.base.ZRadioButton;
import com.rennover.client.framework.widget.panel.ComponentPanel;

/**
 * Un panneau contenant des radio boutons et qui peut être affiché verticalement
 * (par défaut) ou horizontalement. Ces radio boutons sont spécifiés par la
 * méthode {@link #addRadioButton(String, String)}.
 */
public class RadioButtonPanel extends ComponentPanel implements FocusListener
{
	public static final int HORIZONTAL = ComponentPanel.HORIZONTAL;

	public static final int VERTICAL = ComponentPanel.VERTICAL;

	private ButtonGroup m_buttonGroup;

	private Map m_buttonModelMap;

	private Map m_enumMap;

	private ActiveRadioButtonListener m_radioButtonListener;

	private Action m_selectionAction;

	private List m_objectList = new ArrayList();

	private ButtonModel m_nullValueButtonModel = null;

	public RadioButtonPanel()
	{
		this(VERTICAL);
	}

	public RadioButtonPanel(List objectList)
	{
		this(VERTICAL, objectList);
	}

	public RadioButtonPanel(int orientation, List objectList)
	{
		this(orientation);
		setObjectList(objectList);
	}

	/**
	 * Crée un panneau de radio boutons avec une orientation spécifiée.
	 * 
	 * @param orientation
	 *            soit {@link #HORIZONTAL}, soit {@link #VERTICAL}
	 */
	public RadioButtonPanel(int orientation)
	{
		super(orientation);
		m_buttonGroup = new RadioButtonGroup()
		{
			public void setSelected(ButtonModel m, boolean b)
			{
				if (m != null)
				{
					boolean valueChanged = m.isSelected() != b;
					super.setSelected(m, b);

					if (valueChanged)
					{
						RadioButtonPanel.this.fireSelectionAction();
					}
				}
			}
		};

		m_buttonModelMap = new HashMap();
		m_enumMap = new HashMap();
	}

	public void setEnabled(boolean enabled)
	{
		Enumeration enum1 = m_buttonGroup.getElements();

		while (enum1.hasMoreElements())
		{
			AbstractButton button = (AbstractButton) enum1.nextElement();
			button.setEnabled(enabled);
		}

		super.setEnabled(enabled);
	}

	/**
	 * Ajoute un bouton radio avec le texte spécifié.
	 * 
	 * @param enum
	 *            un code qui permettra de distinguer le bouton sélectionné par
	 *            l'utilisateur
	 * @param text
	 *            le libellé qui s'affichera à côté du bouton radio
	 * 
	 */
	public ZRadioButton addRadioButton(String text, Object enum1)
	{
		ZRadioButton button = new ZRadioButton();
		button.setText(text);
		add(button);
		m_buttonGroup.add(button);
		m_buttonModelMap.put(enum1, button.getModel());
		m_enumMap.put(button.getModel(), enum1);
		m_objectList.add(enum1);
		button.addFocusListener(this);

		return button;
	}

	public void focusGained(FocusEvent e)
	{
		processFocusEvent(e);
	}

	public void focusLost(FocusEvent e)
	{
		processFocusEvent(e);
	}

	public void addRadioButtonForNull(String text)
	{
		ZRadioButton button = addRadioButton(text, null);
		m_nullValueButtonModel = button.getModel();

		if (getSelectedItem() == null)
		{
			m_nullValueButtonModel.setSelected(true);
		}
	}

	public void deSelectNullValueRadioButton()
	{
		m_nullValueButtonModel.setSelected(false);
	}

	public Object getSelectedItem()
	{
		ButtonModel seletedButtonModel = m_buttonGroup.getSelection();

		if (seletedButtonModel == null)
		{
			return null;
		}

		return m_enumMap.get(seletedButtonModel);
	}

	public void setSelectedItem(Object enum1)
	{
		if (enum1 != null)
		{
			ButtonModel buttonModel = (ButtonModel) m_buttonModelMap.get(enum1);
			m_buttonGroup.setSelected(buttonModel, true);
		} else
		{
			if (m_nullValueButtonModel != null)
			{
				m_buttonGroup.setSelected(m_nullValueButtonModel, true);
			} else
			{
				ButtonModel selectedButtonModel = m_buttonGroup.getSelection();

				if (selectedButtonModel != null)
				{
					selectedButtonModel.setSelected(false);
				}
			}
		}
	}

	public int getItemCount()
	{
		return m_objectList.size();
	}

	public Object getItemAt(int i)
	{
		return m_objectList.get(i);
	}

	/**
	 * @deprecate use setSelectionAction(Action)
	 * 
	 */
	public synchronized void addRadioButtonListener(ActiveRadioButtonListener listener)
	{
		m_radioButtonListener = listener;
	}

	/**
	 * Method setObjectList.
	 * 
	 * @param objectList
	 */
	public void setObjectList(List objectList)
	{
		if (objectList != null)
		{
			m_objectList = new ArrayList();

			for (int i = 0; i < objectList.size(); i++)
			{
				Object object = objectList.get(i);
				addRadioButton(object.toString(), object);
			}
		}
		// removed the commented else part
	}

	/**
	 * Returns the selectionAction.
	 * 
	 * @return Action
	 */
	public Action getSelectionAction()
	{
		return m_selectionAction;
	}

	/**
	 * Sets the selectionAction.
	 * 
	 * @param selectionAction
	 *            The selectionAction to set
	 */
	public void setSelectionAction(Action selectionAction)
	{
		m_selectionAction = selectionAction;
	}

	/**
	 * Method fireSelectionAction.
	 */
	private void fireSelectionAction()
	{
		if (m_radioButtonListener != null)
		{
			m_radioButtonListener.dataChanged();
		}

		if (m_selectionAction != null)
		{
			ActionEvent actEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null);
			m_selectionAction.actionPerformed(actEvent);
		}
	}

	/**
	 * Ajoute un bouton radio avec le texte spécifié. - for ECR-S-102.04
	 * 
	 * @param enum
	 *            un code qui permettra de distinguer le bouton sélectionné par
	 *            l'utilisateur
	 * @param text
	 *            le libellé qui s'affichera à côté du bouton radio
	 * 
	 */
	public ZRadioButton addRadioButtonSouth(String text, Object enum1)
	{
		ZRadioButton button = new ZRadioButton();
		button.setText(text);
		m_buttonGroup.add(button);
		m_buttonModelMap.put(enum1, button.getModel());
		m_enumMap.put(button.getModel(), enum1);
		m_objectList.add(enum1);
		button.addFocusListener(this);

		return button;
	}
}
