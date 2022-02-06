/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javagu.pos.bean;

import com.javagu.pos.dao.PersonaDao;
import com.javagu.pos.model.Persona;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/**
 *
 * @author Javy
 */
@ManagedBean
@RequestScoped
public class PersonaBean 
{
    private Persona persona=new Persona();
    

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    public void registrar () throws Exception
    {
        PersonaDao dao;
        try
        {
            dao= new PersonaDao();
            dao.registrar(persona);
        }
        catch(Exception e)
        {
            throw e;
        }
    }
    
    public void observarNombre () throws Exception
    {
        PersonaDao dao;
        try
        {
            dao= new PersonaDao();
           persona.setNombre( dao.observarNombre());
        }
        catch(Exception e)
        {
            throw e;
        }
    }
}
