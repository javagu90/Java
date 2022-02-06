package plantilla.solicitud;

import java.sql.Timestamp;

import java.util.List;

import javax.ejb.Remote;

import javax.naming.NamingException;
import plantilla.entidad.TmsComponenteBusTbl;

@Remote
public interface SsnPlantilla {
    Object mergeEntity(Object entity);

    Object persistEntity(Object entity);

    List<TmsComponenteBusTbl> queryTmsComponenteBusTblFindAll();
    
    long countEnc();
    
    long countLineas ();
    
    void addBPE(
            Long alineacionUltfila, Long alinearFilas, Long anchoAsiento, Float anchoAsientoF, Float anchoBus,
            Long anchoCanvas, Long asientosColder, Long asientosColizq, Long capacidadAsientos,
            Long creadoPor, String descripcion, Long distrAsientos, Timestamp fechaCreacion,
            Long largoAsiento, Float largoAsientoF, Float largoBus, Long largoCanvas, String nombreCorto, Long noFilasDer,
            Long noFilasIzq, Long noFilasRealDer, Long noFilasRealIzq, Long plantillaEncId,
            Long tipoNumeracion, Long ultimaActualizacionPor, Timestamp ultimaFechaActualizacion/*,
            List<TmsAutobusPlantLineasTbl> tmsAutobusPlantLineasTblList*/
        ) throws NamingException;

    void addBPL( Long lId, Long eId, Long bCId, Long creadoPor, Timestamp dFecha,
            Long nAsiento, Long posX, Long posZ) throws NamingException;
}
