package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detalleventa;

public class DetalleVentaDao extends GenericDao<Detalleventa> implements Serializable {

	private static final long serialVersionUID = 8937623806190939127L;

	public DetalleVentaDao() {
        super(Detalleventa.class);
    }
	
	public List<Detalleventa> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Detalleventa findbyId(int idDetalleventa) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("iddetalleventa", idDetalleventa);
        return super.findOneResult(Detalleventa.FIND_BY_ID, parameters);
	}
	
    public void delete(Detalleventa detalleVenta) throws AlidaPosException {
        super.delete(detalleVenta.getIddetalleventa(), Detalleventa.class);
    }
    
    @Override
    public Detalleventa save(Detalleventa entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Detalleventa update(Detalleventa entity) throws AlidaPosException {
    	return super.update(entity);
    }


}
