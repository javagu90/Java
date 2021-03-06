package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.helper.PagoApartadoHelper;
import com.alidasoftware.pos.model.Corteparcial;
import com.alidasoftware.pos.model.Detallecorteparcial;

public class DetalleCorteParcialDao extends GenericDao<Detallecorteparcial> implements Serializable {

	private static final long serialVersionUID = -4283976202639997824L;

	public DetalleCorteParcialDao() {
        super(Detallecorteparcial.class);
    }
	
	public List<Detallecorteparcial> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	public Detallecorteparcial findbyId(int idDetallecorteparcial) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("iddetallecorteparcial", idDetallecorteparcial);
        return super.findOneResult(Detallecorteparcial.FIND_BY_ID, parameters);
	}
	
	public List<Detallecorteparcial> findAllbyIdCorteParcial(Corteparcial cp) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idcorteparcial", cp);
        return super.getListResult(Detallecorteparcial.FIND_BY_ID_CORTE_PARCIAL, parameters);
	}
	
    public void delete(Detallecorteparcial detalleCorteParcial) throws AlidaPosException {
        super.delete(detalleCorteParcial.getIddetallecorteparcial(), Detallecorteparcial.class);
    }
    
    @Override
    public Detallecorteparcial save(Detallecorteparcial entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Detallecorteparcial update(Detallecorteparcial entity) throws AlidaPosException {
    	return super.update(entity);
    }

  /*  
    @SuppressWarnings("unchecked")
    public List<Object> listBySumDetalleCP(int idCorteCaja) throws AlidaPosException {
     //List<Object> parameters = new ArrayList<Object>();
    Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("idcortecaja",idCorteCaja);
	 String queryString = "Select d.idformapago, sum(d.cantidad)  "
	 		+ "from detallecorteparcial d "
	 		+ "where d.idcorteparcial "
	 		+ "in (Select idcorteparcial from corteparcial where idcortecaja = ? ) "
	 		+ "group by d.idformapago";
     List<Object> result = super.getListResultNative(queryString, parameters);  
     for (int i = 0; i < result.size(); i++) {
      System.out.println("id      : " + result.get(i));
     // System.out.println("cantidad: " + result.get(i).getCantidad());
     }  
     return result;
    }
*/
    public Detallecorteparcial findByIdCorteParcial(int idCorteParcial) throws AlidaPosException
    {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idcorteparcial", idCorteParcial);
    	return super.findOneResult(Detallecorteparcial.FIND_BY_ID_CORTE_PARCIAL, parameters);
    }
    
}
