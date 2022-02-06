package com.alidasoftware.pos.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.alidasoftware.pos.facade.ClienteFacade;
import com.alidasoftware.pos.model.Cliente;

@FacesConverter ("clienteConverter")
public class ClienteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		//System.out.println("ClienteConverter GetAsObject : " + arg2);
		
		if (arg2 == null || arg2.isEmpty()) {
			throw new ConverterException(new FacesMessage("No existe el cliente..."));
		}
		
		try {
			ClienteFacade facade = new ClienteFacade();
			return facade.findCliente(Integer.valueOf(arg2));			
		} catch (Exception ex) {			
			throw new ConverterException(new FacesMessage("No se puede convertir " + arg2 + " a Cliente"));
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof Cliente) {
			return String.valueOf(((Cliente) arg2).getIdcliente());
		}
		return "";
	}
	
}
