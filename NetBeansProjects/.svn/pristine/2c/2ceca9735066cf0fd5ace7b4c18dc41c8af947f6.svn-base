package tms_ejecutarreportes;

import DialogosExeRrp.JDlgAceptar;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tms_exerpt.solicitud.TmsExeRptFacadeRemote;

public class SesionVenta {
    private SimpleDateFormat formatoTS = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");// = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private JDlgAceptar DialogoAceptar;
    private TmsExeRptFacadeRemote tmsExeRptFacade;
    
    private List<TmsReportesV> tmsReportesV;
    private int ctdGrupos=0;

    private String[] paramReportes;

    private int portAS;
    private String ipAS;
    
    private Context context;
    
    public SesionVenta() { }
    
    public boolean paramReportes(){
        if(!abreSocketAS()) return false;
        System.out.println("abri1");
        paramReportes=tmsExeRptFacade.paramReporte();
        System.out.println("abri2");
        if(paramReportes==null){
            return false;
        }
        System.out.println("abri3");
        //System.out.println("P_VLRREPSER: "+paramReportes[0]+" --- P_VLRREPUID: "+paramReportes[1]+" --- P_VLRREPURL:"+paramReportes[2]);
        return true;
    }
   
    public int proceso(long USUARIO_ID, String pIpAS, int pPortAS){
        System.out.println("ya");
        setIpAS(pIpAS); setPortAS(pPortAS);
        System.out.println("entre");
        if(!abreSocketAS()) return -21;
        try {
            context = getInitialContext();
	    tmsExeRptFacade = (TmsExeRptFacadeRemote)context.lookup("tms_exerpt.solicitud.TmsExeRptFacadeRemote");
            System.out.println("instancia");
        } catch (Exception ex) {
            ex.printStackTrace();
            return -21;
        }
        System.out.println("para");
        if(!paramReportes()) return -2;
        Vector vReportes = new Vector();
        System.out.println("reportes");
        if(!abreSocketAS()) return -21;
        vReportes = tmsExeRptFacade.Reportes(USUARIO_ID);
        if(vReportes==null) return -1;
        tmsReportesV = new ArrayList<TmsReportesV>();
        TmsReportesV reporteV;
        Vector v;
        int i;
        for(i=0; i<vReportes.size(); i++){
            reporteV = new TmsReportesV();
            v=(Vector) vReportes.get(i);
            reporteV.setGrupoNombre(v.get(0).toString());
            reporteV.setCtdReportes(Integer.valueOf(v.get(1).toString()));
            reporteV.setReporteNombre(v.get(2).toString());
            reporteV.setDescripcion(v.get(3).toString());
            reporteV.setReporteExecutable(v.get(4).toString());
            tmsReportesV.add(reporteV);
        }
        i=0;
        ctdGrupos=0;
        while(i<tmsReportesV.size()){
            ctdGrupos++;
            i+=tmsReportesV.get(i).getCtdReportes();
        }
        //System.out.println("ctd grupos "+ctdGrupos);
        return 0;
    }
    
    public String[] getParamReportes(){ return this.paramReportes; }
            
    public int getCtdGrupos(){ return this.ctdGrupos; }
    
    public List<TmsReportesV> getReportes(){ return this.tmsReportesV; }
    
    private static Context getInitialContext() throws NamingException{
        return new InitialContext();
    }
    
    public boolean abreSocketAS(){
        Socket s = null;
        try {
            s = new Socket(getIpAS(), getPortAS());
            return true;
        }catch( IOException e ) {
            return false;
        }catch(Exception err){
            return false;
        }
    }
    
    private void setIpAS(String valor){ this.ipAS = valor; }
    private void setPortAS(int valor){ this.portAS = valor; }
    
    private String getIpAS(){ return this.ipAS; }
    private int getPortAS(){ return this.portAS; }
    
    public Context getContext(){ return this.context; }
}