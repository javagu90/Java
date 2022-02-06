/*
 * ImpresionMonitorThread.java
 *
 * Created on 25 de marzo de 2008, 04:08 PM
 *
 */

package recursos;

import reportesrecaudajasper.JIFReportesRecauda;


//import loginconf.solicitud.LoginUtilRemote;

/**
 *
 * @author halvarado
 */
public class ImpresionMonitorThread implements Runnable{
    //private LoginUtilRemote utilManager;
    private HLoad loader = new HLoad();
    private JIFReportesRecauda pricipal;
    private String fi;
    private String ff;
    private String rec;
    private long usr;
    private String nombreReporte;
    private String estado;
    private String referencia;

    public ImpresionMonitorThread(JIFReportesRecauda ppricipal,String pfi, String pff, String prec, long pusr, String pnombreReporte, String pestado, String ref) {
        this.pricipal = ppricipal;
        this.fi = pfi;
        this.ff = pff;
        this.rec = prec;
        this.estado = pestado;
        this.usr = pusr;
        this.nombreReporte = pnombreReporte;
        this.referencia = ref;
        System.out.println("Entra al constructor...");
    }

    public void run() {
        getLoader().setVisible(true);
        getLoader().jPgrStatus.setString("Generando Reporte");
        System.out.println("Entra a mandar a crear el reporte...");
        pricipal.generarReporte(fi, ff, rec, usr, nombreReporte, estado, referencia);
        getLoader().setVisible(false);
        loader = null;
    }

    public HLoad getLoader() {
        return loader;
    }
    
    
    public void pararHilo()
    {
        loader = null;
        getLoader().setVisible(false);
        getLoader().dispose();
        loader = null;
    }
}
