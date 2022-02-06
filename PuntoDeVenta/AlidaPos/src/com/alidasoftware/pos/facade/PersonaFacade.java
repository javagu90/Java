package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.PersonaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Persona;

public class PersonaFacade implements Serializable {

	private static final long serialVersionUID = 1400795180117147904L;
	
	private PersonaDao personaDao = new PersonaDao();
	
	public void createPersona(Persona persona) throws AlidaPosException {
		try {
			personaDao.beginTransaction();
			personaDao.save(persona);
			personaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			personaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			personaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updatePersona(Persona persona) throws AlidaPosException {
		try {
			personaDao.beginTransaction();
			Persona persistedObject = personaDao.find(persona.getIdpersona());
			persistedObject.setApellidomaterno(persona.getApellidomaterno());
			persistedObject.setApellidopaterno(persona.getApellidopaterno());
			persistedObject.setClientes(persona.getClientes());
			persistedObject.setContacto(persona.getContacto());
			persistedObject.setFechanacimiento(persona.getFechanacimiento());
			persistedObject.setNombre(persona.getNombre());
			persistedObject.setProveedors(persona.getProveedores());
			persistedObject.setRazonsocial(persona.getRazonsocial());
			persistedObject.setRfc(persona.getRfc());
			persistedObject.setTipopersona(persona.getTipopersona());
			persistedObject.setEmpleados(persona.getEmpleados());		
			personaDao.update(persistedObject);
			personaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			personaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			personaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Persona findPersona(int idPersona) throws AlidaPosException {
    	try {
    		personaDao.beginTransaction();
    		Persona persona = personaDao.find(idPersona);
    		personaDao.closeTransaction();
    		return persona;
    	} catch (Exception ex) {
    		try {
    			personaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			personaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Persona> FindLikeInfoPersona(String infoPersona) throws AlidaPosException {
    	try {
    		personaDao.beginTransaction();
    		List<Persona> result = personaDao.findLikeInfoPersona(infoPersona);    		
    		personaDao.closeTransaction();
        	return result;
    	} catch (Exception ex) {
    		try {
    			personaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			personaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Persona> listAll() throws AlidaPosException {
    	try {
    		personaDao.beginTransaction();
    		List<Persona> result = personaDao.findAll();
    		personaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			personaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			personaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deletePersona(Persona persona) throws AlidaPosException {
    	try {
	    	personaDao.beginTransaction();
	    	Persona persistedObject = personaDao.findReferenceOnly(persona.getIdpersona());
	        personaDao.delete(persistedObject);
	        personaDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				personaDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (personaDao != null) {
				personaDao.closeTransaction();
			}
		}
    }
    

}
