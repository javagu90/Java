package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detallecortecaja;

public class DetalleCorteCajaDao extends GenericDao<Detallecortecaja> implements Serializable {

	private static final long serialVersionUID = 3649520684958726302L;

	public DetalleCorteCajaDao() {
        super(Detallecortecaja.class);
    }
	
	public List<Detallecortecaja> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Detallecortecaja findbyId(int idDetallecortecaja) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("iddetallecortecaja", idDetallecortecaja);
        return super.findOneResult(Detallecortecaja.FIND_BY_ID, parameters);
	}
	
    public void delete(Detallecortecaja detalleCorteCaja) throws AlidaPosException {
        super.delete(detalleCorteCaja.getIddetallecortecaja(), Detallecortecaja.class);
    }
    
    @Override
    public Detallecortecaja save(Detallecortecaja entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Detallecortecaja update(Detallecortecaja entity) throws AlidaPosException {
    	return super.update(entity);
    }


}
