package com.rennover.client.framework.window;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import com.rennover.client.framework.widget.base.ZDialog;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class WindowFreezer
{
	/**
	 * Affiche la barre de progression dans une boite de dialogue et change le
	 * curseur en sablier.
	 */
	public static final int DIALOG_AND_HOURGLASS = 0;

	/** Change le curseur en sablier. */
	public static final int HOURGLASS = 1;

	/**
	 * Bloque simplement la fenêtre. Le curseur ne change pas et la boite de
	 * dialogue ne s'affiche pas.
	 */
	public static final int NOTHING = 2;

	/**
	 * Collection de Frame et Dialog qui sont bloqués (c-à-d où l'utilisateur ne
	 * peut pas interagir).
	 */
	private static Map s_frozenDialogs = new HashMap();

	private static Map s_frozenWindows = new HashMap();

	/**
	 * Bloque toute interaction de l'utilisateur avec la fenêtre (Frame ou
	 * Dialog) qui contient le composant spécifié, affiche une barre de
	 * progression et transforme le curseur en sablier.
	 * 
	 * @param childComponent
	 *            un composant contenu (potentiellement à travers une hiérarchie
	 *            de panels) dans le Frame ou le Dialog qui sera bloqué
	 */
	public static void freeze(JComponent childComponent)
	{
		freeze(childComponent, HOURGLASS);
	}

	public static void freeze(JComponent childComponent, int feedBackStyle)
	{
		Window window = WindowManager.getOwningWindow(childComponent);
		freeze(window, feedBackStyle);
	}

	public static void freeze(Window window)
	{
		freeze(window, HOURGLASS);
	}

	/**
	 * Bloque toute interaction de l'utilisateur avec la fenêtre (Frame ou
	 * Dialog) qui contient le composant spécifié. Le "style de blocage" est
	 * spécifié.
	 * 
	 * @param childComponent
	 *            un composant contenu (potentiellement à travers une hiérarchie
	 *            de panels) dans le Frame ou le Dialog qui sera bloqué
	 * @param feedBackStyle
	 *            une des constantes disponibles dans cette classe: {@link
	 *            #NOTHING}, {@link #HOURGLASS} ou {@link #DIALOG_AND_HOURGLASS}
	 */
	public static void freeze(Window window, int feedBackStyle)
	{
		if (window == null)
		{
			return;
		}

		if (!isFrozen(window) || feedBackStyle == NOTHING)
		{
			if (feedBackStyle == HOURGLASS || feedBackStyle == DIALOG_AND_HOURGLASS)
			{
				window.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			}

			// removed the commented if block

			s_frozenWindows.put(window, window);
		}
	}

	// removed the commented method createWaitingDialog(Window parent)

	public static boolean isFrozen(Window window)
	{
		return s_frozenWindows.get(window) != null;
	}

	// removed the commented method freezeOld(Window window, int feedBackStyle)

	/**
	 * Vérifie si la fenêtre qui contient le composant spécifié est bloquée ou
	 * non.
	 */
	public static boolean isFrozen(JComponent childComponent)
	{
		Window window = WindowManager.getOwningWindow(childComponent);

		if (s_frozenWindows.get(window) != null)
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * Dégèle la fenêtre du composant passé en paramètre
	 * 
	 * @param childComponent
	 */
	public static void unfreeze(JComponent childComponent)
	{
		Window window = WindowManager.getOwningWindow(childComponent);
		if (window != null)
		{
			unfreeze(window);
		}
	}

	/**
	 * Dégèle la fenêtre passée en paramètre
	 * 
	 */
	public static void unfreeze(Window window)
	{
		if (isFrozen(window))
		{
			JDialog dialog = (JDialog) s_frozenDialogs.get(window);

			if (dialog != null)
			{
				System.out.println("unfreeze");

				// dialog.setVisible(false);
				s_frozenDialogs.remove(window);
			}

			window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			s_frozenWindows.remove(window);
		}
	}

	// removed the commented class ScreenBlocker

}

class WaitingDialog extends ZDialog
{
	public WaitingDialog(Dialog parent)
	{
		super(parent);
		instanciate();
	}

	public WaitingDialog(Frame parent)
	{
		super(parent);
		instanciate();
	}

	public void instanciate()
	{
		setModal(false);
		setDefaultCloseOperation(ZDialog.DO_NOTHING_ON_CLOSE); // obligatoire a
		// cause de
		// Alt-F4

		setResizable(false);

		JProgressBar progressBar = new JProgressBar(0, 10);
		progressBar.setIndeterminate(true);
		progressBar.setStringPainted(false);

		JPanel progressPanel = new JPanel(new BorderLayout(4, 4));
		progressPanel.setBorder(new EmptyBorder(4, 4, 4, 4));
		progressPanel.add(new JLabel("Longue tâche en cours"), BorderLayout.CENTER);
		progressPanel.add(progressBar, BorderLayout.SOUTH);

		setTitle("Progression...");
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(progressPanel, BorderLayout.CENTER);

		setBounds(0, 0, 200, 100);
		centerToParent();

		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}
}