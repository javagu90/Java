/*
 * TmsRecaudacionTbl.java
 *
 * Created on 10 de septiembre de 2007, 08:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package TmsRecaudacion.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsRecaudacionTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_RECAUDACION_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsRecaudacionTbl.findByRecaudacionId", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.recaudacionId = :recaudacionId"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByTarjetaViajeId", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.tarjetaViajeId = :tarjetaViajeId"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByRecaudacionReferencia", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.recaudacionReferencia = :recaudacionReferencia"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByImporteVentaSistema", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.importeVentaSistema = :importeVentaSistema"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByBoletosVentaSistema", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.boletosVentaSistema = :boletosVentaSistema"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByImporteVentaAbordo", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.importeVentaAbordo = :importeVentaAbordo"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByBoletosVentaAbordo", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.boletosVentaAbordo = :boletosVentaAbordo"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByImporteVentaManual", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.importeVentaManual = :importeVentaManual"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByBoletosVentaManual", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.boletosVentaManual = :boletosVentaManual"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByFechaHoraRecaudacion", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.fechaHoraRecaudacion = :fechaHoraRecaudacion"),
        @NamedQuery(name = "TmsRecaudacionTbl.findBySueldoOperador", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.sueldoOperador = :sueldoOperador"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByRetencion", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.retencion = :retencion"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByAnticipos", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.anticipos = :anticipos"),
        @NamedQuery(name = "TmsRecaudacionTbl.findBySaldo", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.saldo = :saldo"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByEstadoRecaudacion", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.estadoRecaudacion = :estadoRecaudacion"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByRecaudadorId", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.recaudadorId = :recaudadorId"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByAutorizadoPor", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.autorizadoPor = :autorizadoPor"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByCiudadRecaudacionId", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.ciudadRecaudacionId = :ciudadRecaudacionId"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByAdicional1", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByAdicional2", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByAdicional3", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByAdicional4", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByAdicional5", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByAdicional6", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.adicional6 = :adicional6"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByAdicional7", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.adicional7 = :adicional7"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByAdicional8", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.adicional8 = :adicional8"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByAdicional9", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.adicional9 = :adicional9"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByAdicional10", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.adicional10 = :adicional10"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByCreadoPor", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByFechaCreacion", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsRecaudacionTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsRecaudacionTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion")
    })
public class TmsRecaudacionTbl implements Serializable {

    @SequenceGenerator(name="TMS_REC_SEQ",sequenceName="TMS_RECAUDACION_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMS_REC_SEQ")
    @Id
    @Column(name = "RECAUDACION_ID", nullable = false)
    private BigDecimal recaudacionId;

    @Column(name = "TARJETA_VIAJE_ID", nullable = false)
    private BigInteger tarjetaViajeId;

    @Column(name = "RECAUDACION_REFERENCIA")
    private BigInteger recaudacionReferencia;

    @Column(name = "IMPORTE_VENTA_SISTEMA")
    private BigDecimal importeVentaSistema;

    @Column(name = "BOLETOS_VENTA_SISTEMA")
    private BigInteger boletosVentaSistema;

    @Column(name = "IMPORTE_VENTA_ABORDO")
    private BigDecimal importeVentaAbordo;

    @Column(name = "BOLETOS_VENTA_ABORDO")
    private BigInteger boletosVentaAbordo;

    @Column(name = "IMPORTE_VENTA_MANUAL")
    private BigDecimal importeVentaManual;

    @Column(name = "BOLETOS_VENTA_MANUAL")
    private BigInteger boletosVentaManual;

    @Column(name = "FECHA_HORA_RECAUDACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRecaudacion;

    @Column(name = "SUELDO_OPERADOR")
    private BigDecimal sueldoOperador;

    @Column(name = "RETENCION")
    private BigDecimal retencion;

    @Column(name = "ANTICIPOS")
    private BigDecimal anticipos;

    @Column(name = "SALDO")
    private BigDecimal saldo;

    @Column(name = "ESTADO_RECAUDACION", nullable = false)
    private String estadoRecaudacion;

    @Column(name = "RECAUDADOR_ID", nullable = false)
    private BigInteger recaudadorId;

    @Column(name = "AUTORIZADO_POR")
    private BigInteger autorizadoPor;

    @Column(name = "CIUDAD_RECAUDACION_ID", nullable = false)
    private BigInteger ciudadRecaudacionId;

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR", nullable = false)
    private BigInteger ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaFechaActualizacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recaudacionId")
    private Collection<TmsRecaudacionLineasTbl> tmsRecaudacionLineasTblCollection;

    @JoinColumn(name = "CORTE_ID", referencedColumnName = "CORTE_ID")
    @ManyToOne
    private TmsCortesTbl corteId;
    
    /** Creates a new instance of TmsRecaudacionTbl */
    public TmsRecaudacionTbl() {
    }

    /**
     * Creates a new instance of TmsRecaudacionTbl with the specified values.
     * @param recaudacionId the recaudacionId of the TmsRecaudacionTbl
     */
    public TmsRecaudacionTbl(BigDecimal recaudacionId) {
        this.recaudacionId = recaudacionId;
    }

    /**
     * Creates a new instance of TmsRecaudacionTbl with the specified values.
     * @param recaudacionId the recaudacionId of the TmsRecaudacionTbl
     * @param tarjetaViajeId the tarjetaViajeId of the TmsRecaudacionTbl
     * @param fechaHoraRecaudacion the fechaHoraRecaudacion of the TmsRecaudacionTbl
     * @param estadoRecaudacion the estadoRecaudacion of the TmsRecaudacionTbl
     * @param recaudadorId the recaudadorId of the TmsRecaudacionTbl
     * @param ciudadRecaudacionId the ciudadRecaudacionId of the TmsRecaudacionTbl
     * @param creadoPor the creadoPor of the TmsRecaudacionTbl
     * @param fechaCreacion the fechaCreacion of the TmsRecaudacionTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsRecaudacionTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsRecaudacionTbl
     */
    public TmsRecaudacionTbl(BigDecimal recaudacionId, BigInteger tarjetaViajeId, Date fechaHoraRecaudacion, String estadoRecaudacion, BigInteger recaudadorId, BigInteger ciudadRecaudacionId, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.recaudacionId = recaudacionId;
        this.tarjetaViajeId = tarjetaViajeId;
        this.fechaHoraRecaudacion = fechaHoraRecaudacion;
        this.estadoRecaudacion = estadoRecaudacion;
        this.recaudadorId = recaudadorId;
        this.ciudadRecaudacionId = ciudadRecaudacionId;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the recaudacionId of this TmsRecaudacionTbl.
     * @return the recaudacionId
     */
    public BigDecimal getRecaudacionId() {
        return this.recaudacionId;
    }

    /**
     * Sets the recaudacionId of this TmsRecaudacionTbl to the specified value.
     * @param recaudacionId the new recaudacionId
     */
    public void setRecaudacionId(BigDecimal recaudacionId) {
        this.recaudacionId = recaudacionId;
    }

    /**
     * Gets the tarjetaViajeId of this TmsRecaudacionTbl.
     * @return the tarjetaViajeId
     */
    public BigInteger getTarjetaViajeId() {
        return this.tarjetaViajeId;
    }

    /**
     * Sets the tarjetaViajeId of this TmsRecaudacionTbl to the specified value.
     * @param tarjetaViajeId the new tarjetaViajeId
     */
    public void setTarjetaViajeId(BigInteger tarjetaViajeId) {
        this.tarjetaViajeId = tarjetaViajeId;
    }

    /**
     * Gets the recaudacionReferencia of this TmsRecaudacionTbl.
     * @return the recaudacionReferencia
     */
    public BigInteger getRecaudacionReferencia() {
        return this.recaudacionReferencia;
    }

    /**
     * Sets the recaudacionReferencia of this TmsRecaudacionTbl to the specified value.
     * @param recaudacionReferencia the new recaudacionReferencia
     */
    public void setRecaudacionReferencia(BigInteger recaudacionReferencia) {
        this.recaudacionReferencia = recaudacionReferencia;
    }

    /**
     * Gets the importeVentaSistema of this TmsRecaudacionTbl.
     * @return the importeVentaSistema
     */
    public BigDecimal getImporteVentaSistema() {
        return this.importeVentaSistema;
    }

    /**
     * Sets the importeVentaSistema of this TmsRecaudacionTbl to the specified value.
     * @param importeVentaSistema the new importeVentaSistema
     */
    public void setImporteVentaSistema(BigDecimal importeVentaSistema) {
        this.importeVentaSistema = importeVentaSistema;
    }

    /**
     * Gets the boletosVentaSistema of this TmsRecaudacionTbl.
     * @return the boletosVentaSistema
     */
    public BigInteger getBoletosVentaSistema() {
        return this.boletosVentaSistema;
    }

    /**
     * Sets the boletosVentaSistema of this TmsRecaudacionTbl to the specified value.
     * @param boletosVentaSistema the new boletosVentaSistema
     */
    public void setBoletosVentaSistema(BigInteger boletosVentaSistema) {
        this.boletosVentaSistema = boletosVentaSistema;
    }

    /**
     * Gets the importeVentaAbordo of this TmsRecaudacionTbl.
     * @return the importeVentaAbordo
     */
    public BigDecimal getImporteVentaAbordo() {
        return this.importeVentaAbordo;
    }

    /**
     * Sets the importeVentaAbordo of this TmsRecaudacionTbl to the specified value.
     * @param importeVentaAbordo the new importeVentaAbordo
     */
    public void setImporteVentaAbordo(BigDecimal importeVentaAbordo) {
        this.importeVentaAbordo = importeVentaAbordo;
    }

    /**
     * Gets the boletosVentaAbordo of this TmsRecaudacionTbl.
     * @return the boletosVentaAbordo
     */
    public BigInteger getBoletosVentaAbordo() {
        return this.boletosVentaAbordo;
    }

    /**
     * Sets the boletosVentaAbordo of this TmsRecaudacionTbl to the specified value.
     * @param boletosVentaAbordo the new boletosVentaAbordo
     */
    public void setBoletosVentaAbordo(BigInteger boletosVentaAbordo) {
        this.boletosVentaAbordo = boletosVentaAbordo;
    }

    /**
     * Gets the importeVentaManual of this TmsRecaudacionTbl.
     * @return the importeVentaManual
     */
    public BigDecimal getImporteVentaManual() {
        return this.importeVentaManual;
    }

    /**
     * Sets the importeVentaManual of this TmsRecaudacionTbl to the specified value.
     * @param importeVentaManual the new importeVentaManual
     */
    public void setImporteVentaManual(BigDecimal importeVentaManual) {
        this.importeVentaManual = importeVentaManual;
    }

    /**
     * Gets the boletosVentaManual of this TmsRecaudacionTbl.
     * @return the boletosVentaManual
     */
    public BigInteger getBoletosVentaManual() {
        return this.boletosVentaManual;
    }

    /**
     * Sets the boletosVentaManual of this TmsRecaudacionTbl to the specified value.
     * @param boletosVentaManual the new boletosVentaManual
     */
    public void setBoletosVentaManual(BigInteger boletosVentaManual) {
        this.boletosVentaManual = boletosVentaManual;
    }

    /**
     * Gets the fechaHoraRecaudacion of this TmsRecaudacionTbl.
     * @return the fechaHoraRecaudacion
     */
    public Date getFechaHoraRecaudacion() {
        return this.fechaHoraRecaudacion;
    }

    /**
     * Sets the fechaHoraRecaudacion of this TmsRecaudacionTbl to the specified value.
     * @param fechaHoraRecaudacion the new fechaHoraRecaudacion
     */
    public void setFechaHoraRecaudacion(Date fechaHoraRecaudacion) {
        this.fechaHoraRecaudacion = fechaHoraRecaudacion;
    }

    /**
     * Gets the sueldoOperador of this TmsRecaudacionTbl.
     * @return the sueldoOperador
     */
    public BigDecimal getSueldoOperador() {
        return this.sueldoOperador;
    }

    /**
     * Sets the sueldoOperador of this TmsRecaudacionTbl to the specified value.
     * @param sueldoOperador the new sueldoOperador
     */
    public void setSueldoOperador(BigDecimal sueldoOperador) {
        this.sueldoOperador = sueldoOperador;
    }

    /**
     * Gets the retencion of this TmsRecaudacionTbl.
     * @return the retencion
     */
    public BigDecimal getRetencion() {
        return this.retencion;
    }

    /**
     * Sets the retencion of this TmsRecaudacionTbl to the specified value.
     * @param retencion the new retencion
     */
    public void setRetencion(BigDecimal retencion) {
        this.retencion = retencion;
    }

    /**
     * Gets the anticipos of this TmsRecaudacionTbl.
     * @return the anticipos
     */
    public BigDecimal getAnticipos() {
        return this.anticipos;
    }

    /**
     * Sets the anticipos of this TmsRecaudacionTbl to the specified value.
     * @param anticipos the new anticipos
     */
    public void setAnticipos(BigDecimal anticipos) {
        this.anticipos = anticipos;
    }

    /**
     * Gets the saldo of this TmsRecaudacionTbl.
     * @return the saldo
     */
    public BigDecimal getSaldo() {
        return this.saldo;
    }

    /**
     * Sets the saldo of this TmsRecaudacionTbl to the specified value.
     * @param saldo the new saldo
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    /**
     * Gets the estadoRecaudacion of this TmsRecaudacionTbl.
     * @return the estadoRecaudacion
     */
    public String getEstadoRecaudacion() {
        return this.estadoRecaudacion;
    }

    /**
     * Sets the estadoRecaudacion of this TmsRecaudacionTbl to the specified value.
     * @param estadoRecaudacion the new estadoRecaudacion
     */
    public void setEstadoRecaudacion(String estadoRecaudacion) {
        this.estadoRecaudacion = estadoRecaudacion;
    }

    /**
     * Gets the recaudadorId of this TmsRecaudacionTbl.
     * @return the recaudadorId
     */
    public BigInteger getRecaudadorId() {
        return this.recaudadorId;
    }

    /**
     * Sets the recaudadorId of this TmsRecaudacionTbl to the specified value.
     * @param recaudadorId the new recaudadorId
     */
    public void setRecaudadorId(BigInteger recaudadorId) {
        this.recaudadorId = recaudadorId;
    }

    /**
     * Gets the autorizadoPor of this TmsRecaudacionTbl.
     * @return the autorizadoPor
     */
    public BigInteger getAutorizadoPor() {
        return this.autorizadoPor;
    }

    /**
     * Sets the autorizadoPor of this TmsRecaudacionTbl to the specified value.
     * @param autorizadoPor the new autorizadoPor
     */
    public void setAutorizadoPor(BigInteger autorizadoPor) {
        this.autorizadoPor = autorizadoPor;
    }

    /**
     * Gets the ciudadRecaudacionId of this TmsRecaudacionTbl.
     * @return the ciudadRecaudacionId
     */
    public BigInteger getCiudadRecaudacionId() {
        return this.ciudadRecaudacionId;
    }

    /**
     * Sets the ciudadRecaudacionId of this TmsRecaudacionTbl to the specified value.
     * @param ciudadRecaudacionId the new ciudadRecaudacionId
     */
    public void setCiudadRecaudacionId(BigInteger ciudadRecaudacionId) {
        this.ciudadRecaudacionId = ciudadRecaudacionId;
    }

    /**
     * Gets the adicional1 of this TmsRecaudacionTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsRecaudacionTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsRecaudacionTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsRecaudacionTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsRecaudacionTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsRecaudacionTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsRecaudacionTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsRecaudacionTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsRecaudacionTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsRecaudacionTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the adicional6 of this TmsRecaudacionTbl.
     * @return the adicional6
     */
    public String getAdicional6() {
        return this.adicional6;
    }

    /**
     * Sets the adicional6 of this TmsRecaudacionTbl to the specified value.
     * @param adicional6 the new adicional6
     */
    public void setAdicional6(String adicional6) {
        this.adicional6 = adicional6;
    }

    /**
     * Gets the adicional7 of this TmsRecaudacionTbl.
     * @return the adicional7
     */
    public String getAdicional7() {
        return this.adicional7;
    }

    /**
     * Sets the adicional7 of this TmsRecaudacionTbl to the specified value.
     * @param adicional7 the new adicional7
     */
    public void setAdicional7(String adicional7) {
        this.adicional7 = adicional7;
    }

    /**
     * Gets the adicional8 of this TmsRecaudacionTbl.
     * @return the adicional8
     */
    public String getAdicional8() {
        return this.adicional8;
    }

    /**
     * Sets the adicional8 of this TmsRecaudacionTbl to the specified value.
     * @param adicional8 the new adicional8
     */
    public void setAdicional8(String adicional8) {
        this.adicional8 = adicional8;
    }

    /**
     * Gets the adicional9 of this TmsRecaudacionTbl.
     * @return the adicional9
     */
    public String getAdicional9() {
        return this.adicional9;
    }

    /**
     * Sets the adicional9 of this TmsRecaudacionTbl to the specified value.
     * @param adicional9 the new adicional9
     */
    public void setAdicional9(String adicional9) {
        this.adicional9 = adicional9;
    }

    /**
     * Gets the adicional10 of this TmsRecaudacionTbl.
     * @return the adicional10
     */
    public String getAdicional10() {
        return this.adicional10;
    }

    /**
     * Sets the adicional10 of this TmsRecaudacionTbl to the specified value.
     * @param adicional10 the new adicional10
     */
    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
    }

    /**
     * Gets the creadoPor of this TmsRecaudacionTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsRecaudacionTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsRecaudacionTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsRecaudacionTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsRecaudacionTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsRecaudacionTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsRecaudacionTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsRecaudacionTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the tmsRecaudacionLineasTblCollection of this TmsRecaudacionTbl.
     * @return the tmsRecaudacionLineasTblCollection
     */
    public Collection<TmsRecaudacionLineasTbl> getTmsRecaudacionLineasTblCollection() {
        return this.tmsRecaudacionLineasTblCollection;
    }

    /**
     * Sets the tmsRecaudacionLineasTblCollection of this TmsRecaudacionTbl to the specified value.
     * @param tmsRecaudacionLineasTblCollection the new tmsRecaudacionLineasTblCollection
     */
    public void setTmsRecaudacionLineasTblCollection(Collection<TmsRecaudacionLineasTbl> tmsRecaudacionLineasTblCollection) {
        this.tmsRecaudacionLineasTblCollection = tmsRecaudacionLineasTblCollection;
    }

    /**
     * Gets the corteId of this TmsRecaudacionTbl.
     * @return the corteId
     */
    public TmsCortesTbl getCorteId() {
        return this.corteId;
    }

    /**
     * Sets the corteId of this TmsRecaudacionTbl to the specified value.
     * @param corteId the new corteId
     */
    public void setCorteId(TmsCortesTbl corteId) {
        this.corteId = corteId;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.recaudacionId != null ? this.recaudacionId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsRecaudacionTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsRecaudacionTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsRecaudacionTbl)) {
            return false;
        }
        TmsRecaudacionTbl other = (TmsRecaudacionTbl)object;
        if (this.recaudacionId != other.recaudacionId && (this.recaudacionId == null || !this.recaudacionId.equals(other.recaudacionId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "TmsRecaudacion.entidad.TmsRecaudacionTbl[recaudacionId=" + recaudacionId + "]";
    }
    
}
