package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.DetalleTraspasoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detalletraspaso;

public class DetalleTraspasoFacade implements Serializable {

	private static final long serialVersionUID = 2731491543984436948L;
	
	private DetalleTraspasoDao detalleTraspasoDao = new DetalleTraspasoDao();
	
	public void createDetalletraspaso(Detalletraspaso detalleTraspaso) throws AlidaPosException {
		try {
			detalleTraspasoDao.beginTransaction();
			detalleTraspasoDao.save(detalleTraspaso);
			detalleTraspasoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleTraspasoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleTraspasoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateDetalletraspaso(Detalletraspaso detalleTraspaso) throws AlidaPosException {
		try {
			detalleTraspasoDao.beginTransaction();
			Detalletraspaso persistedObject = detalleTraspasoDao.find(detalleTraspaso.getIddetalletraspaso());
			persistedObject.setCantidad(detalleTraspaso.getCantidad());
			persistedObject.setPreciounitario(detalleTraspaso.getPreciounitario());
			persistedObject.setProducto(detalleTraspaso.getProducto());
			persistedObject.setTraspaso(detalleTraspaso.getTraspaso());			
			detalleTraspasoDao.update(persistedObject);
			detalleTraspasoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleTraspasoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleTraspasoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Detalletraspaso findDetalletraspaso(int idDetalletraspaso) throws AlidaPosException {
    	try {
    		detalleTraspasoDao.beginTransaction();
    		Detalletraspaso detalleTraspaso = detalleTraspasoDao.find(idDetalletraspaso);
    		detalleTraspasoDao.closeTransaction();
    		return detalleTraspaso;
    	} catch (Exception ex) {
    		try {
    			detalleTraspasoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleTraspasoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Detalletraspaso> listAll() throws AlidaPosException {
    	try {
    		detalleTraspasoDao.beginTransaction();
    		List<Detalletraspaso> result = detalleTraspasoDao.findAll();
    		detalleTraspasoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			detalleTraspasoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleTraspasoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteDetalletraspaso(Detalletraspaso detalleTraspaso) throws AlidaPosException {
    	try {
	    	detalleTraspasoDao.beginTransaction();
	    	Detalletraspaso persistedObject = detalleTraspasoDao.findReferenceOnly(detalleTraspaso.getIddetalletraspaso());
	        detalleTraspasoDao.delete(persistedObject);
	        detalleTraspasoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				detalleTraspasoDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (detalleTraspasoDao != null) {
				detalleTraspasoDao.closeTransaction();
			}
		}
    }

}
