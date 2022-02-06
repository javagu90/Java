package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Vista;

public class VistaDao extends GenericDao<Vista> implements Serializable {

	private static final long serialVersionUID = 7366438615639525608L;
	
	public VistaDao() {
        super(Vista.class);
    }
	
	public List<Vista> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Vista findById(int idVista) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idvista", idVista);
        return super.findOneResult(Vista.FIND_BY_ID, parameters);
	}
	
	public List<Vista> findByStatus(int status) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("status", status);
		return super.getListResult(Vista.FIND_BY_STATUS, parameters);
	}
	
    public void delete(Vista vista) throws AlidaPosException {
        super.delete(vista.getIdvista(), Vista.class);
    }
    
    @Override
    public Vista save(Vista entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Vista update(Vista entity) throws AlidaPosException {
    	return super.update(entity);
    }

}
