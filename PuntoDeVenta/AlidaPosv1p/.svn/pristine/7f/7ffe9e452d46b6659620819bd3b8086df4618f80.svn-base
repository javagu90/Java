package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detallecorteparcial;

public class DetalleCorteParcialDao extends GenericDao<Detallecorteparcial> implements Serializable {

	private static final long serialVersionUID = -4283976202639997824L;

	public DetalleCorteParcialDao() {
        super(Detallecorteparcial.class);
    }
	
	public List<Detallecorteparcial> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Detallecorteparcial findbyId(int idDetallecorteparcial) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("iddetallecorteparcial", idDetallecorteparcial);
        return super.findOneResult(Detallecorteparcial.FIND_BY_ID, parameters);
	}
	
    public void delete(Detallecorteparcial detalleCorteParcial) throws AlidaPosException {
        super.delete(detalleCorteParcial.getIddetallecorteparcial(), Detallecorteparcial.class);
    }
    
    @Override
    public Detallecorteparcial save(Detallecorteparcial entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Detallecorteparcial update(Detallecorteparcial entity) throws AlidaPosException {
    	return super.update(entity);
    }

}
