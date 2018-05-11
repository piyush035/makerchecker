package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.Action;

import com.rennover.client.framework.mvc.InvokeMethodAction;
import com.rennover.client.framework.window.DefaultDialog;

/**
 * Boite de dialogue contenant deux boutons pour Valider ou Annuler ce qui a �t�
 * fait.
 * 
 * <p>
 * Le contenu r�el de la boite de dialogue et l'impl�mentation des actions li�es
 * aux boutons doivent �tre d�finis par les classes h�ritant de celle-ci.
 * </p>
 */
public class InformationDialog extends DefaultDialog
{
	/**
	 * Instancie une boite de dialogue ouverte � partir d'une frame.
	 */
	public InformationDialog(Frame owner)
	{
		super(owner);
		init2();
	}

	/**
	 * Instancie une boite de dialogue ouverte � partir d'une autre boite de
	 * dialogue.
	 */
	public InformationDialog(Dialog owner)
	{
		super(owner);
		init2();
	}

	private void init2()
	{
		compose();

		setModal(true);
		setDefaultCloseOperation(super.DISPOSE_ON_CLOSE);

		pack();
	}

	private void compose()
	{
		ButtonPanel pnlButtons = new ButtonPanel(true);
		InvokeMethodAction action = new InvokeMethodAction(this, "Close", "doFermer");
		action.putValue(Action.MNEMONIC_KEY, new Integer('F'));
		pnlButtons.addButtonAction(action);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		contentPane.add(pnlButtons, BorderLayout.SOUTH);
	}

	/**
	 * Code � ex�cuter quand l'utilisateur clique sur le bouton Fermer.
	 */
	public void doFermer()
	{
		setVisible(false);
	}
}