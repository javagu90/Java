/*
 * TmsBDConfigTbl.java
 *
 * Created on 3 de octubre de 2007, 06:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.entidad;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "TmsBDConfigTbl.findAll", 
    query = "select o from TmsBDConfigTbl o order by o.nombreTerminal")
@Table(name = "TMS_BASE_DATOS_CONFIG_TBL")
public class TmsBDConfigTbl implements Serializable {
    @Id
    @Column(name="BDCONFIG_ID", nullable = false)
    private Long bdconfigId;
    @Column(name="CREADO_POR", nullable = false)
    private Long creadoPor;
    @Column(name="ESQUEMA_PROPIO")
    private String esquemaPropio;
    @Column(name="FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;
    @Column(name="NOMBRE_DBLINK")
    private String nombreDblink;
    @Column(name="NOMBRE_ESQUEMA")
    private String nombreEsquema;
    @Column(name="NOMBRE_TERMINAL", nullable = false)
    private String nombreTerminal;
    @Column(name="TERMINAL_ID", nullable = false)
    private Long terminalId;
    @Column(name="ULTIMA_ACTUALIZACION_POR", nullable = false)
    private Long ultimaActualizacionPor;
    @Column(name="ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    private Timestamp ultimaFechaActualizacion;

    public TmsBDConfigTbl() {
    }

    public Long getBdconfId() {
        return bdconfigId;
    }

    public void setBdconfId(Long bdconfId) {
        this.bdconfigId = bdconfId;
    }

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getEsquemaPropio() {
        return esquemaPropio;
    }

    public void setEsquemaPropio(String esquemaPropio) {
        this.esquemaPropio = esquemaPropio;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombreDblink() {
        return nombreDblink;
    }

    public void setNombreDblink(String nombreDblink) {
        this.nombreDblink = nombreDblink;
    }

    public String getNombreEsquema() {
        return nombreEsquema;
    }

    public void setNombreEsquema(String nombreEsquema) {
        this.nombreEsquema = nombreEsquema;
    }

    public String getNombreTerminal() {
        return nombreTerminal;
    }

    public void setNombreTerminal(String nombreTerminal) {
        this.nombreTerminal = nombreTerminal;
    }

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
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
}