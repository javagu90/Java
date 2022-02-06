package com.alidasoftware.pos.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.alidasoftware.pos.facade.EmpleadoFacade;
import com.alidasoftware.pos.model.Empleado;

@FacesConverter ("empleadoConverter")
public class EmpleadoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("args2 : " + arg2);
		if (arg2 == null || arg2.isEmpty()) {
			throw new ConverterException(new FacesMessage("No existe el empleado..."));
		}
		
		try {
			EmpleadoFacade facade = new EmpleadoFacade();
			return facade.findById(Integer.valueOf(arg2));			
		} catch (Exception ex) {			
			throw new ConverterException(new FacesMessage("No se puede convertir " + arg2 + " a Empleado"));
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof Empleado) {
			return String.valueOf(((Empleado) arg2).getIdempleado());
		}
		return "";
	}

}
