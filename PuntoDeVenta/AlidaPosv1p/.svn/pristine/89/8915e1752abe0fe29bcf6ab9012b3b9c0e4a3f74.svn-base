package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.FacturaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Factura;

public class FacturaFacade implements Serializable {

	private static final long serialVersionUID = 337921254252104295L;
	
	private FacturaDao facturaDao = new FacturaDao();
	
	public void createFactura(Factura factura) throws AlidaPosException {
		try {
			facturaDao.beginTransaction();
			facturaDao.save(factura);
			facturaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			facturaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			facturaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateFactura(Factura factura) throws AlidaPosException {
		try {
			facturaDao.beginTransaction();
			Factura persistedObject = facturaDao.find(factura.getIdfactura());
			persistedObject.setAddenda(factura.getAddenda());
			persistedObject.setCadenaoriginal(factura.getCadenaoriginal());
			persistedObject.setComentarios(factura.getComentarios());
			persistedObject.setFecha(factura.getFecha());
			persistedObject.setSello(factura.getSello());
			persistedObject.setStatus(factura.getStatus());
			persistedObject.setVenta(factura.getVenta());
			persistedObject.setXml(factura.getXml());
			facturaDao.update(persistedObject);
			facturaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			facturaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			facturaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Factura findFactura(int idFactura) throws AlidaPosException {
    	try {
    		facturaDao.beginTransaction();
    		Factura factura = facturaDao.find(idFactura);
    		facturaDao.closeTransaction();
    		return factura;
    	} catch (Exception ex) {
    		try {
    			facturaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			facturaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Factura> listAll() throws AlidaPosException {
    	try {
    		facturaDao.beginTransaction();
    		List<Factura> result = facturaDao.findAll();
    		facturaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			facturaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			facturaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteFactura(Factura factura) throws AlidaPosException {
    	try {
	    	facturaDao.beginTransaction();
	    	Factura persistedObject = facturaDao.findReferenceOnly(factura.getIdfactura());
	        facturaDao.delete(persistedObject);
	        facturaDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				facturaDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (facturaDao != null) {
				facturaDao.closeTransaction();
			}
		}
    }

}
