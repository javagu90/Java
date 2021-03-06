package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
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
import com.alidasoftware.pos.facade.PerfilFacade;
import com.alidasoftware.pos.facade.PersonaFacade;
import com.alidasoftware.pos.facade.UsuarioFacade;
import com.alidasoftware.pos.model.Empleado;
import com.alidasoftware.pos.model.Estado;
import com.alidasoftware.pos.model.Municipio;
import com.alidasoftware.pos.model.Perfil;
import com.alidasoftware.pos.model.Usuario;
import com.alidasoftware.pos.util.Debug;
import com.alidasoftware.pos.util.Debug.User;
import com.alidasoftware.pos.util.Functions;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;

public class UsuarioBean  extends AbstractBean implements Serializable{
	private static final long serialVersionUID = 9103405700375640029L;
	
	private UsuarioFacade usuarioFacade;
	private PersonaFacade personaFacade;
	private List<Usuario> usuarios;
	private List<Usuario> usuariosFiltrados;
	private Usuario selected;
	 
	//added
	private ContactoFacade contactoFacade;
	private DireccionFacade direccionFacade;
	private EmpleadoFacade empleadoFacade;
	
	private String estadoSeleccionado;
	private String municipioSeleccionado;
	private List<Estado> estados;
	private EstadoFacade estadoFacade;
	private List<Municipio> municipios;
	private MunicipioFacade municipioFacade;
	private List<Empleado> listEmpleados;
	private List<SelectItem> itemsEmpleados;
	private String selectedEmpleado;
	private String nuevaContraseña;
	
	// Perfiles 
	private List<Perfil> perfiles;
	private List<SelectItem> itemsPerfil;
	private Integer perfilSelected;
	private PerfilFacade perfilFacade;
	
	// Filtros
	private String searchNombre;
	private String searchUsuario;
	
	public UsuarioBean() {
		this.municipios = new ArrayList<Municipio>();
		loadUsuarios();
	}

	public Usuario prepareCreate() {
        this.selected = new Usuario();
        this.selected.setEmpleado(new Empleado());
        this.estadoSeleccionado = "";
        this.municipioSeleccionado="";
        try{
        	loadEmpleados();
        	loadPerfiles();
        } catch (Exception e) {
        	System.out.println("el error fue : " +  e.getMessage());
        }
        return this.selected;
    }
	
	public void prepareUpdate(){
		perfilSelected = selected.getPerfil().getIdPerfil();
		loadPerfiles();
	}

	public String getDisabled() {
		if (selected == null) {
			return "true";
		}
		return "false";
	}
	
	public void create() {
		try {			
			//Nombre de usuario duplicado
			if(checkDuplicate()){
				return;
			}       
        	selected.setActivo(true);
        	selected.setEmpleado(getEmpleadoFacade().findById(Integer.parseInt(selectedEmpleado)));
        	selected.setTienda(selected.getEmpleado().getTienda());
        	selected.setPerfil(getPerfilFacade().findPerfilById(perfilSelected));
        	selected.setClaveacceso(Functions.getMD5(selected.getClaveacceso()));
            getUsuarioFacade().createUsuario(selected);
            closeDialog("UsuarioCreateDialog");
            displayInfoMessageToUser("El registro se ha creado");
            loadUsuarios();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
		try {
			if(nuevaContraseña!=null && !nuevaContraseña.equals("")){
				selected.setClaveacceso(Functions.getMD5(nuevaContraseña));
			}
			selected.setPerfil(getPerfilFacade().findPerfilById(perfilSelected));
			getUsuarioFacade().updateUsuario(selected);
            closeDialog("UsuarioEditDialog");
            displayInfoMessageToUser("El registro se ha actualizado");
            loadUsuarios();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
			selected.setActivo(false);
			getUsuarioFacade().updateUsuario(selected);
            displayInfoMessageToUser("El registro se ha eliminado");
            loadUsuarios();
            prepareCreate();                       
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	private void loadUsuarios() {
		try {
			usuarios = getUsuarioFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	private void loadPerfiles(){
		perfiles = new ArrayList<>();
        itemsPerfil = new ArrayList<SelectItem>();
		try {
			perfiles = getPerfilFacade().listAllActive();
			System.out.println("despues de traer la lista de perfiles activos " + perfiles.size());
			// Llenado de lista de perfiles en select items para mostrar en pantalla.
			if(perfiles!=null && !perfiles.isEmpty()){
        		for(Perfil item : perfiles){
        			itemsPerfil.add(new SelectItem(item.getIdPerfil(), item.getNombre()));
        		}
        	}
		} catch (AlidaPosException e) {
			System.out.println("Error al momento de cargar los perfiles " + e.getMessage());
		}
	}
	
	private void loadEmpleados(){
        listEmpleados = new ArrayList<>();
        itemsEmpleados = new ArrayList<SelectItem>();
		try {
			listEmpleados = getEmpleadoFacade().findAllWithoutUser();
	    	System.out.println("despues de traer la lista de empleados sin usuario");
	    	// Llenado de lista de empleados en select items para mostrar en pantalla.
	    	if(listEmpleados!=null && !listEmpleados.isEmpty()){
	    		for(Empleado item : listEmpleados){
	    			itemsEmpleados.add(new SelectItem(item.getIdempleado().toString(), Functions.personName(item.getPersona()) + " (" + item.getClaveempleado() + ")" ));
	    		}
	    	}
		} catch (Exception ex) {
			System.out.println("Error al momento de cargar los empleados " + ex.getMessage());
		}	
	}
	
	public void doSearch() {
		System.out.println("Realizando busqueda por ... " + searchNombre);
		System.out.println("Realizando busqueda por ... " + searchUsuario);
    	selected = null;	
		try {
			usuarios = getUsuarioFacade().searchNombreUsuario(searchNombre, searchUsuario);
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	searchNombre = "";
    	searchUsuario = "";
		RequestContext.getCurrentInstance().update("UsuarioListForm:usuarioListPanel");
	}
	
	public boolean checkDuplicate(){
		boolean result = false;
		try {
			System.out.println("la clave a buscar es : " + selected.getClave());
			Usuario auxUsuario = getUsuarioFacade().findByClave(selected.getClave());
			if (auxUsuario != null) {
					selected.setClave(null);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"El nombre ya existe. Debe seleccionar otro nombre", "El nombre ya existe. Debe seleccionar otro nombre"));
					result = true;
			}		
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser(ex.getMessage());
		}
		return result;
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
	
	public void onRowDblClckSelect(SelectEvent event) {  
		if (event != null && event.getObject() != null) {
			selected = (Usuario) event.getObject();
	        viewRecord();	        
		}
    }
	
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("UsuarioViewDlg");
	}
	
    public void handleStateSelect() {
    	loadMunicipios();
    }
    
    public void handleMunicipioSelect() {
    	
    }
    
    public void preProcessPDF(Object document) {
	   Document doc = (Document) document;
	   doc.setPageSize(PageSize.A4.rotate());
    }
	
	public PerfilFacade getPerfilFacade(){
		if(perfilFacade == null){
			perfilFacade = new PerfilFacade();
		}
		return perfilFacade;
	}

	public EstadoFacade getEstadoFacade() {
        if (estadoFacade == null) {
            estadoFacade = new EstadoFacade();
        }
        return estadoFacade;
    }
	
	public EmpleadoFacade getEmpleadoFacade(){
		if(empleadoFacade == null){
			empleadoFacade = new EmpleadoFacade();
		}
		return empleadoFacade;
	}

	public UsuarioFacade getUsuarioFacade() {
        if (usuarioFacade == null) {
            usuarioFacade = new UsuarioFacade();
        }
        return usuarioFacade;
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

	public List<Usuario> getUsuarios() {
		if(usuarios==null){
			loadUsuarios();
		}
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Usuario> getUsuariosFiltrados() {
		return usuariosFiltrados;
	}
	public void setUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
		this.usuariosFiltrados = usuariosFiltrados;
	}
	public Usuario getSelected() {
		if(selected==null){
			prepareCreate();
		}
		return selected;
	}
	public void setSelected(Usuario selected) {
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

	public List<Empleado> getListEmpleados() {
		return listEmpleados;
	}

	public void setListEmpleados(List<Empleado> listEmpleados) {
		this.listEmpleados = listEmpleados;
	}

	public List<SelectItem> getItemsEmpleados() {
		return itemsEmpleados;
	}

	public void setItemsEmpleados(List<SelectItem> itemsEmpleados) {
		this.itemsEmpleados = itemsEmpleados;
	}

	public String getSelectedEmpleado() {
		return selectedEmpleado;
	}

	public void setSelectedEmpleado(String selectedEmpleado) {
		this.selectedEmpleado = selectedEmpleado;
	}

	public String getNuevaContraseña() {
		return nuevaContraseña;
	}

	public void setNuevaContraseña(String nuevaContraseña) {
		this.nuevaContraseña = nuevaContraseña;
	}

	public String getSearchNombre() {
		return searchNombre;
	}

	public void setSearchNombre(String searchNombre) {
		this.searchNombre = searchNombre;
	}

	public String getSearchUsuario() {
		return searchUsuario;
	}

	public void setSearchUsuario(String searchUsuario) {
		this.searchUsuario = searchUsuario;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public Integer getPerfilSelected() {
		return perfilSelected;
	}

	public void setPerfilSelected(Integer perfilSelected) {
		this.perfilSelected = perfilSelected;
	}

	public List<SelectItem> getItemsPerfil() {
		return itemsPerfil;
	}

	public void setItemsPerfil(List<SelectItem> itemsPerfil) {
		this.itemsPerfil = itemsPerfil;
	}
	
    

}
