package com.hotel.common.window;

import java.awt.Insets;

import javax.swing.Action;
import javax.swing.border.EmptyBorder;

import com.hotel.client.main.HotelClientApplication;
import com.rennover.client.framework.mvc.ActionDescription;
import com.rennover.client.framework.mvc.ActionManager;
import com.rennover.client.framework.print.PrintScreenAction;
import com.rennover.client.framework.widget.base.ZButton;
import com.rennover.client.framework.widget.button.ButtonFactory;
import com.rennover.client.framework.widget.panel.ToolBar;

/**
 * @author Piyush
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MainToolBar extends ToolBar
{
	public MainToolBar()
	{
		super(0, 8);
		compose();
	}

	protected static Action getApplicationAction(ActionDescription actionDescription)
	{
		if (null != actionDescription)
		{
			return HotelClientApplication.getApplication().getAction(actionDescription);
		}
		return null;
	}

	protected Action getAction(ActionDescription actionDescription)
	{
		return ActionManager.createAction(this, actionDescription);
	}

	protected void compose()
	{
		setBorder(new EmptyBorder(2, 2, 2, 2));
		addMainButtons();
		addHomeScreenButtons();
		addPrintButton();
	}

	protected void addMainButtons()
	{
		ButtonFactory.setDefaultIconDirectory(ButtonFactory.BARRE_DE_MENU_DIR + "/rapides");

		/*List fonctionRapideList = UserContextManager.getContexteUtilisateur().getFonctionRapideList();

		if (null != fonctionRapideList)
		{
			for (Iterator iter = fonctionRapideList.iterator(); iter.hasNext();)
			{

				FonctionRapide fonctionRapide = (FonctionRapide) iter.next();

				ActionDescription actionDescription = FonctionsRapidesActionDescription
				        .getActionDescriptionFonctionsRapides(fonctionRapide.getFichierImageBoutonAcces());
				if (null != actionDescription)
				{
					actionDescription.setAccessPermission(Wrapper
					        .toList((ServiceFonctionnel) ServiceFonctionnel.getInstance(ServiceFonctionnel.class,
					                new Long(fonctionRapide.getServiceFonctionnelCode()))));
				}

				addButton(fonctionRapide.getFichierImageBoutonAcces(), null, getApplicationAction(actionDescription));
			}
		}

		addSeparator();

		ButtonFactory.setDefaultIconDirectory(ButtonFactory.BARRE_DE_MENU_DIR + "/recherche");

		addButton("Dossiers.gif", null, null);
		addButton("Etablissements.gif", null,
		        getApplicationAction(AgefosClientApplication.ACTION_REFERENTIEL_CONSULTER_ETABLISSEMENT));
		addButton("Projets.gif", null, null);
		addButton("Grands_comptes.gif", null,
		        getApplicationAction(AgefosClientApplication.ACTION_REFERENTIEL_GERER_GRAND_COMPTE));
		addButton("Factures.gif", null, null);
		addButton("Individus.gif", null,
		        getApplicationAction(AgefosClientApplication.ACTION_REFERENTIEL_GERER_INDIVIDU));

		// Added for ME901
		// Start: ME-1425
		
		 * addSeparator(); addButton("Etats.gif", null,
		 * getApplicationAction(AgefosClientApplication.ACTION_ETATS));
		 
		// End: ME-1425
		addSpring();
*/	}

	protected ZButton createButton(String iconName, String rolloverIconName, String description, Action action)
	{
		ZButton button = ButtonFactory.newButton(iconName, rolloverIconName, description, action);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setFocusable(false);
		if (action == null)
		{
			button.setEnabled(false);
		}
		return button;
	}

	protected void addButton(String iconName, String description, Action action)
	{
		add(createButton(iconName, description, action));
	}

	protected ZButton createButton(String iconName, String description, Action action)
	{
		ZButton button = ButtonFactory.newButton(iconName, description, action);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setFocusable(false);
		if (action == null)
		{
			button.setEnabled(false);
		}
		return button;
	}

	protected void addHomeScreenButtons()
	{
	}

	protected void addPrintButton()
	{
		addSeparator();
		addButtonAction(new PrintScreenAction(this, "Imprimer l'écran..."));
	}
}
