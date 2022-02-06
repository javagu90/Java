package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Factura;

public class FacturaDao extends GenericDao<Factura> implements Serializable {

	private static final long serialVersionUID = 6239570967498545552L;

	public FacturaDao() {
        super(Factura.class);
    }
	
	public List<Factura> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Factura findById(int idFactura) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idfactura", idFactura);
        return super.findOneResult(Factura.FIND_BY_ID, parameters);
	}
	
    public void delete(Factura factura) throws AlidaPosException {
        super.delete(factura.getIdfactura(), Factura.class);
    }
    
    @Override
    public Factura save(Factura entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Factura update(Factura entity) throws AlidaPosException {
    	return super.update(entity);
    }



}
