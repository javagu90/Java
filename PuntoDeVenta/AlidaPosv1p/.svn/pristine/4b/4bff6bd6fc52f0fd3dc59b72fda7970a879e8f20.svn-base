package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Pagoventa;

public class PagoVentaDao extends GenericDao<Pagoventa> implements Serializable {

	private static final long serialVersionUID = 188503192775419034L;

	public PagoVentaDao() {
        super(Pagoventa.class);
    }
	
	public List<Pagoventa> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Pagoventa findbyId(int idPago) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idpago", idPago);
        return super.findOneResult(Pagoventa.FIND_BY_ID, parameters);
	}
	
	public List<Pagoventa> findByIdVenta(int idVenta) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idventa", idVenta);
		return super.getListResult(Pagoventa.FIND_BY_ID_VENTA, parameters);
	}
	
    public void delete(Pagoventa pagoventa) throws AlidaPosException {
        super.delete(pagoventa.getIdpago(), Pagoventa.class);
    }
    
    @Override
    public Pagoventa save(Pagoventa entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Pagoventa update(Pagoventa entity) throws AlidaPosException {
    	return super.update(entity);
    }



}
