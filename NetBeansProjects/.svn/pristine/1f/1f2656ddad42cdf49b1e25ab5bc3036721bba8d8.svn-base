/*
 * TmsAutobusesTbl.java
 *
 * Created on 20 de noviembre de 2007, 10:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsAutobusesTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_AUTOBUSES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsAutobusesTbl.findByAutobusId", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.autobusId = :autobusId"),
        @NamedQuery(name = "TmsAutobusesTbl.findByNumeroEconomico", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.numeroEconomico = :numeroEconomico"),
        @NamedQuery(name = "TmsAutobusesTbl.findByPlantillaEncId", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.plantillaEncId = :plantillaEncId"),
        @NamedQuery(name = "TmsAutobusesTbl.findByFlotillaId", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.flotillaId = :flotillaId"),
        @NamedQuery(name = "TmsAutobusesTbl.findByOperadorIdPlanta", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.operadorIdPlanta = :operadorIdPlanta"),
        @NamedQuery(name = "TmsAutobusesTbl.findByKmsRecorridos", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.kmsRecorridos = :kmsRecorridos"),
        @NamedQuery(name = "TmsAutobusesTbl.findByFechaMantenimiento", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.fechaMantenimiento = :fechaMantenimiento"),
        @NamedQuery(name = "TmsAutobusesTbl.findByComponente1Id", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.componente1Id = :componente1Id"),
        @NamedQuery(name = "TmsAutobusesTbl.findByComponente2Id", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.componente2Id = :componente2Id"),
        @NamedQuery(name = "TmsAutobusesTbl.findByComponente3Id", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.componente3Id = :componente3Id"),
        @NamedQuery(name = "TmsAutobusesTbl.findByFechaDesde", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.fechaDesde = :fechaDesde"),
        @NamedQuery(name = "TmsAutobusesTbl.findByFechaHasta", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.fechaHasta = :fechaHasta"),
        @NamedQuery(name = "TmsAutobusesTbl.findByKmsAsignacion", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.kmsAsignacion = :kmsAsignacion"),
        @NamedQuery(name = "TmsAutobusesTbl.findByKmsUltimaLectura", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.kmsUltimaLectura = :kmsUltimaLectura"),
        @NamedQuery(name = "TmsAutobusesTbl.findByFechaUltimaLectura", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.fechaUltimaLectura = :fechaUltimaLectura"),
        @NamedQuery(name = "TmsAutobusesTbl.findByKmsUltimoReset", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.kmsUltimoReset = :kmsUltimoReset"),
        @NamedQuery(name = "TmsAutobusesTbl.findByFechaUltimoMp", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.fechaUltimoMp = :fechaUltimoMp"),
        @NamedQuery(name = "TmsAutobusesTbl.findByEmpresaId", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.empresaId = :empresaId"),
        @NamedQuery(name = "TmsAutobusesTbl.findByAdicional1", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsAutobusesTbl.findByAdicional2", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsAutobusesTbl.findByAdicional3", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsAutobusesTbl.findByAdicional4", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsAutobusesTbl.findByAdicional5", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsAutobusesTbl.findByAdicional6", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.adicional6 = :adicional6"),
        @NamedQuery(name = "TmsAutobusesTbl.findByAdicional7", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.adicional7 = :adicional7"),
        @NamedQuery(name = "TmsAutobusesTbl.findByAdicional8", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.adicional8 = :adicional8"),
        @NamedQuery(name = "TmsAutobusesTbl.findByAdicional9", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.adicional9 = :adicional9"),
        @NamedQuery(name = "TmsAutobusesTbl.findByAdicional10", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.adicional10 = :adicional10"),
        @NamedQuery(name = "TmsAutobusesTbl.findByCreadoPor", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsAutobusesTbl.findByFechaCreacion", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsAutobusesTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsAutobusesTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsAutobusesTbl.findByReplicacionEstado", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsAutobusesTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsAutobusesTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsAutobusesTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsAutobusesTbl implements Serializable {

    @Id
    @Column(name = "AUTOBUS_ID", nullable = false)
    private BigDecimal autobusId;

    @Column(name = "NUMERO_ECONOMICO", nullable = false)
    private String numeroEconomico;

    @Column(name = "PLANTILLA_ENC_ID")
    private BigInteger plantillaEncId;

    @Column(name = "FLOTILLA_ID")
    private BigInteger flotillaId;

    @Column(name = "OPERADOR_ID_PLANTA")
    private BigInteger operadorIdPlanta;

    @Column(name = "KMS_RECORRIDOS")
    private BigInteger kmsRecorridos;

    @Column(name = "FECHA_MANTENIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaMantenimiento;

    @Column(name = "COMPONENTE_1_ID")
    private BigInteger componente1Id;

    @Column(name = "COMPONENTE_2_ID")
    private BigInteger componente2Id;

    @Column(name = "COMPONENTE_3_ID")
    private BigInteger componente3Id;

    @Column(name = "FECHA_DESDE")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;

    @Column(name = "FECHA_HASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;

    @Column(name = "KMS_ASIGNACION")
    private BigInteger kmsAsignacion;

    @Column(name = "KMS_ULTIMA_LECTURA")
    private BigInteger kmsUltimaLectura;

    @Column(name = "FECHA_ULTIMA_LECTURA")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimaLectura;

    @Column(name = "KMS_ULTIMO_RESET")
    private BigInteger kmsUltimoReset;

    @Column(name = "FECHA_ULTIMO_MP")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimoMp;

    @Column(name = "EMPRESA_ID")
    private BigInteger empresaId;

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

    @OneToMany(mappedBy = "autobusId")
    private Collection<TmsRolesBaseLineasTbl> tmsRolesBaseLineasTblCollection;
    
    /** Creates a new instance of TmsAutobusesTbl */
    public TmsAutobusesTbl() {
    }

    /**
     * Creates a new instance of TmsAutobusesTbl with the specified values.
     * @param autobusId the autobusId of the TmsAutobusesTbl
     */
    public TmsAutobusesTbl(BigDecimal autobusId) {
        this.autobusId = autobusId;
    }

    /**
     * Creates a new instance of TmsAutobusesTbl with the specified values.
     * @param autobusId the autobusId of the TmsAutobusesTbl
     * @param numeroEconomico the numeroEconomico of the TmsAutobusesTbl
     * @param creadoPor the creadoPor of the TmsAutobusesTbl
     * @param fechaCreacion the fechaCreacion of the TmsAutobusesTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsAutobusesTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsAutobusesTbl
     */
    public TmsAutobusesTbl(BigDecimal autobusId, String numeroEconomico, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.autobusId = autobusId;
        this.numeroEconomico = numeroEconomico;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the autobusId of this TmsAutobusesTbl.
     * @return the autobusId
     */
    public BigDecimal getAutobusId() {
        return this.autobusId;
    }

    /**
     * Sets the autobusId of this TmsAutobusesTbl to the specified value.
     * @param autobusId the new autobusId
     */
    public void setAutobusId(BigDecimal autobusId) {
        this.autobusId = autobusId;
    }

    /**
     * Gets the numeroEconomico of this TmsAutobusesTbl.
     * @return the numeroEconomico
     */
    public String getNumeroEconomico() {
        return this.numeroEconomico;
    }

    /**
     * Sets the numeroEconomico of this TmsAutobusesTbl to the specified value.
     * @param numeroEconomico the new numeroEconomico
     */
    public void setNumeroEconomico(String numeroEconomico) {
        this.numeroEconomico = numeroEconomico;
    }

    /**
     * Gets the plantillaEncId of this TmsAutobusesTbl.
     * @return the plantillaEncId
     */
    public BigInteger getPlantillaEncId() {
        return this.plantillaEncId;
    }

    /**
     * Sets the plantillaEncId of this TmsAutobusesTbl to the specified value.
     * @param plantillaEncId the new plantillaEncId
     */
    public void setPlantillaEncId(BigInteger plantillaEncId) {
        this.plantillaEncId = plantillaEncId;
    }

    /**
     * Gets the flotillaId of this TmsAutobusesTbl.
     * @return the flotillaId
     */
    public BigInteger getFlotillaId() {
        return this.flotillaId;
    }

    /**
     * Sets the flotillaId of this TmsAutobusesTbl to the specified value.
     * @param flotillaId the new flotillaId
     */
    public void setFlotillaId(BigInteger flotillaId) {
        this.flotillaId = flotillaId;
    }

    /**
     * Gets the operadorIdPlanta of this TmsAutobusesTbl.
     * @return the operadorIdPlanta
     */
    public BigInteger getOperadorIdPlanta() {
        return this.operadorIdPlanta;
    }

    /**
     * Sets the operadorIdPlanta of this TmsAutobusesTbl to the specified value.
     * @param operadorIdPlanta the new operadorIdPlanta
     */
    public void setOperadorIdPlanta(BigInteger operadorIdPlanta) {
        this.operadorIdPlanta = operadorIdPlanta;
    }

    /**
     * Gets the kmsRecorridos of this TmsAutobusesTbl.
     * @return the kmsRecorridos
     */
    public BigInteger getKmsRecorridos() {
        return this.kmsRecorridos;
    }

    /**
     * Sets the kmsRecorridos of this TmsAutobusesTbl to the specified value.
     * @param kmsRecorridos the new kmsRecorridos
     */
    public void setKmsRecorridos(BigInteger kmsRecorridos) {
        this.kmsRecorridos = kmsRecorridos;
    }

    /**
     * Gets the fechaMantenimiento of this TmsAutobusesTbl.
     * @return the fechaMantenimiento
     */
    public Date getFechaMantenimiento() {
        return this.fechaMantenimiento;
    }

    /**
     * Sets the fechaMantenimiento of this TmsAutobusesTbl to the specified value.
     * @param fechaMantenimiento the new fechaMantenimiento
     */
    public void setFechaMantenimiento(Date fechaMantenimiento) {
        this.fechaMantenimiento = fechaMantenimiento;
    }

    /**
     * Gets the componente1Id of this TmsAutobusesTbl.
     * @return the componente1Id
     */
    public BigInteger getComponente1Id() {
        return this.componente1Id;
    }

    /**
     * Sets the componente1Id of this TmsAutobusesTbl to the specified value.
     * @param componente1Id the new componente1Id
     */
    public void setComponente1Id(BigInteger componente1Id) {
        this.componente1Id = componente1Id;
    }

    /**
     * Gets the componente2Id of this TmsAutobusesTbl.
     * @return the componente2Id
     */
    public BigInteger getComponente2Id() {
        return this.componente2Id;
    }

    /**
     * Sets the componente2Id of this TmsAutobusesTbl to the specified value.
     * @param componente2Id the new componente2Id
     */
    public void setComponente2Id(BigInteger componente2Id) {
        this.componente2Id = componente2Id;
    }

    /**
     * Gets the componente3Id of this TmsAutobusesTbl.
     * @return the componente3Id
     */
    public BigInteger getComponente3Id() {
        return this.componente3Id;
    }

    /**
     * Sets the componente3Id of this TmsAutobusesTbl to the specified value.
     * @param componente3Id the new componente3Id
     */
    public void setComponente3Id(BigInteger componente3Id) {
        this.componente3Id = componente3Id;
    }

    /**
     * Gets the fechaDesde of this TmsAutobusesTbl.
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
        return this.fechaDesde;
    }

    /**
     * Sets the fechaDesde of this TmsAutobusesTbl to the specified value.
     * @param fechaDesde the new fechaDesde
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Gets the fechaHasta of this TmsAutobusesTbl.
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
        return this.fechaHasta;
    }

    /**
     * Sets the fechaHasta of this TmsAutobusesTbl to the specified value.
     * @param fechaHasta the new fechaHasta
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * Gets the kmsAsignacion of this TmsAutobusesTbl.
     * @return the kmsAsignacion
     */
    public BigInteger getKmsAsignacion() {
        return this.kmsAsignacion;
    }

    /**
     * Sets the kmsAsignacion of this TmsAutobusesTbl to the specified value.
     * @param kmsAsignacion the new kmsAsignacion
     */
    public void setKmsAsignacion(BigInteger kmsAsignacion) {
        this.kmsAsignacion = kmsAsignacion;
    }

    /**
     * Gets the kmsUltimaLectura of this TmsAutobusesTbl.
     * @return the kmsUltimaLectura
     */
    public BigInteger getKmsUltimaLectura() {
        return this.kmsUltimaLectura;
    }

    /**
     * Sets the kmsUltimaLectura of this TmsAutobusesTbl to the specified value.
     * @param kmsUltimaLectura the new kmsUltimaLectura
     */
    public void setKmsUltimaLectura(BigInteger kmsUltimaLectura) {
        this.kmsUltimaLectura = kmsUltimaLectura;
    }

    /**
     * Gets the fechaUltimaLectura of this TmsAutobusesTbl.
     * @return the fechaUltimaLectura
     */
    public Date getFechaUltimaLectura() {
        return this.fechaUltimaLectura;
    }

    /**
     * Sets the fechaUltimaLectura of this TmsAutobusesTbl to the specified value.
     * @param fechaUltimaLectura the new fechaUltimaLectura
     */
    public void setFechaUltimaLectura(Date fechaUltimaLectura) {
        this.fechaUltimaLectura = fechaUltimaLectura;
    }

    /**
     * Gets the kmsUltimoReset of this TmsAutobusesTbl.
     * @return the kmsUltimoReset
     */
    public BigInteger getKmsUltimoReset() {
        return this.kmsUltimoReset;
    }

    /**
     * Sets the kmsUltimoReset of this TmsAutobusesTbl to the specified value.
     * @param kmsUltimoReset the new kmsUltimoReset
     */
    public void setKmsUltimoReset(BigInteger kmsUltimoReset) {
        this.kmsUltimoReset = kmsUltimoReset;
    }

    /**
     * Gets the fechaUltimoMp of this TmsAutobusesTbl.
     * @return the fechaUltimoMp
     */
    public Date getFechaUltimoMp() {
        return this.fechaUltimoMp;
    }

    /**
     * Sets the fechaUltimoMp of this TmsAutobusesTbl to the specified value.
     * @param fechaUltimoMp the new fechaUltimoMp
     */
    public void setFechaUltimoMp(Date fechaUltimoMp) {
        this.fechaUltimoMp = fechaUltimoMp;
    }

    /**
     * Gets the empresaId of this TmsAutobusesTbl.
     * @return the empresaId
     */
    public BigInteger getEmpresaId() {
        return this.empresaId;
    }

    /**
     * Sets the empresaId of this TmsAutobusesTbl to the specified value.
     * @param empresaId the new empresaId
     */
    public void setEmpresaId(BigInteger empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * Gets the adicional1 of this TmsAutobusesTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsAutobusesTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsAutobusesTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsAutobusesTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsAutobusesTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsAutobusesTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsAutobusesTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsAutobusesTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsAutobusesTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsAutobusesTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the adicional6 of this TmsAutobusesTbl.
     * @return the adicional6
     */
    public String getAdicional6() {
        return this.adicional6;
    }

    /**
     * Sets the adicional6 of this TmsAutobusesTbl to the specified value.
     * @param adicional6 the new adicional6
     */
    public void setAdicional6(String adicional6) {
        this.adicional6 = adicional6;
    }

    /**
     * Gets the adicional7 of this TmsAutobusesTbl.
     * @return the adicional7
     */
    public String getAdicional7() {
        return this.adicional7;
    }

    /**
     * Sets the adicional7 of this TmsAutobusesTbl to the specified value.
     * @param adicional7 the new adicional7
     */
    public void setAdicional7(String adicional7) {
        this.adicional7 = adicional7;
    }

    /**
     * Gets the adicional8 of this TmsAutobusesTbl.
     * @return the adicional8
     */
    public String getAdicional8() {
        return this.adicional8;
    }

    /**
     * Sets the adicional8 of this TmsAutobusesTbl to the specified value.
     * @param adicional8 the new adicional8
     */
    public void setAdicional8(String adicional8) {
        this.adicional8 = adicional8;
    }

    /**
     * Gets the adicional9 of this TmsAutobusesTbl.
     * @return the adicional9
     */
    public String getAdicional9() {
        return this.adicional9;
    }

    /**
     * Sets the adicional9 of this TmsAutobusesTbl to the specified value.
     * @param adicional9 the new adicional9
     */
    public void setAdicional9(String adicional9) {
        this.adicional9 = adicional9;
    }

    /**
     * Gets the adicional10 of this TmsAutobusesTbl.
     * @return the adicional10
     */
    public String getAdicional10() {
        return this.adicional10;
    }

    /**
     * Sets the adicional10 of this TmsAutobusesTbl to the specified value.
     * @param adicional10 the new adicional10
     */
    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
    }

    /**
     * Gets the creadoPor of this TmsAutobusesTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsAutobusesTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsAutobusesTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsAutobusesTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsAutobusesTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsAutobusesTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsAutobusesTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsAutobusesTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this TmsAutobusesTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsAutobusesTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsAutobusesTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsAutobusesTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsAutobusesTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsAutobusesTbl to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Gets the tmsRolesBaseLineasTblCollection of this TmsAutobusesTbl.
     * @return the tmsRolesBaseLineasTblCollection
     */
    public Collection<TmsRolesBaseLineasTbl> getTmsRolesBaseLineasTblCollection() {
        return this.tmsRolesBaseLineasTblCollection;
    }

    /**
     * Sets the tmsRolesBaseLineasTblCollection of this TmsAutobusesTbl to the specified value.
     * @param tmsRolesBaseLineasTblCollection the new tmsRolesBaseLineasTblCollection
     */
    public void setTmsRolesBaseLineasTblCollection(Collection<TmsRolesBaseLineasTbl> tmsRolesBaseLineasTblCollection) {
        this.tmsRolesBaseLineasTblCollection = tmsRolesBaseLineasTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.autobusId != null ? this.autobusId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsAutobusesTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsAutobusesTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsAutobusesTbl)) {
            return false;
        }
        TmsAutobusesTbl other = (TmsAutobusesTbl)object;
        if (this.autobusId != other.autobusId && (this.autobusId == null || !this.autobusId.equals(other.autobusId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsroles.entidad.TmsAutobusesTbl[autobusId=" + autobusId + "]";
    }
    
}
