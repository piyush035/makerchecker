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
 * Boite de dialogue contenant deux boutons pour Valider ou Annuler ce qui a �t�
 * fait.
 * 
 * <p>
 * Le contenu r�el de la boite de dialogue et l'impl�mentation des actions li�es
 * aux boutons doivent �tre d�finis par les classes h�ritant de celle-ci.
 * </p>
 */
public abstract class ValidateCancelDialog extends JDialog
{
	/** Indique que le bouton Valider a �t� s�lectionn�. */
	public static final int VALIDER_SELECTED = 0;

	/** Indique que le bouton Annuler a �t� s�lectionn�. */
	public static final int ANNULER_SELECTED = 1;

	private Action m_actValider;

	private Action m_actAnnuler;

	private int m_selectedState = -1;

	/**
	 * Instancie une boite de dialogue ouverte � partir d'une frame.
	 */
	public ValidateCancelDialog(Frame owner, String title)
	{
		super(owner, title);
		init();
	}

	/**
	 * Instancie une boite de dialogue ouverte � partir d'une autre boite de
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
	 * Retourne la s�lection de l'utilisateur si elle a eu lieu. Si
	 * l'utilisateur n'a rien s�lectionn� (par exemple, parce que la boite de
	 * dialogue n'a pas encore �t� affich�e), une valeur invalide est retourn�e.
	 * 
	 * @return si une s�lection a �t� faite par l'utilisateur,
	 *         {@link #VALIDER_SELECTED} ou {@link #ANNULER_SELECTED}; sinon,
	 *         -1
	 */
	public int getSelectedState()
	{
		return m_selectedState;
	}

	/**
	 * M�thode helper pour configurer facilement les constraintes de layout.
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
	 * Instancie et assemble les diff�rents composants graphiques de la boite de
	 * dialogue.<br>
	 * Le panel sera plac� dans la boite de dialogue � l'aide de la contrainte
	 * BorderLayout.CENTER.
	 * 
	 * <p>
	 * La zone BorderLayout.SOUTH est r�serv�e aux boutons.
	 * </p>
	 */
	protected abstract ZPanel createMainPanel();

	/**
	 * Code � ex�cuter quand l'utilisateur clique sur le bouton Valider.
	 */
	protected abstract void doValider();

	/**
	 * Code � ex�cuter quand l'utilisateur clique sur le bouton Annuler.
	 */
	protected abstract void doAnnuler();
}