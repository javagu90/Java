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
/*     */ @XmlType(name="canjeBoletoAbiertoADOADOResp", propOrder={"errorMessage", "errormsg", "exito", "folio", "success"})
/*     */ public class CanjeBoletoAbiertoADOADOResp
/*     */ {
/*     */   protected ErrorMessage errorMessage;
/*     */   protected String errormsg;
/*     */   protected boolean exito;
/*     */ 
/*     */   @XmlElement(required=true, nillable=true)
/*     */   protected List<Folio> folio;
/*     */   protected boolean success;
/*     */ 
/*     */   public ErrorMessage getErrorMessage()
/*     */   {
/*  61 */     return this.errorMessage;
/*     */   }
/*     */ 
/*     */   public void setErrorMessage(ErrorMessage value)
/*     */   {
/*  73 */     this.errorMessage = value;
/*     */   }
/*     */ 
/*     */   public String getErrormsg()
/*     */   {
/*  85 */     return this.errormsg;
/*     */   }
/*     */ 
/*     */   public void setErrormsg(String value)
/*     */   {
/*  97 */     this.errormsg = value;
/*     */   }
/*     */ 
/*     */   public boolean isExito()
/*     */   {
/* 105 */     return this.exito;
/*     */   }
/*     */ 
/*     */   public void setExito(boolean value)
/*     */   {
/* 113 */     this.exito = value;
/*     */   }
/*     */ 
/*     */   public List<Folio> getFolio()
/*     */   {
/* 139 */     if (this.folio == null) {
/* 140 */       this.folio = new ArrayList();
/*     */     }
/* 142 */     return this.folio;
/*     */   }
/*     */ 
/*     */   public boolean isSuccess()
/*     */   {
/* 150 */     return this.success;
/*     */   }
/*     */ 
/*     */   public void setSuccess(boolean value)
/*     */   {
/* 158 */     this.success = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.CanjeBoletoAbiertoADOADOResp
 * JD-Core Version:    0.6.0
 */