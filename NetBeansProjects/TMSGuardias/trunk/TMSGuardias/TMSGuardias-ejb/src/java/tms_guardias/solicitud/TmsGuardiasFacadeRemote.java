package tms_guardias.solicitud;

import java.sql.Timestamp;
import javax.ejb.Remote;
import tms_guardias.entidad.TmsActAdicionalesTbl;
import tms_guardias.entidad.TmsOperadorIncidenciasTbl;
import tms_guardias.entidad.TmsPagosActAdicionalesTbl;

/**
 * This is the business interface for TmsVtaFacade enterprise bean.
 */
@Remote
public interface TmsGuardiasFacadeRemote {
    /**
     */
    java.lang.String[] obtenerTerminal();

    java.util.List<tms_guardias.entidad.TmsActAdicionalesTbl> queryTmsIncidenciasAll();

    java.util.List<tms_guardias.entidad.TmsEstadoOperadoresV> queryTmsEstadoOperadoresVByNumero(String numero);

    long registraIncidenciaOperador(String prefijo, TmsOperadorIncidenciasTbl tmsIncidenciaOperadorTbl);

    java.util.List<tms_guardias.entidad.TmsServiciosTbl> queryTmsServiciosAll();

    boolean autorizaIncidenciaOperador(long IncOperId, long usuarioId, Timestamp Fecha);

    java.util.Vector OperadorIncidencias(String claveOperador, String Servicio, String claveIncidencia, String fecha1, String fecha2, String estado, String termial);

    boolean modificarIncidenciaOperador(long IncOperId, String valor, Timestamp fechaFinal, String observacion1, String observacion2, long usuarioId, Timestamp Fecha);

    java.lang.String[][] obtieneDatosAdicionales(String nombreIncidencia);

    void registraPagoAdicional(String prefijo, TmsPagosActAdicionalesTbl tmsPagosActAdicionalesTbl);

    java.util.Vector OperadorIncidencia(long operIncId);

    boolean cambiaEstadoOperador(long OperId, long EstadoId, long UbicacionId, long ActividadId, long usuarioId, Timestamp Fecha, String esquema);

    java.util.List<tms_guardias.entidad.TmsAccionesTbl> queryTmsAccionesTblAll();

    java.lang.String[][] valorServicioGuardia();

    java.lang.String duracionGuardia();
    
    long obtieneIdDatosAdicionales(String nombreIncidencia);

    boolean getInc_Aplica_Recauda(String ClaveInc);

    public String obtenerTerminales();

    public java.lang.String obtenerRegistrosOperador(java.lang.String clave, java.lang.String fechaInicial, java.lang.String fechaFinal);

    public java.lang.Object fechaServidor();


    public java.lang.String obtenerTarjetasOperador(java.lang.String clave, java.lang.String fechaInicial, java.lang.String fechaFinal);

    public java.lang.String obtenerNumTarOperador(java.lang.String clave, java.lang.String fechaInicial, java.lang.String fechaFinal);

    public java.util.Vector FuncionesDeUsuario(long MENU_ID, java.lang.String usuario) throws javax.ejb.EJBException;

    public long buscaGuardiasCruzadas(String operado, String fechaInicial, String fechaFinal);

    public long duracionGuardiaServicio(long pServcioId);

    public java.lang.String validaGuardia(java.lang.String clave, java.lang.String fechaInicial, java.lang.String fechaFinal, long pServicioId);

    public long getDatosRecaudador();

    public java.util.Vector getImpresora(java.lang.String caja);

    public java.util.TimeZone getTimeZone();


    
}