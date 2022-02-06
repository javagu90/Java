package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Municipio;

public class MunicipioDao extends GenericDao<Municipio> implements Serializable {
	
	private static final long serialVersionUID = 614535815091592921L;

	public MunicipioDao() {
        super(Municipio.class);
    }
	
	public List<Municipio> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Municipio findbyId(int idMunicipio) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idmunicipio", idMunicipio);
        return super.findOneResult(Municipio.FIND_BY_ID, parameters);
	}
	
	public Municipio findbyNombre(String nombre, int idEstado)  throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("nombre", nombre);
        parameters.put("idestado", idEstado);
        return super.findOneResult(Municipio.FIND_BY_NOMBRE, parameters);
	}

	public List<Municipio> findbyEstado(int idEstado)  throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idestado", idEstado);
        return super.getListResult(Municipio.FIND_BY_ID_ESTADO, parameters);
	}

	public void delete(Municipio municipio) throws AlidaPosException {
        super.delete(municipio.getIdmunicipio(), Municipio.class);
    }
    
    @Override
    public Municipio save(Municipio entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Municipio update(Municipio entity) throws AlidaPosException {
    	return super.update(entity);
    }



}
