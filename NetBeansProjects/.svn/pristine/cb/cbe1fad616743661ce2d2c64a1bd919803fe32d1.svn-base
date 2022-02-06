
package wsVendERMobile;

import vendERMobile.TiempoAire.clases.VentaTiempoAireRequest;
import vendERMobile.TiempoAire.clases.VentaTiempoAireResponser;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import vendERMobile.clases.abrirCerrarVentaAbordo.AbrirCerrarVentaAbordoRequest;
import vendERMobile.clases.abrirCerrarVentaAbordo.AbrirCerrarVentaAbordoResponse;
import vendERMobile.clases.asientos.AsientosRequest;
import vendERMobile.clases.asientos.AsientosResponse;
import vendERMobile.clases.cancelarBoletos.CancelarBoletosRequest;
import vendERMobile.clases.cancelarBoletos.CancelarBoletosResponse;
import vendERMobile.clases.canjeBoletoAbierto.CanjeBoletoAbiertoRequest;
import vendERMobile.clases.canjeBoletoAbierto.CanjeBoletoAbiertoResponse;
import vendERMobile.clases.corridas.CorridasRequest;
import vendERMobile.clases.corridas.CorridasResponse;
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
import vendERMobile.transacciones.VendERTransacciones;
import vendERSolicitudv3.VendERMobileFacadeRemote;


/**
 * This is the implementation bean class for the WsVendERMobile web service.
 * Created 7/09/2009 01:35:43 PM
 * 
 * 
 * 
 * @author asolis
 */

@WebService
public class WsVendERMobile {
    private VendERTransacciones transacciones;
    
    // Enter web service operations here. (Popup menu: Web Service->Add Operation)
    @EJB
    private VendERMobileFacadeRemote vendERMobileFacadeBean;    

    
    public WsVendERMobile(){
        transacciones = new VendERTransacciones();
        System.out.println("Version de WS 2.1.1");
    }
    
    /**
     * Web service operation
     */
    @WebMethod
    public LoginResp getLogIn(@WebParam(name = "loginRequest") LoginReq loginRequest) {                
        System.out.println("antes de iniciar loginRequest "+loginRequest.getSesion());
        return transacciones.loginRequest(vendERMobileFacadeBean, loginRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public PermisosResponse getPermisos(@WebParam(name = "permisosRequest") PermisosRequest permisosRequest) {        
        return transacciones.permisosRequest(vendERMobileFacadeBean,permisosRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public ParametrosInicialesResponse getParametrosIniciales(@WebParam(name = "parametrosInicialesRequest") ParametrosInicialesRequest parametrosInicialesRequest) {        
        return transacciones.paramaetrosInicialesRequest(vendERMobileFacadeBean, parametrosInicialesRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public TarifasResponse getTarifas(@WebParam(name = "tarifasRequest") TarifasRequest tarifasRequest) {        
        return transacciones.tarifasRequest(vendERMobileFacadeBean, tarifasRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public TiposPasajeroResponse getTiposPasajero(@WebParam(name = "tipoPasajeroRequest") TiposPasajeroRequest tipoPasajeroRequest) {        
        return transacciones.tipoPasajeroRequest(vendERMobileFacadeBean, tipoPasajeroRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public TipoPagoResponse getTiposPago(@WebParam(name = "tipoPagoRequest") TipoPagoRequest tipoPagoRequest) {        
        return transacciones.tipoPagoRequest(vendERMobileFacadeBean, tipoPagoRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public RecoleccionResponse getRecoleccion(@WebParam(name = "recoleccionRequest") RecoleccionRequest recoleccionRequest) {        
        return transacciones.recoleccionRequest(vendERMobileFacadeBean, recoleccionRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public CorridasResponse getCorridas(@WebParam(name = "corridasRequest") CorridasRequest corridasRequest) {        
        return transacciones.corridasRequest(vendERMobileFacadeBean, corridasRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public ItinerariosResponse getItinerarios(@WebParam(name = "itinerariosRequest") ItinerariosRequest itinerariosRequest) {        
        return transacciones.itinerariosRequest(vendERMobileFacadeBean, itinerariosRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public AsientosResponse getAsientos(@WebParam(name = "asientosRequest") AsientosRequest asientosRequest) {        
        return transacciones.mapaAsientosRequest(vendERMobileFacadeBean, asientosRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public OcuparAsientosResponse getOcuparAsientos(@WebParam(name = "ocuparAsientosRequest") OcuparAsientosRequest ocuparAsientosRequest) {
        return transacciones.ocuparAsientosRequest(vendERMobileFacadeBean, ocuparAsientosRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public VenderBoletosResponse getVenderBoletos(@WebParam(name = "venderBoletosRequest") VenderBoletosRequest venderBoletosRequest) {
        return transacciones.venderBoletosRequest(vendERMobileFacadeBean, venderBoletosRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public CancelarBoletosResponse getCancelaBoleto(@WebParam(name = "cancelarBoletosRequest") CancelarBoletosRequest cancelarBoletosRequest) {        
        return transacciones.cancelarBoletosRequest(vendERMobileFacadeBean, cancelarBoletosRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public TransferirBoletosResponse getTransferirBoletos(@WebParam(name = "transferirBoletosRequest") TransferirBoletosRequest transferirBoletosRequest) {
        return transacciones.transferirBoletosRequest(vendERMobileFacadeBean, transferirBoletosRequest);
    }
    
    /**
     * Web service operation
     */
    @WebMethod
    public LogOutResponse getLogOut(@WebParam(name = "logOutRequest") LogOutRequest logOutRequest) {
        return transacciones.logOutRequest(vendERMobileFacadeBean, logOutRequest);
    }   

    /**
     * Web service operation
     */
    @WebMethod
    public AbrirCerrarVentaAbordoResponse getAbrirCerrarVentaAbordo(@WebParam(name = "abrirCerrarVentaAbordoRequest") AbrirCerrarVentaAbordoRequest abrirCerrarVentaAbordoRequest) {        
        return transacciones.AbrirCerrarVentaAbordoRequest(vendERMobileFacadeBean, abrirCerrarVentaAbordoRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public ServiciosResponse getServicios(@WebParam(name = "serviciosRequest") ServiciosRequest serviciosRequest) {        
        return transacciones.serviciosRequest(vendERMobileFacadeBean, serviciosRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public EmpresasResponse getEmpresas(@WebParam(name = "empresasRequest") EmpresasRequest empresasRequest) {        
        return transacciones.empresasRequest(vendERMobileFacadeBean,empresasRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public FechaResponse getFecha(@WebParam(name = "fechaRequest") FechaRequest fechaRequest) {
        return transacciones.fechaRequest(vendERMobileFacadeBean,fechaRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public TipoNoPermitidosResponse getTiposNoPermitidos(@WebParam(name = "tipoNoPermitidosRequest") TipoNoPermitidosRequest tipoNoPermitidosRequest) {        
        return transacciones.tiposNoPermitidosRequest(vendERMobileFacadeBean, tipoNoPermitidosRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public CanjeBoletoAbiertoResponse getCanjeBoletoAbierto(@WebParam(name = "canjeBoletoAbiertoRequest") CanjeBoletoAbiertoRequest canjeBoletoAbiertoRequest) {        
        return transacciones.canjeBoletoAbiertoRequest(vendERMobileFacadeBean, canjeBoletoAbiertoRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public EstadosResponse getEstados(@WebParam(name = "estadosRequest") EstadosRequest estadosRequest) {
        return transacciones.estadosRequest(vendERMobileFacadeBean, estadosRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public RutasResponse getRutas(@WebParam(name = "rutasRequest") RutasRequest rutasRequest) {        
        return transacciones.rutasRequest(vendERMobileFacadeBean, rutasRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public ItinerariosPorRutaResponse getItinerariosPorRuta(@WebParam(name = "itinerariosPorRutaRequest") ItinerariosPorRutaRequest itinerariosPorRutaRequest) {        
        return transacciones.itinerariosPorRutaRequest(vendERMobileFacadeBean, itinerariosPorRutaRequest);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getDatosCorrida")
    public getdatosResponse getDatosCorrida(@WebParam(name = "origen")
    String origen, @WebParam(name = "claveCorrida")
    String claveCorrida) {
        //TODO write your implementation code here:
        return transacciones.getDatosCorridaRequest(vendERMobileFacadeBean, origen,claveCorrida);
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "despacharTarjetasViaje")
    public despacharTarjetaResponse despacharTarjetasViaje(@WebParam(name = "origen")
    String origen, @WebParam(name = "corrida")
    String corrida, @WebParam(name = "empresa")
    String empresa, @WebParam(name = "operador")
    String operador, @WebParam(name = "autobus")
    String autobus, @WebParam(name = "usuario")
    String usuario) {
        //TODO write your implementation code here:
        despacharTarjetaResponse res = transacciones.despacharTarjetasVRequest(vendERMobileFacadeBean, origen, corrida, empresa, operador, autobus, usuario);
            return res;

    }

    /**
     * Web service operation
     */
    @WebMethod
    public VentaTiempoAireResponser VentaTiempoAire(@WebParam(name = "ventaTiempoAireRequest") VentaTiempoAireRequest ventaTiempoAireRequest) {
        // TODO implement operation
        // TODO implement operation 
        return transacciones.ventaTiempoAireT(vendERMobileFacadeBean , ventaTiempoAireRequest);
   
    }
    
    
}
