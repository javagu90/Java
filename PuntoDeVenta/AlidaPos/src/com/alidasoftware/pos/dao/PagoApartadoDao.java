package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;








import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.helper.PagoApartadoHelper;
import com.alidasoftware.pos.helper.PagoVentaHelper;
import com.alidasoftware.pos.model.Pagoapartado;

public class PagoApartadoDao extends GenericDao<Pagoapartado> implements Serializable {

	private static final long serialVersionUID = 188503192775419034L;

	public PagoApartadoDao() {
        super(Pagoapartado.class);
    }
	
	public List<Pagoapartado> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	@SuppressWarnings("rawtypes")
	public List getMaxId() throws AlidaPosException
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		//parameters.put("idpago", 1);
		System.out.println("PARAMETERS: "+ parameters);
		//List result= super.getListResultNative(Pagoapartado.GET_MAX_ID, parameters);
		List result= super.getListResultNative("SELECT MAX(p.idpago) as ultimopagoapartado FROM Pagoapartado p", parameters);
		return result;
	}
	
	public Pagoapartado findbyId(int idPago) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idpago", idPago);
        return super.findOneResult(Pagoapartado.FIND_BY_ID, parameters);
	}
	
	public List<Pagoapartado> findByIdApartado(int idApartado) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idapartado", idApartado);
		return super.getListResult(Pagoapartado.FIND_BY_ID_APARTADO, parameters);
	}
	
    public void delete(Pagoapartado pagoApartado) throws AlidaPosException {
        super.delete(pagoApartado.getIdpago(), Pagoapartado.class);
    }
    
    @Override
    public Pagoapartado save(Pagoapartado entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Pagoapartado update(Pagoapartado entity) throws AlidaPosException {
    	return super.update(entity);
    }
    
    @SuppressWarnings("unchecked")
    public List<PagoApartadoHelper> listBySumFormaPago(int ultimobtenido, int ultimorealizado, Date fecha, int idCaja) throws AlidaPosException {
     List<Object> parameters = new ArrayList<Object>();
     parameters.add(ultimobtenido);
     parameters.add(ultimorealizado);
     parameters.add(fecha);
     parameters.add(idCaja);
		String queryString = "select pa.idformapago, sum(pa.cantidad) as cantidad  "
				+ "from Pagoapartado pa "
				+ "where pa.idpago between ? AND ? "
				+ "AND pa.fecha = ? "
				+ "AND pa.idcaja = ? "
				+ "GROUP BY pa.idformapago";
     List<PagoApartadoHelper> result = super.getListResultNative(queryString, PagoApartadoHelper.class, parameters);  
     for (int i = 0; i < result.size(); i++) {
      System.out.println("id      : " + result.get(i).getIdFormaPago());
      System.out.println("cantidad: " + result.get(i).getCantidad());
     }  
     return result;
    }
    
	@SuppressWarnings("unchecked")
	public List<PagoApartadoHelper> listByFirstCP(int ultimorealizado, Date fecha, int idCaja) throws AlidaPosException {
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(ultimorealizado);
		parameters.add(fecha);
		parameters.add(idCaja);
		String queryString = "select pa.idformapago, sum(pa.cantidad) as cantidad  "
				+ "from Pagoapartado pa "
				+ "where pa.idpago <= ? "
				+ "AND pa.fecha = ? "
				+ "AND pa.idcaja = ? "
				+ "GROUP BY pa.idformapago";
		
		List<PagoApartadoHelper> result = super.getListResultNative(queryString, PagoApartadoHelper.class, parameters);		
		for (int i = 0; i < result.size(); i++) {
			System.out.println("id      : " + result.get(i).getIdFormaPago());
			System.out.println("cantidad: " + result.get(i).getCantidad());
		}		
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getMaxIdUltimoPagoApartadoOfFirstCP(Date fecha, int idCaja) throws AlidaPosException
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("fecha", fecha);
		parameters.put("idcaja", idCaja);
		String queryString = "SELECT MAX(pa.idpago) from pagoapartado pa where pa.idpago <= (select max (p.idpago) from pagoapartado p) and pa.idcaja = idCaja  and pa.fecha = fecha ";
		List result = super.getListResultNative(queryString,  parameters);		
		for (int i = 0; i < result.size(); i++) {
			System.out.println("MAXId      : " + result.get(i));
		}		
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public List getMaxIdUltimoPagoApartado( int idpago, Date fecha, int idCaja) throws AlidaPosException
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idpago",idpago );
		parameters.put("idcaja", idCaja);
		parameters.put("fecha", fecha);
		String queryString = "SELECT MAX(idpago) from pagoapartado where idpago between "
				+ "(select max (idpago) "
				+ "from pagoapartado) and idpago and idcaja = idCaja and fecha = fecha ";		
		List result = super.getListResultNative(queryString, parameters);		
		for (int i = 0; i < result.size(); i++) {
			System.out.println("MAXId      : " + result.get(i));
		}		
		return result;
	}
	
}
