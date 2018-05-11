package com.hotel.client.common.security;

import com.rennover.client.framework.mvc.ActionDescription;
import com.rennover.client.framework.mvc.PanelViewController;
import com.rennover.client.framework.widget.base.ZPasswordField;
import com.rennover.client.framework.widget.icon.IconFactory;
import com.rennover.client.framework.widget.panel.FieldPanel;

public class ChangePasswordPanelController extends PanelViewController
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 752317057808710321L;

	private static final ActionDescription ACTION_VALIDER = new ActionDescription("Validate", "doValidate", IconFactory
	        .getValidateIcon());

	private static final ActionDescription ACTION_ANNULER = new ActionDescription("Cancel", "doCancel", IconFactory
	        .getCancelIcon());

	private ZPasswordField m_newPasswordField = new ZPasswordField();

	private ZPasswordField m_reNewPasswordField = new ZPasswordField();

	private boolean m_isValidated = false;

	public ChangePasswordPanelController()
	{
		setTitle("Changer mot de passe");

		init();
	}

	protected void compose()
	{
		FieldPanel panel = new FieldPanel();

		panel.addComponent("Nouveau mot de passe", m_newPasswordField);

		panel.addComponent("Confirmer", m_reNewPasswordField);

		panel.addButtonAction(getAction(ACTION_VALIDER));

		panel.addButtonAction(getAction(ACTION_ANNULER));

		add(panel);
	}

	public void doValidate()
	{
		String newPassword = new String(m_newPasswordField.getPassword());

		String reNewPassword = new String(m_reNewPasswordField.getPassword());

		/*CompteUtilisateur compteUtilisateur = UserContextManager.getContexteUtilisateur().getCompteUtilisateur();

		if (newPassword != null && reNewPassword != null)
		{
			if (!newPassword.equals(reNewPassword))
			{
				WindowManager.showErrorMessage(MessageConstants.MSG_098, this);

				m_reNewPasswordField.setText("");
			}

			else if (newPassword.equals(compteUtilisateur.getMotDePasse()))
			{
				WindowManager.showErrorMessage(MessageConstants.MSG_099, this);

				m_newPasswordField.setText("");

				m_reNewPasswordField.setText("");
			}

			else
			{
				ValidationResponse vr = ValidationService.validate(CompteUtilisateur.class,
				        CompteUtilisateur.MOT_DE_PASSE, newPassword);

				if (!vr.isValid())
				{
					WindowManager.showErrorMessage(MessageConstants.MSG_100, this);

					m_newPasswordField.setText("");

					m_reNewPasswordField.setText("");
				}

				else
				{
					WindowManager.showInformationMessage(MessageConstants.MSG_101, this);

					compteUtilisateur.setMotDePasse(newPassword);

					compteUtilisateur.setOrigineMotDePasse(OrigineMotDePasse.UTILISATEUR);

					SimpleDateFormat sdfExpirationMdP = new SimpleDateFormat("dd/MM/yyyy");

					ParsePosition posFinishDate = new ParsePosition(0);

					Date expirationDate = sdfExpirationMdP.parse(CreerCompteUtilisateurController
					        .getPasswordExpirationDate(), posFinishDate);

					compteUtilisateur.setDateExpirationMdP(expirationDate);

					try
					{
						FacadeFactory.getGestionCompteUtilisateur().reinitialiserMotDePasse(compteUtilisateur);
					} catch (EnvoiEmailException e)
					{
						showLoginPasseMessage(compteUtilisateur, "", this.getView());
					}

					m_isValidated = true;

					close();
				}
			}
		}
*/	}

	/*public static void showLoginPasseMessage(CompteUtilisateur compteUtilisateur, String individuNomPrenom,
	        PanelView currentView)
	{
		String message = "L'opération a été exécutée avec succès.\n";
		if (compteUtilisateur != null)
		{
			message = message + "Login de connexion : " + compteUtilisateur.getLogin() + " \n";
			message = message + "Le mot de passe est  : " + compteUtilisateur.getMotDePasse();
			WindowManager.showInformationMessage(message, currentView);
		}
	}
*/
	public void doCancel()
	{
		close();
	}

	public boolean isValidated()
	{
		return m_isValidated;
	}
}