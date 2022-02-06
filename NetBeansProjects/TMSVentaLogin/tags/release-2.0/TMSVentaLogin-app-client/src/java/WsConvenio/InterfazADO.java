
package WsConvenio;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2-hudson-752-
 * Generated source version: 2.2
 * 
 */
@WebService(name = "InterfazADO", targetNamespace = "http://interfazado.estrellaroja.com.mx/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface InterfazADO {


    /**
     * 
     * @param destino
     * @param horaDesde
     * @param servicio
     * @param fecha
     * @param marca
     * @param caja
     * @param origen
     * @return
     *     returns WsConvenio.BuscaCorridaResp
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "buscaCorrida", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.BuscaCorrida")
    @ResponseWrapper(localName = "buscaCorridaResponse", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.BuscaCorridaResponse")
    public BuscaCorridaResp buscaCorrida(
        @WebParam(name = "caja", targetNamespace = "")
        String caja,
        @WebParam(name = "marca", targetNamespace = "")
        String marca,
        @WebParam(name = "servicio", targetNamespace = "")
        String servicio,
        @WebParam(name = "origen", targetNamespace = "")
        String origen,
        @WebParam(name = "fecha", targetNamespace = "")
        String fecha,
        @WebParam(name = "destino", targetNamespace = "")
        String destino,
        @WebParam(name = "horaDesde", targetNamespace = "")
        String horaDesde);

    /**
     * 
     * @param hora
     * @param servicio
     * @param claveCorrida
     * @param fecha
     * @param transaccionID
     * @param origen
     * @return
     *     returns WsConvenio.BuscaAsientoResp
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "buscaAsiento", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.BuscaAsiento")
    @ResponseWrapper(localName = "buscaAsientoResponse", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.BuscaAsientoResponse")
    public BuscaAsientoResp buscaAsiento(
        @WebParam(name = "transaccionID", targetNamespace = "")
        String transaccionID,
        @WebParam(name = "claveCorrida", targetNamespace = "")
        String claveCorrida,
        @WebParam(name = "origen", targetNamespace = "")
        String origen,
        @WebParam(name = "servicio", targetNamespace = "")
        String servicio,
        @WebParam(name = "fecha", targetNamespace = "")
        String fecha,
        @WebParam(name = "hora", targetNamespace = "")
        String hora);

    /**
     * 
     * @param fechaCorrida
     * @param servicio
     * @param horaCorrida
     * @param transaccionId
     * @param asientos
     * @param promoRedondo
     * @param corridaId
     * @return
     *     returns WsConvenio.BloqueaAsientoResp
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "bloquearAsiento", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.BloquearAsiento")
    @ResponseWrapper(localName = "bloquearAsientoResponse", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.BloquearAsientoResponse")
    public BloqueaAsientoResp bloquearAsiento(
        @WebParam(name = "transaccionId", targetNamespace = "")
        String transaccionId,
        @WebParam(name = "corridaId", targetNamespace = "")
        String corridaId,
        @WebParam(name = "fechaCorrida", targetNamespace = "")
        String fechaCorrida,
        @WebParam(name = "horaCorrida", targetNamespace = "")
        String horaCorrida,
        @WebParam(name = "promoRedondo", targetNamespace = "")
        boolean promoRedondo,
        @WebParam(name = "asientos", targetNamespace = "")
        List<Asiento> asientos,
        @WebParam(name = "servicio", targetNamespace = "")
        String servicio);

    /**
     * 
     * @param fechaCorrida
     * @param servicio
     * @param horaCorrida
     * @param transaccionId
     * @param asientos
     * @param promoRedondo
     * @param corridaId
     * @return
     *     returns WsConvenio.BloqueaAsientoResp
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "desbloquearAsiento", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.DesbloquearAsiento")
    @ResponseWrapper(localName = "desbloquearAsientoResponse", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.DesbloquearAsientoResponse")
    public BloqueaAsientoResp desbloquearAsiento(
        @WebParam(name = "transaccionId", targetNamespace = "")
        String transaccionId,
        @WebParam(name = "corridaId", targetNamespace = "")
        String corridaId,
        @WebParam(name = "fechaCorrida", targetNamespace = "")
        String fechaCorrida,
        @WebParam(name = "horaCorrida", targetNamespace = "")
        String horaCorrida,
        @WebParam(name = "promoRedondo", targetNamespace = "")
        boolean promoRedondo,
        @WebParam(name = "asientos", targetNamespace = "")
        List<Asiento> asientos,
        @WebParam(name = "servicio", targetNamespace = "")
        String servicio);

    /**
     * 
     * @param boletos
     * @param fechaCorrida
     * @param promocion
     * @param transactionId
     * @param horaCorrida
     * @return
     *     returns WsConvenio.ConfirmaVentaBoletoRes
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "confirmaVentaBoleto", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.ConfirmaVentaBoleto")
    @ResponseWrapper(localName = "confirmaVentaBoletoResponse", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.ConfirmaVentaBoletoResponse")
    public ConfirmaVentaBoletoRes confirmaVentaBoleto(
        @WebParam(name = "transactionId", targetNamespace = "")
        String transactionId,
        @WebParam(name = "boletos", targetNamespace = "")
        List<BoletoCVB> boletos,
        @WebParam(name = "promocion", targetNamespace = "")
        boolean promocion,
        @WebParam(name = "fechaCorrida", targetNamespace = "")
        String fechaCorrida,
        @WebParam(name = "horaCorrida", targetNamespace = "")
        String horaCorrida);

    /**
     * 
     * @param boletos
     * @return
     *     returns WsConvenio.ValidaBoletoRes
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "validaCancelacion", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.ValidaCancelacion")
    @ResponseWrapper(localName = "validaCancelacionResponse", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.ValidaCancelacionResponse")
    public ValidaBoletoRes validaCancelacion(
        @WebParam(name = "boletos", targetNamespace = "")
        List<BoletoVB> boletos);

    /**
     * 
     * @param boletos
     * @return
     *     returns WsConvenio.ValidaBoletoRes
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "validaTransferencia", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.ValidaTransferencia")
    @ResponseWrapper(localName = "validaTransferenciaResponse", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.ValidaTransferenciaResponse")
    public ValidaBoletoRes validaTransferencia(
        @WebParam(name = "boletos", targetNamespace = "")
        List<BoletoVB> boletos);

    /**
     * 
     * @param boletos
     * @return
     *     returns WsConvenio.ValidaBoletoRes
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "validaBoletoAbierto", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.ValidaBoletoAbierto")
    @ResponseWrapper(localName = "validaBoletoAbiertoResponse", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.ValidaBoletoAbiertoResponse")
    public ValidaBoletoRes validaBoletoAbierto(
        @WebParam(name = "boletos", targetNamespace = "")
        List<BoletoVB> boletos);

    /**
     * 
     * @param boletos
     * @return
     *     returns WsConvenio.CancelaBoletoRes
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "cancelaBoleto", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.CancelaBoleto")
    @ResponseWrapper(localName = "cancelaBoletoResponse", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.CancelaBoletoResponse")
    public CancelaBoletoRes cancelaBoleto(
        @WebParam(name = "boletos", targetNamespace = "")
        List<BoletoCB> boletos);

    /**
     * 
     * @param fechaCorrida
     * @param horaCorrida
     * @param promoRedondo
     * @param boletoCanjeado
     * @param transacion
     * @param foliosCanjear
     * @return
     *     returns WsConvenio.TransferenciaBoletoADOADOResp
     */
    @WebMethod(operationName = "TransferenciaBoletoADO_ADO")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "TransferenciaBoletoADO_ADO", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.TransferenciaBoletoADOADO")
    @ResponseWrapper(localName = "TransferenciaBoletoADO_ADOResponse", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.TransferenciaBoletoADOADOResponse")
    public TransferenciaBoletoADOADOResp transferenciaBoletoADOADO(
        @WebParam(name = "Folios_Canjear", targetNamespace = "")
        List<FolioCanjear> foliosCanjear,
        @WebParam(name = "Boleto_Canjeado", targetNamespace = "")
        List<BoletoCanjeado> boletoCanjeado,
        @WebParam(name = "Transacion", targetNamespace = "")
        String transacion,
        @WebParam(name = "FechaCorrida", targetNamespace = "")
        String fechaCorrida,
        @WebParam(name = "HoraCorrida", targetNamespace = "")
        String horaCorrida,
        @WebParam(name = "PromoRedondo", targetNamespace = "")
        boolean promoRedondo);

    /**
     * 
     * @param fechaCorrida
     * @param horaCorrida
     * @param promoRedondo
     * @param boletoCanjeado
     * @param transacion
     * @param foliosCanjear
     * @return
     *     returns WsConvenio.TransferenciaBoletoADOERResp
     */
    @WebMethod(operationName = "TransferenciaBoletoADO_ER")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "TransferenciaBoletoADO_ER", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.TransferenciaBoletoADOER")
    @ResponseWrapper(localName = "TransferenciaBoletoADO_ERResponse", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.TransferenciaBoletoADOERResponse")
    public TransferenciaBoletoADOERResp transferenciaBoletoADOER(
        @WebParam(name = "Folios_Canjear", targetNamespace = "")
        List<FolioCanjear> foliosCanjear,
        @WebParam(name = "Boleto_Canjeado", targetNamespace = "")
        List<BoletoCanjeado> boletoCanjeado,
        @WebParam(name = "Transacion", targetNamespace = "")
        String transacion,
        @WebParam(name = "FechaCorrida", targetNamespace = "")
        String fechaCorrida,
        @WebParam(name = "HoraCorrida", targetNamespace = "")
        String horaCorrida,
        @WebParam(name = "PromoRedondo", targetNamespace = "")
        boolean promoRedondo);

    /**
     * 
     * @param foliosCanjear
     * @return
     *     returns WsConvenio.CanjeBoletoAbiertoADOERResp
     */
    @WebMethod(operationName = "CanjeBoletoAbiertoADO_ER")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "CanjeBoletoAbiertoADO_ER", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.CanjeBoletoAbiertoADOER")
    @ResponseWrapper(localName = "CanjeBoletoAbiertoADO_ERResponse", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.CanjeBoletoAbiertoADOERResponse")
    public CanjeBoletoAbiertoADOERResp canjeBoletoAbiertoADOER(
        @WebParam(name = "Folios_Canjear", targetNamespace = "")
        List<FolioCanjear> foliosCanjear);

    /**
     * 
     * @param fechaCorrida
     * @param numeroOperacion
     * @param horaCorrida
     * @param promoRedondo
     * @param boletoCanjeado
     * @param transacion
     * @param foliosCanjear
     * @return
     *     returns WsConvenio.CanjeBoletoAbiertoADOADOResp
     */
    @WebMethod(operationName = "CanjeBoletoAbiertoADO_ADO")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "CanjeBoletoAbiertoADO_ADO", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.CanjeBoletoAbiertoADOADO")
    @ResponseWrapper(localName = "CanjeBoletoAbiertoADO_ADOResponse", targetNamespace = "http://interfazado.estrellaroja.com.mx/", className = "WsConvenio.CanjeBoletoAbiertoADOADOResponse")
    public CanjeBoletoAbiertoADOADOResp canjeBoletoAbiertoADOADO(
        @WebParam(name = "Folios_Canjear", targetNamespace = "")
        List<FolioCanjear> foliosCanjear,
        @WebParam(name = "Boleto_Canjeado", targetNamespace = "")
        List<BoletoCanjeado> boletoCanjeado,
        @WebParam(name = "Transacion", targetNamespace = "")
        String transacion,
        @WebParam(name = "FechaCorrida", targetNamespace = "")
        String fechaCorrida,
        @WebParam(name = "HoraCorrida", targetNamespace = "")
        String horaCorrida,
        @WebParam(name = "NumeroOperacion", targetNamespace = "")
        String numeroOperacion,
        @WebParam(name = "PromoRedondo", targetNamespace = "")
        boolean promoRedondo);

}
