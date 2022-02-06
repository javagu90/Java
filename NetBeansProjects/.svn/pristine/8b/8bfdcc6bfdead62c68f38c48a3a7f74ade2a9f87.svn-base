
package tmslecturaviaxer.solicitud;

import java.math.BigDecimal;
import javax.ejb.Remote;


/**
 * This is the business interface for TmsVariosFacade enterprise bean.
 */
@Remote
public interface TmsVariosFacadeRemote {

    java.lang.Object BuscaCasetasTramo(String servicio, String origen, String destino);

    java.lang.Object BuscaKilometrosTramo(String servicio, String origen, String destino);

    java.lang.Object actualizarImportes(String claveCorrida);

    java.lang.Object fechaServidor();

    java.lang.Object buscaCortePorUsuario(String empresa, long usuarioId);

    java.lang.Object buscarFuncionesPorMenuId(long menuId);

    java.lang.Object queryBuscaTerminalId();

    java.lang.Object queryBuscaNumeroPaqueteActual();

    java.lang.Object queryBuscaIdEmpresaByNombre(Object nombre);



    java.lang.Object queryBuscaValorActualPago();

    java.lang.Object buscaRegistroPagoPorDia(long gastoId, String operaddor, String fecha);

    /**
     * Query para obtener la lista ciudades de la tabla de estados
     */
    java.util.List<java.lang.Object[]> queryBuscaCiudades();

    java.lang.Object buscaEstadoPorId(long idEdo);

    java.lang.Object queryBuscaTerminalLocal();

    java.lang.Object buscarEstadoSesion(long sesionId);

    java.lang.Object queryBuscaOperadorPorClave(String clave);

    java.lang.Object queryBuscaTodasTarjetaViaje(String viaje);

    java.lang.Object queryBuscaTarjetasViajePorOperador(String viaje, String operador);

    /**
     * Query que busca el nombre corto y el Destino de un servicio por su numero de servicio
     */
    java.util.List<java.lang.Object[]> queryBuscaDestinoServicioPorNumero(String numserv);

    /**
     * Query que busca el nombre corto y el Origen de un servicio por su numero de servicio
     */
    java.util.List<java.lang.Object[]> queryBuscaOrigenServicioPorNumero(String numserv);

    java.lang.Object queryBuscaImpresoraTikets(long cajaId);

    java.lang.Object queryBuscaIdTerminal();

    java.lang.Object buscaMontoRecoleccion();

    java.lang.Object buscaTopeRecoleccion();

    java.lang.Object buscaMontoAcumulado(long idCorte);

    java.lang.Object buscaMontoRecolecciones(long idCorte, long numUsuario);

    java.lang.Object buscaBoletosDATAFARERecolectados(long idCorte);

    tmslecturaviaxer.entidad.TmsEmpresasTbl queryBuscaEmpresaPorId(BigDecimal idemp);

    java.lang.Object queryBuscaNombreEsquema();

    java.lang.Object buscaVentaAbordoManual(long idCorte);

    java.lang.Object queryBuscaTarjetasViajePorServicio(String Servicio);

    java.util.Vector buscaTarjetaPorFolio(String folio);

    java.lang.Object queryBuscaTarjetasViajePorOperadorParaMod(String operador);

    java.lang.Object BuscaTarjetasPendientes(String claveOper);

    java.lang.Object buscaTurnosPendientes();

    void actualizarBoletos(String folio, String adicional, long user);

    java.lang.Object buscaSaldos(long idCorte);

    java.lang.Object queryBuscaTarjetaViaje(String viaje, String id, String opClv, String origen, String destino, String fecha);

    java.lang.Object buscaMontoAcumuladoParaRestar(long idCorte);

    public java.lang.Object buscaTurnosPendientesViaxer();

    public void actualizarBoletosViaxer(java.lang.String folio, java.lang.String adicional, long user);

    public java.lang.Object buscaTurnosPendientesOperadorViaxer(java.lang.String operador);

    public java.util.Vector queryBuscaDatosServicioPorId(java.lang.String numserv);

    public void copiaBoletosCobradosViaxer(java.lang.String operador, java.lang.String adicional2, java.lang.String usuario, java.lang.String corteId, java.lang.String folioTarjeta);

    //22-OCT-2015
    public String getHoraFinal (String adicional2);
    }
