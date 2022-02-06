package tms_venta.solicitud;

import tms_venta.util.NoBloqueoFoliosException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.persistence.NoResultException;
import tms_venta.entidad.TmsVtaTiposPagoTbl;
import tms_venta.entidad.TmsAuditoriaTbl;

import tms_venta.entidad.TmsAutobusPlantLineasTbl;
import tms_venta.entidad.TmsAutobusPlantillasEncTbl;
import tms_venta.entidad.TmsBDConfigTbl;
import tms_venta.entidad.TmsComponenteBusTbl;
import tms_venta.entidad.TmsCorridasVentaTbl;
import tms_venta.entidad.TmsEstadosV;
import tms_venta.entidad.TmsReservacionesTbl;
import tms_venta.entidad.TmsTipopagosV;

import javax.ejb.Remote;
import tms_venta.entidad.TmsBoletosVentaTbl;
import tms_venta.entidad.TmsTiposPasajeroTbl;
import tms_venta.entidad.TmsVtaRvnV;

/**
 * This is the business interface for TmsVtaFacade enterprise bean.
 */
@Remote
public interface TmsVtaFacadeRemote {
    
    List<TmsBDConfigTbl> queryTmsBaseDatosConfigTblAll() throws javax.ejb.EJBException;
    
    List<TmsEstadosV> queryTmsEstadosVAll() throws javax.ejb.EJBException;
    
    boolean actualizaTipoAsiento(String pDBSchema, String pDBLink, long corridaId, long noAsiento, String tipoAsiento) throws javax.ejb.EJBException;
    
    List<TmsBoletosVentaTbl> BuscaBoletoVendido(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa) throws javax.ejb.EJBException;
    
    List<TmsBoletosVentaTbl> BuscaBoletoAbierto(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String Servicio, String empresa) throws javax.ejb.EJBException;

    String obtenerCorridasVenta(String pDBSchema, String pDBLink, String strRutaOrigen, String strOrigen, String strHoy, String strServicio, String strDestino, String strEmpresa) throws javax.ejb.EJBException;
    
    String obtenerUnaCorridaVenta(String pDBSchema, String pDBLink, String corridaId) throws javax.ejb.EJBException;
    
    boolean LiberaCtdTipoPasaje(String pDBSchema, String pDBLink, long corridaId, String tipoPasaje, String ctd, int asiento, String estado) throws javax.ejb.EJBException;
    
    int liberaAsientoCancelado(String pDBSchema, String pDBLink, String ClaveCorrida, int noAsiento, String tipoPasaje, String ctd) throws javax.ejb.EJBException;
    
    String ReservacionRem(String terminalPrefijoId, String pDBSchema, String pDBLink, String Campos, String Valores) throws javax.ejb.EJBException;
    
    List<TmsVtaRvnV> queryTmsVtaRvnVFindByCRvnRem(String dbschema, String dblink, String cRvn, String nResp) throws javax.ejb.EJBException;
    
    boolean actualizaEdoReservacionRem(String pDBSchema, String pDBLink, long IdRvn, String Edo, long idusuario) throws javax.ejb.EJBException;
    
    boolean existeRecoleccionEnSesion(String corteId) throws javax.ejb.EJBException;
    
    Object[] obtieneFolios(long corteId, long empresaId) throws javax.ejb.EJBException;
    
    Vector FuncionesDeUsuario(long MENU_ID) throws javax.ejb.EJBException;
    
    String[] FuncionAutorizadaPorSupervisor(String USUARIO_ID, String FUNCION_NUMERO) throws javax.ejb.EJBException;
    
    boolean FuncionAuditable(String Valores) throws javax.ejb.EJBException;
    
    List<TmsTipopagosV> queryTmsTipopagosVFindAll() throws javax.ejb.EJBException;
    
    String queryTmsTipopagosVFindById(long tipoPagoId) throws javax.ejb.EJBException;
    
    List<TmsTiposPasajeroTbl> queryTmsTiposPasajeroTblFindAll() throws javax.ejb.EJBException;
    
    /**
     * <code>select o from TmsUsuarioFuncionesV o where o.usuarionumero =:iduser and o.contrasenia=:idcontrasenia and o.pantallaid=:idpantalla</code>
     */
    TmsAutobusPlantillasEncTbl queryTmsAutobusPlantillasEncTblFindById(Long pId) throws javax.ejb.EJBException;

    List<TmsAutobusPlantillasEncTbl> queryTmsAutobusPlantillasEncTblFindAll() throws javax.ejb.EJBException;

    List<TmsComponenteBusTbl> queryTmsComponenteBusTblFindAll() throws javax.ejb.EJBException;

    List<TmsAutobusPlantLineasTbl> queryTmsAutobusPlantLineasTblFindAll() throws javax.ejb.EJBException;
    
    String FechaHoraCorridaParaBoleto(String pDBSchema, String pDBLink, String claveCorrida) throws javax.ejb.EJBException;
    
    long esUsuarioSupervisorConFuncion(String numeroFuncion, String usuarioNumero,String pwdEnc) throws javax.ejb.EJBException;
    
    boolean LiberaCtdTipoPasajeRvn(String pDBSchema, String pDBLink, long corridaId, String tipoPasaje, String ctd, int asiento, String estado) throws javax.ejb.EJBException;

    java.lang.String[] queryBoletoConTJAbierta(String pDBSchema, String pDBLink, String claveCorrida) throws javax.ejb.EJBException;

    boolean existeCorrida(String claveCorrida, String pDBLink) throws javax.ejb.EJBException;

    int estamosEnVacaciones() throws javax.ejb.EJBException;

    Object[][] queTiposPasajeVendo(String Servicio) throws javax.ejb.EJBException;

    java.util.List<tms_venta.entidad.TmsTiposPasajeroTbl> queryTmsTiposPasajeroTblFindAble() throws javax.ejb.EJBException;
    
    boolean existeReservacion(String reservacionId, String pDBLink) throws javax.ejb.EJBException;
    
    boolean bloqueaPermisoFolios(String valor) throws NoBloqueoFoliosException, javax.ejb.EJBException;

    /************************* CONSULTAS PARA OPTIMIZAR **********************/
    java.lang.String getParametrosIniciales(long usuarioId, String caja_nombre);

    java.lang.String getParametrosIniciales2(String caja_nombre);

    java.lang.String getTramosTarifas();

    /**
     * CONSULTAS REMOTAS ******************************
     */
    boolean queryTmsCorridasVentaBA(String pDBSchema, String pDBLink, String strRutaOrigen, String strOrigen, String strHoy, String strFecha, String strServicio, String strDestino, String strEmpresa) throws javax.ejb.EJBException;

    int queryExisteCorrida(String pDBSchema, String pDBLink, String strRutaOrigen, String strOrigen, String strHoy, String strFecha, String strServicio, String strDestino, String strEmpresa) throws javax.ejb.EJBException;

    java.lang.String getParametrosIniciales3(long caja_id);

    int getValidaSesionVenta(long corteId, long sesionId, String bVenta);

    boolean bloqueaRvn(String claveRvn, String valorClaveUsuario, String pDBSchema, String pDBLink) throws javax.ejb.EJBException;

    java.lang.String quienOcupaRvn(String claveRvn, String pDBSchema, String pDBLink) throws javax.ejb.EJBException;

    java.util.List<tms_venta.entidad.TmsBoletosVentaTbl> BuscaBoletoVendidoParaHO(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa) throws javax.ejb.EJBException;

    java.util.Vector queryTmsServicioOrigsDestsV_vista(String RutasExcluidas) throws javax.ejb.EJBException;

    java.util.List<tms_venta.entidad.TmsBoletosVentaTbl> BuscaBoletoVendidoParaCancelar(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa) throws javax.ejb.EJBException;

    int validarFolios(long P_TERMINAL_ID, long P_EMPRESA_ID, long P_FOLIO_INICIAL, long P_FOLIO_FINAL, long P_USUARIO_ID);

    String obtieneFondoInicial(long corteId, long empresaId) throws javax.ejb.EJBException;

    int _ModificaEstadoAsientoCorrida(String pDBLink, long corridaId, int asiento, String estado) throws javax.ejb.EJBException;

    int _ModificaCtdTipoPasajeRem(String pDBLink, long corridaId, String tipoPasaje, String ctd) throws javax.ejb.EJBException;

    long[] CancelaBoletoRemoto(String pDBLink, String Campos1, String Valores1, String Campos2, String Valores2, String claveCorrida, long noAsiento, long boletoIdOriginal, String convenio) throws javax.ejb.EJBException;

    long CancelaBoletoLocal(String Campos1, String Valores1, String claveCorrida, long noAsiento, long boletoIdOriginal, String convenio) throws javax.ejb.EJBException;

    int _OcuparAsientosSP(String pDBLink, long corridaId, String asientos, String tiposPasajero, String modo, long usuarioId);
 
    long getCorridaId(String pDBLink, String cCorrida);

    int _RegistraRecoleccion(String strParametros);

    java.lang.String _ObtieneFechaHoraBD();

    long[] _RegistraFolios(String strParametrosX, String strParametros, int n_empresas);

    int _FinalizarVenta(String strParametros);

    java.util.List<tms_venta.entidad.TmsBoletosVentaTbl> BuscaBoletoVendidoParaHOMult(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa) throws javax.ejb.EJBException;

    java.util.List<tms_venta.entidad.TmsBoletosVentaTbl> BuscaBoletoAbiertoMult(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String Servicio, String empresa) throws javax.ejb.EJBException;

    int _ModificaTipoPasajeVarios(String pDBLink, long corridaId, String cadenatipoPasaje) throws javax.ejb.EJBException;

    int _liberaAsientoCancelado(String pDBLink, String CorridaClave, String cadCampoAsientos, String cadCampoTipoPasaje, String noAsientos) throws javax.ejb.EJBException;

    java.lang.String _ReservarAsientosSP(String pDBLink, long corridaId, String asientos, String tiposPasajero, String clienteId, String Responsable, String cancelable, String transaccion, long usuarioId);

    java.util.List<tms_venta.entidad.TmsTiposPasajeroTbl> queryTmsTiposPasajeroTblFindAble2() throws javax.ejb.EJBException;

    java.lang.Object[] _solicitudRegistroVentaSP(String[] _strBoletos,String pqry);

    java.util.List<tms_venta.entidad.TmsBoletosVentaTbl> BuscaBoletoAbiertoRef(String pDBSchema, String pDBLink, String referencia) throws javax.ejb.EJBException;

    java.util.Vector buscaTerminalRef(int terminal);

    java.util.List<tms_venta.entidad.TmsBoletosVentaTbl> buscaBoletosReferencia(String pDBSchema, String pDBLink, String referencia) throws javax.ejb.EJBException;

    java.util.List<tms_venta.entidad.TmsBoletosVentaTbl> BuscaBoletoVendidoParaHORef(String pDBSchema, String pDBLink, String referencia) throws javax.ejb.EJBException;

    java.util.Vector getBoletosReferenciados(String origen, String fecha, String nombre, String referencia);

    java.util.List<tms_venta.entidad.TmsEstadosTbl> cargarEstadosTBL();

    boolean BuscaBoletoValidoparaFO(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa, String folio) throws javax.ejb.EJBException;

    boolean BuscaBoletoValidoparaFT(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa, String folioBoleto) throws javax.ejb.EJBException;

  //  boolean BuscaBoletoValidoparaFT(String pDBSchema, String pDBLink, String Origen, String FolioPreimpreso, String noAsiento, String nombreEmpresa) throws javax.ejb.EJBException;

    int liberarReferenciados(String pDBLink, Vector ids, long usuarioId, String esquema);

    java.util.List<tms_venta.entidad.TmsTipopagosV> queryTmsTipopagosCallVFindAll() throws javax.ejb.EJBException;

    java.lang.String getTramosTarifas1();

    java.lang.String getRutaId_corrida(String corrida, String pDBLink );

    boolean getPromocionVigente();

    

    java.lang.Object[] _solicitudRegistroVentaSP2(String _strBoletos, String pqry, int tam);

    java.lang.String _solicitudRegistroVentaSP3(String nuevo_strBoletos, String pqry, int tam, String seriesConvenio);

    java.lang.String queryTmsVtaRvnVFindByCRvnRem2(String pDBSchema, String pDBLink, String cRvn, String nResp) throws javax.ejb.EJBException;

    java.lang.String _ObtieneFechaHoraBD2();

    boolean getAplicaPromocion(double montoVa, double tarifaRedondo, double descuento, double tarifaSencillo);

    java.lang.String getCaja();

    void setCaja(String caja);

    boolean liberarSelectBoletosRef(String pDBSchema, String pDBLink, String liberar);

    java.lang.String _ObtieneFechaHoraBDLealtad();

    java.lang.String _ObtieneDiaVenta();

    java.lang.String buscaParametroLealtad(String terminal, String parametro);

    java.lang.String buscaEmpresasLealtad();

    java.util.Vector buscaRegistroLealtad(String operacion, String folio, String pDBSchema, String pDBLink);

    void insertaRegistroLealtad(String boleto_id, String folio_preimpreso, String preoducto, String ciudad_venta, String tipo_operacion, String num_tarjeta, String numero_operacion, String usuario, String contraseña, String importe, String tipo_pasajero, String caja, String unidad_negocio, String descuento, long user, String pDBSchema, String pDBLink, String terminId);

    java.lang.String buscaParametroCajaLealtad(String cajaId, String parametro);

    java.lang.String buscaTiposPagoLealtad();

    java.util.Vector buscaRegistroLealtadAcumulaHO(String operacion, String folio, String pDBSchema, String pDBLink);

    java.lang.String buscaEmpresasRedencion();

    java.lang.String _solicitudRegistroVentaSP4(String nuevo_strBoletos, String pqry, int tam, long corteId);

 
    java.lang.String obtener_valor_parametro_misc(String parametro, long EMPRESA_ID, long SERVICIO_ID, long TERMINAL_ID, long RUTA_ID, long CAJA_ID);

    java.util.Vector GetParametrosTAE(long EMPRESA_ID, long SERVICIO_ID, long TERMINAL_ID, long RUTA_ID, long CAJA_ID);

    java.util.Vector obtenerUnaCorridaVentaConvenio(String pDBSchema, String pDBLink, String corridaId, String transactionId, String origen, String servicioId, String fechaCorrida, String horaCorrida, String terminalOrigenId, String plantillaId) throws javax.ejb.EJBException;

    java.lang.String _OcuparAsientosSP_Convenio(String pDBLink, long corridaId, String asientos, String tiposPasajero, String modo, long usuarioId, String transaccionId, String tarifas, String servicio, String bolVenId, String fechaCorrida, String horaCorrida);

    java.lang.String validaBoletoConvenio(String[] _strBoletos, String tipo);

    java.lang.String cancelaBoletoConvenio(String[] _strBoletos);

    java.lang.String cambioHorarioConvenio(String[] _strFolios, String nuevo_strBoletos, String bolVenId, String transaccionId, String fechaCorrida, String horaCorrida, int tam);

    java.lang.String _solicitudRegistroVentaConvenio(String nuevo_strBoletos, String bolVenId, String transaccionId, String fechaCorrida, String horaCorrida, int tam, String promocion, String foliosPreimpresos);



    
    

    

   

}