package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.InventarioFisicoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Inventariofisico;

public class InventarioFisicoFacade implements Serializable {

	private static final long serialVersionUID = 6530646115906086970L;
	
	private InventarioFisicoDao inventarioFisicoDao = new InventarioFisicoDao();
	
	public void createInventariofisico(Inventariofisico inventarioFisico) throws AlidaPosException {
		try {
			inventarioFisicoDao.beginTransaction();
			inventarioFisicoDao.save(inventarioFisico);
			inventarioFisicoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			inventarioFisicoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			inventarioFisicoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateInventariofisico(Inventariofisico inventarioFisico) throws AlidaPosException {
		try {
			inventarioFisicoDao.beginTransaction();
			Inventariofisico persistedObject = inventarioFisicoDao.find(inventarioFisico.getIdinventariofisico());
			persistedObject.setComentarios(inventarioFisico.getComentarios());
			persistedObject.setCostototal(inventarioFisico.getCostototal());
			persistedObject.setDetalleinventariofisicos(inventarioFisico.getDetalleinventariofisicos());
			persistedObject.setFechaInicio(inventarioFisico.getFechaInicio());
			persistedObject.setFechaFin(inventarioFisico.getFechaFin());
			persistedObject.setHorafin(inventarioFisico.getHorafin());
			persistedObject.setHorainicio(inventarioFisico.getHorainicio());
			persistedObject.setUsuario(inventarioFisico.getUsuario());			
			inventarioFisicoDao.update(persistedObject);
			inventarioFisicoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			inventarioFisicoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			inventarioFisicoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Inventariofisico findInventariofisico(int idInventariofisico) throws AlidaPosException {
    	try {
    		inventarioFisicoDao.beginTransaction();
    		Inventariofisico inventarioFisico = inventarioFisicoDao.find(idInventariofisico);
    		inventarioFisicoDao.closeTransaction();
    		return inventarioFisico;
    	} catch (Exception ex) {
    		try {
    			inventarioFisicoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			inventarioFisicoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Inventariofisico> listAll() throws AlidaPosException {
    	try {
    		inventarioFisicoDao.beginTransaction();
    		List<Inventariofisico> result = inventarioFisicoDao.findAll();
    		inventarioFisicoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			inventarioFisicoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			inventarioFisicoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteInventariofisico(Inventariofisico inventarioFisico) throws AlidaPosException {
    	try {
	    	inventarioFisicoDao.beginTransaction();
	    	Inventariofisico persistedObject = inventarioFisicoDao.findReferenceOnly(inventarioFisico.getIdinventariofisico());
	        inventarioFisicoDao.delete(persistedObject);
	        inventarioFisicoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				inventarioFisicoDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (inventarioFisicoDao != null) {
				inventarioFisicoDao.closeTransaction();
			}
		}
    }

}
