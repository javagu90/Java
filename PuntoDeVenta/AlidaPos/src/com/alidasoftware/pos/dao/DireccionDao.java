package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Direccion;

public class DireccionDao extends GenericDao<Direccion> implements Serializable {

	private static final long serialVersionUID = 5836665833867763196L;

	public DireccionDao() {
        super(Direccion.class);
    }
	
	public List<Direccion> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Direccion findbyId(int idDireccion) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("iddireccion", idDireccion);
        return super.findOneResult(Direccion.FIND_BY_ID, parameters);
	}
	
    public void delete(Direccion direccion) throws AlidaPosException {
        super.delete(direccion.getIddireccion(), Direccion.class);
    }
    
    @Override
    public Direccion save(Direccion entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Direccion update(Direccion entity) throws AlidaPosException {
    	return super.update(entity);
    }



}
