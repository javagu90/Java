package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.DetalleVentaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detalleventa;

public class DetalleVentaFacade implements Serializable {

	private static final long serialVersionUID = -4910683039796759804L;
	
	private DetalleVentaDao detalleVentaDao = new DetalleVentaDao();
	
	public void createDetalleventa(Detalleventa detalleVenta) throws AlidaPosException {
		try {
			detalleVentaDao.beginTransaction();
			detalleVentaDao.save(detalleVenta);
			detalleVentaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateDetalleventa(Detalleventa detalleVenta) throws AlidaPosException {
		try {
			detalleVentaDao.beginTransaction();
			Detalleventa persistedObject = detalleVentaDao.find(detalleVenta.getIddetalleventa());
			persistedObject.setCantidad(detalleVenta.getCantidad());
			persistedObject.setPreciounitario(detalleVenta.getPreciounitario());
			persistedObject.setProducto(detalleVenta.getProducto());
			persistedObject.setVenta(detalleVenta.getVenta());			
			detalleVentaDao.update(persistedObject);
			detalleVentaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Detalleventa findDetalleventa(int idDetalleventa) throws AlidaPosException {
    	try {
    		detalleVentaDao.beginTransaction();
    		Detalleventa detalleVenta = detalleVentaDao.find(idDetalleventa);
    		detalleVentaDao.closeTransaction();
    		return detalleVenta;
    	} catch (Exception ex) {
    		try {
    			detalleVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Detalleventa> listAll() throws AlidaPosException {
    	try {
    		detalleVentaDao.beginTransaction();
    		List<Detalleventa> result = detalleVentaDao.findAll();
    		detalleVentaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			detalleVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteDetalleventa(Detalleventa detalleVenta) throws AlidaPosException {
    	try {
	    	detalleVentaDao.beginTransaction();
	    	Detalleventa persistedObject = detalleVentaDao.findReferenceOnly(detalleVenta.getIddetalleventa());
	        detalleVentaDao.delete(persistedObject);
	        detalleVentaDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				detalleVentaDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (detalleVentaDao != null) {
				detalleVentaDao.closeTransaction();
			}
		}
    }

}
