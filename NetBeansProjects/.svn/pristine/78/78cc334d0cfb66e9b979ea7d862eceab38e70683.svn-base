/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Vector;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author brojas
 */
@Entity
@Table(name = "ER_CLIENTES_DIRECCIONES_TBL")
/*@NamedQueries({
    @NamedQuery(name = "ErClientesDireccionesTbl.findAll", query = "SELECT e FROM ErClientesDireccionesTbl e"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByClientesDireccionesId", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.clientesDireccionesId = :clientesDireccionesId"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByTelefono", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByTelefonoCelular", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.telefonoCelular = :telefonoCelular"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByTelefonoOficina", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.telefonoOficina = :telefonoOficina"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByTelefonoFax", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.telefonoFax = :telefonoFax"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByCalle", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.calle = :calle"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByNoExt", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.noExt = :noExt"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByNoInt", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.noInt = :noInt"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByCodigoPostal", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByColonia", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.colonia = :colonia"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByCiudad", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.ciudad = :ciudad"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByEstado", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.estado = :estado"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByPais", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.pais = :pais"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByAdicional1", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.adicional1 = :adicional1"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByAdicional2", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.adicional2 = :adicional2"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByAdicional3", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.adicional3 = :adicional3"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByAdicional4", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.adicional4 = :adicional4"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByAdicional5", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.adicional5 = :adicional5"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByAdicional6", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.adicional6 = :adicional6"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByAdicional7", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.adicional7 = :adicional7"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByAdicional8", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.adicional8 = :adicional8"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByAdicional9", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.adicional9 = :adicional9"),
   // @NamedQuery(name = "ErClientesDireccionesTbl.findByAdicional10", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.adicional10 = :adicional10"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByCreadoPor", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.creadoPor = :creadoPor"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByFechaCreacion", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByUltimaActualizacionPor", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.ultimaActualizacionPor = :ultimaActualizacionPor"),
    @NamedQuery(name = "ErClientesDireccionesTbl.findByUltimaFechaActualizacion", query = "SELECT e FROM ErClientesDireccionesTbl e WHERE e.ultimaFechaActualizacion = :ultimaFechaActualizacion")})
 
 */
public class ERClientesDireccionesTbl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CLIENTES_DIRECCIONES_ID")
    private BigDecimal clientesDireccionesId;
    @Column(name = "TELEFONO")
    private String telefono="";
    @Column(name = "TELEFONO_CELULAR")
    private String telefonoCelular="";
    @Column(name = "TELEFONO_OFICINA")
    private String telefonoOficina="";
    @Column(name = "TELEFONO_FAX")
    private String telefonoFax="";
    @Basic(optional = false)
    @Column(name = "CALLE")
    private String calle=" ";
    @Basic(optional = false)
    @Column(name = "NO_EXT")
    private String noExt=" ";
    @Column(name = "NO_INT")
    private String noInt=" ";
    @Basic(optional = false)
    @Column(name = "CODIGO_POSTAL")
    private String codigoPostal=" ";
    @Column(name = "COLONIA")
    private String colonia="";
    @Column(name = "CIUDAD")
    private String ciudad="";
    @Column(name = "ESTADO")
    private String estado="";
    @Column(name = "PAIS")
    private String pais="";
    @Column(name = "ADICIONAL1")
    private String direccionPredeterminada="";
    /*
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
    @Basic(optional = false)
    @Column(name = "CREADO_POR")
    private BigInteger creadoPor;
    @Basic(optional = false)
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private BigInteger ultimaActualizacionPor;
    @Basic(optional = false)
    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;*/
    private BigDecimal clienteId;

    public ERClientesDireccionesTbl() {
    }

    public ERClientesDireccionesTbl(BigDecimal clientesDireccionesId) {
        this.clientesDireccionesId = clientesDireccionesId;
    }

    public ERClientesDireccionesTbl(BigDecimal clientesDireccionesId, String calle, String noExt, String codigoPostal, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.clientesDireccionesId = clientesDireccionesId;
        this.calle = calle;
        this.noExt = noExt;
        this.codigoPostal = codigoPostal;
        /*this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;*/
    }

        public ERClientesDireccionesTbl(String[] campos) {
        this.setClientesDireccionesId(new BigDecimal(campos[0]));
        this.setClienteId(new BigDecimal(campos[1]));
        this.setTelefono(campos[2]!= null? campos[2].trim():"");
        this.setTelefonoCelular(campos[3]!= null? campos[3].trim():"");
        this.setTelefonoOficina(campos[4]!= null? campos[4].trim():"");
        this.setTelefonoFax(campos[5]!= null? campos[5].trim():"");
        this.setCalle(campos[6]!= null? campos[6].trim():"");
        this.setNoExt(campos[7]!= null? campos[7].trim():"");
        this.setNoInt(campos[8]!= null? campos[8].trim():"");
        this.setCodigoPostal(campos[9]!= null? campos[9].trim():"");
        this.setColonia(campos[10]!= null? campos[10].trim():"");
        this.setCiudad(campos[11]!= null? campos[11].trim():"");
        this.setEstado(campos[12]!= null? campos[12].trim():"");
        this.setPais(campos[13] != null? campos[13].trim():"");

         }

        public ERClientesDireccionesTbl(Vector campos) {
         this.setClientesDireccionesId(new BigDecimal(campos.get(0).toString()));
        this.setClienteId(new BigDecimal(campos.get(1).toString()));
        this.setTelefono(campos.get(2)!= null? campos.get(2).toString():"");
        this.setTelefonoCelular(campos.get(3)!= null? campos.get(3).toString():"");
        this.setTelefonoOficina(campos.get(4)!= null? campos.get(4).toString():"");
        this.setTelefonoFax(campos.get(5)!= null? campos.get(5).toString():"");
        this.setCalle(campos.get(6)!= null? campos.get(6).toString():"");
        this.setNoExt(campos.get(7)!= null? campos.get(7).toString():"");
        this.setNoInt(campos.get(8)!= null? campos.get(8).toString():"");
        this.setCodigoPostal(campos.get(9)!= null? campos.get(9).toString():"");
        this.setColonia(campos.get(10)!= null? campos.get(10).toString():"");
        this.setCiudad(campos.get(11)!= null? campos.get(11).toString():"");
        this.setEstado(campos.get(13)!= null? campos.get(13).toString():"");
        this.setPais(campos.get(14) != null? campos.get(14).toString():"");
        this.setDireccionPredeterminada(campos.get(15) != null? campos.get(15).toString():"N");
         }

    public BigDecimal getClientesDireccionesId() {
        return clientesDireccionesId;
    }

    public void setClientesDireccionesId(BigDecimal clientesDireccionesId) {
        this.clientesDireccionesId = clientesDireccionesId;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getTelefonoOficina() {
        return telefonoOficina;
    }

    public void setTelefonoOficina(String telefonoOficina) {
        this.telefonoOficina = telefonoOficina;
    }

    public String getTelefonoFax() {
        return telefonoFax;
    }

    public void setTelefonoFax(String telefonoFax) {
        this.telefonoFax = telefonoFax;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNoExt() {
        return noExt;
    }

    public void setNoExt(String noExt) {
        this.noExt = noExt;
    }

    public String getNoInt() {
        return noInt;
    }

    public void setNoInt(String noInt) {
        this.noInt = noInt;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

   /*
    public BigInteger getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getUltimaActualizacionPor() {
        return ultimaActualizacionPor;
    }

    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    public Date getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }
*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientesDireccionesId != null ? clientesDireccionesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ERClientesDireccionesTbl)) {
            return false;
        }
        ERClientesDireccionesTbl other = (ERClientesDireccionesTbl) object;
        if ((this.clientesDireccionesId == null && other.clientesDireccionesId != null) || (this.clientesDireccionesId != null && !this.clientesDireccionesId.equals(other.clientesDireccionesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.entidades.ErClientesDireccionesTbl[clientesDireccionesId=" + clientesDireccionesId + "]";
    }

    /**
     * @return the clienteId
     */
    public BigDecimal getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId the clienteId to set
     */
    public void setClienteId(BigDecimal clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return the direccionPredeterminada
     */
    public String getDireccionPredeterminada() {
        return direccionPredeterminada;
    }

    /**
     * @param direccionPredeterminada the direccionPredeterminada to set
     */
    public void setDireccionPredeterminada(String direccionPredeterminada) {
        this.direccionPredeterminada = direccionPredeterminada;
    }

}
