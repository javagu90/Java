package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.TipoVencimientoFacade;
import com.alidasoftware.pos.model.Tipovencimiento;

public class TipoVencimientoBean  extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 6711893269172713372L;
	 
	private TipoVencimientoFacade tipoVencimientoFacade;
	private List<Tipovencimiento> tipoVencimientos;
	private List<Tipovencimiento> tipoVencimientosFiltrados;
	private Tipovencimiento selected;
	private String searchNombre;
	private String searchDescription;
	private String searchTipoDias;
	
	

	public TipoVencimientoBean() {
		loadTipovencimientos();
	}


	public TipoVencimientoFacade getTipoVencimientoFacade() {
        if (tipoVencimientoFacade == null) {
            tipoVencimientoFacade = new TipoVencimientoFacade();
        }
        return tipoVencimientoFacade;
    }

	
	public List<Tipovencimiento> getTipoVencimientos() {
		return tipoVencimientos;
	}
	public void setTipoVencimientos(List<Tipovencimiento> tipoVencimientos) {
		this.tipoVencimientos = tipoVencimientos;
	}
	public List<Tipovencimiento> getTipoVencimientosFiltrados() {
		return tipoVencimientosFiltrados;
	}
	public void setTipoVencimientosFiltrados(List<Tipovencimiento> tipoVencimientosFiltrados) {
		this.tipoVencimientosFiltrados = tipoVencimientosFiltrados;
	}
	public Tipovencimiento getSelected() {
		return selected;
	}

	
	public void setSelected(Tipovencimiento selected) {
		this.selected = selected;
	}
	public Tipovencimiento prepareCreate() {
        selected = new Tipovencimiento();
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
			if(checkDuplicatedCode()){
				return;
			}
			selected.setActivo(true);
            getTipoVencimientoFacade().createTipoVencimiento(selected);
            closeDialog("TipoVencimientoCreateDialog");
            displayInfoMessageToUser("El registro se ha creado");
            loadTipovencimientos();
            prepareCreate();
            ApplicationBean.reloadExpirationTypeList();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
		try {
            getTipoVencimientoFacade().updateTipoVencimiento(selected);
            closeDialog("TipoVencimientoEditDialog");
            displayInfoMessageToUser("El registro se ha actualizado");
            loadTipovencimientos();
            prepareCreate();
            ApplicationBean.reloadExpirationTypeList();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
			selected.setActivo(false);
			getTipoVencimientoFacade().updateTipoVencimiento(selected);
            displayInfoMessageToUser("El registro se ha desactivado");
            loadTipovencimientos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	private void loadTipovencimientos() {
		try {
			tipoVencimientos = getTipoVencimientoFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	public void onRowDblClckSelect(SelectEvent event) {  
		if (event != null && event.getObject() != null) {
			selected = (Tipovencimiento) event.getObject();
	        viewRecord();	        
		}
    }
	
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("TipoVencimientoViewDlg");
	}
	
	public void doSearch() {
		System.out.println("Realizando busqueda por ... " + searchNombre);
    	selected = null;	
		try {
			tipoVencimientos = getTipoVencimientoFacade().searchByFilters(searchNombre, searchDescription, searchTipoDias);
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	searchNombre = "";
    	searchDescription = "";
    	searchTipoDias = "";
		RequestContext.getCurrentInstance().update("UnidadListForm:unidadListPanel");
	}
	
	public boolean checkDuplicatedCode() {
		boolean result = false;
		try {
			System.out.println("el nombre a buscar es : " + selected.getNombre());
			Tipovencimiento auxTV = getTipoVencimientoFacade().findByNombre(selected.getNombre());
			if (auxTV != null) {
					selected.setNombre(null);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"El nombre ya existe. Debe seleccionar otro nombre", "El nombre ya existe. Debe seleccionar otro nombre"));
					result = true;
			}		
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser(ex.getMessage());
		}
		return result;
	}


	public String getSearchNombre() {
		return searchNombre;
	}


	public void setSearchNombre(String searchNombre) {
		this.searchNombre = searchNombre;
	}


	public String getSearchDescription() {
		return searchDescription;
	}


	public void setSearchDescription(String searchDescription) {
		this.searchDescription = searchDescription;
	}


	public String getSearchTipoDias() {
		return searchTipoDias;
	}


	public void setSearchTipoDias(String searchTipoDias) {
		this.searchTipoDias = searchTipoDias;
	}
	
}
