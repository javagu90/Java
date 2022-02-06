package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Inventariofisico;

public class InventarioFisicoDao extends GenericDao<Inventariofisico> implements Serializable {

	private static final long serialVersionUID = 1634869528241788068L;

	public InventarioFisicoDao() {
        super(Inventariofisico.class);
    }
	
	public List<Inventariofisico> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Inventariofisico findbyId(int idInventariofisico) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idinventariofisico", idInventariofisico);
        return super.findOneResult(Inventariofisico.FIND_BY_ID, parameters);
	}
	
    public void delete(Inventariofisico vista) throws AlidaPosException {
        super.delete(vista.getIdinventariofisico(), Inventariofisico.class);
    }
    
    @Override
    public Inventariofisico save(Inventariofisico entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Inventariofisico update(Inventariofisico entity) throws AlidaPosException {
    	return super.update(entity);
    }



}
