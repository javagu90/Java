package tmspcweb;

import TMSProyectoWeb.solicitud.TmsTxVtaFacadeRemote;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import tmspcweb.clases.cancelacionBoletosRequest;
import tmspcweb.clases.cancelacionBoletosResponse;
import tmspcweb.clases.asientosRequest;
import tmspcweb.clases.asientosResponse;
import tmspcweb.clases.cancelacionRequest;
import tmspcweb.clases.cancelacionResponse;
import tmspcweb.clases.confirmaReservacionRequest;
import tmspcweb.clases.confirmaReservacionResponse;
import tmspcweb.clases.corridasRequest;
import tmspcweb.clases.corridasResponse;
import tmspcweb.clases.datos.AsientoAutobus;
import tmspcweb.clases.datos.AsientosDisponibles;
import tmspcweb.clases.datos.Sesion;
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
import tmspcweb.transacciones.TmsTransacciones;


/**
 * Clase que implementa un web service tomando el bean de la clase TmsTxVtaFacadeRemote.
 * @author ocruz
 */

@WebService
public class wsTmsProyectoWeb {
    private TmsTransacciones tmsTx;
    
    // Enter web service operations here. (Popup menu: Web Service->Add Operation)
    @EJB
    private TmsTxVtaFacadeRemote tmsTxVtaFacadeBean;

    /**
     * Constructor de la clase.
     * Inicializa valores importantes para el correcto funcionamiento de la clase.
     */
    public wsTmsProyectoWeb(){
        tmsTx = new TmsTransacciones();
    }
    /**
     * Solicitud de Inicio de Sesion.
     * @param loginRequest Clase loginRequest.
     * @return Clase loginResponse.
     */
    @WebMethod
    public loginResponse getLogin(@WebParam(name = "loginRequest") loginRequest loginRequest) {
        // TODO implement operation 
        return tmsTx._loginRequest(tmsTxVtaFacadeBean, loginRequest);
    }
    
        /**
     * Solicitud de Termino de Sesion.
     * @param logoutRequest Clase logoutRequest.
     * @return Clase logoutResponse.
     */
    @WebMethod
    public logoutResponse getLogout(@WebParam(name = "logoutRequest") logoutRequest logoutRequest) {
        // TODO implement operation 
        return tmsTx._logoutRequest(tmsTxVtaFacadeBean, logoutRequest);
    }
    
    
    /**
     * Solicitud de Corridas.
     * @param corridasRequest Clase corridasRequest.
     * @return Clase corridasResponse.
     */
    @WebMethod
    public corridasResponse getCorridas(@WebParam(name = "corridasRequest") corridasRequest corridasRequest) {
        // TODO implement operation 
        return tmsTx._corridasRequest(tmsTxVtaFacadeBean, corridasRequest);
    }

    /**
     * Solicitud de Corridas.
     * @param corridasRequest Clase corridasRequest.
     * @return Clase corridasResponse.
     */
    @WebMethod
    public corridasResponse getCorridasCDI(@WebParam(name = "corridasRequest") corridasRequest corridasRequest) {
        // TODO implement operation
        return tmsTx._corridasRequestCDI(tmsTxVtaFacadeBean, corridasRequest);
    }


    /**
     * Solicitud de Corridas.
     * @param corridasRequest Clase corridasRequest.
     * @return Clase corridasResponse.
     */
    @WebMethod
    //public reservarAsientosCDIResponse getReservacionAsientosCDI(@WebParam(name = "reservarAsientosCDIRequest") reservarAsientosCDIRequest preservarAsientosCDIRequest) {
    public corridasResponse getReservacionAsientosCDI(@WebParam(name = "corridasRequest") corridasRequest corridasRequest) {
        // TODO implement operation
        reservarAsientosCDIRequest preservarAsientosCDIRequest= new reservarAsientosCDIRequest();
        preservarAsientosCDIRequest.setFechaIda(corridasRequest.getFechaSalida());
        preservarAsientosCDIRequest.setFechaRegreso(corridasRequest.getFechaRegreso());
        preservarAsientosCDIRequest.setNombrePasajero(corridasRequest.getUid());
        preservarAsientosCDIRequest.setNumeroAsientos(""+corridasRequest.getAsientosAdulto());
        preservarAsientosCDIRequest.setOrigenIda(corridasRequest.getOrigen().getClave());
        preservarAsientosCDIRequest.setOrigenRegreso(corridasRequest.getDestino().getClave());
        return tmsTx._reservacionRequestCDI(tmsTxVtaFacadeBean, preservarAsientosCDIRequest);
    }





    /**
     * Solicitud de Corridas.
     * @param corridasRequest Clase corridasRequest.
     * @return Clase corridasResponse.
     */
    @WebMethod
    public String testDCI(@WebParam(name = "parametro") String param) {
        // TODO implement operation
        System.out.println("Se recibio el valor: "+param);
        return "realizado";//tmsTx._reservacionRequestCDI(tmsTxVtaFacadeBean, preservarAsientosCDIRequest);
    }
        /**
     * Solicitud de Itinerarios.
     * @param itinerariosRequest Clase itinerariosRequest.
     * @return Clase itinerariosResponse.
     */
    @WebMethod
    public itinerariosResponse getItinerarios(@WebParam(name = "itinerariosRequest") itinerariosRequest itinerariosRequest) {
        // TODO implement operation 
        return tmsTx._itinerariosRequest(tmsTxVtaFacadeBean, itinerariosRequest);
    }
    /**
     * Solicitud de Mapa de Asientos.
     * @param asientosRequest Clase asientosRequest.
     * @return Clase asientosResponse.
     */
    @WebMethod
    public asientosResponse getAsientos(@WebParam(name = "asientosRequest") asientosRequest asientosRequest) {
        // TODO implement operation
        return tmsTx._asientosRequest(tmsTxVtaFacadeBean, asientosRequest);
    }       
    /**
     * Solicitud de Reservacion.
     * @param reservacionRequest Clase reservacionRequest.
     * @return Clase reservacionResponse.
     */
    @WebMethod
    public reservacionResponse getReservacion(@WebParam(name = "reservacionRequest") reservacionRequest reservacionRequest) {
        // TODO implement operation 
        return tmsTx._reservacionRequest(tmsTxVtaFacadeBean, reservacionRequest);
    }
    
    /**
     * Solicitud de Confirma Reservacion.
     * @param confirmaReservacionRequest Clase confirmaReservacionRequest.
     * @return confirmaReservacionResponse.
     */
    @WebMethod
    public confirmaReservacionResponse getConfirmaReservacion(@WebParam(name = "asientosRequest") confirmaReservacionRequest confirmaReservacionRequest) {
        System.out.println(" ****************** se inicia la llamada desde wl WS de getConfirmaReservacion ************************* ");
        return tmsTx._confirmaReservacionRequest(tmsTxVtaFacadeBean, confirmaReservacionRequest);
    }

    /**
     * Solicitud de Confirma Reservacion.
     * @param confirmaReservacionRequest Clase confirmaReservacionRequest.
     * @return confirmaReservacionResponse.
     */
    @WebMethod
    public confirmaReservacionResponse getConfirmaReservacionCDI(@WebParam(name = "asientosRequest") confirmaReservacionRequest confirmaReservacionRequest) {
        System.out.println(" ****************** se inicia la llamada desde wl WS de getConfirmaReservacion CDI ************************* ");
        return tmsTx._confirmaReservacionRequestCDI(tmsTxVtaFacadeBean, confirmaReservacionRequest);
    }
    /**
     * Solicitud de Cancelar Reservacion.
     * @param cancelacionRequest Clase cancelacionRequest.
     * @return Clase cancelacionResponse.
     */
    @WebMethod
    public cancelacionResponse getCancelacionRequest(@WebParam(name = "reservacionRequest") cancelacionRequest cancelacionRequest) {
        // TODO implement operation 
        return tmsTx._cancelaReservacionRequest(tmsTxVtaFacadeBean, cancelacionRequest);
    }

    /**
     * Solicitud de Cancelar Reservacion.
     * @param cancelacionRequest Clase cancelacionRequest.
     * @return Clase cancelacionResponse.
     */
    @WebMethod
    public cancelacionResponse getCancelacionRequestCDI(@WebParam(name = "reservacionRequest") cancelacionRequest cancelacionRequest) {
        // TODO implement operation
        return tmsTx._cancelaReservacionRequestCDI(tmsTxVtaFacadeBean, cancelacionRequest);
    }
    /**
     * Solicitud de Cancelar Boletos.
     * @param cancelacionBoletosRequest Clase cancelacionBoletosRequest.
     * @return Clase cancelacionBoeltosResponse.
     */
    @WebMethod
    public cancelacionBoletosResponse getCancelacionBoletos(@WebParam(name = "CancelacionBoletosRequest") cancelacionBoletosRequest request) {
        return tmsTx._cancelaBoletosRequest(tmsTxVtaFacadeBean, request);
    }

    /**
     * Obtiene los lugares libres de una sola corrida especificada
     * Web service operation
     * @param Clave da la corrida e verificar
     * @return Lugares libres de la corrida solicitada
     */
    @WebMethod(operationName = "getDisponibles")
    public AsientosDisponibles getDisponibles(@WebParam(name = "claveCorrida") String claveCorrida,
            @WebParam(name = "sesion") Sesion sesion) {
        //TODO write your implementation code here:
        return tmsTx.getDisponibles(tmsTxVtaFacadeBean, claveCorrida);
    }
    
        
}
