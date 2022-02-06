package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.ModuloFacade;
import com.alidasoftware.pos.facade.PerfilFacade;
import com.alidasoftware.pos.helper.ModuloPerfilHelper;
import com.alidasoftware.pos.model.Modulo;
import com.alidasoftware.pos.model.Perfil;
 
public class PerfilBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = -4788161105338034722L;
	
	private List<Perfil> perfiles;
	private Perfil selected;
	private PerfilFacade perfilFacade;
	
	// Lista de modulos
	private List<Modulo> modulos;
	private List<Modulo> selectedModulos;
	private Modulo moduloSelected;
	private ModuloFacade moduloFacade;
	private List<String> modulosGeneral;
	private String categoriaSelected;
	private boolean readOnly;
	
	// Helper perfil modulos
	private List<ModuloPerfilHelper> listModulos;
	
	// Prueba
	private Map<Integer, Modulo> listaModulos;
	
	// Filtros
	private String searchNombre;

	public PerfilBean() {
		searchNombre = "";
		loadPerfiles();
	}
	
	public void doSomething(AjaxBehaviorEvent event){
		if (event != null ) {
			Modulo aux = (Modulo)event.getComponent().getAttributes().get("modulo");
			if(((SelectBooleanCheckbox) event.getSource()).isSelected() == true){
				System.out.println("El modulo seleccionado " + aux.getNombre());
				listaModulos.put(aux.getIdModulo(), aux);
			} else {
				System.out.println("El modulo deseleccionado " + aux.getNombre());
				listaModulos.remove(aux.getIdModulo());
			}
	        
	        
		}
	}
	
	 public void onRowSelect(SelectEvent event) {
		 System.out.println("Realizando busqueda de modulos en base a categoria seleccionada");
		 boolean finded = false;
		 try {
			modulos = getModuloFacade().listAllByCategoria(categoriaSelected);
			listModulos = new ArrayList<ModuloPerfilHelper>();
			System.out.println("Modulos obtenidos " + modulos.size());
			for(Modulo modulo : modulos){
				for(Modulo aux : listaModulos.values()){
					if(modulo.getIdModulo() == aux.getIdModulo()){
						System.out.println("encontro uno seleccionado");
						finded = true;
						break;
					}
				}
				listModulos.add(new ModuloPerfilHelper(modulo, finded));
				finded = false;
			}
			System.out.println("la lista de modulos a mostrar es: " + listModulos.size());
			
		} catch (AlidaPosException e) {
			System.out.println("Error al obtener la lista de modulos por categoria seleccionada : " + e.getMessage());
		}
	 }
	
	public void loadPerfiles(){
		try {
			perfiles = getPerfilFacade().listAll();
		} catch (AlidaPosException e) {
			System.out.println("Ocurrio un error al obtener los perfiles " + e.getMessage());
		}
	}
	
	public void inicializaModulosGenerales(){
		modulos = new ArrayList<Modulo>();
	    listModulos = new ArrayList<ModuloPerfilHelper>();
	    selectedModulos = new ArrayList<>();
		modulosGeneral = new ArrayList<>();
		modulosGeneral.add("Catalogo");
		modulosGeneral.add("Operacion");
		modulosGeneral.add("Reporte");
		categoriaSelected = "";
	    listaModulos = new HashMap<Integer, Modulo>();
	}
	
	public void prepareCreate() {
       selected = new Perfil();
       inicializaModulosGenerales();
    }
	
	public void prepareUpdate(){
		readOnly = false;
		inicializaModulosGenerales();
		for(Modulo aux: selected.getModulos()){
			listaModulos.put(aux.getIdModulo(), aux);
		}
	}
	
	public boolean checkDuplicatedCode() {
		boolean result = false;
		try {
			Perfil perfilAux = getPerfilFacade().findPerfilByNombre(selected.getNombre());
			if (perfilAux != null) {
					selected.setNombre("");
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"El nombre ya existe. Debe seleccionar otro nombre", "Debe seleccionar otro nombre"));
					result = true;
				displayErrorMessageToUser("Debe seleccionar otro nombre");
			}		
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser(ex.getMessage());
		}
		return result;
	}
	
	public void create() {
		try {
			
			if(checkDuplicatedCode()){
				return;
			}
			System.out.println("Nombre : " + selected.getNombre());
			System.out.println("Descripcion : " + selected.getDescripcion());
			System.out.println("la lista de modulos tiene : " + listaModulos.size());			
			selected.setActivo(true);
			List<Perfil> perfiles = new ArrayList<Perfil>();
			perfiles.add(selected);
			for(Modulo aux : listaModulos.values()){
				Modulo auxiliar = getModuloFacade().findModuloById(aux.getIdModulo());
				auxiliar.setPerfiles(perfiles);
				selectedModulos.add(auxiliar);
			}
			for(Modulo item : selectedModulos){
				System.out.println(" nombre : " + item.getNombre());
				System.out.println(" descripcion : " + item.getDescripcion());
			}
			selected.setModulos(selectedModulos);
			getPerfilFacade().createPerfil(selected);
            closeDialog("PerfilCreateDialog");
            displayInfoMessageToUser("El registro se ha creado");
            loadPerfiles();
        } catch (AlidaPosException ex) {
        	System.out.println("Hubo un error al momento de crear el modulo perfil");
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
		try {		
			System.out.println("Nombre : " + selected.getNombre());
			System.out.println("Descripcion : " + selected.getDescripcion());
			System.out.println("la lista de modulos tiene : " + listaModulos.size());			
			List<Perfil> perfiles = new ArrayList<Perfil>();
			perfiles.add(selected);
			for(Modulo aux : listaModulos.values()){
				Modulo auxiliar = getModuloFacade().findModuloById(aux.getIdModulo());
				auxiliar.setPerfiles(perfiles);
				selectedModulos.add(auxiliar);
			}
			for(Modulo item : selectedModulos){
				System.out.println(" nombre : " + item.getNombre());
				System.out.println(" descripcion : " + item.getDescripcion());
			}
			selected.setModulos(selectedModulos);
			getPerfilFacade().updatePerfil(selected);
            closeDialog("PerfilEditDialog");
            displayInfoMessageToUser("El registro se ha actualizado");
            loadPerfiles();
        } catch (AlidaPosException ex) {
        	System.out.println("Hubo un error al momento de crear el modulo perfil");
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
			selected.setActivo(false);
			getPerfilFacade().updatePerfil(selected);
            displayInfoMessageToUser("El registro se ha eliminado");
            loadPerfiles();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	

	public void onRowDblClckSelect(SelectEvent event) {  
		if (event != null && event.getObject() != null) {
			prepareUpdate();
			readOnly = true;
			selected = (Perfil) event.getObject();
	        viewRecord();	        
		}
    }
	
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("PerfilEditDlg");
	}

	public void doSearch() {
		System.out.println("Realizando busqueda por ... " + searchNombre);
		try {
			perfiles = getPerfilFacade().searchByNombre(searchNombre);
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	searchNombre = "";
		RequestContext.getCurrentInstance().update("PerfilListForm:perfilListPanel");
	}
	

	public PerfilFacade getPerfilFacade() {
        if (perfilFacade == null) {
            perfilFacade = new PerfilFacade();
        }
        return perfilFacade;
    }
	
	public ModuloFacade getModuloFacade(){
		if (moduloFacade == null) {
			moduloFacade = new ModuloFacade();
        }
        return moduloFacade;
	}
	
	public String getSearchNombre() {
		return searchNombre;
	}

	public void setSearchNombre(String searchNombre) {
		this.searchNombre = searchNombre;
	}
	
	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public Perfil getSelected() {
		return selected;
	}

	public void setSelected(Perfil selected) {
		this.selected = selected;
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

	public Modulo getModuloSelected() {
		return moduloSelected;
	}

	public void setModuloSelected(Modulo moduloSelected) {
		this.moduloSelected = moduloSelected;
	}

	public List<String> getModulosGeneral() {
		return modulosGeneral;
	}

	public void setModulosGeneral(List<String> modulosGeneral) {
		this.modulosGeneral = modulosGeneral;
	}

	public String getCategoriaSelected() {
		return categoriaSelected;
	}

	public void setCategoriaSelected(String categoriaSelected) {
		this.categoriaSelected = categoriaSelected;
	}

	public List<Modulo> getSelectedModulos() {
		return selectedModulos;
	}

	public void setSelectedModulos(List<Modulo> selectedModulos) {
		this.selectedModulos = selectedModulos;
	}
	
	public List<ModuloPerfilHelper> getListModulos() {
		return listModulos;
	}

	public void setListModulos(List<ModuloPerfilHelper> listModulos) {
		this.listModulos = listModulos;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	
}
