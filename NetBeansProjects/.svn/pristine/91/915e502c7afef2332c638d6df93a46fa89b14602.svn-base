
package xertms.solicitud;

import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;
import xertms.entidad.TmsEmpresasTbl;
import xertms.entidad.TmsEstadosTbl;
import xertms.entidad.TmsOfertasServicioTbl;
import xertms.entidad.TmsOfertasServicioV;
import xertms.entidad.TmsRutasTbl;
import xertms.entidad.TmsRutasV;
import xertms.entidad.TmsServiciosTbl;
import xertms.entidad2.TmsParametrosConfigTbl;

/**
 * This is the business interface for TmsCorteTaqFacade enterprise bean.
 */
@Remote
public interface TmsOfertasServicioFacadeRemote {
    
    boolean validaHora(long servicioId, long rutaId, Timestamp hora);
    
    List<TmsOfertasServicioV> queryTmsOfertasServicioV(String nombreOferta, String empresaId, String rutaId);
    
    List<TmsEmpresasTbl> queryTmsEmpresasTblAll();
    
    List<TmsServiciosTbl> queryTmsServiciosTblAll();
    
    List<TmsRutasV> queryTmsRutasV();
    
    List<TmsRutasTbl> queryTmsRutasTblAll();
    
    List<TmsEstadosTbl> queryTmsEstadosTblAll();
    
    boolean registrarOferta(String PrefijoTerminalId, TmsOfertasServicioTbl oferta);
    
    boolean eliminarOferta(long ofertaId);
    
    boolean actualizarOferta(TmsOfertasServicioTbl oferta);
    
    // anexo funciones parametros
    List findAll();
    
    TmsParametrosConfigTbl busquedaPorCodigo(String codigo);
    
    Object fechaServidor();

    java.lang.String[] obtenerTerminal();

    long validaHoraModificada(String nombreOferta, long ofertaServicioId, Timestamp hora, long origenId, long destinoId, long empresaId);

    Vector PlantillaRutas();

    boolean existeNombreOfertaServicio(String nombreOferta);

    boolean afectaRolBaseLinea(long ofsId);

    java.util.Vector muestraOfertasGuardadas();

    java.util.Vector ServicioEmpresa();
}
