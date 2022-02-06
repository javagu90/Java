package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.DetalleCorteCajaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detallecortecaja;

public class DetalleCorteCajaFacade implements Serializable {

	private static final long serialVersionUID = -3345254085599130046L;
	
	private DetalleCorteCajaDao detalleCorteCajaDao = new DetalleCorteCajaDao();
	
	public void createDetallecortecaja(Detallecortecaja detalleCorteCaja) throws AlidaPosException {
		try {
			detalleCorteCajaDao.beginTransaction();
			detalleCorteCajaDao.save(detalleCorteCaja);
			detalleCorteCajaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleCorteCajaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleCorteCajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateDetallecortecaja(Detallecortecaja detalleCorteCaja) throws AlidaPosException {
		try {
			detalleCorteCajaDao.beginTransaction();
			Detallecortecaja persistedObject = detalleCorteCajaDao.find(detalleCorteCaja.getIddetallecortecaja());
			persistedObject.setCantidad(detalleCorteCaja.getCantidad());
			persistedObject.setCortecaja(detalleCorteCaja.getCortecaja());
			persistedObject.setFormapago(detalleCorteCaja.getFormapago());

			detalleCorteCajaDao.update(persistedObject);
			detalleCorteCajaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleCorteCajaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleCorteCajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Detallecortecaja findDetallecortecaja(int idDetallecortecaja) throws AlidaPosException {
    	try {
    		detalleCorteCajaDao.beginTransaction();
    		Detallecortecaja detalleCorteCaja = detalleCorteCajaDao.find(idDetallecortecaja);
    		detalleCorteCajaDao.closeTransaction();
    		return detalleCorteCaja;
    	} catch (Exception ex) {
    		try {
    			detalleCorteCajaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleCorteCajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Detallecortecaja> listAll() throws AlidaPosException {
    	try {
    		detalleCorteCajaDao.beginTransaction();
    		List<Detallecortecaja> result = detalleCorteCajaDao.findAll();
    		detalleCorteCajaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			detalleCorteCajaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleCorteCajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteDetallecortecaja(Detallecortecaja detalleCorteCaja) throws AlidaPosException {
    	try {
	    	detalleCorteCajaDao.beginTransaction();
	    	Detallecortecaja persistedObject = detalleCorteCajaDao.findReferenceOnly(detalleCorteCaja.getIddetallecortecaja());
	        detalleCorteCajaDao.delete(persistedObject);
	        detalleCorteCajaDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
    		try {
    			detalleCorteCajaDao.rollback();
    		} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (detalleCorteCajaDao != null) {
				detalleCorteCajaDao.closeTransaction();
			}
		}
    }
    
}
