package com.rennover.client.framework.widget.panel;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JComponent;

import com.rennover.client.framework.widget.base.ZPanel;
import com.rennover.client.framework.widget.base.ZSplitPane;

/**
 * @author Piyush
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
public class OrganisedPanel extends ZPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8480502560374335729L;

	public static final int STYLE_2LEFT_1RIGHT = 1;

	public static final int STYLE_2TOP_1BOTTOM = 2;

	public static final int STYLE_1LEFT_2RIGHT = 3;

	public static final int STYLE_1TOP_2BOTTOM = 4;

	public static final int STYLE_3CENTER = 5;

	public static final int STYLE_4PANELS = 6;

	public static final int STYLE_1LEFT_1RIGHT = 7;

	public static final int STYLE_1TOP_1BOTTOM = 8;

	public static final int STYLE_1LEFT_1RIGHT_LEFT_ALGINED = 9;

	private ZSplitPane m_mainPanel = new ZSplitPane();

	private ZSplitPane m_subPanel = new ZSplitPane();
	
	private JComponent firstPanel;
	
	private JComponent secondPanel;
	/**
	 * 
	 * @param style
	 * @param panel1
	 * @param panel2
	 * @param useSplitters
	 */
	public OrganisedPanel(int style, JComponent panel1, JComponent panel2,
			boolean useSplitters) {
		super();		
		firstPanel = panel1;
		secondPanel = panel2;
		Component panel = composeSplitpane(style, panel1, panel2);

		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
	}

	public OrganisedPanel(int style, JComponent panel1, JComponent panel2,
			JComponent panel3, boolean useSplitters) {
		super();

		Component panel = composeSplitpane(style, panel1, panel2, panel3);

		setLayout(new BorderLayout());
		add(panel, BorderLayout.CENTER);
	}

	public OrganisedPanel(JComponent panel1, JComponent panel2,
			JComponent panel3, JComponent panel4, boolean useSplitters) {
		super();
	}

	public void setDividerLocation(double mainSplitWeight) {
		m_mainPanel.setDividerLocation(mainSplitWeight);
		this.validate();
		this.repaint();
	}

	public void setDividerLocation(double mainSplitWeight, double subSplitWeight) {
		m_mainPanel.setDividerLocation(mainSplitWeight);
		m_subPanel.setDividerLocation(subSplitWeight);
		this.validate();
		this.repaint();
	}

	private ZSplitPane composeSplitpane(int style, JComponent panel1,
			JComponent panel2) {
		ZSplitPane mainPanel = m_mainPanel;
		mainPanel.setLayout(new BorderLayout());
		//mainPanel.setOneTouchExpandable(true);

		switch (style) {
		case STYLE_1LEFT_1RIGHT:
			mainPanel.setOrientation(ZSplitPane.HORIZONTAL_SPLIT);
			
			//mainPanel.setOneTouchExpandable(true);
			mainPanel.add(panel1, BorderLayout.WEST);
			mainPanel.add(panel2, BorderLayout.CENTER);
			//mainPanel.setRightComponent(panel2);
			break;
		case STYLE_1TOP_1BOTTOM:
			mainPanel.setOrientation(ZSplitPane.VERTICAL_SPLIT);
			mainPanel.setOneTouchExpandable(true);
			mainPanel.setTopComponent(panel1);
			mainPanel.setBottomComponent(panel2);
			break;
		case STYLE_1LEFT_1RIGHT_LEFT_ALGINED:
			mainPanel.setOrientation(ZSplitPane.HORIZONTAL_SPLIT);
			mainPanel.setOneTouchExpandable(true);
			mainPanel.setLeftComponent(panel1);
			mainPanel.setRightComponent(panel2);
			break;
		default:
			throw new IllegalArgumentException(
					"Style is incompatible with a screen with three panels");
		}

		return mainPanel;
	}

	private ZSplitPane composeSplitpane(int style, JComponent panel1,
			JComponent panel2, JComponent panel3) {
		ZSplitPane mainPanel = m_mainPanel;
		ZSplitPane subPanel = m_subPanel;
		mainPanel.setOneTouchExpandable(true);
		subPanel.setOneTouchExpandable(true);

		switch (style) {
		case STYLE_2LEFT_1RIGHT:
			mainPanel.setOrientation(ZSplitPane.HORIZONTAL_SPLIT);
			subPanel.setOrientation(ZSplitPane.VERTICAL_SPLIT);
			mainPanel.setLeftComponent(subPanel);
			subPanel.setTopComponent(panel1);
			subPanel.setBottomComponent(panel2);
			mainPanel.setRightComponent(panel3);
			break;
		case STYLE_1LEFT_2RIGHT:
			mainPanel.setOrientation(ZSplitPane.HORIZONTAL_SPLIT);
			subPanel.setOrientation(ZSplitPane.VERTICAL_SPLIT);
			mainPanel.setRightComponent(subPanel);
			mainPanel.setLeftComponent(panel3);
			subPanel.setTopComponent(panel1);
			subPanel.setBottomComponent(panel2);
			break;
		case STYLE_1TOP_2BOTTOM:
			mainPanel.setOrientation(ZSplitPane.VERTICAL_SPLIT);
			subPanel.setOrientation(ZSplitPane.HORIZONTAL_SPLIT);
			mainPanel.setBottomComponent(subPanel);
			mainPanel.setTopComponent(panel3);
			subPanel.setLeftComponent(panel1);
			subPanel.setRightComponent(panel2);
			break;
		case STYLE_2TOP_1BOTTOM:
			mainPanel.setOrientation(ZSplitPane.VERTICAL_SPLIT);
			subPanel.setOrientation(ZSplitPane.HORIZONTAL_SPLIT);
			mainPanel.setOneTouchExpandable(true);
			mainPanel.setTopComponent(subPanel);
			subPanel.setLeftComponent(panel1);
			subPanel.setRightComponent(panel2);
			mainPanel.setBottomComponent(panel3);
			break;
		case STYLE_3CENTER:
			mainPanel.setOrientation(ZSplitPane.VERTICAL_SPLIT);
			subPanel.setOrientation(ZSplitPane.VERTICAL_SPLIT);
			mainPanel.setOneTouchExpandable(false);
			subPanel.setOneTouchExpandable(false);
			mainPanel.setBottomComponent(subPanel);
			mainPanel.setTopComponent(panel1);
			subPanel.setTopComponent(panel2);
			subPanel.setBottomComponent(panel3);
			break;
		default:
			throw new IllegalArgumentException(
					"Style is incompatible with a screen with three panels");
		}

		return mainPanel;
	}
	
	public JComponent getLeftPanel(){
		return firstPanel;
	}
	public JComponent getRightPanel(){
		return secondPanel;
	}
}