package com.rennover.client.framework.widget.base;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.JTabbedPane;

import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.widget.field.InputField;

/**
 * Un panneau avec des onglets.
 * 
 */
public class ZTabbedPane extends JTabbedPane
{
	/**
	 * 
	 */
	public ZTabbedPane()
	{
		super();
	}

	/**
	 * @deprecated use addPanel(ZPanel)
	 */
	public void addView(PanelView view)
	{
		addTab(view.getTitle(), view);
	}

	/**
	 * 
	 * Utilise le titre(/nom) du panneau comme label de l'onglet
	 * 
	 * addTab marche aussi avec les PanelView: il suffit d'affecter un titre au
	 * PanelView avec setTitle
	 * 
	 */
	public void addTab(ZPanel panel)
	{
		addTab(panel.getTitle(), panel);
	}

	public void insertTab(String title, Icon icon, Component component, String tip, int index)
	{
		super.insertTab(title, icon, component, tip, index);
		// Try block is added for Array Index Outofbound Exception for defect
		// 9212
		try
		{
			updateValidity(index);
		} catch (ArrayIndexOutOfBoundsException e)
		{
			updateValidity(index - 1);
		}

	}

	public void setEnabled(boolean b)
	{
		; // les ZTabbedPane ne sont pas verrouillables
	}

	public void super_setEnabled(boolean b)
	{
		super.setEnabled(b);
	}

	public void setValidTab(int index, boolean validity)
	{
		setForegroundAt(index, validity ? Color.BLACK : Color.RED.darker());
	}

	private Map m_fieldErrorSetMap = new Hashtable();

	public Set getFieldErrorSet(int index)
	{
		Set fieldErrorSet = (Set) m_fieldErrorSetMap.get(new Integer(index));
		if (fieldErrorSet == null)
		{
			fieldErrorSet = new HashSet();
			setFieldErrorSet(index, fieldErrorSet);
		}
		return fieldErrorSet;
	}

	public void setFieldErrorSet(int index, Set fieldErrorSet)
	{
		m_fieldErrorSetMap.put(new Integer(index), fieldErrorSet);
	}

	protected void addFieldError(int index, Component field)
	{
		Set fieldErrorSet = (Set) getFieldErrorSet(index);
		fieldErrorSet.add(field);
		updateTabValidity(index);
	}

	protected void removeFieldError(int index, Component field)
	{
		Set fieldErrorSet = (Set) getFieldErrorSet(index);

		if (!fieldErrorSet.remove(field))
		{
		}

		updateTabValidity(index);
	}

	public void updateTabValidity(int index)
	{
		Set fieldErrorSet = (Set) getFieldErrorSet(index);
		boolean tabValidity = fieldErrorSet.isEmpty();
		setValidTab(index, tabValidity);
	}

	public void updateValidity()
	{
		for (int i = 0; i < getTabCount(); i++)
		{
			updateValidity(i);
		}
	}

	public void updateValidity(int index)
	{
		searchFieldErrors(index);
		updateTabValidity(index);
	}

	public void searchFieldErrors(int index)
	{

		Set fieldErrorSet = new HashSet();
		searchFieldErrors((Container) getComponentAt(index), fieldErrorSet);
		setFieldErrorSet(index, fieldErrorSet);
	}

	private void searchFieldErrors(Container container, Set fieldErrorSet)
	{
		if (container == null)
		{
			return;
		}

		Component[] components = container.getComponents();
		if (components == null)
		{
			return;
		}

		for (int i = 0; i < components.length; i++)
		{
			Component component = components[i];
			if (component instanceof InputField)
			{
				InputField field = (InputField) component;
				if (field.isEnabled() && (!field.isValidField()))
				{
					fieldErrorSet.add(field);
				}
			} else if (component instanceof Container)
			{
				searchFieldErrors((Container) component, fieldErrorSet);
			}
		}
	}

	public static void updateParentValidity(Component field, boolean valid)
	{
		Component component = (Component) field;
		Component parent = component.getParent();
		while (parent != null)
		{
			if (parent instanceof ZTabbedPane)
			{
				ZTabbedPane tabbedPane = (ZTabbedPane) parent;
				int index = tabbedPane.indexOfComponent(component);
				if (valid)
				{
					tabbedPane.removeFieldError(index, field);
				} else
				{
					tabbedPane.addFieldError(index, field);
				}
			}
			component = parent;
			parent = component.getParent();
		}
	}
}