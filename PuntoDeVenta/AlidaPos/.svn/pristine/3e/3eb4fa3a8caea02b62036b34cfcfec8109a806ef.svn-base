package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Configuracion;

public class ConfiguracionDao extends GenericDao<Configuracion> implements Serializable {

	private static final long serialVersionUID = 2944771964001646655L;

	public ConfiguracionDao() {
        super(Configuracion.class);
    }
	
	public List<Configuracion> findAll() throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select c from Configuracion c";
		return super.getListResultQuery(queryString, parameters);
    }
	
	public Configuracion findById(Integer idConfiguracion) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select c from Configuracion c where c.idConfiguracion = :idConfiguracion";
		parameters.put("idConfiguracion", idConfiguracion);
		return super.findOneResultQuery(queryString, parameters);
	}
	
	public Configuracion findConfiguracionByAtributo(String atributo) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select c from Configuracion c where UPPER(c.atributo) = UPPER(:atributo)";
		parameters.put("atributo", atributo);
		return super.findOneResultQuery(queryString, parameters);
	}
	
    public void delete(Configuracion configuracion) throws AlidaPosException {
        super.delete(configuracion.getIdConfiguracion(), Configuracion.class);
    }
    
    @Override
    public Configuracion save(Configuracion entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Configuracion update(Configuracion entity) throws AlidaPosException {
    	return super.update(entity);
    }
	
}
