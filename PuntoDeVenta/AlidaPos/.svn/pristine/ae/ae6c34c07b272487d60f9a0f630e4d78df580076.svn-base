package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Configuracionfactura;

public class ConfiguracionFacturaDao extends GenericDao<Configuracionfactura> implements Serializable {

	private static final long serialVersionUID = 2944771964001646655L;

	public ConfiguracionFacturaDao() {
        super(Configuracionfactura.class);
    }
	
	public List<Configuracionfactura> findAll() throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select c from Configuracionfactura c";
		return super.getListResultQuery(queryString, parameters);
    }
	
    @Override
    public Configuracionfactura save(Configuracionfactura entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Configuracionfactura update(Configuracionfactura entity) throws AlidaPosException {
    	return super.update(entity);
    }
    
	public Configuracionfactura findById(Integer idConfiguracionFactura) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select c from Configuracionfactura c where c.iconfiguraciofactura = :idConfiguracionFactura";
		parameters.put("idConfiguracionFactura", idConfiguracionFactura);
		return super.findOneResultQuery(queryString, parameters);
	}
	
}
