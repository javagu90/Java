package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.alidasoftware.pos.dao.MunicipioDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Municipio;

public class MunicipioFacade implements Serializable {

	private static final long serialVersionUID = -6566748953121137541L;
	
	private MunicipioDao municipioDao = new MunicipioDao();
	
	public void createMunicipio(Municipio municipio) throws AlidaPosException {
		try {
			municipioDao.beginTransaction();
			municipioDao.save(municipio);
			municipioDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			municipioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			municipioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateMunicipio(Municipio municipio) throws AlidaPosException {
		try {
			municipioDao.beginTransaction();
			Municipio persistedObject = municipioDao.find(municipio.getIdmunicipio());
			persistedObject.setDireccions(municipio.getDireccions());
			persistedObject.setEstado(municipio.getEstado());
			persistedObject.setNombre(municipio.getNombre());			
			persistedObject.setClave(municipio.getClave());			
			persistedObject.setSigla(municipio.getSigla());			
			municipioDao.update(persistedObject);
			municipioDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			municipioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			municipioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Municipio findMunicipio(int idMunicipio) throws AlidaPosException {
    	try {
    		municipioDao.beginTransaction();
    		Municipio ret = municipioDao.find(idMunicipio);
    		municipioDao.closeTransaction();
    		return ret;
    	} catch (Exception ex) {
    		try {
    			municipioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			municipioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Municipio findMunicipiobyNombre(String nombre, int idEstado) throws AlidaPosException {
		List<Municipio> allEstado = findMunicipiosByEstado(idEstado);
        Iterator<Municipio> iteratorMunicipios = allEstado.iterator();
        Municipio  currentMunicipio;
        while(iteratorMunicipios.hasNext()){
        	currentMunicipio = iteratorMunicipios.next();
        	if (nombre.toLowerCase().equals(currentMunicipio.getNombre().toLowerCase()) ) {
        		return currentMunicipio;
			}
 		}  
        return null;
    }
    
    public List<Municipio> findMunicipiosByEstado(int idEstado) throws AlidaPosException {
		/*List<Municipio> all = listAll();		
		List<Municipio> result = new ArrayList<Municipio>();
		
        Iterator<Municipio> iteratorMunicipios = all.iterator();
        Municipio currentMunicipio;

        while(iteratorMunicipios.hasNext()){
        	currentMunicipio = iteratorMunicipios.next();
        	if (currentMunicipio.getEstado().getIdestado() == idEstado) {
        		result.add(currentMunicipio);
			}
 		}        	
		return result;*/
    	try {
    		municipioDao.beginTransaction();
    		List<Municipio> result = municipioDao.findbyEstado(idEstado);
    		municipioDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			municipioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			municipioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }

    public List<Municipio> listAll() throws AlidaPosException {
    	try {
    		municipioDao.beginTransaction();
    		List<Municipio> result = municipioDao.findAll();
    		municipioDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			municipioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			municipioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteMunicipio(Municipio municipio) throws AlidaPosException {
    	try {
	    	municipioDao.beginTransaction();
	    	Municipio persistedObject = municipioDao.findReferenceOnly(municipio.getIdmunicipio());
	        municipioDao.delete(persistedObject);
	        municipioDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				municipioDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (municipioDao != null) {
				municipioDao.closeTransaction();
			}
		}
    }
    

}
