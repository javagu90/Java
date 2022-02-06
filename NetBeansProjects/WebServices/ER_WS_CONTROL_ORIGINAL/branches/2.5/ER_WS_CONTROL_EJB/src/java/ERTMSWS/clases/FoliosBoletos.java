/*
 * FoliosBoletos.java
 *
 * Created on 18 de junio de 2010, 04:23 PM
 *
 * Clase para representar a los Folios de los Boletos dentro de las operaciones de EJB
 */

package ERTMSWS.clases;

import java.io.Serializable;


/**
 *
 * @author opalafox
 */
public class FoliosBoletos implements Serializable{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validacion de datos
     *se verifica la longitud de la cadena
     */
    private String FolioPreimpreso;
    private String SerieBoleto;
    private String Origen;
    private int NoAsiento;
    private String NombrePasajero;

    /** Creates a new instance of FoliosBoletos */
    public FoliosBoletos() {
    }

    public String getFolioPreimpreso() {
        return FolioPreimpreso;
    }

    public void setFolioPreimpreso(String FolioPreimpreso) {
        this.FolioPreimpreso = FolioPreimpreso;
    }

    public String getSerieBoleto() {
        return SerieBoleto;
    }

    public void setSerieBoleto(String SerieBoleto) {
        this.SerieBoleto = SerieBoleto;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    public int getNoAsiento() {
        return NoAsiento;
    }

    public void setNoAsiento(int NoAsiento) {
        this.NoAsiento = NoAsiento;
    }

    public String getNombrePasajero() {
        return NombrePasajero;
    }

    public void setNombrePasajero(String NombrePasajero) {
        this.NombrePasajero = NombrePasajero;
    }
    
}
