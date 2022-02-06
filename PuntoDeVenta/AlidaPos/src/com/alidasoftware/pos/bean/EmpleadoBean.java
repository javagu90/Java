package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.ContactoFacade;
import com.alidasoftware.pos.facade.DireccionFacade;
import com.alidasoftware.pos.facade.EmpleadoFacade;
import com.alidasoftware.pos.facade.EstadoFacade;
import com.alidasoftware.pos.facade.MunicipioFacade;
import com.alidasoftware.pos.facade.PersonaFacade;
import com.alidasoftware.pos.facade.TiendaFacade;
import com.alidasoftware.pos.model.Direccion;
import com.alidasoftware.pos.model.Empleado;
import com.alidasoftware.pos.model.Estado;
import com.alidasoftware.pos.model.Municipio;
import com.alidasoftware.pos.model.Persona;
import com.alidasoftware.pos.model.Cliente;
import com.alidasoftware.pos.model.Contacto;
import com.alidasoftware.pos.model.Tienda;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;

public class EmpleadoBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 9103405700375640029L;

	private List<Cliente> clientesFiltrados;

	private EmpleadoFacade empleadoFacade;
	private List<Empleado> empleados;
	private Empleado selected;

	// added
	private PersonaFacade personaFacade;
	private ContactoFacade contactoFacade;
	private DireccionFacade direccionFacade;
	private TiendaFacade tiendaFacade;

	private String estadoSeleccionado;
	private String municipioSeleccionado;
	private List<Estado> estados;
	private EstadoFacade estadoFacade;
	private List<Municipio> municipios;
	private MunicipioFacade municipioFacade;
	private Integer newIdTienda;
	
	private String searchNombre;
	private String searchClave;

	private List<Direccion> listaDirecciones;
	private Direccion nuevaDireccion;

	private List<SelectItem> itemsTienda;

	// client search
	private String clientQuery;

	public Empleado prepareCreate() {
		loadEmpleados();
		this.selected = new Empleado();
		this.selected.setPersona(new Persona());
		this.selected.getPersona().setContacto(new Contacto());
		this.selected.getPersona().getContacto()
				.setDirecciones(new ArrayList<Direccion>());
		this.selected.getPersona().getContacto().getDirecciones()
				.add(new Direccion());
		this.selected.getPersona().getContacto().getDirecciones().get(0)
				.setMunicipio(new Municipio());
		this.selected.getPersona().getContacto().getDirecciones().get(0)
				.getMunicipio().setEstado(new Estado());
		this.estadoSeleccionado = "";
		this.municipioSeleccionado = "";
		nuevaDireccion = new Direccion();
		try {
			loadTiendas();
		} catch (AlidaPosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return selected;
	}

	public void prepareUpdate() {
		getEstados();
		this.estadoSeleccionado = this.selected.getPersona().getContacto()
				.getDirecciones().get(0).getMunicipio().getEstado().getNombre();
		loadMunicipios();
		this.municipioSeleccionado = this.selected.getPersona().getContacto()
				.getDirecciones().get(0).getMunicipio().getNombre();
	}

	public void create() {
		try {
			// Establecer el municipio actual
			if(checkDuplicatedCode()){
				return;
			}
			List<Contacto> listaContactos = new ArrayList<Contacto>();
			Estado currentState = getEstadoFacade().findEstado(
					estadoSeleccionado);
			Municipio currentMunicipio = getMunicipioFacade()
					.findMunicipiobyNombre(this.municipioSeleccionado,
							currentState.getIdestado());
			nuevaDireccion.setMunicipio(currentMunicipio);
			listaContactos.add(selected.getPersona().getContacto());
			nuevaDireccion.setContactos(listaContactos);
			listaDirecciones = new ArrayList<Direccion>();
			listaDirecciones.add(nuevaDireccion);

			selected.getPersona().getContacto()
					.setDirecciones(listaDirecciones);
			// this.selected.getPersona().getContacto().getDirecciones().get(0).setMunicipio(currentMunicipio);
			// establecer la fecha de alta del contacto
			selected.getPersona().getContacto().setFechaalta(new Date());
			Tienda nueva = getTiendaFacade().findTienda(newIdTienda.intValue());
			selected.setTienda(nueva);
			selected.setActivo(true);
			System.out.println("la razon social que ingresaron es : " + selected.getPersona().getRazonsocial());
			getEmpleadoFacade().createEmpleado(selected);
			closeDialog("EmpleadoCreateDialog");
			displayInfoMessageToUser("El registro se ha creado");
			loadEmpleados();
			selected = new Empleado();
			nuevaDireccion = new Direccion();
			// prepareCreate();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
			displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}

	public void update() {
		try {

			// Establecer el municipio actual
			Estado currentState = getEstadoFacade().findEstado(
					estadoSeleccionado);
			Municipio currentMunicipio = getMunicipioFacade()
					.findMunicipiobyNombre(this.municipioSeleccionado,
							currentState.getIdestado());
			this.selected.getPersona().getContacto().getDirecciones().get(0)
					.setMunicipio(currentMunicipio);

			getEmpleadoFacade().updateEmpleado(selected);
			getPersonaFacade().updatePersona(selected.getPersona());
			getContactoFacade().updateContacto(
					selected.getPersona().getContacto());
			// recorremos la lista de direcciones
			Iterator<Direccion> iteratorDireccion = selected.getPersona()
					.getContacto().getDirecciones().iterator();
			while (iteratorDireccion.hasNext()) {
				Direccion d = iteratorDireccion.next();

				getDireccionFacade().updateDireccion(d);
			}

			closeDialog("EmpleadoEditDialog");
			displayInfoMessageToUser("El registro se ha actualizado");
			loadEmpleados();
			selected = new Empleado();
			nuevaDireccion = new Direccion();
		} catch (AlidaPosException ex) {
			System.out.println("Error en el update : " + ex.getMessage());
			keepDialogOpen();
			displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}

	public void destroy() {
		 try {
			selected.setActivo(false);
			getEmpleadoFacade().updateEmpleado(selected);
			displayInfoMessageToUser("El registro se ha eliminado");
			loadEmpleados();
			prepareCreate();
		 } catch (AlidaPosException ex) {
			 System.out.println("Error al eliminar : " + ex.getMessage());
			 keepDialogOpen();
			 displayErrorMessageToUser("Error: " + ex.getMessage());
		 }
	}
	
	public void onRowDblClckSelect(SelectEvent event) {  
		if (event != null && event.getObject() != null) {
			selected = (Empleado) event.getObject();
	        viewRecord();	        
		}
    }
	
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("EmpleadoViewDlg");
	}

	private void loadEmpleados() {
		try {
			empleados = getEmpleadoFacade().listAll();
		} catch (AlidaPosException ex) {
			System.out.println("error al obtener los empleados "
					+ ex.getMessage());
			keepDialogOpen();
			displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}

	private void loadEstados() {
		try {
			estados = getEstadoFacade().listAll();
		} catch (AlidaPosException ex) {
			displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}

	private void loadMunicipios() {
		try {
			Estado currentState = getEstadoFacade().findEstado(
					estadoSeleccionado);
			municipios = getMunicipioFacade().findMunicipiosByEstado(
					currentState.getIdestado());

		} catch (AlidaPosException ex) {
			displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}

	private void loadTiendas() throws AlidaPosException {
		try {
			itemsTienda = new ArrayList<SelectItem>();
			List<Tienda> list = ApplicationBean.getStoreList();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					itemsTienda.add(new SelectItem(list.get(i).getIdtienda(),
							list.get(i).getNombre()));
				}
			} else {
				keepDialogOpen();
				displayErrorMessageToUser("Error: No exiten registros de Tienda.. verifique catálogo");
			}
		} catch (AlidaPosException ex) {
			keepDialogOpen();
			displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}

	public boolean checkDuplicatedCode() {
		boolean result = false;
		try {
			System.out.println("el nombre a buscar es : "
					+ selected.getClaveempleado());
			Empleado auxEmpleado = getEmpleadoFacade().findEmpleadoByClave(
					selected.getClaveempleado());
			if (auxEmpleado != null) {
				selected.setClaveempleado(null);
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_INFO,
										"La clave del empleado ya existe. Debe seleccionar otro nombre",
										"La clave del empleado ya existe. Debe seleccionar otro nombre"));
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

	public void doSearch() {
    	selected = null;	
		try {
			empleados = getEmpleadoFacade().findByNombreClave(searchNombre, searchClave);
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	searchNombre = "";
    	searchClave = "";
		RequestContext.getCurrentInstance().update("EmpleadoListForm:empleadoListPanel");
	}

	public void handleRowSelect(SelectEvent event) {
		if (event != null) {
			selected = (Empleado) event.getObject();
		}
	}
	
	public void preProcessPDF(Object document) {
		   Document doc = (Document) document;
		   doc.setPageSize(PageSize.A4.rotate());
	    }

	public void handleStateSelect() {
		loadMunicipios();
	}

	public List<Estado> getEstados() {
		loadEstados();
		return estados;
	}

	public void handleMunicipioSelect() {

	}

	public String getDisabled() {
		if (selected == null) {
			return "true";
		}
		return "false";
	}

	public Integer getNewIdTienda() {
		return newIdTienda;
	}

	public void setNewIdTienda(Integer newIdTienda) {
		this.newIdTienda = newIdTienda;
	}

	public String getClientQuery() {
		return clientQuery;
	}

	public void setClientQuery(String clientQuery) {
		this.clientQuery = clientQuery;
	}

	public EmpleadoBean() {
		this.municipios = new ArrayList<Municipio>();
		loadEmpleados();
	}

	public EstadoFacade getEstadoFacade() {
		if (estadoFacade == null) {
			estadoFacade = new EstadoFacade();
		}
		return estadoFacade;
	}

	public EmpleadoFacade getEmpleadoFacade() {
		if (empleadoFacade == null) {
			empleadoFacade = new EmpleadoFacade();
		}
		return empleadoFacade;
	}

	public PersonaFacade getPersonaFacade() {
		if (personaFacade == null) {
			personaFacade = new PersonaFacade();
		}
		return personaFacade;
	}

	public TiendaFacade getTiendaFacade() {
		if (tiendaFacade == null) {
			tiendaFacade = new TiendaFacade();
		}
		return tiendaFacade;
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

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}

	public Empleado getSelected() {
		if (selected == null) {
			prepareCreate();
		}
		return selected;
	}

	public void setSelected(Empleado selected) {
		this.selected = selected;
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

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public List<SelectItem> getItemsTienda() {
		return itemsTienda;
	}

	public void setItemsTienda(List<SelectItem> itemsTienda) {
		this.itemsTienda = itemsTienda;
	}

	public List<Direccion> getListaDirecciones() {
		return listaDirecciones;
	}

	public void setListaDirecciones(List<Direccion> listaDirecciones) {
		this.listaDirecciones = listaDirecciones;
	}

	public Direccion getNuevaDireccion() {
		return nuevaDireccion;
	}

	public void setNuevaDireccion(Direccion nuevaDireccion) {
		this.nuevaDireccion = nuevaDireccion;
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
