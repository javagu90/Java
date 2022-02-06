/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.Ventour.solicitudes;

import WS_CONTROL.Ventour.entidades.ProductoCotiza;
import WS_CONTROL.Ventour.entidades.SalidaCotiza;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author brojas
 */
@Remote
public interface wsVentourFacadeBeanRemote {


    public WS_CONTROLVentour.getUnidadesResp getUnidades(WS_CONTROLVentour.getUnidadesReq parametros);

   
    public WS_CONTROLVentour.getCancelaContratoResp getCancelaContrato(WS_CONTROLVentour.getCancelaContratoReq parametros);

    public WS_CONTROLVentour.getOperacionContratoResp getOperacionContrato(WS_CONTROLVentour.getOperacionContratoReq parametros);

    public WS_CONTROLVentour.getEstadosResp getEstados(WS_CONTROLVentour.getEstadosReq parametros);

    public WS_CONTROLVentour.getMunicipiosResp getMunicipios(WS_CONTROLVentour.getMunicipiosReq parametros);

 //   public java.util.Vector getPrecioCotizacion(WS_CONTROL.Ventour.entidades.Contrato contrato);
     public java.util.Vector getPrecioCotizacion(WS_CONTROL.Ventour.entidades.Contrato contrato, String TipoCotizacion);

    public WS_CONTROLVentour.getKilometrosResp getkilometros(WS_CONTROLVentour.getKilometrosReq parametros);

    public SalidaCotiza get_Cotizador(WS_CONTROL.Ventour.entidades.CotizacionD cotizacion);

}
