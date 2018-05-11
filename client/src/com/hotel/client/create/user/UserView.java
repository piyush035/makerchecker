package com.hotel.client.create.user;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import com.hotel.base.common.domain.address.dto.AddressDto;
import com.hotel.base.common.domain.city.dto.CityDto;
import com.hotel.base.common.domain.user.dto.UserInformationDto;
import com.hotel.base.common.domain.user.dto.UserLoginDto;
import com.hotel.client.create.user.constants.UserConstants;
import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpEvent;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpListener;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpManager;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpPanel;
import com.rennover.client.framework.valueobject.model.ValueObjectModelEvent;
import com.rennover.client.framework.valueobject.model.ValueObjectModelListener;
import com.rennover.client.framework.valueobject.widget.VOPropertyNumericField;
import com.rennover.client.framework.valueobject.widget.VOPropertyTextField;
import com.rennover.client.framework.widget.base.ZLabel;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.field.ValueObjectComboBoxField;
import com.rennover.client.framework.widget.panel.FieldPanel;

/**
 * @author Piyush
 */
public class UserView extends PanelView implements InstantHelpListener, UserConstants {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 505723223841714154L;

	/** */
	private VOPropertyTextField firstNameTf;
	
	
	/** */
	private VOPropertyTextField lastNameTf;
	
	/** */
	private VOPropertyTextField userNameTF;
	
	/** */
	private VOPropertyTextField passwordTF;
	
	/** */
	private VOPropertyTextField emailTf;
	
	/** */
	private VOPropertyNumericField mobileNf;
	
	/** */
	private ValueObjectComboBoxField roleCb;
	
	/** */
	private VOPropertyTextField line1PresentTf;
	
	/** */
	private VOPropertyTextField line2PresentTf;
	
	/** */
	private VOPropertyTextField line3PresentTf;
	
	/** */
	private VOPropertyTextField cityPresentTf;
	
	/** */
	private VOPropertyNumericField pincodePresentTf;
	
	/** */
	private ValueObjectComboBoxField statePresentCb;
	
	/** */
	private ValueObjectComboBoxField countryPresentCb;
	
	/** */
	private VOPropertyTextField line1PermaTf;
	
	/** */
	private VOPropertyTextField line2PermaTf;
	
	/** */
	private VOPropertyTextField line3PermaTf;
	
	/** */
	private VOPropertyTextField cityPermaTf;
	
	/** */
	private VOPropertyNumericField pincodePermaTf;
	
	/** */
	private ValueObjectComboBoxField statePermaCb;
	
	/** */
	private ValueObjectComboBoxField countryPermaCb;
	
	/** */
	private FieldPanel userLoginPanel;
	
	/** */
	private FieldPanel mainPanel;
	
	/** */
	private FieldPanel userInfoPanel;
	
	/** */
	private FieldPanel presentAddPanel;
	
	/** */
	private FieldPanel permanentAddPanel;
	
	/** */
	private InstantHelpPanel helpPanel;
	
	private InstantHelpManager instantHelpManager;
	
	/**
	 * Default Construtor
	 */
	public UserView() {
		super();
		init();
	}
	
	/**
	 * @param controller RoomCalandarController
	 * @param model RoomCalandarModel
	 */
	public UserView(UserController controller, UserModel model) {
		super(controller, model);
		init();
	}
	
	/**
	 * Overloading methods Instantiates all the widgets on the screen.
	 */
	protected void instanciate() {
		userNameTF = new VOPropertyTextField(getUserModel().getUserLoginModel(), UserLoginDto.USER_NAME);
		userNameTF.setColumns(20);
		
		passwordTF = new VOPropertyTextField(getUserModel().getUserLoginModel(), UserLoginDto.PASSWORD);
		passwordTF.setColumns(20);
		passwordTF.setEditable(false);
		
		getUserModel().getUserLoginModel().addValueObjectModelListener(UserLoginDto.USER_NAME,
				new ValueObjectModelListener() {
					@Override
					public void valueObjectPropertyChanged(ValueObjectModelEvent event) {
						disbaleFields();
						getCreateUserController().getAction(UserController.ACTION_CHECK_AVAILABILITY).setEnabled(
								event.isPropertyValid());
					}
					
					@Override
					public void valueObjectChanged(ValueObjectModelEvent event) {
						// TODO Auto-generated method stub
						
					}
				});
		firstNameTf = new VOPropertyTextField(getUserModel().getUserInfoModel(), UserInformationDto.FIRST_NAME);
		firstNameTf.setColumns(20);
		firstNameTf.setEditable(false);
		
		lastNameTf = new VOPropertyTextField(getUserModel().getUserInfoModel(), UserInformationDto.LAST_NAME);
		lastNameTf.setColumns(20);
		lastNameTf.setEditable(false);
		
		emailTf = new VOPropertyTextField(getUserModel().getUserInfoModel(), UserInformationDto.EMAIL);
		emailTf.setColumns(20);
		emailTf.setEditable(false);
		
		mobileNf = new VOPropertyNumericField(getUserModel().getUserInfoModel(), UserInformationDto.MOBILE_NUMBER);
		mobileNf.setColumns(20);
		mobileNf.setEditable(false);
		
		roleCb = new ValueObjectComboBoxField(getUserModel().getRolesListModel());
		roleCb.setColumns(20);
		roleCb.setEnabled(false);		
		
		// Present address
		line1PresentTf = new VOPropertyTextField(getUserModel().getAddressPresentModel(), AddressDto.LINE_1);
		line1PresentTf.setColumns(40);
		line1PresentTf.setEditable(false);
		
		line2PresentTf = new VOPropertyTextField(getUserModel().getAddressPresentModel(), AddressDto.LINE_2);
		line2PresentTf.setColumns(40);
		line2PresentTf.setEditable(false);
		
		line3PresentTf = new VOPropertyTextField(getUserModel().getAddressPresentModel(), AddressDto.LINE_3);
		line3PresentTf.setColumns(40);
		line3PresentTf.setEditable(false);
		
		cityPresentTf = new VOPropertyTextField(getUserModel().getCityPresentModel(), CityDto.CITY_NAME);
		cityPresentTf.setColumns(20);
		cityPresentTf.setEditable(false);
		
		pincodePresentTf = new VOPropertyNumericField(getUserModel().getAddressPresentModel(), AddressDto.POSTAL_CODE_ID);
		pincodePresentTf.setColumns(20);
		pincodePresentTf.setEditable(false);
		
		//statePresentCb = new VOPropertyComboBox(getCreateUserModel().getStateListModel(), getCreateUserModel().getStateList());
		statePresentCb = new ValueObjectComboBoxField(getUserModel().getStateList());
		statePresentCb.setColumns(20);
		statePresentCb.setEditable(false);
		
		countryPresentCb = new ValueObjectComboBoxField(getUserModel().getCountryList());
		countryPresentCb.setColumns(20);
		countryPresentCb.setEditable(false);
		// Present Address ends
		
		// permanent address
		line1PermaTf = new VOPropertyTextField(getUserModel().getAddressPermanentModel(), AddressDto.LINE_1);
		line1PermaTf.setColumns(40);
		line1PermaTf.setEditable(false);
		
		line2PermaTf = new VOPropertyTextField(getUserModel().getAddressPermanentModel(), AddressDto.LINE_2);
		line2PermaTf.setColumns(40);
		line2PermaTf.setEditable(false);
		
		line3PermaTf = new VOPropertyTextField(getUserModel().getAddressPermanentModel(), AddressDto.LINE_3);
		line3PermaTf.setColumns(40);
		line3PermaTf.setEditable(false);
		
		cityPermaTf = new VOPropertyTextField(getUserModel().getCityPermanentModel(), CityDto.CITY_NAME);
		cityPermaTf.setColumns(20);
		cityPermaTf.setEditable(false);
		
		pincodePermaTf = new VOPropertyNumericField(getUserModel().getAddressPermanentModel(), AddressDto.POSTAL_CODE_ID);
		pincodePermaTf.setColumns(20);
		pincodePermaTf.setEditable(false);
		
		//statePresentCb = new VOPropertyComboBox(getCreateUserModel().getStateListModel(), getCreateUserModel().getStateList());
		statePermaCb = new ValueObjectComboBoxField(getUserModel().getStateList());
		statePermaCb.setColumns(20);
		statePermaCb.setEditable(false);
		
		countryPermaCb = new ValueObjectComboBoxField(getUserModel().getCountryList());
		countryPermaCb.setColumns(20);
		countryPermaCb.setEditable(false);
		// Permanent Address ends
		
		userLoginPanel = new FieldPanel(USER_PANEL_TITLE);
		userInfoPanel = new FieldPanel(USER_INFO_PANEL_TITLE);
		presentAddPanel = new FieldPanel(ADDRESS_PRESENT_PANEL);
		permanentAddPanel = new FieldPanel(ADDRESS_PERMANENT_PANEL);
		mainPanel = new FieldPanel();
		
		helpPanel = new InstantHelpPanel();
		instantHelpManager = new InstantHelpManager(helpPanel);
		instantHelpManager.addValueObjectModel(getUserModel().getUserLoginModel());
		instantHelpManager.addInstantHelpListener(getCreateUserController());
	}
	
	@Override
	protected void compose() {
		setLayout(new BorderLayout());
		ZPanel panel = new ZPanel(new GridLayout(2, 6));
		panel.add(new ZLabel(FIRST_NAME));
		panel.add(firstNameTf);
		panel.add(new ZLabel(""));
		panel.add(new ZLabel(""));
		panel.add(new ZLabel(LAST_NAME));
		panel.add(lastNameTf);
		// New Line
		panel.add(new ZLabel(EMAIL));
		panel.add(emailTf);
		panel.add(new ZLabel(""));
		panel.add(new ZLabel(""));
		panel.add(new ZLabel(MOBILE_NUMBER));
		panel.add(mobileNf);
		userLoginPanel.addComponent(USER_LOGIN_NAME, userNameTF, null, '\0', USER_PASSWORD, passwordTF, null, '\0',
				USER_ROLE, roleCb, null, '\0', false);
		userLoginPanel.addButtonAction(getCreateUserController().getAction(UserController.ACTION_CHECK_AVAILABILITY),
				false);
		getCreateUserController().getAction(UserController.ACTION_CHECK_AVAILABILITY).setEnabled(false);
		
		composePresentAddress();		
		composePermanentAddress();
		
		userInfoPanel.addComponent(panel);
		userInfoPanel.addComponent(presentAddPanel);
		userInfoPanel.addComponent(permanentAddPanel);		
		
		mainPanel.addComponent(userLoginPanel);
		mainPanel.addComponent(userInfoPanel);
		mainPanel.addButtonAction(getCreateUserController().getAction(UserController.ACTION_CREATE_USER),
				false);
		mainPanel.addButtonAction(getCreateUserController().getAction(UserController.ACTION_CANCEL),
				false);
		//add(userLoginPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		
		setInstantHelpManager(instantHelpManager);
	}
	
	private void composePermanentAddress() {
		// Permanent Address Panel
		permanentAddPanel.addComponent(ADDRESS_LINE_1, line1PermaTf);
		permanentAddPanel.addComponent(ADDRESS_LINE_2, line2PermaTf);
		permanentAddPanel.addComponent(ADDRESS_LINE_3, line3PermaTf);
		
		ZPanel permaAddpanel = new ZPanel(new GridLayout(2, 6));
		permaAddpanel.add(new ZLabel(CITY));
		permaAddpanel.add(cityPermaTf);
		permaAddpanel.add(new ZLabel(""));
		permaAddpanel.add(new ZLabel(""));
		permaAddpanel.add(new ZLabel(POSTAL_CODE));
		permaAddpanel.add(pincodePermaTf);
		// New Line
		permaAddpanel.add(new ZLabel(STATE));
		permaAddpanel.add(statePermaCb);
		permaAddpanel.add(new ZLabel(""));
		permaAddpanel.add(new ZLabel(""));
		permaAddpanel.add(new ZLabel(COUNTRY));
		permaAddpanel.add(countryPermaCb);
		
		permanentAddPanel.addComponent(permaAddpanel);
	}
	
	private void composePresentAddress() {
		// Present Address Panel
		presentAddPanel.addComponent(ADDRESS_LINE_1, line1PresentTf);
		presentAddPanel.addComponent(ADDRESS_LINE_2, line2PresentTf);
		presentAddPanel.addComponent(ADDRESS_LINE_3, line3PresentTf);
		
		ZPanel preAddpanel = new ZPanel(new GridLayout(2, 6));
		preAddpanel.add(new ZLabel(CITY));
		preAddpanel.add(cityPresentTf);
		preAddpanel.add(new ZLabel(""));
		preAddpanel.add(new ZLabel(""));
		preAddpanel.add(new ZLabel(POSTAL_CODE));
		preAddpanel.add(pincodePresentTf);
		// New Line
		preAddpanel.add(new ZLabel(STATE));
		preAddpanel.add(statePresentCb);
		preAddpanel.add(new ZLabel(""));
		preAddpanel.add(new ZLabel(""));
		preAddpanel.add(new ZLabel(COUNTRY));
		preAddpanel.add(countryPresentCb);
		
		presentAddPanel.addComponent(preAddpanel);
		// End Present Address
	}
	
	@Override
	public void validityChanged(InstantHelpEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void valueChanged(InstantHelpEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public UserController getCreateUserController() {
		return (UserController) getController();
	}
	
	public UserModel getUserModel() {
		return (UserModel) getModel();
	}
	
	public void enableFields() {
		passwordTF.setEditable(true);
		firstNameTf.setEditable(true);
		lastNameTf.setEditable(true);
		mobileNf.setEditable(true);
		emailTf.setEditable(true);
		roleCb.setEnabled(true);
		line1PresentTf.setEditable(true);
		line2PresentTf.setEditable(true);
		line3PresentTf.setEditable(true);
		cityPresentTf.setEditable(true);
		pincodePresentTf.setEditable(true);
		statePresentCb.setEnabled(true);
		countryPresentCb.setEnabled(true);
		line1PermaTf.setEditable(true);
		line2PermaTf.setEditable(true);
		line3PermaTf.setEditable(true);
		cityPermaTf.setEditable(true);
		pincodePermaTf.setEditable(true);
		statePermaCb.setEnabled(true);
		countryPermaCb.setEnabled(true);
		
	}
	
	public void disbaleFields() {
		passwordTF.setEditable(false);
		firstNameTf.setEditable(false);
		lastNameTf.setEditable(false);
		mobileNf.setEditable(false);
		emailTf.setEditable(false);
		roleCb.setEnabled(false);
		line1PresentTf.setEditable(false);
		line2PresentTf.setEditable(false);
		line3PresentTf.setEditable(false);
		cityPresentTf.setEditable(false);
		pincodePresentTf.setEditable(false);
		statePresentCb.setEnabled(false);
		countryPresentCb.setEnabled(false);
		line1PermaTf.setEditable(false);
		line2PermaTf.setEditable(false);
		line3PermaTf.setEditable(false);
		cityPermaTf.setEditable(false);
		pincodePermaTf.setEditable(false);
		statePermaCb.setEnabled(false);
		countryPermaCb.setEnabled(false);
	}
}
