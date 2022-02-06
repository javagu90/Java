/*     */ package WsConvenio;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.datatype.XMLGregorianCalendar;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="confirmaVentaBoleto", propOrder={"transactionId", "boletos", "promocion", "fechaCorrida", "horaCorrida"})
/*     */ public class ConfirmaVentaBoleto
/*     */ {
/*     */   protected String transactionId;
/*     */ 
/*     */   @XmlElement(required=true)
/*     */   protected List<BoletoCVB> boletos;
/*     */   protected boolean promocion;
/*     */   protected XMLGregorianCalendar fechaCorrida;
/*     */   protected String horaCorrida;
/*     */ 
/*     */   public String getTransactionId()
/*     */   {
/*  62 */     return this.transactionId;
/*     */   }
/*     */ 
/*     */   public void setTransactionId(String value)
/*     */   {
/*  74 */     this.transactionId = value;
/*     */   }
/*     */ 
/*     */   public List<BoletoCVB> getBoletos()
/*     */   {
/* 100 */     if (this.boletos == null) {
/* 101 */       this.boletos = new ArrayList();
/*     */     }
/* 103 */     return this.boletos;
/*     */   }
/*     */ 
/*     */   public boolean isPromocion()
/*     */   {
/* 111 */     return this.promocion;
/*     */   }
/*     */ 
/*     */   public void setPromocion(boolean value)
/*     */   {
/* 119 */     this.promocion = value;
/*     */   }
/*     */ 
/*     */   public XMLGregorianCalendar getFechaCorrida()
/*     */   {
/* 131 */     return this.fechaCorrida;
/*     */   }
/*     */ 
/*     */   public void setFechaCorrida(XMLGregorianCalendar value)
/*     */   {
/* 143 */     this.fechaCorrida = value;
/*     */   }
/*     */ 
/*     */   public String getHoraCorrida()
/*     */   {
/* 155 */     return this.horaCorrida;
/*     */   }
/*     */ 
/*     */   public void setHoraCorrida(String value)
/*     */   {
/* 167 */     this.horaCorrida = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.ConfirmaVentaBoleto
 * JD-Core Version:    0.6.0
 */