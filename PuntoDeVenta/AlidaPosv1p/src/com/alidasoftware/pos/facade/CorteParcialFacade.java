package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.CorteParcialDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Corteparcial;

public class CorteParcialFacade implements Serializable {

	private static final long serialVersionUID = -6222541168223370147L;
	
	private CorteParcialDao corteParcialDao = new CorteParcialDao();
	
	public void createCorteparcial(Corteparcial corteParcial) throws AlidaPosException {
		try {
			corteParcialDao.beginTransaction();
			corteParcialDao.save(corteParcial);
			corteParcialDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			corteParcialDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			corteParcialDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateCorteparcial(Corteparcial corteParcial) throws AlidaPosException {
		try {
			corteParcialDao.beginTransaction();
			Corteparcial persistedObject = corteParcialDao.find(corteParcial.getIdcorteparcial());
			persistedObject.setCantidadretirada(corteParcial.getCantidadretirada());
			persistedObject.setComentarios(corteParcial.getComentarios());
			persistedObject.setCortecaja(corteParcial.getCortecaja());
			persistedObject.setFecha(corteParcial.getFecha());
			persistedObject.setHora(corteParcial.getHora());
			persistedObject.setUsuario1(corteParcial.getUsuario1());
			persistedObject.setUsuario2(corteParcial.getUsuario2());
			corteParcialDao.update(persistedObject);
			corteParcialDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			corteParcialDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			corteParcialDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Corteparcial findCorteparcial(int idCorteparcial) throws AlidaPosException {
    	try {
    		corteParcialDao.beginTransaction();
    		Corteparcial corteParcial = corteParcialDao.find(idCorteparcial);
    		corteParcialDao.closeTransaction();
    		return corteParcial;
    	} catch (Exception ex) {
    		try {
    			corteParcialDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			corteParcialDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Corteparcial> listAll() throws AlidaPosException {
    	try {
    		corteParcialDao.beginTransaction();
    		List<Corteparcial> result = corteParcialDao.findAll();
    		corteParcialDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			corteParcialDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			corteParcialDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteCorteparcial(Corteparcial corteParcial) throws AlidaPosException {
    	try {
	    	corteParcialDao.beginTransaction();
	    	Corteparcial persistedObject = corteParcialDao.findReferenceOnly(corteParcial.getIdcorteparcial());
	        corteParcialDao.delete(persistedObject);
	        corteParcialDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
    		try {
    			corteParcialDao.rollback();
    		} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (corteParcialDao != null) {
				corteParcialDao.closeTransaction();
			}
		}
    }

}
