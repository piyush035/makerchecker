package com.rennover.client.framework.widget.table;

/**
 * Cette classe définit quelles valeurs doivent être affichées dans les cellules
 * d'une table affichant des ValueObjects.
 */
public abstract class TableRowModel
{
	/**
	 * Récupère les titres des colonnes de la tables
	 */
	protected abstract String[] getTitles();

	/**
	 * Récupère le type des valeurs affichées dans les colonnes. Cela permet
	 * d'avoir des editeurs par defaut correspondant à certains types. (checkbox
	 * pour les booleans, combo pour des enumerations, etc. Par defaut, le type
	 * de chaque colonne est Object. Surcharger cette méthode pour avoir un
	 * comportement different
	 */
	protected Class[] getColumnTypes()
	{
		Class[] result = new Class[getTitles().length];
		for (int i = 0; i < result.length; i++)
		{
			result[i] = Object.class;
		}
		return result;
	}

	/**
	 * Récupère l'ensemble des tailles de chaque colonne
	 * 
	 * @return
	 */
	protected ColumnSize[] getColumnsSize()
	{
		return null;
	}

	/**
	 * Récupère l'ensemble des tooltips de l'entête
	 * 
	 * @return
	 */
	protected String[] getTooltips()
	{
		return null;
	}

	/**
	 * Récupère la valeur affichée dans une cellule du tableau
	 * 
	 * @param rowObject
	 *            l'objet représenté par une ligne du tableau
	 * @param columnIndex
	 *            l'index de la colonne dans laquelle on souhaite afficher une
	 *            valeur
	 */
	protected abstract Object getValueAt(int columnIndex, Object rowObject);

	/**
	 * Modifie la valeur dans une cellule du tableau
	 * 
	 * @param columnIndex
	 *            l'index de la colonne dans laquelle on souhaite afficher une
	 *            valeur
	 * @param rowObject
	 *            l'objet représenté par une ligne du tableau
	 * @param value
	 *            la valeur que va recevoir l'objet
	 */
	protected void setValueAt(int columnIndex, Object rowObject, Object value)
	{
	}

	/**
	 * Indique si une cellule est éditable
	 * 
	 * @param columnIndex
	 * @param rowObject
	 * @return
	 */
	protected boolean isCellEditable(int columnIndex, Object rowObject)
	{
		return false;
	}
}