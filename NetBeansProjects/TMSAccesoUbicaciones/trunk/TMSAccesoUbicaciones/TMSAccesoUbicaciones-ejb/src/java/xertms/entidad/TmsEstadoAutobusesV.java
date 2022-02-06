/*
 * TmsEstadoAutobusesV.java
 *
 * Created on 26 de octubre de 2007, 02:37 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
@NamedQuery(name = "TmsEstadoAutobusesV.findAll", query = "select o from TmsEstadoAutobusesV o"),
@NamedQuery(name = "TmsEstadoAutobusesV.findByNumeroEconomico", query = "select o from TmsEstadoAutobusesV o where o.numeroEconomico like :NumeroEconomico")
})
@Table(name = "TMS_ESTADO_AUTOBUSES_V")
public class TmsEstadoAutobusesV implements Serializable {
    @Column(name="ACTIVIDAD_ID")
    private Long actividadId;
    @Column(name="ACTIVIDAD_NOMBRE")
    private String actividadNombre;
    @Column(name="AUTOBUS_ID", nullable = false)
    private Long autobusId;
    @Column(name="ESTADO_ID")
    private Long estadoId;
    @Column(name="ESTADO_NOMBRE")
    private String estadoNombre;
    @Column(name="NUMERO_ECONOMICO", nullable = false)
    private String numeroEconomico;
    @Column(name="UBICACION_ID")
    private Long ubicacionId;
    @Column(name="UBICACION_NOMBRE")
    private String ubicacionNombre;
    @Id
    @Column(name="VISTA_ID")
    private Long vistaId;

    public TmsEstadoAutobusesV() {
    }

    public Long getActividadId() {
        return actividadId;
    }

    public void setActividadId(Long actividadId) {
        this.actividadId = actividadId;
    }

    public String getActividadNombre() {
        return actividadNombre;
    }

    public void setActividadNombre(String actividadNombre) {
        this.actividadNombre = actividadNombre;
    }

    public Long getAutobusId() {
        return autobusId;
    }

    public void setAutobusId(Long autobusId) {
        this.autobusId = autobusId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public String getEstadoNombre() {
        return estadoNombre;
    }

    public void setEstadoNombre(String estadoNombre) {
        this.estadoNombre = estadoNombre;
    }

    public String getNumeroEconomico() {
        return numeroEconomico;
    }

    public void setNumeroEconomico(String numeroEconomico) {
        this.numeroEconomico = numeroEconomico;
    }

    public Long getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(Long ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public String getUbicacionNombre() {
        return ubicacionNombre;
    }

    public void setUbicacionNombre(String ubicacionNombre) {
        this.ubicacionNombre = ubicacionNombre;
    }

    public Long getVistaId() {
        return vistaId;
    }

    public void setVistaId(Long vistaId) {
        this.vistaId = vistaId;
    }
}