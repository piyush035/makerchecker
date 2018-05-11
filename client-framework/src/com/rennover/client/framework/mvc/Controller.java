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
	 * Constructeur permettant de créer une hiérachie de controller
	 * 
	 * @param parent
	 *            contrôleur parent du contrôleur en construction
	 */
	public Controller(Controller parent)
	{
		m_parentController = parent;
	}

	public void doExecute()
	{
	}

	/**
	 * Cette méthode permet de récupérer une action. la méthode créée l'action
	 * si elle n'existe pas.
	 * 
	 * NB: la description de l'action est crée dans le contrôleur et c'est la
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
	 * NB: la méthode appelée par l'action doit être publique
	 * 
	 * @param actionDescr
	 *            contient le paramétrage de l'action
	 * @return l'action corespondant à la description
	 */
	public Action getAction(ActionDescription actionDescr)
	{
		return m_actionManager.getAction(this, actionDescr);
	}

	/**
	 * Récupère l'action associé à un nom
	 * 
	 * @param name
	 *            nom de l'action
	 * @return action associée au nom (sinon null)
	 */
	public Action getAction(String name)
	{
		return m_actionManager.getAction(name);
	}

	/**
	 * Créer une action à partir d'une ActionDescription
	 * 
	 * @param name
	 *            nom de l'action à créer
	 * @param actionDescr
	 *            paramètres de l'action
	 * @return action créée
	 */
	public Action createAction(String name, ActionDescription actionDescr)
	{
		return m_actionManager.createAction(name, this, actionDescr);
	}

	/**
	 * retourne le contrôleur parent
	 * 
	 * @return contrôleur parent
	 */
	public Controller getParentController()
	{
		return m_parentController;
	}

	/**
	 * assigne le contrôleur parent
	 * 
	 * @param parentController
	 *            contrôleur parent à assigner
	 */
	public void setParentController(Controller parentController)
	{
		m_parentController = parentController;
	}
}