package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detalleinventariofisico;

public class DetalleInventarioFisicoDao extends GenericDao<Detalleinventariofisico> implements Serializable {

	private static final long serialVersionUID = 3713729012436435570L;

	public DetalleInventarioFisicoDao() {
        super(Detalleinventariofisico.class);
    }
	
	public List<Detalleinventariofisico> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Detalleinventariofisico findbyId(int idDetalleinventariofisico) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("iddetalleinventariofisico", idDetalleinventariofisico);
        return super.findOneResult(Detalleinventariofisico.FIND_BY_ID, parameters);
	}
	
    public void delete(Detalleinventariofisico detalleInventarioFisico) throws AlidaPosException {
        super.delete(detalleInventarioFisico.getIddetalleinventariofisico(), Detalleinventariofisico.class);
    }
    
    @Override
    public Detalleinventariofisico save(Detalleinventariofisico entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Detalleinventariofisico update(Detalleinventariofisico entity) throws AlidaPosException {
    	return super.update(entity);
    }

}
