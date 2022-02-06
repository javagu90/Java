package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.UnidadFacade;
import com.alidasoftware.pos.model.Unidad;

public class UnidadBean  extends AbstractBean implements Serializable{
	private static final long serialVersionUID = -4022391807144320352L;
	
	private UnidadFacade unidadFacade;
	private List<Unidad> unidades;
	private List<Unidad> unidadesFiltradas;
	private Unidad selected;
	private String searchNombre;
	
	public UnidadBean() {
		loadUnidades();
	}

	public Unidad prepareCreate() {
        selected = new Unidad();
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
			selected.setActivo(true);
			if(checkDuplicatedCode()){
				return;
			}
            getUnidadFacade().createUnidad(selected);
            closeDialog("UnidadCreateDialog");
            displayInfoMessageToUser("El registro se ha creado");
            loadUnidades();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
		try {
            getUnidadFacade().updateUnidad(selected);
            closeDialog("UnidadEditDialog");
            displayInfoMessageToUser("El registro se ha actualizado");
            loadUnidades();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
			selected.setActivo(false);
			getUnidadFacade().updateUnidad(selected);
            displayInfoMessageToUser("El registro se ha eliminado");
            loadUnidades();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	private void loadUnidades() {
		try {
			unidades = getUnidadFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	public void onRowDblClckSelect(SelectEvent event) {  
		System.out.println("entro a esto");
		if (event != null && event.getObject() != null) {
			selected = (Unidad) event.getObject();
	        viewRecord();	        
		}
    }
	
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("UnidadViewDlg");
	}
	
	public void doSearch() {
		System.out.println("Realizando busqueda por ... " + searchNombre);
    	selected = null;	
		try {
			unidades = getUnidadFacade().findByNombre(searchNombre);
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	searchNombre = "";
		RequestContext.getCurrentInstance().update("UnidadListForm:unidadListPanel");
	}
	
	public boolean checkDuplicatedCode() {
		boolean result = false;
		try {
			System.out.println("el nombre a buscar es : " + selected.getNombre());
			Unidad auxUnidad = getUnidadFacade().findUnidad(selected.getNombre());
			if (auxUnidad != null) {
					selected.setNombre(null);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"El nombre ya existe. Debe seleccionar otro nombre", "El nombre ya existe. Debe seleccionar otro nombre"));
					result = true;
				//displayErrorMessageToUser("Debe seleccionar otra clave");
			}		
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser(ex.getMessage());
		}
		return result;
	}
	


	public UnidadFacade getUnidadFacade() {
        if (unidadFacade == null) {
            unidadFacade = new UnidadFacade();
        }
        return unidadFacade;
    }

	
	public List<Unidad> getUnidades() {
	       if (unidades == null) {
	    	   loadUnidades();
	        }
		return unidades;
	}
	public void setUnidades(List<Unidad> unidades) {
		this.unidades = unidades;
	}
	public List<Unidad> getUnidadesFiltradas() {
		return unidadesFiltradas;
	}
	public void setUnidadesFiltradas(List<Unidad> unidadesFiltradas) {
		this.unidadesFiltradas = unidadesFiltradas;
	}
	public Unidad getSelected() {
		return selected;
	}
	public void setSelected(Unidad selected) {
		this.selected = selected;
	}


	public String getSearchNombre() {
		return searchNombre;
	}


	public void setSearchNombre(String searchNombre) {
		this.searchNombre = searchNombre;
	}
	
}
