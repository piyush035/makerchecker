package com.rennover.client.framework.widget.spinner;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.rennover.client.framework.widget.field.NumericField;

public class NumericSpinnerEditor extends JPanel implements ChangeListener, LayoutManager
{
	/**
	 * Action used to increment the current value.
	 */
	private final Action m_SharedIncrementAction = new IncrementAction("increment", true);

	/**
	 * Action used to decrement the current value.
	 */
	private final Action m_SharedDecrementAction = new IncrementAction("decrement", false);

	public NumericSpinnerEditor(JSpinner spinner, Class numberClass, int length)
	{
		super(null);

		SpinnerNumberModel spinnerModel = (SpinnerNumberModel) spinner.getModel();
		int minValue = ((Integer) spinnerModel.getMinimum()).intValue();
		int maxValue = ((Integer) spinnerModel.getMaximum()).intValue();

		NumericField field = new NumericField(numberClass, length, 0, minValue, maxValue, false);
		field.setUsingThousandSeparator(false);
		field.setValue(spinner.getValue());
		field.addFocusListener(new FocusAdapter()
		{
			public void focusLost(FocusEvent e)
			{
				commitEdit();
			}
		});
		field.setEditable(true);
		add(field);

		setLayout(this);
		spinner.addChangeListener(this);

		// Install actions to increment/decrement the value when
		// the editor has focus.
		ActionMap map = SwingUtilities.getUIActionMap(spinner);

		if (map == null)
		{
			map = new javax.swing.plaf.ActionMapUIResource();
			SwingUtilities.replaceUIActionMap(spinner, map);
		}

		map.clear();
		map.put("increment", m_SharedIncrementAction);
		map.put("decrement", m_SharedDecrementAction);

		replaceAction(spinner, "increment", m_SharedIncrementAction);
		replaceAction(spinner, "decrement", m_SharedDecrementAction);

		releaseMouse(spinner);

		System.out.println("-------------------------------------");

	}

	private static void replaceAction(JSpinner spinner, String actionName, Action action)
	{
		for (int i = 0; i < spinner.getComponentCount(); i++)
		{
			Component comp = (Component) spinner.getComponent(i);
			if (comp instanceof JButton)
			{
				JButton button = (JButton) comp;
				replaceAction(button, actionName, action);
			}
		}
	}

	private static void replaceAction(JButton component, String actionName, Action newAction)
	{
		ActionListener[] listeners = component.getActionListeners();
		for (int i = 0; i < listeners.length; i++)
		{
			ActionListener listener = (ActionListener) listeners[i];
			if (listener instanceof Action)
			{
				Action action = (Action) listener;
				if (actionName.equals(action.getValue(Action.NAME)))
				{
					component.removeActionListener(listener);
					newAction.putValue(Action.NAME, actionName);
					component.addActionListener(newAction);
					break;
				}
			}
		}
	}

	private static void releaseMouse(Component component)
	{
		MouseListener[] listeners = component.getMouseListeners();
		for (int i = 0; i < listeners.length; i++)
		{
			MouseListener listener = (MouseListener) listeners[i];
			listener.mouseReleased(new MouseEvent(component, MouseEvent.MOUSE_RELEASED, System.currentTimeMillis(), 0,
			        0, 0, 1, false));
		}
	}

	public void dismiss(JSpinner spinner)
	{
		spinner.removeChangeListener(this);
	}

	private boolean m_commitEdit = false;

	public void commitEdit()
	{
		m_commitEdit = true;

		try
		{
			Number value = (Number) getNumericField().getValue();
			getSpinner().setValue(value);
		} finally
		{
			m_commitEdit = false;
		}
	}

	public JSpinner getSpinner()
	{
		for (Component c = this; c != null; c = c.getParent())
		{
			if (c instanceof JSpinner)
			{
				return (JSpinner) c;
			}
		}
		return null;
	}

	public NumericField getNumericField()
	{
		return (NumericField) getComponent(0);
	}

	public void stateChanged(ChangeEvent e)
	{
		JSpinner spinner = (JSpinner) (e.getSource());
		getNumericField().setValue(spinner.getValue());
	}

	// removed the commented method propertyChange(PropertyChangeEvent e)

	public void addLayoutComponent(String name, Component child)
	{
	}

	public void removeLayoutComponent(Component child)
	{
	}

	private Dimension insetSize(Container parent)
	{
		Insets insets = parent.getInsets();
		int w = insets.left + insets.right;
		int h = insets.top + insets.bottom;
		return new Dimension(w, h);
	}

	public Dimension preferredLayoutSize(Container parent)
	{
		Dimension preferredSize = insetSize(parent);
		if (parent.getComponentCount() > 0)
		{
			Dimension childSize = getComponent(0).getPreferredSize();
			preferredSize.width += childSize.width;
			preferredSize.height += childSize.height;
		}
		return preferredSize;
	}

	public Dimension minimumLayoutSize(Container parent)
	{
		Dimension minimumSize = insetSize(parent);
		if (parent.getComponentCount() > 0)
		{
			Dimension childSize = getComponent(0).getMinimumSize();
			minimumSize.width += childSize.width;
			minimumSize.height += childSize.height;
		}
		return minimumSize;
	}

	public void layoutContainer(Container parent)
	{
		if (parent.getComponentCount() > 0)
		{
			Insets insets = parent.getInsets();
			int w = parent.getWidth() - (insets.left + insets.right);
			int h = parent.getHeight() - (insets.top + insets.bottom);
			getComponent(0).setBounds(insets.left, insets.top, w, h);
		}
	}

	// removed the commented method commitEdit()

	private class IncrementAction extends AbstractAction
	{
		private boolean m_isNext;

		IncrementAction(String name, boolean isNext)
		{
			super(name);
			this.m_isNext = isNext;
		}

		public void actionPerformed(ActionEvent ae)
		{

			JSpinner spinner = getSpinner();

			if (spinner.getEditor() instanceof NumericSpinnerEditor)
			{
				NumericSpinnerEditor editor = (NumericSpinnerEditor) spinner.getEditor();
				try
				{
					editor.commitEdit();

					Object value = m_isNext ? spinner.getNextValue() : spinner.getPreviousValue();
					if (value != null)
					{
						spinner.setValue(value);
					}
				} catch (IllegalArgumentException iae)
				{
					UIManager.getLookAndFeel().provideErrorFeedback(spinner);
				}
			}
		}
	}
}