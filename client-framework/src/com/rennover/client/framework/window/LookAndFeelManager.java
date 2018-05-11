
package com.rennover.client.framework.window;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

import com.incors.plaf.alloy.AlloyLookAndFeel;
import com.incors.plaf.alloy.AlloyTheme;
import com.incors.plaf.alloy.themes.custom.CustomThemeFactory;
import com.rennover.client.framework.widget.icon.IconFactory;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class LookAndFeelManager
{
	private static final String DEFAULT_THEME_DIR = "default";

	private static final String BLEU_MAUVE_THEME_DIR = "bleu_mauve";

	private static final String VERT_1_2_THEME_DIR = "vert_1_2";

	private static final AlloyCustomColorSet defaultAlloyColors = new AlloyCustomColorSet();

	private static final AlloyCustomColorSet vert1AlloyColors = new AlloyCustomColorSet();

	private static final AlloyCustomColorSet vert2AlloyColors = new AlloyCustomColorSet();

	private static final AlloyCustomColorSet bleuAlloyColors = new AlloyCustomColorSet();

	private static final AlloyCustomColorSet mauveAlloyColors = new AlloyCustomColorSet();

	private static final MetalCustomColorSet defaultMetalColors = new MetalCustomColorSet();

	private static final MetalCustomColorSet vert1MetalColors = new MetalCustomColorSet();

	private static final MetalCustomColorSet vert2MetalColors = new MetalCustomColorSet();

	private static final MetalCustomColorSet bleuMetalColors = new MetalCustomColorSet();

	private static final MetalCustomColorSet mauveMetalColors = new MetalCustomColorSet();

	static
	{
		/* Agefos Theme */
		defaultAlloyColors.m_primary = new Color(144, 225, 208); // vert :
																	// scroll
																	// bars,
																	// progress
																	// bars,
																	// frame
																	// décorations

		defaultAlloyColors.m_secondary = new Color(236, 234, 230); // gris :
																	// panels,
																	// boutons,
																	// autres
																	// backgrounds
		defaultAlloyColors.m_desktop = new Color(236, 234, 230); // comme
																	// secondary
																	// color :
																	// uniquement
																	// pour le
																	// background
																	// des
																	// JDesktopPane
		defaultAlloyColors.m_selection = new Color(51, 183, 156); // vert
																	// foncé :
																	// sélections
																	// des radio
																	// boutons,
																	// des
																	// check-box
		defaultAlloyColors.m_rollover = new Color(95, 211, 188); // vert
																	// foncé :
																	// rollover
																	// sur les
																	// boutons,
																	// les radio
																	// boutons,
																	// les
																	// check-box
		defaultAlloyColors.m_highlight = new Color(202, 255, 233); // vert
																	// clair :
																	// textes
																	// sélectionnés
																	// et menu
																	// sélectionnés

		/* Agefos Theme */
		vert1AlloyColors.m_primary = new Color(158, 181, 158);
		vert1AlloyColors.m_secondary = new Color(235, 250, 235);
		vert1AlloyColors.m_arrowButton = new Color(158, 181, 158);
		vert1AlloyColors.m_frame = new Color(235, 250, 235);
		vert1AlloyColors.m_desktop = new Color(250, 250, 221);
		vert1AlloyColors.m_highlight = new Color(204, 204, 255);
		vert1AlloyColors.m_rollover = new Color(51, 51, 255);
		vert1AlloyColors.m_selection = new Color(51, 51, 255);

		/* Agefos Theme */
		vert2AlloyColors.m_primary = new Color(73, 164, 147);
		vert2AlloyColors.m_secondary = new Color(232, 231, 231);
		vert2AlloyColors.m_desktop = new Color(241, 255, 241);
		vert2AlloyColors.m_highlight = new Color(166, 216, 250);
		vert2AlloyColors.m_rollover = new Color(0, 0, 204);
		vert2AlloyColors.m_selection = new Color(0, 0, 204);

		/* Agefos Theme */
		bleuAlloyColors.m_primary = new Color(93, 157, 252);
		bleuAlloyColors.m_secondary = new Color(242, 251, 251);
		bleuAlloyColors.m_desktop = new Color(250, 250, 221);
		bleuAlloyColors.m_highlight = new Color(255, 255, 102);
		bleuAlloyColors.m_rollover = new Color(44, 116, 224);
		bleuAlloyColors.m_selection = new Color(255, 217, 0);

		/* Agefos Theme */
		mauveAlloyColors.m_primary = new Color(214, 201, 255);
		mauveAlloyColors.m_secondary = new Color(235, 235, 250);
		mauveAlloyColors.m_desktop = new Color(247, 255, 249);
		mauveAlloyColors.m_highlight = new Color(255, 204, 204);
		mauveAlloyColors.m_rollover = new Color(244, 4, 244);
		mauveAlloyColors.m_selection = new Color(255, 33, 255);

		/* Agefos Theme */
		defaultMetalColors.m_primary1 = new Color(144, 225, 208); // vert : scroll
																// bars,
																// progress
																// bars, frame
																// décorations
		defaultMetalColors.m_primary2 = new Color(236, 234, 230); // gris :
																// panels,
																// boutons,
																// autres
																// backgrounds
		defaultMetalColors.m_primary3 = new Color(236, 234, 230); // comme
																// secondary
																// color :
																// uniquement
																// pour le
																// background
																// des
																// JDesktopPane
		defaultMetalColors.m_secondary1 = new Color(51, 183, 156); // vert
																	// foncé :
																	// sélections
																	// des radio
																	// boutons,
																	// des
																	// check-box
		defaultMetalColors.m_secondary2 = new Color(95, 211, 188); // vert
																	// foncé :
																	// rollover
																	// sur les
																	// boutons,
																	// les radio
																	// boutons,
																	// les
																	// check-box
		defaultMetalColors.m_secondary3 = new Color(202, 255, 233); // vert
																	// clair :
																	// textes
																	// sélectionnés
																	// et menu
																	// sélectionnés

		/* Agefos Theme */
		vert1MetalColors.m_primary1 = new Color(117, 163, 147);
		vert1MetalColors.m_primary2 = new Color(196, 218, 210);
		vert1MetalColors.m_primary3 = new Color(255, 255, 217);
		vert1MetalColors.m_secondary1 = new Color(205, 203, 203);
		vert1MetalColors.m_secondary2 = new Color(224, 238, 233);
		vert1MetalColors.m_secondary3 = new Color(255, 255, 235);

		/* Agefos Theme */
		vert2MetalColors.m_primary1 = new Color(91, 95, 255);
		vert2MetalColors.m_primary2 = new Color(215, 213, 213);
		vert2MetalColors.m_primary3 = new Color(241, 255, 241);
		vert2MetalColors.m_secondary1 = new Color(117, 163, 147);
		vert2MetalColors.m_secondary2 = new Color(221, 223, 255);
		vert2MetalColors.m_secondary3 = new Color(208, 226, 219);

		/* Agefos Theme */
		bleuMetalColors.m_primary1 = new Color(128, 128, 255);
		bleuMetalColors.m_primary2 = new Color(207, 207, 255);
		bleuMetalColors.m_primary3 = new Color(254, 255, 211);
		bleuMetalColors.m_secondary1 = new Color(203, 204, 255);
		bleuMetalColors.m_secondary2 = new Color(231, 232, 255);
		bleuMetalColors.m_secondary3 = new Color(255, 255, 231);

		/* Agefos Theme */
		mauveMetalColors.m_primary1 = new Color(168, 115, 255);
		mauveMetalColors.m_primary2 = new Color(224, 207, 255);
		mauveMetalColors.m_primary3 = new Color(237, 221, 251);
		mauveMetalColors.m_secondary1 = new Color(219, 198, 254);
		mauveMetalColors.m_secondary2 = new Color(242, 235, 255);
		mauveMetalColors.m_secondary3 = new Color(242, 231, 255);

	}

	private static AlloyCustomColorSet s_currentAlloyColors;

	private static MetalCustomColorSet s_currentMetalColors;

	static AlloyLookAndFeel s_alloyLnF = null;

	private static WindowsLookAndFeel s_windowsLnF;

	public static void setAlloyLookAndFeel()
	{
		setAlloyCustomLookAndFeel(defaultAlloyColors);
		IconFactory.setThemeDirectory(DEFAULT_THEME_DIR);
	}

	public static void setAlloyLookAndFeelVert1()
	{
		setAlloyCustomLookAndFeel(vert1AlloyColors);
		IconFactory.setThemeDirectory(VERT_1_2_THEME_DIR);
	}

	public static void setAlloyLookAndFeelVert2()
	{
		setAlloyCustomLookAndFeel(vert2AlloyColors);
		IconFactory.setThemeDirectory(VERT_1_2_THEME_DIR);
	}

	public static void setAlloyLookAndFeelBleu()
	{
		setAlloyCustomLookAndFeel(bleuAlloyColors);
		IconFactory.setThemeDirectory(BLEU_MAUVE_THEME_DIR);
	}

	public static void setAlloyLookAndFeelMauve()
	{
		setAlloyCustomLookAndFeel(mauveAlloyColors);
		IconFactory.setThemeDirectory(BLEU_MAUVE_THEME_DIR);
	}

	public static AlloyLookAndFeel getAlloyLookAndFeel()
	{
		if (s_alloyLnF == null)
		{
			// License Alloy
			AlloyLookAndFeel.setProperty("alloy.licenseCode", "u#AGEFOS_PME#18kut72#8ftq30"); // License
																								// permanente

			s_alloyLnF = new AlloyLookAndFeel();
		}
		return s_alloyLnF;
	}

	public static void setAlloyCustomLookAndFeel(AlloyCustomColorSet colors)
	{
		try
		{
			AlloyLookAndFeel alloyLnF = getAlloyLookAndFeel();
			setCurrentAlloyColors(colors);
			AlloyTheme customTheme = CustomThemeFactory.createTheme(colors.m_primary, colors.m_secondary,
			        colors.m_desktop, colors.m_selection, colors.m_rollover, colors.m_highlight);
			UIManager.setLookAndFeel(s_alloyLnF);

			// autorise à utiliser le AlloyLookAndFeel pour la décoration de la
			// boîte de dialogue
			JDialog.setDefaultLookAndFeelDecorated(false);
			JFrame.setDefaultLookAndFeelDecorated(false);

			alloyLnF.setTheme(customTheme, true);

			changeFontsAndDefaultColors();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		// this line needs to be implemented in order to make JWS work properly
		javax.swing.UIManager.getLookAndFeelDefaults().put("ClassLoader", LookAndFeelManager.class.getClassLoader());
	}

	private static void changeFontsAndDefaultColors()
	{
		// Fonts
		UIManager.put("Label.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("Button.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("Table.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("TableHeader.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("TabbedPane.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("List.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("Panel.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("CheckBox.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("RadioButton.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("ComboBox.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("TitledBorder.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("MenuItem.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("TitledBorder.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("MenuItem.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("Menu.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("TextField.font", new Font("SansSerif", Font.PLAIN, 12));
		UIManager.put("TextArea.font", new Font("SansSerif", Font.PLAIN, 12));

		// Colors
		// ToolTip
		UIManager.put("ToolTip.foregroundInactive", UIManager.getColor("ToolTip.foreground"));
		UIManager.put("ToolTip.backgroundInactive", UIManager.getColor("ToolTip.background"));
		UIManager.put("ToolTip.borderInactive", UIManager.getBorder("ToolTip.border"));

		// ComboBox
		UIManager.put("ComboBox.disabledForeground", UIManager.getColor("ComboBox.foreground"));

		// TextField
		UIManager.put("TextField.inactiveForeground", UIManager.getColor("TextField.foreground"));

		// Textarea
		UIManager.put("TextArea.inactiveForeground", UIManager.getColor("TextField.foreground"));
		UIManager.put("TextArea.inactiveBackground", UIManager.getColor("TextField.inactiveBackground"));

		// FormattedTextField
		UIManager.put("FormattedTextField.inactiveForeground", UIManager.getColor("FormattedTextField.foreground"));

		// CheckBox
		UIManager.put("CheckBox.disabledText", UIManager.getColor("CheckBox.foreground"));

		// RadioButton
		UIManager.put("RadioButton.disabledText", UIManager.getColor("RadioButton.foreground"));

		// List
		UIManager.put("List.disabledForeground", UIManager.getColor("TextField.foreground"));
		UIManager.put("List.disabledBackground", UIManager.getColor("TextField.inactiveBackground"));

		// Table
		UIManager.put("Table.disabledForeground", UIManager.getColor("TextField.foreground"));
		UIManager.put("Table.disabledBackground", UIManager.getColor("TextField.inactiveBackground"));

		// Splitter
		UIManager.put("SplitPane.dividerSize", new Integer(6));
	}

	private static void setCurrentAlloyColors(AlloyCustomColorSet colors)
	{
		s_currentAlloyColors = (AlloyCustomColorSet) colors.clone();
	}

	private static void setCurrentMetalColors(MetalCustomColorSet colors)
	{
		s_currentMetalColors = (MetalCustomColorSet) colors.clone();
	}

	/**
	 * @return
	 */
	public static AlloyCustomColorSet getCurrentAlloyColors()
	{
		return s_currentAlloyColors;
	}

	public static MetalCustomColorSet getCurrentMetalColors()
	{
		return s_currentMetalColors;
	}

	/**
	 * Method setMetalLookAndFeel.
	 */
	public static void setMetalLookAndFeel()
	{
		try
		{

			MetalCustomColorSet colors = new MetalCustomColorSet();
			setCurrentMetalColors(colors);
			CustomMetalTheme metalTheme = new CustomMetalTheme(colors);
			MetalLookAndFeel.setCurrentTheme(metalTheme);

			MetalLookAndFeel metalLnF = new MetalLookAndFeel();
			UIManager.setLookAndFeel(metalLnF);

			JDialog.setDefaultLookAndFeelDecorated(false);
			JFrame.setDefaultLookAndFeelDecorated(false);

			changeFontsAndDefaultColors();
			IconFactory.setThemeDirectory(DEFAULT_THEME_DIR);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void setMetalCustomLookAndFeel(MetalCustomColorSet colors)
	{
		try
		{
			setCurrentMetalColors(colors);
			CustomMetalTheme metalTheme = new CustomMetalTheme(colors);
			MetalLookAndFeel.setCurrentTheme(metalTheme);
			MetalLookAndFeel metalLnF = new MetalLookAndFeel();
			UIManager.setLookAndFeel(metalLnF);

			JDialog.setDefaultLookAndFeelDecorated(false);
			JFrame.setDefaultLookAndFeelDecorated(false);

			AlloyLookAndFeel.updateAllUIs();

			changeFontsAndDefaultColors();
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void setMetalLookAndFeelVert1()
	{
		setMetalCustomLookAndFeel(vert1MetalColors);
		IconFactory.setThemeDirectory(VERT_1_2_THEME_DIR);
	}

	public static void setMetalLookAndFeelVert2()
	{
		setMetalCustomLookAndFeel(vert2MetalColors);
		IconFactory.setThemeDirectory(VERT_1_2_THEME_DIR);
	}

	public static void setMetalLookAndFeelBleu()
	{
		setMetalCustomLookAndFeel(bleuMetalColors);
		IconFactory.setThemeDirectory(BLEU_MAUVE_THEME_DIR);
	}

	public static void setMetalLookAndFeelMauve()
	{
		setMetalCustomLookAndFeel(mauveMetalColors);
		IconFactory.setThemeDirectory(BLEU_MAUVE_THEME_DIR);
	}

	private static WindowsLookAndFeel getWindowsLookAndFeel()
	{
		if (s_windowsLnF == null)
		{
			s_windowsLnF = new WindowsLookAndFeel();
			s_windowsLnF.initialize();
			WindowsLookAndFeel.setMnemonicHidden(false);
		}
		return s_windowsLnF;
	}

	public static void setWindowsLookAndFeel()
	{
		try
		{
			WindowsLookAndFeel windowsLnF = getWindowsLookAndFeel();
			JDialog.setDefaultLookAndFeelDecorated(false);
			JFrame.setDefaultLookAndFeelDecorated(false);

			UIManager.setLookAndFeel(windowsLnF);

			// Splitter
			UIManager.put("SplitPane.dividerSize", new Integer(10));

			AlloyLookAndFeel.updateAllUIs();

			IconFactory.setThemeDirectory(DEFAULT_THEME_DIR);
			changeFontsAndDefaultColors();
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}