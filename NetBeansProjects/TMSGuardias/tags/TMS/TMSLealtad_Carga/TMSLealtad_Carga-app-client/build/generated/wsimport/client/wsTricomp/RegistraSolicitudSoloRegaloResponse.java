
package wsTricomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegistraSolicitudSoloRegaloResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="RegistraSolicitudSoloRegaloResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="RegistraSolicitudSoloRegaloResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "registraSolicitudSoloRegaloResult"
})
@XmlRootElement(name = "RegistraSolicitudSoloRegaloResponse")
public class RegistraSolicitudSoloRegaloResponse {

    @XmlElement(name = "RegistraSolicitudSoloRegaloResult", namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected boolean registraSolicitudSoloRegaloResult;

    /**
     * Gets the value of the registraSolicitudSoloRegaloResult property.
     * 
     */
    public boolean isRegistraSolicitudSoloRegaloResult() {
        return registraSolicitudSoloRegaloResult;
    }

    /**
     * Sets the value of the registraSolicitudSoloRegaloResult property.
     * 
     */
    public void setRegistraSolicitudSoloRegaloResult(boolean value) {
        this.registraSolicitudSoloRegaloResult = value;
    }

}
