package com.alidasoftware.pos.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.alidasoftware.pos.facade.TiendaFacade;
import com.alidasoftware.pos.model.Tienda;

@FacesConverter ("tiendaConverter")
public class TiendaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("arg2 : " + arg2);
		if (arg2 == null || arg2.isEmpty()) {
			throw new ConverterException(new FacesMessage("No existe la tienda..."));
		} else {
			try {
				TiendaFacade facade = new TiendaFacade();
				Tienda tienda = facade.findTienda(Integer.valueOf(arg2));
				System.out.println("tienda AsObject : " + tienda.getNombre());
				return tienda;
			} catch (Exception ex) {			
				throw new ConverterException(new FacesMessage("No se puede convertir " + arg2 + " a Tienda"));
			}
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {	
		//System.out.println("Tipo Tienda: " + arg2.getClass().toString());
		if (arg2 instanceof Tienda) {			
			String valor = String.valueOf(((Tienda) arg2).getIdtienda());
			System.out.println("tienda AsString: " + valor);
			return valor;
		}
		return "";
	}
}
