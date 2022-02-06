package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Tienda;

public class TiendaDao extends GenericDao<Tienda> implements Serializable {

	private static final long serialVersionUID = 7366438615639525608L;
	
	public TiendaDao() {
        super(Tienda.class);
    }
	
	public List<Tienda> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public List<Tienda> findAllActive() throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		return super.getListResult(Tienda.FIND_ALL_ACTIVE, parameters);
	}
	
	public Tienda findbyId(int idTienda) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idtienda", idTienda);
        return super.findOneResult(Tienda.FIND_BY_ID, parameters);
	}
	
    public void delete(Tienda tienda) throws AlidaPosException {
        super.delete(tienda.getIdtienda(), Tienda.class);
    }
    
    @Override
    public Tienda save(Tienda entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Tienda update(Tienda entity) throws AlidaPosException {
    	return super.update(entity);
    }
    
    public List<Tienda> findByNombre(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select c from Tienda c where ";
		if(nombre!=null && !nombre.trim().equals("")){
			queryString += " UPPER(c.nombre) like UPPER(:nombre) ";
			parameters.put("nombre", "%" + nombre + "%");
		} else {
			return super.findAll();
		}		
		return super.getListResultQuery(queryString, parameters);
	}
    
    public List<Tienda> findTiendasDestino(String nombre) throws AlidaPosException{
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	String queryString = "Select t from Tienda t where t.nombre not like :nombre and t.activo = true";
    	parameters.put("nombre", nombre);
    	return super.getListResultQuery(queryString, parameters);
    }

	public Tienda findTiendaByNombre(String nombreTienda) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select t from Tienda t where UPPER(t.nombre) = UPPER(:nombre) and t.activo = true";
		parameters.put("nombre", nombreTienda);
		return super.findOneResultQuery(queryString, parameters);
	}

}
