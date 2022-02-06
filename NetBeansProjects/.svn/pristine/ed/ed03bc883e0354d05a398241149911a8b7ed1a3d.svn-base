/*
 * TmsRutasTbl.java
 *
 * Created on 8 de octubre de 2007, 10:12 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package xertms.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "TmsRutasV.findAll", query = "select o from TmsRutasV o")
@Table(name = "TMS_RUTAS_V")
public class TmsRutasV implements Serializable {
    private String destino;
    @Column(name="DESTINO_ID")
    private Long destinoId;
    private String letra;
    private String origen;
    @Column(name="ORIGEN_ID")
    private Long origenId;
    @Column(name="RUTA_ID")
    private Long rutaId;
    @Column(name="RUTA_NOMBRE")
    private String rutaNombre;
    @Column(name="RUTA_NUMERO")
    private String rutaNumero;
    private String servicio;
    @Column(name="SERVICIO_CLAVE")
    private String servicioClave;
    @Column(name="SERVICIO_ID")
    private Long servicioId;
    @Column(name="TRAMO_VTA_REGRESO")
    private String tramoVtaRegreso;
    @Id
    @Column(name="VISTA_ID")
    private Long vistaId;

    public TmsRutasV() {
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Long getDestinoId() {
        return destinoId;
    }

    public void setDestinoId(Long destinoId) {
        this.destinoId = destinoId;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Long getOrigenId() {
        return origenId;
    }

    public void setOrigenId(Long origenId) {
        this.origenId = origenId;
    }

    public Long getRutaId() {
        return rutaId;
    }

    public void setRutaId(Long rutaId) {
        this.rutaId = rutaId;
    }

    public String getRutaNombre() {
        return rutaNombre;
    }

    public void setRutaNombre(String rutaNombre) {
        this.rutaNombre = rutaNombre;
    }

    public String getRutaNumero() {
        return rutaNumero;
    }

    public void setRutaNumero(String rutaNumero) {
        this.rutaNumero = rutaNumero;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getServicioClave() {
        return servicioClave;
    }

    public void setServicioClave(String servicioClave) {
        this.servicioClave = servicioClave;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public String getTramoVtaRegreso() {
        return tramoVtaRegreso;
    }

    public void setTramoVtaRegreso(String tramoVtaRegreso) {
        this.tramoVtaRegreso = tramoVtaRegreso;
    }

    public Long getVistaId() {
        return vistaId;
    }

    public void setVistaId(Long vistaId) {
        this.vistaId = vistaId;
    }
}