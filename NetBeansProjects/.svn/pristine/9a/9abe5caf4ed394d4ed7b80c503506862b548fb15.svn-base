/*
 * TMSPasesTrasladoFacadeBean.java
 *
 * Created on 27 de enero de 2009, 09:37 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package solicitud;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import oracle.jdbc.internal.OracleCallableStatement;
import oracle.jdbc.internal.OraclePreparedStatement;

/**
 *
 * @author asolis
 */
@Stateless
public class TMSPasesTrasladoFacadeBean implements solicitud.TMSPasesTrasladoFacadeRemote {
    @PersistenceContext(unitName = "TMSPases_Traslado-ejbPU")
    private EntityManager manager;    
    @Resource(name = "TMS_DB")
    private DataSource dataSource;
    
    public Vector buscarDatosRuta(String ruta){
        System.out.println("Buscar Datos Ruta");        
        return (Vector) manager.createNativeQuery("select * from tms_rutas_tbl where ruta_numero = "+ruta).getSingleResult();        
    }
    
    public Vector buscarOperador(String operador) {
        System.out.println("Buscar Datos Operador");        
        return (Vector) manager.createNativeQuery("select * from tms_operadores_tbl where clave_operador = '"+operador+"'").getSingleResult();
    }
    
    public boolean getValidarAutobus(String autobus) {
        System.out.println("Validar autobus");        
        Vector vec = (Vector) manager.createNativeQuery("select count(*) from tms_autobuses_tbl where numero_economico = '"+autobus+"'").getSingleResult();
        if(Integer.parseInt(vec.get(0).toString()) > 0)
            return true;
        return false;
    }
    
    public boolean getValidarOpeador(String opeador) {
        System.out.println("Validar operador");        
        Vector vec = (Vector) manager.createNativeQuery("SELECT COUNT(*) FROM tms_operadores_tbl where clave_operador = '"+opeador+"'").getSingleResult();
        if(Integer.parseInt(vec.get(0).toString()) > 0)
            return true;
        return false;
    }
    
    public String buscarNombreEstado(String estadoId){
        System.out.println("Buscar Datos Estado");        
        return ((Vector)manager.createNativeQuery("select estado_nombre from tms_estados_tbl where estado_id = "+estadoId).getSingleResult()).get(0).toString();
    }
    
    public String buscarNombreServicio(String servicioId){
        System.out.println("Buscar Datos Servicio");        
        return ((Vector)manager.createNativeQuery("SELECT servicio_nombre FROM TMS_SERVICIOS_TBL WHERE servicio_id = "+servicioId).getSingleResult()).get(0).toString();
    }
    
    public Vector buscarImpresoraFacturas(String nombreCaja) {
    String query  = "select  imp.IMPRESORA_NOMBRE  from TMS_CAJAS_IMPRESORAS_TBL cimp " +
                ",TMS_IMPRESORAS_TBL imp  "+
                "where cimp.CAJA_ID = (SELECT caj.CAJA_ID FROM Tms_Cajas_Tbl caj WHERE caj.nombre_Equipo = '"+ nombreCaja +"') " +
                "and   imp.IMPRESORA_ID = cimp.IMPRESORA_ID  " +                               
                "and cimp.ACTIVIDAD_IMPRESORA = 'FACTURAS' ";
        return (Vector)manager.createNativeQuery(query).getResultList();
    }
    
    public Vector buscarRutas(){
        System.out.println("Buscar Rutas");
        String query = "SELECT ot.ESTADO_NOMBRE||'-'||dt.ESTADO_NOMBRE, rt.ruta_numero, "+
                       "nvl((SELECT SUM(CT.CASETA_COSTO) peaje FROM TMS_RUTAS_TBL rtt, TMS_TRAMOS_TBL tt, TMS_CASETAS_TBL CT, TMS_TRAMOS_CASETAS_TBL TCT "+
                       " WHERE rtt.adicional3 = 'S' "+
                       " AND CT.ADICIONAL1 = 'S' "+
                       " AND rtt.RUTA_ID = tt.RUTA_ID "+
                       " AND TT.TRAMO_ID = TCT.TRAMO_ID "+
                       " AND CT.CASETA_ID = TCT.CASETA_ID "+
                       " AND rtt.ruta_id = rt.ruta_id),0) peaje "+
                    " FROM TMS_RUTAS_TBL rt, TMS_ESTADOS_TBL ot, TMS_ESTADOS_TBL dt "+
                       " WHERE rt.TRAMO_ORIGEN_ID = ot.ESTADO_ID "+
                       " AND rt.TRAMO_destino_ID = dt.ESTADO_ID "+
                       " AND rt.ADICIONAL3 = 'S' "+
                       "ORDER BY ruta_numero";
        System.out.println("query  "+query);
        return (Vector)manager.createNativeQuery(query).getResultList();
    }
    
    public Vector buscarMotivos(){
        System.out.println("Buscar Rutas");
        String query = "SELECT estado_nombre FROM TMS_ESTADOS_TBL "+
                       " WHERE tipo_componente = 'ACTIVIDAD' "+
                       " ORDER BY estado_id";
        System.out.println("query  "+query);
        return (Vector)manager.createNativeQuery(query).getResultList();
    }
    
     public String insertarPasesTraslado(String operador, String autobus, String ruta, String motivo, String usuario, String peaje) 
            throws javax.ejb.EJBException{
        String p_respuesta = null;
        Connection cnx = null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                      "? := Xer_Tms_Pkg2.TMS_INS_PASE_TRASLADO_FNC(?,?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";

             stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            //((OraclePreparedStatement)stmt).setString(1, p_respuesta);
            ((OraclePreparedStatement)stmt).setString(2, operador );
            ((OraclePreparedStatement)stmt).setString(3, autobus);
            ((OraclePreparedStatement)stmt).setString(4, ruta);
            ((OraclePreparedStatement)stmt).setString(5, usuario);
            ((OraclePreparedStatement)stmt).setString(6, motivo);
            ((OraclePreparedStatement)stmt).setString(7, peaje);
            stmt.registerOutParameter(1,java.sql.Types.VARCHAR);  
            bResultado=stmt.execute();
            p_respuesta = stmt.getString(1);
            stmt.close();
            /*System.out.println("p_respuesta "+p_respuesta+ " bResultado"+bResultado);
            System.out.println("Bean IniciarSesion - "+p_respuesta);*/
           if(cnx!=null) cnx.close();
          // System.out.println("");
            if(p_respuesta == null)
                return "Algo no salio bien";
           return p_respuesta;
        } catch (SQLException ex) {
            try {
                p_respuesta = stmt.getString(1);
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                p_respuesta="Algo no salio bien";
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("Excepcion Bean IniciarSesion - "+p_respuesta);
                return p_respuesta;
            }
        }
        catch(Exception e){
            p_respuesta="Algo no salio bien";
        }
        return p_respuesta;
   }
     
  public Object buscarEstadoSesion(long sesionId){
     String consulta =  "select sg.ESTADO_SESION from tms_sesiones_global_tbl sg where sg.NUMERO_SESION = "+sesionId;
     return manager.createNativeQuery(consulta).getSingleResult();
 }
  
  public String eliminarPasesTraslado(String folio, String usuario)
            throws javax.ejb.EJBException{
        String p_respuesta = null;
        Connection cnx = null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                      "? := Xer_Tms_Pkg2.TMS_DEL_PASE_TRASLADO_FNC(?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";

             stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            //((OraclePreparedStatement)stmt).setString(1, p_respuesta);
            ((OraclePreparedStatement)stmt).setString(2,folio);
            ((OraclePreparedStatement)stmt).setString(3,usuario);
            stmt.registerOutParameter(1,java.sql.Types.VARCHAR);  
            bResultado=stmt.execute();
            p_respuesta = stmt.getString(1);
            stmt.close();
            System.out.println("p_respuesta "+p_respuesta+ " bResultado"+bResultado);
            //System.out.println("Bean IniciarSesion - "+p_respuesta);
           if(cnx!=null) cnx.close();
           //System.out.println("");
           return p_respuesta;
        } catch (SQLException ex) {
            try {
                p_respuesta = stmt.getString(1);
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                p_respuesta="Algo no salio bien";
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("Excepcion Bean IniciarSesion - "+p_respuesta);
                return p_respuesta;
            }
            
        }
   }
  
    
}
