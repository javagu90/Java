/*
 * TmsAutobusesTbl.java
 *
 * Created on 13 de octubre de 2007, 06:39 PM
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
@NamedQuery(name = "TmsAutobusesTbl.findAll", 
    query = "select o from TmsAutobusesTbl o")
@Table(name = "TMS_AUTOBUSES_TBL")
public class TmsAutobusesTbl implements Serializable {
    private String adicional1;
    private String adicional2;
    private String adicional3;
    private String adicional4;
    private String adicional5;
    @Id
    @Column(name="AUTOBUS_ID", nullable = false)
    private Long autobusId;
    @Column(name="COMPONENTE_1_ID")
    private Long componente1Id;
    @Column(name="COMPONENTE_2_ID")
    private Long componente2Id;
    @Column(name="COMPONENTE_3_ID")
    private Long componente3Id;
    @Column(name="CREADO_POR")
    private Long creadoPor;
    @Column(name="EMPRESA_ID")
    private Long empresaId;
    @Column(name="FECHA_CREACION")
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
    @Column(name="ULTIMA_ACTUALIZACION_POR")
    private Long ultimaActualizacionPor;
    @Column(name="ULTIMA_FECHA_ACTUALIZACION")
    private Timestamp ultimaFechaActualizacion;
    
    @Column(name="REPLICACION_ORIGEN")
    private String replicacionOrigen;

    public TmsAutobusesTbl() {
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
    
    public String getReplicacionOrigen() {
        return replicacionOrigen;
    }

    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }
}