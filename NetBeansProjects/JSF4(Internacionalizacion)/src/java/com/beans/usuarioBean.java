/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Javy
 */
@ManagedBean
@SessionScoped
public class usuarioBean implements Serializable {

    private String localidad;
    private static Map<String, Object> lstPaises;
    public usuarioBean() {
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Map<String, Object> getLstPaises() {
        return lstPaises;
    }

    public static void setLstPaises(Map<String, Object> lstPaises) {
        usuarioBean.lstPaises = lstPaises;
    }

    static
    {
        lstPaises= new LinkedHashMap<String, Object>();
        
        lstPaises.put("English", Locale.ENGLISH);
        lstPaises.put("Frances", Locale.FRENCH);
        
        Locale espaniol= new Locale("ES");
        lstPaises.put("Español",espaniol);
    }
    
    public void localidadChanged(ValueChangeEvent e)
    {
        String newLocaleValue= e.getNewValue().toString();
        for(Map.Entry<String, Object> entry: lstPaises.entrySet())
        {
            if(entry.getValue().toString().equals(newLocaleValue))
            {
                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)entry.getValue());
            }
        }
    }
    
}
