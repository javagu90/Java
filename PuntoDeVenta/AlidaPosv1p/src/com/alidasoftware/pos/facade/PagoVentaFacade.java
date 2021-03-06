package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.PagoVentaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Pagoventa;

public class PagoVentaFacade implements Serializable {

	private static final long serialVersionUID = 1400795180117147904L;
	
	private PagoVentaDao pagoVentaDao = new PagoVentaDao();
	
	public void createPago(Pagoventa pago) throws AlidaPosException {
		try {
			pagoVentaDao.beginTransaction();
			pagoVentaDao.save(pago);
			pagoVentaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			pagoVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updatePago(Pagoventa pago) throws AlidaPosException {
		try {
			pagoVentaDao.beginTransaction();
			Pagoventa persistedObject = pagoVentaDao.find(pago.getIdpago());
			persistedObject.setCantidad(pago.getCantidad());
			persistedObject.setComentarios(pago.getComentarios());
			persistedObject.setFecha(pago.getFecha());
			persistedObject.setFormapago(pago.getFormapago());
			persistedObject.setReferenciapago(pago.getReferenciapago());
			persistedObject.setCambio(pago.getCambio());
			persistedObject.setVenta(pago.getVenta());
			pagoVentaDao.update(persistedObject);
			pagoVentaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			pagoVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Pagoventa findPago(int idPagoVenta) throws AlidaPosException {
    	try {
    		pagoVentaDao.beginTransaction();
    		Pagoventa pagoVenta = pagoVentaDao.find(idPagoVenta);
    		pagoVentaDao.closeTransaction();
    		return pagoVenta;
    	} catch (Exception ex) {
    		try {
    			pagoVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Pagoventa> listByIdVenta(int idVenta) throws AlidaPosException {
    	try {
    		pagoVentaDao.beginTransaction();
    		List<Pagoventa> result = pagoVentaDao.findByIdVenta(idVenta);
    		pagoVentaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			pagoVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Pagoventa> listAll() throws AlidaPosException {
    	try {
    		pagoVentaDao.beginTransaction();
    		List<Pagoventa> result = pagoVentaDao.findAll();
    		pagoVentaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			pagoVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deletePago(Pagoventa pago) throws AlidaPosException {
    	try {
	    	pagoVentaDao.beginTransaction();
	    	Pagoventa persistedObject = pagoVentaDao.findReferenceOnly(pago.getIdpago());
	        pagoVentaDao.delete(persistedObject);
	        pagoVentaDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				pagoVentaDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (pagoVentaDao != null) {
				pagoVentaDao.closeTransaction();
			}
		}
    }
    

}
