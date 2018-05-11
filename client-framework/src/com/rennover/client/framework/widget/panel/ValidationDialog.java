package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.Action;

import com.rennover.client.framework.mvc.ConstantLabels;
import com.rennover.client.framework.mvc.DefaultAction;
import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpEvent;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpListener;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpManager;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.icon.IconFactory;
import com.rennover.client.framework.window.DefaultDialog;

/**
 * Boite de dialogue contenant deux boutons pour Valider ou Annuler ce qui a été
 * fait.
 * 
 * <p>
 * Le contenu réel de la boite de dialogue et l'implémentation des actions liées
 * aux boutons doivent être définis par les classes héritant de celle-ci.
 * </p>
 */
public class ValidationDialog extends DefaultDialog implements InstantHelpListener
{
	private Action m_actValider;

	private Action m_actAnnuler;

	private boolean m_validated = false;

	private ValidationPanel m_panel = null;

	/**
	 * Instancie une boite de dialogue ouverte à partir d'une frame.
	 */
	public ValidationDialog(Frame owner)
	{
		super(owner);
		init2();
	}

	/**
	 * Instancie une boite de dialogue ouverte à partir d'une autre boite de
	 * dialogue.
	 */
	public ValidationDialog(Dialog owner)
	{
		super(owner);
		init2();
	}

	/**
	 * Instancie une boite de dialogue ouverte à partir d'une frame.
	 */
	public ValidationDialog(Frame owner, ValidationPanel panel)
	{
		super(owner);
		m_panel = panel;
		init2();
	}

	/**
	 * Instancie une boite de dialogue ouverte à partir d'une autre boite de
	 * dialogue.
	 */
	public ValidationDialog(Dialog owner, ValidationPanel panel)
	{
		super(owner);
		m_panel = panel;
		init2();
	}

	protected void init2()
	{
		compose();

		setModal(true);
		setDefaultCloseOperation(super.DO_NOTHING_ON_CLOSE);

		pack();

		if (m_panel != null)
		{
			ZPanel panel = m_panel.getPanel();

			setTitle(panel.getName());

			changeContent(panel);
		}
		pack();
		setModal(true);

		centerToParent();
	}

	protected void compose()
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.window.DefaultDialog#changeContent(com.agefospme.nsicm.client.framework.widget.base.ZPanel)
	 */
	public void changeContent(ZPanel panelView)
	{
		ZPanel panel = new ZPanel();
		panel.setLayout(new BorderLayout());

		ButtonPanel pnlButtons = new ButtonPanel(true);
		pnlButtons.addButtonAction(getActValider());
		pnlButtons.addButtonAction(getActAnnuler());

		if (panelView != null)
		{
			panel.add(panelView, BorderLayout.CENTER);
		}

		panel.add(pnlButtons, BorderLayout.SOUTH);

		getContentPane().removeAll();
		if (panelView != null)
		{
			getContentPane().add(panel, BorderLayout.CENTER);
		}
		if (panelView instanceof PanelView)
		{
			getContentPane().add(getInstantHelpView(), BorderLayout.SOUTH);
			InstantHelpManager instantHelpManager = ((PanelView) panelView).getInstantHelpManager();
			if (instantHelpManager != null)
			{
				m_actValider.setEnabled(!instantHelpManager.hasErrors());
			}
			setInstantHelpManager(instantHelpManager);
			setPanelView((PanelView) panelView);
		}
		validate();
		repaint();
	}

	public void setInstantHelpManager(InstantHelpManager instantHelpManager)
	{
		InstantHelpManager oldInstantHelpManager = getInstantHelpManager();
		if (oldInstantHelpManager != null)
		{
			oldInstantHelpManager.removeInstantHelpListener(this);
		}
		if (instantHelpManager != null)
		{
			instantHelpManager.addInstantHelpListener(this);
		}
		super.setInstantHelpManager(instantHelpManager);
	}

	protected Action getActValider()
	{
		if (null == m_actValider)
		{
			m_actValider = new DefaultAction(ConstantLabels.VALIDATE, IconFactory.getValidateIcon())
			{
				public void actionPerformed2(ActionEvent e)
				{
					doValider();
				}
			};

			m_actValider.putValue(Action.MNEMONIC_KEY, new Integer('V'));
		}
		return m_actValider;
	}

	protected Action getActAnnuler()
	{
		if (null == m_actAnnuler)
		{
			m_actAnnuler = new DefaultAction(ConstantLabels.CANCEL, IconFactory.getCancelIcon())
			{
				public void actionPerformed2(ActionEvent e)
				{
					doAnnuler();
				}
			};

			m_actAnnuler.putValue(Action.MNEMONIC_KEY, new Integer('A'));
		}
		return m_actAnnuler;
	}

	/**
	 * Instancie et assemble les différents composants graphiques de la boite de
	 * dialogue.<br>
	 * Le panel sera placé dans la boite de dialogue à l'aide de la contrainte
	 * BorderLayout.CENTER.
	 * 
	 * <p>
	 * La zone BorderLayout.SOUTH est réservée aux boutons.
	 * </p>
	 */

	/**
	 * Code à exécuter quand l'utilisateur clique sur le bouton Valider.
	 */
	protected void doValider()
	{
		if (m_panel == null || m_panel.doValidate())
		{
			m_validated = true;
			setVisible(false);
		}
	}

	/**
	 * Code à exécuter quand l'utilisateur clique sur le bouton Annuler.
	 */
	protected void doAnnuler()
	{
		if (m_panel == null || m_panel.doCancel())
		{
			m_validated = false;
			setVisible(false);
		}
	}

	/**
	 * Returns the validated.
	 * 
	 * @return boolean
	 */
	public boolean isValidated()
	{
		return m_validated;
	}

	/**
	 * Sets the validated.
	 * 
	 * @param validated
	 *            The validated to set
	 */
	public void setValidated(boolean validated)
	{
		m_validated = validated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.valueobject.instanthelp.InstantHelpListener#validityChanged(com.agefospme.nsicm.client.framework.valueobject.instanthelp.InstantHelpEvent)
	 */
	public void validityChanged(InstantHelpEvent e)
	{
		m_actValider.setEnabled(e.isValid());
		// System.out.println("Validity Changed = "+e.isValid());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.agefospme.nsicm.client.framework.valueobject.instanthelp.InstantHelpListener#valueChanged(com.agefospme.nsicm.client.framework.valueobject.instanthelp.InstantHelpEvent)
	 */
	public void valueChanged(InstantHelpEvent e)
	{
	}

	/**
	 * @return
	 */
	public ValidationPanel getPanel()
	{
		return m_panel;
	}

	/**
	 * changed the status of Action Valider. Added for ME_1213.
	 * 
	 * @param valid
	 */
	public void setValidationState(boolean valid)
	{
		m_actValider.setEnabled(valid);
	}

}
