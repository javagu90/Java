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
/*     */ @XmlType(name="buscaCorridaResp", propOrder={"corridas", "errorMessage", "success", "transaccionId"})
/*     */ public class BuscaCorridaResp
/*     */ {
/*     */ 
/*     */   @XmlElement(required=true, nillable=true)
/*     */   protected List<Corrida> corridas;
/*     */   protected ErrorMessage errorMessage;
/*     */   protected boolean success;
/*     */   protected String transaccionId;
/*     */ 
/*     */   public List<Corrida> getCorridas()
/*     */   {
/*  72 */     if (this.corridas == null) {
/*  73 */       this.corridas = new ArrayList();
/*     */     }
/*  75 */     return this.corridas;
/*     */   }
/*     */ 
/*     */   public ErrorMessage getErrorMessage()
/*     */   {
/*  87 */     return this.errorMessage;
/*     */   }
/*     */ 
/*     */   public void setErrorMessage(ErrorMessage value)
/*     */   {
/*  99 */     this.errorMessage = value;
/*     */   }
/*     */ 
/*     */   public boolean isSuccess()
/*     */   {
/* 107 */     return this.success;
/*     */   }
/*     */ 
/*     */   public void setSuccess(boolean value)
/*     */   {
/* 115 */     this.success = value;
/*     */   }
/*     */ 
/*     */   public String getTransaccionId()
/*     */   {
/* 127 */     return this.transaccionId;
/*     */   }
/*     */ 
/*     */   public void setTransaccionId(String value)
/*     */   {
/* 139 */     this.transaccionId = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.BuscaCorridaResp
 * JD-Core Version:    0.6.0
 */