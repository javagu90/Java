/*
 * TmsAutobusesTbl.java
 *
 * Created on 7 de febrero de 2008, 01:03 PM
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
import javax.persistence.NamedQueries;
import javax.persistence.Table;

@Entity
@NamedQueries({
@NamedQuery(name = "TmsAutobusesXTbl.findAll", query = "select o from TmsAutobusesXTbl o"),
@NamedQuery(name = "TmsAutobusesXTbl.findByNumEco", query = "select o from TmsAutobusesXTbl o where o.numeroEconomico like :numEco")
})
@Table(name = "TMS_AUTOBUSES_TBL")
public class TmsAutobusesXTbl implements Serializable {
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
    @Id
    @Column(name="AUTOBUS_ID", nullable = false)
    private Long autobusId;
    @Column(name="COMPONENTE_1_ID")
    private Long componente1Id;
    @Column(name="COMPONENTE_2_ID")
    private Long componente2Id;
    @Column(name="COMPONENTE_3_ID")
    private Long componente3Id;
    @Column(name="CREADO_POR", nullable = false)
    private Long creadoPor;
    @Column(name="EMPRESA_ID")
    private Long empresaId;
    @Column(name="FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;
    @Column(name="FECHA_DESDE")
    private Timestamp fechaDesde;
    @Column(name="FECHA_HASTA")
    private Timestamp fechaHasta;
    @Column(name="FECHA_MANTENIMIENTO")
    private Timestamp fechaMantenimiento;
    @Column(name="FECHA_ULTIMA_LECTURA")
    private Timestamp fechaUltimaLectura;
    @Column(name="FECHA_ULTIMO_MP")
    private Timestamp fechaUltimoMp;
    @Column(name="FLOTILLA_ID")
    private Long flotillaId;
    @Column(name="KMS_ASIGNACION")
    private Long kmsAsignacion;
    @Column(name="KMS_RECORRIDOS")
    private Long kmsRecorridos;
    @Column(name="KMS_ULTIMA_LECTURA")
    private Long kmsUltimaLectura;
    @Column(name="KMS_ULTIMO_RESET")
    private Long kmsUltimoReset;
    @Column(name="NUMERO_ECONOMICO", nullable = false)
    private String numeroEconomico;
    @Column(name="OPERADOR_ID_PLANTA")
    private Long operadorIdPlanta;
    @Column(name="PLANTILLA_ENC_ID")
    private Long plantillaEncId;
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

    public TmsAutobusesXTbl() {
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

    public Long getComponente1Id() {
        return componente1Id;
    }

    public void setComponente1Id(Long componente1Id) {
        this.componente1Id = componente1Id;
    }

    public Long getComponente2Id() {
        return componente2Id;
    }

    public void setComponente2Id(Long componente2Id) {
        this.componente2Id = componente2Id;
    }

    public Long getComponente3Id() {
        return componente3Id;
    }

    public void setComponente3Id(Long componente3Id) {
        this.componente3Id = componente3Id;
    }

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Timestamp fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Timestamp getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Timestamp fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Timestamp getFechaMantenimiento() {
        return fechaMantenimiento;
    }

    public void setFechaMantenimiento(Timestamp fechaMantenimiento) {
        this.fechaMantenimiento = fechaMantenimiento;
    }

    public Timestamp getFechaUltimaLectura() {
        return fechaUltimaLectura;
    }

    public void setFechaUltimaLectura(Timestamp fechaUltimaLectura) {
        this.fechaUltimaLectura = fechaUltimaLectura;
    }

    public Timestamp getFechaUltimoMp() {
        return fechaUltimoMp;
    }

    public void setFechaUltimoMp(Timestamp fechaUltimoMp) {
        this.fechaUltimoMp = fechaUltimoMp;
    }

    public Long getFlotillaId() {
        return flotillaId;
    }

    public void setFlotillaId(Long flotillaId) {
        this.flotillaId = flotillaId;
    }

    public Long getKmsAsignacion() {
        return kmsAsignacion;
    }

    public void setKmsAsignacion(Long kmsAsignacion) {
        this.kmsAsignacion = kmsAsignacion;
    }

    public Long getKmsRecorridos() {
        return kmsRecorridos;
    }

    public void setKmsRecorridos(Long kmsRecorridos) {
        this.kmsRecorridos = kmsRecorridos;
    }

    public Long getKmsUltimaLectura() {
        return kmsUltimaLectura;
    }

    public void setKmsUltimaLectura(Long kmsUltimaLectura) {
        this.kmsUltimaLectura = kmsUltimaLectura;
    }

    public Long getKmsUltimoReset() {
        return kmsUltimoReset;
    }

    public void setKmsUltimoReset(Long kmsUltimoReset) {
        this.kmsUltimoReset = kmsUltimoReset;
    }

    public String getNumeroEconomico() {
        return numeroEconomico;
    }

    public void setNumeroEconomico(String numeroEconomico) {
        this.numeroEconomico = numeroEconomico;
    }

    public Long getOperadorIdPlanta() {
        return operadorIdPlanta;
    }

    public void setOperadorIdPlanta(Long operadorIdPlanta) {
        this.operadorIdPlanta = operadorIdPlanta;
    }

    public Long getPlantillaEncId() {
        return plantillaEncId;
    }

    public void setPlantillaEncId(Long plantillaEncId) {
        this.plantillaEncId = plantillaEncId;
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
}