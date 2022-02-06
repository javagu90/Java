/*    */ package WsConvenio;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="TransferenciaBoletoADO_ADOResponse", propOrder={"_return"})
/*    */ public class TransferenciaBoletoADOADOResponse
/*    */ {
/*    */ 
/*    */   @XmlElement(name="return")
/*    */   protected TransferenciaBoletoADOADOResp _return;
/*    */ 
/*    */   public TransferenciaBoletoADOADOResp getReturn()
/*    */   {
/* 47 */     return this._return;
/*    */   }
/*    */ 
/*    */   public void setReturn(TransferenciaBoletoADOADOResp value)
/*    */   {
/* 59 */     this._return = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.TransferenciaBoletoADOADOResponse
 * JD-Core Version:    0.6.0
 */