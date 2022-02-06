package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Producto;
import com.alidasoftware.pos.model.Tienda;

public class ProductoDao extends GenericDao<Producto> implements Serializable {

	private static final long serialVersionUID = -137099196377554435L;

	public ProductoDao() {
        super(Producto.class);
    }
	
	public List<Producto> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Producto findbyId(int idProducto) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idproducto", idProducto);
        return super.findOneResult(Producto.FIND_BY_ID, parameters);
	}
	
	public Producto findByName(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nombre", nombre);
		return super.findOneResult(Producto.FIND_BY_NOMBRE, parameters);
	}
	
	public List<Producto> findLikeName(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nombre", "%" + nombre + "%");
		return super.getListResult(Producto.FIND_LIKE_NOMBRE, parameters);
	}
	
	public List<Producto> findLikeNameTienda(String nombre, Tienda tienda) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("nombre", "%" + nombre + "%");
		parameters.put("tienda", tienda);
		List<Producto> result = super.getListResult(Producto.FIND_LIKE_NOMBRE_TIENDA, parameters);
		return result;
				
	}
	
    public void delete(Producto producto) throws AlidaPosException {
        super.delete(producto.getIdproducto(), Producto.class);
    }
    
    @Override
    public Producto save(Producto entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Producto update(Producto entity) throws AlidaPosException {
    	return super.update(entity);
    }
    
	public Producto findProductoByNombre(String nombreProducto) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select p from Producto p where ";
		if(nombreProducto!=null && !nombreProducto.trim().equals("")){
			queryString += " UPPER(p.nombre) = UPPER(:nombreProducto) ";
			parameters.put("nombreProducto", nombreProducto);
			return super.findOneResultQuery(queryString, parameters);
		} else {
			return null;
		}
	}
    
    public List<Producto> findByNombreClaveCategoria(String searchNombre,
			String searchClave, String searchCategoria) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "SELECT p FROM Producto p WHERE ";
		if((searchNombre!=null && !searchNombre.trim().equals("")) || 
				(searchClave!=null && !searchClave.trim().equals("")) ){
			if(searchNombre!=null && !searchNombre.trim().equals("")){
				queryString += " UPPER(p.nombre) like UPPER(:nombre) and";
				parameters.put("nombre", "%" + searchNombre + "%");
			}
			if(searchClave!=null && !searchClave.trim().equals("")){
				queryString += " UPPER(p.clave) like UPPER(:clave) and";
				parameters.put("clave", "%" + searchClave + "%");
			}
			queryString = queryString.substring(0, queryString.length()-3);
			return super.getListResultQuery(queryString, parameters);
		} else {
			return super.findAll();
		}
	}

	public Producto findProductoByClave(String claveProducto) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select p from Producto p where ";
		if(claveProducto!=null && !claveProducto.trim().equals("")){
			queryString += " UPPER(p.clave) = UPPER(:claveProducto) ";
			parameters.put("claveProducto", claveProducto);
			return super.findOneResultQuery(queryString, parameters);
		} else {
			return null;
		}
	}


}
