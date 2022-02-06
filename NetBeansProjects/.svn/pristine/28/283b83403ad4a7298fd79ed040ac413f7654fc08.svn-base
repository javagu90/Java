/*
 * Tarifas.java
 *
 * Created on 10 de septiembre de 2009, 02:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package vendERMobile.clases.datos;

import java.util.Vector;

/**
 *
 * @author asolis
 */
public class Tarifas {
    int idRuta;
    float tarifaImporte;
    String tramoId;
    String tarifaTipo;
    TiposPasajero tipoPasajero;
     /** Creates a new instance of Tarifas */
    public Tarifas() {
    }
    
    public Tarifas(Vector tar) {
        //System.out.println("tar "+tar);
        setIdRuta(Integer.parseInt(tar.get(0).toString()));
        setTramoId(tar.get(1).toString());
        setTiposPasajero(new TiposPasajero(tar.get(2).toString()));
                                            /*tar.get(5).toString(),
                                            tar.get(6).toString(),
                                            tar.get(9).toString()));*/
        setTarifaTipo(tar.get(3).toString()); 
        setTarifaImporte(Float.parseFloat(tar.get(4).toString()));                              
    }
    
    public void setIdRuta(int idRuta){
        this.idRuta = idRuta;
    }
    public int getIdRuta(){
        return this.idRuta;
    }
    
    public void setTarifaImporte(float tarifaImporte){
        this.tarifaImporte = tarifaImporte;
    }
    public float getTarifaImporte(){
        return this.tarifaImporte;
    }
    
    public void setTiposPasajero(TiposPasajero tipoPasajero){
        this.tipoPasajero = tipoPasajero;
    }
    public TiposPasajero getTiposPasajero(){
        return this.tipoPasajero;
    }   
    
    public void setTarifaTipo(String tarifaTipo){
        this.tarifaTipo = tarifaTipo;
    }
    public String getTarifaTipo(){
        return this.tarifaTipo;
    }
    
    public void setTramoId(String tramoId){
        this.tramoId = tramoId;
    }
    public String getTramoId(){
        return this.tramoId;
    }
}
