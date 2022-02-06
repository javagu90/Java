package tms_incidencias.solicitud;

import java.sql.Timestamp;
import java.util.Vector;
import javax.ejb.Remote;
import tms_incidencias.entidad.TmsActAdicionalesTbl;
import tms_incidencias.entidad.TmsOperadorIncidenciasTbl;
import tms_incidencias.entidad.TmsPagosActAdicionalesTbl;

/**
 * This is the business interface for TmsVtaFacade enterprise bean.
 */
@Remote
public interface TmsIncidenciaFacadeRemote {
    /**
     */
    java.lang.String[] obtenerTerminal();

    java.util.List<tms_incidencias.entidad.TmsActAdicionalesTbl> queryTmsIncidenciasAll();
    
    Object[][] queryParamIncidencias();

    //java.util.List<tms_incidencias.entidad.TmsEstadoOperadoresV> queryTmsEstadoOperadoresVByNumero(String numero);

    long registraIncidenciaOperador(String prefijo, TmsOperadorIncidenciasTbl tmsIncidenciaOperadorTbl);

    java.util.List<tms_incidencias.entidad.TmsServiciosTbl> queryTmsServiciosAll();

    boolean cancelaIncidenciaOperador(long IncOperId, long usuarioId, Timestamp Fecha);
    
    boolean autorizaIncidenciaOperador(long IncOperId, long usuarioId, Timestamp Fecha);

    java.util.Vector OperadorIncidencias(String claveOperador, String Servicio, String claveIncidencia, String fecha1, String fecha2, String estado);

    boolean modificarIncidenciaOperador(long IncOperId, String valor, Timestamp fechaFinal1, Timestamp fechaFinal2, String Observaciones1, String Observaciones2, long usuarioId, Timestamp Fecha);

    java.lang.String[][] obtieneDatosAdicionales(String nombreIncidencia);

    void registraPagoAdicional(String prefijo, TmsPagosActAdicionalesTbl tmsPagosActAdicionalesTbl);

    java.util.Vector OperadorIncidencia(long operIncId);

    boolean cambiaEstadoOperador(long OperId, long EstadoId, long UbicacionId, long ActividadId, long usuarioId, Timestamp Fecha, String esquema);

    java.util.List<tms_incidencias.entidad.TmsAccionesTbl> queryTmsAccionesTblAll();

    long obtieneIdDatosAdicionales(String nombreIncidencia);
    
    String OperadorTVPR(String operador, String ligaCentral);
    
    String obtenerLigaCentral();
    
    boolean tieneIncidencias(String claveOperador, String fecha1, String fecha2);
    
    String obtenerPMT(String mes, String Empresa, String Servicio, String Categoria);

    java.util.List<tms_incidencias.entidad.TmsEmpresasTbl> queryTmsEmpresasAll();

    java.util.List<tms_incidencias.entidad.TmsEstadoOperadoresV> queryTmsOperadoresVAll();

    java.lang.Object getToDate();

    java.util.TimeZone getTimeZone();

    java.util.Vector getIncidenciabyOperador_Date(String OperadoClave, String fecha);

    java.util.Vector getCorridasByOperador_Date(String OperadoClave, String fecha);

    java.util.Vector getAllKindIncidencias();

    java.util.Vector valorServicioMedGuardia();
    
    boolean  getInc_Aplica_Recauda(String ClaveInc);
}