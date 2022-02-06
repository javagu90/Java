package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.alidasoftware.pos.dao.CategoriaProductoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.exception.SQLStateMessage;
import com.alidasoftware.pos.model.Categoriaproducto;

public class CategoriaProductoFacade implements Serializable {

	private static final long serialVersionUID = 2820107237985762624L;
	
	private CategoriaProductoDao categoriaProductoDao = new CategoriaProductoDao();
	
	public void createCategoriaproducto(Categoriaproducto categoriaProducto) throws AlidaPosException {
		try {
			categoriaProductoDao.beginTransaction();
			categoriaProductoDao.save(categoriaProducto);
			categoriaProductoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
			Throwable t = categoriaProductoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaProductoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			categoriaProductoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateCategoriaproducto(Categoriaproducto categoriaProducto) throws AlidaPosException {
		try {
			categoriaProductoDao.beginTransaction();
			Categoriaproducto persistedObject = categoriaProductoDao.find(categoriaProducto.getIdcategoriaproducto());
			persistedObject.setComentarios(categoriaProducto.getComentarios());
			persistedObject.setDescripcion(categoriaProducto.getDescripcion());
			persistedObject.setNombre(categoriaProducto.getNombre());
			persistedObject.setProductos(categoriaProducto.getProductos());
			persistedObject.setPromocions(categoriaProducto.getPromocions());
			persistedObject.setActivo(categoriaProducto.isActivo());
			categoriaProductoDao.update(persistedObject);
			categoriaProductoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
			Throwable t = categoriaProductoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaProductoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			categoriaProductoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Categoriaproducto findCategoriaproducto(int idCategoriaproducto) throws AlidaPosException {
    	try {
    		categoriaProductoDao.beginTransaction();
    		Categoriaproducto categoriaProducto = categoriaProductoDao.find(idCategoriaproducto);
    		categoriaProductoDao.closeTransaction();
    		return categoriaProducto;
    	} catch (Exception ex) {
    		Throwable t = categoriaProductoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaProductoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			categoriaProductoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Categoriaproducto> listAll() throws AlidaPosException {
    	try {
    		categoriaProductoDao.beginTransaction();
    		List<Categoriaproducto> result = categoriaProductoDao.findAll();
    		categoriaProductoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = categoriaProductoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaProductoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			categoriaProductoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteCategoriaproducto(Categoriaproducto categoriaProducto) throws AlidaPosException {
    	try {
	    	categoriaProductoDao.beginTransaction();
	    	Categoriaproducto persistedObject = categoriaProductoDao.findReferenceOnly(categoriaProducto.getIdcategoriaproducto());
	        categoriaProductoDao.delete(persistedObject);
	        categoriaProductoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
    		Throwable t = categoriaProductoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaProductoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
		} finally {
			if (categoriaProductoDao != null) {
				categoriaProductoDao.closeTransaction();
			}
		}
    }
    
    public Categoriaproducto findByNombre(String nombreCP) throws AlidaPosException {
    	try {
    		categoriaProductoDao.beginTransaction();
    		Categoriaproducto categoriaProducto = categoriaProductoDao.findByNombre(nombreCP);
    		categoriaProductoDao.closeTransaction();
    		return categoriaProducto;
    	} catch (Exception ex) {
    		Throwable t = categoriaProductoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaProductoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			categoriaProductoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
     }

	public List<Categoriaproducto> searchNombreDescripcion(String searchNombre,
			String searchDescription) throws AlidaPosException{
		try {
    		categoriaProductoDao.beginTransaction();
    		List<Categoriaproducto> result = categoriaProductoDao.searchByNombreDescription(searchNombre, searchDescription);
    		categoriaProductoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			categoriaProductoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			categoriaProductoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}

}
