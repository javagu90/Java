package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detalleapartado;

public class DetalleApartadoDao extends GenericDao<Detalleapartado> implements Serializable {

	private static final long serialVersionUID = 1562698720524097111L;

	public DetalleApartadoDao() {
        super(Detalleapartado.class);
    }
	
	public List<Detalleapartado> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Detalleapartado findbyId(int idDetalleapartado) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("iddetalleapartado", idDetalleapartado);
        return super.findOneResult(Detalleapartado.FIND_BY_ID, parameters);
	}
	
    public void delete(Detalleapartado detalleApartado) throws AlidaPosException {
        super.delete(detalleApartado.getIddetalleapartado(), Detalleapartado.class);
    }
    
    @Override
    public Detalleapartado save(Detalleapartado entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Detalleapartado update(Detalleapartado entity) throws AlidaPosException {
    	return super.update(entity);
    }

}
