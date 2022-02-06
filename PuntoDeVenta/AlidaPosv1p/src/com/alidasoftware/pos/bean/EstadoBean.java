package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.EstadoFacade;
import com.alidasoftware.pos.model.Estado;
import com.alidasoftware.pos.util.Utils;
 
public class EstadoBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 6275591448719976691L;

	private EstadoFacade estadoFacade;
	private List<Estado> estados;
	private List<Estado> estadosFiltrados;
	private Estado selected;
	
	public EstadoBean() {
		loadEstados();
	}
	
	public EstadoFacade getEstadoFacade() {
        if (estadoFacade == null) {
            estadoFacade = new EstadoFacade();
        }
        return estadoFacade;
    }

	public Estado getSelected() {
		return selected;
	}

	public void setSelected(Estado selected) {
		this.selected = selected;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public List<Estado> getEstadosFiltrados() {
		return estadosFiltrados;
	}

	public void setEstadosFiltrados(List<Estado> estadosFiltrados) {
		this.estadosFiltrados = estadosFiltrados;
	}
	
	public Estado prepareCreate() {
        selected = new Estado();
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
            getEstadoFacade().createEstado(selected);
            closeDialog();
            displayInfoMessageToUser("El registro se ha creado");
            loadEstados();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
		try {
            getEstadoFacade().updateEstado(selected);
            closeDialog();
            displayInfoMessageToUser("El registro se ha actualizado");
            loadEstados();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
            getEstadoFacade().deleteEstado(selected);
            closeDialog();
            displayInfoMessageToUser("El registro se ha eliminado");
            loadEstados();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	private void loadEstados() {
		try {
			estados = getEstadoFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	
    public List<Estado> queryEstados(String query) {    	  	
    	//comprobación inicial
    	if (estados == null)
			loadEstados();
    	//lista de retorno
    	List<Estado> sortedStateList = new ArrayList<Estado>();
    	//lista de distancias
    	List<Integer> distanceList = new ArrayList<Integer>();
    	//Obtenemos la lista de distancias
        Iterator<Estado> iteratorEstados = estados.iterator();
        Integer currentDistance;
        Integer minimumDistance = Integer.MAX_VALUE;        
        Estado currentEstado;
        String nombreEstado_parcial;
        int i;

        while(iteratorEstados.hasNext()){
        	currentEstado = iteratorEstados.next();
        	//fix nombre estado
        	if (currentEstado.getNombre().length() > query.length()){
        		nombreEstado_parcial = currentEstado.getNombre().substring(0, query.length());
			}else{
				nombreEstado_parcial = currentEstado.getNombre();
        	}
        	//calcular distancia	
        	currentDistance = Utils.stringsDistance(query,nombreEstado_parcial,false);
        	//encontrar el primer elemento menor en la lista de distancias
        	for (i = 0; i < distanceList.size(); i++) {
				if (currentDistance<distanceList.get(i)) {
					break;
				}
			}
        	
        	//se almacenan sólo los elementos con distancia menor
        	if (currentDistance < minimumDistance) {
        		distanceList.clear();
        		sortedStateList.clear();
            	distanceList.add(currentDistance);
            	sortedStateList.add(currentEstado);
            	minimumDistance = currentDistance;
            }else{
            	if (currentDistance == minimumDistance) {           
                	distanceList.add(currentDistance);
                	sortedStateList.add(currentEstado);
            	}
            }

        }
//    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0], "-------------");
//    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0], "-------------");
//    	for (int j = 0; j < sortedStateList.size(); j++) {
//        	Debug.print(User.PEDRO, new Exception().getStackTrace()[0], distanceList.get(j) + " : " + sortedStateList.get(j).getNombre());
//		}        
        return sortedStateList;
    }
    
    public void Show(ActionEvent action)
    {
    	//displayInfoMessageToUser(selected.getNombre());
    	System.out.println(selected.getNombre());
    }
    
    public void onItemSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
    }	
}

