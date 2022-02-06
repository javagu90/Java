package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.CajaFacade;
import com.alidasoftware.pos.facade.TiendaFacade;
import com.alidasoftware.pos.model.Caja;
import com.alidasoftware.pos.model.Tienda;

public class CajaBean extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = -2376937208717390434L;
	
	private CajaFacade cajaFacade;
	private TiendaFacade tiendaFacade;
	private List<Caja> cajas;
	private List<Caja> cajasFiltrados;
	private Caja selected;
	private boolean editMode;
	private String title;
	private List<SelectItem> tiendaList;
	private String searchClave;
	private String searchNombre;
	private Integer searchIdTienda;
	private Tienda searchTienda;
	private Tienda selectedTienda;

	public Tienda getSearchTienda() {
		return searchTienda;
	}

	public void setSearchTienda(Tienda searchTienda) {
		this.searchTienda = searchTienda;
	}

	public List<SelectItem> getTiendaList() {
		return tiendaList;
	}

	public void setTiendaList(List<SelectItem> tiendaList) {
		this.tiendaList = tiendaList;
	}

	public String getSearchClave() {
		return searchClave;
	}

	public void setSearchClave(String searchClave) {
		this.searchClave = searchClave;
	}

	public String getSearchNombre() {
		return searchNombre;
	}

	public void setSearchNombre(String searchNombre) {
		this.searchNombre = searchNombre;
	}

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

	public CajaBean() {
		try {
			loadCajas();
			loadTiendas();
		} catch (AlidaPosException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Aviso!", e.getMessage()));
		}
	}

	public CajaFacade getCajaFacade() {
        if (cajaFacade == null) {
            cajaFacade = new CajaFacade();
        }
        return cajaFacade;
    }
	
	public TiendaFacade getTiendaFacade(){
		if(tiendaFacade == null){
			tiendaFacade = new TiendaFacade();
		}
		return tiendaFacade;
	}
	
	public List<Caja> getCajas() {
		return cajas;
	}
	
	public void setCajas(List<Caja> cajas) {
		this.cajas = cajas;
	}
	
	public List<Caja> getCajasFiltrados() {
		return cajasFiltrados;
	}
	
	public void setCajasFiltrados(List<Caja> cajasFiltrados) {
		this.cajasFiltrados = cajasFiltrados;
	}
	
	public Caja getSelected() {
		return selected;
	}

	public void setSelected(Caja selected) {
		this.selected = selected;
	}
	
	public Caja prepareCreate() {
        selected = new Caja();
        return selected;
    }
	
	public void prepareCreateNew() throws AlidaPosException {
		loadTiendas();
		prepareCreate();
		selectedTienda = new Tienda();
	}
	
	public String getDisabled() {
		if (selected == null) {
			return "true";
		}
		return "false";
	}
	
	public void create() {
		try {		
			selected.setTienda(getTiendaFacade().findTienda(Integer.valueOf(selectedTienda.getIdtienda())));
			if(checkDuplicatedCode()){
				return;
			}
			selected.setActivo(true);
            getCajaFacade().createCaja(selected);
        	//Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"closing CajaCreateDlg");
            closeDialog("CajaCreateDialog");            
            loadCajas();
            displayInfoMessageToUser("El registro se ha creado correctamente.");
        	//Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"selected: antes " +getSelected().getIdcaja());
            prepareCreate();
        } catch (AlidaPosException ex) {
        	//System.out.println("Error CreateCaja: " + ex.getMessage());
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void onRowSelect(SelectEvent event){
    	//Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"selected: " +getSelected().getIdcaja());
	}
	
	public void update() {
		try {
			System.out.println("El valor del estatus al momento de hacer el update es : " + selected.isActivo());
            getCajaFacade().updateCaja(selected);
            closeDialog("CajaEditDialog");            
            loadCajas();
            displayInfoMessageToUser("El registro se ha actualizado");
        	//Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"selected: " +getSelected().getIdcaja());            
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void viewRecord() {
		editMode = false;
		title = "Información Caja";
		RequestContext.getCurrentInstance().update("CajaEditDlg");
	}
	
	public void editRecord() {
		editMode = true;
		title = "Editar Caja";
		RequestContext.getCurrentInstance().update("CajaEditDlg");
	}
	
	public void onRowDblClckSelect(SelectEvent event) {  
		if (event != null && event.getObject() != null) {
			//System.out.println("ejecutando doble clic : " + event.getObject().getClass().toString());
	        //FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selected", event.getObject());
			selected = (Caja) event.getObject();
			//System.out.println("selected caja: " + selected.getClave());			
	        viewRecord();	        
		}
        //return null;
        //return "carDetail?faces-redirect=true";  
    }
	
	public void destroy() {
		try {
            getCajaFacade().deleteCaja(selected);            
            loadCajas();
            prepareCreate();
            displayInfoMessageToUser("El registro se ha eliminado");
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	private void loadCajas() {
		try {
			cajas = getCajaFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	public boolean checkDuplicatedCode() {
		boolean result = false;
		try {
			System.out.println("clave a buscar es : " + selected.getClave());
			Caja auxCaja = getCajaFacade().findByClaveIdTienda(selected);
			if (auxCaja != null) {
				if(auxCaja.getTienda().getIdtienda() == selected.getTienda().getIdtienda()){
					selected.setClave(null);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"La clave ya existe. Debe seleccionar otra clave", "Debe seleccionar otra clave"));
					result = true;
				}
				//displayErrorMessageToUser("Debe seleccionar otra clave");
			}		
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser(ex.getMessage());
		}
		return result;
	}
	
	public void isCajaClaveValid(FacesContext ctx, UIComponent component, Object value) throws ValidatorException, AlidaPosException {
		//System.out.println("component : " + component.getId());
		//System.out.println("value     : " + value);
		if (!value.toString().trim().equals("")) {
			Caja auxCaja = getCajaFacade().findByClave(value.toString());
			if (auxCaja != null) {
				selected.setClave(null);
				String msg = "La clave ingresada ya existe, seleccione una diferente..";
				ctx.addMessage(component.getId(), new FacesMessage(FacesMessage.SEVERITY_WARN, msg, ""));
			}
		} else {
			ctx.addMessage(component.getId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "La clave es requerida", ""));
		}		
	}
	
	private void loadTiendas() throws AlidaPosException {
		System.out.println("dentro de la funcion de loadTiendas");
		try {
			//System.out.println("comienza el try");
			tiendaList = new ArrayList<SelectItem>();
			List<Tienda> list = ApplicationBean.getStoreList();
			if (list != null && list.size() > 0) {
				String label = "";
				//System.out.println("-----------------------");
				for (int i = 0; i < list.size(); i++) {
					label = list.get(i).getNombre();
					tiendaList.add(new SelectItem( list.get(i).getIdtienda(), label));					
					//System.out.println("id     :" + list.get(i).getIdtienda());
					//System.out.println("tienda :" + list.get(i).getNombre());
				}		
				//searchTienda = list.get(0);
				searchTienda = new Tienda();
				selectedTienda = list.get(0);
				//System.out.println("-----------------------");
			} else {
				keepDialogOpen();
	            displayErrorMessageToUser("Error: No exiten registros de Tienda.. verifique catálogo");
			}
		} catch (AlidaPosException ex) {
			//System.out.println("hubo un error en el try");
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	public void valueChangeTienda() {
		// por si se requiere validar algo al seleccionar la tienda
		System.out.println("tienda valueChange: " + selectedTienda.getNombre());
		
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
	
	public void valueChangeClaveFilter() {
		//System.out.println("SearchClave : [" + searchClave + "]");
		if (!searchClave.trim().equals("")) {    			
			selected = null;  			
		}    		
    }
	
	public void listenerSOM(){
		System.out.println("se supone que cambio de valor el id tienda a buscar " + searchIdTienda);
	}
	
	public void doSearch() {
		System.out.println("Realizando busqueda por ... " + searchIdTienda);
    	selected = null;	
		try {
			if(null == searchIdTienda || searchIdTienda==0){
				searchIdTienda = null;
			} 
			cajas = getCajaFacade().listBySearch(searchClave, searchNombre, searchIdTienda);	    	
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	searchNombre = "";
    	searchClave = "";
    	searchTienda = new Tienda();
    	searchIdTienda = 0;
		RequestContext.getCurrentInstance().update("CajaListForm:cajaListPanel");
	}

	public Tienda getSelectedTienda() {
		return selectedTienda;
	}

	public void setSelectedTienda(Tienda selectedTienda) {
		this.selectedTienda = selectedTienda;
		System.out.println("Tienda selected: " + selectedTienda.getNombre());
	}

	public Integer getSearchIdTienda() {
		return searchIdTienda;
	}

	public void setSearchIdTienda(Integer searchIdTienda) {
		this.searchIdTienda = searchIdTienda;
	}
	
	
	
}
