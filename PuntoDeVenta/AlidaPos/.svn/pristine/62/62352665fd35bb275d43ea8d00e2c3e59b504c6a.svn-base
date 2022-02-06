package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Venta;

public class VentaDao extends GenericDao<Venta> implements Serializable {

	private static final long serialVersionUID = -2119489030659074014L;

	public VentaDao() {
        super(Venta.class);
    }
	
	public List<Venta> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Venta findbyId(int idVenta) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idventa", idVenta);
        return super.findOneResult(Venta.FIND_BY_ID, parameters);
	}
	
	public List<Venta> findByStatus(int status) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("status", status);
		return super.getListResult(Venta.FIND_BY_STATUS, parameters);
	}
	
    public void delete(Venta venta) throws AlidaPosException {
        super.delete(venta.getIdventa(), Venta.class);
    }
    
    @Override
    public Venta save(Venta entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Venta update(Venta entity) throws AlidaPosException {
    	return super.update(entity);
    }

	public Venta findByFolio(String folio) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select v from Venta v where ";
		if(folio!=null && !folio.trim().equals("")){
			queryString += " UPPER(v.folio) = UPPER(:folio) ";
			parameters.put("folio", folio);
		} else {
			return null;
		}		
		return super.findOneResultQuery(queryString, parameters);
	}
}
