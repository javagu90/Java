package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.EstadoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Estado;

public class EstadoFacade implements Serializable {

	private static final long serialVersionUID = -5280835025175519462L;
	
	private EstadoDao estadoDao = new EstadoDao();
	
	public void createEstado(Estado estado) throws AlidaPosException {
		try {
			estadoDao.beginTransaction();
			estadoDao.save(estado);
			estadoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			estadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			estadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateEstado(Estado estado) throws AlidaPosException {
		try {
			estadoDao.beginTransaction();
			Estado persistedObject = estadoDao.find(estado.getIdestado());
			//agregar todos los campos que se deseen actualizar			
			persistedObject.setIdestado(estado.getIdestado());
			persistedObject.setNombre(estado.getNombre());
			persistedObject.setClave(estado.getClave());
			persistedObject.setAbreviatura(estado.getAbreviatura());
			//FK
			persistedObject.setMunicipios(estado.getMunicipios());
			//update
			estadoDao.update(persistedObject);
			estadoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			estadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			estadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Estado findEstado(int idEstado) throws AlidaPosException {
    	try {
    		estadoDao.beginTransaction();
    		Estado estado = estadoDao.find(idEstado);
    		estadoDao.closeTransaction();
    		return estado;
    	} catch (Exception ex) {
    		try {
    			estadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			estadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Estado findEstado(String nombre) throws AlidaPosException {
    	try {
    		estadoDao.beginTransaction();
    		Estado estado = estadoDao.findByNombre(nombre);
    		estadoDao.closeTransaction();
    		return estado;
    	} catch (Exception ex) {
    		try {
    			estadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			estadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }

    
    public List<Estado> listAll() throws AlidaPosException {
    	try {
    		estadoDao.beginTransaction();
    		List<Estado> result = estadoDao.findAll();
    		estadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			estadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			estadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteEstado(Estado estado) throws AlidaPosException {
    	try {
	    	estadoDao.beginTransaction();
	    	Estado persistedObject = estadoDao.findReferenceOnly(estado.getIdestado());
	        estadoDao.delete(persistedObject);
	        estadoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				estadoDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (estadoDao != null) {
				estadoDao.closeTransaction();
			}
		}
    }
}
