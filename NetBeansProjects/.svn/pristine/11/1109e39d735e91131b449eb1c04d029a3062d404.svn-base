package xertms.solicitud;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Remote;
import xertms.entidad.TmsCorridasTbl;
import xertms.entidad.TmsCorridasVentaTbl;
import xertms.entidad.TmsEmpresasTbl;
import xertms.entidad.TmsEstadosCorridaTbl;
import xertms.entidad.TmsEstadosTbl;
import xertms.entidad.TmsFlotillasTbl;
import xertms.entidad.TmsMonitorCorridaV;
import xertms.entidad.TmsOpcionesSustAutobusV;
import xertms.entidad.TmsRutasTbl;
import xertms.entidad.TmsRutasV;
import xertms.entidad.TmsServiciosTbl;
import xertms.entidad.TmsTarjetasViajeTbl;
import xertms.entidad.TmsBDConfigTbl;

/**
 * This is the business interface for TmsCorteTaqFacade enterprise bean.
 */
@Remote
public interface TmsPSDFacadeRemote {
    
    Object[] obtenerDatosTerminalLocal();
    
    public List<TmsMonitorCorridaV> BusquedaMonitorCorridas(String Servicio, String Empresa, String Autobus, String Operador,
            String tipoCorrida, String FechaHoraHasta, String FechaHoraDesde,
            String DBLink, String Destino);
    
    List<TmsOpcionesSustAutobusV> buscarOpcionesSustAutobus(long flotillaId, long ubicacionId, String DBLink);
    
    List<TmsEmpresasTbl> queryTmsEmpresasTblAll();
    
    List<TmsEstadosCorridaTbl> queryTmsEstadosCorridaTblAll();
    
    List<TmsFlotillasTbl> queryTmsFlotillasTblAll();
    
    List<TmsEstadosTbl> queryTmsEstadosTblAll();
    
    List<TmsServiciosTbl> queryTmsServiciosTblAll();
    
    Object fechaServidor();
    
    // transacciones
    
    boolean modificaCorrida(long corridaId, long estadoCorridaId, String letraEstadoCorrida, Timestamp FechaHora, String Hora, String esquema, String DBLink);
    
    List<Object[]> buscarOpcionesSustOperador(String operadorOrigId, String ubicacion, String DBLink);
    
    int[] SustituyeAutobusUnaCorrida(long corridaId, long autobusSustId, String numeroEconomicoSust,
                                       long autobusOrigId, String numeroEconomicoOrig, String FechaHora, long plantillaId, long empresaId, String empresa, String esquema, String DBLink);
    
    int[] SustituyeAutobusTodoElDia(long corridaId, long autobusSustId, String numeroEconomicoSust,
                                              long autobusOrigId, String numeroEconomicoOrig, String FechaHora, long plantillaId, long empresaId, String empresa, String esquema, String DBLink);
    
    int[] SustituyeAutobusTodoElRol(long corridaId, long autobusSustId, String numeroEconomicoSust,
                                              long autobusOrigId, String numeroEconomicoOrig, String FechaHora, long plantillaId, long empresaId, String empresa, String esquema, String DBLink);
    
    boolean registraFueraRolAutobusOrig(long autobusOrigId, long fueraRolId, long ubicacionId, long causaId, String esquema, String DBLink);
    
    boolean registraEnRolAutobusSust(long autobusSustId, long rolId, long ubicacionId, long estanciaId, String esquema, String DBLink);
    
    int[] SustituyeOperadorUnaCorrida(long corridaId, long autobusSustId, String numeroEconomicoSust,
                                       long autobusOrigId, String numeroEconomicoOrig, String FechaHora, String esquema, String DBLink);
    
    int[] SustituyeOperadorTodoElDia(long corridaId, long autobusSustId, String numeroEconomicoSust,
                                              long autobusOrigId, String numeroEconomicoOrig, String FechaHora, String esquema, String DBLink);
    
    int[] SustituyeOperadorTodoElRol(long corridaId, long autobusSustId, String numeroEconomicoSust,
                                              long autobusOrigId, String numeroEconomicoOrig, String FechaHora, String esquema, String DBLink);
    
    boolean registraFueraRolOperadorOrig(long autobusOrigId, long fueraRolId, long ubicacionId, long causaId, String esquema, String DBLink);
    
    boolean registraEnRolOperadorSust(long autobusSustId, long rolId, long ubicacionId, long estanciaId, String esquema, String DBLink);
    
    String[] registrarCorridaExtra(String prefijoTerminalId, TmsCorridasTbl corrida, String nombreTerminal, String letraServicio, String strHora, String esquema, String Origen, String DBLink);
    
    boolean registrarCorridaExtraVenta(TmsCorridasVentaTbl corridaVenta, String esquema, String DBLink);
    
    //boolean actualizaClaveCorridaExtra(String prefijoTerminalId, long corridaId, String claveCorrida);
    
    List<TmsRutasTbl> queryTmsRutasTblAll();
    
    List<TmsRutasV> queryTmsRutasV(String esquemaPropio);
    
    int corridasAProcesarVenta(String ServicioId, String Fecha1, String Fecha2, String esquema, String DBLink);
    
    int corridasAProcesarNormal(String esquema, String DBLink);
    
    List<Object[]> corridasAProcesar(String DBLink);
    
    int liberarCorridasVenta(String esquema, String DBLink);
    
    int liberarCorridas(String esquema, String DBLink);
    
    // agrupacion
    
    int ocupacionCorrida(long corridaId, String DBLink);
    
    int TransfiereBoletosABporClave(String ClaveCorrida, String ClaveCorridaNueva);
    
    int TransfiereBoletosABporId(long CorridaId, long CorridaNuevaId);

    int[] SustituyeAutobusUnaCorridaNull(long corridaId, long autobusSustId, String numeroEconomicoSust, String FechaHora, long plantillaId, long empresaId, String empresa, String esquema, String DBLink);

    int[] SustituyeOperadorUnaCorridaNull(long corridaId, long autobusSustId, String numeroEconomicoSust, String FechaHora, String esquema, String DBLink);

    java.util.List<xertms.entidad.TmsAccionesTbl> queryTmsAccionesTblAll();

    java.lang.String[] estadoAutobusActualSiguiente(String autobus, String DBLink);

    java.lang.String[] estadoOperadorActualSiguiente(String operador, String DBLink);

    //long[] obtienePlantilla(long servicioId);
    
    long[] obtienePlantilla(String rutaNumero);

    long obtieneAutobusPlantilla(String numEco, String DBLink);

    int tarjViajeEstaRecaudada(long corridaId, String dblCentral);

    java.lang.String obtenerLigaCentral();

    int capacidadAsientos(long corridaId, String DBLink);

    boolean modificaEstadoCorrida(String loteCorridasId, long estadoCorridaId, String letraEstadoCorrida, long relacionada, String claveRelacionada, String esquema, String DBLink);

    java.lang.Object buscaCorrida(long idCor);

    java.lang.Object buscaEstadoTajeta(String edo);

    xertms.entidad.TmsTarjetasViajeTbl buscaTarjetaPorFolio(String folio, BigInteger edo);

    java.lang.Object queryBuscaNombreEsquema();
    
    void edit(TmsTarjetasViajeTbl tmsTarjetasViajeTbl);

    boolean AsignaOperadorACorrida(long corridaId, long autobusSustId, String numeroEconomicoSust, long usuarioId, String esquema, String DBLink);

    boolean AsignaAutobusACorrida(long corridaId, long autobusSustId, String numeroEconomicoSust, long plantillaId, long usuarioId, String esquema, String DBLink, long empresaId, String empresa);

    java.util.List<xertms.entidad.TmsAutobusesXTbl> queryTmsAutobusesXTblByNumEco(String numEco, String DBLink);
    
    java.util.List<xertms.entidad.TmsOperadoresTbl> queryTmsOperadoresTblByClave(String clave, String DBLink);

    java.util.List<xertms.entidad.TmsOperadoresTbl> queryOperadores();

    long esUsuarioSupervisorConFuncion(String funcionNumero, String usuarioNumero, String pwdEnc);

    long UsuarioConFuncion(String funcionNumero, String usuarioNumero);
    
    String estadoCorrida(String claveCorrida, String DBLink);
    
    int capacidadAsientosAutobus(String numeroEco, String DBLink);

    int MaxAsientoOcupado(String claveCorrida, String DBLink);
    
    boolean validaHoraModificada(Timestamp hora, long servicioId, long rutaId, long empresaId, String DBLink);
    
    List<TmsBDConfigTbl> queryTmsBaseDatosConfigTblAll();
    
    public Object fechaServidorX();

    java.util.List<xertms.entidad.TmsEstadoAutobusesV> queryTmsEstadoAutobusesVByNumeroEconomico(String NumeroEconomico);

    /**
     */
    java.lang.String claveOperador(String numEco);

    boolean actualizaEstadoOperador(String claveOperador, String edo, String ubi, String act, String esquema);

    long obtenerOperadorId(String claveOperador);

    /**
     * esquema de replicacion **************
     */
    boolean ejecutaReplicacion(String Tabla, String filaId, String esquemaPropio, String esquemaPropioOrigen, String vModo);

    boolean registrarAccesoUbicacion(long autobusId, long estadoId, long ubicacionId, long actividadId, long usuarioId, Timestamp fechaAct, String esquema);

    boolean registrarEstadoOperador(long operadorId, long estadoId, long ubicacionId, long actividadId, long usuarioId, Timestamp fechaAct, String esquema);

    java.util.List<xertms.entidad.TmsAutobusPlantillasEncTbl> queryTmsAutobusPlantillasEncTblFindAll();

    long obtienePlantillaCorrida(long corridaId, String DBLink);

    int[] actualizacionesVarias_Plantilla(String loteCorridasId, long plantillaId, String esquema, String DBLink);

    int[] actualizacionesVarias_Empresa(String loteCorridasId, long empresaId, String empresa, String esquema, String DBLink);
    
    int[] actualizacionesVarias_LimitesPasaje(String loteCorridasId, String limites, String esquema, String DBLink,
                                              String s, String e, String p, String c);

    java.lang.String getFlotillaDeAutobus(String numEco, String DBLink);

    String obtieneLimitesPasajeCorrida(Long aLong, String DBLink);
    
    boolean borraCorrida(String loteCorridasId, String DBLink);
    
    boolean borraCorridaCentral(String loteCorridasId, String dblCentral);

    java.lang.String getParametrosIniciales2();

    java.lang.String getEp(String ep);

    int esCorridaDePaso(String claveCorrida, String DBLink);

    java.lang.String estadoTarjetaViaje(long corridaId, String DBLink);

    boolean ServicioEmpresaId(long servicioId, long empresaId, String DBLink);

    boolean ServicioEmpresa(String servicio, String empresa, String DBLink);

    java.util.Vector ServicioEmpresa();

    java.util.Vector BusquedaMonitorCorridasSust(String Servicio, String Empresa, String Autobus, String Operador, String tipoCorrida, String FechaHoraDesde, String FechaHoraHasta, String DBLink, String Destino);

    java.util.Vector BusquedaMonitorCorridasSust2(String Servicio, String Empresa, String Autobus, String Operador, String tipoCorrida, String FechaHoraDesde, String FechaHoraHasta, String DBLink, String Destino);

    String[] registrarCorridaNormal(String prefijoTerminalId, TmsCorridasTbl corrida, String nombreTerminal, String letraServicio, String strHora, String esquema, String Origen, String DBLink);

    xertms.entidad.TmsCorridasTbl buscaCororida(long corridaId, String DBLink);

    xertms.entidad.TmsCorridasVentaTbl buscaCororidaVenta(long corridaId, String DBLink);

    int actualizacionesVarias_Ruta(String loteCorridasId, long rutaId, String esquema, String DBLink);

    public java.util.TimeZone getTimeZone();

    public java.lang.String getValidaIncicenciaOper(java.lang.String p_clave_operador, java.lang.String p_fecha_hora);

  
}