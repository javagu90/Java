/*
 * TmsRutasTbl.java
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
 * Entity class TmsRutasTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_RUTAS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsRutasTbl.findByRutaId", query = "SELECT t FROM TmsRutasTbl t WHERE t.rutaId = :rutaId"),
        @NamedQuery(name = "TmsRutasTbl.findByRutaNumero", query = "SELECT t FROM TmsRutasTbl t WHERE t.rutaNumero = :rutaNumero"),
        @NamedQuery(name = "TmsRutasTbl.findByRutaNombre", query = "SELECT t FROM TmsRutasTbl t WHERE t.rutaNombre = :rutaNombre"),
        @NamedQuery(name = "TmsRutasTbl.findByDescripcion", query = "SELECT t FROM TmsRutasTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsRutasTbl.findByAceptaCorridasExtras", query = "SELECT t FROM TmsRutasTbl t WHERE t.aceptaCorridasExtras = :aceptaCorridasExtras"),
        @NamedQuery(name = "TmsRutasTbl.findByTramoOrigenId", query = "SELECT t FROM TmsRutasTbl t WHERE t.tramoOrigenId = :tramoOrigenId"),
        @NamedQuery(name = "TmsRutasTbl.findByTramoDestinoId", query = "SELECT t FROM TmsRutasTbl t WHERE t.tramoDestinoId = :tramoDestinoId"),
        @NamedQuery(name = "TmsRutasTbl.findByTiempoRecorrido", query = "SELECT t FROM TmsRutasTbl t WHERE t.tiempoRecorrido = :tiempoRecorrido"),
        @NamedQuery(name = "TmsRutasTbl.findByDistanciaRecorrido", query = "SELECT t FROM TmsRutasTbl t WHERE t.distanciaRecorrido = :distanciaRecorrido"),
        @NamedQuery(name = "TmsRutasTbl.findByTiempoEstancia", query = "SELECT t FROM TmsRutasTbl t WHERE t.tiempoEstancia = :tiempoEstancia"),
        @NamedQuery(name = "TmsRutasTbl.findByAdicional1", query = "SELECT t FROM TmsRutasTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsRutasTbl.findByAdicional2", query = "SELECT t FROM TmsRutasTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsRutasTbl.findByAdicional3", query = "SELECT t FROM TmsRutasTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsRutasTbl.findByAdicional4", query = "SELECT t FROM TmsRutasTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsRutasTbl.findByAdicional5", query = "SELECT t FROM TmsRutasTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsRutasTbl.findByAdicional6", query = "SELECT t FROM TmsRutasTbl t WHERE t.adicional6 = :adicional6"),
        @NamedQuery(name = "TmsRutasTbl.findByAdicional7", query = "SELECT t FROM TmsRutasTbl t WHERE t.adicional7 = :adicional7"),
        @NamedQuery(name = "TmsRutasTbl.findByAdicional8", query = "SELECT t FROM TmsRutasTbl t WHERE t.adicional8 = :adicional8"),
        @NamedQuery(name = "TmsRutasTbl.findByAdicional9", query = "SELECT t FROM TmsRutasTbl t WHERE t.adicional9 = :adicional9"),
        @NamedQuery(name = "TmsRutasTbl.findByAdicional10", query = "SELECT t FROM TmsRutasTbl t WHERE t.adicional10 = :adicional10"),
        @NamedQuery(name = "TmsRutasTbl.findByHabilitado", query = "SELECT t FROM TmsRutasTbl t WHERE t.habilitado = :habilitado"),
        @NamedQuery(name = "TmsRutasTbl.findByCreadoPor", query = "SELECT t FROM TmsRutasTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsRutasTbl.findByFechaCreacion", query = "SELECT t FROM TmsRutasTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsRutasTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsRutasTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsRutasTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsRutasTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsRutasTbl.findByReplicacionEstado", query = "SELECT t FROM TmsRutasTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsRutasTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsRutasTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsRutasTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsRutasTbl t WHERE t.replicacionOrigen = :replicacionOrigen")
    })
public class TmsRutasTbl implements Serializable {

    @Id
    @Column(name = "RUTA_ID", nullable = false)
    private BigDecimal rutaId;

    @Column(name = "RUTA_NUMERO", nullable = false)
    private String rutaNumero;

    @Column(name = "RUTA_NOMBRE")
    private String rutaNombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "ACEPTA_CORRIDAS_EXTRAS", nullable = false)
    private String aceptaCorridasExtras;

    @Column(name = "TRAMO_ORIGEN_ID", nullable = false)
    private BigInteger tramoOrigenId;

    @Column(name = "TRAMO_DESTINO_ID", nullable = false)
    private BigInteger tramoDestinoId;

    @Column(name = "TIEMPO_RECORRIDO")
    private BigInteger tiempoRecorrido;

    @Column(name = "DISTANCIA_RECORRIDO")
    private BigInteger distanciaRecorrido;

    @Column(name = "TIEMPO_ESTANCIA")
    private BigInteger tiempoEstancia;

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

    @Column(name = "HABILITADO", nullable = false)
    private String habilitado;

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

    @JoinColumn(name = "SERVICIO_ID", referencedColumnName = "SERVICIO_ID")
    @ManyToOne
    private TmsServiciosTbl servicioId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutaId")
    private Collection<TmsOfertasServicioTbl> tmsOfertasServicioTblCollection;
    
    /** Creates a new instance of TmsRutasTbl */
    public TmsRutasTbl() {
    }

    /**
     * Creates a new instance of TmsRutasTbl with the specified values.
     * @param rutaId the rutaId of the TmsRutasTbl
     */
    public TmsRutasTbl(BigDecimal rutaId) {
        this.rutaId = rutaId;
    }

    /**
     * Creates a new instance of TmsRutasTbl with the specified values.
     * @param rutaId the rutaId of the TmsRutasTbl
     * @param rutaNumero the rutaNumero of the TmsRutasTbl
     * @param aceptaCorridasExtras the aceptaCorridasExtras of the TmsRutasTbl
     * @param tramoOrigenId the tramoOrigenId of the TmsRutasTbl
     * @param tramoDestinoId the tramoDestinoId of the TmsRutasTbl
     * @param habilitado the habilitado of the TmsRutasTbl
     * @param creadoPor the creadoPor of the TmsRutasTbl
     * @param fechaCreacion the fechaCreacion of the TmsRutasTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsRutasTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsRutasTbl
     */
    public TmsRutasTbl(BigDecimal rutaId, String rutaNumero, String aceptaCorridasExtras, BigInteger tramoOrigenId, BigInteger tramoDestinoId, String habilitado, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.rutaId = rutaId;
        this.rutaNumero = rutaNumero;
        this.aceptaCorridasExtras = aceptaCorridasExtras;
        this.tramoOrigenId = tramoOrigenId;
        this.tramoDestinoId = tramoDestinoId;
        this.habilitado = habilitado;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the rutaId of this TmsRutasTbl.
     * @return the rutaId
     */
    public BigDecimal getRutaId() {
        return this.rutaId;
    }

    /**
     * Sets the rutaId of this TmsRutasTbl to the specified value.
     * @param rutaId the new rutaId
     */
    public void setRutaId(BigDecimal rutaId) {
        this.rutaId = rutaId;
    }

    /**
     * Gets the rutaNumero of this TmsRutasTbl.
     * @return the rutaNumero
     */
    public String getRutaNumero() {
        return this.rutaNumero;
    }

    /**
     * Sets the rutaNumero of this TmsRutasTbl to the specified value.
     * @param rutaNumero the new rutaNumero
     */
    public void setRutaNumero(String rutaNumero) {
        this.rutaNumero = rutaNumero;
    }

    /**
     * Gets the rutaNombre of this TmsRutasTbl.
     * @return the rutaNombre
     */
    public String getRutaNombre() {
        return this.rutaNombre;
    }

    /**
     * Sets the rutaNombre of this TmsRutasTbl to the specified value.
     * @param rutaNombre the new rutaNombre
     */
    public void setRutaNombre(String rutaNombre) {
        this.rutaNombre = rutaNombre;
    }

    /**
     * Gets the descripcion of this TmsRutasTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsRutasTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the aceptaCorridasExtras of this TmsRutasTbl.
     * @return the aceptaCorridasExtras
     */
    public String getAceptaCorridasExtras() {
        return this.aceptaCorridasExtras;
    }

    /**
     * Sets the aceptaCorridasExtras of this TmsRutasTbl to the specified value.
     * @param aceptaCorridasExtras the new aceptaCorridasExtras
     */
    public void setAceptaCorridasExtras(String aceptaCorridasExtras) {
        this.aceptaCorridasExtras = aceptaCorridasExtras;
    }

    /**
     * Gets the tramoOrigenId of this TmsRutasTbl.
     * @return the tramoOrigenId
     */
    public BigInteger getTramoOrigenId() {
        return this.tramoOrigenId;
    }

    /**
     * Sets the tramoOrigenId of this TmsRutasTbl to the specified value.
     * @param tramoOrigenId the new tramoOrigenId
     */
    public void setTramoOrigenId(BigInteger tramoOrigenId) {
        this.tramoOrigenId = tramoOrigenId;
    }

    /**
     * Gets the tramoDestinoId of this TmsRutasTbl.
     * @return the tramoDestinoId
     */
    public BigInteger getTramoDestinoId() {
        return this.tramoDestinoId;
    }

    /**
     * Sets the tramoDestinoId of this TmsRutasTbl to the specified value.
     * @param tramoDestinoId the new tramoDestinoId
     */
    public void setTramoDestinoId(BigInteger tramoDestinoId) {
        this.tramoDestinoId = tramoDestinoId;
    }

    /**
     * Gets the tiempoRecorrido of this TmsRutasTbl.
     * @return the tiempoRecorrido
     */
    public BigInteger getTiempoRecorrido() {
        return this.tiempoRecorrido;
    }

    /**
     * Sets the tiempoRecorrido of this TmsRutasTbl to the specified value.
     * @param tiempoRecorrido the new tiempoRecorrido
     */
    public void setTiempoRecorrido(BigInteger tiempoRecorrido) {
        this.tiempoRecorrido = tiempoRecorrido;
    }

    /**
     * Gets the distanciaRecorrido of this TmsRutasTbl.
     * @return the distanciaRecorrido
     */
    public BigInteger getDistanciaRecorrido() {
        return this.distanciaRecorrido;
    }

    /**
     * Sets the distanciaRecorrido of this TmsRutasTbl to the specified value.
     * @param distanciaRecorrido the new distanciaRecorrido
     */
    public void setDistanciaRecorrido(BigInteger distanciaRecorrido) {
        this.distanciaRecorrido = distanciaRecorrido;
    }

    /**
     * Gets the tiempoEstancia of this TmsRutasTbl.
     * @return the tiempoEstancia
     */
    public BigInteger getTiempoEstancia() {
        return this.tiempoEstancia;
    }

    /**
     * Sets the tiempoEstancia of this TmsRutasTbl to the specified value.
     * @param tiempoEstancia the new tiempoEstancia
     */
    public void setTiempoEstancia(BigInteger tiempoEstancia) {
        this.tiempoEstancia = tiempoEstancia;
    }

    /**
     * Gets the adicional1 of this TmsRutasTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsRutasTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsRutasTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsRutasTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsRutasTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsRutasTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsRutasTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsRutasTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsRutasTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsRutasTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the adicional6 of this TmsRutasTbl.
     * @return the adicional6
     */
    public String getAdicional6() {
        return this.adicional6;
    }

    /**
     * Sets the adicional6 of this TmsRutasTbl to the specified value.
     * @param adicional6 the new adicional6
     */
    public void setAdicional6(String adicional6) {
        this.adicional6 = adicional6;
    }

    /**
     * Gets the adicional7 of this TmsRutasTbl.
     * @return the adicional7
     */
    public String getAdicional7() {
        return this.adicional7;
    }

    /**
     * Sets the adicional7 of this TmsRutasTbl to the specified value.
     * @param adicional7 the new adicional7
     */
    public void setAdicional7(String adicional7) {
        this.adicional7 = adicional7;
    }

    /**
     * Gets the adicional8 of this TmsRutasTbl.
     * @return the adicional8
     */
    public String getAdicional8() {
        return this.adicional8;
    }

    /**
     * Sets the adicional8 of this TmsRutasTbl to the specified value.
     * @param adicional8 the new adicional8
     */
    public void setAdicional8(String adicional8) {
        this.adicional8 = adicional8;
    }

    /**
     * Gets the adicional9 of this TmsRutasTbl.
     * @return the adicional9
     */
    public String getAdicional9() {
        return this.adicional9;
    }

    /**
     * Sets the adicional9 of this TmsRutasTbl to the specified value.
     * @param adicional9 the new adicional9
     */
    public void setAdicional9(String adicional9) {
        this.adicional9 = adicional9;
    }

    /**
     * Gets the adicional10 of this TmsRutasTbl.
     * @return the adicional10
     */
    public String getAdicional10() {
        return this.adicional10;
    }

    /**
     * Sets the adicional10 of this TmsRutasTbl to the specified value.
     * @param adicional10 the new adicional10
     */
    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
    }

    /**
     * Gets the habilitado of this TmsRutasTbl.
     * @return the habilitado
     */
    public String getHabilitado() {
        return this.habilitado;
    }

    /**
     * Sets the habilitado of this TmsRutasTbl to the specified value.
     * @param habilitado the new habilitado
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    /**
     * Gets the creadoPor of this TmsRutasTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsRutasTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsRutasTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsRutasTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsRutasTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsRutasTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsRutasTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsRutasTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this TmsRutasTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsRutasTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsRutasTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsRutasTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsRutasTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsRutasTbl to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Gets the servicioId of this TmsRutasTbl.
     * @return the servicioId
     */
    public TmsServiciosTbl getServicioId() {
        return this.servicioId;
    }

    /**
     * Sets the servicioId of this TmsRutasTbl to the specified value.
     * @param servicioId the new servicioId
     */
    public void setServicioId(TmsServiciosTbl servicioId) {
        this.servicioId = servicioId;
    }

    /**
     * Gets the tmsOfertasServicioTblCollection of this TmsRutasTbl.
     * @return the tmsOfertasServicioTblCollection
     */
    public Collection<TmsOfertasServicioTbl> getTmsOfertasServicioTblCollection() {
        return this.tmsOfertasServicioTblCollection;
    }

    /**
     * Sets the tmsOfertasServicioTblCollection of this TmsRutasTbl to the specified value.
     * @param tmsOfertasServicioTblCollection the new tmsOfertasServicioTblCollection
     */
    public void setTmsOfertasServicioTblCollection(Collection<TmsOfertasServicioTbl> tmsOfertasServicioTblCollection) {
        this.tmsOfertasServicioTblCollection = tmsOfertasServicioTblCollection;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.rutaId != null ? this.rutaId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsRutasTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsRutasTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsRutasTbl)) {
            return false;
        }
        TmsRutasTbl other = (TmsRutasTbl)object;
        if (this.rutaId != other.rutaId && (this.rutaId == null || !this.rutaId.equals(other.rutaId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsroles.entidad.TmsRutasTbl[rutaId=" + rutaId + "]";
    }
    
}
