/*
 * TmsSesionBeanFacturarBean.java
 *
 * Created on 7 de enero de 2009, 09:22 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package solicitud;

import entidad.TmsBoletosVentaTbl;
import entidad.TmsClientesTbl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.driver.OracleCallableStatement;

/**
 *
 * @author asolis
 */
@Stateless
public class TmsSesionBeanFacturarBean implements solicitud.TmsSesionBeanFacturarRemote {
    @PersistenceContext(unitName = "TmsFacturarCodigoBarras-ejbPU")
    private EntityManager manager;    
    //@Resource(name = "TMS_DB")
    @Resource(name = "TMS_CENTRAL_DB")
    private DataSource dataSource;
  
    ResultSet rs = null;
    Connection cnx=null;
    OracleCallableStatement stmt=null;
      
    public String fecha(){
        String fecha = manager.createNativeQuery("select to_char(sysdate, 'dd/mm/yyyy') from dual").getSingleResult().toString();
        return fecha.subSequence(1, fecha.length()-1).toString();
    }
    
    public String validar(String hora, String boletoid){
        String valido = null;
        int numero = 0;    
        try{
            //System.out.println("select to_char(sysdate,'DD/MM/RRRR') from dual");
            String hora_act = manager.createNativeQuery("select to_char(sysdate,'DD/MM/RRRR') from dual").getSingleResult().toString();
            char array[] = new char[hora_act.length()-2];
            for(int i = 1; i < hora_act.length()-1; i++)
                array[i-1] = hora_act.charAt(i);
            hora_act = new String(array);
            //System.out.println(hora_act);
            //System.out.println("select to_date('"+hora+"','DD/MM/RRRR') - to_date('"+ hora_act +"','DD/MM/RRRR') from dual");
            /*valido = manager.createNativeQuery("select trunc(to_date('"+hora+"','DD/MM/RRRR HH24:mi')) - to_date('"+ hora_act +"','DD/MM/RRRR') from dual").getSingleResult().toString();
            array = new char[valido.length()-2];
            for(int i = 1; i < valido.length()-1; i++)
                array[i-1] = valido.charAt(i);
            valido = new String(array);
            numero = Integer.parseInt(valido);
            if (numero > 1) {
                valido = "[3]";
            }
            if (numero < 0) {
                valido = "[0]";
            }
            if ((numero < 1)&&(numero >= 0)) {
                valido = "[1]";
            }*/
            valido = "[1]";
         }catch(Exception e){
             System.out.println(e + "No halle Datos");
             hora = null;
             valido = "[2]";
         }         
          return valido;
    }
    
   public Vector buscarCodigoBarras(String referencia) {      
     System.out.println("referencia "+referencia);
  
     Vector temp = null;
     Vector temp1 = null;
     
     Vector link1 = (Vector) manager.createNativeQuery("SELECT t.NOMBRE_DBLINK, T.NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL t where t.TERMINAL_ID = (SELECT Xer_Dbms_Numsystem.hex2dec('"+referencia.substring(0,2)+"') FROM dual)").getSingleResult();
        /*char array[] = new char[link1.length()-2];
         for(int i = 1; i < link1.length()-1; i++)
         array[i-1] = link1.charAt(i);*/
         String dblink = new String(link1.get(0).toString());
         //System.out.println(dblink);
         String origen = new String(link1.get(1).toString());
         //System.out.println(origen);
         try {
             System.out.println("SELECT * FROM TMS_BOLETOS_VENTA_TBL@"+dblink+" WHERE adicional6 = '"+referencia+"'");
             temp = (Vector) manager.createNativeQuery("SELECT * FROM TMS_BOLETOS_VENTA_TBL@"+dblink+" WHERE adicional6 = '"+referencia+"'").getSingleResult();
             if(temp.get(20).toString().equals(origen)){
                 System.out.println("SELECT TO_CHAR(FECHA_HORA_VENTA, 'dd/mm/rrrr') FROM TMS_BOLETOS_VENTA_TBL@"+dblink+" WHERE adicional6 = '"+referencia+"'");
                 temp1 = (Vector) manager.createNativeQuery("SELECT TO_CHAR(FECHA_HORA_VENTA, 'dd/mm/rrrr') FROM TMS_BOLETOS_VENTA_TBL@"+dblink+" WHERE adicional6 = '"+referencia+"'").getSingleResult();
                 temp.add(24,temp1.get(0).toString());
                 System.out.println("SELECT TO_CHAR(FECHA_HORA_CORRIDA, 'dd/mm/rrrr hh24:mi') FROM TMS_corridas_VENTA_TBL@"+dblink+" WHERE clave_corrida = '"+temp.get(5)+"'");
                 temp1 = (Vector) manager.createNativeQuery("SELECT TO_CHAR(FECHA_HORA_CORRIDA, 'dd/mm/rrrr hh24:mi') FROM TMS_corridas_VENTA_TBL@"+dblink+" WHERE clave_corrida = '"+temp.get(5)+"'").getSingleResult();
                 temp.add(temp1.get(0).toString()); 
                 temp.add(dblink);
             }
             else
                 temp = null;
         }catch(Exception e){
             System.out.println(e);
             temp = null;
         }
         return temp;
   }


   public String insertarcliente(int par, String nombre, String calle, String interior,String exterior, String col,String cp,String mun,String cd,String edo, String usuario, String rfc, String rfc_ant,String email)
    throws javax.ejb.EJBException{
        String p_respuesta = null;
        Connection cnx = null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        //System.out.println(par + " "+nombre+" "+apaterno+" "+amaterno + " "+rfc+" "+rfc_ant);
        String apaterno ="";
        String amaterno = "";
        System.out.println("**************   Guardando Email "+email);
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                      "? := Xer_Tms_Pkg.TMS_INSERTAR_CLIENTE_FNC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
             //System.out.println("ql "+q1);  
             stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            //((OraclePreparedStatement)stmt).setString(1, p_respuesta);
            ((OraclePreparedStatement)stmt).setString(2, String.valueOf(par));
            ((OraclePreparedStatement)stmt).setString(3, nombre);
            ((OraclePreparedStatement)stmt).setString(4, apaterno);
            ((OraclePreparedStatement)stmt).setString(5, amaterno);
            ((OraclePreparedStatement)stmt).setString(6, calle);
            ((OraclePreparedStatement)stmt).setString(7, interior);
            ((OraclePreparedStatement)stmt).setString(8, exterior);
            ((OraclePreparedStatement)stmt).setString(9, col);
            ((OraclePreparedStatement)stmt).setString(10, cp);
            ((OraclePreparedStatement)stmt).setString(11, mun);
            ((OraclePreparedStatement)stmt).setString(12, cd);
            ((OraclePreparedStatement)stmt).setString(13, edo);
            ((OraclePreparedStatement)stmt).setString(14, usuario);
            ((OraclePreparedStatement)stmt).setString(15, rfc);
            ((OraclePreparedStatement)stmt).setString(16, rfc_ant);
            ((OraclePreparedStatement)stmt).setString(17, email);
            stmt.registerOutParameter(1,java.sql.Types.VARCHAR);  
            bResultado=stmt.execute();
            p_respuesta = stmt.getString(1);
            stmt.close();
            //System.out.println("p_respuesta "+p_respuesta+ " bResultado"+bResultado);
            //System.out.println("Bean IniciarSesion - "+p_respuesta);
           if(cnx!=null) cnx.close();
           //System.out.println("");
           return p_respuesta;
        } catch (SQLException ex) {
            try {
                ex.printStackTrace();
                p_respuesta = stmt.getString(1);
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                p_respuesta="ALGO NO SALIO BIEN";
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("Excepcion Bean IniciarSesion - "+p_respuesta);
                return p_respuesta;
            }
        }
   }
        
   public String insertarBoletosFacturados(String id_factura, String folios, String ids, String rfc, String usuario, String dblink, String RutaPDF, String LlaveFactura)
            throws javax.ejb.EJBException{
        String p_respuesta = null;
        Connection cnx = null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        try {
             System.out.println("insertarBoletosFacturados  Llamando a Xer_Tms_Pkg.TMS_INS_BOLETO_FACTURADO_FNC ");
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                      "? := Xer_Tms_Pkg.TMS_INS_BOLETO_FACTURADO_FNC(?,?,?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";

             stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            //((OraclePreparedStatement)stmt).setString(1, p_respuesta);
            ((OraclePreparedStatement)stmt).setString(2,id_factura );
            ((OraclePreparedStatement)stmt).setString(3, folios);
            ((OraclePreparedStatement)stmt).setString(4, ids);
            ((OraclePreparedStatement)stmt).setString(5, rfc);
            ((OraclePreparedStatement)stmt).setString(6, usuario);
            ((OraclePreparedStatement)stmt).setString(7, RutaPDF);  
            ((OraclePreparedStatement)stmt).setString(8, LlaveFactura);  
            stmt.registerOutParameter(1,java.sql.Types.VARCHAR);  
            bResultado=stmt.execute();
            p_respuesta = stmt.getString(1);
            
            System.out.println("insertarBoletosFacturados  Result  "+p_respuesta);
            stmt.close();
            /*System.out.println("p_respuesta "+p_respuesta+ " bResultado"+bResultado);
            System.out.println("Bean IniciarSesion - "+p_respuesta);*/
           if(cnx!=null) cnx.close();
          // System.out.println("");
           return p_respuesta;
        } catch (SQLException ex) {
            try {
                ex.printStackTrace();
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
            e.printStackTrace();
            p_respuesta="Algo no salio bien";
        }
        return p_respuesta;
   }
   
   public String eliminarBoletosFacturados(String id_factura, String dblink, String usuario)
            throws javax.ejb.EJBException{
        String p_respuesta = null;
        Connection cnx = null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                      "? := Xer_Tms_Pkg.TMS_DEL_BOLETO_FACTURADO_FNC(?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";

             stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            //((OraclePreparedStatement)stmt).setString(1, p_respuesta);
            ((OraclePreparedStatement)stmt).setString(2,id_factura);
            ((OraclePreparedStatement)stmt).setString(3,usuario);
            stmt.registerOutParameter(1,java.sql.Types.VARCHAR);  
            bResultado=stmt.execute();
            p_respuesta = stmt.getString(1);
            stmt.close();
            //System.out.println("p_respuesta "+p_respuesta+ " bResultado"+bResultado);
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
   
  public Object buscarEstadoSesion(long sesionId, String dblink){
      System.out.println("select sg.ESTADO_SESION from tms_sesiones_global_tbl@"+dblink+" sg where sg.NUMERO_SESION = "+sesionId);
     String consulta =  "select sg.ESTADO_SESION from tms_sesiones_global_tbl@"+dblink+" sg where sg.NUMERO_SESION = "+sesionId;
     return manager.createNativeQuery(consulta).getSingleResult();
 }
  
  public Vector buscarImpresoraFacturas(String nombreCaja) {
    String query  = "select  imp.IMPRESORA_NOMBRE  from TMS_CAJAS_IMPRESORAS_TBL cimp " +
                ",TMS_IMPRESORAS_TBL imp  "+
                "where cimp.CAJA_ID = (SELECT caj.CAJA_ID FROM Tms_Cajas_Tbl caj WHERE caj.nombre_Equipo = '"+ nombreCaja +"') " +
                "and   imp.IMPRESORA_ID = cimp.IMPRESORA_ID  " +                               
                "and cimp.ACTIVIDAD_IMPRESORA = 'FACTURAS' ";
     System.out.println("buscarImpresoraFacturas  "+query);  
        return (Vector)manager.createNativeQuery(query).getResultList();
    }
  
  public String buscarporFolioPreimpreso(String folio_preimpreso){
      Vector temp = new Vector();
      String ref = "";
      try {
          System.out.println(" folio_preimpreso "+folio_preimpreso + " " + folio_preimpreso.substring(0,3));
          
           /*if(folio_preimpreso.substring(0,3).equals("115"))  //Es una venta por interntet
                temp = (Vector) manager.createNativeQuery("SELECT NOMBRE_DBLINK FROM TMS_BASE_DATOS_CONFIG_TBL WHERE nombre_terminal = (SELECT origen FROM TMS_BOLETOS_VENTA_TBL WHERE folio_preimpreso = '"+folio_preimpreso+"')").getSingleResult();
           else
               temp =   (Vector) manager.createNativeQuery("SELECT NOMBRE_DBLINK FROM TMS_BASE_DATOS_CONFIG_TBL WHERE TERMINAL_ID = SUBSTR('"+folio_preimpreso+"',1,3)").getSingleResult();

           String dblink = temp.get(0).toString();
           System.out.println("dblink factura "+dblink);*/
          // Original
          // SELECT adicional6 FROM TMS_BOLETOS_VENTA_TBL WHERE folio_preimpreso = '"+folio_preimpreso+"' and tipo_transaccion = 'L'
           String query = "SELECT adicional6 FROM TMS_BOLETOS_VENTA_TBL WHERE adicional6 = '"+folio_preimpreso+"' and tipo_transaccion = 'L'";
           System.out.println("query "+query);
           temp = (Vector) manager.createNativeQuery(query).getSingleResult();
           System.out.println("TEMP "+temp);
           ref = temp.get(0).toString();
      }
      catch(Exception e)
      {
          System.out.println("buscarporFolioPreimpreso "+e);
          return null;
      }
      
      return ref;
   
  }
  
  // BRA
  public String buscarFolioPreimpreso(String folio_preimpreso){
      Vector VFolioPre = new Vector();
      String ref = "";  
      try {
          System.out.println(" folio_preimpreso "+folio_preimpreso + " " + folio_preimpreso.substring(0,3));
          
           String query = "SELECT FOLIO_PREIMPRESO FROM TMS_BOLETOS_VENTA_TBL WHERE folio_preimpreso = '"+folio_preimpreso+"' and tipo_transaccion = 'L'";
           System.out.println("query "+query);
          
          // temp = (Vector) manager.createNativeQuery("SELECT FOLIO_PREIMPRESO FROM TMS_BOLETOS_VENTA_TBL WHERE folio_preimpreso = '"+folio_preimpreso+"' and tipo_transaccion = 'L'").getSingleResult();
            VFolioPre = (Vector) manager.createNativeQuery(query).getSingleResult();
           
           System.out.println("buscarFolioPreimpreso Result  "+VFolioPre);
           ref = VFolioPre.get(0).toString();
      }
      catch(Exception e)
      {
          System.out.println("buscarporFolioPreimpreso "+e);
          return null;
      }
      
      return ref;
   
  }
  
  // BRA
    public Vector buscarCodigoBarrasReferencia(String referencia) {      
     System.out.println("referencia "+referencia);
  
     Vector temp = null;
     Vector temp1 = null;
     
     // Busca el link
     //Vector link1 = (Vector) manager.createNativeQuery("SELECT t.NOMBRE_DBLINK, T.NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL t where t.TERMINAL_ID = (SELECT Xer_Dbms_Numsystem.hex2dec('"+referencia.substring(0,2)+"') FROM dual)").getSingleResult();
    // String qry ="SELECT t.NOMBRE_DBLINK, T.NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL t where t.TERMINAL_ID = "+referencia.substring(0,3);
    // System.out.println("Buscando DBLINK "+qry);
    // Vector link1 = (Vector) manager.createNativeQuery(qry).getSingleResult();
        /*char array[] = new char[link1.length()-2];
         for(int i = 1; i < link1.length()-1; i++)
         array[i-1] = link1.charAt(i);*/
      //  System.out.println("Buscando Link "+link1);
        
       //  String dblink = new String(link1.get(0).toString());
         //System.out.println(dblink);
       //  String origen = new String(link1.get(1).toString());
         //System.out.println(origen);
         try {
             String qry= "SELECT * FROM TMS_BOLETOS_VENTA_TBL WHERE folio_preimpreso = '"+referencia+"'";
             System.out.println("Qry "+qry);
             temp = (Vector) manager.createNativeQuery(qry).getSingleResult();
             
             if(temp != null ) {  // temp.get(20).toString(.equals(origen)
                 qry= "SELECT TO_CHAR(FECHA_HORA_VENTA, 'dd/mm/rrrr') FROM TMS_BOLETOS_VENTA_TBL WHERE folio_preimpreso = '"+referencia+"'";
                 System.out.println(qry);
                 temp1 = (Vector) manager.createNativeQuery(qry).getSingleResult();
                 temp.add(24,temp1.get(0).toString());
                 
                 qry = "SELECT TO_CHAR(FECHA_HORA_CORRIDA, 'dd/mm/rrrr hh24:mi') FROM TMS_corridas_VENTA_TBL WHERE clave_corrida = '"+temp.get(5)+"'";
                 System.out.println("Buscando Corrida  "+qry);
                 temp1 = (Vector) manager.createNativeQuery(qry).getSingleResult();
                 temp.add(temp1.get(0).toString()); 
                 temp.add("");
             }
             else
                 temp = null;
         }catch(Exception e){
             System.out.println(e);
             temp = null;  
         }
         return temp;
   }
  
  public Vector buscarBoletoNombre(String nombre, String fechaHoraCorrida, String origen, String destino, String servicio, String fecha){              
       System.out.println("nombre "+nombre+" fechaHoraCorrida "+fechaHoraCorrida+" origen "+origen+" destino "+destino+ " servicio "+servicio +" fecha "+fecha);
       String central_link = manager.createNativeQuery("select nombre_dblink from TMS_BASE_DATOS_CONFIG_TBL where nombre_terminal = 'CENTRAL'").getSingleResult().toString();
       central_link = central_link.replace('[', ' ');
       central_link = central_link.replace(']', ' ');
       central_link = central_link.trim();
       Vector resultado = null;
       try {
           System.out.println("SELECT bv.folio_preimpreso,bv.NOMBRE_PASAJERO, "+
                                   "bv.NO_ASIENTO, bv.TIPO_PAGO, " +
                                   "CASE bv.TIPO_PASAJERO WHEN 'A' THEN 'ADULTO' WHEN 'M' THEN 'MENOR' WHEN 'P' THEN 'PROFESOR' WHEN 'E' THEN 'ESTUDIANTE' WHEN 'S' THEN 'SENECTUD' WHEN 'V' THEN 'MENOR VOLARIS' WHEN 'C' THEN 'ESPECIAL' WHEN 'U' THEN 'ESTUDIANTE SMA' END tipoPasajero , " +                                   
                                   "to_char(bv.FECHA_HORA_VENTA, 'dd/mm/yyyy hh24:mi'), " +
                                   "bv.clave_corrida,"+
                                   "to_char(cv.FECHA_HORA_CORRIDA, 'dd/mm/yyyy hh24:mi'), "+
                                   "bv.ORIGEN, " +
                                   "bv.DESTINO, " +
                                   "bv.CAJA, " +
                                   "CASE bv.TIPO_OPERACION WHEN 'VT' THEN 'NORMAL' WHEN 'VA' THEN 'BOL ABIERTO' WHEN 'FO' THEN 'CAMBIO HORARIO' END "+
                                   "FROM "+
                                   "TMS_BOLETOS_VENTA_TBL@"+central_link+" bv "+
                                   ",TMS_CORRIDAS_VENTA_TBL@"+central_link+" CV "+
                                   /*"TMS_BOLETOS_VENTA_TBL bv "+
                                   ",TMS_CORRIDAS_VENTA_TBL CV "+*/
                                   "where bv.caja = 'CAJAWEB' "+
                                   "AND UPPER(REPLACE(bv.nombre_pasajero, ' ')) LIKE '%"+nombre+"%'"+
                                   "AND bv.clave_corrida = CV.clave_corrida "+
                                   "AND (TRUNC(bv.fecha_creacion) = to_date(NVL("+fecha+",TRUNC(SYSDATE)), 'dd/mm/yyyy') OR TRUNC(CV.fecha_hora_corrida) =  to_date(NVL("+fechaHoraCorrida+", TRUNC(SYSDATE)), 'dd/mm/yyyy'))"+
                                   "AND CV.ORIGEN LIKE '%"+origen+"%' "+
                                   "AND CV.destino LIKE '%"+destino+"%' "+
                                   "AND CV.servicio LIKE '%"+servicio+"%' ");
            resultado = (Vector) manager.createNativeQuery("SELECT bv.folio_preimpreso, bv.NOMBRE_PASAJERO, "+
                                   "bv.NO_ASIENTO, bv.TIPO_PAGO, " +
                                   "CASE bv.TIPO_PASAJERO WHEN 'A' THEN 'ADULTO' WHEN 'M' THEN 'MENOR' WHEN 'P' THEN 'PROFESOR' WHEN 'E' THEN 'ESTUDIANTE' WHEN 'S' THEN 'SENECTUD' WHEN 'V' THEN 'MENOR VOLARIS' WHEN 'C' THEN 'ESPECIAL' WHEN 'U' THEN 'ESTUDIANTE SMA' END tipoPasajero , " +                                   
                                   "to_char(bv.FECHA_HORA_VENTA, 'dd/mm/yyyy hh24:mi'), " +
                                   "bv.clave_corrida,"+
                                   "to_char(cv.FECHA_HORA_CORRIDA, 'dd/mm/yyyy hh24:mi'), "+
                                   "bv.ORIGEN, " +
                                   "bv.DESTINO, " +
                                   "bv.CAJA, " +
                                   "CASE bv.TIPO_OPERACION WHEN 'VT' THEN 'NORMAL' WHEN 'VA' THEN 'BOL ABIERTO' WHEN 'FO' THEN 'CAMBIO HORARIO' END "+
                                   "FROM "+
                                   "TMS_BOLETOS_VENTA_TBL@"+central_link+" bv "+
                                   ",TMS_CORRIDAS_VENTA_TBL@"+central_link+" CV "+
                                   //"TMS_BOLETOS_VENTA_TBL bv "+
                                   //",TMS_CORRIDAS_VENTA_TBL CV "+
                                   "where bv.caja = 'CAJAWEB' "+
                                   "AND UPPER(REPLACE(bv.nombre_pasajero, ' ')) LIKE '%"+nombre+"%'"+
                                   "AND bv.clave_corrida = CV.clave_corrida "+
                                   "AND (TRUNC(bv.fecha_creacion) = to_date(NVL("+fecha+",TRUNC(SYSDATE)), 'dd/mm/yyyy') OR TRUNC(CV.fecha_hora_corrida) =  to_date(NVL("+fechaHoraCorrida+", TRUNC(SYSDATE)), 'dd/mm/yyyy'))"+
                                   "AND CV.ORIGEN LIKE '%"+origen+"%' "+
                                   "AND CV.destino LIKE '%"+destino+"%' "+
                                   "AND CV.servicio LIKE '%"+servicio+"%' ").getResultList();
            System.out.println("resultado buscarBoletoNombre "+resultado);
       }catch(Exception ex) {
           ex.printStackTrace();
           resultado = null;
       }
       return resultado;
   }
   
   public Vector buscarTerminal(){
       Vector resultado = null;
       try {
        System.out.println("SELECT NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL where esquema_propio = 'S'");
        resultado = (Vector) manager.createNativeQuery("SELECT NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL where esquema_propio = 'S'").getSingleResult();
       }catch(Exception e){
           e.printStackTrace();
           resultado = null;
       }
       return resultado;
   }
   
   public Vector buscarOrigenesDestinos(){
       Vector resultado = null;
       try {
        System.out.println("select estado_nombre from tms_estados_tbl where tipo_componente = 'TERMINAL' and estado_nombre <> 'CENTRAL' ORDER BY estado_nombre");
        resultado = (Vector) manager.createNativeQuery("select estado_nombre from tms_estados_tbl where tipo_componente = 'TERMINAL' and estado_nombre <> 'CENTRAL' ORDER BY estado_nombre").getResultList();        
        Vector vectorsote = new Vector();
        for(int j = 0; j < resultado.size(); j++)     
            vectorsote.add(((Vector)resultado.get(j)).get(0).toString());        
        resultado = vectorsote;
        vectorsote.add(0, "TODOS...");
       }catch(Exception e){
           e.printStackTrace();
           resultado = null;
       }       
       return resultado;
   }
   
   public Vector buscarServicios(){
       Vector resultado = null;
       try {
           System.out.println("SELECT servicio_nombre FROM TMS_SERVICIOS_TBL");
        resultado = (Vector) manager.createNativeQuery("SELECT servicio_nombre FROM TMS_SERVICIOS_TBL ORDER BY servicio_nombre").getResultList();
       Vector vectorsote = new Vector();
        for(int j = 0; j < resultado.size(); j++)        
            vectorsote.add(((Vector)resultado.get(j)).get(0).toString());
        resultado = vectorsote;
        vectorsote.add(0, "TODOS...");
        }catch(Exception e){
           e.printStackTrace();
           resultado = null;
       }
       return resultado;
   }

   
     
  
 public Vector getParamGlobal(String Parametros)
    {  
      //String rfc = null ;
      Vector resultado;
      String qry =" SELECT GP.PARAMETRO_VALOR "+
                  " FROM TMS_PARAMETROS_CONFIG_TBL P, "+
                  "      TMS_GLOBAL_PARAMETROS_TBL GP "+ 
                  " WHERE  "+
                  " P.PARAMETRO_CODIGO IN("+Parametros+") "+
                  " AND GP.PARAMETRO_CONFIG_ID =( P.PARAMETRO_CONFIG_ID ) ORDER BY PARAMETRO_CODIGO ";
      try {
          System.out.println("get  ParamGlobal RFC "+qry);
           //rfc= em.createNativeQuery(qry).getSingleResult().toString();
          
           resultado =(Vector)manager.createNativeQuery(qry).getResultList();
           
       } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
      return resultado; 
    }
      
     
 public Vector getParamSucursal(String Parametros, String Sucrusal_ID)
    {  
      //String rfc = null ;
      Vector resultado;
      String qry =" SELECT TP.PARAMETRO_VALOR  "+
                 "  FROM  TMS_PARAMETROS_CONFIG_TBL P,  "+
                 "  TMS_TERMINAL_PARAMETROS_TBL TP  WHERE  P.PARAMETRO_CODIGO IN("+Parametros+") "+
                 "  AND TP.PARAMETRO_CONFIG_ID =P.PARAMETRO_CONFIG_ID   AND TP.TERMINAL_ID = (SELECT BDC.TERMINAL_ID "+
                 "  FROM TMS_BASE_DATOS_CONFIG_TBL BDC"+
                 "    WHERE BDC.NOMBRE_TERMINAL ='"+Sucrusal_ID+"')  "+
                 "  ORDER BY PARAMETRO_CODIGO ";
              
             
      try {
          System.out.println("get ParamSucursal"+qry);
           //rfc= em.createNativeQuery(qry).getSingleResult().toString();
          
           resultado =(Vector)manager.createNativeQuery(qry).getResultList();
           
       } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
      return resultado; 
    }
      
     
    
    
   
    
 
  public Vector GeneraFactura( String P_DATOS_RECEPTOR,
  String P_RFC_EMISOR,  String P_DATOS_FACTURA,  String slineasfacturas,//LineasFactura lineasfacturas[] ,
  String P_TOTALES,  String P_IMPUESTO,  String P_RETENCION,  String  P_TRASLADO ) {  
  Vector P_RESULTADO = new Vector();;
   try {
         
       
         System.out.println("P_RFC_EMISOR "+P_RFC_EMISOR);
         System.out.println("P_DATOS_RECEPTOR "+P_DATOS_RECEPTOR);
         System.out.println("Datos Factura "+P_DATOS_FACTURA);
         System.out.println("ENTRANDO a GeneraFactura  Lineas "+slineasfacturas);
       
         System.out.println("P_TOTALES "+P_TOTALES);
         System.out.println("P_IMPUESTO "+P_IMPUESTO);
         System.out.println("P_RETENCION "+P_RETENCION);
         System.out.println("P_TRASLADO "+P_TRASLADO);
        
        Connection cnx=null;
        OracleCallableStatement stmt=null;
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+     
                     "Xer_Tms_Pkg2.Factura_Elect_PRC(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,? ); "+
                     "COMMIT; "+
                    "EXCEPTION "+
                    "WHEN OTHERS THEN "+
                     "ROLLBACK; "+
                     "RAISE; "+ 
                    "END;";
            
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
        
            // Describe el type
            
            //ArrayDescriptor  descCollection = ArrayDescriptor.createDescriptor("LINEAS_FACT_COLLECTION_TYPE", stmt.getConnection());
            //ARRAY arrayLineasFacturas  = null;
    
           
            ((OraclePreparedStatement)stmt).setString(1, P_DATOS_RECEPTOR);
            ((OraclePreparedStatement)stmt).setString(2, P_RFC_EMISOR);
            ((OraclePreparedStatement)stmt).setString(3, P_DATOS_FACTURA);
            
             ((OraclePreparedStatement)stmt).setString(4,slineasfacturas); //ARRAY(4, arrayLineasFacturas);
              //((OraclePreparedStatement)stmt).setCLOB(4,Clineas); //ARRAY(4, arrayLineasFacturas);
              ((OraclePreparedStatement)stmt).setString(5, P_TOTALES);
              ((OraclePreparedStatement)stmt).setString(6, P_IMPUESTO);
              ((OraclePreparedStatement)stmt).setString(7, P_RETENCION);
              ((OraclePreparedStatement)stmt).setString(8, P_TRASLADO);
              
              
            System.out.println("ENTRANDO a GeneraFactura 7");
            stmt.registerOutParameter(9,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(10,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(11,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(12,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(13,java.sql.Types.VARCHAR);
            System.out.println("ENTRANDO a GeneraFactura 8");
            boolean bResultado=stmt.execute();
            System.out.println("ENTRANDO a GeneraFactura 9");
         
            String status = stmt.getString(9);
            String error = stmt.getString(10);
            
            String LlaveFact = stmt.getString(11);
            String rutapdf = stmt.getString(12);
            String rutaxml = stmt.getString(13);
            
            
            P_RESULTADO.addElement(status);   
            P_RESULTADO.addElement(error);
            P_RESULTADO.addElement(LlaveFact);
            P_RESULTADO.addElement(rutapdf);
            P_RESULTADO.addElement(rutaxml);
            
            
            System.out.println("GeneraFactura.P_RESULTADO = "+P_RESULTADO);
              
            stmt.close();
            if(cnx!=null) cnx.close();

        } catch (SQLException ex) {  
            ex.printStackTrace();
            P_RESULTADO = new Vector();
            P_RESULTADO.addElement("RECHAZADA");
            P_RESULTADO.addElement( "No se pudo generar la factura");
            
            try {
                stmt.close();
                if(cnx!=null) cnx.close(); 
            } catch (Exception e1) {
               e1.printStackTrace(); 
            }
            return null;
        }    
    return P_RESULTADO;
    }
  
  
public String  CancelarFact_Elect(String LlaveFactura)
{
    String status= "";  
    try {
        Connection cnx=null;
        OracleCallableStatement stmt=null;
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                     "Xer_Tms_Pkg2.CancelFactura_Elect_PRC(?, ?); "+
                     "COMMIT; "+
                    "EXCEPTION "+
                    "WHEN OTHERS THEN "+
                     "ROLLBACK; "+  
                     "RAISE; "+ 
                    "END;";
            
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
             ((OraclePreparedStatement)stmt).setString(1, LlaveFactura);
            
            stmt.registerOutParameter(2,java.sql.Types.VARCHAR);
            boolean bResultado=stmt.execute();
            System.out.println("ENTRANDO a GenerarFactura Termiando la peticion de la BD");
            
           status = stmt.getString(2);
           System.out.println("status  "+status);
           stmt.close();  
           if(cnx!=null) cnx.close();
         return status;
        } catch (SQLException ex) {  
            ex.printStackTrace();
            
            try {  
             stmt.close();
            if(cnx!=null) cnx.close();   
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return ""; 
        }    
    
} 

public String TieneFactura(String foliopreimpreso)
{
  String BoletoFacturaID="";
      Vector resultado;
      String qry =" SELECT BF.BOL_FACTURADO_ID FROM TMS_BOLETOS_FACTURADOS_TBL BF "+
                  " WHERE BF.FOLIO_PREIMPRESO='"+foliopreimpreso+"' AND  BF.ESTADO='FACTURADO'";
              
             
      try {
          System.out.println("TieneFactura"+qry);
          
           resultado =(Vector)manager.createNativeQuery(qry).getResultList();
           
       } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
      String cade=null;
      if (resultado != null &&  !   resultado.isEmpty() )
          cade=resultado.get(0).toString();
      return cade;       
        
        
}
      
public String getLlaveFactura(String FolioFactura)
{
  String BoletoFacturaID="";
      Vector resultado;
      String qry =" SELECT adicional2 FROM TMS_BOLETOS_FACTURADOS_TBL BF  where BF.folio_factura ='"+FolioFactura+"' AND BF.ESTADO='FACTURADO'";
              
             
      try {
          System.out.println("TieneFactura"+qry);
          
           resultado =(Vector)manager.createNativeQuery(qry).getResultList();
      
           if (resultado != null)
               resultado=(Vector )resultado.elementAt(0);
           
       } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
      String cade=null;
      if (resultado != null &&  !   resultado.isEmpty() )
          cade=resultado.get(0).toString();
      return cade;       
        
        
}
        
public String getRutaPDFFactura(String FolioFactura)
{
  String BoletoFacturaID="";
      Vector resultado;
      String qry =" SELECT adicional1 FROM TMS_BOLETOS_FACTURADOS_TBL BF  where BF.folio_factura ='"+FolioFactura+"'";
             
      try {
          System.out.println("TieneFactura "+qry);
          
           resultado =(Vector)manager.createNativeQuery(qry).getResultList();
           if (resultado != null)
               resultado=(Vector )resultado.elementAt(0);
       } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
      
      String cade=null;
      
      if (resultado != null &&  !   resultado.isEmpty() )
          cade=resultado.get(0).toString();
      return cade;       
        
}


}
