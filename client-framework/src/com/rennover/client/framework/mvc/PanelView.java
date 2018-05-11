
package com.rennover.client.framework.mvc;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;

import com.rennover.client.framework.valueobject.instanthelp.InstantHelpListener;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpManager;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.valueobject.widget.VOPropertyField;
import com.rennover.client.framework.widget.base.ZDialog;
import com.rennover.client.framework.widget.base.ZFrame;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.panel.LockHelper;
import com.rennover.client.framework.window.WindowManager;
import com.rennover.hotel.common.log.Logger;

/**
 * 
 * Classe � sp�cialiser
 * 
 * Cette classe est la repr�sentation d'un �cran.
 * 
 * Elle contient : - l'ensemble des composants graphiques de l'�cran. - la fa�on
 * de la organiser. - la fa�on de les relier aux objets du mod�le. - la fa�on de
 * les relier aux actions du contr�leur.
 * 
 * Cette classe est d�riv�e d'un panneau (ZPanel) et g�re l'interaction avec les
 * fen�tres qui la contienne.
 * 
 * Attention: la m�thode init() doit �tre appel�e apr�s la cr�ation de l'objet
 * 
 * @see com.rennover.client.framework.mvc.PanelViewController
 * @see com.rennover.client.framework.mvc.PanelController
 * @see com.rennover.client.framework.mvc.PanelModel
 */
public abstract class PanelView extends ZPanel
{
	private String m_title;

	private InstantHelpManager m_instantHelpManager = new InstantHelpManager();

	private PanelModel m_model = null;

	private PanelController m_controller = null;

	/**
	 * Instancie le PanelView.
	 * 
	 * Attention: appeler init() avant d'utiliser le PanelView
	 * 
	 */
	public PanelView()
	{
		// init() n'est pas appelee ici car elle peut avoir besoin de donnees
		// qui seront fournies par le controller
	}

	/**
	 * Instancie le PanelView.
	 * 
	 * Attention: appeler init() avant d'utiliser le PanelView
	 * 
	 * @param title
	 *            titre de l'�cran
	 */
	public PanelView(String title)
	{
		setTitle(title);
		// init() n'est pas appelee ici car elle peut avoir besoin de donnees
		// qui seront fournies par le controller
	}

	public PanelView(String title, PanelController controller, PanelModel model)
	{
		setTitle(title);
		setController(controller);
		setModel(model);
		// init() n'est pas appelee ici car elle peut avoir besoin de donnees
		// qui seront fournies par le controller
	}

	public PanelView(PanelController controller, PanelModel model)
	{
		setController(controller);
		setModel(model);
		// init() n'est pas appelee ici car elle peut avoir besoin de donnees
		// qui seront fournies par le controller
	}

	/**
	 * Permet d'ajouter un vom dans la gestion de l'aide instantan�e
	 * 
	 * @param vom
	 *            le vom � ajouter
	 */
	public void addToInstantHelp(ValueObjectModel vom)
	{
		getInstantHelpManager().addValueObjectModel(vom);
	}

	/**
	 * Permet de retirer un vom de la gestion de l'aide instantan�e
	 * 
	 * @param vom
	 *            le vom � retirer
	 */
	public void removeFromInstantHelp(ValueObjectModel vom)
	{
		getInstantHelpManager().removeValueObjectModel(vom);
	}

	/**
	 * Permet de vider la gestion de l'aide instantan�e de tous ses vom
	 */
	public void removeAllFromInstantHelp()
	{
		getInstantHelpManager().removeAllValueObjectModels();
	}

	public void addInstantHelpListener(InstantHelpListener listener)
	{
		getInstantHelpManager().addInstantHelpListener(listener);
	}

	public void removeInstantHelpListener(InstantHelpListener listener)
	{
		getInstantHelpManager().removeInstantHelpListener(listener);
	}

	/**
	 * Modifie le contenu de la dialog pass�e en param�tre pour qu'elle
	 * contienne la vue.
	 * 
	 * @param dialog
	 *            dialog � modifier
	 */
	public void showAsDialog(ZDialog dialog)
	{
		dialog.setTitle(getTitle());
		dialog.changeContent(this);

		dialog.pack();
		dialog.centerToParent();
	}

	/**
	 * Modifie le contenu de la frame pass�e en param�tre pour qu'elle contienne
	 * la vue.
	 * 
	 * @param frame
	 *            frame � modifier
	 */
	public void showAsFrame(ZFrame frame)
	{
		frame.setTitle(getTitle());
		frame.changeContent(this);
		frame.setVisible(true);
	}

	/**
	 * Ferme la fen�tre dans laquelle est contenue la vue
	 */
	public void close()
	{
		Window parentWindow = getOwningWindow();
		if (parentWindow != null)
		{
			parentWindow.dispose();
		} else
		{
			Logger.warn(this, "close sur une vue sans fen�tre");
		}
	}

	/**
	 * Retourne la fen�tre parente de la vue
	 * 
	 * @return fen�tre parente (Frame ou Dialog)
	 */
	public Window getOwningWindow()
	{
		return WindowManager.getOwningWindow(this);
	}

	/**
	 * Initialise la vue en appelant les m�thodes {@link #instanciate()} et
	 * {@link #compose()}. Cette m�thode doit �tre appel�e explicitement par le
	 * {@link PanelController controller} apr�s avoir instanci� la vue.
	 */
	public final void init()
	{
		try
		{
			instanciate();
			compose();
		} catch (Exception e)
		{
			WindowManager.showExceptionMessage(e, this);
		}
	}

	/**
	 * M�thode � surcharger
	 * 
	 * Instancie tous les widgets utilis�s dans l'�cran.
	 * 
	 */
	protected void instanciate()
	{
	}

	/**
	 * M�thode � surcharger
	 * 
	 * Cette m�thode doit v�rifier que la fen�tre contenant la vue peut se
	 * refermer. Dans le cas contraire, elle renvoie false. La m�thode peut
	 * interroger l'utilisateur ou le contr�leur pour effectuer sa v�rification.
	 * 
	 * @return true si la fen�tre peut se refermer sinon false.
	 */
	public boolean canClose()
	{
		return true;
	}

	public void dispose()
	{
		setModel(null);
		setController(null);
		if (m_instantHelpManager != null)
		{
			m_instantHelpManager.removeAllValueObjectModels();
		}
	}

	/**
	 * Retourne l'instantHelpManager de la vue
	 * 
	 * @return l'instantHelpManager de la vue
	 */
	public InstantHelpManager getInstantHelpManager()
	{
		return m_instantHelpManager;
	}

	/**
	 * Permet d'enregistrer l'instantHelpManager de la vue. Cela permet � la
	 * fen�tre contenant la vue de savoir si elle doit afficher une zone d'aide
	 * de saisie. Cette zone d'aide sera g�r�e par l'instantHelpManager
	 * 
	 * @return l'InstantHelpManager de la vue
	 */
	public void setInstantHelpManager(InstantHelpManager instantHelpMngr)
	{
		m_instantHelpManager = instantHelpMngr;
	}

	/**
	 * Indique si le panneau contient des erreurs de saisie. Les erreurs de
	 * saisie sont g�r�s par l'instantHelpManager. Seuls les valueObjectModels
	 * enregistr�s dans l'instantHelpManager sont pris en compte.
	 * 
	 * @return true ne contient pas d'erreurs, false contient au moins une
	 *         erreur
	 */
	public boolean isValid()
	{
		if (m_instantHelpManager == null)
		{
			return true;
		}

		return !m_instantHelpManager.hasErrors();
	}

	/**
	 * M�thode � surcharger
	 * 
	 * Cette m�thode doit composer l'�cran avec les composants graphiques
	 * souhait�s. Le panneau principal est la vue elle-m�me.
	 */
	protected abstract void compose();

	/**
	 * Retourne le titre de l'�cran
	 * 
	 * @return titre de l'�cran
	 */
	public final String getTitle()
	{
		return m_title;
	}

	/**
	 * Affecte le titre de l'�cran
	 */
	public void setTitle(String title)
	{
		m_title = title;
		setName(title);
		setWindowTitle(title);
	}

	/**
	 * Affecte le titre de la fen�tre
	 */
	public void setWindowTitle(String title)
	{
		Window window = getOwningWindow();
		if (window instanceof Dialog)
		{
			((Dialog) window).setTitle(title);
		} else if (window instanceof Frame)
		{
			((Frame) window).setTitle(title);
		}
	}

	/**
	 * Permet de de/verrouiller les champs verrouillables contenus dans la vue
	 * 
	 * @see InputFieldHelper.setLocked(panelToLock, locked)
	 */
	public void setLocked(boolean locked)
	{
		LockHelper.setLocked(this, locked);
	}

	/**
	 * Retourne le contr�leur de la vue
	 * 
	 * @return
	 */
	public PanelController getController()
	{
		return m_controller;
	}

	/**
	 * Retourne le mod�le de la vue
	 * 
	 * @return
	 */
	public PanelModel getModel()
	{
		return m_model;
	}

	/**
	 * Affecte le contr�leur de la vue
	 * 
	 * @param controller
	 */
	public void setController(PanelController controller)
	{
		m_controller = controller;
	}

	/**
	 * Affecte le mod�le de la vue
	 * 
	 * @param model
	 */
	public void setModel(PanelModel model)
	{
		m_model = model;
	}

	public void disconnectFromModel()
	{
		disconnectFromModel(this);
	}

	public static void disconnectFromModel(ZPanel panel)
	{
		disconnectFromModel((Container) panel);
	}

	private static void disconnectFromModel(Container container)
	{
		int count = container.getComponentCount();
		for (int i = 0; i < count; i++)
		{
			Component component = (Component) container.getComponent(i);
			disconnectFromModel(component);
		}
	}

	private static void disconnectFromModel(Component component)
	{
		if (component instanceof VOPropertyField)
		{
			disconnectFromModel((VOPropertyField) component);
		} else if (component instanceof Container)
		{
			disconnectFromModel((Container) component);
		}
	}

	private static void disconnectFromModel(VOPropertyField field)
	{
		field.disconnectFromModel();
	}
}