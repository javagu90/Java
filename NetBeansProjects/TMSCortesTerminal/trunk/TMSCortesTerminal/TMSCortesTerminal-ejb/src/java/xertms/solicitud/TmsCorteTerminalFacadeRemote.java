
package xertms.solicitud;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import xertms.entidad.TmsCortesTbl;


/**
 * This is the business interface for TmsCorteTaqFacade enterprise bean.
 */
@Remote
public interface TmsCorteTerminalFacadeRemote {
    List<TmsCortesTbl> queryTmsCortesTblByFecha(Timestamp fecha);

    List<Object[]> queryTmsCortesTblForPrint(String fecha, String pendientes, String prefijo);

    String[] obtenerTerminal();
    
    Object queryTmsCortesPendientesEnDiaLaboral(String dia);

    String obtenerEmpresaPrincipal(long cajaId);
    
    long obtenerCaja(String computadora);

    java.util.List<xertms.entidad.TmsBDConfigTbl> queryTmsBaseDatosConfigTblAll();

    java.util.Vector obtenerTerminalPrefijo();

    /**
     * IMM 28/01/2009 **
     */
    java.lang.String getFechaInicialCorte(String dia);

    java.lang.String getFechaFinalCorte(String dia);

    java.lang.String buscaCortesFinDia(Timestamp fi, Timestamp ff);
}
