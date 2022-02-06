package xertms.entidad;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQuery(name = "TmsAutobusPlantillasEncTbl.findAll", 
            query = "select o from TmsAutobusPlantillasEncTbl o order by o.plantillaEncId")
@Table(name = "TMS_AUTOBUS_PLANTILLAS_ENC_TBL")
public class

TmsAutobusPlantillasEncTbl implements Serializable {
    @Column(name = "ALINEACION_ULTFILA")
    private Long alineacionUltfila;
    @Column(name = "ALINEAR_FILAS")
    private Long alinearFilas;
    @Column(name = "ANCHO_ASIENTO")
    private Long anchoAsiento;
    @Column(name = "ANCHO_ASIENTO_F")
    private Float anchoAsientoF;
    @Column(name = "ANCHO_BUS")
    private Float anchoBus;
    @Column(name = "ANCHO_CANVAS")
    private Long anchoCanvas;
    @Column(name = "ASIENTOS_COLDER")
    private Long asientosColder;
    @Column(name = "ASIENTOS_COLIZQ")
    private Long asientosColizq;
    @Column(name = "CAPACIDAD_ASIENTOS")
    private Long capacidadAsientos;
    @Column(name = "CREADO_POR")
    private Long creadoPor;
    private String descripcion;
    @Column(name = "DISTR_ASIENTOS")
    private Long distrAsientos;
    @Column(name = "FECHA_CREACION")
    private Timestamp fechaCreacion;
    @Column(name = "LARGO_ASIENTO")
    private Long largoAsiento;
    @Column(name = "LARGO_ASIENTO_F")
    private Float largoAsientoF;
    @Column(name = "LARGO_BUS")
    private Float largoBus;
    @Column(name = "LARGO_CANVAS")
    private Long largoCanvas;
    @Column(name = "NOMBRE_CORTO")
    private String nombreCorto;
    @Column(name = "NO_FILAS_DER")
    private Long noFilasDer;
    @Column(name = "NO_FILAS_IZQ")
    private Long noFilasIzq;
    @Column(name = "NO_FILAS_REAL_DER")
    private Long noFilasRealDer;
    @Column(name = "NO_FILAS_REAL_IZQ")
    private Long noFilasRealIzq;
    @Id
    @Column(name = "PLANTILLA_ENC_ID", nullable = false)
    private Long plantillaEncId;
    @Column(name = "TIPO_NUMERACION")
    private Long tipoNumeracion;
    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private Long ultimaActualizacionPor;
    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    private Timestamp ultimaFechaActualizacion;

    public TmsAutobusPlantillasEncTbl() {
    }

    public Long getAlineacionUltfila() {
        return alineacionUltfila;
    }

    public void setAlineacionUltfila(Long alineacionUltfila) {
        this.alineacionUltfila = alineacionUltfila;
    }

    public Long getAlinearFilas() {
        return alinearFilas;
    }

    public void setAlinearFilas(Long alinearFilas) {
        this.alinearFilas = alinearFilas;
    }

    public Long getAnchoAsiento() {
        return anchoAsiento;
    }

    public void setAnchoAsiento(Long anchoAsiento) {
        this.anchoAsiento = anchoAsiento;
    }

    public Float getAnchoAsientoF() {
        return anchoAsientoF;
    }

    public void setAnchoAsientoF(Float anchoAsientoF) {
        this.anchoAsientoF = anchoAsientoF;
    }

    public Float getAnchoBus() {
        return anchoBus;
    }

    public void setAnchoBus(Float anchoBus) {
        this.anchoBus = anchoBus;
    }

    public Long getAnchoCanvas() {
        return anchoCanvas;
    }

    public void setAnchoCanvas(Long anchoCanvas) {
        this.anchoCanvas = anchoCanvas;
    }

    public Long getAsientosColder() {
        return asientosColder;
    }

    public void setAsientosColder(Long asientosColder) {
        this.asientosColder = asientosColder;
    }

    public Long getAsientosColizq() {
        return asientosColizq;
    }

    public void setAsientosColizq(Long asientosColizq) {
        this.asientosColizq = asientosColizq;
    }

    public Long getCapacidadAsientos() {
        return capacidadAsientos;
    }

    public void setCapacidadAsientos(Long capacidadAsientos) {
        this.capacidadAsientos = capacidadAsientos;
    }

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getDistrAsientos() {
        return distrAsientos;
    }

    public void setDistrAsientos(Long distrAsientos) {
        this.distrAsientos = distrAsientos;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getLargoAsiento() {
        return largoAsiento;
    }

    public void setLargoAsiento(Long largoAsiento) {
        this.largoAsiento = largoAsiento;
    }

    public Float getLargoAsientoF() {
        return largoAsientoF;
    }

    public void setLargoAsientoF(Float largoAsientoF) {
        this.largoAsientoF = largoAsientoF;
    }

    public Float getLargoBus() {
        return largoBus;
    }

    public void setLargoBus(Float largoBus) {
        this.largoBus = largoBus;
    }

    public Long getLargoCanvas() {
        return largoCanvas;
    }

    public void setLargoCanvas(Long largoCanvas) {
        this.largoCanvas = largoCanvas;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public Long getNoFilasDer() {
        return noFilasDer;
    }

    public void setNoFilasDer(Long noFilasDer) {
        this.noFilasDer = noFilasDer;
    }

    public Long getNoFilasIzq() {
        return noFilasIzq;
    }

    public void setNoFilasIzq(Long noFilasIzq) {
        this.noFilasIzq = noFilasIzq;
    }

    public Long getNoFilasRealDer() {
        return noFilasRealDer;
    }

    public void setNoFilasRealDer(Long noFilasRealDer) {
        this.noFilasRealDer = noFilasRealDer;
    }

    public Long getNoFilasRealIzq() {
        return noFilasRealIzq;
    }

    public void setNoFilasRealIzq(Long noFilasRealIzq) {
        this.noFilasRealIzq = noFilasRealIzq;
    }

    public Long getPlantillaEncId() {
        return plantillaEncId;
    }

    public void setPlantillaEncId(Long plantillaEncId) {
        this.plantillaEncId = plantillaEncId;
    }

    public Long getTipoNumeracion() {
        return tipoNumeracion;
    }

    public void setTipoNumeracion(Long tipoNumeracion) {
        this.tipoNumeracion = tipoNumeracion;
    }

    public Long getUltimaActualizacionPor() {
        return ultimaActualizacionPor;
    }

    public void setUltimaActualizacionPor(Long ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    public Timestamp getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(Timestamp ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }


}
