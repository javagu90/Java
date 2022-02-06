 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.paquer.solicitudes;

import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface wsPaqerFacadeBeanRemote {

    public WS_CONTROL.paquer.getServiciosResp getServicios(WS_CONTROL.paquer.getServiciosReq parametros);

    public WS_CONTROL.paquer.getRutaServiciosResp getRutaServicios(WS_CONTROL.paquer.getRutaServiciosReq parametros);

    public WS_CONTROL.paquer.getOperacionesGuiaResp getOperacionesGuia(WS_CONTROL.paquer.getOperacionesGuiaReq parametros);

    public WS_CONTROL.paquer.getCancelarGuiaResp getCancelarGuia(WS_CONTROL.paquer.getCancelarGuiaReq parametros);
    
}
