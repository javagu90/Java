/*    */ package WsConvenio;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="bloquearAsientoResponse", propOrder={"_return"})
/*    */ public class BloquearAsientoResponse
/*    */ {
/*    */ 
/*    */   @XmlElement(name="return")
/*    */   protected BloqueaAsientoResp _return;
/*    */ 
/*    */   public BloqueaAsientoResp getReturn()
/*    */   {
/* 47 */     return this._return;
/*    */   }
/*    */ 
/*    */   public void setReturn(BloqueaAsientoResp value)
/*    */   {
/* 59 */     this._return = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.BloquearAsientoResponse
 * JD-Core Version:    0.6.0
 */