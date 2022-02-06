/*
 * Itinerario.java
 *
 * Created on 27 de noviembre de 2008, 09:44 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.clases.datos;

/**
 *
 * @author ocruz
 */
public class Itinerario {
    
    private DatoGenerico oficina;
    private String horaSalida;
    private String horaLlegada;
    private int diasDefasamiento;
    private String tipoItinerario;
    private String tipoCorrida;
        
    /** Creates a new instance of Itinerario */
    public Itinerario() {
    }

    public DatoGenerico getOficina() {
        return oficina;
    }

    public void setOficina(DatoGenerico oficina) {
        this.oficina = oficina;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public int getDiasDefasamiento() {
        return diasDefasamiento;
    }

    public void setDiasDefasamiento(int diasDefasamiento) {
        this.diasDefasamiento = diasDefasamiento;
    }

    public String getTipoItinerario() {
        return tipoItinerario;
    }

    public void setTipoItinerario(String tipoItinerario) {
        this.tipoItinerario = tipoItinerario;
    }

    public String getTipoCorrida() {
        return tipoCorrida;
    }

    public void setTipoCorrida(String tipoCorrida) {
        this.tipoCorrida = tipoCorrida;
    }    
}
