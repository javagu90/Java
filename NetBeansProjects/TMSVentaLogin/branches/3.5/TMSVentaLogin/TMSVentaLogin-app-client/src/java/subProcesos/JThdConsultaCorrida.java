/*
 * JThreadConsultaCorridas.java
 *
 * Created on 25 de abril de 2008, 02:36 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package subProcesos;

import java.util.Date;
import java.util.Vector;
import tms_venta.SesionVenta;

/**
 *
 * @author ocruz
 */
public class JThdConsultaCorrida extends Thread{
    public static boolean moreQuotes;
    private SesionVenta sesionVenta;
    private int tamSiempre;
    private String corridaId;
    private Object[] matrix;
    private int i, j;
    private static final int segundo = 1000;
    private int tiempoInactividad;
    
    public JThdConsultaCorrida(SesionVenta pSesionVenta,String pCorridaId) {
        sesionVenta=pSesionVenta;
        sesionVenta.b_decode_data=false;
        corridaId=pCorridaId;
        moreQuotes=true;
        matrix=null;
        if(sesionVenta.getTipoTransaccion().equals("L"))
            tiempoInactividad = (int)(sesionVenta.msLocal*segundo);
        else
            tiempoInactividad = (int)(sesionVenta.msRemoto*segundo);
        tamSiempre = 0;
    }
    
    public void run(){
	while(moreQuotes){
            try{
                matrix = null;
                matrix = sesionVenta.obtenerUnaCorridaVenta(corridaId);
                if(matrix!=null)
                    tamSiempre=matrix.length;
                sesionVenta.b_decode_data=true;
                this.sleep(tiempoInactividad);                
            } catch (InterruptedException ex) {
                matrix = null;
                moreQuotes = false;
            } catch (Exception x) { 
                matrix = null;
                moreQuotes = false; 
            }
	}
        System.out.println("hilo JThdConsultaCorrida finalizado.");
    }
    
    public void setCorridaActualId(String pCorridaId){ this.corridaId = pCorridaId; }
    
    public int getTam(){ return tamSiempre; }
    
    public Object[] getCorridaActual(boolean esPrimeraVez){
        if(esPrimeraVez)
            while(!sesionVenta.b_decode_data);
        else
            if(!sesionVenta.b_decode_data) return null;
        while(!sesionVenta.b_decode_data);
        if(matrix==null){
            sesionVenta.b_decode_data=false;
            return null;
        }
        
        Object[] obj = new Object[tamSiempre];
        int h;
        for(h=0; h<tamSiempre; h++)
            obj[h]=matrix[h].toString();
        sesionVenta.b_decode_data=false;
        return obj;
    }
}
