/*
 * TmsClientesTbl.java
 *
 * Created on 8 de enero de 2009, 12:26 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class TmsClientesTbl
 * 
 * @author asolis
 */
@Entity
@Table(name = "TMS_CLIENTES_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsClientesTbl.findByClienteNomComp", query = "SELECT t FROM TmsClientesTbl t WHERE t.nombres = :nombres and t.apaterno = :apaterno and t.amaterno = :amaterno"),
        @NamedQuery(name = "TmsClientesTbl.findByClienteId", query = "SELECT t FROM TmsClientesTbl t WHERE t.clienteId = :clienteId"),
        @NamedQuery(name = "TmsClientesTbl.findByNombres", query = "SELECT t FROM TmsClientesTbl t WHERE t.nombres = :nombres"),
        @NamedQuery(name = "TmsClientesTbl.findByApaterno", query = "SELECT t FROM TmsClientesTbl t WHERE t.apaterno = :apaterno"),
        @NamedQuery(name = "TmsClientesTbl.findByAmaterno", query = "SELECT t FROM TmsClientesTbl t WHERE t.amaterno = :amaterno"),
        @NamedQuery(name = "TmsClientesTbl.findByContacto", query = "SELECT t FROM TmsClientesTbl t WHERE t.contacto = :contacto"),
        @NamedQuery(name = "TmsClientesTbl.findByCalle", query = "SELECT t FROM TmsClientesTbl t WHERE t.calle = :calle"),
        @NamedQuery(name = "TmsClientesTbl.findByNumeroInt", query = "SELECT t FROM TmsClientesTbl t WHERE t.numeroInt = :numeroInt"),
        @NamedQuery(name = "TmsClientesTbl.findByNumeroExt", query = "SELECT t FROM TmsClientesTbl t WHERE t.numeroExt = :numeroExt"),
        @NamedQuery(name = "TmsClientesTbl.findByColonia", query = "SELECT t FROM TmsClientesTbl t WHERE t.colonia = :colonia"),
        @NamedQuery(name = "TmsClientesTbl.findByCodigoPostal", query = "SELECT t FROM TmsClientesTbl t WHERE t.codigoPostal = :codigoPostal"),
        @NamedQuery(name = "TmsClientesTbl.findByDelegMun", query = "SELECT t FROM TmsClientesTbl t WHERE t.delegMun = :delegMun"),
        @NamedQuery(name = "TmsClientesTbl.findByCiudad", query = "SELECT t FROM TmsClientesTbl t WHERE t.ciudad = :ciudad"),
        @NamedQuery(name = "TmsClientesTbl.findByEstado", query = "SELECT t FROM TmsClientesTbl t WHERE t.estado = :estado"),
        @NamedQuery(name = "TmsClientesTbl.findByTelefonoCasa", query = "SELECT t FROM TmsClientesTbl t WHERE t.telefonoCasa = :telefonoCasa"),
        @NamedQuery(name = "TmsClientesTbl.findByTelefonoOficina", query = "SELECT t FROM TmsClientesTbl t WHERE t.telefonoOficina = :telefonoOficina"),
        @NamedQuery(name = "TmsClientesTbl.findByFechaNac", query = "SELECT t FROM TmsClientesTbl t WHERE t.fechaNac = :fechaNac"),
        @NamedQuery(name = "TmsClientesTbl.findBySexo", query = "SELECT t FROM TmsClientesTbl t WHERE t.sexo = :sexo"),
        @NamedQuery(name = "TmsClientesTbl.findByEstadoCivil", query = "SELECT t FROM TmsClientesTbl t WHERE t.estadoCivil = :estadoCivil"),
        @NamedQuery(name = "TmsClientesTbl.findByCompania", query = "SELECT t FROM TmsClientesTbl t WHERE t.compania = :compania"),
        @NamedQuery(name = "TmsClientesTbl.findByPuesto", query = "SELECT t FROM TmsClientesTbl t WHERE t.puesto = :puesto"),
        @NamedQuery(name = "TmsClientesTbl.findByEmail", query = "SELECT t FROM TmsClientesTbl t WHERE t.email = :email"),
        @NamedQuery(name = "TmsClientesTbl.findBySaldoPuntos", query = "SELECT t FROM TmsClientesTbl t WHERE t.saldoPuntos = :saldoPuntos"),
        @NamedQuery(name = "TmsClientesTbl.findByFechaUltimaAcumulacion", query = "SELECT t FROM TmsClientesTbl t WHERE t.fechaUltimaAcumulacion = :fechaUltimaAcumulacion"),
        @NamedQuery(name = "TmsClientesTbl.findByFechaUltimoCanje", query = "SELECT t FROM TmsClientesTbl t WHERE t.fechaUltimoCanje = :fechaUltimoCanje"),
        @NamedQuery(name = "TmsClientesTbl.findByFechaDesde", query = "SELECT t FROM TmsClientesTbl t WHERE t.fechaDesde = :fechaDesde"),
        @NamedQuery(name = "TmsClientesTbl.findByFechaHasta", query = "SELECT t FROM TmsClientesTbl t WHERE t.fechaHasta = :fechaHasta"),
        @NamedQuery(name = "TmsClientesTbl.findByFechaCreacion", query = "SELECT t FROM TmsClientesTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsClientesTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsClientesTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsClientesTbl.findByReplicacionEstado", query = "SELECT t FROM TmsClientesTbl t WHERE t.replicacionEstado = :replicacionEstado"),
        @NamedQuery(name = "TmsClientesTbl.findByReplicacionIntentos", query = "SELECT t FROM TmsClientesTbl t WHERE t.replicacionIntentos = :replicacionIntentos"),
        @NamedQuery(name = "TmsClientesTbl.findByReplicacionOrigen", query = "SELECT t FROM TmsClientesTbl t WHERE t.replicacionOrigen = :replicacionOrigen"),
        @NamedQuery(name = "TmsClientesTbl.findByRfc", query = "SELECT t FROM TmsClientesTbl t WHERE t.rfc = :rfc")
    })
public class TmsClientesTbl implements Serializable {

    @Id
    @Column(name = "CLIENTE_ID", nullable = false)
    private BigDecimal clienteId;

    @Column(name = "NOMBRES", nullable = false)
    private String nombres;

    @Column(name = "APATERNO")
    private String apaterno;

    @Column(name = "AMATERNO")
    private String amaterno;

    @Column(name = "CONTACTO")
    private String contacto;

    @Column(name = "CALLE")
    private String calle;

    @Column(name = "NUMERO_INT")
    private String numeroInt;

    @Column(name = "NUMERO_EXT")
    private String numeroExt;

    @Column(name = "COLONIA")
    private String colonia;

    @Column(name = "CODIGO_POSTAL")
    private String codigoPostal;

    @Column(name = "DELEG_MUN")
    private String delegMun;

    @Column(name = "CIUDAD")
    private String ciudad;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "TELEFONO_CASA")
    private String telefonoCasa;

    @Column(name = "TELEFONO_OFICINA")
    private String telefonoOficina;

    @Column(name = "FECHA_NAC")
    @Temporal(TemporalType.DATE)
    private Date fechaNac;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "ESTADO_CIVIL")
    private String estadoCivil;

    @Column(name = "COMPANIA")
    private String compania;

    @Column(name = "PUESTO")
    private String puesto;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SALDO_PUNTOS")
    private BigDecimal saldoPuntos;

    @Column(name = "FECHA_ULTIMA_ACUMULACION")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimaAcumulacion;

    @Column(name = "FECHA_ULTIMO_CANJE")
    @Temporal(TemporalType.DATE)
    private Date fechaUltimoCanje;

    @Column(name = "FECHA_DESDE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;

    @Column(name = "FECHA_HASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;

    @Column(name = "FECHA_CREACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;

    @Column(name = "REPLICACION_ESTADO")
    private String replicacionEstado;

    @Column(name = "REPLICACION_INTENTOS")
    private BigInteger replicacionIntentos;

    @Column(name = "REPLICACION_ORIGEN")
    private String replicacionOrigen;

    @Column(name = "RFC", nullable = false)
    private String rfc;
    
    /** Creates a new instance of TmsClientesTbl */
    public TmsClientesTbl() {
    }

    /**
     * Creates a new instance of TmsClientesTbl with the specified values.
     * @param clienteId the clienteId of the TmsClientesTbl
     */
    public TmsClientesTbl(BigDecimal clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Creates a new instance of TmsClientesTbl with the specified values.
     * @param clienteId the clienteId of the TmsClientesTbl
     * @param nombres the nombres of the TmsClientesTbl
     * @param fechaDesde the fechaDesde of the TmsClientesTbl
     * @param fechaCreacion the fechaCreacion of the TmsClientesTbl
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion of the TmsClientesTbl
     * @param rfc the rfc of the TmsClientesTbl
     */
    public TmsClientesTbl(BigDecimal clienteId, String nombres, Date fechaDesde, Date fechaCreacion, Date ultimaFechaActualizacion, String rfc) {
        this.clienteId = clienteId;
        this.nombres = nombres;
        this.fechaDesde = fechaDesde;
        this.fechaCreacion = fechaCreacion;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
        this.rfc = rfc;
    }

    /**
     * Gets the clienteId of this TmsClientesTbl.
     * @return the clienteId
     */
    public BigDecimal getClienteId() {
        return this.clienteId;
    }

    /**
     * Sets the clienteId of this TmsClientesTbl to the specified value.
     * @param clienteId the new clienteId
     */
    public void setClienteId(BigDecimal clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Gets the nombres of this TmsClientesTbl.
     * @return the nombres
     */
    public String getNombres() {
        return this.nombres;
    }

    /**
     * Sets the nombres of this TmsClientesTbl to the specified value.
     * @param nombres the new nombres
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Gets the apaterno of this TmsClientesTbl.
     * @return the apaterno
     */
    public String getApaterno() {
        return this.apaterno;
    }

    /**
     * Sets the apaterno of this TmsClientesTbl to the specified value.
     * @param apaterno the new apaterno
     */
    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    /**
     * Gets the amaterno of this TmsClientesTbl.
     * @return the amaterno
     */
    public String getAmaterno() {
        return this.amaterno;
    }

    /**
     * Sets the amaterno of this TmsClientesTbl to the specified value.
     * @param amaterno the new amaterno
     */
    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    /**
     * Gets the contacto of this TmsClientesTbl.
     * @return the contacto
     */
    public String getContacto() {
        return this.contacto;
    }

    /**
     * Sets the contacto of this TmsClientesTbl to the specified value.
     * @param contacto the new contacto
     */
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    /**
     * Gets the calle of this TmsClientesTbl.
     * @return the calle
     */
    public String getCalle() {
        return this.calle;
    }

    /**
     * Sets the calle of this TmsClientesTbl to the specified value.
     * @param calle the new calle
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Gets the numeroInt of this TmsClientesTbl.
     * @return the numeroInt
     */
    public String getNumeroInt() {
        return this.numeroInt;
    }

    /**
     * Sets the numeroInt of this TmsClientesTbl to the specified value.
     * @param numeroInt the new numeroInt
     */
    public void setNumeroInt(String numeroInt) {
        this.numeroInt = numeroInt;
    }

    /**
     * Gets the numeroExt of this TmsClientesTbl.
     * @return the numeroExt
     */
    public String getNumeroExt() {
        return this.numeroExt;
    }

    /**
     * Sets the numeroExt of this TmsClientesTbl to the specified value.
     * @param numeroExt the new numeroExt
     */
    public void setNumeroExt(String numeroExt) {
        this.numeroExt = numeroExt;
    }

    /**
     * Gets the colonia of this TmsClientesTbl.
     * @return the colonia
     */
    public String getColonia() {
        return this.colonia;
    }

    /**
     * Sets the colonia of this TmsClientesTbl to the specified value.
     * @param colonia the new colonia
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Gets the codigoPostal of this TmsClientesTbl.
     * @return the codigoPostal
     */
    public String getCodigoPostal() {
        return this.codigoPostal;
    }

    /**
     * Sets the codigoPostal of this TmsClientesTbl to the specified value.
     * @param codigoPostal the new codigoPostal
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Gets the delegMun of this TmsClientesTbl.
     * @return the delegMun
     */
    public String getDelegMun() {
        return this.delegMun;
    }

    /**
     * Sets the delegMun of this TmsClientesTbl to the specified value.
     * @param delegMun the new delegMun
     */
    public void setDelegMun(String delegMun) {
        this.delegMun = delegMun;
    }

    /**
     * Gets the ciudad of this TmsClientesTbl.
     * @return the ciudad
     */
    public String getCiudad() {
        return this.ciudad;
    }

    /**
     * Sets the ciudad of this TmsClientesTbl to the specified value.
     * @param ciudad the new ciudad
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Gets the estado of this TmsClientesTbl.
     * @return the estado
     */
    public String getEstado() {
        return this.estado;
    }

    /**
     * Sets the estado of this TmsClientesTbl to the specified value.
     * @param estado the new estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Gets the telefonoCasa of this TmsClientesTbl.
     * @return the telefonoCasa
     */
    public String getTelefonoCasa() {
        return this.telefonoCasa;
    }

    /**
     * Sets the telefonoCasa of this TmsClientesTbl to the specified value.
     * @param telefonoCasa the new telefonoCasa
     */
    public void setTelefonoCasa(String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }

    /**
     * Gets the telefonoOficina of this TmsClientesTbl.
     * @return the telefonoOficina
     */
    public String getTelefonoOficina() {
        return this.telefonoOficina;
    }

    /**
     * Sets the telefonoOficina of this TmsClientesTbl to the specified value.
     * @param telefonoOficina the new telefonoOficina
     */
    public void setTelefonoOficina(String telefonoOficina) {
        this.telefonoOficina = telefonoOficina;
    }

    /**
     * Gets the fechaNac of this TmsClientesTbl.
     * @return the fechaNac
     */
    public Date getFechaNac() {
        return this.fechaNac;
    }

    /**
     * Sets the fechaNac of this TmsClientesTbl to the specified value.
     * @param fechaNac the new fechaNac
     */
    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    /**
     * Gets the sexo of this TmsClientesTbl.
     * @return the sexo
     */
    public String getSexo() {
        return this.sexo;
    }

    /**
     * Sets the sexo of this TmsClientesTbl to the specified value.
     * @param sexo the new sexo
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Gets the estadoCivil of this TmsClientesTbl.
     * @return the estadoCivil
     */
    public String getEstadoCivil() {
        return this.estadoCivil;
    }

    /**
     * Sets the estadoCivil of this TmsClientesTbl to the specified value.
     * @param estadoCivil the new estadoCivil
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * Gets the compania of this TmsClientesTbl.
     * @return the compania
     */
    public String getCompania() {
        return this.compania;
    }

    /**
     * Sets the compania of this TmsClientesTbl to the specified value.
     * @param compania the new compania
     */
    public void setCompania(String compania) {
        this.compania = compania;
    }

    /**
     * Gets the puesto of this TmsClientesTbl.
     * @return the puesto
     */
    public String getPuesto() {
        return this.puesto;
    }

    /**
     * Sets the puesto of this TmsClientesTbl to the specified value.
     * @param puesto the new puesto
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    /**
     * Gets the email of this TmsClientesTbl.
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email of this TmsClientesTbl to the specified value.
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the saldoPuntos of this TmsClientesTbl.
     * @return the saldoPuntos
     */
    public BigDecimal getSaldoPuntos() {
        return this.saldoPuntos;
    }

    /**
     * Sets the saldoPuntos of this TmsClientesTbl to the specified value.
     * @param saldoPuntos the new saldoPuntos
     */
    public void setSaldoPuntos(BigDecimal saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }

    /**
     * Gets the fechaUltimaAcumulacion of this TmsClientesTbl.
     * @return the fechaUltimaAcumulacion
     */
    public Date getFechaUltimaAcumulacion() {
        return this.fechaUltimaAcumulacion;
    }

    /**
     * Sets the fechaUltimaAcumulacion of this TmsClientesTbl to the specified value.
     * @param fechaUltimaAcumulacion the new fechaUltimaAcumulacion
     */
    public void setFechaUltimaAcumulacion(Date fechaUltimaAcumulacion) {
        this.fechaUltimaAcumulacion = fechaUltimaAcumulacion;
    }

    /**
     * Gets the fechaUltimoCanje of this TmsClientesTbl.
     * @return the fechaUltimoCanje
     */
    public Date getFechaUltimoCanje() {
        return this.fechaUltimoCanje;
    }

    /**
     * Sets the fechaUltimoCanje of this TmsClientesTbl to the specified value.
     * @param fechaUltimoCanje the new fechaUltimoCanje
     */
    public void setFechaUltimoCanje(Date fechaUltimoCanje) {
        this.fechaUltimoCanje = fechaUltimoCanje;
    }

    /**
     * Gets the fechaDesde of this TmsClientesTbl.
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
        return this.fechaDesde;
    }

    /**
     * Sets the fechaDesde of this TmsClientesTbl to the specified value.
     * @param fechaDesde the new fechaDesde
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Gets the fechaHasta of this TmsClientesTbl.
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
        return this.fechaHasta;
    }

    /**
     * Sets the fechaHasta of this TmsClientesTbl to the specified value.
     * @param fechaHasta the new fechaHasta
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * Gets the fechaCreacion of this TmsClientesTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsClientesTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsClientesTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsClientesTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Gets the replicacionEstado of this TmsClientesTbl.
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return this.replicacionEstado;
    }

    /**
     * Sets the replicacionEstado of this TmsClientesTbl to the specified value.
     * @param replicacionEstado the new replicacionEstado
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * Gets the replicacionIntentos of this TmsClientesTbl.
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return this.replicacionIntentos;
    }

    /**
     * Sets the replicacionIntentos of this TmsClientesTbl to the specified value.
     * @param replicacionIntentos the new replicacionIntentos
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * Gets the replicacionOrigen of this TmsClientesTbl.
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return this.replicacionOrigen;
    }

    /**
     * Sets the replicacionOrigen of this TmsClientesTbl to the specified value.
     * @param replicacionOrigen the new replicacionOrigen
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }

    /**
     * Gets the rfc of this TmsClientesTbl.
     * @return the rfc
     */
    public String getRfc() {
        return this.rfc;
    }

    /**
     * Sets the rfc of this TmsClientesTbl to the specified value.
     * @param rfc the new rfc
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.clienteId != null ? this.clienteId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsClientesTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsClientesTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsClientesTbl)) {
            return false;
        }
        TmsClientesTbl other = (TmsClientesTbl)object;
        if (this.clienteId != other.clienteId && (this.clienteId == null || !this.clienteId.equals(other.clienteId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "entidad.TmsClientesTbl[clienteId=" + clienteId + "]";
    }
    
}
