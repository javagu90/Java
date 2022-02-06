package com.alidasoftware.pos.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.alidasoftware.pos.facade.CategoriaProductoFacade;
import com.alidasoftware.pos.model.Categoriaproducto;

@FacesConverter ("categoriaProductoConverter")
public class CategoriaProductoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			throw new ConverterException(new FacesMessage("No existe el categoriaProducto..."));
		}
		try {
			CategoriaProductoFacade facade = new CategoriaProductoFacade();
			return facade.findCategoriaproducto(Integer.valueOf(arg2));			
		} catch (Exception ex) {			
			throw new ConverterException(new FacesMessage("No se puede convertir " + arg2 + " a CategoriaProducto"));
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof Categoriaproducto) {
			return String.valueOf(((Categoriaproducto) arg2).getIdcategoriaproducto());
		}
		return "";
	}
	
}
