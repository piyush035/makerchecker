
package com.rennover.client.framework.mvc;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JComponent;

import com.rennover.client.framework.window.WindowManager;

/**
 * @author tcaboste
 * 
 * Cette classe est utilis�e pour cr�� les actions utilis�es dans les
 * contr�leurs.
 * 
 */
public class InvokeMethodAction extends AbstractAction
{
	private Object m_controller;

	private Method m_method;

	private String m_methodParameter;

	private int m_waitingType = Controller.NO_WAITING_STYLE;

	/**
	 * Constructeur
	 * 
	 * @param controller
	 *            objet servant de contr�leur contenant la m�thode � appeler
	 * @param actionName
	 *            nom de l'action
	 * @param methodName
	 *            nom de la m�thode � appeler
	 */
	public InvokeMethodAction(Object controller, String actionName, String methodName)
	{
		this(controller, actionName, getMethodFrom(controller, methodName), null, Controller.NO_WAITING_STYLE, null,
		        null, '\0');
	}

	public InvokeMethodAction(Object controller, String actionName, Method method)
	{
		this(controller, actionName, method, null, Controller.NO_WAITING_STYLE, null, null, '\0');
	}

	/**
	 * Constructeur
	 * 
	 * @param controller
	 *            objet servant de contr�leur contenant la m�thode � appeler
	 * @param actionName
	 *            nom de l'action
	 * @param method
	 *            m�thode � appeler
	 * @param waitingType
	 *            type d'attente durant l'ex�cution de la m�thode
	 *            (Controller.NO_WAITING_STYLE | Controller.HOURGLASS |
	 *            Controller.DIALOG)
	 * @param icon
	 *            ic�ne de l'action
	 * @param tooltipText
	 *            infobulle de l'action
	 * @param mnemonicKey
	 *            menmonic de l'action
	 */
	public InvokeMethodAction(Object controller, String actionName, Method method, int waitingType, Icon icon,
	        String tooltipText, char mnemonicKey)
	{
		this(controller, actionName, method, null, Controller.NO_WAITING_STYLE, icon, tooltipText, mnemonicKey);
	}

	/**
	 * Constructeur
	 * 
	 * @param controller
	 *            objet servant de contr�leur contenant la m�thode � appeler
	 * @param actionName
	 *            nom de l'action
	 * @param method
	 *            m�thode � appeler
	 * @param m_methodParameter
	 *            param�tres de la m�thode � appeler
	 * @param waitingType
	 *            type d'attente durant l'ex�cution de la m�thode
	 *            (Controller.NO_WAITING_STYLE | Controller.HOURGLASS |
	 *            Controller.DIALOG)
	 * @param icon
	 *            ic�ne de l'action
	 * @param tooltipText
	 *            infobulle de l'action
	 * @param mnemonicKey
	 *            menmonic de l'action
	 */
	public InvokeMethodAction(Object controller, String actionName, Method method, String methodParameter,
	        int waitingType, Icon icon, String tooltipText, char mnemonicKey)
	{
		super(actionName);
		m_controller = controller;
		m_method = method;
		m_methodParameter = methodParameter;
		m_waitingType = waitingType;
		if (icon != null)
		{
			putValue(SMALL_ICON, icon);
		}
		if (tooltipText != null)
		{
			putValue(SHORT_DESCRIPTION, tooltipText);
		}
		if (mnemonicKey != '\0')
		{
			putValue(MNEMONIC_KEY, new Integer(mnemonicKey));
		}
	}

	/**
	 * M�thode static permettant de retrouver une m�thode d'un objet par son nom
	 * 
	 * @param controller
	 *            objet contenant la m�thode � rechercher
	 * @param methodName
	 *            nom de la m�thode � rechercher
	 * @return m�thode retrouv�e
	 */
	protected static Method getMethodFrom(Object controller, String methodName)
	{
		Method actionMethod = null;
		try
		{
			actionMethod = controller.getClass().getMethod(methodName, null);
		} catch (SecurityException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException("Probl�me de s�curit� sur la m�thode '" + methodName + "' de l'objet '"
			        + controller.getClass() + "'");
		} catch (NoSuchMethodException e)
		{
			e.printStackTrace();
			throw new IllegalArgumentException("La m�thode '" + methodName + "' n'existe pas sur l'objet '"
			        + controller.getClass() + "'");
		}
		return actionMethod;
	}

	/**
	 * M�thode interne impl�mentation de l'interface Action cette m�thode lance
	 * l'invocation de la m�thode en ajoutant le style d'attente et r�cip�rant
	 * certaines exceptions
	 * 
	 * @param e
	 *            information dur l'�v�nement
	 */
	public void actionPerformed(ActionEvent e)
	{
		JComponent source = (e != null) ? (JComponent) e.getSource() : null;

		// removed commented freeze(source);

		// invoke action method
		try
		{
			Object[] param = { m_methodParameter };

			if (m_methodParameter == null)
			{
				m_method.invoke(m_controller, null); // Invocation de la
														// m�thode sans
														// parametre
			} else
			{
				m_method.invoke(m_controller, param); // Invocation de la
														// m�thode avec
														// parametre
			}
		} catch (InvocationTargetException exc)
		{
			if (exc.getCause() == null)
			{
				WindowManager.showExceptionMessage(exc, source);
			} else
			{
				WindowManager.showExceptionMessage(exc.getCause(), source);
			}
		} catch (IllegalArgumentException exc)
		{
			WindowManager.showExceptionMessage(exc, source);
		} catch (IllegalAccessException exc)
		{
			WindowManager.showExceptionMessage(exc, source);
		} finally
		{
			// removed commented unfreeze(source);
		}

	}

	protected void freeze(JComponent component)
	{
		if ((component != null) && (m_waitingType != Controller.NO_WAITING_STYLE))
		{
			WindowManager.freeze(component, m_waitingType);
		}
	}

	protected void unfreeze(JComponent component)
	{
		if ((component != null) && (m_waitingType != Controller.NO_WAITING_STYLE))
		{
			WindowManager.unfreeze(component);
		}
	}

}
