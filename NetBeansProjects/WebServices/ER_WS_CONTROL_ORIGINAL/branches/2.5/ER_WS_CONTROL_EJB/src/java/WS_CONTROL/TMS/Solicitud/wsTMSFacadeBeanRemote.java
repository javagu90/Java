/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.TMS.Solicitud;

import javax.ejb.Remote;

/**
 *
 * @author brojas
 */
@Remote
public interface wsTMSFacadeBeanRemote {

   

    public WS_CONTROL.TMSS.getRutasItinerariosResp getServicios(WS_CONTROL.TMSS.getRutasItinerariosReq parametros);

    public java.lang.String getFecha();
    
}
