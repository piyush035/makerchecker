package com.hotel.client.add.room;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import com.hotel.base.common.domain.room.dto.RoomDto;
import com.hotel.client.add.room.constants.RoomConstants;
import com.hotel.client.common.message.CommonConstants;
import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpEvent;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpListener;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpManager;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpPanel;
import com.rennover.client.framework.valueobject.model.ValueObjectModel;
import com.rennover.client.framework.valueobject.model.ValueObjectModelEvent;
import com.rennover.client.framework.valueobject.model.ValueObjectModelListener;
import com.rennover.client.framework.valueobject.widget.VOPropertyComboBox;
import com.rennover.client.framework.valueobject.widget.VOPropertyTextField;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.field.ValueObjectComboBoxField;
import com.rennover.client.framework.widget.panel.FieldPanel;
import com.rennover.client.framework.widget.panel.TablePanel;
import com.rennover.client.framework.widget.table.ValueObjectListTable;

/**
 * @author Piyush
 */
public class RoomView extends PanelView implements InstantHelpListener, RoomConstants {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 505723223841714154L;
	
	/** */
	private VOPropertyTextField roomNameTf;
	
	/** */
	private VOPropertyTextField roomNumberTf;
	
	/** */
	private VOPropertyComboBox roomTypeCb;
	
	/** */
	private VOPropertyTextField roomNameMTf;
	
	/** */
	private VOPropertyTextField roomNumberMTf;
	
	/** */
	private ValueObjectComboBoxField roomTypeMCb;
	
	/** */
	private TablePanel tbPanel;
	
	/** */
	private ValueObjectListTable searchedRoom;
	
	/** */
	private FieldPanel mainPanel;
	
	/** */
	private FieldPanel roomSearchPanel;
	
	/** */
	private FieldPanel roomInfoPanel;
	
	/** */
	private FieldPanel leftPanel;
	
	/** */
	private FieldPanel rightPanel;
	
	/** */
	private InstantHelpPanel helpPanel;
	
	private InstantHelpManager instantHelpManager;
	
	/**
	 * Default Construtor
	 */
	public RoomView() {
		super();
		init();
	}
	
	/**
	 * @param controller RoomCalandarController
	 * @param model RoomCalandarModel
	 */
	public RoomView(RoomController controller, RoomModel model) {
		super("Add/Modify Rooms", controller, model);
		init();
	}
	
	/**
	 * Overloading methods Instantiates all the widgets on the screen.
	 */
	protected void instanciate() {
		ValueObjectModel vom = getRoomModel().getValueObjectModel(RoomModel.SEARCH_CRITERIA_ROOM_MODEL);
		
		roomNameTf = new VOPropertyTextField(getRoomModel().getSearchRoomModel(), RoomDto.ROOM_NAME);
		roomNameTf.setColumns(10);
		
		roomTypeCb = new VOPropertyComboBox(getRoomModel().getSearchRoomModel(), RoomDto.ROOM_TYPE_ID, getRoomModel().getValueObjectListModel(
				RoomModel.SEARCH_CRITERIA_ROOM_TYPE_MODEL).getValueObjectList(), true);
		roomTypeCb.setColumns(10);
		
		roomNumberTf = new VOPropertyTextField(getRoomModel().getSearchRoomModel(), RoomDto.ROOM_NUMBER);
		roomNumberTf.setColumns(10);
		
		roomNameMTf = new VOPropertyTextField(getRoomModel().getRoomModel(), RoomDto.ROOM_NAME);
		roomNameMTf.setColumns(10);
		
		roomNumberMTf = new VOPropertyTextField(getRoomModel().getRoomModel(), RoomDto.ROOM_NUMBER);
		roomNumberMTf.setColumns(10);
		
		tbPanel = new TablePanel(CommonConstants.SEARCH_RESULT_PANEL, true, true);
		searchedRoom = new ValueObjectListTable(getRoomModel().getValueObjectModel(RoomModel.SEARCHED_ROOM_MODEL),
				getRoomModel().getValueObjectListModel(RoomModel.SEARCHED_ROOM_MODEL), new RoomTableRowModel());
		tbPanel.setTable(searchedRoom, 10);
		
		getRoomModel().getValueObjectModel(
				RoomModel.SEARCHED_ROOM_MODEL).addValueObjectModelListener(
		        new ValueObjectModelListener()
		        {
			        public void valueObjectChanged(ValueObjectModelEvent event)
			        {
				        getRoomController().doSelectRoom();

			        }

			        public void valueObjectPropertyChanged(ValueObjectModelEvent event)
			        {
			        }
		        });

		
		roomSearchPanel = new FieldPanel();
		roomInfoPanel = new FieldPanel(ROOM_INFO_PANEL);
		leftPanel = new FieldPanel();
		rightPanel = new FieldPanel(ROOM_INFO_PANEL);
		mainPanel = new FieldPanel();
		
		helpPanel = new InstantHelpPanel();
		instantHelpManager = new InstantHelpManager(helpPanel);
		instantHelpManager.addValueObjectModel(getRoomModel().getRoomModel());
		instantHelpManager.addInstantHelpListener(getRoomController());
	}
	
	@Override
	protected void compose() {
		
		setLayout(new BorderLayout());
		
		ZPanel mainZpanel = new ZPanel(new GridLayout(1, 2));
		roomSearchPanel.addComponent(ROOM_NUMBER, roomNumberTf, null, '\0', ROOM_NAME, roomNameTf, null, '\0',
				ROOM_TYPE, roomTypeCb, null, '\0', false);
		roomSearchPanel.addButtonAction(getRoomController().getAction(RoomController.ACTION_ROOM_SEARCH), false);
		leftPanel.addComponent(roomSearchPanel);
		leftPanel.addComponent(tbPanel);		
		leftPanel.addButtonAction(getRoomController().getAction(RoomController.ACTION_ADD_ROOM), false);
		leftPanel.addButtonAction(getRoomController().getAction(RoomController.ACTION_MODIFY_ROOM), false);
		rightPanel.addComponent(ROOM_NUMBER, roomNameMTf, false);
		rightPanel.addComponent(ROOM_TYPE, roomNumberMTf, false);
		
		mainZpanel.add(leftPanel);
		mainZpanel.add(rightPanel);
		
		mainPanel.addComponent(mainZpanel);
		
		add(mainPanel, BorderLayout.CENTER);
		
		setInstantHelpManager(instantHelpManager);
	}
	
	@Override
	public void validityChanged(InstantHelpEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void valueChanged(InstantHelpEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public RoomController getRoomController() {
		return (RoomController) getController();
	}
	
	public RoomModel getRoomModel() {
		return (RoomModel) getModel();
	}
	
	public void enableFields() {}
	
	public void disbaleFields() {}
}
