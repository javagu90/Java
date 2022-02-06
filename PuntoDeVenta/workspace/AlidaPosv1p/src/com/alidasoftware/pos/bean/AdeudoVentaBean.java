package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.AdeudoVentaFacade;
import com.alidasoftware.pos.facade.ApartadoFacade;
import com.alidasoftware.pos.facade.PagoApartadoFacade;
import com.alidasoftware.pos.facade.PagoVentaFacade;
import com.alidasoftware.pos.facade.VentaFacade;
import com.alidasoftware.pos.helper.AdeudoHelper;
import com.alidasoftware.pos.model.AdeudoVenta;
import com.alidasoftware.pos.model.Apartado;
import com.alidasoftware.pos.model.Pagoapartado;
import com.alidasoftware.pos.model.Pagoventa;
import com.alidasoftware.pos.model.Venta;
import com.alidasoftware.pos.util.Utils;

public class AdeudoVentaBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = -4493865258097232464L;
	
	private List<AdeudoVenta> listaAdeudoVenta;
	private AdeudoVenta adeudoVenta;
	private AdeudoVenta adeudoSelected;
	private List<AdeudoHelper> listaAdeudoHelper;
	private AdeudoHelper adeudoHelper;
	private AdeudoVentaFacade adeudoVentaFacade;
	private VentaFacade ventaFacade;
	private ApartadoFacade apartadoFacade;
	private PagoApartadoFacade pagoApartadoFacade;
	private PagoVentaFacade pagoVentaFacade;
	private Apartado apartado;
	private Venta venta;
	private boolean editMode;
	private boolean showListDialog;
	private boolean showViewDialog;
	private boolean showEditDialog;	
	private float totalOperacion;
	private String titlePanel;
	private boolean showListPago;
	private boolean showListAdeudo;
	private String recordType;
		
	public String getTitlePanel() {
		return titlePanel;
	}

	public void setTitlePanel(String titlePanel) {
		this.titlePanel = titlePanel;
	}

	public boolean isShowListPago() {
		return showListPago;
	}

	public void setShowListPago(boolean showListPago) {
		this.showListPago = showListPago;
	}

	public boolean isShowListAdeudo() {
		return showListAdeudo;
	}

	public void setShowListAdeudo(boolean showListAdeudo) {
		this.showListAdeudo = showListAdeudo;
	}

	public float getTotalOperacion() {
		return totalOperacion;
	}

	public void setTotalOperacion(float totalOperacion) {
		this.totalOperacion = totalOperacion;
	}

	public List<AdeudoHelper> getListaAdeudoHelper() {
		return listaAdeudoHelper;
	}

	public void setListaAdeudoHelper(List<AdeudoHelper> listaAdeudoHelper) {
		this.listaAdeudoHelper = listaAdeudoHelper;
	}

	public AdeudoHelper getAdeudoHelper() {
		return adeudoHelper;
	}

	public void setAdeudoHelper(AdeudoHelper adeudoHelper) {
		this.adeudoHelper = adeudoHelper;
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

	public boolean isShowEditDialog() {
		return showEditDialog;
	}

	public void setShowEditDialog(boolean showEditDialog) {
		this.showEditDialog = showEditDialog;
	}
	
	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	public List<AdeudoVenta> getListaAdeudoVenta() {
		return listaAdeudoVenta;
	}
	public void setListaAdeudoVenta(List<AdeudoVenta> listaAdeudoVenta) {
		this.listaAdeudoVenta = listaAdeudoVenta;
	}
	public AdeudoVenta getAdeudoVenta() {
		return adeudoVenta;
	}
	public void setAdeudoVenta(AdeudoVenta adeudoVenta) {
		this.adeudoVenta = adeudoVenta;
	}
	public AdeudoVenta getAdeudoSelected() {
		return adeudoSelected;
	}
	public void setAdeudoSelected(AdeudoVenta adeudoSelected) {
		this.adeudoSelected = adeudoSelected;
	}
	
	private ApartadoBean getApartadoBean() {
		return (ApartadoBean) Utils.getManagedBean("apartadoBean");
	}
	
	public AdeudoVentaBean() {		
		showListDialogListener();
	}
	
	private void loadAdeudoVentas() {
		try {
			listaAdeudoVenta = getAdeudoVentaFacade().listAll();
		} catch (AlidaPosException ex) {
			//System.out.println("Error Load : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
    }	
	
	private AdeudoVentaFacade getAdeudoVentaFacade() {
		if (adeudoVentaFacade == null) {
			adeudoVentaFacade = new  AdeudoVentaFacade();
		}
		return adeudoVentaFacade;
	}
	
	private PagoApartadoFacade getPagoApartadoFacade() {
		if (pagoApartadoFacade == null) {
			pagoApartadoFacade = new PagoApartadoFacade();
		}
		return pagoApartadoFacade;
	}
	
	private PagoVentaFacade getPagoVentaFacade() {
		if (pagoVentaFacade == null) {
			pagoVentaFacade = new PagoVentaFacade();
		}
		return pagoVentaFacade;
	}
	
	private VentaFacade getVentaFacade() {
		if (ventaFacade == null) {
			ventaFacade = new VentaFacade();
		}
		return ventaFacade;
	}
	
	private ApartadoFacade getApartadoFacade() {
		if (apartadoFacade == null) {
			apartadoFacade = new ApartadoFacade();
		}
		return apartadoFacade;
	}
	
	private PagoBean getPagoBean() {
		return (PagoBean) Utils.getManagedBean("pagoBean");
	}
	
	public void showListDialogListener() {
		showEditDialog = false;		
		showViewDialog = false;
		showListDialog = true;
		editMode = false;
		loadAdeudoVentas();
	}
	
	public String updatePaymentList(String folio, String module, int idOperacion) {
		//System.out.println("Actualizando tabla de pagos");
		listaAdeudoHelper = loadPaymentInformation(folio, module, idOperacion, true);		                                            
		RequestContext.getCurrentInstance().update("AdeudoVentaListForm:adeudoVentaEditPanel");
		return "/adeudos/List.xhtml?faces-redirect=true";
	}
	
	private List<AdeudoHelper> loadPaymentInformation(String folio, String module, int idOperacion, boolean checkAdeudo) {
		List<AdeudoHelper> list = new ArrayList<AdeudoHelper>();
		//System.out.println("-------------------------------------------------------------------");
		//System.out.println("--------------------- CARGANDO LISTA DE PAGOS ---------------------");
		//System.out.println("id : " + idOperacion + ", folio : " + folio + ", modulo : " + module);
		//System.out.println("-------------------------------------------------------------------");
		try {
			AdeudoHelper helper = null;
			if (ApplicationBean.MODULE_APARTADO.equals(module)) {
				recordType = ApplicationBean.MODULE_APARTADO;
				if (checkAdeudo) {
					apartado = getApartadoFacade().findById(adeudoSelected.getIdoperacion());
					totalOperacion = apartado.getTotal();
				}				
				List<Pagoapartado> auxlist = getPagoApartadoFacade().listByIdApartado(idOperacion);	
				for (int i = 0; i < auxlist.size(); i++) {
					helper = new AdeudoHelper();
					helper.setIdadeudo( auxlist.get(i).getIdpago() );
					helper.setCantidad( auxlist.get(i).getCantidad());
					helper.setComentarios( auxlist.get(i).getComentarios());
					helper.setFecha( auxlist.get(i).getFecha());
					helper.setFormapago( auxlist.get(i).getFormapago());
					helper.setReferenciapago( auxlist.get(i).getReferenciapago());
					helper.setCambio( auxlist.get(i).getCambio() );
					list.add(helper);
				}
			} else if (ApplicationBean.MODULE_VENTA.equals(module)) {
				recordType = ApplicationBean.MODULE_VENTA;
				if (checkAdeudo) {
					venta = getVentaFacade().findVenta(adeudoSelected.getIdoperacion());
					totalOperacion = venta.getTotal();
				}
				List<Pagoventa> auxlist = getPagoVentaFacade().listByIdVenta(idOperacion);
				for (int i = 0; i < auxlist.size(); i++) {
					helper = new AdeudoHelper();
					helper.setIdadeudo( auxlist.get(i).getIdpago() );
					helper.setCantidad( auxlist.get(i).getCantidad());
					helper.setComentarios( auxlist.get(i).getComentarios());
					helper.setFecha( auxlist.get(i).getFecha());
					helper.setFormapago( auxlist.get(i).getFormapago());
					helper.setReferenciapago( auxlist.get(i).getReferenciapago());
					helper.setCambio( auxlist.get(i).getCambio() );
					list.add(helper);
				}
			}
		} catch (Exception ex) {
			//System.out.println("Error LoadPagoList : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		return list;
	}
	
	public void showEditDialogListener() {
		showEditDialog = true;		
		showViewDialog = false;
		showListDialog = false;
		showListAdeudo = true;
		editMode = true;
		listaAdeudoHelper = loadPaymentInformation(adeudoSelected.getFoliooperacion(), adeudoSelected.getModule(), adeudoSelected.getIdoperacion(), true);
	}	
	
	public void showViewDialogListener() {
		showEditDialog = false;		
		showViewDialog = true;
		showListDialog = false;
		editMode = false;
	}
	
	public void addPayment(ActionEvent actionEvent) {
		try {
			PagoBean pagoBean = getPagoBean();
			if (pagoBean != null) {
				if (adeudoSelected.getModule().equals(ApplicationBean.MODULE_APARTADO)) {
					recordType = ApplicationBean.MODULE_APARTADO;
					pagoBean.setAddPago(adeudoSelected, apartado, true, false);
				} else if (adeudoSelected.getModule().equals(ApplicationBean.MODULE_VENTA)) {
					recordType = ApplicationBean.MODULE_VENTA;
					pagoBean.setAddPago(adeudoSelected, venta, true, false);
				}				
			}
		} catch(Exception ex) {
			//System.out.println("Error AddPayment : " + ex.getMessage());
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}
	
	public void showPartialListPago(AdeudoVenta currentAdeudo) {
		adeudoSelected = currentAdeudo;
		showListPago = false;
		showListAdeudo = true;
		titlePanel = "Agregar Pago";
		showEditDialogListener();		
	}
	
	public void showCompleteListPago(Object record) {
		showListAdeudo = false;
		showListPago = true;
		showEditDialog = true;
		showViewDialog = false;
		showListDialog = false;
		titlePanel = "Lista de Pagos";
		if (record instanceof Apartado) {
			recordType = ApplicationBean.MODULE_APARTADO;
			apartado = (Apartado) record;
			listaAdeudoHelper = loadPaymentInformation(apartado.getFolio(), recordType, apartado.getIdapartado(), false);
		} else if (record instanceof Venta) {
			recordType = ApplicationBean.MODULE_VENTA;
			venta = (Venta) record;
			listaAdeudoHelper = loadPaymentInformation(venta.getFolio(), recordType, venta.getIdventa(), false);
		}
	}
	
	public Date getInfoFecha() {
		if (recordType.equals(ApplicationBean.MODULE_APARTADO) && apartado != null) {
			return apartado.getFechaapartado();
		} else if (recordType.equals(ApplicationBean.MODULE_VENTA) && venta != null) {
			return venta.getFecha();
		}
		return new Date();
	}
	
	public float getInfoTotalOperacion() {
		if (recordType.equals(ApplicationBean.MODULE_APARTADO) && apartado != null) {
			return apartado.getTotal();
		} else if (recordType.equals(ApplicationBean.MODULE_VENTA) && venta != null) {
			return venta.getTotal();
		}
		return 0.0f;
	}
	
	public String getInfoTipoOperacion() {
		if (recordType.equals(ApplicationBean.MODULE_APARTADO) && apartado != null) {
			return ApplicationBean.MODULE_APARTADO;
		} else if (recordType.equals(ApplicationBean.MODULE_VENTA) && venta != null) {
			return ApplicationBean.MODULE_VENTA;
		}
		return "";
	}
	
	public String getInfoFolioOperacion() {
		if (recordType.equals(ApplicationBean.MODULE_APARTADO) && apartado != null) {
			return apartado.getFolio();
		} else if (recordType.equals(ApplicationBean.MODULE_VENTA) && venta != null) {
			return venta.getFolio();
		}
		return "";
	}
	
	public String showDetailRecordAction() {
		//System.out.println("Modulo : " + recordType);
		if (recordType.equals(ApplicationBean.MODULE_APARTADO) ) {
			if (apartado == null) {
				try {
					apartado = getApartadoFacade().findById(adeudoSelected.getIdoperacion());
				} catch(Exception ex) {
					//System.out.println("Error ApartadoDetailInfo : " + ex.getMessage());
					keepDialogOpen();
		            displayErrorMessageToUser("Error: " + ex.getMessage());
				}
			}
			if (apartado != null) {
				getApartadoBean().setApartadoSelected(apartado);
				getApartadoBean().showViewDialogListener();			
				return "/ventas/apartado/List.xhtml?faces-redirect=true";
			}
		} else if (recordType.equals(ApplicationBean.MODULE_VENTA) ) {
			if (venta == null) {
	
			}
			if (venta != null) {
				
			}
		}
		return null;
	}
	
}
