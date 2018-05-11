package com.rennover.client.framework.widget.field;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import com.rennover.client.framework.widget.base.ZFormattedTextField;
import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.hotel.common.log.Logger;

/**
 * InputFormattedTextField utilise le MaskFormatter de Swing Il est possible de
 * définir plusieurs masques un à l'affichage et l'autre à la saisie.
 * 
 * Pour définir les masques, il faut utiliser les caractères suivants :
 *  # pour un chiffre ? pour une lettre U pour une lettre en masjuscule L pour
 * une lettre en minuscule A pour une lettre ou un chiffre * pour n'importe quel
 * caractère H pour un caractère hexadécimal
 */
public class InputFormattedTextField extends ZFormattedTextField implements InputField
{
	private ZLabel m_associatedLabel;

	private boolean m_mandatoryField = false;

	private boolean m_validField = true;

	private boolean m_replaceMode = false;

	String m_lastText = "";

	Object m_value;

	private boolean m_validValue = true;

	/**
	 * Constructor for ZFormattedTextField.
	 */
	public InputFormattedTextField()
	{
		super();
	}

	/**
	 * Constructeur avec un format particulier
	 */
	public InputFormattedTextField(String format)
	{
		this(format, format);
	}

	/**
	 * Constructeur avec un format pour l'affichage et un format pour la saisie
	 */
	public InputFormattedTextField(String displayFormat, String editFormat)
	{
		super();
		setColumns(displayFormat.length());

		try
		{
			MaskFormatter displayFormatter = new MaskFormatter(displayFormat);
			MaskFormatter editFormatter = new MaskFormatter(editFormat);
			editFormatter.setPlaceholderCharacter(' ');
			editFormatter.setAllowsInvalid(false);
			editFormatter.setOverwriteMode(true);

			editFormatter.setValueContainsLiteralCharacters(true);

			setFormatterFactory(new DefaultFormatterFactory(displayFormatter, displayFormatter, editFormatter));
			setFocusLostBehavior(COMMIT);

			setFieldValue(null);

			this.getDocument().addDocumentListener(new DocumentListener()
			{
				public void insertUpdate(DocumentEvent e)
				{
					getFieldValue();
				}

				public void removeUpdate(DocumentEvent e)
				{
					getFieldValue();
				}

				public void changedUpdate(DocumentEvent e)
				{
				}
			});
		} catch (ParseException e)
		{
			Logger.error(this, "Can not create instance", e);
		}
		;

		this.addFocusListener(new FocusAdapter()
		{
			public void focusGained(FocusEvent e)
			{
				setCaretPosition(0);
			}
		});
	}

	public void setText(String t)
	{
		setReplaceMode(true);
		try
		{
			super.setText(t);
		} finally
		{
			setReplaceMode(false);
		}
	}

	/**
	 * @return
	 */
	public boolean isReplaceMode()
	{
		return m_replaceMode;
	}

	/**
	 * @param b
	 */
	public void setReplaceMode(boolean b)
	{
		m_replaceMode = b;
	}

	public Object getFieldValue()
	{
		convertStringToValue();
		return m_value;
	}

	public void convertStringToValue()
	{
		if (m_lastText.equals(getText()))
		{
			return;
		}

		Object value;
		try
		{
			commitEdit();
			value = this.getValue();
			setValidValue(true);
		} catch (ParseException ex)
		{
			value = this.getText().trim();
			boolean valid = (value == null || "".equals(value));
			setValidValue(valid);
		}
		m_value = value;
	}

	public void setFieldValue(Object value)
	{
		setValue(value);

		if (value != null)
		{
			setText(value.toString());
			setValidValue(true);
		} else
		{
			setText("");
			setValidValue(true);
		}

		convertStringToValue();
		setValidField(isValidValue());
	}

	// removed the commented method processFocusEvent,getValue,setValue

	// --- Gestion de l'aspect obligatoire et de validité ainsi que du label
	// associé
	public void setMandatoryField(boolean mandatory)
	{
		m_mandatoryField = mandatory;
		InputFieldHelper.doDisplayMandatory(this, mandatory);
	}

	public boolean isMandatoryField()
	{
		return m_mandatoryField;
	}

	public void setValidField(boolean valid)
	{
		m_validField = valid;
		InputFieldHelper.doDisplayValid(this, valid);
	}

	public boolean isValidValue()
	{
		return m_validValue;
	}

	public void setValidValue(boolean valid)
	{
		m_validValue = valid;
		setValidField(valid);
	}

	public boolean isValidField()
	{
		return m_validField;
	}

	public boolean isValueSetted()
	{
		return true;
	}

	public ZLabel getAssociatedLabel()
	{
		return m_associatedLabel;
	}

	public void setAssociatedLabel(ZLabel associatedLabel)
	{
		m_associatedLabel = associatedLabel;
		InputFieldHelper.doDisplayState(this);
	}

	public boolean isReadOnly()
	{
		return InputFieldHelper.isReadOnly(this);
	}

	public void setReadOnly(boolean readOnly)
	{
		InputFieldHelper.setReadOnly(this, readOnly);
	}

	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		InputFieldHelper.doDisplayState(this);
	}

	public void setVisible(boolean aFlag)
	{
		super.setVisible(aFlag);
		if (m_associatedLabel != null)
		{
			m_associatedLabel.setVisible(aFlag);
		}
	}
}