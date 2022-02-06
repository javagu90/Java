package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alidasoftware.pos.model.Configuracion;
import com.alidasoftware.pos.model.Formapago;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.ConfiguracionFacade;
import com.alidasoftware.pos.facade.FormaPagoFacade;
 
public class ConfiguracionBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = -4788161105338034722L;
	
	private List<Configuracion> listaConfiguracion;
	
	private Configuracion configuracionPromociones;
	private Configuracion configuracionIva;
	
	private boolean efectivo;
	private boolean tarjeta;
	private boolean credito;
	private boolean vales;
	
	private boolean promocionTodas;
	private boolean promocionMayorDesc;
	
	private String ivaSelected;
	
	private ConfiguracionFacade configuracionFacade;
	private FormaPagoFacade formaPagoFacade;
	
	public FormaPagoFacade getFormaPagoFacade(){
		if(formaPagoFacade == null){
			formaPagoFacade = new FormaPagoFacade();
		}
		return formaPagoFacade;
	}
	
	public ConfiguracionFacade getConfiguracionFacade(){
		if(configuracionFacade == null){
			configuracionFacade = new ConfiguracionFacade();
		}
		return configuracionFacade;
	}
	
	
	public ConfiguracionBean() {
		System.out.println("funcion para inicializar los valores que se necesitan.");
		ivaSelected = "";
		listaConfiguracion = new ArrayList<Configuracion>();
		boolean promociones = false;
		boolean iva = false;
		try {
			
			// Se obtienen los valores del catalogo de formas de pago
			Formapago formaPagoEfectivo = getFormaPagoFacade().findFormaPagoByNombre("Efectivo");
			efectivo = formaPagoEfectivo.isActivo();
			
			Formapago formaPagoTarjeta = getFormaPagoFacade().findFormaPagoByNombre("Tarjeta");
			tarjeta = formaPagoTarjeta.isActivo();
			
			Formapago formaPagoCredito = getFormaPagoFacade().findFormaPagoByNombre("Crédito");
			credito = formaPagoCredito.isActivo();
			
			Formapago formaPagoVales = getFormaPagoFacade().findFormaPagoByNombre("Vale");
			vales = formaPagoVales.isActivo();
			
			listaConfiguracion = getConfiguracionFacade().listAll();
			System.out.println("El numero de configuraciones encontradas fue : " + listaConfiguracion.size());
			if(!listaConfiguracion.isEmpty()){
				for(Configuracion aux : listaConfiguracion){
					if(aux.getAtributo().equals("Promociones")){
						promociones = true;
						promocionTodas = aux.getValor1().equals("true") ? true : false;
						promocionMayorDesc = aux.getValor2().equals("true") ? true : false;
					}
					if(aux.getAtributo().equals("Iva")){
						iva = true;
						ivaSelected = aux.getValor1();
					}
				}
				if(!promociones){
					Configuracion nuevaConfiguracion = new Configuracion();
					nuevaConfiguracion.setAtributo("Promociones");
					nuevaConfiguracion.setTipo("Arreglo");
					getConfiguracionFacade().createConfiguracion(nuevaConfiguracion);
				}
				if(!iva){
					Configuracion nuevaConfiguracion = new Configuracion();
					nuevaConfiguracion.setAtributo("Iva");
					nuevaConfiguracion.setTipo("Valor");
					getConfiguracionFacade().createConfiguracion(nuevaConfiguracion);
				}
			}
			
		} catch (AlidaPosException e) {
			System.out.println("Ocurrio un error al inicializar " + e.getMessage());
		}
	}
	
	public void update() {
		System.out.println("el valor de ivaSelected " + ivaSelected);
		try {
			// Se actualiza el registro de efectivo
			Formapago formaPagoEfectivo = getFormaPagoFacade().findFormaPagoByNombre("Efectivo");
			formaPagoEfectivo.setActivo(efectivo);
			getFormaPagoFacade().updateFormaPago(formaPagoEfectivo);
			// Se actualiza el registro de tarjeta
			Formapago formaPagoTarjeta = getFormaPagoFacade().findFormaPagoByNombre("Tarjeta");
			formaPagoTarjeta.setActivo(tarjeta);
			getFormaPagoFacade().updateFormaPago(formaPagoTarjeta);
			// Se actualiza el registro de Credito
			Formapago formaPagoCredito = getFormaPagoFacade().findFormaPagoByNombre("Crédito");
			formaPagoCredito.setActivo(credito);
			getFormaPagoFacade().updateFormaPago(formaPagoCredito);
			// Se actualiza el registro de Vales
			Formapago formaPagoVales = getFormaPagoFacade().findFormaPagoByNombre("Vale");
			formaPagoVales.setActivo(vales);
			getFormaPagoFacade().updateFormaPago(formaPagoVales);
			System.out.println("despues de actualizar formas de pago");
			
			if(listaConfiguracion.isEmpty()){
				configuracionPromociones = new Configuracion();
				configuracionPromociones.setAtributo("Promociones");
				configuracionPromociones.setTipo("Arreglo");
				configuracionPromociones.setValor1(promocionTodas ? "true" : "false");
				configuracionPromociones.setValor2(promocionMayorDesc ? "true" : "false");
				getConfiguracionFacade().createConfiguracion(configuracionPromociones);
				configuracionIva = new Configuracion();
				configuracionIva.setAtributo("Iva");
				configuracionIva.setTipo("Valor");
				configuracionIva.setValor1(ivaSelected);
				getConfiguracionFacade().createConfiguracion(configuracionIva);
				
			} else {
				
				for(Configuracion aux : listaConfiguracion){
					if(aux.getAtributo().equals("Promociones")){
						aux.setValor1(promocionTodas ? "true" : "false");
						aux.setValor2(promocionMayorDesc ? "true" : "false");
						getConfiguracionFacade().updateConfiguracion(aux);
						System.out.println("despues de actualizar promociones");
					}
					if(aux.getAtributo().equals("Iva")){
						aux.setValor1(ivaSelected);
						getConfiguracionFacade().updateConfiguracion(aux);
						System.out.println("despues de actualizar el iva");
					}
				}
			}
			displayInfoMessageToUser("La Configuración ha sido actualizada.");
		} catch (AlidaPosException ex) {
			System.out.println("Hubo un error al momento de crear el modulo perfil");
			System.out.println("Error " + ex.getMessage());
			keepDialogOpen();
			displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}


	public List<Configuracion> getListaConfiguracion() {
		return listaConfiguracion;
	}


	public void setListaConfiguracion(List<Configuracion> listaConfiguracion) {
		this.listaConfiguracion = listaConfiguracion;
	}


	public boolean isEfectivo() {
		return efectivo;
	}


	public void setEfectivo(boolean efectivo) {
		this.efectivo = efectivo;
	}


	public boolean isTarjeta() {
		return tarjeta;
	}


	public void setTarjeta(boolean tarjeta) {
		this.tarjeta = tarjeta;
	}


	public boolean isCredito() {
		return credito;
	}


	public void setCredito(boolean credito) {
		this.credito = credito;
	}


	public boolean isVales() {
		return vales;
	}


	public void setVales(boolean vales) {
		this.vales = vales;
	}


	public boolean isPromocionTodas() {
		return promocionTodas;
	}


	public void setPromocionTodas(boolean promocionTodas) {
		this.promocionTodas = promocionTodas;
	}


	public boolean isPromocionMayorDesc() {
		return promocionMayorDesc;
	}


	public void setPromocionMayorDesc(boolean promocionMayorDesc) {
		this.promocionMayorDesc = promocionMayorDesc;
	}


	public String getIvaSelected() {
		return ivaSelected;
	}


	public void setIvaSelected(String ivaSelected) {
		this.ivaSelected = ivaSelected;
	}
	
}
