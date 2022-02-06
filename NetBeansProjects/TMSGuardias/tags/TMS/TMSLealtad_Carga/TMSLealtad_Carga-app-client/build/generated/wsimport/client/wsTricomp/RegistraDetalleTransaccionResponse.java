
package wsTricomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegistraDetalleTransaccionResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="RegistraDetalleTransaccionResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="RegistraDetalleTransaccionResult" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "registraDetalleTransaccionResult"
})
@XmlRootElement(name = "RegistraDetalleTransaccionResponse")
public class RegistraDetalleTransaccionResponse {

    @XmlElement(name = "RegistraDetalleTransaccionResult", namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected long registraDetalleTransaccionResult;

    /**
     * Gets the value of the registraDetalleTransaccionResult property.
     * 
     */
    public long getRegistraDetalleTransaccionResult() {
        return registraDetalleTransaccionResult;
    }

    /**
     * Sets the value of the registraDetalleTransaccionResult property.
     * 
     */
    public void setRegistraDetalleTransaccionResult(long value) {
        this.registraDetalleTransaccionResult = value;
    }

}
