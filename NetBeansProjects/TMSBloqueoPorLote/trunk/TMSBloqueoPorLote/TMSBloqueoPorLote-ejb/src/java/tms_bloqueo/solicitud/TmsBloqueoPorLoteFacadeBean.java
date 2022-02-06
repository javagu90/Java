/*
 * TmsBloqueoPorLoteFacadeBean.java
 *
 * Created on 29 de julio de 2009, 01:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_bloqueo.solicitud;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import oracle.jdbc.internal.OracleCallableStatement;
import tms_bloqueo.entidad.TmsAutobusPlantLineasTbl;
import tms_bloqueo.entidad.TmsAutobusPlantillasEncTbl;
import tms_bloqueo.entidad.TmsComponenteBusTbl;

/**
 *
 * @author asolis
 */
@Stateless
public class TmsBloqueoPorLoteFacadeBean implements tms_bloqueo.solicitud.TmsBloqueoPorLoteFacadeRemote {
    @PersistenceContext
    private EntityManager em;
    @Resource(name = "TMS_DB")
    private DataSource dataSource;
    /** Creates a new instance of TmsBloqueoPorLoteFacadeBean */
    public TmsBloqueoPorLoteFacadeBean() {
    }
    
    public Vector getServicios(){             
        //Vector servicios = (Vector) em.createNativeQuery("select st.servicio_id, st.SERVICIO_CLAVE, st.SERVICIO_NOMBRE from tms_servicios_tbl st ORDER BY st.servicio_nombre").getResultList();        
        System.out.println("Consulta select st.SERVICIO_NOMBRE from tms_servicios_tbl st ORDER BY st.servicio_nombre");
        Vector servicios = (Vector) em.createNativeQuery("select st.SERVICIO_NOMBRE from tms_servicios_tbl st ORDER BY st.servicio_nombre").getResultList();        
        System.out.println("servicios "+servicios);        
        return servicios;
    }
    
    public Vector getRutas(){        
         
        System.out.println("Consulta SELECT rt.ruta_id, "+
                "rt.ruta_nombre, ruta_numero, rt.ruta_numero||'-'||ruta_nombre, st.SERVICIO_NOMBRE, et.ESTADO_NOMBRE, dt.ESTADO_NOMBRE "+
                "FROM TMS_RUTAS_TBL rt, TMS_SERVICIOS_TBL st, TMS_ESTADOS_TBL et, TMS_ESTADOS_TBL dt "+
               "WHERE st.SERVICIO_ID = rt.SERVICIO_ID "+               
               "AND et.ESTADO_ID = rt.TRAMO_ORIGEN_ID "+
               "AND dt.ESTADO_ID = rt.TRAMO_ORIGEN_ID "+
               "ORDER BY rt.RUTA_NOMBRE ");
        Vector rutas = (Vector) em.createNativeQuery("SELECT rt.ruta_id, "+
                "rt.ruta_nombre, ruta_numero, rt.ruta_numero||'-'||ruta_nombre, st.SERVICIO_NOMBRE, et.ESTADO_NOMBRE, dt.ESTADO_NOMBRE "+
                "FROM TMS_RUTAS_TBL rt, TMS_SERVICIOS_TBL st, TMS_ESTADOS_TBL et, TMS_ESTADOS_TBL dt "+                
               "WHERE st.SERVICIO_ID = rt.SERVICIO_ID "+               
               "AND et.ESTADO_ID = rt.TRAMO_ORIGEN_ID "+
               "AND dt.ESTADO_ID = rt.TRAMO_ORIGEN_ID "+
               "ORDER BY rt.RUTA_NOMBRE ").getResultList();        
        System.out.println("rutas "+rutas);        
        return rutas;
    }
    
     public Vector getEmpresas(){
        System.out.println("Consulta select empresa_id, empresa_nombre from tms_empresas_tbl order by empresa_nombre");
        Vector empresas = (Vector) em.createNativeQuery("select empresa_id, empresa_nombre from tms_empresas_tbl order by empresa_nombre").getResultList();
        System.out.println("empresas "+empresas);
        return empresas;
    }
     
     public Vector getCorridas(String servicio, String empresa, String ruta, String fechaInicial, String fechaFinal, String horaInicial, String horaFinal){         
         //procesar fechas
         String sysdate = "";
         if(fechaInicial.equals("sysdate")||fechaFinal.equals("sysdate")){
             sysdate = em.createNativeQuery("select ''''||to_char(sysdate, 'dd/mm/yyyy') from dual").getSingleResult().toString();
             sysdate = sysdate.subSequence(1, sysdate.length()-1).toString();
             if(fechaInicial.equals("sysdate"))
                 fechaInicial = sysdate;
             if(fechaFinal.equals("sysdate"))
                 fechaFinal = sysdate;
         }
         
         //buscar dblink
         System.out.println("SELECT bdct.NOMBRE_DBLINK " +
                            "FROM TMS_RUTAS_TBL rt, TMS_BASE_DATOS_CONFIG_TBL bdct " +
                            "WHERE rt.ruta_id = "+ruta +
                            "AND rt.TRAMO_ORIGEN_ID = bdct.TERMINAL_ID ");
                                     
         String dblink= em.createNativeQuery("SELECT bdct.NOMBRE_DBLINK " +
                            " FROM TMS_RUTAS_TBL rt, TMS_BASE_DATOS_CONFIG_TBL bdct " +
                            " WHERE rt.ruta_id = "+ruta +
                            " AND rt.TRAMO_ORIGEN_ID = bdct.TERMINAL_ID ").getSingleResult().toString();
         
         dblink = "@"+dblink.subSequence(1, dblink.length()-1).toString();
         System.out.println("Consulta SELECT cvt.clave_corrida, cvt.servicio, cvt.origen, cvt.destino, nvl(cvt.operador, '-'), nvl(cvt.autobus, '-'), TO_CHAR(cvt.fecha_hora_corrida, 'dd/mm/yyyy'), TO_CHAR(cvt.fecha_hora_corrida, 'hh24:mi'), " +
                            " CASE cvt.estado_corrida WHEN 'A' THEN 'ABIERTA' WHEN 'E' THEN 'CERRADA' WHEN 'C' THEN 'CANCELADA' WHEN 'G' THEN 'AGRUPADA' "+
                            " WHEN 'D' THEN 'DESPACHADA' WHEN 'P' THEN 'PROCESO' WHEN 'B' THEN 'BORRADA' END " +
                           "FROM TMS_CORRIDAS_VENTA_TBL"+dblink+" cvt, TMS_CORRIDAS_TBL"+dblink+" ct, TMS_RUTAS_TBL"+dblink+" rt "+
                           " WHERE cvt.servicio = '"+servicio+"' "+
                           " AND cvt.clave_corrida = ct.clave_corrida  "+
                           " AND cvt.EMPRESA = '"+empresa+"' "+
                           " AND ct.RUTA_ID = rt.RUTA_ID "+
                           " And ct.ruta_id = "+ruta +
                           " AND TO_CHAR(cvt.fecha_hora_corrida, 'dd/mm/yyyy hh24:mi') BETWEEN ("+fechaInicial+" "+horaInicial+") AND ("+fechaFinal+" "+horaFinal+") "+
                 	   " ORDER BY cvt.fecha_hora_corrida ");
         
         Vector corridas = (Vector) em.createNativeQuery("SELECT cvt.clave_corrida, cvt.servicio, cvt.origen, cvt.destino, nvl(cvt.operador, '-'), nvl(cvt.autobus, '-'), TO_CHAR(cvt.fecha_hora_corrida, 'dd/mm/yyyy'), TO_CHAR(cvt.fecha_hora_corrida, 'hh24:mi'), " +
                            " CASE cvt.estado_corrida WHEN 'A' THEN 'ABIERTA' WHEN 'E' THEN 'CERRADA' WHEN 'C' THEN 'CANCELADA' WHEN 'G' THEN 'AGRUPADA' "+
                            " WHEN 'D' THEN 'DESPACHADA' WHEN 'P' THEN 'PROCESO' WHEN 'B' THEN 'BORRADA' END " +
                           "FROM TMS_CORRIDAS_VENTA_TBL"+dblink+" cvt, TMS_CORRIDAS_TBL"+dblink+" ct, TMS_RUTAS_TBL"+dblink+" rt "+
                           " WHERE cvt.servicio = '"+servicio+"' "+
                           " AND cvt.clave_corrida = ct.clave_corrida  "+
                           " AND cvt.EMPRESA = '"+empresa+"' "+
                           " AND ct.RUTA_ID = rt.RUTA_ID "+
                           " And ct.ruta_id = "+ruta +
                           " AND TO_CHAR(cvt.fecha_hora_corrida, 'dd/mm/yyyy hh24:mi') BETWEEN ("+fechaInicial+" "+horaInicial+") AND ("+fechaFinal+" "+horaFinal+") "+
                 	   " ORDER BY cvt.fecha_hora_corrida").getResultList();
         System.out.println("corridas "+corridas);
         
         System.out.println("SELECT ct.corrida_id" +
                           " FROM TMS_CORRIDAS_VENTA_TBL"+dblink+" cvt, TMS_CORRIDAS_TBL"+dblink+" ct, TMS_RUTAS_TBL"+dblink+" rt "+
                           " WHERE cvt.servicio = '"+servicio+"' "+
                           " AND cvt.clave_corrida = ct.clave_corrida  "+
                           " AND cvt.EMPRESA = '"+empresa+"' "+
                           " AND ct.RUTA_ID = rt.RUTA_ID "+
                           " And ct.ruta_id = "+ruta +
                           " AND TO_CHAR(cvt.fecha_hora_corrida, 'dd/mm/yyyy hh24:mi') BETWEEN ("+fechaInicial+" "+horaInicial+") AND ("+fechaFinal+" "+horaFinal+") "+
                 	   " ORDER BY cvt.fecha_hora_corrida ");
         Vector corridasId = (Vector) em.createNativeQuery("SELECT ct.corrida_id " +
                           " FROM TMS_CORRIDAS_VENTA_TBL"+dblink+" cvt, TMS_CORRIDAS_TBL"+dblink+" ct, TMS_RUTAS_TBL"+dblink+" rt "+
                           " WHERE cvt.servicio = '"+servicio+"' "+
                           " AND cvt.clave_corrida = ct.clave_corrida  "+
                           " AND cvt.EMPRESA = '"+empresa+"' "+
                           " AND ct.RUTA_ID = rt.RUTA_ID "+
                           " And ct.ruta_id = "+ruta +
                           " AND TO_CHAR(cvt.fecha_hora_corrida, 'dd/mm/yyyy hh24:mi') BETWEEN ("+fechaInicial+" "+horaInicial+") AND ("+fechaFinal+" "+horaFinal+") "+
                 	   " ORDER BY cvt.fecha_hora_corrida").getResultList();
         System.out.println("corridasId "+corridasId);
         
         System.out.println("SELECT ap.PLANTILLA_ENC_ID FROM TMS_AUTOBUS_PLANTILLAS_ENC_TBL ap, TMS_RUTA_PARAMETROS_TBL  rp "+
                            "WHERE rp.parametro_config_id = 66 "+
                            "AND rp.parametro_valor = ap.NOMBRE_CORTO "+
                            " AND rp.RUTA_ID = "+ruta);
         String plantillaId = em.createNativeQuery("SELECT ap.PLANTILLA_ENC_ID FROM TMS_AUTOBUS_PLANTILLAS_ENC_TBL ap, TMS_RUTA_PARAMETROS_TBL  rp "+
                            "WHERE rp.parametro_config_id = 66 "+
                            "AND rp.parametro_valor = ap.NOMBRE_CORTO "+
                            " AND rp.RUTA_ID = "+ruta).getSingleResult().toString();
         corridas.add(dblink);
         corridas.add(corridasId);
         corridas.add(plantillaId.subSequence(1, plantillaId.length()-1).toString());
         return corridas;
     }
     
     public String obtieneTerminal(String pDBLink){
         String valor=null;
        Connection cnx=null;
        System.out.println("pDBLink "+pDBLink);
        OracleCallableStatement stmt=null;                
        try {
            System.out.println("SELECT '@'||nombre_dblink FROM TMS_BASE_DATOS_CONFIG_TBL WHERE ESQUEMA_PROPIO = 'S'");
            String dblinkLocal = em.createNativeQuery("SELECT '@'||nombre_dblink FROM TMS_BASE_DATOS_CONFIG_TBL WHERE ESQUEMA_PROPIO = 'S'").getSingleResult().toString();
            if(dblinkLocal.contains(pDBLink)){
                System.out.println("DESDE obtieneTerminal SP true");
                return "true";
            }
            System.out.println("DESDE obtieneTerminal SP false");
            return "false";
       } catch (Exception ex){
            try {
                ex.printStackTrace();
                valor = stmt.getString(7);
                System.out.println("SP ocupa "+(valor==null?"null":valor));
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
               return null;
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                return null;
            }
        }         
     }
     
     public String ocuparAsientos(String pDBLink, String corridaId, String asientos, String tiposPasajero, String modo, String usuarioId){
        String valor=null;
        Connection cnx=null;
        System.out.println("pDBLink "+pDBLink + " corridaId "+corridaId + " asientos "+asientos+" tiposPasajero "+tiposPasajero + " modo "+modo + " usuarioId "+usuarioId);
        OracleCallableStatement stmt=null;                
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                      //"Xer_Tms_Pkg2.Tms_Bloquear_Asientos_Lote_Prc"+pDBLink +"(?, ?, ?, ?, ?, ?, ?); " +
                      "Xer_Tms_Pkg2.Tms_Bloquear_Asientos_Lote_Prc(?, ?, ?, ?, ?, ?, ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK;" +
                      "RAISE; "+
                    "END;";    
            System.out.println("q1 "+q1);
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.registerOutParameter(7,java.sql.Types.VARCHAR);
            stmt.setLong(1,Long.parseLong(corridaId));
            stmt.setString(2, asientos);
            stmt.setString(3, tiposPasajero);
            stmt.setLong(4, Long.parseLong(usuarioId));
            stmt.setString(5, modo);
            stmt.setString(6, pDBLink);
            // -1X reg ocupado, 13S CON EL QUE FALLO; 000; 00X reg ocupado
            stmt.execute();
            
            valor = stmt.getString(7);
            //System.out.println("Valor "+valor);
            stmt.close();
            if(cnx!=null) cnx.close();
            
            if(valor==null) return "-1";
            if(valor.equals("00X") || valor.equals("-1X")) return "-1";
            if(valor.equals("000")) return "0";
            String valorTipo = valor.substring(2);
            return ""+Integer.valueOf(valor.substring(0,2));
        } catch (SQLException ex){
            try {
                valor = stmt.getString(7);
                System.out.println("SP ocupa "+(valor==null?"null":valor));
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                if(valor==null || valor.equals("00X") || valor.equals("-1X")) return "-1";
                if(valor.equals("000")) return "0";
                String valorTipo = valor.substring(2);
                return ""+valor.substring(0,2);
            }
        }
    }
     
    public Vector getFechaHora(){
         System.out.println("");
         Vector fechaHora =  (Vector) em.createNativeQuery("Select to_char(sysdate, 'dd/mm/yyyy'), to_char(sysdate, 'hh24:mi') from dual").getSingleResult();
         return fechaHora;
    }
    
   
     
     /*Plantilla*/
     public List<TmsComponenteBusTbl> queryTmsComponenteBusTblFindAll() {
        return em.createNamedQuery("TmsComponenteBusTbl.findAll").getResultList();
    }

     
     public List<TmsAutobusPlantillasEncTbl> queryTmsAutobusPlantillasEncTblFindAll() {
        return em.createNamedQuery("TmsAutobusPlantillasEncTbl.findAll").getResultList();
    }
    
     public List<TmsAutobusPlantLineasTbl> queryTmsAutobusPlantLineasTblFindAll() {
        return em.createNamedQuery("TmsAutobusPlantLineasTbl.findTodo").getResultList();
    }

    public List<TmsAutobusPlantLineasTbl> queryTmsAutobusPlantLineasTblFindById(Long pId) {
        return em.createNamedQuery("TmsAutobusPlantLineasTbl.findAll").setParameter("encId", 
                                                                                    pId).getResultList();
    }


    
}
