/*
 * TmsPagosActAdicionalesTbl.java
 *
 * Created on 2 de octubre de 2007, 07:50 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsPagosActAdicionalesTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_PAGOS_ACT_ADICIONALES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByPagoActividadAdicionalId", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.pagoActividadAdicionalId = :pagoActividadAdicionalId"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByReferenciaPagoActAdicional", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.referenciaPagoActAdicional = :referenciaPagoActAdicional and t.estadoPago = 'R'"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByFechaHoraRecaudacion", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.fechaHoraRecaudacion = :fechaHoraRecaudacion"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByOperadorId", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.operadorId = :operadorId and t.estadoPago = 'P'"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByMontoPago", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.montoPago = :montoPago"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByRetencion", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.retencion = :retencion"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByEstadoPago", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.estadoPago = :estadoPago"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByRecaudadorId", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.recaudadorId = :recaudadorId"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByComentarios", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.comentarios = :comentarios"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAutorizadoPor", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.autorizadoPor = :autorizadoPor"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByCiudadRecaudacionId", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.ciudadRecaudacionId = :ciudadRecaudacionId"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByCreadoPor", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByFechaCreacion", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional1", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional2", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional3", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional4", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional5", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional6", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional6 = :adicional6"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional7", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional7 = :adicional7"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional8", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional8 = :adicional8"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional9", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional9 = :adicional9"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional10", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional10 = :adicional10"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional11", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional11 = :adicional11"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional12", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional12 = :adicional12"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional13", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional13 = :adicional13"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional14", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional14 = :adicional14"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional15", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional15 = :adicional15"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional16", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional16 = :adicional16"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional17", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional17 = :adicional17"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional18", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional18 = :adicional18"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional19", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional19 = :adicional19"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional20", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional20 = :adicional20"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional21", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional21 = :adicional21"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional22", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional22 = :adicional22"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional23", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional23 = :adicional23"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional24", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional24 = :adicional24"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional25", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional25 = :adicional25"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional26", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional26 = :adicional26"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional27", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional27 = :adicional27"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional28", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional28 = :adicional28"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional29", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional29 = :adicional29"),
        @NamedQuery(name = "TmsPagosActAdicionalesTbl.findByAdicional30", query = "SELECT t FROM TmsPagosActAdicionalesTbl t WHERE t.adicional30 = :adicional30")
    })
public class TmsPagosActAdicionalesTbl implements Serializable {

    @SequenceGenerator(name="TMS_PAD_SEQ",sequenceName="TMS_PAGOS_ACT_ADIC_SEQ",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TMS_PAD_SEQ")
    @Id
    @Column(name = "PAGO_ACTIVIDAD_ADICIONAL_ID", nullable = false)
    private BigDecimal pagoActividadAdicionalId;

    @Column(name = "REFERENCIA_PAGO_ACT_ADICIONAL", nullable = false)
    private BigInteger referenciaPagoActAdicional;

    @Column(name = "FECHA_HORA_RECAUDACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraRecaudacion;

    @Column(name = "OPERADOR_ID", nullable = false)
    private BigInteger operadorId;

    @Column(name = "MONTO_PAGO")
    private BigDecimal montoPago;

    @Column(name = "RETENCION")
    private BigDecimal retencion;

    @Column(name = "ESTADO_PAGO", nullable = false)
    private String estadoPago;

    @Column(name = "RECAUDADOR_ID", nullable = false)
    private BigInteger recaudadorId;

    @Column(name = "COMENTARIOS")
    private String comentarios;

    @Column(name = "AUTORIZADO_POR")
    private BigInteger autorizadoPor;

    @Column(name = "CIUDAD_RECAUDACION_ID", nullable = false)
    private BigInteger ciudadRecaudacionId;

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

    @Column(name = "ADICIONAL11")
    private String adicional11;

    @Column(name = "ADICIONAL12")
    private String adicional12;

    @Column(name = "ADICIONAL13")
    private String adicional13;

    @Column(name = "ADICIONAL14")
    private String adicional14;

    @Column(name = "ADICIONAL15")
    private String adicional15;

    @Column(name = "ADICIONAL16")
    private String adicional16;

    @Column(name = "ADICIONAL17")
    private String adicional17;

    @Column(name = "ADICIONAL18")
    private String adicional18;

    @Column(name = "ADICIONAL19")
    private String adicional19;

    @Column(name = "ADICIONAL20")
    private String adicional20;

    @Column(name = "ADICIONAL21")
    private String adicional21;

    @Column(name = "ADICIONAL22")
    private String adicional22;

    @Column(name = "ADICIONAL23")
    private String adicional23;

    @Column(name = "ADICIONAL24")
    private String adicional24;

    @Column(name = "ADICIONAL25")
    private String adicional25;

    @Column(name = "ADICIONAL26")
    private String adicional26;

    @Column(name = "ADICIONAL27")
    private String adicional27;

    @Column(name = "ADICIONAL28")
    private String adicional28;

    @Column(name = "ADICIONAL29")
    private String adicional29;

    @Column(name = "ADICIONAL30")
    private String adicional30;

    @JoinColumn(name = "TIPO_ACTIVIDAD_ADICIONAL_ID", referencedColumnName = "TIPO_ACTIVIDAD_ADICIONAL_ID")
    @ManyToOne
    private TmsActAdicionalesTbl tipoActividadAdicionalId;

    @JoinColumn(name = "SERVICIO_ID", referencedColumnName = "SERVICIO_ID")
    @ManyToOne
    private TmsServiciosTbl servicioId;
    
    /** Creates a new instance of TmsPagosActAdicionalesTbl */
    public TmsPagosActAdicionalesTbl() {
    }

    /**
     * Creates a new instance of TmsPagosActAdicionalesTbl with the specified values.
     * @param pagoActividadAdicionalId the pagoActividadAdicionalId of the TmsPagosActAdicionalesTbl
     */
    public TmsPagosActAdicionalesTbl(BigDecimal pagoActividadAdicionalId) {
        this.pagoActividadAdicionalId = pagoActividadAdicionalId;
    }

    /**
     * Creates a new instance of TmsPagosActAdicionalesTbl with the specified values.
     * @param pagoActividadAdicionalId the pagoActividadAdicionalId of the TmsPagosActAdicionalesTbl
     * @param referenciaPagoActAdicional the referenciaPagoActAdicional of the TmsPagosActAdicionalesTbl
     * @param fechaHoraRecaudacion the fechaHoraRecaudacion of the TmsPagosActAdicionalesTbl
     * @param operadorId the operadorId of the TmsPagosActAdicionalesTbl
     * @param estadoPago the estadoPago of the TmsPagosActAdicionalesTbl
     * @param recaudadorId the recaudadorId of the TmsPagosActAdicionalesTbl
     * @param ciudadRecaudacionId the ciudadRecaudacionId of the TmsPagosActAdicionalesTbl
     * @param creadoPor the creadoPor of the TmsPagosActAdicionalesTbl
     * @param fechaCreacion the fechaCreacion of the TmsPagosActAdicionalesTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsPagosActAdicionalesTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsPagosActAdicionalesTbl
     */
    public TmsPagosActAdicionalesTbl(BigDecimal pagoActividadAdicionalId, BigInteger referenciaPagoActAdicional, Date fechaHoraRecaudacion, BigInteger operadorId, String estadoPago, BigInteger recaudadorId, BigInteger ciudadRecaudacionId, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.pagoActividadAdicionalId = pagoActividadAdicionalId;
        this.referenciaPagoActAdicional = referenciaPagoActAdicional;
        this.fechaHoraRecaudacion = fechaHoraRecaudacion;
        this.operadorId = operadorId;
        this.estadoPago = estadoPago;
        this.recaudadorId = recaudadorId;
        this.ciudadRecaudacionId = ciudadRecaudacionId;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the pagoActividadAdicionalId of this TmsPagosActAdicionalesTbl.
     * @return the pagoActividadAdicionalId
     */
    public BigDecimal getPagoActividadAdicionalId() {
        return this.pagoActividadAdicionalId;
    }

    /**
     * Sets the pagoActividadAdicionalId of this TmsPagosActAdicionalesTbl to the specified value.
     * @param pagoActividadAdicionalId the new pagoActividadAdicionalId
     */
    public void setPagoActividadAdicionalId(BigDecimal pagoActividadAdicionalId) {
        this.pagoActividadAdicionalId = pagoActividadAdicionalId;
    }

    /**
     * Gets the referenciaPagoActAdicional of this TmsPagosActAdicionalesTbl.
     * @return the referenciaPagoActAdicional
     */
    public BigInteger getReferenciaPagoActAdicional() {
        return this.referenciaPagoActAdicional;
    }

    /**
     * Sets the referenciaPagoActAdicional of this TmsPagosActAdicionalesTbl to the specified value.
     * @param referenciaPagoActAdicional the new referenciaPagoActAdicional
     */
    public void setReferenciaPagoActAdicional(BigInteger referenciaPagoActAdicional) {
        this.referenciaPagoActAdicional = referenciaPagoActAdicional;
    }

    /**
     * Gets the fechaHoraRecaudacion of this TmsPagosActAdicionalesTbl.
     * @return the fechaHoraRecaudacion
     */
    public Date getFechaHoraRecaudacion() {
        return this.fechaHoraRecaudacion;
    }

    /**
     * Sets the fechaHoraRecaudacion of this TmsPagosActAdicionalesTbl to the specified value.
     * @param fechaHoraRecaudacion the new fechaHoraRecaudacion
     */
    public void setFechaHoraRecaudacion(Date fechaHoraRecaudacion) {
        this.fechaHoraRecaudacion = fechaHoraRecaudacion;
    }

    /**
     * Gets the operadorId of this TmsPagosActAdicionalesTbl.
     * @return the operadorId
     */
    public BigInteger getOperadorId() {
        return this.operadorId;
    }

    /**
     * Sets the operadorId of this TmsPagosActAdicionalesTbl to the specified value.
     * @param operadorId the new operadorId
     */
    public void setOperadorId(BigInteger operadorId) {
        this.operadorId = operadorId;
    }

    /**
     * Gets the montoPago of this TmsPagosActAdicionalesTbl.
     * @return the montoPago
     */
    public BigDecimal getMontoPago() {
        return this.montoPago;
    }

    /**
     * Sets the montoPago of this TmsPagosActAdicionalesTbl to the specified value.
     * @param montoPago the new montoPago
     */
    public void setMontoPago(BigDecimal montoPago) {
        this.montoPago = montoPago;
    }

    /**
     * Gets the retencion of this TmsPagosActAdicionalesTbl.
     * @return the retencion
     */
    public BigDecimal getRetencion() {
        return this.retencion;
    }

    /**
     * Sets the retencion of this TmsPagosActAdicionalesTbl to the specified value.
     * @param retencion the new retencion
     */
    public void setRetencion(BigDecimal retencion) {
        this.retencion = retencion;
    }

    /**
     * Gets the estadoPago of this TmsPagosActAdicionalesTbl.
     * @return the estadoPago
     */
    public String getEstadoPago() {
        return this.estadoPago;
    }

    /**
     * Sets the estadoPago of this TmsPagosActAdicionalesTbl to the specified value.
     * @param estadoPago the new estadoPago
     */
    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    /**
     * Gets the recaudadorId of this TmsPagosActAdicionalesTbl.
     * @return the recaudadorId
     */
    public BigInteger getRecaudadorId() {
        return this.recaudadorId;
    }

    /**
     * Sets the recaudadorId of this TmsPagosActAdicionalesTbl to the specified value.
     * @param recaudadorId the new recaudadorId
     */
    public void setRecaudadorId(BigInteger recaudadorId) {
        this.recaudadorId = recaudadorId;
    }

    /**
     * Gets the comentarios of this TmsPagosActAdicionalesTbl.
     * @return the comentarios
     */
    public String getComentarios() {
        return this.comentarios;
    }

    /**
     * Sets the comentarios of this TmsPagosActAdicionalesTbl to the specified value.
     * @param comentarios the new comentarios
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * Gets the autorizadoPor of this TmsPagosActAdicionalesTbl.
     * @return the autorizadoPor
     */
    public BigInteger getAutorizadoPor() {
        return this.autorizadoPor;
    }

    /**
     * Sets the autorizadoPor of this TmsPagosActAdicionalesTbl to the specified value.
     * @param autorizadoPor the new autorizadoPor
     */
    public void setAutorizadoPor(BigInteger autorizadoPor) {
        this.autorizadoPor = autorizadoPor;
    }

    /**
     * Gets the ciudadRecaudacionId of this TmsPagosActAdicionalesTbl.
     * @return the ciudadRecaudacionId
     */
    public BigInteger getCiudadRecaudacionId() {
        return this.ciudadRecaudacionId;
    }

    /**
     * Sets the ciudadRecaudacionId of this TmsPagosActAdicionalesTbl to the specified value.
     * @param ciudadRecaudacionId the new ciudadRecaudacionId
     */
    public void setCiudadRecaudacionId(BigInteger ciudadRecaudacionId) {
        this.ciudadRecaudacionId = ciudadRecaudacionId;
    }

    /**
     * Gets the creadoPor of this TmsPagosActAdicionalesTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsPagosActAdicionalesTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsPagosActAdicionalesTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsPagosActAdicionalesTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsPagosActAdicionalesTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsPagosActAdicionalesTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsPagosActAdicionalesTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsPagosActAdicionalesTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the adicional1 of this TmsPagosActAdicionalesTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsPagosActAdicionalesTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsPagosActAdicionalesTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsPagosActAdicionalesTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsPagosActAdicionalesTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the adicional6 of this TmsPagosActAdicionalesTbl.
     * @return the adicional6
     */
    public String getAdicional6() {
        return this.adicional6;
    }

    /**
     * Sets the adicional6 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional6 the new adicional6
     */
    public void setAdicional6(String adicional6) {
        this.adicional6 = adicional6;
    }

    /**
     * Gets the adicional7 of this TmsPagosActAdicionalesTbl.
     * @return the adicional7
     */
    public String getAdicional7() {
        return this.adicional7;
    }

    /**
     * Sets the adicional7 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional7 the new adicional7
     */
    public void setAdicional7(String adicional7) {
        this.adicional7 = adicional7;
    }

    /**
     * Gets the adicional8 of this TmsPagosActAdicionalesTbl.
     * @return the adicional8
     */
    public String getAdicional8() {
        return this.adicional8;
    }

    /**
     * Sets the adicional8 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional8 the new adicional8
     */
    public void setAdicional8(String adicional8) {
        this.adicional8 = adicional8;
    }

    /**
     * Gets the adicional9 of this TmsPagosActAdicionalesTbl.
     * @return the adicional9
     */
    public String getAdicional9() {
        return this.adicional9;
    }

    /**
     * Sets the adicional9 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional9 the new adicional9
     */
    public void setAdicional9(String adicional9) {
        this.adicional9 = adicional9;
    }

    /**
     * Gets the adicional10 of this TmsPagosActAdicionalesTbl.
     * @return the adicional10
     */
    public String getAdicional10() {
        return this.adicional10;
    }

    /**
     * Sets the adicional10 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional10 the new adicional10
     */
    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
    }

    /**
     * Gets the adicional11 of this TmsPagosActAdicionalesTbl.
     * @return the adicional11
     */
    public String getAdicional11() {
        return this.adicional11;
    }

    /**
     * Sets the adicional11 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional11 the new adicional11
     */
    public void setAdicional11(String adicional11) {
        this.adicional11 = adicional11;
    }

    /**
     * Gets the adicional12 of this TmsPagosActAdicionalesTbl.
     * @return the adicional12
     */
    public String getAdicional12() {
        return this.adicional12;
    }

    /**
     * Sets the adicional12 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional12 the new adicional12
     */
    public void setAdicional12(String adicional12) {
        this.adicional12 = adicional12;
    }

    /**
     * Gets the adicional13 of this TmsPagosActAdicionalesTbl.
     * @return the adicional13
     */
    public String getAdicional13() {
        return this.adicional13;
    }

    /**
     * Sets the adicional13 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional13 the new adicional13
     */
    public void setAdicional13(String adicional13) {
        this.adicional13 = adicional13;
    }

    /**
     * Gets the adicional14 of this TmsPagosActAdicionalesTbl.
     * @return the adicional14
     */
    public String getAdicional14() {
        return this.adicional14;
    }

    /**
     * Sets the adicional14 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional14 the new adicional14
     */
    public void setAdicional14(String adicional14) {
        this.adicional14 = adicional14;
    }

    /**
     * Gets the adicional15 of this TmsPagosActAdicionalesTbl.
     * @return the adicional15
     */
    public String getAdicional15() {
        return this.adicional15;
    }

    /**
     * Sets the adicional15 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional15 the new adicional15
     */
    public void setAdicional15(String adicional15) {
        this.adicional15 = adicional15;
    }

    /**
     * Gets the adicional16 of this TmsPagosActAdicionalesTbl.
     * @return the adicional16
     */
    public String getAdicional16() {
        return this.adicional16;
    }

    /**
     * Sets the adicional16 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional16 the new adicional16
     */
    public void setAdicional16(String adicional16) {
        this.adicional16 = adicional16;
    }

    /**
     * Gets the adicional17 of this TmsPagosActAdicionalesTbl.
     * @return the adicional17
     */
    public String getAdicional17() {
        return this.adicional17;
    }

    /**
     * Sets the adicional17 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional17 the new adicional17
     */
    public void setAdicional17(String adicional17) {
        this.adicional17 = adicional17;
    }

    /**
     * Gets the adicional18 of this TmsPagosActAdicionalesTbl.
     * @return the adicional18
     */
    public String getAdicional18() {
        return this.adicional18;
    }

    /**
     * Sets the adicional18 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional18 the new adicional18
     */
    public void setAdicional18(String adicional18) {
        this.adicional18 = adicional18;
    }

    /**
     * Gets the adicional19 of this TmsPagosActAdicionalesTbl.
     * @return the adicional19
     */
    public String getAdicional19() {
        return this.adicional19;
    }

    /**
     * Sets the adicional19 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional19 the new adicional19
     */
    public void setAdicional19(String adicional19) {
        this.adicional19 = adicional19;
    }

    /**
     * Gets the adicional20 of this TmsPagosActAdicionalesTbl.
     * @return the adicional20
     */
    public String getAdicional20() {
        return this.adicional20;
    }

    /**
     * Sets the adicional20 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional20 the new adicional20
     */
    public void setAdicional20(String adicional20) {
        this.adicional20 = adicional20;
    }

    /**
     * Gets the adicional21 of this TmsPagosActAdicionalesTbl.
     * @return the adicional21
     */
    public String getAdicional21() {
        return this.adicional21;
    }

    /**
     * Sets the adicional21 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional21 the new adicional21
     */
    public void setAdicional21(String adicional21) {
        this.adicional21 = adicional21;
    }

    /**
     * Gets the adicional22 of this TmsPagosActAdicionalesTbl.
     * @return the adicional22
     */
    public String getAdicional22() {
        return this.adicional22;
    }

    /**
     * Sets the adicional22 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional22 the new adicional22
     */
    public void setAdicional22(String adicional22) {
        this.adicional22 = adicional22;
    }

    /**
     * Gets the adicional23 of this TmsPagosActAdicionalesTbl.
     * @return the adicional23
     */
    public String getAdicional23() {
        return this.adicional23;
    }

    /**
     * Sets the adicional23 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional23 the new adicional23
     */
    public void setAdicional23(String adicional23) {
        this.adicional23 = adicional23;
    }

    /**
     * Gets the adicional24 of this TmsPagosActAdicionalesTbl.
     * @return the adicional24
     */
    public String getAdicional24() {
        return this.adicional24;
    }

    /**
     * Sets the adicional24 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional24 the new adicional24
     */
    public void setAdicional24(String adicional24) {
        this.adicional24 = adicional24;
    }

    /**
     * Gets the adicional25 of this TmsPagosActAdicionalesTbl.
     * @return the adicional25
     */
    public String getAdicional25() {
        return this.adicional25;
    }

    /**
     * Sets the adicional25 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional25 the new adicional25
     */
    public void setAdicional25(String adicional25) {
        this.adicional25 = adicional25;
    }

    /**
     * Gets the adicional26 of this TmsPagosActAdicionalesTbl.
     * @return the adicional26
     */
    public String getAdicional26() {
        return this.adicional26;
    }

    /**
     * Sets the adicional26 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional26 the new adicional26
     */
    public void setAdicional26(String adicional26) {
        this.adicional26 = adicional26;
    }

    /**
     * Gets the adicional27 of this TmsPagosActAdicionalesTbl.
     * @return the adicional27
     */
    public String getAdicional27() {
        return this.adicional27;
    }

    /**
     * Sets the adicional27 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional27 the new adicional27
     */
    public void setAdicional27(String adicional27) {
        this.adicional27 = adicional27;
    }

    /**
     * Gets the adicional28 of this TmsPagosActAdicionalesTbl.
     * @return the adicional28
     */
    public String getAdicional28() {
        return this.adicional28;
    }

    /**
     * Sets the adicional28 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional28 the new adicional28
     */
    public void setAdicional28(String adicional28) {
        this.adicional28 = adicional28;
    }

    /**
     * Gets the adicional29 of this TmsPagosActAdicionalesTbl.
     * @return the adicional29
     */
    public String getAdicional29() {
        return this.adicional29;
    }

    /**
     * Sets the adicional29 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional29 the new adicional29
     */
    public void setAdicional29(String adicional29) {
        this.adicional29 = adicional29;
    }

    /**
     * Gets the adicional30 of this TmsPagosActAdicionalesTbl.
     * @return the adicional30
     */
    public String getAdicional30() {
        return this.adicional30;
    }

    /**
     * Sets the adicional30 of this TmsPagosActAdicionalesTbl to the specified value.
     * @param adicional30 the new adicional30
     */
    public void setAdicional30(String adicional30) {
        this.adicional30 = adicional30;
    }

    /**
     * Gets the tipoActividadAdicionalId of this TmsPagosActAdicionalesTbl.
     * @return the tipoActividadAdicionalId
     */
    public TmsActAdicionalesTbl getTipoActividadAdicionalId() {
        return this.tipoActividadAdicionalId;
    }

    /**
     * Sets the tipoActividadAdicionalId of this TmsPagosActAdicionalesTbl to the specified value.
     * @param tipoActividadAdicionalId the new tipoActividadAdicionalId
     */
    public void setTipoActividadAdicionalId(TmsActAdicionalesTbl tipoActividadAdicionalId) {
        this.tipoActividadAdicionalId = tipoActividadAdicionalId;
    }

    /**
     * Gets the servicioId of this TmsPagosActAdicionalesTbl.
     * @return the servicioId
     */
    public TmsServiciosTbl getServicioId() {
        return this.servicioId;
    }

    /**
     * Sets the servicioId of this TmsPagosActAdicionalesTbl to the specified value.
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
        hash += (this.pagoActividadAdicionalId != null ? this.pagoActividadAdicionalId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsPagosActAdicionalesTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsPagosActAdicionalesTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsPagosActAdicionalesTbl)) {
            return false;
        }
        TmsPagosActAdicionalesTbl other = (TmsPagosActAdicionalesTbl)object;
        if (this.pagoActividadAdicionalId != other.pagoActividadAdicionalId && (this.pagoActividadAdicionalId == null || !this.pagoActividadAdicionalId.equals(other.pagoActividadAdicionalId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsactividadesadicionales.entidad.TmsPagosActAdicionalesTbl[pagoActividadAdicionalId=" + pagoActividadAdicionalId + "]";
    }
    
}
