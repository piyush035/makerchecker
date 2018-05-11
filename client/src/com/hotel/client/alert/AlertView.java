package com.hotel.client.alert;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import com.hotel.base.common.domain.alert.dto.AlertDto;
import com.hotel.client.add.alert.constants.AlertConstants;
import com.hotel.client.create.user.constants.UserConstants;
import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpEvent;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpListener;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpManager;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpPanel;
import com.rennover.client.framework.valueobject.widget.VOPropertyTextField;
import com.rennover.client.framework.widget.field.ValueObjectComboBoxField;
import com.rennover.client.framework.widget.panel.FieldPanel;

/**
 * @author Piyush
 */
public class AlertView extends PanelView implements InstantHelpListener, AlertConstants {
	
	/** */
	private VOPropertyTextField alertTypeTf;
	
	/** */
	private ValueObjectComboBoxField alertNameCb;
	
	/** */
	private FieldPanel mainPanel;
	
	/** */
	private FieldPanel alertInfoPanel;
	
	/** */
	//private FieldPanel presentAddPanel;
	
	/** */
	//private FieldPanel permanentAddPanel;
	
	/** */
	private InstantHelpPanel helpPanel;
	
	private InstantHelpManager instantHelpManager;
	
	public AlertView() {
		
		super();
		init();
	}
	
	/**
	 * @param controller RoomCalandarController
	 * @param model RoomCalandarModel
	 */
	public AlertView(AlertController controller, AlertModel model) {
		super(controller, model);
		init();
	}
	
	/**
	 * Overloading methods Instantiates all the widgets on the screen.
	 */
	protected void instanciate() {
		
		alertTypeTf = new VOPropertyTextField(getAlertModel().getAlertModel(), AlertDto.ALERT_TYPE);
		alertTypeTf.setColumns(20);
		
		List<String> list = new ArrayList<String>();
		alertNameCb = new ValueObjectComboBoxField(list);
		alertNameCb.setColumns(20);
		alertNameCb.setEnabled(false);
		
		alertInfoPanel = new FieldPanel(ALERT_INFO_PANEL);
		mainPanel = new FieldPanel();
		
		helpPanel = new InstantHelpPanel();
		instantHelpManager = new InstantHelpManager(helpPanel);
		instantHelpManager.addValueObjectModel(getAlertModel().getAlertModel());
		instantHelpManager.addInstantHelpListener(getAlertController());
		
	}
	
	@Override
	protected void compose() {
		
		setLayout(new BorderLayout());
		
		alertInfoPanel.addComponent(ALERT_TYPE, alertTypeTf, null, '\0', ALERT_NAME, alertNameCb, null, '\0', false);
		
		mainPanel.addComponent(alertInfoPanel);
		//mainPanel.addButtonAction(getAlertController().getAction(AlertController.ACTION_ADD_ROOM), false);
		//mainPanel.addButtonAction(getAlertController().getAction(AlertController.ACTION_CANCEL), false);
		
		add(mainPanel, BorderLayout.CENTER);
		
		setInstantHelpManager(instantHelpManager);
	}
	
	//private void composePermanentAddress() {}
	
	//private void composePresentAddress() {}
	
	@Override
	public void validityChanged(InstantHelpEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void valueChanged(InstantHelpEvent e) {

	}
	
	public AlertController getAlertController() {
		return (AlertController) getController();
	}
	
	public AlertModel getAlertModel() {
		return (AlertModel) getModel();
	}
	
	public void enableFields() {}
	
	public void disbaleFields() {}
}