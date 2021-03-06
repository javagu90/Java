package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.FormaPagoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Formapago;

public class FormaPagoFacade implements Serializable {

	private static final long serialVersionUID = -4143123824851847353L;
	
	private FormaPagoDao formaPagoDao = new FormaPagoDao();
	
	public void createFormaPago(Formapago formaPago) throws AlidaPosException {
		try {
			formaPagoDao.beginTransaction();
			formaPagoDao.save(formaPago);
			formaPagoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			formaPagoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			formaPagoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateFormaPago(Formapago formaPago) throws AlidaPosException {
		try {
			formaPagoDao.beginTransaction();
			Formapago persistedObject = formaPagoDao.find(formaPago.getIdformapago());
			persistedObject.setComentarios(formaPago.getComentarios());
			persistedObject.setDescripcion(formaPago.getDescripcion());
			persistedObject.setDetallecortecajas(formaPago.getDetallecortecajas());
			persistedObject.setNombre(formaPago.getNombre());
			persistedObject.setPagoapartados(formaPago.getPagoapartados());
			persistedObject.setPagoventas(formaPago.getPagoventas());
			persistedObject.setActivo(formaPago.isActivo());
			formaPagoDao.update(persistedObject);
			formaPagoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			formaPagoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			formaPagoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Formapago findFormaPago(int idFormapago) throws AlidaPosException {
    	try {
    		formaPagoDao.beginTransaction();
    		Formapago formaPago = formaPagoDao.find(idFormapago);
    		formaPagoDao.closeTransaction();
    		return formaPago;
    	} catch (Exception ex) {
    		try {
    			formaPagoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			formaPagoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Formapago> listAll() throws AlidaPosException {
    	try {
    		formaPagoDao.beginTransaction();
    		List<Formapago> result = formaPagoDao.findAll();
    		formaPagoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			formaPagoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			formaPagoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Formapago> listAllOrderByIdAsc() throws AlidaPosException {
    	try {
    		formaPagoDao.beginTransaction();
    		List<Formapago> result = formaPagoDao.findAllOrderbyIdAsc();
    		formaPagoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			formaPagoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			formaPagoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public void deleteFormaPago(Formapago formaPago) throws AlidaPosException {
    	try {
	    	formaPagoDao.beginTransaction();
	    	Formapago persistedObject = formaPagoDao.findReferenceOnly(formaPago.getIdformapago());
	        formaPagoDao.delete(persistedObject);
	        formaPagoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				formaPagoDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (formaPagoDao != null) {
				formaPagoDao.closeTransaction();
			}
		}
    }

	public Formapago findFormaPagoByNombre(String nombre) throws AlidaPosException {
		try {
    		formaPagoDao.beginTransaction();
    		Formapago formaPago = formaPagoDao.findByNombre(nombre);
    		formaPagoDao.closeTransaction();
    		return formaPago;
    	} catch (Exception ex) {
    		try {
    			formaPagoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			formaPagoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}

}
