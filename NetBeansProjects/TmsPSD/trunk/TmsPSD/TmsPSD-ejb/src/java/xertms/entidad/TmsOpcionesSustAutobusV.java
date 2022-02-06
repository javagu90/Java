/*
 * TmsOpcionesSustAutobusV.java
 *
 * Created on 15 de octubre de 2007, 02:19 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "TmsOpcionesSustAutobusV.findAll", 
    query = "select o from TmsOpcionesSustAutobusV o")
@Table(name = "TMS_OPCIONES_SUST_AUTOBUS_V")
public class TmsOpcionesSustAutobusV implements Serializable {
    private String actividad;
    @Column(name="ACTIVIDAD_ID", nullable = false)
    private Long actividadId;
    @Column(name="AUTOBUS_ID", nullable = false)
    private Long autobusId;
    private String edogral;
    @Column(name="EDOGRAL_ID", nullable = false)
    private Long edogralId;
    @Column(name="EMPRESA_ID", nullable = false)
    private Long empresaId;
    @Column(name="EMPRESA_NOMBRE", nullable = false)
    private String empresaNombre;
    @Column(name="FLOTILLA_ID", nullable = false)
    private Long flotillaId;
    @Column(name="NOMBRE_FLOTILLA", nullable = false)
    private String nombreFlotilla;
    @Column(name="NUMERO_ECONOMICO", nullable = false)
    private String numeroEconomico;
    private String ubicacion;
    @Column(name="UBICACION_ID", nullable = false)
    private Long ubicacionId;
    @Id
    @Column(name="VISTA_ID")
    private Long vistaId;
    private Long prioridad;

    public TmsOpcionesSustAutobusV() {
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    public Long getAutobusId() {
        return autobusId;
    }

    public void setAutobusId(Long autobusId) {
        this.autobusId = autobusId;
    }

    public String getEdogral() {
        return edogral;
    }

    public void setEdogral(String edogral) {
        this.edogral = edogral;
    }

    public Long getEdogralId() {
        return edogralId;
    }

    public void setEdogralId(Long edogralId) {
        this.edogralId = edogralId;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    public Long getFlotillaId() {
        return flotillaId;
    }

    public void setFlotillaId(Long flotillaId) {
        this.flotillaId = flotillaId;
    }

    public String getNombreFlotilla() {
        return nombreFlotilla;
    }

    public void setNombreFlotilla(String nombreFlotilla) {
        this.nombreFlotilla = nombreFlotilla;
    }

    public String getNumeroEconomico() {
        return numeroEconomico;
    }

    public void setNumeroEconomico(String numeroEconomico) {
        this.numeroEconomico = numeroEconomico;
    }

    public Long getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Long prioridad) {
        this.prioridad = prioridad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Long getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(Long ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public Long getVistaId() {
        return vistaId;
    }

    public void setVistaId(Long vistaId) {
        this.vistaId = vistaId;
    }
}