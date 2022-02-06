package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Perfil;

public class PerfilDao extends GenericDao<Perfil> implements Serializable {

	private static final long serialVersionUID = 2944771964001646655L;

	public PerfilDao() {
        super(Perfil.class);
    }
	
	public List<Perfil> findAll() throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select p from Perfil p";
		return super.getListResultQuery(queryString, parameters);
    }
	
	public List<Perfil> findAllActive() throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select p from Perfil p where p.activo = true";
		return super.getListResultQuery(queryString, parameters);
	}
	
	public Perfil findById(Integer idPerfil) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select p from Perfil p where p.idPerfil = :idperfil";
		parameters.put("idperfil", idPerfil);
		return super.findOneResultQuery(queryString, parameters);
	}
	
	public Perfil findPerfilByNombre(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select p from Perfil p where UPPER(p.nombre) = UPPER(:nombre)";
		parameters.put("nombre", nombre);
		return super.findOneResultQuery(queryString, parameters);
	}
	
	public List<Perfil> findByNombreLike(String searchNombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(searchNombre!=null && !searchNombre.equals("")){
			String queryString = "Select p from Perfil p where UPPER(p.nombre) like UPPER(:nombre)";
			parameters.put("nombre", "%" + searchNombre + "%");
			return super.getListResultQuery(queryString, parameters);
		} else {
			return findAll();
		}
	}
	
    public void delete(Perfil perfil) throws AlidaPosException {
        super.delete(perfil.getIdPerfil(), Perfil.class);
    }
    
    @Override
    public Perfil save(Perfil entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Perfil update(Perfil entity) throws AlidaPosException {
    	return super.update(entity);
    }

    // Falta arreglar esta funcion.
	public List<Perfil> findByNombreClave(String searchNombre,
			String searchClave) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "SELECT e FROM Empleado e WHERE ";
		if((searchNombre!=null && !searchNombre.trim().equals("")) || 
				(searchClave!=null && !searchClave.trim().equals("")) ){
			if(searchNombre!=null && !searchNombre.trim().equals("")){
				queryString += " UPPER(e.persona.nombre) like UPPER(:nombre) and";
				parameters.put("nombre", "%" + searchNombre + "%");
			}
			if(searchClave!=null && !searchClave.trim().equals("")){
				queryString += " UPPER(e.claveempleado) like UPPER(:clave) and";
				parameters.put("clave", "%" + searchClave + "%");
			}
			queryString = queryString.substring(0, queryString.length()-3);
			return super.getListResultQuery(queryString, parameters);
		} else {
			return super.findAll();
		}
	}
	
}
