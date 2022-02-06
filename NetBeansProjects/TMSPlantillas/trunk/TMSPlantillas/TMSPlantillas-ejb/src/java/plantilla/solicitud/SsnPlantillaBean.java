package plantilla.solicitud;

import java.sql.Timestamp;

import java.util.List;
import java.util.Vector;

import javax.ejb.Stateless;

import javax.naming.NamingException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import plantilla.entidad.TmsAutobusPlantLineasTbl;
import plantilla.entidad.TmsAutobusPlantillasEncTbl;
import plantilla.entidad.TmsComponenteBusTbl;

@Stateless(name="SsnPlantilla")
public class SsnPlantillaBean implements SsnPlantilla {
    @PersistenceContext(unitName="TMS_Plantillas-ejbPU")
    private EntityManager em;

    public SsnPlantillaBean() {
    }

    public Object mergeEntity(Object entity) {
        return em.merge(entity);
    }

    public Object persistEntity(Object entity) {
        em.persist(entity);
        return entity;
    }

    /** <code>select o from TmsComponenteBusTbl o where o.tipo<>-1</code> */
    public List<TmsComponenteBusTbl> queryTmsComponenteBusTblFindAll() {
        return em.createNamedQuery("TmsComponenteBusTbl.findAll").getResultList();
    }
    
    public long countEnc (){
        Vector z = (Vector) em.createNativeQuery("SELECT NVL(MAX(PLANTILLA_ENC_ID),0) FROM TMS_AUTOBUS_PLANTILLAS_ENC_TBL").getResultList();
        Vector x = (Vector) z.get(0);
        return Long.valueOf(x.get(0).toString());
    }
    
    public long countLineas (){
        Vector z = (Vector) em.createNativeQuery("SELECT NVL(MAX(PLANTILLA_LINEA_ID),0) FROM TMS_AUTOBUS_PLANT_LINEAS_TBL").getResultList();
        Vector x = (Vector) z.get(0);
        return Long.valueOf(x.get(0).toString());
    }
    
    public void addBPE(
        Long alineacionUltfila, Long alinearFilas, Long anchoAsiento, Float anchoAsientoF, Float anchoBus,
        Long anchoCanvas, Long asientosColder, Long asientosColizq, Long capacidadAsientos,
        Long creadoPor, String descripcion, Long distrAsientos, Timestamp fechaCreacion,
        Long largoAsiento, Float largoAsientoF, Float largoBus, Long largoCanvas, String nombreCorto, Long noFilasDer,
        Long noFilasIzq, Long noFilasRealDer, Long noFilasRealIzq, Long plantillaEncId,
        Long tipoNumeracion, Long ultimaActualizacionPor, Timestamp ultimaFechaActualizacion/*,
        List<TmsAutobusPlantLineasTbl> tmsAutobusPlantLineasTblList*/
    ) throws NamingException{
    TmsAutobusPlantillasEncTbl bPE = new TmsAutobusPlantillasEncTbl();
        bPE.setAlineacionUltfila(alineacionUltfila);
        bPE.setAlinearFilas(alinearFilas);
        bPE.setAnchoAsiento(anchoAsiento);
        bPE.setAnchoAsientoF(anchoAsientoF);
        bPE.setAnchoBus(anchoBus);
        bPE.setAnchoCanvas(anchoCanvas);
        bPE.setAsientosColder(asientosColder);
        bPE.setAsientosColizq(asientosColizq);
        bPE.setCapacidadAsientos(capacidadAsientos);
        bPE.setCreadoPor(creadoPor);
        bPE.setDescripcion(descripcion);
        bPE.setDistrAsientos(distrAsientos);
        bPE.setFechaCreacion(fechaCreacion);
        bPE.setLargoAsiento(largoAsiento);
        bPE.setLargoAsientoF(largoAsientoF);
        bPE.setLargoBus(largoBus);
        bPE.setLargoCanvas(largoCanvas);
        bPE.setNombreCorto(nombreCorto);
        bPE.setNoFilasDer(noFilasDer);
        bPE.setNoFilasIzq(noFilasIzq);
        bPE.setNoFilasRealDer(noFilasRealDer);
        bPE.setNoFilasRealIzq(noFilasRealIzq);
        bPE.setPlantillaEncId(plantillaEncId);
        bPE.setTipoNumeracion(tipoNumeracion);
//        bPE.setTmsAutobusPlantLineasTblList(tmsAutobusPlantLineasTblList);
        bPE.setUltimaActualizacionPor(ultimaActualizacionPor);
        bPE.setUltimaFechaActualizacion(ultimaFechaActualizacion);
        bPE.setReplicacionEstado("P");
        bPE.setReplicacionIntentos((long)0);
        bPE.setReplicacionOrigen("XERTMS");
    this.persistEntity(bPE);
    }
    
    public void addBPL( Long lId, Long eId, Long bCId, Long creadoPor, Timestamp dFecha,
        Long nAsiento, Long posX, Long posZ) throws NamingException{
    TmsAutobusPlantLineasTbl bPL = new TmsAutobusPlantLineasTbl();
        bPL.setPlantillaLineaId(lId);
        bPL.setPlantillaEncId(eId);
        bPL.setComponenteBusId(bCId);
        bPL.setCreadoPor(creadoPor);
        bPL.setFechaCreacion(dFecha);
        bPL.setNumeroAsiento(nAsiento);
        bPL.setPosicionX(posX);
        bPL.setPosicionZ(posZ);
        bPL.setUltimaActualizacionPor(creadoPor);
        bPL.setUltimaFechaActualizacion(dFecha);        
        bPL.setReplicacionEstado("P");
        bPL.setReplicacionIntentos((long)0);
        bPL.setReplicacionOrigen("XERTMS");
    this.persistEntity(bPL);
    }
}
