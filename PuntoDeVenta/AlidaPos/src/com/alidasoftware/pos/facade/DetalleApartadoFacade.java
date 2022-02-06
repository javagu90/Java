package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.DetalleApartadoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detalleapartado;

public class DetalleApartadoFacade implements Serializable {

	private static final long serialVersionUID = 8410442055944429909L;
	
	private DetalleApartadoDao detalleApartadoDao = new DetalleApartadoDao();
	
	public void createDetalleapartado(Detalleapartado detalleApartado) throws AlidaPosException {
		try {
			detalleApartadoDao.beginTransaction();
			detalleApartadoDao.save(detalleApartado);
			detalleApartadoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateDetalleapartado(Detalleapartado detalleApartado) throws AlidaPosException {
		try {
			detalleApartadoDao.beginTransaction();
			Detalleapartado persistedObject = detalleApartadoDao.find(detalleApartado.getIddetalleapartado());
			persistedObject.setApartado(detalleApartado.getApartado());
			persistedObject.setCantidad(detalleApartado.getCantidad());
			persistedObject.setPreciounitario(detalleApartado.getPreciounitario());
			persistedObject.setProducto(detalleApartado.getProducto());			
			detalleApartadoDao.update(persistedObject);
			detalleApartadoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Detalleapartado findDetalleapartado(int idDetalleapartado) throws AlidaPosException {
    	try {
    		detalleApartadoDao.beginTransaction();
    		Detalleapartado detalleApartado = detalleApartadoDao.find(idDetalleapartado);
    		detalleApartadoDao.closeTransaction();
    		return detalleApartado;
    	} catch (Exception ex) {
    		try {
    			detalleApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Detalleapartado> listAll() throws AlidaPosException {
    	try {
    		detalleApartadoDao.beginTransaction();
    		List<Detalleapartado> result = detalleApartadoDao.findAll();
    		detalleApartadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			detalleApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteDetalleapartado(Detalleapartado detalleApartado) throws AlidaPosException {
    	try {
	    	detalleApartadoDao.beginTransaction();
	    	Detalleapartado persistedObject = detalleApartadoDao.findReferenceOnly(detalleApartado.getIddetalleapartado());
	        detalleApartadoDao.delete(persistedObject);
	        detalleApartadoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
    		try {
    			detalleApartadoDao.rollback();
    		} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (detalleApartadoDao != null) {
				detalleApartadoDao.closeTransaction();
			}
		}
    }
    

}
