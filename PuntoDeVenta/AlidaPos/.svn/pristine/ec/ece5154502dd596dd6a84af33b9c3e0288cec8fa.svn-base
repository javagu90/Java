package com.alidasoftware.pos.bean;

import org.primefaces.context.RequestContext;

import com.alidasoftware.pos.util.JSFMessageUtil;

public class AbstractBean {

	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";

	public AbstractBean() {
		super();
	}

	protected void displayErrorMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendErrorMessageToUser(message);
	}

	protected void displayInfoMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendInfoMessageToUser(message);
	}

	protected void closeDialog() {
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
	}

	protected void closeDialog(String dialogName) {
		execute("PF('" + dialogName +"').hide();");
	}

	protected void keepDialogOpen() {
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
	}

	protected RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}
	
	protected void execute(String command) {
		getRequestContext().execute(command);
	}
	
}
