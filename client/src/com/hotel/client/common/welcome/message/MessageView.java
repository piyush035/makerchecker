package com.hotel.client.common.welcome.message;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;

import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.field.CheckBoxField;
import com.rennover.client.framework.widget.panel.ButtonPanel;
import com.rennover.client.framework.widget.panel.FieldPanel;
import com.rennover.hotel.common.helper.CollectionHelper;

/**
 * 
 * @author Piyush
 * 
 *         Creates the view containing messages for the CompteUtilisateur along
 *         with buttons to refresh and delete selected messages
 */
public class MessageView extends PanelView {
	public static final Color BLUE = Color.BLUE;

	public static final Color GREEN = Color.GREEN;

	public static final Color RED = Color.RED;

	private FieldPanel m_pnlMessagePanel;

	private ButtonPanel m_pnlButtonPanel;

	private ZPanel m_chkboxPanel;

	private ZPanel m_northPanel;

	private ZPanel m_centerPanel;

	private ZPanel m_eastPanel;

	private ZPanel m_westPanel;

	private ZPanel m_buttonPanel;

	private List m_selectedList;

	private List m_selectedNationaleList;

	private List m_totalList;

	// private MessageCheckBox m_cb;

	private List m_messageList = null;

	private List m_initialMsgList;

	public MessageView(MessageController controller,
			MessageModel model, List initialMsgList) {
		super(controller, model);
		m_initialMsgList = initialMsgList;
		init();
	}

	protected void instanciate() {
		m_pnlButtonPanel = new ButtonPanel(ButtonPanel.VERTICAL);
		m_pnlButtonPanel.setAlignmentX(ButtonPanel.CENTER_ALIGNMENT);
		m_pnlButtonPanel.setAlignmentY(ButtonPanel.TOP_ALIGNMENT);
		m_messageList = getModel().getValueObjectListModel(
				MessageModel.MESSAGE_UTILISATEUR_MODEL)
				.getValidatedValueObjectList();
		m_pnlMessagePanel = new FieldPanel(true);
		m_chkboxPanel = new ZPanel();
		m_northPanel = new ZPanel();
		m_buttonPanel = new ZPanel();
		m_centerPanel = new ZPanel();
		m_eastPanel = new ZPanel();
		m_westPanel = new ZPanel();
		m_selectedList = new ArrayList();
		m_selectedNationaleList = new ArrayList();
	}

	protected void compose() {
		setLayout(new BorderLayout());
		add(m_northPanel, BorderLayout.NORTH);
		add(m_eastPanel, BorderLayout.EAST);
		add(composeCenterPanel(), BorderLayout.CENTER);
		add(m_westPanel, BorderLayout.WEST);
	}

	private ZPanel composeCenterPanel() {
		m_buttonPanel.setLayout(new BorderLayout());
		m_buttonPanel.add(composeButtonPanel(), BorderLayout.WEST);
		m_buttonPanel.add(composeMessagePanel(), BorderLayout.CENTER);

		return m_buttonPanel;
	}

	private ButtonPanel composeButtonPanel() {
		// changed the width from 100 to 106 for bug fix 2790
		m_pnlButtonPanel.setPreferredSize(new Dimension(106, 100));
		m_pnlButtonPanel.addButtonAction(getController().getAction(
				MessageController.ACTION_UPDATE));
		m_pnlButtonPanel.addStrut(5);

		// ButtonAction added by Bhuvana for ME944
		m_pnlButtonPanel.addButtonAction(getController().getAction(
				MessageController.ACTION_IMPRIMER));
		m_pnlButtonPanel.addStrut(5);
		m_pnlButtonPanel.addButtonAction(getController().getAction(
				MessageController.ACTION_EFFACER));
		getController().getAction(MessageController.ACTION_EFFACER)
				.setEnabled(false);

		// line of code added by Bhuvana for ME944
		getController().getAction(MessageController.ACTION_IMPRIMER)
				.setEnabled(false);

		return m_pnlButtonPanel;
	}

	private ZPanel composeMessagePanel() {
		m_chkboxPanel.setBorder(new TitledBorder(""));
		m_chkboxPanel.setLayout(new BoxLayout(m_chkboxPanel, BoxLayout.Y_AXIS));
		composeMessagePanelMain(m_initialMsgList);

		return m_chkboxPanel;
	}

	/**
	 * called first from compose() and then from doRafraichir() at every refresh
	 * 
	 * @param List
	 *            messageList
	 * 
	 */
	public void composeMessagePanelMain(List messageList) {
		Color DARK_GREEN = GREEN.darker();
		getController().getAction(MessageController.ACTION_EFFACER)
				.setEnabled(false);
		getController().getAction(MessageController.ACTION_IMPRIMER)
				.setEnabled(false);
		m_chkboxPanel.removeAll();

		if (null != messageList) {/*
								 * m_totalList = new ArrayList(messageList);
								 * 
								 * for (Iterator itMessageList =
								 * messageList.iterator();
								 * itMessageList.hasNext();) {
								 * MessageUtilisateur messageUtilisateur =
								 * (MessageUtilisateur) itMessageList.next();
								 * 
								 * if
								 * (messageUtilisateur.getImportance().equals(
								 * MessageImportanceEnum.FORCIBLY_EXIT)) {
								 * ((GestionMessageController)
								 * getController()).forciblyExitUser
								 * (messageUtilisateur); } else { m_cb = new
								 * MessageCheckBox
								 * (messageUtilisateur.getTexte());
								 * m_cb.setValue(messageUtilisateur);
								 * m_cb.setAction(new
								 * CheckBoxListener(messageUtilisateur));
								 * 
								 * if
								 * (messageUtilisateur.getImportance().equals(
								 * MessageImportanceEnum.HIGH)) {
								 * m_cb.getLabel().setForeground(RED); } else if
								 * (messageUtilisateur.getImportance().equals(
								 * MessageImportanceEnum.MEDIUM)) {
								 * m_cb.getLabel().setForeground(DARK_GREEN); }
								 * else if
								 * (messageUtilisateur.getImportance().equals
								 * (MessageImportanceEnum.LOW)) {
								 * m_cb.getLabel().setForeground(BLUE); } //
								 * commented for ME 944 // dead code was removed
								 * during cleaning
								 * 
								 * m_chkboxPanel.add(m_cb); } }
								 */
		}
		m_selectedNationaleList.clear();
		m_selectedList.clear();
		m_chkboxPanel.revalidate();
	}

	/**
	 * called from controller to return the List of selected messages to be
	 * deleted
	 * 
	 * @return List
	 */
	public List getSelectedMessageList() {
		return m_selectedList;
	}

	public void reinitialiseMessageList() {
		m_selectedList = new ArrayList();
	}

	// Added for defect 2789 fix
	protected void clearMessagePanel() {
		m_chkboxPanel.setBorder(new TitledBorder(""));
		m_chkboxPanel.setLayout(new BoxLayout(m_chkboxPanel, BoxLayout.Y_AXIS));
		composeMessagePanelMain(new ArrayList());
	}

	/**
	 * 
	 * @author varund
	 * 
	 *         Inner class executed at the time of selection of a message as an
	 *         action
	 * 
	 */
	class CheckBoxListener implements ActionListener {
		// MessageUtilisateur m_messageUtilisateur;

		/*	*//**
		 * @param utilisateur
		 */
		/*
		 * public CheckBoxListener(MessageUtilisateur utilisateur) {
		 * m_messageUtilisateur = utilisateur; }
		 */
		// removed duplication of code - ME 944 - Bhuvana
		public void actionPerformed(ActionEvent arg0) {
			CheckBoxField chkBox = ((CheckBoxField) arg0.getSource());

			if (chkBox.isSelected()) {
				/*
				 * if (m_selectedList.contains(m_messageUtilisateur)) {
				 * m_selectedList.remove(m_messageUtilisateur); } else {
				 * m_selectedList.add(m_messageUtilisateur); } if
				 * (m_messageUtilisateur.isEstNationale()) { if
				 * (m_selectedNationaleList.contains(m_messageUtilisateur)) {
				 * m_selectedNationaleList.remove(m_messageUtilisateur); } else
				 * { m_selectedNationaleList.add(m_messageUtilisateur); } }
				 */} else if (!chkBox.isSelected()) {
				if (!CollectionHelper.isNullOrEmpty(m_selectedList)) {
					/*
					 * if (m_selectedList.contains(m_messageUtilisateur)) {
					 * m_selectedList.remove(m_messageUtilisateur); } } if
					 * (!CollectionHelper
					 * .isNullOrEmpty(m_selectedNationaleList)) { if
					 * (m_selectedNationaleList.contains(m_messageUtilisateur))
					 * { m_selectedNationaleList.remove(m_messageUtilisateur); }
					 * }
					 */}

				if (!CollectionHelper.isNullOrEmpty(m_selectedList)) {
					if (!CollectionHelper
							.isNullOrEmpty(m_selectedNationaleList)) {
						getController().getAction(
								MessageController.ACTION_EFFACER)
								.setEnabled(false);
					} else {
						getController().getAction(
								MessageController.ACTION_EFFACER)
								.setEnabled(true);
					}
					getController().getAction(
							MessageController.ACTION_IMPRIMER)
							.setEnabled(true);
				} else {
					getController().getAction(
							MessageController.ACTION_EFFACER)
							.setEnabled(false);
					getController().getAction(
							MessageController.ACTION_IMPRIMER)
							.setEnabled(false);
				}
			}
		}

		/*
		 * // added for 8403 NSI uses this mtd to disconnect the user public
		 * void forciblyExitUserToDisConnect(MessageUtilisateur
		 * messageUtilisateur) { ((GestionMessageController)
		 * getController()).forciblyExitUser(messageUtilisateur); }
		 */
	}
}
