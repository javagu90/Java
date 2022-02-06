package WsConvenio;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name="InterfazADO", targetNamespace="http://interfazado.estrellaroja.com.mx/")
public abstract interface InterfazADO
{
  @WebMethod
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="buscaCorrida", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.BuscaCorrida")
  @ResponseWrapper(localName="buscaCorridaResponse", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.BuscaCorridaResponse")
  public abstract BuscaCorridaResp buscaCorrida(@WebParam(name="caja", targetNamespace="") String paramString1, @WebParam(name="marca", targetNamespace="") String paramString2, @WebParam(name="servicio", targetNamespace="") String paramString3, @WebParam(name="origen", targetNamespace="") String paramString4, @WebParam(name="fecha", targetNamespace="") XMLGregorianCalendar paramXMLGregorianCalendar, @WebParam(name="destino", targetNamespace="") String paramString5);

  @WebMethod
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="buscaAsiento", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.BuscaAsiento")
  @ResponseWrapper(localName="buscaAsientoResponse", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.BuscaAsientoResponse")
  public abstract BuscaAsientoResp buscaAsiento(@WebParam(name="transaccionID", targetNamespace="") String paramString1, @WebParam(name="claveCorrida", targetNamespace="") String paramString2, @WebParam(name="origen", targetNamespace="") String paramString3, @WebParam(name="servicio", targetNamespace="") String paramString4, @WebParam(name="fecha", targetNamespace="") XMLGregorianCalendar paramXMLGregorianCalendar, @WebParam(name="hora", targetNamespace="") String paramString5);

  @WebMethod
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="bloquearAsiento", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.BloquearAsiento")
  @ResponseWrapper(localName="bloquearAsientoResponse", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.BloquearAsientoResponse")
  public abstract BloqueaAsientoResp bloquearAsiento(@WebParam(name="transaccionId", targetNamespace="") String paramString1, @WebParam(name="corridaId", targetNamespace="") String paramString2, @WebParam(name="fechaCorrida", targetNamespace="") XMLGregorianCalendar paramXMLGregorianCalendar, @WebParam(name="horaCorrida", targetNamespace="") String paramString3, @WebParam(name="promoRedondo", targetNamespace="") boolean paramBoolean, @WebParam(name="asientos", targetNamespace="") List<Asiento> paramList, @WebParam(name="servicio", targetNamespace="") String paramString4);

  @WebMethod
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="desbloquearAsiento", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.DesbloquearAsiento")
  @ResponseWrapper(localName="desbloquearAsientoResponse", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.DesbloquearAsientoResponse")
  public abstract BloqueaAsientoResp desbloquearAsiento(@WebParam(name="transaccionId", targetNamespace="") String paramString1, @WebParam(name="corridaId", targetNamespace="") String paramString2, @WebParam(name="fechaCorrida", targetNamespace="") XMLGregorianCalendar paramXMLGregorianCalendar, @WebParam(name="horaCorrida", targetNamespace="") String paramString3, @WebParam(name="promoRedondo", targetNamespace="") boolean paramBoolean, @WebParam(name="asientos", targetNamespace="") List<Asiento> paramList, @WebParam(name="servicio", targetNamespace="") String paramString4);

  @WebMethod
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="confirmaVentaBoleto", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.ConfirmaVentaBoleto")
  @ResponseWrapper(localName="confirmaVentaBoletoResponse", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.ConfirmaVentaBoletoResponse")
  public abstract ConfirmaVentaBoletoRes confirmaVentaBoleto(@WebParam(name="transactionId", targetNamespace="") String paramString1, @WebParam(name="boletos", targetNamespace="") List<BoletoCVB> paramList, @WebParam(name="promocion", targetNamespace="") boolean paramBoolean, @WebParam(name="fechaCorrida", targetNamespace="") XMLGregorianCalendar paramXMLGregorianCalendar, @WebParam(name="horaCorrida", targetNamespace="") String paramString2);

  @WebMethod
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="validaCancelacion", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.ValidaCancelacion")
  @ResponseWrapper(localName="validaCancelacionResponse", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.ValidaCancelacionResponse")
  public abstract ValidaBoletoRes validaCancelacion(@WebParam(name="boletos", targetNamespace="") List<BoletoVB> paramList);

  @WebMethod
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="validaTransferencia", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.ValidaTransferencia")
  @ResponseWrapper(localName="validaTransferenciaResponse", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.ValidaTransferenciaResponse")
  public abstract ValidaBoletoRes validaTransferencia(@WebParam(name="boletos", targetNamespace="") List<BoletoVB> paramList);

  @WebMethod
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="validaBoletoAbierto", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.ValidaBoletoAbierto")
  @ResponseWrapper(localName="validaBoletoAbiertoResponse", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.ValidaBoletoAbiertoResponse")
  public abstract ValidaBoletoRes validaBoletoAbierto(@WebParam(name="boletos", targetNamespace="") List<BoletoVB> paramList);

  @WebMethod
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="cancelaBoleto", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.CancelaBoleto")
  @ResponseWrapper(localName="cancelaBoletoResponse", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.CancelaBoletoResponse")
  public abstract CancelaBoletoRes cancelaBoleto(@WebParam(name="boletos", targetNamespace="") List<BoletoCB> paramList);

  @WebMethod(operationName="TransferenciaBoletoADO_ADO")
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="TransferenciaBoletoADO_ADO", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.TransferenciaBoletoADOADO")
  @ResponseWrapper(localName="TransferenciaBoletoADO_ADOResponse", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.TransferenciaBoletoADOADOResponse")
  public abstract TransferenciaBoletoADOADOResp transferenciaBoletoADOADO(@WebParam(name="Folios_Canjear", targetNamespace="") List<FolioCanjear> paramList, @WebParam(name="Boleto_Canjeado", targetNamespace="") List<BoletoCanjeado> paramList1, @WebParam(name="Transacion", targetNamespace="") String paramString1, @WebParam(name="FechaCorrida", targetNamespace="") XMLGregorianCalendar paramXMLGregorianCalendar, @WebParam(name="HoraCorrida", targetNamespace="") String paramString2, @WebParam(name="PromoRedondo", targetNamespace="") boolean paramBoolean);

  @WebMethod(operationName="TransferenciaBoletoADO_ER")
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="TransferenciaBoletoADO_ER", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.TransferenciaBoletoADOER")
  @ResponseWrapper(localName="TransferenciaBoletoADO_ERResponse", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.TransferenciaBoletoADOERResponse")
  public abstract TransferenciaBoletoADOERResp transferenciaBoletoADOER(@WebParam(name="Folios_Canjear", targetNamespace="") List<FolioCanjear> paramList, @WebParam(name="Boleto_Canjeado", targetNamespace="") List<BoletoCanjeado> paramList1, @WebParam(name="Transacion", targetNamespace="") String paramString1, @WebParam(name="FechaCorrida", targetNamespace="") XMLGregorianCalendar paramXMLGregorianCalendar, @WebParam(name="HoraCorrida", targetNamespace="") String paramString2, @WebParam(name="PromoRedondo", targetNamespace="") boolean paramBoolean);

  @WebMethod(operationName="CanjeBoletoAbiertoADO_ER")
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="CanjeBoletoAbiertoADO_ER", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.CanjeBoletoAbiertoADOER")
  @ResponseWrapper(localName="CanjeBoletoAbiertoADO_ERResponse", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.CanjeBoletoAbiertoADOERResponse")
  public abstract CanjeBoletoAbiertoADOERResp canjeBoletoAbiertoADOER(@WebParam(name="Folios_Canjear", targetNamespace="") List<FolioCanjear> paramList);

  @WebMethod(operationName="CanjeBoletoAbiertoADO_ADO")
  @WebResult(targetNamespace="")
  @RequestWrapper(localName="CanjeBoletoAbiertoADO_ADO", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.CanjeBoletoAbiertoADOADO")
  @ResponseWrapper(localName="CanjeBoletoAbiertoADO_ADOResponse", targetNamespace="http://interfazado.estrellaroja.com.mx/", className="WsConvenio.CanjeBoletoAbiertoADOADOResponse")
  public abstract CanjeBoletoAbiertoADOADOResp canjeBoletoAbiertoADOADO(@WebParam(name="Folios_Canjear", targetNamespace="") List<FolioCanjear> paramList, @WebParam(name="Boleto_Canjeado", targetNamespace="") List<BoletoCanjeado> paramList1, @WebParam(name="Transacion", targetNamespace="") String paramString1, @WebParam(name="FechaCorrida", targetNamespace="") String paramString2, @WebParam(name="HoraCorrida", targetNamespace="") String paramString3, @WebParam(name="NumeroOperacion", targetNamespace="") String paramString4, @WebParam(name="PromoRedondo", targetNamespace="") boolean paramBoolean);
}

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.InterfazADO
 * JD-Core Version:    0.6.0
 */