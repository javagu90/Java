/*
 * TmsRolesManagedBean.java
 *
 * Created on 29 de octubre de 2007, 07:14 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsroles;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tmsroles.solicitud.TmsAutobusesTblFacadeRemote;
import tmsroles.solicitud.TmsCorridasTblFacadeRemote;
import tmsroles.solicitud.TmsCorridasVentaTblFacadeRemote;
import tmsroles.solicitud.TmsRolesBaseLineasTblFacadeRemote;
import tmsroles.solicitud.TmsRolesBaseTblFacadeRemote;
import tmsroles.solicitud.TmsRolesMaestroTblFacadeRemote;
import tmsroles.solicitud.TmsServiciosTblFacadeRemote;
import tmsroles.solicitud.VariosFacadeRemote;

/**
 *
 * @author vgonzalez
 */
public class TmsRolesManagedBean {
    TmsServiciosTblFacadeRemote         serviciosTblFacadeRemote;
    VariosFacadeRemote                  variosFacadeRemote;
    TmsCorridasTblFacadeRemote          corridasTblFacadeRemote;
    TmsCorridasVentaTblFacadeRemote     corridasVentaTblFacadeRemote;
    TmsRolesMaestroTblFacadeRemote      rolesMaestroTblFacadeRemote;
    TmsRolesBaseTblFacadeRemote         rolesBaseTblFacadeRemote;
    TmsRolesBaseLineasTblFacadeRemote   rolesBaseLineasTblFacadeRemote;
    TmsAutobusesTblFacadeRemote         autobusesTblFacadeRemote;
    
    
    /** Creates a new instance of TmsRolesManagedBean */
    public TmsRolesManagedBean() {
        serviciosTblFacadeRemote        =  lookupTmsServiciosTblFacadeRemote();
        variosFacadeRemote              =  lookupVariosFacadeRemote();
        corridasTblFacadeRemote         =  lookupTmsCorridasTblFacadeRemote();
        corridasVentaTblFacadeRemote    =  lookupTmsCorridasVentaTblFacadeRemote(); 
        rolesMaestroTblFacadeRemote     =  lookupTmsRolesMaestroTblFacadeRemote(); 
        rolesBaseTblFacadeRemote        =  lookupTmsRolesBaseTblFacadeRemote();
        rolesBaseLineasTblFacadeRemote  =  lookupTmsRolesBaseLineasTblFacadeRemote();
        autobusesTblFacadeRemote        =  lookupTmsAutobusesTblFacadeRemote();
    }

    private TmsServiciosTblFacadeRemote lookupTmsServiciosTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsServiciosTblFacadeRemote) c.lookup("tmsroles.solicitud.TmsServiciosTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private VariosFacadeRemote lookupVariosFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (VariosFacadeRemote) c.lookup("tmsroles.solicitud.VariosFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsCorridasTblFacadeRemote lookupTmsCorridasTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsCorridasTblFacadeRemote) c.lookup("tmsroles.solicitud.TmsCorridasTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsCorridasVentaTblFacadeRemote lookupTmsCorridasVentaTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsCorridasVentaTblFacadeRemote) c.lookup("tmsroles.solicitud.TmsCorridasVentaTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsRolesMaestroTblFacadeRemote lookupTmsRolesMaestroTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsRolesMaestroTblFacadeRemote) c.lookup("tmsroles.solicitud.TmsRolesMaestroTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsRolesBaseTblFacadeRemote lookupTmsRolesBaseTblFacadeRemote() {
        try {
            Context c = new InitialContext();
            return (TmsRolesBaseTblFacadeRemote) c.lookup("tmsroles.solicitud.TmsRolesBaseTblFacadeRemote");
        }
        catch(NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsRolesBaseLineasTblFacadeRemote lookupTmsRolesBaseLineasTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmsroles.solicitud.TmsRolesBaseLineasTblFacadeRemote) c.lookup("tmsroles.solicitud.TmsRolesBaseLineasTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    private TmsAutobusesTblFacadeRemote lookupTmsAutobusesTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmsroles.solicitud.TmsAutobusesTblFacadeRemote) c.lookup("tmsroles.solicitud.TmsAutobusesTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }

    Vector buscaRolMaestroExistente(String string) {
        return null;
    }

    
    
    
}
