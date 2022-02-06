package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Cliente;

public class ClienteDao extends GenericDao<Cliente> implements Serializable {

	private static final long serialVersionUID = -7708247473910004670L;

	public ClienteDao() {
        super(Cliente.class);
    }
	
	public List<Cliente> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Cliente findById(int idCliente) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idcliente", idCliente);
        return super.findOneResult(Cliente.FIND_BY_ID, parameters);
	}
	
	public List<Cliente> findLikeClave(String clave) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("clavecliente", "%" + clave + "%");
		return super.getListResult(Cliente.FIND_LIKE_CLAVE_CLIENTE, parameters);
	}
	
    public void delete(Cliente cliente) throws AlidaPosException {
        super.delete(cliente.getIdcliente(), Cliente.class);
    }
    
    @Override
    public Cliente save(Cliente entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Cliente update(Cliente entity) throws AlidaPosException {
    	return super.update(entity);
    }

	public Cliente findClienteByClave(String claveCliente) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select c from Cliente c where ";
		if(claveCliente!=null && !claveCliente.trim().equals("")){
			queryString += " UPPER(c.clavecliente) = UPPER(:clavecliente) ";
			parameters.put("clavecliente", claveCliente);
			return super.findOneResultQuery(queryString, parameters);
		} else {
			return null;
		}
	}
	
	public List<Cliente> findByNombreClaveCategoria(String searchNombre,
			String searchClave, String searchCategoria) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "SELECT c FROM Cliente c WHERE ";
		if((searchNombre!=null && !searchNombre.trim().equals("")) || 
				(searchClave!=null && !searchClave.trim().equals("")) || 
				(searchCategoria!=null && !searchCategoria.trim().equals("")) ){
			if(searchNombre!=null && !searchNombre.trim().equals("")){
				queryString += " UPPER(c.persona.nombre) like UPPER(:nombre) and";
				parameters.put("nombre", "%" + searchNombre + "%");
			}
			if(searchClave!=null && !searchClave.trim().equals("")){
				queryString += " UPPER(c.clavecliente) like UPPER(:clave) and";
				parameters.put("clave", "%" + searchClave + "%");
			}
			if(searchCategoria!=null && !searchCategoria.trim().equals("")){
				queryString += " UPPER(c.categoriacliente.nombre) like UPPER(:categoriacliente) and";
				parameters.put("categoriacliente", "%" + searchCategoria + "%");
			}
			queryString = queryString.substring(0, queryString.length()-3);
			return super.getListResultQuery(queryString, parameters);
		} else {
			return super.findAll();
		}
	}

}
