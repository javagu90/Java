package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.DetalleDevolucionDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detalledevolucion;

public class DetalleDevolucionFacade implements Serializable {

	private static final long serialVersionUID = -676617504367819906L;
	
	private DetalleDevolucionDao detalleDevolucionDao = new DetalleDevolucionDao();
	
	public void createDetalledevolucion(Detalledevolucion detalleDevolucion) throws AlidaPosException {
		try {
			detalleDevolucionDao.beginTransaction();
			detalleDevolucionDao.save(detalleDevolucion);
			detalleDevolucionDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleDevolucionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleDevolucionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateDetalledevolucion(Detalledevolucion detalleDevolucion) throws AlidaPosException {
		try {
			detalleDevolucionDao.beginTransaction();
			Detalledevolucion persistedObject = detalleDevolucionDao.find(detalleDevolucion.getIddetalledevolucion());
			persistedObject.setCantidad(detalleDevolucion.getCantidad());
			persistedObject.setDevolucion(detalleDevolucion.getDevolucion());
			persistedObject.setPreciocompra(detalleDevolucion.getPreciocompra());
			persistedObject.setProducto(detalleDevolucion.getProducto());			
			detalleDevolucionDao.update(persistedObject);
			detalleDevolucionDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleDevolucionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleDevolucionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Detalledevolucion findDetalledevolucion(int idDetalledevolucion) throws AlidaPosException {
    	try {
    		detalleDevolucionDao.beginTransaction();
    		Detalledevolucion detalleDevolucion = detalleDevolucionDao.find(idDetalledevolucion);
    		detalleDevolucionDao.closeTransaction();
    		return detalleDevolucion;
    	} catch (Exception ex) {
    		try {
    			detalleDevolucionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleDevolucionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Detalledevolucion> listAll() throws AlidaPosException {
    	try {
    		detalleDevolucionDao.beginTransaction();
    		List<Detalledevolucion> result = detalleDevolucionDao.findAll();
    		detalleDevolucionDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			detalleDevolucionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleDevolucionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteDetalledevolucion(Detalledevolucion detalleDevolucion) throws AlidaPosException {
    	try {
	    	detalleDevolucionDao.beginTransaction();
	    	Detalledevolucion persistedObject = detalleDevolucionDao.findReferenceOnly(detalleDevolucion.getIddetalledevolucion());
	        detalleDevolucionDao.delete(persistedObject);
	        detalleDevolucionDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				detalleDevolucionDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (detalleDevolucionDao != null) {
				detalleDevolucionDao.closeTransaction();
			}
		}
    }
    

}
