/*     */ package WsConvenio;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="tipoPasajero", propOrder={"cantidadDisponible", "tarifaRedondo", "tarifaSencilla", "tipoPasajero"})
/*     */ public class TipoPasajero
/*     */ {
/*     */   protected int cantidadDisponible;
/*     */   protected float tarifaRedondo;
/*     */   protected float tarifaSencilla;
/*     */   protected String tipoPasajero;
/*     */ 
/*     */   public int getCantidadDisponible()
/*     */   {
/*  50 */     return this.cantidadDisponible;
/*     */   }
/*     */ 
/*     */   public void setCantidadDisponible(int value)
/*     */   {
/*  58 */     this.cantidadDisponible = value;
/*     */   }
/*     */ 
/*     */   public float getTarifaRedondo()
/*     */   {
/*  66 */     return this.tarifaRedondo;
/*     */   }
/*     */ 
/*     */   public void setTarifaRedondo(float value)
/*     */   {
/*  74 */     this.tarifaRedondo = value;
/*     */   }
/*     */ 
/*     */   public float getTarifaSencilla()
/*     */   {
/*  82 */     return this.tarifaSencilla;
/*     */   }
/*     */ 
/*     */   public void setTarifaSencilla(float value)
/*     */   {
/*  90 */     this.tarifaSencilla = value;
/*     */   }
/*     */ 
/*     */   public String getTipoPasajero()
/*     */   {
/* 102 */     return this.tipoPasajero;
/*     */   }
/*     */ 
/*     */   public void setTipoPasajero(String value)
/*     */   {
/* 114 */     this.tipoPasajero = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.TipoPasajero
 * JD-Core Version:    0.6.0
 */