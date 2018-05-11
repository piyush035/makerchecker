/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

/*
 * 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.rennover.hotel.common.pattern.state;

import com.rennover.hotel.common.log.Logger;
import com.rennover.hotel.common.valueobject.PropertyHelper;


/**
 * Superclasse des �tats relatifs au pattern 'state'.
 */
public abstract class State
{
    /**
     * Identifie un �tat.
     * Permet aussi de sauvegarder un �tat en base.
     * @identifier
     */
    private char m_code;

    /**
     * Trigger a transition in the current state for<BR>
     *  <CODE>subject</CODE> with the <CODE>event</CODE>, <BR>
     * Called when we are out the state
     */
    private void effectuerTransitionExterne(Subject subject, Event event)
	{
		State etatCourant = subject.getState();
		etatCourant.exit(subject);

		TransitionExterne transition = (TransitionExterne) etatCourant.getTransition(subject, event);

		if (transition == null)
		{
			throw new RuntimeException("l'�v�nement " + event + " n'est pas g�r� dans l'�tat "
			        + etatCourant.getClass().getName());
		}

		transition.doAction(subject);

		State etatCible = transition.getTarget();

		if (!PropertyHelper.isNull(etatCible))
		{
			subject.setState(etatCible);
			etatCible.enter(subject);
		}
	}

    /**
	 * Called when we try to go to another state At first, we make an internal
	 * transition and, if it's a success, we make the external transition (and
	 * change the state)
	 */
    public void sendEvent(Subject subject, Event event)
	{
		if (!effectuerTransitionInterne(subject, event))
		{
			effectuerTransitionExterne(subject, event);
		}
	}

    /**
	 * Called before exiting the state
	 */
    private boolean effectuerTransitionInterne(Subject subject, Event event)
	{
		State etatCourant = subject.getState();
		Transition transition = etatCourant.getTransition(subject, event);

		if (transition == null)
		{
			throw new RuntimeException("l'�v�nement " + event + " n'est pas g�r� dans l'�tat "
			        + etatCourant.getClass().getName());
		}

		if (transition instanceof TransitionInterne)
		{
			transition.doAction(subject);

			return true;
		}

		return false;
	}

	public abstract Transition getTransition(Subject subject, Event event);

    /**
	 * A r��crire dans chaque sous-classe repr�sentant un �tat ayant une action
	 * d'entr�e.
	 */
    protected void enter(Subject subject)
	{
		Logger.debug(this, subject, " entre dans l'�tat : ", this.toString());
	}

    public String toString()
	{
		return this.getClass().getName();
	}

    /**
	 * A r��crire dans chaque sous-classe repr�sentant un �tat ayant une action
	 * de sortie.
	 */
    protected void exit(Subject subject)
	{
	}

	public char getCode()
	{
		return m_code;
	}

	public void setCode(char pCode)
	{
		this.m_code = pCode;
	}
}
