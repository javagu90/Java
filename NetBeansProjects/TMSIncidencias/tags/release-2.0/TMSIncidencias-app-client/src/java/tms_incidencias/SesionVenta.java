package tms_incidencias;

import DialogosIncidencias.JDlgAceptar;
import java.awt.Color;
import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.TimeZone;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tms_incidencias.entidad.TmsAccionesTbl;
import tms_incidencias.entidad.TmsEmpresasTbl;
import tms_incidencias.entidad.TmsEstadoOperadoresV;
import tms_incidencias.entidad.TmsActAdicionalesTbl;
import tms_incidencias.entidad.TmsOperadorIncidenciasTbl;
import tms_incidencias.entidad.TmsPagosActAdicionalesTbl;
import tms_incidencias.entidad.TmsServiciosTbl;
import tms_incidencias.solicitud.TmsIncidenciaFacadeRemote;

public class SesionVenta {
    private String ipAS;
    private int portAS;
    private String dblCentral;
    private SimpleDateFormat formatoTS = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SimpleDateFormat formatoTS1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");// = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private JDlgAceptar DialogoAceptar;
    private TmsIncidenciaFacadeRemote tmsIncidenciaFacade;
    
    private String[] datosTerminal=null;
    private String PrefijoTerminalId;
    
    private List<TmsActAdicionalesTbl> tmsIncidenciasTbl=null;
    private Object[][] paramIncidencias=null;
    private List<TmsServiciosTbl> tmsServiciosTbl=null;
    private List<TmsEmpresasTbl> tmsEmpresasTbl=null;
    private List<TmsAccionesTbl> tmsAccionesTbl=null;
    private List<TmsEstadoOperadoresV> objTmsOperadoresV = null;
    private List<TmsEstadoOperadoresV> tmsOperadoresV=null;
    private List<vwIncOper> IncOper=null;
    private vwIncOper especial=null;
    private long operIncId;
    private String[][] datosAdicionales;
    private int filaOperadorSeleccionado;
    private int filaIncidenciaSeleccionada;

    private long actAdicionalId;
  
    public SesionVenta() { }
   
    public int proceso(long MENU_ID, String pIpAS, int pPortAS){
        setIpAS(pIpAS);
        setPortAS(pPortAS);
        if(!abreSocketAS()) return -1;
        try {
            Context context = getInitialContext();
	    tmsIncidenciaFacade = (TmsIncidenciaFacadeRemote)context.lookup("tms_incidencias.solicitud.TmsIncidenciaFacadeRemote");
        } catch (Exception ex) {
            return -1;
        }
        if(!cargaTerminal()) return 1;
        if(!Incidencias()) return 2;
        if(!paramIncidencias()) return 5;
        if(!Servicios()) return 3;
        if(!Empresas()) return 31;
        if(!Estados()) return 4;
        if(!Operadores()) return 41;
        if(!ligaCentral()) return 10;
        return 0;
    }
    
    private boolean cargaTerminal() {
        if(!abreSocketAS()) return false;
        datosTerminal = tmsIncidenciaFacade.obtenerTerminal();
        if(datosTerminal==null) return false;
        if(datosTerminal[0].length()>3) PrefijoTerminalId  = datosTerminal[0].substring(0,3);
        if(datosTerminal[0].length()==3) PrefijoTerminalId = datosTerminal[0];
        if(datosTerminal[0].length()==2) PrefijoTerminalId = datosTerminal[0]+"0";
        if(datosTerminal[0].length()==1) PrefijoTerminalId = datosTerminal[0]+"00";
        System.out.println("cargo la terminal...");
        return true;
    }

    private static Context getInitialContext() throws NamingException{
        return new InitialContext();
    }

    private boolean Incidencias() {
        if(!abreSocketAS()) return false;
        tmsIncidenciasTbl = tmsIncidenciaFacade.queryTmsIncidenciasAll();
        if(tmsIncidenciasTbl==null) return false;
        return true;
    }
    
    private boolean paramIncidencias() {
        if(!abreSocketAS()) return false;
        paramIncidencias = tmsIncidenciaFacade.queryParamIncidencias();
        System.out.println("paramIncidencias: "+paramIncidencias);
        if(paramIncidencias==null) return false;
        return true;
    }
    
    public String obtieneValorParamIncidencia(long incId, long servId){
        String strIncId = String.valueOf(incId), strServId = String.valueOf(servId);
        for(int i=0; i<paramIncidencias.length; i++)
            if(paramIncidencias[i][0].toString().equals(strIncId) && paramIncidencias[i][1].toString().equals(strServId))
                return paramIncidencias[i][2].toString();
        return "";
    }
    
    public String getAccionNombre(long accionId){
        for(int i=0; i<tmsAccionesTbl.size(); i++)
            if(tmsAccionesTbl.get(i).getAccionId() == accionId) return tmsAccionesTbl.get(i).getAccion();
        
        return "";
    }
    
    private boolean Estados() {
        if(!abreSocketAS()) return false;
        tmsAccionesTbl = tmsIncidenciaFacade.queryTmsAccionesTblAll();
        if(tmsAccionesTbl==null) return false;
        return true;
    }
    
    private boolean Operadores() {
        if(!abreSocketAS()) return false;
        tmsOperadoresV = tmsIncidenciaFacade.queryTmsOperadoresVAll();
        if(tmsAccionesTbl==null) return false;
        return true;
    }
    
    public long getAccionId(String accion){
        for(int i=0; i<tmsAccionesTbl.size(); i++)
            if(tmsAccionesTbl.get(i).getAccion().equals(accion)) return tmsAccionesTbl.get(i).getAccionId();
        
        return -1;
    }
    
    public String getActividad(long actId){
        for(int i=0; i<tmsIncidenciasTbl.size(); i++)
            if(tmsIncidenciasTbl.get(i).getTipoActividadAdicionalId() == actId) return tmsIncidenciasTbl.get(i).getActividadClave();
        return "GUARDIA";
    }
    
    private boolean Servicios() {
        if(!abreSocketAS()) return false;
        tmsServiciosTbl = tmsIncidenciaFacade.queryTmsServiciosAll();
        if(tmsServiciosTbl==null) return false;
        return true;
    }
    
    private boolean Empresas() {
        if(!abreSocketAS()) return false;
        tmsEmpresasTbl = tmsIncidenciaFacade.queryTmsEmpresasAll();
        if(tmsServiciosTbl==null) return false;
        return true;
    }
    
    public List<TmsActAdicionalesTbl> getTmsIncidenciasTbl(){ return this.tmsIncidenciasTbl; }
    
    public List<TmsServiciosTbl> getTmsServiciosTbl(){ return this.tmsServiciosTbl; }
    
    public List<TmsEmpresasTbl> getTmsEmpresasTbl(){ return this.tmsEmpresasTbl; }
    
    public Object[][] busqueda(String claveOperador, String Servicio, String claveIncidencia, String fecha1, String fecha2, String estado){
        if(claveOperador.equals("")) claveOperador = "%";
        if(Servicio.equals("TODOS")) Servicio = "";
        if(claveIncidencia.equals("TODOS")) claveIncidencia = "";
        if(estado.equals("TODOS")) estado = "";
        if(estado.equals("AUTORIZADA")) estado = "S";
        if(estado.equals("NO AUTORIZADA")) estado = "N";
        if(estado.equals("CANCELADA")) estado = "C";
        
        if(!abreSocketAS()) return null;
        Vector vectorIncOper = tmsIncidenciaFacade.OperadorIncidencias(claveOperador, Servicio, claveIncidencia, fecha1, fecha2, estado); 
                                                   
        
        if(vectorIncOper==null) return null;
        IncOper = new ArrayList<vwIncOper>();
        Object[][] resultado = new Object[vectorIncOper.size()][7];
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
                VwIncOper.setFechaInicial(new Timestamp(formatoTS1.parse(y.get(9).toString()).getTime()));
             } catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
            try {
                VwIncOper.setFechaFinal((y.get(10)==null ? null : new Timestamp(formatoTS1.parse(y.get(10).toString()).getTime())));
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
            VwIncOper.setIncidenciaAutorizada((y.get(17).toString().equals("S") ? "AUTORIZADA" : (y.get(17).toString().equals("N") ? "NO AUTORIZADA": "CANCELADA")));
            VwIncOper.setObservacion((y.get(18)==null ? "" : y.get(18).toString()));
            IncOper.add(VwIncOper);
            resultado[i][0] = VwIncOper.getClaveOperador();
            resultado[i][1] = VwIncOper.getIncidenciaClave();
            resultado[i][2] = formatoFecha.format(new Date(VwIncOper.getFechaInicial().getTime()));
            resultado[i][3] = (VwIncOper.getFechaFinal()==null ? "" : formatoFecha.format(new Date(VwIncOper.getFechaFinal().getTime())));
            resultado[i][4] = VwIncOper.getIncidenciaValor();
            resultado[i][5] = VwIncOper.getServicioNombre();
            resultado[i][6] = VwIncOper.getIncidenciaAutorizada();
        }
        
        return resultado;
    }
    
    public List<vwIncOper> getVwIncOper(){ return this.IncOper; }
    
    public int getFilaIncidenciaSeleccionada(){
        
      
        return this.filaIncidenciaSeleccionada; }
    
    public void setFilaIncidenciaSeleccionada(int fila){ this.filaIncidenciaSeleccionada = fila; }
    
    public Object[][] buscarOperador(String numero){
        if(!abreSocketAS()) return null;
        int i;
        this.objTmsOperadoresV = new ArrayList<TmsEstadoOperadoresV>();
        if(numero.equals("%")){
            Object[][] datos = new Object[tmsOperadoresV.size()][5];
            for(i=0; i<datos.length; i++){
                datos[i][0] = tmsOperadoresV.get(i).getClaveOperador();
                datos[i][1] = tmsOperadoresV.get(i).getOperadorNombreCompleto();
                datos[i][2] = tmsOperadoresV.get(i).getEstadoNombre();
                datos[i][3] = tmsOperadoresV.get(i).getUbicacionNombre();
                datos[i][4] = tmsOperadoresV.get(i).getActividadNombre();
                this.objTmsOperadoresV.add(llenaObjeto(tmsOperadoresV.get(i)));
            }
            return datos;
        }
        
        numero = numero.replaceAll("%","");
        Vector ids = new Vector();
        for(i=0; i<tmsOperadoresV.size(); i++){
            if(tmsOperadoresV.get(i).getClaveOperador().contains(numero))
                ids.add(i);
        }
        
        if(ids.size()==0) return null;
        
        Object[][] datos = new Object[ids.size()][5];
        int j;
        for(i=0; i<datos.length; i++){
            j=Integer.valueOf(ids.get(i).toString());
            datos[i][0] = tmsOperadoresV.get(j).getClaveOperador();
            datos[i][1] = tmsOperadoresV.get(j).getOperadorNombreCompleto();
            datos[i][2] = tmsOperadoresV.get(j).getEstadoNombre();
            datos[i][3] = tmsOperadoresV.get(j).getUbicacionNombre();
            datos[i][4] = tmsOperadoresV.get(j).getActividadNombre();
            this.objTmsOperadoresV.add(llenaObjeto(tmsOperadoresV.get(j)));
        }
        return datos;
    }
    
    public List<TmsEstadoOperadoresV> buscarOperadorEntidad(String numero){
        if(!abreSocketAS()) return null;
        int i;
        if(numero.equals("%")){
            List<TmsEstadoOperadoresV> datos = new ArrayList<TmsEstadoOperadoresV>();
            datos = this.tmsOperadoresV;
            return datos;
        }
        
        numero = numero.replaceAll("%","");
        Vector ids = new Vector();
        for(i=0; i<tmsOperadoresV.size(); i++){
            if(tmsOperadoresV.get(i).getClaveOperador().contains(numero))
                ids.add(i);
        }
        
        if(ids.size()==0) return null;
        
        List<TmsEstadoOperadoresV> datos = new ArrayList<TmsEstadoOperadoresV>();
        int j;
        for(i=0; i<ids.size(); i++){
            j=Integer.valueOf(ids.get(i).toString());
            datos.add(llenaObjeto(tmsOperadoresV.get(j)));
        }
        return datos;
    }
    
    public List<TmsEstadoOperadoresV> getTmsEstadoOperadoresV(){ return this.objTmsOperadoresV; }
    
    public int getFilaOperadorSeleccionado(){ return this.filaOperadorSeleccionado; }
    
    public void setFilaOperadorSeleccionado(int fila){ this.filaOperadorSeleccionado = fila; }
    
    public boolean registrarIncidencia(TmsOperadorIncidenciasTbl incidencia){
        try{
            if(!abreSocketAS()) return false;
            operIncId=tmsIncidenciaFacade.registraIncidenciaOperador(PrefijoTerminalId, incidencia);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public long getOperIncId(){ return this.operIncId; }
    
    public boolean autorizaIncidencia(long IncOperId, long usuarioId){
        Timestamp tmActual = new Timestamp(new Date().getTime());
        try{
            if(!abreSocketAS()) return false;
            tmsIncidenciaFacade.autorizaIncidenciaOperador(IncOperId, usuarioId, tmActual);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean cancelaIncidencia(long usuarioId){
        Timestamp tmActual = new Timestamp(new Date().getTime());
        try{
            if(!abreSocketAS()) return false;
            tmsIncidenciaFacade.cancelaIncidenciaOperador(getVwIncOper().get(getFilaIncidenciaSeleccionada()).getOperIncidenciaId(), usuarioId, tmActual);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean modificaEstadoOperador(long usuarioId, long operadorId, String operadorClave, String incClave){
            Timestamp tmActual = new Timestamp(new Date().getTime());
            objTmsOperadoresV = buscarOperadorEntidad(operadorClave);
            if(objTmsOperadoresV==null) return true;
            long EstadoId = getAccionId("FUERA DE ROL");
            long UbicacionId = getTmsEstadoOperadoresV().get(0).getUbicacionId();
            long ActividadId = getAccionId("INCIDENCIA");
            if(incClave.equals("INCAPACIDAD")){
                UbicacionId = getAccionId("NO LABORAL");
            }
            return tmsIncidenciaFacade.cambiaEstadoOperador(operadorId, EstadoId, UbicacionId, ActividadId, usuarioId, tmActual, this.datosTerminal[2]);
    }
    
    public String getNombreOperador(String clave){
        for(int i=0; i<this.tmsOperadoresV.size(); i++)
            if(this.tmsOperadoresV.get(i).getClaveOperador().equals(clave)) return this.tmsOperadoresV.get(i).getOperadorNombreCompleto();
        return "S/N";
    }
    
    public boolean modificaIncidencia(long IncOperId, String valor, Timestamp fechaFinal1, Timestamp fechaFinal2, String obsParte1, String obsParte2, long usuarioId){
        Timestamp tmActual = new Timestamp(new Date().getTime());
        try{
            if(!abreSocketAS()) return false;
            tmsIncidenciaFacade.modificarIncidenciaOperador(IncOperId, valor, fechaFinal1, fechaFinal2, obsParte1, obsParte2, usuarioId, tmActual);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    public boolean obtenerDatosAdicionales(){
        if(!abreSocketAS()){
            datosAdicionales = null;
            return false;
        }
        String[][] datosAdicionalesR = tmsIncidenciaFacade.obtieneDatosAdicionales(getVwIncOper().get(getFilaIncidenciaSeleccionada()).getIncidenciaClave());
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
    // 
    public boolean obtenerDatosAdicionalesEspeciales(String IncidenciaClave){
        if(!abreSocketAS()){
            datosAdicionales = null;
            return false;
        }
        System.out.println("IncidenciaClave  "+IncidenciaClave);
      //  String[][] datosAdicionalesR = tmsIncidenciaFacade.obtieneDatosAdicionales(getEspecial().getIncidenciaClave());
         String[][] datosAdicionalesR = tmsIncidenciaFacade.obtieneDatosAdicionales(IncidenciaClave);
         
         System.out.println("datosAdicionalesR "+datosAdicionalesR);
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

    public boolean ingresarPreIngreso(long usuarioId) {
        System.out.println(" Aplica Recauda  "+getVwIncOper().get(getFilaIncidenciaSeleccionada()).getAplicaRecaudacion());
        if(getVwIncOper().get(getFilaIncidenciaSeleccionada()).getAplicaRecaudacion().equals("S")) if(!PreIngresoRecaudacion(usuarioId,getVwIncOper().get(getFilaIncidenciaSeleccionada()).getIncidenciaId())) return false;
        if(!autorizaIncidencia(getVwIncOper().get(getFilaIncidenciaSeleccionada()).getOperIncidenciaId(), usuarioId)) return false;
        return true;
    }
    
    public boolean ingresarPreIngresoEspecial(long usuarioId) {
        if(getEspecial().getAplicaRecaudacion().equals("S")) if(!PreIngresoRecaudacionEspecial(usuarioId,getEspecial().getIncidenciaId())) return false;
        if(!autorizaIncidencia(getEspecial().getOperIncidenciaId(), usuarioId/*,
                getEspecial().getOperadorId(), getEspecial().getClaveOperador(), getEspecial().getIncidenciaClave()*/)) return false;
        return true;
    }

    private boolean PreIngresoRecaudacion(long usuarioId, long tipoActAdicId) {
        Timestamp tmActual = new Timestamp(new Date().getTime());
        TmsPagosActAdicionalesTbl tmsPagosActAdicionalesTbl = new TmsPagosActAdicionalesTbl();
        tmsPagosActAdicionalesTbl.setServicioId(getVwIncOper().get(getFilaIncidenciaSeleccionada()).getServicioId());
        tmsPagosActAdicionalesTbl.setReferenciaPagoActAdicional((long)0);
        tmsPagosActAdicionalesTbl.setTipoActividadAdicionalId(tipoActAdicId);
        tmsPagosActAdicionalesTbl.setFechaHoraRecaudacion(tmActual);
        tmsPagosActAdicionalesTbl.setOperadorId(getVwIncOper().get(getFilaIncidenciaSeleccionada()).getOperadorId());
        tmsPagosActAdicionalesTbl.setMontoPago(Double.valueOf(getVwIncOper().get(getFilaIncidenciaSeleccionada()).getMonto()));
        tmsPagosActAdicionalesTbl.setRetencion(Double.valueOf(getVwIncOper().get(getFilaIncidenciaSeleccionada()).getRetencion()));
        tmsPagosActAdicionalesTbl.setEstadoPago("P");
        tmsPagosActAdicionalesTbl.setRecaudadorId(usuarioId);
        tmsPagosActAdicionalesTbl.setCiudadRecaudacionId(Long.valueOf(datosTerminal[0].toString()));
        tmsPagosActAdicionalesTbl.setCreadoPor(usuarioId);
        tmsPagosActAdicionalesTbl.setFechaCreacion(tmActual);
        tmsPagosActAdicionalesTbl.setUltimaActualizacionPor(usuarioId);
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
        try{
            if(!abreSocketAS()) return false;
            tmsIncidenciaFacade.registraPagoAdicional(PrefijoTerminalId, tmsPagosActAdicionalesTbl);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    
    private boolean PreIngresoRecaudacionEspecial(long usuarioId, long tipoActAdicId) {
        System.out.println ("Entrando a PreIngresoRecaudacionEspecial");
        Timestamp tmActual = new Timestamp(new Date().getTime());
        TmsPagosActAdicionalesTbl tmsPagosActAdicionalesTbl = new TmsPagosActAdicionalesTbl();
        tmsPagosActAdicionalesTbl.setServicioId(getEspecial().getServicioId());
        tmsPagosActAdicionalesTbl.setReferenciaPagoActAdicional((long)0);
        tmsPagosActAdicionalesTbl.setTipoActividadAdicionalId(tipoActAdicId);
        tmsPagosActAdicionalesTbl.setFechaHoraRecaudacion(tmActual);
        tmsPagosActAdicionalesTbl.setOperadorId(getEspecial().getOperadorId());
        tmsPagosActAdicionalesTbl.setMontoPago(Double.valueOf(getEspecial().getMonto()));
        tmsPagosActAdicionalesTbl.setRetencion(Double.valueOf(getEspecial().getRetencion()));
        tmsPagosActAdicionalesTbl.setEstadoPago("P");
        tmsPagosActAdicionalesTbl.setRecaudadorId(usuarioId);
        tmsPagosActAdicionalesTbl.setCiudadRecaudacionId(Long.valueOf(datosTerminal[0].toString()));
        tmsPagosActAdicionalesTbl.setCreadoPor(usuarioId);
        tmsPagosActAdicionalesTbl.setFechaCreacion(tmActual);
        tmsPagosActAdicionalesTbl.setUltimaActualizacionPor(usuarioId);
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
        try{
            if(!abreSocketAS()) return false;
            tmsIncidenciaFacade.registraPagoAdicional(PrefijoTerminalId, tmsPagosActAdicionalesTbl);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean busquedaEspecial(){
        System.out.println("busquedaEspecial  abreSocketAS() "+abreSocketAS() + "   getOperIncId() "+getOperIncId());
        if(!abreSocketAS()) return false;
        
        Vector y = tmsIncidenciaFacade.OperadorIncidencia(getOperIncId());
        System.out.println("***   y   "+y);
        if(y==null  || y.size()<=0) return false;
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
        especial.setIncidenciaAutorizada((y.get(17).toString().equals("S") ? "AUTORIZADA" : (y.get(17).toString().equals("N") ? "NO AUTORIZADA" : "CANCELADA")));
        
        return true;
    }
    
    public vwIncOper getEspecial(){ return this.especial; }
    
    public boolean tieneIncidencia(String claveOperador, String fecha1, String fecha2){
        if(!abreSocketAS()) return false;
        return tmsIncidenciaFacade.tieneIncidencias(claveOperador,fecha1,fecha2);
    }
    
    public String tieneTVPR(String claveOperador){
        if(!abreSocketAS()) return null;
        return tmsIncidenciaFacade.OperadorTVPR(claveOperador, getLigaCentral());
    }
    
    private boolean ligaCentral(){
        if(!abreSocketAS()) return false;
        dblCentral = tmsIncidenciaFacade.obtenerLigaCentral();
        if(dblCentral==null) return false;
        return true;
    }
    
    public String getLigaCentral(){ return this.dblCentral; }
    
    public boolean actAdicId(String clave){
        if(!abreSocketAS()) return false;
        actAdicionalId = tmsIncidenciaFacade.obtieneIdDatosAdicionales(clave);
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
    
    public String obtenerPMT(String mes, String Empresa, String Servicio){
        System.out.println("Mes "+mes+" Empresa  "+Empresa+" Servicio "+Servicio+"  PRIMARIA");
        String x = tmsIncidenciaFacade.obtenerPMT(mes, Empresa, Servicio,"PRIMARIA");
        System.out.println(" Datos en en categoria Primaria "+x);
        
      //  System.out.println("Mes "+mes+" Empresa  "+Empresa+" Servicio "+Servicio+"  AUXILIAR");
       String z = ""; //tmsIncidenciaFacade.obtenerPMT(mes, Empresa, Servicio,"AUXILIAR");
      //  System.out.println(" Datos en en categoria Primaria "+z);
        
        if(x==null && z==null) return null;
        if(x==null) x="";
        if(z==null) z="";
        return x+z;
    }
    
    
    // BRA Trae la fecha del servidor
     public String getToDate(){
        String todate  = (String)tmsIncidenciaFacade.getToDate();
        return todate;
    }
     
     public TimeZone getTimeZone()
     {
        TimeZone timezone =  tmsIncidenciaFacade.getTimeZone();
        return timezone;
     }
     
    public void setIpAS(String valor){ this.ipAS = valor; }
    public void setPortAS(int valor){ this.portAS = valor; }
    
    public String getIpAS(){ return this.ipAS; }
    public int getPortAS(){ return this.portAS; }

    private TmsEstadoOperadoresV llenaObjeto(TmsEstadoOperadoresV e) {
        TmsEstadoOperadoresV op = new TmsEstadoOperadoresV();
        op.setActividadId(e.getActividadId());
        op.setActividadNombre(e.getActividadNombre());
        op.setAplicaRetencion(e.getAplicaRetencion());
        op.setClaveOperador(e.getClaveOperador());
        op.setEstadoId(e.getEstadoId());
        op.setEstadoNombre(e.getEstadoNombre());
        op.setOperadorId(e.getOperadorId());
        op.setOperadorNombreCompleto(e.getOperadorNombreCompleto());
        op.setUbicacionId(e.getUbicacionId());
        op.setUbicacionNombre(e.getUbicacionNombre());
        op.setValorRetencion(e.getValorRetencion());
        op.setVistaId(e.getVistaId());
        return op;
    }
    
    
    // BRA Get informacion de las incidencias del operador por dia
    public Vector getIncideciasOperadorByDay(String cveOperador, String date) {
        Vector VIncidencias=new Vector();
        if(!abreSocketAS()) return null;
        VIncidencias = tmsIncidenciaFacade.getIncidenciabyOperador_Date(cveOperador,date);
        return VIncidencias;
    }
    
    // BRA Get informacion de las corridas del operador por dia
        public Vector getCorridasOperadorByDay(String cveOperador, String date) {
        Vector VIncidencias=new Vector();
        if(!abreSocketAS()) return null;
        VIncidencias = tmsIncidenciaFacade.getCorridasByOperador_Date(cveOperador,date);
        return VIncidencias;
    }

    public long getActAdicionalId() {
        return actAdicionalId;
    }
       
    // BRA Get All incidencias
      public Hashtable getAllkindIncidencia()
      {
          Hashtable htincidencias= new Hashtable();
          if(!abreSocketAS()) return null;
          Vector   VIncidencias = tmsIncidenciaFacade.getAllKindIncidencias();
          if(VIncidencias != null)
              for (int i = 0; i < VIncidencias.size(); i++) {
                   htincidencias.put(((Vector)VIncidencias.elementAt(i)).elementAt(0).toString().trim(),
                                     ((Vector)VIncidencias.elementAt(i)).elementAt(1).toString().trim());
              }
          return htincidencias;
          
      }
      
      public boolean AplicaRecauda_Inc(String strClaveInc)
      {
        return tmsIncidenciaFacade.getInc_Aplica_Recauda(strClaveInc);
      }
      
      public Hashtable getValoresMedGuardia()
      {
          Hashtable htValorMedGuardia= new Hashtable();
          if(!abreSocketAS()) return null;
          Vector   VIncidencias = tmsIncidenciaFacade.valorServicioMedGuardia();
          if(VIncidencias != null)
              for (int i = 0; i < VIncidencias.size(); i++) {
                   htValorMedGuardia.put(((Vector)VIncidencias.elementAt(i)).elementAt(0).toString().trim(),
                                     ((Vector)VIncidencias.elementAt(i)).elementAt(1).toString().trim());
              }
          return htValorMedGuardia;
          
      }
}