/*
 * ejbERCONTROLTMSWSFacadeBean.java
 *
 * Created on 18 de junio de 2010, 04:03 PM
 *
 * Ejecucion de motodos Facade de bean(s) asociados
 * en todos los casos se hace uso de @Interceptors dedicados a la validacoon
 * de los datos que son provistos, tanto requeridos como opcionales para mantener
 * el codigo separado de la logica del negocio
 */

package ERTMSWS;

import WS_CONTROL.TMSS.getOrigenesDestinosReqEr;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.annotation.Resource;
//Importacion de clases definidas para intercambio
import ERTMSWS.clasesx.AsientosDisp.AsientosDispReq;
import ERTMSWS.clasesx.AsientosDisp.AsientosDispResp;
import ERTMSWS.clasesx.BloquearAsientos.BloquearAsientosReq;
import ERTMSWS.clasesx.BloquearAsientos.BloquearAsientosResp;
import ERTMSWS.clasesx.CambioHorario.CambioHorarioReq;
import ERTMSWS.clasesx.CambioHorario.CambioHorarioResp;
import ERTMSWS.clasesx.CancelarBoletos.CancelarBoletosReq;
import ERTMSWS.clasesx.CancelarBoletos.CancelarBoletosResp;
import ERTMSWS.clasesx.CanjeBA.CanjeBAReq;
import ERTMSWS.clasesx.CanjeBA.CanjeBAResp;
import ERTMSWS.clasesx.Corridas.CorridasReq;
import ERTMSWS.clasesx.Corridas.CorridasResp;
import ERTMSWS.clasesx.Login.LoginReq;
import ERTMSWS.clasesx.Login.LoginResp;
import ERTMSWS.clasesx.Logout.LogoutReq;
import ERTMSWS.clasesx.Logout.LogoutResp;
import ERTMSWS.clasesx.VenderBoletos.VenderBoletosReq;
import ERTMSWS.clasesx.VenderBoletos.VenderBoletosResp;
import ERTMSWS.clasesx.ValidarBoleto.ValidarBoletoReq;
import ERTMSWS.clasesx.ValidarBoleto.ValidarBoletoResp;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Array;

import oracle.jdbc.internal.OracleCallableStatement;
import oracle.jdbc.internal.OraclePreparedStatement;
import oracle.jdbc.OracleTypes;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import oracle.sql.CLOB;

import ERTMSWS.clases.Asientos;
import ERTMSWS.clases.FoliosBoletos;
import ERTMSWS.clases.Corrida;
import ERTMSWS.clases.Empresa;
import ERTMSWS.clases.Pagos;
import ERTMSWS.clases.ServicioVta;
import ERTMSWS.clases.Terminal;
import ERTMSWS.clases.TipoPago;
import ERTMSWS.clasesx.Catalogos.EmpresasVtaResp;
import ERTMSWS.clasesx.Catalogos.ServiciosVtaResp;
import ERTMSWS.clasesx.Catalogos.TerminalesResp;
import ERTMSWS.clasesx.Catalogos.TiposPagoResp;
import ERTMSWS.clasesx.VenderBoletos.getAsociarComprasClienteResp;
import ERTMSWS.clasesx.VenderBoletos.getAsociarComprasClienteReq;
import WS_CONTROL.TMS.entidades.Destino;
import WS_CONTROL.TMS.entidades.Origen;
import WS_CONTROL.TMSS.getOrigenesDestinosReq;
import WS_CONTROL.TMSS.getOrigenesDestinosResp;

import java.util.Vector;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Calendar;
import java.sql.Timestamp;


import java.io.BufferedReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Statement;
import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author opalafox
 */
@Stateless
public class ejbERCONTROLTMSWSFacadeBean implements ERTMSWS.ejbERCONTROLTMSWSFacadeRemote, Serializable {
   @Resource(name = "TMS_DB")
    private DataSource dataSource;

    @PersistenceContext(unitName="ER_WS_CONTROL_EJB_TMS")
    private EntityManager em;

    ResultSet rs = null;
    Connection cnx=null;
    OracleCallableStatement stmt=null;
    SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat sdfFechaHora2 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    /** Creates a new instance of ejbERCONTROLTMSWSFacadeBean */
    public ejbERCONTROLTMSWSFacadeBean() {
        sdfFechaHora.setTimeZone(TimeZone.getTimeZone("America/Mexico_City")); 
    }
    
    @Interceptors({ERTMSWS.LocalInterceptors.Validators.ValidateLoginReq.class})
    public LoginResp getLogin (LoginReq loginReq)throws Exception{
        System.out.println("*** INI: EJB.getLogin() ***");
        LoginResp loginResp = new LoginResp();
       
        cnx = null;
        stmt = null;
        boolean bResultado;
        try {

            //java.sql.Date sqlDate = new java.sql.Date(loginReq.getFechaCreacion().getTime());
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_TMSWS_PKG.TMSWS_GETLOGIN_PRC(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            
            
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setInt(1,loginReq.getUsuarioId());
            ((OraclePreparedStatement)stmt).setString(2, loginReq.getUsuarioNumero());
            ((OraclePreparedStatement)stmt).setString(3, loginReq.getUsuarioContrasena());
            ((OraclePreparedStatement)stmt).setString(4, loginReq.getNombreEquipo());
            ((OraclePreparedStatement)stmt).setString(5, loginReq.getNombreCaja());
            ((OraclePreparedStatement)stmt).setString(6, loginReq.getDireccionIP());
            ((OraclePreparedStatement)stmt).setString(7, loginReq.getDireccionMAC());
            ((OraclePreparedStatement)stmt).setInt(8, loginReq.getAutorizadoPor());
            ((OraclePreparedStatement)stmt).setString(9, loginReq.getSucursalClave());
            ((OraclePreparedStatement)stmt).setString(10, loginReq.getCanalVenta());
            String horatemp = sdfFechaHora.format(loginReq.getFechaCreacion().getTime());
            /*Se utiliza timestamp debido a que Oracle en el tipo sql.date no toma en cuenta horas y minutos al inverso de este tipo*/
            Timestamp tst= new Timestamp((sdfFechaHora2.parse(horatemp)).getTime());
            ((OraclePreparedStatement)stmt).setTimestamp(11, tst);
            stmt.registerOutParameter(12,OracleTypes.NUMBER);
            stmt.registerOutParameter(13,OracleTypes.NUMBER);
            stmt.registerOutParameter(14,OracleTypes.VARCHAR);
            stmt.registerOutParameter(15,OracleTypes.VARCHAR);
            
        
            bResultado=stmt.execute();
            
            if (stmt.getInt(13) == 0){
                System.out.println(stmt.getString(14));
                System.out.println(stmt.getString(15));
                loginResp.setOperacionExitosa(false);
                loginResp.setErrorCode(stmt.getString(14));
                loginResp.setErrorMsg(stmt.getString(15));
            }else{
           
           
            loginResp.setSesionId(stmt.getInt(12));
            
        
            if (stmt.getInt(13)==1)
                loginResp.setOperacionExitosa(true);
            else
                loginResp.setOperacionExitosa(false);
            loginResp.setErrorCode(stmt.getString(14));
            loginResp.setErrorMsg(stmt.getString(15));
            }
            
            System.out.println("Correcto");
        

            
        }catch (Exception ex) {
           System.out.println("INCORRECTO");
           
           throw ex;
           
        }finally{
        if(stmt!=null) stmt.close();
        if(cnx!=null) cnx.close();
        }
        
        System.out.println("*** FIN: EJB.getLogin() ***");
        return loginResp;
    }

    @Interceptors({ERTMSWS.LocalInterceptors.Validators.ValidateCorridasReq.class})
    public CorridasResp getCorridas(CorridasReq corridasReq)throws Exception{
        System.out.println("*** INI: EJB.getCorridas() ***");
        CorridasResp corridasResp = new CorridasResp();
        /*
        cnx = null;
        stmt = null;
        boolean bResultado;
        try{
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    //"XER_TMSWS_PKG.TMSWS_getCorridas_Prc(?, ?, ?, ?, ?, ?,   ?,?,?,?); " +
                    "XER_TMSWS_PKG.TMSWS_getCorridas_EBUS_Prc(?, ?, ?, ?, ?, ?,   ?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

           stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setInt(1,corridasReq.getSesionId());
            ((OraclePreparedStatement)stmt).setString(2, corridasReq.getEmpresa());
            ((OraclePreparedStatement)stmt).setString(3, corridasReq.getServicio());
            ((OraclePreparedStatement)stmt).setString(4, corridasReq.getOrigen());
            ((OraclePreparedStatement)stmt).setString(5, corridasReq.getDestino());
  
            //String horatemp = sdfFechaHora.format(corridasReq.getFechaHoraSalida().getTime());
            //Se utiliza timestamp debido a que Oracle en el tipo sql.date no toma en cuenta horas y minutos al inverso de este tipo
            //Timestamp tst= new Timestamp((sdfFechaHora2.parse(horatemp)).getTime());
            Timestamp tst= new Timestamp(corridasReq.getFechaHoraSalida().getTime());
           // System.out.println("imestramp" +tst.toString());
            ((OraclePreparedStatement)stmt).setTimestamp(6, tst);
            stmt.registerOutParameter(7,OracleTypes.CLOB);
            stmt.registerOutParameter(8,OracleTypes.NUMBER);
            stmt.registerOutParameter(9,OracleTypes.VARCHAR);
            stmt.registerOutParameter(10,OracleTypes.VARCHAR);
            
        
            bResultado=stmt.execute();
            if (stmt.getInt(8) == 0){
                System.out.println(stmt.getString(9));
                System.out.println(stmt.getString(10));
                corridasResp.setOperacionExitosa(false);
                corridasResp.setErrorCode(stmt.getString(9));
                corridasResp.setErrorMsg(stmt.getString(10));
            }else{
           
                long cloblength = stmt.getClob(7).length();
                int intClobLength = (int)cloblength;
                String localClob = "";
             
                StringBuffer strOut = new StringBuffer();
                String aux;
                BufferedReader br = new BufferedReader(stmt.getClob(7).getCharacterStream());
                while ((aux=br.readLine()) != null){
                    strOut.append(aux);
                }
                localClob = strOut.toString();
              *

       //         System.out.println(localClob);
                
                
                
                
                
                
            //    CLOB localclob = stmt.getClob(7);
                //((oracle.sql.CLOB)stmt.getClob(7)).putString( (int)stmt.getClob(7).length()+1, "\nBye!" );

               // System.out.println("cadena clob:" +stmt.getClob(7).getSubString(0,intClobLength));
                corridasResp.setcorridas(this.setClobtoArrayCorrida(localClob));



                corridasResp.setOperacionExitosa(true);

                corridasResp.setErrorCode(stmt.getString(9));
                corridasResp.setErrorMsg(stmt.getString(10));
            }
            
            System.out.println("Correcto");
        

            
        }catch (Exception ex) {
            System.out.println("Excepcion en getCorridas...");
            ex.printStackTrace();
           System.out.println("INCORRECTO");
           
           throw ex;
           
        }
        finally{
        stmt.close();
        if(cnx!=null) cnx.close();
        }
         *
         */

        String origenOrigial = corridasReq.getOrigen();
        String destinoOrigial = corridasReq.getDestino();
        String origenes ="'"+origenOrigial+"'";
        String destinos ="'"+destinoOrigial+"'";
        String localClob = "";
        String cors = "";

        /*cors = getCorridasEbus(corridasReq);
        System.out.println("Las corridas Regresadas son: "+cors);
        if(!cors.equals("NO OK"))
        {
            if(localClob.equals(""))
                localClob = cors;
            else
                localClob = localClob+"|"+cors;
        }
         *
         */

        //Inicia la busqueda de as otras corridas

         System.out.println("Cambio 04032015");

        if(origenOrigial.equals("STAFE") )//|| destinoOrigial.equals("STAFE"))
        {
                       origenes = origenes + ",'STAFE'";
                       destinos = destinos + ",'4PTE'";  //'CAPU'040315
                       corridasReq.setOrigen(origenes) ;
                       corridasReq.setDestino(destinos) ;
                        cors = getCorridasEbus(corridasReq);
                        System.out.println("Las corridas Regresadas son: "+cors);
                        if(!cors.equals("NO OK"))
                            localClob = cors;
            System.out.println("Ya se buscaron corridas con del Grupo STAFE");
            /*
            if(origenOrigial.equals("4PTE") && destinoOrigial.equals("STAFE") )
                System.out.println("Ya se buscaron corridas con 4PTE-STAFE");
            else
            {
               corridasReq.setOrigen("4PTE") ;
               corridasReq.setDestino("STAFE") ;
                cors = getCorridasEbus(corridasReq);
                System.out.println("Las corridas Regresadas son: "+cors);
                if(!cors.equals("NO OK"))
                {
                    if(localClob.equals(""))
                        localClob = cors;
                    else
                        localClob = localClob+"|"+cors;
                }
            }

            if(origenOrigial.equals("STAFE") && destinoOrigial.equals("4PTE") )
                System.out.println("Ya se buscaron corridas con STAFE-4PTE");
            else
            {
               corridasReq.setOrigen("STAFE") ;
               corridasReq.setDestino("4PTE") ;
                cors = getCorridasEbus(corridasReq);
                System.out.println("Las corridas Regresadas son: "+cors);
                if(!cors.equals("NO OK"))
                {
                    if(localClob.equals(""))
                        localClob = cors;
                    else
                        localClob = localClob+"|"+cors;
                }
            }
             *
             */


        }
        else
        {
                if(!origenOrigial.equals("4PTE") && !origenOrigial.equals("CAPU"))
                {
                    /*
                    if(origenOrigial.equals("ANGEL") && destinoOrigial.equals("CHOL") )
                        System.out.println("Ya se buscaron corridas con ANGEL-CHOL");
                    else
                    {
                       corridasReq.setOrigen("ANGEL") ;
                       corridasReq.setDestino("CHOL") ;
                        cors = getCorridasEbus(corridasReq);
                        System.out.println("Las corridas Regresadas son: "+cors);
                        if(!cors.equals("NO OK"))
                        {
                            if(localClob.equals(""))
                                localClob = cors;
                            else
                                localClob = localClob+"|"+cors;
                        }
                    }

                    if(origenOrigial.equals("ANGEL") && destinoOrigial.equals("4PTE") )
                        System.out.println("Ya se buscaron corridas con ANGEL-4PTE");
                    else
                    {
                       corridasReq.setOrigen("ANGEL") ;
                       corridasReq.setDestino("4PTE") ;
                        cors = getCorridasEbus(corridasReq);
                        System.out.println("Las corridas Regresadas son: "+cors);
                        if(!cors.equals("NO OK"))
                        {
                            if(localClob.equals(""))
                                localClob = cors;
                            else
                                localClob = localClob+"|"+cors;
                        }
                    }

                    if(origenOrigial.equals("CONDESA") && destinoOrigial.equals("4PTE") )
                        System.out.println("Ya se buscaron corridas con CONDESA-4PTE");
                    else
                    {
                       corridasReq.setOrigen("CONDESA") ;
                       corridasReq.setDestino("4PTE") ;
                        cors = getCorridasEbus(corridasReq);
                        System.out.println("Las corridas Regresadas son: "+cors);
                        if(!cors.equals("NO OK"))
                        {
                            if(localClob.equals(""))
                                localClob = cors;
                            else
                                localClob = localClob+"|"+cors;
                        }
                    }

                    if(origenOrigial.equals("CONDESA") && destinoOrigial.equals("CHOL") )
                        System.out.println("Ya se buscaron corridas con CONDESA-CHOL");
                    else
                    {
                       corridasReq.setOrigen("CONDESA") ;
                       corridasReq.setDestino("CHOL") ;
                        cors = getCorridasEbus(corridasReq);
                        System.out.println("Las corridas Regresadas son: "+cors);
                        if(!cors.equals("NO OK"))
                        {
                            if(localClob.equals(""))
                                localClob = cors;
                            else
                                localClob = localClob+"|"+cors;
                        }
                    }



                    if(origenOrigial.equals("WTC") && destinoOrigial.equals("4PTE") )
                        System.out.println("Ya se buscaron corridas con WTC-4PTE");
                    else
                    {
                       corridasReq.setOrigen("WTC") ;
                       corridasReq.setDestino("4PTE") ;
                        cors = getCorridasEbus(corridasReq);
                        System.out.println("Las corridas Regresadas son: "+cors);
                        if(!cors.equals("NO OK"))
                        {
                            if(localClob.equals(""))
                                localClob = cors;
                            else
                                localClob = localClob+"|"+cors;
                        }
                    }

                    if(origenOrigial.equals("WTC") && destinoOrigial.equals("CHOL") )
                        System.out.println("Ya se buscaron corridas con WTC-CHOL");
                    else
                    {
                       corridasReq.setOrigen("WTC") ;
                       corridasReq.setDestino("CHOL") ;
                        cors = getCorridasEbus(corridasReq);
                        System.out.println("Las corridas Regresadas son: "+cors);
                        if(!cors.equals("NO OK"))
                        {
                            if(localClob.equals(""))
                                localClob = cors;
                            else
                                localClob = localClob+"|"+cors;
                        }
                    }
                     *
                     */
                       origenes = origenes + ",'ANGEL','CONDESA','WTC'";
                       destinos = destinos + ",'CHOL','4PTE','CHOL'";  //'CAPU'  040315
                       corridasReq.setOrigen(origenes) ;
                       corridasReq.setDestino(destinos) ;
                        cors = getCorridasEbus(corridasReq);
                        System.out.println("Las corridas Regresadas son: "+cors);
                        if(!cors.equals("NO OK"))
                            localClob = cors;

                  }
                  else
                    {
                        /*
                        if(origenOrigial.equals("4PTE") && destinoOrigial.equals("STAFE") )
                            System.out.println("Ya se buscaron corridas con 4PTE-STAFE");
                        else
                        {
                           corridasReq.setOrigen("4PTE") ;
                           corridasReq.setDestino("STAFE") ;
                            cors = getCorridasEbus(corridasReq);
                            System.out.println("Las corridas Regresadas son: "+cors);
                            if(!cors.equals("NO OK"))
                            {
                                if(localClob.equals(""))
                                    localClob = cors;
                                else
                                    localClob = localClob+"|"+cors;
                            }
                        }

                        if(origenOrigial.equals("4PTE") && destinoOrigial.equals("ANGEL") )
                            System.out.println("Ya se buscaron corridas con 4PTE-ANGEL");
                        else
                        {
                           corridasReq.setOrigen("4PTE") ;
                           corridasReq.setDestino("ANGEL") ;
                            cors = getCorridasEbus(corridasReq);
                            System.out.println("Las corridas Regresadas son: "+cors);
                            if(!cors.equals("NO OK"))
                            {
                                if(localClob.equals(""))
                                    localClob = cors;
                                else
                                    localClob = localClob+"|"+cors;
                            }
                        }

                        if(origenOrigial.equals("4PTE") && destinoOrigial.equals("CONDESA") )
                            System.out.println("Ya se buscaron corridas con 4PTE-CONDESA");
                        else
                        {
                           corridasReq.setOrigen("4PTE") ;
                           corridasReq.setDestino("CONDESA") ;
                            cors = getCorridasEbus(corridasReq);
                            System.out.println("Las corridas Regresadas son: "+cors);
                            if(!cors.equals("NO OK"))
                            {
                                if(localClob.equals(""))
                                    localClob = cors;
                                else
                                    localClob = localClob+"|"+cors;
                            }
                        }

                        if(origenOrigial.equals("4PTE") && destinoOrigial.equals("WTC") )
                            System.out.println("Ya se buscaron corridas con 4PTE-WTC");
                        else
                        {
                           corridasReq.setOrigen("4PTE") ;
                           corridasReq.setDestino("WTC") ;
                            cors = getCorridasEbus(corridasReq);
                            System.out.println("Las corridas Regresadas son: "+cors);
                            if(!cors.equals("NO OK"))
                            {
                                if(localClob.equals(""))
                                    localClob = cors;
                                else
                                    localClob = localClob+"|"+cors;
                            }
                        }

                         *
                         */
                       origenes = origenes + ",'4PTE'";   //'CAPU'  040315
                       destinos = destinos + ",'STAFE','ANGEL','CONDESA','WTC'";
                       corridasReq.setOrigen(origenes) ;
                       corridasReq.setDestino(destinos) ;
                        cors = getCorridasEbus(corridasReq);
                        System.out.println("Las corridas Regresadas son: "+cors);
                        if(!cors.equals("NO OK"))
                            localClob = cors;
                    }
        }

        //         System.out.println(localClob);
        if(localClob.equals(""))
        {
           corridasResp.setErrorMsg("NO existe una corrida disponible con los datos seleccionados.");
        }
        else
        {
           corridasResp.setcorridas(this.setClobtoArrayCorridaEbus(localClob,origenOrigial,destinoOrigial));
           corridasResp.setErrorMsg("Transacción realizada satisfactoriamente.");
        }
            corridasResp.setOperacionExitosa(true);
            corridasResp.setErrorCode("ER-000");

        
        System.out.println("*** FIN: EJB.getCorridas() ***");
        return corridasResp;
    }

  public TimeZone getTimeZone()
  {
    return TimeZone.getDefault();
  }

    private String getCorridasEbus(CorridasReq corridasReq) throws Exception
    {
        //SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sdfFechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        cnx = null;
        stmt = null;
        boolean bResultado;
        String respuesta = "NO OK";
        System.out.println("*** Busca Corridas con: ---------****"+corridasReq.getServicio() );
        System.out.println("     ORIGEN: "+  corridasReq.getOrigen());
        System.out.println("     DESTINO: "+  corridasReq.getDestino());
        System.out.println("Empresa ----> "+ corridasReq.getEmpresa());
        if(!corridasReq.getEmpresa().equals("TTP1")){
            corridasReq.setDestino(null);
            System.out.println("Quitando los origenes y destinos");
        }
        System.out.println("------------************----------");
        System.out.println("**"+corridasReq);
       StringBuffer strOut = new StringBuffer();
        try{
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    //"XER_TMSWS_PKG.TMSWS_getCorridas_Prc(?, ?, ?, ?, ?, ?,   ?,?,?,?); " +
                    //"XER_TMSWS_EBUS_PKG.TMSWS_getCorridas_EBUS_Prc(?, ?, ?, ?, ?, ?,   ?,?,?,?); " +
                    "XER_TMSWS_EBUS_PKG.TMSWS_getCorridas2_EBUS_Prc(?, ?, ?, ?, ?, ?,   ?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

           stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setInt(1,corridasReq.getSesionId());
            ((OraclePreparedStatement)stmt).setString(2, corridasReq.getEmpresa());
            ((OraclePreparedStatement)stmt).setString(3, corridasReq.getServicio());
            ((OraclePreparedStatement)stmt).setString(4, corridasReq.getOrigen()); //se manda el
            ((OraclePreparedStatement)stmt).setString(5, corridasReq.getDestino());
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Logs de datos a OracleCallableStatement para TMSWS_getCorridas2_EBUS_Prc");
            System.out.println("corridasReq.getSesionId(): "+corridasReq.getSesionId());
            System.out.println("corridasReq.getEmpresa(): "+corridasReq.getEmpresa());
            System.out.println("corridasReq.getServicio(): "+corridasReq.getServicio());
            System.out.println("corridasReq.getOrigen(): "+corridasReq.getOrigen());
            System.out.println("corridasReq.getDestino(): "+corridasReq.getDestino());
            System.out.println("End log OracleCallableStatement para TMSWS_getCorridas2_EBUS_Prc");
            System.out.println("------------------------------------------------------------------------");

            //String horatemp = sdfFechaHora.format(corridasReq.getFechaHoraSalida().getTime());
            /*Se utiliza timestamp debido a que Oracle en el tipo sql.date no toma en cuenta horas y minutos al inverso de este tipo*/
            //Timestamp tst= new Timestamp((sdfFechaHora2.parse(horatemp)).getTime());
            System.out.println("Busca corridas con la fecha: "+corridasReq.getFechaHoraSalida() );
            System.out.println("formato: "+sdfFechaHora.format(corridasReq.getFechaHoraSalida().getTime()) );
            sdfFechaHora.setTimeZone(getTimeZone());
            System.out.println("formato: "+sdfFechaHora.format(corridasReq.getFechaHoraSalida().getTime()) );

            //Timestamp tst= new Timestamp(corridasReq.getFechaHoraSalida().getTime());
             Timestamp tst= Timestamp.valueOf(sdfFechaHora.format(corridasReq.getFechaHoraSalida().getTime())+":00");
            System.out.println("Busca corridas con la fecha: "+tst.toString());
            System.out.println("----****tst***---- : " +tst.toString());
            ((OraclePreparedStatement)stmt).setTimestamp(6, tst);
            stmt.registerOutParameter(7,OracleTypes.CLOB);
            stmt.registerOutParameter(8,OracleTypes.NUMBER);
            stmt.registerOutParameter(9,OracleTypes.VARCHAR);
            stmt.registerOutParameter(10,OracleTypes.VARCHAR);


            bResultado=stmt.execute();
            if (stmt.getInt(8) == 0){
                System.out.println(stmt.getString(9));
                System.out.println(stmt.getString(10));
            }else{

                long cloblength = stmt.getClob(7).length();
                int intClobLength = (int)cloblength;
                String localClob = "";

                String aux;
                BufferedReader br = new BufferedReader(stmt.getClob(7).getCharacterStream());
                while ((aux=br.readLine()) != null){
                    strOut.append(aux);
                }
                respuesta = strOut.toString();
            }
        }catch (Exception ex) {
            System.out.println("Excepcion en getCorridas...");
            ex.printStackTrace();
           System.out.println("INCORRECTO");

           throw ex;

        }
        finally{
        if(stmt!=null) stmt.close();
        System.out.println("getCorridas cierra el stmt...");        
        if(cnx!=null) {cnx.close();        System.out.println("getCorridas cierra el cnx...");        }

        }
      return  respuesta;
    }


    @Interceptors({ERTMSWS.LocalInterceptors.Validators.ValidateAsientosDispReq.class})
    public AsientosDispResp getAsientosDisp(AsientosDispReq asientosDispReq)throws Exception{
        System.out.println("*** INI: EJB.getAsientosDisp() ***");
        AsientosDispResp asientosDispResp = new AsientosDispResp();
        cnx = null;
        
        stmt = null;
        
        boolean bResultado = true;

        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_TMSWS_EBUS_PKG.TMSWS_GETASIENTOSDISP_ebus_PRC(?, ?, ?,  ?,?,?,?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            
            ((OraclePreparedStatement)stmt).setInt(1, asientosDispReq.getSesionId());
            ((OraclePreparedStatement)stmt).setString(2, asientosDispReq.getOrigen());
            ((OraclePreparedStatement)stmt).setString(3, asientosDispReq.getClaveCorrida());
            stmt.registerOutParameter(4,OracleTypes.VARCHAR); //AsientosDisponibles
            stmt.registerOutParameter(5,OracleTypes.NUMBER); //Asientos Senectud
            stmt.registerOutParameter(6,OracleTypes.NUMBER); //Asientos Profesor
            stmt.registerOutParameter(7,OracleTypes.NUMBER); //Asientos Estudiante
            stmt.registerOutParameter(8,OracleTypes.NUMBER); //Asientos Otro
            
            stmt.registerOutParameter(9,OracleTypes.NUMBER); //OperacionExitosa
            stmt.registerOutParameter(10,OracleTypes.VARCHAR); //ErrorCode
            stmt.registerOutParameter(11,OracleTypes.VARCHAR); //ErrrMsg
            
            
            bResultado=stmt.execute();
             if (stmt.getInt(9) == 0){ //Error
                System.out.println(stmt.getString(10));
                System.out.println(stmt.getString(11));
                asientosDispResp.setOperacionExitosa(false);
                //asientosDispResp.setErrorCode(stmt.getString(10));
                //asientosDispResp.setErrorMsg(stmt.getString(11));
            }else{//oxito
                asientosDispResp.setOperacionExitosa(true);
                asientosDispResp.setasientosDisponibles(stmt.getString(4));
                asientosDispResp.setasientosSenectud(stmt.getInt(5));
                asientosDispResp.setasientosProfesor(stmt.getInt(6));
                asientosDispResp.setasientosEstudiante(stmt.getInt(7));
                asientosDispResp.setasientosOtro(stmt.getInt(8));

            }
            asientosDispResp.setErrorCode(stmt.getString(10));
            asientosDispResp.setErrorMsg(stmt.getString(11));
            
          
            System.out.println("Correcto");
        

          
        }catch (Exception ex) {
           System.out.println("INCORRECTO");
          
           throw ex;
           
        }
        finally{
        if(stmt!=null) stmt.close();
        System.out.println("getasientosDisp cierra el stmt...");
        if(cnx!=null) {cnx.close(); System.out.println("getasientosDisp cierra el cnx...");}
        }
        System.out.println("*** FIN: EJB.getAsientosDisp() ***"); 
        return asientosDispResp;
    }
    @Interceptors({ERTMSWS.LocalInterceptors.Validators.ValidateBloquearAsientosReq.class})
    public BloquearAsientosResp getBloquearAsientos(BloquearAsientosReq bloquearAsientosReq)throws Exception{
        System.out.println("*** INI: EJB.getBloquearAsientos() ***"); 
        BloquearAsientosResp bloquearAsientosResp = new BloquearAsientosResp();
        cnx = null;
        
        stmt = null;
        
        boolean bResultado = true;
/*  P_SESIONID := 1501208;
  P_ASIENTOS := ',19';
  P_TIPO_PASAJERO := ',E';
  P_CLAVECORRIDA := 'CAPUI0640N1010525';
  P_MODO := 'E';


TMSWS_GETBLOQUEARASIENTOS_PRC ( 
 P_SESIONID, 
 P_ASIENTOS, 
 P_TIPO_PASAJERO, 
 P_CLAVECORRIDA, 
 P_MODO, P_OPERACIONEXITOSA, P_ERRORCODE, P_ERRORMSG );

 **/
            System.out.println("getSesionId : "+bloquearAsientosReq.getSesionId());
            System.out.println("getNoAsientos : "+bloquearAsientosReq.getNoAsientos());
            System.out.println("getTipoPasajero : "+bloquearAsientosReq.getTipoPasajero());
            System.out.println("getOrigen : "+bloquearAsientosReq.getOrigen());
            System.out.println("getClaveCorrida : "+bloquearAsientosReq.getClaveCorrida());
            System.out.println("getModalidad : "+bloquearAsientosReq.getModalidad());
            if(bloquearAsientosReq.getModalidad().toUpperCase().equals("L"))
                 bloquearAsientosReq.setTipoPasajero(bloquearAsientosReq.getTipoPasajero().replace("S","A").replace("s", "A"));
            System.out.println("getTipoPasajero2 : "+bloquearAsientosReq.getTipoPasajero());

        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_TMSWS_PKG.TMSWS_GETBLOQUEARASIENTOS_PRC(?, ?, ?, ?, ?, ?, ?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            
            ((OraclePreparedStatement)stmt).setInt(1, bloquearAsientosReq.getSesionId());
            ((OraclePreparedStatement)stmt).setString(2, bloquearAsientosReq.getNoAsientos());
            ((OraclePreparedStatement)stmt).setString(3, bloquearAsientosReq.getTipoPasajero());
            ((OraclePreparedStatement)stmt).setString(4, bloquearAsientosReq.getOrigen());
            ((OraclePreparedStatement)stmt).setString(5, bloquearAsientosReq.getClaveCorrida());
            ((OraclePreparedStatement)stmt).setString(6, bloquearAsientosReq.getModalidad());

            stmt.registerOutParameter(7,OracleTypes.NUMBER); //OperacionExitosa
            stmt.registerOutParameter(8,OracleTypes.VARCHAR); //ErrorCode
            stmt.registerOutParameter(9,OracleTypes.VARCHAR); //ErrrMsg
            
            
            bResultado=stmt.execute();
             if (stmt.getInt(7) == 0){ //Error
                System.out.println(stmt.getString(8));
                System.out.println(stmt.getString(9));
                bloquearAsientosResp.setOperacionExitosa(false);
                }else{//oxito
                bloquearAsientosResp.setOperacionExitosa(true);
                
            }
            bloquearAsientosResp.setErrorCode(stmt.getString(8));
            bloquearAsientosResp.setErrorMsg(stmt.getString(9));
            
            
            System.out.println("Correcto");
        

            
        }catch (Exception ex) {
           if(stmt!=null){stmt.close();System.out.println("get bloquearAsientos cierra el stmt(excepction)...");}
           System.out.println("INCORRECTO");
           if(cnx!=null){ cnx.close(); System.out.println("get bloquearAsientos cierra el cnx(excepction)...");}
           cnx=null;
           stmt=null;
           throw ex;
           
        }
        finally{
         if(stmt!=null){stmt.close();System.out.println("get bloquearAsientos cierra el stmt...");}
         if(cnx!=null){ cnx.close(); System.out.println("get bloquearAsientos cierra el cnx...");}
        }
        
        
        
        System.out.println("*** FIN: EJB.getBloquearAsientos() ***"); 
        return bloquearAsientosResp;
        
    }
    @Interceptors({ERTMSWS.LocalInterceptors.Validators.ValidateVenderBoletosReq.class})
    public VenderBoletosResp getVenderBoletos(VenderBoletosReq venderBoletosReq)throws Exception{
        VenderBoletosResp venderBoletosResp = new VenderBoletosResp();
        cnx = null;
        
        stmt = null;
        boolean bResultado = true;

        System.out.println("venderBoletosReq.getTransaccion().getPagos(length): "+venderBoletosReq.getTransaccion().getPagos().length);
        if (venderBoletosReq.getTransaccion().getPagos().length>1)
        {
         for(int i = 0; i < venderBoletosReq.getTransaccion().getAsientos().length;i++)
            venderBoletosReq.getTransaccion().getAsientos()[i].setTipoPago("DIV");
        }
         else
        {
            //agregar los datos de la TDC en el lugar corerspondiente del boleto
            for(int i = 0; i < venderBoletosReq.getTransaccion().getAsientos().length;i++)
            {
                //Boleto TMS
                //REFERENCIA_PAGO   => Numero de Autorizacion
                //ADICIOINAL1        => Nombre del Banco
                //ADICIOINAL2        => Numero de Operacion
                //ADICIOINAL3        => Referencia de la Operacion
                venderBoletosReq.getTransaccion().getAsientos()[i].setTipoPago( venderBoletosReq.getTransaccion().getPagos()[0].getTipoPago());
                venderBoletosReq.getTransaccion().getAsientos()[i].setReferenciaPago( venderBoletosReq.getTransaccion().getPagos()[0].getNumAutorizacion());
                venderBoletosReq.getTransaccion().getAsientos()[i].setReferenciaAdicional1( venderBoletosReq.getTransaccion().getPagos()[0].getNombreBanco());
                venderBoletosReq.getTransaccion().getAsientos()[i].setReferenciaAdicional2( venderBoletosReq.getTransaccion().getPagos()[0].getNumOperacion());
                venderBoletosReq.getTransaccion().getAsientos()[i].setReferenciaAdicional3( venderBoletosReq.getTransaccion().getPagos()[0].getNumReferencia());
            }
         }

        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_TMSWS_EBUS_PKG.tmsws_getVenderBoletosEBus_prc(?, ?,  ?,?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor("BOLETOS_COLLECTION_STR_TYPE", stmt.getConnection());
            ARRAY arrayAsientos = new ARRAY(desc, stmt.getConnection(), this.setAsientosArraytoStringArray(venderBoletosReq.getTransaccion().getAsientos()));
            ARRAY arrayPagos = new ARRAY(desc, stmt.getConnection(), this.setPagosArraytoStringArray(venderBoletosReq.getTransaccion().getPagos()));
            System.out.println("venderBoletosReq.getSesionId(): "+venderBoletosReq.getSesionId());
            System.out.println("arrayAsientos"+arrayAsientos);
            System.out.println("arrayPagos"+arrayPagos);
            ((OraclePreparedStatement)stmt).setLong(1, venderBoletosReq.getSesionId());
            ((OraclePreparedStatement)stmt).setArray(2, arrayAsientos);
            ((OraclePreparedStatement)stmt).setArray(3, arrayPagos);
            ((OraclePreparedStatement)stmt).setLong(4, venderBoletosReq.getTransaccion().getNumeroTransaccion().longValue());

            stmt.registerOutParameter(5,OracleTypes.ARRAY, "FOLIO_BOL_COLLECTION_MV_TYPE"); //FoliosBoletos
            stmt.registerOutParameter(6,OracleTypes.NUMBER); //OperacionExitosa
            stmt.registerOutParameter(7,OracleTypes.VARCHAR); //ErrorCode
            stmt.registerOutParameter(8,OracleTypes.VARCHAR); //ErrrMsg
            
            
            bResultado=stmt.execute();
           
            /* Recuperacion del arreglo de boletos que es devuelto por el procedimiento
             * se hace la recuperacion del Arreglo obtenido como parametro de salida y se transforma en un ResultSet
             * Dicho ResultSet tiene como primera columna el indice del arreglo y en la segunda un objeto que representa
             * el valor del arreglo.
             *
             * Posteriormente se crea un vector para colocar todos los objetos FoliosBoletos que son creados en base a la
             * obtencion de los atributos obtenidos del objeto de la segunda columna del ResultSet
             *
             * Por ultimo se hace la traduccion del Vector a un Array para cumplir con los requisitos de la clase a ser
             * devuelta como respuesta a getVenderBoletos
             */
            
            if (stmt.getString(7).compareTo("000") != 0){
          //      System.out.println(stmt.getString(5));
           //     System.out.println(stmt.getString(6));
                venderBoletosResp.setOperacionExitosa(false);
                venderBoletosResp.setErrorCode(stmt.getString(7));
                venderBoletosResp.setErrorMsg(stmt.getString(8));
            }else{
            
                Array array = (ARRAY) ((OracleCallableStatement)stmt).getOracleObject(5);
                ResultSet rs = array.getResultSet();

                Vector<FoliosBoletos> vFoliosBoletos = new Vector<FoliosBoletos>();

                while(rs.next()){
                    STRUCT obj= (STRUCT)rs.getObject(2);

                    Object[] attrs1=obj.getAttributes();
                    FoliosBoletos folioBoleto = new FoliosBoletos();
               //     System.out.println(attrs1[0].toString());
                    if (attrs1[0] == null)
                        folioBoleto.setNoAsiento(BigInteger.valueOf(Integer.parseInt("0")) ); //NO_ASIENTO
                    else
                        folioBoleto.setNoAsiento(BigInteger.valueOf(Integer.parseInt(attrs1[0].toString()))); //NO_ASIENTO
                    folioBoleto.setSerieBoleto(attrs1[1].toString()); //FOLIO_BOLETO
                    folioBoleto.setFolioPreimpreso(attrs1[2].toString()); //FOLIO_PREIMPRESO
                    System.out.println("Folio Preimpreso: "+attrs1[2].toString());
                    folioBoleto.setNombrePasajero(attrs1[3].toString()); //NOMBRE_PASAJERO
                    folioBoleto.setOrigen(attrs1[4].toString()); //CIUDAD_VENTA
                    folioBoleto.setNumeroTransaccion(venderBoletosReq.getTransaccion().getNumeroTransaccion()); // NUMERO_TRANSACCION
                    folioBoleto.setDatosCodigoBarras((attrs1[8]!=null?attrs1[8].toString():"")+(attrs1[9]!=null?attrs1[9].toString():""));
                    folioBoleto.setSubtotal(BigDecimal.valueOf(Double.valueOf(((attrs1[10]==null)?attrs1[12].toString():attrs1[10].toString()))));
                    folioBoleto.setIva(BigDecimal.valueOf(Double.valueOf(((attrs1[11]==null)?"0":attrs1[11].toString()))));
                    folioBoleto.setTotal(BigDecimal.valueOf(Double.valueOf(attrs1[12].toString())));
                    System.out.println("Datos Adicionales(getVenderBoletos): "+attrs1[13].toString()+ attrs1[14].toString());
                    //cadena separada por ,(coma) fechaViaje,horaViaje,destino,tipoPasajero,edad,fechaHoraTransaccion
                    String cadena = attrs1[13].toString() + attrs1[14].toString();
                    String[] datos = cadena.split(",");
                    folioBoleto.setFechaViaje(datos[0]);
                    folioBoleto.setHoraViaje(datos[1]);
                    folioBoleto.setDestino(datos[2]);
                    folioBoleto.setTipoPasajero(datos[3]);
                    folioBoleto.setEdad(Integer.valueOf(datos[4]));
                    folioBoleto.setFechaHoraTransaccion(datos[5]);
                    folioBoleto.setCantidadBoletosTransaccion( venderBoletosReq.getTransaccion().getAsientos().length);
                    //System.out.println("registro "+registro);
                    vFoliosBoletos.add(folioBoleto);
                }
                //System.out.println("collection: "+collection);
                FoliosBoletos arrfoliosBoletos[] = new FoliosBoletos[vFoliosBoletos.size()];
                vFoliosBoletos.toArray(arrfoliosBoletos);




                venderBoletosResp.setFoliosBoletos(arrfoliosBoletos);

                if (stmt.getInt(6)==1)
                    venderBoletosResp.setOperacionExitosa(true);
                else
                    venderBoletosResp.setOperacionExitosa(false);
                venderBoletosResp.setErrorCode(stmt.getString(7));
                venderBoletosResp.setErrorMsg(stmt.getString(8));
            }
           
        
            System.out.println("CORRECTO");
           
        } catch (Exception ex) {
          //  venderBoletosResp = null;
             System.out.println("INCORRECTO");
                ex.printStackTrace();
                throw ex;
               
            }
        finally{
            if(stmt!=null)
                stmt.close();
        System.out.println("getvenderBoletos cierra el stmt...");
        if(cnx!=null) {cnx.close(); System.out.println("getvenderBoletoscierra el cnx...");}
        }
        
        return venderBoletosResp;
    }

    @Interceptors({ERTMSWS.LocalInterceptors.Validators.ValidateValidarBoletoReq.class})
    public ValidarBoletoResp getValidarBoleto(ValidarBoletoReq validarBoletoReq)throws Exception{
        ValidarBoletoResp validarBoletoResp = new ValidarBoletoResp();
        cnx = null;
        
        stmt = null;
        
        boolean bResultado = true;
        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_TMSWS_EBUS_PKG.tmsws_getValidarBol_EBUS_PRC(?, ?,  ?,  ?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            StructDescriptor descFolio_Boleto = oracle.sql.StructDescriptor.createDescriptor("FOLIO_BOLETO_MOVIL_TYPE",stmt.getConnection()); 
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor("FOLIO_BOL_COLLECTION_MV_TYPE", stmt.getConnection());
    
            ARRAY arrayFoliosBoletos = new ARRAY(desc, stmt.getConnection(),this.setFoliosBoletosArraytoStructArray(validarBoletoReq.getFoliosBoletos(),descFolio_Boleto));
    
            ((OraclePreparedStatement)stmt).setInt(1, validarBoletoReq.getSesionId());
            ((OraclePreparedStatement)stmt).setArray(2, arrayFoliosBoletos);
            ((OraclePreparedStatement)stmt).setString(3, validarBoletoReq.getTipoMovimiento());
            
            stmt.registerOutParameter(4,OracleTypes.ARRAY, "FOLIO_BOL_COLLECTION_MV_TYPE"); //FoliosBoletos
            stmt.registerOutParameter(5,OracleTypes.ARRAY,"BOLETOS_COLLECTION_STR_TYPE"); //arrayasientos
            stmt.registerOutParameter(6,OracleTypes.NUMBER); //OperacionExitosa
            stmt.registerOutParameter(7,OracleTypes.VARCHAR); //ErrorCode
            stmt.registerOutParameter(8,OracleTypes.VARCHAR); //ErrrMsg
            
            
            bResultado=stmt.execute();
            
            
            
            
 
            

            if (stmt.getInt(6)==1){
                validarBoletoResp.setOperacionExitosa(true);
                
                Array arrayF = (ARRAY) ((OracleCallableStatement)stmt).getOracleObject(4);
                ResultSet rsF = arrayF.getResultSet();

                Vector<FoliosBoletos> vFoliosBoletos = new Vector<FoliosBoletos>();

                while(rsF.next()){
                    STRUCT obj= (STRUCT)rsF.getObject(2);
                    Object[] attrs1=obj.getAttributes();
                    FoliosBoletos folioBoleto = new FoliosBoletos();
                    //System.out.println(attrs1[0].toString());
                    folioBoleto.setNoAsiento(BigInteger.valueOf(Integer.parseInt(attrs1[0].toString()))); //NO_ASIENTO
                    folioBoleto.setSerieBoleto(attrs1[1].toString()); //FOLIO_BOLETO
                    folioBoleto.setFolioPreimpreso(attrs1[2].toString()); //FOLIO_PREIMPRESO
                    folioBoleto.setNombrePasajero(attrs1[3].toString()); //NOMBRE_PASAJERO
                    folioBoleto.setOrigen(attrs1[4].toString()); //CIUDAD_VENTA
                    //System.out.println("registro "+registro);
                    vFoliosBoletos.add(folioBoleto);
                }
                //System.out.println("collection: "+collection);
                FoliosBoletos arrfoliosBoletos[] = new FoliosBoletos[vFoliosBoletos.size()];
                vFoliosBoletos.toArray(arrfoliosBoletos);

                validarBoletoResp.setFoliosBoletos(arrfoliosBoletos);

                
                
                Array array = (ARRAY) ((OracleCallableStatement)stmt).getOracleObject(5);
                ResultSet rs = array.getResultSet();
                Vector<Asientos> vAsientos = new Vector<Asientos>();
                while(rs.next()){
                    //STRUCT obj= (STRUCT)rs.getObject(2);
                    //rs.getString(1);
                    System.out.println("StringAsiento: "+rs.getString(2));
                    vAsientos.add(setStringtoAsientos(rs.getString(2)));
                }
                Asientos arrAsientos[] = new Asientos[vAsientos.size()];
                vAsientos.toArray(arrAsientos);
                validarBoletoResp.setAsientos(arrAsientos);

                ERTMSWS.clases.Asientos[] asi = validarBoletoResp.getAsientos();
                ERTMSWS.clases.FoliosBoletos[] fols = validarBoletoResp.getFoliosBoletos();
                for(int i = 0; i<asi.length; i++)
                {
                    double cargo=0;
                    String operacion = "CN";
                    if(validarBoletoReq.getTipoMovimiento().equals("T"))
                        operacion = "HO";

                    Vector re = (Vector) em.createNativeQuery("SELECT XER_TMSWS_EBUS_PKG.TMS_CARGO_HO_CN_EBUS_FNC('"+asi[i].getFolioPreimpreso()+"','"+operacion+"') FROM DUAL").getResultList();
                    if(re.size()==0)
                        cargo = 0;
                    else
                    {
                        Vector vc = (Vector)re.get(0);
                        cargo= Double.valueOf(vc.get(0).toString());
                    }
                    double imp = asi[i].getImporteBoleto();
                    if(imp<0)
                        imp = imp*(-1);
                    fols[i].setImporteDevolucion(BigDecimal.valueOf(imp-cargo));
                    asi[i].setImporteCargo(BigDecimal.valueOf(cargo));
                    fols[i].setImporteCargo(BigDecimal.valueOf(cargo));
                    fols[i].setSerieBoleto(asi[i].getSerieBoleto());
                    fols[i].setNombrePasajero(asi[i].getNombrePasajero());
                    //Boleto TMS 
                    //REFERENCIA_PAGO   => Numero de Autorizacion
                    //ADICIOINAL1        => Nombre del Banco
                    //ADICIOINAL2        => Numero de Operacion
                    //ADICIOINAL3        => Referencia de la Operacion

                    fols[i].setNumReferencia(asi[i].getReferenciaAdicional3());
                    fols[i].setNumAutorizacion(asi[i].getReferenciaPago());
                    fols[i].setNumOperacion(asi[i].getReferenciaAdicional2());
                }
                
            }
            else{
                validarBoletoResp.setOperacionExitosa(false);
                
            }
            validarBoletoResp.setErrorCode(stmt.getString(7));
            validarBoletoResp.setErrorMsg(stmt.getString(8));

            
        
            System.out.println("CORRECTO");
            
        } catch (Exception ex){
            //stmt.close();
           // if(cnx!=null) cnx.close();
             System.out.println("INCORRECTO");
                ex.printStackTrace();
                throw ex;
               
            }
        finally{
            if(stmt!=null)
                stmt.close();
        System.out.println("getvalidarBoleto cierra el stmt...");
        if(cnx!=null){ cnx.close();
        System.out.println("getvalidarBoleto cierra el cnx...");}
        }
        
        return validarBoletoResp;
    }
    @Interceptors({ERTMSWS.LocalInterceptors.Validators.ValidateCancelarBoletosReq.class})
    public CancelarBoletosResp getCancelarBoletos(CancelarBoletosReq cancelarBoletosReq)throws Exception{
        CancelarBoletosResp cancelarBoletosResp = new CancelarBoletosResp();
        cnx = null;        
        stmt = null;
        
        //PROCEDURE TMSWS_GetCancelaBoleto_prc(P_SESIONID NUMBER,
        //P_FoliosBoletosCollec  FOLIO_BOL_COLLECTION_MV_TYPE, 
        //P_MotivoCancel VARCHAR2, P_OPERACIONEXITOSA OUT NUMBER, P_ERRORCODE OUT VARCHAR2, P_ERRORMSG OUT VARCHAR2);
        boolean bResultado = true;
        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      //"XER_TMSWS_PKG.TMSWS_GetCancelaBoleto_prc( ?, ?, ?,   ?,?,?,?); " +
                      "xer_tmsws_ebus_pkg.TMS_GetCancelaBoleto_EBUS_prc( ?, ?, ?,   ?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            StructDescriptor descFolio_Boleto = oracle.sql.StructDescriptor.createDescriptor("FOLIO_BOLETO_MOVIL_TYPE",stmt.getConnection()); 
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor("FOLIO_BOL_COLLECTION_MV_TYPE", stmt.getConnection());
    
            ARRAY arrayFoliosBoletos = new ARRAY(desc, stmt.getConnection(),this.setFoliosBoletosArraytoStructArray(cancelarBoletosReq.getFoliosBoletos(),descFolio_Boleto));
    
            ((OraclePreparedStatement)stmt).setInt(1, cancelarBoletosReq.getSesionId());
            ((OraclePreparedStatement)stmt).setArray(2, arrayFoliosBoletos);
            ((OraclePreparedStatement)stmt).setString(3, cancelarBoletosReq.getMotivoCancelacion());
            stmt.registerOutParameter(4,OracleTypes.ARRAY,"BOLETOS_COLLECTION_STR_TYPE"); //arrayasientos
         
            stmt.registerOutParameter(5,OracleTypes.NUMBER); //OperacionExitosa
            stmt.registerOutParameter(6,OracleTypes.VARCHAR); //ErrorCode
            stmt.registerOutParameter(7,OracleTypes.VARCHAR); //ErrrMsg
            
            
            bResultado=stmt.execute();
            
            
            
            
 
            

            if (stmt.getInt(5)==1){
                cancelarBoletosResp.setOperacionExitosa(true);
                Array array = (ARRAY) ((OracleCallableStatement)stmt).getOracleObject(4);
                ResultSet rs = array.getResultSet();
                Vector<Asientos> vAsientos = new Vector<Asientos>();
                while(rs.next()){
                    //STRUCT obj= (STRUCT)rs.getObject(2);
                    //rs.getString(1);
                    System.out.println("StringAsiento: "+rs.getString(2));
                    vAsientos.add(setStringtoAsientos(rs.getString(2)));
                }
                Asientos arrAsientos[] = new Asientos[vAsientos.size()];
                vAsientos.toArray(arrAsientos);
                cancelarBoletosResp.setAsientos(arrAsientos);
                
    //            FoliosBoletos arrfoliosBoletos[] = new FoliosBoletos[vFoliosBoletos.size()];
      //          vFoliosBoletos.toArray(arrfoliosBoletos);
            }
            else{
                cancelarBoletosResp.setOperacionExitosa(false);
                
                


            }
            cancelarBoletosResp.setErrorCode(stmt.getString(6));
            cancelarBoletosResp.setErrorMsg(stmt.getString(7));

            
        
            System.out.println("CORRECTO");
            
        } catch (Exception ex){
            //stmt.close();
           // if(cnx!=null) cnx.close();
             System.out.println("INCORRECTO");
                ex.printStackTrace();
                throw ex;
               
            }
        finally{
            if(stmt!=null)
                stmt.close();
        System.out.println("getcancelarBoletos cierra el stmt...");
        if(cnx!=null){ cnx.close(); System.out.println("getcancelarBoletos cierra el cnx...");}
        }
        
        return cancelarBoletosResp;
    }
    @Interceptors({ERTMSWS.LocalInterceptors.Validators.ValidateCambioHorarioReq.class})
    public CambioHorarioResp getCambioHorario(CambioHorarioReq cambioHorarioReq)throws Exception{
        CambioHorarioResp cambioHorarioResp = new CambioHorarioResp();
        cnx = null;        
        stmt = null;
        /*
         TCAPU.XER_TMSWS_PKG.TMSWS_GETCAMBIOHCANJEBAREQ_PRC ( P_SESION_ID, 
         P_FOLIOS_BOLETOS_IN, 
         P_ASIENTOS, 
         P_TIPO_OPERACION, 
         P_FOLIOS_BOLETOS, 
         P_OPERACION_EXITOSA, 
         P_ERROR_CODE, 
         P_ERROR_MSG );
         **/
        boolean bResultado = true;
       long numt = this.getNumeroTransaccion();
        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_TMSWS_EBUS_PKG.TMSWS_GETCAMBIOHO_EBUS_PRC (?, ?, ?, ?,   ?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
           
            StructDescriptor descFolio_Boleto = oracle.sql.StructDescriptor.createDescriptor("FOLIO_BOLETO_MOVIL_TYPE",stmt.getConnection()); 
            ArrayDescriptor descFolio_Bol_Col = ArrayDescriptor.createDescriptor("FOLIO_BOL_COLLECTION_MV_TYPE", stmt.getConnection());
            ARRAY arrayFoliosBoletos = new ARRAY(descFolio_Bol_Col, stmt.getConnection(),this.setFoliosBoletosArraytoStructArray(cambioHorarioReq.getFoliosBoletos(),descFolio_Boleto));
     
            ArrayDescriptor descAsientos = ArrayDescriptor.createDescriptor("BOLETOS_COLLECTION_STR_TYPE", stmt.getConnection());
            ARRAY arrayAsientos = new ARRAY(descAsientos, stmt.getConnection(), this.setAsientosArraytoStringArray(cambioHorarioReq.getAsientos()));
            ((OraclePreparedStatement)stmt).setInt(1, cambioHorarioReq.getSesionId());
            ((OraclePreparedStatement)stmt).setArray(2, arrayFoliosBoletos);
            ((OraclePreparedStatement)stmt).setArray(3, arrayAsientos);
            ((OraclePreparedStatement)stmt).setString(4, "HO");
            stmt.registerOutParameter(5,OracleTypes.ARRAY, "FOLIO_BOL_COLLECTION_MV_TYPE"); //FoliosBoletos
            stmt.registerOutParameter(6,OracleTypes.NUMBER); //OperacionExitosa
            stmt.registerOutParameter(7,OracleTypes.VARCHAR); //ErrorCode
            stmt.registerOutParameter(8,OracleTypes.VARCHAR); //ErrrMsg
            ((OraclePreparedStatement)stmt).setLong(9, numt);
            
            bResultado=stmt.execute();
           
            /* Recuperacion del arreglo de boletos que es devuelto por el procedimiento
             * se hace la recuperacion del Arreglo obtenido como parometro de salida y se transforma en un ResultSet
             * Dicho ResultSet tiene como primera columna el indice del arreglo y en la segunda un objeto que representa
             * el valor del arreglo.
             *
             * Posteriormente se crea un vector para colocar todos lso objetos FoliosBoletos que son creados en base a la 
             * obtencion de los atributos obtenidos del objeto de la segunda columna del ResultSet
             *
             * Por oltimo se hace la traduccion del Vector a un Array para cumplir con los requisitos de la clase a ser
             * devuelta como respuesta a getVenderBoletos
             */
            System.out.println("stmt.getStrings:");
            System.out.println("stmt.getString(6): "+stmt.getInt(6));
            System.out.println("stmt.getString(7): "+stmt.getString(7));
            System.out.println("stmt.getString(8): "+stmt.getString(8));
            if (stmt.getString(7).compareTo("000") != 0){
                System.out.println("stmt.getString(7): "+stmt.getString(7));
                System.out.println("stmt.getString(8): "+stmt.getString(8));
                cambioHorarioResp.setOperacionExitosa(false);
                cambioHorarioResp.setErrorCode(stmt.getString(7));
                cambioHorarioResp.setErrorMsg(stmt.getString(8));
            }else{
            
                Array array = (ARRAY) ((OracleCallableStatement)stmt).getOracleObject(5);
                ResultSet rs = array.getResultSet();

                Vector<FoliosBoletos> vFoliosBoletos = new Vector<FoliosBoletos>();

                while(rs.next()){
                    System.out.println("antes del STRUCT ");
                    STRUCT obj= (STRUCT)rs.getObject(2);
                    System.out.println("despues del STRUCT ");
                    System.out.println("antes del Object ");
                    Object[] attrs1=obj.getAttributes();
                    System.out.println("despues del Object ");
                    FoliosBoletos folioBoleto = new FoliosBoletos();
                    System.out.println("attrs1[0] :"+attrs1[0]);
                    System.out.println("attrs1[1] :"+attrs1[1]);
                    System.out.println("attrs1[2] :"+attrs1[2]);
                    System.out.println("attrs1[3] :"+attrs1[3]);
                    System.out.println("attrs1[4] :"+attrs1[4]);
                    folioBoleto.setNoAsiento(BigInteger.valueOf(Integer.parseInt(attrs1[0].toString()))); //NO_ASIENTO
                    folioBoleto.setSerieBoleto(attrs1[1].toString()); //FOLIO_BOLETO
                    folioBoleto.setFolioPreimpreso(attrs1[2].toString()); //FOLIO_PREIMPRESO
                    folioBoleto.setNombrePasajero(attrs1[3].toString()); //NOMBRE_PASAJERO
                    folioBoleto.setOrigen(attrs1[4].toString()); //CIUDAD_VENTA
                    folioBoleto.setNumeroTransaccion(BigInteger.valueOf(numt)); // NUMERO_TRANSACCION
                    folioBoleto.setDatosCodigoBarras((attrs1[8]!=null?attrs1[8].toString():"")+(attrs1[9]!=null?attrs1[9].toString():""));
                    folioBoleto.setSubtotal(BigDecimal.valueOf(Double.valueOf(((attrs1[10]==null)?attrs1[12].toString():attrs1[10].toString()))));
                    folioBoleto.setIva(BigDecimal.valueOf(Double.valueOf(((attrs1[11]==null)?"0":attrs1[11].toString()))));
                    folioBoleto.setTotal(BigDecimal.valueOf(Double.valueOf(attrs1[12].toString())));
                    System.out.println("Datos Adicionales(getCambioHorario): "+attrs1[13].toString()+ attrs1[14].toString());
                    //cadena separada por ,(coma) fechaViaje,horaViaje,destino,tipoPasajero,edad,fechaHoraTransaccion

                    //cadena separada por ,(coma) fechaViaje,horaViaje,destino,tipoPasajero,edad,fechaHoraTransaccion
                    String cadena = attrs1[13].toString() + attrs1[14].toString();
                    String[] datos = cadena.split(",");
                    folioBoleto.setFechaViaje(datos[0]);
                    folioBoleto.setHoraViaje(datos[1]);
                    folioBoleto.setDestino(datos[2]);
                    folioBoleto.setTipoPasajero(datos[3]);
                    folioBoleto.setEdad(Integer.valueOf(datos[4]));
                    folioBoleto.setFechaHoraTransaccion(datos[5]);
                    folioBoleto.setCantidadBoletosTransaccion( cambioHorarioReq.getAsientos().length);
                    
                    vFoliosBoletos.add(folioBoleto);
                }
                //System.out.println("collection: "+collection);
                FoliosBoletos arrfoliosBoletos[] = new FoliosBoletos[vFoliosBoletos.size()];
                vFoliosBoletos.toArray(arrfoliosBoletos);




                cambioHorarioResp.setFoliosBoletos(arrfoliosBoletos);

                if (stmt.getInt(6)==1)
                    cambioHorarioResp.setOperacionExitosa(true);
                else
                    cambioHorarioResp.setOperacionExitosa(false);
                cambioHorarioResp.setErrorCode(stmt.getString(7));
                cambioHorarioResp.setErrorMsg(stmt.getString(8));
            }
           
        
            System.out.println("CORRECTO");
           
        } catch (Exception ex) {
          //  venderBoletosResp = null;
             System.out.println("INCORRECTO");
                ex.printStackTrace();
                throw ex;
               
            }
        finally{
        if(stmt!=null)
            stmt.close();
        System.out.println("getcambioHorario cierra el stmt...");
        if(cnx!=null) {cnx.close();System.out.println("getcambioHorario cierra el cnx...");}
        }
        return cambioHorarioResp;
    }
    @Interceptors({ERTMSWS.LocalInterceptors.Validators.ValidateCanjeBAReq.class})
    public CanjeBAResp getCanjeBA(CanjeBAReq canjeBAReq)throws Exception{
        CanjeBAResp canjeBAResp = new CanjeBAResp();
        cnx = null;        
        stmt = null;
        /*
         TCAPU.XER_TMSWS_PKG.TMSWS_GETCAMBIOHCANJEBAREQ_PRC ( P_SESION_ID, 
         P_FOLIOS_BOLETOS_IN, 
         P_ASIENTOS, 
         P_TIPO_OPERACION, 
         P_FOLIOS_BOLETOS, 
         P_OPERACION_EXITOSA, 
         P_ERROR_CODE, 
         P_ERROR_MSG );
         **/
        boolean bResultado = true;
        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_TMSWS_PKG.TMSWS_GETCAMBIOHCANJEBAREQ_PRC (?, ?, ?, ?,   ?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
           
            StructDescriptor descFolio_Boleto = oracle.sql.StructDescriptor.createDescriptor("FOLIO_BOLETO_MOVIL_TYPE",stmt.getConnection()); 
            ArrayDescriptor descFolio_Bol_Col = ArrayDescriptor.createDescriptor("FOLIO_BOL_COLLECTION_MV_TYPE", stmt.getConnection());
    
            ARRAY arrayFoliosBoletos = new ARRAY(descFolio_Bol_Col, stmt.getConnection(),this.setFoliosBoletosArraytoStructArray(canjeBAReq.getFoliosBoletos(),descFolio_Boleto));
     
            ArrayDescriptor descAsientos = ArrayDescriptor.createDescriptor("BOLETOS_COLLECTION_STR_TYPE", stmt.getConnection());
            ARRAY arrayAsientos = new ARRAY(descAsientos, stmt.getConnection(), this.setAsientosArraytoStringArray(canjeBAReq.getAsientos()));
            
            ((OraclePreparedStatement)stmt).setInt(1, canjeBAReq.getSesionId());
            ((OraclePreparedStatement)stmt).setArray(2, arrayFoliosBoletos);
            ((OraclePreparedStatement)stmt).setArray(3, arrayAsientos);
            ((OraclePreparedStatement)stmt).setString(4, "AC");
            stmt.registerOutParameter(5,OracleTypes.ARRAY, "FOLIO_BOL_COLLECTION_MV_TYPE"); //FoliosBoletos
            stmt.registerOutParameter(6,OracleTypes.NUMBER); //OperacionExitosa
            stmt.registerOutParameter(7,OracleTypes.VARCHAR); //ErrorCode
            stmt.registerOutParameter(8,OracleTypes.VARCHAR); //ErrrMsg
            
            
            bResultado=stmt.execute();
            
            
            
            
           
            /* Recuperacion del arreglo de boletos que es devuelto por el procedimiento
             * se hace la recuperacion del Arreglo obtenido como parometro de salida y se transforma en un ResultSet
             * Dicho ResultSet tiene como primera columna el indice del arreglo y en la segunda un objeto que representa
             * el valor del arreglo.
             *
             * Posteriormente se crea un vector para colocar todos lso objetos FoliosBoletos que son creados en base a la 
             * obtencion de los atributos obtenidos del objeto de la segunda columna del ResultSet
             *
             * Por oltimo se hace la traduccion del Vector a un Array para cumplir con los requisitos de la clase a ser
             * devuelta como respuesta a getVenderBoletos
             */
            System.out.println("stmt.getString(7");
            System.out.println(stmt.getInt(6));
            System.out.println(stmt.getString(7));
                System.out.println(stmt.getString(8));
            if (stmt.getString(7).compareTo("000") != 0){
                System.out.println(stmt.getString(7));
                System.out.println(stmt.getString(8));
                canjeBAResp.setOperacionExitosa(false);
                canjeBAResp.setErrorCode(stmt.getString(7));
                canjeBAResp.setErrorMsg(stmt.getString(8));
            }else{
            
                Array array = (ARRAY) ((OracleCallableStatement)stmt).getOracleObject(5);
                ResultSet rs = array.getResultSet();

                Vector<FoliosBoletos> vFoliosBoletos = new Vector<FoliosBoletos>();

                while(rs.next()){
                    STRUCT obj= (STRUCT)rs.getObject(2);

                    Object[] attrs1=obj.getAttributes();
                    FoliosBoletos folioBoleto = new FoliosBoletos();
                    System.out.println(attrs1[0].toString());
                    folioBoleto.setNoAsiento(BigInteger.valueOf(Integer.parseInt(attrs1[0].toString()))); //NO_ASIENTO
                    folioBoleto.setSerieBoleto(attrs1[1].toString()); //FOLIO_BOLETO
                    folioBoleto.setFolioPreimpreso(attrs1[2].toString()); //FOLIO_PREIMPRESO
                    folioBoleto.setNombrePasajero(attrs1[3].toString()); //NOMBRE_PASAJERO
                    folioBoleto.setOrigen(attrs1[4].toString()); //CIUDAD_VENTA
                    
                    vFoliosBoletos.add(folioBoleto);
                } 
                //System.out.println("collection: "+collection);
                FoliosBoletos arrfoliosBoletos[] = new FoliosBoletos[vFoliosBoletos.size()];
                vFoliosBoletos.toArray(arrfoliosBoletos);




                canjeBAResp.setFoliosBoletos(arrfoliosBoletos);

                if (stmt.getInt(6)==1)
                    canjeBAResp.setOperacionExitosa(true);
                else
                    canjeBAResp.setOperacionExitosa(false);
                canjeBAResp.setErrorCode(stmt.getString(7));
                canjeBAResp.setErrorMsg(stmt.getString(8));
            }
            
        
            System.out.println("CORRECTO");
            
        } catch (Exception ex) {
          //  venderBoletosResp = null;
             System.out.println("INCORRECTO");
                ex.printStackTrace();
                throw ex;
               
            }
        finally{
        if(stmt!=null) stmt.close();
        if(cnx!=null) cnx.close();
        }
        return canjeBAResp;
    }
    @Interceptors({ERTMSWS.LocalInterceptors.Validators.ValidateLogoutReq.class})
    public LogoutResp getLogout(LogoutReq logoutReq)throws Exception{
        LogoutResp logoutResp = new LogoutResp();
       
        cnx = null;
        stmt = null;
        boolean bResultado;
        try {

            /*
             *PROCEDURE TMSWS_getLogOut_Prc(P_SESION_ID IN NUMBER, P_USUARIONUMERO IN VARCHAR2,  P_USUARIOCONTRASENA VARCHAR2,   P_OPERACIONEXITOSA OUT NUMBER, P_ERRORCODE OUT VARCHAR2, P_ERRORMSG OUT VARCHAR2)  ;

*/
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_TMSWS_PKG.TMSWS_GETLOGOUT_PRC(?, ?, ?,  ?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            
            System.out.println(q1);
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setInt(1,logoutReq.getSesionId());
            ((OraclePreparedStatement)stmt).setString(2, logoutReq.getUsuarioNumero());
            ((OraclePreparedStatement)stmt).setString(3, logoutReq.getUsuarioContrasena());
            
            stmt.registerOutParameter(4,OracleTypes.NUMBER);
            stmt.registerOutParameter(5,OracleTypes.VARCHAR);
            stmt.registerOutParameter(6,OracleTypes.VARCHAR);
            
        
            bResultado=stmt.execute();
           
           
            if (stmt.getString(5).compareTo("0")!= 0){
                System.out.println(stmt.getString(5));
                System.out.println(stmt.getString(6));
                logoutResp.setOperacionExitosa(false);
                logoutResp.setErrorCode(stmt.getString(5));
                logoutResp.setErrorMsg(stmt.getString(6));
            }
            logoutResp.setOperacionExitosa((stmt.getInt(4)==1)?true:false);
            /*
                logoutResp.setOperacionExitosa(true);
            else
                logoutResp.setOperacionExitosa(false);
             */
            logoutResp.setErrorCode(stmt.getString(5));
            logoutResp.setErrorMsg(stmt.getString(6));
           
         
            System.out.println("Correcto");
        

         
        }catch (Exception ex) {
           System.out.println("INCORRECTO");
         
           throw ex;
           
        }finally{
        if(stmt!=null) stmt.close();
        if(cnx!=null) cnx.close();
        }
        
        
        return logoutResp;
    }

    private String[] setAsientosArraytoStringArray(Asientos[] aAsientos) {
        String[] sArray = new String[aAsientos.length];
        
        SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for(int i = 0; i < aAsientos.length;i++){
            String sasiento = ",";
            sasiento += aAsientos[i].getEmpresa()+",";
            sasiento += aAsientos[i].getServicio()+",";
            sasiento += aAsientos[i].getCaja()+",";
            sasiento +=","; //CORTE_ID NUMBER
            sasiento += aAsientos[i].getClaveCorrida()+",";
            if (aAsientos[i].getClienteId() == 0)
                sasiento += ",";
            else
                sasiento += aAsientos[i].getClienteId()+",";
            
            sasiento += aAsientos[i].getNoAsiento()+",";
            sasiento += aAsientos[i].getNombrePasajero()+",";
            sasiento += aAsientos[i].getTipoPasajero()+",";
            sasiento += aAsientos[i].getTipoPago()+",";
            sasiento += aAsientos[i].getReferenciaPago()+",";
            sasiento += aAsientos[i].getImporteBoleto()+",";
            sasiento +=","; //TIPO_OPERACION VARCHAR2(2)
            sasiento +=","; //RESERVACION_ID NUMBER
            sasiento +=","; //BOLETO_RELACIONADO_ID NUMBER
            sasiento +=","; //DIAS_VALIDEZ_BOLETO_ABIERTO NUMBER
            sasiento += aAsientos[i].getFolioPreimpreso()+",";
            sasiento += aAsientos[i].getSerieBoleto()+",";
            sasiento += aAsientos[i].getSucursalClave()+",";
            sasiento += aAsientos[i].getOrigen()+",";
            sasiento += aAsientos[i].getDestino()+",";
            sasiento +=","; //TIPO_TRANSACCION VARCHAR2(1)
            sasiento += aAsientos[i].getUsuarioNumero()+",";
            sasiento += sdfFechaHora.format(aAsientos[i].getFechaHoraVenta())+",";
            sasiento += aAsientos[i].getAutorizadoPor()+",";
            sasiento +=","; //CREADO_POR NUMBER
            sasiento +=","; //ULTIMA ACTUALIZACION POR
            sasiento +=aAsientos[i].getReferenciaAdicional1()+","; 
            sasiento +=aAsientos[i].getReferenciaAdicional2()+","; 
            sasiento +=aAsientos[i].getReferenciaAdicional3()+","; 
            sasiento +=aAsientos[i].getReferenciaAdicional4()+","; 
            sasiento +=aAsientos[i].getReferenciaAdicional5()+","; 
            sasiento +=aAsientos[i].getReferenciaAdicional6()+","; 
            sasiento += aAsientos[i].getTipoBoleto()+",";
            sasiento +=aAsientos[i].getReferenciaAdicional8()+","; 
            sasiento +=aAsientos[i].getReferenciaAdicional9()+","; 
            sasiento +=aAsientos[i].getReferenciaAdicional10()+",";
            sasiento +=","; //ADICIONAL11
            sasiento +=aAsientos[i].getReferenciaAdicional12()+","; //ADICIONAL12
            sasiento +=aAsientos[i].getOrigenCorrida()+","; //ADICIONAL13 origen ruta
            sasiento +=","; //ADICIONAL14
            sasiento +=","; //ADICIONAL15   genero H/M
            sasiento +=","; //ADICIONAL16   edad
            sasiento +=","; //ADICIONAL17   email
            sasiento +=","; //ADICIONAL18
            sasiento +=","; //ADICIONAL19 Sentido del Viaje I=ida/R=regreso
            sasiento +=","; //ADICIONAL20
            //sasiento +=","; //RUTA_ORIGEN
            sasiento += aAsientos[i].getOrigenCorrida()+","; //RUTA_ORIGEN
            sasiento += aAsientos[i].getTipoVenta()+",";
            sasiento += aAsientos[i].getClaveReservacion()+",";
            sasiento +=","; //REFERENCIA_ORIGINAL VARCHAR2(30) y BOLETO_REFERENCIADO_ID NUMBER
            sasiento +=","; //BOLETO_REFERENCIADO_ID NUMBER
            sasiento +=aAsientos[i].getGeneroPasajero()+","; //   genero H/M
            sasiento +=((aAsientos[i].getEdad()==null)?"18":aAsientos[i].getEdad())+",";           //   edad
            sasiento +=aAsientos[i].getEmail()+",";          //   email
            sasiento +=aAsientos[i].getSentidoViaje()+",";       //   Sentido del Viaje I=ida/R=regreso
            sasiento +=(aAsientos[i].getImporteCargo()==null?0:aAsientos[i].getImporteCargo())+",";       //   Sentido del Viaje I=ida/R=regreso
            System.out.println("ImporteCargo: "+aAsientos[i].getImporteCargo());
            System.out.println("aAsientos["+i+"]: "+sasiento);
            sArray[i] = new String(sasiento);
        }
        return sArray;
    }


    private String[] setPagosArraytoStringArray(Pagos[] aPagos) {
        String[] sArray = new String[aPagos.length];

        SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for(int i = 0; i < aPagos.length;i++){
            String stringPago = ",";
            stringPago += aPagos[i].getTipoPago()+",";
            stringPago += aPagos[i].getImportePago()+",";
            stringPago += aPagos[i].getNumOperacion()+",";
            stringPago +=aPagos[i].getNumReferencia()+","; 
            stringPago += aPagos[i].getNumAutorizacion()+",";
            stringPago +=aPagos[i].getNumTarjeta()+",";
            stringPago +=aPagos[i].getNombreBanco()+",";

            System.out.println(stringPago);
            sArray[i] = new String(stringPago);
        }
        return sArray;
    }

    private Asientos setStringtoAsientos( String sArray) throws Exception {
        Asientos aAsientos = new Asientos();
        
        
        SimpleDateFormat sdfFH = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
            String asientosplit[] = sArray.split(",");
            aAsientos.setEmpresa(asientosplit[1]);
            aAsientos.setServicio(asientosplit[2]);
            aAsientos.setCaja(asientosplit[3]);
          //  sasiento +=","; //CORTE_ID NUMBER
            aAsientos.setClaveCorrida(asientosplit[5]);
            if (asientosplit[6].compareTo("") == 0)
             aAsientos.setClienteId(0);
                     
            else
                aAsientos.setClienteId(Integer.parseInt(asientosplit[6]));
                
            if (asientosplit[7].compareTo("") == 0)
                aAsientos.setNoAsiento(0);
            else
                aAsientos.setNoAsiento(Integer.parseInt(asientosplit[7]));
            
            aAsientos.setNombrePasajero(asientosplit[8]);
            aAsientos.setTipoPasajero(asientosplit[9]);
            aAsientos.setTipoPago(asientosplit[10]);
            aAsientos.setReferenciaPago(asientosplit[11]);
            if (asientosplit[12].compareTo("") == 0)
                aAsientos.setImporteBoleto(Float.parseFloat("0.0"));
            else
                aAsientos.setImporteBoleto(Float.parseFloat(asientosplit[12]));
            //TIPO_OPERACION VARCHAR2(2)
            //RESERVACION_ID NUMBER
            //BOLETO_RELACIONADO_ID NUMBER
            //DIAS_VALIDEZ_BOLETO_ABIERTO NUMBER
            aAsientos.setFolioPreimpreso(asientosplit[17]);
            aAsientos.setSerieBoleto(asientosplit[18]);
            aAsientos.setSucursalClave(asientosplit[19]);
            aAsientos.setOrigen(asientosplit[20]);
            aAsientos.setDestino(asientosplit[21]);
            //TIPO_TRANSACCION VARCHAR2(1)
            aAsientos.setUsuarioNumero(asientosplit[23]);
            aAsientos.setFechaHoraVenta(sdfFH.parse(asientosplit[24]));
            
            if (asientosplit[25].compareTo("") == 0)
                aAsientos.setAutorizadoPor(0);
            else
                aAsientos.setAutorizadoPor(Integer.parseInt(asientosplit[25]));
            
            
            //sasiento +=","; //CREADO_POR NUMBER
            //sasiento +=","; //ULTIMA ACTUALIZACION POR
            aAsientos.setReferenciaAdicional1(asientosplit[28]); 
            aAsientos.setReferenciaAdicional2(asientosplit[29]); 
            aAsientos.setReferenciaAdicional3(asientosplit[30]); 
            aAsientos.setReferenciaAdicional4(asientosplit[31]); 
            aAsientos.setReferenciaAdicional5(asientosplit[32]); 
            aAsientos.setReferenciaAdicional6(asientosplit[33]); 
            aAsientos.setTipoBoleto(asientosplit[34]);
            aAsientos.setReferenciaAdicional8(asientosplit[35]); 
            aAsientos.setReferenciaAdicional9(asientosplit[36]); 
            aAsientos.setReferenciaAdicional10(asientosplit[37]);
            //sasiento +=","; //ADICIONAL11
            //sasiento +=","; //ADICIONAL12
            //sasiento +=","; //ADICIONAL13
            //sasiento +=","; //ADICIONAL14
            //sasiento +=","; //ADICIONAL15
            //sasiento +=","; //ADICIONAL16
            //sasiento +=","; //ADICIONAL17
            //sasiento +=","; //ADICIONAL18
            //sasiento +=","; //ADICIONAL19
            //sasiento +=","; //ADICIONAL20
            //sasiento +=","; //RUTA_ORIGEN
            aAsientos.setTipoVenta(asientosplit[49]);
            aAsientos.setClaveReservacion(asientosplit[50]);
            //REFERENCIA_ORIGINAL VARCHAR2
            //BOLETO_REFERENCIADO_ID NUMBER
          
            
            
        
        return aAsientos;
    }
     
    private String[] setFoliosBoletosArraytoStringArray(FoliosBoletos[] fBoletos) {
        String[] sArray = new String[fBoletos.length];
        
        
        for(int i = 0; i < fBoletos.length;i++){
            String sfoliosBoletos = "";
            sfoliosBoletos += fBoletos[i].getNoAsiento()+",";
            sfoliosBoletos += fBoletos[i].getSerieBoleto()+",";
            sfoliosBoletos += fBoletos[i].getFolioPreimpreso()+",";
            sfoliosBoletos += fBoletos[i].getNombrePasajero()+",";
            sfoliosBoletos += fBoletos[i].getOrigen()+",";
            sfoliosBoletos +=","; //ADICIONAL1 VARCHAR2(30)
            sfoliosBoletos +=","; //ADICIONAL2 VARCHAR2(30)
            sfoliosBoletos +=","; //ADICIONAL3 VARCHAR2(30)
            sfoliosBoletos +=","; //ADICIONAL4 VARCHAR2(30)
            sfoliosBoletos +=","; //ADICIONAL5 VARCHAR2(30)
            sfoliosBoletos +=","; //ADICIONAL6 VARCHAR2(30)
            sfoliosBoletos +=","; //ADICIONAL7 VARCHAR2(30)
            sfoliosBoletos +=","; //ADICIONAL8 VARCHAR2(30)
            sfoliosBoletos +=","; //ADICIONAL9 VARCHAR2(30) y //ADICIONAL10 VARCHAR2(30)
 //           sfoliosBoletos +=","; //ADICIONAL9 VARCHAR2(30) y //ADICIONAL10 VARCHAR2(30)
            sArray[i] = new String(sfoliosBoletos);
        }
        return sArray;
    }

    private Corrida[] setClobtoArrayCorrida(String clob)throws Exception{
        
        SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        /*
            CAPUI1100N990889,Terminal Las Torres Puebla,INTERMEDIO,26/06/2010,10:00,CAPU,TAPO,26/06/2010 12:00,CAPU,APAPA,
            0               ,1                         ,2         ,3         ,4    ,5   ,6   ,7               ,8   ,9    ,
            39,19.5,19.5,19.5,29.25,78,39,39,39,58.5,0 , 0, 0, 0, 0,47,47,47,47,47, 0,  , N, A, 1,47,26/06/2010,12:00,S
            10,11  ,12  ,13  ,14   ,15,16,17,18,19  ,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36        ,37   ,38
         *
        */
        System.out.println("LOCAL CLOB: "+clob);
        String arrCorridas[] = clob.split("\\|");
        int noCorridas = arrCorridas.length;
        System.out.println("noCorridas: "+noCorridas);
        
        Corrida resultCorridas[] = new Corrida[noCorridas];
        for (int i = 0; i < noCorridas; i++){
            System.out.println("arrCorridas["+i+"]: "+arrCorridas[i]);
            resultCorridas[i] = new Corrida();
            String singleCorrida[] = arrCorridas[i].split(",");
            if (singleCorrida.length != 41){
                throw new Exception("No se puede generar una corrida debido a nomero de argumentos incorrectos");
            }
            resultCorridas[i].setClaveCorrida(singleCorrida[0]);
            resultCorridas[i].setEmpresa(singleCorrida[1]);
            resultCorridas[i].setServicio(singleCorrida[2]);
            resultCorridas[i].setFechaCorrida(sdfFecha.parse(singleCorrida[3]));
            resultCorridas[i].setHoraCorrida(sdfHora.parse(singleCorrida[4]));
            resultCorridas[i].setOrigenCorrida(singleCorrida[5]);
            resultCorridas[i].setDestinoCorrida(singleCorrida[6]);
            resultCorridas[i].setFechaHoraSalida(sdfFechaHora.parse(singleCorrida[7]));
            resultCorridas[i].setOrigenSolicitado(singleCorrida[8]);
            resultCorridas[i].setDestinoSolicitado(singleCorrida[9]);
            resultCorridas[i].setTarifaSencillo(singleCorrida[10]+","+singleCorrida[11]+","+singleCorrida[12]+","+singleCorrida[13]+","+singleCorrida[14]);
            resultCorridas[i].setTarifaRedondo(singleCorrida[15]+","+singleCorrida[16]+","+singleCorrida[17]+","+singleCorrida[18]+","+singleCorrida[19]);
            resultCorridas[i].setAplicaDescuentoRedondo(singleCorrida[20]+","+singleCorrida[21]+","+singleCorrida[22]+","+singleCorrida[23]+","+singleCorrida[24]);
            resultCorridas[i].setTipoPaxPermitido(singleCorrida[25]+","+singleCorrida[26]+","+singleCorrida[27]+","+singleCorrida[28]+","+singleCorrida[29]);
            resultCorridas[i].setAutobus(singleCorrida[30]);
            resultCorridas[i].setOperador(singleCorrida[31]);
            resultCorridas[i].setTipoCorrida(singleCorrida[32]);
            resultCorridas[i].setEstadoCorrida(singleCorrida[33]);
            resultCorridas[i].setPlantillaId(Integer.parseInt(singleCorrida[34]));
            resultCorridas[i].setCapacidad(Integer.parseInt(singleCorrida[35]));
            resultCorridas[i].setFechaLlegada(sdfFecha.parse(singleCorrida[36]));
            resultCorridas[i].setHoraLLegada(sdfHora.parse(singleCorrida[37]));
            resultCorridas[i].setTipoVenta(singleCorrida[38]);
            resultCorridas[i].setPasajerosDisponibles(Integer.parseInt(singleCorrida[39]));
            resultCorridas[i].setINSENDisponibles(Integer.parseInt(singleCorrida[40]));

            System.out.println("fecha hora salida --->   "+sdfFechaHora.parse(singleCorrida[7]));
            
            
        }
        
        return resultCorridas;
    }

    private Corrida[] setClobtoArrayCorridaEbus(String clob, String porigen, String pdestino)throws Exception{

        SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        /*
            CAPUI1100N990889,Terminal Las Torres Puebla,INTERMEDIO,26/06/2010,10:00,CAPU,TAPO,26/06/2010 12:00,CAPU,APAPA,
            0               ,1                         ,2         ,3         ,4    ,5   ,6   ,7               ,8   ,9    ,
            39,19.5,19.5,19.5,29.25,78,39,39,39,58.5,0 , 0, 0, 0, 0,47,47,47,47,47, 0,  , N, A, 1,47,26/06/2010,12:00,S
            10,11  ,12  ,13  ,14   ,15,16,17,18,19  ,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36        ,37   ,38
         *
        */
        System.out.println("LOCAL CLOB: "+clob);
        String arrCorridas[] = clob.split("\\|");
        int noCorridas = arrCorridas.length;
        System.out.println("noCorridas: "+noCorridas);

        Corrida resultCorridas[] = new Corrida[noCorridas];
        for (int i = 0; i < noCorridas; i++){
            System.out.println("arrCorridas["+i+"]: "+arrCorridas[i]);
            resultCorridas[i] = new Corrida();
            String singleCorrida[] = arrCorridas[i].split(",");
            if (singleCorrida.length != 43){
                throw new Exception("No se puede generar una corrida debido a nomero de argumentos incorrectos");
            }
            resultCorridas[i].setClaveCorrida(singleCorrida[0]);
            resultCorridas[i].setEmpresa(singleCorrida[1]);
            resultCorridas[i].setServicio(singleCorrida[2]);
            resultCorridas[i].setFechaCorrida(sdfFecha.parse(singleCorrida[3]));
            resultCorridas[i].setHoraCorrida(sdfHora.parse(singleCorrida[4]));
            resultCorridas[i].setOrigenCorrida(singleCorrida[5]);
            resultCorridas[i].setDestinoCorrida(singleCorrida[6]);
            resultCorridas[i].setFechaHoraSalida(sdfFechaHora.parse(singleCorrida[7]));
            resultCorridas[i].setOrigenSolicitado(singleCorrida[8]);
            resultCorridas[i].setDestinoSolicitado(singleCorrida[9]);
            resultCorridas[i].setTarifaSencillo(singleCorrida[10]+","+singleCorrida[11]+","+singleCorrida[12]+","+singleCorrida[13]+","+singleCorrida[14]);
            resultCorridas[i].setTarifaRedondo(singleCorrida[15]+","+singleCorrida[16]+","+singleCorrida[17]+","+singleCorrida[18]+","+singleCorrida[19]);
            resultCorridas[i].setAplicaDescuentoRedondo(singleCorrida[20]+","+singleCorrida[21]+","+singleCorrida[22]+","+singleCorrida[23]+","+singleCorrida[24]);
            resultCorridas[i].setTipoPaxPermitido(singleCorrida[25]+","+singleCorrida[26]+","+singleCorrida[27]+","+singleCorrida[28]+","+singleCorrida[29]);
            resultCorridas[i].setAutobus(singleCorrida[30]);
            resultCorridas[i].setOperador(singleCorrida[31]);
            if(singleCorrida[8].equals(porigen) && singleCorrida[9].equals(pdestino))
                resultCorridas[i].setTipoCorrida("C");
            else
                resultCorridas[i].setTipoCorrida("E");
            //resultCorridas[i].setTipoCorrida(singleCorrida[32]);
            resultCorridas[i].setEstadoCorrida(singleCorrida[33]);
            resultCorridas[i].setPlantillaId(Integer.parseInt(singleCorrida[34]));
            resultCorridas[i].setCapacidad(Integer.parseInt(singleCorrida[35]));
            resultCorridas[i].setFechaLlegada(sdfFecha.parse(singleCorrida[36]));
            resultCorridas[i].setHoraLLegada(sdfHora.parse(singleCorrida[37]));
            resultCorridas[i].setTipoVenta(singleCorrida[38]);
            resultCorridas[i].setPasajerosDisponibles(Integer.parseInt(singleCorrida[39]));
            resultCorridas[i].setINSENDisponibles(Integer.parseInt(singleCorrida[40]));
            resultCorridas[i].setDescripcionOrigeSolicitado(singleCorrida[41]);
            resultCorridas[i].setDescripcionDestinoSolicitado(singleCorrida[42]);

            System.out.println("fecha hora salida --->   "+sdfFechaHora.parse(singleCorrida[7]));


        }
 
        return resultCorridas;
    }

    private STRUCT[] setFoliosBoletosArraytoStructArray(FoliosBoletos[] fBoletos,StructDescriptor descFolio_Boleto) throws Exception{
        
        
        STRUCT arrmovil[] = new STRUCT[fBoletos.length];
        for(int i = 0; i < fBoletos.length;i++){
            Object[] Folios_Boletos_Values = {(fBoletos[i].getNoAsiento()), 
            (fBoletos[i].getSerieBoleto()), 
            (fBoletos[i].getFolioPreimpreso()),
            (fBoletos[i].getNombrePasajero()), 
            (fBoletos[i].getOrigen()), 
            (""+fBoletos[i].getImporteCargo()==null?0:fBoletos[i].getImporteCargo()),
            (""+fBoletos[i].getImporteDevolucion()==null?0:fBoletos[i].getImporteDevolucion()),
            (""),
            (""),
            (""),
            (""),
            (""),
            (""),
            (""),
            ("")
            };  
            System.out.println("ImporteCargo: "+fBoletos[i].getImporteCargo());
            System.out.println("ImporteDevolucion: "+fBoletos[i].getImporteDevolucion());
            arrmovil[i] = new STRUCT(descFolio_Boleto, stmt.getConnection(), Folios_Boletos_Values);  
        }
        return arrmovil;
    }

    public getOrigenesDestinosResp getOrigenesDestinos(getOrigenesDestinosReqEr req) throws SQLException {
        System.out.println("Metodo Plimorfico de la clase");
        getOrigenesDestinosResp respuesta = new getOrigenesDestinosResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);
        cnx = null;
        stmt = null;
         Connection conn = null;
         ResultSet rset = null;
         Statement stmt = null;
         String res="";

        boolean bResultado = true;

            //Operaciones para insert
            try
            {
                String query = "SELECT XER_TMSWS_EBUS_PKG.GET_ORIGENES_DESTINOS_ER_FNC" +
                        "('"+req.getOrigen()+"' , '"+req.getEmpresa()+"' , " +
                        "'"+ req.getCanal()+"', '"+req.getUnidadNegocio()+"' , " +
                        "'"+req.getServicio()+"') FROM DUAL";
                System.out.println("query(consulta): "+query);
                //Vector list = (Vector)em.createNativeQuery(query).getResultList();
              conn = dataSource.getConnection();
              stmt = conn.createStatement();
                  rset = stmt.executeQuery(query);
                    while (rset.next())
                    {
                        res =  rset.getString(1);
                    }
                  System.out.println("Origenes(getOrigenesDestinos): "+res);

                if(res!=null && !res.equals(""))
                {
                    //Vector r = (Vector)list.get(0);
                    String[] registros = res.split("\\^"); //r.get(0).toString().split("\\^");
                    Origen[] origenes = new Origen[registros.length];
                    for(int i=1; i<registros.length; i++)
                    {
                        Origen org = new Origen();
                        String[] sp =registros[i].split("\\|");
                         System.out.println("registros["+i+"]: "+registros[i]);
                         System.out.println("sp["+0+"]: "+sp[0]);
                         System.out.println("sp["+1+"]: "+sp[1]);
                         String[] spor = sp[0].split("\\*");
                        org.setOrigen(spor[0]);
                        org.setDescripcion(spor[1]);
                        String[] spdes = sp[1].split(",");
                        Destino[] desnos =new Destino[spdes.length];
                        for(int y=0; y<spdes.length; y++)
                        {
                            String[] spd = spdes[y].split("\\*");
                            Destino dt = new Destino();
                            dt.setDestino(spd[0]);
                            dt.setDescripcion(spd[1]);
                            desnos[y]=dt;
                        }
                        org.setDestinos(desnos);
                        origenes[i]=org;
                    }
                    respuesta.setOrigenes(origenes);
                    respuesta.setErrorCode("000");
                    respuesta.setErrorMsg("La consulta se realizó satisfactoriamente");
                    respuesta.setOperacionExitosa(true);
                }
                else
                {
                    respuesta.setErrorCode("000");
                    respuesta.setErrorMsg("No se encontraron destinos para el origen solicitado");
                    respuesta.setOperacionExitosa(true);
                }

            }catch(EJBException e)
            {
                e.printStackTrace();
                respuesta.setErrorCode("ER-001");
                respuesta.setErrorMsg("LA consulta no se pudo realizar: "+e.getMessage());
                respuesta.setOperacionExitosa(false);
            }
        finally{
            if(stmt!=null) stmt.close();;
            if(cnx !=null) cnx.close();
            if(conn!=null) conn.close();
            }

        return respuesta;
    }
   
        
        
   
    private class boleto_movil_type{
        Object NO_ASIENTO;
        Object FOLIO_BOLETO;
  Object FOLIO_PREIMPRESO;
  Object NOMBRE_PASAJERO;
  Object CIUDAD_VENTA;
  Object ADICIONAL1;
  Object ADICIONAL2;
  Object ADICIONAL3;
  Object ADICIONAL4;
  Object ADICIONAL5;
  Object ADICIONAL6;
  Object ADICIONAL7;
  Object ADICIONAL8;
  Object ADICIONAL9;
  Object ADICIONAL10;
    }    


    public long getTipoPago(String ptipoPago)throws Exception{
        System.out.println("*** INI: EJB.getTipoPago() ***");
        long tipoPagoId = -1;
        cnx = null;

        stmt = null;

        boolean bResultado = true;

        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_TMSWS_EBUS_PKG.TMSWS_GETTIPOPAGO_PRC(?, ?, ?,  ?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);

            ((OraclePreparedStatement)stmt).setString(1, ptipoPago);
            stmt.registerOutParameter(2,OracleTypes.NUMBER);
            stmt.registerOutParameter(3,OracleTypes.NUMBER); //OperacionExitosa
            stmt.registerOutParameter(4,OracleTypes.VARCHAR); //ErrorCode
            stmt.registerOutParameter(5,OracleTypes.VARCHAR); //ErrrMsg


            bResultado=stmt.execute();
             if (stmt.getInt(3) == 0){ //Error
                System.out.println(stmt.getString(4));
                System.out.println(stmt.getString(5));
            }else{//Exito
                tipoPagoId = stmt.getLong(2);
            }


            System.out.println("Correcto");



        }catch (Exception ex) {
           System.out.println("INCORRECTO");

           throw ex;

        }
        finally{
        if(stmt!=null) stmt.close();
        if(cnx!=null) cnx.close();
        }
        System.out.println("*** FIN: EJB.getAsientosDisp() ***");
        return tipoPagoId;
    }

    public TiposPagoResp getTiposPago()throws Exception{
        System.out.println("*** INI: EJB.getTiposPago() ***");
        TiposPagoResp resp = new TiposPagoResp();
        cnx = null;

        stmt = null;

        boolean bResultado = true;

        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_TMSWS_EBUS_PKG.TMSWS_GET_TIPOS_PAGO_PRC(?, ?, ?,  ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);

            stmt.registerOutParameter(1,OracleTypes.VARCHAR);
            stmt.registerOutParameter(2,OracleTypes.NUMBER); //OperacionExitosa
            stmt.registerOutParameter(3,OracleTypes.VARCHAR); //ErrorCode
            stmt.registerOutParameter(4,OracleTypes.VARCHAR); //ErrrMsg


            bResultado=stmt.execute();
             if (stmt.getInt(2) == 0){ //Error
                System.out.println(stmt.getString(3));
                System.out.println(stmt.getString(4));
                resp.setOperacionExitosa(false);
                resp.setErrorCode(stmt.getString(3));
                resp.setErrorMsg(stmt.getString(4));
            }else{//Exito
                resp.setOperacionExitosa(true);
                resp.setErrorCode(stmt.getString(3));
                resp.setErrorMsg(stmt.getString(4));
                System.out.println(stmt.getString(1));
                String reg[] = stmt.getString(1).split("\\^");
                TipoPago[] pgs = new TipoPago[reg.length];

                for (int i =1; i<reg.length; i++)
                {
                    System.out.println(reg[i]);
                    String camp[] = reg[i].split("\\|");
                    TipoPago pag = new TipoPago();
                    pag.setTipoPagoId(Long.valueOf(camp[0]));
                    pag.setPagoNombre(camp[1]);
                    pag.setPagoClave(camp[2]);
                    pag.setPagoDescripcion(camp[2]);
                    pgs[i] = pag;
                }
                resp.setTiposPago(pgs);

            }


            System.out.println("Correcto");



        }catch (Exception ex) {
            ex.printStackTrace();
           System.out.println("INCORRECTO");

           throw ex;

        }
        finally{
        if(stmt!=null) stmt.close();
        if(cnx!=null) cnx.close();
        }
        System.out.println("*** FIN: EJB.getTiposPago() ***");
        return resp;
    }

    public TerminalesResp getTerminales()throws Exception{
        System.out.println("*** INI: EJB.getTerminales() ***");
        TerminalesResp resp = new TerminalesResp();
        cnx = null;

        stmt = null;

        boolean bResultado = true;

        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_TMSWS_EBUS_PKG.TMSWS_GET_TERMINALES_PRC(?, ?, ?,  ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);

            stmt.registerOutParameter(1,OracleTypes.VARCHAR);
            stmt.registerOutParameter(2,OracleTypes.NUMBER); //OperacionExitosa
            stmt.registerOutParameter(3,OracleTypes.VARCHAR); //ErrorCode
            stmt.registerOutParameter(4,OracleTypes.VARCHAR); //ErrrMsg


            bResultado=stmt.execute();
             if (stmt.getInt(2) == 0){ //Error
                System.out.println(stmt.getString(3));
                System.out.println(stmt.getString(4));
                resp.setOperacionExitosa(false);
                resp.setErrorCode(stmt.getString(3));
                resp.setErrorMsg(stmt.getString(4));
            }else{//Exito
                resp.setOperacionExitosa(true);
                resp.setErrorCode(stmt.getString(3));
                resp.setErrorMsg(stmt.getString(4));
                String reg[] = stmt.getString(1).split("\\^");
                Terminal[] ters = new Terminal[reg.length];
                for (int i =1; i<reg.length; i++)
                {
                    String camp[] = reg[i].split("\\|");
                    Terminal ter = new Terminal();
                    ter.setTerminalId(Long.valueOf(camp[0]));
                    ter.setTerminalNombre(camp[1]);
                    ter.setTerminalDescripcion(camp[2]);
                    ters[i] = ter;
                }
                resp.setTerminales(ters);

            }


            System.out.println("Correcto");



        }catch (Exception ex) {
           System.out.println("INCORRECTO");

           throw ex;

        }
        finally{
        if(stmt!=null) stmt.close();
        if(cnx!=null) cnx.close();
        }
        System.out.println("*** FIN: EJB.getTerminales() ***");
        return resp;
    }

    public ServiciosVtaResp getServciosVta()throws Exception{
        System.out.println("*** INI: EJB.getServciosVta() ***");
        ServiciosVtaResp resp = new ServiciosVtaResp();
        cnx = null;

        stmt = null;

        boolean bResultado = true;

        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_TMSWS_EBUS_PKG.TMSWS_GET_SERVICIOS_PRC(?, ?, ?,  ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);

            stmt.registerOutParameter(1,OracleTypes.VARCHAR);
            stmt.registerOutParameter(2,OracleTypes.NUMBER); //OperacionExitosa
            stmt.registerOutParameter(3,OracleTypes.VARCHAR); //ErrorCode
            stmt.registerOutParameter(4,OracleTypes.VARCHAR); //ErrrMsg


            bResultado=stmt.execute();
             if (stmt.getInt(2) == 0){ //Error
                System.out.println(stmt.getString(3));
                System.out.println(stmt.getString(4));
                resp.setOperacionExitosa(false);
                resp.setErrorCode(stmt.getString(3));
                resp.setErrorMsg(stmt.getString(4));
            }else{//Exito
                resp.setOperacionExitosa(true);
                resp.setErrorCode(stmt.getString(3));
                resp.setErrorMsg(stmt.getString(4));
                String reg[] = stmt.getString(1).split("\\^");
                ServicioVta[] servss = new ServicioVta[reg.length];
                for (int i =1; i<reg.length; i++)
                {
                    String camp[] = reg[i].split("\\|");
                    ServicioVta ser = new ServicioVta();
                    ser.setServicioId(Long.valueOf(camp[0]));
                    ser.setServicioNombre(camp[1]);
                    ser.setServicioDescripcion(camp[2]);
                    servss[i] = ser;
                }
                resp.setServicios(servss);

            }


            System.out.println("Correcto");



        }catch (Exception ex) {
           System.out.println("INCORRECTO");

           throw ex;

        }
        finally{
        if(stmt!=null) stmt.close();
        if(cnx!=null) cnx.close();
        }
        System.out.println("*** FIN: EJB.getServciosVta() ***");
        return resp;
    }
    
    public EmpresasVtaResp getEmpresas()throws Exception{
        System.out.println("*** INI: EJB.getEmpresas() ***");
        EmpresasVtaResp resp = new EmpresasVtaResp();
        cnx = null;

        stmt = null;

        boolean bResultado = true;

        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_TMSWS_EBUS_PKG.TMSWS_GET_EMPRESAS_PRC(?, ?, ?,  ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);

            stmt.registerOutParameter(1,OracleTypes.VARCHAR);
            stmt.registerOutParameter(2,OracleTypes.NUMBER); //OperacionExitosa
            stmt.registerOutParameter(3,OracleTypes.VARCHAR); //ErrorCode
            stmt.registerOutParameter(4,OracleTypes.VARCHAR); //ErrrMsg


            bResultado=stmt.execute();
             if (stmt.getInt(2) == 0){ //Error
                System.out.println(stmt.getString(3));
                System.out.println(stmt.getString(4));
                resp.setOperacionExitosa(false);
                resp.setErrorCode(stmt.getString(3));
                resp.setErrorMsg(stmt.getString(4));
            }else{//Exito
                resp.setOperacionExitosa(true);
                resp.setErrorCode(stmt.getString(3));
                resp.setErrorMsg(stmt.getString(4));
                String reg[] = stmt.getString(1).split("\\^");
                Empresa[] emps = new Empresa[reg.length];
                for (int i =1; i<reg.length; i++)
                {
                    String camp[] = reg[i].split("\\|");
                    Empresa emp = new Empresa();
                    emp.setEmpresaId(Long.valueOf(camp[0]));
                    emp.setEmpresaNombre(camp[1]);
                    emp.setEmpresaDescripcion(camp[2]);
                    emps[i] = emp;
                }
                resp.setEmpresas(emps);

            }


            System.out.println("Correcto");



        }catch (Exception ex) {
           System.out.println("INCORRECTO");

           throw ex;

        }
        finally{
        if(stmt!=null) stmt.close();
        if(cnx!=null) cnx.close();
        }
        System.out.println("*** FIN: EJB.getEmpresas() ***");
        return resp;
    }

        public getAsociarComprasClienteResp getAsociarComprasCliente(getAsociarComprasClienteReq request){
        System.out.println("*** INI: EJB.getAsociarComprasCliente() ***");
        getAsociarComprasClienteResp resp = new getAsociarComprasClienteResp();
        System.out.println("*** DESPUES: EJB.getAsociarComprasCliente() ***");
        cnx = null;

        stmt = null;

        boolean bResultado = true;
        resp.setOperacionExitosa(true);
        resp.setErrorCode("ER-000");
        resp.setErrorMsg("La operacion se realizó exitosamente");
        for (int i=0; i<request.getFoliosBoletos().length;i++)
        {
            String porigen = request.getFoliosBoletos()[i].getOrigen();
            String pfolio = request.getFoliosBoletos()[i].getFolioPreimpreso();
            long pnoasiento = request.getFoliosBoletos()[i].getNoAsiento().longValue();
            System.out.println("Datos("+i+"): ");
            System.out.println("ClienteId: "+request.getClienteId());
            System.out.println("origen: "+porigen);
            System.out.println("folio: "+pfolio);
            System.out.println("noasiento: "+pnoasiento);
                try {

                    cnx = dataSource.getConnection();
                    //ValidaBoleto
                    //AsociaProducto-Cliente

                    String q1 =
                            "BEGIN "+
                              "XER_TMSWS_EBUS_PKG.TMSWS_GETASOCIARBOLETO_PRC(?, ?, ?, ?, ?, ?, ?); " +
                              "COMMIT; " +
                              "EXCEPTION " +
                              "WHEN OTHERS THEN " +
                              "ROLLBACK; " +
                              "RAISE; "+
                            "END;";
                    stmt = (OracleCallableStatement) cnx.prepareCall(q1);

                    ((OraclePreparedStatement)stmt).setLong(1, request.getClienteId());
                    ((OraclePreparedStatement)stmt).setString(2, porigen);
                    ((OraclePreparedStatement)stmt).setString(3, pfolio);
                    ((OraclePreparedStatement)stmt).setLong(4, pnoasiento);

                    stmt.registerOutParameter(5,OracleTypes.NUMBER); //OperacionExitosa
                    stmt.registerOutParameter(6,OracleTypes.VARCHAR); //ErrorCode
                    stmt.registerOutParameter(7,OracleTypes.VARCHAR); //ErrrMsg


                    bResultado=stmt.execute();
                     if (stmt.getInt(5) == 0){ //Error
                        System.out.println(stmt.getString(6));
                        System.out.println(stmt.getString(7));
                        resp.setOperacionExitosa(false);
                        resp.setErrorCode(stmt.getString(6));
                        resp.setErrorMsg(stmt.getString(7));
                        resp.setFolioBoletoError(pfolio);
                        return resp;
                    }
                    System.out.println("Correcto");
                    if(stmt!=null) stmt.close();
                    if(cnx!=null) cnx.close();
                }catch (Exception ex) {
                    ex.printStackTrace();
                    resp.setOperacionExitosa(false);
                    resp.setErrorCode("ER-111");
                    resp.setErrorMsg("La operacion no pudo ser completada");
                    try {
                        System.out.println("INCORRECTO");
                        if(stmt != null)  stmt.close();
                        if (cnx != null) {
                            cnx.close();
                        }
                    } catch (SQLException ex1) {
                        Logger.getLogger(ejbERCONTROLTMSWSFacadeBean.class.getName()).log(Level.SEVERE, null, ex1);
                    }

                }
            }
        System.out.println("*** FIN: EJB.getAsociarComprasCliente() ***");
        return resp;
    }
 
    public long getNumeroTransaccion(){

     Connection conn = null;
     ResultSet rset = null;
     Statement stmt = null;
     long numt = 0 ;

        String qry=" select XER_TMSWS_EBUS_PKG.GET_NUMTRANSACCION_EBUS_FNC  from dual ";
        cnx = null;

        stmt = null;

        boolean bResultado = true;

        try {
            conn = dataSource.getConnection();

              //stmt = conn.createStatement();
              //rset = stmt.executeQuery(qry);

              stmt = conn.createStatement();
              rset = stmt.executeQuery(qry);
                while (rset.next())
                {
                    numt =  rset.getLong(1);
                }
              System.out.println("NumeroTransaccion(GET_NUMTRANSACCION_EBUS_FNC): "+numt);

        } catch (Exception e) {
            e.printStackTrace();
            numt=0;
        }
        finally{
        try {
            if(stmt!=null) stmt.close();
            if(cnx !=null) cnx.close();
            if(conn!=null) conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ejbERCONTROLTMSWSFacadeBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    return numt;
}

    public getOrigenesDestinosResp getOrigenesDestinos(getOrigenesDestinosReq req) throws SQLException
    {
            System.out.println("Entre a polimorfismo ");
        getOrigenesDestinosResp respuesta = new getOrigenesDestinosResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada ***************");
        respuesta.setOperacionExitosa(false);
        cnx = null;
        stmt = null;
         Connection conn = null;
         ResultSet rset = null;
         Statement stmt = null;
         String res="";

        boolean bResultado = true;

            //Operaciones para insert
            try
            {
                String query = "SELECT XER_TMSWS_EBUS_PKG.GET_ORIGENES_DESTINOS_EBUS_FNC('"+req.getOrigen()+"') FROM DUAL";
                System.out.println("query(consulta): "+query);
                //Vector list = (Vector)em.createNativeQuery(query).getResultList();
              conn = dataSource.getConnection();
              stmt = conn.createStatement();
                  rset = stmt.executeQuery(query);
                    while (rset.next())
                    {
                        res =  rset.getString(1);
                    }
                  System.out.println("Origenes(getOrigenesDestinos)*********************: "+res);

                if(res!=null && !res.equals(""))
                {
                    //Vector r = (Vector)list.get(0);
                    String[] registros = res.split("\\^"); //r.get(0).toString().split("\\^");
                    Origen[] origenes = new Origen[registros.length];
                    for(int i=1; i<registros.length; i++)
                    {
                        Origen org = new Origen();
                        String[] sp =registros[i].split("\\|");
                         System.out.println("registros["+i+"]: "+registros[i]);
                         System.out.println("sp["+0+"]: "+sp[0]);
                         System.out.println("sp["+1+"]: "+sp[1]);
                         String[] spor = sp[0].split("\\*");
                        org.setOrigen(spor[0]);
                        org.setDescripcion(spor[1]);
                        String[] spdes = sp[1].split(",");
                        Destino[] desnos =new Destino[spdes.length];
                        for(int y=0; y<spdes.length; y++)
                        {
                            String[] spd = spdes[y].split("\\*");
                            Destino dt = new Destino();
                            dt.setDestino(spd[0]);
                            dt.setDescripcion(spd[1]);
                            desnos[y]=dt;
                        }
                        org.setDestinos(desnos);
                        origenes[i]=org;
                    } 
                    respuesta.setOrigenes(origenes);
                    respuesta.setErrorCode("000");
                    respuesta.setErrorMsg("La consulta se realizó satisfactoriamente");
                    respuesta.setOperacionExitosa(true);
                }
                else
                {
                    respuesta.setErrorCode("000");
                    respuesta.setErrorMsg("No se encontraron destinos para el origen solicitado");
                    respuesta.setOperacionExitosa(true);
                }

            }catch(EJBException e)
            {
                e.printStackTrace();
                respuesta.setErrorCode("ER-001");
                respuesta.setErrorMsg("LA consulta no se pudo realizar: "+e.getMessage());
                respuesta.setOperacionExitosa(false);
            }
        finally{
            if(stmt!=null) stmt.close();;
            if(cnx !=null) cnx.close();
            if(conn!=null) conn.close();
            }

        return respuesta;
    }




}
