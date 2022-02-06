/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
 * @author brojas
 */
@Entity
@Table(name = "ER_CLIENTES_TBL")
/*@NamedQueries({
    @NamedQuery(name = "ErClientesTbl.findAll", query = "SELECT e FROM ErClientesTbl e"),
    @NamedQuery(name = "ErClientesTbl.findByClienteId", query = "SELECT e FROM ErClientesTbl e WHERE e.clienteId = :clienteId"),
    @NamedQuery(name = "ErClientesTbl.findByNombre", query = "SELECT e FROM ErClientesTbl e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "ErClientesTbl.findByApaterno", query = "SELECT e FROM ErClientesTbl e WHERE e.apaterno = :apaterno"),
    @NamedQuery(name = "ErClientesTbl.findByAmaterno", query = "SELECT e FROM ErClientesTbl e WHERE e.amaterno = :amaterno"),
    @NamedQuery(name = "ErClientesTbl.findByPersonaMoral", query = "SELECT e FROM ErClientesTbl e WHERE e.personaMoral = :personaMoral"),
    @NamedQuery(name = "ErClientesTbl.findByNombreFiscal", query = "SELECT e FROM ErClientesTbl e WHERE e.nombreFiscal = :nombreFiscal"),
    @NamedQuery(name = "ErClientesTbl.findByDireccionFiscal", query = "SELECT e FROM ErClientesTbl e WHERE e.direccionFiscal = :direccionFiscal"),
    @NamedQuery(name = "ErClientesTbl.findByTelefono", query = "SELECT e FROM ErClientesTbl e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "ErClientesTbl.findByTelefonoCelular", query = "SELECT e FROM ErClientesTbl e WHERE e.telefonoCelular = :telefonoCelular"),
    @NamedQuery(name = "ErClientesTbl.findByTelefonoOficina", query = "SELECT e FROM ErClientesTbl e WHERE e.telefonoOficina = :telefonoOficina"),
    @NamedQuery(name = "ErClientesTbl.findByTelefonoFax", query = "SELECT e FROM ErClientesTbl e WHERE e.telefonoFax = :telefonoFax"),
    @NamedQuery(name = "ErClientesTbl.findByCalle", query = "SELECT e FROM ErClientesTbl e WHERE e.calle = :calle"),
    @NamedQuery(name = "ErClientesTbl.findByNoExt", query = "SELECT e FROM ErClientesTbl e WHERE e.noExt = :noExt"),
    @NamedQuery(name = "ErClientesTbl.findByNoInt", query = "SELECT e FROM ErClientesTbl e WHERE e.noInt = :noInt"),
    @NamedQuery(name = "ErClientesTbl.findByCodigoPostal", query = "SELECT e FROM ErClientesTbl e WHERE e.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "ErClientesTbl.findByColonia", query = "SELECT e FROM ErClientesTbl e WHERE e.colonia = :colonia"),
    @NamedQuery(name = "ErClientesTbl.findByCiudad", query = "SELECT e FROM ErClientesTbl e WHERE e.ciudad = :ciudad"),
    @NamedQuery(name = "ErClientesTbl.findByEstado", query = "SELECT e FROM ErClientesTbl e WHERE e.estado = :estado"),
    @NamedQuery(name = "ErClientesTbl.findByPais", query = "SELECT e FROM ErClientesTbl e WHERE e.pais = :pais"),
    @NamedQuery(name = "ErClientesTbl.findByRfc", query = "SELECT e FROM ErClientesTbl e WHERE e.rfc = :rfc"),
    @NamedQuery(name = "ErClientesTbl.findByEmail", query = "SELECT e FROM ErClientesTbl e WHERE e.email = :email"),
    @NamedQuery(name = "ErClientesTbl.findByContacto", query = "SELECT e FROM ErClientesTbl e WHERE e.contacto = :contacto"),
    @NamedQuery(name = "ErClientesTbl.findByActivo", query = "SELECT e FROM ErClientesTbl e WHERE e.activo = :activo"),
    @NamedQuery(name = "ErClientesTbl.findByMotivoSuspencion", query = "SELECT e FROM ErClientesTbl e WHERE e.motivoSuspencion = :motivoSuspencion"),
    @NamedQuery(name = "ErClientesTbl.findByClaveUsuario", query = "SELECT e FROM ErClientesTbl e WHERE e.claveUsuario = :claveUsuario"),
    @NamedQuery(name = "ErClientesTbl.findByContrasenaUsuario", query = "SELECT e FROM ErClientesTbl e WHERE e.contrasenaUsuario = :contrasenaUsuario"),
    @NamedQuery(name = "ErClientesTbl.findByTipoClienteId", query = "SELECT e FROM ErClientesTbl e WHERE e.tipoClienteId = :tipoClienteId"),
    @NamedQuery(name = "ErClientesTbl.findByAplicaRetencion", query = "SELECT e FROM ErClientesTbl e WHERE e.aplicaRetencion = :aplicaRetencion"),
    @NamedQuery(name = "ErClientesTbl.findByNoCuenta", query = "SELECT e FROM ErClientesTbl e WHERE e.noCuenta = :noCuenta"),
    @NamedQuery(name = "ErClientesTbl.findByReferencia", query = "SELECT e FROM ErClientesTbl e WHERE e.referencia = :referencia"),
    @NamedQuery(name = "ErClientesTbl.findByCreditoActivo", query = "SELECT e FROM ErClientesTbl e WHERE e.creditoActivo = :creditoActivo"),
    @NamedQuery(name = "ErClientesTbl.findByLimiteCredito", query = "SELECT e FROM ErClientesTbl e WHERE e.limiteCredito = :limiteCredito"),
    @NamedQuery(name = "ErClientesTbl.findBySaldoActual", query = "SELECT e FROM ErClientesTbl e WHERE e.saldoActual = :saldoActual"),
    @NamedQuery(name = "ErClientesTbl.findBySaldoFavor", query = "SELECT e FROM ErClientesTbl e WHERE e.saldoFavor = :saldoFavor"),
    @NamedQuery(name = "ErClientesTbl.findByFechaRegistro", query = "SELECT e FROM ErClientesTbl e WHERE e.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "ErClientesTbl.findByFechaBaja", query = "SELECT e FROM ErClientesTbl e WHERE e.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "ErClientesTbl.findByAdicional1", query = "SELECT e FROM ErClientesTbl e WHERE e.adicional1 = :adicional1"),
    @NamedQuery(name = "ErClientesTbl.findByAdicional2", query = "SELECT e FROM ErClientesTbl e WHERE e.adicional2 = :adicional2"),
    @NamedQuery(name = "ErClientesTbl.findByAdicional3", query = "SELECT e FROM ErClientesTbl e WHERE e.adicional3 = :adicional3"),
    @NamedQuery(name = "ErClientesTbl.findByAdicional4", query = "SELECT e FROM ErClientesTbl e WHERE e.adicional4 = :adicional4"),
    @NamedQuery(name = "ErClientesTbl.findByAdicional5", query = "SELECT e FROM ErClientesTbl e WHERE e.adicional5 = :adicional5"),
    @NamedQuery(name = "ErClientesTbl.findByAdicional6", query = "SELECT e FROM ErClientesTbl e WHERE e.adicional6 = :adicional6"),
    @NamedQuery(name = "ErClientesTbl.findByAdicional7", query = "SELECT e FROM ErClientesTbl e WHERE e.adicional7 = :adicional7"),
    @NamedQuery(name = "ErClientesTbl.findByAdicional8", query = "SELECT e FROM ErClientesTbl e WHERE e.adicional8 = :adicional8"),
    @NamedQuery(name = "ErClientesTbl.findByAdicional9", query = "SELECT e FROM ErClientesTbl e WHERE e.adicional9 = :adicional9"),
    @NamedQuery(name = "ErClientesTbl.findByAdicional10", query = "SELECT e FROM ErClientesTbl e WHERE e.adicional10 = :adicional10"),
    @NamedQuery(name = "ErClientesTbl.findByCreadoPor", query = "SELECT e FROM ErClientesTbl e WHERE e.creadoPor = :creadoPor"),
    @NamedQuery(name = "ErClientesTbl.findByFechaCreacion", query = "SELECT e FROM ErClientesTbl e WHERE e.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ErClientesTbl.findByUltimaActualizacionPor", query = "SELECT e FROM ErClientesTbl e WHERE e.ultimaActualizacionPor = :ultimaActualizacionPor"),
    @NamedQuery(name = "ErClientesTbl.findByUltimaFechaActualizacion", query = "SELECT e FROM ErClientesTbl e WHERE e.ultimaFechaActualizacion = :ultimaFechaActualizacion")})
 * 
 */
public class ErClientesTbl implements Serializable {
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
    @Column(name = "TIPO_CLIENTE")
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
    
    @Column(name = "GIRO_CLIENTE")
    private BigInteger giroClienteId;

    private List<ERClientesDireccionesTbl> direcciones;

   /* @Column(name = "ADICIONAL1")
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
*/
    public ErClientesTbl() {
    }

    public ErClientesTbl(BigDecimal clienteId) {
        this.clienteId = clienteId;
    }

    public ErClientesTbl(BigDecimal clienteId, String nombre, String personaMoral, String activo, String aplicaRetencion, BigDecimal saldoActual, BigDecimal saldoFavor, Date fechaRegistro, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.personaMoral = personaMoral;
        this.activo = activo;
        this.aplicaRetencion = aplicaRetencion;
        this.saldoActual = saldoActual;
        this.saldoFavor = saldoFavor;
        this.fechaRegistro = fechaRegistro;
     /*   this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;*/
    }

     public ErClientesTbl(String[] campos) {
        DateFormat formatter ;
        Date date ;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
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



        try {
          if(campos[34]!=null && !campos[34].trim().equals(""))
        {
            System.out.println("Fecha  "+campos[34].trim());
            this.setFechaRegistro((Date)formatter.parse(campos[34].trim()));
         }
          if(campos[35]!=null && !campos[35].trim().equals(""))
        {
             System.out.println("Fecha  "+campos[35].trim());  
           this.setFechaBaja((Date)formatter.parse(campos[35].trim()));
            // this.setFechaBaja(campos[35].trim());
             
     
        }
         this.setGiroClienteId(new BigInteger(campos[36]));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

 /*   public String getAdicional1() {   
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

    public String getAdicional6() {
        return adicional6;
    }

    public void setAdicional6(String adicional6) {
        this.adicional6 = adicional6;
    }

    public String getAdicional7() {
        return adicional7;
    }

    public void setAdicional7(String adicional7) {
        this.adicional7 = adicional7;
    }

    public String getAdicional8() {
        return adicional8;
    }

    public void setAdicional8(String adicional8) {
        this.adicional8 = adicional8;
    }

    public String getAdicional9() {
        return adicional9;
    }

    public void setAdicional9(String adicional9) {
        this.adicional9 = adicional9;
    }

    public String getAdicional10() {
        return adicional10;
    }

    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
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
*/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clienteId != null ? clienteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErClientesTbl)) {
            return false;
        }
        ErClientesTbl other = (ErClientesTbl) object;
        if ((this.clienteId == null && other.clienteId != null) || (this.clienteId != null && !this.clienteId.equals(other.clienteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.entidades.ErClientesTbl[clienteId=" + clienteId + "]";
    }

    /**
     * @return the giroClienteId
     */
    public BigInteger getGiroClienteId() {
        return giroClienteId;
    }

    /**
     * @param giroClienteId the giroClienteId to set
     */
    public void setGiroClienteId(BigInteger giroClienteId) {
        this.giroClienteId = giroClienteId;
    }

    /**
     * @return the direcciones
     */
    public List<ERClientesDireccionesTbl> getDirecciones() {
        return direcciones;
    }

    /**
     * @param direcciones the direcciones to set
     */
    public void setDirecciones(List<ERClientesDireccionesTbl> direcciones) {
        this.direcciones = direcciones;
    }

}
