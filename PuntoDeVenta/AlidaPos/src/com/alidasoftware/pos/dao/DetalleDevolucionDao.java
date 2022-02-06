package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detalledevolucion;

public class DetalleDevolucionDao extends GenericDao<Detalledevolucion> implements Serializable {

	private static final long serialVersionUID = 8764454250264050048L;

	public DetalleDevolucionDao() {
        super(Detalledevolucion.class);
    }
	
	public List<Detalledevolucion> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Detalledevolucion findbyId(int idDetalledevolucion) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("iddetalledevolucion", idDetalledevolucion);
        return super.findOneResult(Detalledevolucion.FIND_BY_ID, parameters);
	}
	
    public void delete(Detalledevolucion detalleDevolucion) throws AlidaPosException {
        super.delete(detalleDevolucion.getIddetalledevolucion(), Detalledevolucion.class);
    }
    
    @Override
    public Detalledevolucion save(Detalledevolucion entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Detalledevolucion update(Detalledevolucion entity) throws AlidaPosException {
    	return super.update(entity);
    }


}
