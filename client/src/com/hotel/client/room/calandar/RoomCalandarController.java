package com.hotel.client.room.calandar;

import java.util.Calendar;

import com.hotel.base.common.domain.roles.dto.RolesDto;
import com.hotel.client.common.security.UserContextManager;
import com.rennover.client.framework.mvc.PanelController;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpEvent;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpListener;

/**
 * 
 * @author Piyush
 * 
 */
public class RoomCalandarController extends PanelController implements
		InstantHelpListener {

	/**
	 * 
	 * 
	 */
	public RoomCalandarController() {
		init();
	}

	/**
	 * 
	 * @param parentController
	 * 
	 */
	public RoomCalandarController(PanelController parentController) {
		super(parentController);
		init();
	}

	/**
	 * 
	 * @return
	 */
	public RoomCalandarModel getRoomCalandarModel() {
		return (RoomCalandarModel) this.getModel();
	}

	/**
	 * 
	 * @return
	 */
	public RoomCalandarView getRoomCalandarView() {
		return (RoomCalandarView) this.getView();
	}

	/**
	 * 
	 */
	protected void checkPreconditions() {
		/*RolesDto rolesDto = UserContextManager.getUserContext().getUserLogin()
				.getRoleDto();*/

	}

	@Override
	protected void createModel() {
		this.setModel(new RoomCalandarModel());

	}

	@Override
	protected void retrieveInitialData() {
		Calendar calendar = Calendar.getInstance();
		getRoomCalandarModel().setCurrentMonth(calendar.get(Calendar.MONTH));
		getRoomCalandarModel().setYear(calendar.get(Calendar.YEAR));
	}

	@Override
	protected void createView() {
		this.setView(new RoomCalandarView(this, getRoomCalandarModel()));
	}

	@Override
	public void validityChanged(InstantHelpEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valueChanged(InstantHelpEvent e) {
		// TODO Auto-generated method stub

	}
}