/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
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
@Table(name = "ER_CLIENTES_TBL")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CLIENTE_ID")
    private BigDecimal clienteId;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APATERNO")
    private String apaterno;
    @Column(name = "AMATERNO")
    private String amaterno;
    @Basic(optional = false)
    @Column(name = "PERSONA_MORAL")
    private String personaMoral;
    @Column(name = "NOMBRE_FISCAL")
    private String nombreFiscal;
    @Column(name = "DIRECCION_FISCAL")
    private String direccionFiscal;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "TELEFONO_CELULAR")
    private String telefonoCelular;
    @Column(name = "TELEFONO_OFICINA")
    private String telefonoOficina;
    @Column(name = "TELEFONO_FAX")
    private String telefonoFax;
    @Column(name = "CALLE")
    private String calle;
    @Column(name = "NO_EXT")
    private String noExt;
    @Column(name = "NO_INT")
    private String noInt;
    @Column(name = "CODIGO_POSTAL")
    private String codigoPostal;
    @Column(name = "COLONIA")
    private String colonia;
    @Column(name = "CIUDAD")
    private String ciudad;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "PAIS")
    private String pais;
    @Column(name = "RFC")
    private String rfc;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CONTACTO")
    private String contacto;
    @Basic(optional = false)
    @Column(name = "ACTIVO")
    private String activo;
    @Column(name = "MOTIVO_SUSPENCION")
    private String motivoSuspencion;
    @Column(name = "CLAVE_USUARIO")
    private String claveUsuario;
    @Column(name = "CONTRASENA_USUARIO")
    private String contrasenaUsuario;
    @Column(name = "TIPO_CLIENTE_ID")
    private BigInteger tipoClienteId;
    @Basic(optional = false)
    @Column(name = "APLICA_RETENCION")
    private String aplicaRetencion;
    @Column(name = "NO_CUENTA")
    private String noCuenta;
    @Column(name = "REFERENCIA")
    private String referencia;
    @Column(name = "CREDITO_ACTIVO")
    private String creditoActivo;
    @Column(name = "LIMITE_CREDITO")
    private BigDecimal limiteCredito;
    @Basic(optional = false)
    @Column(name = "SALDO_ACTUAL")
    private BigDecimal saldoActual;
    @Basic(optional = false)
    @Column(name = "SALDO_FAVOR")
    private BigDecimal saldoFavor;
    @Basic(optional = false)
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "FECHA_BAJA")
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
  

   
    public Cliente() {
    }


    public Cliente(String[] campos) {
        this.setClienteId(new BigDecimal(campos[0]));
        this.setNombre(campos[1]);
        this.setApaterno(campos[2]);
        this.setAmaterno(campos[3]);
        this.setPersonaMoral(campos[4]);
        this.setNombreFiscal(campos[5]);
        this.setDireccionFiscal(campos[6]);
        this.setTelefono(campos[7]);
        this.setTelefonoCelular(campos[8]);
        this.setTelefonoOficina(campos[9]);
        this.setTelefonoFax(campos[10]);
        this.setCalle(campos[11]);
        this.setNoExt(campos[12]);
        this.setNoInt(campos[13]);
        this.setCodigoPostal(campos[14]);
        this.setColonia(campos[15]);
        this.setCiudad(campos[16]);
        this.setEstado(campos[17]);
        this.setPais(campos[18]);
        this.setRfc(campos[19]);
        this.setEmail(campos[20]);
        this.setContacto(campos[21]);
        this.setActivo(campos[22]);
        this.setMotivoSuspencion(campos[23]);
        this.setClaveUsuario(campos[24]);
        this.setContrasenaUsuario(campos[25]);
        this.setTipoClienteId(new BigInteger(campos[26]));
        this.setAplicaRetencion(campos[27]);
        this.setNoCuenta(campos[28]);
        this.setReferencia(campos[29]);
        this.setCreditoActivo(campos[30]);
        this.setLimiteCredito(new BigDecimal(campos[31]));
        this.setSaldoActual(new BigDecimal(campos[32]));
        this.setSaldoFavor(new BigDecimal(campos[33]));
        Timestamp t = null;
        t = t.valueOf(campos[34]);
        this.setFechaRegistro(new Date(t.getTime()));
        t = null;
        if(campos.length==36 && campos[35]!=null)
        {
            t = t.valueOf(campos[35]);
            this.setFechaRegistro(new Date(t.getTime()));
        }
    }

    public Cliente(BigDecimal clienteId) {
        this.clienteId = clienteId;
    }

    public Cliente(BigDecimal clienteId, String nombre, String personaMoral, String activo, String aplicaRetencion, BigDecimal saldoActual, BigDecimal saldoFavor, Date fechaRegistro, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.personaMoral = personaMoral;
        this.activo = activo;
        this.aplicaRetencion = aplicaRetencion;
        this.saldoActual = saldoActual;
        this.saldoFavor = saldoFavor;
        this.fechaRegistro = fechaRegistro;
    }

    public BigDecimal getClienteId() {
        return clienteId;
    }

    public void setClienteId(BigDecimal clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getPersonaMoral() {
        return personaMoral;
    }

    public void setPersonaMoral(String personaMoral) {
        this.personaMoral = personaMoral;
    }

    public String getNombreFiscal() {
        return nombreFiscal;
    }

    public void setNombreFiscal(String nombreFiscal) {
        this.nombreFiscal = nombreFiscal;
    }

    public String getDireccionFiscal() {
        return direccionFiscal;
    }

    public void setDireccionFiscal(String direccionFiscal) {
        this.direccionFiscal = direccionFiscal;
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

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getMotivoSuspencion() {
        return motivoSuspencion;
    }

    public void setMotivoSuspencion(String motivoSuspencion) {
        this.motivoSuspencion = motivoSuspencion;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public BigInteger getTipoClienteId() {
        return tipoClienteId;
    }

    public void setTipoClienteId(BigInteger tipoClienteId) {
        this.tipoClienteId = tipoClienteId;
    }

    public String getAplicaRetencion() {
        return aplicaRetencion;
    }

    public void setAplicaRetencion(String aplicaRetencion) {
        this.aplicaRetencion = aplicaRetencion;
    }

    public String getNoCuenta() {
        return noCuenta;
    }

    public void setNoCuenta(String noCuenta) {
        this.noCuenta = noCuenta;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCreditoActivo() {
        return creditoActivo;
    }

    public void setCreditoActivo(String creditoActivo) {
        this.creditoActivo = creditoActivo;
    }

    public BigDecimal getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(BigDecimal limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigDecimal saldoActual) {
        this.saldoActual = saldoActual;
    }

    public BigDecimal getSaldoFavor() {
        return saldoFavor;
    }

    public void setSaldoFavor(BigDecimal saldoFavor) {
        this.saldoFavor = saldoFavor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clienteId != null ? clienteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.clienteId == null && other.clienteId != null) || (this.clienteId != null && !this.clienteId.equals(other.clienteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.entidades.Cliente[clienteId=" + clienteId + "]";
    }



}
