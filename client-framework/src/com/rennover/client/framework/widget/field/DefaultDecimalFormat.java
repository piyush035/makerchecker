package com.rennover.client.framework.widget.field;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParsePosition;
import java.util.StringTokenizer;

import com.rennover.hotel.common.valueobject.SmallDecimal;

/**
 * @author tcaboste
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class DefaultDecimalFormat
{
	/**
	 * Séparateur des milliers par défaut : ' '
	 */
	public static final char DEFAULT_THOUSAND_SEP = ' ';

	/**
	 * Séparateur décimal par défaut : ','
	 */
	public static final char DEFAULT_DECIMAL_SEP = ',';

	private char m_sep = DEFAULT_THOUSAND_SEP;

	private char m_decSep = DEFAULT_DECIMAL_SEP;

	private int m_nbreChif = 7;

	private int m_nbreChifDec = 2;

	private int m_entiers;

	private int m_lengthTotale;

	private boolean m_usingThousandSeparator = true;

	private DecimalFormat m_format = new DecimalFormat();

	private String m_mask = "#####.##";

	protected DecimalFormat m_decimalFormat = new DecimalFormat();

	protected DecimalFormatSymbols m_decimalFormatSymbols = new DecimalFormatSymbols();

	protected char m_decimalSeparator = m_decimalFormatSymbols.getDecimalSeparator();

	/**
	 * Modificateur du format du nombre Définition du nombre avec le nombre
	 * total de chiffres (length) et le nombre de chiffres après la virgule. Il
	 * construit un masque naturel (signe, partie entière avec sép.milliers = ''
	 * sép décimal = DEFAULT_DECIMAL_SEP, partie décimale)
	 * 
	 * @param length
	 *            nombre total de chiffres
	 * @param dec
	 *            nombre de décimales
	 */
	public void setNumberDigits(int length, int dec)
	{
		m_nbreChif = length;
		m_nbreChifDec = dec;
		m_entiers = m_nbreChif - m_nbreChifDec;
		m_lengthTotale = m_entiers + m_nbreChifDec;

		if (m_nbreChifDec > 0)
		{
			m_lengthTotale++;
		}

		int reste = m_entiers % 3;
		int quotient = m_entiers / 3;
		StringBuffer sb = new StringBuffer();

		// Construction du masque
		for (int i = 0; i < reste; i++)
		{
			sb.append("#");
		}
		for (int i = 0; i < quotient; i++)
		{
			if (i + 1 < quotient)
			{
				if (m_usingThousandSeparator)
				{
					sb.append(",###");
				} else
				{
					sb.append("###");
				}
			} else
			{
				if (m_usingThousandSeparator)
				{
					sb.append(",##0");
				} else
				{
					sb.append("##0");
				}
			}
		}
		if (m_nbreChifDec > 0)
		{
			sb.append(".");
		}
		for (int i = 0; i < dec; i++)
		{
			sb.append("0");
		}

		setEditMask(sb.toString());
	}

	public static String createMask(int length, int dec, boolean useNegative, boolean usingThousandSeparator,
	        boolean usingZeroes)
	{
		int entiers = length - dec;
		int lengthTotale = entiers + dec;

		if (dec > 0)
		{
			lengthTotale++;
		}

		int reste = entiers % 3;
		int quotient = entiers / 3;

		StringBuffer sb = new StringBuffer();

		if (useNegative)
		{
			sb.append("-");
		}

		// Construction du masque
		for (int i = 0; i < reste; i++)
		{
			if (usingZeroes)
			{
				sb.append("0");
			} else
			{
				sb.append("#");
			}
		}

		for (int i = 0; i < quotient; i++)
		{
			if (i + 1 < quotient)
			{
				if (usingThousandSeparator)
				{
					if (usingZeroes)
					{
						sb.append(",000");
					} else
					{
						sb.append(",###");
					}
				} else
				{
					if (usingZeroes)
					{
						sb.append("000");
					} else
					{
						sb.append("###");
					}
				}
			} else
			{
				if (usingThousandSeparator)
				{
					if (usingZeroes)
					{
						sb.append(",000");
					} else
					{
						sb.append(",##0");
					}
				} else
				{
					if (usingZeroes)
					{
						sb.append("000");
					} else
					{
						sb.append("##0");
					}
				}
			}
		}
		if (dec > 0)
		{
			sb.append(".");
		}
		for (int i = 0; i < dec; i++)
		{
			sb.append("0");
		}

		String mask = sb.toString();

		return mask;
	}

	/**
	 * Modificateur masque d'édition Masque d'édition (le séparateur de millers
	 * par défaut est DEFAULT_THOUSAND_SEP et le séparateur décimal par défaut
	 * est DEFAULT_DECIMAL_SEP)
	 * 
	 * @param mask
	 *            masque
	 */
	public void setEditMask(String mask)
	{
		this.m_mask = mask;
		m_decimalFormatSymbols.setGroupingSeparator(m_sep);
		m_decimalFormatSymbols.setDecimalSeparator(m_decSep);
		m_format = new DecimalFormat(mask, m_decimalFormatSymbols);
	}

	public static DecimalFormat createFormat(String mask)
	{
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setGroupingSeparator(' ');
		dfs.setDecimalSeparator(',');
		return new DecimalFormat(mask, dfs);
	}

	/**
	 * Modificateur Séparateur de milliers
	 */
	public void setThousandSeparator(char sep)
	{
		this.m_sep = sep;
		m_decimalFormatSymbols.setGroupingSeparator(this.m_sep);
		m_format = new DecimalFormat(m_mask, m_decimalFormatSymbols);
	}

	public char getThousandSeparator()
	{
		return m_sep;
	}

	/**
	 * Modificateur Séparateur décimal
	 */
	public void setDecimalSeparator(char sep)
	{
		this.m_decSep = sep;
		m_decimalFormatSymbols.setDecimalSeparator(this.m_decSep);
		m_format = new DecimalFormat(m_mask, m_decimalFormatSymbols);
	}

	/**
	 * Modificateur présentation des zeros non significatifs Suppression ou non
	 * des zéros non significatifs Exemple : avec (7,2) et ZEROES, 12,34 donne à
	 * l'affichage 00012,34
	 */
	public void setZeroesView(boolean useZeroes)
	{
		if (useZeroes)
		{
			int index = 0;
			StringBuffer sb = new StringBuffer();
			int entiers = m_nbreChif - m_nbreChifDec;

			// Recherche du séparateur de milliers dans le nouveau masque
			index = m_mask.indexOf(DEFAULT_DECIMAL_SEP);

			// Construction du nouveau masque
			if (index == -1)
			{
				for (int i = 0; i < entiers; i++)
				{
					sb.append("0");
				}
			} else
			{
				for (int i = 0; i < index; i++)
				{
					sb.append("0");
				}
				sb.append(",");
				for (int i = index; i < entiers; i++)
				{
					sb.append("0");
				}
			}
			if (m_nbreChifDec > 0)
			{
				sb.append(".");
			}
			for (int i = 0; i < m_nbreChifDec; i++)
			{
				sb.append("0");
			}
			m_mask = new String(sb);
			m_format = new DecimalFormat(m_mask, m_decimalFormatSymbols);
		}
	}

	/**
	 * @param m_numberClass
	 * @param number
	 * @return
	 */
	public static Number convertNumberTo(Number number, Class numberClass)
	{
		Number value = null;

		if (number == null)
		{
			value = null;
		} else if (number.getClass().isAssignableFrom(numberClass))
		{
			value = number;
		} else if (Integer.class.isAssignableFrom(numberClass))
		{
			value = new Integer(number.intValue());
		} else if (Short.class.isAssignableFrom(numberClass))
		{
			value = new Short(number.shortValue());
		} else if (Long.class.isAssignableFrom(numberClass))
		{
			value = new Long(number.longValue());
		} else if (Double.class.isAssignableFrom(numberClass))
		{
			value = new Double(number.doubleValue());
		} else if (Float.class.isAssignableFrom(numberClass))
		{
			value = new Float(number.floatValue());
		}

		return value;
	}

	/**
	 * Returns the usingThousandSep.
	 * 
	 * @return boolean
	 */
	public boolean isUsingThousandSeparator()
	{
		return m_usingThousandSeparator;
	}

	/**
	 * Sets the usingThousandSep.
	 * 
	 * @param usingThousandSep
	 *            The usingThousandSep to set
	 */
	public void setUsingThousandSeparator(boolean usingThousandSep)
	{
		m_usingThousandSeparator = usingThousandSep;
		setNumberDigits(m_nbreChif, m_nbreChifDec);
	}

	/**
	 * @return
	 */
	public int getLengthTotale()
	{
		return m_lengthTotale;
	}

	/**
	 * @param text
	 * @return
	 */
	public Number parse(String text)
	{
		return m_format.parse(text, new ParsePosition(0));
	}

	/**
	 * @return
	 */
	public char getDecimalSeparator()
	{
		return m_decimalSeparator;
	}

	/**
	 * @return
	 */
	public int getEntiers()
	{
		return m_entiers;
	}

	/**
	 * @return
	 */
	public int getNbreChifDec()
	{
		return m_nbreChifDec;
	}

	/**
	 * @param number
	 * @return
	 */
	public String format(Number number)
	{
		if (number instanceof SmallDecimal)
		{
			String formattedValue = format((SmallDecimal) number, m_nbreChifDec);
			return formattedValue;
		} else
		{
			double doubleValue = number.doubleValue();
			return m_decimalFormat.format(doubleValue);
		}
	}

	public static String format(SmallDecimal sd, int digits)
	{
		String tmp = sd.toString();
		int length = tmp.length();
		StringBuffer buffer = new StringBuffer(tmp);
		int index = tmp.indexOf(",");
		if (index == -1 && digits == 0)
		{
			return tmp;
		} else if (index == -1)
		{
			buffer.append(",");
			index = length++;
		} else if (digits == 0)
		{
			return tmp.substring(0, index);
		}
		tmp = buffer.toString();
		index = tmp.indexOf(',');
		while (length++ - index - 1 < digits)
		{
			buffer.append("0");
		}
		StringTokenizer tokens = new StringTokenizer(buffer.toString(), ",");
		buffer = new StringBuffer(tokens.nextToken());
		buffer.reverse();
		int n = (int) (index / 3);
		if (index % 3 == 0)
		{
			n--;
		}
		for (; n > 0; n--)
		{
			buffer.insert(n * 3, " ");
		}
		String decimal = tokens.nextToken();
		while (decimal.endsWith("0") && decimal.length() > digits)
		{
			decimal = decimal.substring(0, decimal.length() - 1);
		}
		return buffer.reverse().append(",").append(decimal).toString();
	}
}