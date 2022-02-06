/*
 * TmsPagosActAdicionalesTbl.java
 *
 * Created on 19 de noviembre de 2007, 11:11 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_guardias.entidad;

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
@NamedQuery(name = "TmsPagosActAdicionalesTbl.findAll", 
    query = "select o from TmsPagosActAdicionalesTbl o")
@Table(name = "TMS_PAGOS_ACT_ADICIONALES_TBL")
public class TmsPagosActAdicionalesTbl implements Serializable {
    private String adicional1;
    private String adicional10;
    private String adicional11;
    private String adicional12;
    private String adicional13;
    private String adicional14;
    private String adicional15;
    private String adicional16;
    private String adicional17;
    private String adicional18;
    private String adicional19;
    private String adicional2;
    private String adicional20;
    private String adicional21;
    private String adicional22;
    private String adicional23;
    private String adicional24;
    private String adicional25;
    private String adicional26;
    private String adicional27;
    private String adicional28;
    private String adicional29;
    private String adicional3;
    private String adicional30;
    private String adicional4;
    private String adicional5;
    private String adicional6;
    private String adicional7;
    private String adicional8;
    private String adicional9;
    @Column(name="AUTORIZADO_POR")
    private Long autorizadoPor;
    @Column(name="CIUDAD_RECAUDACION_ID", nullable = false)
    private Long ciudadRecaudacionId;
    private String comentarios;
    @Column(name="CREADO_POR", nullable = false)
    private Long creadoPor;
    @Column(name="ESTADO_PAGO", nullable = false)
    private String estadoPago;
    @Column(name="FECHA_CREACION", nullable = false)
    private Timestamp fechaCreacion;
    @Column(name="FECHA_HORA_RECAUDACION", nullable = false)
    private Timestamp fechaHoraRecaudacion;
    @Column(name="MONTO_PAGO")
    private Double montoPago;
    @Column(name="OPERADOR_ID", nullable = false)
    private Long operadorId;
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "TMS_PAGOS_ACT_ADIC_GENERADOR")
    @SequenceGenerator(name = "TMS_PAGOS_ACT_ADIC_GENERADOR", 
                       sequenceName = "TMS_PAGOS_ACT_ADIC_SEQ", initialValue = 1, 
                       allocationSize = 1)
    @Column(name="PAGO_ACTIVIDAD_ADICIONAL_ID", nullable = false)
    private Long pagoActividadAdicionalId;
    @Column(name="RECAUDADOR_ID", nullable = false)
    private Long recaudadorId;
    @Column(name="REFERENCIA_PAGO_ACT_ADICIONAL", nullable = false)
    private Long referenciaPagoActAdicional;
    @Column(name="REPLICACION_ESTADO")
    private String replicacionEstado;
    @Column(name="REPLICACION_INTENTOS")
    private Long replicacionIntentos;
    @Column(name="REPLICACION_ORIGEN")
    private String replicacionOrigen;
    private Double retencion;
    @Column(name="SERVICIO_ID", nullable = false)
    private Long servicioId;
    @Column(name="TIPO_ACTIVIDAD_ADICIONAL_ID", nullable = false)
    private Long tipoActividadAdicionalId;
    @Column(name="ULTIMA_ACTUALIZACION_POR", nullable = false)
    private Long ultimaActualizacionPor;
    @Column(name="ULTIMA_FECHA_ACTUALIZACION", nullable = false)
    private Timestamp ultimaFechaActualizacion;

    public TmsPagosActAdicionalesTbl() {
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

    public String getAdicional11() {
        return adicional11;
    }

    public void setAdicional11(String adicional11) {
        this.adicional11 = adicional11;
    }

    public String getAdicional12() {
        return adicional12;
    }

    public void setAdicional12(String adicional12) {
        this.adicional12 = adicional12;
    }

    public String getAdicional13() {
        return adicional13;
    }

    public void setAdicional13(String adicional13) {
        this.adicional13 = adicional13;
    }

    public String getAdicional14() {
        return adicional14;
    }

    public void setAdicional14(String adicional14) {
        this.adicional14 = adicional14;
    }

    public String getAdicional15() {
        return adicional15;
    }

    public void setAdicional15(String adicional15) {
        this.adicional15 = adicional15;
    }

    public String getAdicional16() {
        return adicional16;
    }

    public void setAdicional16(String adicional16) {
        this.adicional16 = adicional16;
    }

    public String getAdicional17() {
        return adicional17;
    }

    public void setAdicional17(String adicional17) {
        this.adicional17 = adicional17;
    }

    public String getAdicional18() {
        return adicional18;
    }

    public void setAdicional18(String adicional18) {
        this.adicional18 = adicional18;
    }

    public String getAdicional19() {
        return adicional19;
    }

    public void setAdicional19(String adicional19) {
        this.adicional19 = adicional19;
    }

    public String getAdicional2() {
        return adicional2;
    }

    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    public String getAdicional20() {
        return adicional20;
    }

    public void setAdicional20(String adicional20) {
        this.adicional20 = adicional20;
    }

    public String getAdicional21() {
        return adicional21;
    }

    public void setAdicional21(String adicional21) {
        this.adicional21 = adicional21;
    }

    public String getAdicional22() {
        return adicional22;
    }

    public void setAdicional22(String adicional22) {
        this.adicional22 = adicional22;
    }

    public String getAdicional23() {
        return adicional23;
    }

    public void setAdicional23(String adicional23) {
        this.adicional23 = adicional23;
    }

    public String getAdicional24() {
        return adicional24;
    }

    public void setAdicional24(String adicional24) {
        this.adicional24 = adicional24;
    }

    public String getAdicional25() {
        return adicional25;
    }

    public void setAdicional25(String adicional25) {
        this.adicional25 = adicional25;
    }

    public String getAdicional26() {
        return adicional26;
    }

    public void setAdicional26(String adicional26) {
        this.adicional26 = adicional26;
    }

    public String getAdicional27() {
        return adicional27;
    }

    public void setAdicional27(String adicional27) {
        this.adicional27 = adicional27;
    }

    public String getAdicional28() {
        return adicional28;
    }

    public void setAdicional28(String adicional28) {
        this.adicional28 = adicional28;
    }

    public String getAdicional29() {
        return adicional29;
    }

    public void setAdicional29(String adicional29) {
        this.adicional29 = adicional29;
    }

    public String getAdicional3() {
        return adicional3;
    }

    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    public String getAdicional30() {
        return adicional30;
    }

    public void setAdicional30(String adicional30) {
        this.adicional30 = adicional30;
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

    public Long getAutorizadoPor() {
        return autorizadoPor;
    }

    public void setAutorizadoPor(Long autorizadoPor) {
        this.autorizadoPor = autorizadoPor;
    }

    public Long getCiudadRecaudacionId() {
        return ciudadRecaudacionId;
    }

    public void setCiudadRecaudacionId(Long ciudadRecaudacionId) {
        this.ciudadRecaudacionId = ciudadRecaudacionId;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Long getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Long creadoPor) {
        this.creadoPor = creadoPor;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaHoraRecaudacion() {
        return fechaHoraRecaudacion;
    }

    public void setFechaHoraRecaudacion(Timestamp fechaHoraRecaudacion) {
        this.fechaHoraRecaudacion = fechaHoraRecaudacion;
    }

    public Double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(Double montoPago) {
        this.montoPago = montoPago;
    }

    public Long getOperadorId() {
        return operadorId;
    }

    public void setOperadorId(Long operadorId) {
        this.operadorId = operadorId;
    }

    public Long getPagoActividadAdicionalId() {
        return pagoActividadAdicionalId;
    }

    public void setPagoActividadAdicionalId(Long pagoActividadAdicionalId) {
        this.pagoActividadAdicionalId = pagoActividadAdicionalId;
    }

    public Long getRecaudadorId() {
        return recaudadorId;
    }

    public void setRecaudadorId(Long recaudadorId) {
        this.recaudadorId = recaudadorId;
    }

    public Long getReferenciaPagoActAdicional() {
        return referenciaPagoActAdicional;
    }

    public void setReferenciaPagoActAdicional(Long referenciaPagoActAdicional) {
        this.referenciaPagoActAdicional = referenciaPagoActAdicional;
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

    public Double getRetencion() {
        return retencion;
    }

    public void setRetencion(Double retencion) {
        this.retencion = retencion;
    }

    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public Long getTipoActividadAdicionalId() {
        return tipoActividadAdicionalId;
    }

    public void setTipoActividadAdicionalId(Long tipoActividadAdicionalId) {
        this.tipoActividadAdicionalId = tipoActividadAdicionalId;
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