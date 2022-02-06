package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.alidasoftware.pos.dao.CajaEfectivoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.exception.SQLStateMessage;
import com.alidasoftware.pos.model.Caja;
import com.alidasoftware.pos.model.Cajaefectivo;

public class CajaEfectivoFacade implements Serializable {

	private static final long serialVersionUID = 5712247321692268032L;

	private CajaEfectivoDao cajaEfectivoDao = new CajaEfectivoDao();
	
	public void createCajaEfectivo(Cajaefectivo cajaEfectivo) throws AlidaPosException {
		try {
			cajaEfectivoDao.beginTransaction();
			cajaEfectivoDao.save(cajaEfectivo);
			cajaEfectivoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
			Throwable t = cajaEfectivoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaEfectivoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			cajaEfectivoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateCajaEfectivo(Cajaefectivo cajaEfectivo) throws AlidaPosException {
		try {
			cajaEfectivoDao.beginTransaction();
			Cajaefectivo persistedObject = cajaEfectivoDao.find(cajaEfectivo.getIdcajaefectivo());			
			persistedObject.setCaja(cajaEfectivo.getCaja());
			persistedObject.setUsuario(cajaEfectivo.getUsuario());
			persistedObject.setEfectivo(cajaEfectivo.getEfectivo());
			persistedObject.setFecha(cajaEfectivo.getFecha());			
			cajaEfectivoDao.update(persistedObject);
			cajaEfectivoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
			Throwable t = cajaEfectivoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaEfectivoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			cajaEfectivoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
	
	public Cajaefectivo findCajaEfectivo(int idCajaEfectivo) throws AlidaPosException {
    	try {
    		cajaEfectivoDao.beginTransaction();
    		Cajaefectivo cajaEfectivo = cajaEfectivoDao.find(idCajaEfectivo);
    		cajaEfectivoDao.closeTransaction();
    		return cajaEfectivo;
    	} catch (Exception ex) {
    		Throwable t = cajaEfectivoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaEfectivoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			cajaEfectivoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
	
	public Cajaefectivo findByCaja(Caja caja) throws AlidaPosException {
    	try {
    		cajaEfectivoDao.beginTransaction();
    		Cajaefectivo cajaEfectivo = cajaEfectivoDao.findByIdCaja(caja.getIdcaja());
    		cajaEfectivoDao.closeTransaction();
    		return cajaEfectivo;
    	} catch (Exception ex) {
    		Throwable t = cajaEfectivoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaEfectivoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			cajaEfectivoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
	
	public List<Cajaefectivo> listAll() throws AlidaPosException {
    	try {
    		cajaEfectivoDao.beginTransaction();
    		List<Cajaefectivo> result = cajaEfectivoDao.findAll();
    		cajaEfectivoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = cajaEfectivoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaEfectivoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			cajaEfectivoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
	
	public void deleteCajaEfectivo(Cajaefectivo cajaEfectivo) throws AlidaPosException {
    	try {
	    	cajaEfectivoDao.beginTransaction();
	    	Cajaefectivo persistedObject = cajaEfectivoDao.findReferenceOnly(cajaEfectivo.getIdcajaefectivo());
	        cajaEfectivoDao.delete(persistedObject);
	        cajaEfectivoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
    		Throwable t = cajaEfectivoDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				cajaEfectivoDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
		} finally {
			if (cajaEfectivoDao != null) {
				cajaEfectivoDao.closeTransaction();
			}
		}
    }
}
