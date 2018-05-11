package com.rennover.client.framework.widget.field;

import java.awt.FontMetrics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import com.rennover.hotel.common.helper.DateHelper;
import com.rennover.hotel.common.log.Logger;

/**
 * @author tcaboste
 * 
 */
public class DateField extends InputFormattedTextField
{
	public static final Date INVALID_DATE = DateHelper.INVALID_DATE;

	public static final String EMPTY_DATE = "  /  /    ";

	public static final int MIN_YEAR = 1900;

	public static final int MAX_YEAR = 2099;

	private DateFormat m_dateFormat;

	String m_lastText = EMPTY_DATE;

	private Date m_dateValue = null;

	private int m_columnWidth = 0;

	/**
	 * Constructor for DateTextField.
	 */
	public DateField()
	{
		super();
		try
		{
			m_dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			m_dateFormat.setLenient(false);
			MaskFormatter dateformatter = new MaskFormatter("##/##/####");
			setColumns(8);

			setFormatterFactory(new DefaultFormatterFactory(dateformatter, dateformatter, dateformatter));
			setFocusLostBehavior(COMMIT);

			this.getDocument().addDocumentListener(new DocumentListener()
			{
				public void insertUpdate(DocumentEvent e)
				{
					convertStringToDate();
				}

				public void removeUpdate(DocumentEvent e)
				{
					convertStringToDate();
				}

				public void changedUpdate(DocumentEvent e)
				{
				}
			});
		} catch (ParseException e)
		{
			Logger.error(this, "Can not create instance", e);
		}

		this.addFocusListener(new FocusAdapter()
		{
			public void focusGained(FocusEvent e)
			{
				setCaretPosition(0);
			}
		});
	}

	protected int getColumnWidth()
	{
		if (m_columnWidth == 0)
		{
			FontMetrics metrics = getFontMetrics(getFont());
			m_columnWidth = metrics.charWidth('8');
		}
		return m_columnWidth;
	}

	public void convertStringToDate()
	{
		Date newDate = null;
		String text = getText();

		if (m_lastText.equals(text))
		{
			return;
		} else
		{
			m_lastText = text;
		}

		if ((text == null) || (text.length() == 0) || (EMPTY_DATE.equals(text)))
		{
			newDate = null;
		} else
		{
			if (text.trim().length() != 10)
			{
				newDate = INVALID_DATE;
			} else
			{
				try
				{
					newDate = m_dateFormat.parse(text);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(newDate);
				} catch (ParseException e)
				{
					newDate = INVALID_DATE;
				}
			}
		}

		Date oldValue = m_dateValue;
		m_dateValue = newDate;
		firePropertyChange("dateValue", oldValue, newDate);

		boolean valid = m_dateValue != INVALID_DATE;
		setValidValue(valid);
	}

	public void setFieldValue(Object value)
	{
		Object oldValue = value;

		if (value == null && m_dateValue == null)
		{
			return;
		}

		if (value != null && value.equals(m_dateValue))
		{
			return;
		}

		if (m_dateValue != null && m_dateValue.equals(value))
		{
			return;
		}

		if (value != null)
		{
			String text = m_dateFormat.format(value);
			setText(text);
			setValidValue(true);
		} else
		{
			setText(EMPTY_DATE);
			setValidValue(true);
		}

		firePropertyChange("dateValue", oldValue, value);
	}

	public Object getFieldValue()
	{
		convertStringToDate(); // conversion si nécessaire
		return m_dateValue;
	}
}