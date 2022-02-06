package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Usuario;

public class UsuarioDao extends GenericDao<Usuario> implements Serializable {

	private static final long serialVersionUID = -8627811956399124234L;

	public UsuarioDao() {
		super(Usuario.class);
	}
	public List<Usuario> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Usuario findById(int idUsuario) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idusuario", idUsuario);
        return super.findOneResult(Usuario.FIND_BY_ID, parameters);
	}
	
	public Usuario findByNamePwd(String userName, String userPwd) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("clave", userName);
		parameters.put("claveacceso", userPwd);
		return super.findOneResult(Usuario.FIND_BY_NAME_PWD, parameters);
	}
	
    public void delete(Usuario usuario) throws AlidaPosException {
        super.delete(usuario.getIdusuario(), Usuario.class);
    }
    
    @Override
    public Usuario save(Usuario entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Usuario update(Usuario entity) throws AlidaPosException {
    	return super.update(entity);
    }
	public Usuario findByClave(String searchClave) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select u from Usuario u where ";
		if(searchClave!=null && !searchClave.trim().equals("")){
			queryString += " UPPER(u.clave) = UPPER(:clave) ";
			parameters.put("clave", searchClave);
			return super.findOneResultQuery(queryString, parameters);
		} else {
			return null;
		}		
	}
	public List<Usuario> findByNombreUsuario(String searchNombre,
			String searchUsuario) throws AlidaPosException{
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "SELECT u FROM Usuario u WHERE ";
		if((searchNombre!=null && !searchNombre.trim().equals("")) || 
				(searchUsuario!=null && !searchUsuario.trim().equals("")) ){
			if(searchNombre!=null && !searchNombre.trim().equals("")){
				queryString += " UPPER(u.empleado.persona.nombre) like UPPER(:nombre) and";
				parameters.put("nombre", "%" + searchNombre + "%");
			}
			if(searchUsuario!=null && !searchUsuario.trim().equals("")){
				queryString += " UPPER(u.clave) like UPPER(:clave) and";
				parameters.put("clave", "%" + searchUsuario + "%");
			}
			queryString = queryString.substring(0, queryString.length()-3);
			return super.getListResultQuery(queryString, parameters);
		} else {
			return super.findAll();
		}
	}

}
