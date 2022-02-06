/*
 * TmsEstadoOperadoresV.java
 *
 * Created on 19 de noviembre de 2007, 12:06 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_incidencias.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
@NamedQuery(name = "TmsEstadoOperadoresV.findAll", query = "select o from TmsEstadoOperadoresV o order by o.operadorId"),
@NamedQuery(name = "TmsEstadoOperadoresV.findByNumero", query = "select o from TmsEstadoOperadoresV o where o.claveOperador like :Numero")
})
@Table(name = "TMS_ESTADO_OPERADORES_V")
public class TmsEstadoOperadoresV implements Serializable {
    @Column(name="ACTIVIDAD_ID", nullable = false)
    private Long actividadId;
    @Column(name="ACTIVIDAD_NOMBRE", nullable = false)
    private String actividadNombre;
    @Column(name="APLICA_RETENCION")
    private String aplicaRetencion;
    @Column(name="CLAVE_OPERADOR", nullable = false)
    private String claveOperador;
    @Column(name="ESTADO_ID", nullable = false)
    private Long estadoId;
    @Column(name="ESTADO_NOMBRE", nullable = false)
    private String estadoNombre;
    @Column(name="OPERADOR_ID", nullable = false)
    private Long operadorId;
    @Column(name="OPERADOR_NOMBRE_COMPLETO")
    private String operadorNombreCompleto;
    @Column(name="UBICACION_ID", nullable = false)
    private Long ubicacionId;
    @Column(name="UBICACION_NOMBRE", nullable = false)
    private String ubicacionNombre;
    @Column(name="VALOR_RETENCION")
    private String valorRetencion;
    @Column(name="VISTA_ID")
    @Id
    private Long vistaId;

    public TmsEstadoOperadoresV() {
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

    public String getAplicaRetencion() {
        return aplicaRetencion;
    }

    public void setAplicaRetencion(String aplicaRetencion) {
        this.aplicaRetencion = aplicaRetencion;
    }

    public String getClaveOperador() {
        return claveOperador;
    }

    public void setClaveOperador(String claveOperador) {
        this.claveOperador = claveOperador;
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

    public Long getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Long operadorId) {
        this.operadorId = operadorId;
    }

    public String getOperadorNombreCompleto() {
        return operadorNombreCompleto;
    }

    public void setOperadorNombreCompleto(String operadorNombreCompleto) {
        this.operadorNombreCompleto = operadorNombreCompleto;
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

    public String getValorRetencion() {
        return valorRetencion;
    }

    public void setValorRetencion(String valorRetencion) {
        this.valorRetencion = valorRetencion;
    }

    public Long getVistaId() {
        return vistaId;
    }

    public void setVistaId(Long vistaId) {
        this.vistaId = vistaId;
    }
}