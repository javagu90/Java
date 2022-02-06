package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Estado;

public class EstadoDao extends GenericDao<Estado> implements Serializable {

	private static final long serialVersionUID = 7366438615639525608L;
	
	public EstadoDao() {
        super(Estado.class);
    }
	
	public List<Estado> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Estado findbyId(int idEstado) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idestado", idEstado);
        return super.findOneResult(Estado.FIND_BY_ID, parameters);
	}
	
	public Estado findByNombre(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nombre", nombre);
        return super.findOneResult(Estado.FIND_BY_NOMBRE, parameters);
	}

	public void delete(Estado estado) throws AlidaPosException {
        super.delete(estado.getIdestado(), Estado.class);
    }
    
    @Override
    public Estado save(Estado entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Estado update(Estado entity) throws AlidaPosException {
    	return super.update(entity);
    }

}
