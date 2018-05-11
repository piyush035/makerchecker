/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */
package com.rennover.client.framework.mvc;

import javax.swing.Action;

import com.rennover.client.framework.window.WindowFreezer;

/**
 * This is base controller for swing framework.
 * 
 * @author Piyush
 * 
 */
public class Controller
{
	public static final int NO_WAITING_STYLE = WindowFreezer.NOTHING;

	public static final int HOURGLASS = WindowFreezer.HOURGLASS;

	public static final int DIALOG = WindowFreezer.DIALOG_AND_HOURGLASS;

	private ActionManager m_actionManager = new ActionManager();

	private Controller m_parentController;

	/**
	 * Constructeur permettant de cr�er une hi�rachie de controller
	 * 
	 * @param parent
	 *            contr�leur parent du contr�leur en construction
	 */
	public Controller(Controller parent)
	{
		m_parentController = parent;
	}

	public void doExecute()
	{
	}

	/**
	 * Cette m�thode permet de r�cup�rer une action. la m�thode cr��e l'action
	 * si elle n'existe pas.
	 * 
	 * NB: la description de l'action est cr�e dans le contr�leur et c'est la
	 * vue utilise, la description est une constante permettant d'identifier
	 * l'action.
	 * 
	 * example : class XxxController ... final static String ACTION_RECHERCHER =
	 * new ActionDescription("Rechercher...", "doRechercher"); ... public void
	 * doRechercher() { ... } ...
	 * 
	 * class XxxView ... action =
	 * m_controller.getAction(XxxController.ACTION_RECHERCHER); ...
	 * 
	 * NB: la m�thode appel�e par l'action doit �tre publique
	 * 
	 * @param actionDescr
	 *            contient le param�trage de l'action
	 * @return l'action corespondant � la description
	 */
	public Action getAction(ActionDescription actionDescr)
	{
		return m_actionManager.getAction(this, actionDescr);
	}

	/**
	 * R�cup�re l'action associ� � un nom
	 * 
	 * @param name
	 *            nom de l'action
	 * @return action associ�e au nom (sinon null)
	 */
	public Action getAction(String name)
	{
		return m_actionManager.getAction(name);
	}

	/**
	 * Cr�er une action � partir d'une ActionDescription
	 * 
	 * @param name
	 *            nom de l'action � cr�er
	 * @param actionDescr
	 *            param�tres de l'action
	 * @return action cr��e
	 */
	public Action createAction(String name, ActionDescription actionDescr)
	{
		return m_actionManager.createAction(name, this, actionDescr);
	}

	/**
	 * retourne le contr�leur parent
	 * 
	 * @return contr�leur parent
	 */
	public Controller getParentController()
	{
		return m_parentController;
	}

	/**
	 * assigne le contr�leur parent
	 * 
	 * @param parentController
	 *            contr�leur parent � assigner
	 */
	public void setParentController(Controller parentController)
	{
		m_parentController = parentController;
	}
}