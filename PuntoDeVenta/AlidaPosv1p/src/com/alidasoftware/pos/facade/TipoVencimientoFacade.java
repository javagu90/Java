package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.alidasoftware.pos.dao.TipoVencimientoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.exception.SQLStateMessage;
import com.alidasoftware.pos.model.Tipovencimiento;

public class TipoVencimientoFacade implements Serializable {

	private static final long serialVersionUID = 7130145567820304562L;
	
	private TipoVencimientoDao tipoVencimientoDao = new TipoVencimientoDao();
	
	public void createTipoVencimiento(Tipovencimiento tipoVencimiento) throws AlidaPosException {
		try {
			tipoVencimientoDao.beginTransaction();
			tipoVencimientoDao.save(tipoVencimiento);
			tipoVencimientoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			tipoVencimientoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			tipoVencimientoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateTipoVencimiento(Tipovencimiento tipoVencimiento) throws AlidaPosException {
		try {
			tipoVencimientoDao.beginTransaction();
			Tipovencimiento persistedObject = tipoVencimientoDao.find(tipoVencimiento.getIdtipovencimiento());
			persistedObject.setApartados(tipoVencimiento.getApartados());
			persistedObject.setComentarios(tipoVencimiento.getComentarios());
			persistedObject.setDescripcion(tipoVencimiento.getDescripcion());
			persistedObject.setDias(tipoVencimiento.getDias());
			persistedObject.setNombre(tipoVencimiento.getNombre());
			persistedObject.setTipodias(tipoVencimiento.getTipodias());
			persistedObject.setActivo(tipoVencimiento.getActivo());
			persistedObject.setVales(tipoVencimiento.getVales());
			persistedObject.setVistas(tipoVencimiento.getVistas());
			tipoVencimientoDao.update(persistedObject);
			tipoVencimientoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			tipoVencimientoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			tipoVencimientoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Tipovencimiento findTipoVencimiento(int idTipovencimiento) throws AlidaPosException {
    	try {
    		tipoVencimientoDao.beginTransaction();
    		Tipovencimiento tipovencimiento = tipoVencimientoDao.find(idTipovencimiento);
    		tipoVencimientoDao.closeTransaction();
    		return tipovencimiento;
    	} catch (Exception ex) {
    		try {
    			tipoVencimientoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			tipoVencimientoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Tipovencimiento> listByStatus(boolean activo) throws AlidaPosException {
    	try {
    		tipoVencimientoDao.beginTransaction();
    		List<Tipovencimiento> result = tipoVencimientoDao.findByStatus(activo);
    		tipoVencimientoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			tipoVencimientoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			tipoVencimientoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Tipovencimiento> listAll() throws AlidaPosException {
    	try {
    		tipoVencimientoDao.beginTransaction();
    		List<Tipovencimiento> result = tipoVencimientoDao.findAll();
    		tipoVencimientoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			tipoVencimientoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			tipoVencimientoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteTipoVencimiento(Tipovencimiento tipoVencimiento) throws AlidaPosException {
    	try {
	    	tipoVencimientoDao.beginTransaction();
	    	Tipovencimiento persistedObject = tipoVencimientoDao.findReferenceOnly(tipoVencimiento.getIdtipovencimiento());
	        tipoVencimientoDao.delete(persistedObject);
	        tipoVencimientoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				tipoVencimientoDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (tipoVencimientoDao != null) {
				tipoVencimientoDao.closeTransaction();
			}
		}
    }

	public Tipovencimiento findByNombre(String nombre) throws AlidaPosException{
		try {
    		tipoVencimientoDao.beginTransaction();
    		Tipovencimiento tipoVencimiento = tipoVencimientoDao.findByNombre(nombre);
    		tipoVencimientoDao.closeTransaction();
    		return tipoVencimiento;
    	} catch (Exception ex) {
    		Throwable t = tipoVencimientoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				tipoVencimientoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			tipoVencimientoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}

	public List<Tipovencimiento> searchByFilters(String searchNombre,
			String searchDescription, String searchTipoDias) throws AlidaPosException{
		try {
			tipoVencimientoDao.beginTransaction();
    		List<Tipovencimiento> result = tipoVencimientoDao.searchByFilters(searchNombre, searchDescription, searchTipoDias);
    		tipoVencimientoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			tipoVencimientoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			tipoVencimientoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}

}
