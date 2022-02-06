package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Persona;

public class PersonaDao extends GenericDao<Persona> implements Serializable {

	private static final long serialVersionUID = 7366438615639525608L;
	
	public PersonaDao() {
        super(Persona.class);
    }
	
	public List<Persona> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Persona findbyId(int idPersona) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idpersona", idPersona);
        return super.findOneResult(Persona.FIND_BY_ID, parameters);
	}
	
	public List<Persona> findLikeInfoPersona(String info) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("info", "%" + info + "%");
		return super.getListResult(Persona.FIND_LIKE_INFO_PERSONA, parameters);
	}
	
    public void delete(Persona persona) throws AlidaPosException {
        super.delete(persona.getIdpersona(), Persona.class);
    }
    
    @Override
    public Persona save(Persona entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Persona update(Persona entity) throws AlidaPosException {
    	return super.update(entity);
    }



}
