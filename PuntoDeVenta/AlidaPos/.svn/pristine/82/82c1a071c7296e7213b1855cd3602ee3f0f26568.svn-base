package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.ValeDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Vale;

public class ValeFacade implements Serializable {

	private static final long serialVersionUID = 8723004466497801663L;
	
	private ValeDao valeDao = new ValeDao();
	
	public void createVale(Vale vale) throws AlidaPosException {
		try {
			valeDao.beginTransaction();
			valeDao.save(vale);
			valeDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			valeDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			valeDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateVale(Vale vale) throws AlidaPosException {
		try {
			valeDao.beginTransaction();
			Vale persistedObject = valeDao.find(vale.getIdvale());
			persistedObject.setCantidad(vale.getCantidad());
			persistedObject.setCliente(vale.getCliente());
			persistedObject.setComentarios(vale.getComentarios());
			persistedObject.setFecha(vale.getFecha());
			persistedObject.setTipovencimiento(vale.getTipovencimiento());		
			persistedObject.setFolio(vale.getFolio());
			persistedObject.setStatus(vale.getStatus());
			valeDao.update(persistedObject);
			valeDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			valeDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			valeDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Vale findVale(int idVale) throws AlidaPosException {
    	try {
    		valeDao.beginTransaction();
    		Vale vale = valeDao.find(idVale);
    		valeDao.closeTransaction();
    		return vale;
    	} catch (Exception ex) {
    		try {
    			valeDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			valeDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public Vale findByFolio(String folioVale) throws AlidaPosException {
    	try {
    		valeDao.beginTransaction();
    		Vale vale = valeDao.findByFolio(folioVale);
    		valeDao.closeTransaction();
    		return vale;
    	} catch (Exception ex) {
    		try {
    			valeDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			valeDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Vale> listByStatus(int status) throws AlidaPosException {
    	try {
    		valeDao.beginTransaction();
    		List<Vale> result = valeDao.findByStatus(status);
    		valeDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			valeDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			valeDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Vale> listAll() throws AlidaPosException {
    	try {
    		valeDao.beginTransaction();
    		List<Vale> result = valeDao.findAll();
    		valeDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			valeDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			valeDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteVale(Vale vale) throws AlidaPosException {
    	try {
	    	valeDao.beginTransaction();
	    	Vale persistedObject = valeDao.findReferenceOnly(vale.getIdvale());
	        valeDao.delete(persistedObject);
	        valeDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				valeDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (valeDao != null) {
				valeDao.closeTransaction();
			}
		}
    }

}
