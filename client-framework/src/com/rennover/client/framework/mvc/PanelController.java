
package com.rennover.client.framework.mvc;

import java.awt.Window;

import com.rennover.client.framework.widget.base.ZDialog;
import com.rennover.client.framework.window.DefaultDialog;

/**
 * Classe à spécialiser Classe centrale du triplet MVC et du framework client.
 * 
 * Le PanelController sert de facade au écran. C'est le premier objet instancié.
 * Il instancie le modèle puis la vue. Il contient les actions appelées par la
 * vue. Il est le seul à communiquer avec le serveur d'application. Il est
 * capable de créer d'autres contrôleurs pour lancer d'autres écrans ou exécuter
 * d'autres opération
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
	 * Instancie le PanelController, puis appelle la méthode {@link #init()}.
	 * 
	 * @param title
	 *            le titre de l'écran, tel qu'il apparaitra dans la barre de
	 *            titre de la fenêtre
	 */
	public PanelController(String title)
	{
		super(null);
		this.m_title = title;
	}

	/**
	 * @deprecated use PanelController(Controller parent)
	 * 
	 * Instancie le PanelController, puis appelle la méthode {@link #init()}.
	 * 
	 * @param title
	 *            le titre de l'écran, tel qu'il apparaitra dans la barre de
	 *            titre de la fenêtre
	 * @param parent
	 *            Contrôleur parent du contrôleur courant
	 */
	public PanelController(String title, Controller parent)
	{
		super(parent);
		this.m_title = title;
	}

	/**
	 * 
	 * Instancie le PanelController, puis appelle la méthode {@link #init()}.
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
	 *            contrôleur parent du contrôleur courant
	 * 
	 */
	public PanelController(Controller parent)
	{
		super(parent);
	}

	/**
	 * 
	 * Méthode à surcharger
	 * 
	 * Cette méthode doit instancier et initialiser le modèle puis la vue.
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
	 * Méthode à surcharger
	 * 
	 * Cette méthode vérifie si l'état des données dans la base permet
	 * d'exécuter le reste du UseCase.
	 * 
	 */
	protected abstract void checkPreconditions();

	/**
	 * 
	 * Méthode à surcharger
	 * 
	 * Cette méthode vérifie si l'état des données dans la base permet
	 * d'exécuter le reste du UseCase.
	 * 
	 */
	protected abstract void createModel();

	/**
	 * Méthode à surcharger
	 * 
	 * Cette méthode doit récupérer les données initiales du contrôleur. Ces
	 * données sont habituellement récupérer du serveur.
	 * 
	 */
	protected abstract void retrieveInitialData();

	/**
	 * 
	 * Méthode à surcharger
	 * 
	 * Cette méthode vérifie si l'état des données dans la base permet
	 * d'exécuter le reste du UseCase.
	 * 
	 */
	protected abstract void createView();

	/**
	 * @deprecated surcharger doRetrieveInitialData()
	 * 
	 * Méthode à surcharger
	 * 
	 * Cette méthode doit récupérer les données initiales du contrôleur. Ces
	 * données sont habituellement récupérer du serveur.
	 */
	protected void doRecupererDonneesInitiales()
	{
	}

	/**
	 * Retourne le modèle du contrôleur. Le modèle est instancié s'il ne l'était
	 * pas.
	 * 
	 * @return le modèle du contrôleur
	 */
	public final PanelModel getModel()
	{
		return m_model;
	}

	/**
	 * Retourne la vue du contrôleur. La vue est instanciée si elle ne l'était
	 * pas.
	 * 
	 * @return la vue du contrôleur
	 */
	public final PanelView getView()
	{
		return m_view;
	}

	/**
	 * Permet d'affecter un modèle au contrôleur
	 * 
	 * @param model
	 */
	public final void setModel(PanelModel model)
	{
		m_model = model;
	}

	/**
	 * Permet d'affecter une vue au contrôleur
	 * 
	 * @param view
	 */
	public final void setView(PanelView view)
	{
		m_view = view;
	}

	/**
	 * @deprecated use PanelView.getTitle() : Attention dorénavant c'est la vue
	 *             (PanelView) qui contient le titre de l'écran
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
	 * Retourne la fenêtre parente du contrôleur. Cette fenêtre sera le parent
	 * de la fenêtre de dialogue contenant la vue
	 * 
	 * @return fenêtre du contrôleur parent
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
	 * Affecte la fenêtre parente du contrôleur . Cette fenêtre est souvent la
	 * fenêtre du contrôleur parent. Cela permet de relier les fenêtres modales
	 * à une fenêtre déjà ouverte.
	 * 
	 * @param parentWindow
	 *            la fenêtre parente à utiliser
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