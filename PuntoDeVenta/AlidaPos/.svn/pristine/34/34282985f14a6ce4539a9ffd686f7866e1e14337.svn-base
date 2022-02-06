package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.AdeudoVentaFacade;
import com.alidasoftware.pos.facade.ApartadoFacade;
import com.alidasoftware.pos.facade.ClienteFacade;
import com.alidasoftware.pos.facade.InventarioFacade;
import com.alidasoftware.pos.facade.PagoApartadoFacade;
import com.alidasoftware.pos.facade.PersonaFacade;
import com.alidasoftware.pos.facade.ProductoFacade;
import com.alidasoftware.pos.helper.PaymentHelper;
import com.alidasoftware.pos.model.AdeudoVenta;
import com.alidasoftware.pos.model.Apartado;
import com.alidasoftware.pos.model.Cliente;
import com.alidasoftware.pos.model.Detalleapartado;
import com.alidasoftware.pos.model.Inventario;
import com.alidasoftware.pos.model.Pagoapartado;
import com.alidasoftware.pos.model.Persona;
import com.alidasoftware.pos.model.Producto;
import com.alidasoftware.pos.model.Tipovencimiento;
import com.alidasoftware.pos.model.Vale;
import com.alidasoftware.pos.util.Utils;

public class ApartadoBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -5909129536560318249L;
	
	private PaymentHelper payment;
	private boolean showCreateDialog;
	private boolean showListDialog;
	private boolean showViewDialog;
	private AdeudoVentaFacade adeudoVentaFacade;
	private ApartadoFacade apartadoFacade;
	private List<Apartado> listaApartado;
	private Apartado apartado;
	private Apartado apartadoSelected;
	private Detalleapartado itemDetalleApartado = new Detalleapartado();
	
	private ProductoFacade productoFacade;
	private Producto producto;	
	private ClienteFacade clienteFacade;
	private Cliente cliente;
	private List<SelectItem> listaVencimiento;
	private Tipovencimiento vencimiento;
	private Date expirationDate;
	private PersonaFacade personaFacade;
	private PagoApartadoFacade pagoApartadoFacade;
	private int apartadoStatus;
	private String panelTitle;
	private boolean editMode;
	private Vale vale;
	private boolean showPaymentButton;
	
	private String searchFolio;
	private Date searchDate;
	private Cliente searchClient;
	private InventarioFacade inventarioFacade;
	private Inventario inventarioTienda;
	
	public String getSearchFolio() {
		return searchFolio;
	}

	public void setSearchFolio(String searchFolio) {
		this.searchFolio = searchFolio;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

	public Cliente getSearchClient() {
		return searchClient;
	}

	public void setSearchClient(Cliente searchClient) {
		this.searchClient = searchClient;
	}

	public boolean isShowPaymentButton() {
		return showPaymentButton;
	}

	public void setShowPaymentButton(boolean showPaymentButton) {
		this.showPaymentButton = showPaymentButton;
	}

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

	public Date getExpirationDate() {
		return expirationDate;
	}
	
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	public String getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(String panelTitle) {
		this.panelTitle = panelTitle;
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
	
	public int getApartadoStatus() {
		return apartadoStatus;
	}
	
	public void setApartadoStatus(int apartadoStatus) {
		this.apartadoStatus = apartadoStatus;
	}
	
	public Apartado getApartadoSelected() {
		return apartadoSelected;
	}
	
	public void setApartadoSelected(Apartado apartadoSelected) {
		this.apartadoSelected = apartadoSelected;
	}
	
	public Detalleapartado getItemDetalleApartado() {
		return itemDetalleApartado;
	}

	public void setItemDetalleApartado(Detalleapartado itemDetalleApartado) {
		this.itemDetalleApartado = itemDetalleApartado;
	}
	
	public List<Apartado> getListaApartado() {
		return listaApartado;
	}
	
	public void setListaApartado(List<Apartado> listaApartado) {
		this.listaApartado = listaApartado;
	}
	
	public Apartado getApartado() {
		return apartado;
	}
	
	public void setApartado(Apartado apartado) {
		this.apartado = apartado;
	}
	
	public List<SelectItem> getlistaStatus() {
		return ApplicationBean.getStatusSelectItems(ApplicationBean.MODULE_APARTADO);
	}
	
	public ApartadoBean() {
		//showListDialogListener();		
		apartadoStatus = ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_APARTADO, ApplicationBean.STATUS_VALUE_GENERATED);
	}
	
	public void cleanUp() {
		showCreateDialog = false;
		showViewDialog = false;
		showListDialog = true;
		apartado = null;
		apartadoSelected = null;
		cliente = null;
		producto = null;
		editMode = false;
		showPaymentButton = false;
		apartadoStatus = ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_APARTADO, ApplicationBean.STATUS_VALUE_GENERATED);
		System.out.println("Inicializando variables de ApartadoBean");
	}
	
	private AdeudoVentaFacade getAdeudoVentaFacade() {
		if (adeudoVentaFacade == null) {
			adeudoVentaFacade = new AdeudoVentaFacade();
		}
		return adeudoVentaFacade;
	}
	
	private ApartadoFacade getApartadoFacade() {
		if (apartadoFacade == null) {
			apartadoFacade = new  ApartadoFacade();
		}
		return apartadoFacade;
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
	
	private PagoApartadoFacade getPagoApartadoFacade() {
		if (pagoApartadoFacade == null) {
			pagoApartadoFacade = new PagoApartadoFacade();
		}
		return pagoApartadoFacade;
	}
	
	public InventarioFacade getInventarioFacade(){
		if(inventarioFacade==null){
			inventarioFacade = new InventarioFacade();
		}
		return inventarioFacade;
	}
	
	/*
	private SessionBean getSessionBean() {
		return (SessionBean) Utils.getManagedBean("sessionBean");
	}
	*/
	
	private AdeudoVentaBean getAdeudoVentaBean() {
		return (AdeudoVentaBean) Utils.getManagedBean("adeudoVentaBean");
	}
	
	private LoginBean getLoginBean() {
		return (LoginBean) Utils.getManagedBean("loginBean");
	}
	
	private ClienteBean getClientBean() {
		return (ClienteBean) Utils.getManagedBean("clienteBean");
	}
	
	private ValeBean getValeBean() {
		return (ValeBean) Utils.getManagedBean("valeBean");
	}
	
	private PagoBean getPagoBean() {
		return (PagoBean) Utils.getManagedBean("pagoBean");
	}
	
	private List<Apartado> updateApartadoStatus(List<Apartado> apartadoList) {
		List<Apartado> auxList = new ArrayList<Apartado>();
		Apartado auxApartado = null;
		int expired = ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_APARTADO, ApplicationBean.STATUS_VALUE_EXPIRED);
		DateTime day = new DateTime();
		Tipovencimiento vence = null;
		int tipoDias = 0;
		for (int i = 0; i < apartadoList.size(); i++) {
			auxApartado = apartadoList.get(i);
	    	vence = auxApartado.getTipovencimiento();
	    	tipoDias = Integer.valueOf(vence.getTipodias());
	    	try {
	    		day = new DateTime(Utils.getExpirationDate(auxApartado.getFechaapartado(), vence.getDias(), tipoDias));
	    	} catch (Exception ex) {
	    		keepDialogOpen();
	            displayErrorMessageToUser("Error: " + ex.getMessage());
			}
	    	if (day.isBeforeNow()) {
	    		try {
		    		//System.out.println("Apartado vencido : " + auxApartado.getFolio());
		    		auxApartado.setStatus(expired);
		    		getApartadoFacade().updateApartado(auxApartado);
		    	} catch (Exception ex) {
		    		keepDialogOpen();
		            displayErrorMessageToUser("Error: " + ex.getMessage());
				}
	    	} else {
	    		auxList.add(auxApartado);
	    	}
		}
		return auxList;
	}
	
	public String showListDialogAction() {
		RequestContext.getCurrentInstance().execute("PF('blockPage').show().show();");
		//System.out.println("--- EJECUTANDO ACTION ---");
		showListDialogListener();
		RequestContext.getCurrentInstance().execute("PF('blockPage').show().hide();");
		return "/ventas/apartado/List.xhtml?faces-redirect=true";
	}
	
	/*
	public void showListDialogL(ActionEvent event) throws AbortProcessingException {
		System.out.println("--- EJECUTANDO ACTION LISTENER ---");
		
		showListDialogListener();
	}
	*/
	
	public void showListDialogListener() {
		//System.out.println("1111");
		showCreateDialog = false;
		//System.out.println("2222");
		showViewDialog = false;
		//System.out.println("3333");
		showListDialog = true;
		//System.out.println("4444");
		apartado = null;
		//System.out.println("5555");
		apartadoSelected = null;
		//System.out.println("6666");
		cliente = null;
		//System.out.println("7777");
		producto = null;
		//System.out.println("8888");
		editMode = false;
		//System.out.println("9999");
		showPaymentButton = false;
		//System.out.println("aaa");		
		//System.out.println("bbb");
		apartadoStatus = 0;
		doSearch();
		//loadApartados(ApplicationBean.getSelectItemLabel(ApplicationBean.MODULE_APARTADO, apartadoStatus));
		//System.out.println("ccc");
		//System.out.println("showListDialog   : " + showListDialog);
		//System.out.println("showCreateDialog : " + showCreateDialog);
		//System.out.println("showViewDialog   : " + showViewDialog);
		//System.out.println("----- showListDialogListener() -----");
		/*
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/ventas/apartado/List.xhtml");
		} catch (Exception ex) {
			
		}
		*/
	}
	
	/*
	public void showListAfterPayment() {
		showListDialog = true;
		showCreateDialog = false;
		//System.out.println("showListDialog   : " + showListDialog);
		//System.out.println("showCreateDialog : " + showCreateDialog);
		//System.out.println("showViewDialog   : " + showViewDialog);
		//System.out.println("Mostrando información despues del primer pago...");		
		RequestContext.getCurrentInstance().update("ApartadoListForm:apartadoCreatePanel");
		RequestContext.getCurrentInstance().update("ApartadoListForm:apartadoListPanel");
		showListDialogListener();
		FacesContext.getCurrentInstance().renderResponse();
	}
	*/
	
	public void showEditDialogListener() {
		String path = loadTipoVencimiento(); 
		if (path != null && path.equals("")) {
			showCreateDialog = true;		
			showViewDialog = false;
			showListDialog = false;
			showPaymentButton = true;		
			apartado = apartadoSelected;
			//System.out.println("apartado : " + apartadoSelected);
			//System.out.println("cliente  : " + apartadoSelected.getCliente().getPersona().getNombre());
			cliente = apartado.getCliente();
			apartado.setFechaapartado(new Date());
			try {				
				vencimiento = apartado.getTipovencimiento();
				int tipoDias = Integer.valueOf(vencimiento.getTipodias());
				expirationDate = Utils.getExpirationDate(new Date(),  vencimiento.getDias(),  tipoDias);
			} catch (AlidaPosException ex) {
				keepDialogOpen();
	            displayErrorMessageToUser("Error: " + ex.getMessage());
			}
			producto = null;
			apartadoSelected = null;
			payment = new PaymentHelper();
			payment.setClient(cliente);		
			try {
				inventarioTienda = getInventarioFacade().findInventarioByTienda(getLoginBean().getUser().getTienda());
				payment.addDetailApartadoList(apartado.getDetalleapartados(), inventarioTienda);
			} catch (AlidaPosException ex) {			
				keepDialogOpen();
	            displayErrorMessageToUser("Error: " + ex.getMessage());
			}
			panelTitle = "Editar Apartado";
			editMode = false;
		}
	}
	
	public void showCreateDialogListener() {
		String path = loadTipoVencimiento(); 
		if (path != null && path.equals("")) {
			showCreateDialog = true;		
			showViewDialog = false;
			showListDialog = false;
			showPaymentButton = false;
			apartado = new Apartado();
			apartado.setFolio(Utils.getFolio(0, 0,ApplicationBean.MODULE_PREFIX_APARTADO, ""));
			apartado.setStatus(ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_APARTADO, ApplicationBean.STATUS_VALUE_GENERATED));
			cliente = null;
			producto = null;
			apartadoSelected = null;
			payment = new PaymentHelper();
			panelTitle = "Nuevo Apartado";
			editMode = false;
		}
	}
	
	public void showViewDialogListener() {
		showCreateDialog = false;		
		showViewDialog = true;
		showListDialog = false;
		showPaymentButton = false;
		cliente = apartadoSelected.getCliente();
		producto = null;
		payment = new PaymentHelper();
		payment.setClient(cliente);
		try {
			inventarioTienda = getInventarioFacade().findInventarioByTienda(getLoginBean().getUser().getTienda());
			payment.addDetailApartadoList(apartadoSelected.getDetalleapartados(), inventarioTienda);
		} catch (AlidaPosException ex) {			
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		System.out.println("inicializar variables para VIEW...");
	}
		
	
	/**
	 * Si existe una devolucion del apartado, se verifica si existe algun pago realizado,
	 * de ser asi, se genera un vale por la cantidad abonada.
	 */
	private float checkForPayments(int idOperacion) throws AlidaPosException {
		List<Pagoapartado> auxlist = getPagoApartadoFacade().listByIdApartado(idOperacion);
		float quantity = 0.0f;
		if (auxlist != null && auxlist.size() > 0) {		
			for (int i = 0; i < auxlist.size(); i++) {
				quantity += auxlist.get(i).getCantidad();
			}
		}
		System.out.println("Total de pagos realizados: " + auxlist.size());
		return quantity;
	}
	
	private void checkForAdeudo(Apartado recordApartado) throws AlidaPosException {
		String module = ApplicationBean.MODULE_APARTADO;
		String folio = recordApartado.getFolio();
		AdeudoVenta adeudo = getAdeudoVentaFacade().findByFolioId(recordApartado.getIdapartado(), folio, module);
		if (adeudo != null) {
			getAdeudoVentaFacade().deleteAdeudoVenta(adeudo);
		}
	}
	
	public void setFolioVale(Vale vale, Apartado record) {
		apartadoSelected = record;
		System.out.println("Vale generado por : " + vale.getCantidad() + ", con folio: " + vale.getFolio());
		System.out.println("apartado : " + apartadoSelected.getFolio() + " - " + apartadoSelected.getTotal());
		this.vale = vale;
		if (vale != null && apartadoSelected != null) {
			try {
				RemoveProductFromApartado(apartadoSelected.getDetalleapartados());
				int cancelStatus = ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_APARTADO, ApplicationBean.STATUS_VALUE_CANCEL);
				apartadoSelected.setStatus(cancelStatus);
				getApartadoFacade().updateApartado(apartadoSelected);
				checkForAdeudo(apartadoSelected);
				//getApartadoFacade().deleteApartado(apartadoSelected);				
				apartadoSelected = null;
				doSearch();
				//loadApartados(ApplicationBean.getSelectItemLabel(ApplicationBean.MODULE_APARTADO, apartadoStatus));
				RequestContext.getCurrentInstance().update("ApartadoListForm:apartadoListPanel");
			} catch (Exception ex) {
				System.out.println("Error : " + ex.getMessage());
				keepDialogOpen();
	            displayErrorMessageToUser("Error : " + ex.getMessage());
			}
		}
		//getSessionBean().setMessageTitle("Vale Generado");
		//getSessionBean().setMessage("Folio : " + vale.getFolio() + ", valor : " + vale.getCantidad());
		//RequestContext.getCurrentInstance().execute("PF('messageDialog').show().show()");		
	}
	
	public void deleteApartadoRecord() {
		try {
			float payment = checkForPayments(apartadoSelected.getIdapartado());
			System.out.println("Pagos realizados por : " + payment);
			if (Float.compare(payment, (float) 0.0) > 0) {			
				System.out.println("Generando vale");
				getValeBean().setValeInformation(payment, apartadoSelected);
				getValeBean().prepareValeDialog(true, ApplicationBean.MODULE_APARTADO);
				RequestContext.getCurrentInstance().execute("PF('valeDialog').show();");
				System.out.println("Dialog 'Vale' mostrado");
			} else {
				int cancelStatus = ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_APARTADO, ApplicationBean.STATUS_VALUE_CANCEL);
				apartadoSelected.setStatus(cancelStatus);
				getApartadoFacade().updateApartado(apartadoSelected);				
				//RemoveProductFromApartado(apartadoSelected.getDetalleapartados());
				//getApartadoFacade().deleteApartado(apartadoSelected);				
				apartadoSelected = null;
				doSearch();
				//loadApartados(ApplicationBean.getSelectItemLabel(ApplicationBean.MODULE_APARTADO, apartadoStatus));				
			}
		} catch (Exception ex) {
			System.out.println("Error : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error : " + ex.getMessage());
		}
		showListDialogListener();
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
	
	public void prepareClientSearchActionListener(ActionEvent actionEvent) {
		//FacesContext fc = FacesContext.getCurrentInstance(); 
	    //String refreshpage = fc.getViewRoot().getViewId();
		getClientBean().prepareSearchdialog(ApplicationBean.MODULE_APARTADO);
	}
	
	public void updateSelectedClient(Cliente client) {
		System.out.println("actualizando cliente...");
		if (client != null) {
			try {
				System.out.println("client 111");
				cliente = getClienteFacade().findCliente(client.getIdcliente());
				if (apartado.getCliente().getIdcliente() != cliente.getIdcliente()) {
					System.out.println("client 222");				
					if (apartado.getDetalleapartados().size() > 0) {
						apartado = (Apartado) payment.updateClientInformation(client, apartado);
						System.out.println("client 333");
						getApartadoFacade().updateApartado(apartado);
						System.out.println("client 444");
						RequestContext.getCurrentInstance().update("ApartadoListForm:createProductDT");
			            RequestContext.getCurrentInstance().execute("updateResultPanel()");
					} else {
						apartado.setCliente(cliente);
						apartado.setTipovencimiento(vencimiento);
						if (apartado.getFechaapartado() == null) {
							apartado.setFechaapartado(new Date());
						}
						getApartadoFacade().updateApartado(apartado);
					}
				}
			} catch (Exception ex) {
				keepDialogOpen();
	            displayErrorMessageToUser("Error : " + ex.getMessage());
			}			
		}
		RequestContext.getCurrentInstance().update("ApartadoListForm:clientapartado");
	}
	
	public String prepareClientSearch() {
		getClientBean().prepareSearchdialog(ApplicationBean.MODULE_APARTADO);
		return null;
	}
	
	private AdeudoVenta createPagoApartado(Apartado apartado) throws AlidaPosException {
		AdeudoVenta adeudoVenta = new AdeudoVenta();
		adeudoVenta.setAdeudo(payment.getTotal());
		adeudoVenta.setFoliooperacion(apartado.getFolio());
		adeudoVenta.setIdoperacion(apartado.getIdapartado());
		adeudoVenta.setFecha(apartado.getFechaapartado());
		adeudoVenta.setModule(ApplicationBean.MODULE_APARTADO);
		getAdeudoVentaFacade().createAdeudoVenta(adeudoVenta);
		return adeudoVenta;
	}
	
	public String saveApartadoAction() {	
		try {
			apartado.setCliente(cliente);
			apartado.setTipovencimiento(vencimiento);
			apartado.setFechaapartado(new Date());
			apartado.setStatus(ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_APARTADO, ApplicationBean.STATUS_VALUE_OPEN));
			apartado.setSubtotal(payment.getSubtotal());
			apartado.setIva(payment.getTax());
			apartado.setTotal(payment.getTotal());
			getApartadoFacade().updateApartado(apartado);
			createPagoApartado(apartado);
			showListDialogListener();
			return "/ventas/apartado/List.xhtml";
		} catch (Exception ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error : " + ex.getMessage());
		}
		return null;
	}
	
	public void generateApartado() {
		try {
			//System.out.println("Genera Apartado");
			//System.out.println("Apartado      : " + apartado);
			//System.out.println("cliente    : " + cliente);
			//System.out.println("vencmiento : " + vencimiento);
			apartado.setCliente(cliente);
			apartado.setTipovencimiento(vencimiento);
			apartado.setSubtotal(payment.getSubtotal());
			apartado.setIva(payment.getTax());
			apartado.setTotal(payment.getTotal());
			apartado.setFechaapartado(new Date());
			getApartadoFacade().createApartado(apartado);
		} catch (Exception ex) {
			keepDialogOpen();
	        displayErrorMessageToUser("Error : " + ex.getMessage());
		}
	}
	
	public String updateApartadoAction() {
		try {
			//System.out.println("Update Apartado");
			//System.out.println("apartado      : " + apartado);
			//System.out.println("cliente    : " + cliente);
			//System.out.println("vencmiento : " + vencimiento);
			apartado.setCliente(cliente);
			apartado.setTipovencimiento(vencimiento);
			apartado.setSubtotal(payment.getSubtotal());
			apartado.setIva(payment.getTax());
			apartado.setTotal(payment.getTotal());
			apartado.setFechaapartado(new Date());
			getApartadoFacade().updateApartado(apartado);
			apartadoStatus = ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_APARTADO, ApplicationBean.STATUS_VALUE_OPEN);
			return "/ventas/apartado/List.xhtml";
		} catch (Exception ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error : " + ex.getMessage());
		}
		return null;
	}
	
	public String cancelApartadoAction() {		
		//System.out.println("Cancel 111");
		if (apartado != null && apartado.getIdapartado() != null) {
			try {
				//System.out.println("Cancel 222 : " + apartado);
				float payment = checkForPayments(apartado.getIdapartado());
				//System.out.println("Cancel 333");
				if (Float.compare(payment, (float) 0.0) > 0) {				
					getValeBean().setValeInformation(payment, apartado.getCliente());
					//System.out.println("Cancel 444");
					getValeBean().prepareValeDialog(true, ApplicationBean.MODULE_APARTADO);
					//System.out.println("Cancel 555");
					RequestContext.getCurrentInstance().execute("PF('valeDialog').show()");
					//System.out.println("Cancel 666");
				} else {
					RemoveProductFromApartado(apartado.getDetalleapartados());
					//System.out.println("Cancel 777");
					getApartadoFacade().deleteApartado(apartado);
					//System.out.println("Cancel 888");
					listaApartado.remove(apartado);
					//System.out.println("Cancel 999");
					apartado = null;
					showListDialogListener();
					return "/ventas/apartado/List.xhtml?faces-redirect=true";
				}
			} catch (Exception ex) {
				System.out.println("Cancel Error : " + ex.getMessage());
				keepDialogOpen();
	            displayErrorMessageToUser("Error : " + ex.getMessage());
			}
		} else {
			apartado = null;
			showListDialogListener();
			return "/ventas/apartado/List.xhtml?faces-redirect=true";
		}
		return null;
	}
	
	private void RemoveProductFromApartado(List<Detalleapartado> apartadoList) throws AlidaPosException {
		PaymentHelper auxPayment = new PaymentHelper();
		for (int i = 0; i < apartadoList.size(); i++) {
			auxPayment.removeAllProductDetail(apartadoList.get(i), apartadoList.get(i).getProducto());
		}
	}
	
	public void deleteProduct() {
		if (itemDetalleApartado != null) {
			try {
				List<Detalleapartado> apartadoList = apartado.getDetalleapartados();
				for (int i = 0; i < apartadoList.size(); i++) {
					if (apartadoList.get(i).getProducto().getIdproducto() == itemDetalleApartado.getProducto().getIdproducto()) {
						payment.removeAllProductDetail(apartadoList.get(i), itemDetalleApartado.getProducto());
						apartadoList.remove(i);
						break;
					}
				}
				getApartadoFacade().updateApartado(apartado);
			} catch (Exception ex) {
				keepDialogOpen();
	            displayErrorMessageToUser("Error al obtener información del itemDetalleApartado : " + ex.getMessage());
			}
		} else {
			keepDialogOpen();
            displayErrorMessageToUser("No se ha seleccionado un producto para eliminar");
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
	
	public void handleSelectProduct(SelectEvent event) {
		producto = (Producto) event.getObject();
		try {
			addProduct();
		} catch(Exception ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	
	private Detalleapartado existsProduct(int idProduct) {
		List<Detalleapartado> apartadoList = apartado.getDetalleapartados();
		for (int i = 0; i < apartadoList.size(); i++) {
			if (apartadoList.get(i).getProducto().getIdproducto() == idProduct) {
				return apartadoList.get(i);
			}
		}
		return null;
	}
	
	private void deleteZeroQuantity() {
		int comp = 0;
		List<Detalleapartado> apartadoList = apartado.getDetalleapartados();
		for (int i = 0; i < apartadoList.size(); i++) {
			comp = Float.compare(apartadoList.get(i).getCantidad(), 0.0f);
			if (comp <= 0) {
				apartadoList.remove(i);
				return;
			}
		}
	}

	private void addProduct() {		
		if (cliente != null && cliente.getIdcliente() != 0) {
			payment.setClient(cliente);
			if (producto != null && producto.getIdproducto() != 0) {
				//System.out.println("MODO EDICION " + editMode);
				if (editMode) {
					try {
						Detalleapartado detApartado = existsProduct(producto.getIdproducto());
						payment.removeProductDetail(detApartado, producto, null, 1);
						deleteZeroQuantity();
						getApartadoFacade().updateApartado(apartado);
						producto = new Producto();
						producto = null;
					} catch (Exception ex) {
						System.out.println("Error: " + ex.getMessage());
						keepDialogOpen();
			            displayErrorMessageToUser("Error: " + ex.getMessage()); 
					}
				} else {
					try {
						if (apartado.getDetalleapartados() == null) {
							apartado.setDetalleapartados(new ArrayList<Detalleapartado>());
						}
						Detalleapartado detApartado = existsProduct(producto.getIdproducto());
						if (detApartado == null) {
							detApartado = new Detalleapartado();
							detApartado.setProducto(producto);
							detApartado.setApartado(apartado);	
							apartado.getDetalleapartados().add(detApartado);
						}
						
						payment.addProductDetail(detApartado, producto, null, 1);
						if (apartado.getIdapartado() == null) {
							//System.out.println("-- generar --");
							generateApartado();
						} else {
							//System.out.println("-- actualizar --");
							getApartadoFacade().updateApartado(apartado);
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
		if (apartado != null) {
			if (apartado.getDetalleapartados() != null ) {
				showPaymentButton = apartado.getDetalleapartados().size() > 0 ? true : false;
				System.out.println("Actualizando estado del boton de pago : " + showPaymentButton);
				RequestContext.getCurrentInstance().update("ApartadoListForm:btnApartadoPayment");
				RequestContext.getCurrentInstance().update("ApartadoListForm:Guardar");
			}
		}
	}
	
	public void valueChangeVencimiento() {
		int tipoDias = Integer.valueOf(vencimiento.getTipodias());
		//System.out.println("tipoDias : " + tipoDias);
		try {
			System.out.println("ValueChange vencimiento 111");
			expirationDate = Utils.getExpirationDate(new Date(),  vencimiento.getDias(),  tipoDias);
			apartado.setTipovencimiento(vencimiento);
			if (apartado.getFechaapartado() == null) {
				apartado.setFechaapartado(new Date());
			}
			getApartadoFacade().updateApartado(apartado);
			System.out.println("ValueChange vencimiento 222");
		} catch (Exception ex) {
			System.out.println("ValueChange error : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}
	
	public String updatePaymentInformation(int idApartado) {
		try {
			apartadoSelected.setPagoapartados(getPagoApartadoFacade().listByIdApartado(idApartado));
			RequestContext.getCurrentInstance().update("ApartadoListForm:apartadoViewPanel");
			return "/ventas/apartado/List.xhtml?faces-redirect=true";
		} catch (Exception ex) {
			keepDialogOpen();
	        displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		return null;
	}
	
	/*
	protected void refreshPage() {
		FacesContext fc = FacesContext.getCurrentInstance();
		String refreshpage = fc.getViewRoot().getViewId();
		System.out.println("-----------------------------------------------------");
		System.out.println("Refresh Page : " + refreshpage);
		System.out.println("-----------------------------------------------------");
		ViewHandler ViewH = fc.getApplication().getViewHandler();
		UIViewRoot UIV = ViewH.createView(fc, refreshpage);
		UIV.setViewId(refreshpage);
		fc.setViewRoot(UIV);
	}
	*/
	
	public String updateAfterFirstPayment(Apartado auxApartado) {
		try {
			System.out.println("mostrando datos una vez realizado el pago");
			apartadoSelected = getApartadoFacade().findById(auxApartado.getIdapartado());
			showViewDialogListener();		
			//refreshPage();
			//RequestContext.getCurrentInstance().update("ApartadoListForm:apartadoListPanel");
			//RequestContext.getCurrentInstance().update("ApartadoListForm:apartadoViewPanel");	
			System.out.println("datos mostrados despues del primer pago...");
			return "/ventas/apartado/List.xhtml?faces-redirect=true";
		} catch (AlidaPosException ex) {
			System.out.println("-- ERR -- : " + ex.getMessage());
			keepDialogOpen();
	        displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		return null;
	}
	
	public void addPayment(ActionEvent actionEvent) {
		try {
			String module = ApplicationBean.MODULE_APARTADO;
			String folio = apartadoSelected.getFolio();
			AdeudoVenta adeudo = getAdeudoVentaFacade().findByFolioId(apartadoSelected.getIdapartado(), folio, module);
			if (adeudo != null) {
				/*
				System.out.println("adeudo       : " + adeudo.getAdeudo());
				System.out.println("adeudo folio : " + adeudo.getFoliooperacion());
				System.out.println("modulo       : " + module);
				System.out.println("folio        : " + folio);
				System.out.println("idOperacion  : " + apartadoSelected.getIdapartado());
				*/
				PagoBean pagoBean = getPagoBean();			
				if (pagoBean != null) {
					getPagoBean().setAddPago(adeudo, apartadoSelected, false, false);
				}				
			}
		} catch (Exception ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}
	
	/*
	public void showPaymentList(ActionEvent actionEvent) {
		try {
			String module = ApplicationBean.MODULE_APARTADO;
			String folio = apartadoSelected.getFolio();
			Adeudoventa adeudo = getAdeudoVentaFacade().findByFolioId(apartadoSelected.getIdapartado(), folio, module);
			if (adeudo != null) {
				getAdeudoVentaBean().showPartialListPago(adeudo);
				String path = "/adeudos/List.xhtml";
				FacesContext.getCurrentInstance().getViewRoot().setViewId(path);
				FacesContext.getCurrentInstance().renderResponse();
				System.out.println("Mostrando pagina de pagos/adeudos...");
			}
		} catch (Exception ex) {
			System.out.println("Error ShowPaymentList Apartado : " + ex.getMessage()); 
			keepDialogOpen();
	        displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}
	*/
	
	public String showPaymentListAction() {
		try {
			//System.out.println("aaa");
			//RequestContext.getCurrentInstance().execute("PF('blockPage').show().show();");
			//System.out.println("bbb");
			String module = ApplicationBean.MODULE_APARTADO;
			//System.out.println("ccc");
			String folio = apartadoSelected.getFolio();
			//System.out.println("ddd :  " + apartadoSelected.getIdapartado() + ", folio : " + folio + ", modulo : " + module);
			AdeudoVenta adeudo = getAdeudoVentaFacade().findByFolioId(apartadoSelected.getIdapartado(), folio, module);
			//System.out.println("eee : " + adeudo);
			if (adeudo != null) {					
				getAdeudoVentaBean().showPartialListPago(adeudo);
			} else {
				getAdeudoVentaBean().showCompleteListPago(apartadoSelected);				
			}
			//System.out.println("fff");
			//RequestContext.getCurrentInstance().execute("PF('blockPage').show().hide();");
			//System.out.println("ggg");
			return "/adeudos/List.xhtml?faces-redirect=true";
		} catch (Exception ex) {
			//RequestContext.getCurrentInstance().execute("PF('blockPage').show().hide();");
			System.out.println("Error ShowPaymentList Apartado : " + ex.getMessage()); 
			keepDialogOpen();
	        displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		return null;				
	}
	
	public void makePayment(ActionEvent actionEvent) {
		// Mostrar dialog para realizar el primer pago.
		try {
			if (Float.compare(payment.getTotal(), 0.0f) > 0) {
				apartado.setCliente(cliente);
				apartado.setTipovencimiento(vencimiento);
				apartado.setFechaapartado(new Date());
				apartado.setStatus(ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_APARTADO, ApplicationBean.STATUS_VALUE_OPEN));
				apartado.setSubtotal(payment.getSubtotal());
				apartado.setIva(payment.getTax());
				apartado.setTotal(payment.getTotal());
				getApartadoFacade().updateApartado(apartado);
				AdeudoVenta adeudo = createPagoApartado(apartado);
				PagoBean pagoBean = getPagoBean();			
				if (pagoBean != null) {
					getPagoBean().setAddPago(adeudo, apartado, false, true);
				}				
			}
		} catch (Exception ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		//return "";
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
    	Detalleapartado editApartado = apartado.getDetalleapartados().get(index);
    	Producto prod = apartado.getDetalleapartados().get(index).getProducto();
        Float oldValue = (Float) event.getOldValue();
        Float newValue = (Float) event.getNewValue();
        if (Float.compare(newValue, (float) 0.0) <= 0) {
        	newValue = oldValue;
        	editApartado.setCantidad(oldValue);
        	try {
        		getApartadoFacade().updateApartado(apartado);
        	} catch (Exception ex) {
        		System.out.println("Error Edit Quantity: " + ex.getMessage());
				keepDialogOpen();
	            displayErrorMessageToUser("Error: " + ex.getMessage());
        	}
        } 
        float diff = Math.abs(oldValue - newValue);
        if (Float.compare(diff, (float) 0.0) != 0) {
        	try {
        		editApartado.setCantidad(oldValue);
	            if (Float.compare(oldValue, newValue) < 0) {
	            	System.out.println("Agregar Diferencia : " + diff);
	            	payment.addProductDetail(editApartado, prod, null, diff);	            	
	            } else {
	            	System.out.println("Restar Diferencia : " + diff);
	            	payment.removeProductDetail(editApartado, prod, null, diff);
	            }
	            getApartadoFacade().updateApartado(apartado);
	            RequestContext.getCurrentInstance().update("ApartadoListForm:createProductDT");
	            RequestContext.getCurrentInstance().execute("updateResultPanel()");
        	} catch (Exception ex) {
        		System.out.println("Error Edit Quantity: " + ex.getMessage());
				keepDialogOpen();
	            displayErrorMessageToUser("Error: " + ex.getMessage());
        	}
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
				return "/ventas/apartado/List.xhtml";
			}
		} catch (AlidaPosException ex) {
			System.out.println("Error vencimiento : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		return "";
    }
    
    //---------------------------------------------------------------------------
    // Metodos ejecutados al seleccionar un valor en los filtros de búsqueda...
    //---------------------------------------------------------------------------
    
    public void valueChangeStatusFilter() {	
		//try {
			if (apartadoStatus > 0) { 
				apartado = null;
				apartadoSelected = null;
				//searchFolio = "";
				//searchDate = null;
				//searchClient = null;
				//listaApartado = getApartadoFacade().listByStatus(apartadoStatus);
			}
		//} catch (AlidaPosException ex) {
		//	keepDialogOpen();
        //    displayErrorMessageToUser("Error: " + ex.getMessage());
		//}
	}
    
    public void valueChangeFolioFilter() {
    	//try {
    		System.out.println("SearchFolio : [" + searchFolio + "]");
    		if (!searchFolio.trim().equals("")) {    			
    			apartado = null;
    			apartadoSelected = null;
    			//apartadoStatus = 0;
				//searchDate = null;
				//searchClient = null;
    			//listaApartado = getApartadoFacade().listByFolio(searchFolio);    			
    		}    		
		//} catch (AlidaPosException ex) {
		//	keepDialogOpen();
        //    displayErrorMessageToUser("Error: " + ex.getMessage());
		//}
    }
    
    public void dateSelectedAction(SelectEvent e) {
    	//try {
    		if (searchDate != null) {
    			System.out.println("Date Selected Is : " + searchDate);
        		apartado = null;
    			apartadoSelected = null;
    			//apartadoStatus = 0;
    			//searchFolio = "";
				//searchClient = null;
    			//listaApartado = getApartadoFacade().listByFecha(searchDate);
        	}  		
		//} catch (AlidaPosException ex) {
		//	System.out.println("FilterDate : " + ex.getMessage());
		//	keepDialogOpen();
        //    displayErrorMessageToUser("Error: " + ex.getMessage());
		//}
    	
    	/*
        Date date = (Date)e.getObject();
        if (date != null) {
        	searchDate = date;
        	
        }
        */
        //System.out.println("Date Selected Is : " + searchDate);
    }
    
    public void valueChangeClienteFilter() {
    	//try {
	    	if (searchClient != null) {
				System.out.println("SearchClient ValueChange-> " + searchClient.getPersona().getNombre());
				apartado = null;
				apartadoSelected = null;
				//apartadoStatus = 0;
				//searchFolio = "";
				//searchDate = null;
				//listaApartado = getApartadoFacade().listByClient(searchClient.getIdcliente());
			}
    	//} catch (AlidaPosException ex) {
		//	System.out.println("FilterValueChangeClient : " + ex.getMessage());
		//	keepDialogOpen();
        //    displayErrorMessageToUser("Error: " + ex.getMessage());
		//}
    }
    
    /*
    public void clientValueChange(ValueChangeEvent event) {
    	try {
			if (event != null && event.getNewValue() != null) {
				System.out.println("cliente valueChange : " + event.getNewValue().getClass().toString());
				searchClient = (Cliente) event.getNewValue();
				if (searchClient != null) {
					System.out.println("SearchClient -> " + searchClient.getPersona().getNombre());
					apartado = null;
	    			apartadoSelected = null;
	    			apartadoStatus = 0;
	    			searchFolio = "";
	    			searchDate = null;
	    			listaApartado = getApartadoFacade().listByClient(searchClient.getIdcliente());
				}
			} 
		} catch (AlidaPosException ex) {
			System.out.println("FilterClient : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		//
		//try {
		//	System.out.println("***********");
		//	System.out.println("cliente valueChange : " + event.getNewValue().getClass().toString());
		//	System.out.println("***********");
		//} catch (Exception ex) {
		//	System.out.println("***********");
		//	System.out.println("ERR ValueChange : " + ex.getMessage());
		//	System.out.println("***********");
		//}
		//
	}
	*/
	
	/*
	public void handleSelectClient(SelectEvent event) {
		try {
			System.out.println("***********");
			System.out.println("cliente SelectEvent : " + event.getObject().getClass().toString());
			System.out.println("***********");
		} catch (Exception ex) {
			System.out.println("***********");
			System.out.println("ERR SelectEvent : " + ex.getMessage());
			System.out.println("***********");
		}
	}
	*/
    
    /*
//    public void folioValueChangeListener(ValueChangeEvent e) {
    	  System.out.println("valueChangeListener invoked:" 
    	                      + " OLD: " + e.getOldValue() 
    	                      + " NEW: " + e.getNewValue());
    }
    */
    
    public void doSearch() {
    	System.out.println("Realizando busqueda por ...");
    	apartado = null;
		apartadoSelected = null;	
		try {
	    	if (apartadoStatus != 0) {
	    		System.out.println("  status");
	    		listaApartado = getApartadoFacade().listByStatus(apartadoStatus);		    		
	    	} else if (searchFolio != null && !searchFolio.trim().equals("")) {
	    		System.out.println("  folio");
	    		listaApartado = getApartadoFacade().listByFolio(searchFolio);
	    	} else if (searchDate != null) {
	    		System.out.println("  fecha");
	    		listaApartado = getApartadoFacade().listByFecha(searchDate);
	    	} else if (searchClient != null) {
	    		System.out.println("  cliente");
	    		listaApartado = getApartadoFacade().listByClient(searchClient.getIdcliente());
	    	} else {
	    		// revisra si es requerido traer todos los registros...
	    		//listaApartado = getApartadoFacade().listAll(); 
	    	}
	    	System.out.println("-*-*-  Actualizando status -*-*-");
	    	// se actualiza el status de los apartados...
	    	if (listaApartado != null && listaApartado.size() > 0 ) {
	    		listaApartado = updateApartadoStatus(listaApartado);
	    	}
		} catch (Exception ex) {
			System.out.println("Filter Exception : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    	apartadoStatus = 0;
    	searchFolio = "";
		searchDate = null;
		searchClient = null;
		RequestContext.getCurrentInstance().update("ApartadoListForm:apartadoListPanel");
    }
    
    /*
	private void loadApartados(String status) {
		try {
			int idStatus = ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_APARTADO, status);
			listaApartado = getApartadoFacade().listByStatus(idStatus);
			if (idStatus == ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_APARTADO, ApplicationBean.STATUS_VALUE_OPEN) ||
				idStatus == ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_APARTADO, ApplicationBean.STATUS_VALUE_EXPIRED)) {
				listaApartado = updateApartadoStatus(listaApartado);
			}
			System.out.println("Leyendo registros de la BD (apartados)");
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }	
    */

}
