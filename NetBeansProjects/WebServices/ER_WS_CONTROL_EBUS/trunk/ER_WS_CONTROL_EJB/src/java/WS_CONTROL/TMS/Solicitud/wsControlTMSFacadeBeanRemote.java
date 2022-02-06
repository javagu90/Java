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
public interface wsControlTMSFacadeBeanRemote {

   

    public WS_CONTROL.TMSS.getRutasItinerariosResp getServicios(WS_CONTROL.TMSS.getRutasItinerariosReq parametros);

    public java.lang.String getFecha();

    public WS_CONTROL.getViajesClienteResp getViajesCliente(WS_CONTROL.getViajesClienteReq req) throws java.sql.SQLException;

    public WS_CONTROL.getViajesClienteResp getViajesHoCn(WS_CONTROL.getViajesClienteResp objetoTransacion, String clienteId);
    
}
