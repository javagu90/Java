package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.ClienteFacade;
import com.alidasoftware.pos.facade.FacturaFacade;
import com.alidasoftware.pos.facade.VentaFacade;
import com.alidasoftware.pos.model.Cliente;
import com.alidasoftware.pos.model.Detalleventa;
import com.alidasoftware.pos.model.Factura;
import com.alidasoftware.pos.model.Persona;
import com.alidasoftware.pos.model.Venta;
import com.alidasoftware.pos.util.CreateXmlFile;;

public class FacturacionBean  extends AbstractBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Factura selected;
	private List<Factura> facturas;
	private FacturaFacade facturaFacade;
	
	private Cliente cliente;
	private ClienteFacade clienteFacade;
	
	private Venta venta;
	private VentaFacade ventaFacade;
	
	private List<Detalleventa> listaDetalleVenta;
	
	public FacturacionBean(){
		loadFacturas();
	}
	
	public void loadFacturas(){
		try{
			facturas = getFacturaFacade().listAll();
		} catch (Exception ex){
			System.out.println("Error al cargar las facturas " + ex.getMessage());
		}
	}
	
	public void prepareCreate(){
		selected = new Factura();
		selected.setFecha(new Date());
		Cliente asdCliente = new Cliente();
		asdCliente.setPersona(new Persona());
		venta = new Venta();
		venta.setCliente(asdCliente);
		selected.setVenta(venta);
		System.out.println("entro a preparecreate");
	}
	
	public void create(){
		System.out.println("creando la factura");
		if(cliente.getPersona().getRfc().equals("XAXX010101000")){
			System.out.println("El rfc del cliente sigue siendo el generico ");
			displayInfoMessageToUser("Debe ingresar un RFC de cliente válido.");
			return;
		}
		com.alidasoftware.pos.util.CreateXmlFile.getXml(selected);
	}
	
	public void cancel(){
		System.out.println("entro a cancelar la factura");
	}
	
	public void folioListener(){
		System.out.println("entro al listener luego del enter");
		System.out.println("el folio a buscar es : " + selected.getVenta().getFolio());
		try {
			Venta aux = getVentaFacade().findVentaByFolio(selected.getVenta().getFolio());
			if(aux == null){
				System.out.println("Mandar mensaje que ese numero de folio no existe");
//				FacesContext
//				.getCurrentInstance()
//				.addMessage(
//						null,
//						new FacesMessage(
//								FacesMessage.SEVERITY_INFO,
//								"No existe el folio.",
//								"No existe el folio, verifiquelo por favor."));
				displayInfoMessageToUser("El folio no existe.");
			} else {
				selected.setVenta(aux);
				System.out.println("Cliente es : " + selected.getVenta().getCliente().getPersona().getNombre());
				cliente = selected.getVenta().getCliente();
				if(cliente.getPersona().getRfc().equals("XAXX010101000")){
					System.out.println("RFC generico.");
					displayInfoMessageToUser("Ingrese RFC del cliente.");
				}
			}
		} catch (Exception ex){
			System.out.println("Error al obtener la venta " + ex.getMessage());
		}
		
		System.out.println("Termina el Listener");
	}
	
	public List<Cliente> completeCliente(String query) {
		List<Cliente> aux = new ArrayList<Cliente>();
		try {
			aux = getClienteFacade().findClienteByRfcLike(query);
		} catch (AlidaPosException ex) {			
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }			
		return aux; 
	}
	
	public void handleSelectCliente(SelectEvent event){
		Cliente clienteSelected = (Cliente) event.getObject();
		if (clienteSelected != null && clienteSelected.getIdcliente() != 0) {
			selected.getVenta().setCliente( (Cliente) event.getObject() );
		}
	}
	
	public ClienteFacade getClienteFacade(){
		if(clienteFacade == null){
			return new ClienteFacade();
		}
		return clienteFacade;
	}
	
	public FacturaFacade getFacturaFacade(){
		if(facturaFacade == null){
			facturaFacade = new FacturaFacade();
		}
		return facturaFacade;
	}
	
	public VentaFacade getVentaFacade(){
		if(ventaFacade == null){
			ventaFacade = new VentaFacade();
		}
		return ventaFacade;
	}

	public Factura getSelected() {
		return selected;
	}

	public void setSelected(Factura selected) {
		this.selected = selected;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public List<Detalleventa> getListaDetalleVenta() {
		return listaDetalleVenta;
	}

	public void setListaDetalleVenta(List<Detalleventa> listaDetalleVenta) {
		this.listaDetalleVenta = listaDetalleVenta;
	}

}
