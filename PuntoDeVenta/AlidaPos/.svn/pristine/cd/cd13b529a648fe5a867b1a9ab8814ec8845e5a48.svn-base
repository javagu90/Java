package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Cajaefectivo;

public class CajaEfectivoDao extends GenericDao<Cajaefectivo> implements Serializable {

	private static final long serialVersionUID = -1894555071478816708L;

	public CajaEfectivoDao() {
        super(Cajaefectivo.class);
    }
	
	public Cajaefectivo findByIdCaja(int idCaja) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idcaja", idCaja);
        return super.findOneResult(Cajaefectivo.FIND_BY_ID_CAJA, parameters);
	}
	
	public List<Cajaefectivo> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
    public void delete(Cajaefectivo cajaefectivo) throws AlidaPosException {
        super.delete(cajaefectivo.getIdcajaefectivo(), Cajaefectivo.class);
    }
    
    @Override
    public Cajaefectivo save(Cajaefectivo entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Cajaefectivo update(Cajaefectivo entity) throws AlidaPosException {
    	return super.update(entity);
    }
}
