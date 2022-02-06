package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alidasoftware.pos.dao.TraspasoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detalleinventario;
import com.alidasoftware.pos.model.Detalletraspaso;
import com.alidasoftware.pos.model.Inventario;
import com.alidasoftware.pos.model.Traspaso;

public class TraspasoFacade  implements Serializable {

	private static final long serialVersionUID = 2636103180502766496L;
	
	private TraspasoDao traspasoDao = new TraspasoDao();
	private InventarioFacade invFacade = new InventarioFacade();
	private DetalleInventarioFacade dif = new DetalleInventarioFacade();
	
    
    public String insertTransactionTraspaso(Traspaso traspaso) throws AlidaPosException{
    	try {
	    	traspasoDao.beginTransaction();
	    	Inventario inventarioOrigen = invFacade.findInventarioByTienda(traspaso.getTienda1());
	    	Inventario inventarioDestino = invFacade.findInventarioByTienda(traspaso.getTienda2());
	    	float nuevaCantidadOrigen;
	    	float nuevaCantidadDestino;
	    	// Se revisa cada uno de los productos que se van a traspasar.
	    	for(Detalletraspaso aux : traspaso.getDetalletraspasos()){
	    		// Se obtienen los detalles inventario de las tiendas Origen y Destino
	    		Detalleinventario detalleInvAux = dif.findDetalleInventarioByProductTienda(aux.getProducto().getIdproducto(), inventarioOrigen.getIdinventario());
	    		Detalleinventario detalleInvDes = dif.findDetalleInventarioByProductTienda(aux.getProducto().getIdproducto(), inventarioDestino.getIdinventario());
	    		// Se obtiene la nueva cantidad de la tienda origen de ese producto.
	    		nuevaCantidadOrigen = detalleInvAux.getExistenciaventa()-aux.getCantidad();
	    		if(nuevaCantidadOrigen < 0){
	    			// si al momento de hacer el traspaso se van a traspasar mas productos de los que hay en existencia se hace un roll back por que no se puede.
	    			traspasoDao.rollback();
	    			return "No se cuenta con suficientes productos en la tienda de Origen.";
	    		}
	    		
	    		// Preguntamos si el detalle inventario de la tienda destino existe (el registro)
	    		if(detalleInvDes != null){
	    			System.out.println("si existia el registro en la tienda destino del producto");
	    			// En caso de que exista vamos a obtener la nueva cantidad de productos 
	    			nuevaCantidadDestino = detalleInvDes.getExistenciaventa() + aux.getCantidad();
	    			if(nuevaCantidadDestino <= detalleInvDes.getCantidadmax()){
	    				// Si la nueva cantidad de productos que va para el inventario de la tienda destino es menor que la cantidad maxima que se puede tener en stock
	    				// Asignamos la nueva cantidad a la existencia para venta y actualizamos el detalle inventario de la tienda destino
	    				detalleInvDes.setExistenciaventa(nuevaCantidadDestino);
	    				dif.updateDetalleinventario(detalleInvDes);
	    				detalleInvAux.setExistenciaventa(nuevaCantidadOrigen);
		    			dif.updateDetalleinventario(detalleInvAux);
	    			} else {
	    				// En caso de que sobrepase la cantidad maxima que se puede tener en stock se hace roll back por que no se puede hacer el traspaso.
	    				traspasoDao.rollback();
	    				return "Sobrepasa la cantidad máxima del producto en la tienda Destino.";
	    			}
	    		} else {
	    		// En caso de que no se encuentre registro en el detalle inventario de la tienda destino, creamos un nuevo objeto.
	    			System.out.println("no existia el registro en la tienda destino ");
	    			Detalleinventario nuevoRegistro = new Detalleinventario();
	    			nuevoRegistro.setCaducidad(detalleInvAux.getCaducidad());
	    			nuevoRegistro.setCantidadmax(aux.getCantidad()+1);
	    			nuevoRegistro.setCantidadmin(aux.getCantidad()-1);
	    			nuevoRegistro.setExistenciaapvi(0f);
	    			nuevoRegistro.setExistenciaventa(aux.getCantidad());
	    			nuevoRegistro.setInventario(inventarioDestino);
	    			nuevoRegistro.setLote(detalleInvAux.getLote());
	    			nuevoRegistro.setPreciocompra(detalleInvAux.getPreciocompra());
	    			nuevoRegistro.setPrecioventa(detalleInvAux.getPrecioventa());
	    			nuevoRegistro.setProducto(detalleInvAux.getProducto());
	    			nuevoRegistro.setUltimaoperacion(new Date());
	    			dif.createDetalleinventario(nuevoRegistro);
	    			System.out.println("despues de crear el detalle inventario");
	    			detalleInvAux.setExistenciaventa(nuevaCantidadOrigen);
	    			dif.updateDetalleinventario(detalleInvAux);
	    		}
	    	}
	    	traspasoDao.save(traspaso);
	        traspasoDao.commitAndCloseTransaction();
	        return "Se agrego el Traspaso correctamente.";
    	} catch (Exception ex) {
    		System.out.println("Error insert " + ex.getMessage());
    		ex.printStackTrace();
			try {
				traspasoDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (traspasoDao != null) {
				traspasoDao.closeTransaction();
			}
		}
    }
	
	
	public void createTraspaso(Traspaso traspaso) throws AlidaPosException {
		try {
			traspasoDao.beginTransaction();
			traspasoDao.save(traspaso);
			traspasoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			traspasoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			traspasoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateTraspaso(Traspaso traspaso) throws AlidaPosException {
		try {
			traspasoDao.beginTransaction();
			Traspaso persistedObject = traspasoDao.find(traspaso.getIdtraspaso());
			persistedObject.setComentarios(traspaso.getComentarios());
			persistedObject.setDetalletraspasos(traspaso.getDetalletraspasos());
			persistedObject.setTienda1(traspaso.getTienda1());
			persistedObject.setTienda2(traspaso.getTienda2());
			persistedObject.setActivo(traspaso.isActivo());
			traspasoDao.update(persistedObject);
			traspasoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			traspasoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			traspasoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Traspaso findTraspaso(int idTraspaso) throws AlidaPosException {
    	try {
    		traspasoDao.beginTransaction();
    		Traspaso traspaso = traspasoDao.find(idTraspaso);
    		traspasoDao.closeTransaction();
    		return traspaso;
    	} catch (Exception ex) {
    		try {
    			traspasoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			traspasoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Traspaso> listAll() throws AlidaPosException {
    	try {
    		traspasoDao.beginTransaction();
    		List<Traspaso> result = traspasoDao.findAll();
    		traspasoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			traspasoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			traspasoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteTraspaso(Traspaso traspaso) throws AlidaPosException {
    	try {
	    	traspasoDao.beginTransaction();
	    	Traspaso persistedObject = traspasoDao.findReferenceOnly(traspaso.getIdtraspaso());
	        traspasoDao.delete(persistedObject);
	        traspasoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				traspasoDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (traspasoDao != null) {
				traspasoDao.closeTransaction();
			}
		}
    }

}
