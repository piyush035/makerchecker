/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */
package com.rennover.client.framework.window;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.Collections;
import java.util.List;

import javax.swing.FocusManager;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import com.rennover.client.framework.message.MessageFactory;
import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.widget.base.ZDialog;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.base.ZScrollPane;
import com.rennover.client.framework.widget.base.ZTextArea;
import com.rennover.client.framework.widget.panel.DefaultValidationPanel;
import com.rennover.client.framework.widget.panel.InformationDialog;
import com.rennover.client.framework.widget.panel.SimpleDialog;
import com.rennover.client.framework.widget.panel.ValidationDialog;
import com.rennover.client.framework.widget.panel.ValidationPanel;
import com.rennover.hotel.common.exception.ExceptionBaseInterface;
import com.rennover.hotel.common.exception.ExceptionHelper;
import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.log.Logger;
import com.rennover.hotel.common.properties.CommonProperties;

/**
 * This class provides useful services for managing windows  * (Especially for
 * dialog boxes) .  *  *
 * <p>
 *  * In particular :  *  *
 * <ul>
 *  *
 * <li>Locking / unlocking of a frame or a dialog with a  * User feedback (
 * hourglass, progress bar , etc. .</ Li>
 *  *
 * <li>Determining the relative frame or dialog</ li>
 *  *
 * <li>Determination graphic details of a dialog with  * Compared to the parent
 * frame or dialog</ li>
 *  *
 * </ Ul>
 *  *
 * </ P>
 *  *  *
 * <p>
 *  * Note by Piyush : beware , there is a bug in the display of the hourglass.  *
 * Although the code requires that the mouse cursor turns into hourglass  * For
 * the dialog box ( the one that displays the progress bar ) and  * For the
 * window below , in practice , the slider becomes an hourglass  * On the dialog
 * box . <br>
 *  * Even more annoying : when you do not call up bar  * Progression (ie , the
 * dialog is not displayed ) , the cursor  * Never becomes an hourglass. This is
 * explained by the fact that the code appears  * A dialog zero size .
 * Filmography  * It should therefore find a way to display an hourglass above
 * the  * Main window. Perhaps by observing the movements of the mouse ?  *
 * </ P>
 */
public final class WindowManager {
	protected static String s_defaultTitle = "<Titre par défaut>";

	public static final int YES_OPTION = JOptionPane.YES_OPTION;

	public static final int NO_OPTION = JOptionPane.NO_OPTION;

	public static final int CANCEL_OPTION = JOptionPane.CANCEL_OPTION;

	/**
	 * isMessageNeedToShowed  when currently
	 * loggedin user gets message from Administrator (MSG_667) we need to close
	 * all Frames and Panel used by user , so no message needs to shown ,after
	 * MSG_667 is shown
	 */
	public static boolean s_isMessageNeedToShowed = false;// 8403

	

	private static final Object[] OPTIONS_FERMER = { "Close" };

	/**
	 * Constructeur privé, car cette classe doit être seulement utilisée à
	 * travers ses méthodes static.
	 */
	private WindowManager() {
	}

	/**
	 * Retourne la hauteur de la fenêtre qui contient le composant spécifié.
	 * 
	 * @param childcomponent
	 *            un composant faisant partie d'un fenêtre dont on veut
	 *            connaître la taille; si null, retourne 768
	 */
	public static int getWindowHeight(Component childComponent) {
		Window window = getOwningWindow(childComponent);

		if (null != window) {
			return window.getHeight();
		} else {
			return 768;
		}
	}

	/**
	 * Retourne la largeur de la fenêtre qui contient le composant spécifié.
	 * 
	 * @param childcomponent
	 *            un composant faisant partie d'un fenêtre dont on veut
	 *            connaître la taille; si null, retourne 1024
	 */
	public static int getWindowWidth(Component childComponent) {
		Window window = getOwningWindow(childComponent);

		if (null != window) {
			return window.getWidth();
		} else {
			return 1024;
		}
	}

	/**
	 * Retourne la coordonnée horizontale du coin en haut à gauche de la fenêtre
	 * qui contient le composant spécifié.
	 */
	public static int getWindowX(Component childcomponent) {
		Window window = getOwningWindow(childcomponent);

		if (null != window) {
			return (int) window.getLocation().getX();
		} else {
			return 0;
		}
	}

	/**
	 * Retourne la coordonnée verticale du coin en haut à gauche de la fenêtre
	 * qui contient le composant spécifié.
	 */
	public static int getWindowY(Component childcomponent) {
		Window window = getOwningWindow(childcomponent);

		if (null != window) {
			return (int) window.getLocation().getY();
		} else {
			return 0;
		}
	}

	public static Dimension getScreenSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		return screenSize;
	}

	public static Dimension getClientScreenSize() {
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		Dimension screenSize = ge.getMaximumWindowBounds().getSize();
		return screenSize;
	}

	/**
	 * Bloque toute interaction de l'utilisateur avec la fenêtre (Frame ou
	 * Dialog) qui contient le composant spécifié, affiche une barre de
	 * progression et transforme le curseur en sablier.
	 * 
	 * @param childComponent
	 *            un composant contenu (potentiellement à travers une hiérarchie
	 *            de panels) dans le Frame ou le Dialog qui sera bloqué
	 */
	public static void freeze(JComponent childComponent) {
		WindowFreezer.freeze(childComponent);
	}

	public static void freeze(JComponent childComponent, int feedBackStyle) {
		WindowFreezer.freeze(childComponent, feedBackStyle);
	}

	public static void freeze(Window window) {
		WindowFreezer.freeze(window);
	}

	public static void freeze(Window window, int feedBackStyle) {
		WindowFreezer.freeze(window, feedBackStyle);
	}

	/**
	 * Dégèle la fenêtre du composant passé en paramètre
	 * 
	 * @param childComponent
	 */
	public static void unfreeze(JComponent childComponent) {
		WindowFreezer.unfreeze(childComponent);
	}

	/**
	 * Dégèle la fenêtre passée en paramètre
	 * 
	 */
	public static void unfreeze(Window window) {
		WindowFreezer.unfreeze(window);
	}

	/**
	 * Retourne la coordonnée horizontale d'une boite de dialogue qui aurait la
	 * largeur spécifiée. Cette coordonnée sera calculée de sorte que la boite
	 * de dialogue sera centrée par rapport à la fenêtre principale.
	 */
	private static int getDialogX(int width, Component childComponent) {
		return (int) (getWindowX(childComponent)
				+ getWindowWidth(childComponent) - width) / 2;
	}

	/**
	 * Retourne la coordonnée verticale d'une boite de dialogue qui aurait la
	 * largeur spécifiée. Cette coordonnée sera calculée de sorte que la boite
	 * de dialogue sera centrée par rapport à la fenêtre principale.
	 */
	private static int getDialogY(int height, Component childComponent) {
		return (int) (getWindowY(childComponent)
				+ getWindowHeight(childComponent) - height) / 2;
	}

	/**
	 * Retourne les coordonnées pour un dialog sous forme d'un Rectangle.
	 * 
	 * <p>
	 * Ces coordonnées sont calculées de façon à ce que le dialog apparaisse au
	 * milieu du dialog ou de la frame parent.
	 * </p>
	 */
	public static Rectangle getDialogCoordinates(int width, int height,
			Component childComponent) {
		int x = getDialogX(width, childComponent);
		int y = getDialogY(height, childComponent);

		return new Rectangle(x, y, width, height);
	}

	/**
	 * Retourne la Frame ou le Dialog contenant le composant spécifié.
	 */
	public static Window getOwningWindow(Component childComponent) {
		Component windowCandidate = childComponent;

		while (windowCandidate != null) {
			if (windowCandidate instanceof Frame
					|| windowCandidate instanceof Dialog) {
				return (Window) windowCandidate;
			}

			if (windowCandidate instanceof JPopupMenu) {
				windowCandidate = ((JPopupMenu) windowCandidate).getInvoker();
			} else {
				windowCandidate = windowCandidate.getParent();
			}
		}

		return null;
	}

	public static boolean isParentComponentOf(Component parentCandidate,
			Component child) {
		boolean result = false;
		Component parent = child;

		while (parentCandidate != null) {
			if (parentCandidate.equals(parent)) {
				result = true;
				break;
			}

			if (parentCandidate instanceof Frame
					|| parentCandidate instanceof Dialog) {
				break;
			}

			if (parent instanceof JPopupMenu) {
				parent = ((JPopupMenu) parent).getInvoker();
			} else {
				parent = parent.getParent();
			}
		}

		return result;
	}

	/**
	 * Retourne la Frame contenant le composant spécifié. <br>
	 * C'est une méthode pratique qui ne doit être utilisée que si l'on est
	 * <i>certain</i> que la fenêtre content le composant est bien une Frame.
	 * 
	 * @exception ClassCastException
	 *                si la fenêtre contenant le composant n'est pas une Frame
	 */
	public static Frame getOwningFrame(Component childComponent)
			throws ClassCastException {
		return (Frame) getOwningWindow(childComponent);
	}

	/**
	 * Retourne le Dialog contenant le composant spécifié. <br>
	 * C'est une méthode pratique qui ne doit être utilisée que si l'on est
	 * <i>certain</i> que la fenêtre content le composant est bien un Dialog.
	 * 
	 * @exception ClassCastException
	 *                si la fenêtre contenant le composant n'est pas un Dialog
	 */
	public static Dialog getOwningDialog(Component childComponent)
			throws ClassCastException {
		return (Dialog) getOwningWindow(childComponent);
	}

	/**
	 * Affiche un message de confirmation avec les options OUI et NON
	 * 
	 * @return vrai si l'utilisateur confirme
	 */
	public static boolean showConfirmationMessage(String message,
			Component parent) {
		parent = getOwningWindow(parent);
		// 8403
		if (s_isMessageNeedToShowed) {
			return true;
		}
		int res = JOptionPane.showConfirmDialog(parent, // parent component
				MessageFactory.translate(message), // dialog message
				getDefaultTitle(), // dialog title
				JOptionPane.YES_NO_OPTION, // option type
				JOptionPane.QUESTION_MESSAGE // message type
				);

		return (res == JOptionPane.YES_OPTION);
	}

	/**
	 * Affiche un message de confirmation avec les options OUI, NON et ANNULER
	 * pour OUI retourne YES_OPTION pour NON retourne NO_OPTION pour ANNULER
	 * retourne CANCEL_OPTION
	 * 
	 * @return option choisie
	 */
	public static int showConfirmationCancelMessage(String message,
			Component parent) {
		parent = getOwningWindow(parent);
		if (s_isMessageNeedToShowed) {
			return JOptionPane.YES_OPTION;
		}
		int res = JOptionPane.showConfirmDialog(parent, // parent component
				MessageFactory.translate(message), // dialog message
				getDefaultTitle(), // dialog title
				JOptionPane.YES_NO_CANCEL_OPTION, // option type
				JOptionPane.QUESTION_MESSAGE // message type
				);

		return res;
	}

	/**
	 * Affiche un message de confirmation avec les options passées en paramètres
	 * 
	 * @return option choisie
	 */
	public static int showOptionMessage(String message, Object[] options,
			Component parent) {
		parent = getOwningWindow(parent);
		if (s_isMessageNeedToShowed) {
			return JOptionPane.YES_OPTION;
		}
		int res = JOptionPane.showOptionDialog(parent,
				MessageFactory.translate(message), getDefaultTitle(),
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[0]);
		return res;
	}

	/**
	 * permet d'afficher des messages d'erreur avec l'aspect standard de swing
	 */
	public static void showErrorMessage(String message, Component parent) {
		// on enregistre dans le log le message technique
		// Logger.GUI.error(message);
		// Affichage de la fenêtre
		parent = getOwningWindow(parent);
		if (parent == null) {
			parent = WindowManager.getActiveWindow();
		}
		if (!s_isMessageNeedToShowed) {
			JOptionPane.showOptionDialog(parent,
					MessageFactory.translate(message), getDefaultTitle(),
					JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
					null, OPTIONS_FERMER, OPTIONS_FERMER[0]);
		}

	}

	/**
	 * permet d'afficher des messages d'alerte avec l'aspect standard de swing
	 */
	public static void showWarningMessage(String message, Component parent) {
		parent = getOwningWindow(parent);
		if (!s_isMessageNeedToShowed) {
			JOptionPane.showOptionDialog(parent,
					MessageFactory.translate(message), getDefaultTitle(),
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, OPTIONS_FERMER, OPTIONS_FERMER[0]);
		}
	}

	public static void showInformationMessage(String message, Component parent) {
		parent = getOwningWindow(parent);
		// Added for Defect 9539
		// Takes the active window as the parent
		if (parent == null) {
			parent = WindowManager.getActiveWindow();
		}
		if (!s_isMessageNeedToShowed) {
			JOptionPane.showOptionDialog(parent,
					MessageFactory.translate(message), getDefaultTitle(),
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, OPTIONS_FERMER,
					OPTIONS_FERMER[0]);
		}
	}

	public static void showExceptionMessage(Throwable throwable,
			Component parent) {
		ExceptionManager exceptionManager = getExceptionManager();
		if (exceptionManager != null) {
			exceptionManager.showException(throwable, parent);
		} else {
			WindowManager._showExceptionMessage(throwable, parent);
		}

	}

	private static ExceptionManager s_exceptionManager = null;

	public static void _showExceptionMessage(Throwable throwable,
			Component parent) {
		Logger.error(WindowManager.class, throwable);

		String msg = "Un problème technique est survenu.\nVeuillez contacter votre administrateur système.\n";

		if (throwable instanceof ExceptionBaseInterface) {
			ExceptionBaseInterface exc = (ExceptionBaseInterface) throwable;
			if (exc.isUserShowable()) {
				msg = exc.getUserShowableMessage();
			}
		}

		if (CommonProperties.isApplicationModeDebug()) {
			showDetailledException(msg, throwable, parent);
		} else {
			showErrorMessage(msg, parent);
		}

	}

	// removed the commented method showExceptionMessageWithoutTrace(Throwable
	// throwable, Component parent), showExceptionMessageWithTrace(Throwable
	// throwable, Component parent)

	public static void showDetailledException(String message,
			Throwable throwable, Component parent) {
		// on enregistre dans le log le message technique
		// Affichage de la fenêtre
		parent = getOwningWindow(parent);
		if (parent == null) {
			parent = WindowManager.getActiveWindow();
		}

		String[] options = new String[] { "Close", "See details" };
		int optionIndex = JOptionPane.showOptionDialog(parent,
				MessageFactory.translate(message), getDefaultTitle(),
				JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null,
				options, OPTIONS_FERMER[0]);
		if (optionIndex == 1) {
			StringBuffer text = new StringBuffer();
			text.append(throwable.getMessage());
			text.append("\n");
			text.append(ExceptionHelper.getInternalStackTrace(throwable));
			showExceptionErrorDialog(message, getDefaultTitle(),
					text.toString(), parent);
		}
	}

	/**
	 * Affiche une boîte de dialogue où l'utilisateur peut saisir un texte à
	 * l'aide d'un TextArea. La boîte contient un bouton Valider et un bouton
	 * Annuler
	 * 
	 * @param message
	 *            message au dessus de la textarea
	 * @param title
	 *            titre de la boîte de dialogue
	 * @param maxChars
	 *            nombre de caractères maximum saisissables
	 * @param nbLines
	 *            nombre de lignes à affichés (taille du textArea)
	 * @param parent
	 *            composant parent de la boîte de dialogue
	 * @return le texte saisi si l'utilisateur appuie sur Valider sinon null
	 */
	public static void showExceptionErrorDialog(String message, String title,
			String text, Component parent) {
		parent = getOwningWindow(parent);
		ZTextArea textArea = new ZTextArea();
		textArea.setText(text);
		textArea.setCaretPosition(0);
		textArea.setEditable(false);
		Object[] messagedialog = { MessageFactory.translate(message),
				new ZScrollPane(textArea, 10) };
		Object[] options = OPTIONS_FERMER;

		JOptionPane pane = new JOptionPane();

		pane.setMessageType(JOptionPane.ERROR_MESSAGE); // dialog icon
		pane.setMessage(messagedialog); // dialog message
		pane.setOptions(options); // button bar options
		pane.setInitialValue(options[0]); // principal option

		JDialog dialog = pane.createDialog(parent,
				MessageFactory.translate(title));

		dialog.setResizable(true);
		dialog.pack();
		dialog.setVisible(true);
	}

	/**
	 * Affiche une dialog permettant de saisir un libellé Renvoie null si
	 * l'utilisateur annule
	 * 
	 * @return le libellé saisi
	 */
	public static Object showInputDialog(String message, String title,
			Component parent) {
		parent = getOwningWindow(parent);
		return (JOptionPane.showInputDialog(parent,
				MessageFactory.translate(message),
				MessageFactory.translate(title), JOptionPane.PLAIN_MESSAGE,
				null, null, null));
	}

	/**
	 * Affiche une boîte de dialogue où l'utilisateur peut saisir un texte à
	 * l'aide d'un TextArea. La boîte contient un bouton Valider et un bouton
	 * Annuler
	 * 
	 * @param message
	 *            message au dessus de la textarea
	 * @param title
	 *            titre de la boîte de dialogue
	 * @param maxChars
	 *            nombre de caractères maximum saisissables
	 * @param nbLines
	 *            nombre de lignes à affichés (taille du textArea)
	 * @param parent
	 *            composant parent de la boîte de dialogue
	 * @return le texte saisi si l'utilisateur appuie sur Valider sinon null
	 */
	public static String showInputTextAreaDialog(String message, String title,
			int maxChars, int nbLines, Component parent) {
		parent = getOwningWindow(parent);
		ZTextArea textArea = new ZTextArea(maxChars);
		textArea.setRows(nbLines);
		Object[] messagedialog = { MessageFactory.translate(message),
				new ZScrollPane(textArea) };
		Object[] options = { "Valider", "Annuler" };

		JOptionPane pane = new JOptionPane();

		pane.setMessage(messagedialog); // dialog message
		pane.setOptions(options); // button bar options
		pane.setInitialValue(options[0]); // principal option

		JDialog dialog = pane.createDialog(parent,
				MessageFactory.translate(title));

		dialog.setResizable(false);
		dialog.setVisible(true);

		return pane.getValue() == options[0] ? textArea.getText() : null;
	}

	/**
	 * Affiche une dialog permettant de sélectionner un objet parmi la liste
	 * passée en paramètre Renvoie null si l'utilisateur annule
	 * 
	 * @return l'objet sélectionné
	 */
	public static Object showSelectionInputDialog(String message, String title,
			List list, Component parent) {
		parent = getOwningWindow(parent);

		if (null == list) {
			list = Collections.EMPTY_LIST;
		}

		return (JOptionPane.showInputDialog(parent,
				MessageFactory.translate(message),
				MessageFactory.translate(title), JOptionPane.PLAIN_MESSAGE,
				null, list.toArray(), null));
	}

	/**
	 * Returns the defaultTitle.
	 * 
	 * @return String
	 */
	public static String getDefaultTitle() {
		return s_defaultTitle;
	}

	/**
	 * Sets the defaultTitle.
	 * 
	 * @param defaultTitle
	 *            The defaultTitle to set
	 */
	public static void setDefaultTitle(String defaultTitle) {
		WindowManager.s_defaultTitle = MessageFactory.translate(defaultTitle);
	}

	/**
	 * @deprecated use showAsDialog(ZPanel view, Component parent) Method
	 *             showDialog.
	 */
	public static void showDialog(PanelView panel) {
		ZDialog dialog = createDialog(panel, null);
		dialog.show();
	}

	/**
	 * Affiche une fenêtre de dialogue modale contenant le panneau passé en
	 * paramètre
	 * 
	 * @param view
	 *            panneau contenant le contenu de la fenêtre
	 * @param parent
	 *            composant parent de la fenêtre
	 */
	public static void showAsDialog(PanelView panel, Component parent) {
		ZDialog dialog = createDialog(panel, parent);
		dialog.show();
	}

	// removed the commented method createDialog(ZPanel panel, Component parent)

	public static ZDialog createDialog(PanelView panel, Component parent) {
		Window window = getOwningWindow(parent);
		ZDialog dialog = DefaultDialog.createDefaultDialog(window, panel);

		dialog.pack();
		dialog.setModal(true);

		dialog.setLocationRelativeTo(parent);
		dialog.centerToParent();

		return dialog;
	}

	/**
	 * Affiche une fenetre de dialogue modale contenant le <code>panel</code>
	 * 
	 * @param contenu
	 *            de la fenetre
	 * @param parent
	 */
	public static void showAsSimpleDialog(ZPanel panel, Component parent) {
		SimpleDialog dialog = createSimpleDialog(panel, parent);
		dialog.show();
	}

	public static SimpleDialog createSimpleDialog(ZPanel panel, Component parent) {
		SimpleDialog dialog;
		Window window = getOwningWindow(parent);

		if (window instanceof Frame) {
			dialog = new SimpleDialog((Frame) window);
		} else if (window instanceof Dialog) {
			dialog = new SimpleDialog((Dialog) window);
		} else {
			throw new TechnicalException(
					"the dialog can not be opened due to an invalid parent");
		}

		dialog.getContentPane().add(panel, BorderLayout.CENTER);
		dialog.setTitle(panel.getName());
		dialog.pack();
		dialog.setModal(true);
		dialog.setLocationRelativeTo(parent);
		dialog.centerToParent();

		return dialog;
	}

	/**
	 * Affiche une fenêtre de dialogue modale contenant le panneau passé en
	 * paramètre
	 * 
	 * @param view
	 *            panneau contenant le contenu de la fenêtre
	 * @param parent
	 *            composant parent de la fenêtre
	 */
	public static boolean showAsValidationDialog(ZPanel panel, Component parent) {
		ValidationDialog dialog = createValidationDialog(
				new DefaultValidationPanel(panel), parent);
		dialog.show();
		return dialog.isValidated();
	}

	public static boolean showAsValidationDialog(ValidationPanel panel,
			Component parent) {
		ValidationDialog dialog = createValidationDialog(panel, parent);
		dialog.show();
		return dialog.isValidated();
	}

	public static ValidationDialog createValidationDialog(
			ValidationPanel panel, Component parent) {
		ValidationDialog dialog;

		Window window = getOwningWindow(parent);

		if (window instanceof Frame) {
			dialog = new ValidationDialog((Frame) window, panel);
		} else if (window instanceof Dialog) {
			dialog = new ValidationDialog((Dialog) window, panel);
		} else {
			throw new IllegalArgumentException(
					"parent must be a frame or a dialog");
		}
		return dialog;
	}

	/**
	 * Affiche une fenêtre de dialogue modale contenant le panneau passé en
	 * paramètre
	 * 
	 * @param view
	 *            panneau contenant le contenu de la fenêtre
	 * @param parent
	 *            composant parent de la fenêtre
	 */
	public static void showAsInformationDialog(ZPanel panel, Component parent) {
		InformationDialog dialog = createInformationDialog(panel, parent);
		dialog.show();
	}

	public static InformationDialog createInformationDialog(ZPanel panel,
			Component parent) {
		InformationDialog dialog;

		Window window = getOwningWindow(parent);

		if (window instanceof Frame) {
			dialog = new InformationDialog((Frame) window);
		} else if (window instanceof Dialog) {
			dialog = new InformationDialog((Dialog) window);
		} else {
			throw new IllegalArgumentException(
					"parent must be a frame or a dialog");
		}

		if (panel != null) {
			dialog.getContentPane().add(panel, BorderLayout.CENTER);
			dialog.setTitle(panel.getName());
		}
		dialog.pack();
		dialog.setModal(true);
		dialog.setLocationRelativeTo(parent);
		dialog.centerToParent();

		return dialog;
	}

	public static void changeFrameContent(JFrame frame, PanelView view) {
		frame.getContentPane().add(view, BorderLayout.CENTER);

		frame.setTitle(view.getTitle());
	}

	/**
	 * Retourne la fenêtre active
	 * 
	 * @return
	 */
	public static Window getActiveWindow() {
		return FocusManager.getCurrentManager().getActiveWindow();
	}

	/**
	 * Affecte la fenêtre actuellement active
	 * 
	 * @param window
	 *            fenêtre active
	 */
	protected static void setActiveWindow(Window window) {
	}

	/**
	 * Initialisation du WindowManager
	 */
	public static void init() {
		KeyboardFocusManager
				.setCurrentKeyboardFocusManager(new DefaultKeyboardFocusManagerPatched());
		WindowHelper.setEnterAsTab();
	}

	/**
	 * @return
	 */
	public static ExceptionManager getExceptionManager() {
		return s_exceptionManager;
	}

	/**
	 * @param manager
	 */
	public static void setExceptionManager(ExceptionManager manager) {
		s_exceptionManager = manager;
	}

}
