/*
 * TmsCorridasTbl.java
 *
 * Created on 13 de octubre de 2007, 06:20 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.entidad;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import static javax.persistence.GenerationType.SEQUENCE;


@Entity
@NamedQuery(name = "TmsCorridasTbl.findAll", 
    query = "select o from TmsCorridasTbl o")
@Table(name = "TMS_CORRIDAS_TBL")
public class TmsCorridasTbl implements Serializable {
    private String adicional1;
    private String adicional10;
    private String adicional2;
    private String adicional3;
    private String adicional4;
    private String adicional5;
    private String adicional6;
    private String adicional7;
    private String adicional8;
    private String adicional9;
    @Column(name="AUTOBUS_ID", nullable = false)
    private Long autobusId;
    @Column(name="AUTOBUS_ORIGINAL_ID", nullable = false)
    private Long autobusOriginalId;
    @Column(name="CLAVE_CORRIDA", nullable = false)
    private String claveCorrida;
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "TMS_CORRIDAS_GENERADOR")
    @SequenceGenerator(name = "TMS_CORRIDAS_GENERADOR", 
                       sequenceName = "TMS_CORRIDAS_SEQ", initialValue = 1, 
                       allocationSize = 1)
    @Column(name="CORRIDA_ID", nullable = false)
    private Long corridaId;
    @Column(name="CREADO_POR", nullable = false)
    private Long creadoPor;
    @Column(name="DESTINO_ID", nullable = false)
    private Long destinoId;
    @Column(name="EMPRESA_ID", nullable = false)
    private Long empresaId;
    @Column(name="ESTADO_CORRIDA_ID", nullable = false)
    private Long estadoCorridaId;
    @Column(name="FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;
    @Column(name="FECHA_HORA_CORRIDA", nullable = false)
    private Timestamp fechaHoraCorrida;
    @Column(name="MONTO_ANTICIPOS")
    private Double montoAnticipos;
    @Column(name="NUMERO_CONTRATO")
    private String numeroContrato;
    @Column(name="NUMERO_ORDEN")
    private String numeroOrden;
    @Column(name="OPERADOR_ID", nullable = false)
    private Long operadorId;
    @Column(name="OPERADOR_ORIGINAL_ID", nullable = false)
    private Long operadorOriginalId;
    @Column(name="ORIGEN_ID", nullable = false)
    private Long origenId;
    @Column(name="PLANTILLA_ID", nullable = false)
    private Long plantillaId;
    @Column(name="RUTA_ID", nullable = false)
    private Long rutaId;
    @Column(name="SERVICIO_ID", nullable = false)
    private Long servicioId;
    @Column(name="SUELDO_OPERADOR")
    private Double sueldoOperador;
    @Column(name="TIPO_CORRIDA", nullable = false)
    private String tipoCorrida;
    @Column(name="ULTIMA_ACTUALIZACION_POR", nullable = false)
    private Long ultimaActualizacionPor;
    @Column(name="ULTIMA_FECHA_ACTUALIZACION")
    private Timestamp ultimaFechaActualizacion;
    @Column(name = "CORRIDA_RELACIONADA_ID", nullable = false)
    private Long corridaRelacionadaId;
    @Column(name = "REPLICACION_ORIGEN")
    private String replicacionOrigen;    

    public TmsCorridasTbl() {
    }

    public String getAdicional1() {
        return adicional1;
    }

    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    public String getAdicional10() {
        return adicional10;
    }

    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
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

    public Long getAutobusId() {
        return autobusId;
    }

    public void setAutobusId(Long autobusId) {
        this.autobusId = autobusId;
    }

    public Long getAutobusOriginalId() {
        return autobusOriginalId;
    }

    public void setAutobusOriginalId(Long autobusOriginalId) {
        this.autobusOriginalId = autobusOriginalId;
    }

    public String getClaveCorrida() {
        return claveCorrida;
    }

    public void setClaveCorrida(String claveCorrida) {
        this.claveCorrida = claveCorrida;
    }

    public Long getCorridaId() {
        return corridaId;
    }

    public void setCorridaId(Long corridaId) {
        this.corridaId = corridaId;
    }


    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Long getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(Long destinoId) {
        this.destinoId = destinoId;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Long getEstadoCorridaId() {
        return estadoCorridaId;
    }

    public void setEstadoCorridaId(Long estadoCorridaId) {
        this.estadoCorridaId = estadoCorridaId;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaHoraCorrida() {
        return fechaHoraCorrida;
    }

    public void setFechaHoraCorrida(Timestamp fechaHoraCorrida) {
        this.fechaHoraCorrida = fechaHoraCorrida;
    }

    public Double getMontoAnticipos() {
        return montoAnticipos;
    }

    public void setMontoAnticipos(Double montoAnticipos) {
        this.montoAnticipos = montoAnticipos;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Long getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Long operadorId) {
        this.operadorId = operadorId;
    }

    public Long getOperadorOriginalId() {
        return operadorOriginalId;
    }

    public void setOperadorOriginalId(Long operadorOriginalId) {
        this.operadorOriginalId = operadorOriginalId;
    }

    public Long getOrigenId() {
        return origenId;
    }

    public void setOrigenId(Long origenId) {
        this.origenId = origenId;
    }

    public Long getPlantillaId() {
        return plantillaId;
    }

    public void setPlantillaId(Long plantillaId) {
        this.plantillaId = plantillaId;
    }

    public Long getRutaId() {
        return rutaId;
    }

    public void setRutaId(Long rutaId) {
        this.rutaId = rutaId;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public Double getSueldoOperador() {
        return sueldoOperador;
    }

    public void setSueldoOperador(Double sueldoOperador) {
        this.sueldoOperador = sueldoOperador;
    }

    public String getTipoCorrida() {
        return tipoCorrida;
    }

    public void setTipoCorrida(String tipoCorrida) {
        this.tipoCorrida = tipoCorrida;
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

    public Long getCorridaRelacionadaId() {
        return corridaRelacionadaId;
    }

    public void setCorridaRelacionadaId(Long corridaRelacionadaId) {
        this.corridaRelacionadaId = corridaRelacionadaId;
    }
    
    public String getReplicacionOrigen() {
        return replicacionOrigen;
    }

    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }
}