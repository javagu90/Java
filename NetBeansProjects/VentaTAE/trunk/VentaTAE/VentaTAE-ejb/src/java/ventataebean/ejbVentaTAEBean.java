/*
 * ejbVentaTAEBean.java
 *
 * Created on 16 de agosto de 2010, 09:51 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package ventataebean;

import javax.ejb.Stateless;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;

import oracle.jdbc.internal.OracleCallableStatement;
import oracle.jdbc.internal.OraclePreparedStatement;
import oracle.jdbc.OracleTypes;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author opalafox
 */
@Stateless
public class ejbVentaTAEBean implements ejbVentaTAERemote {
    @Resource(name = "TMS_DB")
    private DataSource dataSource;
    
    ResultSet rs = null;
    Connection cnx=null;
    OracleCallableStatement stmt=null;
    /** Creates a new instance of ejbVentaTAEBean */
    public ejbVentaTAEBean() {
    }
    /*
     * Se omite el uso de interceptors para realizar la validación de los datos del argumento ya que por razones de practicidad
     * es mejor hacer la validación desde Oracle y evitar hacer posibles modificaciones en los programas de los usuarios
     */
        
    public  String  ventaTaePrincipal(String Compañía, String Usuario, String Password, String Carrier, String Teléfono, String Cantidad, String TipoVenta, String No_Usuario, String Usuario_id, String Caja, String Corte_id,String Ciudad, String Canal )throws Exception {
        System.out.println("*** ININ: ejbVentaTAEBean.ventaTaePrincipal( "+Compañía+", "+Usuario+", "+Password+", "+Carrier+", "+Teléfono+", "+ Cantidad+", "+TipoVenta+", "+No_Usuario+", "+Usuario_id+", "+Caja+", "+Corte_id+", "+Ciudad+", "+Canal+" ) ***");
        
        
        String P_Exito;
        String P_Transactionno_Out;
        String P_Referenceid;
        String P_Telcelid;
        String P_Responsetelcel;
        String P_Responsetime;
        String P_Status;
     
        String P_Store_Id;
        String P_Terminal;
        String P_Amount_Out;
        String P_Phone_Out;
     
        String P_Errormsg;   
        
                
        cnx = null;
        stmt = null;
        boolean bResultado;
        try {
            
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_TAE_PKG2.TAE_VENTA_PRINCIPAL(?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?,   ?,?,?,?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

     
            
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1,Compañía);
            ((OraclePreparedStatement)stmt).setString(2, Usuario);
            ((OraclePreparedStatement)stmt).setString(3, Password);
            ((OraclePreparedStatement)stmt).setString(4, Carrier);
            ((OraclePreparedStatement)stmt).setString(5, Teléfono);
            ((OraclePreparedStatement)stmt).setString(6, Cantidad);
            ((OraclePreparedStatement)stmt).setString(7, TipoVenta);
            ((OraclePreparedStatement)stmt).setString(8, No_Usuario);
            ((OraclePreparedStatement)stmt).setString(9, Usuario_id);
            ((OraclePreparedStatement)stmt).setString(10, Caja);
            ((OraclePreparedStatement)stmt).setString(11, Corte_id);
            ((OraclePreparedStatement)stmt).setString(12, Ciudad);
            ((OraclePreparedStatement)stmt).setString(13, Canal);
            
         
            
      
       
            stmt.registerOutParameter(14,OracleTypes.VARCHAR);
            stmt.registerOutParameter(15,OracleTypes.VARCHAR);
            stmt.registerOutParameter(16,OracleTypes.VARCHAR);
            stmt.registerOutParameter(17,OracleTypes.VARCHAR);
            stmt.registerOutParameter(18,OracleTypes.VARCHAR);
            stmt.registerOutParameter(19,OracleTypes.VARCHAR);
            stmt.registerOutParameter(20,OracleTypes.VARCHAR);
            stmt.registerOutParameter(21,OracleTypes.VARCHAR);
            stmt.registerOutParameter(22,OracleTypes.VARCHAR);
            stmt.registerOutParameter(23,OracleTypes.VARCHAR);
            stmt.registerOutParameter(24,OracleTypes.VARCHAR);
            stmt.registerOutParameter(25,OracleTypes.VARCHAR);
           
            bResultado=stmt.execute();
            
            
            P_Exito = stmt.getString(14);
            P_Transactionno_Out = stmt.getString(15);
            P_Referenceid = stmt.getString(16);
            P_Telcelid = stmt.getString(17);
            P_Responsetelcel = stmt.getString(18);
            P_Responsetime = stmt.getString(19);
            P_Status = stmt.getString(20);
            P_Store_Id = stmt.getString(21);
            P_Terminal = stmt.getString(22);
            P_Amount_Out = stmt.getString(23);
            P_Phone_Out = stmt.getString(24);
            P_Errormsg = stmt.getString(25);
     
            
       
            
            
            
            
            String resultado = "Resultado ejbVentaTAEBean.ventaTaePrincipal:  \n";
            resultado += "P_Exito: "+P_Exito+" P_Transactionno_Out: "+P_Transactionno_Out+" P_Referenceid: "+P_Referenceid+" P_Telcelid: "+P_Telcelid+"\n";
            resultado += "P_Responsetelcel: "+P_Responsetelcel+" P_Responsetime: "+P_Responsetime+" P_Status: "+P_Status+" P_Store_Id: "+P_Store_Id+"\n";
            resultado += "P_Terminal: "+P_Terminal+" P_Amount_Out: "+P_Amount_Out+" P_Phone_Out: "+P_Phone_Out+" P_Errormsg: "+P_Errormsg+"\n";
            

            System.out.println(resultado);
            System.out.println("Correcto");
        

            
        }catch (Exception ex) {
           System.out.println("INCORRECTO");
           
           ex.printStackTrace();
           throw ex;
           
        }finally{
            if (stmt != null) stmt.close();
            stmt = null;
            if(cnx!=null) cnx.close();
            cnx=null;
            System.out.println("*** FIN: ejbVentaTAEBean.ventaTaePrincipal() ***");
        }
        
        
        
        return P_Exito+","+P_Transactionno_Out+","+P_Referenceid+","+P_Telcelid+","+P_Responsetelcel+","+P_Responsetime+","+P_Status+","+P_Store_Id+","+P_Terminal+","+P_Amount_Out+","+P_Phone_Out+","+P_Errormsg;

    }
    
    
}
