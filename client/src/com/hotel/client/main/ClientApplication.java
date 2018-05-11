package com.hotel.client.main;

import java.awt.Window;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;

import com.hotel.client.common.window.ColorWindow;
import com.hotel.common.window.MainDialog;
import com.rennover.client.framework.application.Application;
import com.rennover.client.framework.mvc.PanelController;
import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.widget.base.ZDialog;
import com.rennover.client.framework.widget.base.ZFrame;
import com.rennover.client.framework.window.LookAndFeelManager;
import com.rennover.client.framework.window.ShowUIDefaults;

/**
 * 
 * @author Piyush
 * 
 */
public class ClientApplication extends Application {
	private ZFrame m_mainFrame;

	public ClientApplication() {
		super();
	}

	public ZFrame getMainFrame() {
		return m_mainFrame;
	}

	public void setMainFrame(ZFrame frame) {
		m_mainFrame = frame;
	}

	protected void init() {
	}

	protected void initMainFrame() {
	}

	protected void login(String login, String password) {
	}

	void showMainFrame() {
		getMainFrame().setVisible(true);
	}

	public void showAsDialog(ZDialog dialog, PanelView view) {
		view.showAsDialog(dialog);
	}

	public void showAsFrame(ZFrame frame, PanelView view) {
		view.showAsFrame(frame);
	}

	public void showAsDefaultDialog(Window parent, PanelView view) {
		MainDialog dialog = MainDialog.createMainDialog(parent, view);
		dialog.show();
	}

	protected void doShowUseCase(ZFrame frame, PanelController controller) {
		controller.setParentWindow(frame);
		showAsFrame(frame, controller.getView());
	}

	protected void start(String[] args) {
		init();
	}

	/*
	 * public static void main(String[] args) { ClientInitializer.init();
	 * //Logger.debug(ClientApplication.class, "Full Client is running");
	 * ClientApplication appli = new ClientApplication(); appli.start(args); }
	 */

	public void doBackToHomeScreen() {
		ZFrame mainFrame = getMainFrame();
		mainFrame.requestFocus();
	}

	public void doExitApplication() {
		ZFrame mainFrame = getMainFrame();
		mainFrame.dispatchEvent(new WindowEvent(mainFrame,
				WindowEvent.WINDOW_CLOSING));
	}

	public void resizeMainFrame(int width, int height) {
		ZFrame mainFrame = getMainFrame();
		mainFrame.setSize(width, height);
		mainFrame.validate();
		mainFrame.centerToScreen();
	}

	public void doFitToWindow() {
		resizeMainFrame(1024, 768);
	}

	public void doFitToWindow1024() {
		resizeMainFrame(1020, 740);
	}

	public void doFitToWindow800() {
		resizeMainFrame(792, 572);
	}

	public void doLookWindows() {
		ZFrame mainFrame = getMainFrame();

		LookAndFeelManager.setWindowsLookAndFeel();
		SwingUtilities.updateComponentTreeUI(mainFrame);

		mainFrame.validate();
	}

	public void doLookAlloy() {
		ZFrame mainFrame = getMainFrame();
		LookAndFeelManager.setAlloyLookAndFeel();
		SwingUtilities.updateComponentTreeUI(mainFrame);
		mainFrame.validate();
	}

	public void doLookMetal() {
		ZFrame mainFrame = getMainFrame();
		LookAndFeelManager.setMetalLookAndFeel();
		SwingUtilities.updateComponentTreeUI(mainFrame);
		mainFrame.validate();
	}

	public void doLookAlloyPerso() {
		ColorWindow colorWindow = new ColorWindow(getMainFrame());
		colorWindow.setVisible(true);
	}

	public void doShowUIDefaults() {
		ZFrame mainFrame = getMainFrame();
		ShowUIDefaults f = new ShowUIDefaults(mainFrame, "UI Defaults");
		f.pack();
		f.centerToScreen();
		f.setVisible(true);
	}
}