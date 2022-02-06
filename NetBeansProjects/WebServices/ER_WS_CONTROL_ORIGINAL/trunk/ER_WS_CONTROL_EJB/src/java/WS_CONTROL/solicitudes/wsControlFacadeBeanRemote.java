/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.solicitudes;

import javax.ejb.Remote;

/**
 *
 * @author vgonzalez
 */
@Remote
public interface wsControlFacadeBeanRemote {

    public WS_CONTROL.getOperacionesPromocionResp getOperacionesPromocion(WS_CONTROL.getOperacionesPromocionReq parametros);


    public WS_CONTROL.getOperacionesUsuarioResp getOperacionesUsuario(WS_CONTROL.getOperacionesUsuarioReq parametros);


    public WS_CONTROL.getPromocionesResp getPromociones(WS_CONTROL.getPromocionesReq parametros);



    public WS_CONTROL.getUsuariosResp getUsuarios(WS_CONTROL.getUsuariosReq parametros);

    public WS_CONTROL.getOperacionesClienteResp getOperacionesCliente(WS_CONTROL.getOperacionesClienteReq parametros);

    public WS_CONTROL.getClientesResp getClientes(WS_CONTROL.getClientesReq parametros);

    public WS_CONTROL.getOperacionesClientesUsuarioResp getOperacionesClientesUsuario(WS_CONTROL.getOperacionesClientesUsuarioReq parametros);

    public WS_CONTROL.getPagosResp getPagos(WS_CONTROL.getPagosReq parametros);

    public WS_CONTROL.getOperacionesFacturaResp getOperacionesFactura(WS_CONTROL.getOperacionesFacturaReq parametros);

    public WS_CONTROL.getFacturasResp getFacturas(WS_CONTROL.getFacturasReq parametros);

    public WS_CONTROL.getProductosClienteResp getProductosCliente(WS_CONTROL.getProductosClienteReq parametros);

      


    public WS_CONTROL.getPerfilesUsuarioResp getPerfilesUsuario(WS_CONTROL.getPerfilesUsuarioReq parametros);

    public WS_CONTROL.getOperacionesCreditoClientesResp getOperacionesCreditoClientes(WS_CONTROL.getOperacionesCreditoClientesReq parametros);

    public WS_CONTROL.getOperacionesPagosClienteResp getOperacionesPagosCliente(WS_CONTROL.getOperacionesPagosClienteReq parametros);

    public WS_CONTROL.getPagosFacturaResp getPagosFactura(WS_CONTROL.getPagosFacturaReq parametros);

    public WS_CONTROL.getOperacionesPagosFacturasResp getOperacionesPagosFacturas(WS_CONTROL.getOperacionesPagosFacturasReq parametros);

    public WS_CONTROL.getRegistraTransaccionesClienteResp getRegistraTransaccionesCliente(WS_CONTROL.getRegistraTransaccionesClienteReq parametros);

    public WS_CONTROL.getEstadoCuentaClienteResp getEstadoCuentaCliente(WS_CONTROL.getEstadoCuentaClienteReq parametros);

    public WS_CONTROL.getLoginResp getLogin(WS_CONTROL.getLoginReq parametros);

    public WS_CONTROL.getLogoutResp getLogout(WS_CONTROL.getLogoutReq parametros);

    public WS_CONTROL.getValidaSesionResp getValidaSesion(WS_CONTROL.getValidaSesionReq parametros) throws java.sql.SQLException, java.lang.Exception;
    

}
