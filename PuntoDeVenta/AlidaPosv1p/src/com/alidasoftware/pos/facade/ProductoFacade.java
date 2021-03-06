package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.alidasoftware.pos.dao.ProductoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Producto;

public class ProductoFacade implements Serializable {

	private static final long serialVersionUID = -7735907004216646112L;
	
	private ProductoDao productoDao = new ProductoDao();
	
	public void createProducto(Producto producto) throws AlidaPosException {
		try {
			productoDao.beginTransaction();
			productoDao.save(producto);
			productoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			productoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			productoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateProducto(Producto producto) throws AlidaPosException {
		try {
			productoDao.beginTransaction();
			Producto persistedObject = productoDao.find(producto.getIdproducto());
			persistedObject.setCategoriaproductos(producto.getCategoriaproductos());
			persistedObject.setClave(producto.getClave());
			persistedObject.setComentarios(producto.getComentarios());
			persistedObject.setCodigo(producto.getCodigo());
			persistedObject.setPathimagen(producto.getPathimagen());
			persistedObject.setDescripcion(producto.getDescripcion());
			persistedObject.setDetalleapartados(producto.getDetalleapartados());
			persistedObject.setDetalledevolucions(producto.getDetalledevolucions());
			persistedObject.setDetalleinventariofisicos(producto.getDetalleinventariofisicos());
			persistedObject.setDetalleinventarios(producto.getDetalleinventarios());
			persistedObject.setDetalletraspasos(producto.getDetalletraspasos());
			persistedObject.setDetalleventas(producto.getDetalleventas());
			persistedObject.setDetallevistas(producto.getDetallevistas());
			persistedObject.setEntradainventarios(producto.getEntradainventarios());
			persistedObject.setFechaingreso(producto.getFechaingreso());
			persistedObject.setIva(producto.getIva());
			persistedObject.setNombre(producto.getNombre());
			persistedObject.setPrecioventas(producto.getPrecioventas());
			persistedObject.setUnidad(producto.getUnidad());
			persistedObject.setActivo(producto.isActivo());
			persistedObject.setProveedor(producto.getProveedor());
			persistedObject.setPrecio(producto.getPrecio());
			persistedObject.setExentoDeIva(producto.isExentoDeIva());
			productoDao.update(persistedObject);
			productoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			productoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			productoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Producto findProducto(int idProducto) throws AlidaPosException {
    	try {
    		productoDao.beginTransaction();
    		Producto producto = productoDao.find(idProducto);
    		productoDao.closeTransaction();
    		return producto;
    	} catch (Exception ex) {
    		try {
    			productoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			productoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    public Producto findProducto(String nombre){
		List<Producto> allProductos;
		try {
			allProductos = listAll();
		} catch (AlidaPosException e) {
			return null;
		}
        Iterator<Producto> iteratorProductos = allProductos.iterator();
        Producto  currentProducto;
        while(iteratorProductos.hasNext()){
        	currentProducto = iteratorProductos.next();
        	if (nombre.toLowerCase().equals(currentProducto.getNombre().toLowerCase()) ) {
        		return currentProducto;
			}
 		}  
        return null;
     }
 
 
    public Producto findProductByName(String name) throws AlidaPosException {
    	try {
    		productoDao.beginTransaction();
    		Producto product = productoDao.findByName(name);
    		productoDao.closeTransaction();
    		return product;
    	} catch (Exception ex) {
    		try {
    			productoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			productoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Producto> FindProductLikeName(String name) throws AlidaPosException {
    	try {
    		productoDao.beginTransaction();
    		List<Producto> result = productoDao.findLikeName(name);    		
    		productoDao.closeTransaction();
        	return result;
    	} catch (Exception ex) {
    		try {
    			productoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			productoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Producto> listAll() throws AlidaPosException {
    	try {
    		productoDao.beginTransaction();
    		List<Producto> result = productoDao.findAll();
    		productoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			productoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			productoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteProducto(Producto producto) throws AlidaPosException {
    	try {
	    	productoDao.beginTransaction();
	    	Producto persistedObject = productoDao.findReferenceOnly(producto.getIdproducto());
	        productoDao.delete(persistedObject);
	        productoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				productoDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (productoDao != null) {
				productoDao.closeTransaction();
			}
		}
    }
    
    public Producto findProductoByClave(String claveProducto) throws AlidaPosException {
    	try {
    		productoDao.beginTransaction();
    		Producto result = productoDao.findProductoByClave(claveProducto);
    		productoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			productoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			productoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
     }

	public List<Producto> findByNombreClaveCategoria(String searchNombre,
			String searchClave, String searchCategoria) throws AlidaPosException {
		try {
    		productoDao.beginTransaction();
    		List<Producto> result = productoDao.findByNombreClaveCategoria(searchNombre, searchClave, searchCategoria);
    		productoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			productoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			productoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
    

}
