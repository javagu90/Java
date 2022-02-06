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
import javax.ejb.EJBException;
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
public class TmsSesionBeanFacturarTMSBean implements solicitud.TmsSesionBeanFacturarTMSRemote {
    @PersistenceContext(unitName = "TmsFacturarCodigoBarrasTMS-ejbPU")
    private EntityManager manager;    
    @Resource(name = "TMS_DB")
   
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
    
     
          String dblink = new String(link1.get(0).toString());
          String origen = new String(link1.get(1).toString());
        
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
   /*
   public Vector buscarClienteRFC(String RFC)
   {
       Vector temp =  null;
       //System.out.println("BUSCAR CLIENTE POR RFC");
       try{
           temp = (Vector) manager.createNativeQuery("SELECT * FROM TMS_CLIENTES_TBL WHERE rfc = '"+RFC+"'").getSingleResult();
           //System.out.println(" Todo bien " + temp);
           
       }catch(Exception e)
       {
           System.out.println("exception "+e);
           return null;
       }
       return temp;
   }*/
   
   /*public Vector buscarClienteNombre(String nombres)
   {
       Vector temp =  null;
       //System.out.println("BUSCAR CLIENTE POR Nombre");
       try{
           temp = (Vector) manager.createNativeQuery("SELECT * FROM TMS_CLIENTES_TBL WHERE UPPER(REPLACE(nombre, ' ')) = UPPER(REPLACE('"+nombres+"', ' '))"+
                                                     " and rownum = 1").getSingleResult();
           
           //System.out.println(" Todo bien " + temp);
           
       }catch(Exception e)
       {
           System.out.println("exception "+e);
           return null;
       }
       return temp;
   }
    * 
    */
   
   public String insertarcliente(int par, String nombre, String calle, String interior,String exterior, String col,String cp,String mun,String cd,String edo, String usuario, String rfc, String rfc_ant)
    throws javax.ejb.EJBException{
        String p_respuesta = null;
        Connection cnx = null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        //System.out.println(par + " "+nombre+" "+apaterno+" "+amaterno + " "+rfc+" "+rfc_ant);
        String apaterno ="";
        String amaterno = "";
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                      "? := Xer_Tms_Pkg.TMS_INSERTAR_CLIENTE_FNC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
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
  
  public Vector buscarporFolioPreimpreso(String folio_preimpreso){
      Vector temp = new Vector();
      String ref = "";
      try {
          System.out.println(" folio_preimpreso "+folio_preimpreso + " " + folio_preimpreso.substring(0,3));
          
         String qry ="SELECT t.NOMBRE_DBLINK, T.NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL t where t.TERMINAL_ID = (SELECT Xer_Dbms_Numsystem.hex2dec('"+folio_preimpreso.substring(0,2)+"') FROM dual)";
        System.out.println("Buscando DBLINK "+qry);
       
        Vector link1 = (Vector) manager.createNativeQuery(qry).getSingleResult();
     
       
        System.out.println("Buscando DBLINK "+link1);
        
         String dblink = new String(link1.get(0).toString());
         System.out.println(dblink);
          
          // Original
          // SELECT adicional6 FROM TMS_BOLETOS_VENTA_TBL WHERE folio_preimpreso = '"+folio_preimpreso+"' and tipo_transaccion = 'L'
           String query = "SELECT adicional6,empresa,folio_boleto, ( SELECT to_char(sysdate,'MM/YYYY') FROM dual) hoy, to_char(FECHA_HORA_VENTA,'MM/YYYY') venta,FOLIO_PREIMPRESO FROM TMS_BOLETOS_VENTA_TBL@"+dblink+" WHERE adicional6 = '"+folio_preimpreso+"' "; // and tipo_transaccion = 'L'
           System.out.println("query "+query);
           temp = (Vector) manager.createNativeQuery(query).getSingleResult();
           System.out.println("TEMP "+temp);
          // ref = temp.get(0).toString();
           
            System.out.println("buscarFolioPreimpreso Result  "+temp);
           if (temp != null ){
                String Seriepreimpresa = temp.get(2).toString();
                query = "SELECT adicional4 FROM TMS_BOLETOS_VENTA_TBL@"+dblink+" WHERE adicional4 = '"+Seriepreimpresa+"' ";  
                System.out.println("Buscando si ya exite en el adiconal4 la SeriePreimpresa  "+query);
                try{
                Vector VBolNoVal = (Vector) manager.createNativeQuery(query).getSingleResult();
                              
                temp.addElement("1"); 
                }
                catch(Exception e)
                      {
                          System.out.println("buscarporFolioPreimpreso Adicional4 "+e);
                          temp.addElement("0"); 
                      }
                
           }
           
      }
      catch(Exception e)
      {
          System.out.println("buscarporFolioPreimpreso "+e);
          return null;
      }
      System.out.println(" temp "+temp);
      return temp;
   
  }
  
  // BRA
  public Vector buscarFolioPreimpreso(String folio_preimpreso){
      Vector VFolioPre = new Vector(); 
      String Seriepreimpresa = "";  
      try {
          System.out.println(" folio_preimpreso "+folio_preimpreso + " " + folio_preimpreso.substring(0,3));
          
           String query = "SELECT FOLIO_PREIMPRESO,empresa,folio_boleto, ( SELECT to_char(sysdate,'MM/YYYY') FROM dual) hoy, to_char(FECHA_HORA_VENTA,'MM/YYYY') venta,'' FROM TMS_BOLETOS_VENTA_TBL WHERE folio_preimpreso = '"+folio_preimpreso+"' ";  //and tipo_transaccion = 'L'
           System.out.println("query "+query);
          
          // temp = (Vector) manager.createNativeQuery("SELECT FOLIO_PREIMPRESO FROM TMS_BOLETOS_VENTA_TBL WHERE folio_preimpreso = '"+folio_preimpreso+"' and tipo_transaccion = 'L'").getSingleResult();
            VFolioPre = (Vector) manager.createNativeQuery(query).getSingleResult();
           
           System.out.println("buscarFolioPreimpreso Result  "+VFolioPre);
           if (VFolioPre != null ){
                Seriepreimpresa = VFolioPre.get(2).toString();
                query = "SELECT adicional4 FROM TMS_BOLETOS_VENTA_TBL WHERE adicional4 = '"+Seriepreimpresa+"' ";  
                System.out.println("Buscando si ya exite en el adiconal4 la SeriePreimpresa  "+query);
                try{
                Vector VBolNoVal = (Vector) manager.createNativeQuery(query).getSingleResult();
                System.out.println(" VBolNoVal "+VBolNoVal);
               
                VFolioPre.addElement("1"); 
                }
                catch(Exception e)
                      {
                          System.out.println("buscarporFolioPreimpreso Adicional4 "+e);
                          VFolioPre.addElement("0"); 
                      }
                
           }
      }
      catch(Exception e)
      {
          System.out.println("buscarporFolioPreimpreso "+e);
          return null;
      }
      System.out.println(" VFolioPre "+VFolioPre);
      return VFolioPre;
   
  }
  
  // BRA
    public Vector buscarCodigoBarrasReferencia(String referencia ) {      
     System.out.println("referencia "+referencia);
  
     Vector temp = null;
     Vector temp1 = null;
   
         try {
             String qry= "SELECT * FROM TMS_BOLETOS_VENTA_TBL WHERE folio_preimpreso = '"+referencia+"'  AND tipo_transaccion='L'";
             
             System.out.println("Qry "+qry);
             temp = (Vector) manager.createNativeQuery(qry).getSingleResult();
             
             if(temp != null ) {  // temp.get(20).toString(.equals(origen)
                 qry= "SELECT TO_CHAR(FECHA_HORA_VENTA, 'dd/mm/rrrr') FROM TMS_BOLETOS_VENTA_TBL WHERE folio_preimpreso = '"+referencia+"'  AND tipo_transaccion='L'";
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
      
     
    
    
 
    
  /*
  public Vector GeneraFactura( String P_DATOS_RECEPTOR,
  String P_RFC_EMISOR,  String P_DATOS_FACTURA,  String slineasfacturas,//LineasFactura lineasfacturas[] ,
  String P_TOTALES,  String P_IMPUESTO,  String P_RETENCION,  String  P_TRASLADO, String  P_ANEXO20 ) {
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
         System.out.println("P_TRASLADO "+P_ANEXO20);
        
        Connection cnx=null;
        OracleCallableStatement stmt=null;
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+     
                     "Xer_Tms_Pkg2.Factura_Elect_PRC(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); "+
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
              ((OraclePreparedStatement)stmt).setString(9, P_ANEXO20);
              
            System.out.println("ENTRANDO a GeneraFactura 7");
            stmt.registerOutParameter(10,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(11,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(12,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(13,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(14,java.sql.Types.VARCHAR);
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
    }*/
  
  
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


 public String getTerminalID(String NombreTerminal)
    {  
      //String rfc = null ;
      Vector resultado;
      String qry ="SELECT LPAD(TERMINAL_ID,4,'0') FROM TMS_BASE_DATOS_CONFIG_TBL BDC "+
                  " WHERE BDC.NOMBRE_TERMINAL='"+NombreTerminal+"' ";
              
             
      try {
          System.out.println("get getTerminalID"+qry);
           
           resultado =(Vector)manager.createNativeQuery(qry).getSingleResult();
           
       } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
      System.out.println("get getTerminalID"+resultado);
      return resultado.elementAt(0).toString().trim(); 
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
 
 
  public Vector getParamTerminal(String Parametros, String Sucursal_Nombre)
    {  
      //String rfc = null ;
      Vector resultado;
      String qry ="SELECT TP.PARAMETRO_VALOR"+
                  " FROM  TMS_PARAMETROS_CONFIG_TBL P, "+
                  " TMS_TERMINAL_PARAMETROS_TBL TP "+
                  " WHERE "+
                  " P.PARAMETRO_CODIGO IN("+Parametros+") "+
                  " AND TP.PARAMETRO_CONFIG_ID =P.PARAMETRO_CONFIG_ID  "+
                  " AND TP.TERMINAL_ID=  (SELECT TERMINAL_ID from TMS_BASE_DATOS_CONFIG_TBL where NOMBRE_TERMINAL ='"+ Sucursal_Nombre+"') " +
                  "  ORDER BY PARAMETRO_CODIGO";
                  
             
      try {
          System.out.println("get RFC "+qry);
           //rfc= em.createNativeQuery(qry).getSingleResult().toString();
          
           resultado =(Vector)manager.createNativeQuery(qry).getResultList();
           
       } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
      return resultado; 
    }
 


  //---------------  VAlida boleto
  public Vector ValidaBoletoAFacturarPRC(String CiudadVentaID, String Referencia, String FolioPreimpreso)
  {
   Vector Result = null;
   try {
       
       System.out.println("CiudadVentaID "+CiudadVentaID);
         System.out.println("Referencia "+Referencia);
         System.out.println("FolioPreimpreso "+FolioPreimpreso);
         
        Connection cnx=null;
        OracleCallableStatement stmt=null;
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+     
                     "XER_TMS_PKG3.TMS_VALIDA_BOL_FACT_PRC (?,?, ?, ?, ?,  ?,?,?,?,?,?,?,?,?,?,?,? ,?,? ); "+
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
    
           ((OraclePreparedStatement)stmt).setString(1, CiudadVentaID);
            ((OraclePreparedStatement)stmt).setString(2, Referencia);
            ((OraclePreparedStatement)stmt).setString(3, FolioPreimpreso);
            
            System.out.println("ENTRANDO a Validar Bol a Factura 7");
            stmt.registerOutParameter(4,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(7,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(8,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(9,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(10,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(11,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(12,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(13,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(14,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(15,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(16,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(17,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(18,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(19,java.sql.Types.VARCHAR);

                     
           
                boolean bResultado=stmt.execute();
            System.out.println("ENTRANDO a GeneraFactura 9");  
            
            String  status = stmt.getString(4);
            String error = stmt.getString(5);
            
            Result= new Vector();
            Result.addElement(status);   
            Result.addElement(error);
            
            Result.addElement(stmt.getString(6));
            Result.addElement(stmt.getString(7));
            Result.addElement(stmt.getString(8));
            Result.addElement(stmt.getString(9));
            Result.addElement(stmt.getString(10));
            Result.addElement(stmt.getString(11));
            Result.addElement(stmt.getString(12));
            Result.addElement(stmt.getString(13));
            Result.addElement(stmt.getString(14));
            Result.addElement(stmt.getString(15));
            Result.addElement(stmt.getString(16));
            Result.addElement(stmt.getString(17));
            Result.addElement(stmt.getString(18));
            Result.addElement(stmt.getString(19));
               
            System.out.println("GeneraFactura.P_RESULTADO = "+Result);
              
            stmt.close();
            if(cnx!=null) cnx.close();

        } catch (SQLException ex) {  
            ex.printStackTrace();
            Result = null;;
            
            
            try {
                if(stmt!=null) stmt.close();
                if(cnx!=null) cnx.close(); 
            } catch (Exception e1) {
               e1.printStackTrace(); 
            }
            return null;
        }    
   return Result;
   }
  //       End valida boelto  
  

 public Vector getCiudadVentaList()
    {  
    
      Vector resultado;
      String qry =" SELECT terminal_id , nombre_terminal FROM TMS_BASE_DATOS_CONFIG_TBL BDC "+
                  " WHERE NOMBRE_TERMINAL <>'CENTRAL' ORDER BY nombre_terminal ";
      try {
          System.out.println("get RFC "+qry);
           resultado =(Vector)manager.createNativeQuery(qry).getResultList();
           
       } catch (Exception e) {
          e.printStackTrace();
          return null;
      }
      return resultado; 
    }  
  
}
