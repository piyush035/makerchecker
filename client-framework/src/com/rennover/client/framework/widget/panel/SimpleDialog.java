package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.rennover.client.framework.mvc.ConstantLabels;
import com.rennover.client.framework.widget.base.ZDialog;

/**
 * @author omallassi
 * 
 * affiche une dialog avec un bouton "fermer" en bas
 */
public class SimpleDialog extends ZDialog
{

	/**
	 * Instancie une boite de dialogue ouverte à partir d'une frame.
	 */
	public SimpleDialog(Frame owner)
	{
		super(owner);
		init();
	}

	/**
	 * Instancie une boite de dialogue ouverte à partir d'une autre boite de
	 * dialogue.
	 */
	public SimpleDialog(Dialog owner)
	{
		super(owner);
		init();
	}

	private void init()
	{
		compose();

		setModal(true);
		setDefaultCloseOperation(super.DISPOSE_ON_CLOSE);
		setResizable(false);
		pack();
	}

	private void compose()
	{
		ButtonPanel btnPanel = new ButtonPanel(true);
		btnPanel.addButtonAction(getCloseAction());

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(btnPanel, BorderLayout.SOUTH);
	}

	private Action getCloseAction()
	{
		Action action = new AbstractAction(ConstantLabels.CLOSE)
		{
			public void actionPerformed(ActionEvent e)
			{
				SimpleDialog.this.setVisible(false);
			}
		};

		return action;
	}
}