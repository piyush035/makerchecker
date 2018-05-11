package com.rennover.client.framework.widget.field;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParsePosition;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.textfield.LimitedDocument;
import com.rennover.client.framework.window.WindowManager;
import com.rennover.hotel.common.exception.IncoherenceException;
import com.rennover.hotel.common.valueobject.SmallDecimal;

/**
 * Valeur par défaut dans le constructeur à vide : 7,2 (7 chiffres dont 2 après
 * la virgule), valeur : 99999,99 (sép milliers = DEFAULT_THOUSAND_SEP, sép
 * décimal = DEFAULT_DECIMAL_SEP, aligné à droite)
 * 
 * Masque naturel construit par la méthode setNumberDigits : signe, partie
 * entière avec sép milliers = DEFAULT_THOUSAND_SEP, sépdécimal =
 * DEFAULT_DECIMAL_SEP, partie décimale
 * 
 * Un masque est une String ressemblant à "#,###.00" # représente un chiffre et
 * supprime les zéros significatifs 0 représente un chiffre et laisse les zéros
 * significatifs , dans le masque représente le séparateur de milliers . dans le
 * masque représente le séparateur décimal
 */
public class NumericField extends InputTextField
{
	/**
	 * Séparateur des milliers par défaut : ' '
	 */
	public static final char DEFAULT_THOUSAND_SEP = ' ';

	/**
	 * Séparateur décimal par défaut : ','
	 */
	public static final char DEFAULT_DECIMAL_SEP = ',';

	public static final int INTEGER_MAX_DIGITS = 10;

	public static final int SHORT_MAX_DIGITS = 5;

	public static final int LONG_MAX_DIGITS = 19;

	public static final int BYTE_MAX_DIGITS = 3;

	private int m_maxIntChar = 5;

	private int m_maxDecChar = 2;

	private char m_sep = NumericField.DEFAULT_THOUSAND_SEP;

	private char m_decSep = NumericField.DEFAULT_DECIMAL_SEP;

	private String m_mask = null;

	private Pattern m_patternMask = null;

	private String m_format = "#####.##";

	private DecimalFormat m_decimalFormat = new DecimalFormat();

	private DecimalFormatSymbols m_decimalFormatSymbols = new DecimalFormatSymbols();

	private char m_decimalSeparator = m_decimalFormatSymbols.getDecimalSeparator();

	private Class m_numberClass = Double.class;

	private boolean m_nullValueAvailable;

	private boolean m_usingThousandSeparator = true;

	private boolean m_usingNegative = true;

	private Double m_min;

	private Double m_max;

	private Border m_normalBorder;

	private Border m_errorBorder;

	private int m_columnWidth = 0;

	private String m_lastText = null;

	private Object m_value = null;

	private boolean m_dontSelectNextTime = false;

	private boolean m_selectAllOnFocus = true;

	/**
	 * Constructeur à vide
	 */
	public NumericField()
	{
		super();
		setDocument(new NumericFieldDocument());
		commonConstructor();

		// Valeur par défaut
		this.setText("");
	}

	/**
	 * Constructeur :
	 * 
	 * @param numberClass
	 *            type de la valeur à retournée
	 * @param length
	 *            nbre de chiffres au total (sans virgule)
	 * @param dec
	 *            nbre de chiffres après la virgule
	 */
	public NumericField(Class numberClass, int length, int dec)
	{
		this(numberClass, length, dec, false);
	}

	public NumericField(Class numberClass, int length, int dec, boolean useNullValue)
	{
		super();
		if (numberClass != null)
		{
			m_numberClass = numberClass;
		}
		setNumberDigits(length, dec);
		commonConstructor();
		setNullValueAvailable(useNullValue);
	}

	/**
	 * Constructeur :
	 * 
	 * @param numberClass
	 *            type de la valeur à retournée
	 * @param length
	 *            nbre de chiffres au total (sans virgule)
	 * @param dec
	 *            nbre de chiffres après la virgule
	 * @param min
	 *            valeur minimale de validité
	 * @param max
	 *            valeur maximale de validité
	 */
	public NumericField(Class numberClass, int length, int dec, double min, double max)
	{
		this(numberClass, length, dec);
		setMinMaxValue(new Double(min), new Double(max));
	}

	public NumericField(Class numberClass, int length, int dec, double min, double max, boolean useNullValue)
	{
		this(numberClass, length, dec, min, max);
		setNullValueAvailable(useNullValue);
	}

	public void setAssociatedLabel(ZLabel associatedLabel)
	{
		if (getAssociatedLabel() == null)
		{
			setErrorBorder(false);
		}
		super.setAssociatedLabel(associatedLabel);
	}

	/**
	 * Modificateur Séparateur décimal
	 */
	public void setDecimalSep(char sep)
	{
		m_decSep = sep;
		updateFormat();
	}

	public void setMaxChars(int maxChars)
	{
		// rien
	}

	public void setMaxValue(Double max)
	{
		m_max = max;
		updateValidity();
	}

	public void setMinValue(Double min)
	{
		m_min = min;
		updateValidity();
	}

	/**
	 * Sets the nullValueAvailable.
	 * 
	 * @param nullValueAvailable
	 *            The nullValueAvailable to set
	 */
	public void setNullValueAvailable(boolean nullValueAvailable)
	{
		if (!nullValueAvailable)
		{
			m_nullValueAvailable = true;
			if (m_value == null)
			{
				setValue(getZeroValue());
			}
		}

		m_nullValueAvailable = nullValueAvailable;
	}

	/**
	 * Returns the nullValueAvailable.
	 * 
	 * @return boolean
	 */
	public boolean isNullValueAvailable()
	{
		return m_nullValueAvailable;
	}

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
		m_maxIntChar = length - dec;
		m_maxDecChar = dec;
		check();
		updateFormat();
	}

	/**
	 * Indique que le contenu du champ doit être sélectionné à chaque entrée de
	 * focus
	 * 
	 * @param use
	 */
	public void setSelectAllOnFocus(boolean use)
	{
		m_selectAllOnFocus = use;
	}

	/**
	 * Indique si le contenu du champ
	 * 
	 * @return
	 */
	public boolean isSelectAllOnFocus()
	{
		return m_selectAllOnFocus;
	}

	// remove the commented method setThousandSep(char sep)

	/**
	 * Indique que le signe moins peut être utilisé
	 * 
	 * @param use
	 */
	public void setUseNegative(boolean use)
	{
		m_usingNegative = use;
		updateFormat();
	}

	/**
	 * Indique si le signe moins est utilisé
	 * 
	 * @return
	 */
	public boolean getUseNegative()
	{
		return m_usingNegative;
	}

	/**
	 * Indique qu'il faut afficher le séparateur des milliers
	 * 
	 * @param use
	 */
	public void setUsingThousandSeparator(boolean use)
	{
		m_usingThousandSeparator = use;
		updateFormat();
		setValue(getValue());
	}

	/**
	 * Indique que le séparateur des milliers est affiché
	 * 
	 * @return boolean
	 */
	public boolean isUsingThousandSeparator()
	{
		return m_usingThousandSeparator;
	}

	/**
	 * indique si la valeur saisie est valide
	 */
	public boolean isValidValue()
	{
		boolean valid = true;

		Object value = getValue();
		if (value == null)
		{
			// si la valeur est nulle le contenu doit l'être aussi (sinon erreur
			// de parsing)
			String text = this.getText();
			if (!isEmptyString(text))
			{
				valid = false;
			}
		} else
		{
			// la valeur doit être dans les limites
			double doubleValue = ((Number) value).doubleValue();

			if ((m_min != null) && doubleValue < m_min.doubleValue())
			{
				valid = false;
			}

			if ((m_max != null) && doubleValue > m_max.doubleValue())
			{
				valid = false;
			}
		}

		return valid;
	}

	/**
	 * Modificateur de la valeur du nombre
	 */
	public void setValue(Object value)
	{
		if (value == null && !m_nullValueAvailable)
		{
			value = getZeroValue();
		}

		m_value = value;

		if (value instanceof Number)
		{
			Number number = (Number) value;
			if (number instanceof SmallDecimal)
			{
				String formattedValue = ((SmallDecimal) number).format(m_maxDecChar);
				this.setText(formattedValue);
			} else
			{
				double doubleValue = number.doubleValue();
				this.setText(m_decimalFormat.format(doubleValue));
			}
		} else
		{
			setText("");
		}

		updateValidity();
	}

	/**
	 * Récupère la valeur numérique saisie
	 */
	public Object getValue()
	{
		stringToValue();

		Number number = (Number) m_value;
		if (number == null && !m_nullValueAvailable)
		{
			number = new Integer(0);
		}

		Number value = convertNumberTo(number, m_numberClass);
		return value;
	}

	public static boolean checkFormat(Class numberClass, int intDigits, int decDigits)
	{
		boolean error = false;
		if (Integer.class.isAssignableFrom(numberClass))
		{
			error = (decDigits != 0) || (intDigits >= INTEGER_MAX_DIGITS);
		} else if (Short.class.isAssignableFrom(numberClass))
		{
			error = (decDigits != 0) || (intDigits >= SHORT_MAX_DIGITS);
		} else if (Long.class.isAssignableFrom(numberClass))
		{
			error = (decDigits != 0) || (intDigits >= LONG_MAX_DIGITS);
		} else if (Byte.class.isAssignableFrom(numberClass))
		{
			error = (decDigits != 0) || (intDigits >= BYTE_MAX_DIGITS);
		}

		return !error;
	}

	/**
	 * @return
	 */
	public Class getNumberClass()
	{
		return m_numberClass;
	}

	/**
	 * Usage interne Permet de ne pas sélectionner le contenu du champs au
	 * prochain focusGained
	 */
	public void dontSelectNextTime()
	{
		m_dontSelectNextTime = true;
	}

	/**
	 * override of de JTextField.getColumnWidth()
	 */
	protected int getColumnWidth()
	{
		if (m_columnWidth == 0)
		{
			FontMetrics metrics = getFontMetrics(getFont());
			m_columnWidth = metrics.charWidth('8');
		}
		return m_columnWidth;
	}

	protected void setErrorBorder(boolean active)
	{
		setBorder(active ? m_errorBorder : m_normalBorder);
	}

	/**
	 * override of JTextField.createDefaultModel
	 */
	protected Document createDefaultModel()
	{
		return new NumericFieldDocument();
	}

	private void setEditMask(String mask)
	{
		this.m_mask = mask;
		this.m_patternMask = Pattern.compile(mask);
	}

	private static boolean isEmptyString(String text)
	{
		return (text == null || text.length() == 0);
	}

	private void setFormat(String format)
	{
		m_format = format;
		m_decimalFormatSymbols.setGroupingSeparator(m_sep);
		m_decimalFormatSymbols.setDecimalSeparator(m_decSep);
		m_decimalFormat = new DecimalFormat(format, m_decimalFormatSymbols);
	}

	// remove the commented method getFormat(String format)

	private void setMinMaxValue(Double min, Double max)
	{
		m_min = min;
		m_max = max;
		updateValidity();
	}

	private void _setValue(Object newValue)
	{
		Object oldValue = m_value;

		if (!equals(oldValue, newValue))
		{
			m_value = newValue;
			firePropertyChange("value", oldValue, newValue);
		}
	}

	/**
	 * @param m_numberClass
	 * @param number
	 * @return
	 */
	private static Number convertNumberTo(Number number, Class numberClass)
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

	private Number getZeroValue()
	{
		return convertNumberTo(new Integer(0), m_numberClass);
	}

	private void check()
	{
		Class numberClass = m_numberClass;
		int nbDec = m_maxDecChar;
		int nbInt = m_maxIntChar;

		if (!checkFormat(numberClass, nbInt, nbDec))
		{
			try
			{
				throw new IncoherenceException("Type format '" + (nbInt + nbDec) + "," + nbDec
				        + "' is not compatible with the class number '" + numberClass.getName() + "'.");
			} catch (IncoherenceException exc)
			{
				WindowManager.showExceptionMessage(exc, WindowManager.getActiveWindow());
			}
		}
	}

	private void commonConstructor()
	{
		m_normalBorder = getBorder();
		m_errorBorder = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED);

		// Alignement à droite
		this.setHorizontalAlignment(JTextField.RIGHT);

		// Reformattage à la sortie du champ
		this.addFocusListener(new java.awt.event.FocusAdapter()
		{
			public void focusGained(FocusEvent e)
			{
				if (m_selectAllOnFocus && !m_dontSelectNextTime)
				{
					selectAll();
				}

				if (m_dontSelectNextTime)
				{
					m_dontSelectNextTime = false;
				}
			}

			public void focusLost(FocusEvent e)
			{
				setValue(getValue());
			}
		});
	}

	private static String createDecThousandPattern(int nbChar, boolean thousand)
	{
		if (thousand)
		{
			StringBuffer pattern = new StringBuffer();
			for (int i = 0; i < nbChar - 1; i++)
			{
				pattern.append("(\\d ?|\\d?)");
			}
			pattern.append("(\\d?)");

			return pattern.toString();
		} else
		{
			return "\\d{1," + new Integer(nbChar) + "}";
		}
	}

	private static String createFormat(boolean signe, int nbInt, int nbDec, boolean thousand)
	{
		int reste = nbInt % 3;
		int quotient = nbInt / 3;
		StringBuffer buffer = new StringBuffer();

		// partie entière avec les milliers
		// début
		for (int i = 0; i < reste; i++)
		{
			buffer.append("#");
		}

		// regroupement par trois
		for (int i = 0; i < quotient; i++)
		{
			if (i + 1 < quotient)
			{
				if (thousand)
				{
					buffer.append(",");
				}
				buffer.append("###");
			} else
			{
				if (thousand)
				{
					buffer.append(",");
				}
				buffer.append("##0");
			}
		}

		// ajout des décimales
		if (nbDec > 0)
		{
			buffer.append(".");
			for (int i = 0; i < nbDec; i++)
			{
				buffer.append("0");
			}
		}

		return buffer.toString();
	}

	private static String createMask(boolean signe, int nbInt, int nbDec, boolean thousand)
	{
		String signepart = signe ? "(- ?|-?)" : "";
		String intpart = createDecThousandPattern(nbInt, thousand);
		String decsep = "(\\.|\\,)";
		String decpart = "\\d{1," + new Integer(nbDec) + "}";

		StringBuffer pattern = new StringBuffer();

		if (nbInt > 0)
		{
			if (signe)
			{
				pattern.append("^" + signepart + "\\z|");
			}
			pattern.append("^" + signepart + intpart + "\\z");
		}

		if (nbDec > 0)
		{
			pattern.append("|");
			pattern.append("^" + signepart + intpart + decsep + "\\z|");
			pattern.append("^" + signepart + intpart + decsep + decpart + "\\z");
		}

		return pattern.toString();
	}

	private boolean equals(Object v1, Object v2)
	{
		if (v1 != null && v2 != null)
		{
			return v1.equals(v2);
		} else
		{
			return v1 == v2;
		}
	}

	private void stringToValue()
	{
		String text = this.getText();
		if (text.equals(m_lastText))
		{
			return;
		}

		m_lastText = text;

		Object newValue = m_value;

		if (isEmptyString(text))
		{
			newValue = null;
		} else
		{
			Number number;

			// cas spécial pour le moins tout seul
			if ("-".equals(text))
			{
				text = "0";
			}

			if (m_numberClass == SmallDecimal.class)
			{
				number = new SmallDecimal(text);
			} else
			{
				number = m_decimalFormat.parse(text, new ParsePosition(0));
			}

			newValue = convertNumberTo(number, m_numberClass);
		}

		_setValue(newValue);

		updateValidity();
	}

	private void updateColumns()
	{
		int sizeMax = m_maxIntChar + m_maxDecChar;

		if (m_usingNegative)
		{
			sizeMax++;
		}

		if (m_maxDecChar > 0)
		{
			sizeMax++;
		}

		setColumns(sizeMax);
	}

	private void updateFormat()
	{
		setFormat(createFormat(m_usingNegative, m_maxIntChar, m_maxDecChar, m_usingThousandSeparator));
		setEditMask(createMask(m_usingNegative, m_maxIntChar, m_maxDecChar, m_usingThousandSeparator));
		updateColumns();
	}

	private void updateValidity()
	{
		setValidField(isValidValue());
	}

	private class NumericFieldDocument extends LimitedDocument
	{
		public void insertString(int offs, String strToInsert, AttributeSet a) throws BadLocationException
		{
			strToInsert = strToInsert.replace('.', m_decimalSeparator);

			String oldText = this.getText(0, getLength());
			StringBuffer text = new StringBuffer(oldText);
			text.insert(offs, strToInsert);

			Matcher matcher = m_patternMask.matcher(text);
			boolean matches = matcher.matches();

			if (matches)
			{
				super.insertString(offs, strToInsert, a);
				stringToValue();
			}
		}

		public void remove(int offs, int len) throws BadLocationException
		{
			super.remove(offs, len);
			stringToValue();
		}
	}
}
