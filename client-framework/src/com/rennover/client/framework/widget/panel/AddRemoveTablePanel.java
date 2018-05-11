package com.rennover.client.framework.widget.panel;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.rennover.client.framework.valueobject.model.ValueObjectListModel;
import com.rennover.client.framework.widget.icon.IconFactory;
import com.rennover.client.framework.widget.table.TableRowModel;
import com.rennover.client.framework.widget.table.ValueObjectListTable;

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
 * 
 * <b>Remarque: </b> Pour redéfinir des actions soit sur le passage d'un elt
 * d'une liste à l'autre ou sur le déplacement haut / bas d'un elt dans la
 * liste, le plus simple est de sous classer AddRemoveTablePanel et de redéfinir
 * les méthodes move, add dispo en public sur le AddRemovePanel.
 */
public class AddRemoveTablePanel extends AddRemovePanel
{
	private Action m_moveUp = null;

	private Action m_moveDown = null;

	private boolean m_isListenerAdded = false;

	/**
	 * 
	 * @param objectClass
	 * @param rowModel
	 */
	public AddRemoveTablePanel(Class objectClass, TableRowModel rowModel)
	{
		super(new AddRemoveTable(objectClass, rowModel), new AddRemoveTable(objectClass, rowModel, false));
	}

	/**
	 * 
	 * @param objectClass
	 * @param rowModel
	 * @param isManualySorted
	 */
	public AddRemoveTablePanel(Class objectClass, TableRowModel rowModel, boolean isManualySorted)
	{
		super(new AddRemoveTable(objectClass, rowModel), new AddRemoveTable(objectClass, rowModel, isManualySorted));
	}

	// TO FIX DEFECT#3178
	/**
	 * @param objectClass
	 * @param rowModel
	 * @param isManualySorted
	 * @param buttonOrientation
	 *            ==> To set the button orientation to Vertical/Horizontal
	 *            Example:ButtonPanel.VERTICAL or ButtonPanel.HORIZONTAL
	 * @param buttonPosition
	 *            ==> To position the button to either EAST/SOUTH of the List.
	 *            Example:BorderLayout.EAST or BorderLayout.SOUTH
	 * @param buttonLabel
	 */
	public AddRemoveTablePanel(Class objectClass, TableRowModel rowModel, boolean isManualySorted,
	        int buttonOrientation, String buttonPosition, String buttonLabel)
	{
		super(new AddRemoveTable(objectClass, rowModel), new AddRemoveTable(objectClass, rowModel, isManualySorted),
		        buttonOrientation, buttonPosition, buttonLabel);
	}

	/**
	 * 
	 * @param objectClass
	 * @param sourceTableRowModel
	 * @param destinationTableRowModel
	 */
	public AddRemoveTablePanel(Class objectClass, TableRowModel sourceTableRowModel,
	        TableRowModel destinationTableRowModel)
	{
		super(new AddRemoveTable(objectClass, sourceTableRowModel), new AddRemoveTable(objectClass,
		        destinationTableRowModel));
	}

	/**
	 * permet de récupérer l'action de déplacement des items dans la liste de
	 * destination l'action peut ainsi etre insérer où que ce soit dans l'écran
	 * 
	 * @return
	 */
	public Action getMoveUp()
	{
		if (null == m_moveUp)
		{
			// crée l'action
			m_moveUp = new AbstractAction(null, IconFactory.getUpArrowIcon())
			{
				public void actionPerformed(ActionEvent e)
				{
					AddRemoveTablePanel.this.moveSelectedElementUp();
				}
			};
			// ajoute automatiquement le listener sur la valueObjectListTable
			AddRemoveTablePanel.this.addMoveUpDownListener2DestinationList();
		}
		return m_moveUp;
	}

	/**
	 * permet de récupérer l'action de déplacement des items dans la liste de
	 * destination
	 * 
	 * @return
	 */
	public Action getMoveDown()
	{
		if (null == m_moveDown)
		{
			m_moveDown = new AbstractAction(null, IconFactory.getDownArrowIcon())
			{
				public void actionPerformed(ActionEvent e)
				{
					AddRemoveTablePanel.this.moveSelectedElementDown();
				}
			};

			// ajoute automatiquement le listener sur la valueObjectListTable
			AddRemoveTablePanel.this.addMoveUpDownListener2DestinationList();
		}
		return m_moveDown;
	}

	/**
	 * permet d'ajouter un listener sur la valueObjectListTable pour griser les
	 * boutons up / down
	 * 
	 * @param listener
	 */
	private void addMoveUpDownListener2DestinationList()
	{
		// on vérifie que le listener n'a pas déjà été ajouté car si on a appelé
		// getMoveDown avant, il a pu etre ajouter..
		if (m_lstDestination instanceof AddRemoveTable && !m_isListenerAdded)
		{
			((AddRemoveTable) m_lstDestination).addListSelectionListener(new MoveActionEnablerListener());
			m_isListenerAdded = true;
		}
	}

	protected int getSelectedRowOnDestinationList()
	{
		if (m_lstDestination instanceof AddRemoveTable)
		{
			return ((AddRemoveTable) m_lstDestination).getSelectedRow();
		} else
		{
			return -1;
		}
	}

	protected int getRowCountOnDestinationList()
	{
		if (m_lstDestination instanceof AddRemoveTable)
		{
			return ((AddRemoveTable) m_lstDestination).getRowCount();
		} else
		{
			return -1;
		}
	}

	public void sortDestinationList(int columnIndex, boolean ascendingSort)
	{
		if (m_lstDestination instanceof AddRemoveTable)
		{
			((AddRemoveTable) m_lstDestination).sort(columnIndex, ascendingSort);
		}
	}

	// changed here for sorting of Source List
	public void sortSourceList(int columnIndex, boolean ascendingSort)
	{
		if (m_lstSource instanceof AddRemoveTable)
		{
			((AddRemoveTable) m_lstSource).sort(columnIndex, ascendingSort);
		}
	}

	public void setSortableDestinationList(boolean sortable)
	{
		if (m_lstDestination instanceof AddRemoveTable)
		{
			((AddRemoveTable) m_lstDestination).setSortable(sortable);
		}
	}

	/**
	 * classe imbriquée pour redéfinir un listener et éviter une variable
	 * d'instance
	 * 
	 * @author omallassi Created on 4 févr. 2004
	 * 
	 */
	private class MoveActionEnablerListener implements ListSelectionListener
	{
		private MoveActionEnablerListener()
		{
			super();
		}

		public void valueChanged(ListSelectionEvent e)
		{
			int selectedRow = AddRemoveTablePanel.this.getSelectedRowOnDestinationList();
			int size = AddRemoveTablePanel.this.getRowCountOnDestinationList();
			Action moveUp = null;
			Action moveDown = null;
			moveUp = AddRemoveTablePanel.this.getMoveUp();
			moveDown = AddRemoveTablePanel.this.getMoveDown();

			if (size == 1 || size == 0)
			{
				moveUp.setEnabled(false);
				moveDown.setEnabled(false);
			} else
			{
				if (selectedRow == 0)
				{
					moveUp.setEnabled(false);
					moveDown.setEnabled(true);
				} else if (selectedRow == size - 1)
				{
					moveDown.setEnabled(false);
					moveUp.setEnabled(true);
				} else if (0 < selectedRow && selectedRow < size - 1)
				{
					moveUp.setEnabled(true);
					moveDown.setEnabled(true);
				} else
				{
					moveUp.setEnabled(false);
					moveDown.setEnabled(false);
				}
			}
		}
	}

	// ME-1219 : Added the following methods.
	/**
	 * 
	 * @param objectClass
	 * @param sourceTableRowModel
	 * @param destinationTableRowModel
	 * @param isManuallySorted
	 * @param freezeTableHeader
	 */
	public AddRemoveTablePanel(Class objectClass, TableRowModel sourceTableRowModel,
	        TableRowModel destinationTableRowModel, boolean isManuallySorted, boolean freezeTableHeader)
	{
		super(new AddRemoveTable(objectClass, sourceTableRowModel), new AddRemoveTable(objectClass,
		        destinationTableRowModel, isManuallySorted, freezeTableHeader));
	}

	/**
	 * @param srcTableModel
	 * @param destTableModel
	 * @param sourceTable
	 * @param destTable
	 */
	public AddRemoveTablePanel(ValueObjectListModel srcTableModel, ValueObjectListModel destTableModel,
	        ValueObjectListTable sourceTable, ValueObjectListTable destTable)
	{
		super(new AddRemoveTable(srcTableModel, sourceTable), new AddRemoveTable(destTableModel, destTable));
		disableAddButton(false);
		disableRemoveButton(false);
		disableAddAllButton(false);
		disableRemoveAllButton(false);
	}

	public void disableAddButton(boolean flag)
	{
		getM_addAction().setEnabled(flag);

	}

	public void disableRemoveButton(boolean flag)
	{
		getM_removeAction().setEnabled(flag);
	}

	public void disableAddAllButton(boolean flag)
	{
		getM_addAllAction().setEnabled(flag);

	}

	public void disableRemoveAllButton(boolean flag)
	{
		getM_removeAllAction().setEnabled(flag);
	}
	// ME-1219 : ends.
}

// removed the commented class AddRemoveTable
