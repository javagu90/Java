/*
 * CorridasReq.java
 *
 * Created on 18 de junio de 2010, 04:35 PM
 *
 * Clase para conjuntar los argumentos dados a getCorridas del  WebService y que seron procesadas por EJB
 */

package ERTMSWS.clasesx.Corridas;

/**
 *
 * @author opalafox
 */
import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class CorridasReq implements Serializable{
    
    /*Todas aquellos campos con longitud mayor a 1 son tratados como cadenas, aunque en el validacion de datos
     *se verifica la longitud de la cadena
     */
    private int SesionId;
    private String Empresa;
    private String Servicio;
    private String Origen;
    private String Destino;
    private Date FechaHoraSalida;
    private SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    /** Creates a new instance of CorridasReq */

    public int getSesionId() {
        return SesionId;
    }

    public void setSesionId(int SesionId) {
        this.SesionId = SesionId;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String Empresa) {
        this.Empresa = Empresa;
    }

    public String getServicio() {
        return Servicio;
    }

    public void setServicio(String Servicio) {
        this.Servicio = Servicio;
    }

    public String getOrigen() {
        return Origen;
    }

    public void setOrigen(String Origen) {
        this.Origen = Origen;
    }

    public String getDestino() {
        return Destino;
    }

    public void setDestino(String Destino) {
        this.Destino = Destino;
    }

    public Date getFechaHoraSalida() throws Exception {
        //sdfFechaHora.setTimeZone(TimeZone.getTimeZone("UTC")); 
        sdfFechaHora.setTimeZone(TimeZone.getTimeZone("America/Mexico_City")); 
        sdfFechaHora.parse(sdfFechaHora.format(FechaHoraSalida.getTime()));
        
        return sdfFechaHora.parse(sdfFechaHora.format(FechaHoraSalida.getTime()));
       
       // return FechaHoraSalida;
    }

    public void setFechaHoraSalida(Date FechaHoraSalida) {
        this.FechaHoraSalida = FechaHoraSalida;
    }
    
}
