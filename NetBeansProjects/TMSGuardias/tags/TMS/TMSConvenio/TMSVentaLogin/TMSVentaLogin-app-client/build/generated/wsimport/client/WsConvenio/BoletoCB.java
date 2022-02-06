
package WsConvenio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for boletoCB complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="boletoCB">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="claseServicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corrida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="folioPreimpreso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formaReembolso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motivoCancelacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noAsiento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombPasajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="origen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pagoBolven" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoBoleto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalDevolver" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boletoCB", propOrder = {
    "claseServicio",
    "corrida",
    "destino",
    "fecha",
    "folioPreimpreso",
    "formaReembolso",
    "hora",
    "marca",
    "motivoCancelacion",
    "noAsiento",
    "nombPasajero",
    "origen",
    "pagoBolven",
    "tipoBoleto",
    "totalDevolver"
})
public class BoletoCB {

    protected String claseServicio;
    protected String corrida;
    protected String destino;
    protected XMLGregorianCalendar fecha;
    protected String folioPreimpreso;
    protected String formaReembolso;
    protected String hora;
    protected String marca;
    protected String motivoCancelacion;
    protected int noAsiento;
    protected String nombPasajero;
    protected String origen;
    protected String pagoBolven;
    protected String tipoBoleto;
    protected float totalDevolver;

    /**
     * Gets the value of the claseServicio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaseServicio() {
        return claseServicio;
    }

    /**
     * Sets the value of the claseServicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaseServicio(String value) {
        this.claseServicio = value;
    }

    /**
     * Gets the value of the corrida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrida() {
        return corrida;
    }

    /**
     * Sets the value of the corrida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrida(String value) {
        this.corrida = value;
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
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecha(XMLGregorianCalendar value) {
        this.fecha = value;
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
     * Gets the value of the formaReembolso property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormaReembolso() {
        return formaReembolso;
    }

    /**
     * Sets the value of the formaReembolso property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormaReembolso(String value) {
        this.formaReembolso = value;
    }

    /**
     * Gets the value of the hora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHora() {
        return hora;
    }

    /**
     * Sets the value of the hora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHora(String value) {
        this.hora = value;
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
     * Gets the value of the motivoCancelacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    /**
     * Sets the value of the motivoCancelacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivoCancelacion(String value) {
        this.motivoCancelacion = value;
    }

    /**
     * Gets the value of the noAsiento property.
     * 
     */
    public int getNoAsiento() {
        return noAsiento;
    }

    /**
     * Sets the value of the noAsiento property.
     * 
     */
    public void setNoAsiento(int value) {
        this.noAsiento = value;
    }

    /**
     * Gets the value of the nombPasajero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombPasajero() {
        return nombPasajero;
    }

    /**
     * Sets the value of the nombPasajero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombPasajero(String value) {
        this.nombPasajero = value;
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
     * Gets the value of the pagoBolven property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagoBolven() {
        return pagoBolven;
    }

    /**
     * Sets the value of the pagoBolven property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagoBolven(String value) {
        this.pagoBolven = value;
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
     * Gets the value of the totalDevolver property.
     * 
     */
    public float getTotalDevolver() {
        return totalDevolver;
    }

    /**
     * Sets the value of the totalDevolver property.
     * 
     */
    public void setTotalDevolver(float value) {
        this.totalDevolver = value;
    }

}
