/*
 * TmsAutobusPlantLineasTbl.java
 *
 * Created on 11 de octubre de 2007, 11:39 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_bloqueo.entidad;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries({
@NamedQuery(name = "TmsAutobusPlantLineasTbl.findAll", 
            query = "select o from TmsAutobusPlantLineasTbl o where o.plantillaEncId=:encId"),
@NamedQuery(name = "TmsAutobusPlantLineasTbl.findTodo", 
            query = "select o from TmsAutobusPlantLineasTbl o order by o.plantillaLineaId")
})
@Table(name = "TMS_AUTOBUS_PLANT_LINEAS_TBL")
public class TmsAutobusPlantLineasTbl implements Serializable {
    @Column(name = "COMPONENTE_BUS_ID", nullable = false)
    private Long componenteBusId;
    @Column(name = "CREADO_POR")
    private Long creadoPor;
    @Column(name = "FECHA_CREACION")
    private Timestamp fechaCreacion;
    @Column(name = "NUMERO_ASIENTO")
    private Long numeroAsiento;
    @Id
    @Column(name = "PLANTILLA_LINEA_ID", nullable = false)
    private Long plantillaLineaId;
    @Column(name = "PLANTILLA_ENC_ID", nullable = false)
    private Long plantillaEncId;
    @Column(name = "POSICION_X")
    private Long posicionX;
    @Column(name = "POSICION_Z")
    private Long posicionZ;
    @Column(name = "ULTIMA_ACTUALIZACION_POR")
    private Long ultimaActualizacionPor;
    @Column(name = "ULTIMA_FECHA_ACTUALIZACION")
    private Timestamp ultimaFechaActualizacion;
    /*@ManyToOne
    @JoinColumn(name = "PLANTILLA_ENC_ID", referencedColumnName = "PLANTILLA_ENC_ID")
    private TmsAutobusPlantillasEncTbl tmsAutobusPlantillasEncTbl;*/

    public TmsAutobusPlantLineasTbl() {
    }

    public Long getComponenteBusId() {
        return componenteBusId;
    }

    public void setComponenteBusId(Long componenteBusId) {
        this.componenteBusId = componenteBusId;
    }

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(Long numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public Long getPlantillaLineaId() {
        return plantillaLineaId;
    }

    public void setPlantillaLineaId(Long plantillaLineaId) {
        this.plantillaLineaId = plantillaLineaId;
    }

    public Long getPlantillaEncId() {
        return plantillaEncId;
    }

    public void setPlantillaEncId(Long plantillaEncId) {
        this.plantillaEncId = plantillaEncId;
    }

    public Long getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(Long posicionX) {
        this.posicionX = posicionX;
    }

    public Long getPosicionZ() {
        return posicionZ;
    }

    public void setPosicionZ(Long posicionZ) {
        this.posicionZ = posicionZ;
    }

    public Long getUltimaActualizacionPor() {
        return ultimaActualizacionPor;
    }

    public void setUltimaActualizacionPor(Long ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    public Timestamp getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(Timestamp ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }


    /*public TmsAutobusPlantillasEncTbl getTmsAutobusPlantillasEncTbl() {
        return tmsAutobusPlantillasEncTbl;
    }

    public void setTmsAutobusPlantillasEncTbl(TmsAutobusPlantillasEncTbl tmsAutobusPlantillasEncTbl) {
        this.tmsAutobusPlantillasEncTbl = tmsAutobusPlantillasEncTbl;
    }*/
}