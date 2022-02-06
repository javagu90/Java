
package WsConvenio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for asiento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="asiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bolven_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroAsiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoPasajero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valorTarifa" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "asiento", propOrder = {
    "bolvenId",
    "numeroAsiento",
    "tipoPasajero",
    "valorTarifa"
})
public class Asiento {

    @XmlElement(name = "bolven_id")
    protected String bolvenId;
    protected String numeroAsiento;
    protected String tipoPasajero;
    protected float valorTarifa;

    /**
     * Gets the value of the bolvenId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBolvenId() {
        return bolvenId;
    }

    /**
     * Sets the value of the bolvenId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBolvenId(String value) {
        this.bolvenId = value;
    }

    /**
     * Gets the value of the numeroAsiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    /**
     * Sets the value of the numeroAsiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroAsiento(String value) {
        this.numeroAsiento = value;
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
     * Gets the value of the valorTarifa property.
     * 
     */
    public float getValorTarifa() {
        return valorTarifa;
    }

    /**
     * Sets the value of the valorTarifa property.
     * 
     */
    public void setValorTarifa(float value) {
        this.valorTarifa = value;
    }

}
