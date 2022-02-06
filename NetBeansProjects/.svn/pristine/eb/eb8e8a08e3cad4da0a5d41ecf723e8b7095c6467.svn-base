/*
 * BloquearAsientosReq.java
 *
 * Created on 18 de junio de 2010, 05:34 PM
 *
 * Clase para conjuntar los argumentos dados a getBloquearAsientos del WebService y que seron procesadas por EJB
 */

package ERTMSWS.clasesx.BloquearAsientos;

import java.io.Serializable;

/**
 *
 * @author opalafox
 */
public class BloquearAsientosReq  implements Serializable{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validacion de datos
     *se verifica la longitud de la cadena
     */
    private int SesionId;
    private String NoAsientos;
    private String TipoPasajero;
    private String ClaveCorrida;
    private String  Modalidad;
    private String Origen;

    /** Creates a new instance of BloquearAsientosReq */
    public BloquearAsientosReq() {
    }

    public int getSesionId() {
        return SesionId;
    }

    public void setSesionId(int SesionId) {
        this.SesionId = SesionId;
    }

    public String getNoAsientos() {
        return NoAsientos;
    }

    public void setNoAsientos(String NoAsientos) {
        this.NoAsientos = NoAsientos;
    }

    public String getTipoPasajero() {
        return TipoPasajero;
    }

    public void setTipoPasajero(String TipoPasajero) {
        this.TipoPasajero = TipoPasajero;
    }

    public String getClaveCorrida() {
        return ClaveCorrida;
    }

    public void setClaveCorrida(String ClaveCorrida) {
        this.ClaveCorrida = ClaveCorrida;
    }

    public String  getModalidad() {
        return Modalidad;
    }

    public void setModalidad(String  Modalidad) {
        this.Modalidad = Modalidad;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }
    
}
