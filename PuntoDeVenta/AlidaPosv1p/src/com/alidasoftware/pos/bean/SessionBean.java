package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;

public class SessionBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 6973021012700900745L;
	//private boolean inProcess;
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	
	private String message;
	private String messageTitle;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language, String country) {
        locale = new Locale(language, country);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        //System.out.println("Locale : " + locale.getCountry() + " - " + locale.getLanguage());
    }

    /*
	public boolean isInProcess() {
		return inProcess;
	}

	public void setInProcess(boolean inProcess) {
		this.inProcess = inProcess;
	}
	*/
	
    /*
	public boolean isRenderMenu() {
		
		if (inProcess) {
			return false;
		}
		return true;
	}
	*/
		
	public SessionBean() {
		setLanguage("es","MX");		
	}
	
	/*
	public String checkInProcess() {
		System.out.println("Hay un proceso en ejecucion, debe cancelarlo para continuar");
		if (inProcess) {			
			keepDialogOpen();
			displayInfoMessageToUser("Hay un proceso en ejecucion, debe cancelarlo para continuar");
		}
		return "/catalogos/caja/List.xhtml";
	}
	*/	
		
	public void cleanMessage() {
		message = "";
		messageTitle = "";
	}	
	
}
