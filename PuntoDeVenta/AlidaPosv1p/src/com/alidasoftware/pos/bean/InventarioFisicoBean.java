package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.DetalleInventarioFacade;
import com.alidasoftware.pos.facade.InventarioFacade;
import com.alidasoftware.pos.facade.InventarioFisicoFacade;
import com.alidasoftware.pos.facade.ProductoFacade;
import com.alidasoftware.pos.facade.TiendaFacade;
import com.alidasoftware.pos.model.Detalleinventario;
import com.alidasoftware.pos.model.Detalleinventariofisico;
import com.alidasoftware.pos.model.Inventario;
import com.alidasoftware.pos.model.Inventariofisico;
import com.alidasoftware.pos.model.Producto;
import com.alidasoftware.pos.model.Tienda;
import com.alidasoftware.pos.model.Unidad;
import com.alidasoftware.pos.util.Debug;
import com.alidasoftware.pos.util.Utils;
import com.alidasoftware.pos.util.Debug.User;

public class InventarioFisicoBean extends AbstractBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Inventariofisico selected;
	private List<Inventariofisico> inventarios;
	
	private Producto producto;
	
	private List<Detalleinventariofisico> listaDetalleInventarioFisico;
	
	private Inventario inventarioTienda;
	
	private InventarioFisicoFacade inventarioFisicoFacade;
	private ProductoFacade productoFacade;
	private InventarioFacade inventarioFacade;
	private DetalleInventarioFacade detalleInventarioFacade;
	private TiendaFacade tiendaFacade;
	
	private Tienda tiendaActual;
	
	private SimpleDateFormat sdfTime;
	
	public InventarioFacade getInventarioFacade(){
		if(inventarioFacade==null){
			inventarioFacade = new InventarioFacade();
		}
		return inventarioFacade;
	}
	
	public DetalleInventarioFacade getDetalleInventarioFacade(){
		if(detalleInventarioFacade == null){
			detalleInventarioFacade = new DetalleInventarioFacade();
		}
		return detalleInventarioFacade;
	}
	
	public TiendaFacade getTiendaFacade(){
		if(tiendaFacade == null){
			tiendaFacade = new TiendaFacade();
		}
		return tiendaFacade;
	}
	
	public InventarioFisicoBean(){
		loadInventariosFisicos();
	}
	
	public void create(){
		System.out.println("entro al create");
		if(listaDetalleInventarioFisico.isEmpty()){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede crear un inventario fisico sin ningun producto registrado.") );
			return;
		}
		float costoTotal = 0f;
		Time horaFinal = new Time(new Date().getTime());
		Date fechaFinal = new Date();
		selected.setDetalleinventariofisicos(listaDetalleInventarioFisico);
		selected.setUsuario(getLoginBean().getUser());
		try {
			for(Detalleinventariofisico aux : listaDetalleInventarioFisico){
				// Se calculara el costo total del inventario fisico
				costoTotal = costoTotal + (aux.getProducto().getPrecio() * aux.getExistenciafisica());
			}
			System.out.println("el costo total de las cosas es : " + costoTotal);
			selected.setCostototal(costoTotal);
			selected.setFechaFin(fechaFinal);
			selected.setHorafin(horaFinal);
			inventarioFisicoFacade.createInventariofisico(selected);
			System.out.println("obtener el tamaño : " + selected.getDetalleinventariofisicos().size());
		} catch (Exception ex) {
			System.out.println("Error create inventario fisico : " + ex.getMessage());
		}
		closeDialog("InventarioFisicoCreateDialog");
		RequestContext.getCurrentInstance().execute("PF('InventarioFisicoViewDialog').show();");
		loadInventariosFisicos();
	}
	
	public void prepareCreate(){
		listaDetalleInventarioFisico = new ArrayList<Detalleinventariofisico>();
		selected = new Inventariofisico();
		Date fechaActual = new Date();
		Time hora = new Time(fechaActual.getTime());
		sdfTime = new SimpleDateFormat("hh:mm:ss");
		selected.setFechaInicio(fechaActual);
		selected.setHorainicio(hora);
		tiendaActual = getLoginBean().getUser().getEmpleado().getTienda();
		try {
			inventarioTienda = getInventarioFacade().findInventarioByTienda(tiendaActual);
		} catch(Exception ex) {
			System.out.println("Error al obtener el inventario de la tienda actual " + ex.getMessage());
		}
		
	}
	
	public void loadInventariosFisicos(){
		try{
			inventarios = getInventarioFisicoFacade().listAll();
		} catch(Exception ex) {
			System.out.println("Error al obtener los inventarios fisicos");
		}
	}
	
    private void deleteProductoFromList(int id){
		for (int i = 0; i < listaDetalleInventarioFisico.size(); i++) {	
			if (listaDetalleInventarioFisico.get(i).getProducto().getIdproducto() == id) {				
				listaDetalleInventarioFisico.remove(i);
				break;
			}
		}    	
    }
	
	 public void onCellEdit(CellEditEvent event) {
	    	
	    	int index = event.getRowIndex();
	        Float oldValue = (Float) event.getOldValue();
	        Float newValue = (Float) event.getNewValue();
	        
	    	//si el neuvo valor es <= 0 conservar antiguo valor
	        if (Float.compare(newValue, 0.0f) < 0) {
	        	System.out.println("entro");
	        	newValue = oldValue;
	        	displayErrorMessageToUser("El valor debe ser mayor que cero.");
	        } 
	        //si la cantidad es 0 se elimina la fila
	        if (listaDetalleInventarioFisico.get(index).getExistenciafisica() == 0) {
	        	deleteProductoFromList(listaDetalleInventarioFisico.get(index).getProducto().getIdproducto());
			}

	    }
	
	public List<Producto> completeProducto(String query) {
		List<Producto> aux = new ArrayList<Producto>();
		try {
			aux = getProductoFacade().FindProductLikeName(query);
		} catch (AlidaPosException ex) {			
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }			
		return aux; 
	}
	
	public void handleSelectProduct(SelectEvent event) {
		System.out.println("inventario tienda " + inventarioTienda.getNombre() + inventarioTienda.getIdinventario());
		producto = (Producto) event.getObject();
		if (producto != null && producto.getIdproducto() != 0) {
			Detalleinventariofisico currentProductoEnIF;
			
			int index = existsProduct(producto.getIdproducto());
			if (index == -1) {
				currentProductoEnIF = new Detalleinventariofisico();
				currentProductoEnIF.setProducto(producto);
				currentProductoEnIF.setInventariofisico(selected);
				currentProductoEnIF.setExistenciafisica(1);
				try{
					Detalleinventario DIaux = getDetalleInventarioFacade().findDetalleInventarioByProductTienda(producto.getIdproducto(), inventarioTienda.getIdinventario());
					if(DIaux!=null) {
						currentProductoEnIF.setExistenciasistema(DIaux.getExistenciaventa());
					} else {
						currentProductoEnIF.setExistenciasistema(0);
					}
				} catch (Exception ex) {
					System.out.println("Error al obtener el detalle inventario de la tienda " + ex.getMessage());
				}
				
				listaDetalleInventarioFisico.add(currentProductoEnIF);
			}else{
				listaDetalleInventarioFisico.get(index).setExistenciafisica(listaDetalleInventarioFisico.get(index).getExistenciafisica() + 1);
			}
			producto = new Producto();
			producto = null;
		}		
	}
	
	private int existsProduct(int idProduct) {		
		for (int i = 0; i < listaDetalleInventarioFisico.size(); i++) {
			if (listaDetalleInventarioFisico.get(i).getProducto().getIdproducto() == idProduct) {
				return i;
			}
		}
		return -1;
	}
	
	public void onRowDblClckSelect(SelectEvent event) {  
		if (event != null && event.getObject() != null) {
			selected = (Inventariofisico) event.getObject();
	        viewRecord();	        
		}
    }
	
	public void viewRecord() {
		RequestContext.getCurrentInstance().update("InventarioFisicoViewDlg");
	}
	
	public InventarioFisicoFacade getInventarioFisicoFacade(){
		if(inventarioFisicoFacade == null){
			inventarioFisicoFacade = new InventarioFisicoFacade();
		}
		return inventarioFisicoFacade;
	}
	
	public ProductoFacade getProductoFacade(){
		if(productoFacade == null){
			productoFacade = new ProductoFacade();
		}
		return productoFacade;
	}
	
	private LoginBean getLoginBean() {
		return (LoginBean) Utils.getManagedBean("loginBean");
	}

	public Inventariofisico getSelected() {
		return selected;
	}

	public void setSelected(Inventariofisico selected) {
		this.selected = selected;
	}

	public List<Inventariofisico> getInventarios() {
		return inventarios;
	}

	public void setInventarios(List<Inventariofisico> inventarios) {
		this.inventarios = inventarios;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Detalleinventariofisico> getListaDetalleInventarioFisico() {
		return listaDetalleInventarioFisico;
	}

	public void setListaDetalleInventarioFisico(
			List<Detalleinventariofisico> listaDetalleInventarioFisico) {
		this.listaDetalleInventarioFisico = listaDetalleInventarioFisico;
	}
}
