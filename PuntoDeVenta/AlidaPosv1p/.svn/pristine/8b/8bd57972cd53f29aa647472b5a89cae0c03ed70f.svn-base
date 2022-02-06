package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.ClienteFacade;
import com.alidasoftware.pos.facade.ValeFacade;
import com.alidasoftware.pos.model.Apartado;
import com.alidasoftware.pos.model.Cliente;
import com.alidasoftware.pos.model.Devolucion;
import com.alidasoftware.pos.model.Tipovencimiento;
import com.alidasoftware.pos.model.Vale;
import com.alidasoftware.pos.model.Venta;
import com.alidasoftware.pos.util.Utils;

public class ValeBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 157487486742414670L;
	private boolean showCreateDialog;
	private boolean showListDialog;
	private ValeFacade valeFacade;
	private List<Vale> listaVale;
	private Vale vale;
	private Vale valeSelected;
	private ClienteFacade clienteFacade;
	private int valeStatus;
	private String panelTitle;
	private Date expirationDate;
	private Tipovencimiento vencimiento;
	private List<SelectItem> listaVencimiento;
	private boolean returnFolio;
	private String module;
	private Apartado apartado;
	private Venta venta;
	private Devolucion devolucion;

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

	public String getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(String panelTitle) {
		this.panelTitle = panelTitle;
	}
	
	public int getValeStatus() {
		return valeStatus;
	}

	public void setValeStatus(int valeStatus) {
		this.valeStatus = valeStatus;
	}

	public List<Vale> getListaVale() {
		return listaVale;
	}
	
	public void setListaVale(List<Vale> listaVale) {
		this.listaVale = listaVale;
	}
	
	public Vale getVale() {
		return vale;
	}
	
	public void setVale(Vale vale) {
		this.vale = vale;
	}
	
	public Vale getValeSelected() {
		return valeSelected;
	}
	
	public void setValeSelected(Vale valeSelected) {
		this.valeSelected = valeSelected;
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
	
	private ValeFacade getValeFacade() {
		if (valeFacade == null) {
			valeFacade = new  ValeFacade();
		}
		return valeFacade;
	}
		
	private ClienteFacade getClienteFacade() {
		if (clienteFacade == null) {
			clienteFacade = new  ClienteFacade();
		}
		return clienteFacade;
	}	
	
	private ApartadoBean getApartadoBean() {
		return (ApartadoBean) Utils.getManagedBean("apartadoBean");
	}
	
	private VentaBean getVentaBean() {
		return (VentaBean) Utils.getManagedBean("ventaBean");
	}
	
	private DevolucionBean getDevolucionBean() {
		return (DevolucionBean) Utils.getManagedBean("devolucionBean");
	}
		
	public ValeBean() {		
		showListDialogListener();
		updateValeStatus();
		loadVales(ApplicationBean.STATUS_VALUE_OPEN);		
	}
	
	public List<SelectItem> getlistaStatus() {
		List<SelectItem> statusList = ApplicationBean.getStatusSelectItems(ApplicationBean.MODULE_VALES);
		return statusList;
	}
	
	private void loadVales(String status) {
		try {
			int idStatus = ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VALES, status);
			listaVale = getValeFacade().listByStatus(idStatus);
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }
	
	public void showListDialogListener() {
		showCreateDialog = false;
		showListDialog = true;
		vale = null;
		valeSelected = null;
		valeStatus = ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VALES, ApplicationBean.STATUS_VALUE_OPEN);
	}
	
	
	public void showCreateDialogListener() {
		showCreateDialog = true;		
		showListDialog = false;
		vale = new Vale();
		vale.setFolio(Utils.getFolio(ApplicationBean.MODULE_PREFIX_VALE, ""));
		System.out.println("folio showCreateDialog : " + vale.getFolio());
		vale.setStatus(ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VALES, ApplicationBean.STATUS_VALUE_OPEN));
		valeSelected = null;
		panelTitle = "Nuevo Vale";			
	}
	
	
	public void valueChangeValeList() {	
		try {
			vale = null;
			valeSelected = null;
			if (valeStatus == ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VALES, ApplicationBean.STATUS_VALUE_OPEN) ||
				valeStatus == ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VALES, ApplicationBean.STATUS_VALUE_EXPIRED)) {
				updateValeStatus();
			}
			listaVale = getValeFacade().listByStatus(valeStatus);			
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}
	
	private void updateValeStatus() {
		int auxStatus = ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VALES, ApplicationBean.STATUS_VALUE_OPEN);
		try {
			List<Vale> valeList = getValeFacade().listByStatus(auxStatus);
			Vale auxVale = null;
			int expired = ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VALES, ApplicationBean.STATUS_VALUE_EXPIRED);
			DateTime day = null;
			Tipovencimiento vence = null;
			int tipoDias = 0;
			// verificar que la fecha del vale haya vencido, si es asi, modificar el status del vale			
		    for (int i = 0; i < valeList.size(); i++) {
		    	auxVale = valeList.get(i);
		    	vence = auxVale.getTipovencimiento();
		    	tipoDias = Integer.valueOf(vence.getTipodias());
		    	day = new DateTime(Utils.getExpirationDate(auxVale.getFecha(), vence.getDias(), tipoDias));
		    	if (day.isBeforeNow()) {
		    		//System.out.println("Vale vencido : " + auxVale.getFolio());
		    		auxVale.setStatus(expired);
		    		getValeFacade().updateVale(auxVale);
		    	}
			}
		} catch (Exception ex) {
			
		}
	}
	
	public void setValeInformation(float cantidad, Object record) {
		vale = new Vale();				
		vale.setFecha(new Date());
		vale.setCantidad(cantidad);
		vale.setFolio(Utils.getFolio(ApplicationBean.MODULE_PREFIX_VALE, ""));
		System.out.println("folio setValeinformation : " + vale.getFolio());
		vale.setStatus(ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VALES, ApplicationBean.STATUS_VALUE_OPEN));
		if (record instanceof Apartado) {
			apartado = (Apartado) record;
			vale.setCliente(apartado.getCliente());
		} else if (record instanceof Venta) {
			venta = (Venta) record;
			vale.setCliente(venta.getCliente());
		} else if (record instanceof Devolucion) {
			devolucion = (Devolucion) record;
			// obtener el cliente que esta haciendo la devolucion, utilizando los datos de la operacion y el folio. 
			//vale.setCliente(devolucion);
		}
		System.out.println("informacion del vale generada...");
	}
	
	public void prepareValeDialog(boolean returnFolio, String module) {
		this.returnFolio = returnFolio;
		this.module = module;
		loadTipoVencimiento();
		RequestContext.getCurrentInstance().update("creaValeForm:createValePanel");
		System.out.println("preparando dialog para el vale con parametros");
	}
	
	public void prepareValeDialog() {
		loadTipoVencimiento();
		RequestContext.getCurrentInstance().update("creaValeForm:createValePanel");
		System.out.println("preparando dialog para el vale sin parametros");
	}
	
	public void prepareClientSearchActionListener(ActionEvent actionEvent) {
		try {
			// generar un cliente dummy y asignarlo al vale creado
			Cliente cliente = getClienteFacade().findCliente(1);
			//System.out.println("Cliente     : " + cliente.getPersona().getNombre() + " " + 
			//                                      cliente.getPersona().getApellidopaterno());
			setValeInformation(850.25f, cliente);
		} catch (Exception ex) {
			
		}
		prepareValeDialog(false, ApplicationBean.MODULE_VALES);
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
				//System.out.println("Vencimiento inicial : " + vencimiento.getNombre());
			} else {
				keepDialogOpen();
	            displayErrorMessageToUser("Error: No exiten registros de TipoVencimiento.. verifique catálogo");
				return "/devolucion/List.xhtml";
			}
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		return "";
    }
	
	public void valueChangeVencimiento() {
		int tipoDias = Integer.valueOf(vencimiento.getTipodias());
		try {
			expirationDate = Utils.getExpirationDate(new Date(),  vencimiento.getDias(),  tipoDias);
		} catch (Exception ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}
	
	public void createVale() {
		System.out.println("Metodo que crea el vale...");
		try {		
			vale.setTipovencimiento(vencimiento);
			getValeFacade().createVale(vale);
			System.out.println("el vale de ha creado : " + vale.getFolio() + ", return: " + returnFolio);
			if (returnFolio) {
				System.out.println("Modulo : " + module);
				if (module.equals(ApplicationBean.MODULE_APARTADO)) {
					getApartadoBean().setFolioVale(vale, apartado);
				} else if (module.equals(ApplicationBean.MODULE_VENTA)) {
					getVentaBean().setFolioVale(vale, venta);
				} else if (module.equals(ApplicationBean.MODULE_DEVOLUCION)) {
					getDevolucionBean().setFolioVale(vale, devolucion);
				}
			}
			returnFolio = false;
        } catch (AlidaPosException ex) {
        	System.out.println("Error Vale : " + ex.getMessage());
            keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
        }
		RequestContext.getCurrentInstance().execute("PF('valeDialog').hide();");
	}
	
}
