package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Precioventa;

public class PrecioVentaDao extends GenericDao<Precioventa> implements Serializable {
	
	private static final long serialVersionUID = 1993326175517776463L;

	public PrecioVentaDao() {
        super(Precioventa.class);
    }
	
	public List<Precioventa> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Precioventa findById(int idPrecioventa) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idprecioventa", idPrecioventa);
        return super.findOneResult(Precioventa.FIND_BY_ID, parameters);
	}
	
	public Precioventa findByIdProduct(int idProduct) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idproducto", idProduct);
        return super.findOneResult(Precioventa.FIND_BY_ID_PRODUCTO, parameters);
	}
	
	public List<Precioventa> findByIdProductQuantity(int idProduct, float quantity) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idproducto", idProduct);
        parameters.put("cantidadproductos", quantity);        
        return super.getListResult(Precioventa.FIND_BY_ID_PRODUCTO_CANTIDAD, parameters);
	}
	
    public void delete(Precioventa precioVenta) throws AlidaPosException {
        super.delete(precioVenta.getIdprecioventa(), Precioventa.class);
    }
    
    @Override
    public Precioventa save(Precioventa entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Precioventa update(Precioventa entity) throws AlidaPosException {
    	return super.update(entity);
    }


}
