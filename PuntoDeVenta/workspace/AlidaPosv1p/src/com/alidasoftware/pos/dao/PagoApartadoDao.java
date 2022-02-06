package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Pagoapartado;

public class PagoApartadoDao extends GenericDao<Pagoapartado> implements Serializable {

	private static final long serialVersionUID = 188503192775419034L;

	public PagoApartadoDao() {
        super(Pagoapartado.class);
    }
	
	public List<Pagoapartado> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Pagoapartado findbyId(int idPago) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idpago", idPago);
        return super.findOneResult(Pagoapartado.FIND_BY_ID, parameters);
	}
	
	public List<Pagoapartado> findByIdApartado(int idApartado) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idapartado", idApartado);
		return super.getListResult(Pagoapartado.FIND_BY_ID_APARTADO, parameters);
	}
	
    public void delete(Pagoapartado pagoApartado) throws AlidaPosException {
        super.delete(pagoApartado.getIdpago(), Pagoapartado.class);
    }
    
    @Override
    public Pagoapartado save(Pagoapartado entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Pagoapartado update(Pagoapartado entity) throws AlidaPosException {
    	return super.update(entity);
    }

}
