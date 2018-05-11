
package com.rennover.client.framework.mvc;

import java.awt.Window;

import com.rennover.client.framework.widget.base.ZDialog;
import com.rennover.client.framework.window.DefaultDialog;

/**
 * Classe � sp�cialiser Classe centrale du triplet MVC et du framework client.
 * 
 * Le PanelController sert de facade au �cran. C'est le premier objet instanci�.
 * Il instancie le mod�le puis la vue. Il contient les actions appel�es par la
 * vue. Il est le seul � communiquer avec le serveur d'application. Il est
 * capable de cr�er d'autres contr�leurs pour lancer d'autres �crans ou ex�cuter
 * d'autres op�ration
 * 
 * @see com.rennover.client.framework.mvc.PanelView
 * @see com.rennover.client.framework.mvc.PanelViewController
 * @see com.rennover.client.framework.mvc.PanelModel
 */
public abstract class PanelController extends Controller
{
	private String m_title;

	protected PanelModel m_model;

	protected PanelView m_view;

	private Window m_parentWindow;

	private ZDialog m_dialog;

	/**
	 * @deprecated use PanelController()
	 * 
	 * Instancie le PanelController, puis appelle la m�thode {@link #init()}.
	 * 
	 * @param title
	 *            le titre de l'�cran, tel qu'il apparaitra dans la barre de
	 *            titre de la fen�tre
	 */
	public PanelController(String title)
	{
		super(null);
		this.m_title = title;
	}

	/**
	 * @deprecated use PanelController(Controller parent)
	 * 
	 * Instancie le PanelController, puis appelle la m�thode {@link #init()}.
	 * 
	 * @param title
	 *            le titre de l'�cran, tel qu'il apparaitra dans la barre de
	 *            titre de la fen�tre
	 * @param parent
	 *            Contr�leur parent du contr�leur courant
	 */
	public PanelController(String title, Controller parent)
	{
		super(parent);
		this.m_title = title;
	}

	/**
	 * 
	 * Instancie le PanelController, puis appelle la m�thode {@link #init()}.
	 * 
	 */
	public PanelController()
	{
		super(null);
	}

	/**
	 * 
	 * Instantiates the PanelController then calls the {@ link # init ()} method.
	 * 
	 * @param parent
	 *            contr�leur parent du contr�leur courant
	 * 
	 */
	public PanelController(Controller parent)
	{
		super(parent);
	}

	/**
	 * 
	 * M�thode � surcharger
	 * 
	 * Cette m�thode doit instancier et initialiser le mod�le puis la vue.
	 * 
	 */
	protected void init()
	{
		checkPreconditions();
		createModel();
		retrieveInitialData();
		createView();
	}

	public void clear()
	{
		if (m_model != null)
		{
			m_model.clear();
			m_model = null;
		}
	}

	public void close(PanelView view)
	{
	}

	/**
	 * 
	 * M�thode � surcharger
	 * 
	 * Cette m�thode v�rifie si l'�tat des donn�es dans la base permet
	 * d'ex�cuter le reste du UseCase.
	 * 
	 */
	protected abstract void checkPreconditions();

	/**
	 * 
	 * M�thode � surcharger
	 * 
	 * Cette m�thode v�rifie si l'�tat des donn�es dans la base permet
	 * d'ex�cuter le reste du UseCase.
	 * 
	 */
	protected abstract void createModel();

	/**
	 * M�thode � surcharger
	 * 
	 * Cette m�thode doit r�cup�rer les donn�es initiales du contr�leur. Ces
	 * donn�es sont habituellement r�cup�rer du serveur.
	 * 
	 */
	protected abstract void retrieveInitialData();

	/**
	 * 
	 * M�thode � surcharger
	 * 
	 * Cette m�thode v�rifie si l'�tat des donn�es dans la base permet
	 * d'ex�cuter le reste du UseCase.
	 * 
	 */
	protected abstract void createView();

	/**
	 * @deprecated surcharger doRetrieveInitialData()
	 * 
	 * M�thode � surcharger
	 * 
	 * Cette m�thode doit r�cup�rer les donn�es initiales du contr�leur. Ces
	 * donn�es sont habituellement r�cup�rer du serveur.
	 */
	protected void doRecupererDonneesInitiales()
	{
	}

	/**
	 * Retourne le mod�le du contr�leur. Le mod�le est instanci� s'il ne l'�tait
	 * pas.
	 * 
	 * @return le mod�le du contr�leur
	 */
	public final PanelModel getModel()
	{
		return m_model;
	}

	/**
	 * Retourne la vue du contr�leur. La vue est instanci�e si elle ne l'�tait
	 * pas.
	 * 
	 * @return la vue du contr�leur
	 */
	public final PanelView getView()
	{
		return m_view;
	}

	/**
	 * Permet d'affecter un mod�le au contr�leur
	 * 
	 * @param model
	 */
	public final void setModel(PanelModel model)
	{
		m_model = model;
	}

	/**
	 * Permet d'affecter une vue au contr�leur
	 * 
	 * @param view
	 */
	public final void setView(PanelView view)
	{
		m_view = view;
	}

	/**
	 * @deprecated use PanelView.getTitle() : Attention dor�navant c'est la vue
	 *             (PanelView) qui contient le titre de l'�cran
	 * 
	 * Retourne le titre du usecase
	 * @return titre du usecase
	 */
	public final String getTitle()
	{
		return m_title;
	}

	/**
	 * 
	 * Retourne la fen�tre parente du contr�leur. Cette fen�tre sera le parent
	 * de la fen�tre de dialogue contenant la vue
	 * 
	 * @return fen�tre du contr�leur parent
	 */
	public Window getParentWindow()
	{
		if (m_parentWindow == null)
		{
			Controller controller = this;
			while (m_parentWindow == null)
			{
				Controller parentController = controller.getParentController();
				if (parentController == null)
				{
					break;
				}
				if (parentController instanceof PanelController)
				{
					m_parentWindow = ((PanelController) parentController).getParentWindow();
				}
				controller = parentController;
			}
		}
		return m_parentWindow;
	}

	/**
	 * 
	 * Affecte la fen�tre parente du contr�leur . Cette fen�tre est souvent la
	 * fen�tre du contr�leur parent. Cela permet de relier les fen�tres modales
	 * � une fen�tre d�j� ouverte.
	 * 
	 * @param parentWindow
	 *            la fen�tre parente � utiliser
	 */
	public void setParentWindow(Window parentWindow)
	{
		m_parentWindow = parentWindow;
	}

	/**
	 * @deprecated use
	 *             AgefosFullClientApplication.getAgefosApplication().doShowUseCaseAsDialog()
	 * 
	 */
	public void showDialogView()
	{
		m_dialog = DefaultDialog.createDefaultDialog(getParentWindow(), getView());
		m_dialog.pack();
		m_dialog.show();
	}

	// removed the commented method closeDialogView()
}