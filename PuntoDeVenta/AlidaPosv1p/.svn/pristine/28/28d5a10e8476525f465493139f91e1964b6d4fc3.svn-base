package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.alidasoftware.pos.dao.AdeudoVentaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.exception.SQLStateMessage;
import com.alidasoftware.pos.model.AdeudoVenta;

public class AdeudoVentaFacade implements Serializable {

	private static final long serialVersionUID = 6622851017184451322L;
	
	private AdeudoVentaDao adeudoVentaDao = new AdeudoVentaDao();
	
	public void createAdeudoVenta(AdeudoVenta adeudoVenta) throws AlidaPosException {
		try {
			adeudoVentaDao.beginTransaction();
			adeudoVentaDao.save(adeudoVenta);
			adeudoVentaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
			Throwable t = adeudoVentaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				adeudoVentaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			adeudoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateAdeudoVenta(AdeudoVenta adeudoVenta) throws AlidaPosException {
		try {
			adeudoVentaDao.beginTransaction();
			AdeudoVenta persistedObject = adeudoVentaDao.find(adeudoVenta.getIdadeudoventa());
			persistedObject.setAdeudo(adeudoVenta.getAdeudo());
			persistedObject.setFoliooperacion(adeudoVenta.getFoliooperacion());
			persistedObject.setIdoperacion(adeudoVenta.getIdoperacion());
			persistedObject.setFecha(adeudoVenta.getFecha());
			persistedObject.setModule(adeudoVenta.getModule());
			//persistedObject.setVenta(adeudoVenta.getVenta());			
			adeudoVentaDao.update(persistedObject);
			adeudoVentaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
			Throwable t = adeudoVentaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				adeudoVentaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			adeudoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
	
	public AdeudoVenta findAdeudoVenta(int idAdeudoVenta) throws AlidaPosException {
    	try {
    		adeudoVentaDao.beginTransaction();
    		AdeudoVenta adeudoVenta = adeudoVentaDao.find(idAdeudoVenta);
    		adeudoVentaDao.closeTransaction();
    		return adeudoVenta;
    	} catch (Exception ex) {
    		Throwable t = adeudoVentaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				adeudoVentaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			adeudoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
	
	public AdeudoVenta findByFolioId(int idOperacion, String folio, String module) throws AlidaPosException {
		try {
    		adeudoVentaDao.beginTransaction();
    		AdeudoVenta adeudoVenta = adeudoVentaDao.findByFolioIdModule(idOperacion, folio, module);
    		adeudoVentaDao.closeTransaction();
    		return adeudoVenta;
    	} catch (Exception ex) {
    		Throwable t = adeudoVentaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				adeudoVentaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			adeudoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public List<AdeudoVenta> listAll() throws AlidaPosException {
    	try {
    		adeudoVentaDao.beginTransaction();
    		List<AdeudoVenta> result = adeudoVentaDao.findAll();
    		adeudoVentaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		Throwable t = adeudoVentaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				adeudoVentaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
    	} finally {
    		try {
    			adeudoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
	
	public void deleteAdeudoVenta(AdeudoVenta adeudoVenta) throws AlidaPosException {
    	try {
	    	adeudoVentaDao.beginTransaction();
	    	AdeudoVenta persistedObject = adeudoVentaDao.findReferenceOnly(adeudoVenta.getIdadeudoventa());
	        adeudoVentaDao.delete(persistedObject);
	        adeudoVentaDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
    		Throwable t = adeudoVentaDao.getLastThrowable(ex);
    		SQLException exc = (SQLException) t;
			try {
				adeudoVentaDao.rollback();
			} catch (Exception ex1) {
				//throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(SQLStateMessage.getSQLMessageCode(exc.getSQLState()));
		} finally {
			if (adeudoVentaDao != null) {
				adeudoVentaDao.closeTransaction();
			}
		}
    }
	
}
