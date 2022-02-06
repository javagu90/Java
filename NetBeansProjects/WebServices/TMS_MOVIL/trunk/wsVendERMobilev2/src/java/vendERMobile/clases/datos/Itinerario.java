/*
 * Itinerario.java
 *
 * Created on 17 de septiembre de 2009, 11:46 AM
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
public class Itinerario {
    
    private DatoGenerico origen;
    private DatoGenerico destino;
    private String horaSalida;
    private String horaLlegada;
    private int diasDefasamiento;
    private String tipoItinerario;
    private String tipoCorrida;
    private Tarifas[] sencilla;
    private Tarifas[] redonda;
    private String rutaId;
    private String tramoId;
        
    /** Creates a new instance of Itinerario */
    public Itinerario() {
    }
    
    /*public Itinerario(Vector itinerario) {
        setOrigen(new DatoGenerico(itinerario.get(0).toString(), itinerario.get(1).toString(),itinerario.get(2).toString()));
        setDestino(new DatoGenerico(itinerario.get(3).toString(), itinerario.get(4).toString(),itinerario.get(5).toString()));
        if(itinerario.size() > 6) {
            setHoraSalida(itinerario.get(6).toString());
            setHoraLlegada(itinerario.get(7).toString());
            setDiasDefasamiento(Integer.parseInt(itinerario.get(8).toString()));
            setTipoItinerario(itinerario.get(9).toString());
            setTipoCorrida(itinerario.get(10).toString());
            
            /** TARIFA SENCILLA**/
            /*StringTokenizer token = new StringTokenizer(itinerario.get(11).toString(), ",");
            //tarifas => A,M,E,P,S
            int ruta = Integer.parseInt(token.nextToken());
            Tarifas[] arreglo = new Tarifas[5];
            /*adulto*/
            /*arreglo[0] = setTar("SEN",Float.parseFloat(token.nextToken()), ruta);
            arreglo[0].setTiposPasajero(tipoPas("A", "ADULTO"));
            
            /*menor*/
           /*arreglo[1] = setTar("SEN",Float.parseFloat(token.nextToken()), ruta);
            arreglo[1].setTiposPasajero(tipoPas("M", "MENOR"));
             
            /*ESTUDIANTE*/
            /*arreglo[2] = setTar("SEN",Float.parseFloat(token.nextToken()), ruta);
            arreglo[2].setTiposPasajero(tipoPas("E", "ESTUDIANTE"));
            
            /*PROFESOR*/
            /*arreglo[3] = setTar("SEN",Float.parseFloat(token.nextToken()), ruta);
            arreglo[3].setTiposPasajero(tipoPas("P", "PROFESOR"));
            
            /*SENECTUD*/
            /*arreglo[4] = setTar("SEN",Float.parseFloat(token.nextToken()), ruta);
            arreglo[4].setTiposPasajero(tipoPas("S", "SENECTUD"));
            setTarifaSencilla(arreglo);
            
            /*TARIFA REDONDA*/
            
            /*token = new StringTokenizer(itinerario.get(12).toString(), ",");
            //tarifas => A,M,E,P,S
            ruta = Integer.parseInt(token.nextToken());
            arreglo = new Tarifas[5];
            /*adulto*/
            /*arreglo[0] = setTar("RED",Float.parseFloat(token.nextToken()), ruta);
            arreglo[0].setTiposPasajero(tipoPas("A", "ADULTO"));
            
            /*menor*/
            /*arreglo[1] = setTar("RED",Float.parseFloat(token.nextToken()), ruta);
            arreglo[1].setTiposPasajero(tipoPas("M", "MENOR"));
             
            /*ESTUDIANTE*/
            /*arreglo[2] = setTar("RED",Float.parseFloat(token.nextToken()), ruta);
            arreglo[2].setTiposPasajero(tipoPas("E", "ESTUDIANTE"));
            
            /*PROFESOR*/
            /*arreglo[3] = setTar("RED",Float.parseFloat(token.nextToken()), ruta);
            arreglo[3].setTiposPasajero(tipoPas("P", "PROFESOR"));
            
            /*SENECTUD*/
            /*arreglo[4] = setTar("RED",Float.parseFloat(token.nextToken()), ruta);
            arreglo[4].setTiposPasajero(tipoPas("S", "SENECTUD"));
            
            setTarifaRedonda(arreglo);
        }        
    }*/
    
    public Itinerario(Vector itinerario) {
        setOrigen(new DatoGenerico(itinerario.get(0).toString(), itinerario.get(1).toString(),itinerario.get(2).toString()));
        setDestino(new DatoGenerico(itinerario.get(3).toString(), itinerario.get(4).toString(),itinerario.get(5).toString()));
        setRutaId(itinerario.get(6).toString());
        setTramoId(itinerario.get(7).toString());
    }

    public DatoGenerico getOrigen() {
        return origen;
    }

    public void setOrigen(DatoGenerico origen) {
        this.origen = origen;
    }
    
    public DatoGenerico getDestino() {
        return destino;
    }
    
    public void setDestino(DatoGenerico destino) {
        this.destino = destino;
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
    
    public void setTarifaSencilla(Tarifas[] sencilla) {
        this.sencilla = sencilla;
    }
    
    public Tarifas[] getTarifaSencilla() {
        return this.sencilla;
    }
    
    public void setTarifaRedonda(Tarifas[] redonda) {
        this.redonda = redonda;
    }
    
    public Tarifas[] getTarifaRedonda() {
        return this.redonda;
    }
    
    public TiposPasajero tipoPas(String tipo, String clave){
        TiposPasajero temp1 = new TiposPasajero();
        temp1.setIdTipoPasajero(0);
        temp1.setPct_descuento(0);
        temp1.setTipoPasajeroClave(clave);
        temp1.setTipoPasajeroNombre(tipo);
        
        return temp1;
    }
    
    public Tarifas setTar(String tipo, float importe, int ruta){
        Tarifas temp = new Tarifas();
            //temp.setFechaDesde(null);
            //temp.setFechaHasta(null);
            temp.setIdRuta(ruta);
            //temp.setIdTarifa(0);
            temp.setTarifaTipo(tipo);
            temp.setTarifaImporte(importe);
        
        return temp;
    }
    
    public String getRutaId() {
        return rutaId;
    }

    public void setRutaId(String rutaId) {
        this.rutaId = rutaId;
    }
    
    public String getTramoId() {
        return tramoId;
    }

    public void setTramoId(String tramoId) {
        this.tramoId = tramoId;
    }
}
