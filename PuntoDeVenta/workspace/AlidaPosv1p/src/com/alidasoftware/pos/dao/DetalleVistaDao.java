package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detallevista;

public class DetalleVistaDao extends GenericDao<Detallevista> implements Serializable {

	private static final long serialVersionUID = -8171318680048347731L;

	public DetalleVistaDao() {
        super(Detallevista.class);
    }
	
	public List<Detallevista> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Detallevista findbyId(int idDetallevista) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("iddetallevista", idDetallevista);
        return super.findOneResult(Detallevista.FIND_BY_ID, parameters);
	}
	
    public void delete(Detallevista detalleVista) throws AlidaPosException {
        super.delete(detalleVista.getIddetallevista(), Detallevista.class);
    }
    
    @Override
    public Detallevista save(Detallevista entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Detallevista update(Detallevista entity) throws AlidaPosException {
    	return super.update(entity);
    }


}
