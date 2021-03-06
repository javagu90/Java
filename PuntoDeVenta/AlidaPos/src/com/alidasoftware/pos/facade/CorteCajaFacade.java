package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alidasoftware.pos.dao.CorteCajaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Cortecaja;

public class CorteCajaFacade implements Serializable {

	private static final long serialVersionUID = 799553136435732969L;
	
	private CorteCajaDao corteCajaDao = new CorteCajaDao();
	
	public void createCortecaja(Cortecaja corteCaja) throws AlidaPosException {
		try {
			corteCajaDao.beginTransaction();
			corteCajaDao.save(corteCaja);
			corteCajaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			corteCajaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			corteCajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateCortecaja(Cortecaja corteCaja) throws AlidaPosException {
		try {
			corteCajaDao.beginTransaction();
			Cortecaja persistedObject = corteCajaDao.find(corteCaja.getIdcortecaja());
			persistedObject.setCaja(corteCaja.getCaja());
			persistedObject.setCantidadsistema(corteCaja.getCantidadsistema());
			persistedObject.setCantidadfisica(corteCaja.getCantidadfisica());
			persistedObject.setComentarios(corteCaja.getComentarios());
			persistedObject.setCorteparcials(corteCaja.getCorteparcials());
			persistedObject.setDetallecortecajas(corteCaja.getDetallecortecajas());
			persistedObject.setFecha(corteCaja.getFecha());
			persistedObject.setHorafin(corteCaja.getHorafin());
			persistedObject.setHorainicio(corteCaja.getHorainicio());
			persistedObject.setUsuario1(corteCaja.getUsuario1());
			persistedObject.setUsuario2(corteCaja.getUsuario2());			
			
			persistedObject.setTotalcaja(corteCaja.getTotalcaja());
			persistedObject.setVentas(corteCaja.getVentas());
			persistedObject.setPagos(corteCaja.getPagos());
			
			corteCajaDao.update(persistedObject);
			corteCajaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			corteCajaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			corteCajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
  
    public List<Cortecaja> listAll() throws AlidaPosException {
    	try {
    		corteCajaDao.beginTransaction();
    		List<Cortecaja> result = corteCajaDao.findAll();
    		corteCajaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			corteCajaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			corteCajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteCortecaja(Cortecaja corteCaja) throws AlidaPosException {
    	try {
	    	corteCajaDao.beginTransaction();
	    	Cortecaja persistedObject = corteCajaDao.findReferenceOnly(corteCaja.getIdcortecaja());
	        corteCajaDao.delete(persistedObject);
	        corteCajaDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
    		try {
    			corteCajaDao.rollback();
    		} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (corteCajaDao != null) {
				corteCajaDao.closeTransaction();
			}
		}
    }
    
    public List<Cortecaja> findByFecha(Date searchFecha) throws AlidaPosException {
    	try {
    		corteCajaDao.beginTransaction();
    		List<Cortecaja> result = corteCajaDao.findByFecha(searchFecha);
    		corteCajaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			corteCajaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			corteCajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
}
