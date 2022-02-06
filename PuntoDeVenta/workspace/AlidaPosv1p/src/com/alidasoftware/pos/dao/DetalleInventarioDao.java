package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Detalleinventario;

public class DetalleInventarioDao extends GenericDao<Detalleinventario> implements Serializable {

	private static final long serialVersionUID = -1998692216930512740L;

	public DetalleInventarioDao() {
        super(Detalleinventario.class);
    }
	
	public List<Detalleinventario> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Detalleinventario findById(int idDetalleinventario)  throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("iddetalleinventario", idDetalleinventario);
        return super.findOneResult(Detalleinventario.FIND_BY_ID, parameters);
	}
	
	public Detalleinventario findByProducto(int idProduct) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idproducto", idProduct);
		return super.findOneResult(Detalleinventario.FIND_BY_ID_PRODUCTO, parameters);
	}
	
    public void delete(Detalleinventario detalleInventario) throws AlidaPosException {
        super.delete(detalleInventario.getIddetalleinventario(), Detalleinventario.class);
    }
    
    @Override
    public Detalleinventario save(Detalleinventario entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Detalleinventario update(Detalleinventario entity) throws AlidaPosException {
    	return super.update(entity);
    }
    
    public Detalleinventario findByProductoInventario(int idProducto, int idInventario) throws AlidaPosException {
    	Map<String, Object> parameters = new HashMap<String, Object>();
    	String queryString = "select d from Detalleinventario d where d.producto.idproducto = :idproducto and d.inventario.idinventario = :idinventario";
    	parameters.put("idproducto", idProducto);
    	parameters.put("idinventario", idInventario);
    	return super.findOneResultQuery(queryString, parameters);
    }


}
