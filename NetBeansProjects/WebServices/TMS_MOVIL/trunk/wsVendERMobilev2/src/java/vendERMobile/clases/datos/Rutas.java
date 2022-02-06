/*
 * Rutas.java
 *
 * Created on 20 de octubre de 2009, 05:07 PM
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
public class Rutas {
    DatoGenerico origen;
    DatoGenerico destino;
    DatoGenerico servicio;
    String modoFueraLinea;
    String rutaId;
    
    /** Creates a new instance of Rutas */
    public Rutas() {
    }
    
    public Rutas(Vector cor) {
        setOrigen(new DatoGenerico(cor.get(0).toString(), cor.get(1).toString(),""));
        setDestino(new DatoGenerico(cor.get(2).toString(), cor.get(3).toString(),""));
        setServicio(new DatoGenerico(cor.get(4).toString(), cor.get(5).toString(),""));
        setModoFueraLinea(cor.get(7).toString());
        setRutaId(cor.get(6).toString());
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
    
    public void setServicio(DatoGenerico servicio){
        this.servicio = servicio;
    }
    public DatoGenerico getServicio(){
        return this.servicio;
    }
    
    public void setModoFueraLinea(String modoFueraLinea){
        this.modoFueraLinea = modoFueraLinea;
    }
    public String getModoFueraLinea(){
        return this.modoFueraLinea;
    }

    public void setRutaId(String rutaId){
        this.rutaId = rutaId;
    }
    public String getRutaId(){
        return this.rutaId;
    }
    
}
