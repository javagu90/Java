package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.DiasFestivosFacade;
import com.alidasoftware.pos.model.Diasfestivos;
 
public class DiasFestivosBean  extends AbstractBean implements Serializable {
	private static final long serialVersionUID = -1017680357806973143L;
	
	private DiasFestivosFacade diasFestivosFacade;
	private List<Diasfestivos> diasFestivos;
	private List<Diasfestivos> diasFestivosFiltrados;
	private Diasfestivos selected;
	
	public DiasFestivosBean() {
		loadDiasFestivos();
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
		try {
			diasFestivos = getDiasFestivosFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
		
}
