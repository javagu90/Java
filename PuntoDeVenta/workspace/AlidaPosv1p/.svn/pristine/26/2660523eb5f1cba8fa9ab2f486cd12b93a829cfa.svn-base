package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.AdeudoVenta;

public class AdeudoVentaDao extends GenericDao<AdeudoVenta> implements Serializable {

	private static final long serialVersionUID = -1813748499354653305L;

	public AdeudoVentaDao() {
        super(AdeudoVenta.class);
    }
	
	public List<AdeudoVenta> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public AdeudoVenta findByFolioIdModule(int idOperacion, String folio, String module) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idoperacion", idOperacion);
		parameters.put("foliooperacion", folio);
		parameters.put("module", module);
		return super.findOneResult(AdeudoVenta.FIND_BY_FOLIO_ID_OPERACION, parameters);
	}
	
	public void delete(AdeudoVenta adeudoVenta) throws AlidaPosException {
        super.delete(adeudoVenta.getIdadeudoventa(), AdeudoVenta.class);
    }
    
    @Override
    public AdeudoVenta save(AdeudoVenta entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public AdeudoVenta update(AdeudoVenta entity) throws AlidaPosException {
    	return super.update(entity);
    }
	
}
