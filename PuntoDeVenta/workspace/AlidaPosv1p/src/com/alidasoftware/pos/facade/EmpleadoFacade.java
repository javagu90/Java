package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.EmpleadoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Empleado;

public class EmpleadoFacade implements Serializable {
	
	private static final long serialVersionUID = -2992194348568814580L;
	
	private EmpleadoDao empleadoDao = new EmpleadoDao();
	
	public void createEmpleado(Empleado empleado) throws AlidaPosException {
		try {
			empleadoDao.beginTransaction();
			empleadoDao.save(empleado);
			empleadoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			empleadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			empleadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateEmpleado(Empleado empleado) throws AlidaPosException {
		try {
			empleadoDao.beginTransaction();
			Empleado persistedObject = empleadoDao.find(empleado.getIdempleado());
			persistedObject.setActivo(empleado.getActivo());
			persistedObject.setClaveempleado(empleado.getClaveempleado());
			persistedObject.setComentarios(empleado.getComentarios());
			persistedObject.setPersona(empleado.getPersona());			
			empleadoDao.update(persistedObject);
			empleadoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			System.out.println("Error al editar el empleado " + ex.getMessage());
    			empleadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			empleadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Empleado findEmpleado(int idEmpleado) throws AlidaPosException {
    	try {
    		empleadoDao.beginTransaction();
    		Empleado empleado = empleadoDao.find(idEmpleado);
    		empleadoDao.closeTransaction();
    		return empleado;
    	} catch (Exception ex) {
    		try {
    			empleadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			empleadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }

    public List<Empleado> FindLikeClave(String clave) throws AlidaPosException {
    	try {
    		empleadoDao.beginTransaction();
    		List<Empleado> result = empleadoDao.findLikeClave(clave);    		
    		empleadoDao.closeTransaction();
        	return result;
    	} catch (Exception ex) {
    		try {
    			empleadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			empleadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Empleado> findAllWithoutUser() throws AlidaPosException{
    	try {
    		empleadoDao.beginTransaction();
    		List<Empleado> result = empleadoDao.findAllWithoutUser();
    		empleadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			empleadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			empleadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public Empleado findById(int idEmpleado) throws AlidaPosException {
    	try {
    		empleadoDao.beginTransaction();
    		Empleado empleado = empleadoDao.findById(idEmpleado);
    		empleadoDao.closeTransaction();
    		return empleado;
    	}  catch (Exception ex) {
    		try {
    			empleadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			empleadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Empleado> listAll() throws AlidaPosException {
    	try {
    		empleadoDao.beginTransaction();
    		List<Empleado> result = empleadoDao.findAll();
    		empleadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			System.out.println("Error : " + ex.getMessage());
    			empleadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			empleadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteEmpleado(Empleado empleado) throws AlidaPosException {
    	try {
    		empleadoDao.beginTransaction();
	    	Empleado persistedObject = empleadoDao.findReferenceOnly(empleado.getIdempleado());
	    	empleadoDao.delete(persistedObject);
	        empleadoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				empleadoDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (empleadoDao != null) {
				empleadoDao.closeTransaction();
			}
		}
    }
    
    public Empleado findEmpleadoByClave(String claveEmpleado) throws AlidaPosException {
    	try {
    		empleadoDao.beginTransaction();
    		Empleado result = empleadoDao.findEmpleadoByClave(claveEmpleado);
    		empleadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			empleadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			empleadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
     }

	public List<Empleado> findByNombreClave(String searchNombre,
			String searchClave) throws AlidaPosException {
		try {
    		empleadoDao.beginTransaction();
    		List<Empleado> result = empleadoDao.findByNombreClave(searchNombre, searchClave);
    		empleadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			empleadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			empleadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
}
