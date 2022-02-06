/*
 * TmsUsuariosTbl.java
 *
 * Created on 10 de septiembre de 2007, 08:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsactividadesadicionales.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
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
 * Entity class TmsUsuariosTbl
 * 
 * @author vgonzalez
 */
@Entity
@Table(name = "TMS_USUARIOS_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsUsuariosTbl.findByUsuarioId", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.usuarioId = :usuarioId"),
        @NamedQuery(name = "TmsUsuariosTbl.findByUsuarioNumero", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.usuarioNumero = :usuarioNumero"),
        @NamedQuery(name = "TmsUsuariosTbl.findByUsuarioNombre", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.usuarioNombre = :usuarioNombre"),
        @NamedQuery(name = "TmsUsuariosTbl.findByDescripcion", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.descripcion = :descripcion"),
        @NamedQuery(name = "TmsUsuariosTbl.findByUbicacionId", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.ubicacionId = :ubicacionId"),
        @NamedQuery(name = "TmsUsuariosTbl.findByFechaInicial", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.fechaInicial = :fechaInicial"),
        @NamedQuery(name = "TmsUsuariosTbl.findByFechaFinal", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.fechaFinal = :fechaFinal"),
        @NamedQuery(name = "TmsUsuariosTbl.findByContrasenaEncriptada", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.contrasenaEncriptada = :contrasenaEncriptada"),
        @NamedQuery(name = "TmsUsuariosTbl.findByContrasenaFecha", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.contrasenaFecha = :contrasenaFecha"),
        @NamedQuery(name = "TmsUsuariosTbl.findByContrasenaAccesosFaltan", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.contrasenaAccesosFaltan = :contrasenaAccesosFaltan"),
        @NamedQuery(name = "TmsUsuariosTbl.findByContrasenaLimiteDias", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.contrasenaLimiteDias = :contrasenaLimiteDias"),
        @NamedQuery(name = "TmsUsuariosTbl.findByContrasenaLimiteAccesos", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.contrasenaLimiteAccesos = :contrasenaLimiteAccesos"),
        @NamedQuery(name = "TmsUsuariosTbl.findByCorreoElectronico", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.correoElectronico = :correoElectronico"),
        @NamedQuery(name = "TmsUsuariosTbl.findByAdicional1", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.adicional1 = :adicional1"),
        @NamedQuery(name = "TmsUsuariosTbl.findByAdicional2", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.adicional2 = :adicional2"),
        @NamedQuery(name = "TmsUsuariosTbl.findByAdicional3", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.adicional3 = :adicional3"),
        @NamedQuery(name = "TmsUsuariosTbl.findByAdicional4", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.adicional4 = :adicional4"),
        @NamedQuery(name = "TmsUsuariosTbl.findByAdicional5", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.adicional5 = :adicional5"),
        @NamedQuery(name = "TmsUsuariosTbl.findByCreadoPor", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsUsuariosTbl.findByFechaCreacion", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsUsuariosTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsUsuariosTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsUsuariosTbl.findByEstado", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.estado = :estado"),
        @NamedQuery(name = "TmsUsuariosTbl.findByContadorIntentos", query = "SELECT t FROM TmsUsuariosTbl t WHERE t.contadorIntentos = :contadorIntentos")
    })
public class TmsUsuariosTbl implements Serializable {

    @Id
    @Column(name = "USUARIO_ID", nullable = false)
    private BigDecimal usuarioId;

    @Column(name = "USUARIO_NUMERO")
    private String usuarioNumero;

    @Column(name = "USUARIO_NOMBRE", nullable = false)
    private String usuarioNombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "UBICACION_ID")
    private BigInteger ubicacionId;

    @Column(name = "FECHA_INICIAL", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;

    @Column(name = "FECHA_FINAL")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    @Column(name = "CONTRASENA_ENCRIPTADA", nullable = false)
    private String contrasenaEncriptada;

    @Column(name = "CONTRASENA_FECHA")
    @Temporal(TemporalType.DATE)
    private Date contrasenaFecha;

    @Column(name = "CONTRASENA_ACCESOS_FALTAN")
    private BigInteger contrasenaAccesosFaltan;

    @Column(name = "CONTRASENA_LIMITE_DIAS")
    private BigInteger contrasenaLimiteDias;

    @Column(name = "CONTRASENA_LIMITE_ACCESOS")
    private BigInteger contrasenaLimiteAccesos;

    @Column(name = "CORREO_ELECTRONICO")
    private String correoElectronico;

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

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "CONTADOR_INTENTOS")
    private BigInteger contadorIntentos;
    /*
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId")
    private Collection<TmsCortesTbl> tmsCortesTblCollection;
    */
    /** Creates a new instance of TmsUsuariosTbl */
    public TmsUsuariosTbl() {
    }

    /**
     * Creates a new instance of TmsUsuariosTbl with the specified values.
     * @param usuarioId the usuarioId of the TmsUsuariosTbl
     */
    public TmsUsuariosTbl(BigDecimal usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * Creates a new instance of TmsUsuariosTbl with the specified values.
     * @param usuarioId the usuarioId of the TmsUsuariosTbl
     * @param usuarioNombre the usuarioNombre of the TmsUsuariosTbl
     * @param fechaInicial the fechaInicial of the TmsUsuariosTbl
     * @param contrasenaEncriptada the contrasenaEncriptada of the TmsUsuariosTbl
     * @param creadoPor the creadoPor of the TmsUsuariosTbl
     * @param fechaCreacion the fechaCreacion of the TmsUsuariosTbl
     * @param ultimaActualizacionPor the ultimaActualizacionPor of the TmsUsuariosTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsUsuariosTbl
     */
    public TmsUsuariosTbl(BigDecimal usuarioId, String usuarioNombre, Date fechaInicial, String contrasenaEncriptada, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.fechaInicial = fechaInicial;
        this.contrasenaEncriptada = contrasenaEncriptada;
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the usuarioId of this TmsUsuariosTbl.
     * @return the usuarioId
     */
    public BigDecimal getUsuarioId() {
        return this.usuarioId;
    }

    /**
     * Sets the usuarioId of this TmsUsuariosTbl to the specified value.
     * @param usuarioId the new usuarioId
     */
    public void setUsuarioId(BigDecimal usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * Gets the usuarioNumero of this TmsUsuariosTbl.
     * @return the usuarioNumero
     */
    public String getUsuarioNumero() {
        return this.usuarioNumero;
    }

    /**
     * Sets the usuarioNumero of this TmsUsuariosTbl to the specified value.
     * @param usuarioNumero the new usuarioNumero
     */
    public void setUsuarioNumero(String usuarioNumero) {
        this.usuarioNumero = usuarioNumero;
    }

    /**
     * Gets the usuarioNombre of this TmsUsuariosTbl.
     * @return the usuarioNombre
     */
    public String getUsuarioNombre() {
        return this.usuarioNombre;
    }

    /**
     * Sets the usuarioNombre of this TmsUsuariosTbl to the specified value.
     * @param usuarioNombre the new usuarioNombre
     */
    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    /**
     * Gets the descripcion of this TmsUsuariosTbl.
     * @return the descripcion
     */
    public String getDescripcion() {
        return this.descripcion;
    }

    /**
     * Sets the descripcion of this TmsUsuariosTbl to the specified value.
     * @param descripcion the new descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Gets the ubicacionId of this TmsUsuariosTbl.
     * @return the ubicacionId
     */
    public BigInteger getUbicacionId() {
        return this.ubicacionId;
    }

    /**
     * Sets the ubicacionId of this TmsUsuariosTbl to the specified value.
     * @param ubicacionId the new ubicacionId
     */
    public void setUbicacionId(BigInteger ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    /**
     * Gets the fechaInicial of this TmsUsuariosTbl.
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return this.fechaInicial;
    }

    /**
     * Sets the fechaInicial of this TmsUsuariosTbl to the specified value.
     * @param fechaInicial the new fechaInicial
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * Gets the fechaFinal of this TmsUsuariosTbl.
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return this.fechaFinal;
    }

    /**
     * Sets the fechaFinal of this TmsUsuariosTbl to the specified value.
     * @param fechaFinal the new fechaFinal
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * Gets the contrasenaEncriptada of this TmsUsuariosTbl.
     * @return the contrasenaEncriptada
     */
    public String getContrasenaEncriptada() {
        return this.contrasenaEncriptada;
    }

    /**
     * Sets the contrasenaEncriptada of this TmsUsuariosTbl to the specified value.
     * @param contrasenaEncriptada the new contrasenaEncriptada
     */
    public void setContrasenaEncriptada(String contrasenaEncriptada) {
        this.contrasenaEncriptada = contrasenaEncriptada;
    }

    /**
     * Gets the contrasenaFecha of this TmsUsuariosTbl.
     * @return the contrasenaFecha
     */
    public Date getContrasenaFecha() {
        return this.contrasenaFecha;
    }

    /**
     * Sets the contrasenaFecha of this TmsUsuariosTbl to the specified value.
     * @param contrasenaFecha the new contrasenaFecha
     */
    public void setContrasenaFecha(Date contrasenaFecha) {
        this.contrasenaFecha = contrasenaFecha;
    }

    /**
     * Gets the contrasenaAccesosFaltan of this TmsUsuariosTbl.
     * @return the contrasenaAccesosFaltan
     */
    public BigInteger getContrasenaAccesosFaltan() {
        return this.contrasenaAccesosFaltan;
    }

    /**
     * Sets the contrasenaAccesosFaltan of this TmsUsuariosTbl to the specified value.
     * @param contrasenaAccesosFaltan the new contrasenaAccesosFaltan
     */
    public void setContrasenaAccesosFaltan(BigInteger contrasenaAccesosFaltan) {
        this.contrasenaAccesosFaltan = contrasenaAccesosFaltan;
    }

    /**
     * Gets the contrasenaLimiteDias of this TmsUsuariosTbl.
     * @return the contrasenaLimiteDias
     */
    public BigInteger getContrasenaLimiteDias() {
        return this.contrasenaLimiteDias;
    }

    /**
     * Sets the contrasenaLimiteDias of this TmsUsuariosTbl to the specified value.
     * @param contrasenaLimiteDias the new contrasenaLimiteDias
     */
    public void setContrasenaLimiteDias(BigInteger contrasenaLimiteDias) {
        this.contrasenaLimiteDias = contrasenaLimiteDias;
    }

    /**
     * Gets the contrasenaLimiteAccesos of this TmsUsuariosTbl.
     * @return the contrasenaLimiteAccesos
     */
    public BigInteger getContrasenaLimiteAccesos() {
        return this.contrasenaLimiteAccesos;
    }

    /**
     * Sets the contrasenaLimiteAccesos of this TmsUsuariosTbl to the specified value.
     * @param contrasenaLimiteAccesos the new contrasenaLimiteAccesos
     */
    public void setContrasenaLimiteAccesos(BigInteger contrasenaLimiteAccesos) {
        this.contrasenaLimiteAccesos = contrasenaLimiteAccesos;
    }

    /**
     * Gets the correoElectronico of this TmsUsuariosTbl.
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    /**
     * Sets the correoElectronico of this TmsUsuariosTbl to the specified value.
     * @param correoElectronico the new correoElectronico
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Gets the adicional1 of this TmsUsuariosTbl.
     * @return the adicional1
     */
    public String getAdicional1() {
        return this.adicional1;
    }

    /**
     * Sets the adicional1 of this TmsUsuariosTbl to the specified value.
     * @param adicional1 the new adicional1
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * Gets the adicional2 of this TmsUsuariosTbl.
     * @return the adicional2
     */
    public String getAdicional2() {
        return this.adicional2;
    }

    /**
     * Sets the adicional2 of this TmsUsuariosTbl to the specified value.
     * @param adicional2 the new adicional2
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * Gets the adicional3 of this TmsUsuariosTbl.
     * @return the adicional3
     */
    public String getAdicional3() {
        return this.adicional3;
    }

    /**
     * Sets the adicional3 of this TmsUsuariosTbl to the specified value.
     * @param adicional3 the new adicional3
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * Gets the adicional4 of this TmsUsuariosTbl.
     * @return the adicional4
     */
    public String getAdicional4() {
        return this.adicional4;
    }

    /**
     * Sets the adicional4 of this TmsUsuariosTbl to the specified value.
     * @param adicional4 the new adicional4
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * Gets the adicional5 of this TmsUsuariosTbl.
     * @return the adicional5
     */
    public String getAdicional5() {
        return this.adicional5;
    }

    /**
     * Sets the adicional5 of this TmsUsuariosTbl to the specified value.
     * @param adicional5 the new adicional5
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * Gets the creadoPor of this TmsUsuariosTbl.
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsUsuariosTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsUsuariosTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsUsuariosTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsUsuariosTbl.
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsUsuariosTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsUsuariosTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsUsuariosTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the estado of this TmsUsuariosTbl.
     * @return the estado
     */
    public String getEstado() {
        return this.estado;
    }

    /**
     * Sets the estado of this TmsUsuariosTbl to the specified value.
     * @param estado the new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Gets the contadorIntentos of this TmsUsuariosTbl.
     * @return the contadorIntentos
     */
    public BigInteger getContadorIntentos() {
        return this.contadorIntentos;
    }

    /**
     * Sets the contadorIntentos of this TmsUsuariosTbl to the specified value.
     * @param contadorIntentos the new contadorIntentos
     */
    public void setContadorIntentos(BigInteger contadorIntentos) {
        this.contadorIntentos = contadorIntentos;
    }

 

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.usuarioId != null ? this.usuarioId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsUsuariosTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsUsuariosTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsUsuariosTbl)) {
            return false;
        }
        TmsUsuariosTbl other = (TmsUsuariosTbl)object;
        if (this.usuarioId != other.usuarioId && (this.usuarioId == null || !this.usuarioId.equals(other.usuarioId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "TmsRecaudacion.entidad.TmsUsuariosTbl[usuarioId=" + usuarioId + "]";
    }
    
}
