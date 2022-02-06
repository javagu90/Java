package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Empleado;

public class EmpleadoDao extends GenericDao<Empleado> implements Serializable {

	private static final long serialVersionUID = 2944771964001646655L;

	public EmpleadoDao() {
        super(Empleado.class);
    }
	
	public List<Empleado> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Empleado findById(int idEmpleado) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idempleado", idEmpleado);
        return super.findOneResult(Empleado.FIND_BY_ID, parameters);
	}
	
	public List<Empleado> findLikeClave(String clave) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("claveempleado", "%" + clave + "%");
		return super.getListResult(Empleado.FIND_LIKE_CLAVE_EMPLEADO, parameters);
	}
	
    public void delete(Empleado empleado) throws AlidaPosException {
        super.delete(empleado.getIdempleado(), Empleado.class);
    }
    
    @Override
    public Empleado save(Empleado entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Empleado update(Empleado entity) throws AlidaPosException {
    	return super.update(entity);
    }

	public List<Empleado> findAllWithoutUser() throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "SELECT e FROM Empleado e WHERE e.idempleado not in (SELECT u.empleado.idempleado FROM Usuario u)";
		return super.getListResultQuery(queryString, parameters);
	}

	public Empleado findEmpleadoByClave(String claveEmpleado) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select e from Empleado e where ";
		if(claveEmpleado!=null && !claveEmpleado.trim().equals("")){
			queryString += " UPPER(e.claveempleado) = UPPER(:claveempleado) ";
			parameters.put("claveempleado", claveEmpleado);
			return super.findOneResultQuery(queryString, parameters);
		} else {
			return null;
		}
	}

	public List<Empleado> findByNombreClave(String searchNombre,
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
