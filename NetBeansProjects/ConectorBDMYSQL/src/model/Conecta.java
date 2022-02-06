/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Javy
*/

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conecta 
{
     /*Datos de conexion*/
    private String db;
    private String user;
    private String pass;
    private Connection conect;
    public String mensaje;
    protected PreparedStatement pst;
    private final String URL;
    
    public Conecta()
    {
        URL="jdbc:mysql://webmilab.com:3306/"+getDb();
    }
    
    public Conecta(String db, String user, String pass)
    {
        setDb(db);
        setUser(user);
        setPass(pass);
        URL="jdbc:mysql://webmilab.com:3306/"+getDb();
        
        try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         conect = DriverManager.getConnection( URL, getUser() , getPass() );
         if ( conect!=null ){
            System.out.println("Conexión a la base de datos "+this.db+"...... Listo ");
            mensaje="bien";
         }
      }catch(SQLException | ClassNotFoundException e){
         System.err.println( e.getMessage() );
         mensaje=e.getMessage();
      }
    }
    
    public void insert(String query)
    {
        try
        {
             pst = conect.prepareStatement( query);
        }
        catch(SQLException sqle)
        {
            System.err.println(sqle.getMessage());
            mensaje=sqle.getMessage();
        }
    }
    
    public void insert(int idCliente, String RS, String telefono, String mail, String DS, String DC, String prototipo,
           String nss, String curp, String US, String SM, String STMKT, int prospecto, int contratado,
           int asignado, int integrado, int CI, int escriturado, int VE, int CD, int cartera, int ER, 
           int titular, int avaluo, int dtu, int AR, int cto, int RC, int BP, int CH, String AP,
           String AC, String origen, String credito, String empresa, String folio, String colonia,
           String ciudad, String estado, int cp, String zona, String sexo, String EC, float salario, 
           float SA, float SC, Date registro, Date contratacion, Date asignacion, Date integracion, 
           Date VP, Date escriturada, Date entregada, Date II, Date IN, Date AN, Date EEP, Date LG, 
           String usuario, Date FA, Date FM, Date FR, float PV, float OVA, float diferencial, float adeudo,
           float MP, float MD)
    {
        try
        {
             pst = conect.prepareStatement("INSERT INTO registros (idCliente, razonSocial, telefono, email, desarrolloSolicitado,"
                     + "desarrolloContratado, prototipo, nss, curp, ultimoStatus, statusMonto, statusTMkt, prospecto, "
                     + "contratado, asignado, integrado, creditoIndiv, escriturado, vivEntregada, cancDefinitiva, cartera,"
                     + "expedRecepcionado, titular, avaluo, dtu, avisoRet, cto, regimenCondominio, boletaPredial,"
                     + "cancelacionHipoteca, asesorProspecta, asesorContratante, origen, credito, empresa, folio, colonia, "
                     + "ciudad, estado, cp, zona, sexo, estadoCivil, salario, salarioAdic, salarioConyuge, registro,"
                     + "contratacion, asignacion, integracion, viviendaPagada, escriturada, entregada, ingresoInstitucion,"
                     + "ingresoNotaria, avaluoNotaria, entregaExpedPostventa, liberacionAGestion, usuario, fechaAlta, "
                     + "fechaModif, fechaRegistro, precioVivienda, operacionValorAvaluo, diferencial, adeudo, montoPlaneado, "
                     + "montoDepositado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
             
             pst.setInt(1,idCliente);
             pst.setString(2,RS);
             pst.setString(3,telefono);
             pst.setString(4,mail);
             pst.setString(5,DS);
             pst.setString(6,DC);
             pst.setString(7,prototipo);
             pst.setString(8,nss);
             pst.setString(9,curp);
             pst.setString(10,US);
             pst.setString(11,SM);
             pst.setString(12,STMKT);
             pst.setInt(13,prospecto);
             pst.setInt(14,contratado);
             pst.setInt(15,asignado);
             pst.setInt(16,integrado);
             pst.setInt(17,CI);
             pst.setInt(18,escriturado);
             pst.setInt(19,VE);
             pst.setInt(20,CD);
             pst.setInt(21,cartera);
             pst.setInt(22,ER);
             pst.setInt(23,titular);
             pst.setInt(24,avaluo);
             pst.setInt(25,dtu);
             pst.setInt(26,AR);
             pst.setInt(27,cto);
             pst.setInt(28,RC);
             pst.setInt(29,BP);
             pst.setInt(30,CH);
             pst.setString(31,AP);
             pst.setString(32,AC);
             pst.setString(33,origen);
             pst.setString(34,credito);
             pst.setString(35,empresa);
             pst.setString(36,folio);
             pst.setString(37,colonia);
             pst.setString(38,ciudad);
             pst.setString(39,estado);
             pst.setInt(40,cp);
             pst.setString(41,zona);
             pst.setString(42,sexo);
             pst.setString(43,EC);
             pst.setFloat(44, salario);
             pst.setFloat(45, SA);
             pst.setFloat(46, SC);
             pst.setDate(47, (new java.sql.Date (registro.getTime())));
             pst.setDate(48, (new java.sql.Date (contratacion.getTime())));
             pst.setDate(49, (new java.sql.Date (asignacion.getTime())));
             pst.setDate(50, (new java.sql.Date (integracion.getTime())));
             pst.setDate(51, (new java.sql.Date (VP.getTime())));
             pst.setDate(52, (new java.sql.Date (escriturada.getTime())));
             pst.setDate(53, (new java.sql.Date (entregada.getTime())));
             pst.setDate(54, (new java.sql.Date (II.getTime())));
             pst.setDate(55, (new java.sql.Date (IN.getTime())));
             pst.setDate(56, (new java.sql.Date (AN.getTime())));
             pst.setDate(57, (new java.sql.Date (EEP.getTime())));
             pst.setDate(58, (new java.sql.Date (LG.getTime())));
             pst.setString(59,usuario);
             pst.setDate(60, (new java.sql.Date (FA.getTime())));
             pst.setDate(61, (new java.sql.Date (FM.getTime())));
             pst.setDate(62, (new java.sql.Date (FR.getTime())));
             pst.setFloat(63,PV);
             pst.setFloat(64,OVA);
             pst.setFloat(65,diferencial);
             pst.setFloat(66, adeudo);
             pst.setFloat(67,MP);
             pst.setFloat(68, MD);
         
             pst.executeUpdate();
        }
        catch(SQLException sqle)
        {
            System.err.println(sqle.getMessage());
            mensaje=sqle.getMessage();
        }
    }
    
    public void select(String query)
    {
        
      try
      {
        pst =conect.prepareStatement( query );
        ResultSet res = pst.executeQuery();
         while(res.next())
         {
             System.out.print(res.getString(1)+" ");
             System.out.print(res.getString(2)+" ");
             System.out.print(res.getString(3)+" ");
             System.out.print(res.getString(4)+" ");
             System.out.print(res.getString(5)+" ");
             System.out.print(res.getString(6)+" ");
             System.out.print(res.getString(7)+" ");
             System.out.print(res.getString(8)+" ");
             System.out.print(res.getString(9)+" ");
             System.out.print(res.getString(10)+" ");
             System.out.print(res.getString(11)+" ");
             System.out.print(res.getString(12)+" ");
             System.out.print(res.getString(13)+" ");
             System.out.print(res.getString(14)+" ");
             System.out.print(res.getString(15)+" ");
             System.out.print(res.getString(16)+" ");
             System.out.print(res.getString(17)+" ");
             System.out.print(res.getString(18)+" ");
             System.out.print(res.getString(19)+" ");
             System.out.print(res.getString(20)+" ");
             System.out.print(res.getString(21)+" ");
             System.out.print(res.getString(22)+" ");
             System.out.print(res.getString(23)+" ");
             System.out.print(res.getString(24)+" ");
             System.out.print(res.getString(25)+" ");
             System.out.print(res.getString(26)+" ");
             System.out.print(res.getString(27)+" ");
             System.out.print(res.getString(28)+" ");
             System.out.print(res.getString(29)+" ");
             System.out.print(res.getString(30)+" ");
             System.out.print(res.getString(31)+" ");
             System.out.print(res.getString(32)+" ");
             System.out.print(res.getString(33)+" ");
             System.out.print(res.getString(34)+" ");
             System.out.print(res.getString(35)+" ");
             System.out.print(res.getString(36)+" ");
             System.out.print(res.getString(37)+" ");
             System.out.print(res.getString(38)+" ");
             System.out.print(res.getString(39)+" ");
             System.out.print(res.getString(40)+" ");
             System.out.print(res.getString(41)+" ");
             System.out.print(res.getString(42)+" ");
             System.out.print(res.getString(43)+" ");
             System.out.print(res.getString(44)+" ");
             System.out.print(res.getString(45)+" ");
             System.out.print(res.getString(46)+" ");
             System.out.print(res.getString(47)+" ");
             System.out.print(res.getString(48)+" ");
             try
             {
             System.out.print(res.getString(49)+" ");
             System.out.print(res.getString(50)+" ");
             System.out.print(res.getString(51)+" ");
             System.out.print(res.getString(52)+" ");
             System.out.print(res.getString(53)+" ");
             System.out.print(res.getString(54)+" ");
             System.out.print(res.getString(55)+" ");
             System.out.print(res.getString(56)+" ");
             System.out.print(res.getString(57)+" ");
             System.out.print(res.getString(58)+" ");
             }
             catch(Exception e)
             {
                 //System.out.print("MAL FORMATO DE FECHA, NO SE ACEPTA 0000-00");
             System.out.print(res.getString(59)+" ");
             System.out.print(res.getString(60)+" ");
             System.out.print(res.getString(61)+" ");
             System.out.print(res.getString(62)+" ");
             System.out.print(res.getString(63)+" ");
             System.out.print(res.getString(64)+" ");
             System.out.print(res.getString(65)+" ");
             System.out.print(res.getString(66)+" ");
             System.out.print(res.getString(67)+" ");
             System.out.println(res.getString(68)+" ");
             }
         }
      }
      catch(SQLException sqle)
      {
        System.err.println(sqle.getMessage());
        mensaje=sqle.getMessage();
      }
 
    }
    
    public void delete(String numero)
    {
        try {  
            pst=conect.prepareStatement("DELETE from registros WHERE idCliente="+numero);
            pst.executeUpdate();
            System.out.println("\nRegistro borrado");
        } catch (SQLException ex) {
            Logger.getLogger(Conecta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Connection getConect() {
        return conect;
    }

    public void setConect(Connection conect) {
        this.conect = conect;
    }
    
}
