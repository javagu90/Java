/*    */ package WsConvenio;
/*    */ 
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="cancelaBoletoResponse", propOrder={"_return"})
/*    */ public class CancelaBoletoResponse
/*    */ {
/*    */ 
/*    */   @XmlElement(name="return")
/*    */   protected CancelaBoletoRes _return;
/*    */ 
/*    */   public CancelaBoletoRes getReturn()
/*    */   {
/* 47 */     return this._return;
/*    */   }
/*    */ 
/*    */   public void setReturn(CancelaBoletoRes value)
/*    */   {
/* 59 */     this._return = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.CancelaBoletoResponse
 * JD-Core Version:    0.6.0
 */