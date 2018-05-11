package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.border.EmptyBorder;

import com.rennover.client.framework.mvc.ConstantLabels;
import com.rennover.client.framework.widget.base.ZDialog;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.icon.IconFactory;
import com.rennover.client.framework.window.WindowManager;

/**
 * Une boite de dialogue qui présente le panneau {@link
 * com.agefospme.nsicm.client.common.widget.AddRemovePanel} avec des boutons
 * Annuler & Valider.
 */
public class AddRemoveDialog extends ZDialog
{
	protected AddRemovePanel m_pnlAddRemove;

	private Action m_actValidate;

	private Action m_actCancel;

	private boolean m_isValidated = false;

	public AddRemoveDialog(Dialog owner, String title)
	{
		super(owner, title);
	}

	public AddRemoveDialog(Frame owner, String title)
	{
		super(owner, title);
	}

	public AddRemoveDialog(Dialog owner, String title, List optionsList, List choiceList)
	{
		super(owner, title);
		m_pnlAddRemove = new AddRemovePanel();
		m_pnlAddRemove.setSourceList(optionsList);
		m_pnlAddRemove.setDestinationList(choiceList);
		init();
		centerToParent();
	}

	public AddRemoveDialog(Frame owner, String title, List optionsList, List choiceList)
	{
		super(owner, title);
		m_pnlAddRemove = new AddRemovePanel();
		m_pnlAddRemove.setSourceList(optionsList);
		m_pnlAddRemove.setDestinationList(choiceList);
		init();
		centerToParent();
	}

	public AddRemoveDialog(Dialog owner, String title, List optionsList, List choiceList, boolean manualySorted)
	{
		super(owner, title);
		m_pnlAddRemove = new AddRemovePanel(manualySorted);
		m_pnlAddRemove.setSourceList(optionsList);
		m_pnlAddRemove.setDestinationList(choiceList);
		init();
		centerToParent();
	}

	public AddRemoveDialog(Frame owner, String title, List optionsList, List choiceList, boolean manualySorted)
	{
		super(owner, title);
		m_pnlAddRemove = new AddRemovePanel(manualySorted);
		m_pnlAddRemove.setSourceList(optionsList);
		m_pnlAddRemove.setDestinationList(choiceList);
		init();
		centerToParent();
	}

	/**
	 * @deprecated use AddRemoveDialog(Frame owner, String title, List
	 *             optionsList, List choiceList)
	 */
	public AddRemoveDialog(Frame owner, String title, AddRemovePanel addRemovePanel)
	{
		super(owner, title);
		m_pnlAddRemove = addRemovePanel;
		init();
		centerToParent();
	}

	public void setOneWaySelected(boolean oneWaySelected)
	{
		m_pnlAddRemove.setOneWaySelected(oneWaySelected);
	}

	/**
	 * Permet de créer une boîte de dialogue AddRemove sans avoir préciser le
	 * type de la fenêtre parente (dialog ou frame)
	 */
	public static AddRemoveDialog createAddRemoveDialog(Component parent, String title, List optionsList,
	        List choiceList)
	{
		Window window = WindowManager.getOwningWindow(parent);

		if (window instanceof Frame)
		{
			return new AddRemoveDialog((Frame) window, title, optionsList, choiceList);
		} else if (window instanceof Dialog)
		{
			return new AddRemoveDialog((Dialog) window, title, optionsList, choiceList);
		}

		return null;
	}

	/**
	 * Permet modifier les libellés affichés au dessus des listes
	 */
	public void setListTitles(String sourceTitle, String destinationPanel)
	{
		m_pnlAddRemove.setListTitles(sourceTitle, destinationPanel);
	}

	/**
	 * added by Ivega Offshore on July 12, 2004 this gives an option to opt for
	 * showing the number of elements in title, default is true
	 * 
	 */
	public void setRowCountShowed(boolean rowCountShowed)
	{
		m_pnlAddRemove.setRowCountShowed(rowCountShowed);
		m_pnlAddRemove.updateTitle();
	}

	/**
	 * Permet de modifier les libellés des boutons
	 * 
	 * @param addSelectedTitle
	 * @param addAllTitle
	 * @param removeSelectedTitle
	 * @param removeAllTitle
	 */
	public void setButtonTitles(String addSelectedTitle, String addAllTitle, String removeSelectedTitle,
	        String removeAllTitle)
	{
		m_pnlAddRemove.setButtonTitles(addSelectedTitle, addAllTitle, removeSelectedTitle, removeAllTitle);
	}

	/**
	 * Indique que l'utilisateur a appuyé sur le bouton "Valider"
	 */
	public boolean isValidated()
	{
		return m_isValidated;
	}

	protected void init()
	{
		setResizable(true);
		setModal(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		ZPanel panel = new ZPanel(new BorderLayout());
		panel.setLayout(new BorderLayout(2, 2));
		m_pnlAddRemove.setBorder(new EmptyBorder(4, 4, 2, 4));
		panel.add(m_pnlAddRemove, BorderLayout.CENTER);
		panel.add(createPnlButtons(), BorderLayout.SOUTH);

		Container contentPane = this.getContentPane();
		contentPane.add(panel, BorderLayout.CENTER);

		composeBottomPanel();

		pack();
	}

	/**
	 * Assemble les composants dans un panneau en bas de la fenêtre (optionnel).
	 * La classe qui hérite de {@link AddRemoveDialog} est chargée de maintenir
	 * le pointeur vers ce panneau, ainsi que l'affichage de ce panneau (par
	 * exemple au moment d'un clic sur un bouton "Détails").
	 */
	protected ZPanel composeBottomPanel()
	{
		return null;
	}

	/**
	 * Instancie le panneau de boutons pour le bas de la fenêtre. Surcharger
	 * cette méthode si on désire avoir des boutons différents.
	 */
	protected ButtonPanel createPnlButtons()
	{
		ButtonPanel pnlButtons = new ButtonPanel(true);
		pnlButtons.addButtonAction(getActValidate());
		pnlButtons.addButtonAction(getActCancel());
		return pnlButtons;
	}

	/**
	 * Retourne l'action associée avec le bouton Valider. Il est nécessaire
	 * d'utiliser cette méthode dans les classes filles si le panneau de bouton
	 * en bas de la fenêtre est redéfini.
	 */
	protected Action getActValidate()
	{
		if (null == m_actValidate)
		{
			m_actValidate = new AbstractAction(ConstantLabels.VALIDATE, IconFactory.getValidateIcon())
			{
				public void actionPerformed(ActionEvent e)
				{
					doValidate();
				}
			};

		}
		return m_actValidate;
	}

	/**
	 * Retourne l'action associée avec le bouton Annuler. Il est nécessaire
	 * d'utiliser cette méthode dans les classes filles si le panneau de bouton
	 * en bas de la fenêtre est redéfini.
	 */
	protected Action getActCancel()
	{
		if (null == m_actCancel)
		{
			m_actCancel = new AbstractAction(ConstantLabels.CANCEL, IconFactory.getCancelIcon())
			{
				public void actionPerformed(ActionEvent e)
				{
					doCancel();
				}
			};

		}
		return m_actCancel;
	}

	/**
	 * Retourne la liste résultat
	 */
	public List getDestinationList()
	{
		return m_pnlAddRemove.getDestinationList();
	}

	/**
	 * Retourne la liste des éléments écartés
	 */
	public List getSourceList()
	{
		return m_pnlAddRemove.getSourceList();
	}

	/**
	 * Retourne la liste des éléments ajoutés à la liste résultats
	 */
	public List getAddedList()
	{
		return m_pnlAddRemove.getAddedList();
	}

	/**
	 * Retourne la liste des éléments enlevés à la liste résultats
	 */
	public List getRemovedList()
	{
		return m_pnlAddRemove.getRemovedList();
	}

	protected void doValidate()
	{
		m_isValidated = true;
		setVisible(false);
	}

	protected void doCancel()
	{
		m_isValidated = false;
		setVisible(false);
	}

	protected void processWindowEvent(WindowEvent e)
	{
		if (e.getID() == WindowEvent.WINDOW_CLOSING)
		{
			doCancel();
		}

		super.processWindowEvent(e);
	}
}