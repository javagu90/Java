
package wsLealtad;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for informacionconPuntosResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="informacionconPuntosResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="informacionCliente" type="{http://wsTMSSocioIntimo/}informacionCliente" minOccurs="0"/>
 *         &lt;element name="status" type="{http://wsTMSSocioIntimo/}statusType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "informacionconPuntosResponse", propOrder = {
    "informacionCliente",
    "status"
})
public class InformacionconPuntosResponse {

    protected InformacionCliente informacionCliente;
    protected StatusType status;

    /**
     * Gets the value of the informacionCliente property.
     * 
     * @return
     *     possible object is
     *     {@link InformacionCliente }
     *     
     */
    public InformacionCliente getInformacionCliente() {
        return informacionCliente;
    }

    /**
     * Sets the value of the informacionCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link InformacionCliente }
     *     
     */
    public void setInformacionCliente(InformacionCliente value) {
        this.informacionCliente = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link StatusType }
     *     
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusType }
     *     
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

}
