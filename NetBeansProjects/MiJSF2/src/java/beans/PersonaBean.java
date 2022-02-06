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
public class PersonaBean {

    /**
     * Creates a new instance of PersonaBean
     */
    private Persona persona;
    private static List<Persona> lstPersona =new ArrayList();
    public PersonaBean() {
        this.persona=new Persona();
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getLstPersona() {
        return lstPersona;
    }

    public void setLstPersona(List<Persona> lstPersona) {
        PersonaBean.lstPersona = lstPersona;
    }
    
    public void agregarPersona()
    {
        PersonaBean.lstPersona.add(this.persona);
    }
    public void eliminarPersona(Persona per)
    {
        PersonaBean.lstPersona.remove(per);
    }
}
