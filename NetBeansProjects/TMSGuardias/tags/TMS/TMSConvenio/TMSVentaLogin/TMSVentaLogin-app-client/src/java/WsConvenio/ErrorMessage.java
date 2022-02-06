/*    */ package WsConvenio;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlType;
/*    */ 
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ @XmlType(name="errorMessage", propOrder={"messages", "status"})
/*    */ public class ErrorMessage
/*    */ {
/*    */ 
/*    */   @XmlElement(required=true, nillable=true)
/*    */   protected List<Message> messages;
/*    */   protected int status;
/*    */ 
/*    */   public List<Message> getMessages()
/*    */   {
/* 66 */     if (this.messages == null) {
/* 67 */       this.messages = new ArrayList();
/*    */     }
/* 69 */     return this.messages;
/*    */   }
/*    */ 
/*    */   public int getStatus()
/*    */   {
/* 77 */     return this.status;
/*    */   }
/*    */ 
/*    */   public void setStatus(int value)
/*    */   {
/* 85 */     this.status = value;
/*    */   }
/*    */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.ErrorMessage
 * JD-Core Version:    0.6.0
 */