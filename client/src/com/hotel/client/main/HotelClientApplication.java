package com.hotel.client.main;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;

import com.rennover.hotel.common.log.Logger;

/**
 * @author Piyush
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public class HotelClientApplication extends HotelFullClientApplication {
	/**
	 * Constructor for AgefosClientApplication.
	 */
	public HotelClientApplication() {
		super();
	}

	protected JMenu createCollecteMenu() {
		JMenu menu = new JMenu("Collecte");
		menu.setMnemonic(KeyEvent.VK_C);
		/*
		 * menu.add(getAction(ACTION_COLLECTE_CREER_CIBLE));
		 * menu.add(getAction(ACTION_COLLECTE_ADAPTER_CIBLE_REGIONALE));
		 * menu.add(getAction(ACTION_COLLECTE_VALIDER_CIBLE_REGIONALE));
		 * menu.addSeparator();
		 * menu.add(getAction(ACTION_COLLECTE_SAISIR_COLLECTE));
		 * menu.addSeparator();
		 * menu.add(getAction(ACTION_COLLECTE_PARAMETRER_CIBLE_NATIONALE));
		 * menu.add(getAction(ACTION_COLLECTE_VALIDER_CIBLE_NATIONALE));
		 */
		return menu;
	}

	protected JMenu createReferentielMenu() {
		JMenu menu = new JMenu("Référentiel");
		menu.setMnemonic(KeyEvent.VK_R);

		/*
		 * menu.add(getAction(ACTION_REFERENTIEL_CREER_PROSPECT_ADHERENT));
		 * menu.add(getAction(ACTION_REFERENTIEL_CONSULTER_ETABLISSEMENT));
		 * 
		 * menu.addSeparator();
		 * menu.add(getAction(ACTION_REFERENTIEL_CREER_INDIVIDU));
		 * menu.add(getAction(ACTION_REFERENTIEL_GERER_INDIVIDU));
		 */return menu;
	}

	protected JMenu createWindowMenu() {
		JMenu menu = new JMenu("Fenêtre");
		menu.setMnemonic(KeyEvent.VK_F);
		// menu.add(getAction(ACTION_FIT_TO_WINDOW));
		return menu;
	}

	protected boolean isFull() {
		return false;
	}

	public static void main(String[] args) {
		ClientInitializer.init();
		Logger.debug(HotelClientApplication.class, "Client is running");
		HotelClientApplication appli = new HotelClientApplication();
		appli.start(args);
	}

	public static HotelFullClientApplication getHotelApplication() {
		return (HotelFullClientApplication) getApplication();
	}
}