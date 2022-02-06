package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Inventario;
import com.alidasoftware.pos.model.Tienda;

public class InventarioDao extends GenericDao<Inventario> implements Serializable {

	private static final long serialVersionUID = 2705834862865329775L;

	public InventarioDao() {
        super(Inventario.class);
    }
	
	public List<Inventario> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Inventario findbyId(int idInventario) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idinventario", idInventario);
        return super.findOneResult(Inventario.FIND_BY_ID, parameters);
	}
	
    public void delete(Inventario inventario) throws AlidaPosException {
        super.delete(inventario.getIdinventario(), Inventario.class);
    }
    
    @Override
    public Inventario save(Inventario entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Inventario update(Inventario entity) throws AlidaPosException {
    	return super.update(entity);
    }

	public Inventario findByTienda(Tienda tienda) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "select i from Inventario i where i.tienda = :tienda";
        parameters.put("tienda", tienda);
        return super.findOneResultQuery(queryString, parameters);
	}



}
