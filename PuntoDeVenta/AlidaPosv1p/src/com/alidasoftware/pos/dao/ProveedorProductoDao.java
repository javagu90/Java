package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Proveedorproducto;

public class ProveedorProductoDao extends GenericDao<Proveedorproducto> implements Serializable {

	private static final long serialVersionUID = -5361043247868704220L;

	public ProveedorProductoDao() {
        super(Proveedorproducto.class);
    }
	
	public List<Proveedorproducto> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Proveedorproducto findbyId(int idProveedorproducto) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idproveedorproducto", idProveedorproducto);
        return super.findOneResult(Proveedorproducto.FIND_BY_ID, parameters);
	}
	
    public void delete(Proveedorproducto vista) throws AlidaPosException {
        super.delete(vista.getIdproveedorproducto(), Proveedorproducto.class);
    }
    
    @Override
    public Proveedorproducto save(Proveedorproducto entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Proveedorproducto update(Proveedorproducto entity) throws AlidaPosException {
    	return super.update(entity);
    }


}
