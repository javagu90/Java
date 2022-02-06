
package wsTricomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegistraSolicitudResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="RegistraSolicitudResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="RegistraSolicitudResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "registraSolicitudResult"
})
@XmlRootElement(name = "RegistraSolicitudResponse")
public class RegistraSolicitudResponse {

    @XmlElement(name = "RegistraSolicitudResult", namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected boolean registraSolicitudResult;

    /**
     * Gets the value of the registraSolicitudResult property.
     * 
     */
    public boolean isRegistraSolicitudResult() {
        return registraSolicitudResult;
    }

    /**
     * Sets the value of the registraSolicitudResult property.
     * 
     */
    public void setRegistraSolicitudResult(boolean value) {
        this.registraSolicitudResult = value;
    }

}
