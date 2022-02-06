package com.alidasoftware.pos.dao;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Diasfestivos;

public class DiasFestivosDao extends GenericDao<Diasfestivos> implements Serializable {
	
	private static final long serialVersionUID = 1238519071378187257L;

	public DiasFestivosDao() {
        super(Diasfestivos.class);
    }
	
	public List<Diasfestivos> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Diasfestivos findbyId(int idDiafestivo) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("iddiafestivo", idDiafestivo);
        return super.findOneResult(Diasfestivos.FIND_BY_ID, parameters);
	}
	
	public Diasfestivos findbyFecha(Date fecha) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("fecha", fecha);
        return super.findOneResult(Diasfestivos.FIND_BY_FECHA, parameters);
	}
	
    public void delete(Diasfestivos diafestivo)  throws AlidaPosException{
        super.delete(diafestivo.getIddiafestivo(), Diasfestivos.class);
    }
    
    @Override
    public Diasfestivos save(Diasfestivos entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Diasfestivos update(Diasfestivos entity) throws AlidaPosException {
    	return super.update(entity);
    }

}
