/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ERTMSWS.clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Vector;

/**
 *
 * @author vgonzalez
 */
public class BoletoTransaccion implements Serializable{
    private String folio="";
    private String origen="";
    private String origenCorrida="";
    private String destino="";
    private BigDecimal importe;
    private String tipoPasajero="";
    private BigInteger noAsiento;
    private String fechaViaje="";
    private String horaViaje="";
    private String boletoViajado="";
    private String boletoFacturado="";
    private String folioFactura="";
    private String sentidoViaje="";
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigInteger edad;
    private String nombrePasajero="";
    private String codigoBarras="";
    private String cargo ="$0.00";
    private String estado="Activo".toUpperCase();

public BoletoTransaccion (){

}

public BoletoTransaccion (Vector datos){
    this.folio = datos.get(0).toString();
    this.origen = datos.get(1).toString();
    this.destino = datos.get(2).toString();
    this.importe = BigDecimal.valueOf(Double.valueOf(datos.get(3).toString()));
    this.tipoPasajero = datos.get(4).toString();
    this.fechaViaje = datos.get(5).toString();
    this.horaViaje = datos.get(6).toString();
    this.noAsiento =BigInteger.valueOf(Long.valueOf(datos.get(7).toString()));
    this.sentidoViaje =datos.get(8).toString();
    this.boletoViajado = datos.get(9).toString();
    this.boletoFacturado = datos.get(10).toString();
    this.folioFactura = (datos.get(11)==null?"":datos.get(11).toString());
    this.subtotal = BigDecimal.valueOf(Double.valueOf(datos.get(12).toString()));
    this.iva = BigDecimal.valueOf(Double.valueOf(datos.get(13).toString()));
    this.edad =BigInteger.valueOf(Long.valueOf(datos.get(14).toString()));
    this.origenCorrida = datos.get(15).toString();
    this.nombrePasajero = datos.get(16).toString();
    this.codigoBarras = datos.get(17).toString();
}

public BoletoTransaccion (String pdatos){
    String[] datos = pdatos.split(",");
    this.folio = datos[0];
    this.origen = datos[1];
    this.destino = datos[2];
    this.importe = BigDecimal.valueOf(Double.valueOf(datos[3]));
    this.tipoPasajero = datos[4];
    this.fechaViaje = datos[5];
    this.horaViaje = datos[6];
    this.noAsiento =BigInteger.valueOf(Long.valueOf(datos[7]));
    this.sentidoViaje =datos[8];
    this.boletoViajado = datos[9];
    this.boletoFacturado = datos[10];
    this.folioFactura = (datos[11]==null?"":datos[11]);
    this.subtotal = BigDecimal.valueOf(Double.valueOf(datos[12]));
    this.iva = BigDecimal.valueOf(Double.valueOf(datos[13]));
    this.edad =BigInteger.valueOf(Long.valueOf(datos[14]));
    this.origenCorrida = datos[15];
    this.nombrePasajero = datos[16];
    this.codigoBarras = datos[17];
}
    /**
     * @return the folio
     */
    public String getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(String folio) {
        this.folio = folio;
    }

    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the importe
     */
    public BigDecimal getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    /**
     * @return the tipoPasajero
     */
    public String getTipoPasajero() {
        return tipoPasajero;
    }

    /**
     * @param tipoPasajero the tipoPasajero to set
     */
    public void setTipoPasajero(String tipoPasajero) {
        this.tipoPasajero = tipoPasajero;
    }

    /**
     * @return the noAsiento
     */
    public BigInteger getNoAsiento() {
        return noAsiento;
    }

    /**
     * @param noAsiento the noAsiento to set
     */
    public void setNoAsiento(BigInteger noAsiento) {
        this.noAsiento = noAsiento;
    }

    /**
     * @return the fechaViaje
     */
    public String getFechaViaje() {
        return fechaViaje;
    }

    /**
     * @param fechaViaje the fechaViaje to set
     */
    public void setFechaViaje(String fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    /**
     * @return the horaViaje
     */
    public String getHoraViaje() {
        return horaViaje;
    }

    /**
     * @param horaViaje the horaViaje to set
     */
    public void setHoraViaje(String horaViaje) {
        this.horaViaje = horaViaje;
    }

    /**
     * @return the boletoViajado
     */
    public String getBoletoViajado() {
        return boletoViajado;
    }

    /**
     * @param boletoViajado the boletoViajado to set
     */
    public void setBoletoViajado(String boletoViajado) {
        this.boletoViajado = boletoViajado;
    }

    /**
     * @return the boletoFacturado
     */
    public String getBoletoFacturado() {
        return boletoFacturado;
    }

    /**
     * @param boletoFacturado the boletoFacturado to set
     */
    public void setBoletoFacturado(String boletoFacturado) {
        this.boletoFacturado = boletoFacturado;
    }

    /**
     * @return the folioFactura
     */
    public String getFolioFactura() {
        return folioFactura;
    }

    /**
     * @param folioFactura the folioFactura to set
     */
    public void setFolioFactura(String folioFactura) {
        this.folioFactura = folioFactura;
    }

    /**
     * @return the sentidoViaje
     */
    public String getSentidoViaje() {
        return sentidoViaje;
    }

    /**
     * @param sentidoViaje the sentidoViaje to set
     */
    public void setSentidoViaje(String sentidoViaje) {
        this.sentidoViaje = sentidoViaje;
    }

    /**
     * @return the subtotal
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @return the iva
     */
    public BigDecimal getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    /**
     * @return the edad
     */
    public BigInteger getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(BigInteger edad) {
        this.edad = edad;
    }

    /**
     * @return the origenCorrida
     */
    public String getOrigenCorrida() {
        return origenCorrida;
    }

    /**
     * @param origenCorrida the origenCorrida to set
     */
    public void setOrigenCorrida(String origenCorrida) {
        this.origenCorrida = origenCorrida;
    }

    /**
     * @return the nombrePasajero
     */
    public String getNombrePasajero() {
        return nombrePasajero;
    }

    /**
     * @param nombrePasajero the nombrePasajero to set
     */
    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    /**
     * @return the codigoBarras
     */
    public String getCodigoBarras() {
        return codigoBarras;
    }

    /**
     * @param codigoBarras the codigoBarras to set
     */
    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

}
