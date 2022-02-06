package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Apartado;

public class ApartadoDao extends GenericDao<Apartado> implements Serializable {

	private static final long serialVersionUID = 7662197248168685985L;

	public ApartadoDao() {
        super(Apartado.class);
    }
	
	public List<Apartado> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Apartado findById(int idApartado) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idapartado", idApartado);
        return super.findOneResult(Apartado.FIND_BY_ID, parameters);
	}
	
	public List<Apartado> findByStatus(int status) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("status", status);
		return super.getListResult(Apartado.FIND_BY_STATUS, parameters);
	}
	
	public List<Apartado> findByFolio(String folio) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("folio",  "%" + folio + "%");
		return super.getListResult(Apartado.FIND_BY_FOLIO, parameters);
	}
	
	public List<Apartado> findByFecha(Date fecha) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("fechaapartado", fecha);
		return super.getListResult(Apartado.FIND_BY_FECHA_APARTADO, parameters);
	}
	
	public List<Apartado> findByIdCliente(int idCliente) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idcliente", idCliente);
		return super.getListResult(Apartado.FIND_BY_ID_CLIENTE, parameters);
	}
	
    public void delete(Apartado vista) throws AlidaPosException {
        super.delete(vista.getIdapartado(), Apartado.class);
    }
    
    @Override
    public Apartado save(Apartado entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Apartado update(Apartado entity) throws AlidaPosException {
    	return super.update(entity);
    }

}
