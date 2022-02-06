
package wsTricomp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetPuntosXTarjetaResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="GetPuntosXTarjetaResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="GetPuntosXTarjetaResult" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
    "getPuntosXTarjetaResult"
})
@XmlRootElement(name = "GetPuntosXTarjetaResponse")
public class GetPuntosXTarjetaResponse {

    @XmlElement(name = "GetPuntosXTarjetaResult", namespace = "http://www.tricomp.com.mx/enterprise/ws/")
    protected double getPuntosXTarjetaResult;

    /**
     * Gets the value of the getPuntosXTarjetaResult property.
     * 
     */
    public double getGetPuntosXTarjetaResult() {
        return getPuntosXTarjetaResult;
    }

    /**
     * Sets the value of the getPuntosXTarjetaResult property.
     * 
     */
    public void setGetPuntosXTarjetaResult(double value) {
        this.getPuntosXTarjetaResult = value;
    }

}
