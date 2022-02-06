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
/*    */ @XmlType(name="validaCancelacion", propOrder={"boletos"})
/*    */ public class ValidaCancelacion
/*    */ {
/*    */ 
/*    */   @XmlElement(required=true)
/*    */   protected List<BoletoVB> boletos;
/*    */ 
/*    */   public List<BoletoVB> getBoletos()
/*    */   {
/* 63 */     if (this.boletos == null) {
/* 64 */       this.boletos = new ArrayList();
/*    */     }
/* 66 */     return this.boletos;
/*    */   }
/*    */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.ValidaCancelacion
 * JD-Core Version:    0.6.0
 */