package com.hotel.client.common.welcome.message;

import java.util.ArrayList;
import java.util.List;

import com.hotel.client.common.message.MessageConstants;
import com.hotel.client.common.welcome.HomeScreenFrame;
import com.rennover.client.framework.mvc.ActionDescription;
import com.rennover.client.framework.mvc.PanelController;
import com.rennover.client.framework.widget.icon.IconFactory;
import com.rennover.client.framework.window.WindowManager;
import com.rennover.hotel.common.helper.CollectionHelper;
import com.rennover.hotel.common.properties.PropertiesManager;
/**
 * 
 * @author Piyush
 *
 */
public class MessageController extends PanelController implements
		MessageConstants,
		com.hotel.client.common.welcome.message.MsgConstants {
	private static boolean useScheduler = true;

	public static final ActionDescription ACTION_UPDATE = new ActionDescription(
			MSG_UPDATE, "doUpdate", 'H', IconFactory.getRefreshIcon(),
			null);

	public static final ActionDescription ACTION_IMPRIMER = new ActionDescription(
			MSG_PRINT, "doPrint", 'I', IconFactory.getPrintIcon(), null);

	public static final ActionDescription ACTION_EFFACER = new ActionDescription(
			MSG_DELETE, "doDelete", 'E', IconFactory.getDeleteIcon(), null);

	private HomeScreenFrame m_homeScreenFrame = null;

	private List m_messageList;

	public MessageController(HomeScreenFrame homeScreenFrame) {
		init();
		m_homeScreenFrame = homeScreenFrame;
	}

	protected void checkPreconditions() {
	}

	protected void createModel() {
		setModel(new MessageModel());
	}

	/**
	 * gets the initial message list to be displayed and schedules the timer for
	 * "autorefresh" by performing the task "MessageAutoRefresher"
	 */
	protected void retrieveInitialData() {
		// m_messageList =
		// FacadeFactory.getGestionMessageUtilisateur().getMessageUtilisateurList(false);//
	}

	protected void createView() {
		setView(new MessageView(this, (MessageModel) getModel(), m_messageList));

		if (isUseScheduler()) {
			String periodString = PropertiesManager.getProperty(
					"nsicm.client.message.refresh.period", "300000");
			// UserContextManager.scheduleRepeated(new
			// MessageAutoRefresher(this, true), Long.parseLong(periodString));
			/*
			 * UserContextManager.scheduleRepeated(new
			 * MessageAutoRefresher(this, false),
			 * Long.parseLong(PropertiesManager
			 * .getProperty("nsicm.common.application.userDeConnectMessage.refresh"
			 * , "300000")));
			 */
		}

	}

	public synchronized void doUpdate() {
		((MessageView) getView()).reinitialiseMessageList();

		List messageList = new ArrayList<String>();
		// messageList =
		// FacadeFactory.getGestionMessageUtilisateur().getMessageUtilisateurList(false);//
		// 8403
		//
		getModel().getValueObjectListModel(
				MessageModel.MESSAGE_UTILISATEUR_MODEL).setValueObjectList(
				messageList);

		// Fix for defect 2789
		if (!CollectionHelper.isNullOrEmpty(messageList)) {
			((MessageView) getView()).composeMessagePanelMain(messageList);
		} else {
			((MessageView) getView()).clearMessagePanel();
		}
	}

	public synchronized void doDelete() {
		if (WindowManager.showConfirmationMessage("Test",
				this.getParentWindow())) {
			List effacerList = ((MessageView) getView())
					.getSelectedMessageList();
			/*
			 * FacadeFactory.getGestionMessageUtilisateur().
			 * effacerMessageUtilisateurList(effacerList); ((GestionMessageView)
			 * getView()).reinitialiseMessageList();
			 */
		}

		doUpdate();
	}

	// added for ME944(printing of selected messages) by Bhuvana
	public synchronized void doPrint() {
		List imprimerList = ((MessageView) getView()).getSelectedMessageList();

		// if (!CollectionHelper.isNullOrEmpty(imprimerList))
		// {
		/*
		 * List messageList = new ArrayList(); Iterator iter =
		 * imprimerList.iterator(); StringBuffer printMsg; MessageUtilisateur
		 * msgUtil; MessageUtilisateur orgMsgUtil;
		 * 
		 * while (iter.hasNext()) { orgMsgUtil = (MessageUtilisateur)
		 * iter.next();
		 * 
		 * String msg = orgMsgUtil.getTexte(); printMsg = new StringBuffer();
		 * 
		 * int startIndex = 0; int endIndex = 70; // Added for defect 9305
		 * 
		 * if (msg.length() <= 70) { printMsg.append(msg); } else {
		 * 
		 * while (startIndex < msg.length()) { // commented for 9305 // dead
		 * code was removed during cleaning
		 * 
		 * // If condition added for defect 9305 line break is // added while
		 * the word is completed if (msg.length() > endIndex &&
		 * PropertyHelper.equals(msg.charAt(endIndex), ' ')) {
		 * printMsg.append(msg.substring(startIndex, endIndex) + '\n');
		 * startIndex = endIndex; if ((startIndex + 70) > msg.length()) {
		 * endIndex = msg.length(); printMsg.append(msg.substring(startIndex,
		 * endIndex) + '\n'); break; } else { endIndex = (startIndex + 70); } }
		 * else { endIndex--; } // end of defect 9305 } }
		 * 
		 * msgUtil = new MessageUtilisateur();
		 * msgUtil.setId(orgMsgUtil.getId());
		 * msgUtil.setDateValidate(orgMsgUtil.getDateValidate());
		 * msgUtil.setImportance(orgMsgUtil.getImportance());
		 * msgUtil.setTexte(printMsg.toString());
		 * msgUtil.setEstNationale(orgMsgUtil.isEstNationale()); printMsg =
		 * null; messageList.add(msgUtil); }
		 * 
		 * ValueObjectListModel volm = new
		 * ValueObjectListModel(MessageUtilisateur.class);
		 * volm.setValueObjectList(messageList);
		 * 
		 * ValueObjectListTable tblMessages = new ValueObjectListTable(volm,
		 * Wrapper.toList("texte"), Wrapper .toList("Messages"));
		 * PrintToolbox.doPrintTable("", tblMessages, getView()); }
		 */}

	/*
	 * private synchronized void deleteMessage(MessageUtilisateur msg) {
	 * //FacadeFactory
	 * .getGestionMessageUtilisateur().effacerMessageUtilisateurList
	 * (Wrapper.toList(msg)); }
	 */
	/**
	 * @return
	 */
	public static boolean isUseScheduler() {
		return useScheduler;
	}

	/**
	 * @param b
	 */
	public static void setUseScheduler(boolean b) {
		useScheduler = b;
	}

	/*
	 * public void forciblyExitUser(MessageUtilisateur messageUtilisateur) {
	 * deleteMessage(messageUtilisateur);
	 * 
	 * if (messageUtilisateur.getDateValidate().after(UserContextManager.
	 * getContexteUtilisateur().getLogonDateTime())) { if (m_homeScreenFrame !=
	 * null) {
	 *//**
	 * Added for defect 9469 By Laxmi Narayana to display message dialog on
	 * ctive window and get focus
	 */
	/*
	 * 
	 * Frame[] frames = JFrame.getFrames(); Window windows[] =
	 * frames[frames.length - 1].getOwnedWindows(); final Component owner; if
	 * (windows.length > 0) { owner = windows[windows.length - 1]; } else {
	 * owner = frames[frames.length - 1]; } // if owner is dialog then make it
	 * non model to get focus out of // it if (owner instanceof Dialog) {
	 * ((Dialog) owner).setModal(false); } SwingUtilities.invokeLater(new
	 * Thread() { public void run() { WindowManager.showWarningMessage(MSG_667,
	 * owner); WindowManager.s_isMessageNeedToShowed = true;
	 * m_homeScreenFrame.exitApplicationForicbly();
	 * FacadeFactory.getGestionConnexion().deconnecter(
	 * UserContextManager.getContexteUtilisateur().getLogin()); } }); // End
	 * Defect 9469 // dead code for 9469 was removed during cleaning } } }
	 */

	// added for defect 8403 to fetch the Message's releated to logged in user
	// with importance 'X'
	public synchronized void doRafraichirMessageToDisConnect() {

		List messageList = null;
		// if TRUE NSI will call this every 15 seconds ,
		// if FALSE NSI will call every 10 minutes to update the user Cache
		/*
		 * messageList =
		 * FacadeFactory.getGestionMessageUtilisateur().getMessageUtilisateurList
		 * (true);// 8403 if (!CollectionHelper.isNullOrEmpty(messageList)) {
		 * 
		 * ((GestionMessageView)
		 * getView()).forciblyExitUserToDisConnect((MessageUtilisateur)
		 * messageList.get(0)); }
		 */}
}
