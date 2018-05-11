

package com.rennover.client.framework.mvc;

import javax.swing.Action;


/**
 * 
 * Classe à spécialiser
 * 
 * Cette classe est le ressemblement d'une vue et d'un contrôleur. Elle contient
 * à la fois la composition de l'écran et les actions d'un contrôleur. Elle
 * permet de réaliser rapidement de petits écrans.
 * 
 * @see com.rennover.client.framework.mvc.PanelView
 * @see com.rennover.client.framework.mvc.PanelController
 * @see com.rennover.client.framework.mvc.PanelModel
 */
public abstract class PanelViewController extends PanelView
{
	public static final int NO_WAITING_STYLE = Controller.NO_WAITING_STYLE;

	public static final int HOURGLASS = Controller.HOURGLASS;

	public static final int DIALOG = Controller.DIALOG;

	private ActionManager m_actionManager = new ActionManager(); // Gestionnaire
																	// d'actions

	/**
	 * Constructor for PanelViewController.
	 * 
	 * @param title
	 *            titre de l'écran
	 */
	public PanelViewController(String title)
	{
		super(title);
	}

	/**
	 * Constructor for PanelViewController.
	 */
	public PanelViewController()
	{
		super();
	}

	/**
	 * Cette méthode permet de récupérer une action appartenant à un controleur
	 * en la créant si necessaire. Même fonctionnement que pour la classe
	 * Controller.
	 * 
	 * @param actionDescr
	 *            contient le paramétrage de l'action
	 * @return l'action corespondant à la description
	 * @see Controller.getAction(ActionDescription actionDescr)
	 */
	protected Action getAction(ActionDescription actionDescr)
	{
		return m_actionManager.getAction(this, actionDescr);
	}

	public PanelView getView()
	{
		return this;
	}
}