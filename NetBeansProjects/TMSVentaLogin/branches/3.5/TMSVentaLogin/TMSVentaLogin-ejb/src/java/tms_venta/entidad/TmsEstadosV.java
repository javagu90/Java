/*
 * TmsEstadosV.java
 *
 * Created on 3 de octubre de 2007, 07:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_venta.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "TmsEstadosV.findAll", 
    query = "select o from TmsEstadosV o")
@Table(name = "TMS_ESTADOS_V")
public class TmsEstadosV implements Serializable {
    @Column(name="CLAVE_ESTADO")
    private String claveEstado;
    @Column(name="ESTADO_ID", nullable = false)
    @Id
    private Long estadoId;
    @Column(name="ESTADO_NOMBRE")
    private String estadoNombre;

    public TmsEstadosV() {
    }

    public String getClaveEstado() {
        return claveEstado;
    }

    public void setClaveEstado(String claveEstado) {
        this.claveEstado = claveEstado;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public String getEstadoNombre() {
        return estadoNombre;
    }

    public void setEstadoNombre(String estadoNombre) {
        this.estadoNombre = estadoNombre;
    }
}
