package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.ConfiguracionDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Configuracion;

public class ConfiguracionFacade implements Serializable {
	
	private static final long serialVersionUID = -2992194348568814580L;
	
	private ConfiguracionDao configuracionDao = new ConfiguracionDao();
		
	public void createConfiguracion(Configuracion configuracion) throws AlidaPosException {
		try {
			configuracionDao.beginTransaction();
			configuracionDao.save(configuracion);
			configuracionDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			configuracionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			configuracionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void update(Configuracion configuracion) throws AlidaPosException {
		try {
			configuracionDao.beginTransaction();
			configuracionDao.update(configuracion);
			configuracionDao.closeTransaction();
		} catch (Exception ex) {
			System.out.println("Error " + ex.getMessage());
		}
	}
	
	public void updateConfiguracion(Configuracion configuracion) throws AlidaPosException {
		try {
			configuracionDao.beginTransaction();
			Configuracion persistedObject = configuracionDao.find(configuracion.getIdConfiguracion());
			persistedObject.setAtributo(configuracion.getAtributo());
			persistedObject.setTipo(configuracion.getTipo());
			persistedObject.setValor1(configuracion.getValor1());
			persistedObject.setValor2(configuracion.getValor2());
			persistedObject.setValor3(configuracion.getValor3());
			persistedObject.setValor4(configuracion.getValor4());
			persistedObject.setValor5(configuracion.getValor5());
			configuracionDao.update(persistedObject);
			configuracionDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			System.out.println("Error al editar el perfil " + ex.getMessage());
    			configuracionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			configuracionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Configuracion findConfiguracionById(Integer idConfiguracion) throws AlidaPosException {
    	try {
    		configuracionDao.beginTransaction();
    		Configuracion configuracion = configuracionDao.find(idConfiguracion.intValue());
    		configuracionDao.closeTransaction();
    		return configuracion;
    	} catch (Exception ex) {
    		try {
    			configuracionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			configuracionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Configuracion> listAll() throws AlidaPosException {
    	try {
    		configuracionDao.beginTransaction();
    		List<Configuracion> result = configuracionDao.findAll();
    		configuracionDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			System.out.println("Error : " + ex.getMessage());
    			configuracionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			configuracionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteConfiguracion(Configuracion configuracion) throws AlidaPosException {
    	try {
    		configuracionDao.beginTransaction();
    		Configuracion persistedObject = configuracionDao.findReferenceOnly(configuracion.getIdConfiguracion());
	    	configuracionDao.delete(persistedObject);
	    	configuracionDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				configuracionDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (configuracionDao != null) {
				configuracionDao.closeTransaction();
			}
		}
    }
	
	public Configuracion findConfiguracionByAtributo(String atributo) throws AlidaPosException {
		try {
			configuracionDao.beginTransaction();
			Configuracion result = configuracionDao.findConfiguracionByAtributo(atributo);
    		configuracionDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			configuracionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			configuracionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}

}
