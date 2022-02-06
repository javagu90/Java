/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.Ventour.entidades;

import java.io.Serializable;

/**
 *
 * @author brojas
 */
public class TarifaFija implements Serializable {

private int Tarifa_fija_id=0;
private  String Origen="";
private String Destino="";
private int Flotilla_id=0;
private double Tarifa_2h=0;
private double Tarifa_4h=0;
private double Tarifa_6h=0;
private double Tarifa_8h=0;
private double Tarifa_10h=0;
private double Tarifa_12h=0;
private double Tarifa_dia=0;
private double Estancia=0;
private double Hora_Extra=0;
private double Precio_conductor=0;
private String Aplica_Iva = "S";
private String Aplica_Hora_Extra= "N";
private String Aplica_Estancia = "S";
private String Adicional1="";
private String Adicional2="";
private String Adicional3="";  
private String Adicional4="";
private String Adicional5="";
private String Adicional6="";
private String Adicional7="";
private String Adicional8="";
private String Adicional9="";
private String Adicional10="";
private int  creado_por       ;
private String  fecha_ceacion;
private int ultima_actualizacion_por ;
private String ultima_fecha_actualizacion ;
private String NombreFlotilla="" ;

    /**
     * @return the Tarifa_fija_id
     */
    public int getTarifa_fija_id() {
        return Tarifa_fija_id;
    }

    /**
     * @param Tarifa_fija_id the Tarifa_fija_id to set
     */
    public void setTarifa_fija_id(int Tarifa_fija_id) {
        this.Tarifa_fija_id = Tarifa_fija_id;
    }

    /**
     * @return the Origen
     */
    public String getOrigen() {
        return Origen;
    }

    /**
     * @param Origen the Origen to set
     */
    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    /**
     * @return the Destino
     */
    public String getDestino() {
        return Destino;
    }

    /**
     * @param Destino the Destino to set
     */
    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    /**
     * @return the Flotilla_id
     */
    public int getFlotilla_id() {
        return Flotilla_id;
    }

    /**
     * @param Flotilla_id the Flotilla_id to set
     */
    public void setFlotilla_id(int Flotilla_id) {
        this.Flotilla_id = Flotilla_id;
    }

    /**
     * @return the Tarifa_2h
     */
    public double getTarifa_2h() {
        return Tarifa_2h;
    }

    /**
     * @param Tarifa_2h the Tarifa_2h to set
     */
    public void setTarifa_2h(double Tarifa_2h) {
        this.Tarifa_2h = Tarifa_2h;
    }

    /**
     * @return the Tarifa_4h
     */
    public double getTarifa_4h() {
        return Tarifa_4h;
    }

    /**
     * @param Tarifa_4h the Tarifa_4h to set
     */
    public void setTarifa_4h(double Tarifa_4h) {
        this.Tarifa_4h = Tarifa_4h;
    }

    /**
     * @return the Tarifa_6h
     */
    public double getTarifa_6h() {
        return Tarifa_6h;
    }

    /**
     * @param Tarifa_6h the Tarifa_6h to set
     */
    public void setTarifa_6h(double Tarifa_6h) {
        this.Tarifa_6h = Tarifa_6h;
    }

    /**
     * @return the Tarifa_8h
     */
    public double getTarifa_8h() {
        return Tarifa_8h;
    }

    /**
     * @param Tarifa_8h the Tarifa_8h to set
     */
    public void setTarifa_8h(double Tarifa_8h) {
        this.Tarifa_8h = Tarifa_8h;
    }

    /**
     * @return the Tarifa_10h
     */
    public double getTarifa_10h() {
        return Tarifa_10h;
    }

    /**
     * @param Tarifa_10h the Tarifa_10h to set
     */
    public void setTarifa_10h(double Tarifa_10h) {
        this.Tarifa_10h = Tarifa_10h;
    }

    /**
     * @return the Tarifa_12h
     */
    public double getTarifa_12h() {
        return Tarifa_12h;
    }

    /**
     * @param Tarifa_12h the Tarifa_12h to set
     */
    public void setTarifa_12h(double Tarifa_12h) {
        this.Tarifa_12h = Tarifa_12h;
    }

    /**
     * @return the Tarifa_dia
     */
    public double getTarifa_dia() {
        return Tarifa_dia;
    }

    /**
     * @param Tarifa_dia the Tarifa_dia to set
     */
    public void setTarifa_dia(double Tarifa_dia) {
        this.Tarifa_dia = Tarifa_dia;
    }

    /**
     * @return the Estancia
     */
    public double getEstancia() {
        return Estancia;
    }

    /**
     * @param Estancia the Estancia to set
     */
    public void setEstancia(double Estancia) {
        this.Estancia = Estancia;
    }

    /**
     * @return the Precio_conductor
     */
    public double getPrecio_conductor() {
        return Precio_conductor;
    }

    /**
     * @param Precio_conductor the Precio_conductor to set
     */
    public void setPrecio_conductor(double Precio_conductor) {
        this.Precio_conductor = Precio_conductor;
    }

    /**
     * @return the Adicional1
     */
    public String getAdicional1() {
        return Adicional1;
    }

    /**
     * @param Adicional1 the Adicional1 to set
     */
    public void setAdicional1(String Adicional1) {
        this.Adicional1 = Adicional1;
    }

    /**
     * @return the Adicional2
     */
    public String getAdicional2() {
        return Adicional2;
    }

    /**
     * @param Adicional2 the Adicional2 to set
     */
    public void setAdicional2(String Adicional2) {
        this.Adicional2 = Adicional2;
    }

    /**
     * @return the Adicional3
     */
    public String getAdicional3() {
        return Adicional3;
    }

    /**
     * @param Adicional3 the Adicional3 to set
     */
    public void setAdicional3(String Adicional3) {
        this.Adicional3 = Adicional3;
    }

    /**
     * @return the Adicional4
     */
    public String getAdicional4() {
        return Adicional4;
    }

    /**
     * @param Adicional4 the Adicional4 to set
     */
    public void setAdicional4(String Adicional4) {
        this.Adicional4 = Adicional4;
    }

    /**
     * @return the Adicional5
     */
    public String getAdicional5() {
        return Adicional5;
    }

    /**
     * @param Adicional5 the Adicional5 to set
     */
    public void setAdicional5(String Adicional5) {
        this.Adicional5 = Adicional5;
    }

    /**
     * @return the Adicional6
     */
    public String getAdicional6() {
        return Adicional6;
    }

    /**
     * @param Adicional6 the Adicional6 to set
     */
    public void setAdicional6(String Adicional6) {
        this.Adicional6 = Adicional6;
    }

    /**
     * @return the Adicional7
     */
    public String getAdicional7() {
        return Adicional7;
    }

    /**
     * @param Adicional7 the Adicional7 to set
     */
    public void setAdicional7(String Adicional7) {
        this.Adicional7 = Adicional7;
    }

    /**
     * @return the Adicional8
     */
    public String getAdicional8() {
        return Adicional8;
    }

    /**
     * @param Adicional8 the Adicional8 to set
     */
    public void setAdicional8(String Adicional8) {
        this.Adicional8 = Adicional8;
    }

    /**
     * @return the Adicional9
     */
    public String getAdicional9() {
        return Adicional9;
    }

    /**
     * @param Adicional9 the Adicional9 to set
     */
    public void setAdicional9(String Adicional9) {
        this.Adicional9 = Adicional9;
    }

    /**
     * @return the Adicional10
     */
    public String getAdicional10() {
        return Adicional10;
    }

    /**
     * @param Adicional10 the Adicional10 to set
     */
    public void setAdicional10(String Adicional10) {
        this.Adicional10 = Adicional10;
    }

    /**
     * @return the creado_por
     */
    public int getCreado_por() {
        return creado_por;
    }

    /**
     * @param creado_por the creado_por to set
     */
    public void setCreado_por(int creado_por) {
        this.creado_por = creado_por;
    }

    /**
     * @return the fecha_ceacion
     */
    public String getFecha_ceacion() {
        return fecha_ceacion;
    }

    /**
     * @param fecha_ceacion the fecha_ceacion to set
     */
    public void setFecha_ceacion(String fecha_ceacion) {
        this.fecha_ceacion = fecha_ceacion;
    }

    /**
     * @return the ultima_actualizacion_por
     */
    public int getUltima_actualizacion_por() {
        return ultima_actualizacion_por;
    }

    /**
     * @param ultima_actualizacion_por the ultima_actualizacion_por to set
     */
    public void setUltima_actualizacion_por(int ultima_actualizacion_por) {
        this.ultima_actualizacion_por = ultima_actualizacion_por;
    }

    /**
     * @return the ultima_fecha_actualizacion
     */
    public String getUltima_fecha_actualizacion() {
        return ultima_fecha_actualizacion;
    }

    /**
     * @param ultima_fecha_actualizacion the ultima_fecha_actualizacion to set
     */
    public void setUltima_fecha_actualizacion(String ultima_fecha_actualizacion) {
        this.ultima_fecha_actualizacion = ultima_fecha_actualizacion;
    }

    /**
     * @return the NombreFlotilla
     */
    public String getNombreFlotilla() {
        return NombreFlotilla;
    }

    /**
     * @param NombreFlotilla the NombreFlotilla to set
     */
    public void setNombreFlotilla(String NombreFlotilla) {
        this.NombreFlotilla = NombreFlotilla;
    }

    /**
     * @return the Aplica_Iva
     */
    public String getAplica_Iva() {
        return Aplica_Iva;
    }

    /**
     * @param Aplica_Iva the Aplica_Iva to set
     */
    public void setAplica_Iva(String Aplica_Iva) {
        this.Aplica_Iva = Aplica_Iva;
    }

    /**
     * @return the Aplica_Hora_Extra
     */
    public String getAplica_Hora_Extra() {
        return Aplica_Hora_Extra;
    }

    /**
     * @param Aplica_Hora_Extra the Aplica_Hora_Extra to set
     */
    public void setAplica_Hora_Extra(String Aplica_Hora_Extra) {
        this.Aplica_Hora_Extra = Aplica_Hora_Extra;
    }

    /**
     * @return the Aplica_Estancia
     */
    public String getAplica_Estancia() {
        return Aplica_Estancia;
    }

    /**
     * @param Aplica_Estancia the Aplica_Estancia to set
     */
    public void setAplica_Estancia(String Aplica_Estancia) {
        this.Aplica_Estancia = Aplica_Estancia;
    }

    /**
     * @return the Hora_Extra
     */
    public double getHora_Extra() {
        return Hora_Extra;
    }

    /**
     * @param Hora_Extra the Hora_Extra to set
     */
    public void setHora_Extra(double Hora_Extra) {
        this.Hora_Extra = Hora_Extra;
    }

}
