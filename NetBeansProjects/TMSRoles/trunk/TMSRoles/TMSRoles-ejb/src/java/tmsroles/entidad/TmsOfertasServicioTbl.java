/*
 * TmsOfertasServicioTbl.java
 *
 * Created on 20 de noviembre de 2007, 04:15 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsOfertasServicioTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_OFERTAS_SERVICIO_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsOfertasServicioTbl.findByOfertaServicioId", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.ofertaServicioId = :ofertaServicioId"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByOfertaServicioNombre", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.ofertaServicioNombre = :ofertaServicioNombre"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByTramoOrigenId", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.tramoOrigenId = :tramoOrigenId"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByTramoDestinoId", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.tramoDestinoId = :tramoDestinoId"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByDescripcion", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByHoraCorrida", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.horaCorrida = :horaCorrida"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByLunesAplica", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.lunesAplica = :lunesAplica"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByMartesAplica", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.martesAplica = :martesAplica"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByMiercolesAplica", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.miercolesAplica = :miercolesAplica"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByJuevesAplica", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.juevesAplica = :juevesAplica"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByViernesAplica", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.viernesAplica = :viernesAplica"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findBySabadoAplica", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.sabadoAplica = :sabadoAplica"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByDomingoAplica", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.domingoAplica = :domingoAplica"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByMenoresCorrida", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.menoresCorrida = :menoresCorrida"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findBySenectudCorrida", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.senectudCorrida = :senectudCorrida"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByEstudiantesCorrida", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.estudiantesCorrida = :estudiantesCorrida"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByProfesoresCorrida", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.profesoresCorrida = :profesoresCorrida"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByCortesiasCorrida", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.cortesiasCorrida = :cortesiasCorrida"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByAdicional1", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByAdicional2", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByAdicional3", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByAdicional4", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByAdicional5", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByAdicional6", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.adicional6 = :adicional6"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByAdicional7", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.adicional7 = :adicional7"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByAdicional8", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.adicional8 = :adicional8"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByAdicional9", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.adicional9 = :adicional9"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByAdicional10", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.adicional10 = :adicional10"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByCreadoPor", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByFechaCreacion", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByReplicacionEstado", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsOfertasServicioTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsOfertasServicioTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsOfertasServicioTbl implements Serializable {

    @Id
    @Column(name = "OFERTA_SERVICIO_ID", nullable = false)
    private BigDecimal ofertaServicioId;

    @Column(name = "OFERTA_SERVICIO_NOMBRE", nullable = false)
    private String ofertaServicioNombre;

    @Column(name = "TRAMO_ORIGEN_ID", nullable = false)
    private BigInteger tramoOrigenId;

    @Column(name = "TRAMO_DESTINO_ID", nullable = false)
    private BigInteger tramoDestinoId;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "HORA_CORRIDA", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date horaCorrida;

    @Column(name = "LUNES_APLICA", nullable = false)
    private String lunesAplica;

    @Column(name = "MARTES_APLICA", nullable = false)
    private String martesAplica;

    @Column(name = "MIERCOLES_APLICA", nullable = false)
    private String miercolesAplica;

    @Column(name = "JUEVES_APLICA", nullable = false)
    private String juevesAplica;

    @Column(name = "VIERNES_APLICA", nullable = false)
    private String viernesAplica;

    @Column(name = "SABADO_APLICA", nullable = false)
    private String sabadoAplica;

    @Column(name = "DOMINGO_APLICA", nullable = false)
    private String domingoAplica;

    @Column(name = "MENORES_CORRIDA")
    private BigInteger menoresCorrida;

    @Column(name = "SENECTUD_CORRIDA")
    private BigInteger senectudCorrida;

    @Column(name = "ESTUDIANTES_CORRIDA")
    private BigInteger estudiantesCorrida;

    @Column(name = "PROFESORES_CORRIDA")
    private BigInteger profesoresCorrida;

    @Column(name = "CORTESIAS_CORRIDA")
    private BigInteger cortesiasCorrida;

    @Column(name = "ADICIONAL1")
    private String adicional1;

    @Column(name = "ADICIONAL2")
    private String adicional2;

    @Column(name = "ADICIONAL3")
    private String adicional3;

    @Column(name = "ADICIONAL4")
    private String adicional4;

    @Column(name = "ADICIONAL5")
    private String adicional5;

    @Column(name = "ADICIONAL6")
    private String adicional6;

    @Column(name = "ADICIONAL7")
    private String adicional7;

    @Column(name = "ADICIONAL8")
    private String adicional8;

    @Column(name = "ADICIONAL9")
    private String adicional9;

    @Column(name = "ADICIONAL10")
    private String adicional10;

    @Column(name = "CREADO_POR", nullable = false)
    private BigInteger creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;

    @Column(name = "REPLICACION_ESTADO")
    private String replicacionEstado;

    @Column(name = "REPLICACION_INTENTOS")
    private BigInteger replicacionIntentos;

    @Column(name = "REPLICACION_ORIGEN")
    private String replicacionOrigen;

    @JoinColumn(name = "EMPRESA_ID", referencedColumnName = "EMPRESA_ID")
    @ManyToOne
    private TmsEmpresasTbl empresaId;

    @JoinColumn(name = "RUTA_ID", referencedColumnName = "RUTA_ID")
    @ManyToOne
    private TmsRutasTbl rutaId;

    @JoinColumn(name = "SERVICIO_ID", referencedColumnName = "SERVICIO_ID")
    @ManyToOne
    private TmsServiciosTbl servicioId;
    
    /** Creates a new instance of TmsOfertasServicioTbl */
    public TmsOfertasServicioTbl() {
    }

    /**
     * Creates a new instance of TmsOfertasServicioTbl with the specified values.
     * @param ofertaServicioId the ofertaServicioId of the TmsOfertasServicioTbl
     */
    public TmsOfertasServicioTbl(BigDecimal ofertaServicioId) {
        this.ofertaServicioId = ofertaServicioId;
    }

    /**
     * Creates a new instance of TmsOfertasServicioTbl with the specified values.
     * @param ofertaServicioId the ofertaServicioId of the TmsOfertasServicioTbl
     * @param ofertaServicioNombre the ofertaServicioNombre of the TmsOfertasServicioTbl
     * @param tramoOrigenId the tramoOrigenId of the TmsOfertasServicioTbl
     * @param tramoDestinoId the tramoDestinoId of the TmsOfertasServicioTbl
     * @param horaCorrida the horaCorrida of the TmsOfertasServicioTbl
     * @param lunesAplica the lunesAplica of the TmsOfertasServicioTbl
     * @param martesAplica the martesAplica of the TmsOfertasServicioTbl
     * @param miercolesAplica the miercolesAplica of the TmsOfertasServicioTbl
     * @param juevesAplica the juevesAplica of the TmsOfertasServicioTbl
     * @param viernesAplica the viernesAplica of the TmsOfertasServicioTbl
     * @param sabadoAplica the sabadoAplica of the TmsOfertasServicioTbl
     * @param domingoAplica the domingoAplica of the TmsOfertasServicioTbl
     * @param creadoPor the creadoPor of the TmsOfertasServicioTbl
     * @param fechaCreacion the fechaCreacion of the TmsOfertasServicioTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsOfertasServicioTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsOfertasServicioTbl
     */
    public TmsOfertasServicioTbl(BigDecimal ofertaServicioId, String ofertaServicioNombre, BigInteger tramoOrigenId, BigInteger tramoDestinoId, Date horaCorrida, String lunesAplica, String martesAplica, String miercolesAplica, String juevesAplica, String viernesAplica, String sabadoAplica, String domingoAplica, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.ofertaServicioId = ofertaServicioId;
        this.ofertaServicioNombre = ofertaServicioNombre;
        this.tramoOrigenId = tramoOrigenId;
        this.tramoDestinoId = tramoDestinoId;
        this.horaCorrida = horaCorrida;
        this.lunesAplica = lunesAplica;
        this.martesAplica = martesAplica;
        this.miercolesAplica = miercolesAplica;
        this.juevesAplica = juevesAplica;
        this.viernesAplica = viernesAplica;
        this.sabadoAplica = sabadoAplica;
        this.domingoAplica = domingoAplica;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the ofertaServicioId of this TmsOfertasServicioTbl.
     * @return the ofertaServicioId
     */
    public BigDecimal getOfertaServicioId() {
        return this.ofertaServicioId;
    }

    /**
     * Sets the ofertaServicioId of this TmsOfertasServicioTbl to the specified value.
     * @param ofertaServicioId the new ofertaServicioId
     */
    public void setOfertaServicioId(BigDecimal ofertaServicioId) {
        this.ofertaServicioId = ofertaServicioId;
    }

    /**
     * Gets the ofertaServicioNombre of this TmsOfertasServicioTbl.
     * @return the ofertaServicioNombre
     */
    public String getOfertaServicioNombre() {
        return this.ofertaServicioNombre;
    }

    /**
     * Sets the ofertaServicioNombre of this TmsOfertasServicioTbl to the specified value.
     * @param ofertaServicioNombre the new ofertaServicioNombre
     */
    public void setOfertaServicioNombre(String ofertaServicioNombre) {
        this.ofertaServicioNombre = ofertaServicioNombre;
    }

    /**
     * Gets the tramoOrigenId of this TmsOfertasServicioTbl.
     * @return the tramoOrigenId
     */
    public BigInteger getTramoOrigenId() {
        return this.tramoOrigenId;
    }

    /**
     * Sets the tramoOrigenId of this TmsOfertasServicioTbl to the specified value.
     * @param tramoOrigenId the new tramoOrigenId
     */
    public void setTramoOrigenId(BigInteger tramoOrigenId) {
        this.tramoOrigenId = tramoOrigenId;
    }

    /**
     * Gets the tramoDestinoId of this TmsOfertasServicioTbl.
     * @return the tramoDestinoId
     */
    public BigInteger getTramoDestinoId() {
        return this.tramoDestinoId;
    }

    /**
     * Sets the tramoDestinoId of this TmsOfertasServicioTbl to the specified value.
     * @param tramoDestinoId the new tramoDestinoId
     */
    public void setTramoDestinoId(BigInteger tramoDestinoId) {
        this.tramoDestinoId = tramoDestinoId;
    }

    /**
     * Gets the descripcion of this TmsOfertasServicioTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsOfertasServicioTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the horaCorrida of this TmsOfertasServicioTbl.
     * @return the horaCorrida
     */
    public Date getHoraCorrida() {
        return this.horaCorrida;
    }

    /**
     * Sets the horaCorrida of this TmsOfertasServicioTbl to the specified value.
     * @param horaCorrida the new horaCorrida
     */
    public void setHoraCorrida(Date horaCorrida) {
        this.horaCorrida = horaCorrida;
    }

    /**
     * Gets the lunesAplica of this TmsOfertasServicioTbl.
     * @return the lunesAplica
     */
    public String getLunesAplica() {
        return this.lunesAplica;
    }

    /**
     * Sets the lunesAplica of this TmsOfertasServicioTbl to the specified value.
     * @param lunesAplica the new lunesAplica
     */
    public void setLunesAplica(String lunesAplica) {
        this.lunesAplica = lunesAplica;
    }

    /**
     * Gets the martesAplica of this TmsOfertasServicioTbl.
     * @return the martesAplica
     */
    public String getMartesAplica() {
        return this.martesAplica;
    }

    /**
     * Sets the martesAplica of this TmsOfertasServicioTbl to the specified value.
     * @param martesAplica the new martesAplica
     */
    public void setMartesAplica(String martesAplica) {
        this.martesAplica = martesAplica;
    }

    /**
     * Gets the miercolesAplica of this TmsOfertasServicioTbl.
     * @return the miercolesAplica
     */
    public String getMiercolesAplica() {
        return this.miercolesAplica;
    }

    /**
     * Sets the miercolesAplica of this TmsOfertasServicioTbl to the specified value.
     * @param miercolesAplica the new miercolesAplica
     */
    public void setMiercolesAplica(String miercolesAplica) {
        this.miercolesAplica = miercolesAplica;
    }

    /**
     * Gets the juevesAplica of this TmsOfertasServicioTbl.
     * @return the juevesAplica
     */
    public String getJuevesAplica() {
        return this.juevesAplica;
    }

    /**
     * Sets the juevesAplica of this TmsOfertasServicioTbl to the specified value.
     * @param juevesAplica the new juevesAplica
     */
    public void setJuevesAplica(String juevesAplica) {
        this.juevesAplica = juevesAplica;
    }

    /**
     * Gets the viernesAplica of this TmsOfertasServicioTbl.
     * @return the viernesAplica
     */
    public String getViernesAplica() {
        return this.viernesAplica;
    }

    /**
     * Sets the viernesAplica of this TmsOfertasServicioTbl to the specified value.
     * @param viernesAplica the new viernesAplica
     */
    public void setViernesAplica(String viernesAplica) {
        this.viernesAplica = viernesAplica;
    }

    /**
     * Gets the sabadoAplica of this TmsOfertasServicioTbl.
     * @return the sabadoAplica
     */
    public String getSabadoAplica() {
        return this.sabadoAplica;
    }

    /**
     * Sets the sabadoAplica of this TmsOfertasServicioTbl to the specified value.
     * @param sabadoAplica the new sabadoAplica
     */
    public void setSabadoAplica(String sabadoAplica) {
        this.sabadoAplica = sabadoAplica;
    }

    /**
     * Gets the domingoAplica of this TmsOfertasServicioTbl.
     * @return the domingoAplica
     */
    public String getDomingoAplica() {
        return this.domingoAplica;
    }

    /**
     * Sets the domingoAplica of this TmsOfertasServicioTbl to the specified value.
     * @param domingoAplica the new domingoAplica
     */
    public void setDomingoAplica(String domingoAplica) {
        this.domingoAplica = domingoAplica;
    }

    /**
     * Gets the menoresCorrida of this TmsOfertasServicioTbl.
     * @return the menoresCorrida
     */
    public BigInteger getMenoresCorrida() {
        return this.menoresCorrida;
    }

    /**
     * Sets the menoresCorrida of this TmsOfertasServicioTbl to the specified value.
     * @param menoresCorrida the new menoresCorrida
     */
    public void setMenoresCorrida(BigInteger menoresCorrida) {
        this.menoresCorrida = menoresCorrida;
    }

    /**
     * Gets the senectudCorrida of this TmsOfertasServicioTbl.
     * @return the senectudCorrida
     */
    public BigInteger getSenectudCorrida() {
        return this.senectudCorrida;
    }

    /**
     * Sets the senectudCorrida of this TmsOfertasServicioTbl to the specified value.
     * @param senectudCorrida the new senectudCorrida
     */
    public void setSenectudCorrida(BigInteger senectudCorrida) {
        this.senectudCorrida = senectudCorrida;
    }

    /**
     * Gets the estudiantesCorrida of this TmsOfertasServicioTbl.
     * @return the estudiantesCorrida
     */
    public BigInteger getEstudiantesCorrida() {
        return this.estudiantesCorrida;
    }

    /**
     * Sets the estudiantesCorrida of this TmsOfertasServicioTbl to the specified value.
     * @param estudiantesCorrida the new estudiantesCorrida
     */
    public void setEstudiantesCorrida(BigInteger estudiantesCorrida) {
        this.estudiantesCorrida = estudiantesCorrida;
    }

    /**
     * Gets the profesoresCorrida of this TmsOfertasServicioTbl.
     * @return the profesoresCorrida
     */
    public BigInteger getProfesoresCorrida() {
        return this.profesoresCorrida;
    }

    /**
     * Sets the profesoresCorrida of this TmsOfertasServicioTbl to the specified value.
     * @param profesoresCorrida the new profesoresCorrida
     */
    public void setProfesoresCorrida(BigInteger profesoresCorrida) {
        this.profesoresCorrida = profesoresCorrida;
    }

    /**
     * Gets the cortesiasCorrida of this TmsOfertasServicioTbl.
     * @return the cortesiasCorrida
     */
    public BigInteger getCortesiasCorrida() {
        return this.cortesiasCorrida;
    }

    /**
     * Sets the cortesiasCorrida of this TmsOfertasServicioTbl to the specified value.
     * @param cortesiasCorrida the new cortesiasCorrida
     */
    public void setCortesiasCorrida(BigInteger cortesiasCorrida) {
        this.cortesiasCorrida = cortesiasCorrida;
    }

    /**
     * Gets the adicional1 of this TmsOfertasServicioTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsOfertasServicioTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsOfertasServicioTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsOfertasServicioTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsOfertasServicioTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsOfertasServicioTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsOfertasServicioTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsOfertasServicioTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsOfertasServicioTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsOfertasServicioTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the adicional6 of this TmsOfertasServicioTbl.
     * @return the adicional6
     */
    public String getAdicional6() {
        return this.adicional6;
    }

    /**
     * Sets the adicional6 of this TmsOfertasServicioTbl to the specified value.
     * @param adicional6 the new adicional6
     */
    public void setAdicional6(String adicional6) {
        this.adicional6 = adicional6;
    }

    /**
     * Gets the adicional7 of this TmsOfertasServicioTbl.
     * @return the adicional7
     */
    public String getAdicional7() {
        return this.adicional7;
    }

    /**
     * Sets the adicional7 of this TmsOfertasServicioTbl to the specified value.
     * @param adicional7 the new adicional7
     */
    public void setAdicional7(String adicional7) {
        this.adicional7 = adicional7;
    }

    /**
     * Gets the adicional8 of this TmsOfertasServicioTbl.
     * @return the adicional8
     */
    public String getAdicional8() {
        return this.adicional8;
    }

    /**
     * Sets the adicional8 of this TmsOfertasServicioTbl to the specified value.
     * @param adicional8 the new adicional8
     */
    public void setAdicional8(String adicional8) {
        this.adicional8 = adicional8;
    }

    /**
     * Gets the adicional9 of this TmsOfertasServicioTbl.
     * @return the adicional9
     */
    public String getAdicional9() {
        return this.adicional9;
    }

    /**
     * Sets the adicional9 of this TmsOfertasServicioTbl to the specified value.
     * @param adicional9 the new adicional9
     */
    public void setAdicional9(String adicional9) {
        this.adicional9 = adicional9;
    }

    /**
     * Gets the adicional10 of this TmsOfertasServicioTbl.
     * @return the adicional10
     */
    public String getAdicional10() {
        return this.adicional10;
    }

    /**
     * Sets the adicional10 of this TmsOfertasServicioTbl to the specified value.
     * @param adicional10 the new adicional10
     */
    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
    }

    /**
     * Gets the creadoPor of this TmsOfertasServicioTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsOfertasServicioTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsOfertasServicioTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsOfertasServicioTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsOfertasServicioTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsOfertasServicioTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsOfertasServicioTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsOfertasServicioTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this TmsOfertasServicioTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsOfertasServicioTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsOfertasServicioTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsOfertasServicioTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsOfertasServicioTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsOfertasServicioTbl to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Gets the empresaId of this TmsOfertasServicioTbl.
     * @return the empresaId
     */
    public TmsEmpresasTbl getEmpresaId() {
        return this.empresaId;
    }

    /**
     * Sets the empresaId of this TmsOfertasServicioTbl to the specified value.
     * @param empresaId the new empresaId
     */
    public void setEmpresaId(TmsEmpresasTbl empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * Gets the rutaId of this TmsOfertasServicioTbl.
     * @return the rutaId
     */
    public TmsRutasTbl getRutaId() {
        return this.rutaId;
    }

    /**
     * Sets the rutaId of this TmsOfertasServicioTbl to the specified value.
     * @param rutaId the new rutaId
     */
    public void setRutaId(TmsRutasTbl rutaId) {
        this.rutaId = rutaId;
    }

    /**
     * Gets the servicioId of this TmsOfertasServicioTbl.
     * @return the servicioId
     */
    public TmsServiciosTbl getServicioId() {
        return this.servicioId;
    }

    /**
     * Sets the servicioId of this TmsOfertasServicioTbl to the specified value.
     * @param servicioId the new servicioId
     */
    public void setServicioId(TmsServiciosTbl servicioId) {
        this.servicioId = servicioId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.ofertaServicioId != null ? this.ofertaServicioId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsOfertasServicioTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsOfertasServicioTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsOfertasServicioTbl)) {
            return false;
        }
        TmsOfertasServicioTbl other = (TmsOfertasServicioTbl)object;
        if (this.ofertaServicioId != other.ofertaServicioId && (this.ofertaServicioId == null || !this.ofertaServicioId.equals(other.ofertaServicioId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsroles.entidad.TmsOfertasServicioTbl[ofertaServicioId=" + ofertaServicioId + "]";
    }
    
}
