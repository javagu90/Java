package com.alidasoftware.pos.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.alidasoftware.pos.facade.MunicipioFacade;
import com.alidasoftware.pos.model.Municipio;

 
@FacesConverter ("municipioConverter")
public class MunicipioConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			   throw new ConverterException(new FacesMessage("No existe el municipio..."));
			  }
			  try {
			   MunicipioFacade facade = new MunicipioFacade();
			   return facade.findMunicipio(Integer.valueOf(arg2));
			  } catch (Exception ex) {   
			   throw new ConverterException(new FacesMessage("No existe el municipio '" + arg2 + "'"));
			  }
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof Municipio) {
			   return String.valueOf(((Municipio) arg2).getIdmunicipio());
			  }
			  return "";
	}

}
