
package vendERSolicitudv3;

import javax.ejb.Remote;


/**
 * This is the business interface for vendERMobileFacade enterprise bean.
 */
@Remote
public interface VendERMobileFacadeRemote {

    java.util.Vector getPermisos(String cajaNombre, String usuario, String funcionNumero, String uid, String contrseña);

    java.util.Vector getParametrosIniciales(String cajaNombre, String campoTipo, String uid);

    java.util.Vector getTarifas(String cajaNombre, String uid, String rutaId);

    java.util.Vector getTipoPasajeros(String cajaNombre, String uid);

    java.util.Vector getTipoPago(String cajaNombre, String uid);

    java.util.Vector getServicios(String cajaNombre, String uid);

    java.util.Vector getEmpresas(String cajaNombre, String uid);

    java.util.Vector getEstados(String cajaNombre, String uid);

    java.util.Vector getRutas(String cajaNombre, String uid);

    java.util.Vector getRecoleccion(String cajaNombre, String uid, String pSupervisorNumero, String pSupervisorContrasenia, String formaPagoId, String formaPago, String pCtdMonto, String pMontoReco);

    java.util.Vector getCorridas(String cajaNombre, String uid, String pRutaOrigen, String pOrigen, String pDestino, String pFecha, String pServicio, String pEmpresa);

    java.util.Vector getItinerariosPorRuta(String cajaNombre,String uid, String rutaId);

    java.util.Vector getMapaAsientos(String cajaNombre, String uid, String claveCorrida, String plantillaId, String origen);

    java.util.Vector getOcuparAsientos(String cajaNombre, String uid, String claveCorrida, String mapaAsientos, String mapaAsientosPasajero, String Origen, String numeroUsuario, String modo);

    java.util.Vector logIn(String usuario, String contrasenia, String estacionTrabajo, String mac);

    java.util.Vector getItinerarios(String cajaNombre, String uid, String claveCorrida, String origen);

    java.util.Vector venderBoleto(String[] boletos, String rutaId, String uid, String boletoAbierto, String numeroUsuario, String contraseña, String caja);

    java.util.Vector cancelarBoleto(String uid, String numeroUsuario, String contraseña, String caja, String corteId, String asientos, String foliosPreimpresos, String unfolio, String motivoCancelacion);

    java.util.Vector transferirBoleto(String uid, String numeroUsuario, String contraseña, String caja, String corteId, String foliosPreimpresos, String asientos, String asientosDestino, String origen, String destino, String servicio, String empresa, String claveCorrida, String importe, String tipoPasajero);

    java.util.Vector logOut(String cajaNombre, String uid);

    java.util.Vector abrirCerrarVentaAbordo(String uid, String numeroUsuario, String folioTarjetaViaje, String numAutobus, String claveOperador, String claveModo);

    java.util.Vector getFecha(String uid);

    java.util.Vector getTipoPasajeroNoPermitido(String uid, String origenId, String corridaId);

    java.util.Vector canjeBoletoAbierto(String uid, String numeroUsuario, String contraseña, String caja, String corteId, String foliosPreimpresos, String asientosDestino, String origen, String destino, String servicio, String empresa, String claveCorrida, String tipoPasjaero, String importeBoleto);
 
    public java.util.Vector buscarDatosCorrita(java.lang.String origen, java.lang.String numero);

    public java.util.Vector despacharTarjetaViaje(java.lang.String origen, java.lang.String claveCorrida, java.lang.String empresa, java.lang.String operador, java.lang.String autobus, java.lang.String usuario);

    java.lang.String obtener_valor_parametro(String parametro, long EMPRESA_ID, long SERVICIO_ID, long TERMINAL_ID, long RUTA_ID, long CAJA_ID);

    java.lang.String ventaTaePrincipal(String Compañía, String Usuario, String Password, String Carrier, String Teléfono, String Cantidad, String TipoVenta, String No_Usuario, String Usuario_id, String Caja, String Corte_id, String Ciudad, String Canal);

//    java.util.Vector VentaTiempoAire(long EMPRESA_ID, long SERVICIO_ID, long TERMINAL_ID, long RUTA_ID, long CAJA_ID, String Compania, String Teléfono, String Cantidad, String TipoVenta, String No_Usuario, String Usuario_id, String Caja, String Corte_id, String Ciudad, String Canal);

    java.util.Vector VentaTiempoAire( String Compania, String Teléfono, String Cantidad, String TipoVenta, String No_Usuario,  String Caja, String Corte_id, String Canal);

    java.util.Vector registrarSesionActividad(int opcion, String valor, String valorOpcional, String cajaNombre, String uid);

}
