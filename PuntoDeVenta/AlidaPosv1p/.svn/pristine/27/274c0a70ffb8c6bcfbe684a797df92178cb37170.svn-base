package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.alidasoftware.pos.dao.CategoriaClienteDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.exception.SQLStateMessage;
import com.alidasoftware.pos.model.Categoriacliente;

public class CategoriaClienteFacade implements Serializable {

	private static final long serialVersionUID = 5438902023739226361L;
	
	private CategoriaClienteDao categoriaClienteDao = new CategoriaClienteDao();
	
	public void createCategoriacliente(Categoriacliente categoriaCliente) throws AlidaPosException {
		try {
			categoriaClienteDao.beginTransaction();
			categoriaClienteDao.save(categoriaCliente);
			categoriaClienteDao.commitAndCloseTransaction();
		} catch (Exception ex) {
			Throwable t = categoriaClienteDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaClienteDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			categoriaClienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateCategoriacliente(Categoriacliente categoriaCliente) throws AlidaPosException {
		try {
			categoriaClienteDao.beginTransaction();
			Categoriacliente persistedObject = categoriaClienteDao.find(categoriaCliente.getIdcategoriacliente());
			persistedObject.setClientes(categoriaCliente.getClientes());
			persistedObject.setComentarios(categoriaCliente.getComentarios());
			persistedObject.setDescripcion(categoriaCliente.getDescripcion());
			persistedObject.setDescuento(categoriaCliente.getDescuento());
			persistedObject.setNombre(categoriaCliente.getNombre());
			persistedObject.setActivo(categoriaCliente.isActivo());
			categoriaClienteDao.update(persistedObject);
			categoriaClienteDao.commitAndCloseTransaction();
		} catch (Exception ex) {
			Throwable t = categoriaClienteDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaClienteDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			categoriaClienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Categoriacliente findCategoriacliente(int idCategoriacliente) throws AlidaPosException {
    	try {
    		categoriaClienteDao.beginTransaction();
    		Categoriacliente categoriaCliente = categoriaClienteDao.find(idCategoriacliente);
    		categoriaClienteDao.closeTransaction();
    		return categoriaCliente;
    	} catch (Exception ex) {
    		Throwable t = categoriaClienteDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaClienteDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			categoriaClienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Categoriacliente findCategoriacliente(String nombre) throws AlidaPosException {
    	try {
    		categoriaClienteDao.beginTransaction();
    		Categoriacliente cat = categoriaClienteDao.findByNombre(nombre);
    		categoriaClienteDao.closeTransaction();
    		return cat;
    	} catch (Exception ex) {
    		System.out.println("Error es : " + ex.getMessage());
    		Throwable t = categoriaClienteDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaClienteDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			categoriaClienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Categoriacliente> listByNombre(String nombre) throws AlidaPosException {
    	try {
    		categoriaClienteDao.beginTransaction();
    		List<Categoriacliente> result = categoriaClienteDao.listByNombre(nombre);
    		categoriaClienteDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = categoriaClienteDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaClienteDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			categoriaClienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Categoriacliente> listByDescuento(float descuento) throws AlidaPosException {
    	try {
    		categoriaClienteDao.beginTransaction();
    		List<Categoriacliente> result = categoriaClienteDao.listByDescuento(descuento);
    		categoriaClienteDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = categoriaClienteDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaClienteDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			categoriaClienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }

    public List<Categoriacliente> listAll() throws AlidaPosException {
    	try {
    		categoriaClienteDao.beginTransaction();
    		List<Categoriacliente> result = categoriaClienteDao.findAll();
    		categoriaClienteDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		System.out.println("el rulis es un noob " + ex.getMessage());
    		Throwable t = categoriaClienteDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaClienteDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			categoriaClienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteCategoriacliente(Categoriacliente categoriaCliente) throws AlidaPosException {
    	try {
	    	categoriaClienteDao.beginTransaction();
	    	Categoriacliente persistedObject = categoriaClienteDao.findReferenceOnly(categoriaCliente.getIdcategoriacliente());
	        categoriaClienteDao.delete(persistedObject);
	        categoriaClienteDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
    		Throwable t = categoriaClienteDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaClienteDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
		} finally {
			if (categoriaClienteDao != null) {
				categoriaClienteDao.closeTransaction();
			}
		}
    }

	public List<Categoriacliente> findByNombreDescuento(String searchNombre,
			Float searchDescuento) throws AlidaPosException {
		try {
    		categoriaClienteDao.beginTransaction();
    		List<Categoriacliente> result = categoriaClienteDao.listByNombreDescuento(searchNombre, searchDescuento);
    		categoriaClienteDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		System.out.println("error : " + ex.getMessage());
    		Throwable t = categoriaClienteDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaClienteDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			categoriaClienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public List<Categoriacliente> findAllActive() throws AlidaPosException {
		try {
    		categoriaClienteDao.beginTransaction();
    		List<Categoriacliente> result = categoriaClienteDao.listAllActive();
    		categoriaClienteDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		System.out.println("error : " + ex.getMessage());
    		Throwable t = categoriaClienteDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				categoriaClienteDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			categoriaClienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}

}
