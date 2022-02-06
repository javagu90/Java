package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Unidad;

public class UnidadDao extends GenericDao<Unidad> implements Serializable {

	private static final long serialVersionUID = 7400805699518416397L;

	public UnidadDao() {
        super(Unidad.class);
    }
	
	public List<Unidad> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Unidad findById(int idUnidad) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idunidad", idUnidad);
        return super.findOneResult(Unidad.FIND_BY_ID, parameters);
	}
	
	public List<Unidad> findByNombre(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select c from Unidad c where ";
		if(nombre!=null && !nombre.trim().equals("")){
			queryString += " UPPER(c.nombre) like UPPER(:nombre) ";
			parameters.put("nombre", "%" + nombre + "%");
		} else {
			return super.findAll();
		}		
		return super.getListResultQuery(queryString, parameters);
	} 
	
    public void delete(Unidad unidad) throws AlidaPosException {
        super.delete(unidad.getIdunidad(), Unidad.class);
    }
    
    @Override
    public Unidad save(Unidad entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Unidad update(Unidad entity) throws AlidaPosException {
    	return super.update(entity);
    }

	public Unidad findUnidadByNombre(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select c from Unidad c where ";
		if(nombre!=null && !nombre.trim().equals("")){
			queryString += " UPPER(c.nombre) = UPPER(:nombre) ";
			parameters.put("nombre", nombre);
			return super.findOneResultQuery(queryString, parameters);
		} else {
			return null;
		}		
		
	}

}
