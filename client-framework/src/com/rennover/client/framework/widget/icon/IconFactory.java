/*
 * Copyright (c) 2013 Rennover Technologies, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Rennover Technologies, Inc.
 */

/*
 * 
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

package com.rennover.client.framework.widget.icon;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.rennover.hotel.common.helper.ResourceHelper;
import com.rennover.hotel.common.log.Logger;

/**
 * @author Piyush
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public abstract class IconFactory {
	public static final boolean DEBUG = false;

	private static Icon downArrowIcon;

	private static Icon upArrowIcon;

	private static Icon unknownIcon;

	private static Map<String, Image> imageMap = new HashMap<String, Image>();

	private static Map<String, Image> grayImageMap = new HashMap<String, Image>();

	private static Map<String, Image> brightImageMap = new HashMap<String, Image>();

	private static Map<String, Icon> iconMap = new HashMap<String, Icon>();

	private static Map<String, Icon> grayIconMap = new HashMap<String, Icon>();

	private static Map<String, Icon> brightIconMap = new HashMap<String, Icon>();

	private static String rootDirectory = "com/hotel/client/images";

	private static String themeDirectory = "default";

	private static String defaultThemeDirectory = "default";

	public static Image getUnknownImage() {
		if (unknownIcon == null) {
			unknownIcon = new UnknownIcon();
		}
		return null;
	}

	private static Image loadImage(String filename) {
		if (DEBUG) {
			System.out.println("loadImage( '" + filename + "' )");
		}
		URL url = ResourceHelper.getResourceUrl(IconFactory.class, filename);
		if (url == null) {
			Logger.error(IconFactory.class, "Image file '" + filename
					+ "' was not found.");
			return getUnknownImage();
		}
		return Toolkit.getDefaultToolkit().getImage(url);
	}

	public static Image getImage(String imageName) {
		return getImage(imageName, true, false);
	}

	public static Image getImage(String imageName, boolean reload) {
		return getImage(imageName, true, reload);
	}

	/**
	 * Get image from Image name.
	 * 
	 * @param imageName
	 *            Image Name
	 * @param load
	 *            Load image or not
	 * @param reload
	 *            Reload
	 * @return Image
	 */
	public static Image getImage(String imageName, boolean load, boolean reload) {
		if (DEBUG) {
			System.out.println("getImage( '" + imageName + "' )");
		}

		Image image = null;

		// cache
		if (!reload) {
			image = (Image) imageMap.get(imageName);
		}

		// search
		if (image == null && load) {
			image = searchImage(imageName);
			if (image != null) {
				putImage(imageName, image);
			} else {
				Logger.error(IconFactory.class, "The image file '" + imageName
						+ "' could not be loaded.");
			}
		}

		return image;
	}

	/**
	 * 
	 * @param imageName
	 *            String
	 * @return Image
	 */
	public static Image searchImage(String imageName) {
		Image image = null;
		// try current theme
		if (image == null) {
			image = loadImage(getRootDirectoryName() + "/"
					+ getThemeDirectory() + "/" + imageName);
		}
		// try default theme
		if (image == null) {
			image = loadImage(getRootDirectoryName() + "/"
					+ getDefaultThemeDirectory() + "/" + imageName);
		}
		return image;
	}

	/**
	 * 
	 * @param imageName
	 *            String
	 * @param image
	 *            Image
	 */
	protected static void putImage(String imageName, Image image) {
		if (image != null) {
			imageMap.put(imageName, image);

			// Gray Image
			if (grayImageMap.containsKey(imageName)) {
				Image otherImage = createGrayImage(image);
				grayImageMap.put(imageName, otherImage);
			}

			// Bright Image
			if (brightImageMap.containsKey(imageName)) {
				Image otherImage = createBrightImage(image);
				brightImageMap.put(imageName, otherImage);
			}
		}
	}

	/**
	 * 
	 * @param imageName
	 *            String
	 * @return
	 */
	public static Image getGrayImage(String imageName) {
		Image image = null;
		image = (Image) grayImageMap.get(imageName);
		if (image == null) {
			Image originalImage = getImage(imageName);
			if (originalImage != null) {
				image = createGrayImage(originalImage);
				grayImageMap.put(imageName, image);
			}
		}
		return image;
	}

	/**
	 * 
	 * @param imageName
	 *            String
	 * @return Image
	 */
	public static Image getBrightImage(String imageName) {
		Image image = null;
		image = (Image) brightImageMap.get(imageName);
		if (image == null) {
			Image originalImage = getImage(imageName);
			if (originalImage != null) {
				image = createBrightImage(originalImage);
				brightImageMap.put(imageName, image);
			}
		}
		return image;
	}

	/**
	 * 
	 */
	private static void reload() {
		Set<String> keySet = iconMap.keySet();
		Iterator<String> ite = keySet.iterator();
		while (ite.hasNext()) {
			String iconName = (String) ite.next();
			ImageIcon icon = (ImageIcon) iconMap.get(iconName);
			Image image = getImage(iconName, true);
			if (image != null) {
				icon.setImage(image);

				// Gray Image
				if (grayIconMap.containsKey(iconName)) {
					Image otherImage = getGrayImage(iconName);
					ImageIcon otherIcon = (ImageIcon) grayIconMap.get(iconName);
					otherIcon.setImage(otherImage);
				}

				// Bright Image
				if (brightIconMap.containsKey(iconName)) {
					Image otherImage = getBrightImage(iconName);
					ImageIcon otherIcon = (ImageIcon) brightIconMap
							.get(iconName);
					otherIcon.setImage(otherImage);
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public static Icon getUnknownIcon() {
		if (unknownIcon == null) {
			unknownIcon = new UnknownIcon();
		}
		return unknownIcon;
	}

	public static void setRootDirectory(String themeName) {
		themeDirectory = themeName;
	}

	public static void setThemeDirectory(String themeName) {
		themeDirectory = themeName;
		reload();
	}

	public static String getThemeDirectory() {
		return themeDirectory;
	}

	private static String getRootDirectoryName() {
		return rootDirectory;
	}

	public static Icon getIcon(String iconName) {
		if (DEBUG) {
			System.out.println("getIcon( '" + iconName + "' )");
		}

		Icon icon = (Icon) iconMap.get(iconName);
		if (icon == null || icon == getUnknownIcon()) {
			Image image = getImage(iconName);
			if (image == null) {
				Logger.error(IconFactory.class, "The icon file '" + iconName
						+ "' could not be loaded.");
				return getUnknownIcon();
			}

			icon = new ImageIcon(getImage(iconName));
			iconMap.put(iconName, icon);
		}
		return icon;
	}

	public static Icon getGrayIcon(String iconName) {
		if (DEBUG) {
			System.out.println("getGrayIcon( '" + iconName + "' )");
		}

		Icon icon = (Icon) grayIconMap.get(iconName);
		if (icon == null || icon == getUnknownIcon()) {
			Image image = getGrayImage(iconName);
			if (image == null) {
				Logger.error(IconFactory.class, "File icon '" + iconName
						+ "' could not be loaded.");
				return getUnknownIcon();
			}

			icon = new ImageIcon(image);
			grayIconMap.put(iconName, icon);
		}
		return icon;
	}

	public static Icon getBrightIcon(String iconName) {
		if (DEBUG) {
			System.out.println("getBrightIcon( '" + iconName + "' )");
		}

		Icon icon = (Icon) brightIconMap.get(iconName);
		if (icon == null || icon == getUnknownIcon()) {
			Image image = getBrightImage(iconName);
			if (image == null) {
				Logger.error(IconFactory.class, "File icon '" + iconName
						+ "' could not be loaded.");
				return getUnknownIcon();
			}

			icon = new ImageIcon(image);
			brightIconMap.put(iconName, icon);
		}
		return icon;
	}

	public static Icon getDownArrowIcon() {
		if (downArrowIcon == null) {
			downArrowIcon = new DownArrowIcon();
		}
		return downArrowIcon;
	}

	public static Icon getUpArrowIcon() {
		if (upArrowIcon == null) {
			upArrowIcon = new UpArrowIcon();
		}
		return upArrowIcon;
	}

	public static Icon getErrorIcon() {
		return getIcon("icones/error.gif");
	}

	public static Icon getWarningIcon() {
		return getIcon("icones/warning.gif");
	}

	public static Icon getRefreshIcon() {
		return getIcon("boutons/refresh.gif");
	}

	public static Icon getDeleteIcon() {
		return getIcon("boutons/delete.jpg");
	}

	public static Icon getSearchIcon() {
		return getIcon("boutons/search.gif");
	}

	public static Icon getValidateIcon() {
		return getIcon("boutons/validate.gif");
	}

	public static Icon getCancelIcon() {
		return getIcon("boutons/cancel.gif");
	}

	public static Icon getSaveIcon() {
		return getIcon("boutons/save.gif");
	}

	public static Icon getPrintIcon() {
		return getIcon("boutons/print.gif");
	}

	// Added by Harish on 4-11-2004
	public static Icon getContinueIcon() {
		return getIcon("boutons/continue.gif");
	}

	public static Icon getDemandeOKIcon() {
		return getIcon("icones/ok.gif");
	}

	public static Icon getDemandeKOIcon() {
		return getIcon("icones/ko.gif");
	}

	public static Icon getDemandeTraiteeIcon() {
		return getIcon("icones/current.gif");
	}

	public static Icon getDemandeNonTraiteeIcon() {
		return getIcon("icones/unknown.gif");
	}

	// Added by Joseph on 30-11-2004
	public static Icon getPropertiesIcon() {
		return getIcon("boutons/properties.jpg");
	}

	public static Icon getDetailIcon() {
		return getIcon("boutons/detail.gif");
	}

	/**
	 * @return
	 */
	public static String getDefaultThemeDirectory() {
		return defaultThemeDirectory;
	}

	/**
	 * @param string
	 */
	public static void setDefaultThemeDirectory(String string) {
		defaultThemeDirectory = string;
	}

	public static Image createGrayImage(Image image) {
		image = BrightFilter.createBrightImage(image, 25);
		return GrayFilter.createDisabledImage(image);
	}

	public static Image createBrightImage(Image image) {
		return BrightFilter.createBrightImage(image, 25);
	}

}