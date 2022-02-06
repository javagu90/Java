
package WsConvenio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for boletoCVB complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="boletoCVB">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bolVenId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="caja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ciudadVenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="claveCorrida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="folioAbierto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="folioPreimpreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importeBoleto" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="marca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombrePasajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroAsiento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="origen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoBoleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoOperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoPasajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boletoCVB", propOrder = {
    "bolVenId",
    "caja",
    "ciudadVenta",
    "claveCorrida",
    "destino",
    "folioAbierto",
    "folioPreimpreso",
    "importeBoleto",
    "marca",
    "nombrePasajero",
    "numeroAsiento",
    "origen",
    "tipoBoleto",
    "tipoOperacion",
    "tipoPago",
    "tipoPasajero"
})
public class BoletoCVB {

    protected String bolVenId;
    protected String caja;
    protected String ciudadVenta;
    protected String claveCorrida;
    protected String destino;
    protected String folioAbierto;
    protected String folioPreimpreso;
    protected float importeBoleto;
    protected String marca;
    protected String nombrePasajero;
    protected int numeroAsiento;
    protected String origen;
    protected String tipoBoleto;
    protected String tipoOperacion;
    protected String tipoPago;
    protected String tipoPasajero;

    /**
     * Gets the value of the bolVenId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBolVenId() {
        return bolVenId;
    }

    /**
     * Sets the value of the bolVenId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBolVenId(String value) {
        this.bolVenId = value;
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
     * Gets the value of the folioAbierto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolioAbierto() {
        return folioAbierto;
    }

    /**
     * Sets the value of the folioAbierto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolioAbierto(String value) {
        this.folioAbierto = value;
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
     */
    public float getImporteBoleto() {
        return importeBoleto;
    }

    /**
     * Sets the value of the importeBoleto property.
     * 
     */
    public void setImporteBoleto(float value) {
        this.importeBoleto = value;
    }

    /**
     * Gets the value of the marca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Sets the value of the marca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarca(String value) {
        this.marca = value;
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
     * Gets the value of the numeroAsiento property.
     * 
     */
    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    /**
     * Sets the value of the numeroAsiento property.
     * 
     */
    public void setNumeroAsiento(int value) {
        this.numeroAsiento = value;
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
     * Gets the value of the tipoBoleto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoBoleto() {
        return tipoBoleto;
    }

    /**
     * Sets the value of the tipoBoleto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoBoleto(String value) {
        this.tipoBoleto = value;
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

}
