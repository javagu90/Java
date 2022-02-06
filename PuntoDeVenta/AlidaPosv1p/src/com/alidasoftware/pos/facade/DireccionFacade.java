package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.DireccionDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Direccion;

public class DireccionFacade implements Serializable {

	private static final long serialVersionUID = -258406369085047912L;
	
	private DireccionDao direccionDao = new DireccionDao();
	
	public void createDireccion(Direccion direccion) throws AlidaPosException {
		try {
			direccionDao.beginTransaction();
			direccionDao.save(direccion);
			direccionDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			direccionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			direccionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateDireccion(Direccion direccion) throws AlidaPosException {
		try {
			direccionDao.beginTransaction();
			Direccion persistedObject = direccionDao.find(direccion.getIddireccion());
			persistedObject.setCalle(direccion.getCalle());
			persistedObject.setCodigopostal(direccion.getCodigopostal());
			persistedObject.setColonia(direccion.getColonia());
			persistedObject.setContactos(direccion.getContactos());
			persistedObject.setMunicipio(direccion.getMunicipio());
			persistedObject.setNumext(direccion.getNumext());
			persistedObject.setNumint(direccion.getNumint());
			direccionDao.update(persistedObject);
			direccionDao.commitAndCloseTransaction();
		} catch (Exception ex) {
			System.out.println("Error al hacer update a la direccion " + ex.getMessage());
    		try {
    			direccionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			direccionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Direccion findDireccion(int idDireccion) throws AlidaPosException {
    	try {
    		direccionDao.beginTransaction();
    		Direccion direccion = direccionDao.find(idDireccion);
    		direccionDao.closeTransaction();
    		return direccion;
    	} catch (Exception ex) {
    		try {
    			direccionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			direccionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Direccion> listAll() throws AlidaPosException {
    	try {
    		direccionDao.beginTransaction();
    		List<Direccion> result = direccionDao.findAll();
    		direccionDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			direccionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			direccionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteDireccion(Direccion direccion) throws AlidaPosException {
    	try {
	    	direccionDao.beginTransaction();
	    	Direccion persistedObject = direccionDao.findReferenceOnly(direccion.getIddireccion());
	        direccionDao.delete(persistedObject);
	        direccionDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				direccionDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (direccionDao != null) {
				direccionDao.closeTransaction();
			}
		}
    }
    

}
