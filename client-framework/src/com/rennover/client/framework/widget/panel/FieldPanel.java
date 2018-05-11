package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.rennover.client.framework.widget.base.ZButton;
import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.base.ZSeparator;
import com.rennover.client.framework.widget.button.SmallButton;
import com.rennover.client.framework.widget.field.InputField;
import com.rennover.client.framework.widget.field.InputFieldHelper;
import com.rennover.client.framework.widget.layout.GridData;
import com.rennover.client.framework.widget.layout.SWTGridLayout;
import com.rennover.hotel.common.exception.TechnicalException;

/**
 * @author tcaboste
 * 
 * Ce panneau gère des composants champs (TextField, ComboBox, ChekBox, ...) en
 * leur associant un composant label Les champs sont présentés dans un
 * gridbaglayout. Les champs sont à droite de leur label
 *  [ label : ] [ champ ]
 * 
 * exemple :
 *  [ Nom : ] [ champ ] [ Prénom : ] [ champ ] [ Salaire : ] [ champ ] [ Ville : ] [
 * champ ]
 * 
 * @audit dattias 31/12/02
 * @change renommer les variables nommées component quand ce sont des fields.
 * @change supprimer l'indexation par nom de label car c'est inutile.
 */
public class FieldPanel extends ZPanel
{
	public static final int VERTICAL = GridBagConstraints.VERTICAL;

	public static final int HORIZONTAL = GridBagConstraints.HORIZONTAL;

	public static final int BOTH = GridBagConstraints.BOTH;

	private ZPanel m_internalFieldPanel;

	// private Dimension defaultLabelDim = new Dimension(50, 22);
	private Dimension m_defaultSeparatorDim = new Dimension(50, 9);

	private ButtonPanel m_currentButtonPanel = null;

	private Insets m_defaultInsets = new Insets(2, 4, 2, 4);

	private int m_minLabelSize = -1;

	private List m_labelList = new ArrayList();

	// Code Added For Defect #2961 by Narayan
	private ZLabel m_label;

	public FieldPanel()
	{
		this(null, true, HORIZONTAL);
	}

	public FieldPanel(boolean border)
	{
		this(null, border, HORIZONTAL);
	}

	public FieldPanel(String title)
	{
		this(title, true, HORIZONTAL);
	}

	public FieldPanel(String title, boolean border)
	{
		this(title, border, HORIZONTAL);
	}

	public FieldPanel(String title, int fill)
	{
		this(title, true, fill);
	}

	public FieldPanel(String title, boolean border, int fill)
	{
		setLayout(new BorderLayout());

		ZPanel m_titlePanel = new ZPanel(new BorderLayout());

		m_internalFieldPanel = new ZPanel(new SWTGridLayout(3, false));

		m_titlePanel.add(m_internalFieldPanel, BorderLayout.NORTH);

		// --- set Title and Border
		if (title != null)
		{
			m_titlePanel.setBorder(new TitledBorder(title));
		}

		if (border)
		{
			this.setBorder(new EmptyBorder(m_defaultInsets));
		}

		boolean fillVertical = (fill == VERTICAL) || (fill == BOTH);

		if (fillVertical)
		{
			super.add(m_titlePanel, BorderLayout.CENTER);
		} else
		{
			super.add(m_titlePanel, BorderLayout.NORTH);
		}
	}

	/**
	 * 
	 * Ajoute un nouveau champ au panneau sans lui associé de label et en
	 * prenant toute la largeur du panneau
	 * 
	 * @param m_component
	 *            composant champ à placer
	 * @change renommer en addInputField
	 */
	public void addField(InputField field)
	{
		addField(null, field);
	}

	/**
	 * 
	 * Ajoute un nouveau champ au panneau en lui associant un label
	 * 
	 * @param labelname
	 *            nom du champs présenté par le label
	 * @param m_component
	 *            composant champ à placer à côté du label
	 * @change renommer en addInputField
	 */
	public void addField(String labelName, InputField inputField)
	{
		addField(labelName, inputField, (String) null);
	}

	/**
	 * Ajoute un nouveau champ au panneau en lui associant un label
	 * 
	 * @param labelname
	 *            nom du champs présenté par le label
	 * @param m_component
	 *            composant champ à placer à côté du label
	 * @change renommer en addInputField
	 */
	public void addField(String labelName, InputField inputField, String units)
	{
		addComponent(labelName, (Component) inputField, units, '\0', true);
	}

	/**
	 * 
	 * @param labelName
	 * @param inputField
	 * @param units
	 * @param mnemonic
	 */
	public void addField(String labelName, InputField inputField, String units, char mnemonic)
	{
		addComponent(labelName, (Component) inputField, units, mnemonic, true);
	}

	/**
	 * 
	 */
	public void addField(String labelName, InputField field, Action action)
	{
		addComponent(labelName, (Component) field, action);
	}

	/**
	 * @deprecated use LockHelper.setLocked(ZPanel panel, boolean locked)
	 *             Attention: locked = !activate
	 * 
	 */
	public static void setEnabled(ZPanel panel, boolean activate)
	{
		LockHelper.setLocked(panel, !activate);
	}

	/**
	 * @deprecated use addComponent(Component) Ajoute un panneau verticalement à
	 *             la suite des composants déjà insérés
	 * @param panel
	 */
	public void addPanel(ZPanel panel)
	{
		addComponent(panel);
	}

	/**
	 * Ajoute un bouton portant une action avec une barre de boutons si
	 * necessaire
	 * 
	 * @param m_action
	 *            action associée au bouton
	 */
	public ZButton addButtonAction(String title)
	{
		if (m_currentButtonPanel == null)
		{
			ButtonPanel newPanel = new ButtonPanel();
			addComponent(newPanel);
			m_currentButtonPanel = newPanel;
		}

		return m_currentButtonPanel.addButtonAction(title);
	}

	/**
	 * Ajoute un ressort qui va plaquer les premiers boutons sur le côté gauche
	 * du panneau
	 */
	public void addButtonSpring()
	{
		m_currentButtonPanel.addSpring();
	}

	/**
	 * Ajoute un bouton portant une action avec une barre de boutons si
	 * necessaire
	 * 
	 * @param action
	 *            action associée au bouton
	 */
	public ZButton addButtonAction(Action action)
	{
		if (m_currentButtonPanel == null)
		{
			ButtonPanel newPanel = new ButtonPanel();
			addComponent(newPanel);
			m_currentButtonPanel = newPanel;
		}

		return m_currentButtonPanel.addButtonAction(action);
	}

	/**
	 * 
	 * @param action
	 * @param lockable
	 * @return
	 */
	public ZButton addButtonAction(Action action, boolean lockable)
	{
		if (m_currentButtonPanel == null)
		{
			ButtonPanel newPanel = new ButtonPanel();
			addComponent(newPanel);
			m_currentButtonPanel = newPanel;
		}

		ZButton button = m_currentButtonPanel.addButtonAction(action);
		InputFieldHelper.setLockable(button, lockable);
		return button;
	}

	public void addSpring()
	{
		if (m_currentButtonPanel == null)
		{
			ButtonPanel newPanel = new ButtonPanel();
			addComponent(newPanel);
			m_currentButtonPanel = newPanel;
		}

		m_currentButtonPanel.addSpring();
	}

	public void addLockableComponent(Component component, boolean lockable)
	{
		addComponent(component);
		InputFieldHelper.setLockable(component, lockable);
	}

	public void addLockableComponent(String labelName, Component component, boolean lockable)
	{
		addComponent(labelName, component);
		InputFieldHelper.setLockable(component, lockable);
	}

	public void addLockableComponent(String labelName, Component component, Action action, boolean lockable)
	{
		addLockableComponent(labelName, component, action, '\0', lockable);
	}

	public void addLockableComponent(String labelName, Component component, Action action, char mnemonic,
	        boolean lockable)
	{
		if (component != null)
		{
			InputFieldHelper.setLockable(component, lockable);
		}

		ActionPanel actPanel = new ActionPanel(component, action);
		InputFieldHelper.setLockable(actPanel, lockable);

		ZLabel associatedLabel = addComponent(labelName, actPanel, mnemonic);
		if (component instanceof InputField)
		{
			InputField inputField = (InputField) component;
			if (labelName != null && inputField != null)
			{
				// Association entre le composant et son label
				inputField.setAssociatedLabel(associatedLabel);
			}
		}
	}

	public void addComponent(String labelName, Component component, Action action)
	{
		addComponent(labelName, component, action, '\0', true);
	}

	public void addComponent(String labelName, Component component, Action action, boolean fill)
	{
		addComponent(labelName, component, action, '\0', fill);
	}

	public void addComponent(String labelName, Component component, Action action, char mnemonic)
	{
		addComponent(labelName, component, action, mnemonic, true);
	}

	public void addComponent(String labelName, Component component, Action action, char mnemonic, boolean fill)
	{
		ActionPanel actPanel = new ActionPanel(component, action);

		ZLabel associatedLabel = addComponent(labelName, actPanel, (String) null, mnemonic, fill);
		if (component instanceof InputField)
		{
			InputField inputField = (InputField) component;
			if (labelName != null && inputField != null)
			{
				// Association entre le composant et son label
				inputField.setAssociatedLabel(associatedLabel);
			}
		}
	}

	// ------------------------------------------

	/**
	 * @deprecated use addComponent
	 */
	public Component add(Component component)
	{
		throw new TechnicalException(
		        "Erreur d'utilisation du Framework : \n la méthode FieldPanel.add(component) ne doit pas être utilisée.");
	}

	/**
	 * @deprecated use addComponent
	 */
	public void add(Component component, Object gb)
	{
		throw new TechnicalException(
		        "Erreur d'utilisation du Framework : \n la méthode FieldPanel.add(component, constraints) ne doit pas être utilisée.");
	}

	public void addSeparator()
	{
		addSeparator(false);
	}

	public void addSeparator(boolean visible)
	{
		JComponent separator;

		if (visible)
		{
			separator = new ZSeparator();
		} else
		{
			separator = new ZPanel();
		}

		separator.setMinimumSize(m_defaultSeparatorDim);
		separator.setMaximumSize(m_defaultSeparatorDim);
		separator.setPreferredSize(m_defaultSeparatorDim);
		addComponent(separator);
	}

	public void addComponent(Component component)
	{
		m_currentButtonPanel = null; // ré-initialise la gestion des boutons

		GridData gridData = new GridData();
		gridData.m_horizontalAlignment = GridData.FILL;
		gridData.m_horizontalSpan = 3;
		gridData.m_grabExcessHorizontalSpace = true;
		m_internalFieldPanel.add(component, gridData);
	}

	public void addComponent(String labelName, Component component, String units, char mnemonic, String labelName2,
	        Component component2, String units2, char mnemonic2)
	{
		addComponent(labelName, component, -1, units, mnemonic, null, labelName2, component2, -1, units2, mnemonic2,
		        null, true, true);
	}

	public void addComponent(String labelName, Component component, String units, char mnemonic, String labelName2,
	        Component component2, String units2, char mnemonic2, boolean fill)
	{
		addComponent(labelName, component, -1, units, mnemonic, null, labelName2, component2, -1, units2, mnemonic2,
		        null, fill, true);
	}

	//constructor added for ME-2471
	public void addComponent(String labelName, Component component, String units, char mnemonic, String labelName2,
	        Component component2, String units2, char mnemonic2,  String labelName3,
	        Component component3, String units3, char mnemonic3, boolean fill)
	{
		addComponent(labelName, component, -1, units, mnemonic, null, labelName2, component2, -1, units2, mnemonic2,
		        null, labelName3, component3, -1, units3, mnemonic3,null,fill, true);
	}

	
	public void addComponent(String labelName, Component component, int minSize, String units, char mnemonic,
	        String labelName2, Component component2, String units2, char mnemonic2)
	{
		addComponent(labelName, component, minSize, units, mnemonic, null, labelName2, component2, -1, units2,
		        mnemonic2, null, true, true);
	}

	public void addComponent(String labelName, Component component, boolean useMinSize, String units, char mnemonic,
	        String labelName2, Component component2, String units2, char mnemonic2)
	{
		int minSize = useMinSize ? component.getPreferredSize().width : -1;
		addComponent(labelName, component, minSize, units, mnemonic, null, labelName2, component2, -1, units2,
		        mnemonic2, null, true, true);
	}

	public void addLockableComponent(String labelName, Component component, String units, char mnemonic,
	        String labelName2, Component component2, String units2, char mnemonic2)
	{
		addComponent(labelName, component, -1, units, mnemonic, null, labelName2, component2, -1, units2, mnemonic2,
		        null, true, true);
	}

	public void addLockableComponent(String labelName, Component component, String units, char mnemonic, Action action,
	        String labelName2, Component component2, String units2, char mnemonic2, Action action2)
	{
		addComponent(labelName, component, -1, units, mnemonic, action, labelName2, component2, -1, units2, mnemonic2,
		        action2, true, true);
	}

	public void addComponent(String labelName, Component component, String units, char mnemonic, Action action,
	        String labelName2, Component component2, String units2, char mnemonic2, Action action2)
	{
		addComponent(labelName, component, -1, units, mnemonic, action, labelName2, component2, -1, units2, mnemonic2,
		        action2, true, true);
	}

	public void addComponent(String labelName, Component component, String units, char mnemonic, Action action,
	        String labelName2, Component component2, String units2, char mnemonic2, Action action2, boolean lockable)
	{
		addComponent(labelName, component, -1, units, mnemonic, action, labelName2, component2, -1, units2, mnemonic2,
		        action2, true, lockable);
	}

	public void addComponent(String labelName, Component component, boolean useMinSize, String units, char mnemonic,
	        Action action, String labelName2, Component component2, String units2, char mnemonic2, Action action2,
	        boolean lockable)
	{
		int minSize = useMinSize ? component.getPreferredSize().width : -1;
		addComponent(labelName, component, minSize, units, mnemonic, action, labelName2, component2, -1, units2,
		        mnemonic2, action2, true, lockable);
	}


	public void addComponent(String labelName, Component component, int minSize, String units, char mnemonic,
	        Action action, String labelName2, Component component2, int minSize2, String units2, char mnemonic2,
	        Action action2, boolean lockable)
	{
		addComponent(labelName, component, minSize, units, mnemonic, action, labelName2, component2, minSize2, units2,
		        mnemonic2, action2, true, lockable);
	}

	public void addComponent(String labelName, Component component, int minSize, String units, char mnemonic,
	        Action action, String labelName2, Component component2, int minSize2, String units2, char mnemonic2,
	        Action action2, boolean fill, boolean lockable)
	{
		int nbColumns = 0;

		if (component != null)
		{
			nbColumns++;
		}

		if (units != null)
		{
			nbColumns++;
		}

		if (action != null)
		{
			nbColumns++;
		}

		if (labelName2 != null)
		{
			nbColumns++;
		}

		if (component2 != null)
		{
			nbColumns++;
		}

		if (units2 != null)
		{
			nbColumns++;
		}

		if (action2 != null)
		{
			nbColumns++;
		}

		// Création d'un sous-panneau contenant les 7 colonnes
		ZPanel panel = new ZPanel(new SWTGridLayout(nbColumns, false, false));

		// Ajout du composant
		GridData gridData;

		if (component != null)
		{
			gridData = new GridData();
			gridData.m_horizontalAlignment = GridData.FILL;
			gridData.m_verticalAlignment = GridData.BEGINNING;
			gridData.m_grabExcessHorizontalSpace = true;
			gridData.m_widthHint = minSize;
			panel.add(component, gridData);
			InputFieldHelper.setLockable(component, lockable);
		}

		if (units != null)
		{
			panel.add(new ZLabel(units), new GridData());
		}

		if (action != null)
		{
			ZButton button = new SmallButton(action);
			gridData = new GridData();
			gridData.m_verticalAlignment = GridData.BEGINNING;
			panel.add(button, gridData);
			InputFieldHelper.setLockable(button, lockable);
		}

		// Ajout du composant2
		ZLabel associatedLabel2 = null;
		if (labelName2 != null)
		{
			associatedLabel2 = new ZLabel(labelName2 + " :");
			panel.add(associatedLabel2, new GridData());
			associateLabel(component2, associatedLabel2, mnemonic2);
		}

		if (component2 != null)
		{
			gridData = new GridData();
			gridData.m_horizontalAlignment = GridData.FILL;
			gridData.m_verticalAlignment = GridData.BEGINNING;
			gridData.m_grabExcessHorizontalSpace = true;
			gridData.m_widthHint = minSize2;
			panel.add(component2, gridData);
			InputFieldHelper.setLockable(component2, lockable);
		}

		if (units2 != null)
		{
			panel.add(new ZLabel(units2), new GridData());
		}

		if (action2 != null)
		{
			ZButton button = new SmallButton(action2);
			gridData = new GridData();
			gridData.m_verticalAlignment = GridData.BEGINNING;
			panel.add(button, gridData);
			InputFieldHelper.setLockable(button, lockable);
		}

		// Ajout du panneau avec le premier label
		ZLabel associatedLabel = addComponent(labelName, panel, fill);
		// Code Added For Defect #2961 by Narayan
		setLabel(associatedLabel);
		associateLabel(component, associatedLabel, mnemonic);
	}
	
	
	//Code Added for ME-2471
	
	public void addComponent(String labelName, Component component, int minSize, String units, char mnemonic,
	        Action action, String labelName2, Component component2, int minSize2, String units2, char mnemonic2,
	        Action action2,String labelName3, Component component3, int minSize3, String units3, char mnemonic3,
	        Action action3, boolean fill, boolean lockable)
	{
		int nbColumns = 0;

		if (component != null)
		{
			nbColumns++;
		}

		if (units != null)
		{
			nbColumns++;
		}

		if (action != null)
		{
			nbColumns++;
		}

		if (labelName2 != null)
		{
			nbColumns++;
		}

		if (component2 != null)
		{
			nbColumns++;
		}

		if (units2 != null)
		{
			nbColumns++;
		}

		if (action2 != null)
		{
			nbColumns++;
		}

		if (labelName3 != null)
		{
			nbColumns++;
		}

		if (component3 != null)
		{
			nbColumns++;
		}

		if (units3 != null)
		{
			nbColumns++;
		}

		if (action3 != null)
		{
			nbColumns++;
		}
	
		// Création d'un sous-panneau contenant les 7 colonnes
		
		
		ZPanel panel = new ZPanel(new SWTGridLayout(nbColumns, false, false));

		// Ajout du composant
		GridData gridData;

		if (component != null)
		{
			gridData = new GridData();
			gridData.m_horizontalAlignment = GridData.FILL;
			gridData.m_verticalAlignment = GridData.BEGINNING;
			gridData.m_grabExcessHorizontalSpace = true;
			gridData.m_widthHint = minSize;
			panel.add(component, gridData);
			InputFieldHelper.setLockable(component, lockable);
		}

		if (units != null)
		{
			panel.add(new ZLabel(units), new GridData());
		}

		if (action != null)
		{
			ZButton button = new SmallButton(action);
			gridData = new GridData();
			gridData.m_verticalAlignment = GridData.BEGINNING;
			panel.add(button, gridData);
			InputFieldHelper.setLockable(button, lockable);
		}

		// Ajout du composant2
		ZLabel associatedLabel2 = null;
		if (labelName2 != null)
		{
			associatedLabel2 = new ZLabel(labelName2 + " :");
			panel.add(associatedLabel2, new GridData());
			associateLabel(component2, associatedLabel2, mnemonic2);
		}

		if (component2 != null)
		{
			gridData = new GridData();
			gridData.m_horizontalAlignment = GridData.FILL;
			gridData.m_verticalAlignment = GridData.BEGINNING;
			gridData.m_grabExcessHorizontalSpace = true;
			gridData.m_widthHint = minSize2;
			panel.add(component2, gridData);
			InputFieldHelper.setLockable(component2, lockable);
		}

		if (units2 != null)
		{
			panel.add(new ZLabel(units2), new GridData());
		}

		if (action2 != null)
		{
			ZButton button = new SmallButton(action2);
			gridData = new GridData();
			gridData.m_verticalAlignment = GridData.BEGINNING;
			panel.add(button, gridData);
			InputFieldHelper.setLockable(button, lockable);
		}
		
		
		
		ZLabel associatedLabel3 = null;
		if (labelName3 != null)
		{
			associatedLabel3 = new ZLabel(labelName3 + " :");
			panel.add(associatedLabel3, new GridData());
			associateLabel(component3, associatedLabel3, mnemonic3);
		}

		if (component3 != null)
		{
			gridData = new GridData();
			gridData.m_horizontalAlignment = GridData.FILL;
			gridData.m_verticalAlignment = GridData.BEGINNING;
			gridData.m_grabExcessHorizontalSpace = true;
			gridData.m_widthHint = minSize3;
			panel.add(component3, gridData);
			InputFieldHelper.setLockable(component3, lockable);
		}

		if (units3 != null)
		{
			panel.add(new ZLabel(units3), new GridData());
		}

		if (action3 != null)
		{
			ZButton button = new SmallButton(action3);
			gridData = new GridData();
			gridData.m_verticalAlignment = GridData.BEGINNING;
			panel.add(button, gridData);
			InputFieldHelper.setLockable(button, lockable);
		}


		// Ajout du panneau avec le premier label
		ZLabel associatedLabel = addComponent(labelName, panel, fill);
		// Code Added For Defect #2961 by Narayan
		setLabel(associatedLabel);
		associateLabel(component, associatedLabel, mnemonic);
	}
	
	

	
	
	
	// Code Added For Defect #2961 by Narayan
	public void setLabel(ZLabel label)
	{
		this.m_label = label;

	}

	public ZLabel getLabel()
	{
		return this.m_label;
	}

	// End of Code for Defect #2961 by Narayan
	public void addComponent(FieldPanelParameter field1, FieldPanelParameter field2)
	{
		int nbColumns = 0;

		if (field1.m_component != null)
		{
			nbColumns++;
		}

		if (field1.m_units != null)
		{
			nbColumns++;
		}

		if (field1.m_action != null)
		{
			nbColumns++;
		}

		if (field2.m_labelName != null)
		{
			nbColumns++;
		}

		if (field2.m_component != null)
		{
			nbColumns++;
		}

		if (field2.m_units != null)
		{
			nbColumns++;
		}

		if (field2.m_action != null)
		{
			nbColumns++;
		}

		// Création d'un sous-panneau contenant les 7 colonnes
		ZPanel panel = new ZPanel(new SWTGridLayout(nbColumns, false, false));

		// Ajout du composant
		GridData gridData;

		if (field1.m_component != null)
		{
			gridData = new GridData();
			gridData.m_verticalAlignment = GridData.BEGINNING;
			gridData.m_horizontalAlignment = field1.m_fill ? GridData.FILL : GridData.BEGINNING;
			gridData.m_grabExcessHorizontalSpace = field1.m_fill;
			if (field1.m_minSize != null)
			{
				gridData.m_widthHint = field1.m_minSize.intValue();
			}
			panel.add(field1.m_component, gridData);
			InputFieldHelper.setLockable(field1.m_component, field1.m_lockable);
		}

		if (field1.m_units != null)
		{
			panel.add(new ZLabel(field1.m_units), new GridData());
		}

		if (field1.m_action != null)
		{
			ZButton button = new SmallButton(field1.m_action);
			gridData = new GridData();
			gridData.m_verticalAlignment = GridData.BEGINNING;
			panel.add(button, gridData);
			InputFieldHelper.setLockable(button, field1.m_lockable);
		}

		// Ajout du composant2
		ZLabel associatedLabel2 = null;
		if (field2.m_labelName != null)
		{
			associatedLabel2 = new ZLabel(field2.m_labelName + " :");
			panel.add(associatedLabel2, new GridData());
			associateLabel(field2.m_component, associatedLabel2, field2.m_mnemonic);
			associatedLabel2.setToolTipText(field2.m_toolTipText);
		}

		if (field2.m_component != null)
		{
			gridData = new GridData();
			gridData.m_verticalAlignment = GridData.BEGINNING;
			gridData.m_horizontalAlignment = field2.m_fill ? GridData.FILL : GridData.BEGINNING;
			gridData.m_grabExcessHorizontalSpace = field2.m_fill;
			if (field2.m_minSize != null)
			{
				gridData.m_widthHint = field2.m_minSize.intValue();
			}
			panel.add(field2.m_component, gridData);
			InputFieldHelper.setLockable(field2.m_component, field2.m_lockable);
		}

		if (field2.m_units != null)
		{
			panel.add(new ZLabel(field2.m_units), new GridData());
		}

		if (field2.m_action != null)
		{
			ZButton button = new SmallButton(field2.m_action);
			gridData = new GridData();
			gridData.m_verticalAlignment = GridData.BEGINNING;
			panel.add(button, gridData);
			InputFieldHelper.setLockable(button, field1.m_lockable);
		}

		// Ajout du panneau avec le premier label
		FieldPanelParameter field = new FieldPanelParameter();
		field.m_labelName = field1.m_labelName;
		field.m_toolTipText = field1.m_toolTipText;
		field.m_component = panel;
		field.m_fill = field1.m_fill || field2.m_fill;

		ZLabel associatedLabel = addComponent(field);
		associateLabel(field1.m_component, associatedLabel, field1.m_mnemonic);
	}

	private void associateLabel(Component component, ZLabel associatedLabel, char mnemonic)
	{
		if ((mnemonic != '\0') && (associatedLabel != null))
		{
			associatedLabel.setDisplayedMnemonic(mnemonic);
		}

		if (component instanceof InputField)
		{
			InputField inputField = (InputField) component;
			if ((associatedLabel != null) && (inputField != null))
			{
				// Association entre le composant et son label
				inputField.setAssociatedLabel(associatedLabel);
			}
		}
	}

	/**
	 * Ajoute un composant verticalement à la suite des composants déjà insérés
	 * 
	 * @param labelName
	 * @param component
	 */
	public ZLabel addComponent(String labelName, Component component, String units)
	{
		return addComponent(labelName, component, units, '\0', true);
	}

	public ZLabel addComponent(String labelName, Component component, String units, boolean fill)
	{
		return addComponent(labelName, component, units, '\0', fill);
	}

	public ZLabel addComponent(String labelName, Component component, String units, char mnemonic)
	{
		return addComponent(labelName, component, units, mnemonic, true);
	}

	public ZLabel addComponent(String labelName, Component component, String units, char mnemonic, boolean fill)
	{
		m_currentButtonPanel = null; // ré-initialise la gestion des boutons

		// Ajout du libellé du champ
		ZLabel associatedLabel = null;

		if (labelName != null)
		{
			if (!labelName.equals(""))
			{
				labelName = labelName + " :";
			}

			associatedLabel = new ZLabel(labelName)
			{
				public Dimension getMinimumSize()
				{
					Dimension size = super.getMinimumSize();
					size.height = 21;
					return size;
				}

				public Dimension getPreferredSize()
				{
					Dimension size = super.getPreferredSize();
					size.height = 21;
					return size;
				}
			};

			m_labelList.add(associatedLabel);

			GridData gridData = new GridData();
			if (m_minLabelSize != -1)
			{
				gridData.m_widthHint = m_minLabelSize;
			}
			gridData.m_horizontalAlignment = GridData.BEGINNING;
			gridData.m_horizontalSpan = 1;
			gridData.m_verticalAlignment = GridData.BEGINNING;
			m_internalFieldPanel.add(associatedLabel, gridData);

			// Création du Label associé au composant champ
			if (component != null)
			{
				associatedLabel.setLabelFor(component);
				if (mnemonic != '\0')
				{
					associatedLabel.setDisplayedMnemonic(mnemonic);
				}
			}
		}

		{
			GridData gridData = new GridData();

			// Ajout du composant champ
			if (labelName != null)
			{
				gridData.m_horizontalSpan = 1;
			} else
			{
				gridData.m_horizontalSpan = 2;
			}

			if (units == null)
			{
				gridData.m_horizontalSpan++;
			}

			gridData.m_horizontalAlignment = fill ? GridData.FILL : GridData.BEGINNING;
			gridData.m_grabExcessHorizontalSpace = fill;

			if (component != null)
			{
				m_internalFieldPanel.add((JComponent) component, gridData);
			} else
			{
				m_internalFieldPanel.add(new ZLabel(""), gridData);
			}
		}

		// Ajout des unités
		if (units != null)
		{
			GridData gridData = new GridData();
			gridData.m_horizontalAlignment = GridData.BEGINNING;
			m_internalFieldPanel.add(new ZLabel(units), gridData);
		}

		// Association entre composant et libellé
		if (component instanceof InputField)
		{
			InputField inputField = (InputField) component;
			if (labelName != null && inputField != null)
			{
				// Association entre le composant et son label
				inputField.setAssociatedLabel(associatedLabel);
			}
		}

		return associatedLabel;
	}

	public ZLabel addComponent(FieldPanelParameter field)
	{
		m_currentButtonPanel = null; // ré-initialise la gestion des boutons

		// Ajout du libellé du champ
		ZLabel associatedLabel = null;

		if (field.m_labelName != null)
		{
			String labelName = "";
			if (!field.m_labelName.equals(""))
			{
				labelName = field.m_labelName + " :";
			}

			associatedLabel = new ZLabel(labelName)
			{
				public Dimension getMinimumSize()
				{
					Dimension size = super.getMinimumSize();
					size.height = 21;
					return size;
				}

				public Dimension getPreferredSize()
				{
					Dimension size = super.getPreferredSize();
					size.height = 21;
					return size;
				}
			};
			associatedLabel.setToolTipText(field.m_toolTipText);
			m_labelList.add(associatedLabel);

			GridData gridData = new GridData();
			if (m_minLabelSize != -1)
			{
				gridData.m_widthHint = m_minLabelSize;
			}
			gridData.m_horizontalAlignment = GridData.BEGINNING;
			gridData.m_horizontalSpan = 1;
			gridData.m_verticalAlignment = GridData.BEGINNING;
			m_internalFieldPanel.add(associatedLabel, gridData);

			// association au composant champ
			if (field.m_component != null)
			{
				associatedLabel.setLabelFor(field.m_component);
				if (field.m_mnemonic != '\0')
				{
					associatedLabel.setDisplayedMnemonic(field.m_mnemonic);
				}
			}
		}

		int nbColumns = 0;

		if (field.m_component != null)
		{
			nbColumns++;
		}

		if (field.m_units != null)
		{
			nbColumns++;
		}

		if (field.m_action != null)
		{
			nbColumns++;
		}

		// Création d'un sous-panneau contenant les 7 colonnes
		ZPanel panel = new ZPanel(new SWTGridLayout(nbColumns, false, false));

		// Ajout du composant
		GridData gridData = new GridData();

		if (field.m_component != null)
		{
			gridData.m_horizontalAlignment = GridData.FILL;
			gridData.m_grabExcessHorizontalSpace = true;
			if (field.m_minSize != null)
			{
				gridData.m_widthHint = field.m_minSize.intValue();
			}
			panel.add(field.m_component, gridData);
			InputFieldHelper.setLockable(field.m_component, field.m_lockable);

			if (associatedLabel != null)
			{
				associateLabel(field.m_component, associatedLabel, field.m_mnemonic);
			}
		}

		if (field.m_units != null)
		{
			panel.add(new ZLabel(field.m_units), new GridData());
		}

		if (field.m_action != null)
		{
			ZButton button = new SmallButton(field.m_action);
			panel.add(button, new GridData());
			InputFieldHelper.setLockable(button, field.m_lockable);
		}

		// Ajout du panneau avec le premier label
		gridData = new GridData();
		gridData.m_horizontalAlignment = field.m_fill ? GridData.FILL : GridData.BEGINNING;
		gridData.m_grabExcessHorizontalSpace = field.m_fill;
		gridData.m_horizontalSpan = 2;
		if (associatedLabel == null)
		{
			gridData.m_horizontalSpan++;
		}
		m_internalFieldPanel.add(panel, gridData);

		return associatedLabel;
	}

	/**
	 * Ajoute un composant verticalement à la suite des composants déjà insérés
	 * 
	 * @param labelName
	 * @param component
	 */
	public ZLabel addComponent(String labelName, Component component)
	{
		return addComponent(labelName, component, (String) null, '\0', true);
	}

	public ZLabel addComponent(String labelName, Component component, boolean fill)
	{
		return addComponent(labelName, component, (String) null, '\0', fill);
	}

	public ZLabel addComponent(String labelName, Component component, char mnemonic)
	{
		return addComponent(labelName, component, (String) null, mnemonic, true);
	}

	/**
	 * Sets the defaultInsets.
	 * 
	 * @param defaultInsets
	 *            The defaultInsets to set
	 */
	public void setDefaultInsets(Insets defaultInsets)
	{
		m_defaultInsets = defaultInsets;
	}

	/**
	 * @return
	 */
	public int getMinLabelSize()
	{
		return m_minLabelSize;
	}

	public int getMinLabelWidth()
	{
		SWTGridLayout layout = (SWTGridLayout) m_internalFieldPanel.getLayout();
		layout.minimumLayoutSize(m_internalFieldPanel);
		return layout.getPixelColumnWidths()[0];
	}

	/**
	 * @param i
	 */
	public void setMinLabelSize(int width)
	{
		SWTGridLayout layout = (SWTGridLayout) m_internalFieldPanel.getLayout();
		for (int i = 0; i < m_labelList.size(); i++)
		{
			ZLabel label = (ZLabel) m_labelList.get(i);
			GridData gridData = layout.getLayoutData(label);
			gridData.m_widthHint = width;
			layout.setLayoutData(label, gridData);
		}
	}

	public static void alignLabels(FieldPanel panel1, FieldPanel panel2)
	{
		alignLabels(new FieldPanel[] { panel1, panel2 });
	}

	public static void alignLabels(FieldPanel panel1, FieldPanel panel2, FieldPanel panel3)
	{
		alignLabels(new FieldPanel[] { panel1, panel2, panel3 });
	}

	public static void alignLabels(FieldPanel[] panels)
	{
		for (int i = 0; i < panels.length; i++)
		{
			FieldPanel panel = panels[i];
			panel.setMinLabelSize(-1);
		}

		int labelMaxSize = 0;

		for (int i = 0; i < panels.length; i++)
		{
			FieldPanel panel = panels[i];
			int labelWidth = panel.getMinLabelWidth();
			labelMaxSize = Math.max(labelMaxSize, labelWidth);
		}

		for (int i = 0; i < panels.length; i++)
		{
			FieldPanel panel = panels[i];
			panel.setMinLabelSize(labelMaxSize);
		}
	}
}
