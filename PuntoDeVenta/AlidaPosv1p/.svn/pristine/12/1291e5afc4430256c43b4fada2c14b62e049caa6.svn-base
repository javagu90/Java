package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.joda.time.DateTime;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.AdeudoVentaFacade;
import com.alidasoftware.pos.facade.ApartadoFacade;
import com.alidasoftware.pos.facade.ClienteFacade;
import com.alidasoftware.pos.facade.DetalleInventarioFacade;
import com.alidasoftware.pos.facade.ValeFacade;
import com.alidasoftware.pos.facade.VentaFacade;
import com.alidasoftware.pos.facade.VistaFacade;
import com.alidasoftware.pos.helper.PaymentHelper;
import com.alidasoftware.pos.model.AdeudoVenta;
import com.alidasoftware.pos.model.Apartado;
import com.alidasoftware.pos.model.Cliente;
import com.alidasoftware.pos.model.Detalleinventario;
import com.alidasoftware.pos.model.Detalleventa;
import com.alidasoftware.pos.model.Pagoapartado;
import com.alidasoftware.pos.model.Pagoventa;
import com.alidasoftware.pos.model.Tipovencimiento;
import com.alidasoftware.pos.model.Vale;
import com.alidasoftware.pos.model.Venta;
import com.alidasoftware.pos.model.Vista;
import com.alidasoftware.pos.util.Utils;

public class PagoBean extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 7040942771573143636L;

	private boolean flagPagoVista;
	private boolean flagPagoVenta;
	private boolean flagPagoApartado;
	private Float efectivo;
	private Float ingresado;
	private Float cambio;
	private Float credito;
	private Float tarjeta;
	private Float vale;
	private Float total;
	private Float pago;
	private String refTarjeta;
	private String refVale;
	private boolean showEfectivo;
	private boolean showCredito;
	private boolean showTarjeta;
	private boolean showVale;
	private boolean pagoEfectivo;
	private boolean pagoCredito;
	private boolean pagoTarjeta;
	private boolean pagoVale;
	private boolean pagoAcum;
	private ClienteFacade clientFacade;
	private ValeFacade valeFacade;
	private VentaFacade ventaFacade;
	private VistaFacade vistaFacade;
	private DetalleInventarioFacade detailInvetoryFacade;
	private ApartadoFacade apartadoFacade;
	private AdeudoVentaFacade adeudoVentaFacade;
	private Cliente client;
	private float limiteCredito;
	private Venta venta;
	private Vista vista;
	private PaymentHelper payment;
	private Apartado apartado;
	private List<Vale> valeList;
	private String titlePago;
	private AdeudoVenta adeudo;
	private boolean updateView;
	private boolean firstPayment;
	private String message;
				
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitlePago() {
		return titlePago;
	}

	public void setTitlePago(String titlePago) {
		this.titlePago = titlePago;
	}

	public float getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(float limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public Cliente getClient() {
		return client;
	}

	public void setClient(Cliente client) {
		this.client = client;
	}
	
	public boolean isResto() {
		return Float.compare(pago, 0.0f) > 0 && Float.compare(pago, total) < 0;
	}

	public boolean isPagoCompleto() {
		return pagoAcum;
	}

	public void setPagoCompleto(boolean pagoCompleto) {
		this.pagoAcum = pagoCompleto;
	}

	public boolean isShowEfectivo() {
		return showEfectivo;
	}

	public void setShowEfectivo(boolean showEfectivo) {
		this.showEfectivo = showEfectivo;
	}

	public boolean isShowCredito() {
		return showCredito;
	}

	public void setShowCredito(boolean showCredito) {
		this.showCredito = showCredito;
	}

	public boolean isShowTarjeta() {
		return showTarjeta;
	}

	public void setShowTarjeta(boolean showTarjeta) {
		this.showTarjeta = showTarjeta;
	}

	public boolean isShowVale() {
		return showVale;
	}

	public void setShowVale(boolean showVale) {
		this.showVale = showVale;
	}

	public String getRefTarjeta() {
		return refTarjeta;
	}

	public void setRefTarjeta(String refTarjeta) {
		this.refTarjeta = refTarjeta;
	}

	public String getRefVale() {
		return refVale;
	}

	public void setRefVale(String refVale) {
		this.refVale = refVale;
	}

	public Float getPago() {
		return pago;
	}

	public void setPago(Float pago) {
		this.pago = pago;
	}

	public Float getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(Float efectivo) {
		this.efectivo = efectivo;
	}

	public Float getIngresado() {
		return ingresado;
	}

	public void setIngresado(Float ingresado) {
		this.ingresado = ingresado;
	}

	public Float getCambio() {
		return cambio;
	}

	public void setCambio(Float cambio) {
		this.cambio = cambio;
	}

	public Float getCredito() {
		return credito;
	}

	public void setCredito(Float credito) {
		this.credito = credito;
	}

	public Float getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Float tarjeta) {
		this.tarjeta = tarjeta;
	}

	public Float getVale() {
		return vale;
	}

	public void setVale(Float vale) {
		this.vale = vale;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
	
	public boolean isFlagPagoVista() {
		return flagPagoVista;
	}

	public void setFlagPagoVista(boolean flagPagoVista) {
		this.flagPagoVista = flagPagoVista;
	}

	public boolean isFlagPagoVenta() {
		return flagPagoVenta;
	}

	public void setFlagPagoVenta(boolean flagPagoVenta) {
		this.flagPagoVenta = flagPagoVenta;
	}
	
	public boolean isFlagPagoapartado() {
		return flagPagoApartado;
	}

	public void setFlagPagoApartado(boolean flagPagoApartado) {
		this.flagPagoApartado = flagPagoApartado;
	}
	
	private ClienteFacade getClientFacade() {
		if (clientFacade == null) {
			clientFacade = new ClienteFacade();
		}
		return clientFacade;
	}
	
	private ValeFacade getValeFacade() {
		if (valeFacade == null) {
			valeFacade = new ValeFacade();
		}
		return valeFacade;
	}
	
	private VentaFacade getVentaFacade() {
		if (ventaFacade == null) {
			ventaFacade = new VentaFacade();
		}
		return ventaFacade;
	}
	
	private VistaFacade getVistaFacade() {
		if (vistaFacade == null) {
			vistaFacade = new VistaFacade();
		}
		return vistaFacade;
	}
	
	private DetalleInventarioFacade getDetailInventoryFacade() {
		if (detailInvetoryFacade == null) {
			detailInvetoryFacade = new DetalleInventarioFacade();
		}
		return detailInvetoryFacade;
	}
	
	private ApartadoFacade getApartadoFacade() {
		if (apartadoFacade == null) {
			apartadoFacade = new  ApartadoFacade();
		}
		return apartadoFacade;
	}
	
	private AdeudoVentaFacade getAdeudoVentaFacade() {
		if (adeudoVentaFacade == null) {
			adeudoVentaFacade = new AdeudoVentaFacade();
		}
		return adeudoVentaFacade;
	}
	
	private VentaBean getVentaBean() {
		return (VentaBean) Utils.getManagedBean("ventaBean");
	}
	
	private ApartadoBean getApartadoBean() {
		return (ApartadoBean) Utils.getManagedBean("apartadoBean");
	}
	
	private AdeudoVentaBean getAdeudoVentaBean() {
		return (AdeudoVentaBean) Utils.getManagedBean("adeudoVentaBean");
	}

	public PagoBean() {
		flagPagoVenta = false;
		flagPagoApartado = false;
		flagPagoVista = false;
		initializeValues();
	}
	
	private LoginBean getLoginBean() {
		return (LoginBean) Utils.getManagedBean("loginBean");
	}

	private CorteCajaBean getCorteCajaBean() {
		return (CorteCajaBean) Utils.getManagedBean("corteCajaBean");
	}
	
	public void showEfectivoListener() {	
		showEfectivo = true;
		showCredito = false;
		showTarjeta = false;
		showVale = false;
		updatePago();
		RequestContext.getCurrentInstance().update("pagoForm:pagoGrid");
	}
	
	public void showCreditoListener() {
		showEfectivo = false;
		showCredito = true;
		showTarjeta = false;
		showVale = false;
		updatePago();
		RequestContext.getCurrentInstance().update("pagoForm:pagoGrid");
	}
	
	public void showTarjetaListener() {
		showEfectivo = false;
		showCredito = false;
		showTarjeta = true;
		showVale = false;
		updatePago();
		RequestContext.getCurrentInstance().update("pagoForm:pagoGrid");
	}
	
	public void showValeListener() {
		showEfectivo = false;
		showCredito = false;
		showTarjeta = false;
		showVale = true;
		updatePago();
		RequestContext.getCurrentInstance().update("pagoForm:pagoGrid");
	}	
	
	private void initializeValues() {		
		efectivo = 0.0f;
		ingresado = 0.0f;
		cambio = 0.0f;
		credito = 0.0f;
		tarjeta = 0.0f;
		vale = 0.0f;
		pago = 0.0f;
		refTarjeta = "";
		refVale ="";
		total = 0.0f;
		pagoAcum = false;
	}
	
	private void updatePago() {
		pago = 0.0f;
		pagoEfectivo = false;
		pagoCredito = false;
		pagoTarjeta = false;
		pagoVale = false;
		
		message = "";
		RequestContext.getCurrentInstance().update("pagoForm:msgPago");
		
		// Se verifica si se ha realizado un pago se hizo en efectivo
		if (Float.compare(ingresado, 0.0f) > 0) {
			pago += ingresado;
			pagoEfectivo = true;
		}
		if (Float.compare(pago, total) > 0) {			
			cambio = Math.abs(pago - total);
			/*
			refTarjeta = "";
			tarjeta = 0.0f;
			refVale = "";
			vale = 0.0f;
			valeList = new ArrayList<Vale>();
			credito = 0.0f;
			*/
			pagoAcum = false;
			//return;
		} else {
			cambio = 0.0f;
			pagoAcum = true;
		}
		// Se verifica si se ha ingresado un pago con tarjeta
		if (!refTarjeta.equals("")) {
			pago += tarjeta;
			pagoTarjeta = true;
		}
		if (Float.compare(pago, total) > 0) {
			cambio = Math.abs(pago - total);
			/*
			refVale = "";
			vale = 0.0f;
			valeList = new ArrayList<Vale>();
			credito = 0.0f;	
			*/
			pagoAcum = false;
			//return;
		} else {
			cambio = 0.0f;
			pagoAcum = true;
		}
		// Se verifica si se ha ingresado algun vale
		if (valeList != null && valeList.size() > 0) {
			vale = 0.0f;
			for (int i = 0; i < valeList.size();  i++) {
				vale += valeList.get(i).getCantidad();
			}
			pago += vale;
			pagoVale = true;
		}
		if (Float.compare(pago, total) > 0) {		
			cambio = Math.abs(pago - total);
			//credito = 0.0f;			
			pagoAcum = false;
			//return;
		} else {
			cambio = 0.0f;
			pagoAcum = true;
		}
		// Se verifica si se ha ingresado pago con credito
		if (Float.compare(credito, 0.0f) > 0) {
			pago += credito;
			pagoCredito = true;
		}
		if (Float.compare(pago, total) > 0) {
			cambio = Math.abs(pago - total);			
			pagoAcum = false;
		} else {
			cambio = 0.0f;
			pagoAcum = true;
		}
		// Se verifica si el pago se hizo solo con vales
		if (checkOnlyVoucherPayment()) {
			cambio = 0.0f;
		}
	}
	
	private boolean checkOnlyVoucherPayment() {
		if (!pagoEfectivo && !pagoTarjeta && !pagoCredito && pagoVale) {
			return true;
		}
		return false;
	}
	
	public void enableCredit(ActionEvent actionEvent) {
		try {
			client.setCredito(true);	
			client.setCreditodisponible(limiteCredito);
			client.setLimitecredito(limiteCredito);
			getClientFacade().updateCliente(client);
		} catch (Exception ex) {
			keepDialogOpen();
            displayErrorMessageToUser("No se puede habilitar crédito.");
		}
	}
	
	private boolean checkExpirationDate(Vale auxVale) throws AlidaPosException {		
		Tipovencimiento vence = auxVale.getTipovencimiento();
		Integer tipoDias = Integer.valueOf(vence.getTipodias());
		DateTime day = new DateTime(Utils.getExpirationDate(auxVale.getFecha(), vence.getDias(), tipoDias));
    	if (day.isBeforeNow()) {
    		return false;
    	}
		return true;
	}
	
	private boolean checkValeInList(String folioVale) {
		if (valeList != null && valeList.size() > 0) {
			Vale auxVale = null;
			for (int i = 0; i < valeList.size(); i++) {
				auxVale = valeList.get(i);
				if (folioVale.toLowerCase().equals(auxVale.getFolio().toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}
	
	private Vale removeValeFromList(String folioVale) {
		if (valeList != null && valeList.size() > 0) {
			Vale auxVale = null;
			for (int i = 0; i < valeList.size(); i++) {
				auxVale = valeList.get(i);
				if (folioVale.toLowerCase().equals(auxVale.getFolio().toLowerCase())) {
					return auxVale;
				}
			}
		}
		return null;
	}
	
	public void cancelValeListener(ActionEvent actionEvent) {
		System.out.println("verificando informacion del vale a cancelar. (cancelValeListener)");
		try {
			if (!refVale.trim().equals("")) {
				Vale auxVale = removeValeFromList(refVale);
				if (auxVale != null) {
					refVale = "";
					valeList.remove(auxVale);
					updatePago();
					RequestContext.getCurrentInstance().update("pagoForm:pagoGrid");
				} else {
					refVale = "";
					message = "Error Vale: La referencia no es válida.";
					RequestContext.getCurrentInstance().update("pagoForm:msgPago");
					keepDialogOpen();
			        displayErrorMessageToUser(message);
				}
			} else {
				refVale = "";
				message = "Error Vale: Debe ingresar una referencia.";
				RequestContext.getCurrentInstance().update("pagoForm:msgPago");
				keepDialogOpen();
		        displayErrorMessageToUser(message);
			}
		} catch (Exception ex) {
			refVale = "";
			message = "Error al validar información del Vale.";
			RequestContext.getCurrentInstance().update("pagoForm:msgPago");
			keepDialogOpen();
	        displayErrorMessageToUser(message);
		}
	}
	
	public void checkValeListener(ActionEvent actionEvent) {
		System.out.println("verificando informacion del vale.. (checkValeListener)");
		try {
			if (!refVale.trim().equals("")) {
				Vale auxVale = getValeFacade().findByFolio(refVale);
				if (auxVale != null) {
					if (auxVale.getStatus() == ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VALES, ApplicationBean.STATUS_VALUE_OPEN)) {
						if (checkExpirationDate(auxVale)) {
							if (!checkValeInList(auxVale.getFolio())) {
								valeList.add(auxVale);
								refVale = "";
								updatePago();
								RequestContext.getCurrentInstance().update("pagoForm:pagoGrid");
							} else {
								refVale = "";
							}
						} else {
							refVale = "";
							message = "La vigencia del vale ha expirado, " + "\n" + "ingrese una referencia válida";
							RequestContext.getCurrentInstance().update("pagoForm:msgPago");
							keepDialogOpen();
							displayErrorMessageToUser(message);
						}
					} else {
						refVale = "";
						keepDialogOpen();
						if (auxVale.getStatus() == ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VALES, ApplicationBean.STATUS_VALUE_USED)) {
							message = "El vale ya ha sido utilizado, verifique la referencia";
							RequestContext.getCurrentInstance().update("pagoForm:msgPago");
							displayErrorMessageToUser(message);
						} else {
							message = "La vigencia del vale ha expirado, " + "\n" + "ingrese una referencia válida";
							RequestContext.getCurrentInstance().update("pagoForm:msgPago");
							displayErrorMessageToUser(message);
						}
					}
				} else {
					refVale = "";
					message = "Error Vale: La referencia no es válida.";
					RequestContext.getCurrentInstance().update("pagoForm:msgPago");
					keepDialogOpen();
			        displayErrorMessageToUser(message);
				}
			} else {
				refVale = "";
				message = "Error Vale: Debe ingresar una referencia.";
				RequestContext.getCurrentInstance().update("pagoForm:msgPago");
				keepDialogOpen();
		        displayErrorMessageToUser(message);
			}
		} catch (Exception ex) {
			refVale = "";
			message = "Error al validar información del Vale.";
			RequestContext.getCurrentInstance().update("pagoForm:msgPago");
			keepDialogOpen();
	        displayErrorMessageToUser(message);
		}		
	}
	
	private Pagoventa createSalePayment(float quantity, float change, String reference, String paymentType) {
		Pagoventa pagoVenta = null;
		pagoVenta = new Pagoventa();
		pagoVenta.setVenta(venta);
		pagoVenta.setCantidad(quantity);
		pagoVenta.setFecha(new Date());
		pagoVenta.setReferenciapago(reference);
		if (ApplicationBean.PAYMENT_CASH.equals(paymentType)) {
			pagoVenta.setCambio(change);
		}
		pagoVenta.setFormapago( ApplicationBean.getPaymentType( paymentType));
		return pagoVenta;
	}
	
	private Pagoapartado createApartadoPayment(float quantity, float change, String reference, String paymentType) {
		Pagoapartado pagoApartado = null;
		pagoApartado = new Pagoapartado();
		pagoApartado.setApartado(apartado);
		pagoApartado.setCantidad(quantity);
		pagoApartado.setFecha(new Date());
		pagoApartado.setReferenciapago(reference);
		if (ApplicationBean.PAYMENT_CASH.equals(paymentType)) {
			pagoApartado.setCambio(change);
		}
		pagoApartado.setFormapago( ApplicationBean.getPaymentType( paymentType));
		return pagoApartado;
	}
	
	private AdeudoVenta createAdeudoVenta(float quantity, Venta auxVenta) throws AlidaPosException {
		AdeudoVenta adeudoVenta = new AdeudoVenta();
		adeudoVenta.setAdeudo(quantity);
		adeudoVenta.setFecha(new Date());
		adeudoVenta.setFoliooperacion(auxVenta.getFolio());
		adeudoVenta.setIdoperacion(auxVenta.getIdventa());
		adeudoVenta.setModule(ApplicationBean.MODULE_VENTA);
		getAdeudoVentaFacade().createAdeudoVenta(adeudoVenta);
		return adeudoVenta;
	}
	
	private String saveVistaPayment() {
		venta = new Venta();
		venta.setCliente( vista.getCliente() );
		venta.setComentarios( vista.getComentarios() );
		venta.setFecha(new Date());
		venta.setTotal( payment.getTotal() );
		venta.setIva( payment.getTax() );
		venta.setSubtotal( payment.getSubtotal() );
		venta.setFolio(Utils.getFolio(ApplicationBean.MODULE_PREFIX_VENTA, ""));		
		Detalleventa detailSale = null;
		for (int i = 0; i < vista.getDetallevistas().size(); i++) {
			detailSale = new Detalleventa();
			detailSale.setCantidad( vista.getDetallevistas().get(i).getCantidad() );
			detailSale.setPreciounitario( vista.getDetallevistas().get(i).getPreciounitario() );
			detailSale.setProducto( vista.getDetallevistas().get(i).getProducto() );
			detailSale.setVenta(venta);
			venta.getDetalleventas().add(detailSale);
		}
		venta.setUsuario(getLoginBean().getUser());
		venta.setCaja(getCorteCajaBean().getCaja());
		return null;
	}
	
	private String saveVentaPayment() {
		List<Pagoventa> salePaymentList = new ArrayList<Pagoventa>();
		if (pagoEfectivo) {
			salePaymentList.add(createSalePayment(ingresado, cambio, "", ApplicationBean.PAYMENT_CASH));
		}
		if (pagoCredito) {
			salePaymentList.add(createSalePayment(credito, 0.0f, "", ApplicationBean.PAYMENT_CREDIT));
			try {
				createAdeudoVenta(credito, venta);
			} catch (Exception ex) {
				// mostrar error de que no se puede crear el adeudo para el credito
			}
			float available = client.getCreditodisponible() - credito;
			client.setCreditodisponible(available);
		}
		if (pagoTarjeta) {
			salePaymentList.add(createSalePayment(tarjeta, 0.0f, refTarjeta, ApplicationBean.PAYMENT_CARD));
		}
		if (pagoVale) {
			if (valeList != null && valeList.size() > 0) {
				float cantidad = 0.0f;
				String referencia = "";
				for (int i = 0; i < valeList.size();  i++) {
					cantidad = valeList.get(i).getCantidad();
					referencia = valeList.get(i).getFolio();
					salePaymentList.add( createSalePayment(cantidad, 0.0f, referencia, ApplicationBean.PAYMENT_VOUCHER));
				}
			}
		}	
		
		venta.setStatus(ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VENTA, ApplicationBean.STATUS_VALUE_DONE));
		venta.setPagoventas(salePaymentList);
		client.setUltimaoperacion(new Date());				
		try {
			// verificar como hacer esto en una sola transaccion.
			getVentaFacade().updateVenta(venta);
			getClientFacade().updateCliente(client);
			Detalleinventario detInv = null;
			for (int i = 0; i < venta.getDetalleventas().size(); i++) {
				detInv = getDetailInventoryFacade().findDetalleInventarioByProduct(venta.getDetalleventas().get(i).getProducto().getIdproducto());
				detInv.setUltimaoperacion(new Date());
				getDetailInventoryFacade().updateDetalleinventario(detInv);
			}
			if (flagPagoVista) {
				vista.setStatus(ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VISTA, ApplicationBean.STATUS_VALUE_DONE));					
			}
			getVistaFacade().updateVista(vista);
			if (valeList != null && valeList.size() > 0) {
				for (int i = 0; i < valeList.size();  i++) {
					valeList.get(i).setStatus(ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VALES, ApplicationBean.STATUS_VALUE_USED));
					getValeFacade().updateVale(valeList.get(i));
				}
			}
			// verificar como hacer esto en una sola transaccion.				
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
		
		return getVentaBean().updateAfterPayment(venta);
	}
	
	private String saveApartadoPayment() {
		List<Pagoapartado> apartadoPaymentList = new ArrayList<Pagoapartado>();
		if (pagoEfectivo) {
			apartadoPaymentList.add( createApartadoPayment(ingresado, cambio, "", ApplicationBean.PAYMENT_CASH));
		}		
		if (pagoTarjeta) {
			apartadoPaymentList.add( createApartadoPayment(tarjeta, 0.0f, refTarjeta, ApplicationBean.PAYMENT_CARD));
		}
		if (pagoVale) {
			if (valeList != null && valeList.size() > 0) {
				float cantidad = 0.0f;
				String referencia = "";
				for (int i = 0; i < valeList.size();  i++) {
					cantidad = valeList.get(i).getCantidad();
					referencia = valeList.get(i).getFolio();
					apartadoPaymentList.add( createApartadoPayment(cantidad, 0.0f, referencia, ApplicationBean.PAYMENT_VOUCHER));
				}
			}				
		}
		if (Float.compare(pago, total) >= 0) {
			apartado.setStatus(ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_APARTADO, ApplicationBean.STATUS_VALUE_GIVEN));
		}
		apartado.setPagoapartados(apartadoPaymentList);
		try {
			adeudo.setAdeudo( adeudo.getAdeudo() - (pago - cambio));
			if (Float.compare(adeudo.getAdeudo(), 0.0f) > 0) {
				getAdeudoVentaFacade().updateAdeudoVenta(adeudo);
			} else {
				getAdeudoVentaFacade().deleteAdeudoVenta(adeudo);
			}
			getApartadoFacade().updateApartado(apartado);
			if (valeList != null && valeList.size() > 0) {
				for (int i = 0; i < valeList.size();  i++) {
					valeList.get(i).setStatus(ApplicationBean.getSelectItemLabelValue(ApplicationBean.MODULE_VALES, ApplicationBean.STATUS_VALUE_USED));
					getValeFacade().updateVale(valeList.get(i));
				}
			}
		} catch (AlidaPosException ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}		
		if (updateView) {
			RequestContext.getCurrentInstance().execute("PF('pagoDialog').show().hide();");
			return getAdeudoVentaBean().updatePaymentList(apartado.getFolio(), ApplicationBean.MODULE_APARTADO, apartado.getIdapartado());
		} else {		
			if (firstPayment) {
				RequestContext.getCurrentInstance().execute("PF('pagoDialog').show().hide();");
				return getApartadoBean().updateAfterFirstPayment(apartado);
			} else {
				RequestContext.getCurrentInstance().execute("PF('pagoDialog').show().hide();");
				return getApartadoBean().updatePaymentInformation(apartado.getIdapartado());
			}
		}
	}
	
	public String savePayment() {
		if (Float.compare(pago, (float) 0.0) > 0) {
			if (Float.compare(pago, total) >= 0) {
				if (flagPagoVista) {
					return saveVistaPayment();				
				}
				if (flagPagoVenta) {
					return saveVentaPayment();
				}
			} else {
				message = "No se ha cubierto el total de la deuda, verificar porfavor.";
				RequestContext.getCurrentInstance().update("pagoForm:msgPago");
			}
			if (flagPagoApartado) {
				return saveApartadoPayment();
			}			
		} else {
			if (showCredito) {
				//if () {
					
				//}
			} else {
				message = "Debe ingresar alguna cantidad para realizar el pago.";
				RequestContext.getCurrentInstance().update("pagoForm:msgPago");
			}
		}
		return null;
	}
	
	public void makeSaleFromview(float totalSale, Vista currentVista, PaymentHelper currentPayment) {
		showEfectivoListener();
		initializeValues();
		flagPagoVenta = true;
		flagPagoVista = true;
		flagPagoApartado = false;
		client = currentVista.getCliente();
		vista = currentVista;
		payment = currentPayment;
		total = totalSale;
		valeList = new ArrayList<Vale>();
		RequestContext.getCurrentInstance().update("pagoForm:pagoGrid");
	}
		
	public void setPagoVenta(float totalSale, Venta currentVenta) {		
		showEfectivoListener();
		initializeValues();		
		flagPagoVenta = true;
		flagPagoVista = false;
		flagPagoApartado = false;
		client = currentVenta.getCliente();
		venta = currentVenta;
		total = totalSale;  
		valeList = new ArrayList<Vale>();
		System.out.println("flagPagoVenta    : " + flagPagoVenta);
		System.out.println("flagPagoVista    : " + flagPagoVista);
		System.out.println("flagPagoApartado : " + flagPagoApartado);
		
		RequestContext.getCurrentInstance().update("pagoForm:pnlBtnOptions");
		RequestContext.getCurrentInstance().update("pagoForm:pagoGrid");
	}	
	
	public void setPagoApartado(float totalSale, Apartado currentApartado) {
		try {
			valeList = new ArrayList<Vale>();
			initializeValues();
			flagPagoVenta = false;
			flagPagoVista = false;
			flagPagoApartado = true;
			client = currentApartado.getCliente();
			apartado = currentApartado;
			total = totalSale;  
			valeList = new ArrayList<Vale>();
			adeudo = new AdeudoVenta();
			adeudo.setAdeudo(apartado.getTotal());
			adeudo.setFecha(apartado.getFechaapartado());
			adeudo.setFoliooperacion(apartado.getFolio());
			adeudo.setIdoperacion(apartado.getIdapartado());
			adeudo.setModule(ApplicationBean.MODULE_APARTADO);		
			getAdeudoVentaFacade().createAdeudoVenta(adeudo);
			RequestContext.getCurrentInstance().update("pagoForm:pagoGrid");
		} catch (Exception ex) {
			keepDialogOpen();
            displayErrorMessageToUser("Error: " + ex.getMessage());
		}
	}
	
	public void setAddPago(AdeudoVenta currentAdeudo, Object currentRecord, boolean updateView, boolean firstPayment) {		
		showEfectivoListener();
		initializeValues();	
		flagPagoApartado = false;
		flagPagoVenta = false;
		flagPagoVista = false;
		this.updateView = updateView;
		this.firstPayment = firstPayment;
		if (currentRecord instanceof Apartado) {
			setTitlePago("Agregar Pago Apartado");
			flagPagoApartado = true;			
			apartado = (Apartado) currentRecord;
			client = apartado.getCliente();
		} else if (currentRecord instanceof Venta) {
			setTitlePago("Agregar Pago Venta");
			flagPagoVenta = true;
			venta = (Venta) currentRecord;
			client = venta.getCliente();
		}
		adeudo = currentAdeudo;
		total = currentAdeudo.getAdeudo();	
		valeList = new ArrayList<Vale>();
		RequestContext.getCurrentInstance().update("pagoForm:pagoGrid");
	}
	
	public void handleClose(CloseEvent event) {
		/*
		System.out.println("Cerrando dialog de PAGO");
		if (event != null) {
			if (flagPagoApartado) {
				getAdeudoVentaBean().updatePaymentList(apartado.getFolio(), ApplicationBean.MODULE_APARTADO, apartado.getIdapartado());
			} else if (flagPagoVenta) {
				getAdeudoVentaBean().updatePaymentList(venta.getFolio(), ApplicationBean.MODULE_VENTA, venta.getIdventa());
			}
			RequestContext.getCurrentInstance().execute("PF('pagoDialog').hide().hide();");
			System.out.println("Tabla de pagos y cerrando dialogo de pago actualizados");
		}
		*/
	}
	
}
