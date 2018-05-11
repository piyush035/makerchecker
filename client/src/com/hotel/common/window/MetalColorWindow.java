package com.hotel.common.window;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JColorChooser;
import javax.swing.border.LineBorder;

import com.rennover.client.framework.widget.base.ZDialog;
import com.rennover.client.framework.widget.base.ZFrame;
import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.panel.FieldPanel;
import com.rennover.client.framework.window.LookAndFeelManager;
import com.rennover.client.framework.window.MetalCustomColorSet;

/**
 * @author Piyush
 * 
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MetalColorWindow extends ZDialog
{
	private ColorField m_primary1ColorField = new ColorField();

	private ColorField m_primary2ColorField = new ColorField();

	private ColorField m_primary3ColorField = new ColorField();

	private ColorField m_secondary1ColorField = new ColorField();

	private ColorField m_secondary2ColorField = new ColorField();

	private ColorField m_secondary3ColorField = new ColorField();

	private Action m_applyAction = new AbstractAction("Apply")
	{
		public void actionPerformed(ActionEvent e)
		{
			apply();
		}
	};

	private Action m_validateAction = new AbstractAction("Validate")
	{
		public void actionPerformed(ActionEvent e)
		{
			apply();
			dispose();
		}
	};

	private Action m_cancelAction = new AbstractAction("Cancel")
	{
		public void actionPerformed(ActionEvent e)
		{
			dispose();
		}
	};

	public MetalColorWindow(ZFrame parent)
	{
		super(parent);
		init();
	}

	private void init()
	{
		setModal(false);
		setTitle("Personnalisation des couleurs");

		FieldPanel panel = new FieldPanel();

		addColorField(panel, "Primary 1 Color", m_primary1ColorField);
		addColorField(panel, "Primary 2 Color", m_primary2ColorField);
		addColorField(panel, "Primary 3 Color", m_primary3ColorField);
		addColorField(panel, "Secondary 1 Color", m_secondary1ColorField);
		addColorField(panel, "Secondary 2 Color", m_secondary2ColorField);
		addColorField(panel, "Secondary 3 Color", m_secondary3ColorField);

		panel.addButtonAction(m_applyAction);
		panel.addButtonAction(m_validateAction);
		panel.addButtonAction(m_cancelAction);

		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(getParent());

		MetalCustomColorSet colors = LookAndFeelManager.getCurrentMetalColors();
		setColors(colors);
	}

	private void setColors(MetalCustomColorSet colors)
	{
		m_primary1ColorField.setColor(colors.m_primary1);
		m_primary2ColorField.setColor(colors.m_primary2);
		m_primary3ColorField.setColor(colors.m_primary3);
		m_secondary1ColorField.setColor(colors.m_secondary1);
		m_secondary2ColorField.setColor(colors.m_secondary2);
		m_secondary3ColorField.setColor(colors.m_secondary3);
	}

	public MetalCustomColorSet getColors()
	{
		MetalCustomColorSet colors = new MetalCustomColorSet();

		colors.m_primary1 = m_primary1ColorField.getColor();
		colors.m_primary2 = m_primary2ColorField.getColor();
		colors.m_primary3 = m_primary3ColorField.getColor();
		colors.m_secondary1 = m_secondary1ColorField.getColor();
		colors.m_secondary2 = m_secondary2ColorField.getColor();
		colors.m_secondary3 = m_secondary3ColorField.getColor();

		return colors;
	}

	private void apply()
	{
		MetalCustomColorSet colors = getColors();
		LookAndFeelManager.setMetalCustomLookAndFeel(colors);
	}

	private void addColorField(FieldPanel panel, String label, ColorField field)
	{
		panel.addComponent(label, field, new SelectColorAction(label, field));
	}

	private class SelectColorAction extends AbstractAction
	{
		private String m_label;

		private ColorField m_colorField;

		public SelectColorAction(String label, ColorField field)
		{
			m_label = label;
			m_colorField = field;
		}

		public void actionPerformed(ActionEvent e)
		{
			Color oldColor = m_colorField.getColor();
			Color newColor = JColorChooser.showDialog(MetalColorWindow.this, m_label, oldColor);
			if (newColor != null)
			{
				m_colorField.setColor(newColor);
			}
		}
	}

	private static class ColorField extends ZLabel
	{
		Color m_color;

		public ColorField()
		{
			setOpaque(true);
			setMinimumSize(new Dimension(32, 21));
			setBorder(new LineBorder(Color.BLACK));
			setHorizontalAlignment(CENTER);
		}

		public void setColor(Color color)
		{
			m_color = color;

			int red = color.getRed();
			int green = color.getGreen();
			int blue = color.getBlue();

			Color negColor = new Color(255 - red, 255 - green, 255 - blue);

			setForeground(negColor);
			setBackground(color);

			StringBuffer buffer = new StringBuffer();
			buffer.append(red);
			buffer.append(" - ");
			buffer.append(green);
			buffer.append(" - ");
			buffer.append(blue);

			setText(buffer.toString());
		}

		public Color getColor()
		{
			return m_color;
		}
	}
}