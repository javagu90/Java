/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vgonzalez
 */
@Entity
@Table(name = "ER_CLIENTE_COMISIONES_TBL")
public class ClienteComisiones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CLIENTE_COMISION_ID")
    private BigDecimal clienteComisionId;
    @Column(name = "CLIENTE_ID")
    private BigInteger clienteId;
    @Basic(optional = false)
    @Column(name = "NOMBRE_PRODUCTO")
    private String nombreProducto;
    @Column(name = "SERVICIO_ID")
    private BigInteger servicioId;
    @Column(name = "RUTA_ID")
    private BigInteger rutaId;
    @Column(name = "TRAMO_ID")
    private BigInteger tramoId;
    @Column(name = "EMPRESA_ID")
    private BigInteger empresaId;
    @Column(name = "PORCENTAJE_COMISION")
    private BigInteger porcentajeComision;
    @Column(name = "FECHA_INICIAL")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "FECHA_FINAL")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;

    public ClienteComisiones() {
    }

    public ClienteComisiones(BigDecimal clienteComisionId) {
        this.clienteComisionId = clienteComisionId;
    }

    public ClienteComisiones(BigDecimal clienteComisionId, String nombreProducto, BigInteger creadoPor, Date fechaCreacion, BigInteger ultimaActualizacionPor, Date ultimaFechaActualizacion) {
        this.clienteComisionId = clienteComisionId;
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getClienteComisionId() {
        return clienteComisionId;
    }

    public void setClienteComisionId(BigDecimal clienteComisionId) {
        this.clienteComisionId = clienteComisionId;
    }

    public BigInteger getClienteId() {
        return clienteId;
    }

    public void setClienteId(BigInteger clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigInteger getServicioId() {
        return servicioId;
    }

    public void setServicioId(BigInteger servicioId) {
        this.servicioId = servicioId;
    }

    public BigInteger getRutaId() {
        return rutaId;
    }

    public void setRutaId(BigInteger rutaId) {
        this.rutaId = rutaId;
    }

    public BigInteger getTramoId() {
        return tramoId;
    }

    public void setTramoId(BigInteger tramoId) {
        this.tramoId = tramoId;
    }

    public BigInteger getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(BigInteger empresaId) {
        this.empresaId = empresaId;
    }

    public BigInteger getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(BigInteger porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clienteComisionId != null ? clienteComisionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteComisiones)) {
            return false;
        }
        ClienteComisiones other = (ClienteComisiones) object;
        if ((this.clienteComisionId == null && other.clienteComisionId != null) || (this.clienteComisionId != null && !this.clienteComisionId.equals(other.clienteComisionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.entidades.ClienteComisiones[clienteComisionId=" + clienteComisionId + "]";
    }

}
