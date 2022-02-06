/*     */ package WsConvenio;
/*     */ 
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlType;
/*     */ import javax.xml.datatype.XMLGregorianCalendar;
/*     */ 
/*     */ @XmlAccessorType(XmlAccessType.FIELD)
/*     */ @XmlType(name="buscaCorrida", propOrder={"caja", "marca", "servicio", "origen", "fecha", "destino"})
/*     */ public class BuscaCorrida
/*     */ {
/*     */   protected String caja;
/*     */   protected String marca;
/*     */   protected String servicio;
/*     */   protected String origen;
/*     */   protected XMLGregorianCalendar fecha;
/*     */   protected String destino;
/*     */ 
/*     */   public String getCaja()
/*     */   {
/*  61 */     return this.caja;
/*     */   }
/*     */ 
/*     */   public void setCaja(String value)
/*     */   {
/*  73 */     this.caja = value;
/*     */   }
/*     */ 
/*     */   public String getMarca()
/*     */   {
/*  85 */     return this.marca;
/*     */   }
/*     */ 
/*     */   public void setMarca(String value)
/*     */   {
/*  97 */     this.marca = value;
/*     */   }
/*     */ 
/*     */   public String getServicio()
/*     */   {
/* 109 */     return this.servicio;
/*     */   }
/*     */ 
/*     */   public void setServicio(String value)
/*     */   {
/* 121 */     this.servicio = value;
/*     */   }
/*     */ 
/*     */   public String getOrigen()
/*     */   {
/* 133 */     return this.origen;
/*     */   }
/*     */ 
/*     */   public void setOrigen(String value)
/*     */   {
/* 145 */     this.origen = value;
/*     */   }
/*     */ 
/*     */   public XMLGregorianCalendar getFecha()
/*     */   {
/* 157 */     return this.fecha;
/*     */   }
/*     */ 
/*     */   public void setFecha(XMLGregorianCalendar value)
/*     */   {
/* 169 */     this.fecha = value;
/*     */   }
/*     */ 
/*     */   public String getDestino()
/*     */   {
/* 181 */     return this.destino;
/*     */   }
/*     */ 
/*     */   public void setDestino(String value)
/*     */   {
/* 193 */     this.destino = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\vgonzalez.ESTRELLAROJA\Documents\N\
 * Qualified Name:     WsConvenio.BuscaCorrida
 * JD-Core Version:    0.6.0
 */