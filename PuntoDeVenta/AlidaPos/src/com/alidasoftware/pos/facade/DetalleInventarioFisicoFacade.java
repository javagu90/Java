package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.DetalleInventarioFisicoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detalleinventariofisico;

public class DetalleInventarioFisicoFacade implements Serializable {

	private static final long serialVersionUID = -718504801428774769L;
	
	private DetalleInventarioFisicoDao detalleInventarioFisicoDao = new DetalleInventarioFisicoDao();
	
	public void createDetalleinventariofisico(Detalleinventariofisico detalleInvFisico) throws AlidaPosException {
		try {
			detalleInventarioFisicoDao.beginTransaction();
			detalleInventarioFisicoDao.save(detalleInvFisico);
			detalleInventarioFisicoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleInventarioFisicoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleInventarioFisicoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateDetalleinventariofisico(Detalleinventariofisico detalleInventarioFisico) throws AlidaPosException {
		try {
			detalleInventarioFisicoDao.beginTransaction();
			Detalleinventariofisico persistedObject = detalleInventarioFisicoDao.find(detalleInventarioFisico.getIddetalleinventariofisico());
			persistedObject.setExistenciafisica(detalleInventarioFisico.getExistenciafisica());
			persistedObject.setExistenciasistema(detalleInventarioFisico.getExistenciasistema());
			persistedObject.setInventariofisico(detalleInventarioFisico.getInventariofisico());
			persistedObject.setProducto(detalleInventarioFisico.getProducto());			
			detalleInventarioFisicoDao.update(persistedObject);
			detalleInventarioFisicoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleInventarioFisicoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleInventarioFisicoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Detalleinventariofisico findDetalleinventariofisico(int idDetalleinventariofisico) throws AlidaPosException {
    	try {
    		detalleInventarioFisicoDao.beginTransaction();
    		Detalleinventariofisico detalleInvFisico = detalleInventarioFisicoDao.find(idDetalleinventariofisico);
    		detalleInventarioFisicoDao.closeTransaction();
    		return detalleInvFisico;
    	} catch (Exception ex) {
    		try {
    			detalleInventarioFisicoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleInventarioFisicoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Detalleinventariofisico> listAll() throws AlidaPosException {
    	try {
    		detalleInventarioFisicoDao.beginTransaction();
    		List<Detalleinventariofisico> result = detalleInventarioFisicoDao.findAll();
    		detalleInventarioFisicoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			detalleInventarioFisicoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleInventarioFisicoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteDetalleinventariofisico(Detalleinventariofisico detalleInventarioFisico) throws AlidaPosException {
    	try {
	    	detalleInventarioFisicoDao.beginTransaction();
	    	Detalleinventariofisico persistedObject = detalleInventarioFisicoDao.findReferenceOnly(detalleInventarioFisico.getIddetalleinventariofisico());
	        detalleInventarioFisicoDao.delete(persistedObject);
	        detalleInventarioFisicoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				detalleInventarioFisicoDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (detalleInventarioFisicoDao != null) {
				detalleInventarioFisicoDao.closeTransaction();
			}
		}
    }

}
