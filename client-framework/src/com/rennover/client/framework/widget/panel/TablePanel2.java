package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.rennover.client.framework.print.PrintToolbox;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.base.ZScrollPane;
import com.rennover.client.framework.widget.base.ZTable;
import com.rennover.client.framework.widget.icon.IconFactory;

/**
 * @deprecated now use TablePanel (02/2004)
 * 
 * @author tcaboste
 * 
 * TablePanel est un panneau servant à afficher une table avec un scrollpane et
 * des barres de boutons. Il y a une barre verticale à droite de la table et une
 * barre horizontale sous la tables.
 * 
 * TablePanel2 panel = new TablePanel2("Elément(s) trouvé(s)", false, true);
 * 
 * ... // création de table1
 * 
 * panel.setTable(table1, 5);
 * 
 * panel.addVButtonAction( action1 ); panel.addButtonAction( action2 ); //
 * addButtonAction est equivalent à addVButtonAction
 * 
 * panel.addHButtonAction( actionB);
 * 
 */
public class TablePanel2 extends ZPanel implements TableModelListener, ListSelectionListener
{
	private TitledBorder m_titledBorder = null;

	private ZPanel m_tablePanel = null;

	private ButtonPanel m_horizontalButtonPanel = null;

	private ButtonPanel m_verticalButtonPanel = null;

	private boolean m_rowCountShowed = true;

	private String m_shortTitle = "";

	private ZTable m_table = null;

	private boolean m_verticalButtonPanelOnRight = true;

	private Action m_printAction = new PrintAction();

	/**
	 * Constructeur sans titre
	 * 
	 */
	public TablePanel2()
	{
		this(null, false);
	}

	/**
	 * Constructeur sans titre
	 * 
	 * @param border
	 *            indique que l'on veut un espace autour du panneau
	 */
	public TablePanel2(boolean border)
	{
		this(null, border, false);
	}

	/**
	 * Constructeur
	 * 
	 * @param title
	 *            titre du panneau
	 */
	public TablePanel2(String title)
	{
		this(title, true, true);
	}

	/**
	 * Constructeur
	 * 
	 * @param title
	 *            titre du panneau
	 * @param border
	 *            indique que l'on veut un espace autour du panneau
	 */
	public TablePanel2(String title, boolean border)
	{
		this(title, border, true);
	}

	/**
	 * Constructeur
	 * 
	 * @param title
	 *            titre du panneau
	 * @param table
	 *            table du panneau
	 * @param nbRowVisible
	 *            nombre de lignes visibles souhaité
	 * @param border
	 *            indique que l'on veut un espace autour du panneau
	 * @param rowCountShowed
	 *            indique que l'on veut afficher le nombre de lignes dans le
	 *            titre
	 */
	public TablePanel2(String title, ZTable table, int nbRowVisible, boolean border, boolean rowCountShowed)
	{
		this(title, border, rowCountShowed);
		setTable(table, nbRowVisible);
	}

	/**
	 * Constructeur
	 * 
	 * @param title
	 *            titre du panneau
	 * @param border
	 *            indique que l'on veut un espace autour du panneau
	 * @param rowCountShowed
	 *            indique que l'on veut voir le nombre de lignes dans le titre
	 */
	public TablePanel2(String title, boolean border, boolean rowCountShowed)
	{
		super(title);

		m_rowCountShowed = rowCountShowed;
		m_shortTitle = title;

		setLayout(new BorderLayout(4, 4));
		m_tablePanel = new ZPanel(new BorderLayout(4, 4));
		add(m_tablePanel, BorderLayout.CENTER);

		title = getTitle();

		if (title != null)
		{
			TitledBorder titledBorder = new TitledBorder(title);
			if (border)
			{
				Border emptyBorder = new EmptyBorder(4, 4, 4, 4);
				Border compositeBorder = BorderFactory.createCompoundBorder(emptyBorder, titledBorder);
				setBorder(compositeBorder);
			} else
			{
				setBorder(titledBorder);
			}
			m_titledBorder = titledBorder;
		}

		updateTitle();
	}

	/**
	 * Affecte un titre au panneau (et au TitledBorder)
	 */
	public void setTitle(String title)
	{
		super.setTitle(title);
		if (m_titledBorder != null)
		{
			m_titledBorder.setTitle(title);
		}
	}

	/**
	 * Met à jour le titre du panneau
	 * 
	 */
	public void updateTitle()
	{
		if (m_table != null && m_rowCountShowed)
		{
			setTitle(m_shortTitle + " : " + m_table.getModel().getRowCount());
		} else
		{
			setTitle(m_shortTitle);
		}
		repaint();
	}

	/**
	 * Récupère le ButtonPanel horizontal (sous la table)
	 * 
	 * @return
	 */
	private ButtonPanel getHorizontalButtonPanel()
	{
		if (m_horizontalButtonPanel == null)
		{
			m_horizontalButtonPanel = new ButtonPanel(ButtonPanel.HORIZONTAL);
			m_tablePanel.add(m_horizontalButtonPanel, BorderLayout.SOUTH);
		}

		return m_horizontalButtonPanel;
	}

	/**
	 * Récupère le ButtonPanel vertical (à droite de la table)
	 * 
	 * @return
	 */
	private ButtonPanel getVerticalButtonPanel()
	{
		if (m_verticalButtonPanel == null)
		{
			m_verticalButtonPanel = new ButtonPanel(ButtonPanel.VERTICAL);
			add(m_verticalButtonPanel, BorderLayout.EAST);
		}

		return m_verticalButtonPanel;
	}

	/**
	 * Permet de changer la position du buttonPanel vertical
	 * 
	 * @param onRight
	 *            à true le buttonPanel sera à droite à false il sera à gauche
	 */
	public void setVerticalButtonPanelOnRight(boolean onRight)
	{
		if (m_verticalButtonPanelOnRight != onRight)
		{
			if (onRight)
			{
				remove(m_verticalButtonPanel);
				add(m_verticalButtonPanel, BorderLayout.EAST);
			} else
			{
				remove(m_verticalButtonPanel);
				add(m_verticalButtonPanel, BorderLayout.WEST);
			}
			m_verticalButtonPanelOnRight = onRight;
		}
	}

	/**
	 * Ajoute un bouton portant une action dans la barre par défaut (verticale à
	 * droite)
	 * 
	 * @param action
	 *            action associée au bouton
	 */
	public void addButtonAction(Action action)
	{
		addVButtonAction(action);
	}

	/**
	 * Ajoute un bouton portant une action dans la barre par défaut (verticale à
	 * droite)
	 * 
	 * @param action
	 *            action associée au bouton
	 * @param lockable
	 *            indique si le bouton est verrouillable par le panneau
	 */
	public void addButtonAction(Action action, boolean lockable)
	{
		addVButtonAction(action, lockable);
	}

	/**
	 * Ajoute un bouton portant une action dans la barre verticale à droite de
	 * la table Ce bouton est actif que lorsqu'un item est sélectionné
	 * 
	 * @param action
	 *            action associée au bouton
	 * @param lockable
	 *            indique si le bouton est verrouillable par le panneau
	 */
	public void addItemButtonAction(Action action, boolean lockable)
	{
		addVButtonAction(action, lockable);
		addItemAction(action);
	}

	public void addItemButtonAction(Action action)
	{
		addVButtonAction(action);
		addItemAction(action);
	}

	private Set m_itemActionSet = new HashSet();

	public void addItemAction(Action action)
	{
		m_itemActionSet.add(action);
		updateButtons();
	}

	public void setEnableItemActions(boolean enable)
	{
		Iterator iter = m_itemActionSet.iterator();
		while (iter.hasNext())
		{
			Action action = (Action) iter.next();
			action.setEnabled(enable);
		}
	}

	public void setEnableNotEmptyActions(boolean enable)
	{
		getPrintAction().setEnabled(enable);
	}

	private void updateButtons()
	{
		if (isEnabled())
		{
			boolean enableItemAction = (m_table.getSelectedRow() != -1);
			setEnableItemActions(enableItemAction);

			boolean enableNotEmptyAction = (m_table.getRowCount() != 0);
			setEnableNotEmptyActions(enableNotEmptyAction);
		}
	}

	/**
	 * Ajoute un petit bouton portant une action dans la barre par défaut
	 * (verticale à droite)
	 * 
	 * @param action
	 *            action associée au bouton
	 */
	public void addSmallButtonAction(Action action)
	{
		getVerticalButtonPanel().addSmallButtonAction(action);
	}

	/**
	 * Ajoute un petit bouton portant une action dans la barre par défaut
	 * (verticale à droite)
	 * 
	 * @param action
	 *            action associée au bouton
	 * @param lockable
	 *            indique si le bouton est verrouillable par le panneau
	 */
	public void addSmallButtonAction(Action action, boolean lockable)
	{
		getVerticalButtonPanel().addSmallButtonAction(action, lockable);
	}

	/**
	 * Ajoute un petit bouton portant une action dans la barre par défaut
	 * (verticale à droite)
	 * 
	 * @param action
	 *            action associée au bouton
	 * @param m_lockable
	 *            indique si le bouton est verrouillable par le panneau
	 */
	public void addSmallItemButtonAction(Action action)
	{
		getVerticalButtonPanel().addSmallButtonAction(action);
		addItemAction(action);
	}

	/**
	 * Ajoute un bouton d'impression de la liste contenu dans la table
	 */
	public void addPrintButton()
	{
		getVerticalButtonPanel().addButtonAction(getPrintAction(), false);
	}

	/**
	 * Ajoute un petit bouton d'impression de la liste contenu dans la table
	 */
	public void addSmallPrintButton()
	{
		getVerticalButtonPanel().addSmallButtonAction(getPrintAction(), false);
	}

	public Action getPrintAction()
	{
		return m_printAction;
	}

	/**
	 * Permet de lancer l'impression de la liste contenu dans la table
	 * 
	 */
	public void print()
	{
		PrintToolbox.doPrintTable(getTitle(), m_table, this);
	}

	/**
	 * Ajoute un bouton portant une action dans la barre verticale à droite de
	 * la table
	 * 
	 * @param action
	 *            action associée au bouton
	 */
	public void addVButtonAction(Action action)
	{
		getVerticalButtonPanel().addButtonAction(action);
	}

	/**
	 * Ajoute un bouton portant une action dans la barre verticale à droite de
	 * la table
	 * 
	 * @param action
	 *            action associée au bouton
	 */
	public void addVButtonAction(Action action, boolean lockable)
	{
		getVerticalButtonPanel().addButtonAction(action, lockable);
	}

	/**
	 * Ajoute un bouton portant une action dans la barre verticale à droite de
	 * la table Ce bouton est actif que lorsqu'un item est sélectionné
	 * 
	 * @param action
	 *            action associée au bouton
	 * @param m_lockable
	 *            indique si le bouton est verrouillable par le panneau
	 */
	public void addVItemButtonAction(Action action)
	{
		addVButtonAction(action);
		addItemAction(action);
	}

	/**
	 * Ajoute un bouton portant une action dans la barre horizontale sous la
	 * table
	 * 
	 * @param action
	 *            action associée au bouton
	 */
	public void addHButtonAction(Action action)
	{
		getHorizontalButtonPanel().addButtonAction(action);
	}

	/**
	 * Ajoute un bouton portant une action dans la barre horizontale sous la
	 * table
	 * 
	 * @param action
	 *            action associée au bouton
	 */
	public void addHButtonAction(Action action, boolean lockable)
	{
		getHorizontalButtonPanel().addButtonAction(action, lockable);
	}

	/**
	 * Ajoute un bouton portant une action dans la barre horizontale sous la
	 * table Ce bouton est actif que lorsqu'un item est sélectionné
	 * 
	 * @param action
	 *            action associée au bouton
	 * @param m_lockable
	 *            indique si le bouton est verrouillable par le panneau
	 */
	public void addHItemButtonAction(Action action)
	{
		addHButtonAction(action);
		addItemAction(action);
	}

	/**
	 * Ajoute un bouton portant une action dans la barre par défaut (verticale à
	 * droite)
	 * 
	 * @param m_action
	 *            action associée au bouton
	 */
	public void addButtonAction(String title)
	{
		addVButtonAction(title);
	}

	/**
	 * Ajoute un bouton portant une action dans la barre verticale à droite de
	 * la table
	 * 
	 * @param m_action
	 *            action associée au bouton
	 */
	public void addVButtonAction(String title)
	{
		getVerticalButtonPanel().addButtonAction(title);
	}

	/**
	 * Ajoute un bouton portant une action dans la barre horizontale sous la
	 * table
	 * 
	 * @param m_action
	 *            action associée au bouton
	 */
	public void addHButtonAction(String title)
	{
		getHorizontalButtonPanel().addButtonAction(title);
	}

	/**
	 * Ajoute la table au panneau
	 * 
	 * @param table
	 *            table du panneau
	 */
	public void setTable(ZTable table)
	{
		setTable(table, 5);
	}

	/**
	 * Ajoute la table au panneau en précisant le nombre de lignes à afficher
	 * 
	 * @param table
	 *            table du panneau
	 * @param nbRowVisible
	 *            nombre de lignes visibles (préféré)
	 */
	public void setTable(ZTable table, int nbRowVisible)
	{
		if (m_table != null)
		{
			m_table.getModel().removeTableModelListener(this);
			table.removeListSelectionListener(this);
		}

		m_table = table;

		if (m_table != null)
		{
			ZScrollPane scrollPane = new ZScrollPane(table, nbRowVisible);
			m_tablePanel.add(scrollPane, BorderLayout.CENTER);

			table.getModel().addTableModelListener(this);
			table.addListSelectionListener(this);
		}

		updateButtons();
		updateTitle();
	}

	/**
	 * Implémentation de l'interface ListSelectionListener
	 * 
	 */
	public void valueChanged(ListSelectionEvent e)
	{
		if (!e.getValueIsAdjusting())
		{
			updateButtons();
		}
	}

	/**
	 * Implémentation de l'interface TableModelListener
	 * 
	 */
	public void tableChanged(TableModelEvent e)
	{
		updateTitle();
		updateButtons();
	}

	/**
	 * Indique que le nombre de lignes est affiché
	 * 
	 * @return vrai si le nombre de lignes doit s'afficher
	 */
	public boolean isRowCountShowed()
	{
		return m_rowCountShowed;
	}

	/**
	 * Permet d'indiquer que l'on veut voir le nombre de lignes dans le titre du
	 * panneau
	 * 
	 * @param show
	 *            à vrai si l'on veut voir le nombre de lignes dans le titre
	 */
	public void setRowCountShowed(boolean show)
	{
		m_rowCountShowed = show;
	}

	/**
	 * Cette classe est une action servant à imprimer le contenu de la table
	 * 
	 * @author tcaboste
	 */
	private class PrintAction extends AbstractAction
	{
		public PrintAction()
		{
			super("Imprimer...", IconFactory.getPrintIcon());
			putValue(SHORT_DESCRIPTION, "Imprimer le contenu du tableau.");
		}

		public void actionPerformed(ActionEvent e)
		{
			print();
		}
	}
}
