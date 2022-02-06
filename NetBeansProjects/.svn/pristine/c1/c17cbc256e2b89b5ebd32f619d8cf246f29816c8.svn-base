/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.paquer.solicitudes;

import WS_CONTROL.entidades.Perfil;
import WS_CONTROL.getLoginReq;
import WS_CONTROL.getLoginResp;
import WS_CONTROL.paquer.entidades.Guia;
import WS_CONTROL.paquer.entidades.Paquete;
import WS_CONTROL.paquer.entidades.Ruta;
import WS_CONTROL.paquer.entidades.RutaServicios;
import WS_CONTROL.paquer.entidades.Servicio;
import WS_CONTROL.paquer.getCancelarGuiaReq;
import WS_CONTROL.paquer.getCancelarGuiaResp;
import WS_CONTROL.paquer.getOperacionesGuiaReq;
import WS_CONTROL.paquer.getOperacionesGuiaResp;
import WS_CONTROL.paquer.getRutaServiciosReq;
import WS_CONTROL.paquer.getRutaServiciosResp;
import WS_CONTROL.paquer.getServiciosReq;
import WS_CONTROL.paquer.getServiciosResp;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.sql.DataSource;
import oracle.jdbc.driver.OracleCallableStatement;

/**
 *
 * @author vgonzalez
 */
@Stateless
public class wsPaquersFacadeBean implements wsPaquersFacadeBeanRemote {
    private SimpleDateFormat ffh = new SimpleDateFormat("dd/MM/yyyy HH:mm");


   @Resource(name = "REP_CONTROL_DB")
    private DataSource dataSource;

    public getServiciosResp getServicios(getServiciosReq parametros)
    {
      getServiciosResp respuesta = new getServiciosResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        System.out.println("Clasificacion: "+parametros.getClasificacion());
        String param = ""+parametros.getClasificacion();
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PAQUER_PKG.GET_SERVICIOS_PRC(?, ?, ?, ?, ?, ?); " +
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
                List<Servicio> listado = new ArrayList<Servicio>();
                for(int i=0; i<registros.length;i++)
                {
                    System.out.println(""+registros[i]);
                     String[] registro = registros[i].split("\\|");
                     listado.add(new Servicio(registro));
                }
                respuesta.setServicios(listado);
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
        }catch (EJBException ex){
            ex.printStackTrace();
            return respuesta;
        }
    }

  public getRutaServiciosResp getRutaServicios(getRutaServiciosReq parametros)
    {
      getRutaServiciosResp respuesta = new getRutaServiciosResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);
        String param = '^'+parametros.getCiudadOrigen()+"^"+parametros.getCPOrigen()+"^"+parametros.getCiudadDestino()+"^"+parametros.getCPDestino()+"^"+parametros.getClasificacion();
        System.out.println("parametros: "+param);
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PAQUER_PKG.GET_RUTA_SERVICIOS_PRC(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, ""+param);
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
                String[] registrosRutas = datos.split("~");
                List<RutaServicios> rutas = new ArrayList<RutaServicios>();
                for (int j=0; j<registrosRutas.length;j++)
                {
                    RutaServicios reg = new RutaServicios();
                    String[] registros = registrosRutas[j].split("\\^");
                    System.out.println("registros: "+registros.length);

                    List<Servicio> servicios = new ArrayList<Servicio>();
                    String[] registroRuta = registros[0].split("\\|");
                    System.out.println(""+registroRuta);
                    Ruta ruta =  new Ruta(registroRuta);
                    for(int i=1; i<registros.length;i++)
                    {
                         System.out.println(""+registros[i]);
                         servicios.add(new Servicio(registros[i].split("\\|")));
                    }
                    reg.setRuta(ruta);
                    reg.setServicios(servicios);
                    rutas.add(reg);
                }
                respuesta.setRutasServicios(rutas);
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

public getOperacionesGuiaResp getOperacionesGuia(getOperacionesGuiaReq parametros)
    {
        getOperacionesGuiaResp respuesta = new getOperacionesGuiaResp();
        long totalpaquetes=0;
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        System.out.println("operacion : "+parametros.getOperacion());
        Guia g = parametros.getGuia();
        //guia^ruta^servios^paquetes^datosExtra
        String param = "~^|-1";
        param = param+"~^|"+g.getRuta().getRutaId()+"|"+g.getRuta().getOrigen()+"|"+g.getRuta().getDestino()+"|"+g.getRuta().getDistancia()+"|"+g.getRuta().getAlianza();
        param = param+"~";
        for(Servicio s : g.getServicios())
            param = param+"^|"+s.getServicioId()+"|"+s.getCodigo()+"|"+s.getDescripcion()+"|"+s.getClasificaTipoEnvio()+"|"+s.getRequiereRetencion()+"|"+s.getAplicaDescuento()+"|"+s.getClasificaLpId();
        for(Paquete p : g.getPaquetes())
            totalpaquetes= totalpaquetes + p.getCantidad();
        param = param+"~";
        for(Paquete p : g.getPaquetes())
            param = param+"^|"+p.getAlto()+"|"+p.getAncho()+"|"+p.getLargo()+"|"+p.getPeso()+"|"+p.getContenido()+"|"+p.getNumIni()+"|"+p.getCantidad()+"|"+p.getTipoEnvio()+"|"+p.getPesoMayor(!parametros.getTipoVenta().equals("MOSTRADOR"));
        param = param+"~";
        param = param+"^|"+totalpaquetes+"|"+parametros.getTipoVenta()+"|"+parametros.getGuia().getPaquetes().get(0).getTipoEnvio()+"|"+(parametros.getGuia().getValorDeclarado()==null?0:parametros.getGuia().getValorDeclarado());
        System.out.println("param : "+param);
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PAQUER_PKG.get_Operaciones_Guia_PRC(?, ?, ?, ?, ?, ?, ?); " +
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
            if(stmt.getString(5).equals("0") && datos!=null)
            {
                System.out.println("Entra a agregar los datos");
                String[] registros = datos.split("\\^");
                System.out.println("registros: "+registros.length);
                respuesta.setGuiaId(new BigDecimal(registros[0]));
                List<Servicio> listado = new ArrayList<Servicio>();
                for(int i=1; i<registros.length;i++)
                {
                     System.out.println(""+registros[i]);
                     String[] registro = registros[i].substring(1).split("\\|");
                     Servicio s = new Servicio(registro);
                     s.setPrecio(registro);
                     listado.add(s);
                }
                respuesta.setServicios(listado);
            }
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

    public getCancelarGuiaResp getCancelarGuia(getCancelarGuiaReq parametros)
    {
      getCancelarGuiaResp respuesta = new getCancelarGuiaResp();
        respuesta.setErrorCode("ER-001");
        respuesta.setErrorMsg("La operacion no pudo ser completada");
        respuesta.setOperacionExitosa(false);

        System.out.println("noGuia : "+parametros.getNumeroGuia());
        Connection cnx=null;
        OracleCallableStatement stmt=null;
        try {
            cnx = dataSource.getConnection();
            String q1 =
                    "BEGIN "+
                    "ER_CONTROL_PAQUER_PKG.get_cancela_guia_PRC(?, ?, ?, ?, ?, ?); " +
                    "COMMIT; " +
                    "EXCEPTION " +
                    "WHEN OTHERS THEN " +
                    "ROLLBACK;" +
                    "RAISE; "+
                    "END;";
            stmt = (OracleCallableStatement)cnx.prepareCall(q1);
            stmt.setLong(1,parametros.getSesionId());
            stmt.setString(2, parametros.getNumeroGuia());
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



}
