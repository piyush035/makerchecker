package com.hotel.client.add.room;

import com.hotel.base.common.domain.room.dto.RoomDto;
import com.hotel.base.common.domain.roomtype.dto.RoomTypeDto;
import com.hotel.common.facade.FacadeFactory;
import com.rennover.client.framework.mvc.ActionDescription;
import com.rennover.client.framework.mvc.PanelController;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpEvent;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpListener;

public class RoomController extends PanelController implements InstantHelpListener {
	
	/** */
	public static final ActionDescription ACTION_ROOM_SEARCH = new ActionDescription("Search", "doSearch", null,
			"Search to get rooms.");
	
	/** */
	public static final ActionDescription ACTION_ADD_ROOM = new ActionDescription("Create", "doAddRoom", null,
			"Add new room.");
	
	/** */
	public static final ActionDescription ACTION_MODIFY_ROOM = new ActionDescription("Modify", "doModifyRoom", null,
			"Add new room.");
	
	/** */
	public static final ActionDescription ACTION_SUBMIT = new ActionDescription("Submit", "doSubmit", null,
			"Add new room.");
	
	/** */
	public static final ActionDescription ACTION_CANCEL = new ActionDescription("Cancel", "doCancel", null,
			"Cancel to add room.");
	
	/**
	 * 
	 * 
	 */
	public RoomController() {
		init();
	}
	
	/**
	 * 
	 * 
	 */
	public RoomController(boolean isForModification) {
		init();
	}
	
	/**
	 * @param parentController
	 */
	public RoomController(PanelController parentController) {
		super(parentController);
		init();
	}
	
	/**
	 * @return
	 */
	public RoomModel getRoomModel() {
		return (RoomModel) this.getModel();
	}
	
	/**
	 * @return
	 */
	public RoomView getRoomView() {
		return (RoomView) this.getView();
	}
	
	/**
	 * 
	 */
	protected void checkPreconditions() {

		//RolesDto rolesDto = UserContextManager.getUserContext().getUserLogin().getRoleDto();
		
	}
	
	@Override
	protected void createModel() {
		this.setModel(new RoomModel());
		
	}
	
	/**
	 * 
	 */
	@Override
	protected void retrieveInitialData() {
		getRoomModel().getValueObjectListModel(RoomModel.SEARCH_CRITERIA_ROOM_TYPE_MODEL).setValueObjectList(
				FacadeFactory.getFacadeRoomType().getRoomTypeAll());
	}
	
	@Override
	protected void createView() {
		this.setView(new RoomView(this, getRoomModel()));
	}
	
	@Override
	public void validityChanged(InstantHelpEvent e) {

	}
	
	@Override
	public void valueChanged(InstantHelpEvent e) {

	}
	
	/**
	 * Check that user name availability.
	 */
	public void doCheckAvailability() {}
	
	public void doAddRoom() {

	}
	
	public void doSearch() {
		RoomDto roomDto = 
			(RoomDto) getRoomModel().getSearchRoomModel().getWorkingValueObject();
		roomDto.getRoomTypeId().getIdValue();
		//roomDto.setr//
		//RoomTypeDto roomTypeDto = getRoomModel().getValueObjectListModel(RoomModel.SEARCH_CRITERIA_ROOM_TYPE_MODEL).s
		getRoomModel().getValueObjectListModel(RoomModel.SEARCHED_ROOM_MODEL).setValueObjectList(
				FacadeFactory.getFacadeRoom().getRoomsWithCriteria(roomDto));
	}
	
	/**
	 * On Select room this method will upload data to right panel.
	 */
	public void doSelectRoom() {
		getRoomModel().setRoomModel(
				(RoomDto) getRoomModel().getValueObjectModel(RoomModel.SEARCHED_ROOM_MODEL).getWorkingValueObject());
	}
	/**
	 * 
	 */
	public void doModifyRoom() {

	}
	/**
	 * 
	 */
	public void doSubmit() {

	}
	/**
	 * 
	 */
	public void doCancel() {
		getRoomView().close();
	}
	
}