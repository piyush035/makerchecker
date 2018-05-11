package com.hotel.client.state;

import com.hotel.base.common.domain.country.dto.CountryDto;
import com.hotel.base.common.helper.PropertyHelper;
import com.hotel.client.configuration.constant.ConfigurationConstants;
import com.rennover.client.framework.widget.table.TableRowModel;
import com.rennover.hotel.common.exception.IncoherenceException;

/**
 * @author Piyush
 */
public class StateTableRowModel extends TableRowModel implements ConfigurationConstants {
	/**
	 * Retrieves the column headings of the tables.
	 */
	protected String[] getTitles() {
		return new String[] {
				COUNTRY_CODE, COUNTRY_NAME
		};
	}
	
	/**
	 * Retrieves the value in a table cell.
	 * @param rowObject the object represented by a table row
	 * @param columnIndex the index of the column in which you want to display a value
	 */
	protected Object getValueAt(int columnIndex, Object rowObject) {
		CountryDto countryDto = (CountryDto) rowObject;
		
		switch (columnIndex) {
			case 0:
				if (countryDto.getCountryCode() == 0) {
					return " ";
				} else {
					return countryDto.getCountryCode();
				}
			case 1:
				if (PropertyHelper.isNull(countryDto.getCountryName())) {
					return " ";
				} else {
					return countryDto.getCountryName();
				}
				
			default:
				throw new IncoherenceException("Unmanaged index");
		}
	}
	
	/**
	 * Gets the type of the values shown in the columns. this allows Have publishers default corresponding to certain
	 * types. (checkbox For booleans, combo for enumerations, etc.. By default, the type Of each column is Object.
	 * Override this method to have a Different behavior.
	 */
	public Class[] getColumnTypes() {
		return new Class[] {
				String.class, String.class
		};
	}
	
}
