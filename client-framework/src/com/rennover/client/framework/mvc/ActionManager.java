/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

package com.rennover.client.framework.mvc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;

import com.rennover.hotel.common.access.AccessService;
import com.rennover.hotel.common.exception.TechnicalException;

/**
 * This class handles the instantiation and storage actions. It is used by
 * delegation to the Controller and PanelViewController class.
 * 
 * @author Piyush
 * 
 */
public class ActionManager {
	private Map m_actionMap = new HashMap();

	/**
	 * Constructor
	 */
	public ActionManager() {
	}

	/**
	 * This method allows to recover a share owned by a controller. method
	 * created the action if it does not exist.
	 * 
	 * Note: The description of the action is created in the controller and it
	 * is the To use, the description is a constant identifying Action.
	 * 
	 * Example: class XxxController ... static final String = ACTION_RECHERCHER
	 * New ActionDescription ("Search ...", "doRechercher"); ... public void
	 * DoRechercher () {... } ...
	 * 
	 * Class XxxView ... action = M_controller.getAction
	 * (XxxController.ACTION_RECHERCHER); ...
	 * 
	 * Note: the method called by the action must be public
	 * 
	 * @param controller
	 *            object as a controller and having a method call by Action
	 * @param actionDescr
	 *            contains the configuration of the action.
	 * @return action corespondant the description
	 */
	public Action getAction(Object controller, ActionDescription actionDescr) {
		// Recovery action
		Action action = getAction(actionDescr.m_methodName);

		// automatic instantiation of the action with its description
		if (action == null) {
			// Creating the Action.
			action = createAction(actionDescr.m_methodName, controller,
					actionDescr);
		}

		return action;
	}

	/**
	 * Create an action from a ActionDescription.
	 * 
	 * @param name
	 *            name of the action to create.
	 * @param actionDescr
	 *            parameters of the action
	 * @return action created
	 */
	public Action createAction(String name, Object controller,
			ActionDescription actionDescr) {
		// Creating the Action
		Action action = createAction(controller, actionDescr);

		// Storage action
		m_actionMap.put(name, action);

		return action;
	}

	/**
	 * Gets the name associated with an Action.
	 * 
	 * @param name
	 *            name of the action.
	 * @return action associated with the name (null if the action was not
	 *         found).
	 */
	public Action getAction(String name) {
		return (Action) m_actionMap.get(name);
	}

	/**
	 * This method creates a new action from a Description and an object as a
	 * controller.
	 * 
	 * @param controller
	 *            object as a controller.
	 * @param actionDescr
	 *            description of the action to create.
	 * @return action create
	 */
	public static Action createAction(Object controller,
			ActionDescription actionDescr) {
		try {
			// Recovery of the method to call
			Method actionMethod;
			if (actionDescr.m_methodParameter != null) {
				Class[] listClass = new Class[] { String.class };
				actionMethod = controller.getClass().getMethod(
						actionDescr.m_methodName, listClass);
			} else {
				actionMethod = controller.getClass().getMethod(
						actionDescr.m_methodName, null);
			}

			// Creation of the new action.
			Action action = new InvokeMethodAction(controller,
					actionDescr.m_label, actionMethod,
					actionDescr.m_methodParameter, actionDescr.m_waitingType,
					actionDescr.m_icon, actionDescr.m_tooltipText,
					actionDescr.m_mnemonicKey);
			// Check the activation of the action of availability
			// Functional service in relation to user right
			if (actionDescr.m_serviceFonctionnelList != null) {
				boolean enable = false;
				if (actionDescr.m_authoriseSurTousLesServices) {
					enable = AccessService
							.checkAccess(actionDescr.m_serviceFonctionnelList);
				} else {
					enable = AccessService
							.checkAccessAtOneService(actionDescr.m_serviceFonctionnelList);
				}

				action.setEnabled(enable);
			}

			return action;
		} catch (NoSuchMethodException exc) {
			// error if method not found in the controller object.
			throw new TechnicalException(exc.getMessage(), exc);
		}
	}
}
