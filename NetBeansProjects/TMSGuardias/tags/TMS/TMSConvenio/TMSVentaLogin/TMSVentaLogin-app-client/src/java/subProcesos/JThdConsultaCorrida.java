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
    private String transaccionId;
    private String origen;
    private String servicioId;
    private String fechaCorrida;
    private String horaCorrida;
    private String terminalOrigenId;
    private String plantillaId;
    public JThdConsultaCorrida(SesionVenta pSesionVenta,String pCorridaId, String ptransaccionId, String porigen,String pservicioId,String pfechaCorrida,String phoraCorrida,String pterminalOrigenId, String pplantillaId) {
        sesionVenta=pSesionVenta;
        sesionVenta.b_decode_data=false;
        corridaId=pCorridaId;
        moreQuotes=true;
        matrix=null;
        transaccionId=ptransaccionId;
        origen=porigen;
        servicioId=pservicioId;
        fechaCorrida=pfechaCorrida;
        horaCorrida=phoraCorrida;
        terminalOrigenId=pterminalOrigenId;     
        plantillaId = pplantillaId;
        if(transaccionId.equals("") || transaccionId.equals("-") )
        {
            if(sesionVenta.getTipoTransaccion().equals("L")) 
                tiempoInactividad = (int)(sesionVenta.msLocal*segundo);
            else
                tiempoInactividad = (int)(sesionVenta.msRemoto*segundo);
        }
        else
            tiempoInactividad = (int)(sesionVenta.msConvenio*segundo);
        System.out.println("Tiempo para "+((transaccionId.equals("") || transaccionId.equals("-") )?"Normal":"Convenio")+" es: "+tiempoInactividad);
        tamSiempre = 0;
    }
    
    public void run(){
	while(moreQuotes){
            try{
                matrix = null;
                matrix = sesionVenta.obtenerUnaCorridaVenta(corridaId,transaccionId, origen,  servicioId,  fechaCorrida,  horaCorrida,  terminalOrigenId, plantillaId);
                if(matrix!=null)
                    tamSiempre=matrix.length;
                sesionVenta.b_decode_data=true;
                this.sleep(tiempoInactividad);                
            } catch (InterruptedException ex) {
                System.out.println("Exception ex....");
                ex.printStackTrace();
                matrix = null;
                moreQuotes = false;
            } catch (Exception x) { 
                System.out.println("Exception x....");
                x.printStackTrace();
                matrix = null;
                moreQuotes = false; 
            }
	}
        System.out.println("hilo JThdConsultaCorrida finalizado.");
    }
    
    public void setCorridaActualId(String pCorridaId, String ptransaccionId, String porigen,String pservicioId,String pfechaCorrida,String phoraCorrida,String pterminalOrigenId, String pplantllaId){ 
        this.corridaId = pCorridaId; 
        this.transaccionId=ptransaccionId;
        this.origen=porigen;
        this.servicioId=pservicioId;
        this.fechaCorrida=pfechaCorrida;
        this.horaCorrida=phoraCorrida;
        this.terminalOrigenId=pterminalOrigenId;  
        this.plantillaId =pplantllaId;
        if(transaccionId.equals("") || transaccionId.equals("-") )
        {
            if(sesionVenta.getTipoTransaccion().equals("L")) 
                tiempoInactividad = (int)(sesionVenta.msLocal*segundo);
            else
                tiempoInactividad = (int)(sesionVenta.msRemoto*segundo);
        }
        else
            tiempoInactividad = (int)(sesionVenta.msConvenio*segundo);
        System.out.println("Tiempo para "+((transaccionId.equals("") || transaccionId.equals("-") )?"Normal":"Convenio")+" es: "+tiempoInactividad);
        
        //System.out.println("settea la corrida....");
    }
    
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
