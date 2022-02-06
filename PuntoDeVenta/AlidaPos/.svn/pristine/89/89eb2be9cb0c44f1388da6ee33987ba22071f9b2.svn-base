package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.DevolucionFacade;
import com.alidasoftware.pos.facade.ProductoFacade;
import com.alidasoftware.pos.facade.ValeFacade;
import com.alidasoftware.pos.model.Detalledevolucion;
import com.alidasoftware.pos.model.Devolucion;
import com.alidasoftware.pos.model.Producto;
import com.alidasoftware.pos.model.Vale;

public class DevolucionBean  extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 2997925785045619894L;
	
	private boolean showCreateDialog;
	private boolean showListDialog;
	private boolean showViewDialog;
	private ValeFacade valeFacade;
	private DevolucionFacade devolucionFacade;
	private List<Devolucion> listaDevolucion;
	private Devolucion devolucion;
	private Devolucion devolucionSelected;
	private Detalledevolucion itemDetalledevolucion = new Detalledevolucion();
	private ProductoFacade productoFacade;
	private Producto producto;
	private int tipoOperacion;
	private String panelTitle;
	private int operacionSelected;
	private String folio;
	private int idOperacion;
	private String operacionDesc;
	private Vale vale;
	
	public Vale getVale() {
		return vale;
	}

	public void setVale(Vale vale) {
		this.vale = vale;
	}
	
	
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public int getOperacionSelected() {
		return operacionSelected;
	}

	public void setOperacionSelected(int operacionSelected) {
		this.operacionSelected = operacionSelected;
	}
	
	
	public String getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(String panelTitle) {
		this.panelTitle = panelTitle;
	}
	
	public int getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(int tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public List<Devolucion> getListaDevolucion() {
		return listaDevolucion;
	}

	public void setListaDevolucion(List<Devolucion> listaDevolucion) {
		this.listaDevolucion = listaDevolucion;
	}

	public Devolucion getDevolucion() {
		return devolucion;
	}

	public void setDevolucion(Devolucion devolucion) {
		this.devolucion = devolucion;
	}

	public Devolucion getDevolucionSelected() {
		return devolucionSelected;
	}

	public void setDevolucionSelected(Devolucion devolucionSelected) {
		this.devolucionSelected = devolucionSelected;
	}

	public Detalledevolucion getItemDetalledevolucion() {
		return itemDetalledevolucion;
	}

	public void setItemDetalledevolucion(Detalledevolucion itemDetalledevolucion) {
		this.itemDetalledevolucion = itemDetalledevolucion;
	}

	public boolean isShowCreateDialog() {
		return showCreateDialog;
	}
	
	public void setShowCreateDialog(boolean showCreateDialog) {
		this.showCreateDialog = showCreateDialog;
	}
	
	public boolean isShowListDialog() {
		return showListDialog;
	}
	
	public void setShowListDialog(boolean showListDialog) {
		this.showListDialog = showListDialog;
	}
	
	public boolean isShowViewDialog() {
		return showViewDialog;
	}
	
	public void setShowViewDialog(boolean showViewDialog) {
		this.showViewDialog = showViewDialog;
	}
	
	private ValeFacade getValeFacade() {
		if (valeFacade == null) {
			valeFacade = new ValeFacade();
		}
		return valeFacade;
	}
	
	private DevolucionFacade getDevolucionFacade() {
		if (devolucionFacade == null) {
			devolucionFacade = new  DevolucionFacade();
		}
		return devolucionFacade;
	}
	
	private ProductoFacade getProductoFacade() {
		if (productoFacade == null) {
			productoFacade = new ProductoFacade();
		}
		return productoFacade;
	}
	
	public DevolucionBean() {
		showListDialogListener();
		loadDevoluciones(ApplicationBean.MODULE_APARTADO);		
	}
	
	
	public List<SelectItem> getlistaOperacion() {
		List<SelectItem> statusList = ApplicationBean.getOperationSelectItems();
		return statusList;
	}
	
	private void loadDevoluciones(String operation) {
		try {
			int idOperation = ApplicationBean.getOperationTypeValue(operation);
			listaDevolucion = getDevolucionFacade().listByOperation(idOperation);
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	public void valueChangeDevolucionList() {	
		try {
			devolucion = null;
			devolucionSelected = null;
			listaDevolucion = getDevolucionFacade().listByOperation(tipoOperacion);
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}
	
	
	public void showListDialogListener() {
		showCreateDialog = false;		
		showViewDialog = false;
		showListDialog = true;
		devolucion = null;
		devolucionSelected = null;
		//cliente = null;
		producto = null;
		//editMode = false;
		tipoOperacion = ApplicationBean.getOperationTypeValue(ApplicationBean.MODULE_APARTADO);
	}
	
	public void showCreateDialogListener() {
		showCreateDialog = true;		
		showViewDialog = false;
		showListDialog = false;
		devolucion = new Devolucion();
		//cliente = null;
		//selectedClient = null;
		producto = null;
		devolucionSelected = null;
		//payment = new PaymentHelper();
		panelTitle = "Nueva Devolución";
		//editMode = false;
	}
	
	public void showViewDialogListener() {
		
	}
	
	public void valueChangeTipoOperacion() {
		//System.out.println("La opcion seleccionada es : " + operacionSelected);
		operacionDesc = ApplicationBean.getOperationTypeLabel(operacionSelected);
	}
	
	public String saveVistaAction() {	
		try {
			devolucion.setFecha(new Date());
			devolucion.setIdoperacion(idOperacion);
			devolucion.setOperacion(operacionDesc);			
			getDevolucionFacade().updateDevolucion(devolucion);
			return "/devolucion/List.xhtml";
		} catch (Exception ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error : " + ex.getMessage());
		}
		return null;
	}
	
	public void setFolioVale(Vale vale, Devolucion record) {
		this.vale = vale;
	}

}
