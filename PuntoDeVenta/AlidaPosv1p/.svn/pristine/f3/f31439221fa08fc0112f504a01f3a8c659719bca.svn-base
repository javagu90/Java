package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.DetalleInventarioDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detalleinventario;
import com.alidasoftware.pos.model.Tienda;

public class DetalleInventarioFacade implements Serializable {

	private static final long serialVersionUID = 7399091345558766649L;
	
	private DetalleInventarioDao detalleInventarioDao = new DetalleInventarioDao();
	
	public void createDetalleinventario(Detalleinventario detalleInventario) throws AlidaPosException {
		try {
			detalleInventarioDao.beginTransaction();
			detalleInventarioDao.save(detalleInventario);
			detalleInventarioDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleInventarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleInventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateDetalleinventario(Detalleinventario detalleInventario) throws AlidaPosException {
		try {
			detalleInventarioDao.beginTransaction();
			Detalleinventario persistedObject = detalleInventarioDao.find(detalleInventario.getIddetalleinventario());
			persistedObject.setCaducidad(detalleInventario.getCaducidad());
			persistedObject.setCantidadmax(detalleInventario.getCantidadmax());
			persistedObject.setCantidadmin(detalleInventario.getCantidadmin());
			persistedObject.setExistenciaapvi(detalleInventario.getExistenciaapvi());
			persistedObject.setExistenciaventa(detalleInventario.getExistenciaventa());
			persistedObject.setInventario(detalleInventario.getInventario());
			persistedObject.setLote(detalleInventario.getLote());
			persistedObject.setPreciocompra(detalleInventario.getPreciocompra());
			persistedObject.setProducto(detalleInventario.getProducto());
			detalleInventarioDao.update(persistedObject);
			detalleInventarioDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleInventarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleInventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Detalleinventario findDetalleinventario(int idDetalleinventario) throws AlidaPosException {
    	try {
    		detalleInventarioDao.beginTransaction();
    		Detalleinventario detalleInventario = detalleInventarioDao.find(idDetalleinventario);
    		detalleInventarioDao.closeTransaction();
    		return detalleInventario;
    	} catch (Exception ex) {
    		try {
    			detalleInventarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleInventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public Detalleinventario findDetalleInventarioByProduct(int idProduct) throws AlidaPosException {
    	try {
    		detalleInventarioDao.beginTransaction();
    		Detalleinventario detalleInventario = detalleInventarioDao.findByProducto(idProduct);
    		detalleInventarioDao.closeTransaction();
    		return detalleInventario;
    	} catch (Exception ex) {
    		try {
    			detalleInventarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleInventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Detalleinventario> listAll() throws AlidaPosException {
    	try {
    		detalleInventarioDao.beginTransaction();
    		List<Detalleinventario> result = detalleInventarioDao.findAll();
    		detalleInventarioDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			detalleInventarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleInventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteDetalleinventario(Detalleinventario detalleInventario) throws AlidaPosException {
    	try {
	    	detalleInventarioDao.beginTransaction();
	    	Detalleinventario persistedObject = detalleInventarioDao.findReferenceOnly(detalleInventario.getIddetalleinventario());
	        detalleInventarioDao.delete(persistedObject);
	        detalleInventarioDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				detalleInventarioDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (detalleInventarioDao != null) {
				detalleInventarioDao.closeTransaction();
			}
		}
    }
    
    public Detalleinventario findDetalleInventarioByProductTienda(int idProduct, int idInventario) throws AlidaPosException {
    	try {
    		detalleInventarioDao.beginTransaction();
    		Detalleinventario detalleInventario = detalleInventarioDao.findByProductoInventario(idProduct, idInventario);
    		detalleInventarioDao.closeTransaction();
    		return detalleInventario;
    	} catch (Exception ex) {
    		try {
    			detalleInventarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleInventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }

}
