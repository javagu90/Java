package com.alidasoftware.pos.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.helper.PagoVentaHelper;
import com.alidasoftware.pos.model.Pagoventa;

public class PagoVentaDao extends GenericDao<Pagoventa> implements Serializable {

	private static final long serialVersionUID = 188503192775419034L;

	public PagoVentaDao() {
        super(Pagoventa.class);
    }
	
	public List<Pagoventa> findAll() throws AlidaPosException {
        return super.findAll();
    }
	
	@SuppressWarnings("rawtypes")
	public List getMaxId() throws AlidaPosException
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		//parameters.put("idpago", 1);
		//System.out.println("PARAMETERS: "+ parameters);
		//List result= super.getListResultNative(Pagoapartado.GET_MAX_ID, parameters);
		List result= super.getListResultNative("SELECT MAX(p.idpago) as ultimopagoventa FROM Pagoventa p", parameters);
		return result;
	}
	
	public Pagoventa findbyId(int idPago) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idpago", idPago);
        return super.findOneResult(Pagoventa.FIND_BY_ID, parameters);
	}
	
	public List<Pagoventa> findByIdVenta(int idVenta) throws AlidaPosException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idventa", idVenta);
		return super.getListResult(Pagoventa.FIND_BY_ID_VENTA, parameters);
	}
	
	@SuppressWarnings("unchecked")
	public List<PagoVentaHelper> listBySumFormaPago(int ultimobtenido, int ultimorealizado, Date fecha, int idCaja) throws AlidaPosException {
		
		System.out.println("LISTA DE PAGO VENTA FUNCION DE SUMATORIA LLAMADA");
		System.out.println("ULTIMO OBTENIDO "+ultimobtenido);
		System.out.println("ULTIMO REALIZADO "+ultimorealizado);
		System.out.println("FECHA: "+ fecha);
		System.out.println("ID CAJA: "+ idCaja);
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(ultimobtenido);
		parameters.add(ultimorealizado);
		parameters.add(fecha);
		parameters.add(idCaja);
		String queryString = "select pv.idformapago, sum(pv.cantidad) as cantidad  "
				+ "from Pagoventa pv "
				+ "where pv.idpago between ? AND ? "
				+ "AND pv.fecha = ? "
				+ "AND pv.idcaja = ? "
				+ "GROUP BY pv.idformapago";		
		List<PagoVentaHelper> result = super.getListResultNative(queryString, PagoVentaHelper.class, parameters);
		System.out.println("TERMINE LA CONSULTA");
		for (int i = 0; i < result.size(); i++) {
			System.out.println("id      : " + result.get(i).getIdFormaPago());
			System.out.println("cantidad: " + result.get(i).getCantidad());
		}		
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getMaxIdUltimoPagoVentaOfFirstCP(Date fecha, int idCaja) throws AlidaPosException
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("fecha", fecha);
		parameters.put("idcaja", idCaja);
		String queryString = "SELECT MAX(pv.idpago) from pagoventa pv where pv.idpago <= (select max (p.idpago) from pagoventa p) and pv.idcaja = idCaja  and pv.fecha = fecha ";
		List result = super.getListResultNative(queryString,  parameters);		
		for (int i = 0; i < result.size(); i++) {
			System.out.println("MAXId      : " + result.get(i));
		}		
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public List getMaxIdUltimoPagoVenta( int idpago, Date fecha, int idCaja) throws AlidaPosException
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idpago", idpago );
		parameters.put("idcaja", idCaja);
		parameters.put("fecha", fecha);
		String queryString = "SELECT MAX(idpago) from pagoventa where idpago between "
				+ "(select max (idpago) "
				+ "from pagoventa) and idpago "
				+ "and idcaja = idCaja and fecha = fecha ";		
		List result = super.getListResultNative(queryString, parameters);		
		for (int i = 0; i < result.size(); i++) {
			System.out.println("MAXId      : " + result.get(i));
		}		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<PagoVentaHelper> listByFirstCP(int ultimorealizado, Date fecha, int idCaja) throws AlidaPosException {
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(ultimorealizado);
		parameters.add(fecha);
		parameters.add(idCaja);
		String queryString = "select pv.idformapago, sum(pv.cantidad) as cantidad  "
				+ "from Pagoventa pv "
				+ "where pv.idpago <= ? "
				+ "AND pv.fecha = ? "
				+ "AND pv.idcaja = ? "
				+ "GROUP BY pv.idformapago";
		System.out.println("ultimorealizado "+ultimorealizado);
		System.out.println("fecha "+fecha);
		System.out.println("idcaja "+idCaja);
		List<PagoVentaHelper> result = super.getListResultNative(queryString, PagoVentaHelper.class, parameters);		
		for (int i = 0; i < result.size(); i++) {
			System.out.println("id      : " + result.get(i).getIdFormaPago());
			System.out.println("cantidad: " + result.get(i).getCantidad());
		}		
		return result;
	}
	
	
	
    public void delete(Pagoventa pagoventa) throws AlidaPosException {
        super.delete(pagoventa.getIdpago(), Pagoventa.class);
    }
    
    @Override
    public Pagoventa save(Pagoventa entity) throws AlidaPosException {
    	super.save(entity);
    	return entity;
    }
    
    @Override
    public Pagoventa update(Pagoventa entity) throws AlidaPosException {
    	return super.update(entity);
    }
}
