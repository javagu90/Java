/*    */ package WsConvenio;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="buscaAsientoResponse", propOrder={"_return"})
/*    */ public class BuscaAsientoResponse
/*    */ {
/*    */ 
/*    */   @XmlElement(name="return")
/*    */   protected BuscaAsientoResp _return;
/*    */ 
/*    */   public BuscaAsientoResp getReturn()
/*    */   {
/* 47 */     return this._return;
/*    */   }
/*    */ 
/*    */   public void setReturn(BuscaAsientoResp value)
/*    */   {
/* 59 */     this._return = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.BuscaAsientoResponse
 * JD-Core Version:    0.6.0
 */