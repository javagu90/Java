/*
 * TmsRutasTbl.java
 *
 * Created on 8 de octubre de 2007, 12:42 PM
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
@NamedQuery(name = "TmsRutasTbl.findAll", 
    query = "select o from TmsRutasTbl o")
@Table(name = "TMS_RUTAS_TBL")
public class TmsRutasTbl implements Serializable {
    @Column(name="ACEPTA_CORRIDAS_EXTRAS", nullable = false)
    private String aceptaCorridasExtras;
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
    @Column(name="CREADO_POR", nullable = false)
    private Long creadoPor;
    private String descripcion;
    @Column(name="DISTANCIA_RECORRIDO")
    private Long distanciaRecorrido;
    @Column(name="FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;
    @Column(nullable = false)
    private String habilitado;
    @Id
    @Column(name="RUTA_ID", nullable = false)
    private Long rutaId;
    @Column(name="RUTA_NOMBRE")
    private String rutaNombre;
    @Column(name="RUTA_NUMERO", nullable = false)
    private String rutaNumero;
    @Column(name="SERVICIO_ID", nullable = false)
    private Long servicioId;
    @Column(name="TIEMPO_ESTANCIA")
    private Long tiempoEstancia;
    @Column(name="TIEMPO_RECORRIDO")
    private Long tiempoRecorrido;
    @Column(name="TRAMO_DESTINO_ID", nullable = false)
    private Long tramoDestinoId;
    @Column(name="TRAMO_ORIGEN_ID", nullable = false)
    private Long tramoOrigenId;
    @Column(name="ULTIMA_ACTUALIZACION_POR", nullable = false)
    private Long ultimaActualizacionPor;
    @Column(name="ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    private Timestamp ultimaFechaActualizacion;

    public TmsRutasTbl() {
    }

    public String getAceptaCorridasExtras() {
        return aceptaCorridasExtras;
    }

    public void setAceptaCorridasExtras(String aceptaCorridasExtras) {
        this.aceptaCorridasExtras = aceptaCorridasExtras;
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

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getDistanciaRecorrido() {
        return distanciaRecorrido;
    }

    public void setDistanciaRecorrido(Long distanciaRecorrido) {
        this.distanciaRecorrido = distanciaRecorrido;
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

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public Long getTiempoEstancia() {
        return tiempoEstancia;
    }

    public void setTiempoEstancia(Long tiempoEstancia) {
        this.tiempoEstancia = tiempoEstancia;
    }

    public Long getTiempoRecorrido() {
        return tiempoRecorrido;
    }

    public void setTiempoRecorrido(Long tiempoRecorrido) {
        this.tiempoRecorrido = tiempoRecorrido;
    }

    public Long getTramoDestinoId() {
        return tramoDestinoId;
    }

    public void setTramoDestinoId(Long tramoDestinoId) {
        this.tramoDestinoId = tramoDestinoId;
    }

    public Long getTramoOrigenId() {
        return tramoOrigenId;
    }

    public void setTramoOrigenId(Long tramoOrigenId) {
        this.tramoOrigenId = tramoOrigenId;
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