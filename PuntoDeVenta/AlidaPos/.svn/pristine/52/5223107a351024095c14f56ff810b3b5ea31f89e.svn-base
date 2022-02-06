package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.ContactoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Contacto;

public class ContactoFacade implements Serializable {

	private static final long serialVersionUID = 8922506362845610583L;
	
	private ContactoDao contactoDao = new ContactoDao();
	
	public void createContacto(Contacto contacto) throws AlidaPosException {
		try {
			contactoDao.beginTransaction();
			contactoDao.save(contacto);
			contactoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			contactoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			contactoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateContacto(Contacto contacto) throws AlidaPosException {
		try {
			contactoDao.beginTransaction();
			Contacto persistedObject = contactoDao.find(contacto.getIdcontacto());
			persistedObject.setComentarios(contacto.getComentarios());
			persistedObject.setCorreoelectronico(contacto.getCorreoelectronico());
			persistedObject.setDirecciones(contacto.getDirecciones());
			persistedObject.setFechaalta(contacto.getFechaalta());
			persistedObject.setPersonas(contacto.getPersonas());
			persistedObject.setTelefono1(contacto.getTelefono1());
			persistedObject.setTelefono2(contacto.getTelefono2());
			contactoDao.update(persistedObject);
			contactoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			contactoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			contactoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Contacto findContacto(int idContacto) throws AlidaPosException {
    	try {
    		contactoDao.beginTransaction();
    		Contacto contacto = contactoDao.find(idContacto);
    		contactoDao.closeTransaction();
    		return contacto;
    	} catch (Exception ex) {
    		try {
    			contactoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			contactoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Contacto> listAll() throws AlidaPosException {
    	try {
    		contactoDao.beginTransaction();
    		List<Contacto> result = contactoDao.findAll();
    		contactoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			contactoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			contactoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteContacto(Contacto contacto) throws AlidaPosException {
    	try {
	    	contactoDao.beginTransaction();
	    	Contacto persistedObject = contactoDao.findReferenceOnly(contacto.getIdcontacto());
	        contactoDao.delete(persistedObject);
	        contactoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				contactoDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (contactoDao != null) {
				contactoDao.closeTransaction();
			}
		}
    }

}
