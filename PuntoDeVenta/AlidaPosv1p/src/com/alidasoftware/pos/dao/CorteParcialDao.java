package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Corteparcial;

public class CorteParcialDao extends GenericDao<Corteparcial> implements Serializable {

	private static final long serialVersionUID = 4867836340818775L;

	public CorteParcialDao() {
        super(Corteparcial.class);
    }
	
	public List<Corteparcial> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Corteparcial findbyId(int idCorteparcial) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idcorteparcial", idCorteparcial);
        return super.findOneResult(Corteparcial.FIND_BY_ID, parameters);
	}
	
    public void delete(Corteparcial corteParcial) throws AlidaPosException {
        super.delete(corteParcial.getIdcorteparcial(), Corteparcial.class);
    }
    
    @Override
    public Corteparcial save(Corteparcial entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Corteparcial update(Corteparcial entity) throws AlidaPosException {
    	return super.update(entity);
    }



}
