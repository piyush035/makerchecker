
package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.base.ZList;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.base.ZScrollPane;
import com.rennover.client.framework.widget.base.ZTextField;
import com.rennover.client.framework.widget.list.ZListModel;
import com.rennover.client.framework.widget.textfield.ZTextFieldModel;
import com.rennover.hotel.common.valueobject.ValueObject;

public class FastSearchListPanel extends ZPanel implements DocumentListener
{
	private List m_valueList = new ArrayList();

	private String m_searchLabel;

	private String m_borderLabel;

	private ZLabel m_lblSearch;

	private ZTextField m_tfSearch;

	private ZList m_lstResultat;

	private ZListModel m_lstResultatModel;

	private ZTextFieldModel m_tfSearchModel;

	protected boolean m_ascending = true;

	public void init()
	{
		instanciate();
		compose();
	}

	protected void instanciate()
	{
		m_lblSearch = new ZLabel(m_searchLabel);
		m_tfSearch = new ZTextField();
		m_lstResultat = new ZList();
		m_lstResultat.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		m_lstResultatModel = new ZListModel();
		m_lstResultat.setModel(m_lstResultatModel);
		m_tfSearchModel = new ZTextFieldModel();
		m_tfSearchModel.addDocumentListener(this);
		m_tfSearch.setDocument(m_tfSearchModel);
	}

	protected void compose()
	{
		setLayout(new BorderLayout());
		ZPanel pnlRecherche = new ZPanel();
		pnlRecherche.setLayout(new BorderLayout());
		pnlRecherche.setBorder(new TitledBorder(new EtchedBorder(), m_borderLabel));

		ZPanel pnlHaut = new ZPanel();
		pnlHaut.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		buildConstraints(constraints, 0, 0, 1, 1, 0.0, 0.0);
		pnlHaut.add(m_lblSearch, constraints);

		buildConstraints(constraints, 1, 0, 1, 1, 1.0, 1.0);

		pnlHaut.add(m_tfSearch, constraints);

		ZScrollPane spResultat = new ZScrollPane();
		spResultat.setViewportView(m_lstResultat);
		pnlRecherche.add(pnlHaut, BorderLayout.NORTH);
		pnlRecherche.add(spResultat, BorderLayout.SOUTH);
		add(pnlRecherche, BorderLayout.NORTH);
	}

	/**
	 * Tri la liste : ascendant si param=true.
	 * 
	 * @param ascending
	 */
	private void sort(boolean ascending)
	{
		m_ascending = ascending;
		Collections.sort(m_valueList, new Comparator()
		{
			public int compare(Object o1, Object o2)
			{
				int result = compareRows(o1, o2);
				if (result != 0)
				{
					return m_ascending ? result : -result;
				}
				return 0;
			}
		});
	}

	protected int compareRows(Object row1, Object row2)
	{
		if (row1 == null && row2 == null)
		{
			return 0;
		} else if (row1 == null)
		{
			return -1;
		} else if (row2 == null)
		{
			return 1;
		}
		Class objectClass = row1.getClass();
		Object tmpRow1;
		Object tmpRow2;

		if (java.lang.Number.class.isAssignableFrom(objectClass))
		{
			double d1 = ((Number) row1).doubleValue();
			double d2 = ((Number) row2).doubleValue();

			tmpRow1 = new Double(d1);
			tmpRow2 = new Double(d2);
		} else if (java.util.Date.class.isAssignableFrom(objectClass))
		{
			tmpRow1 = row1;
			tmpRow2 = row2;
		} else if (objectClass == String.class)
		{
			tmpRow1 = ((String) row1).toLowerCase();
			tmpRow2 = ((String) row2).toLowerCase();
		} else
		{
			tmpRow1 = row1.toString().toLowerCase();
			tmpRow2 = row2.toString().toLowerCase();
		}
		return ((Comparable) tmpRow1).compareTo(tmpRow2);
	}

	/**
	 * Retourne le value object selectionné .
	 * 
	 * @return String
	 */
	public ValueObject getSelectedValue()
	{
		return null;
	}

	/**
	 * Retourne le titre du cadre s'il existe ou null autrement.
	 * 
	 * @return String
	 */
	public String getBorderLabel()
	{
		return m_borderLabel;
	}

	/**
	 * Retourne le titre du Label de recherche.
	 * 
	 * @return String
	 */
	public String getSearchLabel()
	{
		return m_searchLabel;
	}

	/**
	 * Retourne la listes des values objects contenu dans la JList.
	 * 
	 * @return List
	 */
	public List getValueList()
	{
		return m_valueList;
	}

	/**
	 * Met à jour le titre du cadre. Si le titre borderLabel est null le cadre
	 * ne doit pas etre afficher.
	 * 
	 * @param borderLabel
	 */
	public void setBorderLabel(String borderLabel)
	{
		m_borderLabel = borderLabel;
	}

	/**
	 * Met à jour le titre du Label de recherche.
	 * 
	 * @param searchLabel
	 */
	public void setSearchLabel(String searchLabel)
	{
		m_searchLabel = searchLabel;
	}

	/**
	 * Met à jour les values objects contenu dans la JList.
	 * 
	 * @param valueList
	 */
	public void setValueList(List valueList)
	{
		m_valueList = valueList;
		m_lstResultatModel.setObjectList(m_valueList);
	}

	public void changedUpdate(DocumentEvent e)
	{
		update(e);
	}

	public void insertUpdate(DocumentEvent e)
	{
		update(e);
	}

	public void removeUpdate(DocumentEvent e)
	{
		update(e);
	}

	private void update(DocumentEvent e)
	{
		try
		{
			String selectedString = e.getDocument().getText(0, e.getDocument().getLength());
			boolean search = false;

			// Recherche le mon exactement
			Iterator iteratorSearch = m_valueList.iterator();
			while (iteratorSearch.hasNext() && search == false)
			{
				Object SelectedObject = (iteratorSearch.next()).toString();
				if (((String) SelectedObject).equalsIgnoreCase(selectedString))
				{
					m_lstResultat.setSelectedValue(SelectedObject, true);
					search = true;
				}
			}

			Iterator iterator = m_valueList.iterator();
			while (iterator.hasNext() && search == false)
			{
				Object SelectedObject = (iterator.next()).toString();
				if (((String) SelectedObject).toUpperCase().startsWith(selectedString.toUpperCase()))
				{
					m_lstResultat.setSelectedValue(SelectedObject, true);
					search = true;
					;
				}
			}
		} catch (BadLocationException ex)
		{
			ex.printStackTrace();
		}
	}

	private void buildConstraints(GridBagConstraints constraints, int gridx, int gridy, int gridwidth, int gridheight,
	        double weightx, double weighty)
	{
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridwidth = gridwidth;
		constraints.gridheight = gridheight;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
	}

	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		JFrame mainFrame = new JFrame();
		FastSearchListPanel fastSearchList = new FastSearchListPanel();
		mainFrame.getContentPane().add(fastSearchList, BorderLayout.CENTER);
		fastSearchList.setSearchLabel("Libellé");
		fastSearchList.setBorderLabel("Rechercher Région");
		List valueList = new ArrayList(8);
		valueList.add("Bretagne");
		valueList.add("Ile de France");
		valueList.add("Auvergne");
		valueList.add("Alsace");
		valueList.add("Corse");
		valueList.add("a");
		valueList.add("A");
		valueList.add("Au");
		fastSearchList.init();
		fastSearchList.setValueList(valueList);
		fastSearchList.sort(true);

		mainFrame.pack();

		mainFrame.setTitle("Recherche");
		mainFrame.setVisible(true);
	}
}