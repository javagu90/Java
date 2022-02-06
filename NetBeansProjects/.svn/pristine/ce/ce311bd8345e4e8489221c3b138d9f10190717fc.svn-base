/*
 * Corridas.java
 *
 * Created on 14 de septiembre de 2009, 06:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.datos;

import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author asolis
 */
public class Corridas {
    DatoGenerico terminalSalida;
    DatoGenerico origen;
    DatoGenerico destino;
    DatoGenerico empresa;
    DatoGenerico tipoServicio;
    DatoGenerico plantilla;
    DatoGenerico ruta;
    String fechaSalida;
    String horaSalida;
    String tipoItinerario;
    String tipoCorrida;
    String claveCorrida;
    String sentidoViaje;
    int minutosDefasamiento;
    String numeroSenectud; 
    String numeroProfesor;
    String numeroEstudiante;
    String numeroCortesia;
    String asientoNumerado;
    //Adicional2 de tms_corridas_tbl => senectud-profesor-estudiante-cortesia
    
    /** Creates a new instance of Corridas */
    public Corridas() {
    }
    
    public Corridas(Vector cor) {
        setClaveCorrida(cor.get(0).toString());
        setTipoServicio(new DatoGenerico(cor.get(1).toString(),cor.get(2).toString(),cor.get(3).toString()));
        setOrigen(new DatoGenerico(cor.get(4).toString(),cor.get(5).toString(),cor.get(6).toString()));
        setDestino(new DatoGenerico(cor.get(7).toString(),cor.get(8).toString(),cor.get(9).toString()));
        setEmpresa(new DatoGenerico(cor.get(10).toString(),cor.get(11).toString(),cor.get(12).toString()));
        setRuta(new DatoGenerico(cor.get(13).toString(),cor.get(14).toString(),cor.get(15).toString()));
        setPlantilla(new DatoGenerico(cor.get(16).toString(),"",cor.get(17).toString()));
        setFechaSalida(cor.get(18).toString());
        setHoraSalida(cor.get(19).toString());
        setSentidoViaje(cor.get(20).toString());
        setTipoCorrida(cor.get(21).toString());
        setTipoItinerario(cor.get(22).toString());
        String adicional2 = cor.get(23).toString();
        StringTokenizer token = new StringTokenizer(adicional2, "-");       
        setNumeroSenectud(token.nextToken());
        setNumeroProfesor(token.nextToken());
        setNumeroEstudiante(token.nextToken());
        setNumeroCortesia(token.nextToken());   
        setAsientoNumerado(cor.get(24).toString());
    }
    
    public Corridas(String cor) {
        //System.out.println("cor "+cor);
        StringTokenizer token = new StringTokenizer(cor, "|");
        setClaveCorrida(token.nextToken());
        setTipoServicio(new DatoGenerico(token.nextToken(),token.nextToken(),token.nextToken()));
        setOrigen(new DatoGenerico(token.nextToken(),token.nextToken(),token.nextToken()));
        setDestino(new DatoGenerico(token.nextToken(),token.nextToken(),token.nextToken()));
        setEmpresa(new DatoGenerico(token.nextToken(),token.nextToken(),token.nextToken()));
        setRuta(new DatoGenerico(token.nextToken(),token.nextToken(),token.nextToken()));
        setPlantilla(new DatoGenerico(token.nextToken(),"",token.nextToken()));
        setFechaSalida(token.nextToken());
        setHoraSalida(token.nextToken());
        setSentidoViaje(token.nextToken());
        setTipoCorrida(token.nextToken());
        setTipoItinerario(token.nextToken());
        String adicional2 = token.nextToken();
        StringTokenizer token2 = new StringTokenizer(adicional2, "-");       
        setNumeroSenectud(token2.nextToken());
        setNumeroProfesor(token2.nextToken());
        setNumeroEstudiante(token2.nextToken());
        setNumeroCortesia(token2.nextToken());   
        setAsientoNumerado(token.nextToken());
    }
    
    public void setTerminalSalida(DatoGenerico terminalSalida){
        this.terminalSalida = terminalSalida;
    }
    public DatoGenerico getTerminalSalida(){
        return this.terminalSalida;
    }
    
    public void setOrigen(DatoGenerico origen){
        this.origen = origen;
    }
    public DatoGenerico getOrigen(){
        return this.origen;
    }
    
    public void setDestino(DatoGenerico destino){
        this.destino = destino;
    }
    public DatoGenerico getDestino(){
        return this.destino;
    }
    
    public void setEmpresa(DatoGenerico empresa){
        this.empresa = empresa;
    }
    public DatoGenerico getEmpresa(){
        return this.empresa;
    }
    
    public void setTipoServicio(DatoGenerico tipoServicio){
        this.tipoServicio = tipoServicio;
    }
    public DatoGenerico getTipoServicio(){
        return this.tipoServicio;
    }
    
    public void setPlantilla(DatoGenerico plantilla){
        this.plantilla = plantilla;
    }
    public DatoGenerico getPlantilla(){
        return this.plantilla;
    }
    
    public void setRuta(DatoGenerico ruta){
        this.ruta = ruta;
    }
    public DatoGenerico getRuta(){
        return this.ruta;
    }
    
    public String getFechaSalida(){
       return this.fechaSalida;
    }
    public void setFechaSalida(String fechaSalida){
       this.fechaSalida = fechaSalida;
    }
    
    public String getHoraSalida(){
       return this.horaSalida;
    }
    public void setHoraSalida(String horaSalida){
       this.horaSalida = horaSalida;
    }
    
    public String getTipoItinerario(){
       return this.tipoItinerario;
    }
    public void setTipoItinerario(String tipoItinerario){
       this.tipoItinerario = tipoItinerario;
    }
    
    public String getTipoCorrida(){
       return this.tipoCorrida;
    }
    public void setTipoCorrida(String tipoCorrida){
       this.tipoCorrida = tipoCorrida;
    }
    
    public String getClaveCorrida(){
       return this.claveCorrida;
    }
    public void setClaveCorrida(String claveCorrida){
       this.claveCorrida = claveCorrida;
    }
    
    public String getSentidoViaje(){
       return this.sentidoViaje;
    }
    public void setSentidoViaje(String sentidoViaje){
       this.sentidoViaje = sentidoViaje;
    }
    
    public String getNumeroSenectud(){
       return this.numeroSenectud;
    }
    public void setNumeroSenectud(String numeroSenectud){
       this.numeroSenectud = numeroSenectud;
    }
    
    public String getNumeroProfesor(){
       return this.numeroProfesor;
    }
    public void setNumeroProfesor(String numeroProfesor){
       this.numeroProfesor = numeroProfesor;
    }
    
    public String getNumeroEstudiante(){
       return this.numeroEstudiante;
    }
    public void setNumeroEstudiante(String numeroEstudiante){
       this.numeroEstudiante = numeroEstudiante;
    }
    
    public String getNumeroCortesia(){
       return this.numeroCortesia;
    }
    public void setNumeroCortesia(String numeroCortesia){
       this.numeroCortesia = numeroCortesia;
    }
    
    public String getAsientoNumerado(){
       return this.asientoNumerado;
    }
    public void setAsientoNumerado(String asientoNumerado){
       this.asientoNumerado = asientoNumerado;
    }       
}
