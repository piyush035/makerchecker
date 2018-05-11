
package com.rennover.client.framework.window;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JScrollBar;
import javax.swing.LayoutFocusTraversalPolicy;
import javax.swing.text.JTextComponent;

/**
 * @author tcaboste
 * 
 * Cette classe gère le passage de focus entre les composants d'une fenêtre.
 * Elle se base sur l'algorithme du LayoutFocusTraversalPolicy, mais dispose de
 * la méthode isCycleFocusableComponent lui permettant d'identifier les
 * composants à sauter lors du passage.
 * 
 * This class manage the focus changing between components in a window. This
 * class is based on the LayoutFocusTraversalPolicy, but the method
 * isCycleFocusableComponent can identify the components to skip.
 * 
 */
public class DefaultLayoutTraversalPolicy extends LayoutFocusTraversalPolicy
{
	public Component getComponentBefore(Container focusCycleRoot, Component aComponent)
	{
		Component nextComponent = super.getComponentBefore(focusCycleRoot, aComponent);

		while (!isCycleFocusableComponent(nextComponent) && (nextComponent != null))
		{
			nextComponent = super.getComponentBefore(focusCycleRoot, nextComponent);
		}

		return nextComponent;
	}

	public Component getComponentAfter(Container focusCycleRoot, Component aComponent)
	{
		Component nextComponent = super.getComponentAfter(focusCycleRoot, aComponent);

		while (!isCycleFocusableComponent(nextComponent) && (nextComponent != null))
		{
			nextComponent = super.getComponentAfter(focusCycleRoot, nextComponent);
		}

		return nextComponent;
	}

	/**
	 * 
	 * Permet de savoir si le composant passé en paramètre est utilisable dans
	 * le passage de focus. Si oui le passage de focus s'arrêtera sur ce
	 * composant, sinon il recherchera le composant suivant selon le sens.
	 * 
	 * Identify if the component can be used in the focus changing. If true the
	 * focus changing will stop on this component, if not the focus changing
	 * will search the next component.
	 * 
	 * @param component
	 *            component to check
	 * @return true if the focus changing can stop on the component
	 * 
	 */
	public static boolean isCycleFocusableComponent(Component component)
	{
		if (component == null)
		{
			return false;
		}

		// textcomponents must be editable
		if (component instanceof JTextComponent)
		{
			return ((JTextComponent) component).isEditable();
		}

		// scrollbars are not in the focus changing
		if (component instanceof JScrollBar)
		{
			return false;
		}

		return true;
	}
}