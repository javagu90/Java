package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.ClienteFacade;
import com.alidasoftware.pos.facade.PersonaFacade;
import com.alidasoftware.pos.facade.ProductoFacade;
import com.alidasoftware.pos.facade.VistaFacade;
import com.alidasoftware.pos.helper.PaymentHelper;
import com.alidasoftware.pos.model.Cliente;
import com.alidasoftware.pos.model.Detallevista;
import com.alidasoftware.pos.model.Persona;
import com.alidasoftware.pos.model.Producto;
import com.alidasoftware.pos.model.Tipovencimiento;
import com.alidasoftware.pos.model.Vista;
import com.alidasoftware.pos.util.Utils;

public class VistaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -9066110533300692129L;
	
	private PaymentHelper payment;
	private boolean showCreateDialog;
	private boolean showListDialog;
	private boolean showViewDialog;
	private VistaFacade vistaFacade;
	private List<Vista> listaVista;
	private Vista vista;
	private Vista vistaSelected;
	private Detallevista itemDetalleVista = new Detallevista();
	private ProductoFacade productoFacade;
	private Producto producto;	
	private ClienteFacade clienteFacade;
	private Cliente cliente;
	//private Cliente selectedClient;
	private PersonaFacade personaFacade;	
	private List<SelectItem> listaVencimiento;
	private Tipovencimiento vencimiento;
	private Date expirationDate;
	private int vistaStatus;
	private String panelTitle;
	private boolean editMode;
	
	/*
	public Cliente getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Cliente selectedClient) {
		this.selectedClient = selectedClient;
	}	
	*/

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

	public Detallevista getItemDetalleVista() {
		return itemDetalleVista;
	}

	public void setItemDetalleVista(Detallevista itemDetalleVista) {
		this.itemDetalleVista = itemDetalleVista;
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
	
	public int getVistaStatus() {
		return vistaStatus;
	}
	
	public void setVistaStatus(int vistaStatus) {
		this.vistaStatus = vistaStatus;
	}
	
	public List<SelectItem> getListaVencimiento() {
		return listaVencimiento;
	}
	
	public void setListaVencimiento(List<SelectItem> listaVencimiento) {
		this.listaVencimiento = listaVencimiento;
	}
	
	public Tipovencimiento getVencimiento() {
		return vencimiento;
	}
	
	public void setVencimiento(Tipovencimiento vencimiento) {
		this.vencimiento = vencimiento;
	}
	
	public Date getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public List<Vista> getListaVista() {
		return listaVista;
	}
	
	public void setListaVista(List<Vista> listaVista) {
		this.listaVista = listaVista;
	}
	
	public Vista getVista() {
		return vista;
	}
	
	public void setVista(Vista vista) {
		this.vista = vista;
	}
	
	public Vista getVistaSelected() {
		return vistaSelected;
	}
	
	public void setVistaSelected(Vista vistaSelected) {
		this.vistaSelected = vistaSelected;
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
	
	public VistaBean() {
		showListDialogListener();
		loadVistas(ApplicationBean.STATUS_VALUE_GENERATED);		
	}
	
	private VistaFacade getVistaFacade() {
		if (vistaFacade == null) {
			vistaFacade = new  VistaFacade();
		}
		return vistaFacade;
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
	private SessionBean getSessionBean() {
		return (SessionBean) Utils.getManagedBean("sessionBean");
	}
	*/
	
	private ClienteBean getClientBean() {
		return (ClienteBean) Utils.getManagedBean("clienteBean");
	}
	
	public void showListDialogListener() {
		showCreateDialog = false;		
		showViewDialog = false;
		showListDialog = true;
		vista = null;
		vistaSelected = null;
		cliente = null;
		producto = null;
		editMode = false;
		vistaStatus = ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VISTA, ApplicationBean.STATUS_VALUE_OPEN);
	}
	
	public void showEditDialogListener() {
		showCreateDialog = true;		
		showViewDialog = false;
		showListDialog = false;
		vista = vistaSelected;
		cliente = vista.getCliente();
		producto = null;
		vistaSelected = null;
		payment = new PaymentHelper();
		payment.setClient(cliente);
		try {
			payment.addDetailViewList(vista.getDetallevistas());
		} catch (AlidaPosException ex) {			
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		panelTitle = "Editar Vista";
		editMode = true;
	}
	
	public void showCreateDialogListener() {
		String path = loadTipoVencimiento(); 
		if (path.equals("")) {
			showCreateDialog = true;		
			showViewDialog = false;
			showListDialog = false;
			vista = new Vista();
			vista.setFolio(Utils.getFolio(ApplicationBean.MODULE_PREFIX_VISTA, ""));
			vista.setStatus(ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VISTA, ApplicationBean.STATUS_VALUE_GENERATED));
			cliente = null;
			//selectedClient = null;
			producto = null;
			vistaSelected = null;
			payment = new PaymentHelper();
			panelTitle = "Nueva Vista";
			editMode = false;		
		} else {
			try {
				 FacesContext.getCurrentInstance().getViewRoot().setViewId(path);
				 FacesContext.getCurrentInstance().renderResponse();  
			} catch (Exception ex) {
				
			}
		}
	}
	
	public void showViewDialogListener() {
		showCreateDialog = false;		
		showViewDialog = true;
		showListDialog = false;
		cliente = vistaSelected.getCliente();
		producto = null;
		payment = new PaymentHelper();
		payment.setClient(cliente);
		try {
			payment.addDetailViewList(vistaSelected.getDetallevistas());
		} catch (AlidaPosException ex) {			
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}
	
	public List<SelectItem> getlistaStatus() {
		List<SelectItem> statusList = ApplicationBean.getStatusSelectItems(ApplicationBean.MODULE_VISTA);
		return statusList;
	}
	
	private void loadVistas(String status) {
		try {
			int idStatus = ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VISTA, status);
			listaVista = getVistaFacade().listByStatus(idStatus);
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	private String loadTipoVencimiento() {
		try {
			listaVencimiento = new ArrayList<SelectItem>();
			List<Tipovencimiento> list = ApplicationBean.getExpirationTypeList();
			if (list != null && list.size() > 0) {
				String label = "";
				for (int i = 0; i < list.size(); i++) {
					label = list.get(i).getNombre() + " ( " + list.get(i).getDias() + " días )";
					listaVencimiento.add(new SelectItem( list.get(i), label));				
				}			
				vencimiento = list.get(0);
				int tipoDias = Integer.valueOf(vencimiento.getTipodias());
				expirationDate = Utils.getExpirationDate(new Date(), vencimiento.getDias(), tipoDias);				
			} else {
				keepDialogOpen();
	            displayErrorMessageToUser("Error: No exiten registros de TipoVencimiento.. verifique catálogo");
				return "/ventas/vista/List.xhtml";
			}
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		return "";
    }
	
	public void valueChangeVistaList() {	
		try {
			vista = null;
			vistaSelected = null;
			listaVista = getVistaFacade().listByStatus(vistaStatus);
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}
	
	public void valueChangeVencimiento() {
		int tipoDias = Integer.valueOf(vencimiento.getTipodias());
		//System.out.println("tipoDias : " + tipoDias);
		try {
			expirationDate = Utils.getExpirationDate(new Date(),  vencimiento.getDias(),  tipoDias);
		} catch (Exception ex) {
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
	
	private void RemoveProductFromView(List<Detallevista> viewList) throws AlidaPosException {
		PaymentHelper auxPayment = new PaymentHelper();
		for (int i = 0; i < viewList.size(); i++) {
			auxPayment.removeAllProductDetail(viewList.get(i), viewList.get(i).getProducto());
		}
	}

	public String saveVistaAction() {	
		try {
			vista.setCliente(cliente);
			vista.setTipovencimiento(vencimiento);
			vista.setFecha(new Date());
			vista.setStatus(ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VISTA, ApplicationBean.STATUS_VALUE_OPEN));			
			getVistaFacade().updateVista(vista);
			return "/ventas/vista/List.xhtml";
		} catch (Exception ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error : " + ex.getMessage());
		}
		return null;
	}
	
	public void generateVista() {
		try {
			//System.out.println("Genera Vista");
			//System.out.println("vista      : " + vista);
			//System.out.println("cliente    : " + cliente);
			//System.out.println("vencmiento : " + vencimiento);
			vista.setCliente(cliente);
			vista.setTipovencimiento(vencimiento);
			vista.setFecha(new Date());
			getVistaFacade().createVista(vista);
		} catch (Exception ex) {
			keepDialogOpen();
	        displayErrorMessageToUser("Error : " + ex.getMessage());
		}
	}
	
	public String updateVistaAction() {
		try {
			//System.out.println("Update Vista");
			//System.out.println("vista      : " + vista);
			//System.out.println("cliente    : " + cliente);
			//System.out.println("vencmiento : " + vencimiento);
			vista.setCliente(cliente);
			vista.setTipovencimiento(vencimiento);
			vista.setFecha(new Date());
			getVistaFacade().updateVista(vista);
			return "/ventas/vista/List.xhtml";
		} catch (Exception ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error : " + ex.getMessage());
		}
		return null;
	}
	
	public void editVistaRecord() {
		try {
			showCreateDialog = true;		
			showViewDialog = false;
			showListDialog = false;			
			cliente = vista.getCliente();
			producto = null;
			vistaSelected = null;
			payment = new PaymentHelper();
		} catch (Exception ex) {
			
		}		
	}
	
	public void deleteVistaRecord() {
		try {
			RemoveProductFromView(vistaSelected.getDetallevistas());
			getVistaFacade().deleteVista(vistaSelected);
			listaVista.remove(vistaSelected);
			vistaSelected = null;
		} catch (Exception ex) {
			System.out.println("Error : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error : " + ex.getMessage());
		}
		showListDialogListener();
	}
	
	
	public String cancelVistaAction() {		
		try {
			RemoveProductFromView(vista.getDetallevistas());
			getVistaFacade().deleteVista(vista);
			listaVista.remove(vista);
			vista = null;
			return "/ventas/vista/List.xhtml";
		} catch (Exception ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error : " + ex.getMessage());
		}
		return null;
	}
	
	
	public void deleteProduct() {
		if (itemDetalleVista != null) {
			try {
				List<Detallevista> vistaList = vista.getDetallevistas();
				for (int i = 0; i < vistaList.size(); i++) {
					if (vistaList.get(i).getProducto().getIdproducto() == itemDetalleVista.getProducto().getIdproducto()) {
						payment.removeAllProductDetail(vistaList.get(i), itemDetalleVista.getProducto());
						vistaList.remove(i);
						break;
					}
				}
			} catch (Exception ex) {
				keepDialogOpen();
	            displayErrorMessageToUser("Error al obtener información del itemDetalleVista : " + ex.getMessage());
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
    	Detallevista editView = vista.getDetallevistas().get(index);
    	Producto prod = vista.getDetallevistas().get(index).getProducto();
        Float oldValue = (Float) event.getOldValue();
        Float newValue = (Float) event.getNewValue();
        if (Float.compare(newValue, 0.0f) <= 0) {
        	newValue = oldValue;
        	editView.setCantidad(oldValue);
        	try {
        		getVistaFacade().updateVista(vista);
        	} catch (Exception ex) {
        		System.out.println("Error Edit Quantity: " + ex.getMessage());
				keepDialogOpen();
	            displayErrorMessageToUser("Error: " + ex.getMessage());
        	}
        } 
        float diff = Math.abs(oldValue - newValue);
        if (Float.compare(diff, 0.0f) != 0) {
        	try {
        		editView.setCantidad(oldValue);
	            if (Float.compare(oldValue, newValue) < 0) {
	            	System.out.println("Agregar Diferencia : " + diff);
	            	payment.addProductDetail(editView, prod, diff);	            	
	            } else {
	            	System.out.println("Restar Diferencia : " + diff);
	            	payment.removeProductDetail(editView, prod, diff);
	            }
	            getVistaFacade().updateVista(vista);
	            //RequestContext.getCurrentInstance().update("VistaListForm:createProductDT");
        	} catch (Exception ex) {
        		System.out.println("Error Edit Quantity: " + ex.getMessage());
				keepDialogOpen();
	            displayErrorMessageToUser("Error: " + ex.getMessage());
        	}
        }
    }
	
	public void makeSale(ActionEvent actionEvent) {
		
	}
	
	public void prepareClientSearchActionListener(ActionEvent actionEvent) {
		//FacesContext fc = FacesContext.getCurrentInstance(); 
	    //String refreshpage = fc.getViewRoot().getViewId();
		getClientBean().prepareSearchdialog(ApplicationBean.MODULE_VISTA);
	}
	
	public void updateSelectedClient(Cliente client) {
		if (client != null) {
			try {
				cliente = getClienteFacade().findCliente(client.getIdcliente());
				// TODO :  actualizar los precios de los productos seleccionados...
				vista.setCliente(client);
				getVistaFacade().updateVista(vista);
			} catch (Exception ex) {
				
			}			
		}
		RequestContext.getCurrentInstance().update("VistaListForm:clientvista");
	}
	

	public String prepareClientSearch() {
		getClientBean().prepareSearchdialog(ApplicationBean.MODULE_VISTA);
		return null;
	}
	
	private Detallevista existsProduct(int idProduct) {
		List<Detallevista> vistaList = vista.getDetallevistas();
		for (int i = 0; i < vistaList.size(); i++) {
			if (vistaList.get(i).getProducto().getIdproducto() == idProduct) {
				return vistaList.get(i);
			}
		}
		return null;
	}
	
	public void clientValueChange(ValueChangeEvent event) {
		System.out.println("cliente valueChange : " + event.getNewValue().getClass().toString());
	}
	
	public void handleSelectClient(SelectEvent event) {	
		//System.out.println("event  : " + event.toString());
		//System.out.println("source : " + event.getSource().toString());
		//System.out.println("object : " + event.getObject().toString());		
		/*
		selectedClient = (Cliente) event.getObject();
		if (selectedClient == null) {
			System.out.println("Error");
		} else {
			System.out.println(selectedClient.getPersona().getNombre());
		}
		
		cliente = (Cliente) event.getObject();
		*/
	}

	public void handleSelectProduct(SelectEvent event) {
		producto = (Producto) event.getObject();
		try {
			addProduct();
		} catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	
	private void deleteZeroQuantity() {
		int comp = 0;
		List<Detallevista> vistaList = vista.getDetallevistas();
		for (int i = 0; i < vistaList.size(); i++) {
			comp = Float.compare(vistaList.get(i).getCantidad(), 0.0f);
			if (comp <= 0) {
				vistaList.remove(i);
				return;
			}
		}
	}
	
	protected void refreshPage() { 
	    FacesContext fc = FacesContext.getCurrentInstance(); 
	    String refreshpage = fc.getViewRoot().getViewId();

	    ViewHandler ViewH = fc.getApplication().getViewHandler(); 
	    UIViewRoot UIV = ViewH.createView(fc,refreshpage);
	    UIV.setViewId(refreshpage); 
	    fc.setViewRoot(UIV); 
	}
	
	private void addProduct() {		
		if (cliente != null && cliente.getIdcliente() != 0) {
			payment.setClient(cliente);
			if (producto != null && producto.getIdproducto() != 0) {
				//System.out.println("MODO EDICION " + editMode);
				if (editMode) {
					try {
						Detallevista detview = existsProduct(producto.getIdproducto());
						payment.removeProductDetail(detview, producto, 1);
						deleteZeroQuantity();
						getVistaFacade().updateVista(vista);
						producto = new Producto();
						producto = null;
					} catch (Exception ex) {
						System.out.println("Error: " + ex.getMessage());
						keepDialogOpen();
			            displayErrorMessageToUser("Error: " + ex.getMessage()); 
					}
				} else {
					try {
						if (vista.getDetallevistas() == null) {
							vista.setDetallevistas( new ArrayList<Detallevista>());
						}
						Detallevista detview = existsProduct(producto.getIdproducto());
						if (detview == null) {
							detview = new Detallevista();
							detview.setProducto(producto);
							detview.setVista(vista);	
							vista.getDetallevistas().add(detview);
						}
						//System.out.println("vista         : " + vista);
						//System.out.println("Detalle vista : " + detview);
						//System.out.println("Payment       : " + payment);
						//System.out.println("Producto      : " + producto);
						
						payment.addProductDetail(detview, producto, 1);
						//System.out.println("vista Id      : " + vista.getIdvista());
						if (vista.getIdvista() == null) {
							//System.out.println("-- generar --");
							generateVista();
						} else {
							//System.out.println("-- actualizar --");
							getVistaFacade().updateVista(vista);
						}
						//System.out.println("agreagr o eliminar fin");	
						producto = new Producto();
						producto = null;
					} catch (Exception ex) {
						System.out.println("Error: " + ex.getMessage());
						keepDialogOpen();
			            displayErrorMessageToUser("Error: " + ex.getMessage()); 
					}
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
	
	public String showListDialogAction() {
		RequestContext.getCurrentInstance().execute("PF('blockPage').show().show();");
		showListDialogListener();
		RequestContext.getCurrentInstance().execute("PF('blockPage').show().hide();");
		return "/ventas/vista/List.xhtml?faces-redirect=true";
	}

}
