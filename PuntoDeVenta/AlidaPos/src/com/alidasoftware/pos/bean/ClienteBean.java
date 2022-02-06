package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;







import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.CategoriaClienteFacade;
import com.alidasoftware.pos.facade.ClienteFacade;
import com.alidasoftware.pos.facade.ContactoFacade;
import com.alidasoftware.pos.facade.DireccionFacade;
import com.alidasoftware.pos.facade.EstadoFacade;
import com.alidasoftware.pos.facade.MunicipioFacade;
import com.alidasoftware.pos.facade.PersonaFacade;
import com.alidasoftware.pos.model.Categoriacliente;
import com.alidasoftware.pos.model.Direccion;
import com.alidasoftware.pos.model.Estado;
import com.alidasoftware.pos.model.Municipio;
import com.alidasoftware.pos.model.Persona;
import com.alidasoftware.pos.model.Cliente;
import com.alidasoftware.pos.model.Contacto;
import com.alidasoftware.pos.util.Debug;
import com.alidasoftware.pos.util.Utils;
import com.alidasoftware.pos.util.Debug.User;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
 
public class ClienteBean  extends AbstractBean implements Serializable{
	private static final long serialVersionUID = 9103405700375640029L;
	
	private ClienteFacade clienteFacade;
	private List<Cliente> clientes;
	private List<Cliente> clientesFiltrados;
	private Cliente selected;
	//added
	private PersonaFacade personaFacade;
	private ContactoFacade contactoFacade;
	private DireccionFacade direccionFacade;

	private String estadoSeleccionado;
	private String municipioSeleccionado;
	private List<Estado> estados;
	private EstadoFacade estadoFacade;
	private List<Municipio> municipios;
	private MunicipioFacade municipioFacade;

	private CategoriaClienteFacade categoriaClienteFacade;
	private List<Categoriacliente> categoriasClientes;
	private List<Categoriacliente> categoriasClientesSeleccionadas;
	private String categoriaClienteSeleccionada;
	private float categoriaClienteSeleccionada_descuento;
	private String creditoHabilitado;
	
	private String searchNombre;
	private String searchClave;
	private String searchCategoria;
	

    private DualListModel<Categoriacliente> categorias;
    
	public DualListModel<Categoriacliente> getCategorias() {
		if (categorias == null) {
			getCategoriasClientes();
			categoriasClientesSeleccionadas = new ArrayList<Categoriacliente>();
			categorias = new DualListModel<Categoriacliente>(categoriasClientes, categoriasClientesSeleccionadas);
		}
		return categorias;
	}
	
	public String getCreditoHabilitado() {
		return creditoHabilitado;
	}

	public void setCreditoHabilitado(String creditoHabilitado) {
		this.creditoHabilitado = creditoHabilitado;
	}

	public float getCategoriaClienteSeleccionada_descuento() {
		return categoriaClienteSeleccionada_descuento;
	}

	public void setCategoriaClienteSeleccionada_descuento(
			float categoriaClienteSeleccionada_descuento) {
		this.categoriaClienteSeleccionada_descuento = categoriaClienteSeleccionada_descuento;
	}

	public String getCategoriaClienteSeleccionada() {
		return categoriaClienteSeleccionada;
	}
	
	// client search
	private String clientQuery;
	private String fromView;

	public String getClientQuery() {
		return clientQuery;
	}

	public void setClientQuery(String clientQuery) {
		this.clientQuery = clientQuery;
	}


	public void setCategoriaClienteSeleccionada(String categoriaClienteSeleccionada) {
		this.categoriaClienteSeleccionada = categoriaClienteSeleccionada;
	}

	public ClienteBean() {
		this.municipios = new ArrayList<Municipio>();
		categoriasClientes = new ArrayList<Categoriacliente>();
		loadClientes();
	}

	public CategoriaClienteFacade getCategoriaClienteFacade() {
        if (categoriaClienteFacade == null) {
        	categoriaClienteFacade = new CategoriaClienteFacade();
        }
        return categoriaClienteFacade;
    }
	
	public EstadoFacade getEstadoFacade() {
        if (estadoFacade == null) {
            estadoFacade = new EstadoFacade();
        }
        return estadoFacade;
    }

	public ClienteFacade getClienteFacade() {
        if (clienteFacade == null) {
            clienteFacade = new ClienteFacade();
        }
        return clienteFacade;
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
	public List<Cliente> getClientes() {
		if(clientes==null){
			loadClientes();
		}
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}
	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}
	public Cliente getSelected() {
		if(selected==null){
			prepareCreate();
		}		
		return selected;
	}
	public void setSelected(Cliente selected) {
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

	
	public Cliente prepareCreate() {
        this.selected = new Cliente();
        this.selected.setPersona(new Persona());
        this.selected.getPersona().setContacto(new Contacto());
        this.selected.getPersona().getContacto().setDirecciones(new ArrayList<Direccion>());
        this.selected.getPersona().getContacto().getDirecciones().add(new Direccion());
        this.selected.getPersona().getContacto().getDirecciones().get(0).setMunicipio(new Municipio());
        this.selected.getPersona().getContacto().getDirecciones().get(0).getMunicipio().setEstado(new Estado());      
        this.selected.setCategoriacliente(new Categoriacliente());

        loadCategoriasClientes();
        this.estadoSeleccionado = "";
        this.municipioSeleccionado="";
        this.categoriaClienteSeleccionada_descuento = 0;
        this.categoriaClienteSeleccionada = "";
        return selected;
    }	

	public void prepareUpdate() {
		getEstados();
		this.estadoSeleccionado = this.selected.getPersona().getContacto().getDirecciones().get(0).getMunicipio().getEstado().getNombre();
		System.out.println("Segun no encuentra el estado : " + estadoSeleccionado);
		loadMunicipios();
		this.municipioSeleccionado = this.selected.getPersona().getContacto().getDirecciones().get(0).getMunicipio().getNombre();
		
		loadCategoriasClientes();
		this.categoriaClienteSeleccionada = this.selected.getCategoriacliente().getNombre();
		this.categoriaClienteSeleccionada_descuento = this.selected.getCategoriacliente().getDescuento();
	}
	
	public String getDisabled() {
		if (selected == null) {
			return "true";
		}
		return "false";
	}
	
	public void create() {
		try {
			//clave de cliente duplicada
			if(checkDuplicatedCode()){
				return;
			}
			List<Contacto> listaContactos = new ArrayList<Contacto>();
			//establecer la categoria de cliente
			Categoriacliente currentCategoria;
			currentCategoria = getCategoriaClienteFacade().findCategoriacliente(this.categoriaClienteSeleccionada);	    	

	    	selected.setCategoriacliente(currentCategoria);

			//Establecer el municipio actual
			Estado currentState = getEstadoFacade().findEstado(estadoSeleccionado);	
			Municipio currentMunicipio = getMunicipioFacade().findMunicipiobyNombre(this.municipioSeleccionado, currentState.getIdestado());
			this.selected.getPersona().getContacto().getDirecciones().get(0).setMunicipio(currentMunicipio);
			listaContactos.add(selected.getPersona().getContacto());
			this.selected.getPersona().getContacto().getDirecciones().get(0).setContactos(listaContactos);
			//establecer la fecha de alta del contacto
			selected.getPersona().getContacto().setFechaalta(new Date());
			//establecer la fecha de la ultima operación
			selected.setUltimaoperacion(new Date());
        	
            getClienteFacade().createCliente(selected);
            closeDialog("ClienteCreateDialog");
            displayInfoMessageToUser("El registro se ha creado");
            loadClientes();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
  		try {
			System.out.println("entro al update");
			//establecer la categoria de cliente
			Categoriacliente currentCategoria;
			currentCategoria = getCategoriaClienteFacade().findCategoriacliente(this.categoriaClienteSeleccionada);
	    	selected.setCategoriacliente(currentCategoria);
			
			//Establecer el municipio actual
			Estado currentState = getEstadoFacade().findEstado(estadoSeleccionado);	
			Municipio currentMunicipio = getMunicipioFacade().findMunicipiobyNombre(this.municipioSeleccionado, currentState.getIdestado());
			this.selected.getPersona().getContacto().getDirecciones().get(0).setMunicipio(currentMunicipio);
			
			System.out.println("entro update cliente");
            getClienteFacade().updateCliente(selected);
            System.out.println("entro update persona");
            getPersonaFacade().updatePersona(selected.getPersona());
            System.out.println("entro update contacto");
            getContactoFacade().updateContacto(selected.getPersona().getContacto());
            //recorremos la lista de direcciones
            Iterator<Direccion> iteratorDireccion = selected.getPersona().getContacto().getDirecciones().iterator();
            while(iteratorDireccion.hasNext()){
            	Direccion d = iteratorDireccion.next();
            	System.out.println("entro update direccion " +  d.getDireccionCompleta());
            	getDireccionFacade().updateDireccion(d);
            }
            
            closeDialog("ClienteEditDialog");
            displayInfoMessageToUser("El registro se ha actualizado");
            loadClientes();
            selected = new Cliente();
        } catch (AlidaPosException ex) {
        	System.out.println("Error : " + ex.getMessage());
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
			selected.setActivo(false);
			getClienteFacade().updateCliente(selected);
            displayInfoMessageToUser("El registro se ha eliminado");
            loadClientes();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void doSearch() {
    	selected = null;	
		try {
			clientes = getClienteFacade().findByNombreClaveCategoria(searchNombre, searchClave, searchCategoria);
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	searchNombre = "";
    	searchClave = "";
    	searchCategoria = "";
		RequestContext.getCurrentInstance().update("ClienteListForm:clienteListPanel");
	}
	
	public boolean checkDuplicatedCode() {
		boolean result = false;
		try {
			System.out.println("la clave del cliente a buscar es : "
					+ selected.getClavecliente());
			Cliente auxCliente = getClienteFacade().findClienteByClave(
					selected.getClavecliente());
			if (auxCliente != null) {
				selected.setClavecliente(null);
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_INFO,
										"La clave del cliente ya existe. Debe seleccionar otra clave",
										"La clave del cliente ya existe. Debe seleccionar otra clave"));
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
			selected = (Cliente) event.getObject();
	        viewRecord();	        
		}
    }
	
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("ClienteViewDlg");
	}
	
	public void prepareSearchdialog(String view) {
		fromView = view;
		clientesFiltrados = null;
		selected = null;
		clientQuery = null;
		RequestContext.getCurrentInstance().update("clientSearchForm:clientSearchPanel");
		RequestContext.getCurrentInstance().update("clientSearchForm:clientSearchDatatable");		
	}
	
	private VistaBean getVistaBean() {
		return (VistaBean) Utils.getManagedBean("vistaBean");
	}
	
	private VentaBean getVentaBean() {
		return (VentaBean) Utils.getManagedBean("ventaBean");
	}
	
	private ApartadoBean getApartadoBean() {
		return (ApartadoBean) Utils.getManagedBean("apartadoBean");
	}
	
	public void handleRowSelect(SelectEvent event) {
		System.out.println("EVT clienteBean ROW SELECT : " + event);
		if( event != null) {	
			selected = (Cliente) event.getObject();
		}
	}
	
	public void handleClose(CloseEvent event) {
		System.out.println("EVT clienteBean CLOSE : " + event);
		if (event != null) {			
			if (selected != null && fromView != null) {
				if (fromView.equals(ApplicationBean.MODULE_VISTA)) {
					if (getVistaBean().getCliente() != null &&
						getVistaBean().getCliente().getIdcliente() != selected.getIdcliente()) {
						getVistaBean().updateSelectedClient(selected);
					} else {
						getVistaBean().updateSelectedClient(selected);
					}					
				} else if (fromView.equals(ApplicationBean.MODULE_VENTA)) {
					System.out.println("Cliente Venta: " + selected.getPersona().getNombre());
					if (getVentaBean().getCliente() != null &&
						getVentaBean().getCliente().getIdcliente() != selected.getIdcliente()) {
						getVentaBean().updateSelectedClient(selected);
					} else {
						getVentaBean().updateSelectedClient(selected);
					}
				} else if (fromView.equals(ApplicationBean.MODULE_APARTADO)) {
					if (getApartadoBean().getCliente() != null &&
						getApartadoBean().getCliente().getIdcliente() != selected.getIdcliente()) {
						getApartadoBean().updateSelectedClient(selected);
					} else {
						getApartadoBean().updateSelectedClient(selected);
					}
				}
				prepareSearchdialog(ApplicationBean.MODULE_NONE);
			}				
		}
	}
	
	public String searchClientList() {
		if (clientQuery != null && !clientQuery.trim().equals("")) {
			List<Persona> per = new ArrayList<Persona>();
			try {
				clientesFiltrados = getClienteFacade().FindLikeClave(clientQuery);
				per = getPersonaFacade().FindLikeInfoPersona(clientQuery);
				clientesFiltrados = Utils.mergeClientPersonList(clientesFiltrados, per);
			} catch (AlidaPosException ex) {			
	            keepDialogOpen();
	            displayErrorMessageToUser("Error: " + ex.getMessage());
	        }
		}
		return null;
	}
	
	private void loadClientes() {
		try {
			clientes = getClienteFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	public void preProcessPDF(Object document) {
		   Document doc = (Document) document;
		   doc.setPageSize(PageSize.A4.rotate());
	    }

    public void handleStateSelect() {
    	loadMunicipios();
    }
    public void handleMunicipioSelect() {
//    	try {
//			Estado currentState = getEstadoFacade().findEstado(estadoSeleccionado);	
////	    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Estado Seleccionado: " + currentState.getNombre() + ":" + currentState.getIdestado());			
////	        Debug.print(User.PEDRO, new Exception().getStackTrace()[0], "Municipio Seleccionado: " + this.municipioSeleccionado);
//
//			Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"municipio: " +
//					getMunicipioFacade().findMunicipiobyNombre(this.municipioSeleccionado, currentState.getIdestado()).getNombre()
//			);
//		} catch (AlidaPosException e) {
//	        Debug.print(User.PEDRO, new Exception().getStackTrace()[0], "MUN NO ENCONTRADO" );
//		}
////		try {
////			this.selected.getPersona().getContacto().getDirecciones().get(0).getMunicipio().setEstado(getEstadoFacade().findEstado(estadoSeleccionado));
////		} catch (AlidaPosException e) {
////	        Debug.print(User.PEDRO, new Exception().getStackTrace()[0], "ESTADO NO ENCONTRADO" );
////		}
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
		return estados;
	}
	private void loadMunicipios() {
		try {
			Estado currentState = getEstadoFacade().findEstado(estadoSeleccionado);		
	    	//Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Estado Seleccionado: " + currentState.getNombre() + ":" + currentState.getIdestado());

			municipios = getMunicipioFacade().findMunicipiosByEstado(currentState.getIdestado());

		} catch (AlidaPosException ex) {
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }

	private void loadCategoriasClientes() {
		try {
			categoriasClientes = getCategoriaClienteFacade().findAllActive();
			//System.out.println("encontro num categorias " + categoriasClientes.size());
		} catch (AlidaPosException ex) {
			//System.out.println("error al obtener las categoriasclientes " + ex.getMessage());
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	public List<Categoriacliente> getCategoriasClientes(){
		loadCategoriasClientes();
		return this.categoriasClientes;		
	}
	
    public void handleCategoriaClienteSelect() {
		Categoriacliente currentCategoria;
		try {
			currentCategoria = getCategoriaClienteFacade().findCategoriacliente(this.categoriaClienteSeleccionada);
	    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"categoria: " + currentCategoria.getNombre() );
	    	categoriaClienteSeleccionada_descuento = currentCategoria.getDescuento();

		} catch (AlidaPosException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al obtener la categoria cliente.");
			e.printStackTrace();
		}		
    }
    

    public void handleCredito() {
    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"credito: " + this.selected.getCredito() );
		if (selected.getCredito() == true) {
			this.setCreditoHabilitado("false");
		}else{
			this.setCreditoHabilitado("true");
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


	public String getSearchCategoria() {
		return searchCategoria;
	}


	public void setSearchCategoria(String searchCategoria) {
		this.searchCategoria = searchCategoria;
	}
}
