package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.border.TitledBorder;

import com.rennover.client.framework.widget.base.ZButton;
import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.base.ZScrollPane;
import com.rennover.client.framework.widget.field.ListField;
import com.rennover.client.framework.widget.icon.IconFactory;
import com.rennover.client.framework.widget.layout.GridData;
import com.rennover.client.framework.widget.layout.SWTGridLayout;

/**
 * Un panneau compsé de deux listes et de boutons. <br>
 * La liste de gauche contient des données que l'on peut sélectionner et
 * déplacer dans la liste de droite. La liste de droite contiendra donc une
 * liste d'éléments choisis par l'utilisateur.
 * 
 * <p>
 * Les boutons permettent l'ajout et le retrait d'éléments individuellement, ou
 * en groupe (sélection multiple) ou complètement (tous les élements d'une
 * liste).
 * </p>
 */
public class AddRemovePanel extends ZPanel implements AddRemove
{
	protected AddRemoveList m_lstSource;

	protected AddRemoveList m_lstDestination;

	private Action m_moveUpAction;

	private Action m_moveDownAction;

	protected Action m_addAction;

	private Action m_addAllAction;

	protected Action m_removeAction;

	private Action m_removeAllAction;

	private ZPanel m_spSource;

	private ZPanel m_spDestination;

	private boolean m_oneWaySelected = false;

	private List m_initialList;

	private String m_sourcePanelTitle;

	private String m_destinationPanelTitle;

	private boolean m_rowCountShowed = false;

	// TO FIX DEFECT#3178
	private int m_btnOrientation = ButtonPanel.VERTICAL;

	private String m_btnPosition = BorderLayout.EAST;

	private String m_btnLabel;

	public AddRemovePanel()
	{
		this(false);
	}

	public AddRemovePanel(boolean manualSort)
	{
		setAddRemoveList(new AddRemoveListList(), new AddRemoveListList(manualSort));
		compose();
	}

	public AddRemovePanel(AddRemoveList sourceList, AddRemoveList destinationList)
	{
		setAddRemoveList(sourceList, destinationList);
		compose();
	}

	// TO FIX DEFECT#3178
	/**
	 * @param sourceList
	 * @param destinationList
	 * @param buttonOrientation
	 *            ==> To set the button orientation to Vertical/Horizontal
	 *            Example:ButtonPanel.VERTICAL or ButtonPanel.HORIZONTAL
	 * @param buttonPosition
	 *            ==> To position the button to either EAST/SOUTH of the List.
	 *            Example:BorderLayout.EAST or BorderLayout.SOUTH
	 * @param buttonLabel
	 */
	public AddRemovePanel(AddRemoveList sourceList, AddRemoveList destinationList, int buttonOrientation,
	        String buttonPosition, String buttonLabel)
	{
		this.m_btnOrientation = buttonOrientation;
		this.m_btnPosition = buttonPosition;
		this.m_btnLabel = buttonLabel;
		setAddRemoveList(sourceList, destinationList);
		compose();
	}

	protected void setAddRemoveList(AddRemoveList sourceList, AddRemoveList destinationList)
	{
		m_lstSource = sourceList;
		m_lstDestination = destinationList;

		// Ajoute les actions d'ajout et de retirer sur le double-clic sur les
		// listes
		m_lstSource.setDoubleClickAction(getAddAction());
		m_lstDestination.setDoubleClickAction(getRemoveAction());
	}

	protected void composeEx()
	{
		this.setLayout(new GridBagLayout());
		ZPanel panel = new ZPanel();
		panel.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTHWEST;
		updateGBConstraints(constraints, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH);
		add(panel, constraints);

		m_spSource = createListPanel(" Eléments disponibles ", m_lstSource);
		updateGBConstraints(constraints, 0, 0, 1, 1, 1, 1, GridBagConstraints.BOTH);

		panel.add(m_spSource, constraints);

		m_spDestination = createListPanel(" Eléments sélectionnés ", m_lstDestination);
		updateGBConstraints(constraints, 2, 0, 1, 1, 1, 1, GridBagConstraints.BOTH);

		panel.add(m_spDestination, constraints);

		constraints.anchor = GridBagConstraints.CENTER;
		updateGBConstraints(constraints, 1, 0, 1, 1, 0, 0, GridBagConstraints.VERTICAL);

		panel.add(composeButtonPanel(), constraints);
	}

	protected void compose()
	{
		this.setLayout(new BorderLayout());
		SWTGridLayout layout = new SWTGridLayout(3, false);
		layout.m_marginHeight = 0;
		layout.m_marginWidth = 0;
		ZPanel panel = new ZPanel(layout);
		add(panel);

		GridData constraints;
		m_spSource = createListPanel(" Eléments disponibles ", m_lstSource);
		constraints = new GridData(GridData.FILL_BOTH);
		panel.add(m_spSource, constraints);

		constraints = new GridData(GridData.FILL_VERTICAL);
		panel.add(composeButtonPanel(), constraints);

		m_spDestination = createListPanel(" Eléments sélectionnés ", m_lstDestination);
		constraints = new GridData(GridData.FILL_BOTH);
		panel.add(m_spDestination, constraints);
	}

	protected ZPanel createListPanel(String title, AddRemoveList list)
	{
		Dimension listSize = new Dimension(250, 200);

		ZPanel panel = new ZPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new TitledBorder(title));

		ZScrollPane scrollPane = new ZScrollPane(list.getComponent());
		scrollPane.setPreferredSize(listSize);

		panel.add(scrollPane, BorderLayout.CENTER);

		if (list.isManualySorted())
		{
			ButtonPanel buttonPanel = new ButtonPanel(m_btnOrientation, true);
			ZButton b1 = buttonPanel.addButtonAction(getMoveUpAction());
			ZButton b2 = buttonPanel.addButtonAction(getMoveDownAction());
			Dimension minSize = b1.getMinimumSize();

			// TODO : TCA : trouver un moyen de calculer cette valeur
			minSize.height = 27;
			b1.setMinimumSize(minSize);
			b2.setMinimumSize(minSize);

			FieldPanel fp = new FieldPanel();
			FieldPanelParameter fpp1 = new FieldPanelParameter();
			fpp1.m_fill = false;
			fpp1.m_labelName = null;
			fpp1.m_component = new ZLabel(m_btnLabel);
			FieldPanelParameter fpp2 = new FieldPanelParameter();
			fpp2.m_fill = false;
			fpp2.m_labelName = null;
			fpp2.m_component = buttonPanel;

			fp.addComponent(fpp1, fpp2);
			panel.add(fp, m_btnPosition);

		}

		return panel;
	}

	/**
	 * Permet modifier les libellés affichés au dessus des listes
	 */
	public void setListTitles(String sourceTitle, String destinationPanel)
	{
		m_sourcePanelTitle = sourceTitle;
		m_destinationPanelTitle = destinationPanel;
		m_spSource.setBorder(new TitledBorder(m_sourcePanelTitle));
		m_spDestination.setBorder(new TitledBorder(m_destinationPanelTitle));
	}

	/**
	 * Permet de modifier les libellés des boutons
	 * 
	 * @param addSelectedTitle
	 * @param addAllTitle
	 * @param removeSelectedTitle
	 * @param removeAllTitle
	 */
	public void setButtonTitles(String addSelectedTitle, String addAllTitle, String removeSelectedTitle,
	        String removeAllTitle)
	{
		if (addSelectedTitle != null)
		{
			getAddAction().putValue(Action.NAME, addSelectedTitle);
		}

		if (addAllTitle != null)
		{
			getAddAllAction().putValue(Action.NAME, addAllTitle);
		}

		if (removeSelectedTitle != null)
		{
			getRemoveAction().putValue(Action.NAME, removeSelectedTitle);
		}

		if (removeAllTitle != null)
		{
			getRemoveAllAction().putValue(Action.NAME, removeAllTitle);
		}

		repaint();
	}

	private ZPanel composeButtonPanel()
	{
		ButtonPanel panel = new ButtonPanel(ButtonPanel.VERTICAL, true);
		panel.addSpring();
		panel.addButtonAction(getAddAction());
		panel.addButtonAction(getAddAllAction());
		panel.addSpring();
		panel.addButtonAction(getRemoveAction());
		panel.addButtonAction(getRemoveAllAction());
		panel.addSpring();
		return panel;
	}

	/**
	 * Retourne la liste des éléments écartés
	 */
	public List getSourceList()
	{
		return m_lstSource.getList();
	}

	/**
	 * Retourne la liste résultat
	 */
	public List getDestinationList()
	{
		return m_lstDestination.getList();
	}

	/**
	 * Retourne la liste des éléments ajoutés à la liste résultats
	 */
	public List getAddedList()
	{
		List addedList = new ArrayList();
		List destinationList = getDestinationList();
		List initialList = m_initialList;
		int nbElements = destinationList.size();
		for (int i = 0; i < nbElements; i++)
		{
			Object object = destinationList.get(i);
			if (!initialList.contains(object))
			{
				addedList.add(object);
			}
		}
		return addedList;
	}

	/**
	 * Retourne la liste des éléments enlevés à la liste résultats
	 */
	public List getRemovedList()
	{
		List removedList = new ArrayList();
		List destinationList = getDestinationList();
		List initialList = m_initialList;
		int nbOldElements = initialList.size();
		for (int i = 0; i < nbOldElements; i++)
		{
			Object object = initialList.get(i);
			if (!destinationList.contains(object))
			{
				removedList.add(object);
			}
		}
		return removedList;
	}

	/**
	 * Affecte la liste des éléments disponibles
	 */
	public void setSourceList(List sourceList)
	{
		m_lstSource.addElements(sourceList);
		updateTitle();
	}

	/**
	 * Affecte la liste des éléments déjà sélectionnés ces éléments sont enlevés
	 * de la liste des éléments disponibles
	 */
	public void setDestinationList(List destinationList)
	{
		m_lstSource.removeElements(destinationList);
		//
		m_lstDestination.addElements(destinationList);
		m_initialList = destinationList;
		updateTitle();
	}

	public void removeDestinationListElements(List destinationList)
	{
		m_lstDestination.removeElements(destinationList);

	}

	/**
	 * Déplace vers le haut le première élément sélectionné de la liste résultat
	 */
	public void moveSelectedElementUp()
	{
		m_lstDestination.moveSelectedUp();
	}

	/**
	 * Déplace vers le bas le première élément sélectionné de la liste résultat
	 */
	public void moveSelectedElementDown()
	{
		m_lstDestination.moveSelectedDown();
	}

	/**
	 * Ajoute les éléments disponibles sélectionnés dans la liste résultats
	 */
	public void addSelectedElements()
	{
		if (m_lstSource.isSelectionEmpty())
		{
			return;
		}

		List values = m_lstSource.getSelectedElements();
		m_lstDestination.addElements(values);
		m_lstSource.removeElements(values);
		updateTitle();
	}

	/**
	 * Enlève les éléments sélectionnés de la liste résultats Les éléments
	 * réapparaîssent dans les éléments disponibles
	 */
	public void removeSelectedElements()
	{
		if (m_lstDestination.isSelectionEmpty())
		{
			return;
		}

		List values = m_lstDestination.getSelectedElements();
		if (!m_oneWaySelected)
		{
			m_lstSource.addElements(values);
		}
		m_lstDestination.removeElements(values);
		updateTitle();
	}

	/**
	 * Ajoute tous les éléments dans la liste résultats
	 */
	public void addAllElements()
	{
		List values = m_lstSource.getList();
		m_lstDestination.addElements(values);
		m_lstSource.removeElements(values);
		updateTitle();
	}

	/**
	 * Enlève tous les éléments de la liste résultats
	 */
	public void removeAllElements()
	{
		List values = m_lstDestination.getList();
		if (!m_oneWaySelected)
		{
			m_lstSource.addElements(values);
		}
		m_lstDestination.removeElements(values);
		updateTitle();
	}

	private Action getMoveUpAction()
	{
		if (null == m_moveUpAction)
		{
			m_moveUpAction = new AbstractAction()
			{
				public void actionPerformed(ActionEvent e)
				{
					moveSelectedElementUp();
				}
			};

			m_moveUpAction.putValue(Action.SMALL_ICON, IconFactory.getUpArrowIcon());
		}
		return m_moveUpAction;
	}

	private Action getMoveDownAction()
	{
		if (null == m_moveDownAction)
		{
			m_moveDownAction = new AbstractAction()
			{
				public void actionPerformed(ActionEvent e)
				{
					moveSelectedElementDown();
				}
			};

			m_moveDownAction.putValue(Action.SMALL_ICON, IconFactory.getDownArrowIcon());
		}
		return m_moveDownAction;
	}

	private Action getAddAction()
	{
		if (null == m_addAction)
		{
			m_addAction = new AbstractAction(">")
			{
				public void actionPerformed(ActionEvent e)
				{
					addSelectedElements();
				}
			};

			m_addAction.putValue(Action.SHORT_DESCRIPTION, "Passe un élément sélectionné dans le tableau de droite");
		}
		return m_addAction;
	}

	private Action getAddAllAction()
	{
		if (null == m_addAllAction)
		{
			m_addAllAction = new AbstractAction(">>")
			{
				public void actionPerformed(ActionEvent e)
				{
					addAllElements();
				}
			};

			m_addAllAction.putValue(Action.SHORT_DESCRIPTION, "Passe tous les éléments dans le tableau de droite");
		}
		return m_addAllAction;
	}

	private Action getRemoveAction()
	{
		if (null == m_removeAction)
		{
			m_removeAction = new AbstractAction("<")
			{
				public void actionPerformed(ActionEvent e)
				{
					if (!m_lstDestination.isSelectionEmpty())
					{
						removeSelectedElements();
					}
				}
			};

			m_removeAction.putValue(Action.SHORT_DESCRIPTION,
			        "Ote un élément sélectionné et le réinsère dans la liste de gauche");
		}
		return m_removeAction;
	}

	private Action getRemoveAllAction()
	{
		if (null == m_removeAllAction)
		{
			m_removeAllAction = new AbstractAction("<<")
			{
				public void actionPerformed(ActionEvent e)
				{
					removeAllElements();
				}
			};

			m_removeAllAction.putValue(Action.SHORT_DESCRIPTION,
			        "Ote tous les éléments et les réinsèrent dans la liste de gauche");
		}
		return m_removeAllAction;
	}

	/**
	 * inutilisé actuellement
	 */
	public boolean isRemoveFromSourceMode()
	{
		return false;
	}

	/**
	 * inutilisé actuellement
	 */
	public void setRemoveFromSourceMode(boolean mode)
	{
	}

	/**
	 * @return
	 */
	public boolean isOneWaySelected()
	{
		return m_oneWaySelected;
	}

	/**
	 * @param b
	 */
	public void setOneWaySelected(boolean b)
	{
		m_oneWaySelected = b;
	}

	/**
	 * added by Ivega Offshore on April 17, 2004 this gives an option to opt for
	 * showing the number of elements in title, default is true
	 * 
	 */
	public void setRowCountShowed(boolean rowCountShowed)
	{
		m_rowCountShowed = rowCountShowed;
	}

	/**
	 * added by Ivega Offshore on April 17, 2004 - VD this updates the title
	 * panel of AddRemovePanel with the current number of elements in both
	 * source and destination list
	 * 
	 */
	public void updateTitle()
	{
		if (m_rowCountShowed)
		{
			m_spSource.setBorder(new TitledBorder(m_sourcePanelTitle + " : " + m_lstSource.getList().size()));
			m_spDestination.setBorder(new TitledBorder(m_destinationPanelTitle + " : "
			        + m_lstDestination.getList().size()));
			m_spSource.repaint();
			m_spDestination.repaint();
		}
	}

	protected Action getM_addAction()
	{
		return m_addAction;
	}

	protected Action getM_addAllAction()
	{
		return m_addAllAction;
	}

	protected Action getM_removeAction()
	{
		return m_removeAction;
	}

	protected Action getM_removeAllAction()
	{
		return m_removeAllAction;
	}
}

class AddRemoveListList implements AddRemoveList
{
	ListField m_list = new ListField();

	public AddRemoveListList()
	{
		this(false);
	}

	public AddRemoveListList(boolean manualSort)
	{
		m_list.setSorted(!manualSort);
	}

	public boolean isManualySorted()
	{
		return !m_list.isSorted();
	}

	public List getList()
	{
		return m_list.getObjectList();
	}

	// removed commented method getModel()

	public void addElements(List elements)
	{
		m_list.addElements(elements);
		m_list.setSelectedValues(elements);
		// FIx for defect#2921
		if (elements.size() > 0)
		{
			m_list.setSelectedIndex(0);
		}
	}

	public void removeElements(List elements)
	{
		m_list.removeElements(elements);
		m_list.setSelectedValues(null);
	}

	public void moveSelectedUp()
	{
		m_list.moveSelectedUp();
	}

	public void moveSelectedDown()
	{
		m_list.moveSelectedDown();
	}

	public List getSelectedElements()
	{
		List selectedList = Arrays.asList(m_list.getSelectedValues());
		return selectedList;
	}

	public JComponent getComponent()
	{
		return m_list;
	}

	public boolean isSelectionEmpty()
	{
		return m_list.isSelectionEmpty();
	}

	public void setDoubleClickAction(Action action)
	{
		m_list.setDoubleClickAction(action);
	}
}