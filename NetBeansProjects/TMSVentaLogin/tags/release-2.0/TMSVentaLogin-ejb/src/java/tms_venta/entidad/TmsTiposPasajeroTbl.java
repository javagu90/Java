/*
 * TmsTiposPasajeroTbl.java
 *
 * Created on 30 de mayo de 2007, 12:37 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_venta.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Entity class TmsTiposPasajeroTbl
 * 
 * @author imunoz
 */
@Entity
@Table(name = "TMS_TIPOS_PASAJERO_TBL")
@NamedQueries( {
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByTipoPasajeroId", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.tipoPasajeroId = :tipoPasajeroId"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByNombreTipo", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.nombreTipo = :nombreTipo"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByLetraTipo", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.letraTipo = :letraTipo"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByPctDescuento", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.pctDescuento = :pctDescuento"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByFechaDesde", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.fechaDesde = :fechaDesde"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByFechaHasta", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.fechaHasta = :fechaHasta"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByCreadoPor", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.creadoPor = :creadoPor"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByFechaCreacion", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.fechaCreacion = :fechaCreacion"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByUltimaActualizacionPor", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.ultimaActualizacionPor = :ultimaActualizacionPor"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findByUltimaFechaActualizacion", query = "SELECT t FROM TmsTiposPasajeroTbl t WHERE t.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
        @NamedQuery(name = "TmsTiposPasajeroTbl.findAll", query = "select o from TmsTiposPasajeroTbl o")
    })
public class TmsTiposPasajeroTbl implements Serializable {

    @Id
    @Column(name = "DESCUENTO_RUTA_ID", nullable = false)
    private Long descuentRutaId;

    @Column(name = "TIPO_PASAJERO_ID", nullable = false)
    private Long tipoPasajeroId;
    
    @Column(name = "NOMBRE_TIPO", nullable = false)
    private String nombreTipo;

    @Column(name = "LETRA_TIPO")
    private String letraTipo;

    @Column(name = "PCT_DESCUENTO", nullable = false)
    private Double pctDescuento;

    @Column(name = "FECHA_DESDE")
    @Temporal(TemporalType.DATE)    
    private Date fechaDesde;

    @Column(name = "FECHA_HASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;

    @Column(name = "CREADO_POR")
    private Integer creadoPor;

    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private Integer ultimaActualizacionPor;

    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;
    
    @Column(name = "RUTA_ID", nullable = false)
    private Integer rutaId;

    @Column(name = "REDONDEO", nullable = false)
    private String redondeo;

   //@Column(name = "LEALTAD")
    @Transient
    private String lealtad;

    //@Column(name = "APLICA_TIPO_LEALTAD")
    @Transient
    private String aplicaTipoLealtad;

    //@Column(name = "APLICA_LEALTAD")
    private String aplicaLealtad;
    
    
    @Transient
    private String L_LUNES;
    
    @Transient
    private String L_MARTES;

    @Transient
    private String L_MIERCOLES;
    
    @Transient
    private String L_JUEVES;

    @Transient
    private String L_VIERNES;

    @Transient
    private String L_SABADO;

    @Transient
    private String L_DOMINGO;
    /** Creates a new instance of TmsTiposPasajeroTbl */
    public TmsTiposPasajeroTbl() {
    }

   public TmsTiposPasajeroTbl(Vector v) {
       this.setDescuentRutaId(( v.get(0)==null ) ?-1 :Long.valueOf(v.get(0).toString()));
       this.setTipoPasajeroId (Long.valueOf( v.get(1).toString()) );
       this.setNombreTipo ( v.get(2).toString() );
       this.setLetraTipo ( v.get(3).toString() );
       this.setPctDescuento (Double.valueOf( v.get(4).toString()) );
       this.setRutaId (Integer.valueOf(v.get(11).toString()) );
       this.setRedondeo (( v.get(12)==null ) ?"" : v.get(12).toString() );
       this.setLealtad (( v.get(13)==null ) ?"" :v.get(13).toString() );
       this.setAplicaTipoLealtad (( v.get(14)==null ) ?"N" : v.get(14).toString() );
       this.setAplicaLealtad (( v.get(15)==null ) ?"N" : v.get(15).toString() );
       this.setLealtades();
//       this.set ( v.get().toString() );
    }    
    /**
     * Gets the tipoPasajeroId of this TmsTiposPasajeroTbl.
     * @return the tipoPasajeroId
     */
    public Long getTipoPasajeroId() {
        return this.tipoPasajeroId;
    }

    /**
     * Sets the tipoPasajeroId of this TmsTiposPasajeroTbl to the specified value.
     * @param tipoPasajeroId the new tipoPasajeroId
     */
    public void setTipoPasajeroId(Long tipoPasajeroId) {
        this.tipoPasajeroId = tipoPasajeroId;
    }

    /**
     * Gets the nombreTipo of this TmsTiposPasajeroTbl.
     * @return the nombreTipo
     */
    public String getNombreTipo() {
        return this.nombreTipo;
    }

    /**
     * Sets the nombreTipo of this TmsTiposPasajeroTbl to the specified value.
     * @param nombreTipo the new nombreTipo
     */
    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    /**
     * Gets the letraTipo of this TmsTiposPasajeroTbl.
     * @return the letraTipo
     */
    public String getLetraTipo() {
        return this.letraTipo;
    }

    /**
     * Sets the letraTipo of this TmsTiposPasajeroTbl to the specified value.
     * @param letraTipo the new letraTipo
     */
    public void setLetraTipo(String letraTipo) {
        this.letraTipo = letraTipo;
    }

    /**
     * Gets the pctDescuento of this TmsTiposPasajeroTbl.
     * @return the pctDescuento
     */
    public Double getPctDescuento() {
        return this.pctDescuento;
    }

    /**
     * Sets the pctDescuento of this TmsTiposPasajeroTbl to the specified value.
     * @param pctDescuento the new pctDescuento
     */
    public void setPctDescuento(Double pctDescuento) {
        this.pctDescuento = pctDescuento;
    }

    /**
     * Gets the fechaDesde of this TmsTiposPasajeroTbl.
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
        return this.fechaDesde;
    }

    /**
     * Sets the fechaDesde of this TmsTiposPasajeroTbl to the specified value.
     * @param fechaDesde the new fechaDesde
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Gets the fechaHasta of this TmsTiposPasajeroTbl.
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
        return this.fechaHasta;
    }

    /**
     * Sets the fechaHasta of this TmsTiposPasajeroTbl to the specified value.
     * @param fechaHasta the new fechaHasta
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * Gets the creadoPor of this TmsTiposPasajeroTbl.
     * @return the creadoPor
     */
    public Integer getCreadoPor() {
        return this.creadoPor;
    }

    /**
     * Sets the creadoPor of this TmsTiposPasajeroTbl to the specified value.
     * @param creadoPor the new creadoPor
     */
    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * Gets the fechaCreacion of this TmsTiposPasajeroTbl.
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Sets the fechaCreacion of this TmsTiposPasajeroTbl to the specified value.
     * @param fechaCreacion the new fechaCreacion
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Gets the ultimaActualizacionPor of this TmsTiposPasajeroTbl.
     * @return the ultimaActualizacionPor
     */
    public Integer getUltimaActualizacionPor() {
        return this.ultimaActualizacionPor;
    }

    /**
     * Sets the ultimaActualizacionPor of this TmsTiposPasajeroTbl to the specified value.
     * @param ultimaActualizacionPor the new ultimaActualizacionPor
     */
    public void setUltimaActualizacionPor(Integer ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * Gets the ultimaFechaActualizacion of this TmsTiposPasajeroTbl.
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return this.ultimaFechaActualizacion;
    }

    /**
     * Sets the ultimaFechaActualizacion of this TmsTiposPasajeroTbl to the specified value.
     * @param ultimaFechaActualizacion the new ultimaFechaActualizacion
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * Returns a hash code value for the object.  This implementation computes 
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.tipoPasajeroId != null ? this.tipoPasajeroId.hashCode() : 0);
        return hash;
    }

    /**
     * Determines whether another object is equal to this TmsTiposPasajeroTbl.  The result is 
     * <code>true</code> if and only if the argument is not null and is a TmsTiposPasajeroTbl object that 
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TmsTiposPasajeroTbl)) {
            return false;
        }
        TmsTiposPasajeroTbl other = (TmsTiposPasajeroTbl)object;
        if (this.tipoPasajeroId != other.tipoPasajeroId && (this.tipoPasajeroId == null || !this.tipoPasajeroId.equals(other.tipoPasajeroId))) return false;
        return true;
    }

    /**
     * Returns a string representation of the object.  This implementation constructs 
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "tmsventalogin.entidad.TmsTiposPasajeroTbl[tipoPasajeroId=" + tipoPasajeroId + "]";
    }
    
    /**
     * Gets the rutaId of this TmsDescuentosRutasTbl.
     * @return the rutaId
     */
    public Integer getRutaId() {
        return this.rutaId;
    }

    /**
     * Sets the rutaId of this TmsDescuentosRutasTbl to the specified value.
     * @param rutaId the new rutaId
     */
    public void setRutaId(Integer rutaId) {
        this.rutaId = rutaId;
    }

    public Long getDescuentRutaId() {
        return descuentRutaId;
    }

    public void setDescuentRutaId(Long descuentRutaId) {
        this.descuentRutaId = descuentRutaId;
    }

    public String getRedondeo() {
        return redondeo;
    }

    public void setRedondeo(String redondeo) {
        this.redondeo = redondeo;
    }

    public String getLealtad() {
        return lealtad;
    }

    public void setLealtad(String lealtad) {
        this.lealtad = lealtad;
        //System.out.println("Lealtad("+this.getRutaId()+"): "+lealtad);
        if(lealtad!=null){
            if(!lealtad.equals(""))
            {
                String[] arreglo = lealtad.split(",");
                this.setL_LUNES(arreglo[0]);
                this.setL_MARTES(arreglo[1]);
                this.setL_MIERCOLES(arreglo[2]);
                this.setL_JUEVES(arreglo[3]);
                this.setL_VIERNES(arreglo[4]);
                this.setL_SABADO(arreglo[5]);
                this.setL_DOMINGO(arreglo[6]);
            }
        }
    }

    public void setLealtades() {
        //this.lealtad = lealtad;
        //System.out.println("Lealtad("+this.getRutaId()+"): "+lealtad);
        if(this.lealtad!=null){
            if(!this.lealtad.equals(""))
            {
                String[] arreglo = this.lealtad.split(",");
                this.setL_LUNES((arreglo[0] == null) ?"N" :arreglo[0]);
                this.setL_MARTES((arreglo[1] == null) ?"N" :arreglo[1]);
                this.setL_MIERCOLES((arreglo[2] == null) ?"N" :arreglo[2]);
                this.setL_JUEVES((arreglo[3] == null) ?"N" :arreglo[3]);
                this.setL_VIERNES((arreglo[4] == null) ?"N" :arreglo[4]);
                this.setL_SABADO((arreglo[5] == null) ?"N" :arreglo[5]);
                this.setL_DOMINGO((arreglo[6] == null) ?"N" :arreglo[6]);
            }
        }
    }    
    
    public String getL_LUNES() {
        return L_LUNES;
    }

    public void setL_LUNES(String L_LUNES) {
        this.L_LUNES = L_LUNES;
    }

    public String getL_MARTES() {
        return L_MARTES;
    }

    public void setL_MARTES(String L_MARTES) {
        this.L_MARTES = L_MARTES;
    }

    public String getL_MIERCOLES() {
        return L_MIERCOLES;
    }

    public void setL_MIERCOLES(String L_MIERCOLES) {
        this.L_MIERCOLES = L_MIERCOLES;
    }

    public String getL_JUEVES() {
        return L_JUEVES;
    }

    public void setL_JUEVES(String L_JUEVES) {
        this.L_JUEVES = L_JUEVES;
    }

    public String getL_VIERNES() {
        return L_VIERNES;
    }

    public void setL_VIERNES(String L_VIERNES) {
        this.L_VIERNES = L_VIERNES;
    }

    public String getL_SABADO() {
        return L_SABADO;
    }

    public void setL_SABADO(String L_SABADO) {
        this.L_SABADO = L_SABADO;
    }

    public String getL_DOMINGO() {
        return L_DOMINGO;
    }

    public void setL_DOMINGO(String L_DOMINGO) {
        this.L_DOMINGO = L_DOMINGO;
    }

    public String getAplicaTipoLealtad() {
        return aplicaTipoLealtad;
    }

    public void setAplicaTipoLealtad(String aplicaTipoLealtad) {
        this.aplicaTipoLealtad = aplicaTipoLealtad;
    }

    public String getAplicaLealtad() {
        return aplicaLealtad;
    }

    public void setAplicaLealtad(String aplicaLealtad) {
        this.aplicaLealtad = aplicaLealtad;
    }
    
    }
