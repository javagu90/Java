package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.CategoriaProductoFacade;
import com.alidasoftware.pos.model.Categoriaproducto;
 
public class CategoriaProductoBean  extends AbstractBean implements Serializable {
	private static final long serialVersionUID = -795045165293427207L;
	
	private CategoriaProductoFacade categoriaProductoFacade;
	private List<Categoriaproducto> categoriaProductos;
	private List<Categoriaproducto> categoriaProductosFiltrados;
	private Categoriaproducto selected;
	private String searchNombre;
	private String searchDescription;

	public CategoriaProductoBean() {
		loadCategoriaProductos();
	}


	public CategoriaProductoFacade getCategoriaProductoFacade() {
        if (categoriaProductoFacade == null) {
            categoriaProductoFacade = new CategoriaProductoFacade();
        }
        return categoriaProductoFacade;
    }

	
	public List<Categoriaproducto> getCategoriaProductos() {
		return categoriaProductos;
	}
	public void setCategoriaProductos(List<Categoriaproducto> categoriaproductos) {
		this.categoriaProductos = categoriaproductos;
	}
	public List<Categoriaproducto> getCategoriaProductosFiltrados() {
		return categoriaProductosFiltrados;
	}
	public void setCategoriaProductosFiltrados(List<Categoriaproducto> categoriaproductosFiltrados) {
		this.categoriaProductosFiltrados = categoriaproductosFiltrados;
	}
	public Categoriaproducto getSelected() {
		return selected;
	}

	
	public void setSelected(Categoriaproducto selected) {
		this.selected = selected;
	}
	public Categoriaproducto prepareCreate() {
        selected = new Categoriaproducto();
        return selected;
    }
	
	public boolean isDisabled() {
		if (selected == null) {
			return true;
		}
		return false;
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
            getCategoriaProductoFacade().createCategoriaproducto(selected);
            closeDialog("CategoriaProductoCreateDialog");
            displayInfoMessageToUser("El registro se ha creado");
            loadCategoriaProductos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
		try {
            getCategoriaProductoFacade().updateCategoriaproducto(selected);
            closeDialog("CategoriaProductoEditDialog");
            displayInfoMessageToUser("El registro se ha actualizado");
            loadCategoriaProductos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
			selected.setActivo(false);
			getCategoriaProductoFacade().updateCategoriaproducto(selected);
            displayInfoMessageToUser("El registro se ha inhabilitado");
            loadCategoriaProductos();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	private void loadCategoriaProductos() {
		try {
			categoriaProductos = getCategoriaProductoFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	public boolean checkDuplicatedCode() {
		boolean result = false;
		try {
			System.out.println("el nombre a buscar es : " + selected.getNombre());
			Categoriaproducto auxCP = getCategoriaProductoFacade().findByNombre(selected.getNombre());
			//Unidad auxUnidad = getUnidadFacade().findUnidad(selected.getNombre());
			if (auxCP != null) {
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
	
	public void doSearch() {
		System.out.println("Realizando busqueda por ... " + searchNombre);
		System.out.println("descripcion : " + searchDescription);
    	selected = null;	
		try {
			categoriaProductos = getCategoriaProductoFacade().searchNombreDescripcion(searchNombre, searchDescription);
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	searchNombre = "";
    	searchDescription = "";
		RequestContext.getCurrentInstance().update("CategoriaProductoListForm:categoriaProductoPanel");
	}
	
	public void onRowDblClckSelect(SelectEvent event) {  
		if (event != null && event.getObject() != null) {
			selected = (Categoriaproducto) event.getObject();
	        viewRecord();	        
		}
    }
	
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("CategoriaProductoViewDlg");
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
	
}
