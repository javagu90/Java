package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.UnidadDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Unidad;

public class UnidadFacade implements Serializable {

	private static final long serialVersionUID = 1674961540218720404L;
	
	private UnidadDao unidadDao = new UnidadDao();
	
	public void createUnidad(Unidad unidad) throws AlidaPosException {
		try {
			unidadDao.beginTransaction();
			unidadDao.save(unidad);
			unidadDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			unidadDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			unidadDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateUnidad(Unidad unidad) throws AlidaPosException {
		try {
			unidadDao.beginTransaction();
			Unidad persistedObject = unidadDao.find(unidad.getIdunidad());
			persistedObject.setDescripcion(unidad.getDescripcion());
			persistedObject.setNombre(unidad.getNombre());
			persistedObject.setAceptafracciones(unidad.getAceptafracciones());
			persistedObject.setProductos(unidad.getProductos());
			persistedObject.setActivo(unidad.isActivo());
			unidadDao.update(persistedObject);
			unidadDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			unidadDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			unidadDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Unidad findUnidad(int idUnidad) throws AlidaPosException {
    	try {
    		unidadDao.beginTransaction();
    		Unidad unidad = unidadDao.find(idUnidad);
    		unidadDao.closeTransaction();
    		return unidad;
    	} catch (Exception ex) {
    		try {
    			unidadDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			unidadDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    public Unidad findUnidad(String nombreUnidad) throws AlidaPosException {
    	try {
    		unidadDao.beginTransaction();
    		Unidad result = unidadDao.findUnidadByNombre(nombreUnidad);
    		unidadDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			unidadDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			unidadDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
     }
    
    public List<Unidad> findByNombre(String searchNombre) throws AlidaPosException {
    	try {
    		unidadDao.beginTransaction();
    		List<Unidad> result = unidadDao.findByNombre(searchNombre);
    		unidadDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			unidadDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			unidadDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Unidad> listAll() throws AlidaPosException {
    	try {
    		unidadDao.beginTransaction();
    		List<Unidad> result = unidadDao.findAll();
    		unidadDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			unidadDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			unidadDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteUnidad(Unidad unidad) throws AlidaPosException {
    	try {
	    	unidadDao.beginTransaction();
	    	Unidad persistedObject = unidadDao.findReferenceOnly(unidad.getIdunidad());
	        unidadDao.delete(persistedObject);
	        unidadDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				unidadDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (unidadDao != null) {
				unidadDao.closeTransaction();
			}
		}
    }

}
