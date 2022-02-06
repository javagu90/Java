package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.CategoriaClienteFacade;
import com.alidasoftware.pos.model.Categoriacliente;
 
public class CategoriaClienteBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = -4788161105338034722L;
	
	private CategoriaClienteFacade categoriaClienteFacade;
	private List<Categoriacliente> categoriaClientes;
	private List<Categoriacliente> categoriaClientesFiltrados;
	private Categoriacliente selected;
	private boolean editMode;
	private String title;
	private String searchDescuento;
	private String percentage;
	private Integer nuevoDescuento;

	public String getSearchDescuento() {
		return searchDescuento;
	}

	public void setSearchDescuento(String searchDescuento) {
		this.searchDescuento = searchDescuento;
	}

	public String getSearchNombre() {
		return searchNombre;
	}

	public void setSearchNombre(String searchNombre) {
		this.searchNombre = searchNombre;
	}

	private String searchNombre;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	

	public CategoriaClienteBean() {
		loadCategoriaClientes();
	}


	public CategoriaClienteFacade getCategoriaClienteFacade() {
        if (categoriaClienteFacade == null) {
            categoriaClienteFacade = new CategoriaClienteFacade();
        }
        return categoriaClienteFacade;
    }

	
	public List<Categoriacliente> getCategoriaClientes() {
		return categoriaClientes;
	}
	public void setCategoriaClientes(List<Categoriacliente> categoriaclientes) {
		this.categoriaClientes = categoriaclientes;
	}
	public List<Categoriacliente> getCategoriaClientesFiltrados() {
		return categoriaClientesFiltrados;
	}
	public void setCategoriaClientesFiltrados(List<Categoriacliente> categoriaclientesFiltrados) {
		this.categoriaClientesFiltrados = categoriaclientesFiltrados;
	}
	public Categoriacliente getSelected() {
		return selected;
	}

	
	public void setSelected(Categoriacliente selected) {
		this.selected = selected;
	}
	public Categoriacliente prepareCreate() {
        selected = new Categoriacliente();
        nuevoDescuento = 0;
        return selected;
    }
	
	public boolean checkDuplicatedCode() {
		boolean result = false;
		try {
			System.out.println("clave a buscar es : " + selected.getNombre());
			Categoriacliente ccAux = getCategoriaClienteFacade().findCategoriacliente(selected.getNombre());
			if (ccAux != null) {
					selected.setNombre("");
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"La clave ya existe. Debe seleccionar otra clave", "Debe seleccionar otra clave"));
					result = true;
				//displayErrorMessageToUser("Debe seleccionar otra clave");
			}		
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser(ex.getMessage());
		}
		return result;
	}
	
	public String getDisabled() {
		if (selected == null) {
			return "true";
		}
		return "false";
	}
	
	public void create() {
		try {		
			System.out.println("Descuento : " + nuevoDescuento);
			selected.setDescuento(nuevoDescuento / 100.0f);
			if(checkDuplicatedCode()){
				return;
			}
			selected.setActivo(true);
            getCategoriaClienteFacade().createCategoriacliente(selected);
            closeDialog("CategoriaClienteCreateDialog");
            displayInfoMessageToUser("El registro se ha creado");
            loadCategoriaClientes();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
		try {
			System.out.println("el nuevo descuento al editar es : " + nuevoDescuento);
			System.out.println("el descuento anterior era : " + selected.getDescuento());
			selected.setDescuento(nuevoDescuento / 100.0f);
            getCategoriaClienteFacade().updateCategoriacliente(selected);
            closeDialog("CategoriaClienteEditDialog");
            displayInfoMessageToUser("El registro se ha actualizado");
            loadCategoriaClientes();
            prepareCreate();
        } catch (AlidaPosException ex) {
        	System.out.println("UpdateError : " + ex.getMessage());
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
			selected.setActivo(false);
            //getCategoriaClienteFacade().deleteCategoriacliente(selected);
			getCategoriaClienteFacade().updateCategoriacliente(selected);
            displayInfoMessageToUser("El registro se ha eliminado");
            loadCategoriaClientes();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	private void loadCategoriaClientes() {
		try {
			categoriaClientes = getCategoriaClienteFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	
	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	
	public void viewRecord() {
		editMode = false;
		title = "Información Categoria Cliente";
		RequestContext.getCurrentInstance().update("CategoriaClienteEditDlg");
	}
	
	public void editRecord() {
		editMode = true;
		title = "Editar Categoria Cliente";
		System.out.println("prepara para la edicion " + selected.getDescuento());
		
		nuevoDescuento = (int) (selected.getDescuento() * 100);
		System.out.println("aqui es : " + nuevoDescuento);
		RequestContext.getCurrentInstance().update("CategoriaClienteEditDlg");
	}
	
	public void onRowDblClckSelect(SelectEvent event) {  
		if (event != null && event.getObject() != null) {
			selected = (Categoriacliente) event.getObject();		
	        viewRecord();	        
		}
        //return null;
        //return "carDetail?faces-redirect=true";  
    }
	
	//---------------------------------------------------------------------------
    // Metodos ejecutados al seleccionar un valor en los filtros de búsqueda...
    //---------------------------------------------------------------------------
	
	public void valueChangeNombreFilter() {
		//System.out.println("SearchNombre : [" + searchNombre + "]");
		if (!searchNombre.trim().equals("")) {    			
			selected = null;  			
		}    		
    }
	
	public void valueChangeDescuentoFilter() {
		//System.out.println("SearchClave : [" + searchClave + "]");
		if (!searchDescuento.trim().equals("")) {    			
			selected = null;  			
		}    		
    }
	
	public void doSearch() {
		System.out.println("Realizando busqueda por ... " + searchNombre);
		System.out.println("Realizando busqueda por ... " + searchDescuento);
    	selected = null;
    	Float descToSearch = null;
		try {
			if(!searchDescuento.trim().equals("") && searchDescuento !=null){
				System.out.println("searchDescuento vino vacio.");
				descToSearch = Float.valueOf(searchDescuento) / 100;
			}
			categoriaClientes = getCategoriaClienteFacade().findByNombreDescuento(searchNombre, descToSearch);	    	
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	searchNombre = "";
    	searchDescuento = "";
		RequestContext.getCurrentInstance().update("CategoriaClienteListForm:categoriaClienteListPanel");
	}

	public Integer getNuevoDescuento() {
		return nuevoDescuento;
	}

	public void setNuevoDescuento(Integer nuevoDescuento) {
		this.nuevoDescuento = nuevoDescuento;
	}
	
}
