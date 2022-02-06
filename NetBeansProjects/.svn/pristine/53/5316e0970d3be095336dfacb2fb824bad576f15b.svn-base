
package TMSProyectoWeb.solicitud;

import javax.ejb.Remote;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.persistence.NoResultException;

/**
 * Esta clase contiene todas las implementaciones de las funciones utilizadas en las
 * anotaciones de los entity beans. las cuales seran utilizadas al instanciar este campo.
 */
@Remote
public interface TmsTxVtaFacadeRemote {
    String getDBLinks();
    
    //String obtenerUnaCorridaVenta(String pDBSchema, String pDBLink, String corridaId) throws javax.ejb.EJBException;

    String obtenerCorridasVenta(String DBLink, String numSocio, String corteId, String strOrigen,
                                       String strFecha1, String strFecha2, String strFecha3, String strFecha4,
                                       String strServicio, String strDestino, String strEmpresa,
                                       int viajeRedondo, int AsientosAdulto, int AsientosNinio,
                                       int AsientosEstudiante, int AsientosMaestro, int AsientosINSEN) throws javax.ejb.EJBException;

    String IniciarSesion(String usuario, String contrasenia, String idEstacionTrabajo) throws javax.ejb.EJBException;
    
    String TerminarSesion(String idSesion) throws javax.ejb.EJBException;

    String getItinerarioCorrida(String DBLink, String numCorrida, String Origen, String strDestino) throws javax.ejb.EJBException;

    String getAsientosCorrida(String DBLink, String numCorrida, String Origen, int asientosAdultos, int asientosNinio,
                              int asientosEstudiante, int asientosMaestro, int asientosINSEN, int cupoAutobus) throws javax.ejb.EJBException;

    String[] CancelaReservacion(String DBLink, String numUsuario,
                                String numCorridaI, String OrigenI, String asientosI, String tiposPasajeroI, String claveReservacionI,
                                String numCorridaR, String OrigenR, String asientosR, String tiposPasajeroR, String claveReservacionR,
                                String motivoCn) throws javax.ejb.EJBException;

    java.lang.String buscaNombreNegocio(String id);

    java.lang.String[] cancelaBoletos(String DBLink, String numUsuario, String corteId, String idEstacion, String claveCaja, String viajeRedondo, String numCorridaI, String OrigenI, String OrigenI_id, String asientosI, String asientosI_tipo, String asientosI_folios, String numCorridaR, String OrigenR, String OrigenR_id, String asientosR, String asientosR_tipo, String asientosR_folios) throws javax.ejb.EJBException;

    java.lang.String[] ConfirmarReservacion(String DBLink, String numUsuario, String corteId, String idEstacion, String claveCaja,
                                            String idCliente, String numCorridaI, String OrigenI, String asientosI, String claveReservacionI, 
                                            String tipoPagoI, String BancoI, String numCorridaR, String OrigenR, String asientosR, String claveReservacionR,
                                            String tipoPagoR, String BancoR) throws javax.ejb.EJBException;

    java.lang.String motorBusqueda(String DBLink, String numSocio, String corteId, String strOrigen, String strFecha1, String strFecha2, String strFecha3, String strFecha4, String strServicio, String strDestino, String strEmpresa, int viajeRedondo) throws javax.ejb.EJBException;

    java.lang.String[] ReservarAsientos(String DBLink, String numUsuario, String idEstacion, String idCliente, String banCompra, String numCorridaI, String OrigenI, String DestinoI, String asientosI, String tiposPasajeroI, String nombrePasajeroI, String TarifasI, String numCorridaR, String OrigenR, String DestinoR, String asientosR, String tiposPasajeroR, String nombrePasajeroR, String TarifasR) throws javax.ejb.EJBException;

    java.lang.String[] ReservacionControl(String Origen, String destino, String idEstacion, String banCompra, String claveReservacion) throws javax.ejb.EJBException;

    /**
      * Obtiene los lugares disponibles en una corrda especifica
      * @param corrida Clave de la corrida a evaluar
      * @return Lugares disponibles en la corrida<br/>
     * menores_corrida<br/>senectud_corrida<br/>estudiantes_corrida<br/>profesores_corrida<br/>cortesias_corrida
      */
    public Object[] getDisponibles(String corrida);

    public java.lang.String obtenerCorridasVentaCDI(java.lang.String DBLink, java.lang.String numSocio, java.lang.String corteId, java.lang.String strOrigen, java.lang.String strFecha1, java.lang.String strFecha2, java.lang.String strFecha3, java.lang.String strFecha4, java.lang.String strServicio, java.lang.String strDestino, java.lang.String strEmpresa, int viajeRedondo, int AsientosAdulto, int AsientosNinio, int AsientosEstudiante, int AsientosMaestro, int AsientosINSEN) throws javax.ejb.EJBException;

    public java.lang.String[] reservacionAsientosCDI(java.lang.String ORIGENI, java.lang.String ORIGENR, int PASIENTOS, java.lang.String PNOMBREPASAJERO, java.lang.String FECHAI, java.lang.String FECHAR) throws javax.ejb.EJBException;

    public java.lang.String getNumCorridaCDI(java.lang.String claveReservacion, java.lang.String DBLink );

    public java.lang.String[] ConfirmarReservacionCDI(java.lang.String DBLink, java.lang.String numUsuario, java.lang.String corteId, java.lang.String idEstacion, java.lang.String claveCaja, java.lang.String idCliente, java.lang.String numCorridaI, java.lang.String OrigenI, java.lang.String asientosI, java.lang.String claveReservacionI, java.lang.String tipoPagoI, java.lang.String BancoI, java.lang.String numCorridaR, java.lang.String OrigenR, java.lang.String asientosR, java.lang.String claveReservacionR, java.lang.String tipoPagoR, java.lang.String BancoR, java.lang.String liga) throws javax.ejb.EJBException;
}