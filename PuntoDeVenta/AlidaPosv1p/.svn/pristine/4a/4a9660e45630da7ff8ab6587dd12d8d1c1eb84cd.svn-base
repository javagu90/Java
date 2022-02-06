package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Caja;

public class CajaDao extends GenericDao<Caja> implements Serializable {
	
	private static final long serialVersionUID = -1095858167887702203L;

	public CajaDao() {
        super(Caja.class);
    }
	
	//queries
	public Caja findById(int idCaja) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idcaja", idCaja); //"idcaja" tiene que tener el mismo valor que en Caja @NamedQuery...
        return super.findOneResult(Caja.FIND_BY_ID, parameters);
	}
	
	public Caja findByNombre(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nombre", nombre);
		return super.findOneResult(Caja.FIND_BY_NOMBRE, parameters);
	}	
	
	public Caja findByClave(String clave) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("clave", clave);
		return super.findOneResult(Caja.FIND_BY_CLAVE, parameters);
	}
	
	public Caja findByClaveIdTienda(String clave, Integer idTienda) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("clave", clave);
		parameters.put("idtienda", idTienda);
		return super.findOneResult(Caja.FIND_BY_CLAVE_IDTIENDA, parameters);
	}
	
	public List<Caja> listByTienda(int idTienda) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idtienda", idTienda);
		return super.getListResult(Caja.LIST_BY_TIENDA, parameters);
	}
	
	public List<Caja> listByNombre(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nombre", "%" + nombre + "%");
		return super.getListResult(Caja.LIST_BY_NOMBRE, parameters);
	}	
	
	public List<Caja> listByClave(String clave) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("clave", "%" + clave + "%");
		return super.getListResult(Caja.LIST_BY_CLAVE, parameters);
	}
	
	public List<Caja> listByClaveNombreTienda(String clave, String nombre, Integer idTienda) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		System.out.println("entra a buscar");
		String queryString = "SELECT c FROM Caja c WHERE ";
		if((clave!=null && !clave.trim().equals("")) || 
				(nombre!=null && !nombre.trim().equals("")) ||
				(idTienda!=null) ){
			if(clave!=null && !clave.trim().equals("")){
				queryString += " UPPER(c.clave) like UPPER(:clave) and";
				parameters.put("clave", "%" + clave + "%");
			}
			if(nombre!=null && !nombre.trim().equals("")){
				queryString += " UPPER(c.nombre) like UPPER(:nombre) and";
				parameters.put("nombre", "%" + nombre + "%");
			}
			if(idTienda!=null){
				queryString += " c.tienda.idtienda = :idtienda and";
				parameters.put("idtienda", idTienda);
			}
			queryString = queryString.substring(0, queryString.length()-3);
			return super.getListResultQuery(queryString, parameters);
		} else {
			return super.findAll();
		}
	}
	
	public List<Caja> listByClaveNombre(String clave, String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("clave", "%" + clave + "%");
		parameters.put("nombre", "%" + nombre + "%");
		return super.getListResult(Caja.LIST_BY_CLAVE_NOMBRE, parameters);
	}
	
	public List<Caja> listByClaveTienda(String clave, Integer idTienda) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("clave", "%" + clave + "%");
		parameters.put("idtienda", idTienda);
		return super.getListResult(Caja.LIST_BY_CLAVE_TIENDA, parameters);
	}
	
	public List<Caja> listByNombreTienda(String nombre, Integer idTienda) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nombre", "%" + nombre + "%");
		parameters.put("idtienda", idTienda);
		return super.getListResult(Caja.LIST_BY_NOMBRE_TIENDA, parameters);
	}
	
	//estos 4 métodos pasan de cajón
	public List<Caja> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
    public void delete(Caja caja) throws AlidaPosException {
        super.delete(caja.getIdcaja(), Caja.class);
    }
    
    @Override
    public Caja save(Caja entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Caja update(Caja entity) throws AlidaPosException {
    	return super.update(entity);
    }
	
}
