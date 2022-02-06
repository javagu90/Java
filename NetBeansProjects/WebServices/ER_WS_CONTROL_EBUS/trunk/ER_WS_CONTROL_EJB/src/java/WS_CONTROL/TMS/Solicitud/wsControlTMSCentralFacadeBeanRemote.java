/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.TMS.Solicitud;

import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface wsControlTMSCentralFacadeBeanRemote {

    public WS_CONTROL.TMS.entidades.Boleto getInformacionBoleto(java.lang.String P_FOLIO_PREIMPRESO, java.lang.String P_NO_ASIENTO, java.lang.String P_ORIGEN);

    
}
