package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.EmpleadoFacade;
import com.alidasoftware.pos.facade.EstadoFacade;
import com.alidasoftware.pos.facade.MunicipioFacade;
import com.alidasoftware.pos.facade.TiendaFacade;
import com.alidasoftware.pos.model.Contacto;
import com.alidasoftware.pos.model.Direccion;
import com.alidasoftware.pos.model.Empleado;
import com.alidasoftware.pos.model.Estado;
import com.alidasoftware.pos.model.Municipio;
import com.alidasoftware.pos.model.Tienda;
import com.alidasoftware.pos.util.Functions;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;

public class TiendaBean  extends AbstractBean implements Serializable {
	
	private static final long serialVersionUID = -7436998041708697003L;
	 
	private TiendaFacade tiendaFacade;
	private List<Tienda> tiendas;
	private List<Tienda> tiendasFiltrados;
	private Tienda selected;
	private Contacto contacto;
	private Direccion direccion;
	private String estadoSelected;
	private String municipioSelected;	
	private EstadoFacade estadoFacade;	
	private MunicipioFacade municipioFacade;
	private EmpleadoFacade empleadoFacade;
	private List<Estado> estados;
	private List<Municipio> municipios;
	private Municipio municipio;
	private Estado estado;
	private String responsable;
	private List<SelectItem> items;
	private boolean editMode;
	private String title;
	private List<SelectItem> itemsEmpleados;
	private String searchNombre;

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public String getEstadoSelected() {
		return estadoSelected;
	}

	public void setEstadoSelected(String estadoSelected) {
		this.estadoSelected = estadoSelected;
	}

	public String getMunicipioSelected() {
		return municipioSelected;
	}

	public void setMunicipioSelected(String municipioSelected) {
		this.municipioSelected = municipioSelected;
	}

	public TiendaBean() {
		loadTiendas();
	}

	public EstadoFacade getEstadoFacade() {
        if (estadoFacade == null) {
            estadoFacade = new EstadoFacade();
        }
        return estadoFacade;
    }

	public MunicipioFacade getMunicipioFacade() {
        if (municipioFacade == null) {
        	municipioFacade = new MunicipioFacade();
        }
		return municipioFacade;
	}
	
	public TiendaFacade getTiendaFacade() {
        if (tiendaFacade == null) {
            tiendaFacade = new TiendaFacade();
        }
        return tiendaFacade;
    }
	
	public EmpleadoFacade getEmpleadoFacade() {
		if (empleadoFacade == null) {
			empleadoFacade = new EmpleadoFacade();
		}
		return empleadoFacade;
	}
	
	public List<Tienda> getTiendas() {
		return tiendas;
	}
	
	public void setTiendas(List<Tienda> tiendas) {
		this.tiendas = tiendas;
	}
	
	public List<Tienda> getTiendasFiltrados() {
		return tiendasFiltrados;
	}
	
	public void setTiendasFiltrados(List<Tienda> tiendasFiltrados) {
		this.tiendasFiltrados = tiendasFiltrados;
	}
	
	public Tienda getSelected() {
		return selected;
	}

	public void setSelected(Tienda selected) {
		this.selected = selected;
	}
	
	public Tienda prepareCreate() {
        selected = new Tienda();
        //selected.setResponsable(new Empleado());
        contacto = new Contacto();
        direccion = new Direccion();
        return selected;
    }
	
	public void prepareCreateNew() {
		try {
			List<Empleado> empleadoList = getEmpleadoFacade().listAll();
			items = new ArrayList<SelectItem>();
			for (int i = 0; i < empleadoList.size(); i++) {
				items.add(new SelectItem(empleadoList.get(i).getIdempleado(), Functions.personName( empleadoList.get(i).getPersona())));
			}
		} catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
		prepareCreate();
	}
	
	public String getDisabled() {
		if (selected == null) {
			return "true";
		}
		return "false";
	}
	
	public void create() {
		try {		
			List<Contacto> contactList = new ArrayList<Contacto>();
			contactList.add(contacto);
			direccion.setMunicipio(municipio);
			direccion.setContactos(contactList);
			selected.setContacto(contacto);
			List<Direccion> dirList = new ArrayList<Direccion>();
			dirList.add(direccion);
			contacto.setDirecciones(dirList);
			contacto.setFechaalta(new Date());
			selected.setResponsable(getEmpleadoFacade().findById(Integer.parseInt(responsable)));
			selected.setActivo(true);
            getTiendaFacade().createTienda(selected);
            closeDialog("TiendaCreateDialog");
            displayInfoMessageToUser("El registro se ha creado");
            loadTiendas();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
		try {
            getTiendaFacade().updateTienda(selected);
            closeDialog("TiendaEditDialog");
            displayInfoMessageToUser("El registro se ha actualizado");
            loadTiendas();
            //prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
			selected.setActivo(false);
			getTiendaFacade().updateTienda(selected);
            displayInfoMessageToUser("El registro se ha desactivado");
            loadTiendas();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public List<SelectItem> getEmpleados() {
		return items;
	}
	
	private void loadTiendas() {
		try {
			tiendas = getTiendaFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	public void handleEmpleado() {
		System.out.println("Estado seleccionado : " + estadoSelected);
    	loadMunicipios();
    }
	
	public void handleStateSelect() {
		System.out.println("Estado seleccionado : " + estadoSelected);
    	loadMunicipios();
    }
	
	public void handleMunicipioSelect() {
		try {
			municipio = getMunicipioFacade().findMunicipiobyNombre(municipioSelected, estado.getIdestado());
		} catch (AlidaPosException ex) {
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}
	
	private void loadMunicipios() {
		try {
			estado = getEstadoFacade().findEstado(estadoSelected);
			municipios = getMunicipioFacade().findMunicipiosByEstado(estado.getIdestado());
			//RequestContext.getCurrentInstance().update("TiendaCreateForm:dirPanel:municipio");
			System.out.println("Total municipios : " + municipios.size());
		} catch (AlidaPosException ex) {
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	public void editRecord() {
		editMode = true;
		title = "Editar Tienda";
		System.out.println("resposable que tiene para editar " + selected.getResponsable().getIdempleado());
		getListEmpleados();
		RequestContext.getCurrentInstance().update("TiendaEditDlg");
	}
	
	public void onRowDblClckSelect(SelectEvent event) {  
		System.out.println("entro a esto");
		//getListEmpleados();
		if (event != null && event.getObject() != null) {
			selected = (Tienda) event.getObject();
	        viewRecord();	        
		}
    }
	
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("TiendaViewDlg");
	}
	
    public void preProcessPDF(Object document) {
	   Document doc = (Document) document;
	   doc.setPageSize(PageSize.A4.rotate());
    }

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void getListEmpleados(){
		try{
			List<Empleado> empleadoList = getEmpleadoFacade().listAll();
			itemsEmpleados = new ArrayList<SelectItem>();
			for (int i = 0; i < empleadoList.size(); i++) {
				itemsEmpleados.add(new SelectItem(empleadoList.get(i).getIdempleado(), Functions.personName( empleadoList.get(i).getPersona())));
			}
		} catch(AlidaPosException ex){
			displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}

	public List<SelectItem> getItemsEmpleados() {
		return itemsEmpleados;
	}

	public void setItemsEmpleados(List<SelectItem> itemsEmpleados) {
		this.itemsEmpleados = itemsEmpleados;
	}
	
	public void doSearch() {
		System.out.println("Realizando busqueda por ... " + searchNombre);
    	selected = null;	
		try {
			tiendas = getTiendaFacade().findByNombre(searchNombre);
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	searchNombre = "";
		RequestContext.getCurrentInstance().update("UnidadListForm:cajaListPanel");
	}

	public String getSearchNombre() {
		return searchNombre;
	}

	public void setSearchNombre(String searchNombre) {
		this.searchNombre = searchNombre;
	}
}
