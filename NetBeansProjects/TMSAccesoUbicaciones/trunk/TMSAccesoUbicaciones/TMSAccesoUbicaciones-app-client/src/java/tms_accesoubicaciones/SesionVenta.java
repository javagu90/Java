package tms_accesoubicaciones;

import java.awt.Color;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;    
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tms_accesoubicaciones.util.JDlgAceptar;
import xertms.entidad.TmsEstadoAutobusesV;
import xertms.entidad.TmsEstadosTbl;
import xertms.solicitud.TmsAccUbicaFacadeRemote;

public class SesionVenta {
    private long usuarioId;
    private TmsAccUbicaFacadeRemote sesionAUFacate = null;
    private Object[] terminal = null;
    private List<TmsEstadosTbl> tmsEstadosTbl = null;
    private List<TmsEstadoAutobusesV> tmsEstadoAutobusesV = null;
    
    private Vector vUbicacion;
    private Vector vCausa;
    
    private JDlgAceptar DialogoAceptar;
    
    private String[] cambiaAEnrolado;
    private String[] cambiaAFueraRol;
    private String NuevoEstadoNombre;
    private long EnRolId;
    private long FueraRolId;
    private String claveOperador;
    private String edoOper, ubiOper, actOper;
    private String msgActEdoOper="";
  
    public SesionVenta(long pUsuarioId, long MENU_ID) { 
        this.usuarioId = pUsuarioId;
    }
    
    private TmsAccUbicaFacadeRemote lookupTmsAccesoUbicacionesFacade() {
        try {
            Context c = new InitialContext();
            return (TmsAccUbicaFacadeRemote) c.lookup("xertms.solicitud.TmsAccUbicaFacadeRemote");
        }
        catch(NamingException ne) {
            return null;
        }
    }
    
    public int procesoInicial(){
        sesionAUFacate = lookupTmsAccesoUbicacionesFacade();
        if(sesionAUFacate == null) return -1;
        if(!Terminal()) return 1;
        if(!Estados()) return 2;
        // CONSTANTES DE ASIGNACION DE ACTIVIDAD
        this.FueraRolId = getEstadoId("FUERA DE ROL");
        this.EnRolId = getEstadoId("ENROLADO");
        return 0; // CARGA COMPLETA EXITOSA
    }
    
    private boolean Terminal(){
        terminal = new Object[3];
        terminal = sesionAUFacate.obtenerDatosTerminalLocal();
        if(terminal==null) return false;
        return true;
    }
    
    private boolean Estados(){
        tmsEstadosTbl = sesionAUFacate.queryTmsEstadosTblAll();
        if(tmsEstadosTbl == null) return false;
        vUbicacion = new Vector();
        vCausa = new Vector();
        for(int i=0; i<tmsEstadosTbl.size(); i++){
            if(tmsEstadosTbl.get(i).getTipoComponente().equals("CIUDAD") 
               || tmsEstadosTbl.get(i).getTipoComponente().equals("TERMINAL")
               || tmsEstadosTbl.get(i).getTipoComponente().equals("UBICACION")){
                vUbicacion.add(tmsEstadosTbl.get(i).getEstadoNombre());
            }
            if(tmsEstadosTbl.get(i).getTipoComponente().equals("ACTIVIDAD")){
                vCausa.add(tmsEstadosTbl.get(i).getEstadoNombre());
            }
        }
        return true;
    }
    
    private long getEstadoId(String estado){
        for(int i=0; i<tmsEstadosTbl.size(); i++){
            if(tmsEstadosTbl.get(i).getEstadoNombre().equals(estado))
                return tmsEstadosTbl.get(i).getEstadoId();
        }
        return -1;
    }
    
    public Object[][] busquedaAutobus(String NumeroEconomico){
        tmsEstadoAutobusesV = sesionAUFacate.queryTmsEstadoAutobusesVByNumeroEconomico(NumeroEconomico);
        if(tmsEstadoAutobusesV==null) return null;
        Object[][] datos = new Object[tmsEstadoAutobusesV.size()][4];
        
        for(int i=0; i<tmsEstadoAutobusesV.size(); i++){
            datos[i][0] = tmsEstadoAutobusesV.get(i).getNumeroEconomico();
            datos[i][1] = tmsEstadoAutobusesV.get(i).getEstadoNombre();
            datos[i][2] = tmsEstadoAutobusesV.get(i).getUbicacionNombre();
            datos[i][3] = tmsEstadoAutobusesV.get(i).getActividadNombre();
        }
        
        return datos;
    }
    
    public boolean registraAccesoUbicacion(int filaGuardar, String Ubi, String Act){
        Timestamp fechaAct = new Timestamp(new Date().getTime());
        msgActEdoOper="";
        // obtiene nuevo estado Rol-Fuera Rol
        //System.out.println("ESTADO NOMBRE "+tmsEstadoAutobusesV.get(filaGuardar).getEstadoNombre());
        long NuevoEstadoId=analizarEstado(tmsEstadoAutobusesV.get(filaGuardar).getEstadoNombre(), Ubi, Act);
        //System.out.println("ESTADO NOMBRE "+NuevoEstadoId);
        claveOperador = sesionAUFacate.claveOperador(tmsEstadoAutobusesV.get(filaGuardar).getNumeroEconomico());
        boolean resultado;
        if(claveOperador!=null)
            if(sesionAUFacate.actualizaEstadoOperador(claveOperador, edoOper, ubiOper, actOper, this.terminal[2].toString())){
                long operadorId=sesionAUFacate.obtenerOperadorId(claveOperador);
                resultado=sesionAUFacate.ejecutaReplicacion("TMS_OPERADORES_TBL", String.valueOf(operadorId),
                                            "XERTMS", this.terminal[2].toString(),
                                            "DIFUSION");
                msgActEdoOper="<br>El operador <font color=ff0000>"+claveOperador+"</font> esta "+edoOper+".";
            }
        resultado=sesionAUFacate.registrarAccesoUbicacion(tmsEstadoAutobusesV.get(filaGuardar).getAutobusId(),
                                                               NuevoEstadoId,
                                                               getEstadoId(Ubi),
                                                               getEstadoId(Act),
                                                               this.usuarioId, fechaAct, this.terminal[2].toString());
        if(!resultado) return false;
        resultado=sesionAUFacate.ejecutaReplicacion("TMS_AUTOBUSES_TBL", String.valueOf(tmsEstadoAutobusesV.get(filaGuardar).getAutobusId()),
                                        "XERTMS", this.terminal[2].toString(),
                                        "DIFUSION");
        return resultado;
    }
    
    private long analizarEstado(String Edo, String Ubi, String Act) {
        if(Act.equals("ACCIDENTE") || Act.equals("CORRALON")){
            this.NuevoEstadoNombre = "Fuera de rol";
            this.edoOper = "FUERA DE ROL";
            this.ubiOper = "NO LABORAL";
            if(Act.equals("CORRALON"))  this.actOper = "GUARDIA";
            else this.actOper = "ACCIDENTE";
            return this.FueraRolId;
        }
        if(Edo.equals("FUERA DE ROL")){
                this.edoOper = "ENROLADO";
                this.ubiOper = Ubi;
                this.actOper = "ESTANCIA";
            if(Act.equals("LISTO") || Act.equals("ESTANCIA")){
                this.edoOper = "ENROLADO";
                this.NuevoEstadoNombre = "Enrolado";
                return this.EnRolId;
            }
            this.NuevoEstadoNombre = "Fuera de rol";
            return this.FueraRolId;
        }
        this.NuevoEstadoNombre = "Enrolado";
        return this.EnRolId;
    };
    
    public Vector getCausas(){ return this.vCausa; }
    
    public Vector getUbicaciones(){ return this.vUbicacion; }
    
    public String getNuevoEstado(){ return this.NuevoEstadoNombre; }
    
    public TmsEstadoAutobusesV getAutobuses(int fila){ return this.tmsEstadoAutobusesV.get(fila); }
    
    public String getMsgActEdoOper(){ return this.msgActEdoOper; }
}