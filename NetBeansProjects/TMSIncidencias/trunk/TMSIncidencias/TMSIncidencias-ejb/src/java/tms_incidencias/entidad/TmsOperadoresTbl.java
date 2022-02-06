/*
 * TmsOperadoresTbl.java
 *
 * Created on 19 de noviembre de 2007, 12:06 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_incidencias.entidad;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "TmsOperadoresTbl.findAll", 
    query = "select o from TmsOperadoresTbl o")
@Table(name = "TMS_OPERADORES_TBL")
public class TmsOperadoresTbl implements Serializable {
    @Column(name="ACCION1_ID", nullable = false)
    private Long accion1Id;
    @Column(name="ACCION2_ID", nullable = false)
    private Long accion2Id;
    @Column(name="ACCION3_ID", nullable = false)
    private Long accion3Id;
    @Column(name="ACCION4_ID", nullable = false)
    private Long accion4Id;
    private String adicional1;
    private String adicional2;
    private String adicional3;
    private String adicional4;
    private String adicional5;
    @Column(name="APLICA_RETENCION")
    private String aplicaRetencion;
    @Column(name="CLAVE_OPERADOR", nullable = false)
    private String claveOperador;
    @Column(name="CREADO_POR", nullable = false)
    private Long creadoPor;
    @Column(name="DIA_ACTIVO", nullable = false)
    private Long diaActivo;
    @Column(name="EMPRESA_ID")
    private Long empresaId;
    @Column(name="FECHA_ALTA")
    private Timestamp fechaAlta;
    @Column(name="FECHA_BAJA")
    private Timestamp fechaBaja;
    @Column(name="FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;
    @Column(nullable = false)
    private String habilitado;
    @Column(name="JORNADA_SERVICIO_ID", nullable = false)
    private Long jornadaServicioId;
    @Column(name="OPERADOR_APELLIDO")
    private String operadorApellido;
    @Id
    @Column(name="OPERADOR_ID", nullable = false)
    private Long operadorId;
    @Column(name="OPERADOR_NOMBRE")
    private String operadorNombre;
    @Column(name="OPERADOR_NOMBRE_COMPLETO")
    private String operadorNombreCompleto;
    @Column(name="OPERADOR_NOMBRE_MEDIO")
    private String operadorNombreMedio;
    @Column(name="REPLICACION_ESTADO")
    private String replicacionEstado;
    @Column(name="REPLICACION_INTENTOS")
    private Long replicacionIntentos;
    @Column(name="REPLICACION_ORIGEN")
    private String replicacionOrigen;
    @Column(name="TIPO_OPERADOR_ID", nullable = false)
    private Long tipoOperadorId;
    @Column(name="TIPO_SERVICIO")
    private String tipoServicio;
    @Column(name="ULTIMA_ACTUALIZACION_POR", nullable = false)
    private Long ultimaActualizacionPor;
    @Column(name="ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    private Timestamp ultimaFechaActualizacion;

    public TmsOperadoresTbl() {
    }

    public Long getAccion1Id() {
        return accion1Id;
    }

    public void setAccion1Id(Long accion1Id) {
        this.accion1Id = accion1Id;
    }

    public Long getAccion2Id() {
        return accion2Id;
    }

    public void setAccion2Id(Long accion2Id) {
        this.accion2Id = accion2Id;
    }

    public Long getAccion3Id() {
        return accion3Id;
    }

    public void setAccion3Id(Long accion3Id) {
        this.accion3Id = accion3Id;
    }

    public Long getAccion4Id() {
        return accion4Id;
    }

    public void setAccion4Id(Long accion4Id) {
        this.accion4Id = accion4Id;
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

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Long getDiaActivo() {
        return diaActivo;
    }

    public void setDiaActivo(Long diaActivo) {
        this.diaActivo = diaActivo;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Timestamp getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Timestamp getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Timestamp fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public Long getJornadaServicioId() {
        return jornadaServicioId;
    }

    public void setJornadaServicioId(Long jornadaServicioId) {
        this.jornadaServicioId = jornadaServicioId;
    }

    public String getOperadorApellido() {
        return operadorApellido;
    }

    public void setOperadorApellido(String operadorApellido) {
        this.operadorApellido = operadorApellido;
    }

    public Long getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Long operadorId) {
        this.operadorId = operadorId;
    }


    public String getOperadorNombre() {
        return operadorNombre;
    }

    public void setOperadorNombre(String operadorNombre) {
        this.operadorNombre = operadorNombre;
    }

    public String getOperadorNombreCompleto() {
        return operadorNombreCompleto;
    }

    public void setOperadorNombreCompleto(String operadorNombreCompleto) {
        this.operadorNombreCompleto = operadorNombreCompleto;
    }

    public String getOperadorNombreMedio() {
        return operadorNombreMedio;
    }

    public void setOperadorNombreMedio(String operadorNombreMedio) {
        this.operadorNombreMedio = operadorNombreMedio;
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

    public Long getTipoOperadorId() {
        return tipoOperadorId;
    }

    public void setTipoOperadorId(Long tipoOperadorId) {
        this.tipoOperadorId = tipoOperadorId;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
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