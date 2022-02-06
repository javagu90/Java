/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wsc.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author imunoz
 */
public class Agentes implements Serializable {
    private BigInteger agenteId;
    private BigInteger agenciaId;
    private String agenteUsuario;
    private String agenteContrasenia;
    private String agenteNombre;
    private String agenteApellidoPaterno;
    private String agenteApellidoMaterno;
    private String agenteDireccion1;
    private String agenteDireccion2;
    private String agenteRfc;
    private String agenteTelefono1;
    private String agenteTelefono2;
    private String agenteFechaNacimiento;
    private String agenteSexo;
    private String agenteEstadoCivil;
    private String agenteEmail;
    private String agentePuesto;
    private Date fechaUltimaVenta;
    private Date fechaUltimoCanje;
    private String agenteTipo;
    private Date fechaEfectivaDesde;
    private Date fechaEfectivaHasta;
    private BigInteger agenteRelacionadoId;
    private BigInteger tipoPagoId;
    private String adicional1;
    private String adicional2;
    private String adicional3;
    private String adicional4;
    private String adicional5;
    private BigInteger creadoPor;
    private Date fechaCreacion;
    private BigInteger ultimaActualizacionPor;
    private Date ultimaFechaActualizacion;
    private String replicacionEstado;
    private BigInteger replicacionIntentos;
    private String replicacionOrigen;


    public Agentes(){
    }
    
    /**
     * @return the agenteId
     */
    public BigInteger getAgenteId() {
        return agenteId;
    }

    /**
     * @param agenteId the agenteId to set
     */
    public void setAgenteId(BigInteger agenteId) {
        this.agenteId = agenteId;
    }

    /**
     * @return the agenciaId
     */
    public BigInteger getAgenciaId() {
        return agenciaId;
    }

    /**
     * @param agenciaId the agenciaId to set
     */
    public void setAgenciaId(BigInteger agenciaId) {
        this.agenciaId = agenciaId;
    }

    /**
     * @return the agenteUsuario
     */
    public String getAgenteUsuario() {
        return agenteUsuario;
    }

    /**
     * @param agenteUsuario the agenteUsuario to set
     */
    public void setAgenteUsuario(String agenteUsuario) {
        this.agenteUsuario = agenteUsuario;
    }

    /**
     * @return the agenteContrasenia
     */
    public String getAgenteContrasenia() {
        return agenteContrasenia;
    }

    /**
     * @param agenteContrasenia the agenteContrasenia to set
     */
    public void setAgenteContrasenia(String agenteContrasenia) {
        this.agenteContrasenia = agenteContrasenia;
    }

    /**
     * @return the agenteNombre
     */
    public String getAgenteNombre() {
        return agenteNombre;
    }

    /**
     * @param agenteNombre the agenteNombre to set
     */
    public void setAgenteNombre(String agenteNombre) {
        this.agenteNombre = agenteNombre;
    }

    /**
     * @return the agenteApellidoPaterno
     */
    public String getAgenteApellidoPaterno() {
        return agenteApellidoPaterno;
    }

    /**
     * @param agenteApellidoPaterno the agenteApellidoPaterno to set
     */
    public void setAgenteApellidoPaterno(String agenteApellidoPaterno) {
        this.agenteApellidoPaterno = agenteApellidoPaterno;
    }

    /**
     * @return the agenteApellidoMaterno
     */
    public String getAgenteApellidoMaterno() {
        return agenteApellidoMaterno;
    }

    /**
     * @param agenteApellidoMaterno the agenteApellidoMaterno to set
     */
    public void setAgenteApellidoMaterno(String agenteApellidoMaterno) {
        this.agenteApellidoMaterno = agenteApellidoMaterno;
    }

    /**
     * @return the agenteDireccion1
     */
    public String getAgenteDireccion1() {
        return agenteDireccion1;
    }

    /**
     * @param agenteDireccion1 the agenteDireccion1 to set
     */
    public void setAgenteDireccion1(String agenteDireccion1) {
        this.agenteDireccion1 = agenteDireccion1;
    }

    /**
     * @return the agenteDireccion2
     */
    public String getAgenteDireccion2() {
        return agenteDireccion2;
    }

    /**
     * @param agenteDireccion2 the agenteDireccion2 to set
     */
    public void setAgenteDireccion2(String agenteDireccion2) {
        this.agenteDireccion2 = agenteDireccion2;
    }

    /**
     * @return the agenteRfc
     */
    public String getAgenteRfc() {
        return agenteRfc;
    }

    /**
     * @param agenteRfc the agenteRfc to set
     */
    public void setAgenteRfc(String agenteRfc) {
        this.agenteRfc = agenteRfc;
    }

    /**
     * @return the agenteTelefono1
     */
    public String getAgenteTelefono1() {
        return agenteTelefono1;
    }

    /**
     * @param agenteTelefono1 the agenteTelefono1 to set
     */
    public void setAgenteTelefono1(String agenteTelefono1) {
        this.agenteTelefono1 = agenteTelefono1;
    }

    /**
     * @return the agenteTelefono2
     */
    public String getAgenteTelefono2() {
        return agenteTelefono2;
    }

    /**
     * @param agenteTelefono2 the agenteTelefono2 to set
     */
    public void setAgenteTelefono2(String agenteTelefono2) {
        this.agenteTelefono2 = agenteTelefono2;
    }

    /**
     * @return the agenteFechaNacimiento
     */
    public String getAgenteFechaNacimiento() {
        return agenteFechaNacimiento;
    }

    /**
     * @param agenteFechaNacimiento the agenteFechaNacimiento to set
     */
    public void setAgenteFechaNacimiento(String agenteFechaNacimiento) {
        this.agenteFechaNacimiento = agenteFechaNacimiento;
    }

    /**
     * @return the agenteSexo
     */
    public String getAgenteSexo() {
        return agenteSexo;
    }

    /**
     * @param agenteSexo the agenteSexo to set
     */
    public void setAgenteSexo(String agenteSexo) {
        this.agenteSexo = agenteSexo;
    }

    /**
     * @return the agenteEstadoCivil
     */
    public String getAgenteEstadoCivil() {
        return agenteEstadoCivil;
    }

    /**
     * @param agenteEstadoCivil the agenteEstadoCivil to set
     */
    public void setAgenteEstadoCivil(String agenteEstadoCivil) {
        this.agenteEstadoCivil = agenteEstadoCivil;
    }

    /**
     * @return the agenteEmail
     */
    public String getAgenteEmail() {
        return agenteEmail;
    }

    /**
     * @param agenteEmail the agenteEmail to set
     */
    public void setAgenteEmail(String agenteEmail) {
        this.agenteEmail = agenteEmail;
    }

    /**
     * @return the agentePuesto
     */
    public String getAgentePuesto() {
        return agentePuesto;
    }

    /**
     * @param agentePuesto the agentePuesto to set
     */
    public void setAgentePuesto(String agentePuesto) {
        this.agentePuesto = agentePuesto;
    }

    /**
     * @return the fechaUltimaVenta
     */
    public Date getFechaUltimaVenta() {
        return fechaUltimaVenta;
    }

    /**
     * @param fechaUltimaVenta the fechaUltimaVenta to set
     */
    public void setFechaUltimaVenta(Date fechaUltimaVenta) {
        this.fechaUltimaVenta = fechaUltimaVenta;
    }

    /**
     * @return the fechaUltimoCanje
     */
    public Date getFechaUltimoCanje() {
        return fechaUltimoCanje;
    }

    /**
     * @param fechaUltimoCanje the fechaUltimoCanje to set
     */
    public void setFechaUltimoCanje(Date fechaUltimoCanje) {
        this.fechaUltimoCanje = fechaUltimoCanje;
    }

    /**
     * @return the agenteTipo
     */
    public String getAgenteTipo() {
        return agenteTipo;
    }

    /**
     * @param agenteTipo the agenteTipo to set
     */
    public void setAgenteTipo(String agenteTipo) {
        this.agenteTipo = agenteTipo;
    }

    /**
     * @return the fechaEfectivaDesde
     */
    public Date getFechaEfectivaDesde() {
        return fechaEfectivaDesde;
    }

    /**
     * @param fechaEfectivaDesde the fechaEfectivaDesde to set
     */
    public void setFechaEfectivaDesde(Date fechaEfectivaDesde) {
        this.fechaEfectivaDesde = fechaEfectivaDesde;
    }

    /**
     * @return the fechaEfectivaHasta
     */
    public Date getFechaEfectivaHasta() {
        return fechaEfectivaHasta;
    }

    /**
     * @param fechaEfectivaHasta the fechaEfectivaHasta to set
     */
    public void setFechaEfectivaHasta(Date fechaEfectivaHasta) {
        this.fechaEfectivaHasta = fechaEfectivaHasta;
    }

    /**
     * @return the agenteRelacionadoId
     */
    public BigInteger getAgenteRelacionadoId() {
        return agenteRelacionadoId;
    }

    /**
     * @param agenteRelacionadoId the agenteRelacionadoId to set
     */
    public void setAgenteRelacionadoId(BigInteger agenteRelacionadoId) {
        this.agenteRelacionadoId = agenteRelacionadoId;
    }

    /**
     * @return the tipoPagoId
     */
    public BigInteger getTipoPagoId() {
        return tipoPagoId;
    }

    /**
     * @param tipoPagoId the tipoPagoId to set
     */
    public void setTipoPagoId(BigInteger tipoPagoId) {
        this.tipoPagoId = tipoPagoId;
    }

    /**
     * @return the adicional1
     */
    public String getAdicional1() {
        return adicional1;
    }

    /**
     * @param adicional1 the adicional1 to set
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * @return the adicional2
     */
    public String getAdicional2() {
        return adicional2;
    }

    /**
     * @param adicional2 the adicional2 to set
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * @return the adicional3
     */
    public String getAdicional3() {
        return adicional3;
    }

    /**
     * @param adicional3 the adicional3 to set
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * @return the adicional4
     */
    public String getAdicional4() {
        return adicional4;
    }

    /**
     * @param adicional4 the adicional4 to set
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * @return the adicional5
     */
    public String getAdicional5() {
        return adicional5;
    }

    /**
     * @param adicional5 the adicional5 to set
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * @return the creadoPor
     */
    public BigInteger getCreadoPor() {
        return creadoPor;
    }

    /**
     * @param creadoPor the creadoPor to set
     */
    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the ultimaActualizacionPor
     */
    public BigInteger getUltimaActualizacionPor() {
        return ultimaActualizacionPor;
    }

    /**
     * @param ultimaActualizacionPor the ultimaActualizacionPor to set
     */
    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    /**
     * @return the ultimaFechaActualizacion
     */
    public Date getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    /**
     * @param ultimaFechaActualizacion the ultimaFechaActualizacion to set
     */
    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    /**
     * @return the replicacionEstado
     */
    public String getReplicacionEstado() {
        return replicacionEstado;
    }

    /**
     * @param replicacionEstado the replicacionEstado to set
     */
    public void setReplicacionEstado(String replicacionEstado) {
        this.replicacionEstado = replicacionEstado;
    }

    /**
     * @return the replicacionIntentos
     */
    public BigInteger getReplicacionIntentos() {
        return replicacionIntentos;
    }

    /**
     * @param replicacionIntentos the replicacionIntentos to set
     */
    public void setReplicacionIntentos(BigInteger replicacionIntentos) {
        this.replicacionIntentos = replicacionIntentos;
    }

    /**
     * @return the replicacionOrigen
     */
    public String getReplicacionOrigen() {
        return replicacionOrigen;
    }

    /**
     * @param replicacionOrigen the replicacionOrigen to set
     */
    public void setReplicacionOrigen(String replicacionOrigen) {
        this.replicacionOrigen = replicacionOrigen;
    }


}
