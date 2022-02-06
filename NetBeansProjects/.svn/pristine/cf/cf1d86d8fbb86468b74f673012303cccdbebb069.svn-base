/*    */ package WsConvenio;
/*    */ 
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
/*    */ import javax.xml.namespace.QName;
/*    */ import javax.xml.ws.Service;
/*    */ import javax.xml.ws.WebEndpoint;
/*    */ import javax.xml.ws.WebServiceClient;
/*    */ 
/*    */ @WebServiceClient(name="InterfazADOService", targetNamespace="http://interfazado.estrellaroja.com.mx/", wsdlLocation="http://devas01:8090/InterfazADOService/InterfazADO?WSDL")
/*    */ public class InterfazADOService extends Service
/*    */ {
/*    */   private static final URL INTERFAZADOSERVICE_WSDL_LOCATION;
/*    */ 
/*    */   public InterfazADOService(URL wsdlLocation, QName serviceName)
/*    */   {
/* 36 */     super(wsdlLocation, serviceName);
/*    */   }
/*    */ 
/*    */   public InterfazADOService() {
/* 40 */     super(INTERFAZADOSERVICE_WSDL_LOCATION, new QName("http://interfazado.estrellaroja.com.mx/", "InterfazADOService"));
/*    */   }
/*    */ 
/*    */   @WebEndpoint(name="InterfazADOPort")
/*    */   public InterfazADO getInterfazADOPort()
/*    */   {
/* 50 */     return (InterfazADO)super.getPort(new QName("http://interfazado.estrellaroja.com.mx/", "InterfazADOPort"), InterfazADO.class);
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 26 */     URL url = null;
/*    */     try {
/* 28 */       url = new URL("http://devas01:8090/InterfazADOService/InterfazADO?WSDL");
/*    */     } catch (MalformedURLException e) {
/* 30 */       e.printStackTrace();
/*    */     }
/* 32 */     INTERFAZADOSERVICE_WSDL_LOCATION = url;
/*    */   }
/*    */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.InterfazADOService
 * JD-Core Version:    0.6.0
 */