/*     */ package WsConvenio;
/*     */ 
/*     */ import javax.xml.bind.JAXBElement;
/*     */ import javax.xml.bind.annotation.XmlElementDecl;
/*     */ import javax.xml.bind.annotation.XmlRegistry;
/*     */ import javax.xml.namespace.QName;
/*     */ 
/*     */ @XmlRegistry
/*     */ public class ObjectFactory
/*     */ {
/*  27 */   private static final QName _ValidaTransferenciaResponse_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "validaTransferenciaResponse");
/*  28 */   private static final QName _BuscaAsiento_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "buscaAsiento");
/*  29 */   private static final QName _ValidaBoletoAbiertoResponse_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "validaBoletoAbiertoResponse");
/*  30 */   private static final QName _CanjeBoletoAbiertoADOER_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "CanjeBoletoAbiertoADO_ER");
/*  31 */   private static final QName _ValidaTransferencia_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "validaTransferencia");
/*  32 */   private static final QName _CanjeBoletoAbiertoADOADO_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "CanjeBoletoAbiertoADO_ADO");
/*  33 */   private static final QName _ConfirmaVentaBoletoResponse_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "confirmaVentaBoletoResponse");
/*  34 */   private static final QName _CancelaBoleto_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "cancelaBoleto");
/*  35 */   private static final QName _DesbloquearAsiento_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "desbloquearAsiento");
/*  36 */   private static final QName _CanjeBoletoAbiertoADOERResponse_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "CanjeBoletoAbiertoADO_ERResponse");
/*  37 */   private static final QName _TransferenciaBoletoADOER_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "TransferenciaBoletoADO_ER");
/*  38 */   private static final QName _BuscaAsientoResponse_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "buscaAsientoResponse");
/*  39 */   private static final QName _ValidaBoletoAbierto_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "validaBoletoAbierto");
/*  40 */   private static final QName _CancelaBoletoResponse_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "cancelaBoletoResponse");
/*  41 */   private static final QName _BloquearAsiento_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "bloquearAsiento");
/*  42 */   private static final QName _BuscaCorrida_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "buscaCorrida");
/*  43 */   private static final QName _BloquearAsientoResponse_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "bloquearAsientoResponse");
/*  44 */   private static final QName _ConfirmaVentaBoleto_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "confirmaVentaBoleto");
/*  45 */   private static final QName _ValidaCancelacionResponse_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "validaCancelacionResponse");
/*  46 */   private static final QName _TransferenciaBoletoADOADO_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "TransferenciaBoletoADO_ADO");
/*  47 */   private static final QName _CanjeBoletoAbiertoADOADOResponse_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "CanjeBoletoAbiertoADO_ADOResponse");
/*  48 */   private static final QName _TransferenciaBoletoADOERResponse_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "TransferenciaBoletoADO_ERResponse");
/*  49 */   private static final QName _TransferenciaBoletoADOADOResponse_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "TransferenciaBoletoADO_ADOResponse");
/*  50 */   private static final QName _ValidaCancelacion_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "validaCancelacion");
/*  51 */   private static final QName _BuscaCorridaResponse_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "buscaCorridaResponse");
/*  52 */   private static final QName _DesbloquearAsientoResponse_QNAME = new QName("http://interfazado.estrellaroja.com.mx/", "desbloquearAsientoResponse");
/*     */ 
/*     */   public BuscaAsientoResp createBuscaAsientoResp()
/*     */   {
/*  66 */     return new BuscaAsientoResp();
/*     */   }
/*     */ 
/*     */   public Asiento createAsiento()
/*     */   {
/*  74 */     return new Asiento();
/*     */   }
/*     */ 
/*     */   public ConfirmaVentaBoletoResponse createConfirmaVentaBoletoResponse()
/*     */   {
/*  82 */     return new ConfirmaVentaBoletoResponse();
/*     */   }
/*     */ 
/*     */   public ValidaTransferenciaResponse createValidaTransferenciaResponse()
/*     */   {
/*  90 */     return new ValidaTransferenciaResponse();
/*     */   }
/*     */ 
/*     */   public TransferenciaBoletoADOADOResponse createTransferenciaBoletoADOADOResponse()
/*     */   {
/*  98 */     return new TransferenciaBoletoADOADOResponse();
/*     */   }
/*     */ 
/*     */   public ValidaBoletoAbierto createValidaBoletoAbierto()
/*     */   {
/* 106 */     return new ValidaBoletoAbierto();
/*     */   }
/*     */ 
/*     */   public BoletoCanjeado createBoletoCanjeado()
/*     */   {
/* 114 */     return new BoletoCanjeado();
/*     */   }
/*     */ 
/*     */   public BoletoCB createBoletoCB()
/*     */   {
/* 122 */     return new BoletoCB();
/*     */   }
/*     */ 
/*     */   public BuscaCorridaResponse createBuscaCorridaResponse()
/*     */   {
/* 130 */     return new BuscaCorridaResponse();
/*     */   }
/*     */ 
/*     */   public BoletoCVB createBoletoCVB()
/*     */   {
/* 138 */     return new BoletoCVB();
/*     */   }
/*     */ 
/*     */   public TransferenciaBoletoADOADO createTransferenciaBoletoADOADO()
/*     */   {
/* 146 */     return new TransferenciaBoletoADOADO();
/*     */   }
/*     */ 
/*     */   public ConfirmaVentaBoleto createConfirmaVentaBoleto()
/*     */   {
/* 154 */     return new ConfirmaVentaBoleto();
/*     */   }
/*     */ 
/*     */   public BuscaCorrida createBuscaCorrida()
/*     */   {
/* 162 */     return new BuscaCorrida();
/*     */   }
/*     */ 
/*     */   public BloquearAsiento createBloquearAsiento()
/*     */   {
/* 170 */     return new BloquearAsiento();
/*     */   }
/*     */ 
/*     */   public CancelaBoleto createCancelaBoleto()
/*     */   {
/* 178 */     return new CancelaBoleto();
/*     */   }
/*     */ 
/*     */   public BloqueaAsientoResp createBloqueaAsientoResp()
/*     */   {
/* 186 */     return new BloqueaAsientoResp();
/*     */   }
/*     */ 
/*     */   public TransferenciaBoletoADOERResp createTransferenciaBoletoADOERResp()
/*     */   {
/* 194 */     return new TransferenciaBoletoADOERResp();
/*     */   }
/*     */ 
/*     */   public BuscaCorridaResp createBuscaCorridaResp()
/*     */   {
/* 202 */     return new BuscaCorridaResp();
/*     */   }
/*     */ 
/*     */   public Message createMessage()
/*     */   {
/* 210 */     return new Message();
/*     */   }
/*     */ 
/*     */   public BoletoVBRes createBoletoVBRes()
/*     */   {
/* 218 */     return new BoletoVBRes();
/*     */   }
/*     */ 
/*     */   public Corrida createCorrida()
/*     */   {
/* 226 */     return new Corrida();
/*     */   }
/*     */ 
/*     */   public TransferenciaBoletoADOADOResp createTransferenciaBoletoADOADOResp()
/*     */   {
/* 234 */     return new TransferenciaBoletoADOADOResp();
/*     */   }
/*     */ 
/*     */   public TransferenciaBoletoADOERResponse createTransferenciaBoletoADOERResponse()
/*     */   {
/* 242 */     return new TransferenciaBoletoADOERResponse();
/*     */   }
/*     */ 
/*     */   public CancelaBoletoRes createCancelaBoletoRes()
/*     */   {
/* 250 */     return new CancelaBoletoRes();
/*     */   }
/*     */ 
/*     */   public ConfirmaVentaBoletoRes createConfirmaVentaBoletoRes()
/*     */   {
/* 258 */     return new ConfirmaVentaBoletoRes();
/*     */   }
/*     */ 
/*     */   public DesbloquearAsiento createDesbloquearAsiento()
/*     */   {
/* 266 */     return new DesbloquearAsiento();
/*     */   }
/*     */ 
/*     */   public ErrorMessage createErrorMessage()
/*     */   {
/* 274 */     return new ErrorMessage();
/*     */   }
/*     */ 
/*     */   public TransferenciaBoletoADOER createTransferenciaBoletoADOER()
/*     */   {
/* 282 */     return new TransferenciaBoletoADOER();
/*     */   }
/*     */ 
/*     */   public CanjeBoletoAbiertoADOADO createCanjeBoletoAbiertoADOADO()
/*     */   {
/* 290 */     return new CanjeBoletoAbiertoADOADO();
/*     */   }
/*     */ 
/*     */   public Folio createFolio()
/*     */   {
/* 298 */     return new Folio();
/*     */   }
/*     */ 
/*     */   public ValidaBoletoAbiertoResponse createValidaBoletoAbiertoResponse()
/*     */   {
/* 306 */     return new ValidaBoletoAbiertoResponse();
/*     */   }
/*     */ 
/*     */   public BuscaAsiento createBuscaAsiento()
/*     */   {
/* 314 */     return new BuscaAsiento();
/*     */   }
/*     */ 
/*     */   public TipoPasajero createTipoPasajero()
/*     */   {
/* 322 */     return new TipoPasajero();
/*     */   }
/*     */ 
/*     */   public ValidaBoletoRes createValidaBoletoRes()
/*     */   {
/* 330 */     return new ValidaBoletoRes();
/*     */   }
/*     */ 
/*     */   public CanjeBoletoAbiertoADOERResponse createCanjeBoletoAbiertoADOERResponse()
/*     */   {
/* 338 */     return new CanjeBoletoAbiertoADOERResponse();
/*     */   }
/*     */ 
/*     */   public CanjeBoletoAbiertoADOERResp createCanjeBoletoAbiertoADOERResp()
/*     */   {
/* 346 */     return new CanjeBoletoAbiertoADOERResp();
/*     */   }
/*     */ 
/*     */   public CanjeBoletoAbiertoADOER createCanjeBoletoAbiertoADOER()
/*     */   {
/* 354 */     return new CanjeBoletoAbiertoADOER();
/*     */   }
/*     */ 
/*     */   public BuscaAsientoResponse createBuscaAsientoResponse()
/*     */   {
/* 362 */     return new BuscaAsientoResponse();
/*     */   }
/*     */ 
/*     */   public BoletoVB createBoletoVB()
/*     */   {
/* 370 */     return new BoletoVB();
/*     */   }
/*     */ 
/*     */   public DesbloquearAsientoResponse createDesbloquearAsientoResponse()
/*     */   {
/* 378 */     return new DesbloquearAsientoResponse();
/*     */   }
/*     */ 
/*     */   public ValidaTransferencia createValidaTransferencia()
/*     */   {
/* 386 */     return new ValidaTransferencia();
/*     */   }
/*     */ 
/*     */   public CanjeBoletoAbiertoADOADOResp createCanjeBoletoAbiertoADOADOResp()
/*     */   {
/* 394 */     return new CanjeBoletoAbiertoADOADOResp();
/*     */   }
/*     */ 
/*     */   public ValidaCancelacionResponse createValidaCancelacionResponse()
/*     */   {
/* 402 */     return new ValidaCancelacionResponse();
/*     */   }
/*     */ 
/*     */   public CanjeBoletoAbiertoADOADOResponse createCanjeBoletoAbiertoADOADOResponse()
/*     */   {
/* 410 */     return new CanjeBoletoAbiertoADOADOResponse();
/*     */   }
/*     */ 
/*     */   public CancelaBoletoResponse createCancelaBoletoResponse()
/*     */   {
/* 418 */     return new CancelaBoletoResponse();
/*     */   }
/*     */ 
/*     */   public BloquearAsientoResponse createBloquearAsientoResponse()
/*     */   {
/* 426 */     return new BloquearAsientoResponse();
/*     */   }
/*     */ 
/*     */   public FolioCanjear createFolioCanjear()
/*     */   {
/* 434 */     return new FolioCanjear();
/*     */   }
/*     */ 
/*     */   public ValidaCancelacion createValidaCancelacion()
/*     */   {
/* 442 */     return new ValidaCancelacion();
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="validaTransferenciaResponse")
/*     */   public JAXBElement<ValidaTransferenciaResponse> createValidaTransferenciaResponse(ValidaTransferenciaResponse value)
/*     */   {
/* 451 */     return new JAXBElement(_ValidaTransferenciaResponse_QNAME, ValidaTransferenciaResponse.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="buscaAsiento")
/*     */   public JAXBElement<BuscaAsiento> createBuscaAsiento(BuscaAsiento value)
/*     */   {
/* 460 */     return new JAXBElement(_BuscaAsiento_QNAME, BuscaAsiento.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="validaBoletoAbiertoResponse")
/*     */   public JAXBElement<ValidaBoletoAbiertoResponse> createValidaBoletoAbiertoResponse(ValidaBoletoAbiertoResponse value)
/*     */   {
/* 469 */     return new JAXBElement(_ValidaBoletoAbiertoResponse_QNAME, ValidaBoletoAbiertoResponse.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="CanjeBoletoAbiertoADO_ER")
/*     */   public JAXBElement<CanjeBoletoAbiertoADOER> createCanjeBoletoAbiertoADOER(CanjeBoletoAbiertoADOER value)
/*     */   {
/* 478 */     return new JAXBElement(_CanjeBoletoAbiertoADOER_QNAME, CanjeBoletoAbiertoADOER.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="validaTransferencia")
/*     */   public JAXBElement<ValidaTransferencia> createValidaTransferencia(ValidaTransferencia value)
/*     */   {
/* 487 */     return new JAXBElement(_ValidaTransferencia_QNAME, ValidaTransferencia.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="CanjeBoletoAbiertoADO_ADO")
/*     */   public JAXBElement<CanjeBoletoAbiertoADOADO> createCanjeBoletoAbiertoADOADO(CanjeBoletoAbiertoADOADO value)
/*     */   {
/* 496 */     return new JAXBElement(_CanjeBoletoAbiertoADOADO_QNAME, CanjeBoletoAbiertoADOADO.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="confirmaVentaBoletoResponse")
/*     */   public JAXBElement<ConfirmaVentaBoletoResponse> createConfirmaVentaBoletoResponse(ConfirmaVentaBoletoResponse value)
/*     */   {
/* 505 */     return new JAXBElement(_ConfirmaVentaBoletoResponse_QNAME, ConfirmaVentaBoletoResponse.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="cancelaBoleto")
/*     */   public JAXBElement<CancelaBoleto> createCancelaBoleto(CancelaBoleto value)
/*     */   {
/* 514 */     return new JAXBElement(_CancelaBoleto_QNAME, CancelaBoleto.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="desbloquearAsiento")
/*     */   public JAXBElement<DesbloquearAsiento> createDesbloquearAsiento(DesbloquearAsiento value)
/*     */   {
/* 523 */     return new JAXBElement(_DesbloquearAsiento_QNAME, DesbloquearAsiento.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="CanjeBoletoAbiertoADO_ERResponse")
/*     */   public JAXBElement<CanjeBoletoAbiertoADOERResponse> createCanjeBoletoAbiertoADOERResponse(CanjeBoletoAbiertoADOERResponse value)
/*     */   {
/* 532 */     return new JAXBElement(_CanjeBoletoAbiertoADOERResponse_QNAME, CanjeBoletoAbiertoADOERResponse.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="TransferenciaBoletoADO_ER")
/*     */   public JAXBElement<TransferenciaBoletoADOER> createTransferenciaBoletoADOER(TransferenciaBoletoADOER value)
/*     */   {
/* 541 */     return new JAXBElement(_TransferenciaBoletoADOER_QNAME, TransferenciaBoletoADOER.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="buscaAsientoResponse")
/*     */   public JAXBElement<BuscaAsientoResponse> createBuscaAsientoResponse(BuscaAsientoResponse value)
/*     */   {
/* 550 */     return new JAXBElement(_BuscaAsientoResponse_QNAME, BuscaAsientoResponse.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="validaBoletoAbierto")
/*     */   public JAXBElement<ValidaBoletoAbierto> createValidaBoletoAbierto(ValidaBoletoAbierto value)
/*     */   {
/* 559 */     return new JAXBElement(_ValidaBoletoAbierto_QNAME, ValidaBoletoAbierto.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="cancelaBoletoResponse")
/*     */   public JAXBElement<CancelaBoletoResponse> createCancelaBoletoResponse(CancelaBoletoResponse value)
/*     */   {
/* 568 */     return new JAXBElement(_CancelaBoletoResponse_QNAME, CancelaBoletoResponse.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="bloquearAsiento")
/*     */   public JAXBElement<BloquearAsiento> createBloquearAsiento(BloquearAsiento value)
/*     */   {
/* 577 */     return new JAXBElement(_BloquearAsiento_QNAME, BloquearAsiento.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="buscaCorrida")
/*     */   public JAXBElement<BuscaCorrida> createBuscaCorrida(BuscaCorrida value)
/*     */   {
/* 586 */     return new JAXBElement(_BuscaCorrida_QNAME, BuscaCorrida.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="bloquearAsientoResponse")
/*     */   public JAXBElement<BloquearAsientoResponse> createBloquearAsientoResponse(BloquearAsientoResponse value)
/*     */   {
/* 595 */     return new JAXBElement(_BloquearAsientoResponse_QNAME, BloquearAsientoResponse.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="confirmaVentaBoleto")
/*     */   public JAXBElement<ConfirmaVentaBoleto> createConfirmaVentaBoleto(ConfirmaVentaBoleto value)
/*     */   {
/* 604 */     return new JAXBElement(_ConfirmaVentaBoleto_QNAME, ConfirmaVentaBoleto.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="validaCancelacionResponse")
/*     */   public JAXBElement<ValidaCancelacionResponse> createValidaCancelacionResponse(ValidaCancelacionResponse value)
/*     */   {
/* 613 */     return new JAXBElement(_ValidaCancelacionResponse_QNAME, ValidaCancelacionResponse.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="TransferenciaBoletoADO_ADO")
/*     */   public JAXBElement<TransferenciaBoletoADOADO> createTransferenciaBoletoADOADO(TransferenciaBoletoADOADO value)
/*     */   {
/* 622 */     return new JAXBElement(_TransferenciaBoletoADOADO_QNAME, TransferenciaBoletoADOADO.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="CanjeBoletoAbiertoADO_ADOResponse")
/*     */   public JAXBElement<CanjeBoletoAbiertoADOADOResponse> createCanjeBoletoAbiertoADOADOResponse(CanjeBoletoAbiertoADOADOResponse value)
/*     */   {
/* 631 */     return new JAXBElement(_CanjeBoletoAbiertoADOADOResponse_QNAME, CanjeBoletoAbiertoADOADOResponse.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="TransferenciaBoletoADO_ERResponse")
/*     */   public JAXBElement<TransferenciaBoletoADOERResponse> createTransferenciaBoletoADOERResponse(TransferenciaBoletoADOERResponse value)
/*     */   {
/* 640 */     return new JAXBElement(_TransferenciaBoletoADOERResponse_QNAME, TransferenciaBoletoADOERResponse.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="TransferenciaBoletoADO_ADOResponse")
/*     */   public JAXBElement<TransferenciaBoletoADOADOResponse> createTransferenciaBoletoADOADOResponse(TransferenciaBoletoADOADOResponse value)
/*     */   {
/* 649 */     return new JAXBElement(_TransferenciaBoletoADOADOResponse_QNAME, TransferenciaBoletoADOADOResponse.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="validaCancelacion")
/*     */   public JAXBElement<ValidaCancelacion> createValidaCancelacion(ValidaCancelacion value)
/*     */   {
/* 658 */     return new JAXBElement(_ValidaCancelacion_QNAME, ValidaCancelacion.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="buscaCorridaResponse")
/*     */   public JAXBElement<BuscaCorridaResponse> createBuscaCorridaResponse(BuscaCorridaResponse value)
/*     */   {
/* 667 */     return new JAXBElement(_BuscaCorridaResponse_QNAME, BuscaCorridaResponse.class, null, value);
/*     */   }
/*     */ 
/*     */   @XmlElementDecl(namespace="http://interfazado.estrellaroja.com.mx/", name="desbloquearAsientoResponse")
/*     */   public JAXBElement<DesbloquearAsientoResponse> createDesbloquearAsientoResponse(DesbloquearAsientoResponse value)
/*     */   {
/* 676 */     return new JAXBElement(_DesbloquearAsientoResponse_QNAME, DesbloquearAsientoResponse.class, null, value);
/*     */   }
/*     */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.ObjectFactory
 * JD-Core Version:    0.6.0
 */