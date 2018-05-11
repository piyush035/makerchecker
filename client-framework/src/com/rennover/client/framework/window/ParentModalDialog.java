
package com.rennover.client.framework.window;

import java.awt.AWTEvent;
import java.awt.ActiveEvent;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;

import com.rennover.client.framework.widget.base.ZDialog;

public class ParentModalDialog extends ZDialog
{
	protected boolean m_modal;

	private WindowAdapter m_parentWindowListener;

	private Window m_owner;

	public ParentModalDialog(Frame frame, String title, boolean modal)
	{
		super(frame, title, false);
		initDialog(frame, title, modal);
	}

	public ParentModalDialog(Dialog frame, String title, boolean modal)
	{
		super(frame, title, false);
		initDialog(frame, title, modal);
	}

	public ParentModalDialog()
	{
		this((Frame) null, "", false);
	}

	private void initDialog(Window parent, String title, boolean isModal)
	{
		this.m_owner = parent;
		this.m_modal = isModal;
		m_parentWindowListener = new WindowAdapter()
		{
			public void windowActivated(WindowEvent e)
			{
				if (isVisible())
				{
					getFocusBack();
				}
			}
		};
	}

	private void getFocusBack()
	{
		Toolkit.getDefaultToolkit().beep();
		super.setVisible(false);
		super.pack();
		super.setLocationRelativeTo(m_owner);
		super.setVisible(true);
	}

	public void show()
	{
		_setVisible(true);
	}

	public void hide()
	{
		_setVisible(false);
	}

	public void _setVisible(boolean visible)
	{
		boolean releaseParent = !(visible && m_modal);
		m_owner.setEnabled(releaseParent);
		m_owner.setFocusableWindowState(releaseParent);

		if (visible)
		{
			super.show();
		} else
		{
			super.hide();
		}

		if (m_modal)
		{
			try
			{
				if (SwingUtilities.isEventDispatchThread())
				{
					EventQueue theQueue = getToolkit().getSystemEventQueue();
					while (isVisible())
					{
						AWTEvent event = theQueue.getNextEvent();

						Object src = event.getSource();
						if (event instanceof ActiveEvent)
						{
							((ActiveEvent) event).dispatch();
						} else if (src instanceof Component)
						{
							((Component) src).dispatchEvent(event);
						}
					}
				} else
				{
					synchronized (getTreeLock())
					{
						while (isVisible())
						{
							try
							{
								getTreeLock().wait();
							} catch (InterruptedException e)
							{
								break;
							}
						}
					}
				}
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	public void setModal(boolean modal)
	{
		this.m_modal = modal;
	}
}
