package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.ConfiguracionDao;
import com.alidasoftware.pos.dao.ConfiguracionFacturaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Configuracion;
import com.alidasoftware.pos.model.Configuracionfactura;

public class ConfiguracionFacturaFacade implements Serializable {
	
	private static final long serialVersionUID = -2992194348568814580L;
	
	private ConfiguracionFacturaDao configuracionFacturaDao = new ConfiguracionFacturaDao();
		
	public void createConfiguracion(Configuracionfactura configuracionFactura) throws AlidaPosException {
		try {
			configuracionFacturaDao.beginTransaction();
			configuracionFacturaDao.save(configuracionFactura);
			configuracionFacturaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			configuracionFacturaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			configuracionFacturaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void update(Configuracionfactura configuracionFactura) throws AlidaPosException {
		try {
			configuracionFacturaDao.beginTransaction();
			Configuracionfactura persistedObject = configuracionFacturaDao.find(configuracionFactura.getIdconfiguracionfactura());
			persistedObject.setCalle(configuracionFactura.getCalle());
			persistedObject.setCfdi(configuracionFactura.getCfdi());
			persistedObject.setCodigopostal(configuracionFactura.getCodigopostal());
			persistedObject.setColonia(configuracionFactura.getColonia());
			persistedObject.setCorreoelectronico(configuracionFactura.getCorreoelectronico());
			persistedObject.setKey(configuracionFactura.getKey());
			persistedObject.setLogo(configuracionFactura.getLogo());
			persistedObject.setLugarexpedicion(configuracionFactura.getLugarexpedicion());
			persistedObject.setMunicipio(configuracionFactura.getMunicipio());
			persistedObject.setNumext(configuracionFactura.getNumext());
			persistedObject.setNumint(configuracionFactura.getNumint());
			persistedObject.setRazonsocial(configuracionFactura.getRazonsocial());
			persistedObject.setRegimenFiscal(configuracionFactura.getRegimenFiscal());
			persistedObject.setRfc(configuracionFactura.getRfc());
			persistedObject.setTelefono(configuracionFactura.getTelefono());
			persistedObject.setPasswordKey(configuracionFactura.getPasswordKey());
			configuracionFacturaDao.update(persistedObject);
			configuracionFacturaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			System.out.println("Error al editar el perfil " + ex.getMessage());
    			configuracionFacturaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			configuracionFacturaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
 
    public List<Configuracionfactura> listAll() throws AlidaPosException {
    	try {
    		configuracionFacturaDao.beginTransaction();
    		List<Configuracionfactura> result = configuracionFacturaDao.findAll();
    		configuracionFacturaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			System.out.println("Error : " + ex.getMessage());
    			configuracionFacturaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			configuracionFacturaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
}
