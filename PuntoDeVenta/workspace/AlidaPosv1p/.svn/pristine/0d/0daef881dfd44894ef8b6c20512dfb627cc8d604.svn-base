package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Tipovencimiento;

public class TipoVencimientoDao extends GenericDao<Tipovencimiento> implements Serializable {

	private static final long serialVersionUID = 7366438615639525608L;
	
	public TipoVencimientoDao() {
        super(Tipovencimiento.class);
    }
	
	public List<Tipovencimiento> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Tipovencimiento findbyId(int idTipovencimiento) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idtipovencimiento", idTipovencimiento);
        return super.findOneResult(Tipovencimiento.FIND_BY_ID, parameters);
	}
	
	public List<Tipovencimiento> findByStatus(boolean activo) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("activo", activo);
		return super.getListResult(Tipovencimiento.FIND_BY_STATUS, parameters);
	}
	
    public void delete(Tipovencimiento tipoeVencimiento) throws AlidaPosException {
        super.delete(tipoeVencimiento.getIdtipovencimiento(), Tipovencimiento.class);
    }
    
    @Override
    public Tipovencimiento save(Tipovencimiento entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Tipovencimiento update(Tipovencimiento entity) throws AlidaPosException {
    	return super.update(entity);
    }

	public Tipovencimiento findByNombre(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select c from Tipovencimiento c where ";
		if(nombre!=null && !nombre.trim().equals("")){
			queryString += " UPPER(c.nombre) = UPPER(:nombre) ";
			parameters.put("nombre", nombre);
			return super.findOneResultQuery(queryString, parameters);
		} else {
			return null;
		}	
	}

	public List<Tipovencimiento> searchByFilters(String searchNombre,
			String searchDescription, String searchTipoDias) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select c from Tipovencimiento c where ";
		if( (searchNombre!=null && !searchNombre.trim().equals(""))
				|| (searchDescription!=null && !searchDescription.trim().equals("")) 
				|| (searchTipoDias!=null && !searchTipoDias.trim().equals("")) ) {
			if(searchNombre!=null && !searchNombre.trim().equals("")){
				queryString += " UPPER(c.nombre) like UPPER(:nombre) and";
				parameters.put("nombre", "%" + searchNombre + "%");
			}
			if(searchDescription!=null && !searchDescription.trim().equals("")){
				queryString += " UPPER(c.descripcion) like UPPER(:descripcion) and";
				parameters.put("descripcion", "%" + searchDescription + "%");
			}
			if(searchTipoDias!=null && !searchTipoDias.trim().equals("")){
				queryString += " c.tipodias = :tipodias and";
				parameters.put("tipodias", searchTipoDias);
			}
			queryString = queryString.substring(0, queryString.length()-3);
			return super.getListResultQuery(queryString, parameters);
		} else {
			return super.findAll();
		}
	}

}
