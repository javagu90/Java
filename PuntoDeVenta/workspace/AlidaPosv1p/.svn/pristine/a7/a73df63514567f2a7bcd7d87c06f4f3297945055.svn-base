package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.EntradaInventarioDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Entradainventario;

public class EntradaInventarioFacade implements Serializable {
	
	private static final long serialVersionUID = -4389772234738629682L;
	
	private EntradaInventarioDao entradaInventarioDao = new EntradaInventarioDao();
	
	public void createEntradainventario(Entradainventario entradaInventario) throws AlidaPosException {
		try {
			entradaInventarioDao.beginTransaction();
			entradaInventarioDao.save(entradaInventario);
			entradaInventarioDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			entradaInventarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			entradaInventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateEntradainventario(Entradainventario entradaInventario) throws AlidaPosException {
		try {
			entradaInventarioDao.beginTransaction();
			Entradainventario persistedObject = entradaInventarioDao.find(entradaInventario.getIdentradainventario());
			persistedObject.setCantidad(entradaInventario.getCantidad());
			persistedObject.setFecha(entradaInventario.getFecha());
			persistedObject.setPreciocompra(entradaInventario.getPreciocompra());
			persistedObject.setProducto(entradaInventario.getProducto());
			persistedObject.setProveedor(entradaInventario.getProveedor());		
			entradaInventarioDao.update(persistedObject);
			entradaInventarioDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			entradaInventarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			entradaInventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Entradainventario findEntradainventario(int idEntradainventario) throws AlidaPosException {
    	try {
    		entradaInventarioDao.beginTransaction();
    		Entradainventario entradaInventario = entradaInventarioDao.find(idEntradainventario);
    		entradaInventarioDao.closeTransaction();
    		return entradaInventario;
    	} catch (Exception ex) {
    		try {
    			entradaInventarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			entradaInventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Entradainventario> listAll() throws AlidaPosException {
    	try {
    		entradaInventarioDao.beginTransaction();
    		List<Entradainventario> result = entradaInventarioDao.findAll();
    		entradaInventarioDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			entradaInventarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			entradaInventarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteEntradainventario(Entradainventario entradaInventario) throws AlidaPosException {
    	try {
	    	entradaInventarioDao.beginTransaction();
	    	Entradainventario persistedObject = entradaInventarioDao.findReferenceOnly(entradaInventario.getIdentradainventario());
	        entradaInventarioDao.delete(persistedObject);
	        entradaInventarioDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				entradaInventarioDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (entradaInventarioDao != null) {
				entradaInventarioDao.closeTransaction();
			}
		}
    }

}
