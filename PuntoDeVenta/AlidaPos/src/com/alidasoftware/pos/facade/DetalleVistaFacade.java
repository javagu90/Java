package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.DetalleVistaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detallevista;

public class DetalleVistaFacade implements Serializable {

	private static final long serialVersionUID = -7612909216148133376L;
	
	private DetalleVistaDao detalleVistaDao = new DetalleVistaDao();
	
	public void createDetallevista(Detallevista detalleVista) throws AlidaPosException {
		try {
			detalleVistaDao.beginTransaction();
			detalleVistaDao.save(detalleVista);
			detalleVistaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleVistaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleVistaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateDetallevista(Detallevista detalleVista) throws AlidaPosException {
		try {
			detalleVistaDao.beginTransaction();
			Detallevista persistedObject = detalleVistaDao.find(detalleVista.getIddetallevista());
			persistedObject.setCantidad(detalleVista.getCantidad());
			persistedObject.setPreciounitario(detalleVista.getPreciounitario());
			persistedObject.setProducto(detalleVista.getProducto());
			persistedObject.setVista(detalleVista.getVista());			
			detalleVistaDao.update(persistedObject);
			detalleVistaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleVistaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleVistaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Detallevista findDetallevista(int idDetallevista) throws AlidaPosException {
    	try {
    		detalleVistaDao.beginTransaction();
    		Detallevista detalleVista = detalleVistaDao.find(idDetallevista);
    		detalleVistaDao.closeTransaction();
    		return detalleVista;
    	} catch (Exception ex) {
    		try {
    			detalleVistaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleVistaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Detallevista> listAll() throws AlidaPosException {
    	try {
    		detalleVistaDao.beginTransaction();
    		List<Detallevista> result = detalleVistaDao.findAll();
    		detalleVistaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			detalleVistaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleVistaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteDetallevista(Detallevista detalleVista) throws AlidaPosException {
    	try {
	    	detalleVistaDao.beginTransaction();
	    	Detallevista persistedObject = detalleVistaDao.findReferenceOnly(detalleVista.getIddetallevista());
	        detalleVistaDao.delete(persistedObject);
	        detalleVistaDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				detalleVistaDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (detalleVistaDao != null) {
				detalleVistaDao.closeTransaction();
			}
		}
    }

}
