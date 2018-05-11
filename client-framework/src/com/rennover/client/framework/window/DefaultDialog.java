
package com.rennover.client.framework.window;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;

import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpManager;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpPanel;
import com.rennover.client.framework.widget.base.ZDialog;
import com.rennover.client.framework.widget.base.ZMenuBar;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.panel.StatusBar;
import com.rennover.hotel.common.exception.TechnicalException;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class DefaultDialog extends ZDialog
{
	private boolean m_DEBUG = false;

	private PanelView m_panelView = null;

	private InstantHelpPanel m_instantHelpView = new InstantHelpPanel();

	private InstantHelpManager m_instantHelpManager = new InstantHelpManager();

	private StatusBar m_statusBar;

	public DefaultDialog(Frame parent) throws HeadlessException
	{
		super(parent, null, true);
		init();
	}

	public DefaultDialog(Dialog parent) throws HeadlessException
	{
		super(parent, null, true);
		init();
	}

	public DefaultDialog(Frame parent, String title) throws HeadlessException
	{
		this(parent);
		setTitle(title);
		init();
	}

	public DefaultDialog(Dialog parent, String title) throws HeadlessException
	{
		this(parent);
		setTitle(title);
		init();
	}

	public DefaultDialog(Frame parent, PanelView view) throws HeadlessException
	{
		this(parent);
		setTitle(view.getTitle());
		m_panelView = view;
		init();

		view.showAsDialog(this);
	}

	public DefaultDialog(Dialog parent, PanelView view) throws HeadlessException
	{
		this(parent);
		setTitle(view.getTitle());
		m_panelView = view;
		init();

		view.showAsDialog(this);
	}

	private void init()
	{
		setCloseOnESC();
		getContentPane().setLayout(new BorderLayout());
		setSize(1024, 768); // taille de la maquette
		centerToScreen();

		// NB: permet le redimensionnement de la fenêtre dans le look Alloy
		Container contentPane = getContentPane();
		if (contentPane instanceof JComponent)
		{
			((JComponent) contentPane).setMinimumSize(new Dimension(10, 10));
		}

		ZMenuBar menuBar = createMenuBar();
		if (menuBar != null)
		{
			setJMenuBar(menuBar);
		}

		setStatusBar(m_instantHelpView);
		setInstantHelpManager(null);

		addWindowListener(new WindowAdapter()
		{
			public void windowActivated(WindowEvent e)
			{
				WindowManager.setActiveWindow(DefaultDialog.this);
			}

			public void windowClosing(WindowEvent e)
			{
				checkClose();
			}
		});

		// nouvelle gestion du passage de focus
		setFocusTraversalPolicy(new DefaultLayoutTraversalPolicy());
	}

	protected void setCloseOnESC()
	{
		WindowHelper.setCloseOnESC(this);
	}

	protected ZMenuBar createMenuBar()
	{
		return null;
	}

	// removed the commented method setPanelView(PanelView view)

	public void setPanelView(PanelView view)
	{
		m_panelView = view;
		if (m_panelView != null)
		{
			setTitle(m_panelView.getTitle());
			setInstantHelpManager(m_panelView.getInstantHelpManager());
		} else
		{
			setInstantHelpManager(null);
		}
	}

	public boolean canClose()
	{
		if (m_panelView == null)
		{
			return true;
		}

		if (m_DEBUG)
		{
			System.out.println("canClose " + getTitle());
		}

		if (m_panelView != null && !m_panelView.canClose())
		{
			return false;
		}

		setPanelView(null);
		return true;
	}

	public void dispose()
	{
		if (checkClose())
		{
			DefaultFrame.disposeMenuBar(getJMenuBar());
			super.dispose();
		}
	}

	private boolean checkClose()
	{
		if (m_DEBUG)
		{
			System.out.println("checkClose" + getTitle());
		}
		if (canClose())
		{
			DefaultDialog.this.setDefaultCloseOperation(ZDialog.DISPOSE_ON_CLOSE);
			return true;
		} else
		{
			DefaultDialog.this.setDefaultCloseOperation(ZDialog.DO_NOTHING_ON_CLOSE);
			return false;
		}
	}

	public void setInstantHelpManager(InstantHelpManager instantHelpManager)
	{
		m_instantHelpManager = instantHelpManager;
		boolean instantHelpManagerReady = (instantHelpManager != null) && (!instantHelpManager.isEmpty());
		if (instantHelpManagerReady)
		{
			instantHelpManager.setInstantHelpView(m_instantHelpView);
		}
		m_instantHelpView.setVisible(instantHelpManagerReady);

		if (m_instantHelpManager != null)
		{
			m_instantHelpManager.updateInstantHelp();
		}
	}

	/**
	 * @return
	 */
	public InstantHelpManager getInstantHelpManager()
	{
		return m_instantHelpManager;
	}

	public void changeContent(ZPanel panel)
	{
		getContentPane().removeAll();

		setStatusBar(m_statusBar);

		if (panel != null)
		{
			getContentPane().add(panel, BorderLayout.CENTER);
		}
		if (panel instanceof PanelView)
		{
			setPanelView((PanelView) panel);
		} else
		{
			setPanelView(null);
		}

		validate();
		repaint();
	}

	public void setStatusBar(StatusBar statusBar)
	{
		m_statusBar = statusBar;
		getContentPane().add(statusBar, BorderLayout.SOUTH);
	}

	public StatusBar getStatusBar()
	{
		return m_statusBar;
	}

	public static DefaultDialog createDialog(Window parent, String title)
	{
		DefaultDialog dialog = null;

		if (parent instanceof Frame)
		{
			dialog = new DefaultDialog((Frame) parent, title);
		} else if (parent instanceof Dialog)
		{
			dialog = new DefaultDialog((Dialog) parent, title);
		} else
		{
			throw new TechnicalException("the dialog can not be opened because of the parent is invalid.");
		}

		return dialog;
	}

	public static DefaultDialog createDefaultDialog(Window parent, PanelView view)
	{
		DefaultDialog dialog = null;

		if (parent instanceof Frame)
		{
			dialog = new DefaultDialog((Frame) parent, view);
		} else if (parent instanceof Dialog)
		{
			dialog = new DefaultDialog((Dialog) parent, view);
		} else
		{
			throw new TechnicalException("the dialog can not be opened because of the parent is invalid.");
		}

		return dialog;
	}

	/**
	 * @return
	 */
	public InstantHelpPanel getInstantHelpView()
	{
		return m_instantHelpView;
	}
}