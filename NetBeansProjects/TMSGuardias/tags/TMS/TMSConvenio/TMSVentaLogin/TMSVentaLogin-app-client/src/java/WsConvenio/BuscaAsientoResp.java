/*     */ package WsConvenio;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="buscaAsientoResp", propOrder={"asientos", "errorMessage", "promocion", "success", "tipoPasajeroA", "tipoPasajeroC", "tipoPasajeroE", "tipoPasajeroM", "tipoPasajeroP", "tipoPasajeroS", "transactionId"})
/*     */ public class BuscaAsientoResp
/*     */ {
/*     */   protected String asientos;
/*     */   protected ErrorMessage errorMessage;
/*     */   protected boolean promocion;
/*     */   protected boolean success;
/*     */   protected TipoPasajero tipoPasajeroA;
/*     */   protected TipoPasajero tipoPasajeroC;
/*     */   protected TipoPasajero tipoPasajeroE;
/*     */   protected TipoPasajero tipoPasajeroM;
/*     */   protected TipoPasajero tipoPasajeroP;
/*     */   protected TipoPasajero tipoPasajeroS;
/*     */   protected String transactionId;
/*     */ 
/*     */   public String getAsientos()
/*     */   {
/*  75 */     return this.asientos;
/*     */   }
/*     */ 
/*     */   public void setAsientos(String value)
/*     */   {
/*  87 */     this.asientos = value;
/*     */   }
/*     */ 
/*     */   public ErrorMessage getErrorMessage()
/*     */   {
/*  99 */     return this.errorMessage;
/*     */   }
/*     */ 
/*     */   public void setErrorMessage(ErrorMessage value)
/*     */   {
/* 111 */     this.errorMessage = value;
/*     */   }
/*     */ 
/*     */   public boolean isPromocion()
/*     */   {
/* 119 */     return this.promocion;
/*     */   }
/*     */ 
/*     */   public void setPromocion(boolean value)
/*     */   {
/* 127 */     this.promocion = value;
/*     */   }
/*     */ 
/*     */   public boolean isSuccess()
/*     */   {
/* 135 */     return this.success;
/*     */   }
/*     */ 
/*     */   public void setSuccess(boolean value)
/*     */   {
/* 143 */     this.success = value;
/*     */   }
/*     */ 
/*     */   public TipoPasajero getTipoPasajeroA()
/*     */   {
/* 155 */     return this.tipoPasajeroA;
/*     */   }
/*     */ 
/*     */   public void setTipoPasajeroA(TipoPasajero value)
/*     */   {
/* 167 */     this.tipoPasajeroA = value;
/*     */   }
/*     */ 
/*     */   public TipoPasajero getTipoPasajeroC()
/*     */   {
/* 179 */     return this.tipoPasajeroC;
/*     */   }
/*     */ 
/*     */   public void setTipoPasajeroC(TipoPasajero value)
/*     */   {
/* 191 */     this.tipoPasajeroC = value;
/*     */   }
/*     */ 
/*     */   public TipoPasajero getTipoPasajeroE()
/*     */   {
/* 203 */     return this.tipoPasajeroE;
/*     */   }
/*     */ 
/*     */   public void setTipoPasajeroE(TipoPasajero value)
/*     */   {
/* 215 */     this.tipoPasajeroE = value;
/*     */   }
/*     */ 
/*     */   public TipoPasajero getTipoPasajeroM()
/*     */   {
/* 227 */     return this.tipoPasajeroM;
/*     */   }
/*     */ 
/*     */   public void setTipoPasajeroM(TipoPasajero value)
/*     */   {
/* 239 */     this.tipoPasajeroM = value;
/*     */   }
/*     */ 
/*     */   public TipoPasajero getTipoPasajeroP()
/*     */   {
/* 251 */     return this.tipoPasajeroP;
/*     */   }
/*     */ 
/*     */   public void setTipoPasajeroP(TipoPasajero value)
/*     */   {
/* 263 */     this.tipoPasajeroP = value;
/*     */   }
/*     */ 
/*     */   public TipoPasajero getTipoPasajeroS()
/*     */   {
/* 275 */     return this.tipoPasajeroS;
/*     */   }
/*     */ 
/*     */   public void setTipoPasajeroS(TipoPasajero value)
/*     */   {
/* 287 */     this.tipoPasajeroS = value;
/*     */   }
/*     */ 
/*     */   public String getTransactionId()
/*     */   {
/* 299 */     return this.transactionId;
/*     */   }
/*     */ 
/*     */   public void setTransactionId(String value)
/*     */   {
/* 311 */     this.transactionId = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.BuscaAsientoResp
 * JD-Core Version:    0.6.0
 */