/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */


package com.rennover.client.framework.mvc;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JComponent;

import com.rennover.client.framework.widget.base.ZAction;
import com.rennover.client.framework.window.WindowManager;

/**
 * Cette méthode remplace AbstractAction
 * 
 * Elle lui ajoute : - la gestion des sabliers - la gestion des exceptions
 * 
 */
public abstract class DefaultAction extends AbstractAction implements ZAction
{
	/**
	 * Constructeur
	 * 
	 */
	public DefaultAction()
	{
		super();
	}

	/**
	 * Constructeur avec le nom de l'action
	 * 
	 * @param name
	 *            libélle de l'action
	 */
	public DefaultAction(String name)
	{
		super(name);
	}

	/**
	 * Constructeur avec le nom de l'action et une icône
	 * 
	 * @param name
	 *            libellé de l'action
	 * @param icon
	 *            icône à faire apparaître sur les boutons ou les menus
	 */
	public DefaultAction(String name, Icon icon)
	{
		super(name, icon);
	}

	/**
	 * Méthode à surcharger. Cette méthode est appelée lorsque l'action est
	 * déclenchée. Cette méthode doit exécuter l'action souhaitée.
	 */
	public abstract void actionPerformed2(ActionEvent e) throws Throwable;

	/**
	 * Utiliser en interne ne devrais pas être surchargée.
	 */
	public final void actionPerformed(ActionEvent e)
	{
		JComponent source = (e != null) ? (JComponent) e.getSource() : null;

		try
		{
			actionPerformed2(e);
		} catch (Throwable t)
		{
			WindowManager.showExceptionMessage(t, source);
		} finally
		{
			if (source != null)
			{
				WindowManager.unfreeze(source);
			}
		}
	}

	public String setAcceleratorKey()
	{
		return ActionHelper.getAcceleratorKey(this);
	}

	public void setAcceleratorKey(String key)
	{
		ActionHelper.setAcceleratorKey(this, key);
	}

	public void setActionCommandKey(String key)
	{
		ActionHelper.setActionCommandKey(this, key);
	}

	public String getActionCommandKey()
	{
		return ActionHelper.getActionCommandKey(this);
	}

	/**
	 * Change l'icône de l'action
	 * 
	 * @param icon
	 */
	public void setIcon(Icon icon)
	{
		ActionHelper.setIcon(this, icon);
	}

	/**
	 * Retourne l'icône de l'action
	 * 
	 * @return
	 */
	public Icon getIcon()
	{
		return ActionHelper.getIcon(this);
	}

	public void setLongDescrition(String description)
	{
		ActionHelper.setLongDescrition(this, description);
	}

	public String getLongDescrition()
	{
		return ActionHelper.getLongDescrition(this);
	}

	public void setMnemonicKey(String key)
	{
		ActionHelper.setMnemonicKey(this, key);
	}

	public String getMnemonicKey()
	{
		return ActionHelper.getMnemonicKey(this);
	}

	/**
	 * change le nom d'une action ainsi que le libellé des composants associés
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		ActionHelper.setName(this, name);
	}

	/**
	 * Retourne le nom de l'action
	 * 
	 * @return
	 */
	public String getName()
	{
		return ActionHelper.getName(this);
	}

	public void setShortDescrition(String description)
	{
		ActionHelper.setShortDescrition(this, description);
	}

	public String getShortDescrition()
	{
		return ActionHelper.getShortDescrition(this);
	}

	/**
	 * Change la visibilité des composants representant l'action (bouton ou
	 * menu)
	 * 
	 * @param visible
	 */
	public void setVisible(boolean visible)
	{
		ActionHelper.setVisible(this, visible);
	}

	/**
	 * Indique l'état de visibilité de l'action (et de ses composants associés)
	 * 
	 * @return
	 */
	public boolean isVisible()
	{
		return ActionHelper.isVisible(this);
	}
}
