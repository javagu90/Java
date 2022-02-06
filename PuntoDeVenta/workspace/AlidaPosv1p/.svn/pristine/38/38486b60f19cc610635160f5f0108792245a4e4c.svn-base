package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Categoriaproducto;

public class CategoriaProductoDao extends GenericDao<Categoriaproducto> implements Serializable {

	private static final long serialVersionUID = 161636410478577761L;

	public CategoriaProductoDao() {
        super(Categoriaproducto.class);
    }
	
	public List<Categoriaproducto> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Categoriaproducto findById(int idCategoriaproducto) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idcategoriaproducto", idCategoriaproducto);
        return super.findOneResult(Categoriaproducto.FIND_BY_ID, parameters);
	}
	
    public void delete(Categoriaproducto categoriaProducto) throws AlidaPosException {
        super.delete(categoriaProducto.getIdcategoriaproducto(), Categoriaproducto.class);
    }
    
    @Override
    public Categoriaproducto save(Categoriaproducto entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Categoriaproducto update(Categoriaproducto entity) throws AlidaPosException {
    	return super.update(entity);
    }
    
    public List<Categoriaproducto> searchByNombre(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select c from Categoriaproducto c where ";
		if(nombre!=null && !nombre.trim().equals("")){
			queryString += " UPPER(c.nombre) like UPPER(:nombre) ";
			parameters.put("nombre", "%" + nombre + "%");
		} else {
			return super.findAll();
		}		
		return super.getListResultQuery(queryString, parameters);
	}
    
    public Categoriaproducto findByNombre(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select c from Categoriaproducto c where ";
		if(nombre!=null && !nombre.trim().equals("")){
			queryString += " UPPER(c.nombre) = UPPER(:nombre) ";
			parameters.put("nombre", nombre);
			return super.findOneResultQuery(queryString, parameters);
		} else {
			return null;
		}		
	}

	public List<Categoriaproducto> searchByNombreDescription(
			String searchNombre, String searchDescription) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select c from Categoriaproducto c where ";
		if( (searchNombre!=null && !searchNombre.trim().equals(""))
				|| (searchDescription!=null && !searchDescription.trim().equals("")) ) {
			if(searchNombre!=null && !searchNombre.trim().equals("")){
				queryString += " UPPER(c.nombre) like UPPER(:nombre) and";
				parameters.put("nombre", "%" + searchNombre + "%");
			}
			if(searchDescription!=null && !searchDescription.trim().equals("")){
				queryString += " UPPER(c.descripcion) like UPPER(:descripcion) and";
				parameters.put("descripcion", "%" + searchDescription + "%");
			}
			queryString = queryString.substring(0, queryString.length()-3);
			return super.getListResultQuery(queryString, parameters);
		} else {
			return super.findAll();
		}		
	} 

}
