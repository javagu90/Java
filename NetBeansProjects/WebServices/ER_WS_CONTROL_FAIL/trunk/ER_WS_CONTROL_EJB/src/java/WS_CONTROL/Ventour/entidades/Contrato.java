/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.Ventour.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author brojas
 */
public class Contrato  implements Serializable{
private Long		Contrato_ID;
    private String Cotizacion_Referencia;
    private String Contrato_Numero;
    private List<Unidad> Unidad;
    private String Refrigerio_Abordo;
    private int NO_personas_Refrigerio;
    private String Viaje_Redondo;
    private String Lugar_Origen;
    private String Lugar_Destino;
    private String Fecha_Hora_Salida;
    private String Lugar_Salida;
    private String Fecha_Hora_Llegada;
    private String Lugar_Llegada;
    private String Nombre_Cliente_Contrato;
    private String Direccion_Cliente_Contrato;
    private String Telefono_Cliente_Contrato;
    private BigDecimal Importe_Servicio;
    private List<TramoRuta> TramoRuta;
    private String FormaPago;
    private String Observaciones;
    private String CiudadContrato;
    private String Regreso_Fecha_Salida;
    private String Regreso_Lugar_Salida;
    private String Regreso_Ciudad_Salida;
    private String Regreso_Fecha_Llegada;
    private String Regreso_Lugar_Llegada;
    private String Regreso_Ciudad_Llegada;
    private String Estado;

    /**
     * @return the Contrato_ID
     */
    public Long getContrato_ID() {
        return Contrato_ID;
    }

    /**
     * @param Contrato_ID the Contrato_ID to set
     */
    public void setContrato_ID(Long Contrato_ID) {
        this.Contrato_ID = Contrato_ID;
    }

    /**
     * @return the Cotizacion_Referencia
     */
    public String getCotizacion_Referencia() {
        return Cotizacion_Referencia;
    }

    /**
     * @param Cotizacion_Referencia the Cotizacion_Referencia to set
     */
    public void setCotizacion_Referencia(String Cotizacion_Referencia) {
        this.Cotizacion_Referencia = Cotizacion_Referencia;
    }

    /**
     * @return the Contrato_Numero
     */
    public String getContrato_Numero() {
        return Contrato_Numero;
    }

    /**
     * @param Contrato_Numero the Contrato_Numero to set
     */
    public void setContrato_Numero(String Contrato_Numero) {
        this.Contrato_Numero = Contrato_Numero;
    }

   

    /**
     * @return the NO_PERSONAS_Refrigerio
     */
    public int getNO_personas_Refrigerio() {
        return NO_personas_Refrigerio;
    }

    /**
     * @param NO_PERSONAS_Refrigerio the NO_PERSONAS_Refrigerio to set
     */
    public void setNO_personas_Refrigerio(int NO_personas_Refrigerio) {
        this.NO_personas_Refrigerio = NO_personas_Refrigerio;
    }

    /**
     * @return the VIAJE_Redondo
     */
    public String getViaje_Redondo() {
        return Viaje_Redondo;
    }

    /**
     * @param VIAJE_Redondo the VIAJE_Redondo to set
     */
    public void setViaje_Redondo(String Viaje_Redondo) {
        this.Viaje_Redondo = Viaje_Redondo;
    }

    /**
     * @return the Lugar_Origen
     */
    public String getLugar_Origen() {
        return Lugar_Origen;
    }

    /**
     * @param Lugar_Origen the Lugar_Origen to set
     */
    public void setLugar_Origen(String Lugar_Origen) {
        this.Lugar_Origen = Lugar_Origen;
    }

    /**
     * @return the Lugar_Destino
     */
    public String getLugar_Destino() {
        return Lugar_Destino;
    }

    /**
     * @param Lugar_Destino the Lugar_Destino to set
     */
    public void setLugar_Destino(String Lugar_Destino) {
        this.Lugar_Destino = Lugar_Destino;
    }

    /**
     * @return the Fecha_Hora_Salida
     */
    public String getFecha_Hora_Salida() {
        return Fecha_Hora_Salida;
    }

    /**
     * @param Fecha_Hora_Salida the Fecha_Hora_Salida to set
     */
    public void setFecha_Hora_Salida(String Fecha_Hora_Salida) {
        this.Fecha_Hora_Salida = Fecha_Hora_Salida;
    }

    /**
     * @return the Lugar_Salida
     */
    public String getLugar_Salida() {
        return Lugar_Salida;
    }

    /**
     * @param Lugar_Salida the Lugar_Salida to set
     */
    public void setLugar_Salida(String Lugar_Salida) {
        this.Lugar_Salida = Lugar_Salida;
    }

    /**
     * @return the Fecha_Hora_Llegada
     */
    public String getFecha_Hora_Llegada() {
        return Fecha_Hora_Llegada;
    }

    /**
     * @param Fecha_Hora_Llegada the Fecha_Hora_Llegada to set
     */
    public void setFecha_Hora_Llegada(String Fecha_Hora_Llegada) {
        this.Fecha_Hora_Llegada = Fecha_Hora_Llegada;
    }

    /**
     * @return the Lugar_Llegada
     */
    public String getLugar_Llegada() {
        return Lugar_Llegada;
    }

    /**
     * @param Lugar_Llegada the Lugar_Llegada to set
     */
    public void setLugar_Llegada(String Lugar_Llegada) {
        this.Lugar_Llegada = Lugar_Llegada;
    }

    /**
     * @return the Nomnbre_Cliente_Contrato
     */
    public String getNombre_Cliente_Contrato() {
        return Nombre_Cliente_Contrato;
    }

    /**
     * @param Nomnbre_Cliente_Contrato the Nomnbre_Cliente_Contrato to set
     */
    public void setNombre_Cliente_Contrato(String Nombre_Cliente_Contrato) {
        this.Nombre_Cliente_Contrato = Nombre_Cliente_Contrato;
    }

    /**
     * @return the Direccion_Cliente_Contrato
     */
    public String getDireccion_Cliente_Contrato() {
        return Direccion_Cliente_Contrato;
    }

    /**
     * @param Direccion_Cliente_Contrato the Direccion_Cliente_Contrato to set
     */
    public void setDireccion_Cliente_Contrato(String Direccion_Cliente_Contrato) {
        this.Direccion_Cliente_Contrato = Direccion_Cliente_Contrato;
    }

    /**
     * @return the Telefono_Cliente_Contrato
     */
    public String getTelefono_Cliente_Contrato() {
        return Telefono_Cliente_Contrato;
    }

    /**
     * @param Telefono_Cliente_Contrato the Telefono_Cliente_Contrato to set
     */
    public void setTelefono_Cliente_Contrato(String Telefono_Cliente_Contrato) {
        this.Telefono_Cliente_Contrato = Telefono_Cliente_Contrato;
    }

    /**
     * @return the Importe_Sservicio
     */
    public BigDecimal getImporte_Servicio() {
        return Importe_Servicio;
    }

    /**
     * @param Importe_Sservicio the Importe_Sservicio to set
     */
    public void setImporte_Servicio(BigDecimal Importe_Sservicio) {
        this.Importe_Servicio = Importe_Sservicio;
    }

    /**
     * @return the FormaPago
     */
    public String getFormaPago() {
        return FormaPago;
    }

    /**
     * @param FormaPago the FormaPago to set
     */
    public void setFormaPago(String FormaPago) {
        this.FormaPago = FormaPago;
    }

    /**
     * @return the Observaciones
     */
    public String getObservaciones() {
        return Observaciones;
    }

    /**
     * @param Observaciones the Observaciones to set
     */
    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    /**
     * @return the Regreso_Fecha_Salida
     */
    public String getRegreso_Fecha_Salida() {
        return Regreso_Fecha_Salida;
    }

    /**
     * @param Regreso_Fecha_Salida the Regreso_Fecha_Salida to set
     */
    public void setRegreso_Fecha_Salida(String Regreso_Fecha_Salida) {
        this.Regreso_Fecha_Salida = Regreso_Fecha_Salida;
    }

    /**
     * @return the Regreso_Lugar_Salida
     */
    public String getRegreso_Lugar_Salida() {
        return Regreso_Lugar_Salida;
    }

    /**
     * @param Regreso_Lugar_Salida the Regreso_Lugar_Salida to set
     */
    public void setRegreso_Lugar_Salida(String Regreso_Lugar_Salida) {
        this.Regreso_Lugar_Salida = Regreso_Lugar_Salida;
    }

    /**
     * @return the Regreso_Ciudad_Salida
     */
    public String getRegreso_Ciudad_Salida() {
        return Regreso_Ciudad_Salida;
    }

    /**
     * @param Regreso_Ciudad_Salida the Regreso_Ciudad_Salida to set
     */
    public void setRegreso_Ciudad_Salida(String Regreso_Ciudad_Salida) {
        this.Regreso_Ciudad_Salida = Regreso_Ciudad_Salida;
    }

    /**
     * @return the Regreso_Fecha_LLEGAD
     */
    public String getRegreso_Fecha_Llegada() {
        return Regreso_Fecha_Llegada;
    }

    /**
     * @param Regreso_Fecha_LLEGAD the Regreso_Fecha_LLEGAD to set
     */
    public void setRegreso_Fecha_Llegada(String Regreso_Fecha_Llegada) {
        this.Regreso_Fecha_Llegada= Regreso_Fecha_Llegada;
    }

    /**
     * @return the Regreso_Lugar_Llegada
     */
    public String getRegreso_Lugar_Llegada() {
        return Regreso_Lugar_Llegada;
    }

    /**
     * @param Regreso_Lugar_Llegada the Regreso_Lugar_Llegada to set
     */
    public void setRegreso_Lugar_Llegada(String Regreso_Lugar_Llegada) {
        this.Regreso_Lugar_Llegada = Regreso_Lugar_Llegada;
    }

    /**
     * @return the Regreso_Ciudad_Llegada
     */
    public String getRegreso_Ciudad_Llegada() {
        return Regreso_Ciudad_Llegada;
    }

    /**
     * @param Regreso_Ciudad_Llegada the Regreso_Ciudad_Llegada to set
     */
    public void setRegreso_Ciudad_Llegada(String Regreso_Ciudad_Llegada) {
        this.Regreso_Ciudad_Llegada = Regreso_Ciudad_Llegada;
    }

    /**
     * @return the Estado
     */
    public String getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

  
    /**
     * @return the CiudadContrato
     */
    public String getCiudadContrato() {
        return CiudadContrato;
    }

    /**
     * @param CiudadContrato the CiudadContrato to set
     */
    public void setCiudadContrato(String CiudadContrato) {
        this.CiudadContrato = CiudadContrato;
    }

    /**
     * @return the Unidad
     */
    public List<Unidad> getUnidad() {
        return Unidad;
    }

    /**
     * @param Unidad the Unidad to set
     */
    public void setUnidad(List<Unidad> Unidad) {
        this.Unidad = Unidad;
    }

    /**
     * @return the TramoRuta
     */
    public List<TramoRuta> getTramoRuta() {
        return TramoRuta;
    }

    /**
     * @param TramoRuta the TramoRuta to set
     */
    public void setTramoRuta(List<TramoRuta> TramoRuta) {
        this.TramoRuta = TramoRuta;
    }

    /**
     * @return the Refrigerio_Abordo
     */
    public String getRefrigerio_Abordo() {
        return Refrigerio_Abordo;
    }

    /**
     * @param Refrigerio_Abordo the Refrigerio_Abordo to set
     */
    public void setRefrigerio_Abordo(String Refrigerio_Abordo) {
        this.Refrigerio_Abordo = Refrigerio_Abordo;
    }


}
