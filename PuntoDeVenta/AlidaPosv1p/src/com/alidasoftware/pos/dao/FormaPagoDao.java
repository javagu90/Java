package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Formapago;

public class FormaPagoDao extends GenericDao<Formapago> implements Serializable {

	private static final long serialVersionUID = 1308665694549632421L;

	public FormaPagoDao() {
        super(Formapago.class);
    }
	
	public List<Formapago> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Formapago findById(int idFormapago) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idformapago", idFormapago);
        return super.findOneResult(Formapago.FIND_BY_ID, parameters);
	}
	
    public void delete(Formapago vista) throws AlidaPosException {
        super.delete(vista.getIdformapago(), Formapago.class);
    }
    
    @Override
    public Formapago save(Formapago entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Formapago update(Formapago entity) throws AlidaPosException {
    	return super.update(entity);
    }



}
