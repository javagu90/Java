/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.TMS.Solicitud;

import WS_CONTROL.TMS.entidades.RutaItinerario;
import WS_CONTROL.TMS.entidades.RutaItinerarios;
import WS_CONTROL.TMS.entidades.Tramo;
import WS_CONTROL.TMSS.getRutasItinerariosReq;
import WS_CONTROL.TMSS.getRutasItinerariosResp;

import java.sql.Connection;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.annotation.Resource;
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

import javax.annotation.Resource;
import javax.smartcardio.ATR;

import oracle.jdbc.OraclePreparedStatement;


/**
 *
 * @author brojas
 */
@Stateless
public class wsTMSFacadeBean implements wsTMSFacadeBeanRemote {

 @PersistenceContext(unitName="ER_WS_CONTROL_EJBPU")
 private EntityManager em;
 private String DBLINK = "@TMS_LINK.ESTRELLAROJA.COM.MX";
 private String paquete ="ER_CONTROL_TMS_PKG";
 private String msgSessionNV ="Session ID no valido.";
 private String msgOperacionNV = "Operación Invalida";


 @Resource(name = "REP_CONTROL_DB")
    private DataSource dataSource;


   public getRutasItinerariosResp getServicios(getRutasItinerariosReq parametros)
    {
        getRutasItinerariosResp respuesta = new getRutasItinerariosResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);
        System.out.println("getProductosCliente SesionId: "+parametros.getSesionId());
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
                    "BEGIN "+paquete+ ".GET_SERVICIOS_PRC(?, ?, ?, ?, ?, ?, ? ,?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";
//GET_SERVICIOS_PRC (  P_SESION_ID IN NUMBER,  P_SERVICIO IN VARCHAR2, P_ORIGEN IN VARCHAR2, P_DESTINO IN VARCHAR2,
  //                             P_DATOS_ENCONTRADOS    OUT CLOB,  P_OPERACION_EXITOSA   OUT VARCHAR2, P_ERROR_CODE   OUT VARCHAR2,  P_ERROR_MSJ   OUT VARCHAR2  ) IS
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2,parametros.getServicio());
            stmt.setString(3,parametros.getOrigen());  
            stmt.setString(4,parametros.getDestino());

            stmt.registerOutParameter(5, java.sql.Types.CLOB);
            stmt.registerOutParameter(6, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(7, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(8, java.sql.Types.VARCHAR);
            stmt.execute();

            String datos = stmt.getString(5);
            System.out.println("datos: "+datos);
            StringTokenizer st_token, st_token1 , st_token2, st_token3= null;
            if( datos!=null && stmt.getString(6).equals("TRUE"))
            {
                List<RutaItinerarios> listado = new ArrayList<RutaItinerarios>();
                 List<RutaItinerario> listadoRutas = new ArrayList<RutaItinerario>();
                  List<Tramo> listadoTramo = new ArrayList<Tramo>();
                  System.out.println("Entra a agregar los datos");
                  st_token1 = new StringTokenizer(datos,"^");
                  String srrtServicio ="", strRutas="", strTramos="";
                  RutaItinerarios   servicio = new RutaItinerarios();
                  String  str_servicio="";
                  RutaItinerario ruta = new RutaItinerario();

                  while (st_token1.hasMoreElements()) {
                      servicio = new RutaItinerarios();
                           srrtServicio= st_token1.nextElement().toString();
                             //  Rutas
                            st_token2 = new StringTokenizer(srrtServicio,"~");

                            str_servicio = st_token2.nextElement().toString();
                           System.out.println("  ****************      Servicio "+str_servicio);

                           st_token = new StringTokenizer(str_servicio,"|");
                           servicio.setEmpresa(st_token.nextToken());
                           servicio.setServicio(st_token.nextToken());
                           servicio.setNombreServicio(st_token.nextToken());
 System.out.println("  ****************      Empresa "+servicio.getEmpresa());
  System.out.println("  ****************      Servicio "+servicio.getServicio());
   System.out.println("  ****************      ClaveServicio "+servicio.getNombreServicio());

                           listadoRutas = new ArrayList<RutaItinerario>();
                            while (st_token2.hasMoreElements()) {
                                  strRutas = st_token2.nextElement().toString();
                                   System.out.println("-------  strRuta-Trama "+strRutas);
                                   //tramos
                                   st_token3 = new StringTokenizer(strRutas,"[");
                                  String str_Ruta = st_token3.nextElement().toString();
                                   System.out.println("Ruta "+st_token);
                                   ruta = new RutaItinerario();
                                   st_token = new StringTokenizer(str_Ruta,"|");

                                   System.out.println("Ruta "+st_token);
                                   ruta.setRuta_ID(Long.parseLong(st_token.nextToken()));
                                   ruta.setNombre(st_token.nextToken());
                                   ruta.setOrigen(st_token.nextToken());
                                   ruta.setDestino(st_token.nextToken());
                                   ruta.setOrigenDescripcion(st_token.nextToken());
                                   ruta.setDestinoDescripcion(st_token.nextToken());


                                   listadoTramo = new ArrayList<Tramo>();
                                    while (st_token3.hasMoreElements()) {
                                          strTramos = st_token3.nextElement().toString();
                                           System.out.println("strTramos "+strTramos);
                                            st_token = new StringTokenizer(strTramos,"|");
                                            Tramo tramo = new Tramo();
                                            tramo.setTramo_Numero(Long.parseLong(st_token.nextToken()));
                                            tramo.setOrigen(st_token.nextToken());
                                            tramo.setDestino(st_token.nextToken());
                                            tramo.setTiempo_Desface(Long.parseLong(st_token.nextToken()));


                                            tramo.setOrigenDescripcion(st_token.nextToken());
                                            tramo.setDestinoDescripcion(st_token.nextToken());

                                            listadoTramo.add(tramo);  


                                     }
                                   ruta.setTramo(listadoTramo);
                                   listadoRutas.add(ruta);
                      }
                      servicio.setRuta(listadoRutas);
                      listado.add(servicio);
                }


               /* RutaItinerarios servicio = new RutaItinerarios();
                STtoken1 = new StringTokenizer("~");
                  String[] registros = datos.split("\\^");
                for(int i=0; i<registros.length;i++)
                {
                    System.out.println(""+registros[i]);
                     String[] registro = registros[i].split("\\|");

                     listado.add(new ErProductoCliente(registro));
                    System.out.println(""+listado);
                }*/
                 respuesta.setServicios(listado);
                            respuesta.setOperacionExitosa(stmt.getString(6).equals("TRUE")?true:false);
                respuesta.setErrorCode(stmt.getString(7));
                respuesta.setErrorMsg(stmt.getString(8));
            }
            else{
                respuesta.setOperacionExitosa(stmt.getString(6).equals("FALSE")?true:false);
                respuesta.setErrorCode("");     
                respuesta.setErrorMsg("No existen datos para los parametros seleccionados."); }


            stmt.close();
            if(cnx!=null) cnx.close();
            System.out.println("respuesta: "+respuesta.isOperacionExitosa());

            return respuesta;
        } catch (SQLException ex){
            ex.printStackTrace();
            return respuesta;
        }
      }

        public String getFecha(){
        return em.createNativeQuery("select to_char(SYSDATE,'DD/MM/RRRR HH24:MI') from dual ").getSingleResult().toString();
    }

}
