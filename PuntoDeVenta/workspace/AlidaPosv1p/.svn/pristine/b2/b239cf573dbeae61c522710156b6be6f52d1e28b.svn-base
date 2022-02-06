package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Proveedor;

public class ProveedorDao extends GenericDao<Proveedor> implements Serializable {

	private static final long serialVersionUID = -508930642421552903L;

	public ProveedorDao() {
        super(Proveedor.class);
    }
	
	public List<Proveedor> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Proveedor findbyId(int idProveedor) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idproveedor", idProveedor);
        return super.findOneResult(Proveedor.FIND_BY_ID, parameters);
	}
	
    public void delete(Proveedor proveedor) throws AlidaPosException {
        super.delete(proveedor.getIdproveedor(), Proveedor.class);
    }
    
    @Override
    public Proveedor save(Proveedor entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Proveedor update(Proveedor entity) throws AlidaPosException {
    	return super.update(entity);
    }
    
	public Proveedor findProveedorByClave(String claveProveedor) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select p from Proveedor p where ";
		if(claveProveedor!=null && !claveProveedor.trim().equals("")){
			queryString += " UPPER(p.claveproveedor) = UPPER(:claveproveedor) ";
			parameters.put("claveproveedor", claveProveedor);
			return super.findOneResultQuery(queryString, parameters);
		} else {
			return null;
		}
	}
	
	public List<Proveedor> findByNombreClave(String searchNombre,
			String searchClave) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "SELECT p FROM Proveedor p WHERE ";
		if((searchNombre!=null && !searchNombre.trim().equals("")) || 
				(searchClave!=null && !searchClave.trim().equals("")) ){
			if(searchNombre!=null && !searchNombre.trim().equals("")){
				queryString += " UPPER(p.persona.nombre) like UPPER(:nombre) and";
				parameters.put("nombre", "%" + searchNombre + "%");
			}
			if(searchClave!=null && !searchClave.trim().equals("")){
				queryString += " UPPER(p.claveproveedor) like UPPER(:clave) and";
				parameters.put("clave", "%" + searchClave + "%");
			}
			queryString = queryString.substring(0, queryString.length()-3);
			return super.getListResultQuery(queryString, parameters);
		} else {
			return super.findAll();
		}
	}


}
