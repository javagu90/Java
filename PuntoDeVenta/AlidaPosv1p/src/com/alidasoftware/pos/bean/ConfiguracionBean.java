package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alidasoftware.pos.model.Configuracion;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.ConfiguracionFacade;
 
public class ConfiguracionBean extends AbstractBean implements Serializable{

	private static final long serialVersionUID = -4788161105338034722L;
	
	private List<Configuracion> listaConfiguracion;
	
	private Configuracion configuracionPago;
	private Configuracion configuracionPromociones;
	private Configuracion configuracionIva;
	
	private boolean efectivo;
	private boolean tarjeta;
	private boolean credito;
	private boolean vales;
	private boolean otro;
	
	private boolean promocionTodas;
	private boolean promocionMayorDesc;
	
	private String ivaSelected;
	
	private ConfiguracionFacade configuracionFacade;
	
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
		boolean formasPago = false;
		boolean promociones = false;
		boolean iva = false;
		try {
			listaConfiguracion = getConfiguracionFacade().listAll();
			System.out.println("El numero de configuraciones encontradas fue : " + listaConfiguracion.size());
			if(!listaConfiguracion.isEmpty()){
				for(Configuracion aux : listaConfiguracion){
					if(aux.getAtributo().equals("Formas de Pago")){
						formasPago = true;
						efectivo = aux.getValor1().equals("true") ? true : false;
						tarjeta = aux.getValor2().equals("true") ? true : false;
						credito = aux.getValor3().equals("true") ? true : false;
						vales = aux.getValor4().equals("true") ? true : false;
					}
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
				if(!formasPago){
					Configuracion nuevaConfiguracion = new Configuracion();
					nuevaConfiguracion.setAtributo("Formas de Pago");
					nuevaConfiguracion.setTipo("Arreglo");
					getConfiguracionFacade().createConfiguracion(nuevaConfiguracion);
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
			if(listaConfiguracion.isEmpty()){
				configuracionPago = new Configuracion();
				configuracionPago.setAtributo("Formas de Pago");
				configuracionPago.setTipo("Arreglo");
				configuracionPago.setValor1(efectivo ? "true" : "false");
				configuracionPago.setValor2(tarjeta ? "true" : "false");
				configuracionPago.setValor3(credito ? "true" : "false");
				configuracionPago.setValor4(vales ? "true" : "false");
				configuracionPago.setValor5(otro ? "true" : "false");
				getConfiguracionFacade().createConfiguracion(configuracionPago);
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
					if(aux.getAtributo().equals("Formas de Pago")){
						aux.setValor1(efectivo ? "true" : "false");
						aux.setValor2(tarjeta ? "true" : "false");
						aux.setValor3(credito ? "true" : "false");
						aux.setValor4(vales ? "true" : "false");
						aux.setValor5(otro ? "true" : "false");
						getConfiguracionFacade().updateConfiguracion(aux);
						System.out.println("despues de actualizar formas de pago");
					}
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
			displayInfoMessageToUser("La Configuraci?n ha sido actualizada.");
		} catch (AlidaPosException ex) {
			System.out.println("Hubo un error al momento de crear el modulo perfil");
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
