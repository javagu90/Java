package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.VistaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Vista;

public class VistaFacade implements Serializable {

	private static final long serialVersionUID = 1400795180117147904L;
	
	private VistaDao vistaDao = new VistaDao();
	
	public void createVista(Vista vista) throws AlidaPosException {
		try {
			vistaDao.beginTransaction();
			vistaDao.save(vista);
			vistaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			vistaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			vistaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateVista(Vista vista) throws AlidaPosException {
		try {
			vistaDao.beginTransaction();
			Vista persistedObject = vistaDao.find(vista.getIdvista());
			persistedObject.setCliente(vista.getCliente());
			persistedObject.setComentarios(vista.getComentarios());
			persistedObject.setDetallevistas(vista.getDetallevistas());
			persistedObject.setFecha(vista.getFecha());
			persistedObject.setFolio(vista.getFolio());
			persistedObject.setStatus(vista.getStatus());
			persistedObject.setTipovencimiento(vista.getTipovencimiento());			
			vistaDao.update(persistedObject);
			vistaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			vistaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			vistaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Vista findVista(int idVista) throws AlidaPosException {
    	try {
    		vistaDao.beginTransaction();
    		Vista vista = vistaDao.find(idVista);
    		vistaDao.closeTransaction();
    		return vista;
    	} catch (Exception ex) {
    		try {
    			vistaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			vistaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Vista> listByStatus(int status) throws AlidaPosException {
    	try {
    		vistaDao.beginTransaction();
    		List<Vista> result = vistaDao.findByStatus(status);
    		vistaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			vistaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			vistaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Vista> listAll() throws AlidaPosException {
    	try {
    		vistaDao.beginTransaction();
    		List<Vista> result = vistaDao.findAll();
    		vistaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			vistaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			vistaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteVista(Vista vista) throws AlidaPosException {
    	try {
	    	vistaDao.beginTransaction();
	    	Vista persistedObject = vistaDao.findReferenceOnly(vista.getIdvista());
	        vistaDao.delete(persistedObject);
	        vistaDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				vistaDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (vistaDao != null) {
				vistaDao.closeTransaction();
			}
		}
    }
    
}
