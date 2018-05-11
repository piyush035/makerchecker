/*
 * 
 */
package com.hotel.client.common.window;

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
import com.rennover.client.framework.window.AlloyCustomColorSet;
import com.rennover.client.framework.window.LookAndFeelManager;

/**
 * @author Piyush
 * 
 */
public class ColorWindow extends ZDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5730259152170041886L;

	private ColorField m_primaryColorField = new ColorField();

	private ColorField m_secondaryColorField = new ColorField();

	private ColorField m_arrowButtonColorField = new ColorField();

	private ColorField m_frameColorField = new ColorField();

	private ColorField m_desktopColorField = new ColorField();

	private ColorField m_highlightColorField = new ColorField();

	private ColorField m_rolloverColorField = new ColorField();

	private ColorField m_selectionColorField = new ColorField();

	private Action m_applyAction = new AbstractAction("Appliquer") {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5323838456486899333L;

		public void actionPerformed(ActionEvent e) {
			apply();
		}
	};
	
	private Action m_validateAction = new AbstractAction("Validate") {
		/**
		 * 
		 */
		private static final long serialVersionUID = 4597251225142213979L;

		public void actionPerformed(ActionEvent e) {
			apply();
			dispose();
		}
	};

	private Action m_cancelAction = new AbstractAction("Cancel") {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2485418228719291390L;

		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	};

	public ColorWindow(ZFrame parent) {
		super(parent);
		init();
	}

	private void init() {
		setModal(false);
		setTitle("Customized colors");

		FieldPanel panel = new FieldPanel();

		addColorField(panel, "Primary Color", m_primaryColorField);
		addColorField(panel, "Secondary Color", m_secondaryColorField);

		addColorField(panel, "Desktop Color", m_desktopColorField);
		addColorField(panel, "Highlight Color", m_highlightColorField);
		addColorField(panel, "Rollover Color", m_rolloverColorField);
		addColorField(panel, "Selection Color", m_selectionColorField);

		panel.addButtonAction(m_applyAction);
		panel.addButtonAction(m_validateAction);
		panel.addButtonAction(m_cancelAction);

		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(getParent());

		AlloyCustomColorSet colors = LookAndFeelManager.getCurrentAlloyColors();
		setColors(colors);
	}

	private void setColors(AlloyCustomColorSet colors) {
		m_primaryColorField.setColor(colors.m_primary);
		m_secondaryColorField.setColor(colors.m_secondary);
		m_arrowButtonColorField.setColor(colors.m_arrowButton);
		m_frameColorField.setColor(colors.m_frame);
		m_desktopColorField.setColor(colors.m_desktop);
		m_highlightColorField.setColor(colors.m_highlight);
		m_rolloverColorField.setColor(colors.m_rollover);
		m_selectionColorField.setColor(colors.m_selection);
	}

	public AlloyCustomColorSet getColors() {
		AlloyCustomColorSet colors = new AlloyCustomColorSet();

		colors.m_primary = m_primaryColorField.getColor();
		colors.m_secondary = m_secondaryColorField.getColor();
		colors.m_arrowButton = m_arrowButtonColorField.getColor();
		colors.m_frame = m_frameColorField.getColor();
		colors.m_desktop = m_desktopColorField.getColor();
		colors.m_highlight = m_highlightColorField.getColor();
		colors.m_rollover = m_rolloverColorField.getColor();
		colors.m_selection = m_selectionColorField.getColor();

		return colors;
	}

	private void apply() {
		AlloyCustomColorSet colors = getColors();
		LookAndFeelManager.setAlloyCustomLookAndFeel(colors);
	}

	private void addColorField(FieldPanel panel, String label, ColorField field) {
		panel.addComponent(label, field, new SelectColorAction(label, field));
	}

	private class SelectColorAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8625766595929710119L;

		private String m_label;

		private ColorField m_colorField;

		public SelectColorAction(String label, ColorField field) {
			m_label = label;
			m_colorField = field;
		}

		public void actionPerformed(ActionEvent e) {
			Color oldColor = m_colorField.getColor();
			Color newColor = JColorChooser.showDialog(ColorWindow.this,
					m_label, oldColor);
			if (newColor != null) {
				m_colorField.setColor(newColor);
			}
		}
	}

	private static class ColorField extends ZLabel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6433998744982930234L;
		Color m_color;

		public ColorField() {
			setOpaque(true);
			setMinimumSize(new Dimension(32, 21));
			setBorder(new LineBorder(Color.BLACK));
			setHorizontalAlignment(CENTER);
		}

		public void setColor(Color color) {
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

		public Color getColor() {
			return m_color;
		}
	}
}