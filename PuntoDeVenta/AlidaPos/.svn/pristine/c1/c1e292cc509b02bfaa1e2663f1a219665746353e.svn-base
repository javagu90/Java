package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alidasoftware.pos.dao.CajaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.exception.SQLStateMessage;
import com.alidasoftware.pos.model.Caja;

public class CajaFacade implements Serializable {
	
	private static final long serialVersionUID = -3421373147178624010L;
	
	private CajaDao cajaDao = new CajaDao();

	public void createCaja(Caja caja) throws AlidaPosException {
		try {
			System.out.println("entro al facade para crear la caja");
			cajaDao.beginTransaction();
			cajaDao.save(caja);
			cajaDao.commitAndCloseTransaction();
			System.out.println("Despues de guardar la nueva caja");
		} catch (Exception ex) {
			System.out.println("hubo un error al momento de querer guardar la caja");
			Throwable t = cajaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		System.out.println("el finally de create Caja");
    		try {
    			cajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateCaja(Caja caja) throws AlidaPosException {
		try {
			cajaDao.beginTransaction();
			Caja persistedObject = cajaDao.find(caja.getIdcaja());
			//agregar todos los campos que se deseen actualizar
			persistedObject.setNombre(caja.getNombre());
			persistedObject.setClave(caja.getClave());
			persistedObject.setComentarios(caja.getComentarios());
			//FK
			persistedObject.setVentas(caja.getVentas());
			persistedObject.setCortecajas(caja.getCortecajas());
			persistedObject.setCajaEfectivo(caja.getCajaEfectivo());
			persistedObject.setTienda(caja.getTienda());
			persistedObject.setActivo(caja.isActivo());
			//update
			cajaDao.update(persistedObject);
			cajaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
			Throwable t = cajaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			cajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Caja findCaja(int idCaja) throws AlidaPosException {
    	try {
    		cajaDao.beginTransaction();
    		Caja caja = cajaDao.find(idCaja);
    		cajaDao.closeTransaction();
    		return caja;
    	} catch (Exception ex) {
    		Throwable t = cajaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			cajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public Caja findByClave(String clave) throws AlidaPosException {
    	try {
    		cajaDao.beginTransaction();
    		System.out.println("antes de hacer el find by clave de la caja");
    		Caja caja = cajaDao.findByClave(clave.toUpperCase());
    		System.out.println("desues de hacer el find by clave");
    		cajaDao.closeTransaction();
    		return caja;
    	} catch (Exception ex) {
    		System.out.println("la excepcion que tiro es : " + ex.getMessage());
    		Throwable t = cajaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			cajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public Caja findByClaveIdTienda(String claveTienda, Integer idTienda) throws AlidaPosException {
    	try {
    		cajaDao.beginTransaction();
    		Caja caja = cajaDao.findByClaveIdTienda(claveTienda, idTienda);
    		cajaDao.closeTransaction();
    		return caja;
	    } catch (Exception ex) {
			Throwable t = cajaDao.getLastThrowable(ex);
			SQLException exc = (SQLException) t;
			try {
				cajaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
		} finally {
			try {
				cajaDao.closeTransaction();
			} catch (Exception ex) {
				throw new AlidaPosException(ex.getMessage());
			}
		}
    }
    
    public Caja findByClaveIdTienda(Caja cajaToSearch) throws AlidaPosException {
    	try {
    		cajaDao.beginTransaction();
    		System.out.println("antes de hacer el find by clave de la caja");
    		Caja caja = cajaDao.findByClaveIdTienda(cajaToSearch.getClave().toUpperCase(), cajaToSearch.getTienda().getIdtienda());
    		System.out.println("desues de hacer el find by clave");
    		cajaDao.closeTransaction();
    		return caja;
    	} catch (Exception ex) {
    		System.out.println("la excepcion que tiro es : " + ex.getMessage());
    		Throwable t = cajaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			cajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Caja> listByTienda(int idTienda) throws AlidaPosException {
    	try {
    		cajaDao.beginTransaction();
    		List<Caja> result = cajaDao.listByTienda(idTienda);
    		cajaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = cajaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			cajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Caja> listByNombre(String nombre) throws AlidaPosException {
    	try {
    		cajaDao.beginTransaction();
    		List<Caja> result = cajaDao.listByNombre(nombre);
    		cajaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = cajaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			cajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Caja> listByClave(String clave) throws AlidaPosException {
    	try {
    		cajaDao.beginTransaction();
    		List<Caja> result = cajaDao.listByClave(clave);
    		cajaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = cajaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			cajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Caja> listAll() throws AlidaPosException {
    	try {
    		cajaDao.beginTransaction();
    		List<Caja> result = cajaDao.findAll();
    		cajaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = cajaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			cajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteCaja(Caja caja) throws AlidaPosException {
    	try {
	    	cajaDao.beginTransaction();
	    	Caja persistedObject = cajaDao.findReferenceOnly(caja.getIdcaja());
	    	persistedObject.setActivo(false);
	    	cajaDao.update(persistedObject);
	        // cajaDao.delete(persistedObject);
	        cajaDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
    		Throwable t = cajaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
		} finally {
			if (cajaDao != null) {
				cajaDao.closeTransaction();
			}
		}
    }
    
    public List<Caja> listBySearch(String clave, String nombre, Integer idTienda) throws AlidaPosException {
    	try {
    		List<Caja> result = new ArrayList<Caja>();
    		cajaDao.beginTransaction();
    		result = cajaDao.listByClaveNombreTienda(clave, nombre, idTienda);
    		cajaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = cajaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			cajaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
	
}
