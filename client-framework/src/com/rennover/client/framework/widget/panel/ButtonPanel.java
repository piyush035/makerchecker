
package com.rennover.client.framework.widget.panel;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;

import com.rennover.client.framework.widget.base.ZBox;
import com.rennover.client.framework.widget.base.ZButton;
import com.rennover.client.framework.widget.button.SmallButton;

/**
 * Un panneau qui affiche des boutons à l'aide d'une {@link javax.swing.Box}.
 * Selon l'alignement choisi, ces boutons seront alignés à droite ou en haut de
 * la zone dans laquelle ils se trouvent.<br>
 * Ces boutons auront tous la même taille (ils seront aligfnés sur la taille du
 * plus grand).
 * 
 * <p>
 * Dans le cas d'un alignement horizontal, ce panneau sera typiquement placé en
 * bas d'une fenêtre (par exemple dans la zone SOUTH d'un BorderLayout), en
 * s'assurant qu'il y a suffisamment d'espace pour afficher tous les boutons.<br>
 * Dans le cas d'un alignement vertical, on le mettra à droite de la fenêtre
 * (BorderLayout.WEST).
 * </p>
 * 
 * <p>
 * Les boutons ne sont pas créés explicitement par l'utilisateur. En fait, ils
 * ne sont créés qu'en fonction des {@link javax.swing.Action}s qui sont
 * ajoutées par l'utilisateur de cette classe. <br>
 * Du coup, il est impossible d'afficher un bouton qui n'aurait pas d'Action
 * associée (même s'il est bien sûr possible de l'associer à une Action qui ne
 * fait rien).
 * </p>
 * 
 * <p>
 * Par ailleurs, comme l'utilisateur de la classe n'a pas accès directement aux
 * boutons, il n'est pas possible d'associer autre chose qu'une Action à ces
 * boutons.
 * </p>
 */
public class ButtonPanel extends ComponentPanel
{
	private static final int STRUT_BETWEEN_BUTTONS = 5;

	private static final int STRUT_BETWEEN_BUTTON_GROUPS = 30;

	public static final int HORIZONTAL = ComponentPanel.HORIZONTAL;

	public static final int VERTICAL = ComponentPanel.VERTICAL;

	private List m_buttonList = new ArrayList(2);

	private boolean m_resizeButtonMode = true;

	/**
	 * Crée un panneau horizontal vide dont les boutons seront alignés à droite.
	 */
	public ButtonPanel()
	{
		this(HORIZONTAL);
	}

	public ButtonPanel(boolean border)
	{
		this(HORIZONTAL, border);
	}

	public ButtonPanel(int orientation)
	{
		this(orientation, false);
	}

	/**
	 * Crée un panneau horizontal vide dont les boutons seront alignés à droite.
	 */
	public ButtonPanel(int orientation, boolean border)
	{
		super(orientation);
		if (HORIZONTAL == getOrientation())
		{
			addGlue();
		}
		if (border)
		{
			setBorder(new EmptyBorder(4, 4, 4, 4));
		}
	}

	/**
	 * Méthode de prototypage Ajoute un bouton associé à un titre à la suite des
	 * autres boutons, séparé par un espace standard (soit autour de 5 pixels).
	 */
	public ZButton addButtonAction(String title)
	{
		ZButton button = addButton(title, null);
		return button;
	}

	/**
	 * Ajoute un bouton associé à cette action à la suite des autres boutons,
	 * séparé par un espace standard (soit autour de 5 pixels).
	 */
	public ZButton addButtonAction(Action action)
	{
		ZButton button = addButton(null, action);
		return button;
	}

	public ZButton addSmallButtonAction(Action action)
	{
		ZButton button = addSmallButton(null, action);
		return button;
	}

	/**
	 * Utilisation interne : permet de factoriser l'ajout de bouton dans une
	 * méthode
	 * 
	 * @param title
	 * @param action
	 * @return
	 */
	private ZButton addButton(String title, Action action)
	{
		if (m_buttonList.size() > 0)
		{
			addStrut(STRUT_BETWEEN_BUTTONS);
		}

		ZButton button;
		if (action == null)
		{
			button = new ZButton(title);
		} else
		{
			button = new ZButton(action);
		}

		m_buttonList.add(button);
		super.add(button);

		if (isResizeButtonMode())
		{
			resizeButtons();
		}

		return button;
	}

	/**
	 * Utilisation interne : permet de factoriser l'ajout de bouton dans une
	 * méthode
	 * 
	 * @param title
	 * @param action
	 * @return
	 */
	private ZButton addSmallButton(String title, Action action)
	{
		if (m_buttonList.size() > 0)
		{
			addStrut(STRUT_BETWEEN_BUTTONS);
		}

		ZButton button;
		if (action == null)
		{
			button = new SmallButton(title);
		} else
		{
			button = new SmallButton(action);
		}

		m_buttonList.add(button);
		super.add(button);

		if (isResizeButtonMode())
		{
			resizeButtons();
		}

		return button;
	}

	public ZButton addSmallButtonAction(Action action, boolean lockable)
	{
		ZButton button = addSmallButtonAction(action);
		LockHelper.setLockable(button, lockable);
		return button;
	}

	public ZButton addButtonAction(Action action, boolean lockable)
	{
		ZButton button = addButtonAction(action);
		LockHelper.setLockable(button, lockable);
		return button;
	}

	/**
	 * @deprecated Use addButtonAction(Action action, boolean lockable)
	 * 
	 * @param action
	 * @return
	 */
	public ZButton addLockableButtonAction(Action action)
	{
		ZButton button = addButtonAction(action);
		LockHelper.setLockable(button, true);
		return button;
	}

	/**
	 * Recalcule la taille des boutons afin qu'ils aient tous la même taille du
	 * plus grand des boutons.
	 */
	private void resizeButtons()
	{
		// recherche le + grand bouton
		Iterator iter = m_buttonList.iterator();
		int maxWidth = 0;
		int maxHeight = 0;
		while (iter.hasNext())
		{
			ZButton element = (ZButton) iter.next();
			if (element.getPreferredSize().width > maxWidth)
			{
				maxWidth = element.getPreferredSize().width;
			}
			if (element.getPreferredSize().height > maxHeight)
			{
				maxHeight = element.getPreferredSize().height;
			}
		}

		// force tous les boutons a avoir la taille du + grand bouton
		Dimension dim = new Dimension(maxWidth, maxHeight);
		iter = m_buttonList.iterator();
		while (iter.hasNext())
		{
			ZButton element = (ZButton) iter.next();
			element.setPreferredSize(dim);
			element.setMaximumSize(dim);
			element.setMinimumSize(dim);
		}
	}

	/**
	 * Ajoute un espace vide qui s'étendra automatiquement selon la place
	 * disponible, à la suite des boutons déjà créés (à droite ou en dessous,
	 * selon l'alignement). S'il existe plusieurs "glues" dans le panneau,
	 * l'espace disponible sera réparti entre toutes les "glues".
	 * 
	 * <p>
	 * Attention: dans le cas d'un alignement horizontal, il faut se souvenir
	 * qu'il y a déjà une "glue" dans le panneau, à gauche des boutons.
	 * </p>
	 */
	public void addGlue()
	{
		if (HORIZONTAL == getOrientation())
		{
			super.add(Box.createHorizontalGlue());
		} else
		{
			super.add(Box.createVerticalGlue());
		}
	}

	/**
	 * Ajoute un ressort qui va plaquer les premiers boutons sur le côté gauche
	 * du panneau
	 */
	public void addSpring()
	{
		if (HORIZONTAL == getOrientation())
		{
			super.add(ZBox.createHorizontalGlue());
		} else
		{
			super.add(ZBox.createVerticalGlue());
		}
	}

	/**
	 * Ajoute un espace de taille fixe à la suite des boutons déjà existants.
	 */
	public void addStrut(int width)
	{
		if (HORIZONTAL == getOrientation())
		{
			super.add(Box.createHorizontalStrut(width));
		} else
		{
			super.add(Box.createVerticalStrut(width));
		}
	}

	/**
	 * Ajoute un espace de taille moyenne à la suite des boutons déjà existants.
	 * Cet espace séparera typiquement deux groupes de boutons.
	 */
	protected void addSeparator()
	{
		addStrut(STRUT_BETWEEN_BUTTON_GROUPS);
	}

	/**
	 * Returns the resizeButtonMode.
	 * 
	 * @return boolean
	 */
	public boolean isResizeButtonMode()
	{
		return m_resizeButtonMode;
	}

	/**
	 * Sets the resizeButtonMode.
	 * 
	 * @param resizeButtonMode
	 *            The resizeButtonMode to set
	 */
	public void setResizeButtonMode(boolean resizeButtonMode)
	{
		m_resizeButtonMode = resizeButtonMode;
	}

	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		LockHelper.setLocked(this, !enabled);
	}
	// removed the commented method setEnabled(boolean enabled)

}