/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import com.classes.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

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
    private Persona persona= new Persona();
    private static List<Persona> lstPersonas= new ArrayList<>();
    public PersonaBean() {
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
    
    public void registrar()
    {
        this.lstPersonas.add(this.persona);
    }
    
    public void validar(FacesContext context, UIComponent toValidate, Object value)
    {
        context=FacesContext.getCurrentInstance();
        String texto=(String) value;
        if(!texto.equalsIgnoreCase("M")&& !texto.equalsIgnoreCase("F"))
        {
            ((UIInput)toValidate).setValid(false);
            context.addMessage(toValidate.getClientId(context), new FacesMessage("Sexo no valido"));
        }
    }
}
