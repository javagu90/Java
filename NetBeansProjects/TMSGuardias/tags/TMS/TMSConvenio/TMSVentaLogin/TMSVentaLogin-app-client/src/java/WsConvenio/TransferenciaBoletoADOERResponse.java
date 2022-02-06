/*    */ package WsConvenio;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="TransferenciaBoletoADO_ERResponse", propOrder={"_return"})
/*    */ public class TransferenciaBoletoADOERResponse
/*    */ {
/*    */ 
/*    */   @XmlElement(name="return")
/*    */   protected TransferenciaBoletoADOERResp _return;
/*    */ 
/*    */   public TransferenciaBoletoADOERResp getReturn()
/*    */   {
/* 47 */     return this._return;
/*    */   }
/*    */ 
/*    */   public void setReturn(TransferenciaBoletoADOERResp value)
/*    */   {
/* 59 */     this._return = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.TransferenciaBoletoADOERResponse
 * JD-Core Version:    0.6.0
 */