package xertms.solicitud;

import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Remote;
import xertms.entidad.TmsAutobusesTbl;
import xertms.entidad.TmsEstadoAutobusesV;
import xertms.entidad.TmsEstadosTbl;

/**
 * This is the business interface for TmsCorteTaqFacade enterprise bean.
 */
@Remote
public interface TmsAccUbicaFacadeRemote {
    
    Object[] obtenerDatosTerminalLocal();
    
    List<TmsEstadosTbl> queryTmsEstadosTblAll();

    List<TmsAutobusesTbl> queryTmsAutobusesTblAll();
    
    List<TmsEstadoAutobusesV> queryTmsEstadoAutobusesVByNumeroEconomico(String NumeroEconomico);
    
    boolean registrarAccesoUbicacion(long autobusId, long estadoId, long ubicacionId, long actividadId,
                                     long usuarioId, Timestamp fechaAct, String esquema);
    
    String dbLinkCentral();
    
    String RecaudaAuto(long servicioId);
    
    Object[] OperadorDeAutobus(long autobusId);
    
    String TarjetasViajePendientes(long operadorId);
    
    String obtieneRetencion(long empresaId);
    
    String claveOperador(String numEco);
    
    boolean actualizaEstadoOperador(String claveOperador, String edo, String ubi, String act, String esquema);

    /**
     * esquema de replicacion **************
     */
    boolean ejecutaReplicacion(String Tabla, String filaId, String esquemaPropio, String esquemaPropioOrigen, String vModo);

    long obtenerOperadorId(String claveOperador);
}