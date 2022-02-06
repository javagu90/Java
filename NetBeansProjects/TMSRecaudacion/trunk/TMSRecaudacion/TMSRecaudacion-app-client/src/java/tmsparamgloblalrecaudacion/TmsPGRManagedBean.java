/*
 * TmsPGRManagedBean.java
 *
 * Created on 30 de agosto de 2007, 12:59 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsparamgloblalrecaudacion;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import TmsRecaudacion.entidad.TmsGlobalParametrosTbl;
import TmsRecaudacion.entidad.TmsParametrosConfigTbl;
import TmsRecaudacion.solicitud.TmsCajaParametrosTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsCajasImpresorasTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsCajasRecTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsEmpresaParametrosTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsFormatosBoletoTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsGlobalParametrosTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsParametrosConfigTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsServicioParametrosTblFacadeRemote;
import TmsRecaudacion.solicitud.TmsTerminalParametrosTblFacadeRemote;

/**
 *
 * @author vgonzalez
 */
public class TmsPGRManagedBean {
    
    /**
     * Creates a new instance of TmsPGRManagedBean
     */
    TmsParametrosConfigTblFacadeRemote   parametrosConfigTblFacadeRemote;
    TmsCajaParametrosTblFacadeRemote     cajaParametrosTblFacadeRemote;
    TmsEmpresaParametrosTblFacadeRemote  empresaParametrosTblFacadeRemote;
    TmsGlobalParametrosTblFacadeRemote   globalParametrosTblFacadeRemote;
    TmsServicioParametrosTblFacadeRemote servicioParametrosTblFacadeRemote;
    TmsTerminalParametrosTblFacadeRemote terminalParametrosTblFacadeRemote;
    TmsCajasImpresorasTblFacadeRemote    cajasImpresorasTblFacadeRemote;
    TmsCajasRecTblFacadeRemote              cajasTblFacadeRemote;
    TmsFormatosBoletoTblFacadeRemote     formatosBoletoTblFacadeRemote;
    SimpleDateFormat ffecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    SimpleDateFormat fhora  = new SimpleDateFormat("HH:mm");

    
    public TmsPGRManagedBean() {
        parametrosConfigTblFacadeRemote  =  lookupTmsParametrosConfigTblFacade();
        cajaParametrosTblFacadeRemote    =  lookupTmsCajaParametrosTblFacade();
        empresaParametrosTblFacadeRemote =  lookupTmsEmpresaParametrosTblFacade();
        globalParametrosTblFacadeRemote  =  lookupTmsGlobalParametrosTblFacade();
        servicioParametrosTblFacadeRemote=  lookupTmsServicioParametrosTblFacade();
        terminalParametrosTblFacadeRemote=  lookupTmsTerminalParametrosTblFacade();
        cajasImpresorasTblFacadeRemote   =  lookupTmsCajasImpresorasTblFacadeRemote();
        cajasTblFacadeRemote             =  lookupTmsCajasTblFacadeRemote();
        formatosBoletoTblFacadeRemote    =  lookupTmsFormatosBoletoTblFacade();
    }

    private TmsParametrosConfigTblFacadeRemote lookupTmsParametrosConfigTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsParametrosConfigTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsParametrosConfigTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }
    
    private TmsCajaParametrosTblFacadeRemote lookupTmsCajaParametrosTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsCajaParametrosTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsCajaParametrosTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsEmpresaParametrosTblFacadeRemote lookupTmsEmpresaParametrosTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsEmpresaParametrosTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsEmpresaParametrosTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsGlobalParametrosTblFacadeRemote lookupTmsGlobalParametrosTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsGlobalParametrosTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsGlobalParametrosTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsServicioParametrosTblFacadeRemote lookupTmsServicioParametrosTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsServicioParametrosTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsServicioParametrosTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsTerminalParametrosTblFacadeRemote lookupTmsTerminalParametrosTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsTerminalParametrosTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsTerminalParametrosTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    private TmsCajasRecTblFacadeRemote lookupTmsCajasTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsCajasRecTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsCajasRecTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsCajasImpresorasTblFacadeRemote lookupTmsCajasImpresorasTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsCajasImpresorasTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsCajasImpresorasTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsFormatosBoletoTblFacadeRemote lookupTmsFormatosBoletoTblFacade() {
        try {
            Context c = new InitialContext();
            return (TmsFormatosBoletoTblFacadeRemote) c.lookup("TmsRecaudacion.solicitud.TmsFormatosBoletoTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }
    
    

    
    
    public Date FechaServidor(){
        Date fecha;
        Vector x = (Vector) parametrosConfigTblFacadeRemote.fechaServidor();
        if(x==null) return null;
        try {
            fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(x.get(0).toString());
        }catch(ParseException ex) {
            ex.printStackTrace();
            fecha = null;
        }
        return fecha;
    }
    
   public String getTurno(){
        String turno = "";
        String pturno = "";
        String sturno = "";
        String tturno = "";
        //TmsGlobalParametrosTbl globales;
        List<TmsGlobalParametrosTbl> lglobales;
        TmsParametrosConfigTbl paramconfig;
        paramconfig = parametrosConfigTblFacadeRemote.busquedaPorCodigo("P_TMPPRITUR");
        System.out.println("Parametro = "+paramconfig.getParametroNombre()+"    ColeccionGlobal= "+paramconfig.getTmsGlobalParametrosTblCollection().size()+"   ColeccionEmpresas= "+paramconfig.getTmsEmpresaParametrosTblCollection().size());
        Collection<TmsGlobalParametrosTbl> collglobales = paramconfig.getTmsGlobalParametrosTblCollection();
        Iterator ipg = collglobales.iterator();
        TmsGlobalParametrosTbl globales;
        while(ipg.hasNext()){
            globales = (TmsGlobalParametrosTbl)ipg.next();
            System.out.println("P_TMPPRITUR = "+globales.getParametroValor());
            pturno = globales.getParametroValor();
        }
        
        paramconfig = parametrosConfigTblFacadeRemote.busquedaPorCodigo("P_TMPSEGTUR");
        System.out.println("Parametro = "+paramconfig.getParametroNombre()+"    ColeccionGlobal= "+paramconfig.getTmsGlobalParametrosTblCollection().size()+"   ColeccionEmpresas= "+paramconfig.getTmsEmpresaParametrosTblCollection().size());
        collglobales = paramconfig.getTmsGlobalParametrosTblCollection();
        ipg = collglobales.iterator();
        while(ipg.hasNext()){
            globales = (TmsGlobalParametrosTbl)ipg.next();
            System.out.println("P_TMPSEGTUR = "+globales.getParametroValor());
            sturno = globales.getParametroValor();
        }
        
        paramconfig = parametrosConfigTblFacadeRemote.busquedaPorCodigo("P_TMPTERTUR");
        System.out.println("Parametro = "+paramconfig.getParametroNombre()+"    ColeccionGlobal= "+paramconfig.getTmsGlobalParametrosTblCollection().size()+"   ColeccionEmpresas= "+paramconfig.getTmsEmpresaParametrosTblCollection().size());
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
        String shora = parametrosConfigTblFacadeRemote.fechaServidor().toString();
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

   
   public String getTerminalId(){
            //Vector TER = (Vector)parametrosConfigTblFacadeRemote.queryBuscaTerminalId();
            Vector TER = (Vector)cajasTblFacadeRemote.queryBuscaTerminalId();
            String terminalId = TER.get(0).toString();
            return terminalId;
   }

    
}
