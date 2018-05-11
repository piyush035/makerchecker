package com.hotel.common.window;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.hotel.client.common.window.ColorWindow;
import com.hotel.client.main.HotelFullClientApplication;
import com.rennover.client.framework.mvc.ActionDescription;
import com.rennover.client.framework.mvc.ActionManager;
import com.rennover.client.framework.mvc.InvokeMethodAction;
import com.rennover.client.framework.print.PrintToolbox;
import com.rennover.client.framework.widget.base.ZFrame;
import com.rennover.client.framework.widget.base.ZMenu;
import com.rennover.client.framework.widget.base.ZMenuBar;
import com.rennover.client.framework.window.LookAndFeelManager;
import com.rennover.client.framework.window.WindowManager;

/**
 * @author tcaboste
 * 
 *         To change the template for this generated type comment go to
 *         Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MainMenuBar extends ZMenuBar {
	public static final ActionDescription ACTION_LOOK_ALLOY = new ActionDescription(
			"Look ALLOY", "doLookAlloy");

	public static final ActionDescription ACTION_LOOK_ALLOY_PERSO = new ActionDescription(
			"Thème perso", "doAlloyPerso");

	public static final ActionDescription ACTION_LOOK_ALLOY_VERT1 = new ActionDescription(
			"Thème vert 1", "doAlloyVert1");

	public static final ActionDescription ACTION_LOOK_ALLOY_VERT2 = new ActionDescription(
			"Thème vert 2", "doAlloyVert2");

	public static final ActionDescription ACTION_LOOK_ALLOY_BLEU = new ActionDescription(
			"Thème bleu", "doAlloyBleu");

	public static final ActionDescription ACTION_LOOK_ALLOY_MAUVE = new ActionDescription(
			"Thème mauve", "doAlloyMauve");

	public static final ActionDescription ACTION_LOOK_METAL = new ActionDescription(
			"Look METAL", "doLookMetal");

	public static final ActionDescription ACTION_LOOK_METAL_PERSO = new ActionDescription(
			"Thème perso", "doMetalPerso");

	public static final ActionDescription ACTION_LOOK_METAL_VERT1 = new ActionDescription(
			"Thème vert 1", "doMetalVert1");

	public static final ActionDescription ACTION_LOOK_METAL_VERT2 = new ActionDescription(
			"Thème vert 2", "doMetalVert2");

	public static final ActionDescription ACTION_LOOK_METAL_BLEU = new ActionDescription(
			"Thème bleu", "doMetalBleu");

	public static final ActionDescription ACTION_LOOK_METAL_MAUVE = new ActionDescription(
			"Thème mauve", "doMetalMauve");

	public static final ActionDescription ACTION_LOOK_WINDOWS = new ActionDescription(
			"Look WINDOWS", "doLookWindows");

	private Window m_parentWindow;

	public MainMenuBar() {
		composeMenu();
	}

	protected void composeMenu() {
		/*
		 * add(createMainFichierMenu()); add(createCollecteMenu());
		 * add(createReferentielMenu()); add(createGAFPMenu());
		 * add(createStyleMenu()); add(createWindowMenu());
		 * add(createHelpMenu());
		 */
	}


	protected ZMenu createGAFPMenu() {/*
									 * ZMenu menu = new ZMenu("GAFP",
									 * KeyEvent.VK_G); // sTART: ME-1432
									 * AgefosClientApplication
									 * .ACTION_GAFP_05.setAccessPermission
									 * (ServiceFonctionnel
									 * .SAISIE_FACTURE_FORMATION);
									 * AgefosClientApplication
									 * .ACTION_GAFP_14.setAccessPermission
									 * (ServiceFonctionnel
									 * .SAISIE_FACTURE_FORMATION);
									 * AgefosClientApplication
									 * .ACTION_GAFP_07.setAccessPermission
									 * (ServiceFonctionnel
									 * .GERER_PROJETS_COFINANCES); // 308 is not
									 * in file
									 * AgefosClientApplication.ACTION_GAFP_09
									 * .setAccessPermission
									 * (ServiceFonctionnel.GERER_LES_FONDS);
									 * AgefosClientApplication
									 * .ACTION_GAFP_10.setAccessPermission
									 * (ServiceFonctionnel
									 * .CLORE_DOSSIER_DE_FORMATION);
									 * AgefosClientApplication
									 * .ACTION_GAFP_15.setAccessPermission
									 * (ServiceFonctionnel.GERER_LES_EDITIONS);
									 * // End: ME-1432 // Start: ME-1311
									 * 
									 * // ZMenu subMenu1 = new
									 * ZMenu("Gestion des factures et bons à payer"
									 * ); //
									 * subMenu1.add(ActionManager.createAction
									 * (AgefosClientApplication
									 * .getApplication(), //
									 * AgefosClientApplication
									 * .ACTION_GAFP_051)); //
									 * subMenu1.add(ActionManager
									 * .createAction(AgefosClientApplication
									 * .getApplication(), //
									 * AgefosClientApplication
									 * .ACTION_GAFP_052)); //
									 * subMenu1.add(ActionManager
									 * .createAction(AgefosClientApplication
									 * .getApplication(), //
									 * AgefosClientApplication
									 * .ACTION_GAFP_053)); //
									 * subMenu1.add(ActionManager
									 * .createAction(AgefosClientApplication
									 * .getApplication(), //
									 * AgefosClientApplication
									 * .ACTION_GAFP_054)); // End: ME-1311
									 * menu.add(ActionManager.createAction(
									 * AgefosClientApplication.getApplication(),
									 * AgefosClientApplication.ACTION_GAFP_01));
									 * menu.add(ActionManager.createAction(
									 * AgefosClientApplication.getApplication(),
									 * AgefosClientApplication.ACTION_GAFP_02));
									 * // menu.add(ActionManager.createAction(
									 * AgefosClientApplication.getApplication(),
									 * //
									 * AgefosClientApplication.ACTION_GAFP_03));
									 * menu.add(ActionManager.createAction(
									 * AgefosClientApplication.getApplication(),
									 * AgefosClientApplication.ACTION_GAFP_04));
									 * // Start: ME-1311 // ME-1430 : Added the
									 * following sub-menu.
									 * menu.add(createGestionDeFacturesMenu());
									 * // menu.add(subMenu1); // End: ME-1311 //
									 * Start: ME-1432 disabled ACTION_GAFP_06
									 * menu.add( ActionManager.createAction(
									 * AgefosClientApplication.getApplication(),
									 * AgefosClientApplication
									 * .ACTION_GAFP_06)).setEnabled(false); //
									 * End: ME-1432
									 * menu.add(ActionManager.createAction
									 * (AgefosClientApplication
									 * .getApplication(),
									 * AgefosClientApplication.ACTION_GAFP_07));
									 * // Start: ME-1432 disabled ACTION_GAFP_06
									 * menu.add( ActionManager.createAction(
									 * AgefosClientApplication.getApplication(),
									 * AgefosClientApplication
									 * .ACTION_GAFP_08));//Menu Link Enabled for
									 * GAFP SP10 link // End: ME-1432
									 * menu.add(ActionManager
									 * .createAction(AgefosClientApplication
									 * .getApplication(),
									 * AgefosClientApplication.ACTION_GAFP_09));
									 * menu.add(ActionManager.createAction(
									 * AgefosClientApplication.getApplication(),
									 * AgefosClientApplication.ACTION_GAFP_10));
									 * // Start: ME-1426 Added a new MenuItem
									 * menu.add(ActionManager.createAction(
									 * AgefosClientApplication.getApplication(),
									 * AgefosClientApplication.ACTION_GAFP_15));
									 * // End: ME-1426 // Start: ME-1434 Added a
									 * new MenuItem
									 * menu.add(ActionManager.createAction
									 * (AgefosClientApplication
									 * .getApplication(),
									 * AgefosClientApplication.ACTION_GAFP_16));
									 * // End: ME-1434 // Start: ME-1436 Added a
									 * new MenuItem
									 * menu.add(ActionManager.createAction
									 * (AgefosClientApplication
									 * .getApplication(),
									 * AgefosClientApplication.ACTION_GAFP_17));
									 * // End: ME-1436 // Start: ME-1425 Added a
									 * new MenuItem
									 * menu.add(ActionManager.createAction
									 * (AgefosClientApplication
									 * .getApplication(),
									 * AgefosClientApplication.ACTION_BO_LINK));
									 * // End: ME-1425 return menu;
									 */
		return null;
	}

	// ME-1430 : Added the following method.
	protected ZMenu createGestionDeFacturesMenu() {
		ZMenu menu = new ZMenu("Gestion des factures", KeyEvent.VK_G);
		/*menu.add("Saisie de factures", KeyEvent.VK_P,
				getApplicationAction(AgefosClientApplication.ACTION_GAFP_05));
		menu.add("Sélection des factures à mettre en BAP", KeyEvent.VK_P,
				getApplicationAction(AgefosClientApplication.ACTION_GAFP_14));*/
		return menu;
	}

	// --- MENU FICHIER
	// -------------------------------------------------------------------------------------------------------------------------------------------
	protected ZMenu createMainFichierMenu() {
		ZMenu menu = new ZMenu("NSI-CM", KeyEvent.VK_N);

		menu.add(getPrintWindowAction("Imprimer la fenêtre..."), KeyStroke
				.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		menu.addSeparator();
		/*menu.add(
				getApplicationAction(AgefosClientApplication.ACTION_EXIT_APPLICATION),
				KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
	*/	return menu;
	}

	protected ZMenu createFichierMenu() {
		return createFichierMenu("NSI-CM", KeyEvent.VK_N);
	}

	protected ZMenu createFichierMenu(String s, int mnemonic) {
		ZMenu menu = new ZMenu(s, mnemonic);

		/*menu.add(
				getApplicationAction(AgefosClientApplication.ACTION_BACK_TO_HOME_SCREEN),
				KeyStroke.getKeyStroke(KeyEvent.VK_HOME, InputEvent.ALT_MASK));*/
		menu.addSeparator();

		menu.add(getPrintWindowAction("Print Window ..."), KeyStroke
				.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		menu.addSeparator();
		menu.add(getCloseAction("Close window"),
				KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		menu.addSeparator();
		/*menu.add(
				getApplicationAction(HotelClientApplication.ACTION_EXIT_APPLICATION),
				KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK
						| InputEvent.CTRL_MASK));*/
		return menu;
	}

	// --- AUTRES MENUS
	// -------------------------------------------------------------------------------------------------------------------------------------------
	protected ZMenu createStyleMenu() {
		ZMenu menu = new ZMenu("Style", KeyEvent.VK_F);

		ZMenu submenu1 = new ZMenu("Alloy", KeyEvent.VK_A);
		menu.add(submenu1);
		submenu1.add("Thème vert 1", KeyEvent.VK_V,
				getAction(ACTION_LOOK_ALLOY_VERT1));
		submenu1.add("Thème vert 2", KeyEvent.VK_E,
				getAction(ACTION_LOOK_ALLOY_VERT2));
		submenu1.add("Thème bleu", KeyEvent.VK_B,
				getAction(ACTION_LOOK_ALLOY_BLEU));
		submenu1.add("Thème mauve", KeyEvent.VK_M,
				getAction(ACTION_LOOK_ALLOY_MAUVE));
		submenu1.add("Thème par défault", KeyEvent.VK_D,
				getAction(ACTION_LOOK_ALLOY));
		submenu1.addSeparator();
		submenu1.add("Thème perso", KeyEvent.VK_P,
				getAction(ACTION_LOOK_ALLOY_PERSO));
		menu.addSeparator();

		ZMenu submenu2 = new ZMenu("Metal", KeyEvent.VK_M);
		menu.add(submenu2);
		submenu2.add("Thème vert 1", KeyEvent.VK_V,
				getAction(ACTION_LOOK_METAL_VERT1));
		submenu2.add("Thème vert 2", KeyEvent.VK_E,
				getAction(ACTION_LOOK_METAL_VERT2));
		submenu2.add("Thème bleu", KeyEvent.VK_B,
				getAction(ACTION_LOOK_METAL_BLEU));
		submenu2.add("Thème mauve", KeyEvent.VK_M,
				getAction(ACTION_LOOK_METAL_MAUVE));
		submenu2.add("Thème par défault", KeyEvent.VK_D,
				getAction(ACTION_LOOK_METAL));
		submenu2.addSeparator();
		submenu2.add("Thème perso", KeyEvent.VK_P,
				getAction(ACTION_LOOK_METAL_PERSO));
		menu.addSeparator();

		menu.add("Windows", KeyEvent.VK_W, getAction(ACTION_LOOK_WINDOWS));

		return menu;
	}

	protected ZMenu createWindowMenu() {
		ZMenu menu = new ZMenu("Fenêtre", KeyEvent.VK_F);

		ZMenu menu1 = new ZMenu("Menu Temporaire");
		//menu1.add(getApplicationAction(AgefosClientApplication.ACTION_TEST_LANCER_UC_COLLECTE));
		//menu1.add(getApplicationAction(AgefosClientApplication.ACTION_TEST_LANCER_UC_REFERENTIEL));
		//menu1.add(getApplicationAction(AgefosClientApplication.ACTION_REFERENTIEL_DEMO_GESTION_SITE));
		menu1.addSeparator();
		//menu1.add(getApplicationAction(AgefosClientApplication.ACTION_AUDIT_MEMOIRE));
		menu1.add(new InvokeMethodAction(this, "DEBUG: Dump Menu",
				"doDebugMenu"));
		menu1.addSeparator();
		/*menu1.add(
				getApplicationAction(AgefosClientApplication.ACTION_SHOW_UI_DEFAULTS),
				KeyStroke.getKeyStroke('U', InputEvent.CTRL_MASK));

*/		menu.add(menu1);

		menu.addSeparator();
/*		menu.add(getAction(AgefosClientApplication.ACTION_FIT_TO_WINDOW_1024));
		menu.add(getAction(AgefosClientApplication.ACTION_FIT_TO_WINDOW_800));
*/
		return menu;
	}

	protected ZMenu createHelpMenu() {
		ZMenu menu = new ZMenu("Aide", KeyEvent.VK_A);

		//menu.add(getAction(AgefosClientApplication.ACTION_ABOUT));

		return menu;
	}

	protected static Action getApplicationAction(
			ActionDescription actionDescription) {
		/*return AgefosClientApplication.getApplication().getAction(
				actionDescription);*/
		return null;
	}

	protected Action getAction(ActionDescription actionDescription) {
		return ActionManager.createAction(this, actionDescription);
	}

	protected boolean isFullClient() {
		return HotelFullClientApplication.isFullClient();
	}

	protected Window getParentWindow() {
		if (m_parentWindow == null) {
			m_parentWindow = WindowManager.getOwningWindow(this);
		}
		return m_parentWindow;
	}

	private void closeWindow() {
		Window parent = getParentWindow();
		parent.dispatchEvent(new WindowEvent(parent, WindowEvent.WINDOW_CLOSING));
	}

	protected Action getCloseAction(String title) {
		return new AbstractAction(title) {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		};
	}

	protected Action getPrintWindowAction(String title) {
		return new AbstractAction(title) {
			public void actionPerformed(ActionEvent e) {
				Window parent = getParentWindow();
				PrintToolbox.print(parent);
			}
		};
	}

	public void doNotAvailable() {
		Window parent = getParentWindow();
		WindowManager
				.showWarningMessage(
						"Cette fonction n'est pas encore disponible.\nNous en sommes désolés.",
						parent);
	}

	public void doAbout() {
		/*Window parent = getParentWindow();
		AboutWindow f = new AboutWindow((Frame) parent);
		f.pack();
		f.centerToScreen();
		f.setVisible(true);*/
	}

	public void doFitToWindow() {
		Window parentWindow = getParentWindow();
		parentWindow.setSize(1024, 768); // taille de la maquette
		parentWindow.validate();

		parentWindow.setLocationRelativeTo(null);
	}

	public void doFitToWindow1024() {
		Window parentWindow = getParentWindow();
		parentWindow.setSize(1024, 768); // taille de la maquette
		parentWindow.validate();

		parentWindow.setLocationRelativeTo(null);
	}

	public void doFitToWindow800() {
		Window parentWindow = getParentWindow();
		parentWindow.setSize(800, 600); // taille de la maquette
		parentWindow.validate();

		parentWindow.setLocationRelativeTo(null);
	}

	public void doLookMetal() {
		Window parentWindow = getParentWindow();
		LookAndFeelManager.setMetalLookAndFeel();
		SwingUtilities.updateComponentTreeUI(parentWindow);
		parentWindow.validate();
	}

	public void doMetalVert1() {
		Window parentWindow = getParentWindow();
		LookAndFeelManager.setMetalLookAndFeelVert1();
		SwingUtilities.updateComponentTreeUI(parentWindow);
		parentWindow.validate();
	}

	public void doMetalVert2() {
		Window parentWindow = getParentWindow();
		LookAndFeelManager.setMetalLookAndFeelVert2();
		SwingUtilities.updateComponentTreeUI(parentWindow);
		parentWindow.validate();
	}

	public void doMetalBleu() {
		Window parentWindow = getParentWindow();
		LookAndFeelManager.setMetalLookAndFeelBleu();
		SwingUtilities.updateComponentTreeUI(parentWindow);
		parentWindow.validate();
	}

	public void doMetalMauve() {
		Window parentWindow = getParentWindow();
		LookAndFeelManager.setMetalLookAndFeelMauve();
		SwingUtilities.updateComponentTreeUI(parentWindow);
		parentWindow.validate();
	}

	public void doMetalPerso() {
		/*Window parentWindow = getParentWindow();
		MetalColorWindow colorWindow = new MetalColorWindow(
				(ZFrame) parentWindow);
		colorWindow.setVisible(true);*/
	}

	public void doLookAlloy() {
		Window parentWindow = getParentWindow();
		LookAndFeelManager.setAlloyLookAndFeel();
		SwingUtilities.updateComponentTreeUI(parentWindow);
		parentWindow.validate();
	}

	public void doAlloyVert1() {
		Window parentWindow = getParentWindow();
		LookAndFeelManager.setAlloyLookAndFeelVert1();
		SwingUtilities.updateComponentTreeUI(parentWindow);
		parentWindow.validate();
	}

	public void doAlloyVert2() {
		Window parentWindow = getParentWindow();
		LookAndFeelManager.setAlloyLookAndFeelVert2();
		SwingUtilities.updateComponentTreeUI(parentWindow);
		parentWindow.validate();
	}

	public void doAlloyBleu() {
		Window parentWindow = getParentWindow();
		LookAndFeelManager.setAlloyLookAndFeelBleu();
		SwingUtilities.updateComponentTreeUI(parentWindow);
		parentWindow.validate();
	}

	public void doAlloyMauve() {
		Window parentWindow = getParentWindow();
		LookAndFeelManager.setAlloyLookAndFeelMauve();
		SwingUtilities.updateComponentTreeUI(parentWindow);
		parentWindow.validate();
	}

	public void doAlloyPerso() {
		Window parentWindow = getParentWindow();
		ColorWindow colorWindow = new ColorWindow((ZFrame) parentWindow);
		colorWindow.setVisible(true);
	}

	public void doLookWindows() {
		Window parentWindow = getParentWindow();

		LookAndFeelManager.setWindowsLookAndFeel();
		SwingUtilities.updateComponentTreeUI(parentWindow);

		parentWindow.validate();
	}

	public void doDebugMenu() {
		debug();
	}

	protected void debug() {
		StringBuffer buffer = new StringBuffer();
		debugMenuBar(buffer, this);
		System.out.println(buffer);
	}

	private static void debugMenuBar(StringBuffer buffer, JMenuBar menu) {
		for (int i = 0; i < menu.getMenuCount(); i++) {
			JMenu subMenu = (JMenu) menu.getMenu(i);
			buffer.append(subMenu.getText());
			buffer.append("\n");
			debugMenu("", buffer, 1, subMenu);
		}
	}

	private static void debugMenu(String indent, StringBuffer buffer,
			int level, JMenu menu) {
		Component[] subMenus = menu.getMenuComponents();
		for (int i = 0; i < subMenus.length; i++) {
			boolean lastElement = i + 1 == subMenus.length;
			String itemIndent = lastElement ? " \\--" : " +--";

			Component subComponent = subMenus[i];
			if (subComponent instanceof JMenuItem) {
				JMenuItem subMenu = (JMenuItem) subMenus[i];
				buffer.append(indent + itemIndent);
				buffer.append(subMenu.getText());
				buffer.append("\n");
			}

			if (subComponent instanceof JSeparator) {
				buffer.append(indent + itemIndent);
				buffer.append("----------------------------------------");
				buffer.append("\n");
			}

			if (subComponent instanceof JMenu) {
				String itemIndent2 = lastElement ? "   " : " | ";
				debugMenu(indent + itemIndent2, buffer, level + 1,
						(JMenu) subComponent);
			}
		}
		buffer.append(indent + "\n");
	}
}
