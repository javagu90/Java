/*     */ package WsConvenio;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="confirmaVentaBoletoRes", propOrder={"errorMessage", "folios", "success"})
/*     */ public class ConfirmaVentaBoletoRes
/*     */ {
/*     */   protected ErrorMessage errorMessage;
/*     */ 
/*     */   @XmlElement(required=true, nillable=true)
/*     */   protected List<String> folios;
/*     */   protected boolean success;
/*     */ 
/*     */   public ErrorMessage getErrorMessage()
/*     */   {
/*  55 */     return this.errorMessage;
/*     */   }
/*     */ 
/*     */   public void setErrorMessage(ErrorMessage value)
/*     */   {
/*  67 */     this.errorMessage = value;
/*     */   }
/*     */ 
/*     */   public List<String> getFolios()
/*     */   {
/*  93 */     if (this.folios == null) {
/*  94 */       this.folios = new ArrayList();
/*     */     }
/*  96 */     return this.folios;
/*     */   }
/*     */ 
/*     */   public boolean isSuccess()
/*     */   {
/* 104 */     return this.success;
/*     */   }
/*     */ 
/*     */   public void setSuccess(boolean value)
/*     */   {
/* 112 */     this.success = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.ConfirmaVentaBoletoRes
 * JD-Core Version:    0.6.0
 */