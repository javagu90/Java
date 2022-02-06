package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Traspaso;

public class TraspasoDao extends GenericDao<Traspaso> implements Serializable {

	private static final long serialVersionUID = -3662394644722477183L;

	public TraspasoDao() {
        super(Traspaso.class);
    }
	
	public List<Traspaso> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Traspaso findbyId(int idTraspaso) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idtraspaso", idTraspaso);
        return super.findOneResult(Traspaso.FIND_BY_ID, parameters);
	}
	
    public void delete(Traspaso traspaso) throws AlidaPosException {
        super.delete(traspaso.getIdtraspaso(), Traspaso.class);
    }
    
    @Override
    public Traspaso save(Traspaso entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Traspaso update(Traspaso entity) throws AlidaPosException {
    	return super.update(entity);
    }

}
