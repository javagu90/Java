/*
 * TmsModTarViajeManagedBean.java
 *
 * Created on 10 de septiembre de 2007, 08:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmsmodificatarjetaviaje;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tmsmodiftarviaje.solicitud.TmsTarjetasViajeTblFacadeRemote;


/**
 *
 * @author vgonzalez
 */
public class TmsModTarViajeManagedBean {
      TmsTarjetasViajeTblFacadeRemote  tarjetasViajeTblFacadeRemote;
    
    /**
     * Creates a new instance of TmsModTarViajeManagedBean
     */
    public TmsModTarViajeManagedBean() {
        tarjetasViajeTblFacadeRemote =  lookupTmsTarjetasViajeTblFacadeRemote();
    }
    

    private TmsTarjetasViajeTblFacadeRemote lookupTmsTarjetasViajeTblFacadeRemote() {
        try {
            javax.naming.Context c = new javax.naming.InitialContext();
            return (tmsmodiftarviaje.solicitud.TmsTarjetasViajeTblFacadeRemote) c.lookup("tmsmodiftarviaje.solicitud.TmsTarjetasViajeTblFacadeRemote");
        }
        catch(javax.naming.NamingException ne) {
            java.util.logging.Logger.getLogger(getClass().getName()).log(java.util.logging.Level.SEVERE,"exception caught" ,ne);
            throw new RuntimeException(ne);
        }
    }



}