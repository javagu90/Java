/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.estrellaroja.confirmacionviajes.solicitudes;

import com.estrellaroja.confirmacionviajes.entidades.TmsBaseDatos;
import com.estrellaroja.confirmacionviajes.entidades.TmsTarjetasViaje;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class generalFacade implements generalFacadeRemote {
    @PersistenceContext(unitName="ConfirmacionViajes-ejbPU2")
    private EntityManager em;
    
  public List<TmsTarjetasViaje> findByFolio(String folio, BigInteger origen) {
    try {
  
      
       Vector v = (Vector)em.createNativeQuery("SELECT ESQUEMA_PROPIO,NOMBRE_DBLINK FROM TMS_BASE_DATOS_CONFIG_TBL WHERE terminal_id = "+origen.longValue()).getResultList();
       Vector res= (Vector)v.get(0);
       String dblink = res.get(0).toString().equals("S")?"":"@"+res.get(1).toString();

       String qry = "SELECT T.TARJETA_VIAJE_ID, T.FOLIO_TARJETA, CS.CLAVE_CORRIDA, CS.FECHA_HORA_CORRIDA,      "
               + "OPS.CLAVE_OPERADOR, OPS.OPERADOR_NOMBRE_COMPLETO, T.AUTOBUS, S.SERVICIO_NOMBRE SERVICIO, CS.EMPRESA_ID, ES.EMPRESA_NOMBRE, "
               + "OS.ESTADO_NOMBRE ORIGEN, DS.ESTADO_NOMBRE DESTINO, T.ADICIONAL4 BOLETOS_VENTA_MANUAL, T.ADICIONAL5 IMPORTE_VENTA_MANUAL, "
               + "ET.NOMBRE_ESTADO , R.ADICIONAL5      "
               + "FROM TMS_TARJETAS_VIAJE_TBL"+dblink+" T      "
               + "INNER JOIN TMS_CORRIDAS_TBL"+dblink+" CS ON CS.CORRIDA_ID = T.CORRIDA_ID      "
               + "INNER JOIN TMS_OPERADORES_TBL"+dblink+" OPS ON OPS.CLAVE_OPERADOR = T.OPERADOR      "
               + "INNER JOIN TMS_RUTAS_TBL"+dblink+" R ON R.RUTA_ID = CS.RUTA_ID      "
               + "INNER JOIN TMS_ESTADOS_TBL"+dblink+" DS ON DS.ESTADO_ID = R.TRAMO_DESTINO_ID      "
               + "INNER JOIN TMS_ESTADOS_TBL"+dblink+" OS ON OS.ESTADO_ID = R.TRAMO_ORIGEN_ID      "
               + "INNER JOIN TMS_SERVICIOS_TBL"+dblink+" S ON S.SERVICIO_ID = R.SERVICIO_ID "
               + "INNER JOIN tms_empresas_tbl"+dblink+" ES ON ES.EMPRESA_ID = CS.EMPRESA_ID      "
               + "INNER JOIN TMS_ESTADOS_TARJETA_VIAJE_TBL"+dblink+" ET ON ET.ESTADO_TARJETA_VIAJE_ID = T.ESTADO_TARJETA_ID      "
               + "WHERE T.FOLIO_TARJETA = '"+folio+"'  "
               + "AND R.TRAMO_ORIGEN_ID = "+origen+" "
               + "and (SELECT E.PARAMETRO_VALOR "
               + "FROM TMS_PARAMETROS_CONFIG_TBL"+dblink+" P "
               + "LEFT JOIN TMS_EMPRESA_PARAMETROS_TBL"+dblink+" e on E.PARAMETRO_CONFIG_ID = P.PARAMETRO_CONFIG_ID "
               + "WHERE P.PARAMETRO_CODIGO      = 'P_VLRPERREC' "
               + "AND E.EMPRESA_ID = CS.EMPRESA_ID ) = 'S'";
       System.out.println("findByFolio: "+qry);
      //List<TmsTarjetasViaje> result = em.createNativeQuery(qry,TmsTarjetasViaje.class).getResultList();
      List<TmsTarjetasViaje> listado = new ArrayList<TmsTarjetasViaje>();

           Vector resultado = (Vector)em.createNativeQuery(qry).getResultList();
           //System.out.println("result("+resultado.size()+"): "+resultado);
           if(resultado.size()==0)
                return listado;
           for(int i=0; i<resultado.size();i++)
           {
               //System.out.println("result("+resultado.get(i));
               //System.out.println("result("+resultado.get(i).getClass());
               Vector tar =(Vector)resultado.get(i);
               TmsTarjetasViaje t = new TmsTarjetasViaje(
                       BigInteger.valueOf(Long.valueOf(tar.get(0).toString()))
                       , (String) tar.get(1)
                       , (String) tar.get(2)
                       , (Timestamp) tar.get(3)
                       , (String) tar.get(14)
                       , (String) tar.get(4)
                       , (String) tar.get(5)
                       , (String) tar.get(6).toString()
                       , (String) tar.get(7).toString()
                       , ((BigInteger.valueOf(Long.valueOf(tar.get(8).toString()))))
                       , (String) tar.get(9)
                       , (String) tar.get(10).toString()
                       , (String) tar.get(11).toString()
                       , (String) (tar.get(12)==null?"":tar.get(12))
                       , (String) (tar.get(13)==null?"":tar.get(13))
                       , (String) (tar.get(15)==null?"N":tar.get(15).toString()));
               listado.add(t);
           }

         //TmsTarjetasViaje(BigInteger tarjetaViajeId, String folioTarjeta, String claveCorrida, Timestamp fechaHoraCorrida, String estadoTarjeta, String claveOperador, String nombreOperador, String autobus, String servicio, BigInteger empresa, String empresaNombre, String origen, String destino, String boletosVentaManual, String importeVentaManual, String recaudacionAutomatica) {


      return listado;
    } catch (Exception e) {
      System.out.println("e: " + e.getLocalizedMessage());
      e.printStackTrace();
      return null;
    }
  }


  public List<TmsTarjetasViaje> findByOperador(String claveOperador, BigInteger origen) {
    try {


       Vector v = (Vector)em.createNativeQuery("SELECT ESQUEMA_PROPIO,NOMBRE_DBLINK FROM TMS_BASE_DATOS_CONFIG_TBL WHERE terminal_id = "+origen.longValue()).getResultList();
       Vector res= (Vector)v.get(0);
       String dblink = res.get(0).toString().equals("S")?"":"@"+res.get(1).toString();

       String qry = "SELECT T.TARJETA_VIAJE_ID, T.FOLIO_TARJETA, CS.CLAVE_CORRIDA, CS.FECHA_HORA_CORRIDA,      "
               + "OPS.CLAVE_OPERADOR, OPS.OPERADOR_NOMBRE_COMPLETO, T.AUTOBUS, S.SERVICIO_NOMBRE SERVICIO, CS.EMPRESA_ID, ES.EMPRESA_NOMBRE, "
               + "OS.ESTADO_NOMBRE ORIGEN, DS.ESTADO_NOMBRE DESTINO, T.ADICIONAL4 BOLETOS_VENTA_MANUAL, T.ADICIONAL5 IMPORTE_VENTA_MANUAL, "
               + "ET.NOMBRE_ESTADO , R.ADICIONAL5      "
               + "FROM TMS_TARJETAS_VIAJE_TBL"+dblink+" T      "
               + "INNER JOIN TMS_CORRIDAS_TBL"+dblink+" CS ON CS.CORRIDA_ID = T.CORRIDA_ID      "
               + "INNER JOIN TMS_OPERADORES_TBL"+dblink+" OPS ON OPS.CLAVE_OPERADOR = T.OPERADOR      "
               + "INNER JOIN TMS_RUTAS_TBL"+dblink+" R ON R.RUTA_ID = CS.RUTA_ID      "
               + "INNER JOIN TMS_ESTADOS_TBL"+dblink+" DS ON DS.ESTADO_ID = R.TRAMO_DESTINO_ID      "
               + "INNER JOIN TMS_ESTADOS_TBL"+dblink+" OS ON OS.ESTADO_ID = R.TRAMO_ORIGEN_ID      "
               + "INNER JOIN TMS_SERVICIOS_TBL"+dblink+" S ON S.SERVICIO_ID = R.SERVICIO_ID "
               + "INNER JOIN tms_empresas_tbl"+dblink+" ES ON ES.EMPRESA_ID = CS.EMPRESA_ID      "
               + "INNER JOIN TMS_ESTADOS_TARJETA_VIAJE_TBL"+dblink+" ET ON ET.ESTADO_TARJETA_VIAJE_ID = T.ESTADO_TARJETA_ID      "
               + "WHERE T.OPERADOR = '"+claveOperador+"'  AND ET.NOMBRE_ESTADO IN ('ABIERTA','CONFIRMADA')  "
               + "AND R.TRAMO_ORIGEN_ID = "+origen+" "
               + "and (SELECT E.PARAMETRO_VALOR "
               + "FROM TMS_PARAMETROS_CONFIG_TBL"+dblink+" P "
               + "LEFT JOIN TMS_EMPRESA_PARAMETROS_TBL"+dblink+" e on E.PARAMETRO_CONFIG_ID = P.PARAMETRO_CONFIG_ID "
               + "WHERE P.PARAMETRO_CODIGO      = 'P_VLRPERREC' "
               + "AND E.EMPRESA_ID = CS.EMPRESA_ID ) = 'S'";
       System.out.println("findByOperador: "+qry);
      //List<TmsTarjetasViaje> result = em.createNativeQuery(qry,TmsTarjetasViaje.class).getResultList();
      List<TmsTarjetasViaje> listado = new ArrayList<TmsTarjetasViaje>();

           Vector resultado = (Vector)em.createNativeQuery(qry).getResultList();
           //System.out.println("result("+resultado.size()+"): "+resultado);
           if(resultado.size()==0)
                return listado;
           for(int i=0; i<resultado.size();i++)
           {
               //System.out.println("result("+resultado.get(i));
               //System.out.println("result("+resultado.get(i).getClass());
               Vector tar =(Vector)resultado.get(i);
               TmsTarjetasViaje t = new TmsTarjetasViaje(
                       BigInteger.valueOf(Long.valueOf(tar.get(0).toString()))
                       , (String) tar.get(1)
                       , (String) tar.get(2)
                       , (Timestamp) tar.get(3)
                       , (String) tar.get(14)
                       , (String) tar.get(4)
                       , (String) tar.get(5)
                       , (String) tar.get(6).toString()
                       , (String) tar.get(7).toString()
                       , ((BigInteger.valueOf(Long.valueOf(tar.get(8).toString()))))
                       , (String) tar.get(9)
                       , (String) tar.get(10).toString()
                       , (String) tar.get(11).toString()
                       , (String) (tar.get(12)==null?"":tar.get(12))
                       , (String) (tar.get(12)==null?"":tar.get(13))
                       , (String) tar.get(15));
               listado.add(t);
           }

         //TmsTarjetasViaje(BigInteger tarjetaViajeId, String folioTarjeta, String claveCorrida, Timestamp fechaHoraCorrida, String estadoTarjeta, String claveOperador, String nombreOperador, String autobus, String servicio, BigInteger empresa, String empresaNombre, String origen, String destino, String boletosVentaManual, String importeVentaManual, String recaudacionAutomatica) {


      return listado;
    } catch (Exception e) {
      System.out.println("e: " + e.getLocalizedMessage());
      e.printStackTrace();
      return null;
    }
  }


  public TimeZone getTimeZone()
  {
    return TimeZone.getDefault();
  }

}
