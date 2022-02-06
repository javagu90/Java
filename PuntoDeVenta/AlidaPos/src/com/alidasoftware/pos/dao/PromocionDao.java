package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Promocion;

public class PromocionDao extends GenericDao<Promocion> implements Serializable {

	private static final long serialVersionUID = 7366438615639525608L;
	
	public PromocionDao() {
        super(Promocion.class);
    }
	
	public List<Promocion> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Promocion findbyId(int idPromocion) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idpromocion", idPromocion);
        return super.findOneResult(Promocion.FIND_BY_ID, parameters);
	}
	
	public Promocion findByIdProduct(int idProduct) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idproducto", idProduct);
        return super.findOneResult(Promocion.FIND_BY_ID_PRODUCT, parameters);
	}
	
	public List<Promocion> findByIdProductTiendaFecha(int idProduct, int idTienda) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();		
        parameters.put("idproducto", idProduct);
        parameters.put("idtienda", idTienda);
        return super.getListResult(Promocion.FIND_BY_ID_PRODUCT_STORE_DATE, parameters);
	}
	
	public List<Promocion> findByCategoriaTiendaFecha(int idProduct, int idTienda) throws AlidaPosException {
	    String queryString = "SELECT p FROM Promocion p WHERE p.idpromocion in "
						+ " (select distinct idpromocion from categoriasconpromocion where idcategoriaproducto in ( select idcategoriaproducto from categoriasdeproducto where idproducto = :idproducto)) AND p.tienda.idtienda = :idtienda AND p.activa = true AND p.fechainicio <= CURRENT_DATE AND p.fechafin >= CURRENT_DATE ";
	    Map<String, Object> parameters = new HashMap<String, Object>();		
        parameters.put("idproducto", idProduct);
        parameters.put("idtienda", idTienda);
        return super.getListResultQuery(queryString, parameters);
	}
	
    public void delete(Promocion promocion) throws AlidaPosException {
        super.delete(promocion.getIdpromocion(), Promocion.class);
    }
    
    @Override
    public Promocion save(Promocion entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Promocion update(Promocion entity) throws AlidaPosException {
    	return super.update(entity);
    }
    
    public Promocion findPromocionByNombre(String nombre) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "Select p from Promocion p where ";
		if(nombre!=null && !nombre.trim().equals("")){
			queryString += " UPPER(p.nombre) = UPPER(:nombre) ";
			parameters.put("nombre", nombre);
			return super.findOneResultQuery(queryString, parameters);
		} else {
			return null;
		}
	}

	public List<Promocion> findByNombreDescuentoEstatus(String searchNombre,
			String searchDescuento, String searchEstatus) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		String queryString = "SELECT p FROM Promocion p WHERE ";
		if((searchNombre!=null && !searchNombre.trim().equals("")) || 
				(searchDescuento!=null && !searchDescuento.trim().equals("")) || 
				(searchEstatus!=null && !searchEstatus.trim().equals("")) ){
			if(searchNombre!=null && !searchNombre.trim().equals("")){
				queryString += " UPPER(p.nombre) like UPPER(:nombre) and";
				parameters.put("nombre", "%" + searchNombre + "%");
			}
			if(searchDescuento!=null && !searchDescuento.trim().equals("")){
				queryString += " p.descuento = :descuento and";
				parameters.put("descuento", (Float.valueOf(searchDescuento) / 100));
			}
			if(searchEstatus!=null && !searchEstatus.trim().equals("")){
				if(searchEstatus.equals("1")){
					Date today = new Date();
					queryString += " :today between p.fechainicio and p.fechafin and";
					parameters.put("today", today);
				} else if (searchEstatus.equals("2")){
					Date today = new Date();
					queryString += " :today not between p.fechainicio and p.fechafin and";
					parameters.put("today", today);
				}
			}
			queryString = queryString.substring(0, queryString.length()-3);
			return super.getListResultQuery(queryString, parameters);
		} else {
			return super.findAll();
		}
	}


}
