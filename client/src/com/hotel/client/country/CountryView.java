package com.hotel.client.country;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import com.hotel.base.common.domain.country.dto.CountryDto;
import com.hotel.client.common.message.CommonConstants;
import com.hotel.client.configuration.constant.ConfigurationConstants;
import com.rennover.client.framework.mvc.PanelView;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpEvent;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpListener;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpManager;
import com.rennover.client.framework.valueobject.instanthelp.InstantHelpPanel;
import com.rennover.client.framework.valueobject.widget.VOPropertyNumericField;
import com.rennover.client.framework.valueobject.widget.VOPropertyTextField;
import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.panel.FieldPanel;
import com.rennover.client.framework.widget.panel.TablePanel;
import com.rennover.client.framework.widget.table.ValueObjectListTable;

/**
 * @author Piyush
 */
public class CountryView extends PanelView implements InstantHelpListener, ConfigurationConstants {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 505723223841714154L;
	
	/** */
	private VOPropertyTextField countryNameTf;
	
	/** */
	private VOPropertyNumericField countryCodeTf;
	
	/** */
	private VOPropertyTextField countryNameMTf;
	
	/** */
	private VOPropertyNumericField countryCodeMNf;
	
	/** */
	private FieldPanel mainPanel;
	
	/** */
	private FieldPanel countrySearchPanel;
	
	/** */
	private FieldPanel searchResultPanel;
	
	/** */
	private FieldPanel leftPanel;
	
	/** */
	private FieldPanel rightPanel;
	
	private TablePanel tbPanel;
	
	private ValueObjectListTable searchedCountry;
	
	/** */
	private InstantHelpPanel helpPanel;
	
	private InstantHelpManager instantHelpManager;
	
	/**
	 * Default Construtor
	 */
	public CountryView() {
		super();
		init();
	}
	
	/**
	 * @param controller RoomCalandarController
	 * @param model RoomCalandarModel
	 */
	public CountryView(CountryController controller, CountryModel model) {
		super(controller, model);
		init();
	}
	
	/**
	 * Overloading methods Instantiates all the widgets on the screen.
	 */
	protected void instanciate() {
		countryNameTf = new VOPropertyTextField(getCountryModel().getSearchCountryModel(), CountryDto.COUNTRY_NAME);
		countryNameTf.setColumns(20);
		
		countryCodeTf = new VOPropertyNumericField(getCountryModel().getSearchCountryModel(), CountryDto.COUNTRY_CODE);
		countryCodeTf.setColumns(20);
		
		countryNameMTf = new VOPropertyTextField(getCountryModel().getCountryModel(), CountryDto.COUNTRY_NAME);
		countryNameMTf.setColumns(20);
		countryNameMTf.setEnabled(false);
		
		countryCodeMNf = new VOPropertyNumericField(getCountryModel().getCountryModel(), CountryDto.COUNTRY_CODE);
		countryCodeMNf.setColumns(20);
		countryCodeMNf.setEnabled(false);
		
		tbPanel = new TablePanel(CommonConstants.SEARCH_RESULT_PANEL, true, true);
		searchedCountry = new ValueObjectListTable(getCountryModel().getValueObjectModel(
				CountryModel.SELECTED_COUNTRY_MODEL), getCountryModel().getValueObjectListModel(
				CountryModel.SELECTED_COUNTRY_MODEL), new CountryTableRowModel());
		tbPanel.setTable(searchedCountry, 10); 
		
		countrySearchPanel = new FieldPanel(SEARCH_PANEL);
		searchResultPanel = new FieldPanel();
		leftPanel = new FieldPanel();
		rightPanel = new FieldPanel(COUNTRY_INFO);
		mainPanel = new FieldPanel();
		
		helpPanel = new InstantHelpPanel();
		instantHelpManager = new InstantHelpManager(helpPanel);
		instantHelpManager.addValueObjectModel(getCountryModel().getSearchCountryModel());
		instantHelpManager.addInstantHelpListener(getCountryController());
	}
	
	@Override
	protected void compose() {
		setLayout(new BorderLayout());
		
		ZPanel mainZpanel = new ZPanel(new GridLayout(1, 2));
		countrySearchPanel.addComponent(COUNTRY_NAME, countryNameTf, null, '\0', COUNTRY_CODE, countryCodeTf, null,
				'\0', false);
		countrySearchPanel.addButtonAction(getCountryController().getAction(CountryController.ACTION_COUNTRY_SEARCH),
				false);
		searchResultPanel.addComponent(tbPanel);
		leftPanel.addComponent(countrySearchPanel);
		leftPanel.addComponent(tbPanel);
		
		
		rightPanel.addComponent(COUNTRY_NAME, countryNameMTf, false);
		rightPanel.addComponent(COUNTRY_CODE, countryCodeMNf, false);
		
		
		mainZpanel.add(leftPanel);
		mainZpanel.add(rightPanel);
		/*
		 * roomInfoPanel.addButtonAction(getCreateUserController().getAction(RoomController.ACTION_CHECK_AVAILABILITY),
		 * false); getCreateUserController().getAction(RoomController.ACTION_CHECK_AVAILABILITY).setEnabled(false);
		 */

		mainPanel.addComponent(mainZpanel);
		//mainPanel.addButtonAction(getRoomController().getAction(RoomController.ACTION_ADD_ROOM), false);
		//mainPanel.addButtonAction(getCountryController().getAction(CountryController.ACTION_CANCEL), false);
		//add(userLoginPanel, BorderLayout.NORTH);
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
	
	public CountryController getCountryController() {
		return (CountryController) getController();
	}
	
	public CountryModel getCountryModel() {
		return (CountryModel) getModel();
	}
	
	public void enableFields() {}
	
	public void disbaleFields() {}
}
