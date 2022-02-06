package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.alidasoftware.pos.dao.ApartadoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.exception.SQLStateMessage;
import com.alidasoftware.pos.model.Apartado;

public class ApartadoFacade implements Serializable {

	private static final long serialVersionUID = -9072067840143721158L;
	
	private ApartadoDao apartadoDao = new ApartadoDao();
	
	public void createApartado(Apartado apartado) throws AlidaPosException {
		try {
			apartadoDao.beginTransaction();
			apartadoDao.save(apartado);
			apartadoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
			Throwable t = apartadoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				apartadoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			apartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateApartado(Apartado apartado) throws AlidaPosException {
		try {
			apartadoDao.beginTransaction();
			Apartado persistedObject = apartadoDao.find(apartado.getIdapartado());
			persistedObject.setCliente(apartado.getCliente());
			persistedObject.setComentarios(apartado.getComentarios());
			persistedObject.setDetalleapartados(apartado.getDetalleapartados());
			persistedObject.setFechaapartado(apartado.getFechaapartado());
			persistedObject.setFolio(apartado.getFolio());
			persistedObject.setIva(apartado.getIva());
			persistedObject.setPagoapartados(apartado.getPagoapartados());
			persistedObject.setStatus(apartado.getStatus());
			persistedObject.setTipovencimiento(apartado.getTipovencimiento());
			persistedObject.setTotal(apartado.getTotal());			
			apartadoDao.update(persistedObject);
			apartadoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
			Throwable t = apartadoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				apartadoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			apartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Apartado findApartado(int idApartado) throws AlidaPosException {
    	try {
    		apartadoDao.beginTransaction();
    		Apartado apartado = apartadoDao.find(idApartado);
    		apartadoDao.closeTransaction();
    		return apartado;
    	} catch (Exception ex) {
    		Throwable t = apartadoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				apartadoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			apartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Apartado> listByFolio(String folio) throws AlidaPosException {
    	try {
    		apartadoDao.beginTransaction();
    		List<Apartado> result = apartadoDao.findByFolio(folio);
    		apartadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = apartadoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				apartadoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			apartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Apartado> listByFecha(Date fecha) throws AlidaPosException {
    	try {
    		apartadoDao.beginTransaction();
    		List<Apartado> result = apartadoDao.findByFecha(fecha);
    		apartadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = apartadoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				apartadoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			apartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Apartado> listByClient(int idCliente) throws AlidaPosException {
    	try {
    		apartadoDao.beginTransaction();
    		List<Apartado> result = apartadoDao.findByIdCliente(idCliente);
    		apartadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = apartadoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				apartadoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			apartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Apartado> listByStatus(int status) throws AlidaPosException {
    	try {
    		apartadoDao.beginTransaction();
    		List<Apartado> result = apartadoDao.findByStatus(status);
    		apartadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = apartadoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				apartadoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			apartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public Apartado findById(int idApartado) throws AlidaPosException {
    	try {
    		apartadoDao.beginTransaction();
    		Apartado apartado = apartadoDao.findById(idApartado);
    		apartadoDao.closeTransaction();
    		return apartado;
    	}  catch (Exception ex) {
    		Throwable t = apartadoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				apartadoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			apartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Apartado> listAll() throws AlidaPosException {
    	try {
    		apartadoDao.beginTransaction();
    		List<Apartado> result = apartadoDao.findAll();
    		apartadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = apartadoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				apartadoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			apartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteApartado(Apartado apartado) throws AlidaPosException {
    	try {
	    	apartadoDao.beginTransaction();
	    	Apartado persistedObject = apartadoDao.findReferenceOnly(apartado.getIdapartado());
	        apartadoDao.delete(persistedObject);
	        apartadoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
    		Throwable t = apartadoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				apartadoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
		} finally {
			if (apartadoDao != null) {
				apartadoDao.closeTransaction();
			}
		}
    }

}
