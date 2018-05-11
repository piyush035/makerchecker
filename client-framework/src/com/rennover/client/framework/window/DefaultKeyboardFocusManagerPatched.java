package com.rennover.client.framework.window;

import java.awt.AWTKeyStroke;
import java.awt.Component;
import java.awt.Container;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.FocusTraversalPolicy;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.Set;

/**
 * The default KeyboardFocusManager for AWT applications. Focus traversal is
 * done in response to a Component's focus traversal keys, and using a
 * Container's FocusTraversalPolicy.
 * 
 * @author David Mendenhall
 * @version 1.14, 04/11/02
 * 
 * @see FocusTraversalPolicy
 * @see Component#setFocusTraversalKeys
 * @see Component#getFocusTraversalKeys
 * @since 1.4
 */
public class DefaultKeyboardFocusManagerPatched extends DefaultKeyboardFocusManager
{

	private boolean m_consumeNextKeyTyped;

	/**
	 * This method initiates a focus traversal operation if and only if the
	 * KeyEvent represents a focus traversal key for the specified
	 * focusedComponent. It is expected that focusedComponent is the current
	 * focus owner, although this need not be the case. If it is not, focus
	 * traversal will nevertheless proceed as if focusedComponent were the focus
	 * owner.
	 * 
	 * @param focusedComponent
	 *            the Component that is the basis for a focus traversal
	 *            operation if the specified event represents a focus traversal
	 *            key for the Component
	 * @param e
	 *            the event that may represent a focus traversal key
	 */
	public void processKeyEvent(Component focusedComponent, KeyEvent e)
	{
		// KEY_TYPED events cannot be focus traversal keys
		if (e.getID() == KeyEvent.KEY_TYPED)
		{
			if (m_consumeNextKeyTyped)
			{
				e.consume();
				m_consumeNextKeyTyped = false;
			}

			return;
		}

		if (focusedComponent.getFocusTraversalKeysEnabled() && !e.isConsumed())
		{
			AWTKeyStroke stroke = AWTKeyStroke.getAWTKeyStrokeForEvent(e);
			AWTKeyStroke oppStroke = AWTKeyStroke.getAWTKeyStroke(
			        stroke.getKeyCode(), stroke.getModifiers(), !stroke.isOnKeyRelease());
			Set toTest;
			boolean contains;
			boolean containsOpp;

			toTest = focusedComponent.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
			contains = toTest.contains(stroke);
			containsOpp = toTest.contains(oppStroke);

			if (contains || containsOpp)
			{
				// BEGIN Correction BUG 4845868:REGRESSION: First keystroke
				// after JDialog is closed is lost

				// removed the commented code which was used to fix defect
				// 4845868

				e.consume();
				m_consumeNextKeyTyped = (e.getID() == KeyEvent.KEY_PRESSED);
				if (contains)
				{
					focusNextComponent(focusedComponent);
				}

				// END Correction BUG 4845868:REGRESSION: First keystroke after
				// JDialog is closed is lost

				return;
			}

			toTest = focusedComponent.getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS);
			contains = toTest.contains(stroke);
			containsOpp = toTest.contains(oppStroke);

			if (contains || containsOpp)
			{

				// BEGIN Correction BUG 4845868:REGRESSION: First keystroke
				// after JDialog is closed is lost
				// removed the commented code which was used to fix defect
				// 4845868
				e.consume();
				m_consumeNextKeyTyped = (e.getID() == KeyEvent.KEY_PRESSED);
				if (contains)
				{
					focusPreviousComponent(focusedComponent);
				}

				// END Correction BUG 4845868:REGRESSION: First keystroke after
				// JDialog is closed is lost

				return;
			}

			toTest = focusedComponent.getFocusTraversalKeys(KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS);
			contains = toTest.contains(stroke);
			containsOpp = toTest.contains(oppStroke);

			if (contains || containsOpp)
			{
				if (contains)
				{
					upFocusCycle(focusedComponent);
				}
				e.consume();
				m_consumeNextKeyTyped = (e.getID() == KeyEvent.KEY_PRESSED);
				return;
			}

			if (!((focusedComponent instanceof Container) && ((Container) focusedComponent).isFocusCycleRoot()))
			{
				return;
			}

			toTest = focusedComponent.getFocusTraversalKeys(KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS);
			contains = toTest.contains(stroke);
			containsOpp = toTest.contains(oppStroke);

			if (contains || containsOpp)
			{
				if (contains)
				{
					downFocusCycle((Container) focusedComponent);
				}
				e.consume();
				m_consumeNextKeyTyped = (e.getID() == KeyEvent.KEY_PRESSED);
			}
		}
	}

}
