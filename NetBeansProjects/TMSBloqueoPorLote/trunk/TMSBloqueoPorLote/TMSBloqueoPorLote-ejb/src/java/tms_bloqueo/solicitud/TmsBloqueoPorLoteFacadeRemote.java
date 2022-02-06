
package tms_bloqueo.solicitud;

import javax.ejb.Remote;


/**
 * This is the business interface for TmsBloqueoPorLoteFacade enterprise bean.
 */
@Remote
public interface TmsBloqueoPorLoteFacadeRemote {
    java.util.Vector getServicios();

    java.util.Vector getRutas();

    java.util.Vector getEmpresas();

    java.util.Vector getCorridas(String servicio, String empresa, String ruta, String fechaInicial, String fechaFinal, String horaInicial, String horaFinal);

    java.util.List<tms_bloqueo.entidad.TmsComponenteBusTbl> queryTmsComponenteBusTblFindAll();

    java.util.List<tms_bloqueo.entidad.TmsAutobusPlantLineasTbl> queryTmsAutobusPlantLineasTblFindAll();

    java.util.List<tms_bloqueo.entidad.TmsAutobusPlantLineasTbl> queryTmsAutobusPlantLineasTblFindById(Long pId);

    java.util.List<tms_bloqueo.entidad.TmsAutobusPlantillasEncTbl> queryTmsAutobusPlantillasEncTblFindAll();    

    java.util.Vector getFechaHora();

    java.lang.String obtieneTerminal(String pDBLink);

    java.lang.String ocuparAsientos(String pDBLink, String corridaId, String asientos, String tiposPasajero, String modo, String usuarioId);

    



    
    
    

    
}
