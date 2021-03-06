package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.ClienteFacade;
import com.alidasoftware.pos.facade.PersonaFacade;
import com.alidasoftware.pos.facade.ProductoFacade;
import com.alidasoftware.pos.facade.VentaFacade;
import com.alidasoftware.pos.helper.PaymentHelper;
import com.alidasoftware.pos.model.Caja;
import com.alidasoftware.pos.model.Cliente;
import com.alidasoftware.pos.model.Detalleventa;
import com.alidasoftware.pos.model.Persona;
import com.alidasoftware.pos.model.Producto;
import com.alidasoftware.pos.model.Vale;
import com.alidasoftware.pos.model.Venta;
import com.alidasoftware.pos.util.Utils;

public class VentaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -5255961360166798456L;
	
	private PaymentHelper payment;
	private boolean showCreateDialog;
	private boolean showListDialog;
	private boolean showViewDialog;
	private VentaFacade ventaFacade;
	private List<Venta> listaVenta;
	private Venta venta;
	private Venta ventaSelected;
	private Detalleventa itemDetalleVenta = new Detalleventa();
	private ProductoFacade productoFacade;
	private Producto producto;	
	private ClienteFacade clienteFacade;
	private Cliente cliente;
	//private UsuarioFacade usuarioFacade;
	private Caja caja;
	//private CajaFacade cajaFacade;
	private PersonaFacade personaFacade;	
	private int ventaStatus;
	private String panelTitle;
	private boolean editMode;
	private Vale vale;
	
	public Vale getVale() {
		return vale;
	}

	public void setVale(Vale vale) {
		this.vale = vale;
	}
	
	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public String getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(String panelTitle) {
		this.panelTitle = panelTitle;
	}

	public Detalleventa getItemDetalleVenta() {
		return itemDetalleVenta;
	}

	public void setItemDetalleVenta(Detalleventa itemDetalleVenta) {
		this.itemDetalleVenta = itemDetalleVenta;
	}

	public PaymentHelper getPayment() {
		return payment;
	}

	public void setPayment(PaymentHelper payment) {
		this.payment = payment;
	}
	
	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}	
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public int getVentaStatus() {
		return ventaStatus;
	}
	
	public void setVentaStatus(int ventaStatus) {
		this.ventaStatus = ventaStatus;
	}
	
	public List<Venta> getListaVenta() {
		return listaVenta;
	}
	
	public void setListaVenta(List<Venta> listaVenta) {
		this.listaVenta = listaVenta;
	}
	
	public Venta getVenta() {
		return venta;
	}
	
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	public Venta getVentaSelected() {
		return ventaSelected;
	}
	
	public void setVentaSelected(Venta ventaSelected) {
		this.ventaSelected = ventaSelected;
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
	
	public VentaBean() {
		showListDialogListener();		
		loadVentas(ApplicationBean.STATUS_VALUE_GENERATED);
	}
	
	private VentaFacade getVentaFacade() {
		if (ventaFacade == null) {
			ventaFacade = new  VentaFacade();
		}
		return ventaFacade;
	}
	
	private ProductoFacade getProductoFacade() {
		if (productoFacade == null) {
			productoFacade = new ProductoFacade();
		}
		return productoFacade;
	}
	
	private ClienteFacade getClienteFacade() {
		if (clienteFacade == null) {
			clienteFacade = new ClienteFacade();
		}
		return clienteFacade;
	}
	
	private PersonaFacade getPersonaFacade() {
		if (personaFacade == null) {
			personaFacade = new PersonaFacade();
		}
		return personaFacade;
	}
	
	/*
	private CajaFacade getCajaFacade() {
		if (cajaFacade == null) {
			cajaFacade = new CajaFacade();
		}
		return cajaFacade;
	}
	
	private UsuarioFacade getUsuarioFacade() {
		if (usuarioFacade == null) {
			usuarioFacade = new UsuarioFacade();
		}
		return usuarioFacade;
	}
	*/
	
	/*
	private SessionBean getSessionBean() {
		return (SessionBean) Utils.getManagedBean("sessionBean");
	}
	*/
	
	private CorteCajaBean getCorteCajaBean() {
		return (CorteCajaBean) Utils.getManagedBean("corteCajaBean");
	}
	
	private LoginBean getLoginBean() {
		return (LoginBean) Utils.getManagedBean("loginBean");
	}
	
	private PagoBean getPagoBean() {
		return (PagoBean) Utils.getManagedBean("pagoBean");
	}
	
	private ClienteBean getClientBean() {
		return (ClienteBean) Utils.getManagedBean("clienteBean");
	}
	
	public void showListDialogListener() {
		showCreateDialog = false;		
		showViewDialog = false;
		showListDialog = true;
		venta = null;
		ventaSelected = null;
		cliente = null;
		producto = null;
		editMode = false;
	}
	
	public void showEditDialogListener() {
		showCreateDialog = true;		
		showViewDialog = false;
		showListDialog = false;
		venta = ventaSelected;
		cliente = venta.getCliente();
		producto = null;
		ventaSelected = null;
		payment = new PaymentHelper();
		payment.setClient(cliente);
		try {
			payment.addDetailSaleList(venta.getDetalleventas());
		} catch (AlidaPosException ex) {			
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		panelTitle = "Editar Venta";
		editMode = true;
	}
	
	public void showCreateDialogListener() {
		if (getCorteCajaBean().getCaja() == null) {
			getCorteCajaBean().openCajaDialogAction();
		} else {		
			showCreateDialog = true;		
			showViewDialog = false;
			showListDialog = false;
			caja = getCorteCajaBean().getCaja();
			venta = new Venta();
			venta.setFolio(Utils.getFolio(ApplicationBean.MODULE_PREFIX_VENTA, ""));
			venta.setStatus(ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VENTA, ApplicationBean.STATUS_VALUE_GENERATED));
			cliente = null;
			producto = null;
			ventaSelected = null;
			payment = new PaymentHelper();
			panelTitle = "Nueva Venta";
			editMode = false;
		}
	}
	
	public void showViewDialogListener() {
		showCreateDialog = false;		
		showViewDialog = true;
		showListDialog = false;
		cliente = ventaSelected.getCliente();
		producto = null;
		payment = new PaymentHelper();
		payment.setClient(cliente);
		try {
			payment.addDetailSaleList(ventaSelected.getDetalleventas());
		} catch (AlidaPosException ex) {			
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		editMode = false;
	}
	
	public List<SelectItem> getlistaStatus() {
		return ApplicationBean.getStatusSelectItems(ApplicationBean.MODULE_VENTA);
	}
	
	private void loadVentas(String status) {
		try {
			int idStatus = ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VENTA, status);
			listaVenta = getVentaFacade().listByStatus(idStatus);
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	public void valueChangeVentaList() {	
		try {
			venta = null;
			ventaSelected = null;
			listaVenta = getVentaFacade().listByStatus(ventaStatus);
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}
	
	public List<Producto> completeProducto(String query) {
		List<Producto> aux = new ArrayList<Producto>();
		try {
			aux = getProductoFacade().FindProductLikeName(query);
		} catch (AlidaPosException ex) {			
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }			
		return aux; 
	}
	
	public List<Cliente> completeCliente(String query) {
		List<Cliente> aux = new ArrayList<Cliente>();
		List<Persona> per = new ArrayList<Persona>();
		try {
			aux = getClienteFacade().FindLikeClave(query);
			per = getPersonaFacade().FindLikeInfoPersona(query);
			aux = Utils.mergeClientPersonList(aux, per);
		} catch (AlidaPosException ex) {			
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }			
		return aux;
	}
	
	private void RemoveProductFromSale(List<Detalleventa> saleList) throws AlidaPosException {
		PaymentHelper auxPayment = new PaymentHelper();
		for (int i = 0; i < saleList.size(); i++) {
			auxPayment.removeAllProductDetail(saleList.get(i), saleList.get(i).getProducto());
		}
	}
	
	public String saveVentaAction() {	
		try {
			venta.setCliente(cliente);
			venta.setFecha(new Date());
			venta.setCaja(caja);
			venta.setUsuario(getLoginBean().getUser());
			venta.setTotal(payment.getTotal());
			venta.setSubtotal(payment.getSubtotal());
			venta.setIva(payment.getTax());
			venta.setStatus(ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VENTA, ApplicationBean.STATUS_VALUE_OPEN));			
			getVentaFacade().updateVenta(venta);
			return "/ventas/venta/List.xhtml";
		} catch (Exception ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error : " + ex.getMessage());
		}
		return null;
	}
	
	public void generateVenta() {
		try {
			venta.setCliente(cliente);
			venta.setFecha(new Date());
			venta.setCaja(caja);
			venta.setUsuario(getLoginBean().getUser());
			venta.setTotal(payment.getTotal());
			venta.setSubtotal(payment.getSubtotal());
			venta.setIva(payment.getTax());
			getVentaFacade().createVenta(venta);
		} catch (Exception ex) {
			keepDialogOpen();
	        displayErrorMessageToUser("Error : " + ex.getMessage());
		}
	}
	
	public String updateVentaAction() {
		try {
			getVentaFacade().updateVenta(venta);
			return "/ventas/venta/List.xhtml";
		} catch (Exception ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error : " + ex.getMessage());
		}
		return null;
	}
	
	public void editVentaRecord() {
		try {
			showCreateDialog = true;		
			showViewDialog = false;
			showListDialog = false;			
			cliente = venta.getCliente();
			producto = null;
			ventaSelected = null;
			payment = new PaymentHelper();
		} catch (Exception ex) {
			
		}		
	}
	
	public void deleteVentaRecord() {
		try {
			RemoveProductFromSale(ventaSelected.getDetalleventas());
			getVentaFacade().deleteVenta(ventaSelected);
			listaVenta.remove(ventaSelected);
			ventaSelected = null;
		} catch (Exception ex) {
			System.out.println("Error : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error : " + ex.getMessage());
		}
		showListDialogListener();
	}
	
	public String cancelVentaAction() {		
		try {
			RemoveProductFromSale(venta.getDetalleventas());
			getVentaFacade().deleteVenta(venta);
			listaVenta.remove(venta);
			venta = null;
			showListDialogListener();
			return "/ventas/venta/List.xhtml?faces-redirect=true";
		} catch (Exception ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error : " + ex.getMessage());
		}
		return null;
	}
	
	public void deleteProduct() {
		if (itemDetalleVenta != null) {
			try {
				List<Detalleventa> ventaList = venta.getDetalleventas();
				for (int i = 0; i < ventaList.size(); i++) {
					if (ventaList.get(i).getProducto().getIdproducto() == itemDetalleVenta.getProducto().getIdproducto()) {
						payment.removeAllProductDetail(ventaList.get(i), itemDetalleVenta.getProducto());
						ventaList.remove(i);
						break;
					}
				}
			} catch (Exception ex) {
				keepDialogOpen();
	            displayErrorMessageToUser("Error al obtener informaci?n del itemDetalleVenta : " + ex.getMessage());
			}
		} else {
			keepDialogOpen();
            displayErrorMessageToUser("No se ha seleccionado un producto para eliminar");
		}
	}
	
	public void onRowEdit(RowEditEvent event) {
		// metodo utilizado cuando se edita toda la linea.
    } 
       
    public void onRowCancel(RowEditEvent event) {
    	// metodo utilizado cuando se edita toda la linea.
    }
        
    public void rowEditListener(RowEditEvent event){
    	// metodo utilizado cuando se edita toda la linea.
    }    
    
    public void onCellEdit(CellEditEvent event) {
    	//System.out.println("source   : " + event.getSource().getClass().toString());
    	int index = event.getRowIndex();
    	Detalleventa editSale = venta.getDetalleventas().get(index);
    	Producto prod = venta.getDetalleventas().get(index).getProducto();
        Float oldValue = (Float) event.getOldValue();
        Float newValue = (Float) event.getNewValue();
        if (Float.compare(newValue, 0.0f) <= 0) {
        	newValue = oldValue;
        	editSale.setCantidad(oldValue);
        	try {
        		getVentaFacade().updateVenta(venta);
        	} catch (Exception ex) {
        		System.out.println("Error Edit Quantity: " + ex.getMessage());
				keepDialogOpen();
	            displayErrorMessageToUser("Error: " + ex.getMessage());
        	}
        } 
        float diff = Math.abs(oldValue - newValue);
        if (Float.compare(diff, 0.0f) != 0) {
        	try {
        		editSale.setCantidad(oldValue);
	            if (Float.compare(oldValue, newValue) < 0) {
	            	System.out.println("Agregar Diferencia : " + diff);
	            	payment.addProductDetail(editSale, prod, diff);	            	
	            } else {
	            	System.out.println("Restar Diferencia : " + diff);
	            	payment.removeProductDetail(editSale, prod, diff);
	            }
	            getVentaFacade().updateVenta(venta);
	            //RequestContext.getCurrentInstance().update("VistaListForm:createProductDT");
        	} catch (Exception ex) {
        		System.out.println("Error Edit Quantity: " + ex.getMessage());
				keepDialogOpen();
	            displayErrorMessageToUser("Error: " + ex.getMessage());
        	}
        }
    }
	
	public void makeSale(ActionEvent actionEvent) {
		if (Float.compare(payment.getTotal(), 0.0f) > 0) {
			PagoBean pagoBean = getPagoBean();
			if (pagoBean != null) {
				getPagoBean().setPagoVenta(payment.getTotal(), venta);
			}		
		}
	}
	
	public String updateAfterPayment(Venta auxVenta) {
		showListDialogListener();
		return "/ventas/venta/List.xhtml?faces-redirect=true";
	}
	
	
	public void prepareClientSearchActionListener(ActionEvent actionEvent) {
		//FacesContext fc = FacesContext.getCurrentInstance(); 
	    //String refreshpage = fc.getViewRoot().getViewId();	    	    
		getClientBean().prepareSearchdialog(ApplicationBean.MODULE_VENTA);
	}
	
	public void updateSelectedClient(Cliente client) {
		if (client != null) {
			try {
				cliente = getClienteFacade().findCliente(client.getIdcliente());
				// TODO :  actualizar los precios de los productos seleccionados...
				venta.setCliente(client);
				getVentaFacade().updateVenta(venta);
			} catch (Exception ex) {
				
			}			
		}
		RequestContext.getCurrentInstance().update("VentaListForm:clientventa");
	}
	

	public String prepareClientSearch() {
		getClientBean().prepareSearchdialog(ApplicationBean.MODULE_VENTA);
		return null;
	}	
	
	
	private Detalleventa existsProduct(int idProduct) {
		List<Detalleventa> ventaList = venta.getDetalleventas();
		for (int i = 0; i < ventaList.size(); i++) {
			if (ventaList.get(i).getProducto().getIdproducto() == idProduct) {
				return ventaList.get(i);
			}
		}
		return null;
	}
	
	public void handleSelectClient(SelectEvent event) {
		cliente = (Cliente) event.getObject();
	}

	public void handleSelectProduct(SelectEvent event) {
		producto = (Producto) event.getObject();
		try {
			addProduct();
		} catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	
	private void addProduct() {
		if (cliente != null && cliente.getIdcliente() != 0) {
			payment.setClient(cliente);
			if (producto != null && producto.getIdproducto() != 0) {
				try {
					if (venta.getDetalleventas() == null) {
						venta.setDetalleventas( new ArrayList<Detalleventa>());
					}
					Detalleventa detSale = existsProduct(producto.getIdproducto());
					if (detSale == null) {
						detSale = new Detalleventa();
						detSale.setProducto(producto);
						detSale.setVenta(venta);
						venta.getDetalleventas().add(detSale);
					}					
					payment.addProductDetail(detSale, producto, 1);
					if (venta.getIdventa() == null) {
						System.out.println("-- generar --");
						generateVenta();
					} else {
						System.out.println("-- actualizar --");
						getVentaFacade().updateVenta(venta);
					}
					producto = new Producto();
					producto = null;
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
					keepDialogOpen();
		            displayErrorMessageToUser("Error: " + ex.getMessage()); 
				}
			} else {
				producto = new Producto();
				producto = null;
				keepDialogOpen();
	            displayErrorMessageToUser("Debe seleccionar un producto de la lista.");
			}		
		} else {
			producto = new Producto();
			producto = null;
			keepDialogOpen();
			displayInfoMessageToUser("Debe seleccionar un cliente...");
		}
	}
	
	public void setFolioVale(Vale vale, Venta record) {
		this.vale = vale;
	}
	
	public void cleanUp() {
		showCreateDialog = false;
		showViewDialog = false;
		showListDialog = true;
		venta = null;
		ventaSelected = null;
	}
	
	public String showListDialogAction() {
		RequestContext.getCurrentInstance().execute("PF('blockPage').show().show();");
		showListDialogListener();
		RequestContext.getCurrentInstance().execute("PF('blockPage').show().hide();");
		return "/ventas/venta/List.xhtml?faces-redirect=true";
	}
}
