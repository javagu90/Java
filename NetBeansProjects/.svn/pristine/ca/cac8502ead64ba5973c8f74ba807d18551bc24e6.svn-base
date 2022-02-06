/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 *
 * @author brojas
 */
@Entity
@Table(name = "ER_USUARIOS_TBL")
/*@NamedQueries({
    @NamedQuery(name = "ErUsuariosTbl.findAll", query = "SELECT e FROM ErUsuariosTbl e"),
    @NamedQuery(name = "ErUsuariosTbl.findByUsuarioId", query = "SELECT e FROM ErUsuariosTbl e WHERE e.usuarioId = :usuarioId"),
    @NamedQuery(name = "ErUsuariosTbl.findByUsuarioNumero", query = "SELECT e FROM ErUsuariosTbl e WHERE e.usuarioNumero = :usuarioNumero"),
    @NamedQuery(name = "ErUsuariosTbl.findByUsuarioNombre", query = "SELECT e FROM ErUsuariosTbl e WHERE e.usuarioNombre = :usuarioNombre"),
    @NamedQuery(name = "ErUsuariosTbl.findByDescripcion", query = "SELECT e FROM ErUsuariosTbl e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "ErUsuariosTbl.findByFechaInicial", query = "SELECT e FROM ErUsuariosTbl e WHERE e.fechaInicial = :fechaInicial"),
    @NamedQuery(name = "ErUsuariosTbl.findByFechaFinal", query = "SELECT e FROM ErUsuariosTbl e WHERE e.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "ErUsuariosTbl.findByContrasenaEncriptada", query = "SELECT e FROM ErUsuariosTbl e WHERE e.contrasenaEncriptada = :contrasenaEncriptada"),
    @NamedQuery(name = "ErUsuariosTbl.findByContrasenaFecha", query = "SELECT e FROM ErUsuariosTbl e WHERE e.contrasenaFecha = :contrasenaFecha"),
    @NamedQuery(name = "ErUsuariosTbl.findByContrasenaAccesosFaltan", query = "SELECT e FROM ErUsuariosTbl e WHERE e.contrasenaAccesosFaltan = :contrasenaAccesosFaltan"),
    @NamedQuery(name = "ErUsuariosTbl.findByContrasenaLimiteDias", query = "SELECT e FROM ErUsuariosTbl e WHERE e.contrasenaLimiteDias = :contrasenaLimiteDias"),
    @NamedQuery(name = "ErUsuariosTbl.findByContrasenaLimiteAccesos", query = "SELECT e FROM ErUsuariosTbl e WHERE e.contrasenaLimiteAccesos = :contrasenaLimiteAccesos"),
    @NamedQuery(name = "ErUsuariosTbl.findByCorreoElectronico", query = "SELECT e FROM ErUsuariosTbl e WHERE e.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "ErUsuariosTbl.findByEstado", query = "SELECT e FROM ErUsuariosTbl e WHERE e.estado = :estado"),
    @NamedQuery(name = "ErUsuariosTbl.findByContadorIntentos", query = "SELECT e FROM ErUsuariosTbl e WHERE e.contadorIntentos = :contadorIntentos"),
    @NamedQuery(name = "ErUsuariosTbl.findByCreadoPor", query = "SELECT e FROM ErUsuariosTbl e WHERE e.creadoPor = :creadoPor"),
    @NamedQuery(name = "ErUsuariosTbl.findByFechaCreacion", query = "SELECT e FROM ErUsuariosTbl e WHERE e.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ErUsuariosTbl.findByUltimaActualizacionPor", query = "SELECT e FROM ErUsuariosTbl e WHERE e.ultimaActualizacionPor = :ultimaActualizacionPor"),
    @NamedQuery(name = "ErUsuariosTbl.findByUltimaFechaActualizacion", query = "SELECT e FROM ErUsuariosTbl e WHERE e.ultimaFechaActualizacion = :ultimaFechaActualizacion")})
 *
 *
 */
public class ErUsuariosTbl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USUARIO_ID")
    private BigDecimal usuarioId;
    @Column(name = "USUARIO_NUMERO")
    private String usuarioNumero;
    @Basic(optional = false)
    @Column(name = "USUARIO_NOMBRE")
    private String usuarioNombre;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "FECHA_INICIAL")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "FECHA_FINAL")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Basic(optional = false)
    @Column(name = "CONTRASENA_ENCRIPTADA")
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
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "CONTADOR_INTENTOS")
    private BigInteger contadorIntentos;
    @Basic(optional = false)
   /* @Column(name = "CREADO_POR")
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
    //@JoinColumn(name = "TIPO_USUARIO_ID", referencedColumnName = "TIPO_USUARIO_ID")
    //@ManyToOne
    @Column(name = "TIPO_USUARIO_ID")
    private BigDecimal tipo_usuario_Id;
    @Column(name = "UNIDAD_NEGOCIO_ID")
    private BigDecimal unidad_negocio_Id;

    @Column(name = "USUARIO_RELACIONADO_ID")
    private  BigDecimal usuario_relacionado_id;

    private List<ErUsuariosTbl> UsuariosRelacionados;

  //  private ErTiposUsuarioTbl erTiposUsuarioTbl;

    public ErUsuariosTbl(String[] campos) {
        DateFormat formatter ;
        Date date ;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.setUsuarioId(new BigDecimal(campos[0]));
        this.setUsuarioNumero(campos[1]);
        this.setUsuarioNombre(campos[2]);
        this.setDescripcion(campos[3]);
        try {
          if(campos[4]!=null && !campos[4].trim().equals(""))
        {
            System.out.println("Fecha  "+campos[4].trim());
            this.setFechaInicial((Date)formatter.parse(campos[4].trim()));
         }
          if(campos[5]!=null && !campos[5].trim().equals(""))
        {
             System.out.println("Fecha  "+campos[5].trim());
            this.setFechaFinal((Date)formatter.parse(campos[5].trim()));

        }
           if(campos[7]!=null && !campos[7].trim().equals(""))
        {
             System.out.println("Fecha  "+campos[7].trim());
            this.setContrasenaFecha((Date)formatter.parse(campos[7].trim()));

            //this.setContrasenaFecha(new Date(fecha.getTime()));
        }   
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        this.setContrasenaEncriptada(campos[6]!= null? campos[6]:"" );

      
        this.setContrasenaAccesosFaltan(campos[8] != null ? new BigInteger(campos[8]):new BigInteger("0"));
        this.setContrasenaLimiteDias(campos[9] != null  && campos[9].equalsIgnoreCase("NULL")? new BigInteger(campos[9]) : new BigInteger("0"));
        this.setContrasenaLimiteAccesos(campos[10] !=null  && campos[10].equalsIgnoreCase("NULL")? new BigInteger(campos[10]): new BigInteger("0") );
        this.setCorreoElectronico(campos[11] != null? campos[11]:"");
        this.setEstado(campos[12]);
        this.setContadorIntentos(campos[13] !=null  && campos[13].equalsIgnoreCase("NULL")? new BigInteger(campos[13]): new BigInteger("0") );
        System.out.println("Tipo_usuario_Id  "+campos[14]);
        this.setTipo_usuario_Id((campos[14] !=null  && campos[14].equalsIgnoreCase("NULL") ? new BigDecimal(campos[14]): new BigDecimal("0") ));
        this.setUnidad_negocio_Id((campos[15] !=null  && campos[15].equalsIgnoreCase("NULL")? new BigDecimal(campos[15]): new BigDecimal("0") ));
        this.setUsuario_relacionado_id((campos[16] !=null  && campos[16].equalsIgnoreCase("NULL") ? new BigDecimal(campos[16]): new BigDecimal("0")) );
    
    }


    public ErUsuariosTbl() {
    }

    public ErUsuariosTbl(BigDecimal usuarioId) {
        this.usuarioId = usuarioId;
    }

    public ErUsuariosTbl(BigDecimal usuarioId, String usuarioNombre, Date fechaInicial, String contrasenaEncriptada, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.fechaInicial = fechaInicial;
        this.contrasenaEncriptada = contrasenaEncriptada;
      /*  this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
        this.ultimaActualizacionPor = ultimaActualizacionPor;
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;*/
    }

    public BigDecimal getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(BigDecimal usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNumero() {
        return usuarioNumero;
    }

    public void setUsuarioNumero(String usuarioNumero) {
        this.usuarioNumero = usuarioNumero;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getContrasenaEncriptada() {
        return contrasenaEncriptada;
    }

    public void setContrasenaEncriptada(String contrasenaEncriptada) {
        this.contrasenaEncriptada = contrasenaEncriptada;
    }

    public Date getContrasenaFecha() {
        return contrasenaFecha;
    }

    public void setContrasenaFecha(Date contrasenaFecha) {
        this.contrasenaFecha = contrasenaFecha;
    }

    public BigInteger getContrasenaAccesosFaltan() {
        return contrasenaAccesosFaltan;
    }

    public void setContrasenaAccesosFaltan(BigInteger contrasenaAccesosFaltan) {
        this.contrasenaAccesosFaltan = contrasenaAccesosFaltan;
    }

    public BigInteger getContrasenaLimiteDias() {
        return contrasenaLimiteDias;
    }

    public void setContrasenaLimiteDias(BigInteger contrasenaLimiteDias) {
        this.contrasenaLimiteDias = contrasenaLimiteDias;
    }

    public BigInteger getContrasenaLimiteAccesos() {
        return contrasenaLimiteAccesos;
    }

    public void setContrasenaLimiteAccesos(BigInteger contrasenaLimiteAccesos) {
        this.contrasenaLimiteAccesos = contrasenaLimiteAccesos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigInteger getContadorIntentos() {
        return contadorIntentos;
    }

    public void setContadorIntentos(BigInteger contadorIntentos) {
        this.contadorIntentos = contadorIntentos;
    }

/*    public BigInteger getCreadoPor() {
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
    }*/

  /*  public ErTiposUsuarioTbl getErTiposUsuarioTbl() {
        return erTiposUsuarioTbl;
    }

    public void setErTiposUsuarioTbl(ErTiposUsuarioTbl erTiposUsuarioTbl) {
        this.erTiposUsuarioTbl = erTiposUsuarioTbl;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErUsuariosTbl)) {
            return false;
        }
        ErUsuariosTbl other = (ErUsuariosTbl) object;
        if ((this.usuarioId == null && other.usuarioId != null) || (this.usuarioId != null && !this.usuarioId.equals(other.usuarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.entidades.ErUsuariosTbl[usuarioId=" + usuarioId + "]";
    }

    /**
     * @return the tipo_usuario_Id
     */
    public BigDecimal getTipo_usuario_Id() {
        return tipo_usuario_Id;
    }

    /**
     * @param tipo_usuario_Id the tipo_usuario_Id to set
     */
    public void setTipo_usuario_Id(BigDecimal tipo_usuario_Id) {
        this.tipo_usuario_Id = tipo_usuario_Id;
    }

    /**
     * @return the unidad_negocio_Id
     */
    public BigDecimal getUnidad_negocio_Id() {
        return unidad_negocio_Id;
    }

    /**
     * @param unidad_negocio_Id the unidad_negocio_Id to set
     */
    public void setUnidad_negocio_Id(BigDecimal unidad_negocio_Id) {
        this.unidad_negocio_Id = unidad_negocio_Id;
    }

    /**
     * @return the usuario_relacionado_id
     */
    public BigDecimal getUsuario_relacionado_id() {
        return usuario_relacionado_id;
    }

    /**
     * @param usuario_relacionado_id the usuario_relacionado_id to set
     */
    public void setUsuario_relacionado_id(BigDecimal usuario_relacionado_id) {
        this.usuario_relacionado_id = usuario_relacionado_id;
    }

    /**
     * @return the UsuariosRelacionados
     */
    public List<ErUsuariosTbl> getUsuariosRelacionados() {
        return UsuariosRelacionados;
    }

    /**
     * @param UsuariosRelacionados the UsuariosRelacionados to set
     */
    public void setUsuariosRelacionados(List<ErUsuariosTbl> UsuariosRelacionados) {
        this.UsuariosRelacionados= UsuariosRelacionados;
    }

}
