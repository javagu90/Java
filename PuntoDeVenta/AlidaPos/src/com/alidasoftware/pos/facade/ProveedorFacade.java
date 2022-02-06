package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.alidasoftware.pos.dao.ProveedorDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Proveedor;

public class ProveedorFacade implements Serializable {

	private static final long serialVersionUID = -6662583093049100409L;
	
	private ProveedorDao proveedorDao = new ProveedorDao();
	
	public void createProveedor(Proveedor proveedor) throws AlidaPosException {
		try {
			proveedorDao.beginTransaction();
			proveedorDao.save(proveedor);
			proveedorDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			proveedorDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			proveedorDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateProveedor(Proveedor proveedor) throws AlidaPosException {
		try {
			proveedorDao.beginTransaction();
			Proveedor persistedObject = proveedorDao.find(proveedor.getIdproveedor());
			persistedObject.setClaveproveedor(proveedor.getClaveproveedor());
			persistedObject.setComentarios(proveedor.getComentarios());
			persistedObject.setEntradainventarios(proveedor.getEntradainventarios());
			persistedObject.setPersona(proveedor.getPersona());
			persistedObject.setRepresentante(proveedor.getRepresentante());
			persistedObject.setUltimaoperacion(proveedor.getUltimaoperacion());
			persistedObject.setActivo(proveedor.isActivo());
			proveedorDao.update(persistedObject);
			proveedorDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			proveedorDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			proveedorDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Proveedor findProveedor(int idProveedor) throws AlidaPosException {
    	try {
    		proveedorDao.beginTransaction();
    		Proveedor proveedor = proveedorDao.find(idProveedor);
    		proveedorDao.closeTransaction();
    		return proveedor;
    	} catch (Exception ex) {
    		try {
    			proveedorDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			proveedorDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Proveedor findProveedor(String claveProveedor) throws AlidaPosException {
		List<Proveedor> allProveedor = listAll();
        Iterator<Proveedor> iteratorProveedores = allProveedor.iterator();
        Proveedor  currentProveedor;
        while(iteratorProveedores.hasNext()){
        	currentProveedor = iteratorProveedores.next();
        	if (claveProveedor.toLowerCase().equals(currentProveedor.getClaveproveedor().toLowerCase()) ) {
        		return currentProveedor;
			}
 		}  
        return null;
     }

    public List<Proveedor> listAll() throws AlidaPosException {
    	try {
    		proveedorDao.beginTransaction();
    		List<Proveedor> result = proveedorDao.findAll();
    		proveedorDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			proveedorDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			proveedorDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteProveedor(Proveedor proveedor) throws AlidaPosException {
    	try {
	    	proveedorDao.beginTransaction();
	    	Proveedor persistedObject = proveedorDao.findReferenceOnly(proveedor.getIdproveedor());
	        proveedorDao.delete(persistedObject);
	        proveedorDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				proveedorDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (proveedorDao != null) {
				proveedorDao.closeTransaction();
			}
		}
    }
    
    public Proveedor findProveedorByClave(String claveProveedor) throws AlidaPosException {
    	try {
    		proveedorDao.beginTransaction();
    		Proveedor result = proveedorDao.findProveedorByClave(claveProveedor);
    		proveedorDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		System.out.println("Error al obtener un proveedor por clave " + ex.getMessage());
    		try {
    			proveedorDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			proveedorDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
     }

	public List<Proveedor> findByNombreClave(String searchNombre,
			String searchClave) throws AlidaPosException {
		try {
    		proveedorDao.beginTransaction();
    		List<Proveedor> result = proveedorDao.findByNombreClave(searchNombre, searchClave);
    		proveedorDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			proveedorDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			proveedorDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
    

}
