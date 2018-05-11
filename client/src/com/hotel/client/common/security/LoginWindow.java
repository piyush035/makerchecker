/*
 * 
 */
package com.hotel.client.common.security;

import java.awt.AWTEvent;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import com.hotel.base.common.domain.user.dto.UserLoginDto;
import com.hotel.base.common.tech.exception.user.ConnectionSimultaneousException;
import com.hotel.base.common.tech.exception.user.ConnexionException;
import com.hotel.base.common.tech.exception.user.DesactivationException;
import com.hotel.base.common.tech.exception.user.LoginIncorrectException;
import com.hotel.base.common.tech.exception.user.PasswordIncorrectException;
import com.hotel.client.common.message.MessageConstants;
import com.rennover.client.framework.widget.base.ZButton;
import com.rennover.client.framework.widget.base.ZFrame;
import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.base.ZPasswordField;
import com.rennover.client.framework.widget.field.InputTextField;
import com.rennover.client.framework.widget.icon.IconFactory;
import com.rennover.client.framework.widget.panel.FieldPanel;
import com.rennover.client.framework.window.WindowManager;
import com.rennover.hotel.common.exception.TechnicalException;
import com.rennover.hotel.common.log.Logger;

/**
 * @author Piyush
 * 
 */
public class LoginWindow extends ZFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3963827456720707417L;

	/** */
	public static final String LOGO_NAME = "logos/Hotel.jpg";

	/** */
	public static final String MSG_CONNEXION_SIMULTANEE = "This user account is being used by another person.";

	/** */
	public static final String MSG_INITIALISATION_INCORRECT = "Initialization error. \n Check the presence of configuration files \n before restarting the application.";

	/** */
	public static final String MSG_SERVEUR_NON_TROUVE = "Communication error with the server. \n Please check availability or try again later.";

	/** */
	private InputTextField loginField = new InputTextField();

	/** */
	private ZPasswordField passwordField = new ZPasswordField();

	/** */
	private ZButton validateButton;

	/** */
	private ZButton cancelButton;

	/** */
	// private ZButton changePasswordButton;

	/** */
	private boolean logged = false;

	/** */
	private FieldPanel panel = new FieldPanel();

	/** */
	int tabCount = 0;
	/**	 */
	private static int nbConnection = 0;

	/**
	 * Constructor for LoginWindow.
	 * 
	 * @throws HeadlessException
	 */
	public LoginWindow(String defaultLogin, String defaultPassword)
			throws HeadlessException {
		setIconImage(IconFactory.getImage(LOGO_NAME));
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				Thread.currentThread().setContextClassLoader(
						LoginWindow.class.getClassLoader());
			}
		});

		init();
		loginField.setText(defaultLogin);
		passwordField.setText(defaultPassword);
		//loginField.setUseUpperCase(true);
		// Method is used to disable the windows default keys like
		setFocusTraversalKeysEnabled(false);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#processKeyEvent(java.awt.event.KeyEvent) Method
	 * created for trapping the tab keys
	 */
	private void setTabTrapping() {
		Toolkit.getDefaultToolkit().getSystemEventQueue()
				.push(new EventQueue() {
					/*
					 * (non-Javadoc)
					 * 
					 * @see java.awt.EventQueue#dispatchEvent(java.awt.AWTEvent)
					 */
					protected void dispatchEvent(AWTEvent event) {
						if (event instanceof KeyEvent) {
							KeyEvent keyEvent = (KeyEvent) event;
							int keyCode = keyEvent.getKeyCode();
							if (keyCode == KeyEvent.VK_TAB) {
								tabCount++;
								switch (tabCount) {
								case 0:
									loginField.setFocusable(true);
									break;
								case 1:
									passwordField.setFocusable(true);
									break;
								case 2:
									validateButton.setFocusable(true);
									break;
								case 3:
									cancelButton.setFocusable(true);
									break;
								/*
								 * case 4:
								 * changePasswordButton.setFocusable(true);
								 */
								default:
									loginField.setFocusable(true);
								}

							}
							// Due to comment change password field
							// if (tabCount == 4) {
							if (tabCount == 4) {
								tabCount = 0;
							}
						}
						super.dispatchEvent(event);
					}

				});
	}

	/**
	 * Method init.
	 */
	private void init() {
		setResizable(false);
		setTitle("HOTEL");
		GridBagConstraints gb;
		getContentPane().setLayout(new GridBagLayout());
		gb = new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
				new Insets(4, 4, 4, 4), 0, 0);
		getContentPane().add(composeLogoLabel(), gb);
		gb = new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.SOUTH,
				GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0);
		getContentPane().add(composePanel(), gb);
		setTabTrapping();
		pack();
		centerToScreen();
	}

	/*
	 * @return ZLabel
	 */
	private ZLabel composeLogoLabel() {
		ZLabel logoLabel;
		ImageIcon agefosLogo = new ImageIcon(IconFactory.getImage(LOGO_NAME));
		logoLabel = new ZLabel(agefosLogo, ZLabel.LEFT);
		return logoLabel;
	}

	/*
	 * 
	 * @return ZPanel
	 */
	private ZPanel composePanel() {
		loginField.setFocusable(true);
		panel.addField("User Name", loginField);
		panel.addComponent("Password", passwordField);

		loginField.addActionListener(new AbstractAction() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6964593076113138513L;

			public void actionPerformed(ActionEvent e) {
				doValidate();
			}

		});

		passwordField.addActionListener(new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 8348311047482053675L;

			public void actionPerformed(ActionEvent e) {
				doValidate();
			}
		});
		// Code added to trap tab keys (Global buttons has been set)
		validateButton = panel.addButtonAction("Validate");
		cancelButton = panel.addButtonAction("Cancel");
		// changePasswordButton = panel.addButtonAction("Change Password");

		validateButton.setAction(new AbstractAction("Validate") {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8442699396333014155L;

			public void actionPerformed(ActionEvent e) {
				doValidate();
			}
		});

		// Submit is the Default button
		cancelButton.setAction(new AbstractAction("Cancel")

		{
			/**
			 * 
			 */
			private static final long serialVersionUID = -3741024647295309251L;

			public void actionPerformed(ActionEvent e) {
				doCancel();
			}
		});

		return panel;
	}

	/**
	 * Get Login name.
	 * 
	 * @return String
	 */
	public String getLogin() {
		return loginField.getText();
	}

	/**
	 * Get the password.
	 * 
	 * @return String
	 */
	public String getPassword() {
		return new String(passwordField.getPassword());
	}

	/**
	 * 
	 */
	public void doValidate() {

		if (checkLogin(false)) {
			dispose();
		}
	}

	/**
	 * 
	 * @param isChangementPassword
	 * @return
	 */
	private boolean checkLogin(boolean isChangementPassword) {

		boolean exit = false;
		String msgErreur = null;
		Throwable error = null;
		try {
			nbConnection++;
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Thread.currentThread().setContextClassLoader(
					UserContextManager.class.getClassLoader());
			UserContextManager.login(getLogin(), getPassword(), nbConnection);
			nbConnection = 0;
			UserLoginDto userLogin = UserContextManager.getUserContext()
					.getUserLogin();

			/*
			 * String messageInformation = null;
			 * 
			 * if (!isChangementPassword &&
			 * DateHelper.compareAvecDateCourante(compteUtilisateur
			 * .getDateExpirationMdP()) < 0) { messageInformation =
			 * MessageConstants.MSG_091; } else if (!isChangementPassword &&
			 * OrigineMotDePasse
			 * .SYSTEME.equals(compteUtilisateur.getOrigineMotDePasse())) {
			 * messageInformation = MessageConstants.MSG_092; }
			 * 
			 * if (!PropertyHelper.isNull(messageInformation)) {
			 * WindowManager.showInformationMessage(messageInformation, this);
			 * ChangePasswordPanelController changePasswordPanel = new
			 * ChangePasswordPanelController();
			 * WindowManager.showAsDialog(changePasswordPanel, this);
			 * 
			 * if (!changePasswordPanel.isValidated()) {
			 * UserContextManager.logout(); return false; } }
			 */
			logged = true;
		} catch (LoginIncorrectException e) {
			error = e;
			if (nbConnection == UserContextManager.NB_MAX_CONNECTIONS) {
				msgErreur = MessageConstants.MSG_094;
				exit = true;
			} else {
				msgErreur = MessageConstants.MSG_093;
			}
		} catch (PasswordIncorrectException e) {
			error = e;
			if (nbConnection == UserContextManager.NB_MAX_CONNECTIONS) {
				msgErreur = MessageConstants.MSG_096;
				exit = true;
			} else {
				msgErreur = MessageConstants.MSG_095;
			}
		} catch (DesactivationException e) {
			error = e;
			msgErreur = MessageConstants.MSG_097;
			exit = true;
		} catch (ConnectionSimultaneousException e) {
			error = e;
			msgErreur = MSG_CONNEXION_SIMULTANEE;
			// Apparently this case error is not taken into account the max # of
			// connection attempts.
			nbConnection--;
		} catch (ConnexionException e) {
			error = e;
			msgErreur = e.getMessage();
			exit = true;
		} catch (ExceptionInInitializerError e) {

			error = e;
			msgErreur = MSG_INITIALISATION_INCORRECT;
			exit = true;

		} catch (TechnicalException e) {
			error = e;
			msgErreur = MSG_SERVEUR_NON_TROUVE;
		} finally {
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		if (msgErreur != null) {
			Logger.error(this, msgErreur, error);
			WindowManager.showErrorMessage(msgErreur, this);
			if (exit) {
				System.exit(1);
			}
		}
		return (msgErreur == null);
	}

	/**
	 * 
	 */
	public void doCancel() {
		System.exit(0);
	}

	/**
	 * 
	 */
	public void doChangePassword() {
	}

	/**
	 * Returns the logged.
	 * 
	 * @return boolean
	 */
	public boolean isLogged() {
		return logged;
	}
}