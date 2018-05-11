package com.rennover.client.framework.valueobject.instanthelp;

/**
 * @author tcaboste
 * 
 * Interface entre les implémentations de zone d'aide à la saisie et
 * l'InstantHelpManager
 * 
 */
public interface InstantHelpView
{
	// Messages constants
	public static final String INVALID_PROPERTIES_HELP = "Les champs en rouge contiennent des erreurs. Cliquez-les pour plus de détails.";

	public static final String MANDATORY_HELP = "Les champs en gras sont obligatoires.";

	public int NO_ICON = 0;

	public int WARNING_ICON = 1;

	public int ERROR_ICON = 2;

	/**
	 * Méthode permettant d'afficher un message d'aide à la saisie
	 * 
	 * @param text
	 *            texte à afficher
	 */
	public void displayString(String text);

	/**
	 * Méthode permettant d'afficher un message d'aide à la saisie
	 * 
	 * @param text
	 *            texte à afficher
	 */
	public void displayString(int iconStyle, String text);
}