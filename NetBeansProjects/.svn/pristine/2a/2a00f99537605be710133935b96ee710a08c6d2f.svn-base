/*
 * TmsBoletosNoDisponiblesV.java
 *
 * Created on 7 de diciembre de 2007, 06:02 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsBolNoDisp.entidad;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "TmsBoletosNoDisponiblesV.findAll", 
    query = "select o from TmsBoletosNoDisponiblesV o")
@Table(name = "TMS_BOLETOS_NO_DISPONIBLES_V")
public class TmsBoletosNoDisponiblesV implements Serializable {
    @Id
    @Column(name="BOL_NO_DIP_ID")
    private long bolNoDipId;
    @Column(name="CLAVE_CORRIDA")
    private String claveCorrida;
    @Column(name="CORRIDA_ID")
    private long corridaId;
    @Column(name="CREADO_POR")
    private long creadoPor;
    private String descripcion;
    private String destino;
    @Column(name="DESTINO_ID")
    private long destinoId;
    private String disponible;
    private String estado;
    @Column(name="ESTADO_ID")
    private long estadoId;
    @Column(name="FECHA_CORRIDA")
    private String fechaCorrida;
    @Column(name="FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;    
    @Column(name="HORA_SALIDA")
    private String horaSalida;
    @Column(name="NO_ASIENTO")
    private long noAsiento;
    private String origen;
    @Column(name="ORIGEN_ID")
    private long origenId;
    @Column(name="USUARIO_NOMBRE")
    private String usuarioNombre;

    public TmsBoletosNoDisponiblesV() {
    }

    public long getBolNoDipId() {
        return bolNoDipId;
    }

    public void setBolNoDipId(long bolNoDipId) {
        this.bolNoDipId = bolNoDipId;
    }

    public String getClaveCorrida() {
        return claveCorrida;
    }

    public void setClaveCorrida(String claveCorrida) {
        this.claveCorrida = claveCorrida;
    }

    public long getCorridaId() {
        return corridaId;
    }

    public void setCorridaId(long corridaId) {
        this.corridaId = corridaId;
    }

    public long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public long getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(long destinoId) {
        this.destinoId = destinoId;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(long estadoId) {
        this.estadoId = estadoId;
    }

    public String getFechaCorrida() {
        return fechaCorrida;
    }

    public void setFechaCorrida(String fechaCorrida) {
        this.fechaCorrida = fechaCorrida;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public long getNoAsiento() {
        return noAsiento;
    }

    public void setNoAsiento(long noAsiento) {
        this.noAsiento = noAsiento;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public long getOrigenId() {
        return origenId;
    }

    public void setOrigenId(long origenId) {
        this.origenId = origenId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }
}
