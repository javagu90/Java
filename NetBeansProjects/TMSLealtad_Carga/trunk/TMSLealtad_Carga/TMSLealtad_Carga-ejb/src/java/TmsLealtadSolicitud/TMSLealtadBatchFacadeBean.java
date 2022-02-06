/*
 * TMSLealtadFacadeBean.java
 *
 * Created on 28 de diciembre de 2009, 01:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsLealtadSolicitud;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.internal.OraclePreparedStatement;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.STRUCT;

/**
 *
 * @author brojasa
 */
@Stateless
public class TMSLealtadBatchFacadeBean implements TmsLealtadSolicitud.TMSLealtadBatchFacadeRemote {
   
    @PersistenceContext
    private EntityManager em;
    @Resource(name = "TMS_DB")
    private DataSource dataSource;                
    Connection cnx=null;
    OracleCallableStatement stmt=null ;
    /** Creates a new instance of TMSLealtadFacadeBean */
    // 
            public TMSLealtadBatchFacadeBean() {
    }
    
        public Vector TMSConsultaPorNumeroOperacion(String numeroOperacion){
    //    System.out.println("************** Inicia TMSConsultaPorNumeroOperacion **************");        
        Vector respuesta=new Vector();
        cnx = null;
        stmt = null;
        boolean bResultado;
        Array array;
        Vector status = null;
        Vector registro = null;
        try {
            System.out.println(" EJECUTO PROCEDIMIENTO BATCH  "+getToDate().toString());
            System.out.println("NumeroOperacion "+numeroOperacion);
            
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    //"Xer_Tmslealtad_Pkg.TMSLEALTAD_RECUPERA_PRC(?, ? , ?); " +
                    "Xer_Tmslealtad_Pkg.TMSLEALTAD_RECUPERA_ACUMUL_PRC(?, ? , ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            System.out.println("q1 "+q1);
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, numeroOperacion);
            stmt.registerOutParameter(2,OracleTypes.STRUCT, "TMSLEALTAD_STATUS_TYPE");
            stmt.registerOutParameter(3,OracleTypes.ARRAY, "TMSLEALTAD_COLLECTION_TYPE");
            bResultado=stmt.execute();
            
            
            Object[] attrs=stmt.getSTRUCT(2).getAttributes();                        
            System.out.println("   ********  "+attrs[0]+"   ,   "+attrs[1]);     
            status=new Vector();
            
       /*     for (int i = 0; i < attrs.length; i++) {
                status.add(attrs[i]);
            }
            respuesta.add(status);*/
            array = (ARRAY) ((OracleCallableStatement)stmt).getOracleObject(3);
            ResultSet rs=array.getResultSet();
            
            registro = new Vector();
            while(rs.next()){
                STRUCT obj= (STRUCT)rs.getObject(2);
                Object[] attrs1=obj.getAttributes();
                registro = new Vector();
                registro.add(attrs1[0]); //folio boleto
                registro.add(attrs1[1]); //Producto
                registro.add(attrs1[2]); //Ciudad Venta
                registro.add(attrs1[3]); //Numeto Tarjeta
                registro.add(attrs1[4]); //Tipo Operacion
                registro.add(attrs1[5]); //Usuario
                registro.add(attrs1[6]); //Usuario Contraseña
                registro.add(attrs1[7]); //Importe Boleto
                registro.add(attrs1[8]); //Total
                registro.add(attrs1[9]); //Fecha
                registro.add(attrs1[10]); //Taquilla
                registro.add(attrs1[11]); //Unidad Negocio 
                if(attrs1[12]==null)
                    registro.add("");
                else
                    registro.add(attrs1[12]); //StatusOperacion
                registro.add(attrs1[13]); //Transccion
                System.out.println("registro "+registro);
                respuesta.add(registro);
            }
            stmt.close();
            System.out.println("Bean IniciarSesion TMSConsultaPorNumeroOperacion - "+status);           
            if(cnx!=null) cnx.close();           
        } catch (SQLException ex) {
            try {
                cnx.close();
                stmt.close();    
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            cnx=null;
           ex.printStackTrace();
           System.out.println("Excepcion Bean - "+status+" respuesta "+respuesta);
           status = null;
        }
        return respuesta;
    }
    
  public Vector TMSOperacionesPendientes(){
   String qry = "select distinct(numero_operacion) from XER_PROGRAMA_LEALTAD_TBL where status ='P' AND TIPO_OPERACION IN ('A','C')  order by numero_operacion  ASC "; //AND PROGRAMA_LEALTAD_ID =24" ; 
   System.out.println("GET OPERACIONES PENDIENTES "+qry);
       try {
           
            return  (Vector)em.createNativeQuery(qry).getResultList(); 
        } catch (Exception err) {
           System.err.println("Error TMSOperacionesPendientes : "+err.getMessage());
           
            err.printStackTrace();
            return null;
        }
  
  }

   public Vector TMSAcumulacionesPendientes(){
   String qry = "select distinct(numero_operacion) from XER_PROGRAMA_LEALTAD_TBL where status ='P' AND TIPO_OPERACION = 'A'  order by numero_operacion  ASC "; //AND PROGRAMA_LEALTAD_ID =24" ; 
   System.out.println("GET OPERACIONES PENDIENTES "+qry);
       try {    
           
            return  (Vector)em.createNativeQuery(qry).getResultList(); 
        } catch (Exception err) {
           System.err.println("Error TMSOperacionesPendientes : "+err.getMessage());
           
            err.printStackTrace();
            return null;
        }
  }
  
    public Vector TMSCancelacionesPendientes(){
   String qry = "select numero_operacion,folio_boleto, usuario, usuario_contrasena from XER_PROGRAMA_LEALTAD_TBL where status ='P' AND TIPO_OPERACION = 'C'  order by numero_operacion  ASC "; //AND PROGRAMA_LEALTAD_ID =24" ; 
   System.out.println("GET OPERACIONES PENDIENTES "+qry);
       try {
           
            return  (Vector)em.createNativeQuery(qry).getResultList(); 
        } catch (Exception err) {
           System.err.println("Error TMSOperacionesPendientes : "+err.getMessage());
           
            err.printStackTrace();
            return null;      
        }
  
  }  
    public int UpdateBloqueados(){
   String qry = "UPDATE ptapo.XER_PROGRAMA_LEALTAD_TBL set status='P' WHERE status ='B'" ; 
   System.out.println("GET OPERACIONES PENDIENTES "+qry);
       try {
           
            return   em.createNativeQuery(qry.toString()).executeUpdate(); 
        } catch (Exception err) {
           System.err.println("Error UpdateBloqueados : "+err.getMessage());
           
            err.printStackTrace();
            return 0;
        }
  
  }
    
 /* 
  public String TMSLealtadCambiaStatus(String numeroOperacion, String status, String numeroTransaccion){
        System.out.println("************** Inicia TMSLealtadCambiaStatus **************");                
        cnx = null;
        stmt = null;
        boolean bResultado;
        String resultado = "";
        try {
            System.out.println("numeroOperacion "+numeroOperacion+" status "+status+" numeroTransaccion "+numeroTransaccion);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "Xer_Tmslealtad_Pkg.TMSLEALTAD_cambiaStatus_prc(?,?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            System.out.println("q1 "+q1);
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, numeroOperacion);
            ((OraclePreparedStatement)stmt).setString(2, status); 
            ((OraclePreparedStatement)stmt).setString(3, numeroTransaccion); 
            stmt.registerOutParameter(4, OracleTypes.VARCHAR);
            bResultado=stmt.execute();            
            resultado = stmt.getString(4);
            System.out.println("resultado "+resultado);
        }catch(Exception e){
            return "1";
        }
        return resultado;
    }*/ // 
   
    public String TMSLealtadCambiaStatus(String numeroOperacion, String status, String numeroTransaccion, String procesoRealizado){
        System.out.println("************** Inicia TMSLealtadCambiaStatus **************");                
        cnx = null;
        stmt = null;
        boolean bResultado;
        String resultado = "";
        try {
            System.out.println("numeroOperacion "+numeroOperacion+" status "+status+" numeroTransaccion "+numeroTransaccion);
            cnx = dataSource.getConnection();    
            String q1 =
                    "BEGIN "+
                    "Xer_Tmslealtad_Pkg.TMSLEALTAD_cambiaStatus_prc(?,?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            System.out.println("q1 "+q1);
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, numeroOperacion);
            ((OraclePreparedStatement)stmt).setString(2, status); 
            ((OraclePreparedStatement)stmt).setString(3, numeroTransaccion); 
            ((OraclePreparedStatement)stmt).setString(4, procesoRealizado);             
            stmt.registerOutParameter(5, OracleTypes.VARCHAR);
            bResultado=stmt.execute();            
            resultado = stmt.getString(5);
            System.out.println("resultado "+resultado);
             stmt.close();
              if(cnx!=null) cnx.close();     
        }catch(Exception e){
            try {
                cnx.close();
                stmt.close();    
            } catch (Exception e1) {
                e1.printStackTrace();
            }
             cnx=null;
             e.printStackTrace();
             status = null;
             return "1";
        }
        return resultado;
    } 
    
        
     public String find_Parametro(String strParametro)
     {
         Vector vresult = null;
          String parametro="";
          String qry ="SELECT G.PARAMETRO_VALOR FROM TMS_PARAMETROS_CONFIG_TBL P, TMS_GLOBAL_PARAMETROS_TBL G "+
                      " WHERE P.PARAMETRO_CODIGO='"+strParametro+"'   "+
                      " AND P.PARAMETRO_CONFIG_ID = G.PARAMETRO_CONFIG_ID ";
          System.out.println("find_Parametro  -->   "+qry);
          Object obj= em.createNativeQuery(qry).getSingleResult();
          
          vresult = obj!=null?(Vector) em.createNativeQuery(qry).getSingleResult():null;
          if(vresult !=  null && vresult.size() > 0)
                parametro = vresult.elementAt(0).toString().trim();
          return parametro;
     }
   
     public Object getToDate() {
        String qry = "SELECT TO_CHAR(SYSDATE,'dd/mm/yyyy HH24:MI:SS') FROM DUAL";
        try {
            Object obj =((Vector)em.createNativeQuery(qry).getSingleResult()).elementAt(0).toString();
        System.out.println(" Fecha del sistema " +obj);
            return  obj;
        } catch (Exception err) {
           System.err.println("Error Inserting Ticket: "+err.getMessage());
            err.printStackTrace();
            return false;
        }
    } 

     
    public List <String> REGISTRA_TRANSACCION_XM(String operacion){
      List listado = new ArrayList <String>();
      Connection cnx=null;
      OracleCallableStatement stmt=null;   
       try {
            cnx = dataSource.getConnection();
            String q1 = 
                    "BEGIN "+
                      "TMS_LEALTAD_WS_PKG.REGISTRA_TRANSACCION_XML_STR(?, ?, ?, ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " + 
                      "ROLLBACK;" +
                      "RAISE; "+
                      "END;";
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.registerOutParameter(2,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(3,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4,java.sql.Types.VARCHAR);
            stmt.setString(1, operacion);
            stmt.execute();
          
            listado.add(stmt.getString(2));  
            listado.add(stmt.getString(3));
            listado.add(stmt.getString(4));
            stmt.close();
            if(cnx!=null) cnx.close();
            return listado;
        } catch (SQLException ex){
            try {
                //System.out.println(this.getCaja()+"=> SP ocupa "+(valor==null?"null":valor));
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }

          finally{
                cnx=null;
                ex.printStackTrace();
                return null;
               }
        }
    }

  
}
