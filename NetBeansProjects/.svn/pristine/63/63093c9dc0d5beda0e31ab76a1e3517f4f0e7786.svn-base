/*
 * TmsLiberarBoletosManagedBean.java
 *
 * Created on 9 de octubre de 2007, 10:49 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tms_liberar_bol_no_disp;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tmsBolNoDisp.solicitud.TmsAsientosNoDispTblFacadeRemote;
import tmsBolNoDisp.solicitud.TmsBoletosNoDisponiblesVFacadeRemote;
import tmsBolNoDisp.solicitud.TmsCorridasVentaTblFacadeRemote;
/**
 *
 * @author vgonzalez
 */
public class TmsLiberarBoletosManagedBean {    
    TmsAsientosNoDispTblFacadeRemote        asientosNoDispTblFacadeRemote;
    TmsBoletosNoDisponiblesVFacadeRemote    boletosNoDisponiblesVFacadeRemote;
    TmsCorridasVentaTblFacadeRemote         corridasVentaTblFacadeRemote;
    /**
     * Creates a new instance of TmsLiberarBoletosManagedBean
     */
    public TmsLiberarBoletosManagedBean() {
        asientosNoDispTblFacadeRemote       =   lookupTmsAsientosNoDispTblFacadeRemote();
        boletosNoDisponiblesVFacadeRemote   =   lookupTmsBoletosNoDisponiblesVFacadeRemote();
        corridasVentaTblFacadeRemote        =   lookupTmsCorridasVentaTblFacadeRemote();
    }



    private TmsAsientosNoDispTblFacadeRemote lookupTmsAsientosNoDispTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmsBolNoDisp.solicitud.TmsAsientosNoDispTblFacadeRemote) c.lookup("tmsBolNoDisp.solicitud.TmsAsientosNoDispTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsBoletosNoDisponiblesVFacadeRemote lookupTmsBoletosNoDisponiblesVFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmsBolNoDisp.solicitud.TmsBoletosNoDisponiblesVFacadeRemote) c.lookup("tmsBolNoDisp.solicitud.TmsBoletosNoDisponiblesVFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsCorridasVentaTblFacadeRemote lookupTmsCorridasVentaTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmsBolNoDisp.solicitud.TmsCorridasVentaTblFacadeRemote) c.lookup("tmsBolNoDisp.solicitud.TmsCorridasVentaTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    
}
