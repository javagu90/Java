/*
 * TmsTxVtaFacadeBean.java
 *
 * Created on 26 de noviembre de 2008, 06:48 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TMSProyectoWeb.solicitud;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.driver.OraclePreparedStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

/**
 *
 * @author ocruz
 */
@Stateless
public class TmsTxVtaFacadeBean implements TMSProyectoWeb.solicitud.TmsTxVtaFacadeRemote {
    
    @PersistenceContext
    private EntityManager em;
    @Resource(name = "TMS_DB")
    private DataSource dataSource;
    public TmsTxVtaFacadeBean(){ }
    private boolean debug = true;


    public String getDBLinks(){
        String Consulta =
                "SELECT " +
                "Xer_Tms_Ws_Pkg.Tms_DBLinks_Fnc VALOR "+
                "FROM DUAL";
        try{

            //System.out.println(Consulta);
            Vector yvector = new Vector();
            yvector = (Vector) em.createNativeQuery(Consulta).getResultList();
            /*java.util.List list = em.createNativeQuery(Consulta).getResultList();
            for (int i=0;i<list.size();i++)
                yvector.add(list.get(i));
             * 
             */
            System.out.println("Resultado "+yvector);
            if(yvector==null || yvector.size()==0) return null;
            String resultado = yvector.get(0).toString();
            //System.out.println("Resultado "+resultado);
            resultado = resultado.replace("[","");
            resultado = resultado.replace("]","");
            if(resultado.equals("null")) return null;
            return resultado;
        }catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }
    
    public String IniciarSesion(String usuario, String contrasenia, String idEstacionTrabajo) throws javax.ejb.EJBException{
        String P_RESPUESTA=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        System.out.println("---------------- Parametros IniciarSesion ----------------\n" +
                "usuario "+usuario+" contrasenia "+contrasenia+" idEstacionTrabajo "+idEstacionTrabajo);        
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "Xer_Tms_Ws_Pkg.Tms_IniciarSesion_Prc(?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, usuario);
            ((OraclePreparedStatement)stmt).setString(2, contrasenia);
            ((OraclePreparedStatement)stmt).setString(3, idEstacionTrabajo);
            stmt.registerOutParameter(4,java.sql.Types.VARCHAR);
            
            bResultado=stmt.execute();
            P_RESPUESTA = stmt.getString(4);
            stmt.close();
            System.out.println("---------------- Respuesta del SP Tms_IniciarSesion_Prc "+P_RESPUESTA+" ---------------- ");
            
            if(cnx!=null) cnx.close();
            
            return P_RESPUESTA;
        } catch (SQLException ex) {
            try {
                P_RESPUESTA = stmt.getString(4);
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                P_RESPUESTA=null;
            } finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("---------------- Excepcion SP Tms_IniciarSesion_Prc "+P_RESPUESTA+" ---------------- ");
                return P_RESPUESTA;
            }
        }
    }
    
    public String TerminarSesion(String idSesion) throws javax.ejb.EJBException{
        String P_RESPUESTA=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        System.out.println("---------------- Parametros TerminarSesion ----------------\n" +
                " idSesion "+idSesion);
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "Xer_Tms_Ws_Pkg.Tms_TerminarSesion_Prc(?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, idSesion);
            stmt.registerOutParameter(2,java.sql.Types.VARCHAR);
            
            bResultado=stmt.execute();
            P_RESPUESTA = stmt.getString(2);
            stmt.close();
            System.out.println("---------------- Respuesta del SP Tms_TerminarSesion_Prc "+P_RESPUESTA+" ---------------- ");
            //System.out.println("Bean TerminarSesion - "+P_RESPUESTA);
            
            if(cnx!=null) cnx.close();
            
            return P_RESPUESTA;
        } catch (SQLException ex) {
            try {
                P_RESPUESTA = stmt.getString(2);
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                P_RESPUESTA=null;
            } finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("---------------- Excepcion Bean Tms_TerminarSesion_Prc "+P_RESPUESTA+" ----------------");
                return P_RESPUESTA;
            }
        }
    }
    
    public String buscaNombreNegocio(String id){
        System.out.println("---------------- Parametros buscaNombreNegocio ----------------\n" +
                "id "+id);
        return em.createNativeQuery("select e.EMPRESA_NOMBRE from tms_empresas_tbl e where e.EMPRESA_ID = "+id).getSingleResult().toString();
    }
    
    public String obtenerCorridasVenta(String DBLink, String numSocio, String corteId, String strOrigen,
            String strFecha1, String strFecha2, String strFecha3, String strFecha4,
            String strServicio, String strDestino, String strEmpresa,
            int viajeRedondo, int AsientosAdulto, int AsientosNinio,
            int AsientosEstudiante, int AsientosMaestro, int AsientosINSEN) throws javax.ejb.EJBException{
        System.out.println("LLega a obtener corridas venta");
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String P_RESPUESTA=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        System.out.println("---------------- Parametros obtenerCorridasVenta ----------------\n" +
                           " DBLink "+DBLink+" numSocio "+numSocio+" corteId "+corteId+" Origen "+strOrigen
                           +" Fecha1 "+strFecha1+" Fecha2 "+strFecha2+" Fecha3 "+strFecha3+" Fecha4 "+strFecha4
                           +" Servicio "+strServicio+" Destino "+strDestino+" Empresa "+strEmpresa
                           +" viajeRedondo "+viajeRedondo+" AsientosAdulto "+ AsientosAdulto+" AsientosNinio "+AsientosNinio
                           +" AsientosEstudiante "+AsientosEstudiante+" AsientosMaestro "+AsientosMaestro+" AsientosINSEN "+AsientosINSEN);
        try {
            cnx = dataSource.getConnection();
            /*  Modif 220611 IMM
             String q1 =
                    "BEGIN "+
                    "Xer_Tms_Ws_Pkg.Tms_GetCorridas_Prc"+DBLink+"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            System.out.println("qry: "+q1);*/
            String q1 =
                    "BEGIN "+
                    "Xer_Tms_Ws_Pkg.Tms_GetCorridas_Prc(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            System.out.println("qry: "+q1);
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, DBLink);
            ((OraclePreparedStatement)stmt).setString(2, numSocio);
            ((OraclePreparedStatement)stmt).setString(3, corteId);
            ((OraclePreparedStatement)stmt).setString(4, strOrigen);
            ((OraclePreparedStatement)stmt).setString(5, strFecha1);
            ((OraclePreparedStatement)stmt).setString(6, strFecha2);
            ((OraclePreparedStatement)stmt).setString(7, strFecha3);
            ((OraclePreparedStatement)stmt).setString(8, strFecha4);
            ((OraclePreparedStatement)stmt).setString(9, strServicio);
            ((OraclePreparedStatement)stmt).setString(10, strDestino);
            ((OraclePreparedStatement)stmt).setString(11, strEmpresa);
            ((OraclePreparedStatement)stmt).setInt(12, viajeRedondo);
            ((OraclePreparedStatement)stmt).setInt(13, AsientosAdulto);
            ((OraclePreparedStatement)stmt).setInt(14, AsientosNinio);
            ((OraclePreparedStatement)stmt).setInt(15, AsientosEstudiante);
            ((OraclePreparedStatement)stmt).setInt(16, AsientosMaestro);
            ((OraclePreparedStatement)stmt).setInt(17, AsientosINSEN);
            stmt.registerOutParameter(18,java.sql.Types.CLOB);
            
            bResultado=stmt.execute();
            P_RESPUESTA = stmt.getString(18);
            stmt.close();
            
            if(cnx!=null) cnx.close();
            System.out.println("---------------- Respuesta del SP Tms_GetCorridas_Prc "+P_RESPUESTA+" ---------------- ");
            //System.out.println("Bean GetCorridas - "+P_RESPUESTA);
            
            return P_RESPUESTA;
           
           
        } catch (SQLException ex) {
            try {
                P_RESPUESTA = stmt.getString(18);
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                P_RESPUESTA=null;
            } finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("---------------- Excepcion SP Tms_GetCorridas_Prc "+P_RESPUESTA+" ----------------");
                //System.out.println("Excepcion Bean GetCorridas - "+P_RESPUESTA);
                return P_RESPUESTA;
            }
        }
    }


    public String obtenerCorridasVentaCDI(String DBLink, String numSocio, String corteId, String strOrigen,
            String strFecha1, String strFecha2, String strFecha3, String strFecha4,
            String strServicio, String strDestino, String strEmpresa,
            int viajeRedondo, int AsientosAdulto, int AsientosNinio,
            int AsientosEstudiante, int AsientosMaestro, int AsientosINSEN) throws javax.ejb.EJBException{
        System.out.println("LLega a obtener corridas venta CDI");
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String P_RESPUESTA=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        System.out.println("---------------- Parametros obtenerCorridasVenta CDI ----------------\n" +
                           " DBLink "+DBLink+" numSocio "+numSocio+" corteId "+corteId+" Origen "+strOrigen
                           +" Fecha1 "+strFecha1+" Fecha2 "+strFecha2+" Fecha3 "+strFecha3+" Fecha4 "+strFecha4
                           +" Servicio "+strServicio+" Destino "+strDestino+" Empresa "+strEmpresa
                           +" viajeRedondo "+viajeRedondo+" AsientosAdulto "+ AsientosAdulto+" AsientosNinio "+AsientosNinio
                           +" AsientosEstudiante "+AsientosEstudiante+" AsientosMaestro "+AsientosMaestro+" AsientosINSEN "+AsientosINSEN);
        try {
            cnx = dataSource.getConnection();
            /*  Modif 220611 IMM
             String q1 =
                    "BEGIN "+
                    "Xer_Tms_Ws_Pkg.Tms_GetCorridas_Prc"+DBLink+"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            System.out.println("qry: "+q1);*/
            String q1 =
                    "BEGIN "+
                    "Xer_Tms_Ws_Pkg.Tms_GetCorridas_Prc(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            System.out.println("qryCDI: "+q1);
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, DBLink);
            ((OraclePreparedStatement)stmt).setString(2, numSocio);
            ((OraclePreparedStatement)stmt).setString(3, corteId);
            ((OraclePreparedStatement)stmt).setString(4, strOrigen);
            ((OraclePreparedStatement)stmt).setString(5, strFecha1);
            ((OraclePreparedStatement)stmt).setString(6, strFecha2);
            ((OraclePreparedStatement)stmt).setString(7, strFecha3);
            ((OraclePreparedStatement)stmt).setString(8, strFecha4);
            ((OraclePreparedStatement)stmt).setString(9, strServicio);
            ((OraclePreparedStatement)stmt).setString(10, strDestino);
            ((OraclePreparedStatement)stmt).setString(11, "Estrella Roja");//strEmpresa);
            ((OraclePreparedStatement)stmt).setInt(12, viajeRedondo);
            ((OraclePreparedStatement)stmt).setInt(13, AsientosAdulto);
            ((OraclePreparedStatement)stmt).setInt(14, AsientosNinio);
            ((OraclePreparedStatement)stmt).setInt(15, AsientosEstudiante);
            ((OraclePreparedStatement)stmt).setInt(16, AsientosMaestro);
            ((OraclePreparedStatement)stmt).setInt(17, AsientosINSEN);
            stmt.registerOutParameter(18,java.sql.Types.CLOB);

            bResultado=stmt.execute();
            P_RESPUESTA = stmt.getString(18);
            stmt.close();

            if(cnx!=null) cnx.close();
            System.out.println("---------------- Respuesta del SP Tms_GetCorridas_Prc CDI"+P_RESPUESTA+" ---------------- ");
            //System.out.println("Bean GetCorridas - "+P_RESPUESTA);

            return P_RESPUESTA;


        } catch (SQLException ex) {
            try {
                P_RESPUESTA = stmt.getString(18);
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                P_RESPUESTA=null;
            } finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("---------------- Excepcion SP Tms_GetCorridas_Prc "+P_RESPUESTA+" ----------------");
                //System.out.println("Excepcion Bean GetCorridas - "+P_RESPUESTA);
                return P_RESPUESTA;
            }
        }
    }

    public String getItinerarioCorrida(String DBLink, String numCorrida, String Origen, String strDestino) throws javax.ejb.EJBException{        
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        System.out.println("---------------- Parametros getItinerarioCorrida ----------------\n" +
                           "DBLink "+DBLink+" numCorrida "+numCorrida+" Origen "+Origen+" Destino " +strDestino);
        String Consulta =
                "SELECT " +
                "Xer_Tms_Ws_Pkg.Tms_GetItinerarioCorrida_Fnc"+DBLink+"('"+numCorrida+"','"+Origen+"','"+strDestino+"') VALOR "+
                "FROM DUAL";
        try{
            System.out.println("qry "+Consulta);
            Vector yvector = new Vector();
            yvector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(yvector==null || yvector.size()==0) return null;
            String resultado = yvector.get(0).toString();
            resultado = resultado.replace("[","");
            resultado = resultado.replace("]","");
            if(resultado.equals("null")) return null;
            return resultado;
        }catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }
    
    public String getAsientosCorrida(String DBLink, String numCorrida, String Origen,
            int asientosAdultos, int asientosNinio,
            int asientosEstudiante, int asientosMaestro,
            int asientosINSEN, int cupoAutobus) throws javax.ejb.EJBException{        
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        System.out.println("---------------- Parametros getAsientosCorrida ----------------\n" +
                           " DBLink "+DBLink+" numCorrida "+numCorrida+" Origen "+Origen+
                           " asientosAdultos "+asientosAdultos+" asientosNinio "+asientosNinio+
                           " asientosEstudiante "+asientosEstudiante+ " asientosMaestro "+asientosMaestro+
                           " asientosINSEN "+asientosINSEN+" cupoAutobus "+cupoAutobus);
        /*  Modif 220611 IMM
        String Consulta =
                "SELECT " +
                "Xer_Tms_Ws_Pkg.Tms_MapaAsientosCorrida_Fnc"+DBLink+"('"+numCorrida+"','"+Origen+
                "','"+asientosAdultos+"','"+asientosNinio+"','"+asientosEstudiante+
                "','"+asientosMaestro+"','"+asientosINSEN+"','"+cupoAutobus+"') VALOR "+
                "FROM DUAL";*/
        String Consulta =
                "SELECT " +
                "Xer_Tms_Ws_Pkg.Tms_MapaAsientosCorrida_Fnc('"+DBLink+"','"+numCorrida+"','"+Origen+
                "','"+asientosAdultos+"','"+asientosNinio+"','"+asientosEstudiante+
                "','"+asientosMaestro+"','"+asientosINSEN+"','"+cupoAutobus+"') VALOR "+
                "FROM DUAL";
        String mapaAsientos = "";
        try{
            System.out.println("qry "+Consulta);
            Vector yvector = new Vector();
            yvector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(yvector==null || yvector.size()==0) return null;
            mapaAsientos = yvector.get(0).toString();
            mapaAsientos = mapaAsientos.replace("[","");
            mapaAsientos = mapaAsientos.replace("]","");
            if(mapaAsientos.equals("null")) return null;
            System.out.println("---------------- Respuesta del SP Tms_MapaAsientosCorrida_Fnc "+mapaAsientos+" ---------------- ");
            return mapaAsientos;
        }catch(Exception nrex){
            nrex.printStackTrace();
            System.out.println("---------------- Excepcion SP Tms_MapaAsientosCorrida_Fnc "+mapaAsientos+" ----------------");
            return null;
        }
    }
    
    /*public String obtenerUnaCorridaVenta(String pDBSchema, String pDBLink, String corridaId) throws javax.ejb.EJBException{
        if(!pDBSchema.equals("")) pDBSchema = pDBSchema + ".";
        if(!pDBLink.equals("")) pDBLink = "@" + pDBLink;
        String Consulta =
                "SELECT " +
                "Xer_Tms_Ws_Pkg.GETUNACORRIDA"+pDBLink+"('','"+corridaId+"') VALOR "+
                "FROM DUAL";
        try{
            //System.out.println(Consulta);
            Vector yvector = new Vector();
            yvector = (Vector) em.createNativeQuery(Consulta).getResultList();
            if(yvector==null || yvector.size()==0) return null;
            String resultado = yvector.get(0).toString();
            resultado = resultado.replace("[","");
            resultado = resultado.replace("]","");
            if(resultado.equals("null")) return null;
            return resultado;
        }catch(Exception nrex){
            nrex.printStackTrace();
            return null;
        }
    }*/
    
    public String[] ReservarAsientos(String DBLink, String numUsuario, String idEstacion, String idCliente, String banCompra,
            String numCorridaI, String OrigenI, String DestinoI,String asientosI, String tiposPasajeroI, String nombrePasajeroI, String TarifasI,
            String numCorridaR, String OrigenR, String DestinoR, String asientosR, String tiposPasajeroR, String nombrePasajeroR, String TarifasR
            ) throws javax.ejb.EJBException{       
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String[] P_RESPUESTA=new String[2];
        String P_RESPUESTA1=null;
        String P_RESPUESTA2=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        System.out.println("---------------- Parametros ReservarAsientos ----------------\n" +
                           "DBLink "+DBLink + " numUsuario "+numUsuario+ " idEstacion " + idEstacion+ " idCliente " +idCliente+"\n");
                System.out.println("............... Parametros ReservarAsientos  Ida ...............\n"+
                                   " numCorridaI "+numCorridaI+" OrigenI "+OrigenI+" DestinoI "+DestinoI+" asientosI "+asientosI+
                                   " tiposPasajeroI "+tiposPasajeroI+" nombrePasajeroI "+nombrePasajeroI+" TarifasI "+TarifasI);
                System.out.println("............... Parametros ReservarAsientos  Regreso ...............\n"+
                                   "numCorridaR "+numCorridaR+" OrigenR "+OrigenR+" DestinoR "+DestinoR+" asientosR "+asientosR+
                                   " tiposPasajeroR "+tiposPasajeroR+" nombrePasajeroR "+nombrePasajeroR+" TarifasR "+TarifasR);                            
        try {           
            
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "Xer_Tms_Ws_Pkg.Tms_Reservar_Asientos_Prc"+DBLink+"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            //((OraclePreparedStatement)stmt).setString(1, DBLink);
            ((OraclePreparedStatement)stmt).setString(1, numUsuario);
            ((OraclePreparedStatement)stmt).setString(2, idEstacion);
            ((OraclePreparedStatement)stmt).setString(3, idCliente);
            ((OraclePreparedStatement)stmt).setString(4, banCompra);
            ((OraclePreparedStatement)stmt).setString(5, numCorridaI);
            ((OraclePreparedStatement)stmt).setString(6, OrigenI);
            ((OraclePreparedStatement)stmt).setString(7, DestinoI);
            ((OraclePreparedStatement)stmt).setString(8, asientosI);
            ((OraclePreparedStatement)stmt).setString(9, tiposPasajeroI);
            ((OraclePreparedStatement)stmt).setString(10, nombrePasajeroI);
            ((OraclePreparedStatement)stmt).setString(11, TarifasI);
            ((OraclePreparedStatement)stmt).setString(12, numCorridaR);
            ((OraclePreparedStatement)stmt).setString(13, OrigenR);
            ((OraclePreparedStatement)stmt).setString(14, DestinoR);
            ((OraclePreparedStatement)stmt).setString(15, asientosR);
            ((OraclePreparedStatement)stmt).setString(16, tiposPasajeroR);
            ((OraclePreparedStatement)stmt).setString(17, nombrePasajeroR);
            ((OraclePreparedStatement)stmt).setString(18, TarifasR);
            stmt.registerOutParameter(19,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(20,java.sql.Types.VARCHAR);
            bResultado=stmt.execute();
            P_RESPUESTA1 = stmt.getString(19);
            P_RESPUESTA2 = stmt.getString(20);
            P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
            stmt.close();
            System.out.println("---------------- Respuesta del SP Tms_Reservar_Asientos_Prc Ida "+P_RESPUESTA1+" ---------------- ");
            System.out.println("---------------- Respuesta del SP Tms_Reservar_Asientos_Prc Regreso "+P_RESPUESTA2+" ---------------- ");
            /*System.out.println("Bean ReservarAsientos - "+P_RESPUESTA1);
            System.out.println("Bean ReservarAsientos - "+P_RESPUESTA2);*/
            
            if(cnx!=null) cnx.close();
            
            return P_RESPUESTA;
        } catch (SQLException ex) {
            try {
                P_RESPUESTA1 = stmt.getString(19);
                P_RESPUESTA2 = stmt.getString(20);
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                P_RESPUESTA1=null;
                P_RESPUESTA2=null;
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
            } finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("---------------- Excepcion SP Tms_Reservar_Asientos_Prc Ida "+P_RESPUESTA1+" ----------------");
                System.out.println("---------------- Excepcion SP Tms_Reservar_Asientos_Prc Regreso "+P_RESPUESTA2+" ----------------");
                /*System.out.println("Excepcion Bean ReservarAsientos - "+P_RESPUESTA1);
                System.out.println("Excepcion Bean ReservarAsientos - "+P_RESPUESTA2);*/
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
                return P_RESPUESTA;
            }
        }
    }
    
    public String[] ReservacionControl(String Origen, String destino, String idEstacion , String banCompra, String claveReservacion) throws javax.ejb.EJBException{
        String[] P_RESPUESTA=new String[2];
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        try {
            
            System.out.println("---------------- Parametros ReservacionControl ----------------\n" +
                               "Origen "+Origen +" Destino "+destino + " idEstacion" + idEstacion+ " banCompra "+banCompra + " claveReservacion "+claveReservacion);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "Xer_Tms_Ws_Pkg.Tms_Act_Res_Ctrl_Prc(?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            System.out.println("q1 "+q1);
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, Origen);
            ((OraclePreparedStatement)stmt).setString(2, destino);
            ((OraclePreparedStatement)stmt).setString(3, idEstacion);
            ((OraclePreparedStatement)stmt).setString(4, banCompra);
            ((OraclePreparedStatement)stmt).setString(5, claveReservacion);
            stmt.registerOutParameter(6,java.sql.Types.VARCHAR);
            bResultado=stmt.execute();
            P_RESPUESTA[0] = stmt.getString(6);
            stmt.close();
            System.out.println("---------------- Respuesta del SP Tms_Act_Res_Ctrl_Prc "+P_RESPUESTA[0]);
            
            if(cnx!=null) cnx.close();
            
            //return P_RESPUESTA;
            P_RESPUESTA[0] = "0,Tms_Act_Res_Ctrl_Prc: Transacción realizada satisfactoriamente.";
            return P_RESPUESTA;
        } catch (SQLException ex) {
            try {
                P_RESPUESTA[0] = stmt.getString(6);
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                P_RESPUESTA[0] = null;
            } finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("---------------- Excepcion SP Tms_Act_Res_Ctrl_Prc - "+P_RESPUESTA[0]);
                return P_RESPUESTA;
            }
        }
    }
    
    public String[] ConfirmarReservacion(String DBLink, String numUsuario, String corteId, String idEstacion, String claveCaja, String idCliente,
            String numCorridaI, String OrigenI, String asientosI, String claveReservacionI, String tipoPagoI, String BancoI,
            String numCorridaR, String OrigenR, String asientosR, String claveReservacionR, String tipoPagoR, String BancoR) throws javax.ejb.EJBException{
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String[] P_RESPUESTA=new String[2];
        String P_RESPUESTA1=null;
        String P_RESPUESTA2=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        try {
            if(debug) {
                System.out.println("---------------- Parametros ConfirmarReservacion ----------------\n" +
                                   "Numero Usuario "+numUsuario+ " CorteId " + corteId + " IdCliente " +idCliente+
                                   " IdEstacion "+ idEstacion +" Banco "+BancoI+" ClaveCaja "+claveCaja);
                System.out.println("............... Parametros ConfirmarReservacion  Ida ...............\n"+
                                   " NumCorridaIda "+numCorridaI+" OrigenIda "+OrigenI+" AsientosIda "+asientosI+
                                   " TipoPagoIda "+tipoPagoI +" ClaveReservacionIda "+claveReservacionI);
                System.out.println("............... Parametros ConfirmarReservacion  Regreso ...............\n"+
                                   " NumCorridaRegreso "+numCorridaR+" OrigenRegreso "+OrigenR+" AsientosRegreso "+
                                   asientosR+" TipoPago "+tipoPagoR+" ClaveReservacionRegreso "+claveReservacionR);
            }
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    //"Xer_Tms_Ws_Pkg.Tms_Confirma_Reservacion_Prc"+DBLink+"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                    "Xer_Tms_Ws_Pkg.Tms_Confirma_Reservacion_Prc(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            System.out.println("q1 "+q1);
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, numUsuario);
            ((OraclePreparedStatement)stmt).setString(2, corteId);
            ((OraclePreparedStatement)stmt).setString(3, idEstacion);
            ((OraclePreparedStatement)stmt).setString(4, claveCaja);
            ((OraclePreparedStatement)stmt).setString(5, idCliente);
            ((OraclePreparedStatement)stmt).setString(6, numCorridaI);
            ((OraclePreparedStatement)stmt).setString(7, OrigenI);
            ((OraclePreparedStatement)stmt).setString(8, asientosI);
            ((OraclePreparedStatement)stmt).setString(9, claveReservacionI);
            ((OraclePreparedStatement)stmt).setString(10, tipoPagoI);
            /*if(tipoPagoI.equals("BBV"))*/
            ((OraclePreparedStatement)stmt).setString(11, BancoI);
            /*else
                ((OraclePreparedStatement)stmt).setString(10, "");*/
            ((OraclePreparedStatement)stmt).setString(12, numCorridaR);
            ((OraclePreparedStatement)stmt).setString(13, OrigenR);
            ((OraclePreparedStatement)stmt).setString(14, asientosR);
            ((OraclePreparedStatement)stmt).setString(15, claveReservacionR);
            ((OraclePreparedStatement)stmt).setString(16, tipoPagoR);
            /*if(tipoPagoR != null && tipoPagoR.equals("BBV"))*/
            ((OraclePreparedStatement)stmt).setString(17, BancoR);
            /*else
                ((OraclePreparedStatement)stmt).setString(16, "");*/
            stmt.registerOutParameter(18,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(19,java.sql.Types.VARCHAR);
            bResultado=stmt.execute();
            P_RESPUESTA1 = stmt.getString(18);
            P_RESPUESTA2 = stmt.getString(19);
            P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
            stmt.close();
            System.out.println("---------------- Respuesta del SP Tms_Confirma_Reservacion_Prc "+P_RESPUESTA1);
            System.out.println("---------------- Respuesta del SP Tms_Confirma_Reservacion_Prc "+P_RESPUESTA2);
            
            if(cnx!=null) cnx.close();
            
            return P_RESPUESTA;
        } catch (SQLException ex) {
            try {
                P_RESPUESTA1 = stmt.getString(18);
                P_RESPUESTA2 = stmt.getString(19);
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                P_RESPUESTA1=null;
                P_RESPUESTA2=null;
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
            } finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("---------------- Excepcion del SP Tms_Confirma_Reservacion_Prc "+P_RESPUESTA1);
                System.out.println("---------------- Excepcion del SP Tms_Confirma_Reservacion_Prc "+P_RESPUESTA2);
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
                return P_RESPUESTA;
            }
        }
    }



    public String[] ConfirmarReservacionCDI(String DBLink, String numUsuario, String corteId, String idEstacion, String claveCaja, String idCliente,
            String numCorridaI, String OrigenI, String asientosI, String claveReservacionI, String tipoPagoI, String BancoI,
            String numCorridaR, String OrigenR, String asientosR, String claveReservacionR, String tipoPagoR, String BancoR,String liga) throws javax.ejb.EJBException{
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String[] P_RESPUESTA=new String[2];
        String P_RESPUESTA1=null;
        String P_RESPUESTA2=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        try {
            if(debug) {
                System.out.println("---------------- Parametros ConfirmarReservacion ----------------\n" +
                                   "Liga: "+liga+" Numero Usuario "+numUsuario+ " CorteId " + corteId + " IdCliente " +idCliente+
                                   " IdEstacion "+ idEstacion +" Banco "+BancoI+" ClaveCaja "+claveCaja);
                System.out.println("............... Parametros ConfirmarReservacion  Ida ...............\n"+
                                   " NumCorridaIda "+numCorridaI+" OrigenIda "+OrigenI+" AsientosIda "+asientosI+
                                   " TipoPagoIda "+tipoPagoI +" ClaveReservacionIda "+claveReservacionI);
                System.out.println("............... Parametros ConfirmarReservacion  Regreso ...............\n"+
                                   " NumCorridaRegreso "+numCorridaR+" OrigenRegreso "+OrigenR+" AsientosRegreso "+
                                   asientosR+" TipoPago "+tipoPagoR+" ClaveReservacionRegreso "+claveReservacionR);
            }
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    //"Xer_Tms_Ws_Pkg.Tms_Confirma_Reservacion_Prc"+DBLink+"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                    "Xer_Tms_Ws_Pkg.Tms_Confirma_Reserv_CDI_Prc(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            System.out.println("q1 "+q1);
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, numUsuario);
            ((OraclePreparedStatement)stmt).setString(2, corteId);
            ((OraclePreparedStatement)stmt).setString(3, idEstacion);
            ((OraclePreparedStatement)stmt).setString(4, claveCaja);
            ((OraclePreparedStatement)stmt).setString(5, idCliente);
            ((OraclePreparedStatement)stmt).setString(6, numCorridaI);
            ((OraclePreparedStatement)stmt).setString(7, OrigenI);
            ((OraclePreparedStatement)stmt).setString(8, asientosI);
            ((OraclePreparedStatement)stmt).setString(9, claveReservacionI);
            ((OraclePreparedStatement)stmt).setString(10, tipoPagoI);
            /*if(tipoPagoI.equals("BBV"))*/
            ((OraclePreparedStatement)stmt).setString(11, BancoI);
            /*else
                ((OraclePreparedStatement)stmt).setString(10, "");*/
            ((OraclePreparedStatement)stmt).setString(12, numCorridaR);
            ((OraclePreparedStatement)stmt).setString(13, OrigenR);
            ((OraclePreparedStatement)stmt).setString(14, asientosR);
            ((OraclePreparedStatement)stmt).setString(15, claveReservacionR);
            ((OraclePreparedStatement)stmt).setString(16, tipoPagoR);
            /*if(tipoPagoR != null && tipoPagoR.equals("BBV"))*/
            ((OraclePreparedStatement)stmt).setString(17, BancoR);
            /*else
                ((OraclePreparedStatement)stmt).setString(16, "");*/
            stmt.registerOutParameter(18,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(19,java.sql.Types.VARCHAR);
            ((OraclePreparedStatement)stmt).setString(20, liga);
            bResultado=stmt.execute();
            P_RESPUESTA1 = stmt.getString(18);
            P_RESPUESTA2 = stmt.getString(19);
            P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
            stmt.close();
            System.out.println("---------------- Respuesta del SP Tms_Confirma_Reservacion_Prc "+P_RESPUESTA1);
            System.out.println("---------------- Respuesta del SP Tms_Confirma_Reservacion_Prc "+P_RESPUESTA2);

            if(cnx!=null) cnx.close();

            return P_RESPUESTA;
        } catch (SQLException ex) {
            try {
                P_RESPUESTA1 = stmt.getString(18);
                P_RESPUESTA2 = stmt.getString(19);
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                P_RESPUESTA1=null;
                P_RESPUESTA2=null;
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
            } finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("---------------- Excepcion del SP Tms_Confirma_Reservacion_Prc "+P_RESPUESTA1);
                System.out.println("---------------- Excepcion del SP Tms_Confirma_Reservacion_Prc "+P_RESPUESTA2);
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
                return P_RESPUESTA;
            }
        }
    }

        public String[] reservacionAsientosCDI( String ORIGENI,String  ORIGENR,int PASIENTOS ,String PNOMBREPASAJERO,String FECHAI,String  FECHAR) throws javax.ejb.EJBException{
        boolean bResultado;
        String[] P_RESPUESTA=new String[8];
        String P_RESERVACION_IDA ="";
        String P_RESERVACION_REGRESO ="";
        String P_NOMBRE_PASAJEROS  ="";
        String P_TIPO_PASAJEROS  ="";
        String P_ASIENTOS_IDA  ="";
        String P_ASIENTOS_REGRESO  ="";
        String P_RESP ="";
        String P_MENSAJE ="";
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            if(debug) {
                System.out.println("---------------- Parametros reservacionAsientosCDI ----------------\n" +
                                   " NumCorridaIda "+FECHAI+" OrigenIda "+ORIGENI+" Asientos "+PASIENTOS+
                                   " NumCorridaRegreso "+FECHAR+" OrigenRegreso "+ORIGENR+
                                   " Nombre pasajero "+PNOMBREPASAJERO );
            }
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "Xer_Tms_Ws_Pkg.TMS_RESERVAR_ASIENTOS_CDI_PRC(?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            System.out.println("q1 "+q1);
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, ORIGENI);
            ((OraclePreparedStatement)stmt).setString(2, ORIGENR);
            ((OraclePreparedStatement)stmt).setInt(3,PASIENTOS);
            ((OraclePreparedStatement)stmt).setString(4, PNOMBREPASAJERO);
            ((OraclePreparedStatement)stmt).setString(5, FECHAI);
            ((OraclePreparedStatement)stmt).setString(6, FECHAR);
            stmt.registerOutParameter(7,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(8,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(9,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(10,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(11,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(12,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(13,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(14,java.sql.Types.VARCHAR);
            bResultado=stmt.execute();
            P_RESPUESTA[0] = stmt.getString(7);
            P_RESPUESTA[1] = stmt.getString(8);
            P_RESPUESTA[2] = stmt.getString(9);
            P_RESPUESTA[3] = stmt.getString(10);
            P_RESPUESTA[4] = stmt.getString(11);
            P_RESPUESTA[5] = stmt.getString(12);
            P_RESPUESTA[6] = stmt.getString(13);
            P_RESPUESTA[7] = stmt.getString(14);
            stmt.close();
            System.out.println("---------------- Respuesta del SP reservacionAsientosCDI "+P_RESPUESTA[6]);

            if(cnx!=null) cnx.close();

            return P_RESPUESTA;
        } catch (SQLException ex) {
            try {
                P_RESPUESTA[6] = "-1";
                P_RESPUESTA[7] = "Hubo un probleba al intentar reservar sus boletos. Intente mas tarde";
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                P_RESPUESTA[6] = "-2";
                P_RESPUESTA[7] = "Hubo un probleba al intentar reservar sus boletos. Intente mas tarde";
            } finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("---------------- Excepcion del SP reservacionAsientosCDI ");
                P_RESPUESTA[6] = "-3";
                P_RESPUESTA[7] = "Hubo un probleba al intentar reservar sus boletos. Intente mas tarde";
                return P_RESPUESTA;
            }
        }
    }



    public String[] CancelaReservacion(String DBLink, String numUsuario,
            String numCorridaI, String OrigenI, String asientosI, String tiposPasajeroI, String claveReservacionI,
            String numCorridaR, String OrigenR, String asientosR, String tiposPasajeroR, String claveReservacionR,
            String motivoCn) throws javax.ejb.EJBException{
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String[] P_RESPUESTA=new String[2];
        String P_RESPUESTA1=null;
        String P_RESPUESTA2=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        try {
            if(debug){
            System.out.println("---------------- Parametros CancelaReservacion ----------------\n" +
                                   "Numero Usuario "+numUsuario);
                System.out.println("............... Parametros CancelaReservacion  Ida ...............\n"+
                                   " NumCorridaIda "+numCorridaI+" OrigenIda "+OrigenI+" AsientosIda "+asientosI+
                                   " tiposPasajeroI "+tiposPasajeroI +" ClaveReservacionIda "+claveReservacionI);
                System.out.println("............... Parametros ConfirmarReservacion  Regreso ...............\n"+
                                   " NumCorridaRegreso "+numCorridaR+" OrigenRegreso "+OrigenR+" AsientosRegreso "+
                                   asientosR+" tiposPasajeroR "+tiposPasajeroR+" ClaveReservacionRegreso "+claveReservacionR);                                
            }
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "Xer_Tms_Ws_Pkg.Tms_Cancela_Reservacion_Prc"+DBLink+"(?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, numUsuario);
            ((OraclePreparedStatement)stmt).setString(2, numCorridaI);
            ((OraclePreparedStatement)stmt).setString(3, OrigenI);
            ((OraclePreparedStatement)stmt).setString(4, asientosI);
            ((OraclePreparedStatement)stmt).setString(5, tiposPasajeroI);
            ((OraclePreparedStatement)stmt).setString(6, claveReservacionI);
            ((OraclePreparedStatement)stmt).setString(7, numCorridaR);
            ((OraclePreparedStatement)stmt).setString(8, OrigenR);
            ((OraclePreparedStatement)stmt).setString(9, asientosR);
            ((OraclePreparedStatement)stmt).setString(10, tiposPasajeroR);
            ((OraclePreparedStatement)stmt).setString(11, claveReservacionR);
            ((OraclePreparedStatement)stmt).setString(12, motivoCn);
            stmt.registerOutParameter(13,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(14,java.sql.Types.VARCHAR);
            bResultado=stmt.execute();
            P_RESPUESTA1 = stmt.getString(13);
            P_RESPUESTA2 = stmt.getString(14);
            P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
            stmt.close();
            System.out.println("---------------- Respuesta del SP Tms_Cancela_Reservacion_Prc Ida "+P_RESPUESTA1);
            System.out.println("---------------- Respuesta del SP Tms_Cancela_Reservacion_Prc Regreso "+P_RESPUESTA2);
            
            if(cnx!=null) cnx.close();
            
            return P_RESPUESTA;
        } catch (SQLException ex) {
            try {
                P_RESPUESTA1 = stmt.getString(13);
                P_RESPUESTA2 = stmt.getString(14);
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                P_RESPUESTA1=null;
                P_RESPUESTA2=null;
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
            } finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("---------------- Excepcion del SP Tms_Cancela_Reservacion_Prc Ida "+P_RESPUESTA1);
                System.out.println("---------------- Excepcion del SP Tms_Cancela_Reservacion_Prc Regreso "+P_RESPUESTA2);
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
                return P_RESPUESTA;
            }
        }
    }
    
    public String[] cancelaBoletos(String DBLink, String numUsuario, String corteId, String idEstacion, String claveCaja,String viajeRedondo,
            String numCorridaI, String OrigenI, String OrigenI_id,
            String asientosI, String asientosI_tipo, String asientosI_folios,
            String numCorridaR, String OrigenR, String OrigenR_id, String asientosR,
            String asientosR_tipo, String asientosR_folios) throws javax.ejb.EJBException{
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String[] P_RESPUESTA=new String[2];
        String P_RESPUESTA1=null;
        String P_RESPUESTA2=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        try {
            System.out.println("---------------- Parametros cancelaBoletos ----------------\n" +
                               " numUsuario "+numUsuario +  " corteId " + corteId + " idEstacion " + idEstacion +
                               " viajeRedondo " + viajeRedondo);
            System.out.println("............... Parametros cancelaBoletos  Ida ...............\n"+
                              " numCorridaI "+numCorridaI+" OrigenI "+OrigenI+" OrigenI_id "+ OrigenI_id +
                              " asientosI "+ asientosI+" asientosI_folios "+ asientosI_folios + " asientosI_tipo "+
                              asientosI_tipo);
            System.out.println("............... Parametros cancelaBoletos Regreso ...............\n"+
                               " numCorridaR "+numCorridaR+" OrigenR "+OrigenR+" OrigenR_id "+ OrigenR_id +" asientosR "+ asientosR+
                               " asientosR_folios "+ asientosR_folios + " asientosR_tipo "+ asientosR_tipo);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "Xer_Tms_Ws_Pkg.Tms_Cancela_Boleto_Prc(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            System.out.println("q1 "+q1);
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, numUsuario);
            ((OraclePreparedStatement)stmt).setString(2, corteId);
            ((OraclePreparedStatement)stmt).setString(3, idEstacion);
            ((OraclePreparedStatement)stmt).setString(4, claveCaja);
            //((OraclePreparedStatement)stmt).setString(5, viajeRedondo);
            ((OraclePreparedStatement)stmt).setString(5, numCorridaI);
            ((OraclePreparedStatement)stmt).setString(6, OrigenI);
            ((OraclePreparedStatement)stmt).setString(7, OrigenI_id);
            ((OraclePreparedStatement)stmt).setString(8, asientosI);
            ((OraclePreparedStatement)stmt).setString(9, asientosI_tipo);
            ((OraclePreparedStatement)stmt).setString(10, asientosI_folios);
            ((OraclePreparedStatement)stmt).setString(11, numCorridaR);
            ((OraclePreparedStatement)stmt).setString(12, OrigenR);
            ((OraclePreparedStatement)stmt).setString(13, OrigenR_id);
            ((OraclePreparedStatement)stmt).setString(14, asientosR);
            ((OraclePreparedStatement)stmt).setString(15, asientosR_tipo);
            ((OraclePreparedStatement)stmt).setString(16, asientosR_folios);
            stmt.registerOutParameter(17,java.sql.Types.VARCHAR);
            stmt.registerOutParameter(18,java.sql.Types.VARCHAR);
            bResultado=stmt.execute();
            P_RESPUESTA1 = stmt.getString(17);
            P_RESPUESTA2 = stmt.getString(18);
            P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
            stmt.close();
            System.out.println("---------------- Respuesta del SP Tms_Cancela_Boleto_Prc Ida "+P_RESPUESTA1);
            System.out.println("---------------- Respuesta del SP Tms_Cancela_Boleto_Prc Regreso "+P_RESPUESTA2);
            
            if(cnx!=null) cnx.close();
            
            return P_RESPUESTA;
        } catch (SQLException ex) {
            try {
                P_RESPUESTA1 = stmt.getString(17);
                P_RESPUESTA2 = stmt.getString(18);
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                P_RESPUESTA1=null;
                P_RESPUESTA2=null;
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
            } finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("---------------- Excepcion del SP Tms_Cancela_Boleto_Prc Ida "+P_RESPUESTA1);
                System.out.println("---------------- Excepcion del SP Tms_Cancela_Boleto_Prc Regreso "+P_RESPUESTA2);
                P_RESPUESTA[0] = P_RESPUESTA1; P_RESPUESTA[1] = P_RESPUESTA2;
                return P_RESPUESTA;
            }
        }
    }
    
    public String motorBusqueda(String DBLink, String numSocio, String corteId, String strOrigen,
            String strFecha1, String strFecha2, String strFecha3, String strFecha4,
            String strServicio, String strDestino, String strEmpresa, int viajeRedondo) throws javax.ejb.EJBException{
        System.out.println(""+DBLink+","+numSocio+","+corteId+","+strOrigen+","+strFecha1+","+strFecha2+","+strFecha3+","+strFecha4+","+
                strServicio+","+strDestino+","+strEmpresa+","+
                viajeRedondo);
        if(!DBLink.equals("")) DBLink = "@" + DBLink;
        String P_RESPUESTA=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        boolean bResultado;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "Xer_Tms_Ws_Pkg.Tms_MOTORCORRIdaS_Prc"+DBLink+"(?,?,?,?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
            System.out.println("qry: "+q1);
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, numSocio);
            ((OraclePreparedStatement)stmt).setString(2, corteId);
            ((OraclePreparedStatement)stmt).setString(3, strOrigen);
            ((OraclePreparedStatement)stmt).setString(4, strFecha1);
            ((OraclePreparedStatement)stmt).setString(5, strFecha2);
            ((OraclePreparedStatement)stmt).setString(6, strFecha3);
            ((OraclePreparedStatement)stmt).setString(7, strFecha4);
            ((OraclePreparedStatement)stmt).setString(8, strServicio);
            ((OraclePreparedStatement)stmt).setString(9, strDestino);
            ((OraclePreparedStatement)stmt).setString(10, strEmpresa);
            ((OraclePreparedStatement)stmt).setInt(11, viajeRedondo);
            stmt.registerOutParameter(12,java.sql.Types.CLOB);
            
            bResultado=stmt.execute();
            P_RESPUESTA = stmt.getString(12);
            stmt.close();
            System.out.println("Bean motorBusqueda - "+P_RESPUESTA);
            
            if(cnx!=null) cnx.close();
            
            return P_RESPUESTA;
        } catch (SQLException ex) {
            try {
                P_RESPUESTA = stmt.getString(12);
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
                P_RESPUESTA=null;
            } finally{
                cnx=null;
                ex.printStackTrace();
                System.out.println("Excepcion Bean motorBusqueda - "+P_RESPUESTA);
                return P_RESPUESTA;
            }
        }
    }

    /**
      * Obtiene los lugares disponibles en una corrda especifica
      * @param corrida Clave de la corrida a evaluar
      * @return Lugares disponibles en la corrida<br/>
     * menores_corrida<br/>senectud_corrida<br/>estudiantes_corrida<br/>profesores_corrida<br/>cortesias_corrida
      */
    public Object[] getDisponibles(String corrida) {
        Query query = em.createNativeQuery("SELECT menores_corrida, senectud_corrida, estudiantes_corrida, profesores_corrida, cortesias_corrida "
                + "FROM TMS_CORRIDAS_VENTA_TBL "
                + "WHERE clave_corrida = '" + corrida + "'");
        List result = null;
        try {
            List set = query.getResultList();
            if(set.size() > 0) {
                result = (List)set.get(0);
                result.add(0, true);
                result.add("Se han recuperado los datos con exito");
            }
            else
                throw new NoResultException();
        } catch(NoResultException ex) {
            result = new ArrayList();
            result.add(false);
            result.add(0);
            result.add(0);
            result.add(0);
            result.add(0);
            result.add(0);
            result.add("La corrida no existe");
        }
        return result.toArray();
    }

    public String getNumCorridaCDI(String claveReservacion, String DBLink){
        //String qry = "select distinct corrida_Id from TMS_RESERVACIONES_TBL@"+DBLink+" where CLAVE_RESERVACION = '"+claveReservacion+"'";
        String qry = "SELECT DISTINCT(R.CORRIDA_ID)||'|'||c.CLAVE_CORRIDA||'-'||to_char(c.fecha_hora_corrida, 'DD/MM/RRRR')||'-'||to_char(c.fecha_hora_corrida, 'HH24:MI')||'-'||c.servicio datos "
                + "FROM  "
                + "TMS_RESERVACIONES_TBL@"+DBLink+"   r left join TMS_CORRIDAS_venta_TBL@"+DBLink+"  c on c.CORRIDA_ID = r.CORRIDA_ID "
                + "where CLAVE_RESERVACION = '"+claveReservacion+"'";

        System.out.println("qry getNumCorridaCDI: "+qry);
        String res = em.createNativeQuery(qry).getSingleResult().toString();
        return res.replace("[", "").replace("]", "");
    }


}
