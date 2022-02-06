package com.alidasoftware.pos.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.alidasoftware.pos.facade.TipoVencimientoFacade;
import com.alidasoftware.pos.model.Tipovencimiento;

@FacesConverter ("tipoVencimientoConverter")
public class TipoVencimientoConverter implements Converter {

	// http://www.mastertheboss.com/jsf/develop-custom-jsf-converters
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
    	//Debug.print(User.PEDRO, new Exception().getStackTrace()[0],"value : " + arg2);
		if (arg2 == null || arg2.isEmpty()) {
			throw new ConverterException(new FacesMessage("No existe tipo vencimiento..."));
		}
		try {
			TipoVencimientoFacade facade = new TipoVencimientoFacade();
			return facade.findTipoVencimiento(Integer.valueOf(arg2));			
		} catch (Exception ex) {			
			throw new ConverterException(new FacesMessage("No se puede convertir " + arg2 + " a TipoVencimiento"));
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {	
		if (arg2 instanceof Tipovencimiento) {
			return String.valueOf(((Tipovencimiento) arg2).getIdtipovencimiento());
		}
		return "";
	}

}
