package tms_ofertasservicios;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;
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
import tms_ofertasservicios.util.JDlgAceptar;
import xertms.entidad.TmsEmpresasTbl;
import xertms.entidad.TmsEstadosTbl;
import xertms.entidad.TmsOfertasServicioTbl;
import xertms.entidad.TmsOfertasServicioV;
import xertms.entidad.TmsRutasTbl;
import xertms.entidad.TmsRutasV;
import xertms.entidad.TmsServiciosTbl;
import xertms.entidad2.TmsGlobalParametrosTbl;
import xertms.entidad2.TmsParametrosConfigTbl;
import xertms.solicitud.TmsOfertasServicioFacadeRemote;

public class SesionVenta {
    private String ipAS;
    private int portAS;
    private Vector ofertasGuardadas = null;
    private String PrefijoTerminalId;
    private String[] datosTerminal=null;
    private long usuarioId;
    private SimpleDateFormat formatoHora= new SimpleDateFormat("HH:mm");
    private SimpleDateFormat formatoFechaHora= new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private TmsOfertasServicioFacadeRemote sesionOfertasServicioFacate = null;
    private List<TmsEmpresasTbl> tmsEmpresasTbl = null;
    private List<TmsServiciosTbl> tmsServiciosTbl = null;
    private List<TmsRutasTbl> tmsRutasTbl = null;
    private List<TmsRutasV> tmsRutasV = null;
    private List<TmsOfertasServicioV> ofertasV = null;
    private List<TmsEstadosTbl> tmsEstadosTbl = null;
    
    private Vector vPlantillaRutas;
    private Vector vEmpresasV;
    private Vector vRutasV;
    private Vector vOrigenes;
    private Vector vDestinos;
    // BUSQUEDA
    private Vector vServicios;
    private Vector vEmpresas;
    private Vector vRutas;
    
    // PARAMETROS GLOBALES E IMPRESORAS
    tmscargaparamglobvta  cargaparametros;
    Vector codigos = null;
    Vector valores = null;

    private JDlgAceptar DialogoAceptar;

    private Object[][] oSE=null;
  
    public SesionVenta(long pUsuarioId, long MENU_ID, String pIpAS, int pPortAS) { 
        this.usuarioId = pUsuarioId;
        setIpAS(pIpAS); setPortAS(pPortAS);
    }
    
    private TmsOfertasServicioFacadeRemote lookupTmsCorteTaqFacade() {
        try {
            Context c = new InitialContext();
            return (TmsOfertasServicioFacadeRemote) c.lookup("xertms.solicitud.TmsOfertasServicioFacadeRemote");
        }
        catch(NamingException ne) {
            return null;
        }
    }
    
    private boolean cargaTerminal() {
        if(!abreSocketAS()) return false;
        datosTerminal = sesionOfertasServicioFacate.obtenerTerminal();
        if(datosTerminal==null) return false;
        if(datosTerminal[0].length()>3) PrefijoTerminalId  = datosTerminal[0].substring(0,3);
        if(datosTerminal[0].length()==3) PrefijoTerminalId = datosTerminal[0];
        if(datosTerminal[0].length()==2) PrefijoTerminalId = datosTerminal[0]+"0";
        if(datosTerminal[0].length()==1) PrefijoTerminalId = datosTerminal[0]+"00";
        return true;
    }
    
    public int procesoInicial(){
        if(!abreSocketAS()) return -1;
        sesionOfertasServicioFacate = lookupTmsCorteTaqFacade();
        if(sesionOfertasServicioFacate == null) return -1;
        //if(!FuncionesDeUsuario(MENU_ID)) return false;
        if(!cargaTerminal()) return 5;
        if(!Rutas()) return 1;
        if(!Empresas()) return 2;
        if(!Servicios()) return 3;
        if(!RutasV()) return 1;
        if(!PlantillaDefaultRuta()) return 6;
        if(!Estados()) return 4;       
        if(!ServiciosEmpresas()) return 7;
        return 0; // CARGA COMPLETA EXITOSA
    }
    
    public int procesoSecundario(){
        cargaparametros = new tmscargaparamglobvta(this);
        codigos = cargaparametros.getCodigos();
        valores = cargaparametros.getValores();
        if(codigos.size()==0 || valores.size()==0) return 5;
        return 0;
    }
    
    public Object[][] busqueda(String nombreOferta, String empresa, String ruta){
        long empresaId=-1; long rutaId=-1;
        if(empresa.equals("TODOS")) empresa="";
        else{
            empresaId=getEmpresaId(empresa);
            if(empresaId==-1) return null;
            empresa = String.valueOf(empresaId);
        }
        if(ruta.equals("TODOS")) ruta="";
        else{
            rutaId=getRutaId(ruta);
            if(rutaId==-1) return null;
            ruta = String.valueOf(rutaId);
        }
        if(!abreSocketAS()) return null;
        ofertasV = sesionOfertasServicioFacate.queryTmsOfertasServicioV(nombreOferta, empresa, ruta);
        
        if(ofertasV==null) return null;
        
        Object[][] datos = new Object[ofertasV.size()][6];
        String horario="";
        for(int i=0; i<ofertasV.size(); i++){
            datos[i][0] = ofertasV.get(i).getServicioNombre();
            datos[i][1] = ofertasV.get(i).getRutaNumero();
            datos[i][2] = ofertasV.get(i).getOrigen();
            datos[i][3] = ofertasV.get(i).getDestino();
            datos[i][4] = ofertasV.get(i).getEmpresaNombre();
            horario = formatoHora.format(ofertasV.get(i).getHoraCorrida())+" ";
            if(ofertasV.get(i).getLunesAplica().equals("S")) horario = horario+"Lun";
            if(ofertasV.get(i).getMartesAplica().equals("S")) horario = horario+"Mar";
            if(ofertasV.get(i).getMiercolesAplica().equals("S")) horario = horario+"Mie";
            if(ofertasV.get(i).getJuevesAplica().equals("S")) horario = horario+"Jue";
            if(ofertasV.get(i).getViernesAplica().equals("S")) horario = horario+"Vie";
            if(ofertasV.get(i).getSabadoAplica().equals("S")) horario = horario+"Sab";
            if(ofertasV.get(i).getDomingoAplica().equals("S")) horario = horario+"Dom";
            datos[i][5] = horario;
        }
        
        return datos;
    }
    
    public boolean validaHora(String strServicio, String rutaNumero, Timestamp hora){
        //return sesionOfertasServicioFacate.validaHora(getServicioId(strServicio), getRutaId(rutaNumero), hora);
        return true;
    }
    
    public long validaHoraModificada(String OfertaNombre, long ofertaId, Timestamp hora, long origenId, long destinoId, long empresaId){
        if(!abreSocketAS()) return -1;
        return sesionOfertasServicioFacate.validaHoraModificada(OfertaNombre, ofertaId, hora, origenId, destinoId, empresaId);
    }
    
    private boolean Empresas(){
        if(!abreSocketAS()) return false;
        tmsEmpresasTbl = sesionOfertasServicioFacate.queryTmsEmpresasTblAll();
        if(tmsEmpresasTbl == null) return false;
        vEmpresas = new Vector();
        vEmpresasV = new Vector();
        vEmpresas.add("TODOS");
        for(int i=0; i<tmsEmpresasTbl.size(); i++){
            vEmpresasV.add(tmsEmpresasTbl.get(i).getEmpresaNombre());
            vEmpresas.add(tmsEmpresasTbl.get(i).getEmpresaNombre());
        }
        return true;
    }
    
    private boolean RutasV(){
        if(!abreSocketAS()) return false;
        tmsRutasV = sesionOfertasServicioFacate.queryTmsRutasV();
        if(tmsRutasV == null) return false;
        return true;
    }
    
    private boolean Servicios(){
        if(!abreSocketAS()) return false;
        tmsServiciosTbl = sesionOfertasServicioFacate.queryTmsServiciosTblAll();
        if(tmsServiciosTbl == null) return false;
        vServicios = new Vector();
        for(int i=0; i<tmsServiciosTbl.size(); i++) vServicios.add(tmsServiciosTbl.get(i).getServicioNombre());
        return true;
    }
    
    private boolean Rutas(){
        if(!abreSocketAS()) return false;
        tmsRutasTbl = sesionOfertasServicioFacate.queryTmsRutasTblAll();
        if(tmsRutasTbl == null) return false;
        vRutas = new Vector();
        vRutas.add("TODOS");
        for(int i=0; i<tmsRutasTbl.size(); i++) vRutas.add(tmsRutasTbl.get(i).getRutaNumero());
        return true;
    }
    
    private boolean Estados(){
        if(!abreSocketAS()) return false;
        tmsEstadosTbl = sesionOfertasServicioFacate.queryTmsEstadosTblAll();
        if(tmsEstadosTbl == null) return false;
        return true;
    }
    
    private boolean ServiciosEmpresas(){
        if(!abreSocketAS()) return false;
        Vector vSEx=new Vector();
        vSEx = sesionOfertasServicioFacate.ServicioEmpresa();
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
    
    public String cargaNombreRuta(String ruta){
        for(int i=0; i<tmsRutasTbl.size(); i++)
            if(ruta.equals(tmsRutasTbl.get(i).getRutaNumero())) return tmsRutasTbl.get(i).getRutaNombre();
        return "";
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
    
    public long getRutaId(String numeroRuta){
        for(int i=0; i<tmsRutasTbl.size(); i++)
            if(tmsRutasTbl.get(i).getRutaNumero().equals(numeroRuta)) return tmsRutasTbl.get(i).getRutaId();
        return -1;
    }
    
    public long getEmpresaId(String nombreEmpresa){
        for(int i=0; i<tmsEmpresasTbl.size(); i++)
            if(tmsEmpresasTbl.get(i).getEmpresaNombre().equals(nombreEmpresa)) return tmsEmpresasTbl.get(i).getEmpresaId();
        return -1;
    }
    
    public long getServicioId(String nombreServicio){
        for(int i=0; i<tmsServiciosTbl.size(); i++)
            if(tmsServiciosTbl.get(i).getServicioNombre().equals(nombreServicio)) return tmsServiciosTbl.get(i).getServicioId();
        return -1;
    }
    
    public long getOrigenDestinoId(String Origen){
        for(int i=0; i<tmsEstadosTbl.size(); i++)
            if(tmsEstadosTbl.get(i).getEstadoNombre().equals(Origen)) return tmsEstadosTbl.get(i).getEstadoId();
        return -1;
    }
    
    // TRANSACCIONES
    
    public boolean guardarOfertas(List<TmsOfertasServicioV> o){
        if(!abreSocketAS()) return false;
        TmsOfertasServicioTbl oferta = null;
        Timestamp fecha = new Timestamp(new Date().getTime());
        for(int i=0; i<o.size(); i++){
            oferta = new TmsOfertasServicioTbl();
            oferta.setOfertaServicioNombre(o.get(i).getOfertaServicioNombre());
            oferta.setDescripcion(oferta.getOfertaServicioNombre());
            oferta.setServicioId(o.get(i).getServicioId());
            oferta.setEmpresaId(o.get(i).getEmpresaId());
            oferta.setRutaId(o.get(i).getRutaId());
            oferta.setTramoOrigenId(o.get(i).getOrigenId());
            oferta.setTramoDestinoId(o.get(i).getDestinoId());
            oferta.setHoraCorrida(o.get(i).getHoraCorrida());
            oferta.setLunesAplica(o.get(i).getLunesAplica());
            oferta.setMartesAplica(o.get(i).getMartesAplica());
            oferta.setMiercolesAplica(o.get(i).getMiercolesAplica());
            oferta.setJuevesAplica(o.get(i).getJuevesAplica());
            oferta.setViernesAplica(o.get(i).getViernesAplica());
            oferta.setSabadoAplica(o.get(i).getSabadoAplica());
            oferta.setDomingoAplica(o.get(i).getDomingoAplica());
            oferta.setMenoresCorrida(o.get(i).getMenoresCorrida());
            oferta.setEstudiantesCorrida(o.get(i).getEstudiantesCorrida());
            oferta.setProfesoresCorrida(o.get(i).getProfesoresCorrida());
            oferta.setSenectudCorrida(o.get(i).getSenectudCorrida());
            oferta.setCortesiasCorrida(o.get(i).getCortesiasCorrida());
            oferta.setCreadoPor(this.usuarioId);
            oferta.setFechaCreacion(fecha);
            oferta.setUltimaActualizacionPor(this.usuarioId);
            oferta.setUltimaFechaActualizacion(fecha);
            if(!sesionOfertasServicioFacate.registrarOferta(PrefijoTerminalId, oferta)) return false;
        }
        return true;        
    }
    
    public boolean actualizaOfertas(int iFila){
        Timestamp fecha = new Timestamp(new Date().getTime());
        TmsOfertasServicioTbl oferta = new TmsOfertasServicioTbl();
        oferta.setOfertaServicioId(ofertasV.get(iFila).getOfertaServicioId());
        oferta.setOfertaServicioNombre(ofertasV.get(iFila).getOfertaServicioNombre());
        oferta.setDescripcion(oferta.getOfertaServicioNombre());
        oferta.setServicioId(ofertasV.get(iFila).getServicioId());
        oferta.setEmpresaId(ofertasV.get(iFila).getEmpresaId());
        oferta.setRutaId(ofertasV.get(iFila).getRutaId());
        oferta.setTramoOrigenId(ofertasV.get(iFila).getOrigenId());
        oferta.setTramoDestinoId(ofertasV.get(iFila).getDestinoId());
        oferta.setHoraCorrida(ofertasV.get(iFila).getHoraCorrida());
        oferta.setLunesAplica(ofertasV.get(iFila).getLunesAplica());
        oferta.setMartesAplica(ofertasV.get(iFila).getMartesAplica());
        oferta.setMiercolesAplica(ofertasV.get(iFila).getMiercolesAplica());
        oferta.setJuevesAplica(ofertasV.get(iFila).getJuevesAplica());
        oferta.setViernesAplica(ofertasV.get(iFila).getViernesAplica());
        oferta.setSabadoAplica(ofertasV.get(iFila).getSabadoAplica());
        oferta.setDomingoAplica(ofertasV.get(iFila).getDomingoAplica());
        oferta.setMenoresCorrida(ofertasV.get(iFila).getMenoresCorrida());
        oferta.setEstudiantesCorrida(ofertasV.get(iFila).getEstudiantesCorrida());
        oferta.setProfesoresCorrida(ofertasV.get(iFila).getProfesoresCorrida());
        oferta.setSenectudCorrida(ofertasV.get(iFila).getSenectudCorrida());
        oferta.setCortesiasCorrida(ofertasV.get(iFila).getCortesiasCorrida());
        oferta.setUltimaActualizacionPor(this.usuarioId);
        oferta.setUltimaFechaActualizacion(fecha);
        if(!abreSocketAS()) return false;
        if(!sesionOfertasServicioFacate.actualizarOferta(oferta)) return false;
        return true;        
    }
    
    public boolean eliminarOferta(long ofertaId){
        if(!abreSocketAS()) return false;
        return sesionOfertasServicioFacate.eliminarOferta(ofertaId);
    }
    
    public Vector getVectorEmpresas(){ return vEmpresas; }
    
    public Vector getEmpresasV(){ return vEmpresasV; }
    
    public Vector getVectorServicios(){ return vServicios; }
    
    public Vector getVectorRutas(){ return vRutas; }
    
    public Vector getRutasV(){ return vRutasV; }
    
    public Vector getVectorOrigenes(){ return vOrigenes; }
    
    public Vector getVectorDestinos(){ return vDestinos; }
    
    public List<TmsOfertasServicioV> getResultadosBusquedas(){ return ofertasV; }
    
    // ANEXO FUNCIONES DE PARAMETROS
    public String getTurno(){
        String turno = "";
        String pturno = "";
        String sturno = "";
        String tturno = "";
        //TmsGlobalParametrosTbl globales;
        List<TmsGlobalParametrosTbl> lglobales;
        TmsParametrosConfigTbl paramconfig;
        paramconfig = sesionOfertasServicioFacate.busquedaPorCodigo("P_TMPPRITUR");
        Collection<TmsGlobalParametrosTbl> collglobales = paramconfig.getTmsGlobalParametrosTblCollection();
        Iterator ipg = collglobales.iterator();
        TmsGlobalParametrosTbl globales;
        while(ipg.hasNext()){
            globales = (TmsGlobalParametrosTbl)ipg.next();
            System.out.println("P_TMPPRITUR = "+globales.getParametroValor());
            pturno = globales.getParametroValor();
        }
        
        paramconfig = sesionOfertasServicioFacate.busquedaPorCodigo("P_TMPSEGTUR");
        collglobales = paramconfig.getTmsGlobalParametrosTblCollection();
        ipg = collglobales.iterator();
        while(ipg.hasNext()){
            globales = (TmsGlobalParametrosTbl)ipg.next();
            System.out.println("P_TMPSEGTUR = "+globales.getParametroValor());
            sturno = globales.getParametroValor();
        }
        
        paramconfig = sesionOfertasServicioFacate.busquedaPorCodigo("P_TMPTERTUR");
        collglobales = paramconfig.getTmsGlobalParametrosTblCollection();
        ipg = collglobales.iterator();
        while(ipg.hasNext()){
            globales = (TmsGlobalParametrosTbl)ipg.next();
            System.out.println("P_TMPTERTUR = "+globales.getParametroValor());
            tturno = globales.getParametroValor();
        }
        Timestamp primero = null;
        Timestamp segundo = null;
        Timestamp tercero = null;
        primero = primero.valueOf("2004-12-10 "+pturno+":00");
        segundo = segundo.valueOf("2004-12-10 "+sturno+":00");
        tercero = tercero.valueOf("2004-12-10 "+tturno+":00");
        String shora = sesionOfertasServicioFacate.fechaServidor().toString();
        shora = shora.substring(11,shora.length()-1);
        Timestamp actual = null;
        actual = actual.valueOf("2004-12-10 "+shora.trim());
        if(actual.getTime()>=primero.getTime() && actual.getTime()<segundo.getTime())
            turno = "1";
        if(actual.getTime()>=segundo.getTime() && actual.getTime()<tercero.getTime())
            turno = "2";
        if(actual.getTime()>=tercero.getTime() || actual.getTime()<primero.getTime())
            turno = "3";

        return turno;
    }
    
    public List findAllTodosParametros() {
        List<TmsParametrosConfigTbl> todosparametros = sesionOfertasServicioFacate.findAll();
        if(todosparametros==null) return null;
        return todosparametros;
    }
    
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
    
    public long[] getPlantillaDefaultRuta(String rutaNumero) {
        long[] a = new long[2];
        Vector vPlantillaRuta;
        for(int i=0; i<vPlantillaRutas.size(); i++){
            vPlantillaRuta = (Vector) vPlantillaRutas.get(i);
            if(rutaNumero.equals(vPlantillaRuta.get(2).toString())){
                a[0] = Long.valueOf(vPlantillaRuta.get(0).toString());
                a[1] = Long.valueOf(vPlantillaRuta.get(1).toString());
                return a;
            }
        }
        return null;
    }
 
    private boolean PlantillaDefaultRuta(){
        if(!abreSocketAS()) return false;
        vPlantillaRutas = sesionOfertasServicioFacate.PlantillaRutas();
        if(vPlantillaRutas == null) return false;
        return true;
    }
    
    public boolean existeNombreOfertaServicio(String nombreOferta){
        if(!abreSocketAS()) return false;
        return sesionOfertasServicioFacate.existeNombreOfertaServicio(nombreOferta);
    }
    
    public boolean afectaRolBaseLinea(long ofsId){
        if(!abreSocketAS()) return false;
        return sesionOfertasServicioFacate.afectaRolBaseLinea(ofsId);
    }
    
    public boolean muestraOfertasGuardadas(){
        if(!abreSocketAS()) return false;
        Vector ofertasGuardadasX = sesionOfertasServicioFacate.muestraOfertasGuardadas();
        if(ofertasGuardadasX==null ||ofertasGuardadasX.size()==0) return false;
        Vector x = null;
        ofertasGuardadas = new Vector();
        for(int i=0; i<ofertasGuardadasX.size(); i++){
            x = (Vector) ofertasGuardadasX.get(i);
            ofertasGuardadas.add(x.get(0).toString());
        }
        return true;
    }
    
    public Vector getOfertasGuardadas(){ return this.ofertasGuardadas; }
    
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
    
    public void setIpAS(String valor){ this.ipAS = valor; }
    public void setPortAS(int valor){ this.portAS = valor; }
    
    public String getIpAS(){ return this.ipAS; }
    public int getPortAS(){ return this.portAS; }
    
    public boolean validaServicioEmpresa(String Servicio, String Ruta, String Empresa){
        for(int i=0; i<oSE.length; i++)
            if(oSE[i][0].toString().equals(Servicio) && oSE[i][1].toString().equals(Ruta) && oSE[i][2].toString().equals(Empresa)) return true;
        return false;
    }
}