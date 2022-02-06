package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.DiasFestivosFacade;
import com.alidasoftware.pos.model.Diasfestivos;
 
public class DiasFestivosBean  extends AbstractBean implements Serializable {
	private static final long serialVersionUID = -1017680357806973143L;
	
	private DiasFestivosFacade diasFestivosFacade;
	private List<Diasfestivos> diasFestivos;
	private List<Diasfestivos> diasFestivosFiltrados;
	private Diasfestivos selected;
	
	private ScheduleModel eventModel;
	private ScheduleModel lazyEventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();
	private String strTimeZone = "";		

	public DiasFestivosBean() {
		Calendar calendar = new GregorianCalendar();
		TimeZone timeZone = calendar.getTimeZone();
		long miliseg = 1000 * 60 * 60;
		strTimeZone = "GMT" + String.valueOf(timeZone.getRawOffset() / miliseg);
		System.out.println(strTimeZone);
		loadDiasFestivos();
	}

	public String getStrTimeZone() {
		return strTimeZone;
	}


	public void setStrTimeZone(String strTimeZone) {
		this.strTimeZone = strTimeZone;
	}
	
	public DiasFestivosFacade getDiasFestivosFacade() {
        if (diasFestivosFacade == null) {
        	diasFestivosFacade = new DiasFestivosFacade();
        }
        return diasFestivosFacade;
	}


	public void setDiasFestivosFacade(DiasFestivosFacade diasFestivosFacade) {
		this.diasFestivosFacade = diasFestivosFacade;
	}


	public List<Diasfestivos> getDiasFestivos() {
		return diasFestivos;
	}

	public List<Diasfestivos> getDiasFestivosFiltrados() {
		return diasFestivosFiltrados;
	}

	public void setDiasFestivosFiltrados(List<Diasfestivos> diasFestivosFiltrados) {
		this.diasFestivosFiltrados = diasFestivosFiltrados;
	}

	public Diasfestivos getSelected() {
		return selected;
	}

	public void setSelected(Diasfestivos selected) {
		this.selected = selected;
	}

	public Diasfestivos prepareCreate() {
        selected = new Diasfestivos();
        return selected;
    }
	
	public String getDisabled() {
		if (selected == null) {
			return "true";
		}
		return "false";
	}
	
	public void create() {
		try {
            getDiasFestivosFacade().createDiafestivo(selected);
            closeDialog("DiasFestivosCreateDialog");
            displayInfoMessageToUser("El registro se ha creado");
            loadDiasFestivos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
		try {
            getDiasFestivosFacade().updateDiaFestivo(selected);
            closeDialog("DiasFestivosEditDialog");
            displayInfoMessageToUser("El registro se ha actualizado");
            loadDiasFestivos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
            getDiasFestivosFacade().deleteDiaFestivo(selected);
            displayInfoMessageToUser("El registro se ha eliminado");
            loadDiasFestivos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	private void loadDiasFestivos() {
		/*
		try {
			diasFestivos = getDiasFestivosFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		*/
		lazyEventModel = new LazyScheduleModel() {
            
            @Override
            public void loadEvents(Date start, Date end) {
            	System.out.println(start + "-" + end);
            	/*
                Date random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));
                 
                random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
                */
            }   
        };
    }
	
	public ScheduleModel getEventModel() {
        return eventModel;
    }
	
	public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }
	
	public ScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
    
    public void removeEvent(ActionEvent actionEvent) {
    	if(event.getId() != null) {
    		eventModel.deleteEvent(event);
    	}
    }
     
    public void addEvent(ActionEvent actionEvent) {
    	Date dateEvent = event.getStartDate();
    	if (dateEvent.before(new Date())) {            
    		keepDialogOpen();
			displayErrorMessageToUser("Error: No se pueden agregar días festivos en fechas anteriores");
    	} else {
    		if (eventModel == null) {
	    		eventModel = new DefaultScheduleModel();
	    	}
	        if(event.getId() == null) {
	            eventModel.addEvent(event);
	        } else {
	        	eventModel.updateEvent(event);
	        }         
	        event = new DefaultScheduleEvent();
    	}
	    	        
    }
    
    public void onViewChange(SelectEvent selectEvent) {
    	selectEvent.getObject();
    }
	
	public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
    	event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    	Date dateEvent = event.getStartDate();
    	if (dateEvent.before(new Date())) {
    		RequestContext.getCurrentInstance().execute("PF('eventDialog').hide();");
    		keepDialogOpen();
			displayErrorMessageToUser("Error: No se pueden agregar días festivos en fechas anteriores");
    	} else {
    		RequestContext.getCurrentInstance().execute("PF('eventDialog').show();");
    	}        
    }
     
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());         
        addMessage(message);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());         
        addMessage(message);
    }
    
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
		RequestContext.getCurrentInstance().update("growl");
    }   
		
}
