/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estrellaroja.confirmacionviajes.solicitudes;

import com.estrellaroja.confirmacionviajes.entidades.TmsBaseDatos;
import com.estrellaroja.confirmacionviajes.entidades.TmsTarjetasViaje;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import oracle.jdbc.OracleCallableStatement;
import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.sessions.DatabaseRecord;

/**
 *
 * @author EKS Victor
 */
@Stateless
public class TmsTarjetasViajeFacade extends AbstractFacade<TmsTarjetasViaje> implements TmsTarjetasViajeFacadeRemote {

  @PersistenceContext(unitName = "ConfirmacionViajes-ejbPU")
  private EntityManager em;

  @Resource(name = "TMS_DB")
  private DataSource dataSource;

  protected EntityManager getEntityManager() {
    return em;
  }

  public TmsTarjetasViajeFacade() {
    super(TmsTarjetasViaje.class);
  }

  public List<TmsTarjetasViaje> findByFolio(String folio, BigInteger origen) {
    try {
      /*
      Query terminal_query = em.createNamedQuery("findByTerminal");
      terminal_query.setParameter("origen", origen);
      TmsBaseDatos db_origen = (TmsBaseDatos) terminal_query.getSingleResult();
      */
      /*if (db_origen == null) {
        return null;
      }
      Query query = em.createNamedQuery("findByFolio");
      query.setParameter("folio", folio);
      query.setParameter("origen", origen);
      query.setParameter("dblink", db_origen.getEsquemaPropio().equals("S")?"":"@"+db_origen.getNombreDblink());
      List<TmsTarjetasViaje> result = query.getResultList();
      */
      String dblink = "";//db_origen.getEsquemaPropio().equals("S")?"":"@"+db_origen.getNombreDblink();

       String qry = "SELECT T.TARJETA_VIAJE_ID, T.FOLIO_TARJETA, CS.CLAVE_CORRIDA, CS.FECHA_HORA_CORRIDA,      "
               + "OPS.CLAVE_OPERADOR, OPS.OPERADOR_NOMBRE_COMPLETO, T.AUTOBUS, S.SERVICIO_NOMBRE SERVICIO, CS.EMPRESA_ID, ES.EMPRESA_NOMBRE, "
               + "OS.ESTADO_NOMBRE ORIGEN, DS.ESTADO_NOMBRE DESTINO, T.ADICIONAL4 BOLETOS_VENTA_MANUAL, T.ADICIONAL5 IMPORTE_VENTA_MANUAL, "
               + "ET.NOMBRE_ESTADO AS ESTADO_TARJETA, R.ADICIONAL5 AS RECAUDACION_AUTOMATICA      "
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
           System.out.println("result("+resultado.size()+"): "+resultado);
           if(resultado.size()==0)
                return null;
           for(int i=0; i<resultado.size();i++)
           {
               Vector tar =(Vector)resultado.get(i);
               TmsTarjetasViaje t = new TmsTarjetasViaje((BigInteger)tar.get(0), (String) tar.get(1), (String) tar.get(2), (Timestamp) tar.get(3), (String) tar.get(14), (String) tar.get(4), (String) tar.get(5), (String) tar.get(6), (String) tar.get(7), (BigInteger) tar.get(8), (String) tar.get(9), (String) tar.get(10), (String) tar.get(11), (String) (tar.get(12)==null?"":tar.get(12)), (String) (tar.get(12)==null?"":tar.get(13)), (String) tar.get(15));
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

  public List<TmsTarjetasViaje> findByOperador(String clave_operador, BigInteger origen) {
    Query terminal_query = em.createNamedQuery("findByTerminal");
    terminal_query.setParameter("origen", origen);
    TmsBaseDatos db_origen = (TmsBaseDatos) terminal_query.getSingleResult();
    if (db_origen == null) {
      return null;
    }
    Query query = em.createNamedQuery("findByOperador");
    query.setParameter("clave", clave_operador);
    query.setParameter("origen", origen);
    query.setParameter("dblink", db_origen.getNombreDblink());
    List<TmsTarjetasViaje> result = query.getResultList();
    return result;
  }

  public ArrayList<String> confirmarTarjetas(ArrayList<BigInteger> tarjetas, Long usuario_id, BigInteger origen,
          String operador,String autobus, String boletos_venta_manual, String importe_venta_manual) {
    ArrayList<String> results = new ArrayList<String>();
    for (BigInteger tarjeta_id : tarjetas) {
      results.add(confirmarTarjeta(tarjeta_id, usuario_id, origen, operador,autobus, boletos_venta_manual, importe_venta_manual));
    }
    return results;
  }

  public String confirmarTarjeta(BigInteger tarjeta_id, Long usuario_id, BigInteger origen,
          String operador,String autobus, String boletos_venta_manual, String importe_venta_manual) {
    /*Query query = em.createNamedQuery("confirmarTarjeta");
    query.setParameter("tarjeta_id", tarjeta_id);
    query.setParameter("usuario_id", usuario_id);
    query.setParameter("origen", origen);
    query.setParameter("clave_operador", operador);
    query.setParameter("clave_autobus", autobus);
    query.setParameter("boletos_venta_manual", boletos_venta_manual);
    query.setParameter("importe_venta_manual", importe_venta_manual);
    query.setParameter("return_code", new Integer(0));
    try {
      int executeUpdate = query.executeUpdate();
      return "";
    } catch (Exception e) {
      e.printStackTrace();
      String msg = e.getMessage();
      int idx = msg.indexOf("ORA-");
      int idx2 = msg.indexOf("\n", idx);
      String m = msg.substring(idx + 10, idx2);
      System.out.println("e.getMessage() = " + m);
      return m;

    }*/
    Connection cnx = null;
    OracleCallableStatement stmt = null;
    try {
      cnx = dataSource.getConnection();
      System.out.println("tarjeta_id: = " + tarjeta_id);
      System.out.println("usuario_id: = " + usuario_id);
      System.out.println("origen:  = " + origen);
      String q1 =
              "BEGIN "
              + "TMS_CONFIRMACION_VIAJE_PKG.CONFIRMAR_TARJETA(?, ?, ?, ?, ?, ?,?,?,?); "
              + "COMMIT; "
              + "EXCEPTION "
              + "WHEN OTHERS THEN "
              + "ROLLBACK;"
              + "RAISE; "
              + "END;";

      stmt = (OracleCallableStatement) cnx.prepareCall(q1);
      stmt.setLong(1, tarjeta_id.longValue());
      stmt.setLong(2, usuario_id.longValue());
      stmt.setLong(3, origen.longValue());
      stmt.setString(4, operador);
      stmt.setString(5, autobus);
      stmt.setString(6, boletos_venta_manual);
      stmt.setString(7, importe_venta_manual);
      stmt.registerOutParameter(8, java.sql.Types.NUMERIC);
      stmt.registerOutParameter(9, java.sql.Types.VARCHAR);

      stmt.execute();
      long codigo = stmt.getLong(8);
      String mensaje = stmt.getString(9);
      if (mensaje == null )
          mensaje = "";
      stmt.close();
      if (cnx != null) {
        cnx.close();
      }
      return mensaje;
    } catch (SQLException ex) {
      System.out.println("ex = " + ex.getMessage());
      try {
        stmt.close();
        if (cnx != null) {
          cnx.close();
        }
      } catch (SQLException ex1) {
      } finally {
        cnx = null;
        return "No fue posible realizar la Transaccion(EJB)";
      }
     }
    }

  public Timestamp getFechaServidor() {
    String consulta = "SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD HH24:MI:SS') FROM DUAL";
    try {
      List result = (List) this.em.createNativeQuery(consulta).getResultList();
      return Timestamp.valueOf(result.get(0).toString());
    } catch (NoResultException nre) {
    }
    return null;
  }

  public ArrayList getValues2(BigInteger tarjeta, String operador) {
    ArrayList values = new ArrayList();
    JpaEntityManager jem = (JpaEntityManager) em.getDelegate();
    Query query = jem.createNamedQuery("getValues");
    query.setParameter("tarjeta_id", tarjeta);
    query.setParameter("OPERADOR", operador);
    System.out.println("tarjeta = " + tarjeta);
    System.out.println("operador = " + operador);
    query.executeUpdate();
    List results = (List) query.getResultList();
    DatabaseRecord record = (DatabaseRecord) results.get(0);
//String y = (String) record.get("y");
//String z = (String) record.get("z");
    values.add(record.get("sueldo"));
    values.add(record.get("retencion"));
    values.add(record.get("pagooperador"));
    values.add(record.get("saldo_operador"));
    return values;
  }

  public ArrayList getValues(BigInteger tarjeta, String operador) {
    Query terminal_query = em.createNamedQuery("findByPrefijo");
      terminal_query.setParameter("prefijo", "CENT");
      TmsBaseDatos db_origen = (TmsBaseDatos) terminal_query.getSingleResult();
      if (db_origen == null) {
        return null;
      }


    ArrayList values = new ArrayList();
    Connection cnx = null;
    OracleCallableStatement stmt = null;
    try {
      cnx = dataSource.getConnection();
      String q1 =
              "BEGIN "
              + "TMS_CONFIRMACION_VIAJE_PKG.TMS_GET_VALORES_SP@"+db_origen.getNombreDblink()+"(?, ?, ?, ?, ?, ?); "
              + "COMMIT; "
              + "EXCEPTION "
              + "WHEN OTHERS THEN "
              + "ROLLBACK;"
              + "RAISE; "
              + "END;";

      stmt = (OracleCallableStatement) cnx.prepareCall(q1);
      stmt.registerOutParameter(3, java.sql.Types.NUMERIC);
      stmt.registerOutParameter(4, java.sql.Types.NUMERIC);
      stmt.registerOutParameter(5, java.sql.Types.NUMERIC);
      stmt.registerOutParameter(6, java.sql.Types.NUMERIC);
      stmt.setLong(1, tarjeta.longValue());
      stmt.setString(2, operador);
      stmt.execute();

      values.add(stmt.getDouble(3));
      values.add(stmt.getDouble(4));
      values.add(stmt.getDouble(5));
      values.add(stmt.getDouble(6));
      //System.out.println(this.getCaja()+"=> Valor "+valor);
      stmt.close();
      if (cnx != null) {
        cnx.close();
      }
      return values;
    } catch (SQLException ex) {
      System.out.println("ex = " + ex.getMessage());
      try {
        stmt.close();
        if (cnx != null) {
          cnx.close();
        }
      } catch (SQLException ex1) {
      } finally {
        cnx = null;
        return null;
      }

//    Query query = jem.createNamedQuery("getValues");
//    query.setParameter("tarjeta_id", tarjeta);
//    query.setParameter("OPERADOR", operador);
//    values.add(record.get("sueldo"));
//    values.add(record.get("retencion"));
//    values.add(record.get("pagooperador"));
//    values.add(record.get("saldo_operador"));
    }
  }
}
