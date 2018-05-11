package com.hotel.client.main;

import java.awt.BorderLayout;

import com.hotel.client.alert.AlertController;
import com.hotel.client.common.security.LoginWindow;
import com.hotel.client.common.welcome.HomeScreenFrame;
import com.hotel.client.configuration.ConfigurationController;
import com.hotel.client.create.user.UserController;
import com.hotel.client.room.calandar.RoomCalandarController;
import com.rennover.client.framework.application.ApplicationHelper;
import com.rennover.client.framework.mvc.ActionDescription;
import com.rennover.client.framework.mvc.PanelController;
import com.rennover.client.framework.widget.base.ZFrame;
import com.rennover.client.framework.widget.base.ZMenu;
import com.rennover.hotel.common.log.Logger;
import com.rennover.hotel.common.properties.CommonProperties;

/**
 * Main Class from where code will start executing.
 * 
 * @author Piyush
 * 
 */
public class HotelFullClientApplication extends ClientApplication {

	/** */
	public static final ActionDescription ACTION_ROOM_CALENDAR = new ActionDescription(
			"Room Calendar", "doRoomCalendar");

	/** */
	public static final ActionDescription ACTION_BANQUET_CALENDAR = new ActionDescription(
			"Banquet Calendar", "doBanquetCalendar");

	/** */
	public static final ActionDescription ACTION_EVENT_CALENDAR = new ActionDescription(
			"Event Calendar", "doEventCalendar");

	/** */
	public static final ActionDescription ACTION_HISTORY = new ActionDescription(
			"History", "doHistory");

	/** */
	public static final ActionDescription ACTION_REPORTS = new ActionDescription(
			"Reports", "doReports");

	/** */
	public static final ActionDescription ACTION_KITCHEN_ORDER = new ActionDescription(
			"Kitchen Order", "doKitchen");

	/** */
	public static final ActionDescription ACTION_LAUNDRY = new ActionDescription(
			"Laundry", "doLaundry");

	/** */
	public static final ActionDescription ACTION_TOOLS = new ActionDescription(
			"Tools", "doTools");

	/** */
	public static final ActionDescription ACTION_DIRECTORY = new ActionDescription(
			"Directory", "doDirectory");
	/** */
	public static final ActionDescription ACTION_CONFIGURATION = new ActionDescription(
			"Configuration", "doConfiguration");
	public static final ActionDescription ACTION_ALERT = new ActionDescription(
			"Alert", "doAlert");

	/**
	 * 
	 */
	public HotelFullClientApplication() {
		super();
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isFullClient() {
		return HotelClientApplication.getHotelApplication().isFull();
	}

	/**
	 * 
	 * @return
	 */
	protected boolean isFull() {
		return true;
	}

	/**
	 * 
	 */
	protected void login(String login, String password) {
		LoginWindow loginWindow = new LoginWindow(login, password) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3074919878691933297L;

			public void doValidate() {
				super.doValidate();
				if (isLogged()) {
					initMainFrame();
					showMainFrame();
				}
			}
		};
		loginWindow.setDefaultCloseOperation(ZFrame.EXIT_ON_CLOSE);
		loginWindow.show();
	}

	/**
	 * 
	 */
	protected void initMainFrame() {
		setMainFrame(new HomeScreenFrame());
	}

	public void logout() {/*
						 * if (UserContextManager.isLogged()) {
						 * //Logger.debug(this, "log out");
						 * 
						 * UserContextManager.logout(); }
						 */
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Thread.currentThread().setContextClassLoader(
				HotelFullClientApplication.class.getClassLoader());

		if (ApplicationHelper.isOptionDefined(args, "-USEREDMENU")) {
			ZMenu.setUseRedMenu(true);
		}

		if (ApplicationHelper.isOptionDefined(args, "-NOREDMENU")) {
			ZMenu.setUseRedMenu(false);
		}

		if (ApplicationHelper.isOptionDefined(args, "-NOSERVER")) {
			CommonProperties.setNoServerMode();
		}

		if (ApplicationHelper.isOptionDefined(args, "-NOSCHEDULER")) {
			// GestionMessageController.setUseScheduler(false);
		}

		ClientInitializer.init();

		if (Logger.isDebugEnabled()) {
			Logger.debug(HotelFullClientApplication.class,
					"Full Client is running");
		}
		HotelFullClientApplication appli = new HotelFullClientApplication();
		appli.start(args);
	}

	/**
	 * 
	 */
	protected void start(String[] args) {
		String login = ApplicationHelper.getArgumentNoOption(args, 0);
		String password = ApplicationHelper.getArgumentNoOption(args, 1);
		login(login, password);
	}

	public void doShowAsRightPanel(PanelController controller) {
		HomeScreenFrame homeScreenFrame = (HomeScreenFrame) getMainFrame();
		homeScreenFrame.getHomeScreenPanel().getRightPanel().removeAll();
		homeScreenFrame.getHomeScreenPanel().getRightPanel()
				.setLayout(new BorderLayout());
		homeScreenFrame.getHomeScreenPanel().getRightPanel()
				.add(controller.getView(), BorderLayout.CENTER);
		homeScreenFrame.getHomeScreenPanel().getRightPanel().revalidate();
		homeScreenFrame.getHomeScreenPanel().getRightPanel().repaint();
		homeScreenFrame.getHomeScreenPanel().getRightPanel().setVisible(true);
	}

	/**
	 * 
	 */
	public void doRoomCalendar() {
		final RoomCalandarController controller = new RoomCalandarController();
		doShowAsRightPanel(controller);
	}

	public void doBanquetCalendar() {
		// ZFrame mainFrame = getMainFrame();

		// controller.setParentWindow(mainFrame);
		System.out.println("Hi In Do doBanquetCalendar");

	}

	public void doEventCalendar() {
		// ZFrame mainFrame = getMainFrame();

		// controller.setParentWindow(mainFrame);
		System.out.println("Hi In doEventCalendar");

	}

	public void doHistory() {
		// ZFrame mainFrame = getMainFrame();

		// controller.setParentWindow(mainFrame);
		System.out.println("Hi In doHistory");

	}

	public void doReports() {
		// ZFrame mainFrame = getMainFrame();

		// controller.setParentWindow(mainFrame);
		System.out.println("Hi In doReports");

	}

	public void doKitchen() {
		// ZFrame mainFrame = getMainFrame();

		// controller.setParentWindow(mainFrame);
		System.out.println("Hi In doKitchen");

	}

	public void doLaundry() {
		// ZFrame mainFrame = getMainFrame();

		// controller.setParentWindow(mainFrame);
		System.out.println("Hi In doLaundry");

	}

	public void doTools() {
		// ZFrame mainFrame = getMainFrame();

		// controller.setParentWindow(mainFrame);
		System.out.println("Hi In doTools");

	}

	public void doDirectory() {

	}

	public void doConfiguration() {
		ConfigurationController controller = new ConfigurationController();
		ZFrame mainFrame = getMainFrame();
		controller.setParentWindow(mainFrame);
		doShowAsRightPanel(controller);
	}

	public void doAlert() {		
		AlertController alertControler = new AlertController();
		ZFrame mainFrame = getMainFrame();
		alertControler.setParentWindow(mainFrame);
		doShowAsFrame(alertControler);
	}

	public void doShowAsFrame(PanelController controller) {
		HomeScreenFrame homeScreenFrame = (HomeScreenFrame) getMainFrame();

		doShowUseCase(homeScreenFrame, controller);

		homeScreenFrame.setVisible(true);
	}

}