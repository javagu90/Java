package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alidasoftware.pos.dao.DetalleCorteParcialDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.helper.PagoApartadoHelper;
import com.alidasoftware.pos.model.Corteparcial;
import com.alidasoftware.pos.model.Detallecorteparcial;
import com.alidasoftware.pos.model.Traspaso;

public class DetalleCorteParcialFacade implements Serializable {

	private static final long serialVersionUID = -3345254085599130046L;
	
	private DetalleCorteParcialDao detalleCorteParcialDao = new DetalleCorteParcialDao();
	
	public void createDetallecorteparcial(Detallecorteparcial detalleCorteParcial) throws AlidaPosException {
		try {
			detalleCorteParcialDao.beginTransaction();
			detalleCorteParcialDao.save(detalleCorteParcial);
			detalleCorteParcialDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleCorteParcialDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleCorteParcialDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateDetallecorteparcial(Detallecorteparcial detalleCorteParcial) throws AlidaPosException {
		try {
			detalleCorteParcialDao.beginTransaction();
			Detallecorteparcial persistedObject = detalleCorteParcialDao.find(detalleCorteParcial.getIddetallecorteparcial());
			persistedObject.setCantidad(detalleCorteParcial.getCantidad());
			persistedObject.setCorteparcial(detalleCorteParcial.getCorteparcial());
			persistedObject.setFormapago(detalleCorteParcial.getFormapago());

			detalleCorteParcialDao.update(persistedObject);
			detalleCorteParcialDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			detalleCorteParcialDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleCorteParcialDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Detallecorteparcial findDetallecorteparcial(int idDetallecorteparcial) throws AlidaPosException {
    	try {
    		detalleCorteParcialDao.beginTransaction();
    		Detallecorteparcial detalleCorteParcial = detalleCorteParcialDao.find(idDetallecorteparcial);
    		detalleCorteParcialDao.closeTransaction();
    		return detalleCorteParcial;
    	} catch (Exception ex) {
    		try {
    			detalleCorteParcialDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleCorteParcialDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Detallecorteparcial> listAll() throws AlidaPosException {
    	try {
    		detalleCorteParcialDao.beginTransaction();
    		List<Detallecorteparcial> result = detalleCorteParcialDao.findAll();
    		detalleCorteParcialDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			detalleCorteParcialDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleCorteParcialDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }

    public List<Detallecorteparcial> listAllbyCP(Corteparcial cp) throws AlidaPosException {
    	try {
    		detalleCorteParcialDao.beginTransaction();
    		List<Detallecorteparcial> result = detalleCorteParcialDao.findAllbyIdCorteParcial(cp);
    		detalleCorteParcialDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			detalleCorteParcialDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleCorteParcialDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }

    
    public void deleteDetallecorteparcial(Detallecorteparcial detalleCorteParcial) throws AlidaPosException {
    	try {
	    	detalleCorteParcialDao.beginTransaction();
	    	Detallecorteparcial persistedObject = detalleCorteParcialDao.findReferenceOnly(detalleCorteParcial.getIddetallecorteparcial());
	        detalleCorteParcialDao.delete(persistedObject);
	        detalleCorteParcialDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				detalleCorteParcialDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (detalleCorteParcialDao != null) {
				detalleCorteParcialDao.closeTransaction();
			}
		}
    }

   /* 
    public List<Object> listBySumDetalleCP(int idCorteCaja) throws AlidaPosException {
    	try {
    		detalleCorteParcialDao.beginTransaction();
    		List<Object> result = detalleCorteParcialDao.listBySumDetalleCP(idCorteCaja);
    		detalleCorteParcialDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			detalleCorteParcialDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			detalleCorteParcialDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
   */

}
