package com.alidasoftware.pos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.FormaPagoFacade;
import com.alidasoftware.pos.facade.TiendaFacade;
import com.alidasoftware.pos.facade.TipoVencimientoFacade;
import com.alidasoftware.pos.model.Formapago;
import com.alidasoftware.pos.model.Tienda;
import com.alidasoftware.pos.model.Tipovencimiento;

public class ApplicationBean extends AbstractBean  implements Serializable {

	private static final long serialVersionUID = -9004105909509127033L;

	public static String MODULE_NONE                  = "_NONE";
	public static String MODULE_VISTA                 = "VISTA";
	public static String MODULE_VENTA                 = "VENTA";
	public static String MODULE_APARTADO              = "APARTADO";
	public static String MODULE_VALES                 = "VALES";
	public static String MODULE_DEVOLUCION            = "DEVOLUCION";
 	
	public static String STATUS_VALUE_GENERATED       = "Generada";
	public static String STATUS_VALUE_OPEN            = "Abierto";
	public static String STATUS_VALUE_CLOSE           = "Cerrado";
	public static String STATUS_VALUE_CANCEL          = "Cancelado";
	public static String STATUS_VALUE_DONE            = "Realizada";
	public static String STATUS_VALUE_USED            = "Utilizado";
	public static String STATUS_VALUE_EXPIRED         = "Expirado";
	public static String STATUS_VALUE_GIVEN           = "Entregado";
	
	public static String PAYMENT_CASH                 = "Efectivo";
	public static String PAYMENT_CREDIT               = "Credito";
	public static String PAYMENT_CARD                 = "Tarjeta";
	public static String PAYMENT_VOUCHER              = "Vale";
		
	public static String SALE_PRICE_MODE_PERCENTAGE   = "1"; // "porcentaje"
	public static String SALE_PRICE_MODE_VALUE        = "2"; // "valor"	
	public static String TYPE_DAY_BUSINESS            = "1"; // "laboral"
	public static String TYPE_DAY_CALENDAR            = "2"; // "habil"
	public static String STATUS_DESCRIPTION_ACTIVE    = "Activo";
	public static String STATUS_DESCRIPTION_INACTIVE  = "Inactivo";
	public static String PATTERN_DATE                 = "yyyyMMdd'T'HHmmssz";
	public static String MODULE_PREFIX_VALE           = "VA-";
	public static String MODULE_PREFIX_VISTA          = "VI-";
	public static String MODULE_PREFIX_VENTA          = "VE-";
	public static String MODULE_PREFIX_APARTADO       = "AP-";
	
	private static List<SelectItem> statusVista               = null;
	private static List<SelectItem> statusVenta               = null;
	private static List<SelectItem> statusApartado            = null;
	private static List<SelectItem> statusVale                = null;
	private static List<SelectItem> saleOperation             = null;
	private static List<Date> holidays                        = null;
	private static List<Tipovencimiento> expirationTypeList   = null;
	private static List<Tienda> storeList                     = null;
	private static TipoVencimientoFacade expirationTypeFacade = null;
	private static TiendaFacade storeFacade                   = null;
	private static List<Formapago> paymentTypeList            = null;
	private static FormaPagoFacade paymentTypeFacade          = null;
	
	public ApplicationBean() {
		
	}
	
	private static void createStatusValeSelectItems() {
		statusVale = new ArrayList<SelectItem>();
		statusVale.add(new SelectItem(1, STATUS_VALUE_OPEN));
		statusVale.add(new SelectItem(2, STATUS_VALUE_USED));
		statusVale.add(new SelectItem(3, STATUS_VALUE_EXPIRED));
	}
	
	private static void createStatusVistaSelectItems() {
		statusVista = new ArrayList<SelectItem>();
		statusVista.add(new SelectItem(1, STATUS_VALUE_GENERATED));
		statusVista.add(new SelectItem(2, STATUS_VALUE_OPEN));
		statusVista.add(new SelectItem(3, STATUS_VALUE_DONE));
		statusVista.add(new SelectItem(4, STATUS_VALUE_CANCEL));
	}
	
	private static void createStatusVentaSelectItems() {
		statusVenta = new ArrayList<SelectItem>();
		statusVenta.add(new SelectItem(1, STATUS_VALUE_GENERATED));
		statusVenta.add(new SelectItem(2, STATUS_VALUE_OPEN));
		statusVenta.add(new SelectItem(3, STATUS_VALUE_DONE));			
	}
	
	private static void createStatusApartadoSelectItems() {
		statusApartado = new ArrayList<SelectItem>();
		statusApartado.add(new SelectItem(1, STATUS_VALUE_GENERATED));
		statusApartado.add(new SelectItem(2, STATUS_VALUE_OPEN));
		statusApartado.add(new SelectItem(3, STATUS_VALUE_GIVEN));
		statusApartado.add(new SelectItem(4, STATUS_VALUE_EXPIRED));
		statusApartado.add(new SelectItem(5, STATUS_VALUE_CANCEL));
	}
	
	private static void createSaleOperationSelectItems() {
		saleOperation = new ArrayList<SelectItem>();
		saleOperation.add(new SelectItem(1, MODULE_APARTADO));
		saleOperation.add(new SelectItem(2, MODULE_VENTA));
		saleOperation.add(new SelectItem(3, MODULE_VISTA));
	}
	
	public static List<SelectItem> getOperationSelectItems() {
		if (saleOperation == null) {
			createSaleOperationSelectItems();
		}
		return saleOperation;
	}
	
	public static List<SelectItem> getStatusSelectItems(String module) {
		if	(module.equals(MODULE_VISTA)) {
			if (statusVista == null) {
				createStatusVistaSelectItems();
			}
			return statusVista;
		} else if (module.equals(MODULE_APARTADO)) {
			if (statusApartado == null) {
				createStatusApartadoSelectItems();
			}
			return statusApartado;
		} else if (module.equals(MODULE_VENTA)) {
			if (statusVenta == null) {
				createStatusVentaSelectItems();
			}
			return statusVenta;
		} else if (module.equals(MODULE_VALES)) {
			if (statusVale == null) {
				createStatusValeSelectItems();
			}
			return statusVale;
		}
		return new ArrayList<SelectItem>();
	}
	
	public static void updateHolidays() throws AlidaPosException {
		loadHolidays();
	}
	
	public static List<Date> getHolidays() throws AlidaPosException {
		if (holidays == null) {
			loadHolidays();
		}
		return holidays;
	}
	
	private static void loadHolidays() throws AlidaPosException {
		holidays = new ArrayList<Date>();
		
		//DateTime day = new DateTime(recordDate);
	}
	
	public static void updateStoreList() throws AlidaPosException {
		loadStoreList();
	}
	
	public static List<Tienda> getStoreList() throws AlidaPosException {
		if (storeList == null) {
			loadStoreList();
		}
		return storeList;
	}
	
	private static TiendaFacade getStoreFacade() {
		if (storeFacade == null) {
			storeFacade = new TiendaFacade();
		}
		return storeFacade;
	}
	
	private static void loadStoreList() throws AlidaPosException {
		storeList = getStoreFacade().listAll();	
	}
	
	public static void reloadStoreList() throws AlidaPosException {
		loadStoreList();
	}
	
	public static void updateExpirationType() throws AlidaPosException {
		loadExpirationType();
	}
	
	public static List<Tipovencimiento> getExpirationTypeList() throws AlidaPosException {
		if (expirationTypeList == null) {
			loadExpirationType();
		}
		return expirationTypeList;
	}
	
	private static TipoVencimientoFacade getExpirationTypeFacade() {
		if (expirationTypeFacade == null) {
			expirationTypeFacade = new TipoVencimientoFacade();
		}
		return expirationTypeFacade;
	}
	
	private static void loadExpirationType() throws AlidaPosException {
		expirationTypeList = getExpirationTypeFacade().listByStatus(true);	
	}
	
	public static void reloadExpirationTypeList() throws AlidaPosException {
		loadExpirationType();
	}
	
	private static List<Formapago> getPaymentTypeList() throws AlidaPosException {
		if (paymentTypeList == null) {
			loadPaymentType();
		}
		return paymentTypeList;
	}
	
	private static FormaPagoFacade getPaymentTypeFacade() {
		if (paymentTypeFacade == null) {
			paymentTypeFacade = new FormaPagoFacade();
		}
		return paymentTypeFacade;
	}
	
	public static Formapago getPaymentType(String paymentType) {
		try {
			getPaymentTypeList();
			for (int i = 0; i < paymentTypeList.size(); i++) {
				if (paymentTypeList.get(i).getDescripcion().equals(paymentType)) {
					return paymentTypeList.get(i);
				}
			}
		} catch (Exception ex) {
			return null;
			//throw new AlidaPosException("No existe la forma de pago seleccionada");
		}
		return null;
	}
	
	private static void loadPaymentType() throws AlidaPosException {
		paymentTypeList = getPaymentTypeFacade().listAll();
	}
	
	public static Tipovencimiento getExpirationType(int idExpirationType) throws AlidaPosException {
		return getExpirationTypeFacade().findTipoVencimiento(idExpirationType);
	}
	
	public static int getOperationTypeValue(String label) {
		List<SelectItem> list = getOperationSelectItems();
		SelectItem item = null;
		for (int i = 0; i < list.size(); i++) {
            item = list.get(i);
            if (item.getLabel().equals(label)) {
            	return Integer.valueOf(item.getValue().toString());
            }
        }
		return -1;				
	}
	
	public static String getOperationTypeLabel(int value) {
		List<SelectItem> list = getOperationSelectItems();
		SelectItem item = null;
		for (int i = 0; i < list.size(); i++) {
            item = list.get(i);
            if (Integer.valueOf(item.getValue().toString()) == value) {
            	return item.getLabel();
            }
        }
		return "";
	}
	
	public static int getSelectItemLabelValue(String module, String label) {
		List<SelectItem> list = getStatusSelectItems(module);
		SelectItem item = null;
		for (int i = 0; i < list.size(); i++) {
            item = list.get(i);
            if (item.getLabel().equals(label)) {
            	return Integer.valueOf(item.getValue().toString());
            }
		}
		return -1;
	}
	
	public static String getSelectItemLabel(String module, int value) {
		List<SelectItem> list = getStatusSelectItems(module);
		SelectItem item = null;
		for (int i = 0; i < list.size(); i++) {
			item = list.get(i);
			if (Integer.valueOf(item.getValue().toString()) == value) {
				return item.getLabel();
			}
		}
		return "";
	}
	
	public static String getStatusDescription(boolean status) {
		if (status) {
			return STATUS_DESCRIPTION_ACTIVE;
		} else {
			return STATUS_DESCRIPTION_INACTIVE;
		}
	}

}
