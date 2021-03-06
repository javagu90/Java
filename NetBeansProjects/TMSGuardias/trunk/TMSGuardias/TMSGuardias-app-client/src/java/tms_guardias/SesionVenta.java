package tms_guardias;

import Dialogos.JDlgAceptar;
import java.awt.Color;
import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tms_guardias.entidad.TmsAccionesTbl;
import tms_guardias.entidad.TmsEstadoOperadoresV;
import tms_guardias.entidad.TmsActAdicionalesTbl;
import tms_guardias.entidad.TmsOperadorIncidenciasTbl;
import tms_guardias.entidad.TmsPagosActAdicionalesTbl;
import tms_guardias.entidad.TmsServiciosTbl;
import tms_guardias.solicitud.TmsGuardiasFacadeRemote;

public class SesionVenta {
    private String ipAS;
    private int portAS;
    private SimpleDateFormat formatoTS = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");// = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private JDlgAceptar DialogoAceptar;
    private TmsGuardiasFacadeRemote tmsGuardiasFacade;

    
    private String[] datosTerminal=null;
    private String PrefijoTerminalId;
    public long actAdicionalId; 
    
    private List<TmsActAdicionalesTbl> tmsIncidenciasTbl=null;
    private List<TmsServiciosTbl> tmsServiciosTbl=null;
    private List<TmsEstadoOperadoresV> tmsEstadoOperadoresV=null;
    private List<TmsAccionesTbl> tmsAccionesTbl=null;
    private List<vwIncOper> IncOper=null;
    private String[][] valorServicioGuardia;
    private int duracionGuardia;
    private long guardiaId;
    private String guardiaClave;
    
    private vwIncOper especial=null;
    private long operIncId;
    private String[][] datosAdicionales;
    private int filaOperadorSeleccionado;
    private int filaIncidenciaSeleccionada;
    
    private double monto, tiempoGuardia;
    private final long hora = 3600000;

    private double CantidadGuardia;
    private long unaGuardiaHoras;
    private String terminales;
    private Timestamp fecha_servidor= null;
    private String[][] FuncionesUsuario = null;
    private long recaudadorId;
    private String puerto = "";
    private String nombreFormato = "";
    private String nombreImpresora = "";

    public SesionVenta() { }
   
    public long getGuardiaId(){ return this.guardiaId; }
    
    public String getGuardiaClave(){ return this.guardiaClave; }
    
    public int proceso(long MENU_ID, String pIpAS, int pPortAS){
        setIpAS(pIpAS); setPortAS(pPortAS);
        if(!abreSocketAS()) return -1;
        try {
            Context context = getInitialContext();
	    tmsGuardiasFacade = (TmsGuardiasFacadeRemote )context.lookup("tms_guardias.solicitud.TmsGuardiasFacadeRemote");
        } catch (Exception ex) {
            return -1;
        }
        if(!cargaTerminal()) return 1;
        if(!duracionGuardia()) return 5;
        if(!DatosGuardias()) return 2; // ID. CLAVE. NOMBRE, MONTO POR GUARDIA, HORAS
        if(!Incidencias()) return 2;
        if(!Servicios()) return 3;
        if(!Estados()) return 4;
        if(!cargaTerminales()) return 6;
        if(!cargaImpresoras()) return 7;
        getDatosRecaudador();
        return 0;
    }
    
    private boolean cargaTerminal() {
        if(!abreSocketAS()) return false;
        datosTerminal = tmsGuardiasFacade.obtenerTerminal();
        if(datosTerminal==null) return false;
        if(datosTerminal[0].length()>3) PrefijoTerminalId  = datosTerminal[0].substring(0,3);
        if(datosTerminal[0].length()==3) PrefijoTerminalId = datosTerminal[0];
        if(datosTerminal[0].length()==2) PrefijoTerminalId = datosTerminal[0]+"0";
        if(datosTerminal[0].length()==1) PrefijoTerminalId = datosTerminal[0]+"00";
        return true;
    }

    private static Context getInitialContext() throws NamingException{
        return new InitialContext();
    }
    
    private boolean Estados() {
        if(!abreSocketAS()) return false;
        tmsAccionesTbl = tmsGuardiasFacade.queryTmsAccionesTblAll();
        if(tmsAccionesTbl==null) return false;
        return true;
    }
    
    public long getAccionId(String accion){
        for(int i=0; i<tmsAccionesTbl.size(); i++)
            if(tmsAccionesTbl.get(i).getAccion().equals(accion)) return tmsAccionesTbl.get(i).getAccionId();
        
        return -1;
    }
    
    private boolean Servicios() {
        if(!abreSocketAS()) return false;
        tmsServiciosTbl = tmsGuardiasFacade.queryTmsServiciosAll();
        if(tmsServiciosTbl==null) return false;
        return true;
    }
    
    private boolean Incidencias() {
        if(!abreSocketAS()) return false;
        tmsIncidenciasTbl = tmsGuardiasFacade.queryTmsIncidenciasAll();
        if(tmsIncidenciasTbl==null) return false;
        for(int i=0; i<tmsIncidenciasTbl.size(); i++)
            if(tmsIncidenciasTbl.get(i).getActividadClave().equals("GUA")){
                this.guardiaId = tmsIncidenciasTbl.get(i).getAccionId();
                this.guardiaClave = tmsIncidenciasTbl.get(i).getActividadClave();
                return true;
            }
        return false;
    }
    
 public int FuncionesDeUsuario(long MENU_ID,String usuario){
        Vector x = null;
        try{
            x = tmsGuardiasFacade.FuncionesDeUsuario(MENU_ID,usuario);
            System.out.println("Funcione encontradas: "+x.toString());
        }catch(javax.ejb.EJBException ejbex){
            return 1;
        }
        if(x==null) return 1;
        Vector z;
        FuncionesUsuario = new String[x.size()][3];
        for(int i=0; i<FuncionesUsuario.length; i++){
            z=(Vector) x.get(i);
            FuncionesUsuario[i][0] = z.get(0).toString();
            FuncionesUsuario[i][1] = z.get(1).toString();
            FuncionesUsuario[i][2] = z.get(2).toString();
        }
        return 0;
    }

 public boolean ValidaFuncionUsuario(String Funcion){
        for(int f=0; f<FuncionesUsuario.length; f++){
            if(FuncionesUsuario[f][0].equals(Funcion)){
                return true;
            }
        }
        return false;
    }

     public boolean cargaImpresoras(){
        PcInfo pc = new PcInfo();
         System.out.println("Host Name: "+pc.getHostName());
       Vector res = tmsGuardiasFacade.getImpresora(pc.getHostName());
       if(res.size()==0)
           return false;
       Vector v = (Vector)res.get(0);
       setNombreImpresora(v.get(0).toString());
       setPuerto(v.get(1).toString());
       setNombreFormato(v.get(2).toString());
       System.out.println("NombreImpresora: "+getNombreImpresora());
       System.out.println("Puerto: "+getPuerto());
       System.out.println("NombreFormato: "+getNombreFormato());
       return true;
    }   

    public boolean cargaTerminales(){
     if(!abreSocketAS()) return false;
        this.setTerminales(tmsGuardiasFacade.obtenerTerminales());
       return true;
    }
    
    public List<TmsActAdicionalesTbl> getTmsIncidenciasTbl(){ return this.tmsIncidenciasTbl; }
    
    public List<TmsServiciosTbl> getTmsServiciosTbl(){ return this.tmsServiciosTbl; }
    
    public Object[][] busqueda(String claveOperador, String Servicio, String claveIncidencia, String fecha1, String fecha2, String estado,String terminal){
        if(claveOperador.equals("")) claveOperador = "%";
        if(Servicio.equals("TODOS")) Servicio = "";
        if(claveIncidencia.equals("TODOS")) claveIncidencia = "";
        if(estado.equals("TODOS")) estado = "";
        if(estado.equals("AUTORIZADA")) estado = "S";
        if(estado.equals("NO AUTORIZADA")) estado = "N";
        if(terminal.equals("TODAS")) terminal = "%";
        
        if(!abreSocketAS()) return null;
        Vector vectorIncOper = tmsGuardiasFacade.OperadorIncidencias(claveOperador, Servicio, claveIncidencia, fecha1, fecha2, estado,terminal);
        
        if(vectorIncOper==null) return null;
        IncOper = new ArrayList<vwIncOper>();
        Object[][] resultado = new Object[vectorIncOper.size()][8];
        Vector y;
        vwIncOper VwIncOper;
        for(int i=0; i<vectorIncOper.size(); i++){
            VwIncOper = new vwIncOper();
            y = (Vector) vectorIncOper.get(i);
            VwIncOper.setOperIncidenciaId(Long.valueOf(y.get(0).toString()));
            VwIncOper.setOperadorId(Long.valueOf(y.get(1).toString()));
            VwIncOper.setClaveOperador(y.get(2).toString());
            VwIncOper.setOperadorNombreCompleto(y.get(3).toString());
            VwIncOper.setIncidenciaId(Long.valueOf(y.get(4).toString()));
            VwIncOper.setIncidenciaClave(y.get(5).toString());
            VwIncOper.setIncidenciaNombre(y.get(6).toString());
            VwIncOper.setAplicaRecaudacion(y.get(7).toString());
            VwIncOper.setConfigurable(y.get(8).toString());
            try {
               // BRA VwIncOper.setFechaInicial(new Timestamp(formatoTS.parse(y.get(9).toString()).getTime()));
              //BRA  VwIncOper.setFechaFinal((y.get(10)==null ? null : new Timestamp(formatoTS.parse(y.get(10).toString()).getTime())));
              VwIncOper.setFechaInicial(new Timestamp(formatoFechaHora.parse(y.get(9).toString()).getTime()));      
              VwIncOper.setFechaFinal((y.get(10)==null ? null : new Timestamp(formatoFechaHora.parse(y.get(10).toString()).getTime())));
               
            } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
            
            VwIncOper.setIncidenciaValor(y.get(11).toString());
            VwIncOper.setDuracion((y.get(12)==null ? "" : y.get(12).toString()));
            VwIncOper.setRetencion((y.get(13)==null ? "0" : y.get(13).toString()));
            VwIncOper.setMonto((y.get(14)==null ? "0" : y.get(14).toString()));
            VwIncOper.setServicioId(Long.valueOf(y.get(15).toString()));
            VwIncOper.setServicioNombre(y.get(16).toString());
            VwIncOper.setIncidenciaAutorizada((y.get(17).toString().equals("S") ? "AUTORIZADA" : "NO AUTORIZADA"));
            VwIncOper.setObservacion((y.get(18)==null ? "" : y.get(18).toString()));
            VwIncOper.setTerminal((y.get(19)==null ? "" : y.get(19).toString()));
            VwIncOper.setViajes((y.get(20)==null ? "" : y.get(20).toString()));

            IncOper.add(VwIncOper);
            resultado[i][0] = VwIncOper.getClaveOperador();
            resultado[i][1] = VwIncOper.getIncidenciaClave();
            resultado[i][2] = formatoFechaHora.format(new Date(VwIncOper.getFechaInicial().getTime()));
            resultado[i][3] = (VwIncOper.getFechaFinal()==null ? "" : formatoFechaHora.format(new Date(VwIncOper.getFechaFinal().getTime())));
            resultado[i][4] = VwIncOper.getTerminal();
            resultado[i][5] = VwIncOper.getServicioNombre();
            resultado[i][6] = VwIncOper.getIncidenciaAutorizada();
            resultado[i][7] = (y.get(21)==null ? "" : y.get(21).toString());
        }
        
        return resultado;
    }
    
    public List<vwIncOper> getVwIncOper(){ return this.IncOper; }
    
    public int getFilaIncidenciaSeleccionada(){ return this.filaIncidenciaSeleccionada; }
    
    public void setFilaIncidenciaSeleccionada(int fila){ this.filaIncidenciaSeleccionada = fila; }
    
    public Object[][] buscarOperador(String numero){
        if(!abreSocketAS()) return null;
        tmsEstadoOperadoresV = tmsGuardiasFacade.queryTmsEstadoOperadoresVByNumero(numero);
        if(tmsEstadoOperadoresV==null) return null;
        Object[][] datos = new Object[tmsEstadoOperadoresV.size()][5];
        for(int i=0; i<tmsEstadoOperadoresV.size(); i++){
            datos[i][0] = tmsEstadoOperadoresV.get(i).getClaveOperador();
            datos[i][1] = tmsEstadoOperadoresV.get(i).getOperadorNombreCompleto();
            datos[i][2] = tmsEstadoOperadoresV.get(i).getEstadoNombre();
            datos[i][3] = tmsEstadoOperadoresV.get(i).getUbicacionNombre();
            datos[i][4] = tmsEstadoOperadoresV.get(i).getActividadNombre();
        }
        return datos;
    }
    
    public List<TmsEstadoOperadoresV> getTmsEstadoOperadoresV(){ return this.tmsEstadoOperadoresV; }
    
    public int getFilaOperadorSeleccionado(){ return this.filaOperadorSeleccionado; }
    
    public void setFilaOperadorSeleccionado(int fila){ this.filaOperadorSeleccionado = fila; }
    
    public boolean registrarIncidencia(TmsOperadorIncidenciasTbl incidencia, long operadorId, String operadorClave){
        if(!abreSocketAS()) return false;
        try{
            operIncId=tmsGuardiasFacade.registraIncidenciaOperador(PrefijoTerminalId, incidencia);
            if(especial != null)
                especial.setIncidenciaId(operIncId);
            
            if (getEspecial() != null)
                getEspecial().setOperIncidenciaId(operIncId);
            setOperIncId(operIncId);
            
            // cambia estado operador
            tmsEstadoOperadoresV = tmsGuardiasFacade.queryTmsEstadoOperadoresVByNumero(operadorClave);
            if(tmsEstadoOperadoresV==null) return true;
            long EstadoId = getAccionId("ENROLADO");
            long UbicacionId = getTmsEstadoOperadoresV().get(0).getUbicacionId();
            long ActividadId = getAccionId("GUARDIA");
            tmsGuardiasFacade.cambiaEstadoOperador(operadorId,
                    EstadoId, UbicacionId, ActividadId, incidencia.getCreadoPor(), incidencia.getFechaCreacion(), this.datosTerminal[2]);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public long getOperIncId(){ return this.operIncId; }
    
    public void setOperIncId(long p_operincidencia){
         this.operIncId =  p_operincidencia; }
    
    public boolean autorizaIncidencia(long IncOperId, long usuarioId, Timestamp fechaserv){
        Timestamp tmActual = fechaserv;//new Timestamp(new Date().getTime());
        try{
            if(!abreSocketAS()) return false;
               tmsGuardiasFacade.autorizaIncidenciaOperador(IncOperId, usuarioId, tmActual);            
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean modificaIncidencia(long IncOperId, String valor, Timestamp fechaFinal, String observacion1, String observacion2, long usuarioId){
        Timestamp tmActual = new Timestamp(new Date().getTime());
        try{
            if(!abreSocketAS()) return false;
            tmsGuardiasFacade.modificarIncidenciaOperador(IncOperId, valor, fechaFinal, observacion1, observacion2, usuarioId, tmActual);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean obtenerDatosAdicionales(){
        if(!abreSocketAS()){
            datosAdicionales = null;
            return false;
        }
        String[][] datosAdicionalesR = tmsGuardiasFacade.obtieneDatosAdicionales(getVwIncOper().get(getFilaIncidenciaSeleccionada()).getIncidenciaClave());
        if(datosAdicionalesR == null){
            datosAdicionales = null;
            return false;
        }
        datosAdicionales = new String[datosAdicionalesR.length][4];
        for(int i=0; i<datosAdicionalesR.length; i++){
            datosAdicionales[i][0] = datosAdicionalesR[i][0];
            datosAdicionales[i][1] = datosAdicionalesR[i][1];
            datosAdicionales[i][2] = "";
            datosAdicionales[i][3] = datosAdicionalesR[i][2];
        }
        return true;
    }
    
    public boolean obtenerDatosAdicionalesEspeciales(String CveIncidencia){
        if(!abreSocketAS()){
            datosAdicionales = null;
            return false;
        }  
        // BRA 
        //String[][] datosAdicionalesR = tmsGuardiasFacade.obtieneDatosAdicionales(getEspecial().getIncidenciaClave());
        String[][] datosAdicionalesR = tmsGuardiasFacade.obtieneDatosAdicionales(CveIncidencia);
        if(datosAdicionalesR == null){
            datosAdicionales = null;
            return false;
        }
        datosAdicionales = new String[datosAdicionalesR.length][4];
        for(int i=0; i<datosAdicionalesR.length; i++){
            datosAdicionales[i][0] = datosAdicionalesR[i][0];
            datosAdicionales[i][1] = datosAdicionalesR[i][1];
            datosAdicionales[i][2] = "";
            datosAdicionales[i][3] = datosAdicionalesR[i][2];
        }
        return true;
    }
    
    public String[][] xDatosAdicionales(){ return this.datosAdicionales; }

    public boolean ingresarPreIngreso(long usuarioId, Timestamp fechaserv) {
                System.out.println("Entra ingresarPreingreso...");
                 System.out.println("(1)getAplicaRecaudacion("+getFilaIncidenciaSeleccionada()+"): "+getVwIncOper().get(getFilaIncidenciaSeleccionada()).getAplicaRecaudacion());
        if(getVwIncOper().get(getFilaIncidenciaSeleccionada()).getAplicaRecaudacion().equals("S")) if(!PreIngresoRecaudacion(usuarioId, fechaserv)) return false;
        if(!autorizaIncidencia(getVwIncOper().get(getFilaIncidenciaSeleccionada()).getOperIncidenciaId(), usuarioId, fechaserv)) return false;
        return true;
    }
    
    public boolean ingresarPreIngresoEspecial(long usuarioId, Timestamp fechaserv) {
        try {
            
           if(getEspecial().getAplicaRecaudacion().equals("S"))
            if(!PreIngresoRecaudacionEspecial(usuarioId,fechaserv)) return false;
        if(!autorizaIncidencia(getEspecial().getOperIncidenciaId(), usuarioId,fechaserv)) return false;
        
        } catch (Exception e) {
         e.printStackTrace();
         System.out.println("ingresarPreIngresoEspecial "+e.getMessage());
         return false;
        }
        return true;
    }

    private boolean PreIngresoRecaudacion(long usuarioId, Timestamp fechaserv) {
        Timestamp tmActual = fechaserv;//new Timestamp(new Date().getTime());
        TmsPagosActAdicionalesTbl tmsPagosActAdicionalesTbl = new TmsPagosActAdicionalesTbl();
        tmsPagosActAdicionalesTbl.setServicioId(getVwIncOper().get(getFilaIncidenciaSeleccionada()).getServicioId());
        tmsPagosActAdicionalesTbl.setReferenciaPagoActAdicional((long)0);
        tmsPagosActAdicionalesTbl.setTipoActividadAdicionalId((xDatosAdicionales()==null ? this.actAdicionalId : Long.valueOf(xDatosAdicionales()[0][3])));
        tmsPagosActAdicionalesTbl.setFechaHoraRecaudacion(tmActual);
        tmsPagosActAdicionalesTbl.setOperadorId(getVwIncOper().get(getFilaIncidenciaSeleccionada()).getOperadorId());
        System.out.println("retencion - monto "+getVwIncOper().get(getFilaIncidenciaSeleccionada()).getRetencion()+" - "+this.monto);
        tmsPagosActAdicionalesTbl.setMontoPago(this.monto);
        tmsPagosActAdicionalesTbl.setRetencion(Math.ceil(this.monto*Double.valueOf(getVwIncOper().get(getFilaIncidenciaSeleccionada()).getRetencion())));
        //tmsPagosActAdicionalesTbl.setEstadoPago("P");
        tmsPagosActAdicionalesTbl.setEstadoPago("R");
        //tmsPagosActAdicionalesTbl.setRecaudadorId( usuarioId);
        tmsPagosActAdicionalesTbl.setRecaudadorId( this.recaudadorId);
        tmsPagosActAdicionalesTbl.setCiudadRecaudacionId(Long.valueOf(datosTerminal[0].toString()));
        tmsPagosActAdicionalesTbl.setCreadoPor(usuarioId);
        tmsPagosActAdicionalesTbl.setFechaCreacion(tmActual);
        //tmsPagosActAdicionalesTbl.setUltimaActualizacionPor(usuarioId);
        tmsPagosActAdicionalesTbl.setUltimaActualizacionPor(this.recaudadorId);
        tmsPagosActAdicionalesTbl.setUltimaFechaActualizacion(tmActual);
        
        
        if(xDatosAdicionales()!=null){
            for(int i=0; i<xDatosAdicionales().length; i++){
                if(xDatosAdicionales()[i][1].equals("ADICIONAL1")) tmsPagosActAdicionalesTbl.setAdicional1(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL2")) tmsPagosActAdicionalesTbl.setAdicional2(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL3")) tmsPagosActAdicionalesTbl.setAdicional3(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL4")) tmsPagosActAdicionalesTbl.setAdicional4(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL5")) tmsPagosActAdicionalesTbl.setAdicional5(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL6")) tmsPagosActAdicionalesTbl.setAdicional6(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL7")) tmsPagosActAdicionalesTbl.setAdicional7(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL8")) tmsPagosActAdicionalesTbl.setAdicional8(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL9")) tmsPagosActAdicionalesTbl.setAdicional9(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL10")) tmsPagosActAdicionalesTbl.setAdicional10(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL11")) tmsPagosActAdicionalesTbl.setAdicional11(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL12")) tmsPagosActAdicionalesTbl.setAdicional12(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL13")) tmsPagosActAdicionalesTbl.setAdicional13(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL14")) tmsPagosActAdicionalesTbl.setAdicional14(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL15")) tmsPagosActAdicionalesTbl.setAdicional15(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL16")) tmsPagosActAdicionalesTbl.setAdicional16(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL17")) tmsPagosActAdicionalesTbl.setAdicional17(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL18")) tmsPagosActAdicionalesTbl.setAdicional18(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL19")) tmsPagosActAdicionalesTbl.setAdicional19(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL20")) tmsPagosActAdicionalesTbl.setAdicional20(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL21")) tmsPagosActAdicionalesTbl.setAdicional21(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL22")) tmsPagosActAdicionalesTbl.setAdicional22(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL23")) tmsPagosActAdicionalesTbl.setAdicional23(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL24")) tmsPagosActAdicionalesTbl.setAdicional24(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL25")) tmsPagosActAdicionalesTbl.setAdicional25(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL26")) tmsPagosActAdicionalesTbl.setAdicional26(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL27")) tmsPagosActAdicionalesTbl.setAdicional27(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL28")) tmsPagosActAdicionalesTbl.setAdicional28(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL29")) tmsPagosActAdicionalesTbl.setAdicional29(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL30")) tmsPagosActAdicionalesTbl.setAdicional30(xDatosAdicionales()[i][2]);
            }
        }
        tmsPagosActAdicionalesTbl.setAdicional1("1");
        try{
            if(!abreSocketAS()) return false;
            tmsGuardiasFacade.registraPagoAdicional(PrefijoTerminalId, tmsPagosActAdicionalesTbl);
            System.out.println("Se aplico PreIngresoRecaudacion");
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    private boolean PreIngresoRecaudacionEspecial(long usuarioId, Timestamp fechaserv) {
        Timestamp tmActual = fechaserv;//new Timestamp(new Date().getTime());
        TmsPagosActAdicionalesTbl tmsPagosActAdicionalesTbl = new TmsPagosActAdicionalesTbl();
        tmsPagosActAdicionalesTbl.setServicioId(getEspecial().getServicioId());
        tmsPagosActAdicionalesTbl.setReferenciaPagoActAdicional((long)0);
        tmsPagosActAdicionalesTbl.setTipoActividadAdicionalId((xDatosAdicionales()==null ? this.actAdicionalId : Long.valueOf(xDatosAdicionales()[0][3])));
        tmsPagosActAdicionalesTbl.setFechaHoraRecaudacion(tmActual);
        tmsPagosActAdicionalesTbl.setOperadorId(getEspecial().getOperadorId());
        System.out.println("retencion - monto "+getEspecial().getRetencion()+" - "+this.monto);
        tmsPagosActAdicionalesTbl.setMontoPago(this.monto);
        tmsPagosActAdicionalesTbl.setRetencion(Math.ceil(this.monto*Double.valueOf(getEspecial().getRetencion())));
        //tmsPagosActAdicionalesTbl.setEstadoPago("P");
        tmsPagosActAdicionalesTbl.setEstadoPago("R");
        //tmsPagosActAdicionalesTbl.setRecaudadorId( usuarioId);
        tmsPagosActAdicionalesTbl.setRecaudadorId( this.recaudadorId);
        tmsPagosActAdicionalesTbl.setCiudadRecaudacionId(Long.valueOf(datosTerminal[0].toString()));
        tmsPagosActAdicionalesTbl.setCreadoPor(usuarioId);
        tmsPagosActAdicionalesTbl.setFechaCreacion(tmActual);
        //tmsPagosActAdicionalesTbl.setUltimaActualizacionPor(usuarioId);
        tmsPagosActAdicionalesTbl.setUltimaActualizacionPor(this.recaudadorId);
        tmsPagosActAdicionalesTbl.setUltimaFechaActualizacion(tmActual);
        
        if(xDatosAdicionales()!=null){
            for(int i=0; i<xDatosAdicionales().length; i++){
                if(xDatosAdicionales()[i][1].equals("ADICIONAL1")) tmsPagosActAdicionalesTbl.setAdicional1(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL2")) tmsPagosActAdicionalesTbl.setAdicional2(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL3")) tmsPagosActAdicionalesTbl.setAdicional3(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL4")) tmsPagosActAdicionalesTbl.setAdicional4(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL5")) tmsPagosActAdicionalesTbl.setAdicional5(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL6")) tmsPagosActAdicionalesTbl.setAdicional6(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL7")) tmsPagosActAdicionalesTbl.setAdicional7(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL8")) tmsPagosActAdicionalesTbl.setAdicional8(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL9")) tmsPagosActAdicionalesTbl.setAdicional9(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL10")) tmsPagosActAdicionalesTbl.setAdicional10(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL11")) tmsPagosActAdicionalesTbl.setAdicional11(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL12")) tmsPagosActAdicionalesTbl.setAdicional12(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL13")) tmsPagosActAdicionalesTbl.setAdicional13(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL14")) tmsPagosActAdicionalesTbl.setAdicional14(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL15")) tmsPagosActAdicionalesTbl.setAdicional15(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL16")) tmsPagosActAdicionalesTbl.setAdicional16(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL17")) tmsPagosActAdicionalesTbl.setAdicional17(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL18")) tmsPagosActAdicionalesTbl.setAdicional18(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL19")) tmsPagosActAdicionalesTbl.setAdicional19(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL20")) tmsPagosActAdicionalesTbl.setAdicional20(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL21")) tmsPagosActAdicionalesTbl.setAdicional21(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL22")) tmsPagosActAdicionalesTbl.setAdicional22(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL23")) tmsPagosActAdicionalesTbl.setAdicional23(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL24")) tmsPagosActAdicionalesTbl.setAdicional24(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL25")) tmsPagosActAdicionalesTbl.setAdicional25(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL26")) tmsPagosActAdicionalesTbl.setAdicional26(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL27")) tmsPagosActAdicionalesTbl.setAdicional27(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL28")) tmsPagosActAdicionalesTbl.setAdicional28(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL29")) tmsPagosActAdicionalesTbl.setAdicional29(xDatosAdicionales()[i][2]);
                if(xDatosAdicionales()[i][1].equals("ADICIONAL30")) tmsPagosActAdicionalesTbl.setAdicional30(xDatosAdicionales()[i][2]);
            }
        }
        tmsPagosActAdicionalesTbl.setAdicional1("1");
        try{
            if(!abreSocketAS()) return false;
            tmsGuardiasFacade.registraPagoAdicional(PrefijoTerminalId, tmsPagosActAdicionalesTbl);
            return true;
        }catch(Exception ex){
            System.out.println("PreIngresoRecaudacionEspecial "+ex.getMessage());
            return false;
        }
    }
    
    public boolean busquedaEspecial(){
        if(!abreSocketAS()) return false;
        Vector y = tmsGuardiasFacade.OperadorIncidencia(getOperIncId());
        System.out.println("datos de Busqueda especial *****   \n"+y);
        if(y==null) return false;
        especial = new vwIncOper();
        especial.setOperIncidenciaId(Long.valueOf(y.get(0).toString()));
        especial.setOperadorId(Long.valueOf(y.get(1).toString()));
        especial.setClaveOperador(y.get(2).toString());
        especial.setOperadorNombreCompleto(y.get(3).toString());
        especial.setIncidenciaId(Long.valueOf(y.get(4).toString()));
        especial.setIncidenciaClave(y.get(5).toString());
        especial.setIncidenciaNombre(y.get(6).toString());
        especial.setAplicaRecaudacion(y.get(7).toString()); 
        especial.setConfigurable(y.get(8).toString());
        try {
            especial.setFechaInicial(new Timestamp(formatoTS.parse(y.get(9).toString()).getTime()));
        } catch (ParseException ex) {
            ex.printStackTrace();
            return false;
        }
        try {
            especial.setFechaFinal((y.get(10)==null ? null : new Timestamp(formatoTS.parse(y.get(10).toString()).getTime())));
        } catch (ParseException ex) {
            ex.printStackTrace();
            return false;
        }
        especial.setIncidenciaValor(y.get(11).toString());
        especial.setDuracion((y.get(12)==null ? "" : y.get(12).toString()));
        especial.setRetencion((y.get(13)==null ? "0" : y.get(13).toString()));
        especial.setMonto((y.get(14)==null ? "0" : y.get(14).toString()));
        especial.setServicioId(Long.valueOf(y.get(15).toString()));
        especial.setServicioNombre(y.get(16).toString());
        especial.setIncidenciaAutorizada((y.get(17).toString().equals("S") ? "AUTORIZADA" : "NO AUTORIZADA"));
        
        return true;
    }
    
    public vwIncOper getEspecial(){ return this.especial; }
    
    public Vector tieneGuardia(String claveOperador, String Fecha_Ini, String Fecha_Fin){
        System.out.println("Sessin.tieneGuardia  "+claveOperador+" abreSocketAS "+abreSocketAS()+  "claveOperador");
        if(!abreSocketAS()) return null;  //BRA false;
        System.out.println("Antes de ir a B D "+claveOperador+"   "+ Fecha_Ini+"   "+ Fecha_Fin);
        Vector vectorIncOper = tmsGuardiasFacade.OperadorIncidencias(claveOperador, "", "", Fecha_Ini, Fecha_Fin, "N","TODAS");
        //if(vectorIncOper==null) return false;
        return vectorIncOper;    //true;
    }

    public boolean tieneGuardiasCruzadas(String claveOperador, String Fecha_Ini, String Fecha_Fin){
        System.out.println("Sessin.tieneGuardiasCruzadas claveOperador: "+claveOperador+" abreSocketAS: "+abreSocketAS());
        System.out.println("Antes de ir a B D (tieneGuardiasCruzadas) "+claveOperador+"   "+ Fecha_Ini+"   "+ Fecha_Fin);
        long nguardias = tmsGuardiasFacade.buscaGuardiasCruzadas(claveOperador, Fecha_Ini, Fecha_Fin);
        //if(vectorIncOper==null) return false;
        return (nguardias>0?true:false);    //true;
    }

    private boolean duracionGuardia() {
        if(!abreSocketAS()) return false;
        String strDuracionGuardia = tmsGuardiasFacade.duracionGuardia();
        if(strDuracionGuardia==null) return false;
        try{
            duracionGuardia = Integer.valueOf(strDuracionGuardia);
        }catch(Exception ex){
            return false;
        }
        unaGuardiaHoras = duracionGuardia * hora;
        return true;
    }
    
    public void setTiempoGuardia(long d){
        tiempoGuardia = (double)(d/hora);
        //System.out.println("TIEMPO GUARDIA "+tiempoGuardia+" ----- "+d);
    }
    
    public double getTiempoGuardia(){
        return this.tiempoGuardia;
    }
    
    public void setCtdGuardia(){
        this.CantidadGuardia = (tiempoGuardia/duracionGuardia);
        //System.out.println("CANTIDAD GUARDIA "+CantidadGuardia);
        if(this.CantidadGuardia<0.5){
            this.CantidadGuardia = 0;
            return;
        }
        if(this.CantidadGuardia>=0.5 && this.CantidadGuardia<1.0){
            this.CantidadGuardia = 0.5;
            return;
        }
        if(this.CantidadGuardia>1.0){
            this.CantidadGuardia = 1.0;
            return;
        }
    }
    
    public double getCtdGuardia(){
        return this.CantidadGuardia;
    }
    
    public long getUnaGuardiaHoras(){
        return this.unaGuardiaHoras;
    }
    
    public void setMonto(double dlbMonto, double guardia){
        this.monto = dlbMonto*guardia;
        System.out.println("monto GUARDIA cobro "+monto+ " - " +dlbMonto+" - "+guardia);
    }
    
    public double getMonto(){
        return this.monto;
    }

    private void getDatosRecaudador()
    {
        this.recaudadorId = tmsGuardiasFacade.getDatosRecaudador();
    }

    private boolean DatosGuardias() {
        if(!abreSocketAS()) return false;
        valorServicioGuardia = tmsGuardiasFacade.valorServicioGuardia();
        if(valorServicioGuardia==null) return false;
        return true;
    }

    public long duracionGuardiaServicio(long servicioId) {
        return tmsGuardiasFacade.duracionGuardiaServicio(servicioId);
    }

    public String getDatosGuardia(String ServicioId){
        for(int i=0; i<valorServicioGuardia.length; i++)
        {
            if(valorServicioGuardia[i][0].equals(ServicioId)) return valorServicioGuardia[i][1];
        }
        return "1";
    }
    
    public boolean actAdicId(String clave){
        if(!abreSocketAS()) return false;
        actAdicionalId = tmsGuardiasFacade.obtieneIdDatosAdicionales(clave);
        
        if(actAdicionalId==-1) return false;
        return true;
    }
    
    public boolean abreSocketAS(){
        /*Socket s = null;
        try {
            s = new Socket(getIpAS(), getPortAS());
            return true;
        }catch( IOException e ) {
            return false;
        }catch(Exception err){
            return false;
        }*/
        return true;
    }
    
    public void setIpAS(String valor){ this.ipAS = valor; }
    public void setPortAS(int valor){ this.portAS = valor; }
    
    public String getIpAS(){ return this.ipAS; }
    public int getPortAS(){ return this.portAS; }
    
     public boolean isRecauda(String  CveIncidencia) {
         if(!abreSocketAS()) return false;
        
        return tmsGuardiasFacade.getInc_Aplica_Recauda(CveIncidencia);
       
    }   
    
     public void setOperIncidencia(String operador,String claveincidencia)
     {
      this.operIncId = Long.parseLong(operador );
     // getEspecial().setIncidenciaClave(claveincidencia);
      
     }

     public String getRegistrosOper(String clave,String fechaInicial, String fechaFinal)
     {
         return tmsGuardiasFacade.obtenerRegistrosOperador(clave, fechaInicial, fechaFinal);
     }

    public String getTarjetasOper(String clave,String fechaInicial, String fechaFinal)
     {
         return tmsGuardiasFacade.obtenerTarjetasOperador(clave, fechaInicial, fechaFinal);
     }

    public String getNumTarOper(String clave,String fechaInicial, String fechaFinal)
     {
         return tmsGuardiasFacade.obtenerNumTarOperador(clave, fechaInicial, fechaFinal);
     }

    public boolean isGuardiaValida(String clave,String fechaInicial, String fechaFinal,long servicioId)
     {
         String res = tmsGuardiasFacade.validaGuardia(clave, fechaInicial, fechaFinal,servicioId);

         return res.equals("AUTORIZADA");
     }


     /**
     * @return the terminales
     */
    public String getTerminales() {
        return terminales;
    }

    /**
     * @param terminales the terminales to set
     */
    public void setTerminales(String terminales) {
        this.terminales = terminales;
    }

    public Timestamp getFechaServidor()
    {
     Vector x = (Vector) tmsGuardiasFacade.fechaServidor();
     return  fecha_servidor.valueOf(x.get(0).toString());
    }

    /**
     * @return the puerto
     */
    public String getPuerto() {
        return puerto;
    }

    /**
     * @param puerto the puerto to set
     */
    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    /**
     * @return the nombreImpresora
     */
    public String getNombreImpresora() {
        return nombreImpresora;
    }

    /**
     * @param nombreImpresora the nombreImpresora to set
     */
    public void setNombreImpresora(String nombreImpresora) {
        this.nombreImpresora = nombreImpresora;
    }

    
  public TimeZone getTimeZone()
  {
    TimeZone.setDefault(tmsGuardiasFacade.getTimeZone());
    return tmsGuardiasFacade.getTimeZone();
  }

    /**
     * @return the nombreFormato
     */
    public String getNombreFormato() {
        return nombreFormato;
    }

    /**
     * @param nombreFormato the nombreFormato to set
     */
    public void setNombreFormato(String nombreFormato) {
        this.nombreFormato = nombreFormato;
    }
    
    
}