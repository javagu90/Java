package com.alidasoftware.pos.facade;
import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Diasfestivos;
import com.alidasoftware.pos.dao.DiasFestivosDao;

public class DiasFestivosFacade implements Serializable {
	
	private static final long serialVersionUID = -27174682508746603L;
	
	private DiasFestivosDao diasfestivosDao = new DiasFestivosDao();
	
	public void createDiafestivo(Diasfestivos diasfestivos) throws AlidaPosException {
		try {
			diasfestivosDao.beginTransaction();
			diasfestivosDao.save(diasfestivos);
			diasfestivosDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			diasfestivosDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			diasfestivosDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateDiaFestivo(Diasfestivos diasfestivos) throws AlidaPosException {
		try {
			diasfestivosDao.beginTransaction();
			Diasfestivos persistedObject = diasfestivosDao.find(diasfestivos.getIddiafestivo());
			//agregar todos los campos que se deseen actualizar			
			persistedObject.setIddiafestivo(diasfestivos.getIddiafestivo());
			persistedObject.setFecha(diasfestivos.getFecha());
			//update
			diasfestivosDao.update(persistedObject);
			diasfestivosDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			diasfestivosDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			diasfestivosDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Diasfestivos findDiaFestivo(int idDiafestivo) throws AlidaPosException {
    	try {
    		diasfestivosDao.beginTransaction();
    		Diasfestivos diafestivo = diasfestivosDao.find(idDiafestivo);
    		diasfestivosDao.closeTransaction();
    		return diafestivo;
    	} catch (Exception ex) {
    		try {
    			diasfestivosDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			diasfestivosDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Diasfestivos> listAll() throws AlidaPosException {
    	try {
    		diasfestivosDao.beginTransaction();
    		List<Diasfestivos> result = diasfestivosDao.findAll();
    		diasfestivosDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			diasfestivosDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			diasfestivosDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteDiaFestivo(Diasfestivos diafestivo) throws AlidaPosException {
    	try {
	    	diasfestivosDao.beginTransaction();
	    	Diasfestivos persistedObject = diasfestivosDao.findReferenceOnly(diafestivo.getIddiafestivo());
	        diasfestivosDao.delete(persistedObject);
	        diasfestivosDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				diasfestivosDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (diasfestivosDao != null) {
				diasfestivosDao.closeTransaction();
			}
		}
    }

}
