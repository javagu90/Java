/*
 * TmsTransacciones.java
 *
 * Created on 27 de noviembre de 2008, 09:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.transacciones;

import TMSProyectoWeb.solicitud.TmsTxVtaFacadeRemote;
import java.util.StringTokenizer;
import java.util.Vector;
import tms_encriptacion.EncriptarMD5;
import tmspcweb.clases.asientosRequest;
import tmspcweb.clases.asientosResponse;
import tmspcweb.clases.cancelacionBoletosRequest;
import tmspcweb.clases.cancelacionBoletosResponse;
import tmspcweb.clases.cancelacionRequest;
import tmspcweb.clases.cancelacionResponse;
import tmspcweb.clases.confirmaReservacionRequest;
import tmspcweb.clases.confirmaReservacionResponse;
import tmspcweb.clases.corridasRequest;
import tmspcweb.clases.corridasResponse;
import tmspcweb.clases.datos.AsientoAutobus;
import tmspcweb.clases.datos.AsientosDisponibles;
import tmspcweb.clases.datos.AsientosReq;
import tmspcweb.clases.datos.CorridaAutobus;
import tmspcweb.clases.datos.DatoGenerico;
import tmspcweb.clases.datos.Itinerario;
import tmspcweb.clases.datos.ItinerariosReq;
import tmspcweb.clases.datos.ReservacionReq;
import tmspcweb.clases.datos.ReservacionResp;
import tmspcweb.clases.datos.TabladeHorarios;
import tmspcweb.clases.itinerariosRequest;
import tmspcweb.clases.itinerariosResponse;
import tmspcweb.clases.loginRequest;
import tmspcweb.clases.loginResponse;
import tmspcweb.clases.logoutRequest;
import tmspcweb.clases.logoutResponse;
import tmspcweb.clases.reservacionRequest;
import tmspcweb.clases.reservacionResponse;
import tmspcweb.clases.reservarAsientosCDIRequest;
import tmspcweb.clases.reservarAsientosCDIResponse;

/**
 * Clase que implementa los metodos web de la clase principal wsTmsPC3.
 * Cada uno de los 8 motodos recibe como parametro un objeto de la clase TmsTxVtaFacadeRemote (EJB),
 * ademos de la clase que identifica la solicitud.
 * @author ocruz
 */
public class TmsTransacciones {
    
    private EstadoTransaccion _EstadoTransaccion;
    private Resultado _Resultado;
    private ValidaCampos _valida;
    private String resp;
    private String[] respuesta;
    private int intValor;
    private TabladeHorarios tabladeHorarios;
    private boolean existenDBlinks;
    private Object[][] objDBLinks = null;
    private String DBLinkIda;
    private String DBLinkRegreso;
    private String _errorMsg;
    private boolean success;
    private boolean debug = true;
    /**
     * Crea una nueva instancia de la clase TmsTransacciones
     */
    public TmsTransacciones() {
        _EstadoTransaccion = new EstadoTransaccion();
        _Resultado = new Resultado();
        tabladeHorarios = new TabladeHorarios();
        _valida = new ValidaCampos();
        _errorMsg=null;
    }
      
    private void _DBLinks(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean){
        String strDBLinks = tmsTxVtaFacadeBean.getDBLinks();
        if(strDBLinks == null){
            existenDBlinks = false;
            _EstadoTransaccion.setEstadoCodigo(-1, "General", null);
            return;
        }
        //System.out.println("Links "+strDBLinks);
        objDBLinks = _DECODE(strDBLinks);
        existenDBlinks = true;
        return;
    }
    
    private String getDBLink(String origen, String objeto){
        String link;
        int i;
        for(i=0; i<objDBLinks.length; i++)
            if(objDBLinks[i][0].toString().equals(origen)){
                link = objDBLinks[i][1].toString();
                return link;
            }
        _EstadoTransaccion.setEstadoCodigo(-5, objeto, origen);
        //System.out.println("No existe dblink para el origen: "+origen);
        return "";
    }
    
    /**
     * Solicitud de Inicio de Sesion.
     * @param tmsTxVtaFacadeBean Clase EJB
     * @param loginRequest Clase loginRequest
     * @return Clase loginResponse
     */
    public loginResponse _loginRequest(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, loginRequest loginRequest) {
        System.out.println("*************** Inicia _loginRequest ***************");
        // TODO implement operation
        loginResponse loginResponse = new loginResponse();
        
        if(loginRequest.getSesion()==null || (_errorMsg=_valida.SolicitudLogin(loginRequest))!=null){
            _EstadoTransaccion.setEstadoCodigo(-3, "loginRequest",_errorMsg);
            success = false;
        }
        else{
            String NOMBRE_EQUIPO=loginRequest.getSesion().getIdPais()+"_"+loginRequest.getSesion().getIdCanal()+"_"+loginRequest.getSesion().getIdSucursal()+"_"+loginRequest.getSesion().getIdEstacionTrabajo();
            NOMBRE_EQUIPO = (NOMBRE_EQUIPO.length()>99? NOMBRE_EQUIPO.substring(0,99):NOMBRE_EQUIPO);
            String valor=null;
            try {
                valor = new EncriptarMD5().encriptar(loginRequest.getSesion().getContrasenia());
            } catch (Exception ex) {
                valor = ex.getMessage();
            }
            if(valor == null){
                _EstadoTransaccion.setEstadoCodigo(-6, "loginRequest",(valor.length()>99?valor.substring(0,99):valor));
                success = false;
            }
            else{
                //System.out.println("Parametros de IniciarSesion "+loginRequest.getSesion().getNumUsuario()+" "+valor+" "+loginRequest.getSesion().getIdSucursal()+" "+NOMBRE_EQUIPO);
                resp = tmsTxVtaFacadeBean.IniciarSesion(loginRequest.getSesion().getNumUsuario(),valor,NOMBRE_EQUIPO);
                if(resp == null){
                    _EstadoTransaccion.setEstadoCodigo(-1, "loginRequest", null);
                    success = false;
                }
                else{
                    _Resultado.setResultado1(resp);
                    if(_Resultado.getProceso1()){
                        if(_Resultado.getSuccess1())
                            loginRequest.getSesion().setIdSesion(_Resultado.getInformacion1());
                        success = _Resultado.getSuccess1();
                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "loginRequest");
                    }
                    else{
                        _EstadoTransaccion.setEstadoCodigo(-2, "loginRequest", null);
                        success = false;
                    }
                }
            }
        }
        
        loginResponse.setUid(loginRequest.getUid());
        loginResponse.setSuccess(success);
        loginResponse.setErrorCode(_EstadoTransaccion.getCodigo());
        loginResponse.setErrorMsg(_EstadoTransaccion.getCodigoMsg());
        loginResponse.setSesion(loginRequest.getSesion());
        System.out.println("*************** Termina _loginRequest ***************");
        return loginResponse;
    }

    /**
     * Solicitud de Corridas.
     * @param tmsTxVtaFacadeBean Clase EJB.
     * @param corridasRequest Clase corridasRequest.
     * @return Clase corridasResponse.
     */
    public corridasResponse _corridasRequest(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, corridasRequest corridasRequest){        
        System.out.println("*************** Inicia _corridasRequest ***************");
        corridasResponse corridasResponse = new corridasResponse();
        if(objDBLinks==null) _DBLinks(tmsTxVtaFacadeBean);
        if(existenDBlinks){
            if(corridasRequest.getSesion()==null || (_errorMsg=_valida.SolicitudCorridas(corridasRequest))!=null){
                _EstadoTransaccion.setEstadoCodigo(-3, "corridasRequest", _errorMsg);
                success = false;
            }
            else{
                String Origen, strFecha1, strFecha2, strFecha3, strFecha4, strServicio, strDestino, strEmpresa;
                int viajeRedondo;
                Origen = corridasRequest.getOrigen().getClave();
                DBLinkIda = getDBLink(Origen, "corridasRequest");
                if(!DBLinkIda.equals("")){
                    tabladeHorarios.setCodigo(corridasRequest.getHorarioSalida());
                    strFecha1 = corridasRequest.getFechaSalida()+" "+tabladeHorarios.getHora1();
                    strFecha2 = corridasRequest.getFechaSalida()+" "+tabladeHorarios.getHora2();
                    tabladeHorarios.setCodigo(corridasRequest.getHorarioRegreso());
                    strFecha3 = corridasRequest.getFechaRegreso()+" "+tabladeHorarios.getHora1();
                    strFecha4 = corridasRequest.getFechaRegreso()+" "+tabladeHorarios.getHora2();
                    if(corridasRequest.getTipoServicio() == null)
                        strServicio = null;
                    else
                        strServicio = corridasRequest.getTipoServicio().getClave();
                    strDestino = corridasRequest.getDestino().getClave();
                    //VAG23102008 Busca el nombre del negocio por el id y compara si el nombre obtenido es igual al nombre del parametro
                    strEmpresa = null;
                    if(corridasRequest.getNegocio().getId()!=null)
                    {                     
                     strEmpresa = tmsTxVtaFacadeBean.buscaNombreNegocio(corridasRequest.getNegocio().getId());
                     if(strEmpresa != null){
                         if(strEmpresa.length() > 0){
                             strEmpresa = (String) strEmpresa.subSequence(1, strEmpresa.length()-1);
                         }
                         else
                            strEmpresa = null;
                     }
                        /*if(vv.size()==0)
                          strEmpresa = null;
                        else
                        {
                            Vector v = (Vector)vv.get(0);
                            strEmpresa = v.get(0).toString();
                        }*/
                    }
                    if(corridasRequest.getNegocio().getNombre()!=null)
                    {
                        if(strEmpresa.equals(corridasRequest.getNegocio().getNombre()))
                           strEmpresa = corridasRequest.getNegocio().getNombre();
                        else
                           strEmpresa = null;
                           
                    }
                    
                    viajeRedondo = (corridasRequest.isViajeRedondo()?1:0);
                 
                   
                    if(!corridasRequest.getBusqueda()) { // si se trata de una compra normal
                      
                        int AsientosAdulto = (corridasRequest.getAsientosAdulto()<0 ? 0 : corridasRequest.getAsientosAdulto());
                        int AsientosNinio = (corridasRequest.getAsientosNinio()<0 ? 0 : corridasRequest.getAsientosNinio());
                        int AsientosEstudiante = (corridasRequest.getAsientosEstudiante()<0 ? 0 : corridasRequest.getAsientosEstudiante());
                        int AsientosMaestro = (corridasRequest.getAsientosMaestro()<0 ? 0 : corridasRequest.getAsientosMaestro());
                        int AsientosINSEN = (corridasRequest.getAsientosINSEN()<0 ? 0 : corridasRequest.getAsientosINSEN());
                        /*if(AsientosAdulto==0 && AsientosNinio==0 && AsientosEstudiante==0 && AsientosMaestro==0 && AsientosINSEN==0 ){
                            System.out.println("Rastreo5");
                            _EstadoTransaccion.setEstadoCodigo(-7, "corridasRequest", null);
                            success = false;
                        }
                        else{ */
                            System.out.println("Busqueda para Compra "+Origen+" "+strFecha1+" "+strFecha2+" "+strFecha3+" "+strFecha4+" "+strServicio+" "+strDestino+" "+strEmpresa+" corteId"+corridasRequest.getSesion().getIdSesion());
                            resp = tmsTxVtaFacadeBean.obtenerCorridasVenta(DBLinkIda, corridasRequest.getSesion().getNumUsuario(), corridasRequest.getSesion().getIdSesion(), Origen, strFecha1, strFecha2, strFecha3, strFecha4, strServicio, strDestino, strEmpresa, viajeRedondo,
                                    AsientosAdulto, AsientosNinio, AsientosEstudiante, AsientosMaestro, AsientosINSEN);
                        //}
                    }else{
                        System.out.println("Motor de Busqueda "+Origen+" "+strFecha1+" "+strFecha2+" "+strFecha3+" "+strFecha4+" "+strServicio+" "+strDestino+" "+strEmpresa+" corteId"+corridasRequest.getSesion().getIdSesion());
                        /* Modificacion OPH 060910
                         * se utiliza el mismo procedimiento de obtenerCorridasVenta pero con valores en cero en el caso de los asientos
                         *
                        resp = tmsTxVtaFacadeBean.motorBusqueda(DBLinkIda, corridasRequest.getSesion().getNumUsuario(), corridasRequest.getSesion().getIdSesion(), Origen, strFecha1, strFecha2, strFecha3, strFecha4, strServicio, strDestino, strEmpresa, viajeRedondo);
                        */
                              resp =  tmsTxVtaFacadeBean.obtenerCorridasVenta(DBLinkIda, corridasRequest.getSesion().getNumUsuario(), corridasRequest.getSesion().getIdSesion(), Origen, strFecha1, strFecha2, strFecha3, strFecha4, strServicio, strDestino, strEmpresa, viajeRedondo,
                                    0,0,0,0,0);
                    }
                  System.out.println("Regresa Corridas "+resp);
                       
                    if(resp == null){
                        _EstadoTransaccion.setEstadoCodigo(-1, "corridasRequest", null);
                        success = false;
                    }
                    else{
                        System.out.println("_Resultado.Asignando informacion ");
                        _Resultado.setResultado1(resp);
                        System.out.println("_Resultado_Resultado.getProceso1() "+_Resultado.getProceso1());
                        
                        if(_Resultado.getProceso1()){
                            System.out.println("_Resultado.getInformacion() "+_Resultado.getSuccess1());
                            if(_Resultado.getSuccess1()){
                                Object[][] objCorridas = _DECODE(_Resultado.getInformacion1());
                                System.out.println("_Resultado.getInformacion1() "+_Resultado.getInformacion1());
                                corridasResponse.setCorridas(obtenerListadoCorridas(objCorridas));
                            }
                            success = _Resultado.getSuccess1();
                            _EstadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "corridasRequest");
                        }
                        else{
                            _EstadoTransaccion.setEstadoCodigo(-2, "corridasRequest", null);
                            success = false;
                        }
                    }                        
                }
            }
        }

        corridasResponse.setUid(corridasRequest.getUid());
        corridasResponse.setSuccess(success);
        corridasResponse.setErrorCode(_EstadoTransaccion.getCodigo());
        corridasResponse.setErrorMsg(_EstadoTransaccion.getCodigoMsg());
        System.out.println("*************** Termina _corridasRequest ***************  success "
                            +success+"  _EstadoTransaccion.getCodigo() " +_EstadoTransaccion.getCodigo());
        return corridasResponse;
    }

    /**
     * Solicitud de Corridas.
     * @param tmsTxVtaFacadeBean Clase EJB.
     * @param corridasRequest Clase corridasRequest.
     * @return Clase corridasResponse.
     */
    public corridasResponse _corridasRequestCDI(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, corridasRequest corridasRequest){
        System.out.println("*************** Inicia _corridasRequest CDI ***************");
        corridasResponse corridasResponse = new corridasResponse();
        if(objDBLinks==null) _DBLinks(tmsTxVtaFacadeBean);
        if(existenDBlinks){
            if(corridasRequest.getSesion()==null || (_errorMsg=_valida.SolicitudCorridas(corridasRequest))!=null){
                _EstadoTransaccion.setEstadoCodigo(-3, "corridasRequest", _errorMsg);
                success = false;
            }
            else{
                String Origen, strFecha1, strFecha2, strFecha3, strFecha4, strServicio, strDestino, strEmpresa;
                int viajeRedondo;
                Origen = corridasRequest.getOrigen().getClave();
                DBLinkIda = getDBLink(Origen, "corridasRequest");
                if(!DBLinkIda.equals("")){
                    tabladeHorarios.setCodigo(corridasRequest.getHorarioSalida());
                    strFecha1 = corridasRequest.getFechaSalida()+" "+tabladeHorarios.getHora1();
                    strFecha2 = corridasRequest.getFechaSalida()+" "+tabladeHorarios.getHora2();
                    tabladeHorarios.setCodigo(corridasRequest.getHorarioRegreso());
                    strFecha3 = corridasRequest.getFechaRegreso()+" "+tabladeHorarios.getHora1();
                    strFecha4 = corridasRequest.getFechaRegreso()+" "+tabladeHorarios.getHora2();
                    if(corridasRequest.getTipoServicio() == null)
                        strServicio = null;
                    else
                        strServicio = corridasRequest.getTipoServicio().getClave();
                    strDestino = corridasRequest.getDestino().getClave();
                    //VAG23102008 Busca el nombre del negocio por el id y compara si el nombre obtenido es igual al nombre del parametro
                    strEmpresa = null;
                    if(corridasRequest.getNegocio().getId()!=null)
                    {
                     strEmpresa = tmsTxVtaFacadeBean.buscaNombreNegocio(corridasRequest.getNegocio().getId());
                     if(strEmpresa != null){
                         if(strEmpresa.length() > 0){
                             strEmpresa = (String) strEmpresa.subSequence(1, strEmpresa.length()-1);
                         }
                         else
                            strEmpresa = null;
                     }
                        /*if(vv.size()==0)
                          strEmpresa = null;
                        else
                        {
                            Vector v = (Vector)vv.get(0);
                            strEmpresa = v.get(0).toString();
                        }*/
                    }
                    if(corridasRequest.getNegocio().getNombre()!=null)
                    {
                        if(strEmpresa.equals(corridasRequest.getNegocio().getNombre()))
                           strEmpresa = corridasRequest.getNegocio().getNombre();
                        else
                           strEmpresa = null;

                    }

                    viajeRedondo = (corridasRequest.isViajeRedondo()?1:0);


                    if(!corridasRequest.getBusqueda()) { // si se trata de una compra normal

                        int AsientosAdulto = (corridasRequest.getAsientosAdulto()<0 ? 0 : corridasRequest.getAsientosAdulto());
                        int AsientosNinio = (corridasRequest.getAsientosNinio()<0 ? 0 : corridasRequest.getAsientosNinio());
                        int AsientosEstudiante = (corridasRequest.getAsientosEstudiante()<0 ? 0 : corridasRequest.getAsientosEstudiante());
                        int AsientosMaestro = (corridasRequest.getAsientosMaestro()<0 ? 0 : corridasRequest.getAsientosMaestro());
                        int AsientosINSEN = (corridasRequest.getAsientosINSEN()<0 ? 0 : corridasRequest.getAsientosINSEN());
                        /*if(AsientosAdulto==0 && AsientosNinio==0 && AsientosEstudiante==0 && AsientosMaestro==0 && AsientosINSEN==0 ){
                            System.out.println("Rastreo5");
                            _EstadoTransaccion.setEstadoCodigo(-7, "corridasRequest", null);
                            success = false;
                        }
                        else{ */
                            System.out.println("Busqueda para Compra CDI =>  "+Origen+" "+strFecha1+" "+strFecha2+" "+strFecha3+" "+strFecha4+" "+strServicio+" "+strDestino+" "+strEmpresa+" corteId"+corridasRequest.getSesion().getIdSesion());
                            resp = tmsTxVtaFacadeBean.obtenerCorridasVentaCDI(DBLinkIda, corridasRequest.getSesion().getNumUsuario(), corridasRequest.getSesion().getIdSesion(), Origen, strFecha1, strFecha2, strFecha3, strFecha4, strServicio, strDestino, strEmpresa, viajeRedondo,
                                    AsientosAdulto, AsientosNinio, AsientosEstudiante, AsientosMaestro, AsientosINSEN);
                        //}
                    }else{
                        System.out.println("Motor de Busqueda "+Origen+" "+strFecha1+" "+strFecha2+" "+strFecha3+" "+strFecha4+" "+strServicio+" "+strDestino+" "+strEmpresa+" corteId"+corridasRequest.getSesion().getIdSesion());
                        /* Modificacion OPH 060910
                         * se utiliza el mismo procedimiento de obtenerCorridasVenta pero con valores en cero en el caso de los asientos
                         *
                        resp = tmsTxVtaFacadeBean.motorBusqueda(DBLinkIda, corridasRequest.getSesion().getNumUsuario(), corridasRequest.getSesion().getIdSesion(), Origen, strFecha1, strFecha2, strFecha3, strFecha4, strServicio, strDestino, strEmpresa, viajeRedondo);
                        */
                              resp =  tmsTxVtaFacadeBean.obtenerCorridasVentaCDI(DBLinkIda, corridasRequest.getSesion().getNumUsuario(), corridasRequest.getSesion().getIdSesion(), Origen, strFecha1, strFecha2, strFecha3, strFecha4, strServicio, strDestino, strEmpresa, viajeRedondo,
                                    0,0,0,0,0);
                    }
                  System.out.println("Regresa Corridas "+resp);

                    if(resp == null){
                        _EstadoTransaccion.setEstadoCodigo(-1, "corridasRequest", null);
                        success = false;
                    }
                    else{
                        System.out.println("_Resultado.Asignando informacion ");
                        _Resultado.setResultado1(resp);
                        System.out.println("_Resultado_Resultado.getProceso1() "+_Resultado.getProceso1());

                        if(_Resultado.getProceso1()){
                            System.out.println("_Resultado.getInformacion() "+_Resultado.getSuccess1());
                            if(_Resultado.getSuccess1()){
                                Object[][] objCorridas = _DECODE(_Resultado.getInformacion1());
                                System.out.println("_Resultado.getInformacion1() "+_Resultado.getInformacion1());
                                corridasResponse.setCorridas(obtenerListadoCorridas(objCorridas));
                            }
                            success = _Resultado.getSuccess1();
                            _EstadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "corridasRequest");
                        }
                        else{
                            _EstadoTransaccion.setEstadoCodigo(-2, "corridasRequest", null);
                            success = false;
                        }
                    }
                }
            }
        }

        corridasResponse.setUid(corridasRequest.getUid());
        corridasResponse.setSuccess(success);
        corridasResponse.setErrorCode(_EstadoTransaccion.getCodigo());
        corridasResponse.setErrorMsg(_EstadoTransaccion.getCodigoMsg());
        System.out.println("*************** Termina _corridasRequest CDI ***************  success "
                            +success+"  _EstadoTransaccion.getCodigo() " +_EstadoTransaccion.getCodigo());
        return corridasResponse;
    }



        /**
     * Solicitud de Corridas.
     * @param tmsTxVtaFacadeBean Clase EJB.
     * @param corridasRequest Clase corridasRequest.
     * @return Clase corridasResponse.
     */
    public corridasResponse _reservacionRequestCDI(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, reservarAsientosCDIRequest preservarAsientosCDIRequest){
        System.out.println("*************** Inicia _reservacionRequestCDI  ***************");
        corridasResponse resp = new corridasResponse();
        reservarAsientosCDIResponse reservaResponse = new reservarAsientosCDIResponse();
//                  reservacionAsientosCDI(java.lang.String ORIGENI, java.lang.String ORIGENR, int PASIENTOS, java.lang.String PNOMBREPASAJERO, java.lang.String FECHAI, java.lang.String FECHAR)
          respuesta = tmsTxVtaFacadeBean.reservacionAsientosCDI(preservarAsientosCDIRequest.getOrigenIda(),preservarAsientosCDIRequest.getOrigenRegreso(),Integer.valueOf(preservarAsientosCDIRequest.getNumeroAsientos()),preservarAsientosCDIRequest.getNombrePasajero(),preservarAsientosCDIRequest.getFechaIda(),preservarAsientosCDIRequest.getFechaRegreso() );
                            if(respuesta == null){
                                _EstadoTransaccion.setEstadoCodigo(-1, "confirmaReservacionRequest", null);
                                success = false;
                                    System.out.println("La respuesta del FACADE en _reservacionRequestCDI fue null Codigo de error -1 720");
                                    resp.setSuccess(false);
                                    resp.setErrorMsg(respuesta[7]);
                                    return resp;
                            }
                            else{
                                 System.out.println("La respuesta que regreso es: "+  respuesta[6]);
                                    if(!respuesta[6].equals("0"))
                                    {
                                        resp.setSuccess(false);
                                        resp.setErrorMsg(respuesta[7]);
                                        return resp;
                                    }
                                    /*
                                        reservaResponse.setSuccess(true);
                                        reservaResponse.setReservacionIda(respuesta[0]);
                                        reservaResponse.setReservacionRegreso(respuesta[1]);
                                        reservaResponse.setNombrePasajeros(respuesta[2]);
                                        reservaResponse.setTipoPasajeros(respuesta[3]);
                                        reservaResponse.setAsientosIda(respuesta[4]);
                                        reservaResponse.setAsientosRegreso(respuesta[5]);
                                        reservaResponse.setErrorMsg(respuesta[7]);
                                     * 
                                     */
                                    CorridaAutobus[] corridas = new CorridaAutobus[2];
                                    CorridaAutobus c = new CorridaAutobus();
                                    String[] array = respuesta[0].split(";");
                                    String   reserv = array[3];
                                    String[] array2 = reserv.split(",");
                                    String   reservacion = array2[0];
                                    String   monto = array2[1].replaceAll("\\|","");
                                    c.setClaveControl(reservacion);//tReservacionIda
                                    c.setPrecioAdulto(Double.valueOf(monto));//totalBoletos
                                     c.setNumCorrida(respuesta[2]);//NombrePasajeros
                                     c.setFechaSalida(respuesta[3]);//TipoPasajeros
                                     c.setHoraSalida(respuesta[4]);//AsientosIda
                                     corridas[0]=c;
                                     c = new CorridaAutobus();
                                     array = respuesta[1].split(";");
                                    reserv = array[3];
                                    array2 = reserv.split(",");
                                    reservacion = array2[0];
                                    monto = array2[1].replaceAll("\\|","");
                                    c.setClaveControl(reservacion);//tReservacionIda
                                    c.setPrecioAdulto(Double.valueOf(monto));//totalBoletos
                                    System.out.println("montoIda: "+monto);
                                     c.setNumCorrida(respuesta[2]);//NombrePasajeros
                                     c.setFechaSalida(respuesta[3]);//TipoPasajeros
                                     c.setHoraSalida(respuesta[5]);//AsientosRegreso
                                     corridas[1]=c;
                                     resp.setCorridas(corridas);
                                     resp.setSuccess(true);
                                     success= true;
                                     resp.setErrorMsg(respuesta[7]);
                                     System.out.println("ReservacionIda: "+respuesta[0]);
                                     System.out.println("ReservacionRegreso: "+respuesta[1]);
                                     System.out.println("TipoPasajeros: "+respuesta[3]);
                                     System.out.println("AsientosIda: "+respuesta[4]);
                                     System.out.println("AsientosRegreso: "+respuesta[5]);
                                     System.out.println("montoRegreso: "+monto);
                                }


        System.out.println("*************** Termina _reservacionRequestCDI ***************  success "
                            +success+"  _EstadoTransaccion.getCodigo() " +_EstadoTransaccion.getCodigo());
        return resp;
    }


    /**/
    private Object[][] _DECODE(String strCorridas) {
        int iCampos;
        
        StringTokenizer strToken = new StringTokenizer(strCorridas,"|");
        String valor = null;
        StringTokenizer strTokenCampos = null;
        valor = strToken.nextToken();
        strTokenCampos = new StringTokenizer(valor,",");
        iCampos = strTokenCampos.countTokens();        
        //System.out.println("_DECODE no. campos: "+iCampos);
        
        strToken = new StringTokenizer(strCorridas,"|");
        valor = null;
        strTokenCampos = null;
        
        int i, j, fin;
        Object[][] oCorridas = new Object[strToken.countTokens()][iCampos];
        for(i=0; i<oCorridas.length; i++){
            valor = strToken.nextToken();
            strTokenCampos = new StringTokenizer(valor,",");
            fin = iCampos;
            for(j=0; j<fin; j++) 
                oCorridas[i][j] = strTokenCampos.nextToken(); 
        }
        return oCorridas;
    }
    /**/
    
    private CorridaAutobus[] obtenerListadoCorridas(Object[][] objCorridas) {
        CorridaAutobus[] lstCorridas = new CorridaAutobus[objCorridas.length];
        CorridaAutobus Corrida=null;
        DatoGenerico dato = null;
        
        for(int i=0; i<lstCorridas.length; i++){
            Corrida = new CorridaAutobus();
            dato = new DatoGenerico();
            dato.setId(objCorridas[i][0].toString());
            dato.setClave(objCorridas[i][1].toString());
            dato.setNombre(objCorridas[i][2].toString());
            Corrida.setOrigen(dato);
            dato = new DatoGenerico();
            dato.setId(objCorridas[i][3].toString());
            dato.setClave(objCorridas[i][4].toString());
            dato.setNombre(objCorridas[i][5].toString());
            Corrida.setDestino(dato);
            dato = new DatoGenerico();
            dato.setId(objCorridas[i][6].toString());
            dato.setClave(objCorridas[i][7].toString());
            dato.setNombre(objCorridas[i][8].toString());
            Corrida.setNegocio(dato);
            dato = new DatoGenerico();
            dato.setId(objCorridas[i][9].toString());
            dato.setClave(objCorridas[i][10].toString());
            dato.setNombre(objCorridas[i][11].toString());
            Corrida.setEmpresa(dato);
            Corrida.setFechaSalida(objCorridas[i][12].toString());
            Corrida.setHoraSalida(objCorridas[i][13].toString());
            dato = new DatoGenerico();
            dato.setId(objCorridas[i][14].toString());
            dato.setClave(objCorridas[i][15].toString());
            dato.setNombre(objCorridas[i][16].toString());
            Corrida.setTipoServicio(dato);
            Corrida.setTipoItinerario(objCorridas[i][17].toString());
            Corrida.setTipoCorrida(objCorridas[i][18].toString());
            Corrida.setAsientosDisponibles(Integer.valueOf(objCorridas[i][19].toString()));
            Corrida.setAsientosDispEstudiante(Integer.valueOf(objCorridas[i][20].toString()));
            Corrida.setAsientosDispMaestro(Integer.valueOf(objCorridas[i][21].toString()));
            Corrida.setAsientosDispINSEN(Integer.valueOf(objCorridas[i][22].toString()));
            Corrida.setPrecioAdulto(Double.valueOf(objCorridas[i][23].toString()));
            Corrida.setPrecioNinio(Double.valueOf(objCorridas[i][24].toString()));
            Corrida.setPrecioEstudiante(Double.valueOf(objCorridas[i][25].toString()));
            Corrida.setPrecioMaestro(Double.valueOf(objCorridas[i][26].toString()));
            Corrida.setPrecioINSEN(Double.valueOf(objCorridas[i][27].toString()));
            Corrida.setNumCorrida(objCorridas[i][28].toString());
            Corrida.setSentidoViaje(objCorridas[i][29].toString());
            Corrida.setFechaDefasamiento(objCorridas[i][30].toString());
            dato = new DatoGenerico();
            dato.setId(objCorridas[i][31].toString());
            dato.setNombre(objCorridas[i][32].toString());
            //dato.setId("-1");
            //dato.setNombre("PRUEBA");
            Corrida.setPlantilla(dato);
            Corrida.setCupoAutobus(Integer.valueOf(objCorridas[i][33].toString()));
            Corrida.setTipoLectura(objCorridas[i][34].toString());
            Corrida.setClaveControl(objCorridas[i][35].toString());
            Corrida.setSecuencia(Integer.valueOf(objCorridas[i][36].toString()));
            Corrida.setUbicacion(objCorridas[i][37].toString());
            lstCorridas[i] = Corrida;
        }
                
        return lstCorridas;
    }
    
    /**
     * Solicitud de Itinerarios.
     * @param tmsTxVtaFacadeBean Clase EJB.
     * @param itinerariosRequest Clase itinerariosRequest
     * @return Clase itinerariosResponse.
     */
    public itinerariosResponse _itinerariosRequest(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, itinerariosRequest itinerariosRequest){
        System.out.println("*************** Inicia  _itinerariosRequest ***************");
        itinerariosResponse itinerariosResponse = new itinerariosResponse();
        if(objDBLinks==null) _DBLinks(tmsTxVtaFacadeBean);
        if(existenDBlinks){
            if(itinerariosRequest.getSesion()==null || (_errorMsg=_valida.SolicitudItinerarios(itinerariosRequest))!=null){
                _EstadoTransaccion.setEstadoCodigo(-3, "itinerariosRequest", _errorMsg);
                success = false;
            }
            else{
                Itinerario[] itinerario = ProcesoItinerarios(tmsTxVtaFacadeBean, itinerariosRequest.getDatosIda(),"Ida");
                itinerariosResponse.setItinerarioIda(itinerario);
                if(_valida.ItinerariosRegreso(itinerariosRequest)){
                    itinerario = ProcesoItinerarios(tmsTxVtaFacadeBean, itinerariosRequest.getDatosRegreso(),"Regreso");
                    itinerariosResponse.setItinerarioRegreso(itinerario);
                    if(itinerario==null){
                        _EstadoTransaccion.setEstadoCodigo(-8, "itinerariosResponse", null);
                        success = false;
                    }
                }
            }
        }
        //System.out.println("succes final "+success);
        itinerariosResponse.setUid(itinerariosResponse.getUid());
        itinerariosResponse.setSuccess(success);
        itinerariosResponse.setErrorCode(_EstadoTransaccion.getCodigo());
        itinerariosResponse.setErrorMsg(_EstadoTransaccion.getCodigoMsg());
        System.out.println("*************** Termina _itinerariosRequest ***************");
        return itinerariosResponse;
    }
    
    private Itinerario[] obtenerItinerario(Object[][] objCorridas) {
        Itinerario[] lstItinerario = new Itinerario[objCorridas.length];
        DatoGenerico dato = null;
        
        Itinerario iti = null;
        for(int i=0; i<lstItinerario.length; i++){
            iti = new Itinerario();
            dato = new DatoGenerico();
            dato.setId(objCorridas[i][0].toString());
            dato.setClave(objCorridas[i][1].toString());
            dato.setNombre(objCorridas[i][2].toString());
            iti.setOficina(dato);
            dato = new DatoGenerico();
            iti.setHoraSalida(objCorridas[i][3].toString());
            iti.setHoraLlegada(objCorridas[i][4].toString());
            iti.setDiasDefasamiento(Integer.valueOf(objCorridas[i][5].toString()));
            iti.setTipoItinerario(objCorridas[i][6].toString());
            iti.setTipoCorrida(objCorridas[i][7].toString());
            lstItinerario[i] = iti;
        }
        System.out.println("*************** Termina obtenerItinerario ***************");
        return lstItinerario;
    }
    
    /**
     * Solicitud de Asientos.
     * @param tmsTxVtaFacadeBean Clase EJB.
     * @param asientosRequest Clase asientosRequest.
     * @return Clase asientosResponse.
     */
    public asientosResponse _asientosRequest(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, asientosRequest asientosRequest){
        System.out.println("*************** Inicia _asientosRequest ***************");
        asientosResponse asientosResponse = new asientosResponse();
        if(objDBLinks==null) _DBLinks(tmsTxVtaFacadeBean);
        if(existenDBlinks){
            if(asientosRequest.getSesion()==null || (_errorMsg=_valida.SolicitudAsientos(asientosRequest))!=null){
                _EstadoTransaccion.setEstadoCodigo(-3, "asientosRequest", _errorMsg);
                success = false;
            }
            else{
                String mapaAsientos = ProcesoAsientos(tmsTxVtaFacadeBean, asientosRequest.getDatosIda(),
                        asientosRequest.getAsientosAdulto(), asientosRequest.getAsientosNinio(), asientosRequest.getAsientosEstudiante(),
                        asientosRequest.getAsientosMaestro(), asientosRequest.getAsientosINSEN(), "Ida");
                System.out.println("##################" + mapaAsientos);
                if(mapaAsientos != null) {
                    String[] data = mapaAsientos.split("\\|");
                    System.out.println("Mapa: " + data[0]);
                    asientosResponse.setMapaAsientosIda(data[0]);
                    System.out.println("Estudiantes: " + data[1]);
                    asientosResponse.setAsientosDispEstudianteIda(Integer.parseInt(data[1]));
                    System.out.println("maestros: " + data[2]);
                    asientosResponse.setAsientosDispMaestroIda(Integer.parseInt(data[2]));
                    System.out.println("Insen: " + data[3]);
                    asientosResponse.setAsientosDispINSENIda(Integer.parseInt(data[3]));
                }
                if(_valida.AsientosRegreso(asientosRequest)){
                    mapaAsientos = ProcesoAsientos(tmsTxVtaFacadeBean, asientosRequest.getDatosRegreso(),
                            asientosRequest.getAsientosAdulto(), asientosRequest.getAsientosNinio(), asientosRequest.getAsientosEstudiante(),
                        asientosRequest.getAsientosMaestro(), asientosRequest.getAsientosINSEN(), "Regreso");
                    if(mapaAsientos==null){
                        _EstadoTransaccion.setEstadoCodigo(-8, "asientosRequest", null);
                        success = false;
                    }
                    else {
                        String[] data = mapaAsientos.split("\\|");
                        asientosResponse.setMapaAsientosRegreso(data[0]);
                        asientosResponse.setAsientosDispEstudianteRegreso(Integer.parseInt(data[1]));
                        asientosResponse.setAsientosDispMaestroRegreso(Integer.parseInt(data[2]));
                        asientosResponse.setAsientosDispINSENRegreso(Integer.parseInt(data[3]));
                    }
                }
            }
        }
        //System.out.println("success final mapa "+success);
        asientosResponse.setUid(asientosRequest.getUid());
        asientosResponse.setSuccess(success);
        asientosResponse.setErrorCode(_EstadoTransaccion.getCodigo());
        asientosResponse.setErrorMsg(_EstadoTransaccion.getCodigoMsg());
        System.out.println("*************** Termina _asientosRequest ***************");
        return asientosResponse;
    }
    
    /**
     * Solicitud de Reservacion.
     * @param tmsTxVtaFacadeBean Clase EJB.
     * @param reservacionRequest Clase reservacionRequest.
     * @return Clase reservacionResponse.
     */
    public reservacionResponse _reservacionRequest(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, reservacionRequest reservacionRequest){
        System.out.println("*************** Inicia _reservacionRequest ***************");
        boolean continuar = true;
        int numAsI = 0, numAsR = 0;
        reservacionResponse reservacionResponse = new reservacionResponse();
        if(objDBLinks==null) _DBLinks(tmsTxVtaFacadeBean);
        if(existenDBlinks){        
            if(reservacionRequest.getSesion()==null || (_errorMsg=_valida.SolicitudReservacion(reservacionRequest))!=null){
                _EstadoTransaccion.setEstadoCodigo(-3, "reservacionRequest", _errorMsg);
                success = false;
                continuar = false;
            }
            else{
                String origen = reservacionRequest.getDatosIda().getOrigen().getClave();
                DBLinkIda = getDBLink(origen, "reservacionRequest");
                if(!DBLinkIda.equals("")){
                    String numCorrida = reservacionRequest.getDatosIda().getNumCorrida();
                    AsientoAutobus[] x = null;
                    String[] cads = cadsAsiPasNombre(reservacionRequest.getDatosIda().getAsientosSeleccionados());
                    if(cads==null){
                        _EstadoTransaccion.setEstadoCodigo(-3, "reservacionRequest","Asientos seleccionados");
                        success = false;
                        continuar = false;
                    }
                    else{
                        String asientos = cads[0];
                        String pasajeros = cads[1];
                        String nombrePasajeros = cads[2];                                                 
                        String tarifas = cadsTarifas(reservacionRequest.getDatosIda());
                        if(tarifas==null){
                            _EstadoTransaccion.setEstadoCodigo(-3, "reservacionRequest","Tarifas");
                            success = false;
                            continuar = false;
                        }
                        else{
                            String origenR=null, numCorridaR=null, asientosR=null, pasajerosR=null, nombrePasajerosR=null, tarifasR=null;
                            if(_valida.ReservacionRegreso(reservacionRequest)){
                                numCorridaR = reservacionRequest.getDatosRegreso().getNumCorrida();
                                origenR =  reservacionRequest.getDatosRegreso().getOrigen().getClave();
                                cads = cadsAsiPasNombre(reservacionRequest.getDatosRegreso().getAsientosSeleccionados());
                                if(cads!=null){
                                    asientosR = cads[0];
                                    pasajerosR = cads[1];
                                    nombrePasajerosR = cads[2];
                                    for(int k = 0; k<asientosR.length();k++ ){
                                        if(asientosR.charAt(k) == ',')
                                            numAsR++;
                                    }
                                }
                                tarifasR = cadsTarifas(reservacionRequest.getDatosRegreso());
                            }
                            if(_valida.ReservacionRegreso(reservacionRequest) && (tarifasR==null || cads==null)){
                                _EstadoTransaccion.setEstadoCodigo(-3, "reservacionRequest Regreso","Tarifas, Asientos Seleccionados");
                                success = false;
                                continuar = false;
                            }
                            else{
                                for(int k = 0; k<asientos.length();k++ ){
                                    if(asientos.charAt(k) == ',')
                                        numAsI++;
                                }
                                if(((numAsI > 5)||(numAsR > 5))&&(reservacionRequest.getbanCompra().equals("0"))) {
                                    _EstadoTransaccion.setEstadoCodigo(29, "confirmaReservacionRequest", null);
                                    success = false;
                                    continuar = false;
                                }
                                /*if(((numAsI > 40)||(numAsR > 40))&&(reservacionRequest.getbanCompra().equals("1"))) {
                                    _EstadoTransaccion.setEstadoCodigo(30, "confirmaReservacionRequest", null);
                                    success = false;
                                    continuar = false;
                                }*/
                                int j = 0;
                                int cont = 0;
                                while(j < nombrePasajeros.length()){
                                    if(cont > 149) {
                                        _EstadoTransaccion.setEstadoCodigo(31, "reservacionRequest","Nombre");
                                        success = false;
                                        continuar = false;
                                    }
                                    cont++;
                                    if(nombrePasajeros.charAt(j) == ',')
                                        cont = 0;
                                    j++;
                                }
                                if(continuar){
                                    respuesta = tmsTxVtaFacadeBean.ReservarAsientos(DBLinkIda, reservacionRequest.getSesion().getNumUsuario(),
                                                                                    reservacionRequest.getSesion().getIdEstacionTrabajo(), reservacionRequest.getSesion().getIdCliente(),reservacionRequest.getbanCompra(),
                                                                                    numCorrida, origen, reservacionRequest.getDatosIda().getDestino().getClave(), asientos, pasajeros, nombrePasajeros, tarifas, 
                                                                                    numCorridaR, origenR, "", asientosR, pasajerosR, nombrePasajerosR, tarifasR);
                                     System.out.println("respuesta en _reservacionRequest: "+respuesta[0]);
                                    if(respuesta == null){
                                        _EstadoTransaccion.setEstadoCodigo(-1, "reservacionRequest", null);
                                        success = false;
                                        
                                    }
                                    else{
                                        _Resultado.setResultadoMultiple(respuesta);
                                        if(!_valida.ReservacionRegreso(reservacionRequest)){
                                            if(_Resultado.getProceso1()){
                                                //System.out.println("_Resultado.getInformacion() "+_Resultado.getInformacion1());
                                                if(_Resultado.getSuccess1()){
                                                    ReservacionResp reservacionResp = new ReservacionResp();
                                                    reservacionResp=procesoReservacion(_Resultado.getInformacion1(), reservacionRequest, true);
                                                    reservacionResponse.setImporteTotal(reservacionResp.getImporteTotal());
                                                    reservacionResponse.setReservacionIda(reservacionResp);
                                                    
                                                    if (reservacionRequest.getbanCompra().equals("0")) 
                                                        reservacionRequest.setbanCompra("RESERVACION");
                                    
                                                    if (reservacionRequest.getbanCompra().equals("1")) 
                                                        reservacionRequest.setbanCompra("VENTA");

                                                    String noimporta[] = tmsTxVtaFacadeBean.ReservacionControl(reservacionRequest.getDatosIda().getOrigen().getClave(), reservacionRequest.getDatosIda().getDestino().getClave(), reservacionRequest.getSesion().getIdEstacionTrabajo(), reservacionRequest.getbanCompra()
                                                                        ,reservacionResponse.getReservacionIda().getFolioReservacionNegocio());
                                               }
                                                success = _Resultado.getSuccess1();
                                                _EstadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "reservacionRequest");
                                            }
                                            else{
                                                _EstadoTransaccion.setEstadoCodigo(-2, "reservacionRequest", null);
                                                success = false;
                                            }
                                        }
                                        else{
                                            if(_Resultado.getProceso2()){
                                                //System.out.println("_Resultado.getInformacion() "+_Resultado.getInformacion1()+" _Resultado.getInformacion2()"+_Resultado.getInformacion2());
                                                if(_Resultado.getSuccess2()){
                                                    ReservacionResp reservacionResp = new ReservacionResp();
                                                    reservacionResp=procesoReservacion(_Resultado.getInformacion1(), reservacionRequest, true);
                                                    reservacionResponse.setReservacionIda(reservacionResp);

                                                    reservacionResp = new ReservacionResp();
                                                    reservacionResp=procesoReservacion(_Resultado.getInformacion2(), reservacionRequest, false);
                                                    reservacionResponse.setReservacionRegreso(reservacionResp);

                                                    reservacionResponse.setImporteTotal(reservacionResponse.getReservacionIda().getImporteTotal()+reservacionResponse.getReservacionRegreso().getImporteTotal());
                                                    
                                                    if (reservacionRequest.getbanCompra().equals("0")) 
                                                        reservacionRequest.setbanCompra("RESERVACION");
                                    
                                                  if (reservacionRequest.getbanCompra().equals("1")) 
                                                        reservacionRequest.setbanCompra("VENTA");
                                    
                                                  String noimporta[] = tmsTxVtaFacadeBean.ReservacionControl(reservacionRequest.getDatosIda().getOrigen().getClave(), reservacionRequest.getDatosIda().getDestino().getClave(), reservacionRequest.getSesion().getIdEstacionTrabajo(), reservacionRequest.getbanCompra()
                                                                        ,reservacionResponse.getReservacionIda().getFolioReservacionNegocio());
                                                  
                                                  noimporta = tmsTxVtaFacadeBean.ReservacionControl(reservacionRequest.getDatosRegreso().getOrigen().getClave(), reservacionRequest.getDatosRegreso().getDestino().getClave(),reservacionRequest.getSesion().getIdEstacionTrabajo(), reservacionRequest.getbanCompra()
                                                        ,reservacionResponse.getReservacionRegreso().getFolioReservacionNegocio());
                                                }
                                                success = _Resultado.getSuccess2();
                                                _EstadoTransaccion.setEstado(_Resultado.getErrorCode2(), _Resultado.getErrorMsg2(), "reservacionRequest");
                                            }
                                            else{
                                                _EstadoTransaccion.setEstadoCodigo(-8, "reservacionRequest", null);
                                                success = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        reservacionResponse.setUid(reservacionRequest.getUid());
        reservacionResponse.setSuccess(success);
        reservacionResponse.setErrorCode(_EstadoTransaccion.getCodigo());
        reservacionResponse.setErrorMsg(_EstadoTransaccion.getCodigoMsg());
        System.out.println("*************** Termina _reservacionRequest ***************");
        return reservacionResponse;
    }
    
    /**
     * Solicitud de Confirmar Reservacion.
     * @param tmsTxVtaFacadeBean Clase EJB.
     * @param confirmaReservacionRequest Clase confirmaReservacionRequest.
     * @return Clase confirmaReservacionResponse.
     */
    public confirmaReservacionResponse _confirmaReservacionRequest(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, confirmaReservacionRequest confirmaReservacionRequest){
        if(debug) 
            System.out.println(" ********************* _confirmaReservacionRequest **************************** ");
        confirmaReservacionResponse confirmaReservacionResponse = new confirmaReservacionResponse();
        if(objDBLinks==null) _DBLinks(tmsTxVtaFacadeBean);        
        if(existenDBlinks){
            if(confirmaReservacionRequest.getSesion()==null || (_errorMsg=_valida.SolicitudConfirmaReservacion(confirmaReservacionRequest))!=null){
                _EstadoTransaccion.setEstadoCodigo(-3, "reservacionRequest", _errorMsg);
                success = false;
                if(debug) 
                    System.out.println("Validacion de la Solicitud de Confirma Reservacion "+success+" Codigo de Error -3 653");                    
                
            }
            else{
                String strOrigen = confirmaReservacionRequest.getDatosIda().getOrigen().getClave();
                DBLinkIda = getDBLink(strOrigen, "reservacionRequest");
                if(debug) {
                    System.out.println("Paso la Valicacion de los datos de ConfirmaReservacion 659");
                    System.out.println("El dblink del origen es "+DBLinkIda+" 666");
                }
                if(!DBLinkIda.equals("")){
                    String numCorrida = confirmaReservacionRequest.getDatosIda().getNumCorrida();
                    //confirmaReservacionRequest.getDatosIda().
                    String[] cads = cadsAsiPasNombre(confirmaReservacionRequest.getDatosIda().getAsientosSeleccionados());
                    if(cads==null){
                        _EstadoTransaccion.setEstadoCodigo(-3, "confirmaReservacionRequest","Asientos seleccionados");
                        success = false;
                        if(debug) 
                            System.out.println("Encontro Informacion de No. Asiento, Tipos de Pasajero y Nombres? "+success+" Codigo de Error -3");
                    }
                    else{
                        String asientos = cads[0];
                        String pasajeros = cads[1];
                        String nombrePasajeros = cads[2];
                        if(debug) {
                            System.out.println("Los no. asiento son : "+asientos);
                            System.out.println("Los tipos de pasajero son : "+pasajeros);
                            System.out.println("Los tipos nombrePasajeros son : "+nombrePasajeros);
                        }
                        String origenR=null, numCorridaR=null, asientosR=null, pasajerosR=null, nombrePasajerosR=null, tarifasR=null, claveRvnR=null, bancoR=null, tipoPagoR=null;
                        if(_valida.ComfirmaReservacionRegreso(confirmaReservacionRequest)){
                            if(debug) 
                                System.out.println("Es un boleto Redondo");
                            numCorridaR = confirmaReservacionRequest.getDatosRegreso().getNumCorrida();
                            origenR = confirmaReservacionRequest.getDatosRegreso().getOrigen().getClave();
                            claveRvnR = confirmaReservacionRequest.getDatosRegreso().getFolioReservacionNegocio();
                            tipoPagoR = confirmaReservacionRequest.getDatosRegreso().getTipoPago();
                            bancoR=confirmaReservacionRequest.getDatosRegreso().getBanco();
                            cads = cadsAsiPasNombre(confirmaReservacionRequest.getDatosRegreso().getAsientosSeleccionados());
                            if(cads!=null){
                                asientosR = cads[0];
                                pasajerosR = cads[1];
                                nombrePasajerosR = cads[2];
                                if(debug) {
                                    System.out.println("Los no. asiento de Regreso son : "+asientosR);
                                    System.out.println("Los tipos de pasajero de Regreso son : "+pasajerosR);
                                    System.out.println("Los tipos nombrePasajeros de Regreso son : "+nombrePasajerosR);
                                }
                            }
                        }
                        if(_valida.ComfirmaReservacionRegreso(confirmaReservacionRequest) && cads==null){
                            _EstadoTransaccion.setEstadoCodigo(-3, "confirmaReservacionRequest Regreso","Asientos Seleccionados");
                            success = false;
                            if(debug) 
                                System.out.println("Es un boleto Redondo, pero no hay informacion de no. asiento, ni tipos de pasajero ni nombres Codigo de Error -3");
                        }
                        else{
                            respuesta = tmsTxVtaFacadeBean.ConfirmarReservacion(DBLinkIda, confirmaReservacionRequest.getSesion().getNumUsuario(), confirmaReservacionRequest.getSesion().getIdSesion() , confirmaReservacionRequest.getSesion().getIdEstacionTrabajo(),confirmaReservacionRequest.getSesion().getClaveCaja(),
                                        confirmaReservacionRequest.getSesion().getIdCliente(),
                                    numCorrida, strOrigen, asientos, confirmaReservacionRequest.getDatosIda().getFolioReservacionNegocio(), confirmaReservacionRequest.getDatosIda().getTipoPago(), confirmaReservacionRequest.getDatosIda().getBanco(),
                                    numCorridaR, origenR, asientosR, claveRvnR, tipoPagoR, bancoR);                                                       
                            /*if(debug) 
                                System.out.println("La respuesta en el WS del FACADE de ConfirmarReservacion es "+respuesta);*/
                            if(respuesta == null){
                                _EstadoTransaccion.setEstadoCodigo(-1, "confirmaReservacionRequest", null);
                                success = false;
                                if(debug) 
                                    System.out.println("La respuesta del FACADE fue null Codigo de error -1 720");
                            }
                            else{
                                _Resultado.setResultadoMultiple(respuesta);
                                if(!_valida.ComfirmaReservacionRegreso(confirmaReservacionRequest)){
                                    if(_Resultado.getProceso1()){
                                        System.out.println("_Resultado.getInformacion() "+_Resultado.getInformacion1());
                                        if(_Resultado.getSuccess1()){
                                            AsientoAutobus[] x = procesoConfirmaReservacion(_Resultado.getInformacion1(), confirmaReservacionRequest, true);
                                            confirmaReservacionResponse.setAsientosAutobusIda(x);
                                        }
                                        success = _Resultado.getSuccess1();
                                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "confirmaReservacionRequest");
                                        if(debug) {
                                            System.out.println("Confirma Reservacion 736 "+_Resultado.getErrorCode1()+" "+_Resultado.getErrorMsg1());
                                        }
                                    }
                                    else{
                                        _EstadoTransaccion.setEstadoCodigo(-2, "confirmaReservacionRequest", null);
                                        success = false;
                                        if(debug) 
                                            System.out.println("Confirma Reservacion 728 Fallo Al decodificar los resultados del FACADE Codigo de Error -2");
                                    }
                                }
                                else{
                                    if(_Resultado.getProceso2()){
                                        /*System.out.println("748 Confirma Reservacion Decodifico la informacion correctamente \n"+
                                                " _Resultado.getInformacion1() "+_Resultado.getInformacion1()+" _Resultado.getInformacion2()"+_Resultado.getInformacion2());*/
                                        if(_Resultado.getSuccess2()){
                                            AsientoAutobus[] x = procesoConfirmaReservacion(_Resultado.getInformacion1(), confirmaReservacionRequest, true);
                                            confirmaReservacionResponse.setAsientosAutobusIda(x);

                                            x = procesoConfirmaReservacion(_Resultado.getInformacion2(), confirmaReservacionRequest, false);
                                            confirmaReservacionResponse.setAsientosAutobusRegreso(x);
                                        }
                                        success = _Resultado.getSuccess2();
                                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode2(), _Resultado.getErrorMsg2(), "reservacionRequest");                                        
                                    }
                                    else{
                                        _EstadoTransaccion.setEstadoCodigo(-8, "reservacionRequest", null);
                                        success = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        confirmaReservacionResponse.setUid(confirmaReservacionRequest.getUid());
        confirmaReservacionResponse.setSuccess(success);
        confirmaReservacionResponse.setErrorCode(_EstadoTransaccion.getCodigo());
        confirmaReservacionResponse.setErrorMsg(_EstadoTransaccion.getCodigoMsg());
        
        if(debug)
        System.out.println(" FINAL \n"+
                " UID "+confirmaReservacionRequest.getUid()+
                " success "+success+
                " _EstadoTransaccion.getCodigo() "+_EstadoTransaccion.getCodigo()+
                " _EstadoTransaccion.getCodigoMsg() "+_EstadoTransaccion.getCodigoMsg());
        System.out.println("*************** Termina _confirmaReservacionRequest ***************");
        return confirmaReservacionResponse;
    }

    /**
     * Solicitud de Confirmar Reservacion.
     * @param tmsTxVtaFacadeBean Clase EJB.
     * @param confirmaReservacionRequest Clase confirmaReservacionRequest.
     * @return Clase confirmaReservacionResponse.
     */
    public confirmaReservacionResponse _confirmaReservacionRequestCDI(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, confirmaReservacionRequest pconfirmaReservacionRequest){
            System.out.println(" ********************* _confirmaReservacionRequest CDI **************************** ");
        confirmaReservacionResponse confirmaReservacionResponse = new confirmaReservacionResponse();
        if(objDBLinks==null) _DBLinks(tmsTxVtaFacadeBean);
        if(existenDBlinks){
            confirmaReservacionRequest confirmaReservacion = pconfirmaReservacionRequest;

                String[] strOrigenDestino = confirmaReservacion.getDatosIda().getOrigen().getClave().split("\\|");
                String strOrigen =strOrigenDestino[0];
                String strdestino = strOrigenDestino[1];
                String liga ="TMS";
                try{
                      liga  = strOrigenDestino[2];
                } catch(java.lang.ArrayIndexOutOfBoundsException e)
                {
                    liga ="TMS";
                }
                DBLinkIda = getDBLink(strOrigen, "reservacionRequest");
                DBLinkRegreso = getDBLink(strdestino, "reservacionRequest");
                    System.out.println("Paso la Valicacion de los datos de ConfirmaReservacion 659");
                    System.out.println("origen: "+strOrigen);
                    System.out.println("El dblink del origen es "+DBLinkIda);
                    System.out.println("liga: "+liga);
                if(!DBLinkIda.equals(""))
                {
                    //String numCorrida = confirmaReservacionRequest.getDatosIda().getNumCorrida();
                    String[] nc = confirmaReservacion.getDatosIda().getFolioReservacionNegocio().split("\\|");

                    String numCorridaId = tmsTxVtaFacadeBean.getNumCorridaCDI(nc[0],DBLinkIda);
                    String numCorridaReg = tmsTxVtaFacadeBean.getNumCorridaCDI(nc[1],DBLinkRegreso);

                    String[] datos = numCorridaId.split("\\|");
                    String numCorridaIda = datos[0];
                    String datosCorridaIda= datos[1];
                    datos = numCorridaReg.split("\\|");
                    String numCorridaRegreso = datos[0];
                    String datosCorridaIdRegreso= datos[1];
                    //confirmaReservacionRequest.getDatosIda().
                    //String[] cads = cadsAsiPasNombre(confirmaReservacionRequest.getDatosIda().getAsientosSeleccionados());
                    System.out.println("Datos de asientos, pasaje y nombres: "+confirmaReservacion.getDatosIda().getNumCorrida());
                    System.out.println("Folio Reservacion ida:"+nc[0]);
                    System.out.println("Folio Reservacion regreso:"+nc[1]);
                    System.out.println("numCorrida Reservacion ida:"+numCorridaIda);
                    System.out.println("numCorrida Reservacion regreso:"+numCorridaRegreso);
                    String[] cads = confirmaReservacion.getDatosIda().getNumCorrida().split("-");
                    /*if(cads==null){
                        _EstadoTransaccion.setEstadoCodigo(-3, "confirmaReservacionRequest","Asientos seleccionados");
                        success = false;
                        if(debug)
                            System.out.println("Encontro Informacion de No. Asiento, Tipos de Pasajero y Nombres? "+success+" Codigo de Error -3");
                    }*/
                        String asientos = cads[0];
                        String pasajeros = cads[1];
                        String nombrePasajeros = cads[2];
                        String asientosRegreso = cads[3];
                        if(debug) {
                            System.out.println("Los no. asiento son : "+asientos);
                            System.out.println("Los no. asiento regreso son : "+asientosRegreso);
                            System.out.println("Los tipos de pasajero son : "+pasajeros);
                            System.out.println("Los tipos nombrePasajeros son : "+nombrePasajeros);
                        }
                        String[] array = asientos.split(",");
                        String[] arrayNombres = nombrePasajeros.split(",");
                        System.out.println("array.length: "+array.length);
                        AsientoAutobus[] arrayAsientos = new AsientoAutobus[((array.length)-1)];
                        for(int k=1;k<array.length;k++)
                        {
                         int indice = k-1;
                         AsientoAutobus as = new AsientoAutobus();
                         as.setNumeroAsiento(Integer.valueOf(array[k]));
                         as.setTipoAsiento("A");
                         as.setNombreOcupante(arrayNombres[k]);
                         as.setFolioCancelacionEK(datosCorridaIda+"-"+datosCorridaIdRegreso+"-"+((array.length)-1 ));
                         System.out.println("guarda el indice: "+indice+" y la variable k= "+k);
                         arrayAsientos[indice] = as;
                        } 
                        confirmaReservacion.getDatosIda().setAsientosSeleccionados(arrayAsientos);
                        System.out.println("ArrayAientos: "+arrayAsientos.length);
                        String origenR=null, numCorridaR=null, asientosR=null, pasajerosR=null, nombrePasajerosR=null, tarifasR=null, claveRvnR=null, bancoR=null, tipoPagoR=null;
                        /*if(_valida.ComfirmaReservacionRegreso(confirmaReservacionRequest)){
                            if(debug)
                                System.out.println("Es un boleto Redondo");
                            numCorridaR = confirmaReservacionRequest.getDatosRegreso().getNumCorrida();
                            origenR = confirmaReservacionRequest.getDatosRegreso().getOrigen().getClave();
                            claveRvnR = confirmaReservacionRequest.getDatosRegreso().getFolioReservacionNegocio();
                            tipoPagoR = confirmaReservacionRequest.getDatosRegreso().getTipoPago();
                            bancoR=confirmaReservacionRequest.getDatosRegreso().getBanco();
                            cads = cadsAsiPasNombre(confirmaReservacionRequest.getDatosRegreso().getAsientosSeleccionados());
                            if(cads!=null){
                                asientosR = cads[0];
                                pasajerosR = cads[1];
                                nombrePasajerosR = cads[2];
                                if(debug) {
                                    System.out.println("Los no. asiento de Regreso son : "+asientosR);
                                    System.out.println("Los tipos de pasajero de Regreso son : "+pasajerosR);
                                    System.out.println("Los tipos nombrePasajeros de Regreso son : "+nombrePasajerosR);
                                }
                            }
                        }
                        if(_valida.ComfirmaReservacionRegreso(confirmaReservacionRequest) && cads==null){
                            _EstadoTransaccion.setEstadoCodigo(-3, "confirmaReservacionRequest Regreso","Asientos Seleccionados");
                            success = false;
                            if(debug)
                                System.out.println("Es un boleto Redondo, pero no hay informacion de no. asiento, ni tipos de pasajero ni nombres Codigo de Error -3");
                        }*/
                        /********************** Registra los datos de IDA *********/
                            respuesta = tmsTxVtaFacadeBean.ConfirmarReservacionCDI(DBLinkIda, "1001", "1156155" , "192.168.16.1","CAJAWEB",
                                        null,
                                    numCorridaIda, strOrigen, asientos, nc[0], "BBV", "SANTANDER",
                                    numCorridaR, origenR, asientosR, claveRvnR, tipoPagoR, bancoR,liga);
                            /*if(debug)
                                System.out.println("La respuesta en el WS del FACADE de ConfirmarReservacion es "+respuesta);*/
                            if(respuesta == null){
                                _EstadoTransaccion.setEstadoCodigo(-1, "confirmaReservacionRequest", null);
                                success = false;
                                if(debug)
                                    System.out.println("La respuesta del FACADE fue null Codigo de error -1 720");
                            }
                            else{
                                _Resultado.setResultadoMultiple(respuesta);
                                if(!_valida.ComfirmaReservacionRegreso(confirmaReservacion)){
                                    if(_Resultado.getProceso1()){
                                        System.out.println("_Resultado.getInformacion() "+_Resultado.getInformacion1());
                                        if(_Resultado.getSuccess1()){
                                            AsientoAutobus[] x = procesoConfirmaReservacion(_Resultado.getInformacion1(), confirmaReservacion, true);
                                            confirmaReservacionResponse.setAsientosAutobusIda(x);
                                        }
                                        success = _Resultado.getSuccess1();
                                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "confirmaReservacionRequest");
                                        if(debug) {
                                            System.out.println("Confirma Reservacion 736 "+_Resultado.getErrorCode1()+" "+_Resultado.getErrorMsg1());
                                        }
                                    }
                                    else{
                                        _EstadoTransaccion.setEstadoCodigo(-2, "confirmaReservacionRequest", null);
                                        success = false;
                                        if(debug)
                                            System.out.println("Confirma Reservacion 728 Fallo Al decodificar los resultados del FACADE Codigo de Error -2");
                                    }
                                }
                                else{
                                    if(_Resultado.getProceso2()){
                                        /*System.out.println("748 Confirma Reservacion Decodifico la informacion correctamente \n"+
                                                " _Resultado.getInformacion1() "+_Resultado.getInformacion1()+" _Resultado.getInformacion2()"+_Resultado.getInformacion2());*/
                                        if(_Resultado.getSuccess2()){
                                            AsientoAutobus[] x = procesoConfirmaReservacion(_Resultado.getInformacion1(), confirmaReservacion, true);
                                            confirmaReservacionResponse.setAsientosAutobusIda(x);

                                            //x = procesoConfirmaReservacion(_Resultado.getInformacion2(), confirmaReservacion, false);
                                            //confirmaReservacionResponse.setAsientosAutobusRegreso(x);
                                        }
                                        success = _Resultado.getSuccess2();
                                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode2(), _Resultado.getErrorMsg2(), "reservacionRequest");
                                    }
                                    else{
                                        _EstadoTransaccion.setEstadoCodigo(-8, "reservacionRequest", null);
                                        success = false;
                                    }
                                }
                            }//if(respuesta == null)

                        /********************** Registra los datos de Regreso *********/
                            respuesta = tmsTxVtaFacadeBean.ConfirmarReservacionCDI(DBLinkRegreso, "1001", "1156155" , "192.168.16.1","CAJAWEB",
                                        null,
                                    numCorridaRegreso, strdestino, asientosRegreso, nc[1], "BBV", "SANTANDER",
                                    numCorridaR, origenR, asientosR, claveRvnR, tipoPagoR, bancoR,liga);
                            /*if(debug)
                                System.out.println("La respuesta en el WS del FACADE de ConfirmarReservacion es "+respuesta);*/
                            if(respuesta == null){
                                _EstadoTransaccion.setEstadoCodigo(-1, "confirmaReservacionRequest", null);
                                success = false;
                                if(debug)
                                    System.out.println("La respuesta del FACADE fue null Codigo de error -1 720");
                            }
                            else{/*
                                _Resultado.setResultadoMultiple(respuesta);
                                if(!_valida.ComfirmaReservacionRegreso(confirmaReservacion)){
                                    if(_Resultado.getProceso1()){
                                        System.out.println("_Resultado.getInformacion() "+_Resultado.getInformacion1());
                                        if(_Resultado.getSuccess1()){
                                            AsientoAutobus[] x = procesoConfirmaReservacion(_Resultado.getInformacion1(), confirmaReservacion, true);
                                            confirmaReservacionResponse.setAsientosAutobusIda(x);
                                        }
                                        success = _Resultado.getSuccess1();
                                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "confirmaReservacionRequest");
                                        if(debug) {
                                            System.out.println("Confirma Reservacion 736 "+_Resultado.getErrorCode1()+" "+_Resultado.getErrorMsg1());
                                        }
                                    }
                                    else{
                                        _EstadoTransaccion.setEstadoCodigo(-2, "confirmaReservacionRequest", null);
                                        success = false;
                                        if(debug)
                                            System.out.println("Confirma Reservacion 728 Fallo Al decodificar los resultados del FACADE Codigo de Error -2");
                                    }
                                }
                                else{
                                    if(_Resultado.getProceso2()){
                                        --System.out.println("748 Confirma Reservacion Decodifico la informacion correctamente \n"+
                                          --      " _Resultado.getInformacion1() "+_Resultado.getInformacion1()+" _Resultado.getInformacion2()"+_Resultado.getInformacion2());
                                        if(_Resultado.getSuccess2()){
                                            AsientoAutobus[] x = procesoConfirmaReservacion(_Resultado.getInformacion1(), confirmaReservacion, true);
                                            confirmaReservacionResponse.setAsientosAutobusIda(x);

                                            //x = procesoConfirmaReservacion(_Resultado.getInformacion2(), confirmaReservacion, false);
                                            //confirmaReservacionResponse.setAsientosAutobusRegreso(x);
                                        }
                                        success = _Resultado.getSuccess2();
                                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode2(), _Resultado.getErrorMsg2(), "reservacionRequest");
                                    }
                                    else{
                                        _EstadoTransaccion.setEstadoCodigo(-8, "reservacionRequest", null);
                                        success = false;
                                    }
                                }*/
                            }//if(respuesta == null)





                }

        }

        confirmaReservacionResponse.setUid("1156155");//confirmaReservacion.getUid());
        confirmaReservacionResponse.setSuccess(success);
        confirmaReservacionResponse.setErrorCode(_EstadoTransaccion.getCodigo());
        confirmaReservacionResponse.setErrorMsg(_EstadoTransaccion.getCodigoMsg());

        System.out.println(" FINAL \n"+
                " UID "+//confirmaReservacionRequest.getUid()+
                " success "+success+
                " _EstadoTransaccion.getCodigo() "+_EstadoTransaccion.getCodigo()+
                " _EstadoTransaccion.getCodigoMsg() "+_EstadoTransaccion.getCodigoMsg());
//        System.out.println("confirmaReservacionResponse.getDatosIda().getAsientosSeleccionados() = "+confirmaReservacionResponse.getAsientosAutobusIda().length);
//        System.out.println("confirmaReservacionResponse.getDatosIda().getAsientosSeleccionados().getAsientosAutobusIda()[0].getFolioBoletoNegocio() = "+confirmaReservacionResponse.getAsientosAutobusIda()[0].getFolioBoletoNegocio());
//        System.out.println("confirmaReservacionResponse.getDatosIda().getAsientosSeleccionados().getAsientosAutobusIda()[0].getNombreOcupante() = "+confirmaReservacionResponse.getAsientosAutobusIda()[0].getNombreOcupante());
//        System.out.println("confirmaReservacionResponse.getDatosIda().getAsientosSeleccionados()getAsientosAutobusIda()[0].getTipoAsiento() = "+confirmaReservacionResponse.getAsientosAutobusIda()[0].getTipoAsiento());

        System.out.println("*************** Termina _confirmaReservacionRequest CDI ***************");
        return confirmaReservacionResponse;
    }



    /**
     * Solicitud de Cancelar Reservacion.
     * @param tmsTxVtaFacadeBean Clase EJB.
     * @param cancelacionRequest Clase cancelacionRequest.
     * @return Clase cancelacionResponse.
     */
    public cancelacionResponse _cancelaReservacionRequest(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, cancelacionRequest cancelacionRequest){
        System.out.println("*************** Inicia _cancelaReservacionRequest ***************");
        cancelacionResponse cancelacionResponse = new cancelacionResponse();
        if(objDBLinks==null) _DBLinks(tmsTxVtaFacadeBean);
        if(existenDBlinks){        
            if(cancelacionRequest.getSesion()==null || (_errorMsg=_valida.SolicitudCancelaReservacion(cancelacionRequest))!=null){
                _EstadoTransaccion.setEstadoCodigo(-3, "cancelacionRequest", _errorMsg);
                success = false;
            }
            else{
                String strOrigen = cancelacionRequest.getDatosIda().getOrigen().getClave();
                DBLinkIda = getDBLink(strOrigen, "reservacionRequest");
                if(!DBLinkIda.equals("")){
                    String numCorrida = cancelacionRequest.getDatosIda().getNumCorrida();
                    String[] cads = cadsAsiPasNombre(cancelacionRequest.getDatosIda().getAsientosSeleccionados());
                    if(cads==null){
                        _EstadoTransaccion.setEstadoCodigo(-3, "cancelacionRequest","Asientos seleccionados");
                        success = false;
                    }
                    else{
                        String asientos = cads[0];
                        String pasajeros = cads[1];
                        String nombrePasajeros = cads[2];                                               
                        
                        String origenR=null, numCorridaR=null, asientosR=null, pasajerosR=null;
                        if(_valida.CancelaReservacionRegreso(cancelacionRequest)){
                            numCorridaR = cancelacionRequest.getDatosRegreso().getNumCorrida();
                            origenR = cancelacionRequest.getDatosRegreso().getOrigen().getClave();
                            cads = cadsAsiPasNombre(cancelacionRequest.getDatosRegreso().getAsientosSeleccionados());
                            if(cads!=null){
                                asientosR = cads[0];
                                pasajerosR = cads[1];
                            }
                        }
                        if(_valida.CancelaReservacionRegreso(cancelacionRequest) && cads==null){
                            _EstadoTransaccion.setEstadoCodigo(-3, "cancelacionRequest Regreso","Asientos Seleccionados");
                            success = false;
                        }
                        else{
                            respuesta = tmsTxVtaFacadeBean.CancelaReservacion(DBLinkIda, cancelacionRequest.getSesion().getNumUsuario(),
                                    numCorrida, strOrigen, asientos, pasajeros, cancelacionRequest.getFolioReservacionNegocio(),
                                    numCorridaR, origenR, asientosR, pasajerosR, cancelacionRequest.getFolioReservacionNegocio(),
                                    cancelacionRequest.getMotivoCancelacion());

                            if(respuesta == null){
                                _EstadoTransaccion.setEstadoCodigo(-1, "cancelacionRequest", null);
                                success = false;
                            }
                            else{
                                _Resultado.setResultadoMultiple(respuesta);
                                if(!_valida.CancelaReservacionRegreso(cancelacionRequest))
                                {
                                    if(_Resultado.getProceso1()){
                                        //System.out.println("_Resultado.getInformacion() "+_Resultado.getInformacion1());
                                        if(_Resultado.getSuccess1()){
                                            AsientoAutobus[] x = procesoCancelaReservacion(_Resultado.getInformacion1(), cancelacionRequest, true);
                                            cancelacionResponse.setAsientosAutobusIda(x);
                                        }
                                        success = _Resultado.getSuccess1();
                                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "confirmaReservacionRequest");
                                    }
                                    else{
                                        _EstadoTransaccion.setEstadoCodigo(-2, "confirmaReservacionRequest", null);
                                        success = false;
                                    }
                                }
                                else{
                                    if(_Resultado.getProceso2()){
                                        //System.out.println("_Resultado.getInformacion() "+_Resultado.getInformacion1()+" _Resultado.getInformacion2()"+_Resultado.getInformacion2());
                                        if(_Resultado.getSuccess2()){
                                            AsientoAutobus[] x = procesoCancelaReservacion(_Resultado.getInformacion1(), cancelacionRequest, true);
                                            cancelacionResponse.setAsientosAutobusIda(x);

                                            x = procesoCancelaReservacion(_Resultado.getInformacion2(), cancelacionRequest, false);
                                            cancelacionResponse.setAsientosAutobusRegreso(x);
                                        }
                                        success = _Resultado.getSuccess2();
                                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode2(), _Resultado.getErrorMsg2(), "reservacionRequest");
                                    }
                                    else{
                                        _EstadoTransaccion.setEstadoCodigo(-8, "reservacionRequest", null);
                                        success = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        cancelacionResponse.setUid(cancelacionRequest.getUid());
        cancelacionResponse.setSuccess(success);
        cancelacionResponse.setErrorCode(_EstadoTransaccion.getCodigo());
        cancelacionResponse.setErrorMsg(_EstadoTransaccion.getCodigoMsg());
        System.out.println("*************** Termina _cancelaReservacionRequest ***************");
        return cancelacionResponse;
    }


    /**
     * Solicitud de Cancelar Reservacion.
     * @param tmsTxVtaFacadeBean Clase EJB.
     * @param cancelacionRequest Clase cancelacionRequest.
     * @return Clase cancelacionResponse.
     */
    public cancelacionResponse _cancelaReservacionRequestCDI(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, cancelacionRequest cancelacionRequest){
        System.out.println("*************** Inicia _cancelaReservacionRequest CDI ***************");
        //$mensaje = "NO SE HIZO EL CARGO EN LA TARJETA, SE LIBERA RESERVACION"."-".$origen."-".$destino."-".$asientosIda."-".$tipoAsientos."-".$nombres."-".$asientosRegreso."-".$folioReservacionIda."-".$folioReservacionRegreso;
        System.out.println("cancelacionRequest.getMotivoCancelacion(): "+cancelacionRequest.getMotivoCancelacion());
        String[] datos = cancelacionRequest.getMotivoCancelacion().split("\\|");
        String motivo = datos[0];
        String strOrigen =datos[1];
        String strDestino =datos[2];
        String asientos = datos[3];
        String pasajeros = datos[4];
        String nombrePasajeros = datos[5];
        String asientosRegreso = datos[6];
        String folioReservacionIda = datos[7];
        String folioReservacionRegreso = datos[8];



        cancelacionResponse cancelacionResponse = new cancelacionResponse();
        if(objDBLinks==null) _DBLinks(tmsTxVtaFacadeBean);
        if(existenDBlinks){
                //String strOrigen = cancelacionRequest.getDatosIda().getOrigen().getClave();
                DBLinkIda = getDBLink(strOrigen, "reservacionRequest");
                DBLinkRegreso = getDBLink(strDestino, "reservacionRequest");
                System.out.println("DBLinkIda: "+DBLinkIda);
                System.out.println("DBLinkRegreso: "+DBLinkRegreso);
                String numCorridaId = tmsTxVtaFacadeBean.getNumCorridaCDI(folioReservacionIda,DBLinkIda);
                String numCorridaReg = tmsTxVtaFacadeBean.getNumCorridaCDI(folioReservacionRegreso,DBLinkRegreso);

                String[] datos2 = numCorridaId.split("\\|");
                String numCorridaIda = datos2[0];
                String datosCorridaIda= datos2[1];
                datos2 = numCorridaReg.split("\\|");
                String numCorridaRegreso = datos2[0];
                String datosCorridaIdRegreso= datos2[1];

                if(!DBLinkIda.equals("")){
                    //String numCorrida = cancelacionRequest.getDatosIda().getNumCorrida();
                    //String[] cads = cadsAsiPasNombre(cancelacionRequest.getDatosIda().getAsientosSeleccionados());
                    /*if(cads==null){
                        _EstadoTransaccion.setEstadoCodigo(-3, "cancelacionRequest","Asientos seleccionados");
                        success = false;
                    }*/
                    if(true){
//                        String asientos = cads[0];
//                        String pasajeros = cads[1];
//                        String nombrePasajeros = cads[2];

                        String origenR=null, numCorridaR=null, asientosR=null, pasajerosR=null;

                        if(true){
                            respuesta = tmsTxVtaFacadeBean.CancelaReservacion(DBLinkIda, "1001",
                                    numCorridaIda, strOrigen, asientos, pasajeros, folioReservacionIda,
                                    numCorridaR, origenR, asientosR, pasajerosR, folioReservacionIda,
                                    motivo);

                            if(respuesta == null){
                                _EstadoTransaccion.setEstadoCodigo(-1, "cancelacionRequest", null);
                                success = false;
                            }
                            else{
                                _Resultado.setResultadoMultiple(respuesta);
                                if(!_valida.CancelaReservacionRegreso(cancelacionRequest))
                                {
                                    if(_Resultado.getProceso1()){
                                        //System.out.println("_Resultado.getInformacion() "+_Resultado.getInformacion1());
                                        /*if(_Resultado.getSuccess1()){
                                            AsientoAutobus[] x = procesoCancelaReservacion(_Resultado.getInformacion1(), cancelacionRequest, true);
                                            cancelacionResponse.setAsientosAutobusIda(x);
                                        }*/
                                        success = _Resultado.getSuccess1();
                                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "confirmaReservacionRequest");
                                    }
                                    else{
                                        _EstadoTransaccion.setEstadoCodigo(-2, "confirmaReservacionRequest", null);
                                        success = false;
                                    }
                                }
                                else{
                                    if(_Resultado.getProceso2()){
                                        //System.out.println("_Resultado.getInformacion() "+_Resultado.getInformacion1()+" _Resultado.getInformacion2()"+_Resultado.getInformacion2());
                                        if(_Resultado.getSuccess2()){
                                            /*
                                            AsientoAutobus[] x = procesoCancelaReservacion(_Resultado.getInformacion1(), cancelacionRequest, true);
                                            cancelacionResponse.setAsientosAutobusIda(x);

                                            x = procesoCancelaReservacion(_Resultado.getInformacion2(), cancelacionRequest, false);
                                            cancelacionResponse.setAsientosAutobusRegreso(x);
                                             *
                                             */
                                        }
                                        success = _Resultado.getSuccess2();
                                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode2(), _Resultado.getErrorMsg2(), "reservacionRequest");
                                    }
                                    else{
                                        _EstadoTransaccion.setEstadoCodigo(-8, "reservacionRequest", null);
                                        success = false;
                                    }
                                }
                            }
                        }
                        /***************** Librera los asientos de regreso ******/
                            if(true){
                            respuesta = tmsTxVtaFacadeBean.CancelaReservacion(DBLinkRegreso, "1001",
                                    numCorridaRegreso, strDestino, asientosRegreso, pasajeros, folioReservacionRegreso,
                                    numCorridaR, origenR, asientosR, pasajerosR, folioReservacionIda,
                                    motivo);

                            if(respuesta == null){
                                _EstadoTransaccion.setEstadoCodigo(-1, "cancelacionRequest", null);
                                success = false;
                            }
                            else{
                                _Resultado.setResultadoMultiple(respuesta);
                                if(!_valida.CancelaReservacionRegreso(cancelacionRequest))
                                {
                                    if(_Resultado.getProceso1()){
                                        //System.out.println("_Resultado.getInformacion() "+_Resultado.getInformacion1());
                                        if(_Resultado.getSuccess1()){
                                            /*
                                            AsientoAutobus[] x = procesoCancelaReservacion(_Resultado.getInformacion1(), cancelacionRequest, true);
                                            cancelacionResponse.setAsientosAutobusIda(x);
                                             *
                                             */
                                        }
                                        success = _Resultado.getSuccess1();
                                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "confirmaReservacionRequest");
                                    }
                                    else{
                                        _EstadoTransaccion.setEstadoCodigo(-2, "confirmaReservacionRequest", null);
                                        success = false;
                                    }
                                }
                                else{
                                    if(_Resultado.getProceso2()){
                                        //System.out.println("_Resultado.getInformacion() "+_Resultado.getInformacion1()+" _Resultado.getInformacion2()"+_Resultado.getInformacion2());
                                        if(_Resultado.getSuccess2()){
                                            /*
                                            AsientoAutobus[] x = procesoCancelaReservacion(_Resultado.getInformacion1(), cancelacionRequest, true);
                                            cancelacionResponse.setAsientosAutobusIda(x);

                                            x = procesoCancelaReservacion(_Resultado.getInformacion2(), cancelacionRequest, false);
                                            cancelacionResponse.setAsientosAutobusRegreso(x);
                                             * 
                                             */
                                        }
                                        success = _Resultado.getSuccess2();
                                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode2(), _Resultado.getErrorMsg2(), "reservacionRequest");
                                    }
                                    else{
                                        _EstadoTransaccion.setEstadoCodigo(-8, "reservacionRequest", null);
                                        success = false;
                                    }
                                }
                            }
                        }




                    }
                }
        }

        cancelacionResponse.setUid("1156155");
        cancelacionResponse.setSuccess(success);
        cancelacionResponse.setErrorCode(_EstadoTransaccion.getCodigo());
        cancelacionResponse.setErrorMsg(_EstadoTransaccion.getCodigoMsg());
        System.out.println("*************** Termina _cancelaReservacionRequest CDI ***************");
        return cancelacionResponse;
    }

    /**
     * Solicitud de Cancelar Boleto Vendido.
     * @param tmsTxVtaFacadeBean Clase EJB.
     * @param cancelacionBoletosRequest Clase cancelacionBoletosRequest.
     * @return Clase cancelacionBoletosResponse.
     */
    public cancelacionBoletosResponse _cancelaBoletosRequest(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, cancelacionBoletosRequest cancelacionBoletosRequest){
        System.out.println("*************** Inicia _cancelaBoletosRequest ***************");
        String foliosR = "";
        String tiposR= "";
        String asientosR= "";
        String asientos= "";
        String folios= "";
        String tipos= "";
        cancelacionBoletosResponse cancelacionBoletosResponse = new cancelacionBoletosResponse();
        if(objDBLinks==null) _DBLinks(tmsTxVtaFacadeBean);
        if(existenDBlinks){
            if((cancelacionBoletosRequest.getSesion()==null) || (_errorMsg =_valida.SolicitudCancelacionBoletos(cancelacionBoletosRequest))!=null){
                _EstadoTransaccion.setEstadoCodigo(-3, "cancelacionBoletosRequest", _errorMsg);
                success = false;
            }
            else{                
                System.out.println("cancelacionBoletosRequest "+cancelacionBoletosRequest);
                System.out.println("Sesion "+cancelacionBoletosRequest.getSesion().getIdSesion()+
                        "\nNumero Usuario"+ cancelacionBoletosRequest.getSesion().getNumUsuario()+
                        "\nId Estacion"+ cancelacionBoletosRequest.getSesion().getIdEstacionTrabajo()+
                        "\nNum Corrida"+ cancelacionBoletosRequest.getDatosIda().getNumCorrida()+
                        "\nOrigen"+ cancelacionBoletosRequest.getDatosIda().getOrigen().getClave()+
                        "\nOrigen Id"+ cancelacionBoletosRequest.getDatosIda().getOrigen().getId());
                DBLinkIda = getDBLink(cancelacionBoletosRequest.getDatosIda().getOrigen().getClave(), "cancelaBoletosRequest");
                System.out.println("DBLINK "+DBLinkIda);
                if(!DBLinkIda.equals("")){
                    String[] cads = cadsAsiTipFolNombre(cancelacionBoletosRequest.getDatosIda().getAsientosSeleccionados());
                    if(cads==null){
                        _EstadoTransaccion.setEstadoCodigo(-3, "cancelacionBoletosRequest","Asientos seleccionados");
                        success = false;
                    }
                    else{
                        asientos = cads[0];
                        tipos = cads[1];
                        folios = cads[2];
         
                        //System.out.println("asientos " + asientos + "tipos " + asientos +"folios " + folios);
                        if(_valida.cancelacionBoletosnRegreso(cancelacionBoletosRequest)){
                            /*numCorridaR = cancelacionBoletosRequest.getDatosRegreso().getNumCorrida();
                            origenR = cancelacionBoletosRequest.getDatosRegreso().getOrigen().getClave();*/
                            cads = cadsAsiTipFolNombre(cancelacionBoletosRequest.getDatosRegreso().getAsientosSeleccionados());
                            if(cads!=null){
                                asientosR = cads[0];
                                tiposR = cads[1];
                                foliosR = cads[2];
                            }
                        }
                        if(_valida.cancelacionBoletosnRegreso(cancelacionBoletosRequest) && cads==null){
                            _EstadoTransaccion.setEstadoCodigo(-3, "cancelacionBoletosRequest Regreso","Asientos Seleccionados");
                            success = false;
                        }
                        else{
                            //System.out.println("_valida "+_valida.cancelacionBoletosnRegreso(cancelacionBoletosRequest));
                            if(_valida.cancelacionBoletosnRegreso(cancelacionBoletosRequest) == true) 
                                respuesta = tmsTxVtaFacadeBean.cancelaBoletos(DBLinkIda, cancelacionBoletosRequest.getSesion().getNumUsuario(),
                                    cancelacionBoletosRequest.getSesion().getIdSesion(),cancelacionBoletosRequest.getSesion().getIdEstacionTrabajo(),
                                        cancelacionBoletosRequest.getSesion().getClaveCaja(),
                                    cancelacionBoletosRequest.getViajeRedondo(), cancelacionBoletosRequest.getDatosIda().getNumCorrida(),
                                    cancelacionBoletosRequest.getDatosIda().getOrigen().getClave(), cancelacionBoletosRequest.getDatosIda().getOrigen().getId(), 
                                    asientos, tipos, folios, cancelacionBoletosRequest.getDatosRegreso().getNumCorrida(),
                                    cancelacionBoletosRequest.getDatosRegreso().getOrigen().getClave(), cancelacionBoletosRequest.getDatosRegreso().getOrigen().getId(), 
                                    asientosR, tiposR, foliosR);
                            else {
                                //System.out.println("tmsTxVtaFacadeBean "+tmsTxVtaFacadeBean);
                                respuesta = tmsTxVtaFacadeBean.cancelaBoletos(DBLinkIda, cancelacionBoletosRequest.getSesion().getNumUsuario(),
                                    cancelacionBoletosRequest.getSesion().getIdSesion(),cancelacionBoletosRequest.getSesion().getIdEstacionTrabajo(),
                                        cancelacionBoletosRequest.getSesion().getClaveCaja(),
                                    cancelacionBoletosRequest.getViajeRedondo(), cancelacionBoletosRequest.getDatosIda().getNumCorrida(),
                                    cancelacionBoletosRequest.getDatosIda().getOrigen().getClave(), cancelacionBoletosRequest.getDatosIda().getOrigen().getId(), 
                                    asientos, tipos, folios, "", "", "", "", "", "");
                            }
                            if(respuesta == null){
                                _EstadoTransaccion.setEstadoCodigo(-1, "cancelacionBoletosRequest", null);
                                success = false;
                            }
                            else{
                                _Resultado.setResultadoMultiple(respuesta);
                                if(!_valida.cancelacionBoletosnRegreso(cancelacionBoletosRequest)){
                                    if(_Resultado.getProceso1()){
                                        //System.out.println("_Resultado.getInformacion() "+_Resultado.getInformacion1());
                                        if(_Resultado.getSuccess1()){
                                            AsientoAutobus[] x = procesoCancelaBoletos(_Resultado.getInformacion1(), cancelacionBoletosRequest, true);
                                            cancelacionBoletosResponse.setAsientosAutobusIda(x);
                                        }
                                        success = _Resultado.getSuccess1();
                                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "confirmaReservacionRequest");
                                    }
                                    else{
                                        _EstadoTransaccion.setEstadoCodigo(-2, "confirmaReservacionRequest", null);
                                        success = false;
                                    }
                                }
                                else{
                                    if(_Resultado.getProceso2()){
                                        //System.out.println("_Resultado.getInformacion() "+_Resultado.getInformacion1()+" _Resultado.getInformacion2()"+_Resultado.getInformacion2());
                                        if(_Resultado.getSuccess2()){
                                            AsientoAutobus[] x = procesoCancelaBoletos(_Resultado.getInformacion1(), cancelacionBoletosRequest, true);
                                            cancelacionBoletosResponse.setAsientosAutobusIda(x);
         
                                            x = procesoCancelaBoletos(_Resultado.getInformacion2(), cancelacionBoletosRequest, false);
                                            cancelacionBoletosResponse.setAsientosAutobusRegreso(x);
                                        }
                                        success = _Resultado.getSuccess2();
                                        _EstadoTransaccion.setEstado(_Resultado.getErrorCode2(), _Resultado.getErrorMsg2(), "reservacionRequest");
                                    }
                                    else{
                                        _EstadoTransaccion.setEstadoCodigo(-8, "reservacionRequest", null);
                                        success = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        cancelacionBoletosResponse.setUid(cancelacionBoletosRequest.getUid());
        cancelacionBoletosResponse.setSuccess(success);
        cancelacionBoletosResponse.setErrorCode(_EstadoTransaccion.getCodigo());
        cancelacionBoletosResponse.setErrorMsg(_EstadoTransaccion.getCodigoMsg());
        System.out.println("*************** Termina _cancelaBoletosRequest ***************");
        return cancelacionBoletosResponse;
    }
    
    /**
     * Solicitud de Termino de Sesion.
     * @param tmsTxVtaFacadeBean Clase EJB.
     * @param logoutRequest Clase logoutRequest.
     * @return Clase logoutResponse.
     */
    public logoutResponse _logoutRequest(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, logoutRequest logoutRequest) {
        System.out.println("*************** Inicia _logoutRequest ***************");
        // TODO implement operation
        logoutResponse logoutResponse = new logoutResponse();
        
        
        if(logoutRequest.getSesion()==null ||(_errorMsg=_valida.SolicitudLogout(logoutRequest))!=null){
            _EstadoTransaccion.setEstadoCodigo(-3, "logoutRequest", _errorMsg);
            success = false;
        }
        else{
            resp = tmsTxVtaFacadeBean.TerminarSesion(logoutRequest.getSesion().getIdSesion());
            if(resp == null){
                _EstadoTransaccion.setEstadoCodigo(-1, "logoutRequest", null);
                success = false;
            }
            else{
                _Resultado.setResultado1(resp);
                if(_Resultado.getProceso1()){
                    success = _Resultado.getSuccess1();
                    _EstadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "logoutRequest");
                }
                else{
                    _EstadoTransaccion.setEstadoCodigo(-2, "logoutRequest", null);
                    success = false;
                }
            }
        }
        
        logoutResponse.setUid(logoutRequest.getUid());
        logoutResponse.setSuccess(success);
        logoutResponse.setErrorCode(_EstadoTransaccion.getCodigo());
        logoutResponse.setErrorMsg(_EstadoTransaccion.getCodigoMsg());
        System.out.println("*************** Termina _logoutRequest ***************");
        return logoutResponse;
    }
        
    private String[] cadsAsiPasNombre(AsientoAutobus[] asientoAutobus) {
        String cadAsi="";
        String cadPas="";
        boolean siHayNombre=false;
//        for(int i=0; i<asientoAutobus.length; i++){
//            if(asientoAutobus[i].getNumeroAsiento()==1){
//                if(asientoAutobus[i].getNombreOcupante()==null||asientoAutobus[i].getNombreOcupante().equals("")) return null;
//                siHayNombre=true;
//                break;
//            }
//        }
//        if(!siHayNombre) return null;
        
        String cadNombres="";
        String tipo = "";
        for(int asiento=0; asiento<asientoAutobus.length; asiento++){
            //if(asientoAutobus[asiento].getNumeroAsiento()==1){
                //cadAsi=cadAsi+","+(asiento+1);
                cadAsi=cadAsi+","+asientoAutobus[asiento].getNumeroAsiento();
                /*cadPas=cadPas+","+(asientoAutobus[asiento].getTipoAsiento()==null || asientoAutobus[asiento].getTipoAsiento().equals("")
                       ?"A":asientoAutobus[asiento].getTipoAsiento());*/
                if(asientoAutobus[asiento].getTipoAsiento()==null || asientoAutobus[asiento].getTipoAsiento().equals("")){
                    tipo = "A";
                }else{
                    tipo = "A";
                    if(asientoAutobus[asiento].getTipoAsiento().equals("N"))
                        tipo = "M";
                    if(asientoAutobus[asiento].getTipoAsiento().equals("M"))
                        tipo = "P";
                    if(asientoAutobus[asiento].getTipoAsiento().equals("I"))
                        tipo = "S";
                    if(asientoAutobus[asiento].getTipoAsiento().equals("E"))
                        tipo = "E";
                }
                cadPas = cadPas+","+tipo;
                cadNombres=cadNombres+","+(asientoAutobus[asiento].getNombreOcupante()==null || asientoAutobus[asiento].getNombreOcupante().equals("")
                           ?"VoLIDO AL PORTADOR":asientoAutobus[asiento].getNombreOcupante());
            //}
        }
        
        if(cadAsi.equals("") || cadPas.equals("") || cadNombres.equals("")) return null;
        
        String cads[] = new String[3];
        cads[0] = cadAsi;
        cads[1] = cadPas;
        cads[2] = cadNombres;
        return cads;
    }

    private String cadsTarifas(ReservacionReq reservacionReq) {
        String cadTarifas="";
        
        cadTarifas=cadTarifas+","+reservacionReq.getTarifaAdulto();
        cadTarifas=cadTarifas+","+reservacionReq.getTarifaNinio();
        cadTarifas=cadTarifas+","+reservacionReq.getTarifaEstudiante();
        cadTarifas=cadTarifas+","+reservacionReq.getTarifaMaestro();
        cadTarifas=cadTarifas+","+reservacionReq.getTarifaINSEN();
        
        return cadTarifas;
    }

    private String ObtieneFolioBoleto(Object[][] objResultados, int pos) {
        for(int i=0; i<objResultados[0].length; i+=4) {
            try {
            if(Integer.parseInt(objResultados[0][i].toString()) == pos) return objResultados[0][i+1].toString();
            }catch(Exception e) {System.out.println(e);}
        }
        return "0";
    }
    
    private String ObtieneFolioReverso(Object[][] objResultados, int asiento) {
        for(int i=0; i<objResultados[0].length; i+=4)
            if(objResultados[0][i].toString().equals(String.valueOf(asiento))) return objResultados[0][i+2].toString();
        return "0";
    }
    
    private String ObtieneFolioBoletoEK(Object[][] objResultados, int asiento) {
        for(int i=0; i<objResultados[0].length; i+=4)
            if(objResultados[0][i].toString().equals(String.valueOf(asiento))) return objResultados[0][i+3].toString();
        return "0";
    }
    /** METODOS PARA TRATAR LOS DATOS DE IDA Y DE REGRESO **/
    private Itinerario[] ProcesoItinerarios(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, ItinerariosReq itinerariosReq, String sentidoInfo) {
        Itinerario[] itinerario=null;
        String numCorrida, strOrigen, strDestino, strEmpresa;
                
        numCorrida = itinerariosReq.getNumCorrida();
        strOrigen = itinerariosReq.getOrigen().getClave();
        DBLinkIda = getDBLink(strOrigen, "itinerariosResponse "+sentidoInfo);
        if(!DBLinkIda.equals("")){
            strDestino = itinerariosReq.getDestino().getClave();
            strEmpresa = itinerariosReq.getEmpresa().getNombre();

            //System.out.println(numCorrida+" "+strOrigen+" "+strDestino+" "+strEmpresa);
            resp = tmsTxVtaFacadeBean.getItinerarioCorrida(DBLinkIda, numCorrida, strOrigen, strDestino);
            //System.out.println("_Respuesta Itinerario: "+resp);

            if(resp == null){
                _EstadoTransaccion.setEstadoCodigo(-1, "itinerariosResponse", null);
                success = false;
            }
            else{
                _Resultado.setResultado1(resp);
                if(_Resultado.getProceso1()){
                    if(_Resultado.getSuccess1()){
                        Object[][] objCorridas = _DECODE(_Resultado.getInformacion1());
                        itinerario = obtenerItinerario(objCorridas);
                    }
                    success = _Resultado.getSuccess1();
                    //System.out.println("succes "+success);
                    _EstadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "itinerariosResponse "+sentidoInfo);
                }
                else{
                    _EstadoTransaccion.setEstadoCodigo(-2, "itinerariosResponse "+sentidoInfo, null);
                    success = false;
                }
            }
        }
        return itinerario;
    }

    private String ProcesoAsientos(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, AsientosReq asientosReq, 
            int asientosAdultos, int asientosNinio, int asientosEstudiante, int asientosMaestro, int asientosINSEN,
            String sentidoInfo) {
        String mapaAsientos = null;
        String numCorrida, strOrigen;
        int cupoAutobus;

        numCorrida = asientosReq.getNumCorrida();
        strOrigen = asientosReq.getOrigen().getClave();
        DBLinkIda = getDBLink(strOrigen, "asientosRequest "+sentidoInfo);
        if(!DBLinkIda.equals("")){
            cupoAutobus = asientosReq.getCupoAutobus();
            //System.out.println("NumCorrida: "+numCorrida);
            resp = tmsTxVtaFacadeBean.getAsientosCorrida(DBLinkIda, numCorrida, strOrigen,
                                         asientosAdultos, asientosNinio, asientosEstudiante,
                                         asientosMaestro, asientosINSEN, cupoAutobus);
            if(resp == null){
                _EstadoTransaccion.setEstadoCodigo(-1, "asientosResponse "+sentidoInfo, null);
                success = false;
            }
            else{
                _Resultado.setResultado1(resp);
                if(_Resultado.getProceso1()){
                    //System.out.println("_Resultado.getInformacion() "+_Resultado.getInformacion1());
                    if(_Resultado.getInformacion1()!=null)
                        mapaAsientos = _Resultado.getInformacion1();
                    success = _Resultado.getSuccess1();
                    //System.out.println("success mapa0 "+success);
                    _EstadoTransaccion.setEstado(_Resultado.getErrorCode1(), _Resultado.getErrorMsg1(), "asientosResponse "+sentidoInfo);
                }
                else{
                    _EstadoTransaccion.setEstadoCodigo(-2, "asientosResponse "+sentidoInfo, null);
                    success = false;
                }
            }
        }
        //System.out.println("success mapa "+success);
        return mapaAsientos;
    }

    private ReservacionResp procesoReservacion(String info, reservacionRequest reservacionRequest, boolean sentidoDatos) {
        ReservacionResp reservacionResp = new ReservacionResp();
        Object[][] objResultados = _DECODE(info);
        AsientoAutobus[] x = null;
        if(sentidoDatos) // true ida
            reservacionResp.setAsientosReservados(reservacionRequest.getDatosIda().getAsientosSeleccionados());
        else
            reservacionResp.setAsientosReservados(reservacionRequest.getDatosRegreso().getAsientosSeleccionados());
        
        for(int asiento=0; asiento<reservacionResp.getAsientosReservados().length; asiento++){
            if(reservacionResp.getAsientosReservados()[asiento].getNumeroAsiento()==1)
               reservacionResp.getAsientosReservados()[asiento].setNumeroAsiento(1);
        }
                
        reservacionResp.setImporteTotal(Double.valueOf(objResultados[0][1].toString()));
        reservacionResp.setFolioReservacionNegocio(objResultados[0][0].toString());
        
        return reservacionResp;
    }
    
    private AsientoAutobus[] procesoConfirmaReservacion(String info, confirmaReservacionRequest confirmaReservacionRequest, boolean sentidoDatos) {
        AsientoAutobus[] x = null;
        Object[][] objResultados = _DECODE(info);
        
        if(sentidoDatos) // true ida
            x = confirmaReservacionRequest.getDatosIda().getAsientosSeleccionados();
        else
            x = confirmaReservacionRequest.getDatosRegreso().getAsientosSeleccionados();
        
        
        String folioBoleto, folioReverso;
        System.out.println("confirmaReservacionRequest.getDatosIda().getAsientosSeleccionados() = "+confirmaReservacionRequest.getDatosIda().getAsientosSeleccionados().length);
        System.out.println("Obteniendo los asientos: x.length= "+x.length);
        for(int asiento=0; asiento<x.length; asiento++){
            //if(x[asiento].getNumeroAsiento()==1){
                //x[asiento].setNumeroAsiento(1);
                System.out.println("  x["+asiento+"]: "+x[asiento]);
                folioBoleto=ObtieneFolioBoleto(objResultados,x[asiento].getNumeroAsiento());
                x[asiento].setFolioBoletoNegocio(folioBoleto);
                folioReverso=ObtieneFolioReverso(objResultados,x[asiento].getNumeroAsiento());
                x[asiento].setFolioReversoNegocio(folioReverso);
                x[asiento].setFolioBoletoEK(ObtieneFolioBoletoEK(objResultados,x[asiento].getNumeroAsiento()));
                /*if (x[asiento].getTipoAsiento() == "O" ) 
                    x[asiento].setTipoAsiento("N");*/
            //}
        }
        
        return x;
    }
    
    private AsientoAutobus[] procesoCancelaReservacion(String info, cancelacionRequest cancelacionRequest, boolean sentidoDatos) {
        AsientoAutobus[] x = null;
        //Object[][] objResultados = _DECODE(info);
        
        if(sentidoDatos) // true ida
            x = cancelacionRequest.getDatosIda().getAsientosSeleccionados();
        else
            x = cancelacionRequest.getDatosRegreso().getAsientosSeleccionados();
        
        for(int asiento=0; asiento<x.length; asiento++){
            //if(x[asiento].getNumeroAsiento()==1){
            //    x[asiento].setNumeroAsiento(1);
                x[asiento].setFolioCancelacionNegocio(cancelacionRequest.getFolioReservacionNegocio());
            //}
        }
        
        return x;
    }
    
    private AsientoAutobus[] procesoCancelaBoletos(String info, cancelacionBoletosRequest cancelacionBoletosRequest, boolean sentidoDatos) {
        AsientoAutobus[] x = null;
        //Object[][] objResultados = _DECODE(info);
        
        if(sentidoDatos) // true ida
            x = cancelacionBoletosRequest.getDatosIda().getAsientosSeleccionados();
        else
            x = cancelacionBoletosRequest.getDatosRegreso().getAsientosSeleccionados();
        
        for(int asiento=0; asiento<x.length; asiento++){
            /*if(x[asiento].getNumeroAsiento()==1){
                x[asiento].setNumeroAsiento(1);*/
                x[asiento].setFolioCancelacionNegocio(x[asiento].getFolioBoletoNegocio());
            }
        
        return x;
    }

     private String[] cadsAsiTipFolNombre(AsientoAutobus[] asientoAutobus) {
        String cadAsi="";
        String cadTip = "";
        String cadFol = "";
        String tipo = "";
        
        for(int asiento=0; asiento<asientoAutobus.length; asiento++){
                cadAsi=cadAsi+","+asientoAutobus[asiento].getNumeroAsiento();
                tipo = "A";
                /*if(asientoAutobus[asiento].getTipoAsiento()==null || asientoAutobus[asiento].getTipoAsiento().equals("")){
                    tipo = "A";
                }else{
                    tipo = "A";*/
                    if(asientoAutobus[asiento].getTipoAsiento().equals("N"))
                        tipo = "M";
                    if(asientoAutobus[asiento].getTipoAsiento().equals("M"))
                        tipo = "P";
                    if(asientoAutobus[asiento].getTipoAsiento().equals("I"))
                        tipo = "S";
                //}
                cadTip = cadTip+","+tipo;
                if((asientoAutobus[asiento].getFolioBoletoNegocio() == null) || (asientoAutobus[asiento].getFolioBoletoNegocio().equals(""))) 
                    return null;
                cadFol = cadFol+","+asientoAutobus[asiento].getFolioBoletoNegocio();
            //}
        }
        
        if(cadAsi.equals("") || cadTip.equals("") || cadFol.equals("")) return null;
        
        String cads[] = new String[3];
        cads[0] = cadAsi;
        cads[1] = cadTip;
        cads[2] = cadFol;
        return cads;
    }

     /**
      * Obtiene los lugares disponibles en una corrda especifica
      * @param tmsTxVtaFacadeBean
      * @param claveCorrida Clave de la corrida a evaluar
      * @return Lugares disponibles en la corrida
      */
    public AsientosDisponibles getDisponibles(TmsTxVtaFacadeRemote tmsTxVtaFacadeBean, String claveCorrida) {
        AsientosDisponibles disponibles = new AsientosDisponibles();
        Object[] data = tmsTxVtaFacadeBean.getDisponibles(claveCorrida);
        disponibles.setOk((Boolean)data[0]);
        disponibles.setMenores(Integer.parseInt(data[1].toString()));
        disponibles.setSenectud(Integer.parseInt(data[2].toString()));
        disponibles.setEstudiante(Integer.parseInt(data[3].toString()));
        disponibles.setProfesor(Integer.parseInt(data[4].toString()));
        disponibles.setCortesia(Integer.parseInt(data[5].toString()));
        disponibles.setMensaje(data[6].toString());
        return disponibles;
    }
}