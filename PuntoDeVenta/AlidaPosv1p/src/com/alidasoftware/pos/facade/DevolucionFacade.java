package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.DevolucionDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Devolucion;

public class DevolucionFacade implements Serializable {

	private static final long serialVersionUID = -2263353927170156206L;
	
	private DevolucionDao devolucionDao = new DevolucionDao();
	
	public void createDevolucion(Devolucion devolucion) throws AlidaPosException {
		try {
			devolucionDao.beginTransaction();
			devolucionDao.save(devolucion);
			devolucionDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			devolucionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			devolucionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateDevolucion(Devolucion devolucion) throws AlidaPosException {
		try {
			devolucionDao.beginTransaction();
			Devolucion persistedObject = devolucionDao.find(devolucion.getIddevolucion());
			persistedObject.setComentarios(devolucion.getComentarios());
			persistedObject.setDetalledevoluciones(devolucion.getDetalledevoluciones());
			persistedObject.setFecha(devolucion.getFecha());
			persistedObject.setIdoperacion(devolucion.getIdoperacion());
			persistedObject.setMotivo(devolucion.getMotivo());
			persistedObject.setOperacion(devolucion.getOperacion());			
			devolucionDao.update(persistedObject);
			devolucionDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			devolucionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			devolucionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Devolucion findDevolucion(int idDevolucion) throws AlidaPosException {
    	try {
    		devolucionDao.beginTransaction();
    		Devolucion devolucion = devolucionDao.find(idDevolucion);
    		devolucionDao.closeTransaction();
    		return devolucion;
    	} catch (Exception ex) {
    		try {
    			devolucionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			devolucionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Devolucion> listByOperation(int operacion) throws AlidaPosException {
    	try {
    		devolucionDao.beginTransaction();
    		List<Devolucion> result = devolucionDao.findByOperation(operacion);
    		devolucionDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			devolucionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			devolucionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Devolucion> listAll() throws AlidaPosException {
    	try {
    		devolucionDao.beginTransaction();
    		List<Devolucion> result = devolucionDao.findAll();
    		devolucionDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			devolucionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			devolucionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteDevolucion(Devolucion devolucion) throws AlidaPosException {
    	try {
	    	devolucionDao.beginTransaction();
	    	Devolucion persistedObject = devolucionDao.findReferenceOnly(devolucion.getIddevolucion());
	        devolucionDao.delete(persistedObject);
	        devolucionDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				devolucionDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (devolucionDao != null) {
				devolucionDao.closeTransaction();
			}
		}
    }

}
