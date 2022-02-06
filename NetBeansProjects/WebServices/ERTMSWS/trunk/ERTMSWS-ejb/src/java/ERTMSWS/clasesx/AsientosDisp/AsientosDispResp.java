/*
 * AsientosDispResp.java
 *
 * Created on 18 de junio de 2010, 05:20 PM
 *
 * Clase para conjuntar los resultados obtenidos de getAsientosDisp del WebService y que serán procesadas por EJB
 */

package ERTMSWS.clasesx.AsientosDisp;
import ERTMSWS.clasesx.ExchangeResp;
/**
 *
 * @author opalafox
 */
public class AsientosDispResp extends ExchangeResp{
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validación de datos
     *se verifica la longitud de la cadena
     */
    private String asientosDisponibles;
    private int asientosSenectud;
    private int asientosProfesor;
    private int asientosEstudiante;
    private int asientosOtro;
   

    
    /** Creates a new instance of AsientosDispResp */
    public AsientosDispResp() {
    }

    public String getasientosDisponibles() {
        return asientosDisponibles;
    }

    public void setasientosDisponibles(String asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    public int getasientosSenectud() {
        return asientosSenectud;
    }

    public void setasientosSenectud(int asientosSenectud) {
        this.asientosSenectud = asientosSenectud;
    }

    public int getasientosProfesor() {
        return asientosProfesor;
    }

    public void setasientosProfesor(int asientosProfesor) {
        this.asientosProfesor = asientosProfesor;
    }

    public int getasientosEstudiante() {
        return asientosEstudiante;
    }

    public void setasientosEstudiante(int asientosEstudiante) {
        this.asientosEstudiante = asientosEstudiante;
    }

    public int getasientosOtro() {
        return asientosOtro;
    }

    public void setasientosOtro(int asientosOtro) {
        this.asientosOtro = asientosOtro;
    }

   
    
}
