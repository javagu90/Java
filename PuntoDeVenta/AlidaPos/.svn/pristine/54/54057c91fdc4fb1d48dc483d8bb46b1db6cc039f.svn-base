package com.alidasoftware.pos.util;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.alidasoftware.pos.bean.ApplicationBean;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.ProductoFacade;
import com.alidasoftware.pos.model.Apartado;
import com.alidasoftware.pos.model.Categoriaproducto;
import com.alidasoftware.pos.model.Cliente;
import com.alidasoftware.pos.model.Contacto;
import com.alidasoftware.pos.model.Direccion;
import com.alidasoftware.pos.model.Empleado;
import com.alidasoftware.pos.model.Pagoapartado;
import com.alidasoftware.pos.model.Persona;
import com.alidasoftware.pos.model.Producto;
import com.alidasoftware.pos.model.Promocion;
import com.alidasoftware.pos.model.Usuario;
import com.alidasoftware.pos.model.Venta;
import com.alidasoftware.pos.model.Vista;

public class Functions {
	
	public static float getApartadoPagoMonto(Apartado apartado) {
		float total = 0.0f;
		List<Pagoapartado> list = apartado.getPagoapartados();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				total += (list.get(i).getCantidad() - list.get(i).getCambio());
				//System.out.println("total :" + total + " - cantidad : "  + list.get(i).getCantidad() + " - cambio : " + list.get(i).getCambio());
			}
		}
		return total;
	}
	
	public static int getApartadoPagoCount(Apartado apartado) {
		if (apartado.getPagoapartados() != null) {
			return apartado.getPagoapartados().size();
		}
		return 0;
	}
	
	public static String getStatusDescription(boolean status) {
		return ApplicationBean.getStatusDescription(status);
	}
	
	public static String valeStatus(int idStatus) {
		return ApplicationBean.getSelectItemLabel(ApplicationBean.MODULE_VALES, idStatus);
	}
	
	public static String apartadoStatus(int idStatus) {
		return ApplicationBean.getSelectItemLabel(ApplicationBean.MODULE_APARTADO, idStatus);
    }
	
	public static boolean showDeleteApartado(int idStatus) {
		String status = apartadoStatus(idStatus);
		if (status.equals(ApplicationBean.STATUS_VALUE_OPEN) ||
		    status.equals(ApplicationBean.STATUS_VALUE_GENERATED)) {
			return true;
		}
		return false;
	}
	
	public static boolean showEditApartado(int idStatus) {
		String status = apartadoStatus(idStatus);
		if (status.equals(ApplicationBean.STATUS_VALUE_GENERATED)) {
			return true;
		}
		return false;
	}
	
	public static boolean showAddPaymentApartado(int idStatus) {
		String status = apartadoStatus(idStatus);
		if (status.equals(ApplicationBean.STATUS_VALUE_GIVEN) ||
			status.equals(ApplicationBean.STATUS_VALUE_CANCEL) ||
			status.equals(ApplicationBean.STATUS_VALUE_CLOSE) ||
			status.equals(ApplicationBean.STATUS_VALUE_GENERATED)) {
			return false;
		}
		return true;
	}
	
	public static boolean disabledEditApartadoButton(Apartado apartado) {
		if (apartado != null && showDeleteApartado(apartado.getStatus())) {
			return false;
		}
		return true;
	}	
	
	public static String vistaStatus(int idStatus) {
		return ApplicationBean.getSelectItemLabel(ApplicationBean.MODULE_VISTA, idStatus);
    }
	
	public static boolean showDeleteVista(int idStatus) {
		String status = vistaStatus(idStatus);
		if (status.equals(ApplicationBean.STATUS_VALUE_OPEN) ||
		    status.equals(ApplicationBean.STATUS_VALUE_GENERATED)) {
			return true;
		}
		return false;
	}
	
	public static boolean disabledEditViewButton(Vista view) {
		if (view != null && showDeleteVista(view.getStatus())) {
			return false;
		}
		return true;
	}
	
	public static String ventaStatus(int idStatus) {
		return ApplicationBean.getSelectItemLabel(ApplicationBean.MODULE_VENTA, idStatus);
    }
	
	public static boolean showDeleteVenta(int idStatus) {
		String status = ventaStatus(idStatus);
		if (status.equals(ApplicationBean.STATUS_VALUE_OPEN) ||
			status.equals(ApplicationBean.STATUS_VALUE_GENERATED)) {
			return true;
		}
		return false;
	}
	
	public static boolean disabledEditVentaButton(Venta sale) {
		if (sale != null && showDeleteVenta(sale.getStatus())) {
			return false;
		}
		return true;
	}
	
	public static String ContactFullDirection(Usuario usr) {
		if	(usr != null) {
			return "hhh";//persona.getContacto().GetDireccionesCompletas();
		}
		return "";
	}
	
	public static String clientName(Cliente client) {
		if	(client != null) {
			String code = client.getClavecliente();
			String name = client.getPersona().getNombre();
			String surname1 = client.getPersona().getApellidopaterno();
			String surname2 = client.getPersona().getApellidomaterno();
			return code + " - " + name + " " + surname1 + " " + surname2;
		}
		return "";
	}
	
	public static String personName(Persona persona) {
		if (persona != null) {
			String name = persona.getNombre();
			String surname1 = persona.getApellidopaterno();
			String surname2 = persona.getApellidomaterno();
			return name + " " + surname1 + " " + surname2;
		}
		return "";
	}
	
	public static String empleadoInfo(Empleado empleado) {
		if (empleado != null) {
			String code = empleado.getClaveempleado() + " ";
			return code + personName(empleado.getPersona());
		}
		return "";
	}
	
	public static String contactInfo(Contacto contact) {
		if (contact != null) {
			String direc = "";
			if (contact.getDirecciones() != null && contact.getDirecciones().size() > 0) {
				Direccion dir = contact.getDirecciones().get(0);
				direc = "Dirección : " + dir.getCalle() + " " + dir.getNumext();
				if (!dir.getNumint().trim().equals("")) {
					direc += " int. " + dir.getNumint();
				}				
				direc += " Colonia : " + dir.getColonia();
			}
			String phone = "";
			String email = "";
			if (!contact.getTelefono1().trim().equals("")) {
				phone = " Teléfono : " + contact.getTelefono1();
				if (!contact.getTelefono2().trim().equals("") ) {
					phone += ", " + contact.getTelefono2();
				}
			} else if (!contact.getTelefono2().trim().equals("")) {
				phone = " Teléfono : " + contact.getTelefono2();
			}
			if (!contact.getCorreoelectronico().trim().equals("")) {
				email = " Correo : " + contact.getCorreoelectronico();
			}
			return direc + " " + phone + " " + email;
		}
		return "";
	}

	public static String productNameCode(Producto product) {
		if (product != null) {
			String code = product.getClave();
			String name = product.getNombre();
			return code + " - " + name;
		}
		return "";
	}
	
	public static float obtenImporte(float cantidad, float precioUnitario) {
		return cantidad * precioUnitario;
	}
	
	public static float getPorcentaje(float numero){
		return 100*numero;
	}
	public static String getTipoPromocion(Promocion promocion){
		String ret = "";
		if (promocion != null) {		
			if (promocion.getIdproducto() == -1) {
				ret = "Promoción por categorías";
			}else{
				ret = "Promoción por producto";				
			}
		}
		return ret;
	}
	
	public static String getElementosEnPromocion(Promocion promocion){
		String ret = "";
		if (promocion != null) {		
			if (promocion.getIdproducto() == -1) {
		        StringBuilder builder = new StringBuilder();   		        
		        for(Object item : promocion.getCategoriaproductos()) {
		        	if (builder.length()>0) {
		        		builder.append(", ");
					}
		            builder.append(((Categoriaproducto) item).getNombre());
		        }        
				ret = builder.toString();							
			}else{
				ProductoFacade productoFacade = new ProductoFacade();
				try {
					Producto p = productoFacade.findProducto(promocion.getIdproducto());
					ret = productNameCode(p);
				} catch (AlidaPosException e) {
					return ret;
				}
				
			}
		}
		return ret;
	}
	
	public static String getCategoriasFromProducto(Producto product){
		String ret = "";
		if (product != null) {		
			for (Categoriaproducto item : product.getCategoriaproductos()) {
				if (!ret.equals("")) {
					ret = ret + ", ";
				}
				ret = ret + item.getNombre();
			}
		}
		return ret;
	}	
	
	public static String getTipoDias(String tipoDias){
		if (tipoDias.equals(ApplicationBean.TYPE_DAY_BUSINESS)) {
			return "Laborales";
		}
		if (tipoDias.equals(ApplicationBean.TYPE_DAY_CALENDAR)) {
			return "Naturales";
		}
		return "Naturales";		
	}
	
	public static String getMD5(String cadena){
		String result;
		result = DigestUtils.md5Hex(cadena);
		return result;
	}
	
}
