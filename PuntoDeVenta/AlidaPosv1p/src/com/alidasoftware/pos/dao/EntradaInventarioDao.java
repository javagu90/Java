package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Entradainventario;

public class EntradaInventarioDao extends GenericDao<Entradainventario> implements Serializable {

	private static final long serialVersionUID = 3613554041984556017L;

	public EntradaInventarioDao() {
        super(Entradainventario.class);
    }
	
	public List<Entradainventario> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Entradainventario findbyId(int idEntradainventario) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("identradainventario", idEntradainventario);
        return super.findOneResult(Entradainventario.FIND_BY_ID, parameters);
	}
	
    public void delete(Entradainventario entradaInventario) throws AlidaPosException {
        super.delete(entradaInventario.getIdentradainventario(), Entradainventario.class);
    }
    
    @Override
    public Entradainventario save(Entradainventario entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Entradainventario update(Entradainventario entity) throws AlidaPosException {
    	return super.update(entity);
    }



}
