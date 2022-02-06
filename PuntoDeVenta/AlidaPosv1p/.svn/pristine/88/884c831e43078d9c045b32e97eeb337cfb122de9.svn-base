package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Vale;

public class ValeDao extends GenericDao<Vale> implements Serializable {

	private static final long serialVersionUID = 8843681841248365894L;

	public ValeDao() {
        super(Vale.class);
    }
	
	public List<Vale> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Vale findById(int idVale) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idvale", idVale);
        return super.findOneResult(Vale.FIND_BY_ID, parameters);
	}
	
	public Vale findByFolio(String folio) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("folio", folio);
		return super.findOneResult(Vale.FIND_BY_FOLIO, parameters);
	}
	
	public List<Vale> findByStatus(int status) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("status", status);
		return super.getListResult(Vale.FIND_BY_STATUS, parameters);
	}
	
    public void delete(Vale vale) throws AlidaPosException {
        super.delete(vale.getIdvale(), Vale.class);
    }
    
    @Override
    public Vale save(Vale entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Vale update(Vale entity) throws AlidaPosException {
    	return super.update(entity);
    }

}
