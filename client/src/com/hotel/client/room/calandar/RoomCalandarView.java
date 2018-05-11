package com.hotel.client.room.calandar;

import java.awt.BorderLayout;

import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.base.ZTextField;
import com.rennover.client.framework.widget.panel.FieldPanel;

/**
 * 
 * @author Piyush
 * 
 */
public class RoomCalandarView extends PanelView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5159264915531354649L;
	/**
	 * Main FieldPanel. All component are fixed on this panel.
	 */
	private FieldPanel mainPanel;
	
	private ZLabel monthLabel;
	
	private ZTextField textField;
	/**
	 * Default Construtor
	 */
	public RoomCalandarView() {
		super();
		init();
	}

	/**
	 * Constructor with model and controller
	 * 
	 * @param controller
	 *            JournalDesContributionsController
	 * @param model
	 *            JournalDesContributionsModel
	 */
	public RoomCalandarView(RoomCalandarController controller,
			RoomCalandarModel model) {
		super(controller, model);
		init();
	}

	/**
	 * Overloading methods
	 * 
	 * Instantiates all the widgets on the screen.
	 * 
	 */
	protected void instanciate() {
		mainPanel = new FieldPanel();
		monthLabel = new ZLabel(getRoomCalandarModel().getCurrentMonth() + " " + getRoomCalandarModel().getYear());
	}

	@Override
	protected void compose() {
		setLayout(new BorderLayout());
		//m_mainPanel.addComponent(m_tabJournalPerYearPanel);
		mainPanel.addComponent(monthLabel);
		add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * Getting RoomCalandarController
	 * 
	 * @return RoomCalandarController
	 */
	public RoomCalandarController getRoomCalandarController() {
		return (RoomCalandarController) getController();
	}

	/**
	 * Getting RoomCalandarModel
	 * 
	 * @return RoomCalandarModel
	 */
	public RoomCalandarModel getRoomCalandarModel() {
		return (RoomCalandarModel) getModel();
	}
}
