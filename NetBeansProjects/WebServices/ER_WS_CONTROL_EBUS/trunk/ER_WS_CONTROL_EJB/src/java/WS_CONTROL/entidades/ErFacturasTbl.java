/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author brojas
 */
@Entity
@Table(name = "ER_FACTURAS_TBL")
/*@NamedQueries({
    @NamedQuery(name = "ErFacturasTbl.findAll", query = "SELECT e FROM ErFacturasTbl e"),
    @NamedQuery(name = "ErFacturasTbl.findByFacturaId", query = "SELECT e FROM ErFacturasTbl e WHERE e.facturaId = :facturaId"),
    @NamedQuery(name = "ErFacturasTbl.findByClaveFacturaElec", query = "SELECT e FROM ErFacturasTbl e WHERE e.claveFacturaElec = :claveFacturaElec"),
    @NamedQuery(name = "ErFacturasTbl.findByClienteId", query = "SELECT e FROM ErFacturasTbl e WHERE e.clienteId = :clienteId"),
    @NamedQuery(name = "ErFacturasTbl.findBySubtotal", query = "SELECT e FROM ErFacturasTbl e WHERE e.subtotal = :subtotal"),
    @NamedQuery(name = "ErFacturasTbl.findByIva", query = "SELECT e FROM ErFacturasTbl e WHERE e.iva = :iva"),
    @NamedQuery(name = "ErFacturasTbl.findByIvaRetenido", query = "SELECT e FROM ErFacturasTbl e WHERE e.ivaRetenido = :ivaRetenido"),
    @NamedQuery(name = "ErFacturasTbl.findByRet5millar", query = "SELECT e FROM ErFacturasTbl e WHERE e.ret5millar = :ret5millar"),
    @NamedQuery(name = "ErFacturasTbl.findByDescuento", query = "SELECT e FROM ErFacturasTbl e WHERE e.descuento = :descuento"),
    @NamedQuery(name = "ErFacturasTbl.findByTotal", query = "SELECT e FROM ErFacturasTbl e WHERE e.total = :total"),
    @NamedQuery(name = "ErFacturasTbl.findByRazonSocial", query = "SELECT e FROM ErFacturasTbl e WHERE e.razonSocial = :razonSocial"),
    @NamedQuery(name = "ErFacturasTbl.findByRfc", query = "SELECT e FROM ErFacturasTbl e WHERE e.rfc = :rfc"),
    @NamedQuery(name = "ErFacturasTbl.findByConcepto", query = "SELECT e FROM ErFacturasTbl e WHERE e.concepto = :concepto"),
    @NamedQuery(name = "ErFacturasTbl.findByObservaciones", query = "SELECT e FROM ErFacturasTbl e WHERE e.observaciones = :observaciones"),
    @NamedQuery(name = "ErFacturasTbl.findByFecha", query = "SELECT e FROM ErFacturasTbl e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "ErFacturasTbl.findByTelefono", query = "SELECT e FROM ErFacturasTbl e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "ErFacturasTbl.findByDomicilio", query = "SELECT e FROM ErFacturasTbl e WHERE e.domicilio = :domicilio"),
    @NamedQuery(name = "ErFacturasTbl.findByColonia", query = "SELECT e FROM ErFacturasTbl e WHERE e.colonia = :colonia"),
    @NamedQuery(name = "ErFacturasTbl.findByCodigopostal", query = "SELECT e FROM ErFacturasTbl e WHERE e.codigopostal = :codigopostal"),
    @NamedQuery(name = "ErFacturasTbl.findByCiudad", query = "SELECT e FROM ErFacturasTbl e WHERE e.ciudad = :ciudad"),
    @NamedQuery(name = "ErFacturasTbl.findByEstado", query = "SELECT e FROM ErFacturasTbl e WHERE e.estado = :estado"),
    @NamedQuery(name = "ErFacturasTbl.findByPais", query = "SELECT e FROM ErFacturasTbl e WHERE e.pais = :pais"),
    @NamedQuery(name = "ErFacturasTbl.findByFechaVencimiento", query = "SELECT e FROM ErFacturasTbl e WHERE e.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "ErFacturasTbl.findByFechaContable", query = "SELECT e FROM ErFacturasTbl e WHERE e.fechaContable = :fechaContable"),
    @NamedQuery(name = "ErFacturasTbl.findByEstadoFactura", query = "SELECT e FROM ErFacturasTbl e WHERE e.estadoFactura = :estadoFactura"),
    @NamedQuery(name = "ErFacturasTbl.findByEMail", query = "SELECT e FROM ErFacturasTbl e WHERE e.eMail = :eMail"),
    @NamedQuery(name = "ErFacturasTbl.findByRutaPdf", query = "SELECT e FROM ErFacturasTbl e WHERE e.rutaPdf = :rutaPdf"),
    @NamedQuery(name = "ErFacturasTbl.findByRutaXml", query = "SELECT e FROM ErFacturasTbl e WHERE e.rutaXml = :rutaXml"),
    @NamedQuery(name = "ErFacturasTbl.findByAdicional1", query = "SELECT e FROM ErFacturasTbl e WHERE e.adicional1 = :adicional1"),
    @NamedQuery(name = "ErFacturasTbl.findByAdicional2", query = "SELECT e FROM ErFacturasTbl e WHERE e.adicional2 = :adicional2"),
    @NamedQuery(name = "ErFacturasTbl.findByAdicional3", query = "SELECT e FROM ErFacturasTbl e WHERE e.adicional3 = :adicional3"),
    @NamedQuery(name = "ErFacturasTbl.findByAdicional4", query = "SELECT e FROM ErFacturasTbl e WHERE e.adicional4 = :adicional4"),
    @NamedQuery(name = "ErFacturasTbl.findByAdicional5", query = "SELECT e FROM ErFacturasTbl e WHERE e.adicional5 = :adicional5"),
    @NamedQuery(name = "ErFacturasTbl.findByAdicional6", query = "SELECT e FROM ErFacturasTbl e WHERE e.adicional6 = :adicional6"),
    @NamedQuery(name = "ErFacturasTbl.findByAdicional7", query = "SELECT e FROM ErFacturasTbl e WHERE e.adicional7 = :adicional7"),
    @NamedQuery(name = "ErFacturasTbl.findByAdicional8", query = "SELECT e FROM ErFacturasTbl e WHERE e.adicional8 = :adicional8"),
    @NamedQuery(name = "ErFacturasTbl.findByAdicional9", query = "SELECT e FROM ErFacturasTbl e WHERE e.adicional9 = :adicional9"),
    @NamedQuery(name = "ErFacturasTbl.findByAdicional10", query = "SELECT e FROM ErFacturasTbl e WHERE e.adicional10 = :adicional10"),
    @NamedQuery(name = "ErFacturasTbl.findByCreadoPor", query = "SELECT e FROM ErFacturasTbl e WHERE e.creadoPor = :creadoPor"),
    @NamedQuery(name = "ErFacturasTbl.findByFechaCreacion", query = "SELECT e FROM ErFacturasTbl e WHERE e.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ErFacturasTbl.findByUltimaActualizacionPor", query = "SELECT e FROM ErFacturasTbl e WHERE e.ultimaActualizacionPor = :ultimaActualizacionPor"),
    @NamedQuery(name = "ErFacturasTbl.findByUltimaFechaActualizacion", query = "SELECT e FROM ErFacturasTbl e WHERE e.ultimaFechaActualizacion = :ultimaFechaActualizacion"),
    @NamedQuery(name = "ErFacturasTbl.findByReplicadoAr", query = "SELECT e FROM ErFacturasTbl e WHERE e.replicadoAr = :replicadoAr")})
 *
 */
public class ErFacturasTbl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = " FACTURA_ID")
    private BigDecimal facturaId;
    @Column(name = " CLAVE_FACTURA_ELEC")
    private String claveFacturaElec="";

    @Column(name = " FOLIO_FACTURA")
    private String folio_factura="";

    private String  UnidadNegocio=""; // PAQUER, TMS, VENTOUR
    private String  UnidaFactura="";   // No Aplica, Pza, Servicio

    @Column(name = " CLIENTE_ID")
    private BigInteger clienteId;
    //private BigInteger clienteId=BigInteger.valueOf(Long.valueOf("-1"));
    @Column(name = " SUBTOTAL")
    private BigDecimal subtotal= BigDecimal.valueOf(Double.valueOf("0"));
    @Column(name = " IVA")
    private BigDecimal iva= BigDecimal.valueOf(Double.valueOf("0"));
    @Column(name = " IVA_RETENIDO")
    private BigDecimal ivaRetenido= BigDecimal.valueOf(Double.valueOf("0"));
    @Column(name = " MONTO_IVARETENIDO")
    private BigDecimal montoRetenido= BigDecimal.valueOf(Double.valueOf("0"));
    @Column(name = " RET_5MILLAR")
    private BigDecimal ret5millar= BigDecimal.valueOf(Double.valueOf("0"));
    @Column(name = " DESCUENTO")
    private BigDecimal descuento= BigDecimal.valueOf(Double.valueOf("0"));
    @Column(name = " TOTAL")
    private BigDecimal total= BigDecimal.valueOf(Double.valueOf("0"));
    @Column(name = " RAZON_SOCIAL")
    private String razonSocial="";
    @Column(name = " RFC")
    private String rfc="";
    @Column(name = " CONCEPTO")
    private String concepto="";
    @Column(name = " OBSERVACIONES")
    private String observaciones="";
    @Column(name = " FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = " TELEFONO")
    private String telefono="";
    @Column(name = " DOMICILIO")
  
    private String domicilio="";
    @Column(name = " COLONIA")
    private String colonia="";
    @Column(name = " CODIGOPOSTAL")
    private String codigopostal="";
    @Column(name = " CIUDAD")
    private String ciudad="";
    @Column(name = " ESTADO")
    private String estado="";
    @Column(name = " PAIS")
    private String pais="";
    @Column(name = " FECHA_VENCIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Column(name = " FECHA_CONTABLE")
    @Temporal(TemporalType.DATE)
    private Date fechaContable;
    @Column(name = " ESTADO_FACTURA")
    private String estadoFactura="";
    @Column(name = " E_MAIL")
    private String eMail="";
    @Column(name = " RUTA_PDF")
    private String rutaPdf="";
    @Column(name = " RUTA_XML")
    private String rutaXml="";
   /* @Column(name = " ADICIONAL1")
    private String adicional1;
    @Column(name = " ADICIONAL2")
    private String adicional2;
    @Column(name = " ADICIONAL3")
    private String adicional3;
    @Column(name = " ADICIONAL4")
    private String adicional4;
    @Column(name = " ADICIONAL5")
    private String adicional5;
    @Column(name = " ADICIONAL6")
    private String adicional6;
    @Column(name = " ADICIONAL7")
    private String adicional7;
    @Column(name = " ADICIONAL8")
    private String adicional8;
    @Column(name = " ADICIONAL9")
    private String adicional9;
    @Column(name = " ADICIONAL10")
    private String adicional10;
    @Column(name = " CREADO_POR")
    private BigInteger creadoPor;
    @Column(name = " FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
    @Column(name = " ULTIMA_ACTUALIZACION_POR")
    private BigInteger ultimaActualizacionPor;
    @Column(name = " ULTIMA_FECHA_ACTUALIZACION")
    @Temporal(TemporalType.DATE)
    private Date ultimaFechaActualizacion;
    @Column(name = " REPLICADO_AR")
    private String replicadoAr;*/
     private  String No_Ext="";
    private  String No_Int="";

    private String  Producto="";
  
    private String MetodoPago="";
    private String NumCuenta ="";
    private String TipoComprobante="";
    // datos Adenda
    private String CodProveedor="";
    private String Solicitante="";
    private String CorreoSolic="";
    private String OrdenCompra="";
   /* private String Adicional1;
    private String Adicional2;
    private String Adicional3;
    private String Adicional4;
    private String Adicional5;*/

    public ErFacturasTbl() {
    }


     public ErFacturasTbl(String[] campos) {
        DateFormat formatter ;
        Date date ;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
         try {

          System.out.println("Datos Factura  "+campos);
        this.setFacturaId(new BigDecimal(campos[0]));
        this.setClaveFacturaElec(campos[1].replace('~', '|'));
        if (!campos[2].equals(""))
            this.setClienteId(new BigInteger(campos[2]));
        this.setSubtotal(new BigDecimal(campos[3]));
        this.setIva(new BigDecimal(campos[4]));
        this.setIvaRetenido(new BigDecimal(campos[5]));
        this.setRet5millar(new BigDecimal(campos[6]));
        this.setDescuento(new BigDecimal(campos[7]));
        this.setTotal(new BigDecimal(campos[8]));
        this.setRazonSocial(campos[9]);
        this.setRfc(campos[10]);
        this.setConcepto(campos[11]);
        this.setObservaciones(campos[12]);

        try {
          if(campos[13]!=null && !campos[13].trim().equals(""))
        {
            System.out.println("Fecha  "+campos[13].trim());
            this.setFecha((Date)formatter.parse(campos[13].trim()));
         }

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setTelefono(campos[14]);
        this.setDomicilio(campos[15]);
        this.setColonia(campos[16]);
        this.setCodigopostal(campos[17]);
        this.setCiudad(campos[18]);
        this.setEstado(campos[19]);
        this.setPais(campos[20]);
         try {
          if(campos[21]!=null && !campos[21].trim().equals(""))
        {
            System.out.println("Fecha  "+campos[21].trim());
            this.setFechaVencimiento((Date)formatter.parse(campos[21].trim()));
         }
           if(campos[22]!=null && !campos[22].trim().equals(""))
        {
            System.out.println("Fecha  "+campos[22].trim());
            this.setFechaContable((Date)formatter.parse(campos[22].trim()));
         }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setEstadoFactura(campos[23]);
        this.setEMail(campos[24]);
        this.setFolio_factura(campos[25]);
        this.setUnidadNegocio(campos[26]);
        this.setMontoRetenido(new BigDecimal(campos[27]));

        this.setRutaPdf(campos[28]);
        this.setRutaXml(campos[29]);
      } catch (Exception e) {
          e.printStackTrace();
         }
    }




    public ErFacturasTbl(BigDecimal facturaId) {
        this.facturaId = facturaId;
    }

    public BigDecimal getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(BigDecimal facturaId) {
        this.facturaId = facturaId;
    }

    public String getClaveFacturaElec() {
        return claveFacturaElec;
    }

    public void setClaveFacturaElec(String claveFacturaElec) {
        this.claveFacturaElec = claveFacturaElec;
    }

    public BigInteger getClienteId() {
        return clienteId;
    }

    public void setClienteId(BigInteger clienteId) {
        this.clienteId = clienteId;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getIvaRetenido() {
        return ivaRetenido;
    }

    public void setIvaRetenido(BigDecimal ivaRetenido) {
        this.ivaRetenido = ivaRetenido;
    }

    public BigDecimal getRet5millar() {
        return ret5millar;
    }

    public void setRet5millar(BigDecimal ret5millar) {
        this.ret5millar = ret5millar;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(String codigopostal) {
        this.codigopostal = codigopostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getFechaContable() {
        return fechaContable;
    }

    public void setFechaContable(Date fechaContable) {
        this.fechaContable = fechaContable;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getRutaPdf() {
        return rutaPdf;
    }

    public void setRutaPdf(String rutaPdf) {
        this.rutaPdf = rutaPdf;
    }

    public String getRutaXml() {
        return rutaXml;
    }

    public void setRutaXml(String rutaXml) {
        this.rutaXml = rutaXml;
    }
/*
    public String getAdicional1() {
        return adicional1;
    }

    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    public String getAdicional2() {
        return adicional2;
    }

    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    public String getAdicional3() {
        return adicional3;
    }

    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    public String getAdicional4() {
        return adicional4;
    }

    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    public String getAdicional5() {
        return adicional5;
    }

    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    public String getAdicional6() {
        return adicional6;
    }

    public void setAdicional6(String adicional6) {
        this.adicional6 = adicional6;
    }

    public String getAdicional7() {
        return adicional7;
    }

    public void setAdicional7(String adicional7) {
        this.adicional7 = adicional7;
    }

    public String getAdicional8() {
        return adicional8;
    }

    public void setAdicional8(String adicional8) {
        this.adicional8 = adicional8;
    }

    public String getAdicional9() {
        return adicional9;
    }

    public void setAdicional9(String adicional9) {
        this.adicional9 = adicional9;
    }

    public String getAdicional10() {
        return adicional10;
    }

    public void setAdicional10(String adicional10) {
        this.adicional10 = adicional10;
    }

    public BigInteger getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(BigInteger creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public BigInteger getUltimaActualizacionPor() {
        return ultimaActualizacionPor;
    }

    public void setUltimaActualizacionPor(BigInteger ultimaActualizacionPor) {
        this.ultimaActualizacionPor = ultimaActualizacionPor;
    }

    public Date getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

    public void setUltimaFechaActualizacion(Date ultimaFechaActualizacion) {
        this.ultimaFechaActualizacion = ultimaFechaActualizacion;
    }

    public String getReplicadoAr() {
        return replicadoAr;
    }

    public void setReplicadoAr(String replicadoAr) {
        this.replicadoAr = replicadoAr;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facturaId != null ? facturaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErFacturasTbl)) {
            return false;
        }
        ErFacturasTbl other = (ErFacturasTbl) object;
        if ((this.facturaId == null && other.facturaId != null) || (this.facturaId != null && !this.facturaId.equals(other.facturaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WS_CONTROL.entidades.ErFacturasTbl[facturaId=" + facturaId + "]";
    }

    /**
     * @return the NumCuenta
     */
    public String getNumCuenta() {
        return NumCuenta;
    }

    /**
     * @param NumCuenta the NumCuenta to set
     */
    public void setNumCuenta(String NumCuenta) {
        this.NumCuenta = NumCuenta;
    }

    /**
     * @return the CodProveedor
     */
    public String getCodProveedor() {
        return CodProveedor;
    }

    /**
     * @param CodProveedor the CodProveedor to set
     */
    public void setCodProveedor(String CodProveedor) {
        this.CodProveedor = CodProveedor;
    }

    /**
     * @return the Solicitante
     */
    public String getSolicitante() {
        return Solicitante;
    }

    /**
     * @param Solicitante the Solicitante to set
     */
    public void setSolicitante(String Solicitante) {
        this.Solicitante = Solicitante;
    }

    /**
     * @return the CorreoSolic
     */
    public String getCorreoSolic() {
        return CorreoSolic;
    }

    /**
     * @param CorreoSolic the CorreoSolic to set
     */
    public void setCorreoSolic(String CorreoSolic) {
        this.CorreoSolic = CorreoSolic;
    }

    /**
     * @return the OrdenCompra
     */
    public String getOrdenCompra() {
        return OrdenCompra;
    }

    /**
     * @param OrdenCompra the OrdenCompra to set
     */
    public void setOrdenCompra(String OrdenCompra) {
        this.OrdenCompra = OrdenCompra;
    }

   
    /**
     * @return the MetodoPago
     */
    public String getMetodoPago() {
        return MetodoPago;
    }

    /**
     * @param MetodoPago the MetodoPago to set
     */
    public void setMetodoPago(String MetodoPago) {
        this.MetodoPago = MetodoPago;
    }

   
   

    /**
     * @return the Producto
     */
    public String getProducto() {
        return Producto;
    }

    /**
     * @param Producto the Producto to set
     */
    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

   

    /**
     * @return the No_Ext
     */
    public String getNo_Ext() {
        return No_Ext;
    }

    /**
     * @param No_Ext the No_Ext to set
     */
    public void setNo_Ext(String No_Ext) {
        this.No_Ext = No_Ext;
    }

    /**
     * @return the No_Int
     */
    public String getNo_Int() {
        return No_Int;
    }

    /**
     * @param No_Int the No_Int to set
     */
    public void setNo_Int(String No_Int) {
        this.No_Int = No_Int;
    }

    /**
     * @return the folio_factura
     */
    public String getFolio_factura() {
        return folio_factura;
    }

    /**
     * @param folio_factura the folio_factura to set
     */
    public void setFolio_factura(String folio_factura) {
        this.folio_factura = folio_factura;
    }

    /**
     * @return the UnidadNegocio
     */
    public String getUnidadNegocio() {
        return UnidadNegocio;
    }

    /**
     * @param UnidadNegocio the UnidadNegocio to set
     */
    public void setUnidadNegocio(String UnidadNegocio) {
        this.UnidadNegocio = UnidadNegocio;
    }

    /**
     * @return the UnidaFactura
     */
    public String getUnidaFactura() {
        return UnidaFactura;
    }

    /**
     * @param UnidaFactura the UnidaFactura to set
     */
    public void setUnidaFactura(String UnidaFactura) {
        this.UnidaFactura = UnidaFactura;
    }

    /**
     * @return the montoRetenido
     */
    public BigDecimal getMontoRetenido() {
        return montoRetenido;
    }

    /**
     * @param montoRetenido the montoRetenido to set
     */
    public void setMontoRetenido(BigDecimal montoRetenido) {
        this.montoRetenido = montoRetenido;
    }

    /**
     * @return the TipoComprobante
     */
    public String getTipoComprobante() {
        return TipoComprobante;
    }

    /**
     * @param TipoComprobante the TipoComprobante to set
     */
    public void setTipoComprobante(String TipoComprobante) {
        this.TipoComprobante = TipoComprobante;
    }

    

}
