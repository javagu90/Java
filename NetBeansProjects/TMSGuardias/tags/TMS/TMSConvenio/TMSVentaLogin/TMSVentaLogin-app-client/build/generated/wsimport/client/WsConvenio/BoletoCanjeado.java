
package WsConvenio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for boletoCanjeado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="boletoCanjeado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bol_Ven" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="caja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ciudadVenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="claveCorrida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="empresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="folioBoleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="folioPreimpreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importeBoleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noAsiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombrePasajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="origen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="servicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoPasajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoTransaccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoVenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boletoCanjeado", propOrder = {
    "bolVen",
    "caja",
    "ciudadVenta",
    "claveCorrida",
    "destino",
    "empresa",
    "folioBoleto",
    "folioPreimpreso",
    "importeBoleto",
    "noAsiento",
    "nombrePasajero",
    "origen",
    "servicio",
    "tipoOperacion",
    "tipoPago",
    "tipoPasajero",
    "tipoTransaccion",
    "tipoVenta"
})
public class BoletoCanjeado {

    @XmlElement(name = "bol_Ven")
    protected String bolVen;
    protected String caja;
    protected String ciudadVenta;
    protected String claveCorrida;
    protected String destino;
    protected String empresa;
    protected String folioBoleto;
    protected String folioPreimpreso;
    protected String importeBoleto;
    protected String noAsiento;
    protected String nombrePasajero;
    protected String origen;
    protected String servicio;
    protected String tipoOperacion;
    protected String tipoPago;
    protected String tipoPasajero;
    protected String tipoTransaccion;
    protected String tipoVenta;

    /**
     * Gets the value of the bolVen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBolVen() {
        return bolVen;
    }

    /**
     * Sets the value of the bolVen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBolVen(String value) {
        this.bolVen = value;
    }

    /**
     * Gets the value of the caja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaja() {
        return caja;
    }

    /**
     * Sets the value of the caja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaja(String value) {
        this.caja = value;
    }

    /**
     * Gets the value of the ciudadVenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudadVenta() {
        return ciudadVenta;
    }

    /**
     * Sets the value of the ciudadVenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudadVenta(String value) {
        this.ciudadVenta = value;
    }

    /**
     * Gets the value of the claveCorrida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveCorrida() {
        return claveCorrida;
    }

    /**
     * Sets the value of the claveCorrida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveCorrida(String value) {
        this.claveCorrida = value;
    }

    /**
     * Gets the value of the destino property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Sets the value of the destino property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestino(String value) {
        this.destino = value;
    }

    /**
     * Gets the value of the empresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Sets the value of the empresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmpresa(String value) {
        this.empresa = value;
    }

    /**
     * Gets the value of the folioBoleto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioBoleto() {
        return folioBoleto;
    }

    /**
     * Sets the value of the folioBoleto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioBoleto(String value) {
        this.folioBoleto = value;
    }

    /**
     * Gets the value of the folioPreimpreso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioPreimpreso() {
        return folioPreimpreso;
    }

    /**
     * Sets the value of the folioPreimpreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioPreimpreso(String value) {
        this.folioPreimpreso = value;
    }

    /**
     * Gets the value of the importeBoleto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImporteBoleto() {
        return importeBoleto;
    }

    /**
     * Sets the value of the importeBoleto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImporteBoleto(String value) {
        this.importeBoleto = value;
    }

    /**
     * Gets the value of the noAsiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoAsiento() {
        return noAsiento;
    }

    /**
     * Sets the value of the noAsiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoAsiento(String value) {
        this.noAsiento = value;
    }

    /**
     * Gets the value of the nombrePasajero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombrePasajero() {
        return nombrePasajero;
    }

    /**
     * Sets the value of the nombrePasajero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombrePasajero(String value) {
        this.nombrePasajero = value;
    }

    /**
     * Gets the value of the origen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Sets the value of the origen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrigen(String value) {
        this.origen = value;
    }

    /**
     * Gets the value of the servicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * Sets the value of the servicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicio(String value) {
        this.servicio = value;
    }

    /**
     * Gets the value of the tipoOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Sets the value of the tipoOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoOperacion(String value) {
        this.tipoOperacion = value;
    }

    /**
     * Gets the value of the tipoPago property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * Sets the value of the tipoPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPago(String value) {
        this.tipoPago = value;
    }

    /**
     * Gets the value of the tipoPasajero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPasajero() {
        return tipoPasajero;
    }

    /**
     * Sets the value of the tipoPasajero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPasajero(String value) {
        this.tipoPasajero = value;
    }

    /**
     * Gets the value of the tipoTransaccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    /**
     * Sets the value of the tipoTransaccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoTransaccion(String value) {
        this.tipoTransaccion = value;
    }

    /**
     * Gets the value of the tipoVenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoVenta() {
        return tipoVenta;
    }

    /**
     * Sets the value of the tipoVenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoVenta(String value) {
        this.tipoVenta = value;
    }

}
