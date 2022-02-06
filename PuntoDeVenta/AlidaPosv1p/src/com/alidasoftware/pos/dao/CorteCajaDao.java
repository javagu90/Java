package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Cortecaja;

public class CorteCajaDao extends GenericDao<Cortecaja> implements Serializable {

	private static final long serialVersionUID = -6594164704089255427L;

	public CorteCajaDao() {
        super(Cortecaja.class);
    }
	
	public List<Cortecaja> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Cortecaja findbyId(int idCortecaja) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idcortecaja", idCortecaja);
        return super.findOneResult(Cortecaja.FIND_BY_ID, parameters);
	}
	
    public void delete(Cortecaja corteCaja) throws AlidaPosException {
        super.delete(corteCaja.getIdcortecaja(), Cortecaja.class);
    }
    
    @Override
    public Cortecaja save(Cortecaja entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Cortecaja update(Cortecaja entity) throws AlidaPosException {
    	return super.update(entity);
    }



}
