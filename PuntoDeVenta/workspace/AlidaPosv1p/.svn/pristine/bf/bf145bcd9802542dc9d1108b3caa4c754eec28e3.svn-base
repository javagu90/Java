package com.alidasoftware.pos.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.alidasoftware.pos.facade.ProductoFacade;
import com.alidasoftware.pos.model.Producto;

@FacesConverter ("productConverter")
public class ProductConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 == null || arg2.isEmpty()) {
			throw new ConverterException(new FacesMessage("No existe el producto..."));
		}
		try {
			ProductoFacade facade = new ProductoFacade();
			return facade.findProducto(Integer.valueOf(arg2));
		} catch (Exception ex) {			
			throw new ConverterException(new FacesMessage("No existe el producto '" + arg2 + "'"));
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 instanceof Producto) {
			return String.valueOf(((Producto) arg2).getIdproducto());
		}
		return "";
	}

}
