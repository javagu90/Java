/*
 * VendERMobileFacadeBean.java
 *
 * Created on 4 de septiembre de 2009, 09:56 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERSolicitudv3;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.internal.OracleCallableStatement;
import oracle.jdbc.internal.OraclePreparedStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;

/**
 *
 * @author asolis
 */
@Stateless
public class VendERMobileFacadeBean implements vendERSolicitudv3.VendERMobileFacadeRemote {
    @PersistenceContext
    private EntityManager em;
    @Resource(name = "TMS_DB")
    private DataSource dataSource;
    private Vector regreso;
    ResultSet rs = null;
    Connection cnx=null;
    OracleCallableStatement stmt=null;
    Vector vec = null;
    Vector valores = new Vector();
    private Vector regresoTodos;
    /**
     * Creates a new instance of VendERMobileFacadeBean
     */
    public VendERMobileFacadeBean() {
    }

    public Vector logIn(String usuario, String contrasenia, String estacionTrabajo, String mac){
        System.out.println("----------------------  Inicia Facade LogIn ----------------------");
        Vector respuesta=new Vector();
        cnx = null;
        stmt = null;
        boolean bResultado;
        try {
            System.out.println("Parametros");
            System.out.println("usuario "+usuario+ " contraseña "+contrasenia+" nombreEquipo "+estacionTrabajo
                    +" mac "+mac);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_IniciaSesion2_Prc(?, ?, ?, ?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, usuario);
            ((OraclePreparedStatement)stmt).setString(2, contrasenia);
            ((OraclePreparedStatement)stmt).setString(3, estacionTrabajo);
            ((OraclePreparedStatement)stmt).setString(4, mac);
            stmt.registerOutParameter(5,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            //stmt.registerOutParameter(4,java.sql.Types.VARCHAR);
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(5).getAttributes();
            System.out.println("............. Respuesta SP LogIn "+(String)attrs[0]+","+(String)attrs[1]+" ............. ");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            stmt.close();
            System.out.println("............. Respuesta Facade LogIn => "+respuesta+" .............");

            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
           cnx=null;
           ex.printStackTrace();
           System.out.println("+++++++++++++++++++++ Excepcion Facade LogIn - "+respuesta+" +++++++++++++++++++++");
           respuesta = null;
        }
        System.out.println("----------------------  Termina Facade LogIn ----------------------");
        return respuesta;
    }

    public Vector getPermisos(String cajaNombre, String usuario, String funcionNumero, String uid, String contraseña){
        System.out.println("----------------------  Inicia Facade getPermisos ----------------------");
        Vector respuesta=new Vector();
        boolean bResultado;
        if(funcionNumero == null || funcionNumero.length() == 0)
            funcionNumero = "null";
        try {
            if(usuario.length() == 0)
                usuario  = "no hay";
            if(contraseña.length() == 0)
                contraseña = "no hay";
            System.out.println("Parametros");
            System.out.println("cajaNombre "+cajaNombre +" usuario "+usuario+ " contraseña "+contraseña+" funcionNumero "+funcionNumero+" uid "+uid);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_GetPermisos_Prc(?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, usuario);
            ((OraclePreparedStatement)stmt).setString(3, contraseña);
            ((OraclePreparedStatement)stmt).setString(4, funcionNumero);
            ((OraclePreparedStatement)stmt).setString(5, uid);
            stmt.registerOutParameter(6,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(7,OracleTypes.CURSOR);
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(6).getAttributes();
            System.out.println("............. Respuesta SP getPermisos "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }

            Vector temp = new Vector();
            if(!respuesta.get(0).equals("0"))
                return respuesta;

            vec = new Vector();
            vec.add("idPermiso");
            vec.add("permisoNombre");
            vec.add("permisoClave");
            vec.add("descripcion");
            vec.add("perfilNombre");
            respuesta.add(getCursor(7,vec, "getPermisos"));
            stmt.close();
            System.out.println("............. Respuesta Facade getPermisos => "+respuesta+" ............. ");
            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
            cnx=null;
            ex.printStackTrace();
            System.out.println("+++++++++++++++++++++ Excepcion Facade getPermisos => "+respuesta+" +++++++++++++++++++++");
            respuesta = null;
        }
        System.out.println("----------------------  Termina Facade getPermisos ----------------------");
        return respuesta;
    }

    public Vector getParametrosIniciales(String cajaNombre,String campoTipo, String uid){
        Vector respuesta=new Vector();
        cnx=null;
        stmt=null;
        boolean bResultado;
        if(campoTipo != null && campoTipo.length() > 0)
            campoTipo = campoTipo; //dummy;
        else
            campoTipo = "todos";
        try {
			System.out.println("----------------------  Inicia Facade getParametrosIniciales ----------------------");
			System.out.println("Parametros");
            System.out.println("cajaNombre "+cajaNombre+" campoTipo "+campoTipo+ " uid "+uid);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERmobile_GetParamsInic_prc(?,?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, campoTipo);
            ((OraclePreparedStatement)stmt).setString(3, uid);
            stmt.registerOutParameter(4,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(5,OracleTypes.CURSOR);
            stmt.registerOutParameter(6,OracleTypes.CURSOR);
            stmt.registerOutParameter(7,OracleTypes.CURSOR);
            stmt.registerOutParameter(8,OracleTypes.CURSOR);
            stmt.registerOutParameter(9,OracleTypes.CURSOR);
            stmt.registerOutParameter(10,OracleTypes.CURSOR);

            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(4).getAttributes();

            System.out.println("............. Respuesta SP getParametrosIniciales => "+(String)attrs[0]+","+(String)attrs[1]+" ............. ");

            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            if(respuesta != null &&!respuesta.get(0).equals("0"))
                return respuesta;
            regreso = new Vector();
            Vector columnas = new Vector();
            columnas.add("idParametro");
            columnas.add("parametroCodigo");
            columnas.add("parametroNombre");
            columnas.add("parametroValor");
            columnas.add("descripcion");
            columnas.add("idParametroValor");
            columnas.add("parametroTipo");
            columnas.add("fechaDesde");
            columnas.add("fechaHasta");
            columnas.add("adicional1");
            columnas.add("adicional2");
            columnas.add("adicional3");
            columnas.add("adicional4");
            columnas.add("adicional5");
            if(campoTipo.equals("todos")){
                regresoTodos = new Vector();
                /*caja*/
                //System.out.println("cajas");
                valores = new Vector();
                StringTokenizer token = new StringTokenizer(respuesta.get(2).toString(), "|");
                while(token.hasMoreTokens()){
                    valores.add(token.nextToken());
                }
                getCursorTodos(5,null);
                /*ruta*/
                //System.out.println("ruta");
                getCursorTodos(6,null);
                /*sucursal*/
                //System.out.println("sucursal");
                getCursorTodos(7,valores);
                /*servicio*/
                //System.out.println("servicio");
                getCursorTodos(8,null);
                /*empresa*/
                //System.out.println("empresa");
                getCursorTodos(9,null);
                /*global*/
                //System.out.println("global");
                getCursorTodos(10,null);
                respuesta.add(regresoTodos);
                stmt.close();
                System.out.println("............. Respuesta Facade getParametrosIniciales => "+respuesta+" .............");

                if(cnx!=null) cnx.close();
                return respuesta;
            }
            if(campoTipo.equals("caja")){
                regreso = new Vector();
                /*caja*/
                //System.out.println("caja");
                //regreso.add(getCursor(4,columnas,"getParametrosIniciales").get(0));
                regreso= getCursor(5,columnas,"getParametrosIniciales");
            }
            if(campoTipo.equals("ruta")){
                regreso = new Vector();
                /*ruta*/
                //System.out.println("ruta");
                //regreso.add(getCursor(5,columnas,"getParametrosIniciales").get(0));
                regreso= getCursor(6,columnas,"getParametrosIniciales");
            }
            if(campoTipo.equals("sucursal")){
                StringTokenizer token = new StringTokenizer(respuesta.get(2).toString(), "|");
                while(token.hasMoreTokens()){
                    valores.add(token.nextToken());
                }
                regreso = new Vector();
                /*sucursal*/
                //System.out.println("sucursal");
                //regreso.add(getCursor(6,columnas,"getParametrosIniciales").get(0));
                regreso= getCursor(7,columnas,"getParametrosIniciales");
            }
            if(campoTipo.equals("servicio")){
                regreso = new Vector();
                /*sucursal*/
                //System.out.println("servicio");
                //regreso.add(getCursor(7,columnas,"getParametrosIniciales").get(0));
                regreso= getCursor(8,columnas,"getParametrosIniciales");
            }
            if(campoTipo.equals("empresa")){
                regreso = new Vector();
                /*sucursal*/
                //System.out.println("empresa");
                //regreso.add(getCursor(8,columnas,"getParametrosIniciales").get(0));
                regreso = getCursor(9,columnas,"getParametrosIniciales");
            }
            if(campoTipo.equals("global")){
                regreso = new Vector();
                /*global*/
                //System.out.println("global");
                //regreso.add(getCursor(9,columnas,"getParametrosIniciales"));
                regreso = getCursor(10,columnas,"getParametrosIniciales");
            }

            respuesta.add(regreso);
            stmt.close();
            System.out.println("............. Respuesta Facade getParametrosIniciales => "+respuesta+" .............");

            if(cnx!=null) cnx.close();
            return respuesta;
        } catch (SQLException ex) {
            cnx=null;
            ex.printStackTrace();
            System.out.println("+++++++++++++++++++++ Excepcion Facade getParametrosIniciales => "+respuesta+" +++++++++++++++++++++ ");
            respuesta = null;
        }
        System.out.println("----------------------  Termina Facade getParametrosIniciales ----------------------");
        return respuesta;
    }

    public Vector getTarifas(String cajaNombre,String uid, String rutaId){
        System.out.println("----------------------  Inicia Facade getTarifas ----------------------");
        Vector respuesta=new Vector();
        cnx=null;
        stmt=null;
        boolean bResultado;
        if(rutaId == null || rutaId.length() <= 0 || rutaId.equals("0"))
            rutaId = "null";
        try {
            System.out.println("Parametros");
            System.out.println("cajaNombre"+cajaNombre+" uid "+uid+ " rutaId "+rutaId);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_GetTarifas2_Prc(?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, uid);
            ((OraclePreparedStatement)stmt).setString(3, rutaId);
            stmt.registerOutParameter(4,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(5,OracleTypes.CURSOR);
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(4).getAttributes();

            System.out.println("............. Respuesta SP getTarifas2 " +(String)attrs[0]+","+(String)attrs[1]+" .............");

            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            Vector temp = new Vector();
            if(!respuesta.get(0).equals("0"))
                    return respuesta;
            vec = new Vector();                        
            vec.add("Ruta_Id");
            vec.add("tramo_Id");
            vec.add("tipo_pasajero_id");
            vec.add("tipo_tarifa");
            vec.add("importe");
            respuesta.add(getCursor(5,vec, "getTarifas"));
            stmt.close();
            System.out.println("............. Respuesta Facade getTarifas => "+respuesta+" .............");

            if(cnx!=null) cnx.close();
            return respuesta;
        } catch (SQLException ex) {
				System.out.println("+++++++++++++++++++++ Excepcion Facade getTarifas =>");
                ex.printStackTrace();
                respuesta=null;
       }
       System.out.println("----------------------  Termina Facade getTarifas ----------------------");
       return respuesta;
    }

    public Vector getTipoPasajeros(String cajaNombre,String uid){
        System.out.println("----------------------  Inicia Facade getTipoPasajeros ----------------------");
        Vector respuesta=new Vector();
        cnx = null;
        stmt = null;
        boolean bResultado;
        try {
            System.out.println("Parametros");
            System.out.println("cajaNombre " +cajaNombre+" uid "+uid);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_getTipoPas_Prc(?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, uid);
            stmt.registerOutParameter(3,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(4,OracleTypes.CURSOR);
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(3).getAttributes();

            System.out.println("............. Respuesta SP getTipoPasajeros "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            if(!respuesta.get(0).equals("0"))
                    return respuesta;

            Vector temp = new Vector();
            if(!respuesta.get(0).equals("0"))
                    return respuesta;
            vec = new Vector();
            vec.add("idTipoPasajero");
            vec.add("tipoPasajeroNombre");
            vec.add("tipoPasajeroClave");
            vec.add("pct_descuento");
            respuesta.add(getCursor(4,vec, "getPermisos"));

            stmt.close();
            System.out.println("............. Respuesta Facade getTipoPasajeros => "+respuesta+" .............");

            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade getTipoPasajeros => "+respuesta+"+++++++++++++++++++++");
            }
       System.out.println("----------------------  Termina Facade getTipoPasajeros ----------------------");
       return respuesta;
    }

    public Vector getTipoPago(String cajaNombre,String uid){
        System.out.println("----------------------  Inicia Facade getTipoPago ----------------------");
        Vector respuesta=new Vector();
        cnx = null;
        stmt = null;
        boolean bResultado;
        try {
            System.out.println("Parametros");
            System.out.println("cajaNombre "+cajaNombre+" uid "+uid);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_getTipoPago_Prc(?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, uid);
            stmt.registerOutParameter(3,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(4,OracleTypes.CURSOR);
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(3).getAttributes();
            System.out.println("............. Respuesta SP getTipoPago "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            if(!respuesta.get(0).equals("0"))
                    return respuesta;
            Vector temp = new Vector();
            if(!respuesta.get(0).equals("0"))
                    return respuesta;
            vec = new Vector();
            vec.add("idTipoPago");
            vec.add("tipoPagoNombre");
            vec.add("tipoPagoClave");
            respuesta.add(getCursor(4,vec, "getPermisos"));
            stmt.close();
            System.out.println("............. Respuesta Facade getTipoPago => "+respuesta+" .............");

            if(cnx!=null) cnx.close();
            return respuesta;
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade getTipoPago => "+respuesta+"+++++++++++++++++++++");
            }
        System.out.println("----------------------  Termina Facade getTipoPago ----------------------");
        return respuesta;
    }

    public Vector getServicios(String cajaNombre,String uid){
        System.out.println("----------------------  Inicia Facade getServicios ----------------------");
        Vector respuesta=new Vector();
        cnx = null;
        stmt = null;
        boolean bResultado;
        try {
            System.out.println("CajaNombre "+cajaNombre+ " uid "+uid);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_getServicios_prc(?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, uid);
            stmt.registerOutParameter(3,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(4,OracleTypes.CURSOR);
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(3).getAttributes();
            System.out.println("............. Respuesta SP getServicios "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            if(!respuesta.get(0).equals("0"))
                    return respuesta;

            Vector temp = new Vector();
            if(!respuesta.get(0).equals("0"))
                    return respuesta;
            vec = new Vector();
            vec.add("idServicio");
            vec.add("servicioClave");
            vec.add("servicioNombre");
            respuesta.add(getCursor(4,vec, "getServicios"));

            stmt.close();
            System.out.println("............. Respuesta Facade getServicios => "+respuesta+" .............");

            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade getServicios => "+respuesta+"+++++++++++++++++++++");
            }
       System.out.println("----------------------  Termina Facade getServicios ----------------------");
       return respuesta;
    }

    public Vector getEmpresas(String cajaNombre,String uid){
        System.out.println("----------------------  Inicia Facade getEmpresas ----------------------");
        Vector respuesta=new Vector();
        cnx = null;
        stmt = null;
        boolean bResultado;
        try {
            System.out.println("Parametros");
            System.out.println("cajaNombre "+cajaNombre+" uid "+uid);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_getEmpresas_prc(?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, uid);
            stmt.registerOutParameter(3,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(4,OracleTypes.CURSOR);
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(3).getAttributes();
            System.out.println("............. Respuesta SP getEmpresas "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            if(!respuesta.get(0).equals("0"))
                    return respuesta;

            Vector temp = new Vector();
            if(!respuesta.get(0).equals("0"))
                    return respuesta;
            vec = new Vector();
            vec.add("idEmpresa");
            vec.add("empresaClave");
            vec.add("empresaNombre");
            respuesta.add(getCursor(4,vec, "getEmpresas"));

            stmt.close();
            System.out.println("............. Respuesta Facade getEmpresas => "+respuesta+" .............");

            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade getEmpresas => "+respuesta+"+++++++++++++++++++++");
            }
       System.out.println("----------------------  Termina Facade getEmpresas ----------------------");
       return respuesta;
    }

    public Vector getEstados(String cajaNombre,String uid){
        System.out.println("----------------------  Inicia Facade getEstados ----------------------");
        Vector respuesta=new Vector();
        cnx = null;
        stmt = null;
        boolean bResultado;
        try {
            System.out.println("Parametros");
            System.out.println("cajaNombre "+cajaNombre+" uid "+uid);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_getEstados_prc(?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, uid);
            stmt.registerOutParameter(3,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(4,OracleTypes.CURSOR);
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(3).getAttributes();
            System.out.println("............. Respuesta SP getEstados "+(String)attrs[0]+","+(String)attrs[1]);
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            if(!respuesta.get(0).equals("0"))
                    return respuesta;

            Vector temp = new Vector();
            if(!respuesta.get(0).equals("0"))
                    return respuesta;
            vec = new Vector();
            vec.add("ESTADO_ID");
            vec.add("ESTADO_NOMBRE");
            vec.add("nada");
            respuesta.add(getCursor(4,vec, "getEstados"));

            stmt.close();
            System.out.println("............. Respuesta Facade getEstados => "+respuesta);

            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade getEstados => "+respuesta);
            }
       System.out.println("----------------------  Termina Facade getEstados ----------------------");
       return respuesta;
    }

    public Vector getRutas(String cajaNombre,String uid){
        System.out.println("----------------------  Inicia Facade getRutas ----------------------");
        Vector respuesta=new Vector();
        cnx = null;
        stmt = null;
        boolean bResultado;
        try {
            System.out.println("Parametros");
            System.out.println("cajaNombre "+cajaNombre+" uid "+uid);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_getRutas_prc(?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, uid);
            stmt.registerOutParameter(3,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(4,OracleTypes.CURSOR);
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(3).getAttributes();
            System.out.println("............. Respuesta SP getRutas "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            if(!respuesta.get(0).equals("0"))
                    return respuesta;

            Vector temp = new Vector();
            if(!respuesta.get(0).equals("0"))
                    return respuesta;
            vec = new Vector();
            vec.add("idOrigen");
            vec.add("origen");
            vec.add("iddestino");
            vec.add("destino");
            vec.add("iDservicio");
            vec.add("servicio");
            vec.add("rutaId");
            vec.add("Modooffline");
            respuesta.add(getCursor(4,vec, "getRutas"));

            stmt.close();

            System.out.println("............. Respuesta Facade getRutas => "+respuesta+" .............");

            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade getRutas => "+respuesta+"+++++++++++++++++++++");
            }
       System.out.println("----------------------  Termina Facade getRutas ----------------------");
       return respuesta;
    }

    //Puid VARCHAR2, pSupervisorNumero IN VARCHAR2, pSupervisorContrasenia IN VARCHAR2,  pMontoReco IN VARCHAR2,pCtdMonto IN VARCHAR2, PRESPUESTA OUT vender_vector_type
        public Vector getRecoleccion(String cajaNombre,String uid, String pSupervisorNumero, String pSupervisorContrasenia, String formaPagoId, String formaPago, String pCtdMonto,String pMontoReco){
        System.out.println("----------------------  Inicia Facade getRecoleccion ----------------------");
        Vector respuesta=new Vector();
        cnx = null;
        stmt = null;
        boolean bResultado;
        Vector tipoPagos;
        try {
            System.out.println("Parametros");
            System.out.println("cajaNombre "+cajaNombre+" uid "+uid+" pSupervisorNumero "+((pSupervisorNumero.length() == 0) ? "no hay" : pSupervisorNumero) + " pSupervisorContrasenia "+((pSupervisorContrasenia.length() == 0) ? "no hay" : pSupervisorContrasenia)+" formaPagoId "+formaPagoId+" formaPago "+formaPago+" pCtdMonto "+pCtdMonto+" pMontoReco "+pMontoReco);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_setRecolec_Prc(?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, uid);
            ((OraclePreparedStatement)stmt).setString(3, (pSupervisorNumero.length() == 0) ? "no hay" : pSupervisorNumero);
            ((OraclePreparedStatement)stmt).setString(4, (pSupervisorContrasenia.length() == 0) ? "no hay" : pSupervisorContrasenia);
            ((OraclePreparedStatement)stmt).setString(5, formaPagoId);
            ((OraclePreparedStatement)stmt).setString(6, formaPago);
            ((OraclePreparedStatement)stmt).setString(7, pCtdMonto);
            ((OraclePreparedStatement)stmt).setString(8, pMontoReco);
            stmt.registerOutParameter(9,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(9).getAttributes();
            System.out.println("............. Respuesta SP getRecoleccion "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            stmt.close();
            System.out.println("............. Respuesta Facade getRecoleccion => "+respuesta+" .............");

            if(cnx!=null) cnx.close();
            //return respuesta;
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
            } finally{
                cnx=null;
                //ex.printStackTrace();
                System.out.println("+++++++++++++++++++++ Excepcion Facade getRecoleccion => "+respuesta+"+++++++++++++++++++++");
            }
        System.out.println("----------------------  Termina Facade getRecoleccion ----------------------");
        return respuesta;
    }

    public Vector getCorridas(String cajaNombre,String uid, String pRutaOrigen, String pOrigen, String pDestino,
                              String pFecha,
                              String pServicio, String pEmpresa){
        System.out.println("----------------------  Inicia Facade getCorridas ----------------------");
        Vector respuesta=new Vector();
        cnx = null;
        stmt = null;
        boolean bResultado;
        Vector tipoPagos;
        try {
            pRutaOrigen = "'"+pRutaOrigen+"'";
            pOrigen = "'"+pOrigen+"'";
            pDestino = "'"+pDestino+"'";
            pFecha = "'"+pFecha+"'";
            pServicio = pServicio.length() == 0 ? "null" : "'"+pServicio+"'";
            pEmpresa = pEmpresa.length() == 0 ? "null" : "'"+pEmpresa+"'";

            System.out.println("Parametros");
            System.out.println("cajaNombre "+cajaNombre+" uid "+uid+" pRutaOrigen "+pRutaOrigen + " pOrigen "+pOrigen+" pDestino "+pDestino+" pFechaIda "+pFecha
                    +" pServicio "+pServicio+" pEmpresa "+pEmpresa);

            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_getCorridas_Prc(?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, uid);
            ((OraclePreparedStatement)stmt).setString(3, pOrigen);
            ((OraclePreparedStatement)stmt).setString(4, pDestino);
            ((OraclePreparedStatement)stmt).setString(5, pFecha);
            ((OraclePreparedStatement)stmt).setString(6, pServicio);
            ((OraclePreparedStatement)stmt).setString(7, pEmpresa);
            stmt.registerOutParameter(8,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(9,OracleTypes.CLOB);
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(8).getAttributes();
            System.out.println("............. Respuesta SP getCorridas "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }


            if(!respuesta.get(0).equals("0"))
                return respuesta;
            /*Vector vec = new Vector();
            vec = new Vector();
            vec.add("clave_corrida");
            vec.add("SERVICIO_id");
            vec.add("SERVICIO_clave");
            vec.add("SERVICIO");
            vec.add("origen_id");
            vec.add("origen_clave");
            vec.add("origen");
            vec.add("destino_id");
            vec.add("destino_clave");
            vec.add("destino");
            vec.add("empresa_id");
            vec.add("empresa_clave");
            vec.add("empresa");
            vec.add("ruta_id");
            vec.add("ruta_clave");
            vec.add("ruta_nombre");
            vec.add("PLANTILLA_ID");
            vec.add("PLANTILLA_nombre");
            vec.add("fecha_corrida");
            vec.add("hora_corrida");
            vec.add("sentidoViaje");
            vec.add("tipo_corrida");
            vec.add("tipo_ITINERARIO");
            vec.add("ADICIONAL2");
            vec.add("asientoNumerado");
            respuesta.add(getCursor(10,vec, "getCorridas"));*/
            String res = "";
            try{
                res = (stmt.getString(9).length() > 0 ) ? stmt.getString(9) : "reinicio";
            }catch(Exception e){
                res = "reinicio";
            }
            System.out.println("............. Respuesta Facade getCorridas => "+res+" .............");
            respuesta.add(res);
            stmt.close();
            System.out.println("............. Respuesta Facade getCorridas => "+respuesta+" .............");
            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade getCorridas => "+respuesta+"+++++++++++++++++++++");
        }
        System.out.println("----------------------  Termina Facade getCorridas ----------------------");
        return respuesta;
    }

    public Vector getItinerarios(String cajaNombre,String uid, String claveCorrida, String origen){
        System.out.println("----------------------  Inicia Facade getItinerarios ----------------------");
        Vector respuesta=new Vector();
        cnx = null;
        stmt = null;
        boolean bResultado;
        Vector tipoPagos;
        try {
            System.out.println("Parametros");
            System.out.println("cajaNombre "+cajaNombre+" uid "+uid+" claveCorrida "+claveCorrida+" origen "+origen);

            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_getItinerario_Prc(?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, uid);
            ((OraclePreparedStatement)stmt).setString(3, claveCorrida);
            ((OraclePreparedStatement)stmt).setString(4, origen);
            stmt.registerOutParameter(5,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(6,OracleTypes.CURSOR);
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(5).getAttributes();
            System.out.println("............. Respuesta SP getPermisos "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }

            if(!respuesta.get(0).equals("0"))
                    return respuesta;
            Vector vec = new Vector();
            vec = new Vector();
            vec.add("origen_id");
            vec.add("origen_clave");
            vec.add("origen");
            vec.add("destino_id");
            vec.add("destino_clave");
            vec.add("destino");
            vec.add("horaSalida");
            vec.add("horaLlegada");
            vec.add("diasDefasamiento");
            vec.add("tipoItinerario");
            vec.add("tipoCorrida");
            vec.add("TARIFA_sen");
            vec.add("TARIFA_RED");
            respuesta.add(getCursor(6,vec, "getItinerarios"));
            stmt.close();
            System.out.println("............. Respuesta Facade getItinerarios => "+respuesta+" .............");
            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade getItinerarios => "+respuesta+"+++++++++++++++++++++");
            }
         System.out.println("----------------------  Termina Facade getItinerarios ----------------------");
         return respuesta;
    }

    public Vector getItinerariosPorRuta(String cajaNombre,String uid, String rutaId){
        System.out.println("----------------------  Inicia Facade getItinerariosPorRuta ----------------------");
        Vector respuesta=new Vector();
        cnx = null;
        stmt = null;
        boolean bResultado;
        Vector tipoPagos;
        try {
            System.out.println("Parametros");
            System.out.println("cajaNombre "+cajaNombre+" uid "+uid+" rutaId "+rutaId);

            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_getItinRuta_Prc(?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, uid);            
            stmt.registerOutParameter(3,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(4,OracleTypes.CURSOR);
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(3).getAttributes();
            System.out.println("............. Respuesta SP getItinerariosPorRuta "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }

            if(!respuesta.get(0).equals("0"))
                    return respuesta;
            Vector vec = new Vector();
            vec = new Vector();
            vec.add("origen_id");
            vec.add("origen_clave");
            vec.add("origen");
            vec.add("destino_id");
            vec.add("destino_clave");
            vec.add("destino");
            vec.add("ruta_id");
            vec.add("tramo_id");
            respuesta.add(getCursor(4,vec, "getItinerarios"));
            stmt.close();
            System.out.println("............. Respuesta Facade getItinerariosPorRuta => "+respuesta+" .............");
            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade getItinerariosPorRuta => "+respuesta+"+++++++++++++++++++++");
            }
         System.out.println("----------------------  Termina Facade getItinerariosPorRuta ----------------------");
         return respuesta;
    }

    public Vector getMapaAsientos(String cajaNombre,String uid, String claveCorrida, String plantillaId, String origen){
        System.out.println("----------------------  Inicia Facade getMapaAsientos ----------------------");
        Vector respuesta=new Vector();
        Vector itinerarios;
        cnx = null;
        stmt = null;
        boolean bResultado;
        Vector tipoPagos;
        try {
            System.out.println("Parametros");
            System.out.println("cajaNombre "+cajaNombre+" uid "+uid+" claveCorrida "+claveCorrida+" plantillaId "+plantillaId);

            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_MAPAASIENTOS_Prc(?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, uid);
            ((OraclePreparedStatement)stmt).setString(3, claveCorrida);
            ((OraclePreparedStatement)stmt).setString(4, plantillaId);
            ((OraclePreparedStatement)stmt).setString(5, origen);
            stmt.registerOutParameter(6,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(6).getAttributes();
            System.out.println("............. Respuesta SP getMapaAsientos "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            stmt.close();
            System.out.println("............. Respuesta Facade getMapaAsientos => "+respuesta+" .............");

            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade getMapaAsientos => "+respuesta+"+++++++++++++++++++++");
            }
         System.out.println("----------------------  Termina Facade getMapaAsientos ----------------------");
         return respuesta;
    }

    public Vector getOcuparAsientos(String cajaNombre,String uid, String claveCorrida, String mapaAsientos, String mapaAsientosPasajero, String Origen, String numeroUsuario, String modo){
        System.out.println("----------------------  Inicia Facade getOcuparAsientos ----------------------");
        Vector respuesta=new Vector();
        Vector itinerarios;
        cnx = null;
        stmt = null;
        boolean bResultado;
        Vector tipoPagos;
        try {
            System.out.println("Parametros");
            System.out.println("cajaNombre "+cajaNombre+" uid "+uid+" claveCorrida "+claveCorrida+" mapaAsientos "+mapaAsientos
                    +" mapaAsientosPasajero "+mapaAsientosPasajero+" Origen "+Origen+" numeroUsuario "+(numeroUsuario.length() == 0 ? "no hay" : numeroUsuario)+" modo "+modo);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_ocupaAsientos_prc(?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, uid);
            ((OraclePreparedStatement)stmt).setString(3, claveCorrida);
            ((OraclePreparedStatement)stmt).setString(4, mapaAsientos);
            ((OraclePreparedStatement)stmt).setString(5, mapaAsientosPasajero);
            ((OraclePreparedStatement)stmt).setString(6, Origen);
            ((OraclePreparedStatement)stmt).setString(7, (numeroUsuario.length() == 0 ? "no hay" : numeroUsuario));
            ((OraclePreparedStatement)stmt).setString(8, modo);
            stmt.registerOutParameter(9,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(9).getAttributes();
            System.out.println("............. Respuesta SP getOcuparAsientos "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            stmt.close();
            System.out.println("............. Respuesta Facade getOcuparAsientos => "+respuesta+" .............");

            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade getOcuparAsientos => "+respuesta+"+++++++++++++++++++++");
            }
        System.out.println("----------------------  Termina Facade getOcuparAsientos ----------------------");
        return respuesta;
    }

    public Vector venderBoleto(String[] boletos, String rutaId, String uid, String boletoAbierto, String numeroUsuario,String contraseña,String caja){
        System.out.println("----------------------  Inicia Facade venderBoleto ----------------------");
        String valor=null;
        String ref=null;
        cnx = null;
        Object[] res = new Object[2];
        stmt = null;
        Vector respuesta = null;
        boolean bResultado = true;
        System.out.println("Parametros");
        System.out.println("boletos "+boletos +" boletos.length() "+boletos.length+ " rutaId "+rutaId+" uid "+uid + "boletoAbierto "+boletoAbierto
                +" numeroUsuario "+((numeroUsuario.length() == 0) ? "no hay":numeroUsuario)
                +"contraseña "+((contraseña.length() == 0) ? "no hay":contraseña)+" caja "+caja);
        for(int k = 0; k < boletos.length; k++)
                System.out.println("boletos "+boletos[k]);
        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_VENDER_MOBILE_PKG.vendERMobile_venderBol2_Prc(?,?,?,?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ArrayDescriptor desc = ArrayDescriptor.createDescriptor("BOLETOS_COLLECTION_STR_TYPE", stmt.getConnection());
            ARRAY arrayBoletos = new ARRAY(desc, stmt.getConnection(), boletos);
            ((OraclePreparedStatement)stmt).setString(1, uid);
            ((OraclePreparedStatement)stmt).setString(2, ((numeroUsuario.length() == 0) ? "no hay":numeroUsuario));
            ((OraclePreparedStatement)stmt).setString(3, ((contraseña.length() == 0) ? "no hay":contraseña));
            ((OraclePreparedStatement)stmt).setString(4, caja);
            ((OraclePreparedStatement)stmt).setString(5, rutaId);
            ((OraclePreparedStatement)stmt).setArray(6, arrayBoletos);
            stmt.registerOutParameter(7,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(8,OracleTypes.ARRAY, "FOLIO_BOL_COLLECTION_MV_TYPE");
            bResultado=stmt.execute();
            respuesta = new Vector();
            Object[] attrs=stmt.getSTRUCT(7).getAttributes();
            System.out.println("............. Respuesta SP venderBoleto "+(String)attrs[0]+","+(String)attrs[1]+","+attrs[2]+","+attrs[3]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            
            //System.out.println("respuesta antes de obtener folios "+respuesta);
            Array array = (ARRAY) ((OracleCallableStatement)stmt).getOracleObject(8);
            ResultSet rs=array.getResultSet();

            Vector registro = new Vector();
            Vector collection = new Vector();

            while(rs.next()){
                STRUCT obj= (STRUCT)rs.getObject(2);
                Object[] attrs1=obj.getAttributes();
                registro = new Vector();
                registro.add(attrs1[0]); //numero de Asiento
                registro.add(attrs1[1]); //folio boleto
                registro.add(attrs1[2]); //folio preimpreso
                registro.add(attrs1[3]); //nombre pasajero
                registro.add(attrs1[4]); //ciudad venta
                //VAGL17092014 se agrega el texto para el codigo de barras
                //dividio en dos campos ya que cadacampo solo tiene 30 caracteres
                registro.add(attrs1[5]); //Adicional1
                registro.add(attrs1[6]); //Adicional2
                //System.out.println("registro "+registro);
                collection.add(registro);
            }
            //System.out.println("collection: "+collection);
                        
            respuesta.add(collection);
            
            System.out.println("............. Respuesta Facade venderBoleto => "+respuesta+" .............");
            stmt.close();
            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade venderBoleto => "+respuesta+"+++++++++++++++++++++");
            }
        System.out.println("----------------------  Termina Facade venderBoleto ----------------------");
        return respuesta;
    }

    public Vector cancelarBoleto(String uid, String numeroUsuario, String contraseña, String caja, String corteId, String asientos, String foliosPreimpresos, String unfolio, String  motivoCancelacion){
        System.out.println("----------------------  Inicia Facade cancelarBoleto ----------------------");
        String valor=null;
        String ref=null;
        cnx = null;
        Object[] res = new Object[2];
        stmt = null;
        Vector respuesta = null;
        boolean bResultado = true;
        System.out.println("Parametros");
        System.out.println("uid "+uid +" numeroUsuario "+numeroUsuario+" contraseña "+contraseña+" caja "+caja+" corteId "+corteId+" asientos "+
                            asientos.substring(0, asientos.length()-1)+" foliosPreimpresos "+foliosPreimpresos.substring(0, foliosPreimpresos.length()-1)
                            +" motivoCancelacion "+motivoCancelacion);
        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_VENDER_MOBILE_PKG.vendERMobile_cancelaBoleto_Prc(?,?,?,?,?,?,?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, uid);
            ((OraclePreparedStatement)stmt).setString(2, (numeroUsuario.length() == 0) ? "no hay" :numeroUsuario);
            ((OraclePreparedStatement)stmt).setString(3, (contraseña.length() == 0) ? "no hay" :contraseña);
            ((OraclePreparedStatement)stmt).setString(4, caja);
            ((OraclePreparedStatement)stmt).setString(5, corteId);
            ((OraclePreparedStatement)stmt).setString(6, asientos.substring(0, asientos.length()-1));
            ((OraclePreparedStatement)stmt).setString(7, foliosPreimpresos.substring(0, foliosPreimpresos.length()-1));
            ((OraclePreparedStatement)stmt).setString(8, motivoCancelacion);
            stmt.registerOutParameter(9,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(10,OracleTypes.CLOB);
            stmt.registerOutParameter(11,OracleTypes.CLOB);
            bResultado=stmt.execute();
            respuesta = new Vector();
            Object[] attrs=stmt.getSTRUCT(9).getAttributes();
            System.out.println("............. Respuesta SP cancelarBoleto "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            respuesta.add(""+stmt.getString(10));
            respuesta.add(""+stmt.getString(11));
            System.out.println("............. Respuesta Facade cancelarBoleto => "+respuesta+" .............");
            stmt.close();
            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade cancelarBoleto => "+respuesta+"+++++++++++++++++++++");
            }
        System.out.println("----------------------  Termina Facade cancelarBoleto ----------------------");
        return respuesta;
    }

    public Vector transferirBoleto(String uid, String numeroUsuario,String contraseña, String caja, String corteId, String foliosPreimpresos,String asientos, String asientosDestino, String origen, String destino, String servicio, String empresa, String claveCorrida, String importe, String tipoPasajero){
        System.out.println("----------------------  Inicia Facade transferirBoleto ----------------------");
        String valor=null;
        String ref=null;
        cnx = null;
        Object[] res = new Object[2];
        stmt = null;
        Vector respuesta = null;
        boolean bResultado = true;
        System.out.println("Parametros");
        System.out.println("uid "+uid +" numeroUsuario "+((numeroUsuario.length() == 0) ? "no hay" :numeroUsuario)+" contraseña "+((contraseña.length() == 0) ? "no hay" :contraseña)+" caja "+caja+" corteId "+corteId+" foliosPreimpresos "+foliosPreimpresos +" asientos "+
                            asientos+ " asientosDestino "+ asientosDestino+" origen "+origen+" destino "+destino+ "servicio "+servicio+ " empresa "+empresa+ " importe "+importe
                            +" claveCorrida "+claveCorrida+" tipoPasajero "+tipoPasajero);
        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_VENDER_MOBILE_PKG.vendERMobile_CambioHorario_prc(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, uid);
            ((OraclePreparedStatement)stmt).setString(2, (numeroUsuario.length() == 0) ? "no hay" :numeroUsuario);
            ((OraclePreparedStatement)stmt).setString(3, (contraseña.length() == 0) ? "no hay" :contraseña);
            ((OraclePreparedStatement)stmt).setString(4, caja);
            ((OraclePreparedStatement)stmt).setString(5, corteId);
            ((OraclePreparedStatement)stmt).setString(6, foliosPreimpresos);
            ((OraclePreparedStatement)stmt).setString(7, asientos);
            ((OraclePreparedStatement)stmt).setString(8, asientosDestino);
            ((OraclePreparedStatement)stmt).setString(9, origen);
            ((OraclePreparedStatement)stmt).setString(10, destino);
            ((OraclePreparedStatement)stmt).setString(11, servicio);
            ((OraclePreparedStatement)stmt).setString(12, empresa);
            ((OraclePreparedStatement)stmt).setString(13, importe);
            ((OraclePreparedStatement)stmt).setString(14, claveCorrida);
            ((OraclePreparedStatement)stmt).setString(15, tipoPasajero);

            stmt.registerOutParameter(16,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(17,OracleTypes.CLOB);
            stmt.registerOutParameter(18,OracleTypes.CLOB);
            stmt.registerOutParameter(19,OracleTypes.CLOB);
            bResultado=stmt.execute();
            respuesta = new Vector();
            Object[] attrs=stmt.getSTRUCT(16).getAttributes();
            System.out.println("............. Respuesta SP transferirBoleto "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            System.out.println(" RespuestaCompleta: ");
            for (int i = 0; i < attrs.length; i++) {
                System.out.println("  attrs["+i+"]: "+attrs[i]);
                respuesta.add(attrs[i]);
            }
            System.out.println("Folios Preimpresos: "+stmt.getString(17));
            System.out.println("Folios Boletos: "+stmt.getString(18));
            System.out.println("Codigos de Barras: "+stmt.getString(19));
            respuesta.add(""+stmt.getString(17));//Folios Preimpresos
            respuesta.add(""+stmt.getString(18));//Folios Boletos
            respuesta.add(""+stmt.getString(19));//Codigos de Barras
            System.out.println("............. Respuesta Facade transferirBoleto => "+respuesta+" .............");
            stmt.close();
            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade transferirBoleto => "+respuesta+"+++++++++++++++++++++");
            }
        System.out.println("----------------------  Termina Facade transferirBoleto ----------------------");
        return respuesta;
    }

    public Vector abrirCerrarVentaAbordo(String uid, String numeroUsuario, String folioTarjetaViaje, String numAutobus, String claveOperador, String claveModo){
        System.out.println("************** Inicia logOut *******************");
        String valor=null;
        String ref=null;
        cnx = null;
        Object[] res = new Object[2];
        stmt = null;
        Vector respuesta = null;
        boolean bResultado = true;
        System.out.println("uid "+uid);
        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_VENDER_MOBILE_PKG.vendERMobile_ventaAbordo_Prc(?,?,?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, uid);
            ((OraclePreparedStatement)stmt).setString(2, numeroUsuario);
            ((OraclePreparedStatement)stmt).setString(3, folioTarjetaViaje);
            ((OraclePreparedStatement)stmt).setString(4, numAutobus);
            ((OraclePreparedStatement)stmt).setString(5, claveOperador);
            ((OraclePreparedStatement)stmt).setString(6, claveModo);
            stmt.registerOutParameter(7,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            bResultado=stmt.execute();
            respuesta = new Vector();
            Object[] attrs=stmt.getSTRUCT(7).getAttributes();
            System.out.println((String)attrs[0]+","+(String)attrs[1]);
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            System.out.println("respuesta "+respuesta);
            stmt.close();
            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("Excepcion Bean abrirCerrarVentaAbordo - "+respuesta);
            }
        return respuesta;
    }

    public Vector logOut(String cajaNombre, String uid){
        System.out.println("----------------------  Inicia Facade logOut ----------------------");
        String valor=null;
        String ref=null;
        cnx = null;
        Object[] res = new Object[2];
        stmt = null;
        Vector respuesta = null;
        boolean bResultado = true;
        System.out.println("Parametros");
        System.out.println("uid "+uid);
        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_VENDER_MOBILE_PKG.vendERMobile_logOut2_Prc(?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(2, uid);
            stmt.registerOutParameter(3,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            bResultado=stmt.execute();
            respuesta = new Vector();
            Object[] attrs=stmt.getSTRUCT(3).getAttributes();
            System.out.println("............. Respuesta SP logOut "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            System.out.println("............. Respuesta Facade logOut => "+respuesta+" .............");
            stmt.close();
            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade logOut => "+respuesta+"+++++++++++++++++++++");
            }
        System.out.println("----------------------  Termina Facade logOut ----------------------");
        return respuesta;
    }

    public Vector getFecha(String uid){
        System.out.println("************** Inicia getFecha *******************");
        Vector respuesta=new Vector();
        cnx = null;
        stmt = null;
        boolean bResultado;
        Vector tipoPagos;
        try {
            System.out.println("uid "+uid);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_getfechaHora_Prc(?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, uid);
            stmt.registerOutParameter(2,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(2).getAttributes();
            System.out.println((String)attrs[0]+","+(String)attrs[1]);
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            stmt.close();
            System.out.println("Bean  - "+respuesta);

            if(cnx!=null) cnx.close();
            return respuesta;
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
            } finally{
                cnx=null;
                //ex.printStackTrace();
                System.out.println("Excepcion Bean getFecha - "+respuesta);
                return respuesta;
            }
    }

    public Vector getTipoPasajeroNoPermitido(String uid, String origenId, String corridaId){
        System.out.println("************** Inicia getTipoPasajeroNoPermitido *******************");
        Vector respuesta=new Vector();
        cnx = null;
        stmt = null;
        boolean bResultado;
        Vector tipoPagos;
        try {
            System.out.println("uid "+uid+" origenId "+origenId+" corridaId "+corridaId);
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "XER_VENDER_MOBILE_PKG.vendERMobile_TiposPNoP_Prc(?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, uid);
            ((OraclePreparedStatement)stmt).setString(2, origenId);
            ((OraclePreparedStatement)stmt).setString(3, corridaId);
            stmt.registerOutParameter(4,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            bResultado=stmt.execute();
            Object[] attrs=stmt.getSTRUCT(4).getAttributes();
            System.out.println((String)attrs[0]+","+(String)attrs[1]);
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            stmt.close();
            System.out.println("Bean  - "+respuesta);

            if(cnx!=null) cnx.close();
            return respuesta;
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
            } finally{
                cnx=null;
                //ex.printStackTrace();
                System.out.println("Excepcion Bean getFecha - "+respuesta);
                return respuesta;
            }
    }

    public Vector canjeBoletoAbierto(String uid, String numeroUsuario, String contraseña, String caja, String corteId, String foliosPreimpresos, String asientosDestino, String origen, String destino, String servicio, String empresa, String claveCorrida, String tipoPasjaero, String importeBoleto){
        System.out.println("----------------------  Inicia Facade canjeBoletoAbierto ----------------------");
        String valor=null;
        String ref=null;
        cnx = null;
        Object[] res = new Object[2];
        stmt = null;
        Vector respuesta = null; 
        boolean bResultado = true;
        System.out.println("Parametros");
        System.out.println("uid "+uid +" numeroUsuario "+((numeroUsuario.length() == 0) ? "no hay" :numeroUsuario)+" numeroUsuario "+((contraseña.length() == 0) ? "no hay" :contraseña)+"caja "+caja+" corteId "+corteId+" foliosPreimpresos "+foliosPreimpresos +
                            " tipos "+" asientosDestino "+ asientosDestino+" origen "+origen+" destino "+destino+ "servicio "+servicio+ " empresa "+empresa
                            +" claveCorrida "+claveCorrida+" tipoPasjaero "+tipoPasjaero+" importeBoleto "+importeBoleto);
        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_VENDER_MOBILE_PKG.vendERMobile_CanjeBA_prc(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, uid);
            ((OraclePreparedStatement)stmt).setString(2, (numeroUsuario.length() == 0) ? "no hay" :numeroUsuario);
            ((OraclePreparedStatement)stmt).setString(3, (contraseña.length() == 0) ? "no hay" :contraseña);
            ((OraclePreparedStatement)stmt).setString(4, caja);
            ((OraclePreparedStatement)stmt).setString(5, corteId);
            ((OraclePreparedStatement)stmt).setString(6, foliosPreimpresos);
            ((OraclePreparedStatement)stmt).setString(7, asientosDestino);
            ((OraclePreparedStatement)stmt).setString(8, origen);
            ((OraclePreparedStatement)stmt).setString(9, destino);
            ((OraclePreparedStatement)stmt).setString(10, servicio);
            ((OraclePreparedStatement)stmt).setString(11, empresa);
            ((OraclePreparedStatement)stmt).setString(12, claveCorrida);
            ((OraclePreparedStatement)stmt).setString(13, tipoPasjaero);
            ((OraclePreparedStatement)stmt).setString(14, importeBoleto);

            stmt.registerOutParameter(15,OracleTypes.STRUCT, "VENDER_VECTOR_TYPE");
            stmt.registerOutParameter(16,OracleTypes.CLOB);
            stmt.registerOutParameter(17,OracleTypes.CLOB);
            stmt.registerOutParameter(18,OracleTypes.CLOB);
            bResultado=stmt.execute();
            respuesta = new Vector();
            Object[] attrs=stmt.getSTRUCT(15).getAttributes();
            System.out.println("............. Respuesta SP canjeBoletoAbierto "+(String)attrs[0]+","+(String)attrs[1]+" .............");
            for (int i = 0; i < attrs.length; i++) {
                respuesta.add(attrs[i]);
            }
            respuesta.add(""+stmt.getString(16));//Folio Preimpresos
            respuesta.add(""+stmt.getString(17));//Folios
            respuesta.add(""+stmt.getString(18));//Codigo de Barras
            System.out.println("............. Respuesta Facade canjeBoletoAbierto => "+respuesta+" .............");
            stmt.close();
            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade canjeBoletoAbierto => "+respuesta+"+++++++++++++++++++++");
            }
        System.out.println("----------------------  Termina Facade canjeBoletoAbierto ----------------------");
        return respuesta;
    }


    /******************** Operaciones Extras **************************/
    private void getCursorTodos(int indice, Vector valor){
        System.out.println("indice "+indice);
        Vector temp = null;
        try {
            rs = (ResultSet) stmt.getObject(indice);
            while(rs.next()){
            temp = new Vector();
            temp.add(rs.getString("idParametro"));
            temp.add(rs.getString("parametroCodigo"));
            temp.add(rs.getString("parametroNombre"));
            System.out.println("temp.get(1).toString() "+temp.get(1).toString());
            if(temp.get(1).toString().equals("P_LIMEFEREC"))
                temp.add(valor.get(0).toString());
            else if(temp.get(1).toString().equals("P_LIMEFENVT"))
                temp.add(valor.get(1).toString());
            else if(temp.get(1).toString().equals("P_LIMFONMAX"))
                temp.add(valor.get(2).toString());
            else
                temp.add(rs.getString("parametroValor"));
            temp.add(rs.getString("descripcion"));
            temp.add(rs.getString("idParametroValor"));
            temp.add(rs.getString("parametroTipo"));
            temp.add(rs.getString("fechaDesde"));
            temp.add(rs.getString("fechaHasta"));
            temp.add(rs.getString("adicional1"));
            temp.add(rs.getString("adicional2"));
            temp.add(rs.getString("adicional3"));
            temp.add(rs.getString("adicional4"));
            temp.add(rs.getString("adicional5"));
            regresoTodos.add(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private Vector getCursor(int indice, Vector vec, String funcion){
        Vector temp = null;
        Vector respuesta = new Vector();
        try {
            rs = (ResultSet) stmt.getObject(indice);
             while(rs.next()){
                temp = new Vector();
                for(int k = 0; k < vec.size(); k++)
                    temp.add(rs.getString(vec.get(k).toString()));
                if(temp.contains("P_LIMEFEREC"))
                    temp.setElementAt(valores.get(0).toString(),3);
                if(temp.contains("P_LIMEFENVT"))
                    temp.setElementAt(valores.get(1).toString(),3);
                if(temp.contains("P_LIMFONMAX"))
                    temp.setElementAt(valores.get(2).toString(),3);
                respuesta.add(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            respuesta = new Vector();
            respuesta.add("getCursor");
            respuesta.add(ex.getMessage()+"\n en la funcion "+funcion);
        }
        if(respuesta.size() == 0)
            respuesta.add("reinicio");
        return respuesta;
    }

    private Vector getCursor(int indice, Vector vec, String funcion, String parametro){
        Vector temp = null;
        Vector respuesta = new Vector();
        try {
            rs = (ResultSet) stmt.getObject(indice);
             while(rs.next()){
                temp = new Vector();
                for(int k = 0; k < vec.size(); k++)
                    temp.add(rs.getString(vec.get(k).toString()));
                respuesta.add(temp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            respuesta = new Vector();
            respuesta.add("getCursor "+parametro);
            respuesta.add(ex.getMessage()+"\n en la funcion "+funcion);
        }
        if(respuesta.size() == 0)
            respuesta.add("reinicio "+parametro);
        return respuesta;
    }

    public Vector buscarDatosCorrita(String origen, String claveCorrida){
        System.out.println("----------------------  Inicia Facade buscarDatosCorrita ----------------------");
        String valor=null;
        cnx = null;
        stmt = null;
        Vector respuesta = null;
        boolean bResultado = true;
        System.out.println("Parametros");
        System.out.println("origen "+origen +" claveCorrida "+claveCorrida);
        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_VENDER_MOBILE_PKG.vendERMobile_datosCorrida_prc(?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
            ((OraclePreparedStatement)stmt).setString(1, origen);
            ((OraclePreparedStatement)stmt).setString(2, claveCorrida);
            stmt.registerOutParameter(3,OracleTypes.VARCHAR);
            stmt.registerOutParameter(4,OracleTypes.VARCHAR);
            stmt.registerOutParameter(5,OracleTypes.VARCHAR);
            bResultado=stmt.execute();
            respuesta = new Vector();
            respuesta.add(""+stmt.getString(3));
            respuesta.add(""+stmt.getString(4));
            respuesta.add(""+stmt.getString(5));
            System.out.println("............. Respuesta Facade buscarDatosCorrita => "+respuesta+" .............");
            stmt.close();
            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade buscarDatosCorrita => "+respuesta+"+++++++++++++++++++++");
            }
        System.out.println("----------------------  Termina Facade buscarDatosCorrita ----------------------");
        return respuesta;
    }

    public Vector despacharTarjetaViaje(String origen, String claveCorrida, String empresa,String operador, String autobus, String usuario){

        System.out.println("----------------------  Inicia Facade despacharTarjetaViaje ----------------------");
        String valor=null;
        cnx = null;
        stmt = null;
        Vector respuesta = null;
        boolean bResultado = true;
        System.out.println("Parametros");
        System.out.println("origen: "+origen +" claveCorrida: "+claveCorrida+" empresa: "+empresa+" operador: "+operador+" autobus: "+autobus+" usuario: "+usuario);
        
        try {
            
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_VENDER_MOBILE_PKG.vendERMobile_despachoTar_prc(?,?,?,?,?,?,?,?,?,?,?,?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK; " +
                      "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);
              
            ((OraclePreparedStatement)stmt).setString(1, origen);
            ((OraclePreparedStatement)stmt).setString(2, claveCorrida);
            ((OraclePreparedStatement)stmt).setString(3, empresa);
            ((OraclePreparedStatement)stmt).setString(4, operador);
            ((OraclePreparedStatement)stmt).setString(5, autobus);
            ((OraclePreparedStatement)stmt).setString(6, usuario);
            stmt.registerOutParameter(7,OracleTypes.VARCHAR);
            stmt.registerOutParameter(8,OracleTypes.VARCHAR);
            stmt.registerOutParameter(9,OracleTypes.VARCHAR);
            stmt.registerOutParameter(10,OracleTypes.VARCHAR);
            stmt.registerOutParameter(11,OracleTypes.VARCHAR);
            stmt.registerOutParameter(12,OracleTypes.VARCHAR);
            
            bResultado=stmt.execute();
            
            respuesta = new Vector();
            
            respuesta.add(""+stmt.getString(7));
            respuesta.add(""+stmt.getString(8));
            respuesta.add(""+stmt.getString(9));
            respuesta.add(""+stmt.getString(10));
            respuesta.add(""+stmt.getString(11));
            respuesta.add(""+stmt.getString(12));
         
            System.out.println("............. Respuesta Facade despacharTarjetaViaje => "+respuesta+" .............");
            //stmt.close();
            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade despacharTarjetaViaje => "+respuesta+"+++++++++++++++++++++");
            }
        System.out.println("----------------------  Termina Facade despacharTarjetaViaje ----------------------");
        return respuesta;
        //return new Vector();
    }



    //************** TIEMPO  Aire
    
     public  String  ventaTaePrincipal(String Compañía, String Usuario, String Password, String Carrier, String Telefono, String Cantidad, String TipoVenta, String No_Usuario, String Usuario_id, String Caja, String Corte_id,String Ciudad, String Canal ){
        System.out.println("*** ININ: ventaTaePrincipal( "+Compañía+", "+Usuario+", "+Password+", "+Carrier+", "+Telefono+", "+ Cantidad+", "+TipoVenta+", "+No_Usuario+", "+Usuario_id+", "+Caja+", "+Corte_id+", "+Ciudad+", "+Canal+" ) ***");
        
        String StrResult="";
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
            ((OraclePreparedStatement)stmt).setString(5, Telefono);
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
             
            StrResult =P_Exito;    //","+P_Transactionno_Out+","+P_Referenceid+","+P_Telcelid+","+P_Responsetelcel+","+P_Responsetime+","+P_Status+","+P_Store_Id+","+P_Terminal+","+P_Amount_Out+","+P_Phone_Out+","+P_Errormsg;     
       if (stmt != null) stmt.close();
          stmt = null;
        if(cnx!=null) cnx.close();
          cnx=null;
        }catch (Exception ex) {
           System.out.println("INCORRECTO");
           
           ex.printStackTrace();
        
           
        }
          
            System.out.println("*** FIN: .ventaTaePrincipal() ***");
        
        
        return StrResult.trim();

    }
     
    
      public String obtener_valor_parametro(String parametro, long  EMPRESA_ID, long SERVICIO_ID, long TERMINAL_ID, long RUTA_ID, long CAJA_ID){
        String valor=null;
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        
        System.out.println("obtener_valor_parametro.parametro:  "+parametro);
        System.out.println("obtener_valor_parametro.EMPRESA_ID:  "+EMPRESA_ID);
        System.out.println("obtener_valor_parametro.SERVICIO_ID:  "+SERVICIO_ID);
        System.out.println("obtener_valor_parametro.TERMINAL_ID:  "+TERMINAL_ID);
        System.out.println("obtener_valor_parametro.RUTA_ID:  "+RUTA_ID);
        System.out.println("obtener_valor_parametro.CAJA_ID:  "+CAJA_ID);
        
        try {
            cnx = dataSource.getConnection();
                   String q1 = 
                    "BEGIN "+
                      "XER_TMS_PKG2.TMS_OBTENER_PARAM_MISC_PRC(?, ?, ?, ?, ?, ?, ?); " +
                      "COMMIT; " +
                      "EXCEPTION " +
                      "WHEN OTHERS THEN " +
                      "ROLLBACK;" +
                      "RAISE; "+
                    "END;";
            
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.registerOutParameter(7,java.sql.Types.VARCHAR);
            stmt.setString(1,parametro);
            stmt.setLong(2, EMPRESA_ID);
            stmt.setLong(3, SERVICIO_ID);
            stmt.setLong(4, TERMINAL_ID);
            stmt.setLong(5, RUTA_ID);
            stmt.setLong(6, CAJA_ID);
            // -1X reg ocupado, 13S CON EL QUE FALLO; 000; 00X reg ocupado
            stmt.execute();
            
            valor = stmt.getString(7);
            stmt.close();
            if(cnx!=null) cnx.close();
            
            if(valor==null) return "";
            else
              System.out.println("obtener_valor_parametro  =====> Valor "+valor);

        } catch (SQLException ex){
            try {
                valor = stmt.getString(7);
                stmt.close();
                if(cnx!=null) cnx.close();
            } catch (SQLException ex1) {
                ex1.printStackTrace();
            }
            finally{
                cnx=null;
                ex.printStackTrace();
                if(valor==null ) return "";
                return "";  
            }
        }        
        return valor;
    }
     
     
      
    public Vector VentaTiempoAire( String Compania, String Telefono, 
                                   String Cantidad, String TipoVenta, String No_Usuario,
                                   String Caja, String Corte_id, String Canal)

    {
      long RUTA_ID=0, CAJA_ID =0, EMPRESA_ID =0, SERVICIO_ID =0;
      Vector Vrespue = new Vector();
      String status  = "1";
      String mensaje = "";
      String Usuario_id;
      
      Vector vterminal = getTerminal();

      if (vterminal == null)
      {
        Vrespue.addElement("1");
        Vrespue.addElement("Error al buscar los datos de la terminal.");
        return Vrespue;
      }
      Usuario_id = getUsuarioID(No_Usuario);

      if (Usuario_id == null || Usuario_id.length() <=0 )
      {
        Vrespue.addElement("1");
        Vrespue.addElement("Verifique el no de usuario.");
        return Vrespue;
      }

      String TERMINAL_ID = vterminal.elementAt(0).toString();
      String Ciudad  = vterminal.elementAt(1).toString();

      String tae_usuario= null,  tae_compania =  null , tae_pass = null;
      
      tae_compania = obtener_valor_parametro("P_TAE_COMPANIA", EMPRESA_ID, SERVICIO_ID, Long.parseLong( TERMINAL_ID), RUTA_ID, CAJA_ID);
        
       if (tae_compania == null || tae_compania.length()<=0 )
       {
         status ="1";
         mensaje ="El parametro Compania no existe  o esta mal configurado. Consulte al administrador.";
        }
       else{
              tae_usuario  = obtener_valor_parametro("P_TAE_USUARIO", EMPRESA_ID, SERVICIO_ID, Long.parseLong( TERMINAL_ID), RUTA_ID, CAJA_ID);
               if (tae_usuario == null || tae_usuario.length()<=0 )
               {
                 status ="1";
                 mensaje ="El parametro Compania no existe o esta mal configurado. Consulte al administrador.";
               }

               else{
                  tae_pass     = obtener_valor_parametro("P_TAE_PASSWORD", EMPRESA_ID, SERVICIO_ID, Long.parseLong( TERMINAL_ID), RUTA_ID, CAJA_ID);
                   if (tae_usuario == null || tae_usuario.length()<=0 )
                   {
                     status ="1";
                     mensaje ="El parametro password no exite o esta mal configurado. Consulte al administrador.";
                   }     
               }
               
       }  
       
      if ( tae_usuario!= null && tae_compania !=  null && tae_pass != null &&
           tae_usuario.length()> 0 &&  tae_compania .length()> 0 &&  tae_pass.length()> 0){
           System.out.println("Llegando a VentaPrincipal ");

           mensaje=ventaTaePrincipal( tae_usuario,tae_compania, tae_pass, Compania,  Telefono,  Cantidad,  TipoVenta,  No_Usuario,  Usuario_id,  Caja,  Corte_id, Ciudad,  Canal );

           System.out.println("SALIDA "+mensaje);

           if (mensaje.equalsIgnoreCase("TRUE") )
               {
                 status ="0";
                 mensaje ="La recarga fué  realizada  satisfactoriamente.";
               }
          else  
           {  
               status = "1";    
              mensaje ="La recarga no pudo ser realizada.";
          }
        }   // if ( tae_usuario.length()> 0 &&  tae_compania .length()> 0 &&  tae_pass.length()> 0)


       System.out.println("VentaTiempoAire status "+status);
       System.out.println("VentaTiempoAire mensaje "+mensaje);
       
       Vrespue.addElement(status);    
       Vrespue.addElement(mensaje);
       
       
     return Vrespue;
    }  //VentaTiempoAire
       

     public Vector getTerminal() {
        Vector Vresult =  null;
        String Query = "SELECT BDC.TERMINAL_ID, BDC.NOMBRE_TERMINAL FROM TMS_BASE_DATOS_CONFIG_TBL BDC "+
                       " WHERE BDC.ESQUEMA_PROPIO='S' " ;

        try{
            Vresult= (Vector)em.createNativeQuery(Query).getResultList();

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        System.out.println("getTerminal getterminal --> "+(Vector)Vresult.elementAt(0));
        return (Vector)Vresult.elementAt(0);
    }

  public String  getUsuarioID(String NoUsuario) {
        String UsuarioID =  null;
        String Query = " SELECT USUARIO_ID FROM TMS_USUARIOS_TBL U "+
                       " WHERE U.USUARIO_NUMERO='"+NoUsuario+"' " ;
         System.out.println("getusuario --> "+Query);
        try{
            UsuarioID= ((Vector)em.createNativeQuery(Query).getSingleResult()).elementAt(0).toString();

        System.out.println("getusuario --> "+UsuarioID);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return UsuarioID;
    }

  /***
   * Método que registra una sesión actividad en la base de datos
   * @param opcion Indice de la sesión actividad
   * @param valor Valor a registrar
   * @param valorOpcional Valor opcional, aplica para foliado y refoliado
   * @param cajaNombre Nombre de la caja
   * @param uid Identificador de sesión
   * @return Un Vector que contiene un identificador númerico y un mensaje
   */
  public Vector registrarSesionActividad(int opcion, String valor, String valorOpcional, String cajaNombre, String uid){
        System.out.println("----------------------  Inicia Facade registrarSesionActividad ----------------------");
        cnx = null;
        stmt = null;
        Vector respuesta = null;
        boolean bResultado = true;
        System.out.println("Parametros");
        System.out.println("opcion " + opcion + " uid "+uid + " valor " + valor + " valorOpc " + valorOpcional + " caja " + cajaNombre);
        try {
            cnx = dataSource.getConnection();
                   String q1 =
                    "BEGIN "+
                      "XER_VENDER_MOBILE_PKG.VENDERMOBILE_REG_ACTIVIDAD_PCR(?,?,?,?,?,?,?); " +
                    "END;";
            stmt = (OracleCallableStatement) cnx.prepareCall(q1);

            ((OraclePreparedStatement)stmt).setLong(1, opcion);
            ((OraclePreparedStatement)stmt).setString(2, cajaNombre);
            ((OraclePreparedStatement)stmt).setString(3, uid);
            ((OraclePreparedStatement)stmt).setString(4, valor);
            ((OraclePreparedStatement)stmt).setString(5, valorOpcional);
            stmt.registerOutParameter(6,Types.NUMERIC);
            stmt.registerOutParameter(7,Types.VARCHAR);
            bResultado=stmt.execute();
            respuesta = new Vector();
            respuesta.add(stmt.getLong(6));
            respuesta.add(stmt.getString(7));
            System.out.println("............. Respuesta SP registrarSesionActividad "+ respuesta.get(0).toString()+","+respuesta.get(1).toString()+" .............");

            stmt.close();
            if(cnx!=null) cnx.close();
        } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta=null;
                cnx=null;
                System.out.println("+++++++++++++++++++++ Excepcion Facade registrarSesionActividad => "+respuesta+"+++++++++++++++++++++");
            }
        System.out.println("----------------------  Termina Facade registrarSesionActividad ----------------------");
        return respuesta;
    }

}  // Clase

