package com.rennover.client.framework.widget.table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import com.rennover.client.framework.print.PrintToolbox;
import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.base.ZScrollPane;
import com.rennover.client.framework.widget.icon.IconFactory;
import com.rennover.client.framework.widget.panel.ButtonPanel;
import com.rennover.hotel.common.valueobject.ValueObject;

public class CRUDTable extends ZPanel
{
	private CRUDTableController m_controller;
	
	private ValueObjectListModel m_model;

	private TableRowModel m_rowModel;

	private ValueObjectListTable m_table;

	private ButtonPanel m_buttonPanel;

	private Action m_printAction = new PrintAction();

	// #8201 -ashisht added m_CRUDEditor
	private CRUDEditor m_CRUDEditor;

	// Modified Constructor for Defect 7825 - To hide Modifier Button
	public CRUDTable(CRUDEditor editor, ValueObjectListModel model, TableRowModel rowModel, boolean useConsulterButton,
	        boolean useModifierButton)
	{
		m_controller = new CRUDTableController(m_CRUDEditor = editor, model, useConsulterButton, useModifierButton);

		// a remonter dans TablePanel quand CRUDTable heritera de TablePanel
		m_table = new ValueObjectListTable(model, rowModel);

		m_controller.setTable(m_table);
		m_model = model;
		m_rowModel = rowModel;
		m_buttonPanel = new ButtonPanel(ButtonPanel.VERTICAL);
		Iterator buttonActions = m_controller.getButtonActionList().iterator();
		while (buttonActions.hasNext())
		{
			Action action = (Action) buttonActions.next();
			m_buttonPanel.addButtonAction(action, true);
		}
		compose();
	}
	
	// Modified Constructor for Defect 19874 (related to ME 1870 Rib changes) - To hide Modifier Button
	public CRUDTable(CRUDEditor editor, ValueObjectListModel model, TableRowModel rowModel, boolean useConsulterButton,
	        boolean useModifierButton, boolean ribReferenced)
	{
		m_controller = new RibCRUDTableController(m_CRUDEditor = editor, model, useConsulterButton, useModifierButton);

		// a remonter dans TablePanel quand CRUDTable heritera de TablePanel
		m_table = new ValueObjectListTable(model, rowModel);

		m_controller.setTable(m_table);
		m_model = model;
		m_rowModel = rowModel;
		m_buttonPanel = new ButtonPanel(ButtonPanel.VERTICAL);
		Iterator buttonActions = m_controller.getButtonActionList().iterator();
		while (buttonActions.hasNext())
		{
			Action action = (Action) buttonActions.next();
			m_buttonPanel.addButtonAction(action, true);
		}
		compose();
	}

	public CRUDTable(CRUDEditor editor, ValueObjectListModel model, Class valueObjectClass, String[] columnNames,
	        String[] propertyNames)
	{
		this(editor, model, new PropertyTableRowModel(valueObjectClass, columnNames, propertyNames), false, true);
	}

	public CRUDTable(VOEditionComponent editionComponent, ValueObjectListModel model, Class valueObjectClass,
	        String[] columnNames, String[] propertyNames, Component parent)
	{
		this(new DefaultCRUDEditor(editionComponent, parent), model, new PropertyTableRowModel(valueObjectClass,
		        columnNames, propertyNames), false, true);
	}

	public CRUDTable(VOEditionComponent editionComponent, ValueObjectListModel model, Class valueObjectClass,
	        String[] columnNames, String[] propertyNames, Component parent, boolean useConsulterButton)
	{
		this(new DefaultCRUDEditor(editionComponent, parent), model, new PropertyTableRowModel(valueObjectClass,
		        columnNames, propertyNames), useConsulterButton, true);
	}

	public CRUDTable(VOEditionComponent editionComponent, ValueObjectListModel model, Class valueObjectClass,
	        TableRowModel rowModel, Component parent)
	{
		this(new DefaultCRUDEditor(editionComponent, parent), model, rowModel, false, true);
	}

	public CRUDTable(VOEditionComponent editionComponent, ValueObjectListModel model, Class valueObjectClass,
	        TableRowModel rowModel, Component parent, boolean useConsulterButton)
	{
		this(new DefaultCRUDEditor(editionComponent, parent), model, rowModel, useConsulterButton, true);
	}

	// constructor added for defect 7825 - to hide Modifier Button
	public CRUDTable(VOEditionComponent editionComponent, ValueObjectListModel model, Class valueObjectClass,
	        String[] columnNames, String[] propertyNames, Component parent, boolean useConsulterButton,
	        boolean useModifierButton)
	{
		this(new DefaultCRUDEditor(editionComponent, parent), model, new PropertyTableRowModel(valueObjectClass,
		        columnNames, propertyNames), useConsulterButton, useModifierButton);
	}

	/**
	 * This constructor is used to customize the fonctionality of Valider button
	 * of the Dailog used to show the Reference Bank management panel. Added for
	 * ME_1213.
	 * 
	 * @param editionComponent
	 * @param model
	 * @param valueObjectClass
	 * @param rowModel
	 * @param parent
	 * @param useConsulterButton
	 * @param customizedDialog
	 */
	public CRUDTable(VOEditionComponent editionComponent, ValueObjectListModel model, Class valueObjectClass,
	        TableRowModel rowModel, Component parent, boolean useConsulterButton, boolean customizedDialog)
	{
		this(new DefaultCRUDEditor(editionComponent, parent, customizedDialog), model, rowModel, useConsulterButton,
		        true);
	}
	
	public CRUDTable(VOEditionComponent editionComponent, ValueObjectListModel model, Class valueObjectClass,
	        TableRowModel rowModel, Component parent, boolean useConsulterButton, boolean customizedDialog, boolean ribReferenced)
	{
		this(new DefaultCRUDEditor(editionComponent, parent, customizedDialog), model, rowModel, useConsulterButton,
		        true, ribReferenced);
	}
	
	// a remonter dans TablePanel idem constructeur
	public void addListSelectionListener(ListSelectionListener listener)
	{
		m_table.addListSelectionListener(listener);
	}

	public void removeListeSelectionListener(ListSelectionListener listener)
	{
		m_table.removeListSelectionListener(listener);
	}

	public ValueObject getSelectedValueObject()
	{
		return m_table.getSelectedValueObject();
	}

	public void setSelectedValueObject(ValueObject vo)
	{
		m_table.setSelectedValueObject(vo);
	}

	/**
	 * permet d'activer ou non les boutons à droites de la table. utiliser cette
	 * méthode plutot que <code>InputFieldHelper.setLocked()</code>
	 */
	public void setEnabled(boolean activate)
	{
		m_controller.setEnabled(activate);
	}

	public void setSupprimerEnabled(boolean enabled)
	{
		m_controller.setSupprimerEnabled(enabled);
	}

	public void setAjouterEnabled(boolean enabled)
	{
		m_controller.setAjouterEnabled(enabled);
	}

	public void setModifierEnabled(boolean enabled)
	{
		m_controller.setModifierEnabled(enabled);
	}

	/**
	 * Permet de lancer l'impression de la liste contenu dans la table
	 * 
	 */
	public void print()
	{
		PrintToolbox.doPrintTable(getTitle(), m_table, this);
	}

	/**
	 * Ajoute un bouton d'impression de la liste contenu dans la table
	 */
	public void addPrintButton()
	{
		m_buttonPanel.addButtonAction(getPrintAction(), false);
	}

	/**
	 * Ajoute un petit bouton d'impression de la liste contenu dans la table
	 */
	public void addSmallPrintButton()
	{
		m_buttonPanel.addSmallButtonAction(getPrintAction(), false);
	}

	private Action getPrintAction()
	{
		return m_printAction;
	}

	protected void compose()
	{
		this.setLayout(new BorderLayout());

		ZScrollPane sp = new ZScrollPane(m_table);

		this.add(sp, BorderLayout.CENTER);

		this.add(m_buttonPanel, BorderLayout.EAST);
	}

	public void setUniqueCompositeKeyErrorMessage(String message)
	{
		m_controller.setUniqueCompositeKeyErrorMessage(message);
	}

	public String getColumnName(int index)
	{
		return m_table.getColumnName(index);
	}

	public TableColumn getColumn(String name)
	{
		return m_table.getColumn(name);
	}

	/**
	 * Cette classe est une action servant à gérer l'impression du contenu de la
	 * table
	 * 
	 * @author tcaboste
	 */
	private class PrintAction extends AbstractAction
	{
		public PrintAction()
		{
			super("Imprimer...", IconFactory.getPrintIcon());
			putValue(SHORT_DESCRIPTION, "Imprimer le contenu du tableau.");
		}

		public void actionPerformed(ActionEvent e)
		{
			print();
		}
	}

	// added for defect 7941
	public void setConsultorEnabled(boolean enabled)
	{
		m_controller.setConsultorEnabled(enabled);
	}

	/**
	 * used to clean the refrence #8201 -ashisht
	 */
	public void cleanReferences()
	{
		if (null != m_controller)
		{
			m_controller.cleanReferences();
			m_controller = null;
		}

		m_model = null;
		m_rowModel = null;
		m_table = null;
		m_buttonPanel = null;
		m_printAction = null;

		if (null != m_CRUDEditor)
		{
			m_CRUDEditor.cleanReferences();
			m_CRUDEditor = null;
		}
	}
	//added for defect #19874 (related to ME 1870 Rib changes.)
	public void setRibReference(boolean enabled)
	{
		((RibCRUDTableController)m_controller).setRibReference(enabled);
	}
	
	public void setFactureReference(boolean enabled)
	{
		((RibCRUDTableController)m_controller).setFacturebReference(enabled);
	}
}
