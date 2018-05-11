

package com.rennover.client.framework.mvc;

import javax.swing.Action;


/**
 * 
 * Classe � sp�cialiser
 * 
 * Cette classe est le ressemblement d'une vue et d'un contr�leur. Elle contient
 * � la fois la composition de l'�cran et les actions d'un contr�leur. Elle
 * permet de r�aliser rapidement de petits �crans.
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
	 *            titre de l'�cran
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
	 * Cette m�thode permet de r�cup�rer une action appartenant � un controleur
	 * en la cr�ant si necessaire. M�me fonctionnement que pour la classe
	 * Controller.
	 * 
	 * @param actionDescr
	 *            contient le param�trage de l'action
	 * @return l'action corespondant � la description
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