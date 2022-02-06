package com.alidasoftware.pos.helper;

import java.io.Serializable;

public class MenuItemHelper implements Serializable {

	private static final long serialVersionUID = 4748069154364235348L;
	
	private String title = "" ;
	private String outcome = "";
	private String icon = "";
	private String action = "";
	private String actionListener = "";
	private boolean showWaitImage = false;	
	private String paramName = "";
	private String paramValue = "";
	
	/*
	private boolean actionOutcome;
	public boolean isActionOutcome() {
		return actionOutcome;
	}

	public void setActionOutcome(boolean actionOutcome) {
		this.actionOutcome = actionOutcome;
	}	
	*/
	
	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionListener() {
		return actionListener;
	}

	public void setActionListener(String actionListener) {
		this.actionListener = actionListener;
	}

	public boolean isShowWaitImage() {
		return showWaitImage;
	}

	public void setShowWaitImage(boolean showWaitImage) {
		this.showWaitImage = showWaitImage;
	}
	
	public MenuItemHelper(String title, String action, String icon) {
		this.title = title;
		this.action = action;
		this.icon = icon;
		this.showWaitImage = false;
	}
	
	public MenuItemHelper(String title, String outcome, String action, String icon) {
		this.title = title;
		this.action = action;
		this.outcome = outcome;
		this.icon = icon;
		this.showWaitImage = false;
	}

	public MenuItemHelper(String title, String outcome, String icon, boolean showWaitImage) {
		this.title = title;
		this.outcome = outcome;
		this.icon = icon;
		this.showWaitImage = showWaitImage;
	}
	
	public MenuItemHelper(String title, String outcome, String actionListener, String paramName, String icon) {
		this.title = title;
		this.outcome = outcome;
		this.actionListener = actionListener;
		this.paramName = paramName;
		this.paramValue = outcome;
		this.icon = icon;
		this.showWaitImage = false;
	}
	
	public MenuItemHelper(String title, String outcome, String actionListener, String icon, boolean showWaitImage) {
		this.title = title;
		this.outcome = outcome;
		this.actionListener = actionListener;
		this.icon = icon;
		this.showWaitImage = showWaitImage;
	}
		
}
