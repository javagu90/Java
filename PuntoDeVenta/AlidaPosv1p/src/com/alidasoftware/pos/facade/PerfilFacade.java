package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.PerfilDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Perfil;

public class PerfilFacade implements Serializable {
	
	private static final long serialVersionUID = -2992194348568814580L;
	
	private PerfilDao perfilDao = new PerfilDao();
	
	public void createPerfil(Perfil perfil) throws AlidaPosException {
		try {
			perfilDao.beginTransaction();
			perfilDao.save(perfil);
			perfilDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			perfilDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			perfilDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updatePerfil(Perfil perfil) throws AlidaPosException {
		try {
			perfilDao.beginTransaction();
			Perfil persistedObject = perfilDao.find(perfil.getIdPerfil());
			persistedObject.setNombre(perfil.getNombre());
			persistedObject.setDescripcion(perfil.getDescripcion());
			persistedObject.setActivo(perfil.isActivo());
			persistedObject.setModulos(perfil.getModulos());
			perfilDao.update(persistedObject);
			perfilDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			System.out.println("Error al editar el perfil " + ex.getMessage());
    			perfilDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			perfilDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Perfil findPerfilById(Integer idPerfil) throws AlidaPosException {
    	try {
    		perfilDao.beginTransaction();
    		Perfil perfil = perfilDao.find(idPerfil.intValue());
    		perfilDao.closeTransaction();
    		return perfil;
    	} catch (Exception ex) {
    		try {
    			perfilDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			perfilDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Perfil> listAll() throws AlidaPosException {
    	try {
    		perfilDao.beginTransaction();
    		List<Perfil> result = perfilDao.findAll();
    		perfilDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			System.out.println("Error : " + ex.getMessage());
    			perfilDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			perfilDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deletePerfil(Perfil perfil) throws AlidaPosException {
    	try {
    		perfilDao.beginTransaction();
	    	Perfil persistedObject = perfilDao.findReferenceOnly(perfil.getIdPerfil());
	    	perfilDao.delete(persistedObject);
	    	perfilDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				perfilDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (perfilDao != null) {
				perfilDao.closeTransaction();
			}
		}
    }
    
    public List<Perfil> listAllActive() throws AlidaPosException {
    	try {
    		perfilDao.beginTransaction();
    		List<Perfil> result = perfilDao.findAllActive();
    		perfilDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			System.out.println("Error : " + ex.getMessage());
    			perfilDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			perfilDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }

	public List<Perfil> findByNombreClave(String searchNombre,
			String searchClave) throws AlidaPosException {
		try {
    		perfilDao.beginTransaction();
    		List<Perfil> result = perfilDao.findByNombreClave(searchNombre, searchClave);
    		perfilDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			perfilDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			perfilDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public Perfil findPerfilByNombre(String findNombre) throws AlidaPosException {
		try {
    		perfilDao.beginTransaction();
    		Perfil result = perfilDao.findPerfilByNombre(findNombre);
    		perfilDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			perfilDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			perfilDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public List<Perfil> searchByNombre(String searchNombre) throws AlidaPosException {
		try {
			perfilDao.beginTransaction();
			List<Perfil> result = perfilDao.findByNombreLike(searchNombre);
			perfilDao.closeTransaction();
			return result;
		} catch (Exception ex) {
			try {
				perfilDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			try {
				perfilDao.closeTransaction();
			} catch (Exception ex) {
				throw new AlidaPosException(ex.getMessage());
			}
		}
	}
}
