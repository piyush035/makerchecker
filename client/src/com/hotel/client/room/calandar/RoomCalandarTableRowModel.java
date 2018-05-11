package com.hotel.client.room.calandar;

import com.rennover.client.framework.widget.table.TableRowModel;

/**
 * 
 * @author Piyush
 * 
 */
public class RoomCalandarTableRowModel extends TableRowModel implements
		RoomCalandarConstants {
	/**
	 * Retrieves the column headings of the tables.
	 */
	protected String[] getTitles() {
		return new String[] { "1", "2" };
	}

	/**
	 * Retrieves the value in a table cell.
	 * 
	 * @param rowObject
	 *            the object represented by a table row
	 * @param columnIndex
	 *            the index of the column in which you want to display a value
	 */
	protected Object getValueAt(int columnIndex, Object rowObject) {
		/*ReferenceReglement referenceReglement = (ReferenceReglement) rowObject;

		switch (columnIndex) {
		case 0:
			if (PropertyHelper.isNull(referenceReglement.getReglementNumber())) {
				return " ";
			} else {
				return referenceReglement.getReglementNumber();
			}
		case 1:
			if (PropertyHelper.isNull(referenceReglement.getMontantTTC())) {
				return " ";
			} else {
				return referenceReglement.getMontantTTC().format(2);
			}

		case 2:
			if (PropertyHelper.isNull(referenceReglement
					.getRemiseBanqueNumber())) {
				return " ";
			} else {
				return referenceReglement.getRemiseBanqueNumber();
			}

		case 3:
			if (PropertyHelper.isNull(referenceReglement.getBanqueName())) {
				return " ";
			} else {
				return referenceReglement.getBanqueName();
			}

		case 4: {
			String numeroAV = "";
			if (!PropertyHelper.isNull(referenceReglement.getNumeroAV())) {
				numeroAV = referenceReglement.getNumeroAV();
			}
			if (!PropertyHelper.isNull(referenceReglement.getDateEmissionAV())) {
				// defect 21703
				Date dateEmissionAv = referenceReglement.getDateEmissionAV();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				numeroAV = numeroAV + " " + dateFormat.format(dateEmissionAv);
			}
			return numeroAV;
		}
		case 5: {
			String numeroRL = "";
			if (!PropertyHelper.isNull(referenceReglement.getNumeroRL())) {
				numeroRL = referenceReglement.getNumeroRL();
			}
			if (!PropertyHelper.isNull(referenceReglement.getDateEmissionRL())) {
				// defect 21703
				Date dateEmissionRL = referenceReglement.getDateEmissionRL();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				numeroRL = numeroRL + " " + dateFormat.format(dateEmissionRL);
			}
			return numeroRL;
		}

		default:
			throw new IncoherenceException("Index non géré");
		}*/
		return null;
	}

	/**
	 * Gets the type of the values ??shown in the columns. this allows Have
	 * publishers default corresponding to certain types. (checkbox For
	 * booleans, combo for enumerations, etc.. By default, the type Of each
	 * column is Object. Override this method to have a Different behavior.
	 */
	public Class[] getColumnTypes() {
		return new Class[] { String.class, Integer.class };
	}

}
