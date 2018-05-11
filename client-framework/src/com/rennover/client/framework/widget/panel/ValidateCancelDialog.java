package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JDialog;

import com.rennover.client.framework.mvc.ConstantLabels;
import com.rennover.client.framework.mvc.DefaultAction;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.icon.IconFactory;

/**
 * Boite de dialogue contenant deux boutons pour Valider ou Annuler ce qui a été
 * fait.
 * 
 * <p>
 * Le contenu réel de la boite de dialogue et l'implémentation des actions liées
 * aux boutons doivent être définis par les classes héritant de celle-ci.
 * </p>
 */
public abstract class ValidateCancelDialog extends JDialog
{
	/** Indique que le bouton Valider a été sélectionné. */
	public static final int VALIDER_SELECTED = 0;

	/** Indique que le bouton Annuler a été sélectionné. */
	public static final int ANNULER_SELECTED = 1;

	private Action m_actValider;

	private Action m_actAnnuler;

	private int m_selectedState = -1;

	/**
	 * Instancie une boite de dialogue ouverte à partir d'une frame.
	 */
	public ValidateCancelDialog(Frame owner, String title)
	{
		super(owner, title);
		init();
	}

	/**
	 * Instancie une boite de dialogue ouverte à partir d'une autre boite de
	 * dialogue.
	 */
	public ValidateCancelDialog(Dialog owner, String title)
	{
		super(owner, title);
		init();
	}

	public boolean isValidated()
	{
		return m_selectedState == VALIDER_SELECTED;
	}

	private void init()
	{
		compose();

		setModal(true);
		setDefaultCloseOperation(super.DO_NOTHING_ON_CLOSE);

		pack();
	}

	private void compose()
	{
		ButtonPanel pnlButtons = new ButtonPanel();
		pnlButtons.addButtonAction(getActValider());
		pnlButtons.addButtonAction(getActAnnuler());
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(createMainPanel(), BorderLayout.CENTER);
		contentPane.add(pnlButtons, BorderLayout.SOUTH);
	}

	/**
	 * Retourne la sélection de l'utilisateur si elle a eu lieu. Si
	 * l'utilisateur n'a rien sélectionné (par exemple, parce que la boite de
	 * dialogue n'a pas encore été affichée), une valeur invalide est retournée.
	 * 
	 * @return si une sélection a été faite par l'utilisateur,
	 *         {@link #VALIDER_SELECTED} ou {@link #ANNULER_SELECTED}; sinon,
	 *         -1
	 */
	public int getSelectedState()
	{
		return m_selectedState;
	}

	/**
	 * Méthode helper pour configurer facilement les constraintes de layout.
	 */
	protected final void updateConstraints(GridBagConstraints constraints, int gridx, int gridy, int gridwidth,
	        int gridheight, double weightx, double weighty)
	{
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridwidth = gridwidth;
		constraints.gridheight = gridheight;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
	}

	private Action getActValider()
	{
		if (null == m_actValider)
		{
			m_actValider = new DefaultAction(ConstantLabels.VALIDATE, IconFactory.getValidateIcon())
			{
				public void actionPerformed2(ActionEvent e)
				{
					m_selectedState = VALIDER_SELECTED;
					doValider();
					setVisible(false);
				}
			};

		}
		return m_actValider;
	}

	private Action getActAnnuler()
	{
		if (null == m_actAnnuler)
		{
			m_actAnnuler = new DefaultAction(ConstantLabels.CANCEL, IconFactory.getCancelIcon())
			{
				public void actionPerformed2(ActionEvent e)
				{
					m_selectedState = ANNULER_SELECTED;
					doAnnuler();
					setVisible(false);
				}
			};

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
	protected abstract ZPanel createMainPanel();

	/**
	 * Code à exécuter quand l'utilisateur clique sur le bouton Valider.
	 */
	protected abstract void doValider();

	/**
	 * Code à exécuter quand l'utilisateur clique sur le bouton Annuler.
	 */
	protected abstract void doAnnuler();
}