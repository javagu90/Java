package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.PagoApartadoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Pagoapartado;

public class PagoApartadoFacade implements Serializable {

	private static final long serialVersionUID = 1400795180117147904L;
	
	private PagoApartadoDao pagoApartadoDao = new PagoApartadoDao();
	
	public void createPago(Pagoapartado pago) throws AlidaPosException {
		try {
			pagoApartadoDao.beginTransaction();
			pagoApartadoDao.save(pago);
			pagoApartadoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			pagoApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updatePago(Pagoapartado pago) throws AlidaPosException {
		try {
			pagoApartadoDao.beginTransaction();
			Pagoapartado persistedObject = pagoApartadoDao.find(pago.getIdpago());
			persistedObject.setCantidad(pago.getCantidad());
			persistedObject.setComentarios(pago.getComentarios());
			persistedObject.setFecha(pago.getFecha());
			persistedObject.setFormapago(pago.getFormapago());
			persistedObject.setReferenciapago(pago.getReferenciapago());
			persistedObject.setCambio(pago.getCambio());
			persistedObject.setApartado(pago.getApartado());
			pagoApartadoDao.update(persistedObject);
			pagoApartadoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			pagoApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Pagoapartado findPago(int idPagoApartado) throws AlidaPosException {
    	try {
    		pagoApartadoDao.beginTransaction();
    		Pagoapartado pagoApartado = pagoApartadoDao.find(idPagoApartado);
    		pagoApartadoDao.closeTransaction();
    		return pagoApartado;
    	} catch (Exception ex) {
    		try {
    			pagoApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Pagoapartado> listByIdApartado(int idApartado) throws AlidaPosException {
    	try {
    		pagoApartadoDao.beginTransaction();
    		List<Pagoapartado> result = pagoApartadoDao.findByIdApartado(idApartado);
    		pagoApartadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			pagoApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Pagoapartado> listAll() throws AlidaPosException {
    	try {
    		pagoApartadoDao.beginTransaction();
    		List<Pagoapartado> result = pagoApartadoDao.findAll();
    		pagoApartadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			pagoApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deletePago(Pagoapartado pago) throws AlidaPosException {
    	try {
	    	pagoApartadoDao.beginTransaction();
	    	Pagoapartado persistedObject = pagoApartadoDao.findReferenceOnly(pago.getIdpago());
	        pagoApartadoDao.delete(persistedObject);
	        pagoApartadoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				pagoApartadoDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (pagoApartadoDao != null) {
				pagoApartadoDao.closeTransaction();
			}
		}
    }
    

}
