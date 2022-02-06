package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Empleado;
import com.alidasoftware.pos.model.Modulo;
import com.alidasoftware.pos.model.Perfil;

public class ModuloDao extends GenericDao<Modulo> implements Serializable {

	private static final long serialVersionUID = 2944771964001646655L;

	public ModuloDao() {
        super(Modulo.class);
    }
	
	public List<Modulo> findAll() throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select m from Modulo m";
		return super.getListResultQuery(queryString, parameters);
    }
	
	public List<Modulo> findAllActive() throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select m from Modulo m where m.activo = true";
		return super.getListResultQuery(queryString, parameters);
	}
	
	public List<Modulo> findAllByCategoria(String categoria) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(categoria!=null && !categoria.equals("")){
			String queryString = "Select m from Modulo m where m.activo = true and m.categoria = :categoria";
			parameters.put("categoria", categoria.trim());
			return super.getListResultQuery(queryString, parameters);
		} else {
			return null;
		}
	}
	
	public Modulo findModuloById(Integer idModulo) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select m from Modulo m where m.idModulo = :idmodulo";
		parameters.put("idmodulo", idModulo);
		return super.findOneResultQuery(queryString, parameters);
	}
	
	public Modulo findByNombre(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select m from Modulo m where m.nombre = :nombre";
		parameters.put("nombre", nombre);
		return super.findOneResultQuery(queryString, parameters);
	}
	
	public List<Modulo> findByNombreLike(String searchNombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(searchNombre!=null && !searchNombre.equals("")){
			String queryString = "Select m from Modulo m where m.nombre like :nombre";
			parameters.put("nombre", "%" + searchNombre + "%");
			return super.getListResultQuery(queryString, parameters);
		} else {
			return null;
		}
	}
	
    public void delete(Modulo modulo) throws AlidaPosException {
        super.delete(modulo.getIdModulo(), Modulo.class);
    }
    
    @Override
    public Modulo save(Modulo entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Modulo update(Modulo entity) throws AlidaPosException {
    	return super.update(entity);
    }

    // Falta arreglar esta funcion.
	public List<Modulo> findByNombreCategoria(String searchNombre,
			String searchCategoria) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "SELECT m FROM Modulo m WHERE ";
		if((searchNombre!=null && !searchNombre.trim().equals("")) || 
				(searchCategoria!=null && !searchCategoria.trim().equals("")) ){
			if(searchNombre!=null && !searchNombre.trim().equals("")){
				queryString += " UPPER(m.nombre) like UPPER(:nombre) and";
				parameters.put("nombre", "%" + searchNombre + "%");
			}
			if(searchCategoria!=null && !searchCategoria.trim().equals("")){
				queryString += " UPPER(m.categoria) like UPPER(:categoria) and";
				parameters.put("categoria", "%" + searchCategoria + "%");
			}
			queryString = queryString.substring(0, queryString.length()-3);
			return super.getListResultQuery(queryString, parameters);
		} else {
			return super.findAll();
		}
	}
	
}
