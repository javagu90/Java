package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Devolucion;

public class DevolucionDao extends GenericDao<Devolucion> implements Serializable {

	private static final long serialVersionUID = 4580961958317320112L;

	public DevolucionDao() {
        super(Devolucion.class);
    }
	
	public List<Devolucion> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Devolucion findbyId(int idDevolucion) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("iddevolucion", idDevolucion);
        return super.findOneResult(Devolucion.FIND_BY_ID, parameters);
	}
	
	public List<Devolucion> findByOperation(int operacion) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("operacion", operacion);
		return super.getListResult(Devolucion.FIND_BY_ID_OPERACION, parameters);
	}
	
    public void delete(Devolucion devolucion) throws AlidaPosException {
        super.delete(devolucion.getIddevolucion(), Devolucion.class);
    }
    
    @Override
    public Devolucion save(Devolucion entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Devolucion update(Devolucion entity) throws AlidaPosException {
    	return super.update(entity);
    }



}
