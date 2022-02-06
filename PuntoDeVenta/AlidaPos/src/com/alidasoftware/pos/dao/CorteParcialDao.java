package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Corteparcial;

public class CorteParcialDao extends GenericDao<Corteparcial> implements Serializable {

	private static final long serialVersionUID = 4867836340818775L;

	public CorteParcialDao() {
        super(Corteparcial.class);
    }
	
	public List<Corteparcial> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Corteparcial findbyId(int idCorteparcial) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idcorteparcial", idCorteparcial);
        return super.findOneResult(Corteparcial.FIND_BY_ID, parameters);
	}
	
    public void delete(Corteparcial corteParcial) throws AlidaPosException {
        super.delete(corteParcial.getIdcorteparcial(), Corteparcial.class);
    }
        
    
    @Override
    public Corteparcial save(Corteparcial entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Corteparcial update(Corteparcial entity) throws AlidaPosException {
    	return super.update(entity);
    }
    
 
    public List<Corteparcial> findByFecha(Date fecha) throws AlidaPosException, ParseException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("fecha", fecha);
        String queryString = "Select cp from Corteparcial cp where cp.fecha = :fecha";
        return super.getListResultQuery(queryString, parameters);
     
   }
    
	@SuppressWarnings("rawtypes")
	public List getUltimoPagoVentaofCaja( int idCorteCaja) throws AlidaPosException
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idcortecaja", idCorteCaja);
		String queryString = "Select Max(ultimopagoventa) from corteparcial where idcortecaja= idCorteCaja";		
		List result = super.getListResultNative(queryString, parameters);		
		for (int i = 0; i < result.size(); i++) {
			System.out.println("MAXId      : " + result.get(i));
		}		
		return result;
	}    
    
	@SuppressWarnings("rawtypes")
	public List getUltimoPagoApartadoofCaja( int idCorteCaja) throws AlidaPosException
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idcortecaja", idCorteCaja);
		String queryString = "Select Max(ultimopagoapartado) from corteparcial where idcortecaja= idCorteCaja";		
		List result = super.getListResultNative(queryString, parameters);		
		for (int i = 0; i < result.size(); i++) {
			System.out.println("MAXId      : " + result.get(i));
		}		
		return result;
	}    
}
