package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.ContactoFacade;
import com.alidasoftware.pos.facade.DireccionFacade;
import com.alidasoftware.pos.facade.EstadoFacade;
import com.alidasoftware.pos.facade.MunicipioFacade;
import com.alidasoftware.pos.facade.PersonaFacade;
import com.alidasoftware.pos.facade.ProveedorFacade;
import com.alidasoftware.pos.model.Direccion;
import com.alidasoftware.pos.model.Estado;
import com.alidasoftware.pos.model.Municipio;
import com.alidasoftware.pos.model.Persona;
import com.alidasoftware.pos.model.Proveedor;
import com.alidasoftware.pos.model.Contacto;
import com.alidasoftware.pos.util.Debug;
import com.alidasoftware.pos.util.Debug.User;
 
public class ProveedorBean  extends AbstractBean implements Serializable{
	private static final long serialVersionUID = 9103405700375640029L;
	
	private ProveedorFacade proveedorFacade;
	private PersonaFacade personaFacade;	
	private List<Proveedor> proveedores;
	private List<Proveedor> proveedoresFiltrados;
	private Proveedor selected;
	//added
	private ContactoFacade contactoFacade;
	private DireccionFacade direccionFacade;

	private String estadoSeleccionado;
	private String municipioSeleccionado;
	private List<Estado> estados;
	private EstadoFacade estadoFacade;
	private List<Municipio> municipios;
	private MunicipioFacade municipioFacade;
	
	private String searchNombre;
	private String searchClave;

	public ProveedorBean() {
		this.municipios = new ArrayList<Municipio>();		
		loadProveedores();
	}
	
	public EstadoFacade getEstadoFacade() {
        if (estadoFacade == null) {
            estadoFacade = new EstadoFacade();
        }
        return estadoFacade;
    }

	public ProveedorFacade getProveedorFacade() {
        if (proveedorFacade == null) {
            proveedorFacade = new ProveedorFacade();
        }
        return proveedorFacade;
    }

	public PersonaFacade getPersonaFacade() {
        if (personaFacade == null) {
        	personaFacade = new PersonaFacade();
        }
		return personaFacade;
	}

	public ContactoFacade getContactoFacade() {
        if (contactoFacade == null) {
        	contactoFacade = new ContactoFacade();
        }
		return contactoFacade;
	}
	
	public DireccionFacade getDireccionFacade() {
        if (direccionFacade == null) {
        	direccionFacade = new DireccionFacade();
        }
		return direccionFacade;
	}	
	public MunicipioFacade getMunicipioFacade() {
        if (municipioFacade == null) {
        	municipioFacade = new MunicipioFacade();
        }
		return municipioFacade;
	}	
	
	public List<Proveedor> getProveedores() {
		if(proveedores==null){
			loadProveedores();
		}
		return proveedores;
	}
	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}
	public List<Proveedor> getProveedoresFiltrados() {
		return proveedoresFiltrados;
	}
	public void setProveedoresFiltrados(List<Proveedor> proveedoresFiltrados) {
		this.proveedoresFiltrados = proveedoresFiltrados;
	}
	public Proveedor getSelected() {
		if(selected==null){
			prepareCreate();
		}
		return selected;
	}
	public String getEstadoSeleccionado() {
		return estadoSeleccionado;
	}
	public void setEstadoSeleccionado(String estadoSeleccionado) {
		this.estadoSeleccionado = estadoSeleccionado;		
	}
	public String getMunicipioSeleccionado() {
		return municipioSeleccionado;
	}
	public void setMunicipioSeleccionado(String municipioSeleccionado) {
		this.municipioSeleccionado = municipioSeleccionado;
	}
	public List<Municipio> getMunicipios() {
		return this.municipios;
	}
	
	public void setSelected(Proveedor selected) {
		this.selected = selected;
	}
	
	
	public Proveedor prepareCreate() {
        selected = new Proveedor();
        selected.setPersona(new Persona());
        selected.getPersona().setContacto(new Contacto());     
        this.selected.getPersona().getContacto().setDirecciones(new ArrayList<Direccion>());
        this.selected.getPersona().getContacto().getDirecciones().add(new Direccion());
        this.selected.getPersona().getContacto().getDirecciones().get(0).setMunicipio(new Municipio());
        this.selected.getPersona().getContacto().getDirecciones().get(0).getMunicipio().setEstado(new Estado());
        this.estadoSeleccionado = "";
        this.municipioSeleccionado="";
        return selected;
    }	
	public void prepareUpdate() {
		getEstados();
		this.estadoSeleccionado = this.selected.getPersona().getContacto().getDirecciones().get(0).getMunicipio().getEstado().getNombre();
		loadMunicipios();
		this.municipioSeleccionado = this.selected.getPersona().getContacto().getDirecciones().get(0).getMunicipio().getNombre();
	}
	
	public String getDisabled() {
		if (selected == null) {
			return "true";
		}
		return "false";
	}
	
	public void create() {
		try {
			// Verificar que la clave sea unica.
			if(checkDuplicatedCode()){
				return;
			}
			//Establecer el municipio actual
			List<Contacto> listaContactos = new ArrayList<Contacto>();
			Estado currentState = getEstadoFacade().findEstado(estadoSeleccionado);	
			Municipio currentMunicipio = getMunicipioFacade().findMunicipiobyNombre(this.municipioSeleccionado, currentState.getIdestado());
			this.selected.getPersona().getContacto().getDirecciones().get(0).setMunicipio(currentMunicipio);
			listaContactos.add(selected.getPersona().getContacto());
			selected.getPersona().getContacto().getDirecciones().get(0).setContactos(listaContactos);

			//establecer la fecha de alta del contacto
			selected.getPersona().getContacto().setFechaalta(new Date());
			//establecer la fecha de la ultima operación
			selected.setUltimaoperacion(new Date());

			selected.setActivo(true);
			getProveedorFacade().createProveedor(selected);
            closeDialog("ProveedorCreateDialog");
            displayInfoMessageToUser("El registro se ha creado");
            loadProveedores();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
		try {
			//Establecer el municipio actual
			Estado currentState = getEstadoFacade().findEstado(estadoSeleccionado);	
			Municipio currentMunicipio = getMunicipioFacade().findMunicipiobyNombre(this.municipioSeleccionado, currentState.getIdestado());
			this.selected.getPersona().getContacto().getDirecciones().get(0).setMunicipio(currentMunicipio);

			getProveedorFacade().updateProveedor(selected);
            getPersonaFacade().updatePersona(selected.getPersona());    			
            getContactoFacade().updateContacto(selected.getPersona().getContacto());
            //recorremos la lista de direcciones
            Iterator<Direccion> iteratorDireccion = selected.getPersona().getContacto().getDirecciones().iterator();
            while(iteratorDireccion.hasNext()){
            	Direccion d = iteratorDireccion.next();
            	getDireccionFacade().updateDireccion(d);
            }

            closeDialog("ProveedorEditDialog");
            displayInfoMessageToUser("El registro se ha actualizado");
            loadProveedores();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
			selected.setActivo(false);
			getProveedorFacade().updateProveedor(selected);
            displayInfoMessageToUser("El registro se ha eliminado");
            loadProveedores();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void doSearch() {
    	selected = null;	
		try {
			proveedores = getProveedorFacade().findByNombreClave(searchNombre, searchClave); 
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	searchNombre = "";
    	searchClave = "";
		RequestContext.getCurrentInstance().update("ClienteListForm:clienteListPanel");
	}
	
	public boolean checkDuplicatedCode() {
		boolean result = false;
		try {
			System.out.println("la clave del proveedor a buscar es : "
					+ selected.getClaveproveedor());
			Proveedor auxProveedor = getProveedorFacade().findProveedorByClave(selected.getClaveproveedor());
			if (auxProveedor != null) {
				selected.setClaveproveedor(null);
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_INFO,
										"La clave del proveedor ya existe. Debe seleccionar otra clave",
										"La clave del proveedor ya existe. Debe seleccionar otra clave"));
				result = true;
				displayErrorMessageToUser("Debe seleccionar otra clave");
			}
		} catch (AlidaPosException ex) {
			System.out.println("Error : " + ex.getMessage());
			keepDialogOpen();
			displayErrorMessageToUser(ex.getMessage());
		}
		return result;
	}
	
	public void onRowDblClckSelect(SelectEvent event) {  
		if (event != null && event.getObject() != null) {
			selected = (Proveedor) event.getObject();
	        viewRecord();	        
		}
    }
	
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("ProveedorViewDlg");
	}
	
	private void loadProveedores() {
		try {
			proveedores = getProveedorFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }	
    public void handleStateSelect() {
    	loadMunicipios();
    }
    public void handleMunicipioSelect() {
    }
	private void loadEstados() {
		try {
			estados = getEstadoFacade().listAll();
		} catch (AlidaPosException ex) {
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	public List<Estado> getEstados() {
		loadEstados();
		return this.estados;
	}
	private void loadMunicipios() {
		try {
			Estado currentState = getEstadoFacade().findEstado(estadoSeleccionado);		
	    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Estado Seleccionado: " + currentState.getNombre() + ":" + currentState.getIdestado());

			this.municipios = getMunicipioFacade().findMunicipiosByEstado(currentState.getIdestado());

		} catch (AlidaPosException ex) {
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }

	public String getSearchNombre() {
		return searchNombre;
	}

	public void setSearchNombre(String searchNombre) {
		this.searchNombre = searchNombre;
	}

	public String getSearchClave() {
		return searchClave;
	}

	public void setSearchClave(String searchClave) {
		this.searchClave = searchClave;
	}
    
	

	
}
