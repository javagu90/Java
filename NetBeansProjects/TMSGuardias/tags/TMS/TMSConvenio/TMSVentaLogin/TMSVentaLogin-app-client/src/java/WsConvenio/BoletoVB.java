/*     */ package WsConvenio;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="boletoVB", propOrder={"destino", "folioBoleto", "nombrePasajero", "numeroAsiento", "origen"})
/*     */ public class BoletoVB
/*     */ {
/*     */   protected String destino;
/*     */   protected String folioBoleto;
/*     */   protected String nombrePasajero;
/*     */   protected int numeroAsiento;
/*     */   protected String origen;
/*     */ 
/*     */   public String getDestino()
/*     */   {
/*  57 */     return this.destino;
/*     */   }
/*     */ 
/*     */   public void setDestino(String value)
/*     */   {
/*  69 */     this.destino = value;
/*     */   }
/*     */ 
/*     */   public String getFolioBoleto()
/*     */   {
/*  81 */     return this.folioBoleto;
/*     */   }
/*     */ 
/*     */   public void setFolioBoleto(String value)
/*     */   {
/*  93 */     this.folioBoleto = value;
/*     */   }
/*     */ 
/*     */   public String getNombrePasajero()
/*     */   {
/* 105 */     return this.nombrePasajero;
/*     */   }
/*     */ 
/*     */   public void setNombrePasajero(String value)
/*     */   {
/* 117 */     this.nombrePasajero = value;
/*     */   }
/*     */ 
/*     */   public int getNumeroAsiento()
/*     */   {
/* 125 */     return this.numeroAsiento;
/*     */   }
/*     */ 
/*     */   public void setNumeroAsiento(int value)
/*     */   {
/* 133 */     this.numeroAsiento = value;
/*     */   }
/*     */ 
/*     */   public String getOrigen()
/*     */   {
/* 145 */     return this.origen;
/*     */   }
/*     */ 
/*     */   public void setOrigen(String value)
/*     */   {
/* 157 */     this.origen = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.BoletoVB
 * JD-Core Version:    0.6.0
 */