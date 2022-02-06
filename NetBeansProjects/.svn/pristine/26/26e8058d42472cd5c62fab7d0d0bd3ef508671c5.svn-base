/*
 * VendERTransacciones.java
 *
 * Created on 4 de septiembre de 2009, 12:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.transacciones;


import com.sun.msv.verifier.regexp.StringToken;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.StringTokenizer;
import java.util.Vector;
import tms_encriptacion.EncriptarMD5;
import vendERMobile.TiempoAire.clases.VentaTiempoAireRequest;
import vendERMobile.TiempoAire.clases.VentaTiempoAireResponser;
import vendERMobile.clases.abrirCerrarVentaAbordo.AbrirCerrarVentaAbordoRequest;
import vendERMobile.clases.abrirCerrarVentaAbordo.AbrirCerrarVentaAbordoResponse;
import vendERMobile.clases.asientos.AsientosRequest;
import vendERMobile.clases.asientos.AsientosResponse;
import vendERMobile.clases.cancelarBoletos.CancelarBoletosResponse;
import vendERMobile.clases.cancelarBoletos.CancelarBoletosRequest;
import vendERMobile.clases.canjeBoletoAbierto.CanjeBoletoAbiertoRequest;
import vendERMobile.clases.canjeBoletoAbierto.CanjeBoletoAbiertoResponse;
import vendERMobile.clases.corridas.CorridasRequest;
import vendERMobile.clases.corridas.CorridasResponse;
import vendERMobile.clases.datos.AsientoAutobus;
import vendERMobile.clases.datos.Corridas;
import vendERMobile.clases.datos.DatoGenerico;
import vendERMobile.clases.datos.Itinerario;
import vendERMobile.clases.datos.ParametrosIniciales;
import vendERMobile.clases.datos.Permisos;
import vendERMobile.clases.datos.Rutas;
import vendERMobile.clases.datos.Tarifas;
import vendERMobile.clases.datos.TiposPago;
import vendERMobile.clases.datos.TiposPasajero;
import vendERMobile.clases.despachoTarjetas.Folio;
import vendERMobile.clases.despachoTarjetas.Pasajero;
import vendERMobile.clases.despachoTarjetas.despacharTarjetaResponse;
import vendERMobile.clases.despachoTarjetas.getdatosResponse;
import vendERMobile.clases.empresas.EmpresasRequest;
import vendERMobile.clases.empresas.EmpresasResponse;
import vendERMobile.clases.estados.EstadosRequest;
import vendERMobile.clases.estados.EstadosResponse;
import vendERMobile.clases.fecha.FechaRequest;
import vendERMobile.clases.fecha.FechaResponse;
import vendERMobile.clases.itinerarios.ItinerariosRequest;
import vendERMobile.clases.itinerarios.ItinerariosResponse;
import vendERMobile.clases.itinerariosruta.ItinerariosPorRutaRequest;
import vendERMobile.clases.itinerariosruta.ItinerariosPorRutaResponse;
import vendERMobile.clases.logIn.LoginReq;
import vendERMobile.clases.logIn.LoginResp;
import vendERMobile.clases.logOut.LogOutRequest;
import vendERMobile.clases.logOut.LogOutResponse;
import vendERMobile.clases.ocuparAsientos.OcuparAsientosRequest;
import vendERMobile.clases.ocuparAsientos.OcuparAsientosResponse;
import vendERMobile.clases.parametrosIniciales.ParametrosInicialesRequest;
import vendERMobile.clases.parametrosIniciales.ParametrosInicialesResponse;
import vendERMobile.clases.permisos.PermisosRequest;
import vendERMobile.clases.permisos.PermisosResponse;
import vendERMobile.clases.recoleccion.RecoleccionRequest;
import vendERMobile.clases.recoleccion.RecoleccionResponse;
import vendERMobile.clases.rutas.RutasRequest;
import vendERMobile.clases.rutas.RutasResponse;
import vendERMobile.clases.servicios.ServiciosRequest;
import vendERMobile.clases.servicios.ServiciosResponse;
import vendERMobile.clases.tarifas.TarifasRequest;
import vendERMobile.clases.tarifas.TarifasResponse;
import vendERMobile.clases.tiposNoPermitidos.TipoNoPermitidosRequest;
import vendERMobile.clases.tiposNoPermitidos.TipoNoPermitidosResponse;
import vendERMobile.clases.tiposPago.TipoPagoRequest;
import vendERMobile.clases.tiposPago.TipoPagoResponse;
import vendERMobile.clases.tiposPasajero.TiposPasajeroRequest;
import vendERMobile.clases.tiposPasajero.TiposPasajeroResponse;
import vendERMobile.clases.transferirBoletos.TransferirBoletosRequest;
import vendERMobile.clases.transferirBoletos.TransferirBoletosResponse;
import vendERMobile.clases.venderBoletos.VenderBoletosRequest;
import vendERMobile.clases.venderBoletos.VenderBoletosResponse;
import vendERSolicitudv3.VendERMobileFacadeRemote;

/**
 *
 * @author asolis
 */
public class VendERTransacciones {
    String errorMsg;
    VendERValidaCampos validaCampos;
    EstadoTransaccion estadoTransaccion;
    String resp;
    String vacios = "";
    /**
     * Creates a new instance of VendERTransacciones
     */
    public VendERTransacciones() {
        estadoTransaccion = new EstadoTransaccion();
        validaCampos = new VendERValidaCampos();
    }

    /**
     * Solicitud de Inicio de Sesión.
     *
     *
     *
     *
     * @param tmsTxVtaFacadeBean Clase EJB
     * @param LoginReq Clase LoginReq
     * @return ClaseloginResponse1e
     */
    public LoginResp loginRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, LoginReq loginReq) {
        // TODO implement operation
        System.out.println("********************** Inicia loginRequest ********************** ");
        Vector respuesta = new Vector();
        LoginResp loginResponse = new LoginResp();
        if(loginReq.getSesion()==null || (errorMsg=validaCampos.solicitudLogin(loginReq))!=null){
            estadoTransaccion.setEstadoCodigo(-3, errorMsg,"loginRequest");
            loginResponse.setSuccess(false);
        }
        else{
            //String NOMBRE_EQUIPO=LoginReq.getSesion().getIdCanal()+"_"+LoginReq.getSesion().getIdSucursal()+"_"+LoginReq.getSesion().getIdEstacionTrabajo();
            String NOMBRE_EQUIPO= "";
            try{
                NOMBRE_EQUIPO=loginReq.getSesion().getIdEstacionTrabajo();
                NOMBRE_EQUIPO = (NOMBRE_EQUIPO.length()>99? NOMBRE_EQUIPO.substring(0,99):NOMBRE_EQUIPO);
            }catch(Exception ex){
                System.out.println("Nombre de Equipo "+loginReq.getSesion().getIdEstacionTrabajo());
                ex.printStackTrace();
            }
            String valor=null;
            try {
                valor = new EncriptarMD5().encriptar(loginReq.getSesion().getContrasenia());
            } catch (Exception ex) {
                valor = ex.getMessage();
            }
            if(valor == null){
                estadoTransaccion.setEstadoCodigo(-6, (valor.length()>99?valor.substring(0,99):valor), "loginRequest");
                //success = false;
                loginResponse.setSuccess(false);
            }
            else{
                //System.out.println("Login "+loginReq.getSesion().getNumUsuario()+" "+valor+" "+loginReq.getSesion().getIdSucursal()+" "+NOMBRE_EQUIPO);
                respuesta = vendERMobileFacadeBean.logIn(loginReq.getSesion().getNumUsuario(),valor,NOMBRE_EQUIPO, loginReq.getSesion().getIdSucursal());
                //System.out.println("respuesta "+respuesta+ " size "+respuesta.size());
                if(respuesta == null&&respuesta.size() == 0){
                    estadoTransaccion.setEstadoCodigo(-1, null,"loginRequest");
                    loginResponse.setSuccess(false);
                }
                else{
                    if(respuesta.get(0).toString().equals("0")){
                        StringTokenizer token = new StringTokenizer(respuesta.get(2).toString(), "|");
                        //loginReq.getSesion().setIdSesion(respuesta.get(2).toString());
                        loginReq.getSesion().setIdSesion(token.nextToken());
                        loginReq.getSesion().setIdEstacionTrabajo(token.nextToken());
                        loginReq.setUid(token.nextToken());
                        loginReq.setNombreUsuario(token.nextToken());
                        loginResponse.setSuccess(true);

                    }else
                        loginResponse.setSuccess(false);

                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "loginRequest");

                    /*_Resultado.setResultado1(resp);
                    if(_Resultado.getProceso1()){
                        if(_Resultado.getSuccess1())
                            LoginReq.getSesion().setIdSesion(_Resultado.getInformacion1());
                        //success = _Resultado.getSuccess1();
                        LoginResp.setSuccess(true);
                        //estadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "LoginReq");
                    }
                    else{
                        estadoTransaccion.setEstadoCodigo(-2, "LoginReq", null);
                        success = false;
                        LoginResp.setSuccess(false);
                    }*/
                }
            }
        }
        loginResponse.setUid(loginReq.getUid());
        //LoginResp.setSuccess(success);
        loginResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "loginRequest");
        loginResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        loginResponse.setSesion(loginReq.getSesion());
        loginResponse.setSesion(loginReq.getSesion());
        loginResponse.setNombreUsuario(loginReq.getNombreUsuario());
        System.out.println("********************** Termina loginRequest ********************** ");
        return loginResponse;
    }

    public PermisosResponse permisosRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, PermisosRequest permisosReq) {
        // Si el usuarioNumero es null, con el uid se busca el usurioId del usuario loggeado actualmente
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia permisosRequest ********************** ");
        PermisosResponse permisosResponse = new PermisosResponse();
        if(permisosReq.getUid()==null || (errorMsg=validaCampos.solicitudPermisos(permisosReq))!=null){
            estadoTransaccion.setEstadoCodigo(-3, errorMsg, "permisosRequest");
            permisosResponse.setSuccess(false);
        }
        else{
            String valor=null;
            //System.out.println("permisosReq.getContrasenia() "+permisosReq.getContrasenia()+" permisosReq.getContrasenia().length() "+permisosReq.getContrasenia().length());
            if(permisosReq.getContrasenia() != null&&!permisosReq.getContrasenia().equals("") && permisosReq.getContrasenia().length() > 0) {
                try {
                    valor = new EncriptarMD5().encriptar(permisosReq.getContrasenia());
                    //System.out.println("Contraseña Encriptada "+valor);
                } catch (Exception ex) {
                    System.out.println("Error al encriptar la contraseña");
                    ex.printStackTrace();
                    valor = ex.getMessage();
                }
            }
            System.out.println("Contraseña Encriptada "+valor);
            if((valor == null)&&permisosReq.getContrasenia().length() > 0){
                estadoTransaccion.setEstadoCodigo(-6, valor, "permisosRequest");
                permisosResponse.setSuccess(false);
            }
            else{
                //System.out.println("getPermisos "+permisosReq.getNumUsuario()+" "+valor);
                respuesta = vendERMobileFacadeBean.getPermisos(permisosReq.getcajaNombre(), permisosReq.getNumUsuario(),permisosReq.getpermisoClave(), permisosReq.getUid(),(valor== null)?"":valor);
                //System.out.println("respuesta "+respuesta);
                if(respuesta == null){
                    estadoTransaccion.setEstadoCodigo(-1, null, "permisosRequest");
                    permisosResponse.setSuccess(false);
                }else {
                    if(respuesta.get(0).toString().equals("getCursor")){
                        estadoTransaccion.setEstadoCodigo(-9, respuesta.get(1).toString(), "permisosRequest");
                        permisosResponse.setSuccess(false);
                    }
                    else{
                        estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "permisosRequest");
                        if(respuesta.get(0).toString().equals("0")){
                            permisosResponse.setSuccess(true);
                            permisosResponse.setPermisos(setPermisosVector((Vector) respuesta.get(4)));
                            if(permisosResponse.getPermisos() == null){
                                estadoTransaccion.setEstadoCodigo(46, permisosReq.getNumUsuario(), "permisosRequest");
                                permisosResponse.setSuccess(false);
                           }
                        }
                        else
                           permisosResponse.setSuccess(false);
                    }
                }
            }
        }
        ////System.out.println("vendERMobileFacadeBean "+vendERMobileFacadeBean);
        permisosResponse.setUid(permisosReq.getUid());
        permisosResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "permisosRequest");
        permisosResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        System.out.println("********************** Termina permisosRequest ********************** ");
        return permisosResponse;
    }

    public ParametrosInicialesResponse paramaetrosInicialesRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, ParametrosInicialesRequest parametrosInicialesReq) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia paramaetrosInicialesRequest ********************** ");
        ParametrosInicialesResponse parametrosInicialesRes= new ParametrosInicialesResponse();
        if(parametrosInicialesReq.getUid()==null || (errorMsg=validaCampos.solicitudParametrosIniciales(parametrosInicialesReq.getUid()))!=null){
            estadoTransaccion.setEstadoCodigo(-3,errorMsg, "paramaetrosInicialesRequest");
            parametrosInicialesRes.setSuccess(false);
        }
        else{
            vacios = "";
            respuesta = vendERMobileFacadeBean.getParametrosIniciales(parametrosInicialesReq.getCajaNombre(), parametrosInicialesReq.getCampoTipo().toLowerCase(),parametrosInicialesReq.getUid());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, null, "paramaetrosInicialesRequest");
                parametrosInicialesRes.setSuccess(false);
            }else {
                if(respuesta.get(0).toString().equals("getCursor")){
                        estadoTransaccion.setEstadoCodigo(-9, respuesta.get(1).toString(), "paramaetrosInicialesRequest");
                        parametrosInicialesRes.setSuccess(false);
                }
                else{
                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "paramaetrosInicialesRequest");
                    if(respuesta.get(0).toString().equals("0")){
                        parametrosInicialesRes.setSuccess(true);
                        parametrosInicialesRes.setParametrosIniciales(setParamIniciales((Vector) respuesta.get(4)));
                        System.out.println("Lista de Parametros Inciales Vacios "+vacios);
                        if(vacios.length() > 0){
                            parametrosInicialesRes.setSuccess(false);
                            estadoTransaccion.setEstadoCodigo(-10, vacios.substring(0, vacios.length()-1).toUpperCase(), "paramaetrosInicialesRequest");
                        }
                        if(parametrosInicialesRes.getParametrosIniciales() == null){
                                estadoTransaccion.setEstadoCodigo(45,  null, "paramaetrosInicialesRequest");
                                parametrosInicialesRes.setSuccess(false);
                        }
                    }
                    else
                        parametrosInicialesRes.setSuccess(false);
                }
            }
        }
        ////System.out.println("vendERMobileFacadeBean "+vendERMobileFacadeBean);
        parametrosInicialesRes.setUid(parametrosInicialesReq.getUid());
        parametrosInicialesRes.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "paramaetrosInicialesRequest");
        parametrosInicialesRes.setErrorMsg(estadoTransaccion.getParentMsg());
        System.out.println("********************** Termina paramaetrosInicialesRequest ********************** ");
        return parametrosInicialesRes;
    }

    public TarifasResponse tarifasRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, TarifasRequest tarifasReq) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia tarifasRequest ********************** ");
        TarifasResponse tarifasRes= new TarifasResponse();
        if(tarifasReq.getUid()==null || (errorMsg=validaCampos.solicitudTarifas(tarifasReq.getUid(),tarifasReq.getIdRuta()))!=null){
            estadoTransaccion.setEstadoCodigo(-3, errorMsg, "tarifasRequest");
            tarifasRes.setSuccess(false);
        }
        else{
            respuesta = vendERMobileFacadeBean.getTarifas(tarifasReq.getCajaNombre(), tarifasReq.getUid(), String.valueOf(tarifasReq.getIdRuta()));
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, "tarifasRequest", null);
                tarifasRes.setSuccess(false);
            }else {
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, respuesta.get(1).toString(), "tarifasRequest");
                    tarifasRes.setSuccess(false);
                }
                else{
                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "tarifasRequest");
                    if(respuesta.get(0).toString().equals("0")){
                        tarifasRes.setSuccess(true);
                        tarifasRes.setTarifas(setTarifas((Vector) respuesta.get(4)));
                         if(tarifasRes.getTarifas() == null){
                                estadoTransaccion.setEstadoCodigo(45, null, "tarifasRequest");
                                tarifasRes.setSuccess(false);
                         }
                    }else
                        tarifasRes.setSuccess(false);
                }
            }
        }
        //System.out.println("vendERMobileFacadeBean "+vendERMobileFacadeBean);
        tarifasRes.setUid(tarifasReq.getUid());
        tarifasRes.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "tarifasRequest");
        tarifasRes.setErrorMsg(estadoTransaccion.getParentMsg());
        System.out.println("********************** Termina tarifasRequest ********************** ");
        return tarifasRes;
    }


    public TiposPasajeroResponse tipoPasajeroRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, TiposPasajeroRequest tiposPasajeroReq) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia tipoPasajeroRequest ********************** ");
        TiposPasajeroResponse tiposPasajeroRes= new TiposPasajeroResponse();
        if(tiposPasajeroReq.getUid()==null || (errorMsg=validaCampos.solicitudParametrosIniciales(tiposPasajeroReq.getUid()))!=null){
            estadoTransaccion.setEstadoCodigo(-3, errorMsg, "tipoPasajeroRequest");
            tiposPasajeroRes.setSuccess(false);
        }
        else{
            respuesta = vendERMobileFacadeBean.getTipoPasajeros(tiposPasajeroReq.getCajaNombre(), tiposPasajeroReq.getUid());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1,  null, "tipoPasajeroRequest");
                tiposPasajeroRes.setSuccess(false);
            }else {
                estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "tipoPasajeroRequest");
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, respuesta.get(1).toString(), "tipoPasajeroRequest");
                    tiposPasajeroRes.setSuccess(false);
                }
                else{
                    if(respuesta.get(0).toString().equals("0")){
                        tiposPasajeroRes.setSuccess(true);
                        tiposPasajeroRes.setTiposPasajero(setTiposPasajero((Vector) respuesta.get(4)));
                        if(tiposPasajeroRes.getTiposPasajero() == null){
                                estadoTransaccion.setEstadoCodigo(45, null, "tipoPasajeroRequest");
                                tiposPasajeroRes.setSuccess(false);
                         }
                    }
                    else
                    tiposPasajeroRes.setSuccess(false);

                }
            }
        }
        //System.out.println("vendERMobileFacadeBean "+vendERMobileFacadeBean);
        tiposPasajeroRes.setUid(tiposPasajeroReq.getUid());
        //LoginResp.setSuccess(success);
        tiposPasajeroRes.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "tipoPasajeroRequest");
        tiposPasajeroRes.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
		System.out.println("********************** Termina tipoPasajeroRequest ********************** ");
        return tiposPasajeroRes;
    }

   public TipoPagoResponse tipoPagoRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, TipoPagoRequest tiposPagoReq) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia tipoPagoRequest ********************** ");
        TipoPagoResponse tiposPagoRes= new TipoPagoResponse();
        if(tiposPagoReq.getUid()==null || (errorMsg=validaCampos.solicitudParametrosIniciales(tiposPagoReq.getUid()))!=null){
            estadoTransaccion.setEstadoCodigo(-3, "permisosRequest",errorMsg);
            tiposPagoRes.setSuccess(false);
        }
        else{
            respuesta = vendERMobileFacadeBean.getTipoPago(tiposPagoReq.getCajaNombre(), tiposPagoReq.getUid());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, null, "tipoPagoRequest");
                tiposPagoRes.setSuccess(false);
            }else {
                estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "tipoPagoRequest");
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, respuesta.get(1).toString(), "tipoPagoRequest");
                    tiposPagoRes.setSuccess(false);
                }
                else{
                    if(respuesta.get(0).toString().equals("0")){
                        tiposPagoRes.setSuccess(true);
                        tiposPagoRes.setTiposPago(setTiposPago((Vector) respuesta.get(4)));
                        if(tiposPagoRes.getTiposPago() == null){
                                estadoTransaccion.setEstadoCodigo(45, null, "tipoPagoRequest");
                                tiposPagoRes.setSuccess(false);
                         }
                    }else
                        tiposPagoRes.setSuccess(false);
                }
            }
        }
        //System.out.println("vendERMobileFacadeBean "+vendERMobileFacadeBean);
        tiposPagoRes.setUid(tiposPagoReq.getUid());
        //LoginResp.setSuccess(success);
        tiposPagoRes.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "tipoPagoRequest");
        tiposPagoRes.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
		System.out.println("********************** Termina tipoPagoRequest ********************** ");
        return tiposPagoRes;
    }

    public ServiciosResponse serviciosRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, ServiciosRequest serviciosRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia serviciosRequest ********************** ");
        ServiciosResponse serviciosResponse= new ServiciosResponse();
        if(serviciosRequest.getUid()==null || (errorMsg=validaCampos.solicitudParametrosIniciales(serviciosRequest.getUid()))!=null){
            estadoTransaccion.setEstadoCodigo(-3, "serviciosRequest",errorMsg);
            serviciosResponse.setSuccess(false);
        }
        else{
            respuesta = vendERMobileFacadeBean.getServicios(serviciosRequest.getCajaNombre(),serviciosRequest.getUid());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, null, "serviciosRequest");
                serviciosResponse.setSuccess(false);
            }else {
                estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "tipoPagoRequest");
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, respuesta.get(1).toString(), "serviciosRequest");
                    serviciosResponse.setSuccess(false);
                }
                else{
                    if(respuesta.get(0).toString().equals("0")){
                        serviciosResponse.setSuccess(true);
                        serviciosResponse.setServicios(setEmpresasServicios((Vector) respuesta.get(4)));
                        if(serviciosResponse.getServicios() == null){
                                estadoTransaccion.setEstadoCodigo(45, null, "serviciosRequest");
                                serviciosResponse.setSuccess(false);
                         }
                    }else
                        serviciosResponse.setSuccess(false);
                }
            }
        }
        //System.out.println("vendERMobileFacadeBean "+vendERMobileFacadeBean);
        serviciosResponse.setUid(serviciosRequest.getUid());
        //LoginResp.setSuccess(success);
        serviciosResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "serviciosRequest");
        serviciosResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
        System.out.println("********************** Termina serviciosRequest ********************** ");
        return serviciosResponse;
    }

    public EmpresasResponse empresasRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, EmpresasRequest empresasRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia empresasRequest ********************** ");
        EmpresasResponse empresasResponse= new EmpresasResponse();
        if(empresasRequest.getUid()==null || (errorMsg=validaCampos.solicitudParametrosIniciales(empresasRequest.getUid()))!=null){
            estadoTransaccion.setEstadoCodigo(-3, "empresasRequest",errorMsg);
            empresasResponse.setSuccess(false);
        }
        else{
            respuesta = vendERMobileFacadeBean.getEmpresas(empresasRequest.getCajaNombre(), empresasRequest.getUid());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, null, "empresasRequest");
                empresasResponse.setSuccess(false);
            }else {
                estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "tipoPagoRequest");
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, respuesta.get(1).toString(), "empresasRequest");
                    empresasResponse.setSuccess(false);
                }
                else{
                    if(respuesta.get(0).toString().equals("0")){
                        empresasResponse.setSuccess(true);
                        empresasResponse.setEmpresas(setEmpresasServicios((Vector) respuesta.get(4)));
                        if(empresasResponse.getEmpresas() == null){
                                estadoTransaccion.setEstadoCodigo(45, null, "empresasRequest");
                                empresasResponse.setSuccess(false);
                         }
                    }else
                        empresasResponse.setSuccess(false);
                }
            }
        }
        //System.out.println("vendERMobileFacadeBean "+vendERMobileFacadeBean);
        empresasResponse.setUid(empresasRequest.getUid());
        //LoginResp.setSuccess(success);
        empresasResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "empresasRequest");
        empresasResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
        System.out.println("********************** Termina empresasRequest ********************** ");
        return empresasResponse;
    }

    public EstadosResponse estadosRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, EstadosRequest estadosRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia estadosRequest ********************** ");
        EstadosResponse estadosResponse= new EstadosResponse();
        if(estadosRequest.getUid()==null || (errorMsg=validaCampos.solicitudParametrosIniciales(estadosRequest.getUid()))!=null){
            estadoTransaccion.setEstadoCodigo(-3, "estadosRequest",errorMsg);
            estadosResponse.setSuccess(false);
        }
        else{
            respuesta = vendERMobileFacadeBean.getEstados(estadosRequest.getCajaNombre(), estadosRequest.getUid());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, null, "estadosRequest");
                estadosResponse.setSuccess(false);
            }else {
                estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "estadosRequest");
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, respuesta.get(1).toString(), "estadosRequest");
                    estadosResponse.setSuccess(false);
                }
                else{
                    if(respuesta.get(0).toString().equals("0")){
                        estadosResponse.setSuccess(true);
                        estadosResponse.setEstados(setEmpresasServicios((Vector) respuesta.get(4)));
                        if(estadosResponse.getEstados() == null){
                                estadoTransaccion.setEstadoCodigo(45, null, "estadosRequest");
                                estadosResponse.setSuccess(false);
                         }
                    }else
                        estadosResponse.setSuccess(false);
                }
            }
        }
        //System.out.println("vendERMobileFacadeBean "+vendERMobileFacadeBean);
        estadosResponse.setUid(estadosRequest.getUid());
        //LoginResp.setSuccess(success);
        estadosResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "estadosRequest");
        estadosResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
        System.out.println("********************** Termina estadosRequest ********************** ");
        return estadosResponse;
    }

    public RutasResponse rutasRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, RutasRequest rutasRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia rutasRequest ********************** ");
        RutasResponse rutasResponse= new RutasResponse();
        if(rutasRequest.getUid()==null || (errorMsg=validaCampos.solicitudParametrosIniciales(rutasRequest.getUid()))!=null){
            estadoTransaccion.setEstadoCodigo(-3, "rutasRequest",errorMsg);
            rutasResponse.setSuccess(false);
        }
        else{
            respuesta = vendERMobileFacadeBean.getRutas(rutasRequest.getcajaNombre() , rutasRequest.getUid());
            System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, null, "rutasRequest");
                rutasResponse.setSuccess(false);
            }else {
                estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "rutasRequest");
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, respuesta.get(1).toString(), "rutasRequest");
                    rutasResponse.setSuccess(false);
                }
                else{
                    if(respuesta.get(0).toString().equals("0")){
                        rutasResponse.setSuccess(true);
                        rutasResponse.setRutas(setRutas((Vector) respuesta.get(4)));
                        if(rutasResponse.getRutas() == null){
                                estadoTransaccion.setEstadoCodigo(45, null, "rutasRequest");
                                rutasResponse.setSuccess(false);
                         }
                    }else
                        rutasResponse.setSuccess(false);
                }
            }
        }
        //System.out.println("vendERMobileFacadeBean "+vendERMobileFacadeBean);
        rutasResponse.setUid(rutasRequest.getUid());
        //LoginResp.setSuccess(success);
        rutasResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "rutasRequest");
        rutasResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
        System.out.println("********************** Termina rutasRequest ********************** ");
        return rutasResponse;
    }

    public RecoleccionResponse recoleccionRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, RecoleccionRequest recoleccionRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia recoleccionRequest ********************** ");
        RecoleccionResponse recoleccionResponse= new RecoleccionResponse();
        if(recoleccionRequest.getUid()==null || (errorMsg=validaCampos.solicitudRecoleccion(recoleccionRequest.getUid(), recoleccionRequest.getUsuarioNumero(), recoleccionRequest.getContrasenia(), String.valueOf(recoleccionRequest.getImporteRecoleccion()), String.valueOf(recoleccionRequest.getCantidadRecoleccion()), recoleccionRequest.getTiposPagos().getTipoPagoClave(), String.valueOf(recoleccionRequest.getTiposPagos().getIdTipoPago())))!=null){
            estadoTransaccion.setEstadoCodigo(-3, errorMsg, "recoleccionRequest");
            recoleccionResponse.setSuccess(false);
        }
        else{
            String valor=null;
            //System.out.println("recoleccionRequest.getContrasenia() "+recoleccionRequest.getContrasenia()+" recoleccionRequest "+recoleccionRequest.getUsuarioNumero());
            if(recoleccionRequest.getContrasenia() != null&&!recoleccionRequest.getContrasenia().equals("") && recoleccionRequest.getContrasenia().length() > 0) {
                try {
                    valor = new EncriptarMD5().encriptar(recoleccionRequest.getContrasenia());
                } catch (Exception ex) {
                    valor = ex.getMessage();
                }
                if(valor == null){
                    estadoTransaccion.setEstadoCodigo(-6, (valor.length()>99?valor.substring(0,99):valor), "recoleccionRequest");
                    recoleccionResponse.setSuccess(false);
                }
            }
            System.out.println("Constraseña Encriptada "+valor);
            respuesta = vendERMobileFacadeBean.getRecoleccion(recoleccionRequest.getcajaNombre(), recoleccionRequest.getUid(), recoleccionRequest.getUsuarioNumero(), (valor== null) ?"":valor, String.valueOf(recoleccionRequest.getTiposPagos().getIdTipoPago()), recoleccionRequest.getTiposPagos().getTipoPagoClave(),  String.valueOf(recoleccionRequest.getCantidadRecoleccion()),  String.valueOf(recoleccionRequest.getImporteRecoleccion()));
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, null, "recoleccionRequest");
                recoleccionResponse.setSuccess(false);
            }else {
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, respuesta.get(1).toString(),"recoleccionRequest");
                    recoleccionResponse.setSuccess(false);
                }
                else{
                    if(respuesta.get(0).toString().equals("0")){
                        recoleccionResponse.setSuccess(true);
                        StringTokenizer token = new StringTokenizer(respuesta.get(2).toString(), "|");
                        recoleccionResponse.setFolioRecoleccion(token.nextToken());
                        recoleccionResponse.setNombreSupervisor(token.nextToken());
                        recoleccionResponse.setNombreUsuario(token.nextToken());
                        //recoleccionResponse.set setTiposPago(setTiposPago((Vector) respuesta.get(4)));
                   }else
                        recoleccionResponse.setSuccess(false);

                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "recoleccionResponse");
                }
            }
        }
        //System.out.println("vendERMobileFacadeBean "+vendERMobileFacadeBean);
        recoleccionResponse.setUid(recoleccionRequest.getUid());
        //LoginResp.setSuccess(success);
        recoleccionResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "recoleccionRequest");
        recoleccionResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());

        return recoleccionResponse;
    }

    public CorridasResponse corridasRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, CorridasRequest corridasRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia corridasRequest ********************** ");
        CorridasResponse corridasResponse= new CorridasResponse();
        if(corridasRequest.getUid()==null || (errorMsg = validaCampos.solicitudCorridas(corridasRequest))!=null){
            estadoTransaccion.setEstadoCodigo(-3, errorMsg, "corridasRequest");
            corridasResponse.setSuccess(false);
        }
        else{
          /*  String uid, String pRutaOrigen, String pOrigen, String pDestino,
          String pFecha1, String pFecha2, String pFecha3, String pFecha4,
          String pServicioClave,String pServicio, String pEmpresa, String pViajeRedondo*/
            respuesta = vendERMobileFacadeBean.getCorridas(corridasRequest.getSesion().getIdEstacionTrabajo(), corridasRequest.getUid(), corridasRequest.getOrigen().getClave(),corridasRequest.getOrigen().getClave(),
                                                corridasRequest.getDestino().getClave(), corridasRequest.getFechaSalida()+" "+((corridasRequest.getHorarioSalida().length() == 0) ? "00:00" : corridasRequest.getHorarioSalida()), corridasRequest.getTipoServicio().getNombre()
                                                ,corridasRequest.getEmpresaNombre().getNombre());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, null, "corridasRequest");
                corridasResponse.setSuccess(false);
            }else {
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, respuesta.get(1).toString(), "corridasRequest");
                    corridasResponse.setSuccess(false);
                }
                else{
                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "corridasResponse");
                    if(respuesta.get(0).toString().equals("0")){
                        corridasResponse.setSuccess(true);
                        if( respuesta.get(4).toString().length() > 0)
                            corridasResponse.setCorridas(setCorridas(respuesta.get(4).toString()));
                        if(corridasResponse.getCorridas() == null){
                            estadoTransaccion.setEstadoCodigo(45, null, "corridasResponse");
                            corridasResponse.setSuccess(false);
                        }
                    }else{
                        corridasResponse.setSuccess(false);
                    }
                }
            }
        }
        corridasResponse.setUid(corridasRequest.getUid());
        //LoginResp.setSuccess(success);
        corridasResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "corridasResponse");
        corridasResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
        System.out.println("********************** Termina corridasRequest ********************** ");
        return corridasResponse;
    }

    public ItinerariosResponse itinerariosRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, ItinerariosRequest itinerariosRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia itinerariosRequest ********************** ");
        ItinerariosResponse itinerariosResponse= new ItinerariosResponse();
        if(itinerariosRequest.getUid()==null || (errorMsg = validaCampos.solicitudItinerarios(itinerariosRequest))!=null){
            estadoTransaccion.setEstadoCodigo(-3, "recoleccionRequest",errorMsg);
            itinerariosResponse.setSuccess(false);
        }
        else{
          /*  String uid, String pRutaOrigen, String pOrigen, String pDestino,
          String pFecha1, String pFecha2, String pFecha3, String pFecha4,
          String pServicioClave,String pServicio, String pEmpresa, String pViajeRedondo*/
            respuesta = vendERMobileFacadeBean.getItinerarios(itinerariosRequest.getSesion().getIdEstacionTrabajo(), itinerariosRequest.getUid(), itinerariosRequest.getDatosIda().getRutaId(), itinerariosRequest.getDatosIda().getOrigen().getClave());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, "recoleccionRequest", null);
                itinerariosResponse.setSuccess(false);
            } else {
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, "permisosRequest", respuesta.get(1).toString());
                    itinerariosResponse.setSuccess(false);
                }
                else{
                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "itinerariosResponse");
                    if(respuesta.get(0).toString().equals("0")){
                        itinerariosResponse.setSuccess(true);
                        if(((Vector) respuesta.get(4)).size() > 0)
                            itinerariosResponse.setItinerarioIda(setItinerario((Vector) respuesta.get(4)));
                        if(itinerariosResponse.getItinerarioIda()== null){
                            estadoTransaccion.setEstadoCodigo(45, "itinerariosResponse", null);
                            itinerariosResponse.setSuccess(false);
                        }
                    }
                    else
                        itinerariosResponse.setSuccess(false);
                }
            }
        }
        itinerariosResponse.setUid(itinerariosRequest.getUid());
        //LoginResp.setSuccess(success);
        itinerariosResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "itinerariosResponse");
        itinerariosResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
        System.out.println("********************** Termina itinerariosRequest ********************** ");
        return itinerariosResponse;
    }

    public ItinerariosPorRutaResponse itinerariosPorRutaRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, ItinerariosPorRutaRequest itinerariosPorRutaRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia ItinerariosPorRutaResponse ********************** ");
        ItinerariosPorRutaResponse itinerariosPorRutaResponse= new ItinerariosPorRutaResponse();
        if(itinerariosPorRutaRequest.getUid()==null || (errorMsg = validaCampos.solicitudItinerarios(itinerariosPorRutaRequest))!=null){
            estadoTransaccion.setEstadoCodigo(-3, "itinerariosPorRutaRequest",errorMsg);
            itinerariosPorRutaResponse.setSuccess(false);
        }
        else{
          /*  String uid, String pRutaOrigen, String pOrigen, String pDestino,
          String pFecha1, String pFecha2, String pFecha3, String pFecha4,
          String pServicioClave,String pServicio, String pEmpresa, String pViajeRedondo*/
            respuesta = vendERMobileFacadeBean.getItinerariosPorRuta(itinerariosPorRutaRequest.getcajaNombre() , itinerariosPorRutaRequest.getUid(), itinerariosPorRutaRequest.getRutaId());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, "itinerariosPorRutaRequest", null);
                itinerariosPorRutaResponse.setSuccess(false);
            } else {
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, "itinerariosPorRutaRequest", respuesta.get(1).toString());
                    itinerariosPorRutaResponse.setSuccess(false);
                }
                else{
                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "itinerariosResponse");
                    if(respuesta.get(0).toString().equals("0")){
                        itinerariosPorRutaResponse.setSuccess(true);
                        if(((Vector) respuesta.get(4)).size() > 0)
                            itinerariosPorRutaResponse.setItinerario(setItinerario((Vector) respuesta.get(4)));
                        if(itinerariosPorRutaResponse.getItinerario()== null){
                            estadoTransaccion.setEstadoCodigo(45, "itinerariosPorRutaRequest", null);
                            itinerariosPorRutaResponse.setSuccess(false);
                        }
                    }
                    else
                        itinerariosPorRutaResponse.setSuccess(false);
                }
            }
        }
        itinerariosPorRutaResponse.setUid(itinerariosPorRutaRequest.getUid());
        //LoginResp.setSuccess(success);
        itinerariosPorRutaResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "itinerariosResponse");
        itinerariosPorRutaResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
        System.out.println("********************** Termina itinerariosPorRutaRequest ********************** ");
        return itinerariosPorRutaResponse;
    }


    public AsientosResponse mapaAsientosRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, AsientosRequest asientosRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia mapaAsientosRequest ********************** ");
        AsientosResponse asientosResponse= new AsientosResponse();
        if(asientosRequest.getUid()==null || (errorMsg = validaCampos.solicitudAsientos(asientosRequest))!=null){
            estadoTransaccion.setEstadoCodigo(-3, "asientosRequest",errorMsg);
            asientosResponse.setSuccess(false);
        }
        else{
          /*  String uid, String pRutaOrigen, String pOrigen, String pDestino,
          String pFecha1, String pFecha2, String pFecha3, String pFecha4,
          String pServicioClave,String pServicio, String pEmpresa, String pViajeRedondo*/
            respuesta = vendERMobileFacadeBean.getMapaAsientos(asientosRequest.getSesion().getIdEstacionTrabajo(), asientosRequest.getUid(), asientosRequest.getCorrida().getClaveCorrida(), String.valueOf(asientosRequest.getCorrida().getPlantilla().getId()),asientosRequest.getCorrida().getOrigen().getClave());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, "recoleccionRequest", null);
                asientosResponse.setSuccess(false);
            } else {
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, "permisosRequest", respuesta.get(1).toString());
                    asientosResponse.setSuccess(false);
                }
                else{
                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "asientosResponse");
                    if(respuesta.get(0).toString().equals("0")) {
                        asientosResponse.setSuccess(true);
                        if(respuesta.size() >= 3) {
                            StringTokenizer token = new StringTokenizer(respuesta.get(2).toString(), "|");
                            asientosResponse.setMapaAsientos(token.nextElement().toString());
                            asientosResponse.setMapaAsientosPasajero(token.nextElement().toString());
                        }
                        else{
                            estadoTransaccion.setEstadoCodigo(45, "itinerariosResponse", null);
                            asientosResponse.setSuccess(false);
                        }
                    }
                    else
                        asientosResponse.setSuccess(false);

                }
            }
        }
        asientosResponse.setUid(asientosRequest.getUid());
        //LoginResp.setSuccess(success);
        asientosResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "itinerariosResponse");
        asientosResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
        System.out.println("********************** Termina mapaAsientosRequest ********************** ");
        return asientosResponse;
    }

    public OcuparAsientosResponse ocuparAsientosRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, OcuparAsientosRequest ocuparAsientosRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia ocuparAsientosRequest ********************** ");
        OcuparAsientosResponse ocuparAsientosResponse= new OcuparAsientosResponse();
        if(ocuparAsientosRequest.getUid()==null || (errorMsg = validaCampos.solicitudOcuparAsientos(ocuparAsientosRequest))!=null){
            estadoTransaccion.setEstadoCodigo(-3, "asientosRequest",errorMsg);
            ocuparAsientosResponse.setSuccess(false);
        }
        else{
          /*  String uid, String pRutaOrigen, String pOrigen, String pDestino,
          String pFecha1, String pFecha2, String pFecha3, String pFecha4,
          String pServicioClave,String pServicio, String pEmpresa, String pViajeRedondo*/
            respuesta = vendERMobileFacadeBean.getOcuparAsientos(ocuparAsientosRequest.getSesion().getIdEstacionTrabajo(), ocuparAsientosRequest.getUid(), ocuparAsientosRequest.getCorridas().getClaveCorrida(), ocuparAsientosRequest.getMapaAsientosOcupados(), ocuparAsientosRequest.getMapaAsientosPasajero(), ocuparAsientosRequest.getCorridas().getOrigen().getClave(), ocuparAsientosRequest.getSesion().getNumUsuario(),ocuparAsientosRequest.getModoOcupacion());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, "asientosRequest", null);
                ocuparAsientosResponse.setSuccess(false);
            }else {
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, "asientosRequest", respuesta.get(1).toString());
                    ocuparAsientosResponse.setSuccess(false);
                }
                else{
                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "ocuparAsientosResponse");
                    if(respuesta.get(0).toString().equals("0"))
                        ocuparAsientosResponse.setSuccess(true);
                    else
                        ocuparAsientosResponse.setSuccess(false);
                }
            }
        }
        ocuparAsientosResponse.setUid(ocuparAsientosRequest.getUid());
        ocuparAsientosResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "ocuparAsientosResponse");
        ocuparAsientosResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        System.out.println("********************** Termina ocuparAsientosRequest ********************** ");
        return ocuparAsientosResponse;
    }

    public VenderBoletosResponse venderBoletosRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, VenderBoletosRequest venderBoletosRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia venderBoletosRequest ********************** ");
        VenderBoletosResponse venderBoletosResponse= new VenderBoletosResponse();
        //System.out.println("venderBoletosRequest.getCorridas().getClaveCorrida() "+venderBoletosRequest.getCorridas().getClaveCorrida());
        boolean bolAbierto = ((venderBoletosRequest.getCorridas().getClaveCorrida() != null) && !(venderBoletosRequest.getCorridas().getClaveCorrida().equals("null")) && (venderBoletosRequest.getCorridas().getClaveCorrida().length() > 5)) ? false : true;
        //System.out.println("bolAbierto "+bolAbierto +" venderBoletosRequest.getCorridas().getClaveCorrida().length() "+venderBoletosRequest.getCorridas().getClaveCorrida().length());
        if(venderBoletosRequest.getUid()==null || (errorMsg = validaCampos.solicitudVenderBoleto(venderBoletosRequest,bolAbierto))!=null){        
            estadoTransaccion.setEstadoCodigo(-3, "asientosRequest",errorMsg);
            venderBoletosResponse.setSuccess(false);
        }else{
            String valor=null;
            //System.out.println("venderBoletosRequest.getContrasenia() "+venderBoletosRequest.getSesion().getContrasenia()+" venderBoletosRequest "+venderBoletosRequest.getSesion().getNumUsuario());
            if(venderBoletosRequest.getSesion().getContrasenia() != null&&!venderBoletosRequest.getSesion().getContrasenia().equals("") && venderBoletosRequest.getSesion().getContrasenia().length() > 0) {
                try {
                    valor = new EncriptarMD5().encriptar(venderBoletosRequest.getSesion().getContrasenia());
                } catch (Exception ex) {
                    valor = ex.getMessage();
                }
                if(valor == null){
                    estadoTransaccion.setEstadoCodigo(-6, (valor.length()>99?valor.substring(0,99):valor), "recoleccionRequest");
                    venderBoletosResponse.setSuccess(false);
                }
            }
            System.out.println("Contraseña Encriptada "+valor);
            //System.out.println("venderBoletosRequest.getCorridas().getRuta().getId() "+venderBoletosRequest.getCorridas().getRuta().getId());
            respuesta = vendERMobileFacadeBean.venderBoleto(formarBoletos(venderBoletosRequest, bolAbierto), String.valueOf(venderBoletosRequest.getCorridas().getRuta().getId()), venderBoletosRequest.getUid(), (bolAbierto == true ? "true" : "false"),  venderBoletosRequest.getSesion().getNumUsuario(), (valor == null ? "" : valor),  venderBoletosRequest.getSesion().getIdEstacionTrabajo());
            System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, "venderBoletosRequest", null);
                venderBoletosResponse.setSuccess(false);
            }else {
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, "venderBoletosRequest", respuesta.get(1).toString());
                    venderBoletosResponse.setSuccess(false);
                }
                else{
                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "ocuparAsientosResponse");
                    if(respuesta.get(0).toString().equals("0")) {
                        venderBoletosResponse.setSuccess(true);
                        if(respuesta.get(4) != null) {
                            System.out.println("respuesta.get(4) "+respuesta.get(4));
                            venderBoletosResponse.setAsientoSeleccionados(setAsientosSeleccionados((Vector)respuesta.get(4),venderBoletosRequest));
                       }
                        else{
                            estadoTransaccion.setEstadoCodigo(45, null, "venderBoletosRequest");
                            venderBoletosResponse.setSuccess(false);
                        }
                    }else
                        venderBoletosResponse.setSuccess(false);
                }
            }
       }
        venderBoletosResponse.setUid(venderBoletosRequest.getUid());
        //LoginResp.setSuccess(success);
        venderBoletosResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "venderBoletosRequest");
        venderBoletosResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
		System.out.println("********************** Termina venderBoletosRequest ********************** ");
        return venderBoletosResponse;
    }

    public CancelarBoletosResponse cancelarBoletosRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, CancelarBoletosRequest cancelarBoletosRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia cancelarBoletosRequest ********************** ");
        CancelarBoletosResponse cancelarBoletosResponse= new CancelarBoletosResponse();
        if(cancelarBoletosRequest.getUid()==null || (errorMsg =validaCampos.solicitudCancelarBoleto(cancelarBoletosRequest))!=null){
            estadoTransaccion.setEstadoCodigo(-3, "cancelarBoletosRequest",errorMsg);
            cancelarBoletosResponse.setSuccess(false);
        }
        else{
            String valor=null;
            //System.out.println("cancelarBoletosRequest.getContrasenia() "+cancelarBoletosRequest.getSesion().getContrasenia()+" cancelarBoletosRequest "+cancelarBoletosRequest.getSesion().getNumUsuario());
            if(cancelarBoletosRequest.getSesion().getContrasenia() != null&&!cancelarBoletosRequest.getSesion().getContrasenia().equals("") && cancelarBoletosRequest.getSesion().getContrasenia().length() > 0) {
                try {
                    valor = new EncriptarMD5().encriptar(cancelarBoletosRequest.getSesion().getContrasenia());
                } catch (Exception ex) {
                    valor = ex.getMessage();
                }
                if(valor == null){
                    estadoTransaccion.setEstadoCodigo(-6, (valor.length()>99?valor.substring(0,99):valor), "recoleccionRequest");
                    cancelarBoletosResponse.setSuccess(false);
                }
            }
            System.out.println("Contraseña Encriptada "+valor);
            Vector varios = formarBoletosCancelacion(cancelarBoletosRequest);
            respuesta = vendERMobileFacadeBean.cancelarBoleto(cancelarBoletosRequest.getUid(), cancelarBoletosRequest.getSesion().getNumUsuario(), valor == null ? "": valor, cancelarBoletosRequest.getSesion().getIdEstacionTrabajo(), cancelarBoletosRequest.getSesion().getIdSesion(),varios.get(0).toString(),varios.get(1).toString(), cancelarBoletosRequest.getAsientosSeleccionados()[0].getFolioPreImpresoBoleto(), cancelarBoletosRequest.getMotivoCancelacion());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, "cancelarBoletosRequest", null);
                cancelarBoletosResponse.setSuccess(false);
            }else {
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, "permisosRequest", respuesta.get(1).toString());
                    cancelarBoletosResponse.setSuccess(false);
                }
                else{
                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "ocuparAsientosResponse");
                    if(respuesta.get(0).toString().equals("0")) {
                        cancelarBoletosResponse.setSuccess(true);
                        if(respuesta.get(4).toString().length() > 0 && respuesta.get(5).toString().length() > 0) {
                            cancelarBoletosResponse.setAsientosSeleccionados(setAsientosSeleccionadosCancelados(respuesta.get(4).toString(), respuesta.get(5).toString(), respuesta.get(2).toString(),cancelarBoletosRequest));
                        }
                        else{
                            estadoTransaccion.setEstadoCodigo(45, "cancelarBoletosResponse", null);
                            cancelarBoletosResponse.setSuccess(false);
                        }
                    }
                    else
                        cancelarBoletosResponse.setSuccess(false);
                }
            }
        }
        cancelarBoletosResponse.setUid(cancelarBoletosRequest.getUid());
        //LoginResp.setSuccess(success);
        cancelarBoletosResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "cancelarBoletosResponse");
        cancelarBoletosResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
		System.out.println("********************** Termina cancelarBoletosRequest ********************** ");
        return cancelarBoletosResponse;
    }

    public TransferirBoletosResponse transferirBoletosRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, TransferirBoletosRequest transferirBoletosRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia transferirBoletosRequest ********************** ");
        TransferirBoletosResponse transferirBoletosResponse= new TransferirBoletosResponse();
        if(transferirBoletosRequest.getUid()==null || (errorMsg =validaCampos.solicitudTransferirBoleto(transferirBoletosRequest))!=null){
            estadoTransaccion.setEstadoCodigo(-3, "transferirBoletosRequest",errorMsg);
            transferirBoletosResponse.setSuccess(false);
        }else{
            String valor=null;
            //System.out.println("transferirBoletosRequest.getContrasenia() "+transferirBoletosRequest.getSesion().getContrasenia()+" transferirBoletosRequest "+transferirBoletosRequest.getSesion().getNumUsuario());
            if(transferirBoletosRequest.getSesion().getContrasenia() != null&&!transferirBoletosRequest.getSesion().getContrasenia().equals("") && transferirBoletosRequest.getSesion().getContrasenia().length() > 0) {
                try {
                    valor = new EncriptarMD5().encriptar(transferirBoletosRequest.getSesion().getContrasenia());
                } catch (Exception ex) {
                    valor = ex.getMessage();
                }
                if(valor == null){
                    estadoTransaccion.setEstadoCodigo(-6, (valor.length()>99?valor.substring(0,99):valor), "recoleccionRequest");
                    transferirBoletosResponse.setSuccess(false);
                }
            }
            System.out.println("Contraseña Encriptada "+valor);
            Vector varios = formarBoletosHO(transferirBoletosRequest);
            respuesta = vendERMobileFacadeBean.transferirBoleto(transferirBoletosRequest.getUid(), transferirBoletosRequest.getSesion().getNumUsuario(), (valor == null) ? "":valor,
                        transferirBoletosRequest.getSesion().getIdEstacionTrabajo(), transferirBoletosRequest.getSesion().getIdSesion(),varios.get(0).toString(),
                        varios.get(1).toString(),varios.get(2).toString(),transferirBoletosRequest.getCorridasDestino().getOrigen().getClave(),
                        transferirBoletosRequest.getCorridasDestino().getDestino().getClave(),transferirBoletosRequest.getCorridasDestino().getTipoServicio().getNombre(),
                        transferirBoletosRequest.getCorridasDestino().getEmpresa().getNombre(), transferirBoletosRequest.getCorridasDestino().getClaveCorrida(), varios.get(3).toString(), varios.get(4).toString());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, "transferirBoletosRequest", null);
                transferirBoletosResponse.setSuccess(false);
            }else {
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, "permisosRequest", respuesta.get(1).toString());
                    transferirBoletosResponse.setSuccess(false);
                }
                else{
                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "ocuparAsientosResponse");
                    if(respuesta.get(0).toString().equals("0")) {
                        transferirBoletosResponse.setSuccess(true);
                        if(respuesta.get(4).toString().length() > 0 && respuesta.get(5).toString().length() > 0) {
                            transferirBoletosResponse.setAsientosSeleccionadosDestino(setAsientosSeleccionadosHO(respuesta.get(4).toString(), respuesta.get(5).toString(), respuesta.get(2).toString(),transferirBoletosRequest));
                        }
                        else{
                            estadoTransaccion.setEstadoCodigo(45, "transferirBoletosRequest", null);
                            transferirBoletosResponse.setSuccess(false);
                        }
                    }
                    else
                        transferirBoletosResponse.setSuccess(false);
                }
            }
        }
        transferirBoletosResponse.setUid(transferirBoletosRequest.getUid());
        //LoginResp.setSuccess(success);
        transferirBoletosResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "transferirBoletosRequest");
        transferirBoletosResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
		System.out.println("********************** Termina transferirBoletosRequest ********************** ");
        return transferirBoletosResponse;
    }

    public LogOutResponse logOutRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, LogOutRequest logoutRequest) {
        // TODO implemecanjent operation
        Vector respuesta = null;
        System.out.println("********************** Inicia logOutRequest ********************** ");
        LogOutResponse logoutResponse= new LogOutResponse();
        if(logoutRequest.getUid()==null || (errorMsg =validaCampos.solicitudParametrosIniciales(logoutRequest.getUid()))!=null){
            estadoTransaccion.setEstadoCodigo(-3, "logOutRequest",errorMsg);
            logoutResponse.setSuccess(false);
        }else{
            respuesta = vendERMobileFacadeBean.logOut(logoutRequest.getSesion().getIdEstacionTrabajo(), logoutRequest.getUid());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, "logOutRequest", null);
                logoutResponse.setSuccess(false);
            }else {
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, "permisosRequest", respuesta.get(1).toString());
                    logoutResponse.setSuccess(false);
                }
                else{
                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "ocuparAsientosResponse");
                    if(respuesta.get(0).toString().equals("0")) {
                        logoutResponse.setSuccess(true);
                    }else
                        logoutResponse.setSuccess(false);
                }
            }
        }
        logoutResponse.setUid(logoutRequest.getUid());
        //LoginResp.setSuccess(success);
        logoutResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "ocuparAsientosResponse");
        logoutResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
		System.out.println("********************** Termina logOutRequest ********************** ");
        return logoutResponse;
    }

    public AbrirCerrarVentaAbordoResponse AbrirCerrarVentaAbordoRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, AbrirCerrarVentaAbordoRequest abrirCerrarVentaAbordoRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("Inicia logOutRequest");
        AbrirCerrarVentaAbordoResponse abrirCerrarVentaAbordoResponse= new AbrirCerrarVentaAbordoResponse();
        if(abrirCerrarVentaAbordoRequest.getUid()==null || (errorMsg =validaCampos.solicitudAbrirCerrarVentaAbordo(abrirCerrarVentaAbordoRequest))!=null){
            estadoTransaccion.setEstadoCodigo(-3, "logOutRequest",errorMsg);
            abrirCerrarVentaAbordoResponse.setSuccess(false);
        }else{
            respuesta = vendERMobileFacadeBean.abrirCerrarVentaAbordo(abrirCerrarVentaAbordoRequest.getUid(),abrirCerrarVentaAbordoRequest.getSesion().getNumUsuario(),
                        abrirCerrarVentaAbordoRequest.getFolioTarjetaViaje(),abrirCerrarVentaAbordoRequest.getNumAutobus(),
                        abrirCerrarVentaAbordoRequest.getClaveOperador(), abrirCerrarVentaAbordoRequest.getClaveModo());
            System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, "logOutRequest", null);
                abrirCerrarVentaAbordoResponse.setSuccess(false);
            }
            if(respuesta.get(0).toString().equals("getCursor")){
                estadoTransaccion.setEstadoCodigo(-9, "permisosRequest", respuesta.get(1).toString());
                abrirCerrarVentaAbordoResponse.setSuccess(false);
            }
            else{
                estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "abrirCerrarVentaAbordoRequest");
                if(respuesta.get(0).toString().equals("0")) {
                    abrirCerrarVentaAbordoResponse.setSuccess(true);
                }else
                    abrirCerrarVentaAbordoResponse.setSuccess(false);
            }
        }
        abrirCerrarVentaAbordoResponse.setUid(abrirCerrarVentaAbordoRequest.getUid());
        //LoginResp.setSuccess(success);
        abrirCerrarVentaAbordoResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "abrirCerrarVentaAbordoRequest");
        abrirCerrarVentaAbordoResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());

        return abrirCerrarVentaAbordoResponse;
    }

    public FechaResponse fechaRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, FechaRequest fechaRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("Inicia recoleccionRequest");
        FechaResponse fechaResponse= new FechaResponse();
        if(fechaRequest.getUid()==null || (errorMsg=validaCampos.solicitudParametrosIniciales(fechaRequest.getUid()))!=null){
            estadoTransaccion.setEstadoCodigo(-3, errorMsg, "recoleccionRequest");
            fechaResponse.setSuccess(false);
        }
        else{
            respuesta = vendERMobileFacadeBean.getFecha(fechaRequest.getUid());
            System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, null, "recoleccionRequest");
                fechaResponse.setSuccess(false);
            }else {
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, respuesta.get(1).toString(),"recoleccionRequest");
                    fechaResponse.setSuccess(false);
                }
                else{
                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "fechaRequest");
                    if(respuesta.get(0).toString().equals("0")){
                        fechaResponse.setSuccess(true);
                        fechaResponse.setFecha(respuesta.get(2).toString());
                   }else
                        fechaResponse.setSuccess(false);
                }
            }
        }
        //System.out.println("vendERMobileFacadeBean "+vendERMobileFacadeBean);
        fechaResponse.setUid(fechaRequest.getUid());
        //LoginResp.setSuccess(success);
        fechaResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "fechaRequest");
        fechaResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());

        return fechaResponse;
    }

    public TipoNoPermitidosResponse tiposNoPermitidosRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, TipoNoPermitidosRequest tipoNoPermitidosRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("Inicia recoleccionRequest");
        TipoNoPermitidosResponse tipoNoPermitidosResponse = new TipoNoPermitidosResponse();
        if(tipoNoPermitidosRequest.getUid()==null || (errorMsg=validaCampos.solicitudTiposNoPermitidos(tipoNoPermitidosRequest))!=null){
            estadoTransaccion.setEstadoCodigo(-3, errorMsg, "recoleccionRequest");
            tipoNoPermitidosResponse.setSuccess(false);
        }
        else{
            respuesta = vendERMobileFacadeBean.getTipoPasajeroNoPermitido(tipoNoPermitidosRequest.getUid(), String.valueOf(tipoNoPermitidosRequest.getOrigen().getId()), tipoNoPermitidosRequest.getCorridaId());
            System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, null, "recoleccionRequest");
                tipoNoPermitidosResponse.setSuccess(false);
            }else {
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, respuesta.get(1).toString(),"recoleccionRequest");
                    tipoNoPermitidosResponse.setSuccess(false);
                }
                else{
                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "fechaRequest");
                    if(respuesta.get(0).toString().equals("0")){
                        tipoNoPermitidosResponse.setSuccess(true);
                        tipoNoPermitidosResponse.setTiposNoPermitidos(setTiposNoPermitidos(respuesta.get(2).toString()));
                   }else
                        tipoNoPermitidosResponse.setSuccess(false);
                }
            }
        }
        //System.out.println("vendERMobileFacadeBean "+vendERMobileFacadeBean);
        tipoNoPermitidosResponse.setUid(tipoNoPermitidosRequest.getUid());
        //LoginResp.setSuccess(success);
        tipoNoPermitidosResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "fechaRequest");
        tipoNoPermitidosResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());

        return tipoNoPermitidosResponse;
    }

    public CanjeBoletoAbiertoResponse canjeBoletoAbiertoRequest(VendERMobileFacadeRemote vendERMobileFacadeBean, CanjeBoletoAbiertoRequest canjeBoletoAbiertoRequest) {
        // TODO implement operation
        Vector respuesta = null;
        System.out.println("********************** Inicia canjeBoletoAbiertoRequest ********************** ");
        CanjeBoletoAbiertoResponse canjeBoletoAbiertoResponse= new CanjeBoletoAbiertoResponse();
        if(canjeBoletoAbiertoRequest.getUid()==null || (errorMsg =validaCampos.solicitudCanjearBoletoAbierto(canjeBoletoAbiertoRequest))!=null){
            estadoTransaccion.setEstadoCodigo(-3, errorMsg, "canjeBoletoAbiertoRequest");
            canjeBoletoAbiertoResponse.setSuccess(false);
        } else{
            String valor=null;
            //System.out.println("canjeBoletoAbiertoRequest.getContrasenia() "+canjeBoletoAbiertoRequest.getSesion().getContrasenia()+" canjeBoletoAbiertoRequest "+canjeBoletoAbiertoRequest.getSesion().getNumUsuario());
            if(canjeBoletoAbiertoRequest.getSesion().getContrasenia() != null&&!canjeBoletoAbiertoRequest.getSesion().getContrasenia().equals("") && canjeBoletoAbiertoRequest.getSesion().getContrasenia().length() > 0) {
                try {
                    valor = new EncriptarMD5().encriptar(canjeBoletoAbiertoRequest.getSesion().getContrasenia());
                } catch (Exception ex) {
                    valor = ex.getMessage();
                }
                if(valor == null){
                    estadoTransaccion.setEstadoCodigo(-6, (valor.length()>99?valor.substring(0,99):valor), "canjeBoletoAbiertoRequest");
                    canjeBoletoAbiertoResponse.setSuccess(false);
                }
            }
            System.out.println("Contraseña Encriptada "+valor);
            Vector varios = formarBoletosAC(canjeBoletoAbiertoRequest);
            respuesta = vendERMobileFacadeBean.canjeBoletoAbierto(canjeBoletoAbiertoRequest.getUid(), canjeBoletoAbiertoRequest.getSesion().getNumUsuario(), (valor == null) ? "":valor,
                        canjeBoletoAbiertoRequest.getSesion().getIdEstacionTrabajo(), canjeBoletoAbiertoRequest.getSesion().getIdSesion(),varios.get(0).toString(),
                        varios.get(1).toString(),canjeBoletoAbiertoRequest.getCorridasDestino().getOrigen().getClave(),
                        canjeBoletoAbiertoRequest.getCorridasDestino().getDestino().getClave(),canjeBoletoAbiertoRequest.getCorridasDestino().getTipoServicio().getNombre(),
                        canjeBoletoAbiertoRequest.getCorridasDestino().getEmpresa().getNombre(),canjeBoletoAbiertoRequest.getCorridasDestino().getClaveCorrida(), varios.get(2).toString(), varios.get(3).toString());
            //System.out.println("respuesta "+respuesta);
            if(respuesta == null){
                estadoTransaccion.setEstadoCodigo(-1, "canjeBoletoAbiertoRequest", null);
                canjeBoletoAbiertoResponse.setSuccess(false);
            }else {
                if(respuesta.get(0).toString().equals("getCursor")){
                    estadoTransaccion.setEstadoCodigo(-9, "canjeBoletoAbiertoRequest", respuesta.get(1).toString());
                    canjeBoletoAbiertoResponse.setSuccess(false);
                }
                else{
                    estadoTransaccion.setEstado(Integer.parseInt(respuesta.get(0).toString()), respuesta.get(1).toString(), "ocuparAsientosResponse");
                    if(respuesta.get(0).toString().equals("0")) {
                        canjeBoletoAbiertoResponse.setSuccess(true);
                        if(respuesta.get(4).toString().length() > 0 && respuesta.get(5).toString().length() > 0) {
                            canjeBoletoAbiertoResponse.setAsientosSeleccionadosDestino(setAsientosSeleccionadosAC(respuesta.get(4).toString(), respuesta.get(5).toString(), respuesta.get(2).toString(),canjeBoletoAbiertoRequest));
                        }
                        else{
                            estadoTransaccion.setEstadoCodigo(45, "canjeBoletoAbiertoRequest", null);
                            canjeBoletoAbiertoResponse.setSuccess(false);
                        }
                    }
                    else
                        canjeBoletoAbiertoResponse.setSuccess(false);
                }
            }
        }
        canjeBoletoAbiertoResponse.setUid(canjeBoletoAbiertoRequest.getUid());
        //LoginResp.setSuccess(success);
        canjeBoletoAbiertoResponse.setErrorCode(estadoTransaccion.getCodigo());
        estadoTransaccion.setEstadoCodigo(estadoTransaccion.getCodigo(), (respuesta == null || respuesta.get(3) == null) ? "" : respuesta.get(3).toString(), "canjeBoletoAbiertoRequest");
        canjeBoletoAbiertoResponse.setErrorMsg(estadoTransaccion.getParentMsg());
        //permisosResponse.setSesion(permisosReq.getSesion());
		System.out.println("********************** Termina canjeBoletoAbiertoRequest ********************** ");
        return canjeBoletoAbiertoResponse;
    }

    /* ************************************************ OTRAS OPERACIONES ****************************************** */
    private Permisos[] setPermisosVector(Vector permisos) {
        //System.out.println("setPermisosVector "+permisos);
        Permisos[] perm = null;
        if(permisos != null)
            if(!permisos.get(0).toString().equals("reinicio")){
                //System.out.println("((Vector)permisos.get(0)).size() "+((Vector)permisos.get(0)).size());
                if(((Vector)permisos.get(0)).size() > 0){
                        perm = new Permisos[permisos.size()];
                        for(int i = 0; i< permisos.size(); i++){
                            perm[i] = new Permisos((Vector) permisos.get(i));
                        }
                }
            }
        return perm;
    }


    private ParametrosIniciales[] setParamIniciales(Vector parametros){
        //System.out.println("ParametrosIniciales "+parametros);
//        //System.out.println("((Vector)parametros.get(0)).size() "+((Vector)parametros.get(0)).size());
        ParametrosIniciales[] par = null;
        if(parametros != null)
            if(!parametros.get(0).toString().equals("reinicio")){
                if(parametros.size() > 0){
                        par = new ParametrosIniciales[parametros.size()];
                        for(int i = 0; i< parametros.size(); i++){
                            if(parametros.get(i).toString().contains("reinicio"))
                                vacios = parametros.get(i).toString().substring(9)+","+vacios;
                            else if(parametros.get(i).toString().contains("getCursor"))
                                    vacios = parametros.get(i).toString().substring(10)+","+vacios;
                                else {
                                    //System.out.println("(Vector) parametros.get(i) "+(Vector) parametros.get(i));
                                    par[i] = new ParametrosIniciales((Vector) parametros.get(i));
                                }
                        }
                }
            }
        //System.out.println("vacios "+vacios);
        return par;
    }

    private Tarifas[] setTarifas(Vector tar){
        //System.out.println("tar "+tar+ " tar.size "+tar.size());
        Tarifas[] tarifa = null;
        if(tar != null)
            if(!tar.get(0).toString().equals("reinicio"))
                if(tar.size() > 0){
                        tarifa = new Tarifas[tar.size()];
                        //System.out.println("hola?");
                        for(int i = 0; i< tar.size(); i++){
                            //System.out.println("(Vector) tarifas.get(i) "+(Vector) tar.get(i));
                           tarifa[i] = new Tarifas((Vector) tar.get(i));
                        }
            }
        return tarifa;
    }

    private TiposPasajero[] setTiposPasajero(Vector tipos){
       // System.out.println("tipos "+tipos+ " tipos.size "+tipos.size());
        TiposPasajero[] tiposP= null;
        if(tipos != null)
            if(!tipos.get(0).toString().equals("reinicio"))
                if(tipos.size() > 0){
                        tiposP = new TiposPasajero[tipos.size()];
                        for(int i = 0; i< tipos.size(); i++){
                            //System.out.println("(Vector) tipos.get(i) "+(Vector) tipos.get(i));
                           tiposP[i] = new TiposPasajero((Vector) tipos.get(i));
                        }
                }
        return tiposP;
    }

    private TiposPago[] setTiposPago(Vector tipos){
        //System.out.println("tipos "+tipos+ " tipos.size "+tipos.size());
        TiposPago[] tiposP= null;
        if(tipos != null)
            if(!tipos.get(0).toString().equals("reinicio"))
                if(tipos.size() > 0){
                        tiposP = new TiposPago[tipos.size()];
                        for(int i = 0; i< tipos.size(); i++){
                            //System.out.println("(Vector) tipos.get(i) "+(Vector) tipos.get(i));
                           tiposP[i] = new TiposPago((Vector) tipos.get(i));
                        }
                }
        return tiposP;
    }

    private Corridas[] setCorridas(String corridas){
        //System.out.println("corridas "+corridas+ " corridas.size "+corridas.length());
        StringTokenizer token = null;
        int i = 0;
        Corridas[] cor= null;
        if(corridas != null)
            if(!corridas.equals("reinicio")){
                    token = new StringTokenizer(corridas, "*");
                    cor = new Corridas[token.countTokens()];
                    while(token.hasMoreTokens()) {
                        cor[i] = new Corridas(token.nextToken());
                        i++;
                    }
             }
        return cor;
    }

    private Itinerario[] setItinerario(Vector itinerario){
        //System.out.println("itinerario "+itinerario+ " itinerario.size "+itinerario.size());
        Itinerario[] it= null;
        if(itinerario != null)
            if(!itinerario.get(0).toString().equals("reinicio"))
                if(itinerario.size() > 0){
                        it = new Itinerario[itinerario.size()];
                        for(int i = 0; i< itinerario.size(); i++){
                            //System.out.println("(Vector) itinerario.get(i) "+(Vector) itinerario.get(i));
                           it[i] = new Itinerario((Vector) itinerario.get(i));
                        }
                }
        return it;
    }

    private String[] formarBoletos(VenderBoletosRequest vbr,boolean bolAbierto) {
        String boletitos[] = new String[vbr.getAsientosSeleccionados().length];
        System.out.println("vbr.getFDC() "+vbr.getFDC());
        StringTokenizer token = new StringTokenizer(vbr.getFDC(), ",");
        String fecha = "";
                
        for(int k = 0; k < vbr.getAsientosSeleccionados().length; k++){
            boletitos[k] = new String(",");
            
            fecha = vbr.getCorridas().getFechaSalida()+" "+vbr.getCorridas().getHoraSalida();           
            if(fecha == null)
               fecha = ",";            
            
            if(!bolAbierto) {
                boletitos[k] = boletitos[k]+vbr.getCorridas().getEmpresa().getNombre()+","+
                        vbr.getCorridas().getTipoServicio().getNombre()+","+
                        vbr.getSesion().getIdEstacionTrabajo()+","+
                        vbr.getSesion().getIdSesion()+","+
                        vbr.getCorridas().getClaveCorrida()+","+
                        ","+ //clienteId
                        vbr.getAsientosSeleccionados()[k].getNumeroAsiento()+","+
                        vbr.getAsientosSeleccionados()[k].getNombreOcupante()+","+
                        vbr.getAsientosSeleccionados()[k].getTipoPasajero()+","+
                        vbr.getTiposPago().getTipoPagoClave()+","+
                        vbr.getReferenciaPago()+","+ //referencia pago
                        vbr.getAsientosSeleccionados()[k].getImporteBoleto()+","+
                        "VT,"+
                        ","+ //reservacion id
                        ","+ //boleto relacionado id
                        "," + //dias de validez
                        ","+","+ //foliopreimpreso
                        vbr.getOrigen().getClave()+","+
                        vbr.getOrigen().getClave()+","+
                        vbr.getDestino().getClave()+","+
                        "L,"+
                        vbr.getSesion().getNumUsuario()+","+
                        ","+//usuarioId
                        ","+//usuarioId
                        ","+//usuarioId
                        ","+//adicional1
                        ","+//adicional2
                        ","+//adicional3
                        ","+//adicional4
                        ","+//adicional5
                        ","+//adicional6
                        ","+//adicional7
                        ","+//adicional8
                        ","+//adicional9
                        ","+//adicional10
                        //caja+"-"+nextVal+","+//adicional11
                        //token.nextToken()+","+
                        token.nextToken()+","+ //FDC
                        ","+//adicional12
                        ","+//adicional13
                        ","+//adicional14
                        ","+//adicional15
                        ","+//adicional16
                        ","+//adicional17
                        ","+//adicional18
                        ","+//adicional19
                        ","+//adicional20
                        vbr.getCorridas().getOrigen().getClave()+
                        ","+ //tipo de venta
                        ","+ //nombre referencia
                        ","+","+ //refrencia original
                        ","+fecha;
                        //vbr.getFechaCorrida();                        
            }else{
                 boletitos[k] = boletitos[k]+vbr.getEmpresaNombre().getNombre()+","+
                        vbr.getTipoServicio().getNombre()+","+
                        vbr.getSesion().getIdEstacionTrabajo()+","+
                        vbr.getSesion().getIdSesion()+","+
                        ","+ //claveCorrida
                        ","+ //clienteId
                        ","+ //no_asiento
                        vbr.getAsientosSeleccionados()[k].getNombreOcupante()+","+
                        vbr.getAsientosSeleccionados()[k].getTipoPasajero()+","+
                        vbr.getTiposPago().getTipoPagoClave()+","+
                        vbr.getReferenciaPago()+","+ //referencia pago
                        vbr.getAsientosSeleccionados()[k].getImporteBoleto()+","+
                        "VA,"+
                        ","+ //reservacion id
                        ","+ //boleto relacionado id
                        "," + //dias de validez
                        ","+","+ //foliopreimpreso
                        vbr.getOrigen().getClave()+","+
                        vbr.getOrigen().getClave()+","+
                        vbr.getDestino().getClave()+","+
                        "L,"+
                        vbr.getSesion().getNumUsuario()+","+
                        ","+//usuarioId
                        ","+//usuarioId
                        ","+//usuarioId
                        ","+//adicional1
                        ","+//adicional2
                        ","+//adicional3
                        ","+//adicional4
                        ","+//adicional5
                        ","+//adicional6
                        ","+//adicional7
                        ","+//adicional8
                        ","+//adicional9
                        ","+//adicional10
                        token.nextToken()+","+ //FDC
                        //caja+"-"+nextVal+","+//adicional11
                        ","+//adicional12
                        ","+//adicional13
                        ","+//adicional14
                        ","+//adicional15
                        ","+//adicional16
                        ","+//adicional17
                        ","+//adicional18
                        ","+//adicional19
                        ","+//adicional20
                        vbr.getOrigen().getClave()+","+
                        ","+ //tipo de venta
                        ","+ //nombre referencia
                        ","+","+ //refrencia original
                         ","+
                        vbr.getCorridas().getFechaSalida()+" "+vbr.getCorridas().getHoraSalida();
            }
            //nextVal = nextVal+1;
        }
        return boletitos;
    }

    private AsientoAutobus[] setAsientosSeleccionados(Vector informacion, VenderBoletosRequest venderBoletosRequest) {
        System.out.println("informacion "+informacion);
        String noAsiento = "";        
        AsientoAutobus[] asientoAutobus = new AsientoAutobus[venderBoletosRequest.getAsientosSeleccionados().length];
        for (int k = venderBoletosRequest.getAsientosSeleccionados().length-1; k >= 0; k--){
            asientoAutobus[k] = new AsientoAutobus();            
            asientoAutobus[k].setImporteBoleto(venderBoletosRequest.getAsientosSeleccionados()[k].getImporteBoleto());
            asientoAutobus[k].setTipoPasajero(venderBoletosRequest.getAsientosSeleccionados()[k].getTipoPasajero());
            asientoAutobus[k].setFolioPreImpresoBoleto(((Vector)informacion.get(k)).get(2).toString());
            asientoAutobus[k].setFolioBoleto(((Vector)informacion.get(k)).get(1).toString());
            if(((Vector)informacion.get(k)).get(0) != null)
                noAsiento = ((Vector)informacion.get(k)).get(0).toString();
            else
                noAsiento = "0";
            //asientoAutobus[k].setNumeroAsiento(((Vector)informacion.get(k)).get(0).toString());
            asientoAutobus[k].setNumeroAsiento(noAsiento);
            asientoAutobus[k].setNombreOcupante(((Vector)informacion.get(k)).get(3).toString());
            asientoAutobus[k].setCiudadVenta(((Vector)informacion.get(k)).get(4).toString());
        }
        return asientoAutobus;
    }

    private AsientoAutobus[] setAsientosSeleccionadosCancelados(String foliosPreimpresos, String foliosBoletos, String importeBoleto, CancelarBoletosRequest cancelarBoletosRequest) {
        AsientoAutobus[] asientoAutobus = new AsientoAutobus[cancelarBoletosRequest.getAsientosSeleccionados().length];
        //System.out.println("foliosPreimpresos "+foliosPreimpresos);
        //System.out.prcanintln("foliosBoletos "+foliosBoletos);
        StringTokenizer tokenp = new StringTokenizer(foliosPreimpresos, "|");
        StringTokenizer tokenb = new StringTokenizer(foliosBoletos, "|");
        for (int k = cancelarBoletosRequest.getAsientosSeleccionados().length-1; k >= 0; k--){
            asientoAutobus[k] = new AsientoAutobus();
            asientoAutobus[k].setNombreOcupante(cancelarBoletosRequest.getAsientosSeleccionados()[k].getNombreOcupante());
            asientoAutobus[k].setNumeroAsiento(cancelarBoletosRequest.getAsientosSeleccionados()[k].getNumeroAsiento());
            asientoAutobus[k].setImporteBoleto(-Float.parseFloat(importeBoleto));
            asientoAutobus[k].setTipoPasajero(cancelarBoletosRequest.getAsientosSeleccionados()[k].getTipoPasajero());
            asientoAutobus[k].setFolioPreImpresoCancelacion(tokenp.nextToken());
            asientoAutobus[k].setFolioCancelacion(tokenb.nextToken());
           // System.out.println("pase y entre");
        }
        return asientoAutobus;
    }

    private Vector formarBoletosCancelacion(CancelarBoletosRequest cbr) {
        Vector result = new Vector();
        String asientos = ",";
        //String tipoPasajeros = ",";
        String folioPreimpreso = ",";
        for(int k = 0; k < cbr.getAsientosSeleccionados().length; k++){
            System.out.println("cbr.getAsientosSeleccionados()[k].getNumeroAsiento() "+cbr.getAsientosSeleccionados()[k].getNumeroAsiento());
            asientos = asientos+cbr.getAsientosSeleccionados()[k].getNumeroAsiento()+",";
            //tipoPasajeros = tipoPasajeros+cbr.getAsientosSeleccionados()[k].getTipoPasajero()+",";
            folioPreimpreso = folioPreimpreso+cbr.getAsientosSeleccionados()[k].getFolioPreImpresoBoleto()+",";
        }
        System.out.println("asientos "+asientos);
        result.add(asientos.substring(0, asientos.length()-1));
        //result.add(tipoPasajeros.substring(0, tipoPasajeros.length()-1));
        result.add(folioPreimpreso.substring(0, folioPreimpreso.length()-1));
        return result;
    }

    private Vector formarBoletosHO(TransferirBoletosRequest tbr) {
        Vector result = new Vector();
        String asientos = "";
        String folioPreimpreso = "";
        String asientosDestino = "";
        String tiposPasajeros = ",";
        String importeBoleto = "";
        for(int k = 0; k < tbr.getAsientosSeleccionadosOrigen().length; k++){
            asientos = asientos+tbr.getAsientosSeleccionadosOrigen()[k].getNumeroAsiento()+",";
            folioPreimpreso = folioPreimpreso+tbr.getAsientosSeleccionadosOrigen()[k].getFolioPreImpresoBoleto()+",";
            asientosDestino = asientosDestino+tbr.getAsientosSeleccionadosDestino()[k].getNumeroAsiento()+",";
            tiposPasajeros = tiposPasajeros+tbr.getAsientosSeleccionadosDestino()[k].getTipoPasajero()+",";
            importeBoleto = importeBoleto+tbr.getAsientosSeleccionadosDestino()[k].getImporteBoleto()+",";
        }

        result.add(folioPreimpreso.substring(0, folioPreimpreso.length()-1));
        result.add(asientos.substring(0, asientos.length()-1));
        result.add(asientosDestino.substring(0, asientosDestino.length()-1));
        result.add(importeBoleto.substring(0, importeBoleto.length()-1));
        result.add(tiposPasajeros.substring(0, tiposPasajeros.length()-1));
        return result;
    }

    private AsientoAutobus[] setAsientosSeleccionadosHO(String foliosPreimpresos, String foliosBoletos, String nos_asientos, TransferirBoletosRequest transferirBoletosRequest) {
        AsientoAutobus[] asientoAutobus = new AsientoAutobus[transferirBoletosRequest.getAsientosSeleccionadosOrigen().length];
        //System.out.println("foliosPreimpresos "+foliosPreimpresos);
        //System.out.println("foliosBoletos "+foliosBoletos);
        StringTokenizer tokenp = new StringTokenizer(foliosPreimpresos, "|");
        StringTokenizer tokenb = new StringTokenizer(foliosBoletos, "|");
        StringTokenizer tokenn = new StringTokenizer(nos_asientos, ",");
        StringTokenizer tokennom = null;
        //String no_asiento = "";
        for (int k = transferirBoletosRequest.getAsientosSeleccionadosOrigen().length-1; k >= 0; k--){
            asientoAutobus[k] = new AsientoAutobus();
            //asientoAutobus[k].setNombreOcupante(transferirBoletosRequest.getAsientosSeleccionadosOrigen()[k].getNombreOcupante());
            //asientoAutobus[k].setNumeroAsiento(transferirBoletosRequest.getAsientosSeleccionadosDestino()[k].getNumeroAsiento());
            asientoAutobus[k].setImporteBoleto(transferirBoletosRequest.getAsientosSeleccionadosDestino()[k].getImporteBoleto());
            asientoAutobus[k].setTipoPasajero(transferirBoletosRequest.getAsientosSeleccionadosDestino()[k].getTipoPasajero());
            asientoAutobus[k].setFolioPreImpresoBoleto(tokenp.nextToken());
            asientoAutobus[k].setFolioBoleto(tokenb.nextToken());
            String temp = tokenn.nextToken();
            tokennom = new StringTokenizer(temp, "|");
            asientoAutobus[k].setNumeroAsiento(tokennom.nextToken());
            asientoAutobus[k].setNombreOcupante(tokennom.nextToken());
            //System.out.println("pase y entre");
        }
        return asientoAutobus;
    }

    private DatoGenerico[] setEmpresasServicios(Vector empresas){
        //System.out.println("empresas "+empresas+ " empresas.size "+empresas.size());
        DatoGenerico[] dato= null;
        if(empresas != null)
            if(!empresas.get(0).toString().equals("reinicio"))
                if(empresas.size() > 0){
                        dato = new DatoGenerico[empresas.size()];
                        for(int i = 0; i< empresas.size(); i++){
                            //System.out.println("(Vector) empresas.get(i) "+(Vector) empresas.get(i));
                           dato[i] = new DatoGenerico((Vector) empresas.get(i));
                        }
                }
        return dato;

    }

    private Rutas[] setRutas(Vector rutas){
        //System.out.println("rutas "+rutas+ " rutas.size "+rutas.size());
        Rutas[] rutita= null;
        if(rutas != null)
            if(!rutas.get(0).toString().equals("reinicio"))
                if(rutas.size() > 0){
                        rutita = new Rutas[rutas.size()];
                        for(int i = 0; i< rutas.size(); i++){
                           //System.out.println("(Vector) empresas.get(i) "+(Vector) rutas.get(i));
                           rutita[i] = new Rutas((Vector) rutas.get(i));
                        }
                }
        return rutita;

    }

    private String[] setTiposNoPermitidos(String tipos) {
        String[] dato = null;
        StringTokenizer token = null;
        int i = 0;
        //System.out.println("tipos "+tipos);
        if(tipos != null)
            if(!tipos.equals("reinicio"))
                if(tipos.length() > 0){
                        token = new StringTokenizer(tipos, "|");
                        dato = new String[token.countTokens()];
                        while(token.hasMoreTokens()) {
                               dato[i] = new String(token.nextToken());
                               i++;
                            }
                }
        return dato;
    }

    private Vector formarBoletosAC(CanjeBoletoAbiertoRequest canjeBoletoAbiertoRequest) {
       Vector result = new Vector();
        String asientos = "";
        String folioPreimpreso = "";
        String asientosDestino = "";
        String tiposPasajeros = "";
        String importeBoleto = "";
        for(int k = 0; k < canjeBoletoAbiertoRequest.getAsientosSeleccionadosOrigen().length; k++){
            folioPreimpreso = folioPreimpreso+canjeBoletoAbiertoRequest.getAsientosSeleccionadosOrigen()[k].getFolioPreImpresoBoleto()+",";
            asientosDestino = asientosDestino+canjeBoletoAbiertoRequest.getAsientosSeleccionadosDestino()[k].getNumeroAsiento()+",";
            tiposPasajeros = tiposPasajeros+canjeBoletoAbiertoRequest.getAsientosSeleccionadosDestino()[k].getTipoPasajero()+",";
            importeBoleto = importeBoleto+canjeBoletoAbiertoRequest.getAsientosSeleccionadosDestino()[k].getImporteBoleto()+",";
        }

        result.add(folioPreimpreso.substring(0, folioPreimpreso.length()-1));
        result.add(asientosDestino.substring(0, asientosDestino.length()-1));
        result.add(tiposPasajeros.substring(0, tiposPasajeros.length()-1));
        result.add(importeBoleto.substring(0, importeBoleto.length()-1));
        return result;
    }

    private AsientoAutobus[] setAsientosSeleccionadosAC(String foliosPreimpresos, String foliosBoletos, String nos_asientos, CanjeBoletoAbiertoRequest canjeBoletoAbiertoRequest) {
        AsientoAutobus[] asientoAutobus = new AsientoAutobus[canjeBoletoAbiertoRequest.getAsientosSeleccionadosOrigen().length];
        //System.out.println("foliosPreimpresos "+foliosPreimpresos);
        //System.out.println("foliosBoletos "+foliosBoletos);
        StringTokenizer tokenp = new StringTokenizer(foliosPreimpresos, "|");
        StringTokenizer tokenb = new StringTokenizer(foliosBoletos, "|");
        StringTokenizer tokenn = new StringTokenizer(nos_asientos, ",");
        StringTokenizer tokennom = null;
        for (int k = canjeBoletoAbiertoRequest.getAsientosSeleccionadosOrigen().length-1; k >= 0; k--){
            asientoAutobus[k] = new AsientoAutobus();
            //asientoAutobus[k].setNombreOcupante(canjeBoletoAbiertoRequest.getAsientosSeleccionadosDestino()[k].getNombreOcupante());
            //asientoAutobus[k].setNumeroAsiento(canjeBoletoAbiertoRequest.getAsientosSeleccionadosDestino()[k].getNumeroAsiento());
            asientoAutobus[k].setImporteBoleto(canjeBoletoAbiertoRequest.getAsientosSeleccionadosDestino()[k].getImporteBoleto());
            asientoAutobus[k].setTipoPasajero(canjeBoletoAbiertoRequest.getAsientosSeleccionadosDestino()[k].getTipoPasajero());
            asientoAutobus[k].setFolioPreImpresoBoleto(tokenp.nextToken());
            asientoAutobus[k].setFolioBoleto(tokenb.nextToken());
            String temp = tokenn.nextToken();
            tokennom = new StringTokenizer(temp, "|");
            asientoAutobus[k].setNumeroAsiento(tokennom.nextToken());
            asientoAutobus[k].setNombreOcupante(tokennom.nextToken());
            //System.out.println("pase y entre");
        }
        return asientoAutobus;
    }

    public getdatosResponse getDatosCorridaRequest(VendERMobileFacadeRemote vendERMobileFacadeBean,String origen, String claveCorrida)
    {
        getdatosResponse datos = new getdatosResponse();
        Vector respuesta = new Vector();
                respuesta = vendERMobileFacadeBean.buscarDatosCorrita(origen, claveCorrida);
                System.out.println("respuesta(getDatosCorridaRequest) "+respuesta);
                if(respuesta == null&& respuesta.size() == 0){
                    datos.setOperador("");
                    datos.setAutobus("");
                    datos.setEmpresa("");
                }
                else{
                 datos.setOperador(respuesta.get(0).toString());
                 datos.setAutobus(respuesta.get(1).toString());
                 datos.setEmpresa(respuesta.get(2).toString());
                }
        return datos;
    }


    public despacharTarjetaResponse despacharTarjetasVRequest(VendERMobileFacadeRemote vendERMobileFacadeBean,String origen, String claveCorrida,String empresa,String operador,String autobus,String usuario)
    {
       despacharTarjetaResponse despacho = new despacharTarjetaResponse();
       
       Vector respuesta = new Vector();
                respuesta = vendERMobileFacadeBean.despacharTarjetaViaje(origen, claveCorrida, empresa, operador, autobus, usuario);
                System.out.println("respuesta(despacharTarjetaRequest) "+respuesta);
       
                if(respuesta == null || respuesta.size() == 0){
                    despacho.setCodigo(1);
                    despacho.setMensaje("Error al tratar de despachar la tarjeta");
                }
                else{
                    despacho.setCodigo(Integer.valueOf(respuesta.get(4).toString()));
                    despacho.setMensaje(respuesta.get(5).toString());
                    despacho.setFolioTarjeta(respuesta.get(0).toString());
                    despacho.setNombreOperador(respuesta.get(1).toString());
                    if(!respuesta.get(3).toString().equals("") && !respuesta.get(3).toString().equals("null"))
                    {
                        List<Folio> listaFolios = new ArrayList<Folio>();
                        String[] folios = respuesta.get(3).toString().split("\\|");
                        for (int i=0; i<folios.length;i++)
                        {
                            Folio f = new Folio();
                            System.out.println("Folio["+i+"]: "+folios[i]);
                            String[] fol = folios[i].split("-");
                            f.setAsientoNumero(Integer.parseInt(fol[0]));
                            f.setFolioPreimpreso(fol[1]);
                            listaFolios.add(f);
                            despacho.setFolios(listaFolios);
                        }
                    }

                    if(!respuesta.get(2).toString().equals("") && !respuesta.get(2).toString().equals("null"))
                    {
                            String[] tipos = respuesta.get(2).toString().split("\\|");
                            System.out.println("Tipos[0]: "+tipos[0]);

                            int nEspecial = 0;
                            float mEspecial = 0f;
                            List<Pasajero> listaOcuapcion = new ArrayList<Pasajero>();
                            Pasajero adulto = new Pasajero();
                            Pasajero menor = new Pasajero();
                            Pasajero estudiante = new Pasajero();
                            Pasajero senectud = new Pasajero();
                            Pasajero profesor = new Pasajero();
                            Pasajero especial = new Pasajero();
                            for (int i=0; i<tipos.length;i++)
                            {
                                System.out.println("Tipos["+i+"]: "+tipos[i]);
                                String[] datos = tipos[i].split(",");
                                if(datos[0].equals("A"))
                                {
                                 adulto.setNombre("ADULTO");
                                 adulto.setOcupacion(Integer.parseInt(datos[1]));
                                 adulto.setImporte(Float.parseFloat(datos[2]));
                                }else{
                                    if(datos[0].equals("M"))
                                    {
                                     menor.setNombre("MENOR");
                                     menor.setOcupacion(Integer.parseInt(datos[1]));
                                     menor.setImporte(Float.parseFloat(datos[2]));
                                    }else{
                                        if(datos[0].equals("E"))
                                        {
                                         estudiante.setNombre("ESTUDIANTE");
                                         estudiante.setOcupacion(Integer.parseInt(datos[1]));
                                         estudiante.setImporte(Float.parseFloat(datos[2]));
                                        }else{
                                            if(datos[0].equals("S"))
                                            {
                                             senectud.setNombre("SENECTUD");
                                             senectud.setOcupacion(Integer.parseInt(datos[1]));
                                             senectud.setImporte(Float.parseFloat(datos[2]));
                                            }else{
                                                if(datos[0].equals("P"))
                                                {
                                                 profesor.setNombre("PROFESOR");
                                                 profesor.setOcupacion(Integer.parseInt(datos[1]));
                                                 profesor.setImporte(Float.parseFloat(datos[2]));
                                                }else{
                                                     especial.setNombre("ESPECIAL");
                                                     nEspecial = nEspecial+Integer.parseInt(datos[1]);
                                                     mEspecial = mEspecial+Integer.parseInt(datos[2]);
                                                     especial.setOcupacion(nEspecial);
                                                     especial.setImporte(mEspecial);
                                                }//else P
                                            }//else S
                                        }//else E
                                    }//else M
                                }//else A
                            }
                            listaOcuapcion.add(adulto);
                            listaOcuapcion.add(menor);
                            listaOcuapcion.add(estudiante);
                            listaOcuapcion.add(senectud);
                            listaOcuapcion.add(profesor);
                            listaOcuapcion.add(especial);
                            despacho.setOcupacion(listaOcuapcion);
                            System.out.println("Salio de los tipos...");
                             System.out.println("Ahora si llen alos tipos...");
                    }
            }
        System.out.println("Salio de crear despachoTarjetasResponse...");
        System.out.println("Se realizo todo...");
        return despacho;
    }
   
      
    public VentaTiempoAireResponser ventaTiempoAireT(VendERMobileFacadeRemote vendERMobileFacadeBean, VentaTiempoAireRequest ventaTiempoAireRequest)
    {
     VentaTiempoAireResponser ventaTiempoAireReq= new VentaTiempoAireResponser();
    
      System.out.println("ventaTiempoAireRequest.getCompania() "+ ventaTiempoAireRequest.getCompania() );
     System.out.println("ventaTiempoAireRequest.getTelefono() "+  ventaTiempoAireRequest.getTelefono()  );
     System.out.println("ventaTiempoAireRequest.getMonto()  "+  ventaTiempoAireRequest.getMonto() );
     System.out.println("ventaTiempoAireRequest.getTipoPago() "+  ventaTiempoAireRequest.getTipoPago() );
     System.out.println("ventaTiempoAireRequest.getNoUsuario() "+  ventaTiempoAireRequest.getNoUsuario() );
     System.out.println("ventaTiempoAireRequest.getCaja()  "+ ventaTiempoAireRequest.getCaja()  );
     System.out.println("ventaTiempoAireRequest.getCorteId() "+ ventaTiempoAireRequest.getCorteId());
     
     System.out.println("ventaTiempoAireRequest.getCanalVenta() "+ ventaTiempoAireRequest.getCanalVenta() );
     
     Vector Vresp = vendERMobileFacadeBean.VentaTiempoAire(  
                                           ventaTiempoAireRequest.getCompania(), ventaTiempoAireRequest.getTelefono(),
                                           ventaTiempoAireRequest.getMonto(),    ventaTiempoAireRequest.getTipoPago(),
                                           ventaTiempoAireRequest.getNoUsuario(), ventaTiempoAireRequest.getCaja(),
                                           ventaTiempoAireRequest.getCorteId(),   ventaTiempoAireRequest.getCanalVenta());
   
      if (Vresp!= null   && Vresp.size() > 0)
       {
           ventaTiempoAireReq.setStatus(Vresp.elementAt(0).toString());
           ventaTiempoAireReq.setMensaje(Vresp.elementAt(1).toString());
       }
      else
      {  
         ventaTiempoAireReq.setStatus("1");
          ventaTiempoAireReq.setMensaje("No se pudo realizr la recarga.");
      }
     return ventaTiempoAireReq;      
    
    }  
    
}
