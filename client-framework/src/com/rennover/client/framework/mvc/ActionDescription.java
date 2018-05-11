/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

package com.rennover.client.framework.mvc;

import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;

import com.rennover.hotel.common.access.AccessPermission;

/**
 * This class contains the necessary information to create an action. Added the
 * ability to have a list of functional services related to the action.
 * 
 * @author Piyush
 */
public class ActionDescription {
	/**
	 * Wording or Action Name Note: This name appears on the widgets associated
	 * with the action.
	 */
	public String m_label;

	/**
	 * Name of the method to call in the controller to initiate action.
	 */
	public String m_methodName;

	/**
	 * Parameter name of the method to call in the controller to initiate
	 * action.
	 */
	public String m_methodParameter;

	/**
	 * List of functional services used for condition clearance action based on
	 * the user.
	 */
	public List m_serviceFonctionnelList;

	/**
	 * Indicates whether the hourglass must be enabled when the call to action
	 * method.
	 */
	public int m_waitingType = Controller.NO_WAITING_STYLE;

	/** Icon to display on the buttons or menus. */
	public Icon m_icon;

	/** Tooltip on the action. */
	public String m_tooltipText;

	/** Mnemonic character (shown underlined). */
	public char m_mnemonicKey = '\0';

	/**
	 * Indicates whether the player must have all services (TRUE) or at least
	 * one (FALSE) Default is TRUE.
	 */
	public boolean m_authoriseSurTousLesServices = true;

	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param methodParameter
	 */
	public ActionDescription(String label, String methodName,
			String methodParameter) {
		this.m_label = label;
		this.m_methodName = methodName;
		this.m_methodParameter = methodParameter;
	}

	/**
	 * 
	 * @param label
	 * @param methodName
	 */
	public ActionDescription(String label, String methodName) {
		this.m_label = label;
		this.m_methodName = methodName;
	}

	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param mnemonicKey
	 */
	public ActionDescription(String label, String methodName, char mnemonicKey) {
		this(label, methodName);
		this.m_mnemonicKey = mnemonicKey;
	}

	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param icon
	 */
	public ActionDescription(String label, String methodName, Icon icon) {
		this(label, methodName);
		this.m_icon = icon;
	}

	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param icon
	 * @param waitingType
	 */
	public ActionDescription(String label, String methodName, Icon icon,
			int waitingType) {
		this(label, methodName, icon);
		this.m_waitingType = waitingType;
	}

	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param icon
	 * @param tooltipText
	 */
	public ActionDescription(String label, String methodName, Icon icon,
			String tooltipText) {
		this(label, methodName, icon);
		this.m_tooltipText = tooltipText;
	}

	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param mnemonicKey
	 * @param icon
	 * @param tooltipText
	 */
	public ActionDescription(String label, String methodName, char mnemonicKey,
			Icon icon, String tooltipText) {
		this(label, methodName, icon);
		this.m_tooltipText = tooltipText;
		this.m_mnemonicKey = mnemonicKey;
	}

	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param waitingType
	 */
	public ActionDescription(String label, String methodName, int waitingType) {
		this(label, methodName);
		this.m_waitingType = waitingType;
	}

	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param mnemonicKey
	 * @param icon
	 * @param tooltipText
	 * @param waitingType
	 */
	public ActionDescription(String label, String methodName, char mnemonicKey,
			Icon icon, String tooltipText, int waitingType) {
		this(label, methodName, icon);
		this.m_tooltipText = tooltipText;
		this.m_mnemonicKey = mnemonicKey;
		this.m_waitingType = waitingType;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param service
	 */
	public ActionDescription(String label, String methodName,
			AccessPermission service) {
		this(label, methodName);
		this.m_serviceFonctionnelList = Arrays.asList(new Object[] { service });
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param service
	 * @param icon
	 */
	public ActionDescription(String label, String methodName,
			AccessPermission service, Icon icon) {
		this(label, methodName, service);
		this.m_icon = icon;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param service
	 * @param waitingType
	 */
	public ActionDescription(String label, String methodName,
			AccessPermission service, int waitingType) {
		this(label, methodName, service);
		this.m_waitingType = waitingType;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param service
	 * @param icon
	 * @param tooltipText
	 */
	public ActionDescription(String label, String methodName,
			AccessPermission service, Icon icon, String tooltipText) {
		this(label, methodName, service, icon);
		this.m_tooltipText = tooltipText;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param service
	 * @param icon
	 * @param waitingType
	 */
	public ActionDescription(String label, String methodName,
			AccessPermission service, Icon icon, int waitingType) {
		this(label, methodName, service, waitingType);
		this.m_icon = icon;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param service
	 * @param icon
	 * @param tooltipText
	 * @param waitingType
	 */
	public ActionDescription(String label, String methodName,
			AccessPermission service, Icon icon, String tooltipText,
			int waitingType) {
		this(label, methodName, service, icon, waitingType);
		this.m_tooltipText = tooltipText;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param service
	 * @param mnemonicKey
	 * @param icon
	 * @param tooltipText
	 */
	public ActionDescription(String label, String methodName,
			AccessPermission service, char mnemonicKey, Icon icon,
			String tooltipText) {
		this(label, methodName, service, icon, tooltipText);
		this.m_mnemonicKey = mnemonicKey;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param service
	 * @param mnemonicKey
	 * @param icon
	 * @param tooltipText
	 * @param waitingType
	 */
	public ActionDescription(String label, String methodName,
			AccessPermission service, char mnemonicKey, Icon icon,
			String tooltipText, int waitingType) {
		this(label, methodName, service, icon, waitingType);
		this.m_tooltipText = tooltipText;
		this.m_mnemonicKey = mnemonicKey;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList) {
		this(label, methodName);
		this.m_serviceFonctionnelList = serviceFonctionnelList;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param icon
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, Icon icon) {
		this(label, methodName, serviceFonctionnelList);
		this.m_icon = icon;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param waitingType
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, int waitingType) {
		this(label, methodName, serviceFonctionnelList);
		this.m_waitingType = waitingType;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param icon
	 * @param tooltipText
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, Icon icon, String tooltipText) {
		this(label, methodName, serviceFonctionnelList, icon);
		this.m_tooltipText = tooltipText;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param icon
	 * @param waitingType
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, Icon icon, int waitingType) {
		this(label, methodName, serviceFonctionnelList, waitingType);
		this.m_icon = icon;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param icon
	 * @param tooltipText
	 * @param waitingType
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, Icon icon, String tooltipText,
			int waitingType) {
		this(label, methodName, serviceFonctionnelList, icon, waitingType);
		this.m_tooltipText = tooltipText;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param mnemonicKey
	 * @param icon
	 * @param tooltipText
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, char mnemonicKey, Icon icon,
			String tooltipText) {
		this(label, methodName, serviceFonctionnelList, icon, tooltipText);
		this.m_mnemonicKey = mnemonicKey;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param mnemonicKey
	 * @param icon
	 * @param tooltipText
	 * @param waitingType
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, char mnemonicKey, Icon icon,
			String tooltipText, int waitingType) {
		this(label, methodName, serviceFonctionnelList, icon, waitingType);
		this.m_tooltipText = tooltipText;
		this.m_mnemonicKey = mnemonicKey;
	}

	/**
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param authoriseSurTousLesServices
	 *            Indicates whether the player must have all services (TRUE) or at least one (FALSE)
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, boolean authoriseSurTousLesServices) {
		this(label, methodName);
		this.m_serviceFonctionnelList = serviceFonctionnelList;
		this.m_authoriseSurTousLesServices = authoriseSurTousLesServices;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param icon
	 * @param authoriseSurTousLesServices
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, Icon icon,
			boolean authoriseSurTousLesServices) {
		this(label, methodName, serviceFonctionnelList,
				authoriseSurTousLesServices);
		this.m_icon = icon;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param waitingType
	 * @param authoriseSurTousLesServices
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, int waitingType,
			boolean authoriseSurTousLesServices) {
		this(label, methodName, serviceFonctionnelList,
				authoriseSurTousLesServices);
		this.m_waitingType = waitingType;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param icon
	 * @param tooltipText
	 * @param authoriseSurTousLesServices
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, Icon icon, String tooltipText,
			boolean authoriseSurTousLesServices) {
		this(label, methodName, serviceFonctionnelList, icon,
				authoriseSurTousLesServices);
		this.m_tooltipText = tooltipText;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param icon
	 * @param waitingType
	 * @param authoriseSurTousLesServices
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, Icon icon, int waitingType,
			boolean authoriseSurTousLesServices) {
		this(label, methodName, serviceFonctionnelList, waitingType,
				authoriseSurTousLesServices);
		this.m_icon = icon;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param icon
	 * @param tooltipText
	 * @param waitingType
	 * @param authoriseSurTousLesServices
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, Icon icon, String tooltipText,
			int waitingType, boolean authoriseSurTousLesServices) {
		this(label, methodName, serviceFonctionnelList, icon, waitingType,
				authoriseSurTousLesServices);
		this.m_tooltipText = tooltipText;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param mnemonicKey
	 * @param icon
	 * @param tooltipText
	 * @param authoriseSurTousLesServices
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, char mnemonicKey, Icon icon,
			String tooltipText, boolean authoriseSurTousLesServices) {
		this(label, methodName, serviceFonctionnelList, icon, tooltipText,
				authoriseSurTousLesServices);
		this.m_mnemonicKey = mnemonicKey;
	}
	/**
	 * 
	 * @param label
	 * @param methodName
	 * @param serviceFonctionnelList
	 * @param mnemonicKey
	 * @param icon
	 * @param tooltipText
	 * @param waitingType
	 * @param authoriseSurTousLesServices
	 */
	public ActionDescription(String label, String methodName,
			List serviceFonctionnelList, char mnemonicKey, Icon icon,
			String tooltipText, int waitingType,
			boolean authoriseSurTousLesServices) {
		this(label, methodName, serviceFonctionnelList, icon, waitingType,
				authoriseSurTousLesServices);
		this.m_tooltipText = tooltipText;
		this.m_mnemonicKey = mnemonicKey;
	}

	/**
	 * Affects a functional service action. If the service is not available to the user, the action is disabled.
	 * 
	 * @param service
	 *            service fonctionnel
	 */
	public void setAccessPermission(AccessPermission service) {
		this.m_serviceFonctionnelList = Arrays.asList(new Object[] { service });
	}

	/**
	 * Assigns a list of servicing action. If a service is not available to the user, the action is disabled.
	 * 
	 * @param serviceFonctionnelList
	 *            list of functional services
	 */
	public void setAccessPermission(List serviceFonctionnelList) {
		this.m_serviceFonctionnelList = serviceFonctionnelList;
	}
}