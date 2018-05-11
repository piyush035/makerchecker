package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.Action;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import com.rennover.client.framework.widget.base.ZButton;
import com.rennover.client.framework.widget.field.InputFieldHelper;

public class ToolBar extends ComponentPanel
{
	public static final int STRUT_BETWEEN_ELEMENTS = 5;

	public static final int STRUT_BETWEEN_GROUPS = 30;

	int m_strutBetweenElements = STRUT_BETWEEN_ELEMENTS;

	int m_strutBetweenGroups = STRUT_BETWEEN_GROUPS;

	/**
	 * 
	 */
	public ToolBar()
	{
		super(HORIZONTAL);
	}

	public ToolBar(int strutBetweenElements, int strutBetweenGroups)
	{
		super(HORIZONTAL);
		setStrutBetweenElements(strutBetweenElements);
		setStrutBetweenGroups(strutBetweenGroups);

		setBorder(new EmptyBorder(0, 0, 0, 0));
		add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
	}

	public Component add(Component comp)
	{
		super.add(comp);
		addStrut();
		return comp;
	}

	/**
	 * Ajoute un bouton associé à cette action à la suite des autres boutons,
	 * séparé par un espace standard (soit autour de 5 pixels).
	 */
	public ZButton addButtonAction(Action action)
	{
		return addButtonAction(null, action);
	}

	public ZButton addLockableButtonAction(Action action)
	{
		return addLockableButtonAction(null, action);
	}

	private ZButton addButtonAction(String label, Action action)
	{
		ZButton button;

		if (label != null)
		{
			button = new ZButton(label);
		} else
		{
			button = new ZButton(action);
		}

		button.setFocusable(false);

		add(button);
		return button;
	}

	private ZButton addLockableButtonAction(String label, Action action)
	{
		ZButton button = addButtonAction(label, action);
		InputFieldHelper.setLockable(button, true);
		return button;
	}

	public void setStrutBetweenElements(int size)
	{
		m_strutBetweenElements = size;
	}

	public void setStrutBetweenGroups(int size)
	{
		m_strutBetweenGroups = size;
	}

	public void addStrut()
	{
		addStrut(m_strutBetweenElements);
	}

	public void addSeparator()
	{
		addStrut(m_strutBetweenGroups);
	}
}