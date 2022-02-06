/*     */ package WsConvenio;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="canjeBoletoAbiertoADOERResp", propOrder={"errorMessage", "errormsg", "folio", "success"})
/*     */ public class CanjeBoletoAbiertoADOERResp
/*     */ {
/*     */   protected ErrorMessage errorMessage;
/*     */   protected String errormsg;
/*     */   protected String folio;
/*     */   protected boolean success;
/*     */ 
/*     */   public ErrorMessage getErrorMessage()
/*     */   {
/*  54 */     return this.errorMessage;
/*     */   }
/*     */ 
/*     */   public void setErrorMessage(ErrorMessage value)
/*     */   {
/*  66 */     this.errorMessage = value;
/*     */   }
/*     */ 
/*     */   public String getErrormsg()
/*     */   {
/*  78 */     return this.errormsg;
/*     */   }
/*     */ 
/*     */   public void setErrormsg(String value)
/*     */   {
/*  90 */     this.errormsg = value;
/*     */   }
/*     */ 
/*     */   public String getFolio()
/*     */   {
/* 102 */     return this.folio;
/*     */   }
/*     */ 
/*     */   public void setFolio(String value)
/*     */   {
/* 114 */     this.folio = value;
/*     */   }
/*     */ 
/*     */   public boolean isSuccess()
/*     */   {
/* 122 */     return this.success;
/*     */   }
/*     */ 
/*     */   public void setSuccess(boolean value)
/*     */   {
/* 130 */     this.success = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.CanjeBoletoAbiertoADOERResp
 * JD-Core Version:    0.6.0
 */