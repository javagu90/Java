package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Categoriacliente;

public class CategoriaClienteDao extends GenericDao<Categoriacliente> implements Serializable {

	private static final long serialVersionUID = -6302335629840444108L;

	public CategoriaClienteDao() {
        super(Categoriacliente.class);
    }
	
	public List<Categoriacliente> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Categoriacliente findById(int idCategoriacliente) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idcategoriacliente", idCategoriacliente);
        return super.findOneResult(Categoriacliente.FIND_BY_ID, parameters);
	}
	
	public Categoriacliente findByNombre(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("nombre", nombre);
        return super.findOneResult(Categoriacliente.FIND_BY_NOMBRE, parameters);
	}
	
	public List<Categoriacliente> listByNombre(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("nombre", "%" + nombre + "%");
        return super.getListResult(Categoriacliente.LIST_BY_NOMBRE, parameters);
	}
	
	public List<Categoriacliente> listByDescuento(float descuento) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("descuento", descuento);
        return super.getListResult(Categoriacliente.FIND_BY_DESCUENTO, parameters);
	}

	public void delete(Categoriacliente categoriaCliente) throws AlidaPosException {
        super.delete(categoriaCliente.getIdcategoriacliente(), Categoriacliente.class);
    }
	
	public List<Categoriacliente> listByNombreDescuento(String nombre, Float descuento) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "SELECT c FROM Categoriacliente c WHERE ";
		if( (nombre!=null && !nombre.trim().equals("")) ||
				(descuento!=null) ){
			
			if(nombre!=null && !nombre.trim().equals("")){
				queryString += " UPPER(c.nombre) like UPPER(:nombre) and";
				parameters.put("nombre", "%" + nombre + "%");
			}
			if(descuento!=null){
				queryString += " c.descuento = :descuento and";
				parameters.put("descuento", descuento);
			}
			queryString = queryString.substring(0, queryString.length()-3);
			return super.getListResultQuery(queryString, parameters);
		} else {
			return super.findAll();
		}
	}
    
    @Override
    public Categoriacliente save(Categoriacliente entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Categoriacliente update(Categoriacliente entity) throws AlidaPosException {
    	return super.update(entity);
    }

	public List<Categoriacliente> listAllActive() throws AlidaPosException{
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "SELECT c FROM Categoriacliente c WHERE c.activo = true";
		return super.getListResultQuery(queryString, parameters);
	}



}
