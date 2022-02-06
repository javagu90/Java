/*
 * TmsOperadoresTbl.java
 *
 * Created on 9 de octubre de 2007, 10:40 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_ocupacion.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsOperadoresTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_OPERADORES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsOperadoresTbl.findByOperadorId", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.operadorId = :operadorId"),
        @NamedQuery(name = "TmsOperadoresTbl.findByClaveOperador", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.claveOperador = :claveOperador"),
        @NamedQuery(name = "TmsOperadoresTbl.findByDiaActivo", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.diaActivo = :diaActivo"),
        @NamedQuery(name = "TmsOperadoresTbl.findByHabilitado", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsOperadoresTbl.findByCreadoPor", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsOperadoresTbl.findByFechaCreacion", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsOperadoresTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsOperadoresTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsOperadoresTbl.findByOperadorNombre", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.operadorNombre = :operadorNombre"),
        @NamedQuery(name = "TmsOperadoresTbl.findByOperadorNombreMedio", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.operadorNombreMedio = :operadorNombreMedio"),
        @NamedQuery(name = "TmsOperadoresTbl.findByOperadorApellido", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.operadorApellido = :operadorApellido"),
        @NamedQuery(name = "TmsOperadoresTbl.findByOperadorNombreCompleto", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.operadorNombreCompleto = :operadorNombreCompleto"),
        @NamedQuery(name = "TmsOperadoresTbl.findByFechaAlta", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.fechaAlta = :fechaAlta"),
        @NamedQuery(name = "TmsOperadoresTbl.findByFechaBaja", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.fechaBaja = :fechaBaja"),
        @NamedQuery(name = "TmsOperadoresTbl.findByAplicaRetencion", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.aplicaRetencion = :aplicaRetencion"),
        @NamedQuery(name = "TmsOperadoresTbl.findByAdicional1", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsOperadoresTbl.findByAdicional2", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsOperadoresTbl.findByAdicional3", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsOperadoresTbl.findByAdicional4", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsOperadoresTbl.findByAdicional5", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsOperadoresTbl.findByEmpresaId", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.empresaId = :empresaId"),
        @NamedQuery(name = "TmsOperadoresTbl.findByTipoServicio", query = "SELECT t FROM TmsOperadoresTbl t WHERE t.tipoServicio = :tipoServicio")
    })
public class TmsOperadoresTbl implements Serializable {

    @Id
    @Column(name = "OPERADOR_ID", nullable = false)
    private BigDecimal operadorId;

    @Column(name = "CLAVE_OPERADOR", nullable = false)
    private String claveOperador;

    @Column(name = "DIA_ACTIVO", nullable = false)
    private BigInteger diaActivo;

    @Column(name = "HABILITADO", nullable = false)
    private String habilitado;

    @Column(name = "CREADO_POR", nullable = false)
    private int creadoPor;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private Integer ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActualizacion;

    @Column(name = "OPERADOR_NOMBRE")
    private String operadorNombre;

    @Column(name = "OPERADOR_NOMBRE_MEDIO")
    private String operadorNombreMedio;

    @Column(name = "OPERADOR_APELLIDO")
    private String operadorApellido;

    @Column(name = "OPERADOR_NOMBRE_COMPLETO")
    private String operadorNombreCompleto;

    @Column(name = "FECHA_ALTA")
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;

    @Column(name = "FECHA_BAJA")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;

    @Column(name = "APLICA_RETENCION")
    private String aplicaRetencion;

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

    @Column(name = "EMPRESA_ID")
    private BigInteger empresaId;

    @Column(name = "TIPO_SERVICIO")
    private String tipoServicio;

    @JoinColumn(name = "ACCION1_ID", referencedColumnName = "ACCION_ID")
    @ManyToOne
    private TmsAccionesTbl accion1Id;

    @JoinColumn(name = "ACCION2_ID", referencedColumnName = "ACCION_ID")
    @ManyToOne
    private TmsAccionesTbl accion2Id;

    @JoinColumn(name = "ACCION4_ID", referencedColumnName = "ACCION_ID")
    @ManyToOne
    private TmsAccionesTbl accion4Id;

    @JoinColumn(name = "ACCION3_ID", referencedColumnName = "ACCION_ID")
    @ManyToOne
    private TmsAccionesTbl accion3Id;

    @JoinColumn(name = "JORNADA_SERVICIO_ID", referencedColumnName = "JORNADA_SERVICIO_ID")
    @ManyToOne
    private TmsJornadasServiciosTbl jornadaServicioId;

    @OneToMany(mappedBy = "operadorIdFijo")
    private Collection<TmsOperadoresTbl> tmsOperadoresTblCollection;

    @JoinColumn(name = "OPERADOR_ID_FIJO", referencedColumnName = "OPERADOR_ID")
    @ManyToOne
    private TmsOperadoresTbl operadorIdFijo;

    @JoinColumn(name = "TIPO_OPERADOR_ID", referencedColumnName = "TIPO_OPERADOR_ID")
    @ManyToOne
    private TmsTiposOperadorTbl tipoOperadorId;

    @OneToMany(mappedBy = "operadorIdPlanta")
    private Collection<TmsAutobusesTbl> tmsAutobusesTblCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operadorId")
    private Collection<TmsCorridasTbl> tmsCorridasTblCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operadorOriginalId")
    private Collection<TmsCorridasTbl> tmsCorridasTblCollection1;
    
    /** Creates a new instance of TmsOperadoresTbl */
    public TmsOperadoresTbl() {
    }

    /**
     * Creates a new instance of TmsOperadoresTbl with the specified values.
     * @param operadorId the operadorId of the TmsOperadoresTbl
     */
    public TmsOperadoresTbl(BigDecimal operadorId) {
        this.operadorId = operadorId;
    }

    /**
     * Creates a new instance of TmsOperadoresTbl with the specified values.
     * @param operadorId the operadorId of the TmsOperadoresTbl
     * @param claveOperador the claveOperador of the TmsOperadoresTbl
     * @param diaActivo the diaActivo of the TmsOperadoresTbl
     * @param habilitado the habilitado of the TmsOperadoresTbl
     * @param creadoPor the creadoPor of the TmsOperadoresTbl
     * @param fechaCreacion the fechaCreacion of the TmsOperadoresTbl
     */
    public TmsOperadoresTbl(BigDecimal operadorId, String claveOperador, BigInteger diaActivo, String habilitado, int creadoPor, Date fechaCreacion) {
        this.operadorId = operadorId;
        this.claveOperador = claveOperador;
        this.diaActivo = diaActivo;
        this.habilitado = habilitado;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the operadorId of this TmsOperadoresTbl.
     * @return the operadorId
     */
    public BigDecimal getOperadorId() {
        return this.operadorId;
    }

    /**
     * Sets the operadorId of this TmsOperadoresTbl to the specified value.
     * @param operadorId the new operadorId
     */
    public void setOperadorId(BigDecimal operadorId) {
        this.operadorId = operadorId;
    }

    /**
     * Gets the claveOperador of this TmsOperadoresTbl.
     * @return the claveOperador
     */
    public String getClaveOperador() {
        return this.claveOperador;
    }

    /**
     * Sets the claveOperador of this TmsOperadoresTbl to the specified value.
     * @param claveOperador the new claveOperador
     */
    public void setClaveOperador(String claveOperador) {
        this.claveOperador = claveOperador;
    }

    /**
     * Gets the diaActivo of this TmsOperadoresTbl.
     * @return the diaActivo
     */
    public BigInteger getDiaActivo() {
        return this.diaActivo;
    }

    /**
     * Sets the diaActivo of this TmsOperadoresTbl to the specified value.
     * @param diaActivo the new diaActivo
     */
    public void setDiaActivo(BigInteger diaActivo) {
        this.diaActivo = diaActivo;
    }

    /**
     * Gets the habilitado of this TmsOperadoresTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsOperadoresTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsOperadoresTbl.
     * @return the creadoPor
     */
    public int getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsOperadoresTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(int creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsOperadoresTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsOperadoresTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsOperadoresTbl.
     * @return the ultimaActualizacionPor
     */
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsOperadoresTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsOperadoresTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsOperadoresTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the operadorNombre of this TmsOperadoresTbl.
     * @return the operadorNombre
     */
    public String getOperadorNombre() {
        return this.operadorNombre;
    }

    /**
     * Sets the operadorNombre of this TmsOperadoresTbl to the specified value.
     * @param operadorNombre the new operadorNombre
     */
    public void setOperadorNombre(String operadorNombre) {
        this.operadorNombre = operadorNombre;
    }

    /**
     * Gets the operadorNombreMedio of this TmsOperadoresTbl.
     * @return the operadorNombreMedio
     */
    public String getOperadorNombreMedio() {
        return this.operadorNombreMedio;
    }

    /**
     * Sets the operadorNombreMedio of this TmsOperadoresTbl to the specified value.
     * @param operadorNombreMedio the new operadorNombreMedio
     */
    public void setOperadorNombreMedio(String operadorNombreMedio) {
        this.operadorNombreMedio = operadorNombreMedio;
    }

    /**
     * Gets the operadorApellido of this TmsOperadoresTbl.
     * @return the operadorApellido
     */
    public String getOperadorApellido() {
        return this.operadorApellido;
    }

    /**
     * Sets the operadorApellido of this TmsOperadoresTbl to the specified value.
     * @param operadorApellido the new operadorApellido
     */
    public void setOperadorApellido(String operadorApellido) {
        this.operadorApellido = operadorApellido;
    }

    /**
     * Gets the operadorNombreCompleto of this TmsOperadoresTbl.
     * @return the operadorNombreCompleto
     */
    public String getOperadorNombreCompleto() {
        return this.operadorNombreCompleto;
    }

    /**
     * Sets the operadorNombreCompleto of this TmsOperadoresTbl to the specified value.
     * @param operadorNombreCompleto the new operadorNombreCompleto
     */
    public void setOperadorNombreCompleto(String operadorNombreCompleto) {
        this.operadorNombreCompleto = operadorNombreCompleto;
    }

    /**
     * Gets the fechaAlta of this TmsOperadoresTbl.
     * @return the fechaAlta
     */
    public Date getFechaAlta() {
        return this.fechaAlta;
    }

    /**
     * Sets the fechaAlta of this TmsOperadoresTbl to the specified value.
     * @param fechaAlta the new fechaAlta
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * Gets the fechaBaja of this TmsOperadoresTbl.
     * @return the fechaBaja
     */
    public Date getFechaBaja() {
        return this.fechaBaja;
    }

    /**
     * Sets the fechaBaja of this TmsOperadoresTbl to the specified value.
     * @param fechaBaja the new fechaBaja
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * Gets the aplicaRetencion of this TmsOperadoresTbl.
     * @return the aplicaRetencion
     */
    public String getAplicaRetencion() {
        return this.aplicaRetencion;
    }

    /**
     * Sets the aplicaRetencion of this TmsOperadoresTbl to the specified value.
     * @param aplicaRetencion the new aplicaRetencion
     */
    public void setAplicaRetencion(String aplicaRetencion) {
        this.aplicaRetencion = aplicaRetencion;
    }

    /**
     * Gets the adicional1 of this TmsOperadoresTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsOperadoresTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsOperadoresTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsOperadoresTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsOperadoresTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsOperadoresTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsOperadoresTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsOperadoresTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsOperadoresTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsOperadoresTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the empresaId of this TmsOperadoresTbl.
     * @return the empresaId
     */
    public BigInteger getEmpresaId() {
        return this.empresaId;
    }

    /**
     * Sets the empresaId of this TmsOperadoresTbl to the specified value.
     * @param empresaId the new empresaId
     */
    public void setEmpresaId(BigInteger empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * Gets the tipoServicio of this TmsOperadoresTbl.
     * @return the tipoServicio
     */
    public String getTipoServicio() {
        return this.tipoServicio;
    }

    /**
     * Sets the tipoServicio of this TmsOperadoresTbl to the specified value.
     * @param tipoServicio the new tipoServicio
     */
    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    /**
     * Gets the accion1Id of this TmsOperadoresTbl.
     * @return the accion1Id
     */
    public TmsAccionesTbl getAccion1Id() {
        return this.accion1Id;
    }

    /**
     * Sets the accion1Id of this TmsOperadoresTbl to the specified value.
     * @param accion1Id the new accion1Id
     */
    public void setAccion1Id(TmsAccionesTbl accion1Id) {
        this.accion1Id = accion1Id;
    }

    /**
     * Gets the accion2Id of this TmsOperadoresTbl.
     * @return the accion2Id
     */
    public TmsAccionesTbl getAccion2Id() {
        return this.accion2Id;
    }

    /**
     * Sets the accion2Id of this TmsOperadoresTbl to the specified value.
     * @param accion2Id the new accion2Id
     */
    public void setAccion2Id(TmsAccionesTbl accion2Id) {
        this.accion2Id = accion2Id;
    }

    /**
     * Gets the accion4Id of this TmsOperadoresTbl.
     * @return the accion4Id
     */
    public TmsAccionesTbl getAccion4Id() {
        return this.accion4Id;
    }

    /**
     * Sets the accion4Id of this TmsOperadoresTbl to the specified value.
     * @param accion4Id the new accion4Id
     */
    public void setAccion4Id(TmsAccionesTbl accion4Id) {
        this.accion4Id = accion4Id;
    }

    /**
     * Gets the accion3Id of this TmsOperadoresTbl.
     * @return the accion3Id
     */
    public TmsAccionesTbl getAccion3Id() {
        return this.accion3Id;
    }

    /**
     * Sets the accion3Id of this TmsOperadoresTbl to the specified value.
     * @param accion3Id the new accion3Id
     */
    public void setAccion3Id(TmsAccionesTbl accion3Id) {
        this.accion3Id = accion3Id;
    }

    /**
     * Gets the jornadaServicioId of this TmsOperadoresTbl.
     * @return the jornadaServicioId
     */
    public TmsJornadasServiciosTbl getJornadaServicioId() {
        return this.jornadaServicioId;
    }

    /**
     * Sets the jornadaServicioId of this TmsOperadoresTbl to the specified value.
     * @param jornadaServicioId the new jornadaServicioId
     */
    public void setJornadaServicioId(TmsJornadasServiciosTbl jornadaServicioId) {
        this.jornadaServicioId = jornadaServicioId;
    }

    /**
     * Gets the tmsOperadoresTblCollection of this TmsOperadoresTbl.
     * @return the tmsOperadoresTblCollection
     */
    public Collection<TmsOperadoresTbl> getTmsOperadoresTblCollection() {
        return this.tmsOperadoresTblCollection;
    }

    /**
     * Sets the tmsOperadoresTblCollection of this TmsOperadoresTbl to the specified value.
     * @param tmsOperadoresTblCollection the new tmsOperadoresTblCollection
     */
    public void setTmsOperadoresTblCollection(Collection<TmsOperadoresTbl> tmsOperadoresTblCollection) {
        this.tmsOperadoresTblCollection = tmsOperadoresTblCollection;
    }

    /**
     * Gets the operadorIdFijo of this TmsOperadoresTbl.
     * @return the operadorIdFijo
     */
    public TmsOperadoresTbl getOperadorIdFijo() {
        return this.operadorIdFijo;
    }

    /**
     * Sets the operadorIdFijo of this TmsOperadoresTbl to the specified value.
     * @param operadorIdFijo the new operadorIdFijo
     */
    public void setOperadorIdFijo(TmsOperadoresTbl operadorIdFijo) {
        this.operadorIdFijo = operadorIdFijo;
    }

    /**
     * Gets the tipoOperadorId of this TmsOperadoresTbl.
     * @return the tipoOperadorId
     */
    public TmsTiposOperadorTbl getTipoOperadorId() {
        return this.tipoOperadorId;
    }

    /**
     * Sets the tipoOperadorId of this TmsOperadoresTbl to the specified value.
     * @param tipoOperadorId the new tipoOperadorId
     */
    public void setTipoOperadorId(TmsTiposOperadorTbl tipoOperadorId) {
        this.tipoOperadorId = tipoOperadorId;
    }

    /**
     * Gets the tmsAutobusesTblCollection of this TmsOperadoresTbl.
     * @return the tmsAutobusesTblCollection
     */
    public Collection<TmsAutobusesTbl> getTmsAutobusesTblCollection() {
        return this.tmsAutobusesTblCollection;
    }

    /**
     * Sets the tmsAutobusesTblCollection of this TmsOperadoresTbl to the specified value.
     * @param tmsAutobusesTblCollection the new tmsAutobusesTblCollection
     */
    public void setTmsAutobusesTblCollection(Collection<TmsAutobusesTbl> tmsAutobusesTblCollection) {
        this.tmsAutobusesTblCollection = tmsAutobusesTblCollection;
    }

    /**
     * Gets the tmsCorridasTblCollection of this TmsOperadoresTbl.
     * @return the tmsCorridasTblCollection
     */
    public Collection<TmsCorridasTbl> getTmsCorridasTblCollection() {
        return this.tmsCorridasTblCollection;
    }

    /**
     * Sets the tmsCorridasTblCollection of this TmsOperadoresTbl to the specified value.
     * @param tmsCorridasTblCollection the new tmsCorridasTblCollection
     */
    public void setTmsCorridasTblCollection(Collection<TmsCorridasTbl> tmsCorridasTblCollection) {
        this.tmsCorridasTblCollection = tmsCorridasTblCollection;
    }

    /**
     * Gets the tmsCorridasTblCollection1 of this TmsOperadoresTbl.
     * @return the tmsCorridasTblCollection1
     */
    public Collection<TmsCorridasTbl> getTmsCorridasTblCollection1() {
        return this.tmsCorridasTblCollection1;
    }

    /**
     * Sets the tmsCorridasTblCollection1 of this TmsOperadoresTbl to the specified value.
     * @param tmsCorridasTblCollection1 the new tmsCorridasTblCollection1
     */
    public void setTmsCorridasTblCollection1(Collection<TmsCorridasTbl> tmsCorridasTblCollection1) {
        this.tmsCorridasTblCollection1 = tmsCorridasTblCollection1;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.operadorId != null ? this.operadorId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsOperadoresTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsOperadoresTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsOperadoresTbl)) {
            return false;
        }
        TmsOperadoresTbl other = (TmsOperadoresTbl)object;
        if (this.operadorId != other.operadorId && (this.operadorId == null || !this.operadorId.equals(other.operadorId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmspuesrtas.entidad.TmsOperadoresTbl[operadorId=" + operadorId + "]";
    }
    
}
