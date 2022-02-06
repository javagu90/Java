
package wsTricomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegistraTransaccionXML element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="RegistraTransaccionXML">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="Strxml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "strxml"
})
@XmlRootElement(name = "RegistraTransaccionXML")
public class RegistraTransaccionXML {

    @XmlElement(name = "Strxml", namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected String strxml;

    /**
     * Gets the value of the strxml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrxml() {
        return strxml;
    }

    /**
     * Sets the value of the strxml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrxml(String value) {
        this.strxml = value;
    }

}
