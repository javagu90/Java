/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.solicitudes;


import WS_CONTROL.entidades.ErClientesTbl;
import WS_CONTROL.entidades.ErFacturasTbl;
import WS_CONTROL.entidades.ErPagosTbl;
import WS_CONTROL.entidades.ErProductoCliente;
import WS_CONTROL.entidades.ErProductosRefTbl;

import WS_CONTROL.entidades.Cliente;
import WS_CONTROL.entidades.ClienteComisiones;
import WS_CONTROL.entidades.ClienteTransaccion;
import WS_CONTROL.entidades.ERClientesDireccionesTbl;

import WS_CONTROL.entidades.ErPromocionesTbl;

import WS_CONTROL.entidades.ErUsuariosTbl;
import WS_CONTROL.getClientesReq;
import WS_CONTROL.getClientesResp;
import WS_CONTROL.getFacturasReq;
import WS_CONTROL.getFacturasResp;
import WS_CONTROL.getOperacionesClienteReq;
import WS_CONTROL.getOperacionesClienteResp;
import WS_CONTROL.getOperacionesClientesUsuarioReq;
import WS_CONTROL.getOperacionesClientesUsuarioResp;
import WS_CONTROL.getOperacionesFacturaReq;
import WS_CONTROL.getOperacionesFacturaResp;

import WS_CONTROL.entidades.Funcion;
import WS_CONTROL.entidades.PagoFactura;
import WS_CONTROL.entidades.Perfil;

import WS_CONTROL.getEstadoCuentaClienteReq;
import WS_CONTROL.getEstadoCuentaClienteResp;
import WS_CONTROL.getLoginReq;
import WS_CONTROL.getLoginResp;
import WS_CONTROL.getLogoutReq;
import WS_CONTROL.getLogoutResp;
import WS_CONTROL.getOperacionesCreditoClientesReq;
import WS_CONTROL.getOperacionesCreditoClientesResp;
import WS_CONTROL.getOperacionesPagosClienteReq;
import WS_CONTROL.getOperacionesPagosClienteResp;
import WS_CONTROL.getOperacionesPagosFacturasReq;
import WS_CONTROL.getOperacionesPagosFacturasResp;

import WS_CONTROL.getOperacionesPromocionReq;
import WS_CONTROL.getOperacionesPromocionResp;


import WS_CONTROL.getOperacionesUsuarioReq;
import WS_CONTROL.getOperacionesUsuarioResp;
import WS_CONTROL.getPagosReq;
import WS_CONTROL.getPagosResp;
import WS_CONTROL.getProductosClienteReq;
import WS_CONTROL.getProductosClienteResp;

import WS_CONTROL.getPagosFacturaReq;
import WS_CONTROL.getPagosFacturaResp;
import WS_CONTROL.getPerfilesUsuarioReq;
import WS_CONTROL.getPerfilesUsuarioResp;

import WS_CONTROL.getPromocionesReq;
import WS_CONTROL.getPromocionesResp;

import WS_CONTROL.getUsuariosReq;
import WS_CONTROL.getUsuariosResp;


import WS_CONTROL.getRegistraTransaccionesClienteReq;
import WS_CONTROL.getRegistraTransaccionesClienteResp;
import WS_CONTROL.getValidaSesionReq;
import WS_CONTROL.getValidaSesionResp;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;




import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import oracle.jdbc.driver.OracleCallableStatement;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialBlob;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleBlobInputStream;
import oracle.sql.BLOB;
import tms_encriptacion.EncriptarMD5;
/**
 *
 * @author vgonzalez
 */
@Stateless
public class wsControlFacadeBean implements wsControlFacadeBeanRemote {
    private SimpleDateFormat ffh = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private String paquete ="ER_CONTROL_PKG2";

    private String msgSessionNV ="Session ID no valido.";
    private String msgOperacionNV = "Operación Invalida";

    //@PersistenceContext

    
    @PersistenceContext(unitName="ER_WS_CONTROL_EJBPU")
    private EntityManager em;

   @Resource(name = "REP_CONTROL_DB")
    private DataSource dataSource;


   public getLoginResp getLogin(getLoginReq parametros)
    {
      getLoginResp respuesta = new getLoginResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        System.out.println("UsuarioNumero(getLogin) : "+parametros.getUsuarioNumero());
        System.out.println("UsuarioContrasena(getLogin) : "+parametros.getUsuarioContrasena());
        try {
                parametros.setUsuarioContrasena(new EncriptarMD5().encriptar(parametros.getUsuarioContrasena()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        System.out.println("UsuarioContrasena : "+parametros.getUsuarioContrasena());
        System.out.println("NombreEquipo : "+parametros.getNombreEquipo());
        System.out.println("DireccionIP : "+parametros.getDireccionIP());
        System.out.println("DireccionMAC : "+parametros.getDireccionMAC());
        System.out.println("NombreCaja : "+parametros.getNombreCaja());
        System.out.println("SucursalClave : "+parametros.getSucursalClave());
        String param = "^"+parametros.getUsuarioNumero()+"^"+parametros.getUsuarioContrasena()+"^"+parametros.getNombreEquipo()+"^"+parametros.getDireccionIP()+"^"+parametros.getDireccionMAC()+"^"+parametros.getNombreCaja()+"^"+parametros.getSucursalClave();
        System.out.println("param : "+param);
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PKG.get_Login_PRC(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,-1);
            stmt.setString(2, param);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.execute();
            String datos = stmt.getString(3);
            System.out.println("datos: "+datos);
            if (stmt.getString(4).equals("0") && !datos.equals(""))
                respuesta.setSesionId(Long.valueOf(datos));
            respuesta.setOperacionExitosa(stmt.getString(4).equals("0")?true:false);
            respuesta.setErrorCode(stmt.getString(5));
            respuesta.setErrorMsg(stmt.getString(6));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());
            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
    }

   public getValidaSesionResp getValidaSesion(getValidaSesionReq parametros) throws SQLException, Exception
    {
        getValidaSesionResp respuesta = new getValidaSesionResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);
        System.out.println("sesionId : "+parametros.getSesionId());
        System.out.println("canalVenta : "+parametros.getCanalVenta());
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {

            System.out.println("Validando sesion en Control...");
            cnx = dataSource.getConnection();
            String q =
                    "BEGIN "+
                    "ER_CONTROL_PKG.get_validaSesion_PRC (?,?,?,?,?,?,?,?,?,?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK; " +
                    "RAISE; "+
                    "END;";
                stmt = (OracleCallableStatement) cnx.prepareCall(q);
                ((OraclePreparedStatement)stmt).setLong(1,parametros.getSesionId());
                stmt.registerOutParameter(2,OracleTypes.NUMBER);
                stmt.registerOutParameter(3,OracleTypes.VARCHAR);
                stmt.registerOutParameter(4,OracleTypes.VARCHAR);
                stmt.registerOutParameter(5,OracleTypes.NUMBER);
                stmt.registerOutParameter(6,OracleTypes.NUMBER);
                stmt.registerOutParameter(7,OracleTypes.VARCHAR);
                stmt.registerOutParameter(8,OracleTypes.VARCHAR);
                stmt.registerOutParameter(9,OracleTypes.VARCHAR);
                stmt.registerOutParameter(10,OracleTypes.VARCHAR);
                stmt.registerOutParameter(11,OracleTypes.VARCHAR);

                boolean bResultado=stmt.execute();
                System.out.println("P_OPERACION_EXITOSA: "+stmt.getString(8));
                 if (stmt.getString(8).equals("0"))
                 {
                    //corridasReq.setSesionId();
                     System.out.println("P_USUARIO_ID: "+stmt.getInt(2));
                     System.out.println("P_CLIENTE_ID: "+stmt.getString(11));
                     System.out.println("P_USUARIO_NUMERO: "+stmt.getString(3));
                     System.out.println("P_USUARIO_NOMBRE: "+stmt.getString(4));
                     System.out.println("P_CORTETMS: "+stmt.getLong(5));
                     System.out.println("P_CORTEPAQUER: "+stmt.getLong(6));
                     System.out.println("P_SUCURSAL: "+stmt.getString(7));
                     System.out.println("P_OPERACION_EXITOSA: "+stmt.getString(8));
                     System.out.println("P_ERROR_CODE: "+stmt.getString(9));
                     System.out.println("P_ERROR_MSJ: "+stmt.getString(10));

                     respuesta.setUsuarioId(new Long(stmt.getInt(2)));
                     respuesta.setClienteId(new Long(stmt.getInt(11)));
                     //boolean generarCorteTMS = false;
                     if(parametros.getCanalVenta().equals("TMS"))
                     /*{
                         if(stmt.getInt(5)==0 )
                             generarCorteTMS = true;
                         else
                         {
                            //Valida si el corte de TMS asignado a la sesion esta abierto para la venta

                         }
                     }
                     if(generarCorteTMS)*/
                     {
                         System.out.println("Generarndo corte de TMS...");
                        q =
                                            "BEGIN "+
                                            "ER_CONTROL_PKG.get_loginTMS_prc (?,?,?,?,?); " +
                                            "COMMIT; " +
                                            "EXCEPTION " +
                                            "WHEN OTHERS THEN " +
                                            "ROLLBACK; " +
                                            "RAISE; "+
                                            "END;";
                                        stmt = (OracleCallableStatement) cnx.prepareCall(q);
                                        ((OraclePreparedStatement)stmt).setLong(1,parametros.getSesionId());
                                        stmt.registerOutParameter(2,OracleTypes.NUMBER);
                                        stmt.registerOutParameter(3,OracleTypes.VARCHAR);
                                        stmt.registerOutParameter(4,OracleTypes.VARCHAR);
                                        stmt.registerOutParameter(5,OracleTypes.VARCHAR);
                                        bResultado=stmt.execute();
                                        System.out.println("P_OPERACION_EXITOSA: "+stmt.getString(3));
                                         if (stmt.getString(3).equals("0"))
                                         {
                                            respuesta.setCorteId(stmt.getLong(2));
                                            respuesta.setOperacionExitosa(true);
                                            respuesta.setErrorCode(stmt.getString(4));
                                            respuesta.setErrorMsg(stmt.getString(5));
                                            System.out.println("Corte TMS: "+stmt.getLong(2));
                                         }
                                         else
                                         {
                                            respuesta.setOperacionExitosa(false);
                                            respuesta.setErrorCode(stmt.getString(4));
                                            respuesta.setErrorMsg(stmt.getString(5));
                                            return respuesta;
                                         }
                     }
                     else
                     {
                         if(parametros.getCanalVenta().equals("TMS"))
                            respuesta.setCorteId(stmt.getLong(5));

                         if(parametros.getCanalVenta().equals("PAQUER"))
                            respuesta.setCorteId(stmt.getLong(6));


                            respuesta.setOperacionExitosa(true);
                            respuesta.setErrorCode(stmt.getString(9));
                            respuesta.setErrorMsg(stmt.getString(10));
                     }
                 }
                 else
                 {
                    respuesta.setOperacionExitosa(false);
                    respuesta.setErrorCode(stmt.getString(9));
                    respuesta.setErrorMsg(stmt.getString(10));
                    return respuesta;
                 }
           return respuesta;
        }catch (Exception ex) {
           System.out.println("INCORRECTO");
           throw ex;
        }
        finally{
        stmt.close();
        if(cnx!=null) cnx.close();
        }
    }

   public getLogoutResp getLogout(getLogoutReq parametros)
    {
      getLogoutResp respuesta = new getLogoutResp();
      respuesta.setOperacionExitosa(true);
      respuesta.setErrorCode("ER-000");
      respuesta.setErrorMsg("La sesion se cerró correctamente");
        System.out.println("SesionId : "+parametros.getSesionId());
        System.out.println("UsuarioNumero : "+parametros.getUsuarioNumero());
        System.out.println("UsuarioContrasena : "+parametros.getUsuarioContrasena());
        try {
                parametros.setUsuarioContrasena(new EncriptarMD5().encriptar(parametros.getUsuarioContrasena()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        System.out.println("UsuarioContrasena : "+parametros.getUsuarioContrasena());
        String param = "^"+parametros.getUsuarioNumero()+"^"+parametros.getUsuarioContrasena()+"^"+parametros.getSesionId();
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PKG.get_Logout_PRC(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,-1);
            stmt.setString(2, param);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.execute();
            String datos = stmt.getString(3);
            System.out.println("datos: "+datos);
            respuesta.setOperacionExitosa(stmt.getString(4).equals("0")?true:false);
            respuesta.setErrorCode(stmt.getString(5));
            respuesta.setErrorMsg(stmt.getString(6));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());
            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
    }




    public getOperacionesPromocionResp getOperacionesPromocion(getOperacionesPromocionReq parametros)
    {
        getOperacionesPromocionResp respuesta = new getOperacionesPromocionResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        if(parametros.getOperacion().equals("A"))
        {
            //Operaciones para insert
            String query = "select ER_PROMOCIONES_SEQ.nextval from dual";
            long id = -1;
            try
            {
             Vector vid = ((Vector)em.createNativeQuery(query).getSingleResult());
                id = Long.valueOf(vid.get(0).toString());
              query = "INSERT INTO er_promociones_tbl (PROMOCION_ID,NOMBRE_PROMOCION,CODIGO_PROMOCION,DESCRIPCION_PROMOCION,NOMBRE_PRODUCTO,SERVICIO_ID,RUTA_ID,TRAMO_ID,EMPRESA_ID,APLICA_SENCILLO,APLICA_REDONDO,VIGENCIA_FECHA_INICIAL,VIGENCIA_FECHA_FINAL,CANAL_VENTA,COMPRA_MINIMA,ADICIONAL1,ADICIONAL2,ADICIONAL3,ADICIONAL4,ADICIONAL5,CREADO_POR,FECHA_CREACION,ULTIMA_ACTUALIZACION_POR,ULTIMA_FECHA_ACTUALIZACION) "
                    + "VALUES ("+id+",'"+parametros.getPromocion().getNombrePromocion()+"','"+parametros.getPromocion().getCodigoPromocion()+"','"+parametros.getPromocion().getDescripcionPromocion()+"','"+parametros.getPromocion().getNombreProducto()+"',"+parametros.getPromocion().getServicioId()+","+parametros.getPromocion().getRutaId()+","+parametros.getPromocion().getTramoId()+","+parametros.getPromocion().getEmpresaId()+",'"+parametros.getPromocion().getAplicaSencillo()+"','"+parametros.getPromocion().getAplicaRedondo()+"'"
                    +( parametros.getPromocion().getVigenciaFechaInicial()==null?",SYSDATE":",to_date('"+ffh.format(parametros.getPromocion().getVigenciaFechaInicial())+"','DD/MM/RRRR HH24:MI') " )
                    +( parametros.getPromocion().getVigenciaFechaFinal()==null?",null,": ",to_date('"+ffh.format(parametros.getPromocion().getVigenciaFechaFinal())+"','DD/MM/RRRR HH24:MI'), ")
                    + "'"+parametros.getPromocion().getCanalVenta()+"',"+parametros.getPromocion().getCompraMinima()+",'"+parametros.getPromocion().getAdicional1()+"','"+parametros.getPromocion().getAdicional2()+"','"+parametros.getPromocion().getAdicional3()+"','"+parametros.getPromocion().getAdicional4()+"','"+parametros.getPromocion().getAdicional5()+"',"+parametros.getUsuarioId()+",SYSDATE,"+parametros.getUsuarioId()+",SYSDATE)";
              System.out.println("query(insert): "+query);
              em.createNativeQuery(query).executeUpdate();
            }catch(EJBException e)
            {
                e.printStackTrace();
                respuesta.setErrorCode("ER-002");
                respuesta.setErrorMsg("La promocion no pudo se agregada: "+e.getMessage());
                respuesta.setOperacionExitosa(false);
            }
            respuesta.setPromocionId(id);
            respuesta.setErrorCode("");
            respuesta.setErrorMsg("la promocion se agrego satisfactoriamente");
            respuesta.setOperacionExitosa(true);

        }
        if(parametros.getOperacion().equals("M"))
        {
            //Operaciones para update

            try
            {
              String query = "update er_promociones_tbl set "
                      + "NOMBRE_PROMOCION = '"+parametros.getPromocion().getNombrePromocion()+"', "
                      + "CODIGO_PROMOCION = '"+parametros.getPromocion().getCodigoPromocion()+"', "
                      + "DESCRIPCION_PROMOCION = '"+parametros.getPromocion().getDescripcionPromocion()+"', "
                      + "SERVICIO_ID = "+parametros.getPromocion().getServicioId()+", "
                      + "RUTA_ID = "+parametros.getPromocion().getRutaId()+", "
                      + "TRAMO_ID = "+parametros.getPromocion().getTramoId()+", "
                      + "EMPRESA_ID = "+parametros.getPromocion().getEmpresaId()+", "
                      + "APLICA_SENCILLO = '"+parametros.getPromocion().getAplicaSencillo()+"', "
                      + "APLICA_REDONDO = '"+parametros.getPromocion().getAplicaRedondo()+"', "
                      + "VIGENCIA_FECHA_INICIAL = to_date('"+ffh.format(parametros.getPromocion().getVigenciaFechaInicial())+"','DD/MM/RRRR HH24:MI'), "
                      + "VIGENCIA_FECHA_FINAL = to_date('"+ffh.format(parametros.getPromocion().getVigenciaFechaFinal())+"','DD/MM/RRRR HH24:MI'), "
                      + "CANAL_VENTA = '"+parametros.getPromocion().getCanalVenta()+"', "
                      + "COMPRA_MINIMA = "+parametros.getPromocion().getCompraMinima()+", "
                      + "ADICIONAL1 = '"+parametros.getPromocion().getAdicional1()+"', "
                      + "ADICIONAL2 = '"+parametros.getPromocion().getAdicional2()+"', "
                      + "ADICIONAL3 = '"+parametros.getPromocion().getAdicional3()+"', "
                      + "ADICIONAL4 = '"+parametros.getPromocion().getAdicional4()+"', "
                      + "ADICIONAL5 = '"+parametros.getPromocion().getAdicional5()+"', "
                      + "ULTIMA_ACTUALIZACION_POR = "+parametros.getUsuarioId()+", "
                      + "ULTIMA_FECHA_ACTUALIZACION = SYSDATE "
                      + "where PROMOCION_ID = "+parametros.getPromocion().getPromocionId();
              System.out.println("query(update): "+query);
              em.createNativeQuery(query).executeUpdate();
            }catch(EJBException e)
            {
                e.printStackTrace();
                respuesta.setErrorCode("ER-002");
                respuesta.setErrorMsg("La promocion no pudo ser modificada: "+e.getMessage());
                respuesta.setOperacionExitosa(false);
            }
            respuesta.setPromocionId(parametros.getPromocion().getServicioId().longValue());
            respuesta.setErrorCode("");
            respuesta.setErrorMsg("la promocion se modifico satisfactoriamente");
            respuesta.setOperacionExitosa(true);        }

        return respuesta;
    }

    public getPromocionesResp getPromociones(getPromocionesReq parametros)
    {
      getPromocionesResp respuesta = new getPromocionesResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);
        System.out.println("CodigoPromocion: "+parametros.getCodigoPromocion());
        System.out.println("NombrePromocion "+parametros.getNombrePromocion());
        System.out.println("NombreProducto : "+parametros.getNombreProducto());
        String param = "^%"+parametros.getCodigoPromocion().toUpperCase()+"%^%"+parametros.getNombrePromocion().toUpperCase()+"%^%"+parametros.getNombreProducto().toUpperCase()+"%";
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PKG.GET_PROMOCIONES_PRC(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+ 
                    "END;";
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId()); 
            stmt.setString(2, param);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.execute();
            String datos = stmt.getString(3);
            System.out.println("datos: "+datos);
            if(stmt.getString(4).equals("0") && datos!=null)
            {
                System.out.println("Entra a agregar los datos");
                String[] registros = datos.split("\\^");
                System.out.println("registros: "+registros.length);
                List<ErPromocionesTbl> listado = new ArrayList<ErPromocionesTbl>();
                for(int i=0; i<registros.length;i++)
                {
                    System.out.println(""+registros[i]);
                     String[] registro = registros[i].split("\\|");
                     listado.add(new ErPromocionesTbl(registro));
                }
                respuesta.setPromociones(listado);
            }
                respuesta.setOperacionExitosa(stmt.getString(4).equals("0")?true:false);
                respuesta.setErrorCode(stmt.getString(5));
                respuesta.setErrorMsg(stmt.getString(6));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
    }


      public getOperacionesUsuarioResp getOperacionesUsuario(getOperacionesUsuarioReq parametros)
    {
          SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy");
          System.out.println("getOperacionesUsuarioResp Entrando: ");
          getOperacionesUsuarioResp respuesta = new getOperacionesUsuarioResp();
          respuesta.setErrorCode("ER-001");
         // respuesta.setErrorMsg("La operacion no pudo ser completada");
          respuesta.setOperacionExitosa(false);

           System.out.println("getOperacionesUsuarioResp Nombre: "+parametros.getUsuario().getUsuarioNombre());
           System.out.println("getOperacionesUsuarioResp Session : "+parametros.getSesionId());
           System.out.println("getOperacionesUsuarioResp Operacion : "+parametros.getOperacion());
           // Validaciones
          

           if(  parametros.getSesionId() <=0)
           {
             respuesta.setErrorMsg(msgSessionNV);
             return respuesta;
           }
           if( !parametros.getOperacion().equalsIgnoreCase("A")  && !parametros.getOperacion().equalsIgnoreCase("M") )
           {
                System.out.println("Entro a condicion Operacion ");
             respuesta.setErrorMsg(msgOperacionNV);
             return respuesta;
           }

           if( parametros.getOperacion().equalsIgnoreCase("M")  &&
                (parametros.getUsuario().getUsuarioId() == null || parametros.getUsuario().getUsuarioId() == new BigDecimal(0)  ) )
           {
             respuesta.setErrorMsg("ID de usuario Invalido");
            
           }

          /* if( parametros.getOperacion().equalsIgnoreCase("A")  )
           {

              if   (parametros.getUsuario().getFechaInicial() != null )
                 try {
                   String fecha=ff.format(parametros.getUsuario().getFechaInicial())+"";
               } catch (Exception e) {
                    respuesta.setErrorMsg(respuesta.getErrorMsg()+"  Fecha inical invalida el formato debe ser (DD/MM/YYYY)");
               }

              
           }*/

          // Validando Fechas

            if   (parametros.getUsuario().getFechaFinal() != null )
               try {
                   String fecha=ff.format(parametros.getUsuario().getFechaFinal())+"";

               } catch (Exception e) {
                    respuesta.setErrorMsg(respuesta.getErrorMsg()+"  Fecha inical invalida el formato debe ser (DD/MM/YYYY)");
                      return respuesta;
               }
           if   (parametros.getUsuario().getUsuarioNombre() == null || parametros.getUsuario().getUsuarioNombre().trim().length() <=0  )
                    respuesta.setErrorMsg("Nombre de usuario no valido");
           if   (parametros.getUsuario().getTipo_usuario_Id() == null || parametros.getUsuario().getTipo_usuario_Id() == new BigDecimal(0)   )
                    respuesta.setErrorMsg(respuesta.getErrorMsg()+"  Tipo de usuario no valido");
           if   (parametros.getOperacion().equalsIgnoreCase("M") &&  (parametros.getUsuario().getUsuarioId() ==  null || parametros.getUsuario().getUsuarioId() == new BigDecimal(0) ) )
               respuesta.setErrorMsg(respuesta.getErrorMsg()+" Id de usuario no valido");

             if   (parametros.getUsuario().getTipo_usuario_Id() == null || parametros.getUsuario().getTipo_usuario_Id() == new BigDecimal(0)   )
                    respuesta.setErrorMsg(respuesta.getErrorMsg()+"  Tipo de usuario no valido");


           if (respuesta.getErrorMsg()!= null && respuesta.getErrorMsg().length() > 0)
               return respuesta;

           respuesta.setErrorMsg("La operacion no pudo ser completada");
           // end Validando Fechas
           String strUsuario="|"+(parametros.getUsuario().getUsuarioId() != null? parametros.getUsuario().getUsuarioId():"0")+
                              "|"+parametros.getUsuario().getUsuarioNumero()+
                              "|"+parametros.getUsuario().getUsuarioNombre()+
                              "|"+parametros.getUsuario().getDescripcion()+
                              "|"+(parametros.getUsuario().getFechaInicial() != null? ff.format(parametros.getUsuario().getFechaInicial())+"":null)+
                              "|"+(parametros.getUsuario().getFechaFinal() != null?ff.format(parametros.getUsuario().getFechaFinal())+"":null)+
                              "|"+(parametros.getUsuario().getContrasenaEncriptada() != null? parametros.getUsuario().getContrasenaEncriptada():"")+
                              "|"+parametros.getUsuario().getContrasenaFecha() ;
           strUsuario=strUsuario+ "|"+(parametros.getUsuario().getContrasenaAccesosFaltan()!=null?parametros.getUsuario().getContrasenaAccesosFaltan():'0');
           strUsuario=strUsuario+ "|"+(parametros.getUsuario().getContrasenaLimiteDias()!=null?parametros.getUsuario().getContrasenaLimiteDias():'0');
           strUsuario=strUsuario+ "|"+(parametros.getUsuario().getContrasenaLimiteAccesos()!=null?parametros.getUsuario().getContrasenaLimiteAccesos():'0');
           strUsuario=strUsuario+ "|"+parametros.getUsuario().getCorreoElectronico()+
                                 "|"+parametros.getUsuario().getEstado();
           strUsuario=strUsuario+ "|"+(parametros.getUsuario().getContadorIntentos()!=null?parametros.getUsuario().getContadorIntentos():'0');
           strUsuario=strUsuario+ "|"+parametros.getUsuario().getTipo_usuario_Id()+
                                  "|"+parametros.getUsuario().getUnidad_negocio_Id()+
                                  "|"+(parametros.getUsuario().getUsuario_relacionado_id()!= null ? parametros.getUsuario().getUsuario_relacionado_id():"0");
           

           System.out.println("getOperacionesUsuarioResp Datos Usuario : "+strUsuario);
           Connection cnx=null;
           OracleCallableStatement stmt=null;
           try {
             cnx = dataSource.getConnection();
             String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PKG2.GET_OPERACIONES_USUARIO_PRC(?, ?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, strUsuario);
            stmt.setString(3, parametros.getOperacion().toUpperCase());
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
            stmt.execute();  

            String usuarioID = stmt.getString(4);
            System.out.println("usuarioID: "+usuarioID);

            respuesta.setUsuarioId(Long.parseLong(usuarioID));
            respuesta.setOperacionExitosa(stmt.getString(5).equals("TRUE")?true:false);
            respuesta.setErrorCode(stmt.getString(6));
            respuesta.setErrorMsg(stmt.getString(7));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }

    }


      public getUsuariosResp getUsuarios(getUsuariosReq parametros)
    {
        getUsuariosResp respuesta = new getUsuariosResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);
        System.out.println("SesionId: "+parametros.getSesionId());
        System.out.println("UsuarioNombre "+parametros.getUsuarioNombre());
        System.out.println("getUsuarioNumero : "+parametros.getUsuarioNumero());

         // Validaciones
          

           if(  parametros.getSesionId() <=0)
           {
             respuesta.setErrorMsg(msgSessionNV);
             return respuesta;
           }
          // END Validaciones


        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PKG2.GET_USUARIOS_PRC(?, ?, ?, ?, ?, ?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, parametros.getUsuarioNombre());
            stmt.setString(3, parametros.getUsuarioNumero());

            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
            stmt.execute();
            String datos = stmt.getString(4);
            System.out.println("datos: "+datos);
            if( datos!=null &&stmt.getString(5).equals("TRUE") )
            {
                System.out.println("Entra a agregar los datos");
                String[] registros = datos.split("\\^");
                System.out.println("registros: "+registros.length);
                 List<ErUsuariosTbl> listado = new ArrayList<ErUsuariosTbl>();
                 ErUsuariosTbl usuario=null;

                 for(int i=0; i<registros.length;i++)
                {

                     System.out.println("Encontro "+registros[i]+" Usuario");
                     String[] registro = registros[i].split("\\|");
                     usuario = new ErUsuariosTbl(registro);
                     System.out.println("Buscando usuarios Relacionados del user Principal: "+usuario.getUsuarioId());


                     List<ErUsuariosTbl> usuariosRel= getUsuariosRelac( usuario.getUsuarioId()+"");
                   

                     List<ErUsuariosTbl> userRel= new ArrayList<ErUsuariosTbl>();
                     List<ErUsuariosTbl> userRel1= new ArrayList<ErUsuariosTbl>();
                     List<ErUsuariosTbl> userRel2= new ArrayList<ErUsuariosTbl>();
                      if (usuariosRel != null)
                      for(int j=0; j<usuariosRel.size() ;j++){
                           ErUsuariosTbl  usuario1=usuariosRel.get(j);
                           System.out.println("Usuario nivel 1 "+usuario1.getUsuarioId());
                           List<ErUsuariosTbl> usuariosRel1= getUsuariosRelac( usuario1.getUsuarioId()+"");

                           if (usuariosRel1 != null){
                             for(int k=0; k<usuariosRel1.size() ;k++){
                                    ErUsuariosTbl  usuario2=usuariosRel1.get(k) ;
                                     List<ErUsuariosTbl> usuariosRel2= getUsuariosRelac( usuario2.getUsuarioId()+"");

                                     if (usuariosRel2!=null)
                                          for(int l=0; l<usuariosRel1.size() ;l++){
                                              ErUsuariosTbl  usuario3=usuariosRel2.get(l) ;
                                               List<ErUsuariosTbl> usuariosRel3= getUsuariosRelac( usuario3.getUsuarioId()+"");
                                              userRel2.add(usuario3);
                                          }
                                     if (userRel2!= null && userRel2.size()>0)
                                         usuario2.setUsuariosRelacionados(usuariosRel2);
                                    userRel1.add(usuario2);
                            }
                          
                               usuario1.setUsuariosRelacionados(userRel1);
                          }
                           
                        userRel.add(usuario1);
                      } // for interno
                      usuario.setUsuariosRelacionados(userRel);
                     listado.add(usuario);
                }  // for

                 respuesta.setUsuarios(listado);
            }
                respuesta.setOperacionExitosa(stmt.getString(5).equals("TRUE")?true:false);
                respuesta.setErrorCode(stmt.getString(6));
                respuesta.setErrorMsg(stmt.getString(7));
            stmt.close();
            if(cnx!=null) cnx.close();
            
            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
      }




       public List<ErUsuariosTbl> getUsuariosRelac( String usuarioID)
    {
       /* getUsuariosResp respuesta = new getUsuariosResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);*/
            List<ErUsuariosTbl> listado = null;

        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PKG2.GET_USUARIOS_RELACIONADOS_PRC(?, ?, ?, ?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setString(1,usuarioID);

            stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.execute();
            String datos = stmt.getString(2);
            System.out.println("getUsuariosResp datos: "+datos);
            if( datos!=null &&stmt.getString(3).equals("TRUE") )
            {
                //System.out.println("Entra a agregar los datos");
                listado = new ArrayList<ErUsuariosTbl>();
                String[] registros = datos.split("\\^");
                //System.out.println("registros: "+registros.length);
                
                for(int i=0; i<registros.length;i++)
                {
                    System.out.println("usuarios Relacionados --->>>"+registros[i]);
                     String[] registro = registros[i].split("\\|");

                      System.out.println("registros: "+registros.length);
                     listado.add(new ErUsuariosTbl(registro));


                }
                 //respuesta.setUsuarios(listado);
            }
                /*respuesta.setOperacionExitosa(stmt.getString(3).equals("TRUE")?true:false);
                respuesta.setErrorCode(stmt.getString(4));
                respuesta.setErrorMsg(stmt.getString(5));*/
            stmt.close();
            if(cnx!=null) cnx.close();
           

            return listado;
        } catch (SQLException ex){
            System.out.println("/*/*/*/*/* Exc en getUsuariosRelac:");
            ex.printStackTrace();
            return null;
        }
      }



      public getOperacionesClienteResp getOperacionesCliente(getOperacionesClienteReq parametros)
    {
          SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy");
          System.out.println("getOperacionesClienteReq Entrando: ");
          getOperacionesClienteResp respuesta = new getOperacionesClienteResp();
          respuesta.setErrorCode("ER-001");
          //respuesta.setErrorMsg("La operacion no pudo ser completada");
          respuesta.setErrorMsg("");
          respuesta.setOperacionExitosa(false);

           System.out.println("getOperacionesCliente Nombre: "+parametros.getCliente().getNombre());
           System.out.println("getOperacionesCliente Session : "+parametros.getSesionId());
           System.out.println("getOperacionesCliente Operacion : "+parametros.getOperacion());
  
          // BEGIN Validaciones
           respuesta.setOperacionExitosa(false);

           if( parametros.getSesionId() <=0)
           {
             respuesta.setErrorMsg(msgSessionNV);
             return respuesta;
           }
            if( !parametros.getOperacion().equalsIgnoreCase("A")  && !parametros.getOperacion().equalsIgnoreCase("M") )
           {
             respuesta.setErrorMsg(msgOperacionNV);
             return respuesta;
           }  

           if( parametros.getCliente().getNombre() == null || parametros.getCliente().getNombre().length() <=0)
             respuesta.setErrorMsg(respuesta.getErrorMsg()+ "\nNombre del cliente ");
           if( parametros.getOperacion().equalsIgnoreCase("M") && (parametros.getCliente().getClienteId() == null || parametros.getCliente().getClienteId() == new BigDecimal("0")) )
             respuesta.setErrorMsg(respuesta.getErrorMsg()+ "\nID del cliente no valido ");
           if( 
               parametros.getCliente().getPersonaMoral() == null && !parametros.getCliente().getPersonaMoral().equals("S") &&  !parametros.getCliente().getPersonaMoral().equals("N")  )
             respuesta.setErrorMsg(respuesta.getErrorMsg()+ "\n Persona Moral debe ser S O N ");
         /*  if( parametros.getCliente().getSaldoActual() == null  )
                respuesta.setErrorMsg(respuesta.getErrorMsg()+ "\nSaldo actual debe ser mayor a 0 ");*/
           if( parametros.getCliente().getSaldoFavor() == null  )
                respuesta.setErrorMsg(respuesta.getErrorMsg()+ "\nSaldo a favor  debe ser mayor a 0 ");


         if (respuesta.getErrorMsg()!= null && respuesta.getErrorMsg().length() > 0)
               return respuesta;
           respuesta.setErrorMsg("La operacion no pudo ser completada");
          // END Validaciones

         System.out.println(" parametros.getCliente().getFechaBaja() : "+ parametros.getCliente().getFechaBaja());

           String  strDirecciones ="";
           String strCliente="|"+(parametros.getCliente().getClienteId()!= null? parametros.getCliente().getClienteId():'0')+
                              "|"+parametros.getCliente().getNombre()+
                              "|"+parametros.getCliente().getApaterno()+
                              "|"+parametros.getCliente().getAmaterno()+
                              "|"+parametros.getCliente().getPersonaMoral().toUpperCase()+
                              "|"+parametros.getCliente().getNombreFiscal()+
                              "|"+parametros.getCliente().getDireccionFiscal()+
                              "|"+parametros.getCliente().getTelefono()+
                              "|"+parametros.getCliente().getTelefonoCelular()+
                              "|"+parametros.getCliente().getTelefonoOficina()+
                              "|"+parametros.getCliente().getTelefonoFax()+
                              "|"+parametros.getCliente().getCalle()+
                              "|"+parametros.getCliente().getNoExt()+
                              "|"+parametros.getCliente().getNoInt()+
                              "|"+parametros.getCliente().getCodigoPostal()+
                              "|"+parametros.getCliente().getColonia()+
                              "|"+parametros.getCliente().getCiudad()+
                              "|"+parametros.getCliente().getEstado()+
                              "|"+parametros.getCliente().getPais()+
                              "|"+parametros.getCliente().getRfc()+
                              "|"+parametros.getCliente().getEmail()+
                              "|"+parametros.getCliente().getContacto()+
                              "|"+parametros.getCliente().getActivo()+
                              "|"+parametros.getCliente().getMotivoSuspencion()+   
                              "|"+parametros.getCliente().getClaveUsuario()+
                              "|"+parametros.getCliente().getContrasenaUsuario()+
                              "|"+parametros.getCliente().getTipoClienteId()+
                              "|"+parametros.getCliente().getGiroClienteId()+
                              "|"+parametros.getCliente().getAplicaRetencion()+
                              "|"+parametros.getCliente().getNoCuenta()+
                              "|"+parametros.getCliente().getReferencia()+
                              "|"+parametros.getCliente().getCreditoActivo()+
                              "|"+(parametros.getCliente().getLimiteCredito()!= null ? parametros.getCliente().getLimiteCredito():"0" )+
                              "|"+(parametros.getCliente().getSaldoActual()!= null ? parametros.getCliente().getSaldoActual():"0")+
                              "|"+(parametros.getCliente().getSaldoFavor() != null ? parametros.getCliente().getSaldoFavor() :"0")+
                              "|"+(parametros.getCliente().getFechaRegistro() != null?ff.format(parametros.getCliente().getFechaRegistro())+"":"")+
                              "|"+(parametros.getCliente().getFechaBaja() != null?ff.format(parametros.getCliente().getFechaBaja())+"":"");
                             
           System.out.println("getOperacionesUsuarioResp Datos Direcciones : "+strDirecciones);

          List<ERClientesDireccionesTbl> listdirecciones= parametros.getCliente().getDirecciones();
          ERClientesDireccionesTbl direccion=null;
          for (int i = 0; i < listdirecciones.size(); i++) {
               direccion=listdirecciones.get(i);
               strDirecciones=strDirecciones+ "^|";
               strDirecciones=strDirecciones+(direccion.getClientesDireccionesId()!= null? direccion.getClientesDireccionesId():'0')+
                              "|"+(direccion.getClienteId()!= null? direccion.getClienteId():'0')+
                              "|"+direccion.getTelefono()+
                              "|"+direccion.getTelefonoCelular()+
                              "|"+direccion.getTelefonoOficina()+
                              "|"+direccion.getTelefonoFax()+
                              "|"+direccion.getCalle()+
                              "|"+direccion.getNoExt()+
                              "|"+direccion.getNoInt()+
                              "|"+direccion.getCodigoPostal()+
                              "|"+direccion.getColonia()+
                              "|"+direccion.getCiudad()+
                              "|"+direccion.getEstado()+
                              "|"+direccion.getPais();
        }


           Connection cnx=null;
           OracleCallableStatement stmt=null;
           try {
             cnx = dataSource.getConnection();
             String q1 =
                    "BEGIN "+paquete+ ".GET_OPERACIONES_CLIENTE_PRC(?, ?, ?, ?, ?, ?, ?,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";


            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, strCliente);
            stmt.setString(3, strDirecciones);
            stmt.setString(4, parametros.getOperacion().toUpperCase());
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(8, java.sql.Types.VARCHAR);
            stmt.execute();

            if (   stmt.getString(6).equals("TRUE"))
            {
  
            String clienteID = stmt.getString(5);
            System.out.println("usuarioID: "+clienteID);
           
                respuesta.setClienteId(Long.parseLong(clienteID));
               }
            respuesta.setOperacionExitosa(stmt.getString(6).equals("TRUE")?true:false);
            respuesta.setErrorCode(stmt.getString(7));
            respuesta.setErrorMsg(stmt.getString(8));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
    }


       public getClientesResp getClientes(getClientesReq parametros)
    {
        getClientesResp respuesta = new getClientesResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);
        System.out.println("SesionId: "+parametros.getSesionId());
        System.out.println("ClienteNombre "+parametros.getClienteNombre());
        System.out.println("ClienteNoCuenta : "+parametros.getClienteNoCuenta());
        System.out.println("Clienterfc : "+parametros.getClienteRFC());


         // BEGIN Validaciones


           if( parametros.getSesionId() <=0)
           {
              respuesta.setOperacionExitosa(false);
             respuesta.setErrorMsg(msgSessionNV);
             return respuesta;
           }


        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            //GET_OPER_CLIENTEUSUARIOS_PRC
            String q1 =
                   "BEGIN "+paquete+ ".GET_CLIENTES_PRC(?, ?, ?, ?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, parametros.getClienteNombre());
            stmt.setString(3, parametros.getClienteRFC());
            stmt.setString(4, parametros.getClienteNoCuenta());

            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(8, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(9, java.sql.Types.VARCHAR);
            stmt.execute();

            String datos = stmt.getString(5);
            String datosDir = stmt.getString(6);
            System.out.println("datos: "+datos);
            System.out.println("datos Dir : "+datosDir);

            if( datos!=null && stmt.getString(7).equals("TRUE") )
            {
                System.out.println("Entra a agregar los datos del cliente");
                String[] registros = datos.split("\\^");  
                System.out.println("registros Clientes: "+registros.length);
                List<ErClientesTbl> listado= new ArrayList<ErClientesTbl>();

                String [] arrDirecciones = datosDir.split("~");
               System.out.println("Clientes obtenidas "+registros.length);
               System.out.println("Direcciones obtenidas "+arrDirecciones.length);

              //  StringTokenizer st = new StringTokenizer(datosDir, "~");

                for(int i=0; i<registros.length;i++)
                {
                    System.out.println(""+registros[i]);
                     String[] registro = registros[i].split("\\|");

                     ErClientesTbl cliente=new ErClientesTbl(registro);
                     
                     // Trae las direcciones
                     //String direcciones=st.nextToken();
                    String direcciones=arrDirecciones[i];
                     System.out.println("Dir del cliente : "+direcciones);
                     if (direcciones.length() >1)
                         {
                             String[] registrosDir = direcciones.split("\\^");
                             System.out.println("registros Direcciones : "+registrosDir);
                                List<ERClientesDireccionesTbl> listadoDir= new ArrayList<ERClientesDireccionesTbl>();
                                for(int j=0; j<registrosDir.length;j++)
                                {
                                    System.out.println(""+registrosDir[j]);
                                     String[] registroDir = registrosDir[j].split("\\|");

                                     listadoDir.add(new ERClientesDireccionesTbl(registroDir));

                                }
                              cliente.setDirecciones(listadoDir);
                        }

                    // listado.add(new ErClientesTbl(registro));
                     listado.add(cliente);
                    
                }
                 respuesta.setClientes(listado);
            }
                respuesta.setOperacionExitosa(stmt.getString(7).equals("TRUE")?true:false);
                respuesta.setErrorCode(stmt.getString(8));
                respuesta.setErrorMsg(stmt.getString(9));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
      }


  public getOperacionesClientesUsuarioResp getOperacionesClientesUsuario(getOperacionesClientesUsuarioReq parametros)
    {
          SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy");
          System.out.println("getOperacionesClienteReq Entrando: ");
          getOperacionesClientesUsuarioResp respuesta = new getOperacionesClientesUsuarioResp();
          respuesta.setErrorCode("ER-001");
         // respuesta.setErrorMsg("La operacion no pudo ser completada");
          respuesta.setOperacionExitosa(false);
           System.out.println("OperacionesClientesUsuario usuario : "+parametros.getUsuarioId());
           System.out.println("OperacionesClientesUsuario Session : "+parametros.getSesionId());
           System.out.println("OperacionesClientesUsuario Operacion : "+parametros.getOperacion());

           // BEGIN Validaciones


           if( parametros.getSesionId() <=0)
           {
              respuesta.setOperacionExitosa(false);
             respuesta.setErrorMsg(msgSessionNV);
             return respuesta;
           }
           if( parametros.getUsuarioId()== null  || parametros.getUsuarioId().trim().length() <= 0 )
           {
             respuesta.setOperacionExitosa(false);
             respuesta.setErrorMsg("El ID del cliente no es valido");
             return respuesta;
           }

           respuesta.setErrorMsg("La operacion no pudo ser completada");
           
           String clientes_ID="";
           List<ErClientesTbl> clienteslist=parametros.getCliente();

           for (int i = 0; i < clienteslist.size(); i++)
                    clientes_ID=clientes_ID+ "|"+((ErClientesTbl)clienteslist.get(i)).getClienteId();


           System.out.println("getOperacionesUsuarioCliente ClientesID : "+clientes_ID);

           Connection cnx=null;
           OracleCallableStatement stmt=null;
           try {
             cnx = dataSource.getConnection();
             String q1 =
                    "BEGIN "+paquete+ ".GET_OPER_CLIENTESUSUARIOS_PRC(?, ?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, parametros.getUsuarioId());
            stmt.setString(3, clientes_ID);
            stmt.setString(4, parametros.getOperacion().toUpperCase());
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
            stmt.execute();




            respuesta.setOperacionExitosa(stmt.getString(5).equals("TRUE")?true:false);
            respuesta.setErrorCode(stmt.getString(6));
            respuesta.setErrorMsg(stmt.getString(7));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }

    }


    public getPagosResp getPagos(getPagosReq parametros)
    {
        getPagosResp respuesta = new getPagosResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);
        System.out.println("SesionId: "+parametros.getSesionId());
        System.out.println("cliente id  "+parametros.getClienteId());
        System.out.println("referencia : "+parametros.getReferencia());
        System.out.println("estado : "+parametros.getEstado());

         if(  parametros.getSesionId() <=0)
           {
             respuesta.setErrorMsg(msgSessionNV);
             return respuesta;
           }
         if( parametros.getEstado() !=null && parametros.getEstado().trim().length()>0 && !parametros.getEstado().equalsIgnoreCase("A")  && !parametros.getEstado().equalsIgnoreCase("I")
                && !parametros.getEstado().equalsIgnoreCase("C")  )
           {
             respuesta.setErrorMsg("El estado de los pagos no es valido");
             return respuesta;
           }

        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+paquete+ ".GET_PAGOS_PRC(?, ?, ?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";   

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, parametros.getClienteId());
            stmt.setString(3, parametros.getReferencia());
            stmt.setString(4, parametros.getEstado().toUpperCase());

            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(8, java.sql.Types.VARCHAR);
            stmt.execute();

            String datos = stmt.getString(5);
            System.out.println("datos: "+datos);
            if( datos!=null && stmt.getString(6).equals("TRUE"))
            {
                System.out.println("Entra a agregar los datos");
                String[] registros = datos.split("\\^");
                System.out.println("registros: "+registros.length);
                 List<ErPagosTbl> listado = new ArrayList<ErPagosTbl>();
                for(int i=0; i<registros.length;i++)
                {
                    System.out.println(""+registros[i]);
                     String[] registro = registros[i].split("\\|");

                     listado.add(new ErPagosTbl(registro));
                    System.out.println(""+listado);
                }
                 respuesta.setPagos(listado);
            }
                respuesta.setOperacionExitosa(stmt.getString(6).equals("TRUE")?true:false);
                respuesta.setErrorCode(stmt.getString(7));
                respuesta.setErrorMsg(stmt.getString(8));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
      }



     public getOperacionesFacturaResp getOperacionesFactura(getOperacionesFacturaReq parametros)
    {
        getOperacionesFacturaResp respuesta = new getOperacionesFacturaResp();

        // try {

          String usuario_id ="0";

          SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy");
          System.out.println("getOperacionesClienteReq Entrando: ");
          respuesta.setErrorCode("ER-001");
          respuesta.setErrorMsg("La operacion no pudo ser completada");
          // respuesta.setErrorMsg("");
          respuesta.setOperacionExitosa(false);

           System.out.println("getOperacionesFactura Session : "+parametros.getSesionId());
            System.out.println("Operacion : "+parametros.getOperacion()+"--");
           

           // BEGIN Validaciones


           

          respuesta.setOperacionExitosa(false);

           if( parametros.getSesionId() <=0)
           {
              
             respuesta.setErrorMsg(msgSessionNV);
             return respuesta;
           }

           if( !parametros.getOperacion().trim().equalsIgnoreCase("I") &&
               !parametros.getOperacion().trim().equalsIgnoreCase("A") &&
               !parametros.getOperacion().trim().equalsIgnoreCase("C") )
           {
              System.out.println("Operacion Incorrecta y se sale ");
             respuesta.setErrorMsg("Operación no valida.");
             return respuesta;
           }  
  
            if( ( parametros.getOperacion() == "A" )
                &&  parametros.getFactura().getFacturaId()== new BigDecimal("0")   )
            {
                respuesta.setErrorMsg("Factura ID no valido");
                return respuesta;
            }     

           if( (   parametros.getOperacion() == "C" )
                &&  parametros.getFactura().getFolio_factura().trim().length()<=0
                &&  parametros.getFactura().getUnidadNegocio().trim().length() <=0 )
            {
                respuesta.setErrorMsg("Para cancelar la factura debe ingresar el folio y la unidad de negocio de la  factura.");
                return respuesta;
            }   
  

           if (parametros.getFactura().getClienteId() == null || parametros.getFactura().getClienteId()== new BigInteger("0")  )
           {
               respuesta.setErrorMsg(respuesta.getErrorMsg() + " \n Cliente ID no puede ser nulo");
               return respuesta;}
            if (parametros.getFactura().getRfc() == null || parametros.getFactura().getRfc().length() <=0 )
            {
                respuesta.setErrorMsg(respuesta.getErrorMsg() + " \nRFC no valido");
                return respuesta;}
            if (parametros.getFactura().getRazonSocial() == null || parametros.getFactura().getRazonSocial().length() <=0 )
            {
                respuesta.setErrorMsg(respuesta.getErrorMsg() + " \nRazon Social no valida");
                return respuesta;}
            if (parametros.getFactura().getDomicilio() == null || parametros.getFactura().getDomicilio().length() <=0 )
            {
                respuesta.setErrorMsg(respuesta.getErrorMsg() + " \nDomicilio no valido");
                           return respuesta;}
           if (parametros.getFactura().getNo_Ext() == null || parametros.getFactura().getNo_Ext().length() <=0 )
           {
               respuesta.setErrorMsg(respuesta.getErrorMsg() + " \nNo. Exterior  no valido");
                return respuesta;}

             if (parametros.getFactura().getNo_Int() == null || parametros.getFactura().getNo_Int().length() <=0 )
           {
               respuesta.setErrorMsg(respuesta.getErrorMsg() + " \nNo. Interior  no valido");
                return respuesta;}

           respuesta.setErrorMsg("La operacion no pudo ser completada");



           System.out.println("Fatura 1 ");
           String productos="";
           List<ErProductosRefTbl> productoslist=parametros.getProductos();


           if (productoslist.size() <=0)
              {
                     respuesta.setOperacionExitosa(false);
                     respuesta.setErrorMsg("Agregar productos a facturar.");

                     return respuesta;
             }
           for (int i = 0; i < productoslist.size(); i++)
                    productos=productos+ "^|"+((ErProductosRefTbl)productoslist.get(i)).getNombre()+
                                         "|"+((ErProductosRefTbl)productoslist.get(i)).getReferencia();




           productos= productos.substring(0,productos.length()-1);

           ErFacturasTbl factura = parametros.getFactura();
           System.out.println("No_Ext: "+factura.getNo_Ext());
           System.out.println("No_Int : "+factura.getNo_Int());

           getValidaSesionReq par_ses= new getValidaSesionReq();
           par_ses.setSesionId(parametros.getSesionId());
           par_ses.setCanalVenta(factura.getUnidadNegocio().toUpperCase());

           getValidaSesionResp resvalidasesion=null;
           try {
                resvalidasesion=getValidaSesion(par_ses);

               if (!resvalidasesion.isOperacionExitosa())
               {
                   respuesta.setErrorMsg(resvalidasesion.getErrorMsg() );
                   return respuesta;
               }  
               else
               usuario_id=""+resvalidasesion.getUsuarioId();

        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setOperacionExitosa(false);
            respuesta.setErrorMsg("Error al validar sesion.");
            return respuesta;

        }



           String strfactura="|"+(factura.getFacturaId()!= null? factura.getFacturaId():'0')+
                             "|"+factura.getFolio_factura()+
                             "|"+factura.getUnidadNegocio().toUpperCase()+
                             "|"+factura.getClaveFacturaElec().replace("|","~");
           

           System.out.println("OperacionesOperacion Factura 1");

           if (parametros.getOperacion().equalsIgnoreCase("I") || parametros.getOperacion().equalsIgnoreCase("A"))  {
              
                if (factura.getTipoComprobante() == null)
                    factura.setTipoComprobante("ingreso");

                strfactura= strfactura +
                             "|"+(factura.getClienteId()!= null? factura.getClienteId():'0')+
                             "|"+(factura.getSubtotal()!= null? factura.getSubtotal():'0')+
                             "|"+(factura.getIva()!= null? factura.getIva():'0')+
                             "|"+(factura.getIvaRetenido()!= null? factura.getIvaRetenido():'0')+
                             "|"+(factura.getMontoRetenido()!= null? factura.getMontoRetenido():'0')+
                             "|"+(factura.getRet5millar()!= null? factura.getRet5millar():'0')+
                             "|"+(factura.getDescuento()!= null? factura.getDescuento():'0')+
                             "|"+(factura.getTotal()!= null? factura.getTotal():'0')+
                             "|"+factura.getRazonSocial()+
                             "|"+factura.getRfc()+
                             "|"+factura.getConcepto()+
                             
                             "|"+(factura.getFecha() != null?ff.format(factura.getFecha())+"":"")+
                             "|"+factura.getTelefono()+
                             "|"+factura.getDomicilio()+
                             "|"+(factura.getNo_Ext()!= null?factura.getNo_Ext().trim():"") +
                             "|"+(factura.getNo_Int()!= null?factura.getNo_Int().trim():"")+
                             "|"+factura.getColonia()+
                             "|"+factura.getCodigopostal()+
                             "|"+factura.getCiudad()+
                             "|"+factura.getEstado()+
                             "|"+factura.getPais()+
                             "|"+(factura.getFechaVencimiento() != null?ff.format(factura.getFechaVencimiento())+"":"")+
                             "|"+(factura.getFechaContable() != null?ff.format(factura.getFechaContable())+"":"")+
                             "|"+factura.getEstado()+
                             "|"+(factura.getEMail()!=null && factura.getEMail().length()>0 ? factura.getEMail().replace("|", "^"):"")+
                             "|"+factura.getRutaPdf()+
                             "|"+factura.getRutaXml()+
                             "|"+factura.getProducto()+
                             "|"+factura.getMetodoPago()+
                             "|"+factura.getNumCuenta()+
                             "|"+factura.getUnidaFactura()+ // Pza, Servicio, No Aplica
                             "|MOS"+     // Tipo de factura  , CRED, MOS   
                             "|"+factura.getTipoComprobante(); //  ingreso, egreso, tralado
                
                       System.out.println("OperacionesOperacion Factura 3");
                  }   // fin de Obtener datos para insert

                  strfactura=strfactura+ "|"+factura.getObservaciones()+
                              "|"+usuario_id+  // USUARIO
                              "|"+"WEB";   // sucursal

            String strAdenda= "|"+factura.getCodProveedor()+
                             "|"+factura.getSolicitante()+
                             "|"+factura.getCorreoSolic()+
                             "|"+factura.getOrdenCompra();
                            
            System.out.println("OperacionesClientesUsuario factura.getEMail() : "+factura.getEMail().replace("|", "^"));

            System.out.println("OperacionesOperacionFactura "+strfactura);
            System.out.println("OperacionesOperacionFactura "+productos);
            System.out.println("OperacionesOperacionFactura "+strAdenda);

           Connection cnx=null;
           OracleCallableStatement stmt=null;
           try {
             cnx = dataSource.getConnection();
             String q1 =
                    "BEGIN "+ ""+paquete+ ".GET_OPERACION_FACTURA_PRC(?, ?, ?, ?, ?, ?, ?, ?, ?); " +
                                              
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, strfactura);
            stmt.setString(3, productos);
            stmt.setString(4, strAdenda);
            stmt.setString(5, parametros.getOperacion().toUpperCase());
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(8, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(9, java.sql.Types.VARCHAR);
            stmt.execute();  

            //respuesta.setFacturaId(stmt.getString(5));
             String datos = stmt.getString(6);
            System.out.println("datos: "+datos);
            if( datos!=null && stmt.getString(7).equals("TRUE"))
            {
                System.out.println("Entra a agregar los datos");
                StringTokenizer datos_t=new StringTokenizer(datos,"~");
                 respuesta.setFacturaId(datos_t.nextToken());
                 if (parametros.getOperacion().equalsIgnoreCase("A")){
                      respuesta.setClaveFactura(datos_t.nextToken());
                      try {
                          respuesta.setFacturaPDF(getfacturaPDF(datos_t.nextToken()));
                          respuesta.setFacturaXML(getContenidoXML (datos_t.nextToken()));
                          respuesta.setFolioFactura(datos_t.nextToken());
                          guarda_archivos_Fact(respuesta.getFacturaId(),
                                               respuesta.getFacturaPDF(),
                                               respuesta.getFacturaXML());
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                      
                }
                   
             }
            respuesta.setOperacionExitosa(stmt.getString(7).equals("TRUE")?true:false);
            respuesta.setErrorCode(stmt.getString(8));
            respuesta.setErrorMsg(stmt.getString(9));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

     
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
       return respuesta;
    }



      public getFacturasResp getFacturas(getFacturasReq parametros)
    {
        getFacturasResp respuesta = new getFacturasResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

         if( parametros.getSesionId() == null   )
           {

             respuesta.setErrorMsg(msgSessionNV);
             return respuesta;
           }  

        if ((parametros.getFolioFactura() != null  && parametros.getFolioFactura().trim().length() > 0  && parametros.getUnidadNegocio().trim().length() <= 0 )
                || ( parametros.getUnidadNegocio() != null  && parametros.getUnidadNegocio().trim().length() > 0 &&  parametros.getFolioFactura().trim().length() <= 0   ))
         {

             respuesta.setErrorMsg("Para aplicar este filtro debe proporciona el folio y la unidad de negocio.");
             return respuesta;
           }

        System.out.println("getFacturas SesionId: "+parametros.getSesionId());
        String param_busqueda =  '|'+parametros.getClienteId()+
                            '|'+parametros.getNombreProducto()+
                           '|'+parametros.getFolioFactura()+
                           '|'+parametros.getUnidadNegocio()+
                           '|'+parametros.getClaveFactElect().replace('|', '~')  +
                           '|'+parametros.getEstado()+
                           '|'+parametros.getRazonSocial();

        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+paquete+ ".GET_FACTURAS_PRC(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2,param_busqueda);
            
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.execute();

            String datos = stmt.getString(3);
            System.out.println("datos: "+datos);
            if( datos!=null && stmt.getString(4).equals("TRUE"))
            {
                System.out.println("Entra a agregar los datos");
                String[] registros = datos.split("\\^");
                System.out.println("registros: "+registros.length);
                 List<ErFacturasTbl> listado = new ArrayList<ErFacturasTbl>();
                for(int i=0; i<registros.length;i++)
                {
                    System.out.println(""+registros[i]);
                     String[] registro = registros[i].split("\\|");

                     listado.add(new ErFacturasTbl(registro));
                    System.out.println(""+listado);
                }
                 respuesta.setFacturas(listado);
            }
                respuesta.setOperacionExitosa(stmt.getString(4).equals("TRUE")?true:false);
                respuesta.setErrorCode(stmt.getString(5));
                respuesta.setErrorMsg(stmt.getString(6));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
      }


     public getProductosClienteResp getProductosCliente(getProductosClienteReq parametros)
    {
        getProductosClienteResp respuesta = new getProductosClienteResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);
        System.out.println("getProductosCliente SesionId: "+parametros.getSesionId());
        String param_busqueda =  '|'+parametros.getClienteId()+
                            '|'+parametros.getNombreProducto()+
                           '|'+parametros.getReferencia();

        if( parametros.getSesionId() <=0   )
           {
             respuesta.setErrorMsg(msgSessionNV);
             return respuesta;
           }


        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+paquete+ ".GET_PRODUCTOS_CLIENTE_PRC(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2,param_busqueda);

            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.execute();

            String datos = stmt.getString(3);
            System.out.println("datos: "+datos);
            if( datos!=null && stmt.getString(4).equals("TRUE"))
            {
                System.out.println("Entra a agregar los datos");
                String[] registros = datos.split("\\^");
                System.out.println("registros: "+registros.length);
                 List<ErProductoCliente> listado = new ArrayList<ErProductoCliente>();
                for(int i=0; i<registros.length;i++)
                {
                    System.out.println(""+registros[i]);
                     String[] registro = registros[i].split("\\|");

                     listado.add(new ErProductoCliente(registro));
                    System.out.println(""+listado);
                }
                 respuesta.setProductos(listado);
            }
                respuesta.setOperacionExitosa(stmt.getString(4).equals("TRUE")?true:false);
                respuesta.setErrorCode(stmt.getString(5));
                respuesta.setErrorMsg(stmt.getString(6));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
      }

    public getPerfilesUsuarioResp getPerfilesUsuario(getPerfilesUsuarioReq parametros)
    {
      getPerfilesUsuarioResp respuesta = new getPerfilesUsuarioResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        System.out.println("UsuarioId: "+parametros.getUsuarioId());
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PKG.GET_USUARIO_PERFILES_PRC(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, ""+parametros.getUsuarioId());
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.execute();
            String datos = stmt.getString(3);
            System.out.println("datos: "+datos);
            if(stmt.getString(4).equals("0") && datos!=null)
            {
                System.out.println("Entra a agregar los datos");
                String[] registrosPerf = datos.split("~");
                List<Perfil> perfiles = new ArrayList<Perfil>();
                for (int j=0; j<registrosPerf.length;j++)
                {
                    String[] registros = registrosPerf[j].split("\\^");
                    System.out.println("registros: "+registros.length);
                    
                    List<Funcion> funciones = new ArrayList<Funcion>();
                    String[] registroPerfil = registros[0].split("\\|");
                    System.out.println(""+registroPerfil);
                    Perfil perfil =  new Perfil(registroPerfil);
                    for(int i=1; i<registros.length;i++)
                    {
                        System.out.println(""+registros[i]);
                         String[] registrofuncion = registros[i].split("\\|");
                         funciones.add(new Funcion(registrofuncion));
                    }
                    perfil.setFunciones(funciones);
                    perfiles.add(perfil);
                }
                respuesta.setPerfiles(perfiles);
            }
                respuesta.setOperacionExitosa(stmt.getString(4).equals("0")?true:false);
                respuesta.setErrorCode(stmt.getString(5));
                respuesta.setErrorMsg(stmt.getString(6));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
    }

    public getOperacionesCreditoClientesResp getOperacionesCreditoClientes(getOperacionesCreditoClientesReq parametros)
    {
      getOperacionesCreditoClientesResp respuesta = new getOperacionesCreditoClientesResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        System.out.println("clienteId: "+parametros.getClienteId());
        System.out.println("Comisiones "+parametros.getClienteComisiones().size());
        System.out.println("noCuenta : "+parametros.getNoCuenta());
        String param = "^|"+parametros.getClienteId()+"|"+parametros.getNoCuenta()+"|"+parametros.getReferencia()+"|"+parametros.getCreditoActivo()+"|"+parametros.getLimiteCredito()+"|"+parametros.getAplicaRetencion();
        System.out.println("param : "+param);
        for(ClienteComisiones c : parametros.getClienteComisiones())
        {
          System.out.println("FechaInicial : "+c.getFechaInicial());
          System.out.println("FechaFinal : "+c.getFechaFinal());
            param = param + "^|"+(c.getClienteComisionId()==null?-1:c.getClienteComisionId())+"|"+c.getClienteId() +"|"+c.getNombreProducto() +"|"+ c.getServicioId()+"|"+c.getRutaId() +"|"+c.getTramoId() +"|"+c.getEmpresaId() +"|"+c.getPorcentajeComision() +"|"+ffh.format(c.getFechaInicial()) +"|"+(c.getFechaFinal()==null?"-1":ffh.format(c.getFechaFinal()));
        }
        System.out.println("param : "+param);
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PKG.get_OperCred_Clientes_PRC(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, param);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.execute();
            String datos = stmt.getString(3);
            System.out.println("datos: "+datos);
                respuesta.setOperacionExitosa(stmt.getString(4).equals("0")?true:false);
                respuesta.setErrorCode(stmt.getString(5));
                respuesta.setErrorMsg(stmt.getString(6));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
    }

  public getOperacionesPagosClienteResp getOperacionesPagosCliente(getOperacionesPagosClienteReq parametros)
    {
      getOperacionesPagosClienteResp respuesta = new getOperacionesPagosClienteResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        System.out.println("pagoId: "+parametros.getPago().getPagoId());
        System.out.println("operacion : "+parametros.getOperacion());
        String param = "|"+(parametros.getPago().getPagoId()==null?-1:parametros.getPago().getPagoId())+"|"+parametros.getPago().getClienteId()+"|"+parametros.getPago().getTipoPago()+"|"+parametros.getPago().getFormaPagoId()+"|"+parametros.getPago().getEstado()+"|"+parametros.getPago().getMonto()+"|"+ffh.format(parametros.getPago().getFecha())+"|"+ffh.format(parametros.getPago().getFechaContable())+"|"+parametros.getPago().getReferencia()+"|"+parametros.getPago().getNoTarjeta();
        System.out.println("param : "+param);
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PKG.get_OperPagos_Clientes_PRC(?, ?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, param);
            stmt.setString(3, parametros.getOperacion());
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
            stmt.execute();
            String datos = stmt.getString(4);
            System.out.println("datos: "+datos);
            if(stmt.getString(5).equals("0"))
              respuesta.setPagoId(Long.valueOf(datos));
                respuesta.setOperacionExitosa(stmt.getString(5).equals("0")?true:false);
                respuesta.setErrorCode(stmt.getString(6));
                respuesta.setErrorMsg(stmt.getString(7));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
    }


    public getPagosFacturaResp getPagosFactura(getPagosFacturaReq parametros)
    {
      getPagosFacturaResp respuesta = new getPagosFacturaResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        System.out.println("facturaId: "+parametros.getFacturaId());
        String param = ""+parametros.getFacturaId();
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PKG.get_Pagos_Factura_prc(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, param);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.execute();
            String datos = stmt.getString(3);
            System.out.println("datos: "+datos);
            if(stmt.getString(4).equals("0") && datos!=null)
            {
                System.out.println("Entra a agregar los datos");
                String[] registros = datos.split("\\^");
                System.out.println("registros: "+registros.length);
                List<PagoFactura> listado = new ArrayList<PagoFactura>();
                for(int i=0; i<registros.length;i++)
                {
                    System.out.println(""+registros[i]);
                     String[] registro = registros[i].split("\\|");
                     listado.add(new PagoFactura(registro));
                }
                respuesta.setPagosFacturas(listado);
            }
                respuesta.setOperacionExitosa(stmt.getString(4).equals("0")?true:false);
                respuesta.setErrorCode(stmt.getString(5));
                respuesta.setErrorMsg(stmt.getString(6));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
    }


public getOperacionesPagosFacturasResp getOperacionesPagosFacturas(getOperacionesPagosFacturasReq parametros)
    {
      getOperacionesPagosFacturasResp respuesta = new getOperacionesPagosFacturasResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        System.out.println("pagos: "+parametros.getPagos().size());
        System.out.println("operacion : "+parametros.getOperacion());
        String param ="";
        for(PagoFactura p : parametros.getPagos())
                param = param+"^|"+(p.getPagosFacturaId()==null?-1:p.getPagosFacturaId())+"|"+p.getPagoId()+"|"+p.getFacturaId()+"|"+p.getMontoAplicado()+"|"+p.getReferenciaPago()+"|"+p.getMontoPago();
        System.out.println("param : "+param);
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PKG.get_OperPagos_Facturas_PRC(?, ?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, param);
            stmt.setString(3, parametros.getOperacion());
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
            stmt.execute();
            String datos = stmt.getString(4);
            System.out.println("datos: "+datos);
                respuesta.setOperacionExitosa(stmt.getString(5).equals("0")?true:false);
                respuesta.setErrorCode(stmt.getString(6));
                respuesta.setErrorMsg(stmt.getString(7));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
    }


    public getRegistraTransaccionesClienteResp getRegistraTransaccionesCliente(getRegistraTransaccionesClienteReq parametros)
    {
      getRegistraTransaccionesClienteResp respuesta = new getRegistraTransaccionesClienteResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        System.out.println("clienteId: "+parametros.getClienteId());
        System.out.println("sesionId: "+parametros.getSesionId());
        System.out.println("TipoMovimiento "+parametros.getTipoMovimiento());
        System.out.println("nombreProducto : "+parametros.getNombreProducto());
        System.out.println("numeroTransaccion : "+parametros.getNumeroTransaccion());
        System.out.println("montoTransaccion : "+parametros.getMontoTransaccion());
        System.out.println("montoComision : "+parametros.getMontoComision());
        System.out.println("montoDescuento : "+parametros.getMontoDescuento());
        System.out.println("subtotal : "+parametros.getSubtotal());
        System.out.println("IVA : "+parametros.getIVA());
        System.out.println("IVARetenido : "+parametros.getIVARetenido());
        System.out.println("Retencion5Millar : "+parametros.getRetencion5Millar());
        System.out.println("total : "+parametros.getTotal());
        System.out.println("fechaTransaccion : "+parametros.getFechaTransaccion());
        String param = "|"+parametros.getClienteId()+"|"+parametros.getTipoMovimiento()+"|"+parametros.getNombreProducto()+"|"+parametros.getNumeroTransaccion()+"|"+parametros.getMontoTransaccion()+"|"+parametros.getMontoComision()+"|"+parametros.getMontoDescuento()+"|"+ffh.format(parametros.getFechaTransaccion())+"|"+parametros.getSubtotal()+"|"+parametros.getIVA()+"|"+parametros.getIVARetenido()+"|"+parametros.getRetencion5Millar()+"|"+parametros.getTotal();
        System.out.println("param : "+param);
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PKG.get_RegistraTrans_Cte_PRC(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, param);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.execute();
            String datos = stmt.getString(3);
            System.out.println("datos: "+datos);
            if(stmt.getString(4).equals("0"))
            {
                String[] saldo = datos.split("\\|");
                respuesta.setSaldoActual(Float.valueOf(saldo[0]));
                respuesta.setSaldoFavor(Float.valueOf(saldo[1]));
            }
                respuesta.setOperacionExitosa(stmt.getString(4).equals("0")?true:false);
                respuesta.setErrorCode(stmt.getString(5));
                respuesta.setErrorMsg(stmt.getString(6));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
    }


    public getEstadoCuentaClienteResp getEstadoCuentaCliente(getEstadoCuentaClienteReq parametros)
    {
      getEstadoCuentaClienteResp respuesta = new getEstadoCuentaClienteResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        System.out.println("clienteNombre: "+parametros.getClienteNombre());
        System.out.println("clienteRFC: "+parametros.getClienteRFC());
        System.out.println("ClienteNoCuenta: "+parametros.getClienteNoCuenta());
        System.out.println("fechaInicial: "+parametros.getFechaInicial());
        System.out.println("FechaFinal: "+parametros.getFechaFinal());
        String param = "|"+(parametros.getClienteNombre().equals("")?"%":parametros.getClienteNombre())+"|"+(parametros.getClienteRFC().equals("")?"%":parametros.getClienteRFC())+"|"+(parametros.getClienteNoCuenta().equals("")?"%":parametros.getClienteNoCuenta())+"|"+ffh.format(parametros.getFechaInicial())+"|"+ffh.format(parametros.getFechaFinal());
        System.out.println("param: "+param);
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PKG.get_EstadoCuenta_Cliente_prc(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, param);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.execute();
            String datos = stmt.getString(3);
            System.out.println("datos: "+datos);
            if(stmt.getString(4).equals("0") && datos!=null)
            {
                System.out.println("Entra a agregar los datos");
                String[] registros = datos.split("\\^");
                System.out.println("registros: "+registros.length);
                List<ClienteTransaccion> listado = new ArrayList<ClienteTransaccion>();
                respuesta.setCliente(new Cliente(registros[0].split("\\|")));
                for(int i=1; i<registros.length;i++)
                {
                    //System.out.println(""+registros[i]);
                     String[] registro = registros[i].split("\\|");
                     listado.add(new ClienteTransaccion(registro));
                }
                respuesta.setClienteTransacciones(listado);
            }
                respuesta.setOperacionExitosa(stmt.getString(4).equals("0")?true:false);
                respuesta.setErrorCode(stmt.getString(5));
                respuesta.setErrorMsg(stmt.getString(6));
            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
         catch (javax.ejb.EJBException ex){
            ex.printStackTrace();
            return respuesta;
        }
    }

  private byte[]  getfacturaPDF(String rutaArchivo) throws FileNotFoundException, IOException
  {
       System.out.println("getfacturaPDF – " + rutaArchivo);
      
       rutaArchivo=rutaArchivo.replace("C:\\_tomcat626\\webapps\\EfacturaT\\certificados", "\\\\Facturas.estrellaroja.com.mx\\certificados");
      System.out.println("getfacturaPDF – " + rutaArchivo);
      byte[] buf = new byte[1024];
       FileInputStream fis = new FileInputStream(rutaArchivo);
        //System.out.println(file.exists() + "!!");
        //InputStream in = resource.openStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum); //no doubt here is 0
                //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
               // System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        buf= bos.toByteArray();

      return buf;

  }

  private String getContenidoXML (String rutaArchivo) throws Exception {
  System.out.println("getContenidoXML – " + rutaArchivo);
 // rutaArchivo=rutaArchivo.replace("C:\\_tomcat626\\webapps\\EfacturaT\\certificados", "\\\\Facturas.estrellaroja.com.mx\\certificados");

      System.out.println("getfacturaPDF – " + rutaArchivo);
   StringBuffer contenidoArchivo      = new StringBuffer ();
    BufferedReader  br            = null;
      try {
            br      = new BufferedReader (new FileReader(new File (rutaArchivo)));
            String linea;
            while (( linea = br.readLine()) != null){

                  contenidoArchivo.append(linea);
                  contenidoArchivo.append(System.getProperty("line.separator"));
            }
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("Excepcion – " + e.toString());
       } finally {
            if (br!= null) {
                  br.close();
            }
      }
    return contenidoArchivo.toString();
}


  private boolean guarda_archivos_Fact(String Factura_id,byte[] pdf,String xml)
  {
      boolean result = true;

        System.out.println("Factura_id: "+Factura_id);

        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                   
                    "BEGIN "+ ""+paquete+ ".GET_GUARDA_ARCHIVOS(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";

            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setString(1,Factura_id);
            stmt.setBytes(2,pdf );
           //  stmt.setString(2,pdf.toString() );
            stmt.setString(3,xml);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.execute();

            result=(stmt.getString(4).equals("TRUE")?true:false);
            stmt.close();
            if(cnx!=null) cnx.close();


            return result;
        } catch (SQLException ex){
            ex.printStackTrace();
            result =false;
            return result;
        }
         catch (javax.ejb.EJBException ex){
            ex.printStackTrace();
            result =false;
            return result;
        }

  }


}

