package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.MunicipioFacade;
//import com.alidasoftware.pos.model.Estado;
import com.alidasoftware.pos.model.Municipio;
//import com.alidasoftware.pos.model.Usuario;
//import com.alidasoftware.pos.util.Debug;
import com.alidasoftware.pos.util.Utils;
//import com.alidasoftware.pos.util.Debug.User;
 
public class MunicipioBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 6275591448719976691L;

	private MunicipioFacade municipioFacade;
	private List<Municipio> municipios;
	private List<Municipio> municipiosFiltrados;
	private Municipio selected;
	
	public MunicipioBean() {
		loadMunicipios();
	}
	
	public MunicipioFacade getMunicipioFacade() {
        if (municipioFacade == null) {
            municipioFacade = new MunicipioFacade();
        }
        return municipioFacade;
    }

	public Municipio getSelected() {
		return selected;
	}

	public void setSelected(Municipio selected) {
		this.selected = selected;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public List<Municipio> getMunicipiosFiltrados() {
		return municipiosFiltrados;
	}

	public void setMunicipiosFiltrados(List<Municipio> municipiosFiltrados) {
		this.municipiosFiltrados = municipiosFiltrados;
	}
	
	public Municipio prepareCreate() {
        selected = new Municipio();
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
            getMunicipioFacade().createMunicipio(selected);
            closeDialog();
            displayInfoMessageToUser("El registro se ha creado");
            loadMunicipios();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void update() {
		try {
            getMunicipioFacade().updateMunicipio(selected);
            closeDialog();
            displayInfoMessageToUser("El registro se ha actualizado");
            loadMunicipios();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	public void destroy() {
		try {
            getMunicipioFacade().deleteMunicipio(selected);
            closeDialog();
            displayInfoMessageToUser("El registro se ha eliminado");
            loadMunicipios();
            prepareCreate();
        } catch (AlidaPosException ex) {
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
	}
	
	private void loadMunicipios() {
		try {
			municipios = getMunicipioFacade().listAll();
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }


    public List<Municipio> queryMunicipios(String query) {
//    	/**
//    	 * Se obtiene una variable atributo del contexto. Variable Usuario Actual
//    	 */
//        FacesContext context = FacesContext.getCurrentInstance();
//        Estado usuarioActual = (Estado) UIComponent.getCurrentComponent(context).getAttributes().get("estadoseleccionado");                		    	
//    	Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"Estado Seleccionado: " + usuarioActual.getNombre());
    	
    	//comprobación inicial
    	if (municipios == null)
			loadMunicipios();
    	//lista de retorno
    	List<Municipio> sortedStateList = new ArrayList<Municipio>();
    	//lista de distancias
    	List<Integer> distanceList = new ArrayList<Integer>();
    	//Obtenemos la lista de distancias
        Iterator<Municipio> iteratorMunicipios = municipios.iterator();
        Integer currentDistance;
        Municipio currentMunicipio;
        Integer minimumDistance = Integer.MAX_VALUE;    
        String nombreMunicipio_parcial;
        int i;
    	ArrayList<String> tokens = new ArrayList<String>();

        while(iteratorMunicipios.hasNext()){
        	currentMunicipio = iteratorMunicipios.next();
        	
        	//Tokenizar el nombre del municipio y buscar por la mejor distancia
        	tokens.clear();		
        	Scanner tokenize = new Scanner(currentMunicipio.getNombre());
        	while (tokenize.hasNext()) {
        	    tokens.add(tokenize.next());
        	}
        	tokenize.close();
        	Integer min_distance = Integer.MAX_VALUE;
        	Iterator<String> token_iterator = tokens.iterator();
        	while (token_iterator.hasNext()) {
        		nombreMunicipio_parcial = token_iterator.next();
                //fix nombre parcial municipio
            	if (nombreMunicipio_parcial.length() > query.length()){
            		nombreMunicipio_parcial = nombreMunicipio_parcial.substring(0, query.length());
    			}

               	//calcular distancia
            	currentDistance = Utils.stringsDistance(query, nombreMunicipio_parcial,false);
            	if (currentDistance < min_distance) {
            		min_distance = currentDistance;
				}
        	}
        	currentDistance = min_distance;
        	
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
            	sortedStateList.add(currentMunicipio);
            	minimumDistance = currentDistance;
            }else{
            	if (currentDistance == minimumDistance) {           
                	distanceList.add(currentDistance);
                	sortedStateList.add(currentMunicipio);
            	}
            }        
        }
//        //DEBUG
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

