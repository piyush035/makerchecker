
package com.rennover.client.framework.window;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpManager;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpPanel;
import com.rennover.client.framework.widget.base.ZFrame;
import com.rennover.client.framework.widget.base.ZMenuBar;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.icon.IconFactory;
import com.rennover.client.framework.widget.panel.StatusBar;
import com.rennover.client.framework.widget.panel.ToolBar;

/**
 * @author tcaboste
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type
 * comments go to Window>Preferences>Java>Code Generation.
 */
public class DefaultFrame extends ZFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5555956924127003706L;

	private static boolean s_askForExit = true;

	private static List s_frameList = new ArrayList();

	private boolean m_DEBUG = false;

	private PanelView m_panelView = null;

	private InstantHelpPanel m_instantHelpView = new InstantHelpPanel();

	private InstantHelpManager m_instantHelpManager = new InstantHelpManager();

	private ToolBar m_toolBar = null;

	private StatusBar m_statusBar = null;

	public DefaultFrame(String title) throws HeadlessException
	{
		super(title);
		init();
	}

	public DefaultFrame(PanelView view) throws HeadlessException
	{
		super(view.getTitle());

		init();

		changeContent(view);
	}

	private void init()
	{
		setIconImage(IconFactory.getImage("logos/frame_icon.gif"));
		s_frameList.add(DefaultFrame.this);

		getContentPane().setLayout(new BorderLayout());
		setSize(1024, 768); // taille de la maquette
		centerToScreen();

		// NB: permet le redimensionnement de la fenêtre dans le look Alloy
		Container contentPane = getContentPane();
		if (contentPane instanceof JComponent)
		{
			((JComponent) contentPane).setMinimumSize(new Dimension(30, 20));
		}

		ZMenuBar menuBar = createMenuBar();
		if (menuBar != null)
		{
			setJMenuBar(menuBar);
		}

		ToolBar toolBar = createToolBar();
		if (toolBar != null)
		{
			setToolBar(toolBar);
		}

		setStatusBar(m_instantHelpView);

		setInstantHelpManager(null);

		if (isFirstFrame())
		{
			setDefaultCloseOperation(ZFrame.EXIT_ON_CLOSE);
		} else
		{
			setDefaultCloseOperation(ZFrame.DISPOSE_ON_CLOSE);
		}

		addWindowListener(new WindowAdapter()
		{
			public void windowActivated(WindowEvent e)
			{
				WindowManager.setActiveWindow(DefaultFrame.this);
			}

			public void windowClosing(WindowEvent e)
			{
				checkCloseAndExit();
			}
		});

		setCloseOnESC();

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

	protected ToolBar createToolBar()
	{
		return null;
	}

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

	public boolean confirmExit()
	{
		return true;
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
		if (!m_panelView.canClose())
		{
			return false;
		}

		setPanelView(null);
		return true;
	}

	public void dispose()
	{
		if (checkCloseAndExit())
		{
			disposeMenuBar(getJMenuBar());
			super.dispose();
		}
	}

	protected static void disposeMenuBar(JMenuBar menuBar)
	{
		if (menuBar == null)
		{
			return;
		}

		for (int i = 0; i < menuBar.getMenuCount(); i++)
		{
			JMenu menu = menuBar.getMenu(i);
			disposeMenu(menu);
		}
	}

	private static void disposeMenu(JMenu menu)
	{
		Component[] menus = menu.getMenuComponents();
		for (int i = 0; i < menus.length; i++)
		{
			Component component = menus[i];
			if (component instanceof JMenu)
			{
				JMenu subMenu = (JMenu) component;
				disposeMenu(subMenu);
			} else if (component instanceof JMenuItem)
			{
				JMenuItem menuItem = (JMenuItem) component;

				menuItem.setAction(null);
			}
		}
	}

	private boolean checkCloseAndExit()
	{
		if (m_DEBUG)
		{
			System.out.println("checkCloseAndExit" + getTitle());
		}

		boolean close = true;

		boolean isFirst = isFirstFrame();
		if (isFirst)
		{
			boolean exitOk = true;
			if (isAskForExit())
			{
				exitOk = confirmExit();
			}

			if (exitOk)
			{
				if (closeAllFrames())
				{
					DefaultFrame.this.setDefaultCloseOperation(ZFrame.EXIT_ON_CLOSE);
					try
					{
						doOnExitApplication();
					} catch (Exception ex)
					{
						// rien
					}
					System.exit(0);
				} else
				{
					close = false;
				}
			} else
			{
				close = false;
			}
		} else
		{
			if (canClose())
			{
				close = true;
			} else
			{
				close = false;
			}
		}

		if (close)
		{
			DefaultFrame.this.setDefaultCloseOperation(ZFrame.DISPOSE_ON_CLOSE);
			s_frameList.remove(DefaultFrame.this);
		} else
		{
			DefaultFrame.this.setDefaultCloseOperation(ZFrame.DO_NOTHING_ON_CLOSE);
		}

		return close;
	}

	public void doOnExitApplication()
	{
	}

	private boolean isFirstFrame()
	{
		return this == s_frameList.get(0);
	}

	public static DefaultFrame getFirstFrame()
	{
		return (DefaultFrame) s_frameList.get(0);
	}

	/**
	 * 
	 */
	public static void exitApplication()
	{
		DefaultFrame mainFrame = getFirstFrame();
		mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * Method exitApplication.
	 */
	public boolean closeAllFrames()
	{
		if (m_DEBUG)
		{
			System.out.println("closeAllFrames" + getTitle());
		}
		boolean exit = true;

		try
		{
			for (int i = s_frameList.size() - 1; i >= 0; i--)
			{
				DefaultFrame frame = (DefaultFrame) s_frameList.get(i);
				if (frame.isFirstFrame())
				{
					break;
				}

				if (frame.canClose())
				{
					frame.dispose();
				} else
				{
					exit = false;
					break;
				}
			}

			return exit;
		} finally
		{
		}
	}

	public void setInstantHelpManager(InstantHelpManager instantHelpManager)
	{
		m_instantHelpManager = instantHelpManager;

		boolean instantHelpManagerReady = instantHelpManager != null;
		if (instantHelpManagerReady)
		{
			instantHelpManager.setInstantHelpView(m_instantHelpView);
		}

		if (m_instantHelpManager != null)
		{
			m_instantHelpManager.updateInstantHelp();
		}
	}

	public void changeContent(ZPanel panel)
	{
		getContentPane().removeAll();
		//setToolBar(m_toolBar);
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

	/**
	 * @return
	 */
	public InstantHelpPanel getInstantHelpView()
	{
		return m_instantHelpView;
	}

	/**
	 * @return
	 */
	public InstantHelpManager getInstantHelpManager()
	{
		return m_instantHelpManager;
	}

	/**
	 * Returns the askForExit.
	 * 
	 * @return boolean
	 */
	public static boolean isAskForExit()
	{
		return s_askForExit;
	}

	/**
	 * Sets the askForExit.
	 * 
	 * @param askForExit
	 *            The askForExit to set
	 */
	public static void setAskForExit(boolean askForExit)
	{
		s_askForExit = askForExit;
	}

	/**
	 * @return
	 */
	public ToolBar getToolBar()
	{
		return m_toolBar;
	}

	/**
	 * @param bar
	 */
	public void setToolBar(ToolBar toolBar)
	{
		if (toolBar == null && m_toolBar != null)
		{
			getContentPane().remove(m_toolBar);
		}

		m_toolBar = toolBar;
		if (m_toolBar != null)
		{
			getContentPane().add(m_toolBar, BorderLayout.NORTH);
		}
	}

}