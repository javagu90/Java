/*
 * Paquete.java
 *
 * Created on 18 de diciembre de 2007, 01:34 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package WS_CONTROL.paquer.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author galbarran
 */
@Entity
@Table(name="PAQ_PAQUETES_TBL")
public class Paquete implements Serializable {

    @Id
    @Column(name = "ALTO")
    private int alto = 0;
    
    @Column(name = "ANCHO")
    private int ancho = 0;
    
    @Column(name = "LARGO")
    private int largo = 0;
    
    @Column(name = "PESO")
    private float peso = 0f;
    
    @Column(name = "CONTENIDO")
    private String contenido;
    
//    @Column(name = "PRECIO")
//    private float tarifa = 0f;
//    
//    @Column(name = "PRECIO_COTIZADO")
//    private float tarifaCotizada = 0f;
//    
//    @Column(name= "FLEJE")
//    private float fleje;
    
    @Column(name= "NUM")
    private int numIni;
    
    @Column(name= "CANTIDAD")
    private int cantidad = 1;
     
    @Column(name="SOBRE")
    private String tipoEnvio = "N";


    
   // private String paquete = "N"; 
   // private String carga = "N";
     
           
    /** Creates a new instance of Paquete */
    public Paquete() {
    }


    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    public float getPesoVolumetrico(boolean GP){
        //return (( alto * ancho) * largo )/6000;
            //VAGL 27102011 Autorizado por Ivan Muooz y Solicitado por Gabriel Aguirre, Se divide ahora por 5500 y anteriormente se dividia entre 6000
            double s = 5500;
            if(GP) s = 6000;
            double d = (alto * ancho * largo) / s;
            double x =0;
            try {
                x = roundNum(d);
            } catch (Exception ex) {
                Logger.getLogger(Paquete.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        return Float.valueOf(""+x);
    }
    
    public float getPesoMayor(boolean GP){
            float pesoVol = getPesoVolumetrico(GP);
//            double s = 6000;
//            double d = (10 * 10 * 10) / s;
//            double m = (10 * 10) * 10;
//            double n = m / 6000;
//        double x =0;
//            System.out.println("Medidas: ( " + alto + " * " + ancho + " * " + largo + " )/6000 ==> " + (double) (( 10 * 10 * 10 )/6000) + "   ==> " + (((10 * 10) * 10) / 6000) + "  Double ==> " + d + "   M = " + m + "  N: " + n +"  X= "+x);
            System.out.println("Calcula peso,  volumetrico : " + pesoVol + "   Peso(kg): " + getPeso());
            return pesoVol > getPeso() ? pesoVol : getPeso();
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
    
    public float getVolumen(){
        return alto * ancho * largo;
    }
    
    public String getStrVolumen(){
        if ( isSobre() )
            return "";
        else
            return alto + "x" + ancho + "x" + largo;
    }
    
    /**
     * Returna un valor de codigo hash para el objeto.  Esta implementacion computa
     * un valor de codigo hash basado sobre los campos id en este objeto.
     * @return  un valor de codigo hash para este objeto.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        //hash += (this.paqueteId != null ? this.paqueteId.hashCode() : 0);
        return hash;
    }

    /**
     * Determina si otro objeto es igual a este APaquetes.  El resultado es  
     * <code>verdadero</code> so y solo so el argumento no es nulo y es unAPaquetes objeto que tiene  valores idonticos en el campo id tal como este objeto.
     * @param objeto que referencia el objeto con el cual comparar @return <code>verdadero</code> si este objeto es idontico al argumento; de otra manera sero<code>falso</code>.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Paquete)) {
            return false;
        }
        
        Paquete other = (Paquete)object;
        

        if ( this.alto == other.alto &&
             this.ancho == other.ancho &&
             this.largo == other.largo &&
             this.peso == other.peso 
             //this.sobre.equals(other.sobre) &&
             //this.contenido.equals(other.contenido)
                ){
            return true;
        }
        
        return false;
    }
    /*
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paquete)) {
            return false;
        }
        Paquete other = (Paquete)object;
        if (this.paqueteId != other.paqueteId && (this.paqueteId == null || !this.paqueteId.equals(other.paqueteId))) return false;
        return true;
    }*/

    /**
     * Returna un representacion de cadena del objeto.  Esta implementacion construye esta representacion basada sobre los campos id.
     * @return una  representacion de cadena del objeto.
     */
    @Override
    public String toString() {
        return "dominio.Paquete[paqueteId=" + contenido + "]";
    }    


    
    /*  public String getPaquete() {
        return paquete;
    }

    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }
    
    
    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }*/
    
    public boolean isSobre(){
        return getTipoEnvio().toUpperCase().equals("S") ? true : false;
    }

    public boolean isPaquete(){
        if(getTipoEnvio().toUpperCase().equals("N") || getTipoEnvio().equals ("P"))
           return true;
        else
            return false;
    }
    public boolean isCarga(){
        if(getTipoEnvio().toUpperCase().equals ("C"))
           return true;
        else
            return false;
    }
    public int getNumIni() {
        return numIni;
    }

    public void setNumIni(int numIni) {
        this.numIni = numIni;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double roundNum(double num) throws Exception{
       double valor = 0;
       valor = num;
       valor = valor*100;
       valor = Math.round(valor);
       valor = valor/100;
       return valor;
      }

    /**
     * @return the tipoEnvio
     */
    public String getTipoEnvio() {
        return tipoEnvio;
    }

    /**
     * @param tipoEnvio the tipoEnvio to set
     */
    public void setTipoEnvio(String tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }



}
