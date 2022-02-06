package com.alidasoftware.pos.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.alidasoftware.pos.facade.CajaFacade;
import com.alidasoftware.pos.model.Caja;

@FacesConverter ("cajaConverter")
public class CajaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			throw new ConverterException(new FacesMessage("No existe la Caja..."));
		}
		try {
			CajaFacade facade = new CajaFacade();
			return facade.findCaja(Integer.valueOf(arg2));			
		} catch (Exception ex) {			
			throw new ConverterException(new FacesMessage("No se puede convertir " + arg2 + " a Caja"));
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {	
		if (arg2 instanceof Caja) {
			return String.valueOf(((Caja) arg2).getIdcaja());
		}
		return "";
	}
	
}
