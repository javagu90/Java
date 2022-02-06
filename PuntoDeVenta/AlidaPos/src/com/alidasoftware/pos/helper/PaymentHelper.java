package com.alidasoftware.pos.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.bean.ApplicationBean;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.facade.ConfiguracionFacade;
import com.alidasoftware.pos.facade.DetalleInventarioFacade;
import com.alidasoftware.pos.facade.PrecioVentaFacade;
import com.alidasoftware.pos.facade.PromocionFacade;
import com.alidasoftware.pos.model.Apartado;
import com.alidasoftware.pos.model.Categoriaproducto;
import com.alidasoftware.pos.model.Cliente;
import com.alidasoftware.pos.model.Detalleapartado;
import com.alidasoftware.pos.model.Detalleinventario;
import com.alidasoftware.pos.model.Detalleventa;
import com.alidasoftware.pos.model.Detallevista;
import com.alidasoftware.pos.model.Inventario;
import com.alidasoftware.pos.model.Precioventa;
import com.alidasoftware.pos.model.Producto;
import com.alidasoftware.pos.model.Promocion;
import com.alidasoftware.pos.model.Venta;
import com.alidasoftware.pos.model.Vista;
import com.alidasoftware.pos.util.Utils;

public class PaymentHelper implements Serializable {

	private static final long serialVersionUID = 2410250710883752486L;

	private float subtotal = 0.0f;
	private float tax      = 0.0f;
	private float total    = 0.0f;
	private float discount = 0.0f;
	private Map<Integer, DiscountHelper> discountList = new HashMap<Integer, DiscountHelper>();
	private Map<Integer, AmountTaxHelper> priceList = new HashMap<Integer, AmountTaxHelper>();
	private DetalleInventarioFacade detailStockFacade;
	private PrecioVentaFacade salePriceFacade;
	private PromocionFacade promotionFacade;	
	private ConfiguracionFacade configFacade;
	private Cliente client;
	
	public Cliente getClient() {
		return client;
	}

	public void setClient(Cliente client) {
		this.client = client;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public float getTax() {
		return tax;
	}

	public float getTotal() {
		return total;
	}
	
	public float getDiscount() {
		return discount;
	}
	
	public PaymentHelper() {
		
	}
	
	private DetalleInventarioFacade getDetailStockFacade() {
		if (detailStockFacade == null) {
			detailStockFacade = new DetalleInventarioFacade();
		}
		return detailStockFacade;
	}
	
	private PrecioVentaFacade getSalePriceFacade() {
		if (salePriceFacade == null) {
			salePriceFacade = new PrecioVentaFacade();
		}
		return salePriceFacade;
	}
	
	private PromocionFacade getPromotionFacade() {
		if (promotionFacade == null) {
			promotionFacade = new PromocionFacade();
		}
		return promotionFacade;
	}
	
	private ConfiguracionFacade getConfiguracionFacade() {
		if (configFacade == null) {
			configFacade = new ConfiguracionFacade();
		}
		return configFacade;
	}
	
	public void addDetailViewList(List<Detallevista> viewList, Inventario inventario) throws AlidaPosException {
		Detallevista view = null;
		for (int i = 0; i < viewList.size(); i++) {
			view = viewList.get(i);
			getProductDetailInformation(view, view.getProducto(), inventario, view.getCantidad());
		}
	}
	
	public void addDetailSaleList(List<Detalleventa> saleList, Inventario inventario) throws AlidaPosException {
		Detalleventa sale = null;
		for (int i = 0; i < saleList.size(); i++) {
			sale = saleList.get(i);
			getProductDetailInformation(sale, sale.getProducto(), inventario, sale.getCantidad());
		}
	}	
	
	public void addDetailApartadoList(List<Detalleapartado> apartadoList, Inventario inventario) throws AlidaPosException {
		Detalleapartado apartado = null;
		for (int i = 0; i< apartadoList.size(); i++) {
			apartado = apartadoList.get(i);
			getProductDetailInformation(apartado, apartado.getProducto(), inventario, apartado.getCantidad());
		}
	}
	
	public void getProductDetailInformation(Object object, Producto product, Inventario inventario, float quantity) throws AlidaPosException {
		float priceValue  = 0.0f;
		float amountValue = 0.0f;
		float taxValue    = 0.0f;
		float discount    = 0.0f;
		AmountTaxHelper amountTax = null;
		DiscountHelper discountHelper = null;			
		if (object instanceof Detalleapartado) {
			Detalleapartado apartadoDetail = (Detalleapartado) object;
			priceValue = apartadoDetail.getPreciounitario();
			amountValue = apartadoDetail.getCantidad() * priceValue;
	    } else if (object instanceof Detallevista) {
			Detallevista vistaDetail = (Detallevista) object;			
			priceValue = vistaDetail.getPreciounitario();				
			amountValue = vistaDetail.getCantidad() * priceValue;
		} else if ( object instanceof Detalleventa) {
			Detalleinventario detailInventory = getDetailStockFacade().findDetalleInventarioByProduct(product.getIdproducto());
			Detalleventa saleDetail = (Detalleventa) object;
			priceValue = saleDetail.getPreciounitario();
			amountValue = saleDetail.getCantidad() * priceValue;
			// se buscan las promociones del producto y se aplican los descuentos correspondientes
			discountHelper = applyPromotion(product, amountValue, detailInventory, inventario);
			amountValue = discountHelper.getFinalPrice();
			discount  = discountHelper.getTotalDiscount(); 
		}					
		taxValue  = product.getIva() * amountValue;		
		amountTax = new AmountTaxHelper(amountValue, taxValue, discount);
		discountList.put(product.getIdproducto(), discountHelper);
		priceList.put(product.getIdproducto(), amountTax);			
		updatePayment();
	}
	
	public boolean removeProductDetail(Object object, Producto product, Inventario inventario, float quantity) throws AlidaPosException {
		float priceValue              = 0.0f;
		float amountValue             = 0.0f;
		float taxValue                = 0.0f;
		boolean fractions             = false;
		boolean deleteProd            = false;
		int comp                      = 0;
		AmountTaxHelper amountTax     = null;
		DiscountHelper discountHelper = null;
		
		fractions = product.getUnidad().getAceptafracciones();
		Detalleinventario detailInventory = getDetailStockFacade().findDetalleInventarioByProductTienda(product.getIdproducto(), inventario.getIdinventario());
		if (!fractions) {			
			quantity =  (int) quantity;
		}
		detailInventory.setExistenciaventa(detailInventory.getExistenciaventa() + quantity);
		detailInventory.setExistenciaapvi(detailInventory.getExistenciaapvi() - quantity);
		getDetailStockFacade().updateDetalleinventario(detailInventory);
		if (object instanceof Detalleapartado) {
			Detalleapartado apartadoDetail = (Detalleapartado) object;
			apartadoDetail.setCantidad( apartadoDetail.getCantidad() - quantity );
			comp = Float.compare(apartadoDetail.getCantidad(), (float) 0.0);
			if (comp <= 0) {
				deleteProd = true;
			} else {
				priceValue = getProductPrice(product, apartadoDetail.getCantidad(), detailInventory);
				apartadoDetail.setPreciounitario(priceValue);
				amountValue = apartadoDetail.getCantidad() * priceValue;
			}
		} else if (object instanceof Detallevista) {
			Detallevista vistaDetail = (Detallevista) object;			
			vistaDetail.setCantidad( vistaDetail.getCantidad() - quantity );
			comp = Float.compare(vistaDetail.getCantidad(), (float) 0.0);
			if (comp <= 0) {
				deleteProd = true;
			} else {
				priceValue = getProductPrice(product, vistaDetail.getCantidad(), detailInventory);		
				vistaDetail.setPreciounitario(priceValue);				
				amountValue = vistaDetail.getCantidad() * priceValue;
			}			
		} else if (object instanceof Detalleventa) {
			Detalleventa saleDetail = (Detalleventa) object;
			saleDetail.setCantidad( saleDetail.getCantidad() - quantity );
			comp = Float.compare(saleDetail.getCantidad(), (float) 0.0);
			if (comp <= 0) {
				deleteProd = true;
			} else {
				priceValue = getProductPrice(product, saleDetail.getCantidad(), detailInventory);
				saleDetail.setPreciounitario(priceValue);
				amountValue = saleDetail.getCantidad() * priceValue;
				// se buscan las promociones del producto y se aplican los descuentos correspondientes
				discountHelper = applyPromotion(product, amountValue, detailInventory, inventario);
				amountValue = discountHelper.getFinalPrice();
				discount  = discountHelper.getTotalDiscount();
			}
		}					
		if	(deleteProd) {
			priceList.remove(product.getIdproducto());
		} else {
			taxValue  = product.getIva() * amountValue;		
			amountTax = new AmountTaxHelper(amountValue, taxValue, discount);
			discountList.put(product.getIdproducto(), discountHelper);
			priceList.put(product.getIdproducto(), amountTax);
		}
		updatePayment();
		System.out.println("Eliminando producto...");
		return true;
	}

	public boolean addProductDetail(Object object, Producto product, Inventario inventario, float quantity) throws AlidaPosException {		
		float priceValue  = 0.0f;
		float amountValue = 0.0f;
		float taxValue    = 0.0f;
		float discount    = 0.0f;
		boolean fractions = false;
		AmountTaxHelper amountTax = null;
		DiscountHelper discountHelper = null;		
		float stockProduct;
		
		//System.out.println("checa fracciones : " + product.getUnidad().getAceptafracciones());
		fractions = product.getUnidad().getAceptafracciones();
		//System.out.println("agregando producto");
		Detalleinventario detailInventory = getDetailStockFacade().findDetalleInventarioByProductTienda(product.getIdproducto(), inventario.getIdinventario());
		if (fractions) {
			stockProduct = detailInventory.getExistenciaventa() - quantity;
		} else {
			stockProduct = detailInventory.getExistenciaventa() - (int) quantity;
		}	
		//System.out.println("Stock : " + stockProduct);
		if (stockProduct >= 0) {
			detailInventory.setExistenciaventa(detailInventory.getExistenciaventa() - quantity);
			detailInventory.setExistenciaapvi(detailInventory.getExistenciaapvi() + quantity);
			getDetailStockFacade().updateDetalleinventario(detailInventory);
			if (object instanceof Detalleapartado) {
				Detalleapartado apartadoDetail = (Detalleapartado) object;
				apartadoDetail.setCantidad( apartadoDetail.getCantidad() + quantity );
				priceValue = getProductPrice(product, apartadoDetail.getCantidad(), detailInventory);
				apartadoDetail.setPreciounitario(priceValue);
				amountValue = apartadoDetail.getCantidad() * priceValue;
		    } else if (object instanceof Detallevista) {
				Detallevista vistaDetail = (Detallevista) object;			
				vistaDetail.setCantidad( vistaDetail.getCantidad() + quantity );
				priceValue = getProductPrice(product, vistaDetail.getCantidad(), detailInventory);		
				vistaDetail.setPreciounitario(priceValue);				
				amountValue = vistaDetail.getCantidad() * priceValue;
			} else if ( object instanceof Detalleventa) {
				Detalleventa saleDetail = (Detalleventa) object;
				saleDetail.setCantidad( saleDetail.getCantidad() + quantity );
				priceValue = getProductPrice(product, saleDetail.getCantidad(), detailInventory);
				saleDetail.setPreciounitario(priceValue);
				amountValue = saleDetail.getCantidad() * priceValue;
				// se buscan las promociones del producto y se aplican los 
				// descuentos correspondientes (solo ventas)
				discountHelper = applyPromotion(product, amountValue, detailInventory, inventario);
				amountValue = discountHelper.getFinalPrice();
				discount  = discountHelper.getTotalDiscount(); 
			}					
			taxValue  = product.getIva() * amountValue;		
			amountTax = new AmountTaxHelper(amountValue, taxValue, discount);
			discountList.put(product.getIdproducto(), discountHelper);
			priceList.put(product.getIdproducto(), amountTax);			
			updatePayment();
			//System.out.println("UpdatePago...");	
		} else {
			throw new AlidaPosException("Solo existen " + stockProduct + " en inventario");
		}		
		return true;
	}
	
	public void removeAllProductDetail(Object object, Producto product) throws AlidaPosException {
		try {
			float quantity;
			Detalleinventario detailInventory = getDetailStockFacade().findDetalleInventarioByProduct(product.getIdproducto());
			if (object instanceof Detallevista) {
				quantity = ((Detallevista) object).getCantidad();
				detailInventory.setExistenciaapvi(detailInventory.getExistenciaapvi() - quantity);
			} else if (object instanceof Detalleapartado) {
				quantity = ((Detalleapartado) object).getCantidad();
				detailInventory.setExistenciaapvi(detailInventory.getExistenciaapvi() - quantity);
			} else {
				quantity = ((Detalleventa) object).getCantidad();
			}
			detailInventory.setExistenciaventa(detailInventory.getExistenciaventa() + quantity);		
			getDetailStockFacade().updateDetalleinventario(detailInventory);				
			priceList.remove(product.getIdproducto());
			updatePayment();
		} catch (Exception ex) {
			throw new AlidaPosException("No se pudo eliminar el producto de la lista.");
		}
	}
	
	private void updatePayment() {
		AmountTaxHelper amountTax;
		this.subtotal = 0f;
		this.tax      = 0f;
		Iterator<Integer> it = priceList.keySet().iterator();

		while(it.hasNext()){
		  Integer key = it.next();
		  amountTax = (AmountTaxHelper) priceList.get(key);
		  this.subtotal += amountTax.getAmount();
		  this.tax += amountTax.getTax();
		}
		
		this.total = (this.subtotal + this.tax) - this.discount;
		/*
		System.out.println("subtotal : " + this.subtotal);
		System.out.println("tax      : " + this.tax);
		System.out.println("discount : " + this.discount);
		System.out.println("total    : " + this.total);
		*/
	}
	
	public DiscountHelper applyPromotion(Producto product, float price, Detalleinventario detailInventory, Inventario inventario) throws AlidaPosException {
		DiscountHelper discountHelper = new DiscountHelper(product.getIdproducto(), price);
		Promocion promotion;
		float discountPromo = 0.0f;
		List<Promocion> promotionList = new ArrayList<Promocion>();
		List<Promocion> auxPromotionList = new ArrayList<Promocion>();
		if	(detailInventory == null) {
			detailInventory = getDetailStockFacade().findDetalleInventarioByProductTienda(product.getIdproducto(), inventario.getIdinventario());
		}
		System.out.println("Buscando promociones... producto: " + product.getIdproducto() + " - tienda: " + inventario.getTienda().getIdtienda());
		// Se buscan las promociones asociadas al producto.
		promotionList = getPromotionFacade().findPromocionByProductTiendaFecha(product.getIdproducto(), inventario.getTienda().getIdtienda());
		// Se buscan las promociones asociadas a la(s) categoria(s) del producto.
		auxPromotionList = getPromotionFacade().findPromocionByCategoriaTiendaFecha(product.getIdproducto(), inventario.getTienda().getIdtienda());
		promotionList.addAll(auxPromotionList);
		
		System.out.println("Total de promociones Producto: " + promotionList.size());		
		
		if (promotionList != null && promotionList.size() > 0) {
			for (int i = 0; i < promotionList.size(); i++) {
				discountPromo = promotionList.get(i).getDescuento();
				price = price * (1.0f - discountPromo);
				discountHelper.addPromotion(new PromotionHelper(promotionList.get(i).getIdpromocion(), discountPromo));
				System.out.println("Descuento: " + discountPromo + " - Precio : " + price);
			}
		}
		
		
		
		/*
		//System.out.println("Precio con categoria descuento = 0 : " + priceBase);
		if (detailInventory.getPromocional()) {
			//System.out.println("buscando promocion por promocional producto");
			try {
				promotionList = getPromotionFacade().findPromocionByProductTiendaFecha(product.getIdproducto(), inventario.getTienda().getIdtienda());
				if (promotionList != null && promotionList.size() > 0) {
					
					//System.out.println("Promocion producto promocional true");
					//if (Utils.checkDate(promotion.getFechainicio(), promotion.getFechafin(), new Date())) {
					//	discountPromo = promotion.getDescuento();
					//	price = price * (1.0f - discountPromo);
					//	discountHelper.addPromotion(new PromotionHelper(promotion.getIdpromocion(), discountPromo));
					//}
					//
					for (int i = 0; i < promotionList.size(); i++) {
						discountPromo = promotionList.get(i).getDescuento();
						price = price * (1.0f - discountPromo);
						discountHelper.addPromotion(new PromotionHelper(promotionList.get(i).getIdpromocion(), discountPromo));
					}
				}
			} catch (Exception ex) {
				
			}
		} 
		List<Categoriaproducto> categoryList = product.getCategoriaproductos();		
		if (categoryList != null) {
			
			//for (int i = 0; i < categoryList.size(); i++) {
			//	try {
			//		promotionList = categoryList.get(i).getPromocions();
			//		if	(promotionList != null) {
			//			for (int j = 0; j < promotionList.size(); j++) {
			//				promotion = promotionList.get(j);
			//				if (promotion != null) {
			//					//System.out.println("Promocion : " + promotion.getNombre() + " - " + promotion.getDescuento());
			//					if (Utils.checkDate(promotion.getFechainicio(), promotion.getFechafin(), new Date())) {
			//						discountPromo = promotion.getDescuento();
			//						price = price * (1.0f - discountPromo);
			//						discountHelper.addPromotion(new PromotionHelper(promotion.getIdpromocion(), discountPromo));
			//						//System.out.println("Precio Promocion : " + priceBase);
			//					}
			//				}
			//			}
			//		}
			//	} catch (Exception ex) {
			//		
			//	}
			//}
			
			//aplicar la promocion con el descuento mas alto
			for (int i = 0; i < categoryList.size(); i++) {
				promotionList.addAll(categoryList.get(i).getPromocions());
			}
			if (promotionList.size() > 0) {
				int index = 0;
				Promocion tempPromo = promotionList.get(0);
				for (int j = 1; j < promotionList.size(); j++) {
					promotion = promotionList.get(j);
					if (Float.compare(tempPromo.getDescuento(), promotion.getDescuento()) < 0) {
						index = j;
					}
				}
				promotion = promotionList.get(index);
				if (promotion != null) {
					//System.out.println("Promocion : " + promotion.getNombre() + " - " + promotion.getDescuento());
					if (Utils.checkDate(promotion.getFechainicio(), promotion.getFechafin(), new Date())) {
						discountPromo = promotion.getDescuento();
						price = price * (1.0f - discountPromo);
						discountHelper.addPromotion(new PromotionHelper(promotion.getIdpromocion(), discountPromo));
						//System.out.println("Precio Promocion : " + priceBase);
					}
				}
			}			
		}
		*/
		discountHelper.setFinalPrice(price);		
		return discountHelper;
	}
	
	public float updateProductPrice(Cliente cliente, Producto product, float quantity) throws AlidaPosException {
		float priceBase = 0.0f;
		Precioventa salePrice;
		Detalleinventario detailInventory = getDetailStockFacade().findDetalleInventarioByProduct(product.getIdproducto());
		priceBase = detailInventory.getPrecioventa();
		if (Float.compare(cliente.getCategoriacliente().getDescuento(), 0.0f) == 0) {
			salePrice = getSalePriceFacade().findPrecioVentaByIdProductQuantity(product.getIdproducto(), quantity);
			if (salePrice != null) {
				if (salePrice.getModoprecio().equals(ApplicationBean.SALE_PRICE_MODE_PERCENTAGE)) {
					priceBase = priceBase * (1.0f - salePrice.getValor());
				} else {
					priceBase = priceBase - salePrice.getValor();
				}
			}
		} else {
			priceBase = priceBase * (1.0f - client.getCategoriacliente().getDescuento());
		}
		return priceBase;
	}
		
	public float getProductPrice(Producto product, float quantity, Detalleinventario detailInventory) throws AlidaPosException {		
		float priceBase = 0.0f;		
		Precioventa salePrice;
		priceBase = detailInventory.getPrecioventa();
		//System.out.println("\n");
		//System.out.println("Producto : " + product.getIdproducto() + " - " + product.getNombre());
		//System.out.println("Cantidad : " + quantity);
		//System.out.println("Precio base inventario : " + priceBase);
		//System.out.println("Descuento Categoria Cliente : " + client.getCategoriacliente().getDescuento());
		if (Float.compare(client.getCategoriacliente().getDescuento(), 0.0f) == 0) {
			salePrice = getSalePriceFacade().findPrecioVentaByIdProductQuantity(product.getIdproducto(), quantity);
			if (salePrice != null) {
				//System.out.println("Precio venta sin categoria..." + priceBase);
				if (salePrice.getModoprecio().equals(ApplicationBean.SALE_PRICE_MODE_PERCENTAGE)) {
					priceBase = priceBase * (1.0f - salePrice.getValor());
					//System.out.println("Precio venta descuento porcentaje : " + salePrice.getValor() + " -> " + priceBase);
				} else {
					priceBase = priceBase - salePrice.getValor();
					//System.out.println("Precio venta descuento valor : " + salePrice.getValor() + " -> " + priceBase);
				}
			}			
		} else {
			priceBase = priceBase * (1.0f - client.getCategoriacliente().getDescuento());
			//System.out.println("Precio usando Categoria Cliente : " + priceBase);
		}
		//System.out.println("Precio Unitario final : " + priceBase);	
		return priceBase;
	}
	
	public Object updateClientInformation(Cliente cliente, Object record) throws AlidaPosException {
		//float priceValue  = 0.0f;
		float amountValue = 0.0f;
		float taxValue    = 0.0f;
		float discount    = 0.0f;
		AmountTaxHelper amountTax = null;
		this.client = cliente;
		System.out.println("modificando informacion del cliente y precios de los productos");
		priceList.clear();
		if (record instanceof Apartado) {
			System.out.println("Record - Apartado");
			Apartado apartado = (Apartado) record;
			apartado.setCliente(cliente);
			List<Detalleapartado> list = apartado.getDetalleapartados(); 
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setPreciounitario( updateProductPrice( cliente, list.get(i).getProducto(), list.get(i).getCantidad() ) );
				amountValue = list.get(i).getCantidad() * list.get(i).getPreciounitario();
				taxValue  = list.get(i).getProducto().getIva() * amountValue;		
				amountTax = new AmountTaxHelper(amountValue, taxValue, discount);
//				discountList.put(product.getIdproducto(), discountHelper); // esto solo va en la venta
				priceList.put(list.get(i).getProducto().getIdproducto(), amountTax);			
				updatePayment();
			}
			return apartado;
		} else if (record instanceof Venta) {
			Venta venta = (Venta) record;
			venta.setCliente(cliente);
			List<Detalleventa> list = venta.getDetalleventas();
			for (int i = 0; i < list.size(); i++) {
				
			}			
			return venta;
		} else if (record instanceof Vista) {
			Vista vista = (Vista) record;
			vista.setCliente(cliente);
			List<Detallevista> list = vista.getDetallevistas();
			for (int i = 0; i < list.size(); i++) {
				
			}
			
			return vista;
		}
		return record;
	}

}
