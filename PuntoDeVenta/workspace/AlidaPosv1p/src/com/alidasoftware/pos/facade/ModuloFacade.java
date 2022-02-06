package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.ModuloDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Modulo;

public class ModuloFacade implements Serializable {
	
	private static final long serialVersionUID = -2992194348568814580L;
	
	private ModuloDao moduloDao = new ModuloDao();
	
	public void createModulo(Modulo modulo) throws AlidaPosException {
		try {
			moduloDao.beginTransaction();
			moduloDao.save(modulo);
			moduloDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			moduloDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			moduloDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
//	public void updatePerfil(Perfil perfil) throws AlidaPosException {
//		try {
//			perfilDao.beginTransaction();
//			Perfil persistedObject = perfilDao.find(perfil.getIdPerfil());
//			persistedObject.setNombre(perfil.getNombre());
//			persistedObject.setDescripcion(perfil.getDescripcion());
//			persistedObject.setActivo(perfil.isActivo());
//			perfilDao.update(persistedObject);
//			perfilDao.commitAndCloseTransaction();
//		} catch (Exception ex) {
//    		try {
//    			System.out.println("Error al editar el perfil " + ex.getMessage());
//    			perfilDao.rollback();
//    		} catch (Exception ex1) {
//    			throw new AlidaPosException(ex1.getMessage());
//    		}
//    		throw new AlidaPosException(ex.getMessage());
//    	} finally {
//    		try {
//    			perfilDao.closeTransaction();
//    		} catch (Exception ex) {
//    			throw new AlidaPosException(ex.getMessage());
//    		}
//    	}
//    }
// 
    public Modulo findModuloById(Integer idModulo) throws AlidaPosException {
    	try {
    		moduloDao.beginTransaction();
    		Modulo modulo = moduloDao.find(idModulo.intValue());
    		moduloDao.closeTransaction();
    		return modulo;
    	} catch (Exception ex) {
    		try {
    			moduloDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			moduloDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Modulo> listAll() throws AlidaPosException {
    	try {
    		moduloDao.beginTransaction();
    		List<Modulo> result = moduloDao.findAll();
    		moduloDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			System.out.println("Error : " + ex.getMessage());
    			moduloDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			moduloDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deletePerfil(Modulo modulo) throws AlidaPosException {
    	try {
    		moduloDao.beginTransaction();
	    	Modulo persistedObject = moduloDao.findReferenceOnly(modulo.getIdModulo());
	    	moduloDao.delete(persistedObject);
	    	moduloDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				moduloDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (moduloDao != null) {
				moduloDao.closeTransaction();
			}
		}
    }
    
    public List<Modulo> listAllActive() throws AlidaPosException {
    	try {
    		moduloDao.beginTransaction();
    		List<Modulo> result = moduloDao.findAllActive();
    		moduloDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			System.out.println("Error : " + ex.getMessage());
    			moduloDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			moduloDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Modulo> listAllByCategoria(String categoria) throws AlidaPosException {
    	try {
    		moduloDao.beginTransaction();
    		List<Modulo> result = moduloDao.findAllByCategoria(categoria);
    		moduloDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			System.out.println("Error : " + ex.getMessage());
    			moduloDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			moduloDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }

//	public List<Perfil> findByNombreClave(String searchNombre,
//			String searchClave) throws AlidaPosException {
//		try {
//    		perfilDao.beginTransaction();
//    		List<Perfil> result = perfilDao.findByNombreClave(searchNombre, searchClave);
//    		perfilDao.closeTransaction();
//    		return result;
//    	} catch (Exception ex) {
//    		try {
//    			perfilDao.rollback();
//    		} catch (Exception ex1) {
//    			throw new AlidaPosException(ex1.getMessage());
//    		}
//    		throw new AlidaPosException(ex.getMessage());
//    	} finally {
//    		try {
//    			perfilDao.closeTransaction();
//    		} catch (Exception ex) {
//    			throw new AlidaPosException(ex.getMessage());
//    		}
//    	}
//	}
}
