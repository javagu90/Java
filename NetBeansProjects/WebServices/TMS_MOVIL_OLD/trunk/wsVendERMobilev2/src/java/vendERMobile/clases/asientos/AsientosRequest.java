/*
 * AsientosRequest.java
 *
 * Created on 17 de septiembre de 2009, 01:47 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.asientos;

import vendERMobile.clases.datos.Corridas;
import vendERMobile.clases.datos.Sesion;

/**
 *
 * @author asolis
 */
public class AsientosRequest {
    String uid;
    Sesion sesion;
    Corridas corrida;
    /** Creates a new instance of AsientosRequest */
    public AsientosRequest() {
    }
     public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
    }
    
    public void setSesion(Sesion sesion){
        this.sesion = sesion;
    }
    public Sesion getSesion(){
        return this.sesion;
    }
    
    public void setCorrida(Corridas corrida){
        this.corrida = corrida;
    }
    public Corridas getCorrida(){
        return this.corrida;
    }    
}
