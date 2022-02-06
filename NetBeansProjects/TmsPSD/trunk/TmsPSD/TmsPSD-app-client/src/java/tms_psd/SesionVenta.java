package tms_psd;

import java.io.IOException;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.Color;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tms_encriptacion.EncriptarMD5;
import tms_psd.util.JDlgAceptar;
import tms_psd.util.PcInfo;
import tms_psd.util.TmsCorridasVentaV;
import xertms.entidad.TmsAccionesTbl;
import xertms.entidad.TmsAutobusPlantillasEncTbl;
import xertms.entidad.TmsAutobusesXTbl;
import xertms.entidad.TmsCorridasTbl;
import xertms.entidad.TmsCorridasVentaTbl;
import xertms.entidad.TmsEmpresasTbl;
import xertms.entidad.TmsEstadosCorridaTbl;
import xertms.entidad.TmsEstadosTbl;
import xertms.entidad.TmsFlotillasTbl;
import xertms.entidad.TmsMonitorCorridaV;
import xertms.entidad.TmsOpcionesSustAutobusV;
import xertms.entidad.TmsOperadoresTbl;
import xertms.entidad.TmsRutasTbl;
import xertms.entidad.TmsRutasV;
import xertms.entidad.TmsServiciosTbl;
import xertms.entidad.TmsTarjetasViajeTbl;
import xertms.solicitud.TmsPSDFacadeRemote;
import xertms.entidad.TmsBDConfigTbl;
import xertms.entidad.TmsEstadoAutobusesV;

public class SesionVenta {
    private String ipAS;
    private int portAS;
    private String loteCorridasId;
    private List<TmsOperadoresTbl> listadoOperadores;
    private String NuevoEstadoNombre;
    private long EnRolId;
    private long FueraRolId;
    private long EnRolOperadorId;
    private long FueraRolOperadorId;
    private String claveOperador;
    private String edoOper, ubiOper, actOper;
    private String msgActEdoOper="";
    private List<TmsEstadoAutobusesV> tmsEstadoAutobusesV = null;
    private String DBLink;
    private List<TmsBDConfigTbl> tmsBaseDatosConfigTbl;
    private Object[] OrigenesDBLink;
    private String dblCentral;
    private long usuarioId;
    private String numeroUsuario;
    private SimpleDateFormat formatoHora= new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat formatoFechaHora= new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private TmsPSDFacadeRemote sesionPSDFacate = null;
    private List<TmsMonitorCorridaV> tmsMonitorCorridaV = null;
    private List<TmsOpcionesSustAutobusV> tmsOpcionesSustAutobusV = null;
    private List<TmsEstadosCorridaTbl> tmsEstadosCorridaTbl = null;
    private Object[][] maestro = null;
    private Object[][] detalle = null;
    private Object[] terminal = null;
    private List<TmsEmpresasTbl> tmsEmpresasTbl = null;
    private List<TmsFlotillasTbl> tmsFlotillasTbl = null;
    private List<TmsAutobusPlantillasEncTbl> tmsPlantillasTbl = null;
    private List<TmsServiciosTbl> tmsServiciosTbl = null;
    private List<TmsEstadosTbl> tmsEstadosTbl = null;
    private List<TmsRutasV> tmsRutasV = null;
    private List<TmsRutasTbl> tmsRutasTbl = null;
    private List<TmsOperadoresTbl> tmsOperadoresTbl = null;
    
    private Vector vUbicacionAccion;
    private Vector vCausaAccion;
    private Vector vUbicacion;
    private Vector vCausa;
    private Vector vRutas;
    private Vector vRutasV;
    private Vector vOrigenes;
    private Vector vDestinos;
    private Vector vServicio;
    private Vector vEmpresa;
    public String empresaPrincipal="SE"; // NO CONFIGURADA
    // BUSQUEDA
    private Vector vServicios;
    private Vector vEmpresas;
    private Vector vPlantillas;
    // PARAMETROS GLOBALES E IMPRESORAS
    Vector codigos = null;
    Vector valores = null;

    private JDlgAceptar DialogoAceptar;

    private Vector operadoresSust;
    private Vector conjuntoCorridas;
    private String PrefijoTerminalId;
    
    private String esquemaPropio="";
    private String UbicacionTerminal="";
    private long UbicacionTerminalId;

    private Vector vDestinosTerminal;

    private PcInfo estaCaja;
    private Object[][] oSE=null;

    private Vector vectorCorridasSust;
    private List<TmsAccionesTbl> tmsAccionesTbl;
  
    public SesionVenta(long pUsuarioId, String pNumeroUsuario, String pIpAS, int pPortAS) { 
        this.usuarioId = pUsuarioId;
        this.numeroUsuario = pNumeroUsuario;
        this.setIpAS(pIpAS);
        this.setPortAS(pPortAS);
    }
    
    private TmsPSDFacadeRemote lookupTmsCorteTaqFacade() {
        try {
            if(!abreSocketAS()) return null;
            Context c = new InitialContext();
            return (TmsPSDFacadeRemote) c.lookup("xertms.solicitud.TmsPSDFacadeRemote");
        }
        catch(NamingException ne) {
            ne.printStackTrace();
            return null;
        }
    }
    
    public int procesoInicial(){
        sesionPSDFacate = lookupTmsCorteTaqFacade();
        if(getSesionPSDFacate() == null) return -1;
        //if(!FuncionesDeUsuario(MENU_ID)) return false;
        if(!Empresas()) return 2;
        if(!Servicios()) return 3;
        if(!Estados()) return 4;
        if(!Acciones()) return 13;
        if(!Terminal()) return 5;
        if(!ConfigBD()) return 16;
        if(!ligaCentral()) return 10;
        if(!Flotillas()) return 6;
        if(!Plantillas()) return 17;
        if(!EstadosCorrida()) return 7;
        if(!Rutas()) return 8;
        if(!RutasV(getEsquemaPropio())) return 9;
        if(!Operadores()) return 15;
        if(!ServiciosEmpresas()) return 18;
        this.FueraRolId = getEstadoId("FUERA DE ROL");
        this.EnRolId = getEstadoId("ENROLADO");
        this.FueraRolOperadorId = getAccionId("FUERA DE ROL");
        this.EnRolOperadorId = getAccionId("ENROLADO");
        return 0; // CARGA COMPLETA EXITOSA
    }
    
    private boolean Terminal(){
        if(!abreSocketAS()) return false;
        terminal = new Object[3];
        terminal = getSesionPSDFacate().obtenerDatosTerminalLocal();
        if(terminal==null) return false;
        if(terminal[0].toString().length()>3) PrefijoTerminalId  = terminal[0].toString().substring(0,3);
        if(terminal[0].toString().length()==3) PrefijoTerminalId = terminal[0].toString();
        if(terminal[0].toString().length()==2) PrefijoTerminalId = terminal[0].toString()+"0";
        if(terminal[0].toString().length()==1) PrefijoTerminalId = terminal[0].toString()+"00";
        esquemaPropio=terminal[2].toString();
        return true;
    }
    
    public String getLigaCentral(){ return this.dblCentral; }
    
    private boolean ligaCentral(){
        if(!abreSocketAS()) return false;
        dblCentral = getSesionPSDFacate().obtenerLigaCentral();
        if(dblCentral==null) return false;
        return true;
    }
    
    public boolean RutasV(String esquemaPropio){
        System.out.println("Antes del socket");
        if(!abreSocketAS()) return false;
        System.out.println("Esquema Propio: "+esquemaPropio);
        tmsRutasV = getSesionPSDFacate().queryTmsRutasV(esquemaPropio);
        if(tmsRutasV == null || tmsRutasV.size()==0) return false;
        return true;
    }
    
    private boolean Rutas(){
        if(!abreSocketAS()) return false;
        tmsRutasTbl = getSesionPSDFacate().queryTmsRutasTblAll();
        if(tmsRutasTbl == null || tmsRutasTbl.size()==0) return false;
        vRutas = new Vector();
        for(int i=0; i<tmsRutasTbl.size(); i++) vRutas.add(tmsRutasTbl.get(i).getRutaNumero());
        return true;
    }
    
    public Vector getRutasCambio(long origen, long destino, long servicio, String excluir ){
        Vector vRutasc = new Vector();
        for(int i=0; i<tmsRutasTbl.size(); i++){
            if(tmsRutasTbl.get(i).getTramoOrigenId() == origen && tmsRutasTbl.get(i).getTramoDestinoId() == destino && tmsRutasTbl.get(i).getServicioId() == servicio && (!(tmsRutasTbl.get(i).getRutaNumero().toString()+"-"+tmsRutasTbl.get(i).getRutaNombre().toString()).equals(excluir))  )
                vRutasc.add(tmsRutasTbl.get(i).getRutaNumero().toString()+"-"+tmsRutasTbl.get(i).getRutaNombre().toString());
        } 
        return vRutasc;
    }    
    
    public String getRutaNombreCambio(long ruta_id ){
        Vector vRutasc = new Vector();
        for(int i=0; i<tmsRutasTbl.size(); i++){
            if(tmsRutasTbl.get(i).getRutaId() == ruta_id)
                return tmsRutasTbl.get(i).getRutaNumero() +"-"+tmsRutasTbl.get(i).getRutaNombre();
                
        } 
        return "";
    }    
    
    
    private boolean Operadores(){
        if(!abreSocketAS()) return false;
        tmsOperadoresTbl = getSesionPSDFacate().queryOperadores();
        if(tmsOperadoresTbl == null || tmsOperadoresTbl.size()==0) return false;
        return true;
    }
    
    private boolean ServiciosEmpresas(){
        if(!abreSocketAS()) return false;
        Vector vSEx=new Vector();
        vSEx = getSesionPSDFacate().ServicioEmpresa();
        if(vSEx == null) return false;
        Vector y = null;
        oSE = new Object[vSEx.size()][3];
        for(int i=0; i<vSEx.size(); i++){
            y=new Vector();
            y=(Vector) vSEx.get(i);
            oSE[i][0]=y.get(0).toString();
            oSE[i][1]=y.get(1).toString();
            oSE[i][2]=y.get(2).toString();
        }
        return true;
    }
    
    public TmsOperadoresTbl getOperador(long operadorId){
        for(int i=0; i<tmsOperadoresTbl.size(); i++){
            if(tmsOperadoresTbl.get(i).getOperadorId()==operadorId) return tmsOperadoresTbl.get(i);
        }
        return null;
    }
    
    public String getNombreOperador(long operadorId){
        for(int i=0; i<tmsOperadoresTbl.size(); i++){
            if(tmsOperadoresTbl.get(i).getOperadorId()==operadorId) return tmsOperadoresTbl.get(i).getOperadorNombreCompleto();
        }
        return "";
    }
    
    public String getClaveOperador(long operadorId){
        for(int i=0; i<tmsOperadoresTbl.size(); i++){
            if(tmsOperadoresTbl.get(i).getOperadorId()==operadorId) return tmsOperadoresTbl.get(i).getClaveOperador();
        }
        return "";
    }

    public List<TmsOperadoresTbl> getOperadores(){ return tmsOperadoresTbl; }
    
    private boolean Empresas(){
        if(!abreSocketAS()) return false;
        tmsEmpresasTbl = getSesionPSDFacate().queryTmsEmpresasTblAll();
        if(tmsEmpresasTbl == null || tmsEmpresasTbl.size()==0) return false;
        vEmpresas = new Vector();
        vEmpresa = new Vector();
        vEmpresas.add("TODOS");
        for(int i=0; i<tmsEmpresasTbl.size(); i++){
            vEmpresa.add(tmsEmpresasTbl.get(i).getEmpresaNombre());
            vEmpresas.add(tmsEmpresasTbl.get(i).getEmpresaNombre());
        }
        return true;
    }
    
    private boolean Flotillas(){
        if(!abreSocketAS()) return false;
        tmsFlotillasTbl = getSesionPSDFacate().queryTmsFlotillasTblAll();
        if(tmsFlotillasTbl == null || tmsFlotillasTbl.size()==0) return false;
        return true;
    }
    
    private boolean Plantillas(){
        if(!abreSocketAS()) return false;
        tmsPlantillasTbl = getSesionPSDFacate().queryTmsAutobusPlantillasEncTblFindAll();
        if(tmsPlantillasTbl == null || tmsPlantillasTbl.size()==0) return false;
        vPlantillas = new Vector();
        for(int i=0; i<tmsPlantillasTbl.size(); i++)
            vPlantillas.add(tmsPlantillasTbl.get(i).getNombreCorto());
        return true;
    }
    
    public long getPlantillaId(String nombrePlantilla){
        for(int i=0; i<tmsPlantillasTbl.size(); i++)
            if(tmsPlantillasTbl.get(i).getNombreCorto().equals(nombrePlantilla))
                return tmsPlantillasTbl.get(i).getPlantillaEncId();
        
        return -1;
    }
    
    public String getPlantilla(long plantillaId){
        for(int i=0; i<tmsPlantillasTbl.size(); i++)
            if(tmsPlantillasTbl.get(i).getPlantillaEncId() == plantillaId)
                return tmsPlantillasTbl.get(i).getNombreCorto();
        return "";
    }
    
    public long getPlantillaCapacidad(long plantillaId){
        for(int i=0; i<tmsPlantillasTbl.size(); i++)
            if(tmsPlantillasTbl.get(i).getPlantillaEncId() == plantillaId)
                return tmsPlantillasTbl.get(i).getCapacidadAsientos();
        return 0;
    }
    
    private boolean EstadosCorrida(){
        if(!abreSocketAS()) return false;
        tmsEstadosCorridaTbl = getSesionPSDFacate().queryTmsEstadosCorridaTblAll();
        if(tmsEstadosCorridaTbl == null || tmsEstadosCorridaTbl.size()==0) return false;
        return true;
    }
           
    private boolean Servicios(){
        if(!abreSocketAS()) return false;
        tmsServiciosTbl = getSesionPSDFacate().queryTmsServiciosTblAll();
        if(tmsServiciosTbl == null || tmsServiciosTbl.size()==0) return false;
        vServicios = new Vector();
        vServicios.add("TODOS");
        vServicio = new Vector();
        for(int i=0; i<tmsServiciosTbl.size(); i++){
            vServicio.add(tmsServiciosTbl.get(i).getServicioNombre());
            vServicios.add(tmsServiciosTbl.get(i).getServicioNombre());
        }
        return true;
    }
    
    private boolean Estados(){
        if(!abreSocketAS()) return false;
        tmsEstadosTbl = getSesionPSDFacate().queryTmsEstadosTblAll();
        if(tmsEstadosTbl == null || tmsEstadosTbl.size()==0) return false;
        vUbicacion = new Vector();
        vCausa = new Vector();
        vDestinosTerminal = new Vector();
        vDestinosTerminal.add("TODOS");
        for(int i=0; i<tmsEstadosTbl.size(); i++){
            if(tmsEstadosTbl.get(i).getTipoComponente().equals("CIUDAD") || tmsEstadosTbl.get(i).getTipoComponente().equals("TERMINAL")){
                vUbicacion.add(tmsEstadosTbl.get(i).getEstadoNombre());
                if(tmsEstadosTbl.get(i).getTipoComponente().equals("TERMINAL")) vDestinosTerminal.add(tmsEstadosTbl.get(i).getEstadoNombre());
            }
            if(tmsEstadosTbl.get(i).getTipoComponente().equals("ACTIVIDAD")){
                vCausa.add(tmsEstadosTbl.get(i).getEstadoNombre());
            }
        }
        return true;
    }
    
    private boolean Acciones(){
        if(!abreSocketAS()) return false;
        tmsAccionesTbl = getSesionPSDFacate().queryTmsAccionesTblAll();
        if(tmsAccionesTbl == null || tmsAccionesTbl.size()==0) return false;
        vUbicacionAccion = new Vector();
        vCausaAccion = new Vector();
        for(int i=0; i<tmsAccionesTbl.size(); i++){
            if(tmsAccionesTbl.get(i).getComponente()==1){
                vUbicacionAccion.add(tmsAccionesTbl.get(i).getAccion());
            }
            if(tmsAccionesTbl.get(i).getComponente()==2){
                vCausaAccion.add(tmsAccionesTbl.get(i).getAccion());
            }
        }
        return true;
    }
    
    public int procesoSecundario(){
        if(getParametrosIniciales2()!=0) return -5;
        estaCaja = new PcInfo();
        empresaPrincipal=getSesionPSDFacate().getEp(estaCaja.getHostName().toUpperCase());
        if(empresaPrincipal==null) empresaPrincipal="SE";
        System.out.println("Empresa Principal: "+empresaPrincipal);
        return 0;
    }
    
    public int getParametrosIniciales2(){
        if(!abreSocketAS()) return 13;
        String cadena = getSesionPSDFacate().getParametrosIniciales2();
        if(cadena==null) return 13;
        codigos = new Vector();
        valores = new Vector();
        String valor;
        StringTokenizer strToken = new StringTokenizer(cadena,"|");
        StringTokenizer strTokenCampos;
        int i,j=strToken.countTokens();
        for(i=0; i<j; i++){
            valor=strToken.nextToken();
            strTokenCampos = new StringTokenizer(valor,",");
            codigos.add(strTokenCampos.nextToken());
            valores.add(strTokenCampos.nextToken());
        }
        
        return 0; // carga completa y exitosa
    }
    
    public int busqueda(String pServicio, String pEmpresa, String pAutobus, String pOperador, boolean TipoCorrida,
            String Fecha, String Hora, String Fecha2, String Hora2, String pDestino){
        if(pServicio.equals("TODOS")) pServicio = "";
        if(pEmpresa.equals("TODOS")) pEmpresa = "";
        if(pDestino.equals("TODOS")) pDestino = "";
        String fechaHoraDesde = Fecha + " " + Hora;
        String fechaHoraHasta = Fecha2 + " " + Hora2;
        if(fechaHoraDesde.equals(" ")) fechaHoraDesde="";
        if(fechaHoraHasta.equals(" ")) fechaHoraHasta=fechaHoraDesde;
        else{
            if(Hora2.equals("")){
                Hora2 = "23:59";
                fechaHoraHasta = Fecha2 + " " + Hora2;
            }
        }
        
        if(!abreSocketAS()) return -21;
        tardaraProceso(Fecha, Fecha2);
        tmsMonitorCorridaV = getSesionPSDFacate().BusquedaMonitorCorridas(pServicio, pEmpresa,
                pAutobus, pOperador, (TipoCorrida ? "E" : ""), fechaHoraDesde, fechaHoraHasta,
                getDBLink(), pDestino);
        
        if(tmsMonitorCorridaV==null) return 0;
        
        maestro = new Object[tmsMonitorCorridaV.size()][11];
        detalle = new Object[tmsMonitorCorridaV.size()][9];
        
        String horario="";
        for(int i=0; i<tmsMonitorCorridaV.size(); i++){
            maestro[i][0] = tmsMonitorCorridaV.get(i).getClaveCorrida();
            maestro[i][1] = tmsMonitorCorridaV.get(i).getTipoCorrida();
            maestro[i][2] = tmsMonitorCorridaV.get(i).getEstadoCorrida();
            maestro[i][3] = tmsMonitorCorridaV.get(i).getServicio();
            maestro[i][4] = tmsMonitorCorridaV.get(i).getEmpresa();
            maestro[i][5] = formatoFecha.format(tmsMonitorCorridaV.get(i).getFecha());
            maestro[i][6] = tmsMonitorCorridaV.get(i).getOrigen();
            maestro[i][7] = tmsMonitorCorridaV.get(i).getDestino();
            maestro[i][8] = formatoHora.format(tmsMonitorCorridaV.get(i).getHora());
            maestro[i][9] = tmsMonitorCorridaV.get(i).getAutobus();
            maestro[i][10] = tmsMonitorCorridaV.get(i).getOperador();
            detalle[i][0] = tmsMonitorCorridaV.get(i).getAutobusOriginal();
            detalle[i][1] = tmsMonitorCorridaV.get(i).getAutobus();
            detalle[i][2] = tmsMonitorCorridaV.get(i).getOperadorOriginal();
            detalle[i][3] = tmsMonitorCorridaV.get(i).getNombreOperadorOriginal();
            detalle[i][4] = tmsMonitorCorridaV.get(i).getOperador();
            detalle[i][5] = tmsMonitorCorridaV.get(i).getNombreOperadorSust();
            detalle[i][6] = tmsMonitorCorridaV.get(i).getCorridaCancelada();
            detalle[i][7] = tmsMonitorCorridaV.get(i).getEstadoTarjetaViaje();
            detalle[i][8] = tmsMonitorCorridaV.get(i).getFolioTarjetaViaje();
        }
        return 1;
    }
    
    public int busquedaSustitucion(String pServicio, String pEmpresa, String pAutobus, String pOperador, boolean TipoCorrida,
            String Fecha, String Hora, String Fecha2, String Hora2, String pDestino){
        if(pServicio.equals("TODOS")) pServicio = "";
        if(pEmpresa.equals("TODOS")) pEmpresa = "";
        if(pDestino.equals("TODOS")) pDestino = "";
        String fechaHoraDesde = Fecha + " " + Hora;
        String fechaHoraHasta = Fecha + " " + Hora2;
        if(fechaHoraDesde.equals(" ")) fechaHoraDesde="";
        if(fechaHoraHasta.equals(" ")) fechaHoraHasta=fechaHoraDesde;
        else{
            if(Hora.equals("")){
                Hora = "00:00";
                fechaHoraDesde = Fecha + " " + Hora;
            }
            if(Hora2.equals("")){
                Hora2 = "23:59";
                fechaHoraHasta = Fecha + " " + Hora2;
            }
        }
        
        if(!abreSocketAS()) return -21;
        tardaraProceso(Fecha, Fecha);
        System.out.println("entra a buscar las corridas...");
        this.setVectorCorridasSust((Vector) getSesionPSDFacate().BusquedaMonitorCorridasSust(pServicio, pEmpresa,
                pAutobus, pOperador, "", fechaHoraDesde, fechaHoraHasta,
                getDBLink(), pDestino));
        System.out.println("devuelve las corridas: "+this.getVectorCorridasSust());
        return 1;
    }
    
    
    public int busquedaSustitucion2(String pServicio, String pEmpresa, String pAutobus, String pOperador, boolean TipoCorrida,
            String Fecha, String Hora, String Fecha2, String Hora2, String pDestino){
        if(pServicio.equals("TODOS")) pServicio = "";
        if(pEmpresa.equals("TODOS")) pEmpresa = "";
        if(pDestino.equals("TODOS")) pDestino = "";
        String fechaHoraDesde = Fecha + " " + Hora;
        String fechaHoraHasta = Fecha + " " + Hora2;
        if(fechaHoraDesde.equals(" ")) fechaHoraDesde="";
        if(fechaHoraHasta.equals(" ")) fechaHoraHasta=fechaHoraDesde;
        else{
            if(Hora.equals("")){
                Hora = "00:00";
                fechaHoraDesde = Fecha + " " + Hora;
            }
            if(Hora2.equals("")){
                Hora2 = "23:59";
                fechaHoraHasta = Fecha + " " + Hora2;
            }
        }
        
        if(!abreSocketAS()) return -21;
        tardaraProceso(Fecha, Fecha);
        System.out.println("entra a buscar las corridas...");
        this.setVectorCorridasSust((Vector) getSesionPSDFacate().BusquedaMonitorCorridasSust2(pServicio, pEmpresa,
                pAutobus, pOperador, "", fechaHoraDesde, fechaHoraHasta,
                getDBLink(), pDestino));
        System.out.println("devuelve las corridas: "+this.getVectorCorridasSust());
        return 1;
    }
    
    public Object[][] calcSust(String flotilla){
        long flotillaId;
        flotillaId=this.getFlotillaId(flotilla);
        if(!abreSocketAS()) return null;
        tmsOpcionesSustAutobusV = getSesionPSDFacate().buscarOpcionesSustAutobus(flotillaId, UbicacionTerminalId, this.DBLink);
        if(tmsOpcionesSustAutobusV==null) return null;
        //System.out.println("SIZE1 "+tmsOpcionesSustAutobusV.size());
        eliminaDuplicados();
        //System.out.println("SIZE2 "+tmsOpcionesSustAutobusV.size());
        Object[][] busSust = new Object[tmsOpcionesSustAutobusV.size()][4];
        
        for(int i=0; i<tmsOpcionesSustAutobusV.size(); i++){
            busSust[i][0] = tmsOpcionesSustAutobusV.get(i).getNumeroEconomico();
            busSust[i][1] = tmsOpcionesSustAutobusV.get(i).getNombreFlotilla();
            busSust[i][2] = tmsOpcionesSustAutobusV.get(i).getActividad();
            busSust[i][3] = tmsOpcionesSustAutobusV.get(i).getUbicacion();
        }
        
        return busSust;
    }
    
    // transacciones
    public boolean cancelaCorrida(int fila){
        if(!abreSocketAS()) return false;
        loteCorridasId = tmsMonitorCorridaV.get(fila).getCorridaId().toString();
        return getSesionPSDFacate().modificaEstadoCorrida(loteCorridasId,
                getEstadoCorridaId("CANCELADA"), getEstadoCorridaLetra("CANCELADA"), (long)-1, "",this.getEsquemaPropio(), getDBLink());
    }
    
    // ***
    public boolean cancelaCorrida(long CorridaId){
        if(!abreSocketAS()) return false;
        loteCorridasId = String.valueOf(CorridaId);
        return getSesionPSDFacate().modificaEstadoCorrida(loteCorridasId,
                getEstadoCorridaId("CANCELADA"), getEstadoCorridaLetra("CANCELADA"), (long)-1, "",this.getEsquemaPropio(), getDBLink());
    }
    
    public int tarjViajeEstaRecaudada(long CorridaId){
        if(!abreSocketAS()) return -1;
        return getSesionPSDFacate().tarjViajeEstaRecaudada(CorridaId, getLigaCentral());
    }
    
    public String estadoTarjetaViaje(long CorridaId){
        if(!abreSocketAS()) return null;
        return getSesionPSDFacate().estadoTarjetaViaje(CorridaId, getLigaCentral());
    }
    
    public boolean abreCorrida(long CorridaId){
        if(!abreSocketAS()) return false;
        loteCorridasId = String.valueOf(CorridaId);
        return getSesionPSDFacate().modificaEstadoCorrida(loteCorridasId,
                getEstadoCorridaId("ABIERTA"), getEstadoCorridaLetra("ABIERTA"), (long)-1, "",this.getEsquemaPropio(), getDBLink());
    }
    
    public boolean cierraCorrida(long CorridaId){
        if(!abreSocketAS()) return false;
        loteCorridasId = String.valueOf(CorridaId);
        return getSesionPSDFacate().modificaEstadoCorrida(loteCorridasId,
                getEstadoCorridaId("CERRADA"), getEstadoCorridaLetra("CERRADA"), (long)-1, "",this.getEsquemaPropio(), getDBLink());
    }
    
    public boolean borraCorrida(long CorridaId){
        /*if(!abreSocketAS()) return false;
        return sesionPSDFacate.borraCorrida(String.valueOf(CorridaId), getDBLink());*/
        return getSesionPSDFacate().modificaEstadoCorrida(String.valueOf(CorridaId),
                getEstadoCorridaId("BORRADA"), getEstadoCorridaLetra("BORRADA"), (long)-1, "",this.getEsquemaPropio(), getDBLink());
    }
    
    public boolean borraCorridaCentral(long CorridaId){
        if(!abreSocketAS()) return false;
        return getSesionPSDFacate().borraCorridaCentral(String.valueOf(CorridaId), getLigaCentral());
    }
    // ***
    
    public boolean abreCorridaNuevoHorario(long CorridaId, Timestamp nuevoHorario, String strHora){
        if(!abreSocketAS()) return false;
        strHora = strHora.replace(":","");
        return getSesionPSDFacate().modificaCorrida(CorridaId,
                getEstadoCorridaId("ABIERTA"), getEstadoCorridaLetra("ABIERTA"), nuevoHorario, strHora, this.getEsquemaPropio(), getDBLink());
    }
    
    public int sustituyeAutobus(long corridaId, long autobusOrigId, String numeroEconomicoOrig, 
                                long autobusSustId, String numeroEconomicoSust, Timestamp tmFecha,
                                int opcionSust, long plantillaId, long empresaId, String empresa){
        if(!abreSocketAS()) return -1;
        int[] bandera=null;
        int r=-1;
        switch(opcionSust){
            case 0: bandera = getSesionPSDFacate().SustituyeAutobusUnaCorrida(corridaId, autobusSustId,
                                    numeroEconomicoSust, autobusOrigId,
                                    numeroEconomicoOrig, formatoFechaHora.format(tmFecha), plantillaId, empresaId, empresa, this.getEsquemaPropio(), this.DBLink); break;
            case 1: bandera = getSesionPSDFacate().SustituyeAutobusTodoElDia(corridaId, autobusSustId,
                                    numeroEconomicoSust, autobusOrigId,
                                    numeroEconomicoOrig, formatoFechaHora.format(tmFecha), plantillaId, empresaId, empresa, this.getEsquemaPropio(), this.DBLink); break;
            case 2: bandera = getSesionPSDFacate().SustituyeAutobusTodoElRol(corridaId, autobusSustId,
                                    numeroEconomicoSust, autobusOrigId,
                                    numeroEconomicoOrig, formatoFechaHora.format(tmFecha), plantillaId, empresaId, empresa, this.getEsquemaPropio(), this.DBLink); break;
        }
        if(bandera!=null){
            if(bandera[0]==-1) return -69;
            if(bandera[0]>bandera[1]) r=bandera[1];
            else r=bandera[0];
        }
        return r;
    }
    
    public int sustituyeAutobusNull(long corridaId,
                                    long autobusSustId, String numeroEconomicoSust, Timestamp tmFecha, long plantillaId, long empresaId, String empresa){
        if(!abreSocketAS()) return -1;
        int[] bandera=null;
        int r=-1;
        
        bandera = getSesionPSDFacate().SustituyeAutobusUnaCorridaNull(corridaId, autobusSustId, numeroEconomicoSust, formatoFechaHora.format(tmFecha), plantillaId, empresaId, empresa, this.getEsquemaPropio(), this.DBLink);
        if(bandera!=null){
            if(bandera[0]==-1) return -69;
            if(bandera[0]>bandera[1]) r=bandera[1];
            else r=bandera[0];
        }
        return r;
    }
    
    public boolean registraFueraRolAutobusOrig(long autobusOrigId, String ubicacion, String causa){
        if(!abreSocketAS()) return false;
        return getSesionPSDFacate().registraFueraRolAutobusOrig(autobusOrigId, getEstadoId("FUERA DE ROL"),getEstadoId(ubicacion), getEstadoId(causa), getEsquemaPropio(), this.DBLink);
    }
    
    public boolean registraEnRolAutobusSust(long autobusSustId){
        if(!abreSocketAS()) return false;
        return getSesionPSDFacate().registraEnRolAutobusSust(autobusSustId, getEstadoId("ENROLADO"),UbicacionTerminalId, getEstadoId("ESTANCIA"), getEsquemaPropio(), this.DBLink);
    }
    
    public Object[][] calcSustOper(String operOrigId){
        if(!abreSocketAS()) return null;
        operadoresSust = new Vector();
        operadoresSust = (Vector) getSesionPSDFacate().buscarOpcionesSustOperador(operOrigId, UbicacionTerminal, this.DBLink);
        
        if(operadoresSust==null || operadoresSust.size()==0) return null;
        eliminaDuplicadosOperador();
        Object[][] busSust = new Object[operadoresSust.size()][3];
        Vector os;
        for(int i=0; i<operadoresSust.size(); i++){
            os = new Vector();
            os = (Vector) operadoresSust.get(i);
            busSust[i][0] = os.get(1);
            busSust[i][1] = os.get(2);
            busSust[i][2] = os.get(3);
        }
        
        return busSust;
    }
    
    public int sustituyeOperador(long corridaId, long operOrigId, String operOrig, 
                                    long operSustId, String operSust, Timestamp tmFecha, int opcionSust){
        if(!abreSocketAS()) return -1;
        int[] bandera=null;
        int r=-1;
        switch(opcionSust){
            case 0: bandera = getSesionPSDFacate().SustituyeOperadorUnaCorrida(corridaId, operSustId,
                                    operSust, operOrigId,
                                    operOrig, formatoFechaHora.format(tmFecha), this.getEsquemaPropio(), this.DBLink); break;
            case 1: bandera = getSesionPSDFacate().SustituyeOperadorTodoElDia(corridaId, operSustId,
                                    operSust, operOrigId,
                                    operOrig, formatoFechaHora.format(tmFecha), this.getEsquemaPropio(), this.DBLink); break;
            case 2: bandera = getSesionPSDFacate().SustituyeOperadorTodoElRol(corridaId, operSustId,
                                    operSust, operOrigId,
                                    operOrig, formatoFechaHora.format(tmFecha), this.getEsquemaPropio(), this.DBLink); break;
        }
        if(bandera!=null){
            if(bandera[0]==-1) return -69;
            if(bandera[0]>bandera[1]) r=bandera[1];
            else r=bandera[0];
        }
        return r;
    }
    
    public int sustituyeOperadorNull(long corridaId, long operSustId, String operSust, Timestamp tmFecha){
        if(!abreSocketAS()) return -1;
        int[] bandera=null;
        int r=-1;
        bandera = getSesionPSDFacate().SustituyeOperadorUnaCorridaNull(corridaId, operSustId,
                                    operSust, formatoFechaHora.format(tmFecha), this.getEsquemaPropio(), this.DBLink);
        if(bandera!=null){
            if(bandera[0]==-1) return -69;
            if(bandera[0]>bandera[1]) r=bandera[1];
            else r=bandera[0];
        }
        return r;
    }
    
    public boolean registraFueraRolOperadorOrig(long operOrigId, String ubicacion, String causa){
        if(!abreSocketAS()) return false;
        if(causa.equals("ACCIDENTE"))
            return getSesionPSDFacate().registraFueraRolOperadorOrig(operOrigId, getAccionId("FUERA DE ROL"),getAccionId(ubicacion), getAccionId(causa), getEsquemaPropio(), this.DBLink);
        return getSesionPSDFacate().registraFueraRolOperadorOrig(operOrigId, getAccionId("ENROLADO"),getAccionId(ubicacion), getAccionId(causa), getEsquemaPropio(), this.DBLink);
    }
    
    public boolean registraEnRolOperadorSust(long operSustId){
        if(!abreSocketAS()) return false;
        return getSesionPSDFacate().registraEnRolOperadorSust(operSustId, getAccionId("ENROLADO"),getAccionId(UbicacionTerminal), getAccionId("ESTANCIA"), getEsquemaPropio(), this.DBLink);
    }
    //
    
    public long getEmpresaId(String nombreEmpresa){
        for(int i=0; i<tmsEmpresasTbl.size(); i++)
            if(tmsEmpresasTbl.get(i).getEmpresaNombre().equals(nombreEmpresa)) return tmsEmpresasTbl.get(i).getEmpresaId();
        return -1;
    }
    
    public String getEmpresa(long empresaId){
        for(int i=0; i<tmsEmpresasTbl.size(); i++)
            if(tmsEmpresasTbl.get(i).getEmpresaId() == empresaId) return tmsEmpresasTbl.get(i).getEmpresaNombre();
        return "";
    }
    
    public long getEstadoCorridaId(String nombreCorrida){
        for(int i=0; i<tmsEstadosCorridaTbl.size(); i++)
            if(tmsEstadosCorridaTbl.get(i).getNombreEstado().equals(nombreCorrida)) return tmsEstadosCorridaTbl.get(i).getEstadoCorridaId();
        return -1;
    }
    
    public String getEstadoCorridaLetra(String nombreCorrida){
        for(int i=0; i<tmsEstadosCorridaTbl.size(); i++)
            if(tmsEstadosCorridaTbl.get(i).getNombreEstado().equals(nombreCorrida)) return tmsEstadosCorridaTbl.get(i).getNombreCortoEstado();
        return "X";
    }
    
    public String getEstadoCorrida(String letraCorrida){
        for(int i=0; i<tmsEstadosCorridaTbl.size(); i++)
            if(tmsEstadosCorridaTbl.get(i).getNombreCortoEstado().equals(letraCorrida)) return tmsEstadosCorridaTbl.get(i).getNombreEstado();
        return "X";
    }
    
    public long getFlotillaId(String nombreFlotilla){
        for(int i=0; i<tmsFlotillasTbl.size(); i++)
            if(tmsFlotillasTbl.get(i).getNombreFlotilla().equals(nombreFlotilla)) return tmsFlotillasTbl.get(i).getFlotillaId();
        return -1;
    }
    
    public String getFlotilla(long FlotillaId){
        for(int i=0; i<tmsFlotillasTbl.size(); i++)
            if(tmsFlotillasTbl.get(i).getFlotillaId()==FlotillaId) return tmsFlotillasTbl.get(i).getNombreFlotilla();
        return "";
    }
    
    public long getServicioId(String nombreServicio){
        for(int i=0; i<tmsServiciosTbl.size(); i++)
            if(tmsServiciosTbl.get(i).getServicioNombre().equals(nombreServicio)) return tmsServiciosTbl.get(i).getServicioId();
        return -1;
    }
    
    public String getServicioLetra(String nombreServicio){
        for(int i=0; i<tmsServiciosTbl.size(); i++)
            if(tmsServiciosTbl.get(i).getServicioNombre().equals(nombreServicio)) return tmsServiciosTbl.get(i).getServicioClave();
        return "";
    }
    
    public long getEstadoId(String estado){
        for(int i=0; i<tmsEstadosTbl.size(); i++)
            if(tmsEstadosTbl.get(i).getEstadoNombre().equals(estado)) return tmsEstadosTbl.get(i).getEstadoId();
        return -1;
    }
    
    public String getEstadoNombre(long estadoId){
        for(int i=0; i<tmsEstadosTbl.size(); i++)
            if(tmsEstadosTbl.get(i).getEstadoId()==estadoId) return tmsEstadosTbl.get(i).getEstadoNombre();
        return "";
    }
    
    public long getAccionId(String estado){
        for(int i=0; i<tmsAccionesTbl.size(); i++)
            if(tmsAccionesTbl.get(i).getAccion().equals(estado)) return tmsAccionesTbl.get(i).getAccionId();
        return -1;
    }
    
    public String getAccion(long estadoId){
        for(int i=0; i<tmsAccionesTbl.size(); i++)
            if(tmsAccionesTbl.get(i).getAccionId() == estadoId) return tmsAccionesTbl.get(i).getAccion();
        return "";
    }
    
    public String[] estadoAutobusActualSiguiente(String autobus){
        if(!abreSocketAS()) return null;
        String[] resultado = null;
        resultado = getSesionPSDFacate().estadoAutobusActualSiguiente(autobus, this.DBLink);
        if(resultado == null) return null;
        String[] edoBus = new String[2];
        if(resultado[4].contains("-"))
            edoBus[0] = resultado[4];
        else
            edoBus[0] = "Estado: "+resultado[2]+" - Ubicacion: "+resultado[3]+" - Actividad: "+resultado[4];
        if(resultado[5].contains("-"))
            edoBus[1] = resultado[5];
        else
            edoBus[1] = "Estado: "+resultado[2]+" - Ubicacion: "+resultado[3]+" - Actividad: "+resultado[5];
        return edoBus;
    }
    
    public String[] estadoOperadorActualSiguiente(String operador){
        if(!abreSocketAS()) return null;
        String[] resultado = null;
        resultado = getSesionPSDFacate().estadoOperadorActualSiguiente(operador, this.DBLink);
        if(resultado == null) return null;
        String[] edoOper = new String[2];
        if(resultado[4].contains("-"))
            edoOper[0] = resultado[4];
        else
            edoOper[0] = "Estado: "+resultado[2]+" - Area Laboral: "+resultado[3]+" - Actividad: "+resultado[4];
        if(resultado[5].contains("-"))
            edoOper[1] = resultado[5];
        else
            edoOper[1] = "Estado: "+resultado[2]+" - area Laboral: "+resultado[3]+" - Actividad: "+resultado[5];
        return edoOper;
    }
    // TRANSACCIONES
    public Object[][] getDetalle(){ return detalle; }
    
    public Object[][] getMaestro(){ return maestro; }
    
    public List<TmsMonitorCorridaV> getTmsMonitorCorridaV(){ return tmsMonitorCorridaV; }
    
    public List<TmsOpcionesSustAutobusV> getTmsOpcionesSustAutobusV(){ return tmsOpcionesSustAutobusV; }
    
    public Vector getTmsOpcionesSustOperador(){ return operadoresSust; }
    
    public List<TmsEstadosCorridaTbl> getTmsEstadosCorridaTbl(){ return tmsEstadosCorridaTbl; }
    
    public Vector getVectorEmpresas(){ return vEmpresas; }
    
    public Vector getVectorEmpresa(){ return vEmpresa; }
    
    public Vector getVectorPlantillas(){ return vPlantillas; }
    
    public Vector getVectorRutas(){ return vRutas; }

    public Vector getRutasV(){ return vRutasV; }
    
    public Vector getVectorOrigenes(){ return vOrigenes; }
    
    public Vector getVectorDestinos(){ return vDestinos; }
    
    public Vector getVectorServicios(){ return vServicios; }
    
    public Vector getVectorServicio(){ return vServicio; }
    
    public Vector getVectorUbicacion(){ return vUbicacion; }
    
    public Vector getVectorCausa(){ return vCausa; }
    
    public Vector getVectorUbicacionAccion(){ return vUbicacionAccion; }
    
    public Vector getVectorCausaAccion(){ return vCausaAccion; }
    
    public String getValorParametro(String ParamComponente, int NivelError){
        int indice = 0;
        indice = codigos.indexOf(ParamComponente);
        if(indice<0){
            if(NivelError==-1) return "0";
            DialogoAceptar = new JDlgAceptar("¡Parametro "+ParamComponente+" incorrecto!", "Contacte al administrador del sistema.", Color.RED);
            //procesoNivelError(NivelError);
            System.exit(0);
            return null;
        }
        else return valores.get(indice).toString();
    }
    
    // corridas extras
    public String[] guardarCorridaExtra(List<TmsCorridasVentaV> o){
        String[] s;
        if(!abreSocketAS()){
            s = new String[2];
            s[0] = "-2"; s[1] = "-2";
            return s;
        }
        TmsCorridasTbl corrida = null;
        TmsCorridasVentaTbl corridaVenta = null;
        long corridaId;
        String claveCorrida;
        Vector FechaBD = (Vector) getSesionPSDFacate().fechaServidorX();
        if(FechaBD==null){
            s = new String[2];
            s[0] = "-2"; s[1] = "-2";
            return s;
        }
        Timestamp fecha = null;
        fecha=fecha.valueOf(FechaBD.get(0).toString());
        for(int i=0; i<o.size(); i++){
            corrida = new TmsCorridasTbl();
            corridaVenta = new TmsCorridasVentaTbl();
            corrida.setClaveCorrida("EXTRA");
            corrida.setServicioId(o.get(i).getServicioId());
            corrida.setEmpresaId(o.get(i).getEmpresaId());
            corrida.setRutaId(o.get(i).getRutaId());
            corrida.setFechaHoraCorrida(o.get(i).getFechaHoraCorrida());
            corrida.setOrigenId(o.get(i).getOrigenId());
            corrida.setDestinoId(o.get(i).getDestinoId());
            corrida.setAutobusId(o.get(i).getAutobusId());
            corrida.setAutobusOriginalId(o.get(i).getAutobusOriginalId());
            corrida.setOperadorId(o.get(i).getOperadorId());
            corrida.setOperadorOriginalId(o.get(i).getOperadorOriginalId());
            corrida.setTipoCorrida(o.get(i).getTipoCorrida());
            corrida.setEstadoCorridaId(o.get(i).getEstadoCorridaId());
            corrida.setPlantillaId(o.get(i).getPlantillaId());
            corrida.setCorridaRelacionadaId(o.get(i).getCorridaRelacionadaId());
            corrida.setSueldoOperador(o.get(i).getSueldoOperador());
            corrida.setNumeroContrato(o.get(i).getNumeroContrato());
            corrida.setNumeroOrden(o.get(i).getNumeroOrden());
            corrida.setMontoAnticipos(o.get(i).getMontoAnticipos());
            corrida.setCreadoPor(this.getUsuarioId());
            corrida.setFechaCreacion(fecha);
            corrida.setUltimaActualizacionPor(this.getUsuarioId());
            corrida.setUltimaFechaActualizacion(fecha);
            corrida.setAdicional4("PSD");
            // corrida extra
            //System.out.println("UbicacionTerminal "+UbicacionTerminal);
            s = getSesionPSDFacate().registrarCorridaExtra(getPrefijoTerminalId(), corrida, 
                    (UbicacionTerminal.toString().length()>3 ? UbicacionTerminal.toString().substring(0,4) : UbicacionTerminal.toString().substring(0,3)+"_"),
                    getServicioLetra(o.get(i).getServicio()), 
                    formatoHora.format(o.get(i).getHoraCorrida()).replaceAll(":",""), this.getEsquemaPropio(),                     claveOrigenCorrida(o.get(i).getOrigen()), this.DBLink);
            if(s == null){
                s = new String[2];
                s[0] = "0"; s[1] = "0";
                return s;
            }
            if(s[0].equals("-1")) return s;
            corridaId = Long.valueOf(s[0]);
            claveCorrida = s[1];
            //
            //corridaId = Long.valueOf(PrefijoTerminalId+corridaId);
            corridaVenta.setCorridaId(corridaId);
            corridaVenta.setClaveCorrida(claveCorrida);
            corridaVenta.setServicio(o.get(i).getServicio());
            corridaVenta.setEmpresa(o.get(i).getEmpresa());
            corridaVenta.setFechaHoraCorrida(o.get(i).getFechaHoraCorrida());
            corridaVenta.setOrigen(o.get(i).getOrigen());
            corridaVenta.setDestino(o.get(i).getDestino());
            corridaVenta.setAutobus(o.get(i).getAutobus());
            corridaVenta.setAutobusOriginal(o.get(i).getAutobusOriginal());
            corridaVenta.setOperador(o.get(i).getOperador());
            corridaVenta.setOperadorAdicional(o.get(i).getOperadorOriginal());
            corridaVenta.setOperadorOriginal(o.get(i).getOperadorOriginal());
            corridaVenta.setTipoCorrida(o.get(i).getTipoCorrida());
            //System.out.println("o.get(i).getTipoCorrida() "+o.get(i).getTipoCorrida());
            corridaVenta.setEstadoCorrida(o.get(i).getEstadoCorrida());
            corridaVenta.setPlantillaId(o.get(i).getPlantillaId());
            corridaVenta.setCorridaRelacionadaId(o.get(i).getCorridaRelacionadaId());
            corridaVenta.setMenoresCorrida(o.get(i).getMenoresCorrida());
            corridaVenta.setEstudiantesCorrida(o.get(i).getEstudiantesCorrida());
            corridaVenta.setProfesoresCorrida(o.get(i).getProfesoresCorrida());
            corridaVenta.setSenectudCorrida(o.get(i).getSenectudCorrida());
            corridaVenta.setCortesiasCorrida(o.get(i).getCortesiasCorrida());
            corridaVenta.setAdicional2(""+o.get(i).getSenectudCorrida()+"-"+o.get(i).getProfesoresCorrida()+"-"+o.get(i).getEstudiantesCorrida()+"-"+o.get(i).getCortesiasCorrida());
            corridaVenta.setAsiento1("D"); corridaVenta.setAsiento2("D"); corridaVenta.setAsiento3("D"); corridaVenta.setAsiento4("D"); corridaVenta.setAsiento5("D");
            corridaVenta.setAsiento6("D"); corridaVenta.setAsiento7("D"); corridaVenta.setAsiento8("D"); corridaVenta.setAsiento9("D"); corridaVenta.setAsiento10("D");
            corridaVenta.setAsiento11("D"); corridaVenta.setAsiento12("D"); corridaVenta.setAsiento13("D"); corridaVenta.setAsiento14("D"); corridaVenta.setAsiento15("D");
            corridaVenta.setAsiento16("D"); corridaVenta.setAsiento17("D"); corridaVenta.setAsiento18("D"); corridaVenta.setAsiento19("D"); corridaVenta.setAsiento20("D");
            corridaVenta.setAsiento21("D"); corridaVenta.setAsiento22("D"); corridaVenta.setAsiento23("D"); corridaVenta.setAsiento24("D"); corridaVenta.setAsiento25("D");
            corridaVenta.setAsiento26("D"); corridaVenta.setAsiento27("D"); corridaVenta.setAsiento28("D"); corridaVenta.setAsiento29("D"); corridaVenta.setAsiento30("D");
            corridaVenta.setAsiento31("D"); corridaVenta.setAsiento32("D"); corridaVenta.setAsiento33("D"); corridaVenta.setAsiento34("D"); corridaVenta.setAsiento35("D");
            corridaVenta.setAsiento36("D"); corridaVenta.setAsiento37("D"); corridaVenta.setAsiento38("D"); corridaVenta.setAsiento39("D"); corridaVenta.setAsiento40("D");
            corridaVenta.setAsiento41("D"); corridaVenta.setAsiento42("D"); corridaVenta.setAsiento43("D"); corridaVenta.setAsiento44("D"); corridaVenta.setAsiento45("D");
            corridaVenta.setAsiento46("D"); corridaVenta.setAsiento47("D"); corridaVenta.setAsiento48("D"); corridaVenta.setAsiento49("D"); corridaVenta.setAsiento50("D");
            corridaVenta.setAsiento51("D"); corridaVenta.setAsiento52("D"); corridaVenta.setAsiento53("D"); corridaVenta.setAsiento54("D"); corridaVenta.setAsiento55("D");
            corridaVenta.setAsiento56("D"); corridaVenta.setAsiento57("D"); corridaVenta.setAsiento58("D"); corridaVenta.setAsiento59("D"); corridaVenta.setAsiento60("D");
            corridaVenta.setAsiento61("D"); corridaVenta.setAsiento62("D"); corridaVenta.setAsiento63("D"); corridaVenta.setAsiento64("D"); corridaVenta.setAsiento65("D");
            corridaVenta.setAsiento66("D"); corridaVenta.setAsiento67("D"); corridaVenta.setAsiento68("D"); corridaVenta.setAsiento69("D"); corridaVenta.setAsiento70("D");
            corridaVenta.setAsiento71("D"); corridaVenta.setAsiento72("D"); corridaVenta.setAsiento73("D"); corridaVenta.setAsiento74("D"); corridaVenta.setAsiento75("D");
            corridaVenta.setAsiento76("D"); corridaVenta.setAsiento77("D"); corridaVenta.setAsiento78("D"); corridaVenta.setAsiento79("D"); corridaVenta.setAsiento80("D");
            corridaVenta.setAsiento81("D"); corridaVenta.setAsiento82("D"); corridaVenta.setAsiento83("D"); corridaVenta.setAsiento84("D"); corridaVenta.setAsiento85("D");
            corridaVenta.setAdicional4("PSD");
            // guardar corrida extra
            if(!getSesionPSDFacate().registrarCorridaExtraVenta(corridaVenta, this.getEsquemaPropio(), this.DBLink)){
                s = new String[2];
                s[0] = "0"; s[1] = "0";
                return s;
            }
        }
        s = new String[2];
        s[0] = "1"; s[1] = "1";
        return s;
    }
    
    public String claveOrigenCorrida(String origen){
        if(origen.length()>4) return origen.substring(0,4);
        if(origen.length()==4) return origen;
        if(origen.length()==3) return origen+"_";
        if(origen.length()==2) return origen+"__";
        if(origen.length()==1) return origen+"___";
        return origen;
    }
    
    public String cargaNombreRuta(String ruta){
        for(int i=0; i<tmsRutasV.size(); i++)
            if(ruta.equals(tmsRutasV.get(i).getRutaNumero())) return tmsRutasV.get(i).getRutaNombre();
        return "";
    }
    
    public long getRutaId(String ruta){
        for(int i=0; i<tmsRutasV.size(); i++)
            if(ruta.equals(tmsRutasV.get(i).getRutaNumero())) return tmsRutasV.get(i).getRutaId();
        return -1;
    }
    
      public long getRutaCambioId(String ruta){
           for(int i=0; i<tmsRutasTbl.size(); i++){
            if((tmsRutasTbl.get(i).getRutaNumero().toString()+"-"+tmsRutasTbl.get(i).getRutaNombre().toString()).equals(ruta) )
                return tmsRutasTbl.get(i).getRutaId();
            }

        return -1;
    }
      
    public void cargaRelacionServicioRutas(String servicio){
        vRutasV = new Vector();
        int i, j; boolean igual=false;
        for(i=0; i<tmsRutasV.size(); i++)
            if(tmsRutasV.get(i).getServicio().equals(servicio)){
                igual = false;
                for(j=0; j<vRutasV.size(); j++){
                    if(vRutasV.get(j).toString().equals(tmsRutasV.get(i).getRutaNumero())){
                        igual= true;
                        break;
                    }
                }
                if(!igual) vRutasV.addElement(tmsRutasV.get(i).getRutaNumero());
            }
    }
    
    public void cargaRelacionRutaOrigenesDestinos(String ruta){
        vOrigenes = new Vector();
        vDestinos = new Vector();
        int i, j; boolean igual=false;
        for(i=0; i<tmsRutasV.size(); i++)
            if(tmsRutasV.get(i).getRutaNumero().equals(ruta)){
                igual = false;
                for(j=0; j<vOrigenes.size(); j++){
                    if(vOrigenes.get(j).toString().equals(tmsRutasV.get(i).getOrigen())){
                        igual= true;
                        break;
                    }
                }
                if(!igual) vOrigenes.addElement(tmsRutasV.get(i).getOrigen());
                igual = false;
                for(j=0; j<vDestinos.size(); j++){
                    if(vDestinos.get(j).toString().equals(tmsRutasV.get(i).getDestino())){
                        igual = true;
                        break;
                    }
                }
                if(!igual) vDestinos.addElement(tmsRutasV.get(i).getDestino());
            }
    }
    
    public int corridasAProcesarVenta(String Servicio, String Fecha1, String Fecha2){
        if(!abreSocketAS()) return -1;
        if(Servicio.equals("TODOS")) Servicio="";
        else Servicio = ""+getServicioId(Servicio);
        return getSesionPSDFacate().corridasAProcesarVenta(Servicio, Fecha1, Fecha2, this.getEsquemaPropio(), getDBLink());
    }
    
    public int corridasAProcesarNormal(){
        if(!abreSocketAS()) return -1;
        return getSesionPSDFacate().corridasAProcesarNormal(this.getEsquemaPropio(), getDBLink());
    }
    
    public Object[][] corridasAProcesar(){
        if(!abreSocketAS()) return null;
        conjuntoCorridas = new Vector();
        conjuntoCorridas = (Vector) getSesionPSDFacate().corridasAProcesar(getDBLink());
        if(conjuntoCorridas == null) return null;
        Object[][] x = new Object[conjuntoCorridas.size()][9];
        Vector y = new Vector();
        for(int i=0; i<conjuntoCorridas.size(); i++){
            y = (Vector) conjuntoCorridas.get(i);
            x[i][0] = y.get(1);
            x[i][1] = y.get(2);
            x[i][2] = y.get(3);
            x[i][3] = y.get(4);
            x[i][4] = y.get(5);
            x[i][5] = y.get(6);
            x[i][6] = y.get(7);
            x[i][7] = formatoHora.format(y.get(8));
            x[i][8] = y.get(0);
        }
        return x;
    }
    
    public int liberarCorridas(){
        if(!abreSocketAS()) return -1;
        return getSesionPSDFacate().liberarCorridas(this.getEsquemaPropio(), getDBLink());
    }
    
    public int liberarCorridasVenta(){
        if(!abreSocketAS()) return -1;
        return getSesionPSDFacate().liberarCorridasVenta(this.getEsquemaPropio(), getDBLink());
    }
    
    public Vector getConjuntoCorridas(){ return this.conjuntoCorridas; }
    
    public int getOcupacion(int filaSel){
        if(!abreSocketAS()) return -1;
        return getSesionPSDFacate().ocupacionCorrida(this.getTmsMonitorCorridaV().get(filaSel).getCorridaId(), this.DBLink);
    }
    
    public int getOcupacion(long corridaId){
        if(!abreSocketAS()) return -1;
        return getSesionPSDFacate().ocupacionCorrida(corridaId, this.DBLink);
    }

    public int getCapacidadAsientos(int filaSel){
        if(!abreSocketAS()) return -1;
        return getSesionPSDFacate().capacidadAsientos(this.getTmsMonitorCorridaV().get(filaSel).getCorridaId(), this.DBLink);
    }
    
    public int getCapacidadAsientosAutobus(String numeroEco){
        if(!abreSocketAS()) return -1;
        return getSesionPSDFacate().capacidadAsientosAutobus(numeroEco, this.DBLink);
    }
    
    public int CorridasEnProceso(int filaA, int filaB) {
        if(!abreSocketAS()) return 1;
        loteCorridasId = this.getTmsMonitorCorridaV().get(filaA).getCorridaId().toString();
        if(!getSesionPSDFacate().modificaEstadoCorrida(loteCorridasId,
                getEstadoCorridaId("PROCESO"), getEstadoCorridaLetra("PROCESO"), (long)-1, "",this.getEsquemaPropio(), getDBLink())) return 1;
        loteCorridasId = this.getTmsMonitorCorridaV().get(filaB).getCorridaId().toString();
        if(!getSesionPSDFacate().modificaEstadoCorrida(loteCorridasId,
                getEstadoCorridaId("PROCESO"), getEstadoCorridaLetra("PROCESO"), (long)-1, "",this.getEsquemaPropio(), getDBLink())) return 2;
        return 0;
    }

    public int TransfiereBoletosAB(int filaA, int filaB) {
        if(!abreSocketAS()) return 1;
        if(getSesionPSDFacate().TransfiereBoletosABporClave(this.getTmsMonitorCorridaV().get(filaA).getClaveCorrida(),
                                                        this.getTmsMonitorCorridaV().get(filaB).getClaveCorrida())==-1) return 1;
        if(getSesionPSDFacate().TransfiereBoletosABporId(this.getTmsMonitorCorridaV().get(filaA).getCorridaId(),
                                                        this.getTmsMonitorCorridaV().get(filaB).getCorridaId())==-1) return 2;
        return 0;
    }

    public int EstadoCorridaAgrupada(int filaA, int filaB) { 
        if(!abreSocketAS()) return 1;
        loteCorridasId=this.getTmsMonitorCorridaV().get(filaA).getCorridaId().toString();
        if(!getSesionPSDFacate().modificaEstadoCorrida(loteCorridasId,
                getEstadoCorridaId("AGRUPADA"), getEstadoCorridaLetra("AGRUPADA"), this.getTmsMonitorCorridaV().get(filaB).getCorridaId(), this.getTmsMonitorCorridaV().get(filaB).getClaveCorrida(),this.getEsquemaPropio(), getDBLink())) return 1;
        loteCorridasId = this.getTmsMonitorCorridaV().get(filaB).getCorridaId().toString();
        if(!getSesionPSDFacate().modificaEstadoCorrida(loteCorridasId,
                getEstadoCorridaId("ABIERTA"), getEstadoCorridaLetra("ABIERTA"), (long)-1, "",this.getEsquemaPropio(), getDBLink())) return 2;
        return 0;
    }
    
    public long[] obtienePlantilla(String rutaNumero) {
        if(!abreSocketAS()) return null;
        return getSesionPSDFacate().obtienePlantilla(rutaNumero);
    }
    
    public long obtieneAutobusPlantilla(String numEco) {
        if(!abreSocketAS()) return -5;
        return getSesionPSDFacate().obtieneAutobusPlantilla(numEco, this.DBLink);
    }
    
    public long obtienePlantillaCorrida(long corridaId) {
        if(!abreSocketAS()) return -1;
        return getSesionPSDFacate().obtienePlantillaCorrida(corridaId, this.DBLink);
    }
    
    public TmsTarjetasViajeTbl buscaTarjetaPorFolio(String folio, BigInteger edo){
        if(!abreSocketAS()) return null;
        return getSesionPSDFacate().buscaTarjetaPorFolio(folio, edo);
    }
    public Object buscaCorrida(long idCor){
        if(!abreSocketAS()) return null;
        return getSesionPSDFacate().buscaCorrida(idCor);
    }
    
    public Object buscaEstadoTajeta(String  edo){
        if(!abreSocketAS()) return null;
        return getSesionPSDFacate().buscaEstadoTajeta(edo);
    }   
     
    public Object queryBuscaNombreEsquema(){
        if(!abreSocketAS()) return null;
        return getSesionPSDFacate().queryBuscaNombreEsquema();
    }  
   
    public void edit(TmsTarjetasViajeTbl tmsTarjetasViajeTbl) {
        getSesionPSDFacate().edit(tmsTarjetasViajeTbl);
    }
    
    public List<TmsAutobusesXTbl> queryAutobusesByNumEco(String numEco){
        if(!abreSocketAS()) return null;
        List<TmsAutobusesXTbl> x=getSesionPSDFacate().queryTmsAutobusesXTblByNumEco(numEco, this.DBLink);
        return x;
    }  
    
    public List<TmsOperadoresTbl> queryOperadoresByClave(String clave){
        if(!abreSocketAS()) return null;
        return getSesionPSDFacate().queryTmsOperadoresTblByClave(clave, this.DBLink);
    }  
    
    public boolean asignaAutobus(long corridaId, long autobusSustId, String numeroEconomicoSust, long plantillaId,
            long empresaId, String empresa){
        if(!abreSocketAS()) return false;
        return getSesionPSDFacate().AsignaAutobusACorrida(corridaId, autobusSustId, numeroEconomicoSust, plantillaId,
this.getUsuarioId(), getEsquemaPropio(), this.DBLink, empresaId, empresa);
    }
    
    public boolean asignaOperador(long corridaId, long operSustId, String claveOper){
        if(!abreSocketAS()) return false;
        return getSesionPSDFacate().AsignaOperadorACorrida(corridaId, operSustId, claveOper, this.getUsuarioId(), this.getEsquemaPropio(), this.DBLink);
    }
    
    public boolean validarClaveSupervisorConFuncion(String funcionNumero, String usuarioNumero, String usuarioPwd) {
        if(!abreSocketAS()) return false;
        EncriptarMD5 pwdEnc = new EncriptarMD5();
        long valido = -1;
        try {
            valido = getSesionPSDFacate().esUsuarioSupervisorConFuncion(funcionNumero, usuarioNumero, pwdEnc.encriptar(usuarioPwd));
            if(valido==-1) return false;
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public boolean validarClaveUsuarioConFuncion(String funcionNumero) {
        if(!abreSocketAS()) return false;
        long valido = -1;
        try {
            valido = getSesionPSDFacate().UsuarioConFuncion(funcionNumero, numeroUsuario);
            if(valido==-1) return false;
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public String consultarEstadoCorrida(String claveCorrida) {
        String r = getSesionPSDFacate().estadoCorrida(claveCorrida, this.DBLink);
        if(r==null){
            System.out.println("No existe corrida "+claveCorrida+" en corridaVenta");
            r="";
        }
        return r;
    }
    
    public int maxAsientoOcupado(String claveCorrida){
        if(!abreSocketAS()) return -1;
        return getSesionPSDFacate().MaxAsientoOcupado(claveCorrida, this.DBLink);
    }
    
    public boolean validaHoraModificada(Timestamp hora, long servicioId, long rutaId, long empresaId){
        if(!abreSocketAS()) return false;
        return getSesionPSDFacate().validaHoraModificada(hora, servicioId, rutaId, empresaId, getDBLink());
    }

    public Object[] getOrigenesDBLink() {
        return OrigenesDBLink;
    }
    
    public Vector getDestinos() {
        return vDestinosTerminal;
    }
    
    private boolean ConfigBD(){
        if(!abreSocketAS()) return false;
        tmsBaseDatosConfigTbl = new ArrayList<TmsBDConfigTbl>(getSesionPSDFacate().queryTmsBaseDatosConfigTblAll());
        if(tmsBaseDatosConfigTbl==null || tmsBaseDatosConfigTbl.size()==0) return false;
        int contador=0, i;
        
        for(i=0; i<tmsBaseDatosConfigTbl.size(); i++)
            if(!tmsBaseDatosConfigTbl.get(i).getNombreTerminal().equals("CENTRAL")) contador++;
        
        OrigenesDBLink=new Object[contador];
        
        contador=0;
        
        for(i=0; i<tmsBaseDatosConfigTbl.size(); i++){
            if(!tmsBaseDatosConfigTbl.get(i).getNombreTerminal().equals("CENTRAL")){
                OrigenesDBLink[contador] =tmsBaseDatosConfigTbl.get(i).getNombreTerminal();
                contador++;
            }
        }
        return true;
    }
    
    public String getTerminalNombre(){
        return terminal[1].toString();
    }
    
    public String getDBLink(){ return this.DBLink; }
    
    public String getDBLinkClaveOrigen(String Terminal){
        for(int i=0; i<tmsBaseDatosConfigTbl.size(); i++)
            if(Terminal.equals(tmsBaseDatosConfigTbl.get(i).getNombreTerminal())){
                this.DBLink=tmsBaseDatosConfigTbl.get(i).getNombreDblink();
                if(this.DBLink==null){
                    this.DBLink="";
                    if(terminal[0].toString().length()>3) PrefijoTerminalId  = terminal[0].toString().substring(0,3);
                    if(terminal[0].toString().length()==3) PrefijoTerminalId = terminal[0].toString();
                    if(terminal[0].toString().length()==2) PrefijoTerminalId = terminal[0].toString()+"0";
                    if(terminal[0].toString().length()==1) PrefijoTerminalId = terminal[0].toString()+"00";
                    esquemaPropio=terminal[2].toString();
                    UbicacionTerminal=terminal[1].toString();
                    UbicacionTerminalId=Long.valueOf(terminal[0].toString());
                    //System.out.println("error::: TERMINAL "+Terminal+" - DBLINK "+(this.DBLink.equals("")?"LOCAL":this.DBLink));
                    return null;
                }
                if(Terminal.equals(getTerminalNombre())){
                    this.DBLink=""; 
                    if(terminal[0].toString().length()>3) PrefijoTerminalId  = terminal[0].toString().substring(0,3);
                    if(terminal[0].toString().length()==3) PrefijoTerminalId = terminal[0].toString();
                    if(terminal[0].toString().length()==2) PrefijoTerminalId = terminal[0].toString()+"0";
                    if(terminal[0].toString().length()==1) PrefijoTerminalId = terminal[0].toString()+"00";
                    esquemaPropio=terminal[2].toString();
                    UbicacionTerminal=terminal[1].toString();
                    UbicacionTerminalId=Long.valueOf(terminal[0].toString());
                    //System.out.println("TERMINAL "+Terminal+" - DBLINK "+(this.DBLink.equals("")?"LOCAL":this.DBLink));
                    return this.DBLink;
                }
                String terminalIdRemoto=String.valueOf(tmsBaseDatosConfigTbl.get(i).getTerminalId());
                if(terminalIdRemoto.length()>3) PrefijoTerminalId=terminalIdRemoto.substring(0, 3);
                if(terminalIdRemoto.length()==3) PrefijoTerminalId=terminalIdRemoto;
                if(terminalIdRemoto.length()==2) PrefijoTerminalId=terminalIdRemoto+"0";
                if(terminalIdRemoto.length()==1) PrefijoTerminalId=terminalIdRemoto+"00";
                esquemaPropio=tmsBaseDatosConfigTbl.get(i).getNombreEsquema();
                UbicacionTerminal=tmsBaseDatosConfigTbl.get(i).getNombreTerminal();
                UbicacionTerminalId=tmsBaseDatosConfigTbl.get(i).getTerminalId();
                //System.out.println("TERMINAL "+Terminal+" - DBLINK "+(this.DBLink.equals("")?"LOCAL":this.DBLink));
                return this.DBLink;
            }
        return "";
    }

    private void eliminaDuplicados() {
        String nE1="", nE2="";
        int i, j;
        for(i=0; i<tmsOpcionesSustAutobusV.size(); i++){
            nE1 = tmsOpcionesSustAutobusV.get(i).getNumeroEconomico();
            for(j=i+1; j<tmsOpcionesSustAutobusV.size(); j++){
                nE2=tmsOpcionesSustAutobusV.get(j).getNumeroEconomico();
                if(nE1.equals(nE2)){
                    tmsOpcionesSustAutobusV.remove(i);
                    i--;
                    break;
                }
            }
        }
    }
    
    private void eliminaDuplicadosOperador() {
        String nE1="", nE2="";
        int i, j;
        Vector y = null;
        Vector z = null;
        for(i=0; i<operadoresSust.size(); i++){
            y = (Vector) operadoresSust.get(i);
            nE1 = y.get(0).toString();
            for(j=i+1; j<operadoresSust.size(); j++){
                z = (Vector) operadoresSust.get(j);
                nE2=z.get(0).toString();
                if(nE1.equals(nE2)){
                    operadoresSust.remove(i);
                    i--;
                    break;
                }
            }
        }
    }
    
    /***********/
    public String getUbicacionTerminal(){ return this.UbicacionTerminal; }
    
    public Vector getCausas(){ return this.vCausa; }
    
    public Vector getUbicaciones(){ return this.vUbicacion; }
    
    public String getNuevoEstado(){ return this.NuevoEstadoNombre; }
    
    public TmsOperadoresTbl getOperadores(int fila){ return this.listadoOperadores.get(fila); }
    
    public TmsEstadoAutobusesV getAutobuses(int fila){ return this.tmsEstadoAutobusesV.get(fila); }
    
    public String getMsgActEdoOper(){ return this.msgActEdoOper; }
    
    public Object[][] busquedaAutobus(String NumeroEconomico){
        if(!abreSocketAS()) return null;
        tmsEstadoAutobusesV = getSesionPSDFacate().queryTmsEstadoAutobusesVByNumeroEconomico(NumeroEconomico);
        if(tmsEstadoAutobusesV==null || tmsEstadoAutobusesV.size()==0) return null;
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
        if(!abreSocketAS()) return false;
        claveOperador = getSesionPSDFacate().claveOperador(tmsEstadoAutobusesV.get(filaGuardar).getNumeroEconomico());
        boolean resultado;
        if(claveOperador!=null)
            if(getSesionPSDFacate().actualizaEstadoOperador(claveOperador, edoOper, ubiOper, actOper, this.terminal[2].toString())){
                long operadorId=getSesionPSDFacate().obtenerOperadorId(claveOperador);
                resultado=getSesionPSDFacate().ejecutaReplicacion("TMS_OPERADORES_TBL", String.valueOf(operadorId),
                                            "XERTMS", this.terminal[2].toString(),
                                            "DIFUSION");
                msgActEdoOper="<br>El operador <font color=ff0000>"+claveOperador+"</font> esta "+edoOper+".";
            }
        if(!abreSocketAS()) return false;
        resultado=getSesionPSDFacate().registrarAccesoUbicacion(tmsEstadoAutobusesV.get(filaGuardar).getAutobusId(),
                                                               NuevoEstadoId,
                                                               getEstadoId(Ubi),
                                                               getEstadoId(Act),
this.getUsuarioId(), fechaAct, this.terminal[2].toString());
        if(!resultado) return false;
        resultado=getSesionPSDFacate().ejecutaReplicacion("TMS_AUTOBUSES_TBL", String.valueOf(tmsEstadoAutobusesV.get(filaGuardar).getAutobusId()),
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
    }
    
    //*****
    public Object[][] listaOperadores(String clv){
        if(!abreSocketAS()) return null;
        listadoOperadores = getSesionPSDFacate().queryTmsOperadoresTblByClave(clv, "");
        if(listadoOperadores==null || listadoOperadores.size()==0)
        {
           return null;
        }
        Object[][] objetos = new Object[listadoOperadores.size()][2];
        for(int i=0; i<listadoOperadores.size(); i++)
        {
            objetos[i][0] = listadoOperadores.get(i).getClaveOperador();
            objetos[i][1] = listadoOperadores.get(i).getOperadorNombreCompleto();
        }
        return objetos;
    }
    
    public boolean registraEstadoOperador(int filaGuardar, String Ubi, String Act){
        Timestamp fechaAct = new Timestamp(new Date().getTime());
        msgActEdoOper="";
        claveOperador = listadoOperadores.get(filaGuardar).getClaveOperador();
        long NuevoEstadoId=analizarEstadoOperador(Ubi, Act);
        boolean resultado = false;
        if(!abreSocketAS()) return false;
        if(getSesionPSDFacate().actualizaEstadoOperador(claveOperador, edoOper, ubiOper, actOper, this.terminal[2].toString())){
            long operadorId=getSesionPSDFacate().obtenerOperadorId(claveOperador);
            resultado=getSesionPSDFacate().ejecutaReplicacion("TMS_OPERADORES_TBL", String.valueOf(operadorId),
                                        "XERTMS", this.terminal[2].toString(),
                                        "DIFUSION");
        }
        return resultado;
    }
    
    private long analizarEstadoOperador(String Ubi, String Act) {
        if(!Act.equals("GUARDIA") && !Act.equals("ESTANCIA") && !Act.equals("CORRIDA")){
            this.NuevoEstadoNombre = "Fuera de rol";
            this.edoOper = "FUERA DE ROL";
            this.ubiOper = "NO LABORAL";
            this.actOper = "GUARDIA";
            return this.FueraRolOperadorId;
        }
        this.NuevoEstadoNombre = "Enrolado";
        this.edoOper = "ENROLADO";
        this.ubiOper = Ubi;
        this.actOper = Act;
        return this.EnRolOperadorId;
    };
    
    public int actualizacionesVarias_Plantillas(long corridaId, long plantillaId){
        int[] bandera=null;
        int r=-1;
        if(!abreSocketAS()) return -1;
        bandera = getSesionPSDFacate().actualizacionesVarias_Plantilla(String.valueOf(corridaId), plantillaId, this.getEsquemaPropio(), this.DBLink);
        if(bandera!=null){
            if(bandera[0]>bandera[1]) r=bandera[1];
            else r=bandera[0];
        }
        return r;
    }
    
    public int actualizacionesVarias_Empresas(long corridaId, long empresaId, String empresa){
        int[] bandera=null;
        int r=-1;
        if(!abreSocketAS()) return -1;
        bandera = getSesionPSDFacate().actualizacionesVarias_Empresa(String.valueOf(corridaId), empresaId, empresa, this.getEsquemaPropio(), this.DBLink);
        if(bandera!=null){
            if(bandera[0]>bandera[1]) r=bandera[1];
            else r=bandera[0];
        }
        return r;
    }
    
     public int actualizacionesVarias_Rutas(long corridaId, long rutaId){
        int r=-1;
        if(!abreSocketAS()) return -1;
        r  = getSesionPSDFacate().actualizacionesVarias_Ruta(String.valueOf(corridaId), rutaId,this.getEsquemaPropio(), this.DBLink);
        return r;
    }
    
    public int actualizacionesVarias_LimitesPasaje(long corridaId, String limites,
                String s, String e, String p, String c){
        int[] bandera=null;
        int r=-1;
        if(!abreSocketAS()) return -1;
        bandera = getSesionPSDFacate().actualizacionesVarias_LimitesPasaje(String.valueOf(corridaId), limites, this.getEsquemaPropio(), this.DBLink,
                s, e, p, c);
        if(bandera!=null){
            if(bandera[0]>bandera[1]) r=bandera[1];
            else r=bandera[0];
        }
        return r;
    }
    
    public String flotillaDeAutobus(String numEco){
        if(!abreSocketAS()) return "";
        return getSesionPSDFacate().getFlotillaDeAutobus(numEco, this.DBLink);
    }

    public String obtieneLimitesPasajeCorrida(Long aLong) {
        if(!abreSocketAS()) return "";
        return getSesionPSDFacate().obtieneLimitesPasajeCorrida(aLong, this.DBLink);
    }
    
    // transaccion por lotes
    public boolean cierraCorridaPorLotes(String pLoteCorridasIds){
        //if(!abreSocketAS()) return false;
        return getSesionPSDFacate().modificaEstadoCorrida(pLoteCorridasIds,
                getEstadoCorridaId("CERRADA"), getEstadoCorridaLetra("CERRADA"), (long)-1, "",this.getEsquemaPropio(), getDBLink());
    }
    
    public boolean abreCorridaPorLotes(String pLoteCorridasIds){
        //if(!abreSocketAS()) return false;
        return getSesionPSDFacate().modificaEstadoCorrida(pLoteCorridasIds,
                getEstadoCorridaId("ABIERTA"), getEstadoCorridaLetra("ABIERTA"), (long)-1, "",this.getEsquemaPropio(), getDBLink());
    }
    
    public boolean borraCorridaPorLotes(String pLoteCorridasIds){
        //if(!abreSocketAS()) return false;
        //return sesionPSDFacate.borraCorrida(pLoteCorridasIds, getDBLink());
        return getSesionPSDFacate().modificaEstadoCorrida(pLoteCorridasIds,
                getEstadoCorridaId("BORRADA"), getEstadoCorridaLetra("BORRADA"), (long)-1, "",this.getEsquemaPropio(), getDBLink());
    }
    
    public boolean borraCorridaPorLotesCentral(String pLoteCorridasIds){
        //if(!abreSocketAS()) return false;
        return getSesionPSDFacate().borraCorridaCentral(pLoteCorridasIds, getLigaCentral());
    }
    
    public int actualizacionesVariasPorLotes_Plantillas(String pLoteCorridasIds, long plantillaId){
        int[] bandera=null;
        int r=-1;
        //if(!abreSocketAS()) return -1;
        bandera = getSesionPSDFacate().actualizacionesVarias_Plantilla(pLoteCorridasIds, plantillaId, this.getEsquemaPropio(), this.DBLink);
        if(bandera!=null){
            if(bandera[0]>bandera[1]) r=bandera[1];
            else r=bandera[0];
        }
        return r;
    }
    
    public int actualizacionesVariasPorLotes_Empresas(String pLoteCorridasIds, long empresaId, String empresa){
        int[] bandera=null;
        int r=-1;
        //if(!abreSocketAS()) return -1;
        bandera = getSesionPSDFacate().actualizacionesVarias_Empresa(pLoteCorridasIds, empresaId, empresa, this.getEsquemaPropio(), this.DBLink);
        if(bandera!=null){
            if(bandera[0]>bandera[1]) r=bandera[1];
            else r=bandera[0];
        }
        return r;
    }
    
     public int actualizacionesVariasPorLotes_Rutas(String pLoteCorridasIds, long rutaId){
        int r=-1;
        if(!abreSocketAS()) return -1;
        r  = getSesionPSDFacate().actualizacionesVarias_Ruta(pLoteCorridasIds, rutaId,this.getEsquemaPropio(), this.DBLink);
        return r;
    }
    
    public int actualizacionesVariasPorLotes_LimitesPasaje(String pLoteCorridasIds, String limites,
            String s, String e, String p, String c){
        int[] bandera=null;
        int r=-1;
        //if(!abreSocketAS()) return -1;
        bandera = getSesionPSDFacate().actualizacionesVarias_LimitesPasaje(pLoteCorridasIds, limites, this.getEsquemaPropio(), this.DBLink,
                s, e, p, c);
        if(bandera!=null){
            if(bandera[0]>bandera[1]) r=bandera[1];
            else r=bandera[0];
        }
        return r;
    }
    
    private void setIpAS(String valor) {
        this.ipAS = valor;
    }
    
    private void setPortAS(int valor) {
        this.portAS = valor;
    }
    
    private String getIpAS() {
        return this.ipAS;
    }
    
    private int getPortAS() {
        return this.portAS;
    }
    
    public boolean abreSocketAS(){
        try {
            Socket s = null;
            s = new Socket(getIpAS(), getPortAS());
            return true;
        }catch( IOException e ) {
            e.printStackTrace();
            return false;
        }catch(Exception err){
            err.printStackTrace();
            return false;
        }
    } 
    
    public int esCorridaDePaso(String claveCorrida){
        return getSesionPSDFacate().esCorridaDePaso(claveCorrida, this.DBLink);
    }
    /*
    public boolean ServicioEmpresaId(long servicioId, long empresaId){
        return sesionPSDFacate.ServicioEmpresaId(servicioId, empresaId, this.DBLink);
    }
    
    public boolean ServicioEmpresa(String servicio, String empresa){
        return sesionPSDFacate.ServicioEmpresa(servicio, empresa, this.DBLink);
    }
    */
    public boolean validaServicioEmpresa(String Servicio, String Ruta, String Empresa){
        Long rutaId=getRutaId(Ruta);
        return validaServicioEmpresa(Servicio, rutaId, Empresa);
    }
    
    public boolean validaServicioEmpresa(String Servicio, long RutaId, String Empresa){
        System.out.println("validaServicioRutaEmpresa "+Servicio+" "+RutaId+" "+Empresa);
        for(int i=0; i<oSE.length; i++)
            if(oSE[i][0].toString().equals(Servicio) && Long.valueOf(oSE[i][1].toString())==RutaId && oSE[i][2].toString().equals(Empresa)) return true;
        return false;
    }

    private void tardaraProceso(String fecha1, String fecha2) {
        if(fecha1.equals("") || fecha2.equals("")) return;
        Date d1=null, d2=null;
        try {
            d1 = formatoFecha.parse(fecha1);
            d2 = formatoFecha.parse(fecha2);
            
            if((d2.getTime()-d1.getTime())>3600000)
                DialogoAceptar = new JDlgAceptar("Aviso.", "La consulta puede tardar unos segundos.", Color.RED);
        } catch (ParseException ex) {
            ;
        }
    }

    public Vector getVectorCorridasSust() {
        return vectorCorridasSust;
    }

    public void setVectorCorridasSust(Vector vectorCorridasSust) {
        this.vectorCorridasSust = vectorCorridasSust;
    }

    public TmsPSDFacadeRemote getSesionPSDFacate() {
        return sesionPSDFacate;
    }

    public String getPrefijoTerminalId() {
        return PrefijoTerminalId;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public String getEsquemaPropio() {
        return esquemaPropio;
    }
}