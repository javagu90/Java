package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.ProveedorProductoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Proveedorproducto;

public class ProveedorProductoFacade implements Serializable {

	private static final long serialVersionUID = -4743392674381709896L;
	
	private ProveedorProductoDao proveedorProductoDao = new ProveedorProductoDao();
	
	public void createProveedorproducto(Proveedorproducto proveedorProducto) throws AlidaPosException {
		try {
			proveedorProductoDao.beginTransaction();
			proveedorProductoDao.save(proveedorProducto);
			proveedorProductoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			proveedorProductoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			proveedorProductoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateProveedorproducto(Proveedorproducto proveedorProducto) throws AlidaPosException {
		try {
			proveedorProductoDao.beginTransaction();
			Proveedorproducto persistedObject = proveedorProductoDao.find(proveedorProducto.getIdproveedorproducto());
			persistedObject.setPrecio(proveedorProducto.getPrecio());
			persistedObject.setProducto(proveedorProducto.getProducto());
			persistedObject.setProveedor(proveedorProducto.getProveedor());			
			proveedorProductoDao.update(persistedObject);
			proveedorProductoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			proveedorProductoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			proveedorProductoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Proveedorproducto findProveedorproducto(int idProveedorproducto) throws AlidaPosException {
    	try {
    		proveedorProductoDao.beginTransaction();
    		Proveedorproducto proveedorProducto = proveedorProductoDao.find(idProveedorproducto);
    		proveedorProductoDao.closeTransaction();
    		return proveedorProducto;
    	} catch (Exception ex) {
    		try {
    			proveedorProductoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			proveedorProductoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Proveedorproducto> listAll() throws AlidaPosException {
    	try {
    		proveedorProductoDao.beginTransaction();
    		List<Proveedorproducto> result = proveedorProductoDao.findAll();
    		proveedorProductoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			proveedorProductoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			proveedorProductoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteProveedorproducto(Proveedorproducto proveedorProducto) throws AlidaPosException {
    	try {
	    	proveedorProductoDao.beginTransaction();
	    	Proveedorproducto persistedObject = proveedorProductoDao.findReferenceOnly(proveedorProducto.getIdproveedorproducto());
	        proveedorProductoDao.delete(persistedObject);
	        proveedorProductoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				proveedorProductoDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (proveedorProductoDao != null) {
				proveedorProductoDao.closeTransaction();
			}
		}
    }

}
