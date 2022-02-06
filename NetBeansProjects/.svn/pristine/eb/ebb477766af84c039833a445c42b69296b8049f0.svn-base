/*
 * TmsOperadorIncidenciasTbl.java
 *
 * Created on 19 de noviembre de 2007, 12:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_guardias.entidad;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.SequenceGenerator;

@Entity
@NamedQuery(name = "TmsOperadorIncidenciasTbl.findAll", 
    query = "select o from TmsOperadorIncidenciasTbl o")
@Table(name = "TMS_OPERADOR_INCIDENCIAS_TBL")
public class TmsOperadorIncidenciasTbl implements Serializable {
    private String adicional1;
    private String adicional2;
    private String adicional3;
    private String adicional4;
    private String adicional5;
    @Column(name="SERVICIO_ID")
    private Long servicioId;
    @Column(name="CREADO_POR", nullable = false)
    private Long creadoPor;
    @Column(name="FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;
    @Column(name="FECHA_FINAL")
    private Timestamp fechaFinal;
    @Column(name="FECHA_INICIAL", nullable = false)
    private Timestamp fechaInicial;
    @Column(name="INCIDENCIA_ID", nullable = false)
    private Long incidenciaId;
    @Column(name="INCIDENCIA_VALOR")
    private String incidenciaValor;
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "TMS_OPER_INC_GENERADOR")
    @SequenceGenerator(name = "TMS_OPER_INC_GENERADOR", 
                       sequenceName = "TMS_OPER_INC_SEQ", initialValue = 1, 
                       allocationSize = 1)
    @Column(name="OPER_INCIDENCIA_ID", nullable = false)
    private Long operIncidenciaId;
    @Column(name="REPLICACION_ESTADO")
    private String replicacionEstado;
    @Column(name="REPLICACION_INTENTOS")
    private Long replicacionIntentos;
    @Column(name="REPLICACION_ORIGEN")
    private String replicacionOrigen;
    @Column(name="ULTIMA_ACTUALIZACION_POR", nullable = false)
    private Long ultimaActualizacionPor;
    @Column(name="ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    private Timestamp ultimaFechaActualizacion;
    @Column(name = "OPERADOR_ID", nullable = false)
    private Long operadorId;
    @Column(name="INCIDENCIA_AUTORIZADA")
    private String incidenciaAutorizada;
    
    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public TmsOperadorIncidenciasTbl() {
    }

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

    public Timestamp getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Timestamp fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Timestamp getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Timestamp fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Long getIncidenciaId() {
        return incidenciaId;
    }

    public void setIncidenciaId(Long incidenciaId) {
        this.incidenciaId = incidenciaId;
    }

    public String getIncidenciaValor() {
        return incidenciaValor;
    }

    public void setIncidenciaValor(String incidenciaValor) {
        this.incidenciaValor = incidenciaValor;
    }


    public Long getOperIncidenciaId() {
        return operIncidenciaId;
    }

    public void setOperIncidenciaId(Long operIncidenciaId) {
        this.operIncidenciaId = operIncidenciaId;
    }

    public String getReplicacionEstado() {
        return replicacionEstado;
    }

    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    public Long getReplicacionIntentos() {
        return replicacionIntentos;
    }

    public void setReplicacionIntentos(Long replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    public String getReplicacionOrigen() {
        return replicacionOrigen;
    }

    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
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

    public Long getOperadorId() {
        return operadorId;
    }

    public void setOperadoresId(Long tmsOperadoresTbl) {
        this.operadorId = tmsOperadoresTbl;
    }
    
    public void setIncidenciaAutorizada(String adicional1) {
        this.incidenciaAutorizada = adicional1;
    }

    public String getIncidenciaAutorizada() {
        return incidenciaAutorizada;
    }
}