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
 * @author vgonzalez
 */
@Entity
@Table(name = "ER_CLIENTES_DIR_FISCALES_TBL")
/*@NamedQueries({
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findAll", query = "SELECT e FROM ErClientesDirFiscalesTbl e"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByClientesDirFiscalesId", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.clientesDirFiscalesId = :clientesDirFiscalesId"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByClienteId", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.clienteId = :clienteId"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByRazonSocial", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.razonSocial = :razonSocial"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByRfc", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.rfc = :rfc"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByTelefono", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByTelefonoCelular", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.telefonoCelular = :telefonoCelular"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByTelefonoOficina", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.telefonoOficina = :telefonoOficina"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByTelefonoFax", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.telefonoFax = :telefonoFax"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByDomicilio", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.domicilio = :domicilio"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByNoInt", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.noInt = :noInt"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByNoExt", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.noExt = :noExt"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByCodigopostal", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.codigopostal = :codigopostal"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByColonia", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.colonia = :colonia"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByCiudad", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.ciudad = :ciudad"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByEstado", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.estado = :estado"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByPais", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.pais = :pais"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByAdicional1", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.adicional1 = :adicional1"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByAdicional2", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.adicional2 = :adicional2"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByAdicional3", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.adicional3 = :adicional3"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByAdicional4", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.adicional4 = :adicional4"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByAdicional5", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.adicional5 = :adicional5"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByCreadoPor", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.creadoPor = :creadoPor"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByFechaCreacion", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByUltimaActualizacionPor", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.ultimaActualizacionPor = :ultimaActualizacionPor"),
    @NamedQuery(name = "ErClientesDirFiscalesTbl.findByUltimaFechaActualizacion", query = "SELECT e FROM ErClientesDirFiscalesTbl e WHERE e.ultimaFechaActualizacion = :ultimaFechaActualizacion")})
 *
 */
public class ErClientesDirFiscalesTbl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CLIENTES_DIR_FISCALES_ID")
    private BigDecimal clientesDirFiscalesId;
    @Column(name = "CLIENTE_ID")
    private BigDecimal clienteId;
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    @Column(name = "RFC")
    private String rfc;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "TELEFONO_CELULAR")
    private String telefonoCelular;
    @Column(name = "TELEFONO_OFICINA")
    private String telefonoOficina;
    @Column(name = "TELEFONO_FAX")
    private String telefonoFax;
    @Column(name = "DOMICILIO")
    private String domicilio;
    @Column(name = "NO_INT")
    private String noInt;
    @Column(name = "NO_EXT")
    private String noExt;
    @Column(name = "CODIGOPOSTAL")
    private String codigopostal;
    @Column(name = "COLONIA")
    private String colonia;
    @Column(name = "CIUDAD")
    private String ciudad;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "PAIS")
    private String pais;
    /*@Column(name = "ADICIONAL1")
    private String adicional1;
    @Column(name = "ADICIONAL2")
    private String adicional2;
    @Column(name = "ADICIONAL3")
    private String adicional3;
    @Column(name = "ADICIONAL4")
    private String adicional4;
    @Column(name = "ADICIONAL5")
    private String adicional5;
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
    private Date ultimaFechaActualizacion;
     *
     */

    public ErClientesDirFiscalesTbl() {
    }

    public ErClientesDirFiscalesTbl(BigDecimal clientesDirFiscalesId) {
        this.clientesDirFiscalesId = clientesDirFiscalesId;
    }

    public ErClientesDirFiscalesTbl(BigDecimal clientesDirFiscalesId, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.clientesDirFiscalesId = clientesDirFiscalesId;
        /*this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
         *
         */
    }

        public ErClientesDirFiscalesTbl(Vector campos) {
         this.setClientesDirFiscalesId(new BigDecimal(campos.get(0).toString()));
        this.setClienteId(new BigDecimal(campos.get(1).toString()));
        this.setRazonSocial(campos.get(2)!= null? campos.get(2).toString():"");
        this.setRfc(campos.get(3)!= null? campos.get(3).toString():"");
        this.setTelefono(campos.get(4)!= null? campos.get(4).toString():"");
        this.setTelefonoCelular(campos.get(5)!= null? campos.get(5).toString():"");
        this.setTelefonoOficina(campos.get(6)!= null? campos.get(6).toString():"");
        this.setTelefonoFax(campos.get(7)!= null? campos.get(7).toString():"");
        this.setDomicilio(campos.get(8)!= null? campos.get(8).toString():"");
        this.setNoInt(campos.get(9)!= null? campos.get(9).toString():"");
        this.setNoExt(campos.get(10)!= null? campos.get(10).toString():"");
        this.setCodigopostal(campos.get(11)!= null? campos.get(11).toString():"");
        this.setColonia(campos.get(12)!= null? campos.get(12).toString():"");
        this.setCiudad(campos.get(13) != null? campos.get(13).toString():"");
        this.setEstado(campos.get(14) != null? campos.get(14).toString():"");
        this.setPais(campos.get(15) != null? campos.get(15).toString():"");
         }

    public BigDecimal getClientesDirFiscalesId() {
        return clientesDirFiscalesId;
    }

    public void setClientesDirFiscalesId(BigDecimal clientesDirFiscalesId) {
        this.clientesDirFiscalesId = clientesDirFiscalesId;
    }

    public BigDecimal getClienteId() {
        return clienteId;
    }

    public void setClienteId(BigDecimal clienteId) {
        this.clienteId = clienteId;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNoInt() {
        return noInt;
    }

    public void setNoInt(String noInt) {
        this.noInt = noInt;
    }

    public String getNoExt() {
        return noExt;
    }

    public void setNoExt(String noExt) {
        this.noExt = noExt;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
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
    public String getAdicional1() {
        return adicional1;
    }

    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    public String getAdicional2() {
        return adicional2;
    }

    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    public String getAdicional3() {
        return adicional3;
    }

    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    public String getAdicional4() {
        return adicional4;
    }

    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    public String getAdicional5() {
        return adicional5;
    }

    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

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
     *
     */

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientesDirFiscalesId != null ? clientesDirFiscalesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErClientesDirFiscalesTbl)) {
            return false;
        }
        ErClientesDirFiscalesTbl other = (ErClientesDirFiscalesTbl) object;
        if ((this.clientesDirFiscalesId == null && other.clientesDirFiscalesId != null) || (this.clientesDirFiscalesId != null && !this.clientesDirFiscalesId.equals(other.clientesDirFiscalesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.entidades.ErClientesDirFiscalesTbl[clientesDirFiscalesId=" + clientesDirFiscalesId + "]";
    }

}
