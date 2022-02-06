package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detalletraspaso;

public class DetalleTraspasoDao extends GenericDao<Detalletraspaso> implements Serializable {

	private static final long serialVersionUID = 5174107126605531823L;

	public DetalleTraspasoDao() {
        super(Detalletraspaso.class);
    }
	
	public List<Detalletraspaso> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Detalletraspaso findbyId(int idDetalletraspaso) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("iddetalletraspaso", idDetalletraspaso);
        return super.findOneResult(Detalletraspaso.FIND_BY_ID, parameters);
	}
	
    public void delete(Detalletraspaso detalleTraspaso) throws AlidaPosException {
        super.delete(detalleTraspaso.getIddetalletraspaso(), Detalletraspaso.class);
    }
    
    @Override
    public Detalletraspaso save(Detalletraspaso entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Detalletraspaso update(Detalletraspaso entity) throws AlidaPosException {
    	return super.update(entity);
    }


}
