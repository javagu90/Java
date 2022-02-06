/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import classes.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Javy
 */
@ManagedBean
@RequestScoped
public class personaBean {

    private Persona persona= new Persona();
    private static List <Persona> lstPersonas= new ArrayList();
    
    public personaBean() {
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getLstPersonas() {
        return lstPersonas;
    }

    public void setLstPersonas(List<Persona> lstPersonas) {
        this.lstPersonas = lstPersonas;
    }
    
    public void agregarPersona()
    {
        this.lstPersonas.add(this.persona);
    }
    public void eliminarPersona(Persona p)
    {
        personaBean.lstPersonas.remove(p);
    }
    
}
