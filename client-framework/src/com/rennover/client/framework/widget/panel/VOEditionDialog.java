package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.util.Iterator;

import com.rennover.client.framework.valueobject.instanthelp.InstantHelpEvent;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpListener;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpManager;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpPanel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.table.VOEditionComponent;

/**
 * Cette classe est utilisée pour afficher les boite d'édition de Value Object.
 * La boite de dialogie contient en bas deux boutons (valider/annuler) et un
 * champ d'aide contextuelle.
 * 
 * @author gtardif
 */
public final class VOEditionDialog extends ValidationDialog implements InstantHelpListener
{
	private InstantHelpManager m_instantHelpListener;

	public VOEditionDialog(Dialog owner, VOEditionComponent editionComponent, boolean newObject, boolean isConsultation)
	{
		super(owner);
		init(editionComponent, newObject, isConsultation);
	}

	public VOEditionDialog(Frame owner, VOEditionComponent editionComponent, boolean newObject, boolean isConsultation)
	{
		super(owner);
		init(editionComponent, newObject, isConsultation);
	}

	private void init(VOEditionComponent editionComponent, boolean newObject, boolean isConsultation)
	{
		ZPanel centerPanel;
		if (isConsultation)
		{
			centerPanel = editionComponent.getDisplayPanel();
		} else if (newObject)
		{
			centerPanel = editionComponent.getAddPanel();
			getActValider().setEnabled(false);
		} else
		{
			centerPanel = editionComponent.getEditionPanel();
		}
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		setTitle(centerPanel.getName());
		if (editionComponent.getVomListForValidation() != null)
		{
			Iterator voms = editionComponent.getVomListForValidation().iterator();
			m_instantHelpListener.addInstantHelpListener(this);
			while (voms.hasNext())
			{
				ValueObjectModel vom = (ValueObjectModel) voms.next();
				m_instantHelpListener.addValueObjectModel(vom);
				vom.fireValueObjectChanged(); // rafraichit l'état valide/non
												// valide
			}
		}
	}

	protected void compose()
	{
		ButtonPanel pnlButtons = new ButtonPanel(true);
		pnlButtons.addButtonAction(getActValider());
		pnlButtons.addButtonAction(getActAnnuler());
		ZPanel southPanel = new ZPanel();
		southPanel.setLayout(new BorderLayout());
		southPanel.add(pnlButtons, BorderLayout.NORTH);
		InstantHelpPanel helpPanel = new InstantHelpPanel();
		m_instantHelpListener = new InstantHelpManager(helpPanel);
		southPanel.add(helpPanel, BorderLayout.SOUTH);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		contentPane.add(southPanel, BorderLayout.SOUTH);
	}

	public void validityChanged(InstantHelpEvent e)
	{
		getActValider().setEnabled(e.isValid());
	}

	public void valueChanged(InstantHelpEvent e)
	{
	}
}