package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.InventarioDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Inventario;
import com.alidasoftware.pos.model.Tienda;

public class InventarioFacade implements Serializable {

	private static final long serialVersionUID = 3088938993235777550L;
	
	private InventarioDao inventarioDao = new InventarioDao();
	
	public void createInventario(Inventario inventario) throws AlidaPosException {
		try {
			inventarioDao.beginTransaction();
			inventarioDao.save(inventario);
			inventarioDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			inventarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			inventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateInventario(Inventario inventario) throws AlidaPosException {
		try {
			inventarioDao.beginTransaction();
			Inventario persistedObject = inventarioDao.find(inventario.getIdinventario());
			persistedObject.setComentarios(inventario.getComentarios());
			persistedObject.setDescripcion(inventario.getDescripcion());
			persistedObject.setDetalleinventarios(inventario.getDetalleinventarios());
			persistedObject.setNombre(inventario.getNombre());			
			inventarioDao.update(persistedObject);
			inventarioDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			inventarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			inventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Inventario findInventario(int idInventario) throws AlidaPosException {
    	try {
    		inventarioDao.beginTransaction();
    		Inventario inventario = inventarioDao.find(idInventario);
    		inventarioDao.closeTransaction();
    		return inventario;
    	} catch (Exception ex) {
    		try {
    			inventarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			inventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Inventario> listAll() throws AlidaPosException {
    	try {
    		inventarioDao.beginTransaction();
    		List<Inventario> result = inventarioDao.findAll();
    		inventarioDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			inventarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			inventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteInventario(Inventario inventario) throws AlidaPosException {
    	try {
	    	inventarioDao.beginTransaction();
	    	Inventario persistedObject = inventarioDao.findReferenceOnly(inventario.getIdinventario());
	        inventarioDao.delete(persistedObject);
	        inventarioDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				inventarioDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (inventarioDao != null) {
				inventarioDao.closeTransaction();
			}
		}
    }

	public Inventario findInventarioByTienda(Tienda tienda) throws AlidaPosException{
		try {
    		inventarioDao.beginTransaction();
    		Inventario inventario = inventarioDao.findByTienda(tienda);
    		inventarioDao.closeTransaction();
    		return inventario;
    	} catch (Exception ex) {
    		try {
    			inventarioDao.rollback();
    		} catch (Exception ex1) {
    			System.out.println("Error : " + ex1.getMessage());
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			inventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
   

}
