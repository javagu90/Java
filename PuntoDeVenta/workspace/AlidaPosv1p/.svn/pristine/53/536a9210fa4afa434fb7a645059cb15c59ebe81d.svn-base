package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Contacto;

public class ContactoDao extends GenericDao<Contacto> implements Serializable {

	private static final long serialVersionUID = -8513385679635286178L;

	public ContactoDao() {
        super(Contacto.class);
    }
	
	public List<Contacto> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Contacto findById(int idContacto) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idcontacto", idContacto);
        return super.findOneResult(Contacto.FIND_BY_ID, parameters);
	}
	
    public void delete(Contacto contacto) throws AlidaPosException {
        super.delete(contacto.getIdcontacto(), Contacto.class);
    }
    
    @Override
    public Contacto save(Contacto entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Contacto update(Contacto entity) throws AlidaPosException {
    	return super.update(entity);
    }



}
